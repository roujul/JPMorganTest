package uk.co.jpmorgan.reporting.service;

import uk.co.jpmorgan.reporting.domain.Instruction;
import uk.co.jpmorgan.reporting.domain.Settlement;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

public class SettlementService {

    private static final List<DayOfWeek> REGULAR_DAYS = Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
    private static final List<DayOfWeek> ALTERNATIVE_DAYS = Arrays.asList(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final List<String> ALTERNATIVE_DAY_CURRENCIES = Arrays.asList("AED", "SAR");

    public Settlement addInstruction(Instruction instruction) {
        final Settlement settlement = new Settlement();
        settlement.setEntity(instruction.getEntity());
        settlement.setTradeAmount(calculateTradeAmount(instruction));
        settlement.setExecutionDate(calculateExecutionDate(instruction));
        settlement.setInstructionType(instruction.getInstructionType());
        return settlement;
    }

    private LocalDate calculateExecutionDate(Instruction instruction) {
        LocalDate executionDate = instruction.getSettlementDate();
        if(ALTERNATIVE_DAY_CURRENCIES.contains(instruction.getCurrency())) {
            if(!ALTERNATIVE_DAYS.contains(executionDate.getDayOfWeek())) {
                executionDate = executionDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            }
        } else {
            if(!REGULAR_DAYS.contains(executionDate.getDayOfWeek())) {
                executionDate = executionDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            }
        }
        return executionDate;
    }

    private BigDecimal calculateTradeAmount(Instruction instruction) {
        return instruction.getAgreedFx().multiply(instruction.getPricePerUnit()).multiply(BigDecimal.valueOf(instruction.getUnits()));
    }
}
