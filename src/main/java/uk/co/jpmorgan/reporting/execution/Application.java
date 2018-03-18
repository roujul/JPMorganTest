package uk.co.jpmorgan.reporting.execution;


import uk.co.jpmorgan.reporting.service.ReportService;

public class Application {

    public static void main(String[] args) {
        ReportService reportService = new ReportService(System.out);
        reportService.loadInstructions("instructions.json");
        reportService.printEveryReport();
    }
}
