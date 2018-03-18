package uk.co.jpmorgan.reporting.service;

import uk.co.jpmorgan.reporting.domain.Instruction;
import uk.co.jpmorgan.reporting.domain.Settlement;

import java.io.PrintStream;
import java.util.List;

public class ReportService {

    private final DailyReportService dailyReportService;
    private final EntityReportService entityReportService;
    private PrintStream printstream;

    public ReportService(PrintStream printstream) {
        this.printstream = printstream;
        this.entityReportService = new EntityReportService(printstream);
        this.dailyReportService = new DailyReportService(printstream);
    }

    public void loadInstructions(String url) {
        List<Instruction> instructions = new ReaderService().readInstructions(url);
        instructions.forEach(instructionDto -> {
            Settlement settlement = new SettlementService().addInstruction(instructionDto);
            dailyReportService.addSettlement(settlement);
            entityReportService.addSettlement(settlement);
        });
    }

    public void printEveryReport() {
        printTitle("Daily Incomings");
        dailyReportService.pritnIncomingReport();
        printTitle("Daily Outgoings");
        dailyReportService.printOutgoingReport();
        printTitle("Entities Incomings");
        entityReportService.pritnIncomingReport();
        printTitle("Entities Outgoings");
        entityReportService.printOutgoingReport();
    }

    private void printTitle(String title) {
        printstream.println("\n======================================");
        printstream.println(title);
        printstream.println("======================================");
    }



}