package simple.unit.testing.core;

import java.lang.reflect.InvocationTargetException;

public class TestCase implements Test {
    private TestClass testClass;
    private TestMethod testMethod;
    private Object instanceOfTestClass;

    public TestCase(TestClass testClass, TestMethod testMethod) throws IllegalAccessException, InstantiationException {
        this.testClass = testClass;
        this.testMethod = testMethod;
        instanceOfTestClass = testClass.getJavaClass().newInstance();
    }

    @Override
    public void run(TestResult testResult) {
        if (testMethod.isIgnored()) {
            testResult.addIgnore(this);
        } else {
            testResult.startTest(this);
            try {
                setUp();
                runTest();
                tearDown();
            } catch (Throwable error) {
                Throwable caused = error.getCause();
                if (caused instanceof AssertionFailedError) {
                    testResult.addFailure(this, caused);
                } else {
                    testResult.addError(this, caused);
                }
            }
        }
    }

    public String getFullyQualifiedName() {
        return testClass.getName() + '.' + testMethod.getName();
    }

    private void runTest() throws InvocationTargetException, IllegalAccessException {
        testMethod.getMethod().invoke(instanceOfTestClass);
    }

    private void setUp() throws InvocationTargetException, IllegalAccessException {
        for (TestMethod method : testClass.getBeforeEachMethods()) {
            method.getMethod().invoke(instanceOfTestClass);
        }
    }

    private void tearDown() throws InvocationTargetException, IllegalAccessException {
        for (TestMethod method : testClass.getAfterEachMethods()) {
            method.getMethod().invoke(instanceOfTestClass);
        }
    }
}
