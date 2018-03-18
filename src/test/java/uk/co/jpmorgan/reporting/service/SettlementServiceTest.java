package uk.co.jpmorgan.reporting.service;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.co.jpmorgan.reporting.builder.SettlementBuilder;
import uk.co.jpmorgan.reporting.domain.Settlement;
import uk.co.jpmorgan.reporting.domain.Instruction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.fest.assertions.Assertions.assertThat;

public class SettlementServiceTest {

    private static final LocalDate SATURDAY = LocalDate.of(2018, 3, 17);
    private static final LocalDate SUNDAY = LocalDate.of(2018, 3, 18);
    private static final LocalDate MONDAY = LocalDate.of(2018, 3, 19);
    private static final LocalDate TUESDAY = LocalDate.of(2018, 3, 20);

    private SettlementService instructionService;

    @BeforeMethod
    public void before() {
        instructionService = new SettlementService();
    }

    @Test
    public void convert_instruction_to_settlement() {
        //Given
        Instruction instruction = new SettlementBuilder()
                .withEntity("Entity")
                .withCurrency("GBP")
                .withInstructionType("B")
                .withAgreedFx(BigDecimal.valueOf(2.5))
                .withInstructionDate(LocalDate.now())
                .withSettlementDate(TUESDAY)
                .withUnits(12)
                .withPricePerUnit(BigDecimal.valueOf(200.5))
                .build();

        //When
        Settlement settlement = instructionService.addInstruction(instruction);

        //Then
        assertThat(settlement.getInstructionType()).isEqualTo(instruction.getInstructionType());
        assertThat(settlement.getEntity()).isEqualTo(instruction.getEntity());
        assertThat(settlement.getExecutionDate()).isEqualTo(TUESDAY);
        assertThat(settlement.getTradeAmount().doubleValue()).isEqualTo(6015);
    }

    @Test
    public void convert_sunday_gbp_instruction_to_settlement() {
        //Given
        Instruction instruction = new SettlementBuilder()
                .withSettlementDate(SUNDAY)
                .withCurrency("GBP")
                .build();

        //When
        Settlement settlement = instructionService.addInstruction(instruction);

        //Then
        assertThat(settlement.getExecutionDate()).isEqualTo(MONDAY);
    }

    @Test
    public void convert_sunday_aed_instruction_to_settlement() {
        //Given
        Instruction instruction = new SettlementBuilder()
                .withSettlementDate(SUNDAY)
                .withCurrency("AED")
                .build();

        //When
        Settlement settlement = instructionService.addInstruction(instruction);

        //Then
        assertThat(settlement.getExecutionDate()).isEqualTo(SUNDAY);
    }

    @Test
    public void convert_saturday_aed_instruction_to_settlement() {
        //Given
        Instruction instruction = new SettlementBuilder()
                .withSettlementDate(SATURDAY)
                .withCurrency("AED")
                .build();

        //When
        Settlement settlement = instructionService.addInstruction(instruction);

        //Then
        assertThat(settlement.getExecutionDate()).isEqualTo(SUNDAY);
    }

}
