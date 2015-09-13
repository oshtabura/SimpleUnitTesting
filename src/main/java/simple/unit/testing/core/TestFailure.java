package simple.unit.testing.core;

public class TestFailure {
    private Test failedTest;
    private Throwable thrownException;

    public TestFailure(Test failedTest, Throwable thrownException) {
        this.failedTest = failedTest;
        this.thrownException = thrownException;
    }

    public Test getFailedTest() {
        return failedTest;
    }

    public Throwable getThrownException() {
        return thrownException;
    }
}
