package uk.co.jpmorgan.reporting.service;

import uk.co.jpmorgan.reporting.domain.Report;
import uk.co.jpmorgan.reporting.domain.Settlement;
import uk.co.jpmorgan.reporting.utils.FormatUtils;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * To be extended to created dedicated reports (Entities, Daily, Currencies, etc.)
 */
public abstract class GenericReportService<T, V extends Report> {

    private static final String BUY_OUTGOING = "B";
    private Map<T, V> incomings = new HashMap<>();
    private Map<T, V> outgoings = new HashMap<>();
    private PrintStream printstream;

    public GenericReportService(PrintStream printstream) {
        this.printstream = printstream;
    }

    abstract void addSettlement(Settlement settlement);

    abstract Comparator<V> getComparator();

    void pritnIncomingReport() {
        printReport(incomings);
    }

    void printOutgoingReport() {
        printReport(outgoings);
    }

    Map<T, V> getReports(String instructionType) {
        if (BUY_OUTGOING.equals(instructionType)) {
            return outgoings;
        }
        return incomings;
    }

    private void printReport(Map<T, V> dailyReports) {
        dailyReports.values().stream().sorted(getComparator()).forEach(printstream::println);
        printTotal(dailyReports);
    }

    private void printTotal(Map<T, V> reports) {
        BigDecimal total = reports.values().stream()
                .map(V::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        printstream.println("--------------------------------------");
        printstream.println(FormatUtils.formatLine("Total", total));
    }

}
