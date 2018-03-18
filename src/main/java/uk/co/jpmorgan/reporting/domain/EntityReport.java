package uk.co.jpmorgan.reporting.domain;

import uk.co.jpmorgan.reporting.utils.FormatUtils;

/**
 * Report based on entity name and amount, for entities ranking
 */
public class EntityReport extends Report<String> {

    public EntityReport(String key) {
        super(key);
    }

    @Override
    public String toString() {
        return FormatUtils.formatLine(getKey(), getAmount());
    }
}
