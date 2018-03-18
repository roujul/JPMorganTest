package uk.co.jpmorgan.reporting.domain;

import uk.co.jpmorgan.reporting.utils.FormatUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Report based on date and amount, for daily USD settlements
 */
public class DailyReport extends Report<LocalDate> {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE dd MMM yyyy").withLocale(Locale.UK);

    public DailyReport(LocalDate key) {
        super(key);
    }

    @Override
    public String toString() {
        return FormatUtils.formatLine(DATE_FORMATTER.format(getKey()), getAmount());
    }
}
