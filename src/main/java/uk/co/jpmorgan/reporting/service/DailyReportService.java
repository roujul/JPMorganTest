package uk.co.jpmorgan.reporting.service;

import uk.co.jpmorgan.reporting.domain.DailyReport;
import uk.co.jpmorgan.reporting.domain.Settlement;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;

public class DailyReportService extends GenericReportService<LocalDate, DailyReport> {

    private static final Comparator<DailyReport> COMPARATOR = Comparator.comparingLong(e -> e.getKey().toEpochDay());

    DailyReportService(PrintStream printstream) {
        super(printstream);
    }

    @Override
    void addSettlement(Settlement settlement) {
        Map<LocalDate, DailyReport> reports = getReports(settlement.getInstructionType());
        DailyReport reportDTO = getDailyReport(settlement.getExecutionDate(), reports);
        reportDTO.add(settlement.getTradeAmount());
    }

    @Override
    Comparator<DailyReport> getComparator() {
        return COMPARATOR;
    }

    private DailyReport getDailyReport(LocalDate executionDate, Map<LocalDate, DailyReport> reports) {
        DailyReport report = reports.get(executionDate);
        if(report == null) {
            report = new DailyReport(executionDate);
            reports.put(executionDate, report);
        }
        return report;
    }

}
