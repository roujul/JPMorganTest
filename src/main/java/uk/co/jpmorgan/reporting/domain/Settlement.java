package uk.co.jpmorgan.reporting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Settlement operated by JP Morgan
 */
public class Settlement {

    /**
     * Date of execution computed from the settlement date
     */
    private LocalDate executionDate;

    /**
     * Price per unit * Units * Agreed Fx
     */
    private BigDecimal tradeAmount;

    /**
     * A financial entity whose shares are to be bought or sold
     */
    private String entity;

    /**
     * Buy/Sell flag:
     o B – Buy – outgoing
     o S – Sell – incoming
     */
    private String instructionType;

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(String instructionType) {
        this.instructionType = instructionType;
    }

    @Override
    public String toString() {
        return "Entity: " +
                entity +
                "\n Settlement type: " +
                instructionType +
                "\n Trade amount: " +
                tradeAmount +
                "\n Execution date: " +
                executionDate +
                "\n Execution day: " +
                executionDate.getDayOfWeek();
    }
}
