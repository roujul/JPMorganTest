package uk.co.jpmorgan.reporting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Instruction sent by a client to JP Morgan
 */
public class Instruction {

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

    /**
     * foreign exchange rate with respect to USD that was agreed
     */
    private BigDecimal agreedFx;

    /**
     * Currency of the trade
     */
    private String currency;

    /**
     * Date on which the instruction was sent to JP Morgan by various clients
     */
    private LocalDate instructionDate;

    /**
     * The date on which the client wished for the instruction to be settled with respect to Instruction Date
     */
    private LocalDate settlementDate;

    /**
     * Number of shares to be bought or sold
     */
    private int units;

    /**
     * Price per unit
     */
    private BigDecimal pricePerUnit;

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

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Entity: " +
                entity +
                "\n Settlement type: " +
                instructionType +
                "\n Agreed FX: " +
                agreedFx +
                "\n Currency: " +
                currency +
                "\n Settlement date: " +
                instructionDate +
                "\n Settlement date: " +
                settlementDate +
                "\n Settlement day: " +
                settlementDate.getDayOfWeek() +
                "\n Units: " +
                units +
                "\n Price per unit: " +
                pricePerUnit;
    }
}