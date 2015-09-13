package simple.unit.testing.core;

import java.util.LinkedList;
import java.util.List;

public class TestResult {
    private int totalNumberOfTests;
    private List<TestFailure> failuresTests;
    private List<TestFailure> errorsTests;
    private List<Test> successfulTests;
    private List<Test> ignoredTests;
    private int successfulTestsCounter;

    public TestResult() {
        this.totalNumberOfTests = 0;
        this.failuresTests = new LinkedList<TestFailure>();
        this.errorsTests = new LinkedList<TestFailure>();
        this.successfulTests = new LinkedList<Test>();
        this.ignoredTests = new LinkedList<Test>();
        this.successfulTestsCounter = 0;
    }

    public void startTest(Test test) {
        totalNumberOfTests++;
        successfulTestsCounter++;
        successfulTests.add(test);
    }

    public void addIgnore(Test test) {
        totalNumberOfTests++;
        this.ignoredTests.add(test);
    }

    public void addError(Test test, Throwable error) {
        errorsTests.add(new TestFailure(test, error));
        successfulTests.remove(--successfulTestsCounter);
    }

    public void addFailure(Test test, Throwable failure) {
        failuresTests.add(new TestFailure(test, failure));
        successfulTests.remove(--successfulTestsCounter);
    }

    public int getTotalNumberOfTests() {
        return totalNumberOfTests;
    }

    public List<TestFailure> getFailuresTests() {
        return failuresTests;
    }

    public List<TestFailure> getErrorsTests() {
        return errorsTests;
    }

    public List<Test> getSuccessfulTests() {
        return successfulTests;
    }

    public int getSuccessfulTestsCounter() {
        return successfulTestsCounter;
    }

    public int getErrorsTestsCounter() {
        return errorsTests.size();
    }

    public int getFailuresTestsCounter() {
        return failuresTests.size();
    }

    public int getIgnoredTestsCounter() {
        return ignoredTests.size();
    }

    public List<Test> getIgnoredTests() {
        return ignoredTests;
    }
}
