package uk.co.jpmorgan.reporting.builder;

import uk.co.jpmorgan.reporting.domain.Instruction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SettlementBuilder {

    private final Instruction instruction;

    public SettlementBuilder() {
        instruction = new Instruction();
        instruction.setEntity("default entity");
        instruction.setInstructionType("B");
        instruction.setAgreedFx(BigDecimal.valueOf(0.5));
        instruction.setCurrency("GBP");
        instruction.setInstructionDate(LocalDate.now());
        instruction.setSettlementDate(LocalDate.now());
        instruction.setUnits(100);
        instruction.setPricePerUnit(BigDecimal.valueOf(12.5));
    }

    public SettlementBuilder withEntity(String entity) {
        instruction.setEntity(entity);
        return this;
    }

    public SettlementBuilder withInstructionType(String instructionType) {
        instruction.setInstructionType(instructionType);
        return this;
    }

    public SettlementBuilder withAgreedFx(BigDecimal agreedFx) {
        instruction.setAgreedFx(agreedFx);
        return this;
    }

    public SettlementBuilder withCurrency(String currency) {
        instruction.setCurrency(currency);
        return this;
    }

    public SettlementBuilder withInstructionDate(LocalDate instructionDate) {
        instruction.setInstructionDate(instructionDate);
        return this;
    }

    public SettlementBuilder withSettlementDate(LocalDate settlementDate) {
        instruction.setSettlementDate(settlementDate);
        return this;
    }

    public SettlementBuilder withUnits(int units) {
        instruction.setUnits(units);
        return this;
    }

    public SettlementBuilder withPricePerUnit(BigDecimal pricePerUnit) {
        instruction.setPricePerUnit(pricePerUnit);
        return this;
    }




    public Instruction build() {
        return instruction;
    }
}
