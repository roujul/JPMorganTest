package uk.co.jpmorgan.reporting.utils;

import java.math.BigDecimal;

public class FormatUtils {

    private FormatUtils() {
    }

    private static String formatAmount(BigDecimal amount) {
        return String.format("%1$15s", amount.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private static String formatlabel(String label) {
        return String.format("%-16s", label);
    }

    public static String formatLine(String label, BigDecimal amount) {
        return String.format("%s: %s USD", formatlabel(label), formatAmount(amount));
    }
}
