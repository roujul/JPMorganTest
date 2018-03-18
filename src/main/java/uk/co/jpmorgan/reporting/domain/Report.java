package uk.co.jpmorgan.reporting.domain;

import java.math.BigDecimal;

public class Report<V> {

    private V key;
    private BigDecimal amount = BigDecimal.ZERO;

    public Report(V key) {
        this.key = key;
    }

    public void add(BigDecimal amountToAdd) {
        amount = amount.add(amountToAdd);
    }

    public V getKey() {
        return key;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
