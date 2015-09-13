package simple.unit.testing.core;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import simple.unit.testing.dtos.TestReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultReport {
    private TestResult result;

    public ResultReport(TestResult result) {
        this.result = result;
    }

    public void createReport() throws IOException, TemplateException {
        Template template = loadTemplate();
        Map<String, Object> data = generateDataFromTestResult();
        writeTestsResultToFile(template, data);
    }

    private void writeTestsResultToFile(Template template, Map<String, Object> data) throws IOException, TemplateException {
        Writer fileWriter = new FileWriter(new File("TestsResult.html"));
        try {
            template.process(data, fileWriter);
        } finally {
            fileWriter.close();
        }
    }

    private Template loadTemplate() throws IOException {
        Configuration cfg = new Configuration();
        TemplateLoader templateLoader = new ClassTemplateLoader(getClass(), "/");
        cfg.setTemplateLoader(templateLoader);
        return cfg.getTemplate("templates/ResultReport.ftl");
    }

    private Map<String, Object> generateDataFromTestResult() {
        Map<String, Object> input = new HashMap<String, Object>();
        List<TestReport> tests = new ArrayList<TestReport>();
        addFailedTests(tests, result.getErrorsTests());
        addFailedTests(tests, result.getFailuresTests());
        addTests(tests, result.getIgnoredTests(), "Ignored");
        addTests(tests, result.getSuccessfulTests(), "Successful");
        input.put("tests", tests);
        input.put("total", result.getTotalNumberOfTests());
        input.put("successful", result.getSuccessfulTestsCounter());
        input.put("ignored", result.getIgnoredTestsCounter());
        input.put("failed", result.getFailuresTestsCounter());
        input.put("errors", result.getErrorsTestsCounter());
        return input;
    }

    private void addFailedTests(List<TestReport> reportTests, List<TestFailure> failedTests) {
        for (TestFailure failedTest : failedTests) {
            TestCase testCase = (TestCase) failedTest.getFailedTest();
            String message = failedTest.getThrownException().getMessage();
            reportTests.add(new TestReport(testCase.getFullyQualifiedName(), "Failed", message == null ? "" : message));
        }
    }

    private void addTests(List<TestReport> reportTests, List<Test> tests, String status) {
        for (Test test : tests) {
            TestCase testCase = (TestCase) test;
            reportTests.add(new TestReport(testCase.getFullyQualifiedName(), status, ""));
        }
    }
}
