package uk.co.jpmorgan.reporting.service;

import uk.co.jpmorgan.reporting.domain.EntityReport;
import uk.co.jpmorgan.reporting.domain.Settlement;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.Map;

public class EntityReportService extends GenericReportService<String, EntityReport> {

    private static final Comparator<EntityReport> COMPARATOR = (Comparator.comparingDouble(e -> e.getAmount().doubleValue()));

    EntityReportService(PrintStream printstream) {
        super(printstream);
    }

    @Override
    void addSettlement(Settlement settlement) {
        Map<String, EntityReport> reportes = getReports(settlement.getInstructionType());
        EntityReport entityReport = getEntityReport(settlement.getEntity(), reportes);
        entityReport.add(settlement.getTradeAmount());
    }

    @Override
    Comparator<EntityReport> getComparator() {
        return COMPARATOR.reversed();
    }

    private EntityReport getEntityReport(String entity, Map<String, EntityReport> entities) {
        EntityReport report = entities.get(entity);
        if(report == null) {
            report = new EntityReport(entity);
            entities.put(entity, report);
        }
        return report;
    }

}
