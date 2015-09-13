package simple.unit.testing.core;

import java.util.List;

public class BaseTestsRunner implements TestsRunner {
    private List<Class> classes;

    public BaseTestsRunner(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public TestResult start() {
        TestResult result = new TestResult();
        Test testSuite = new TestSuite(classes);
        testSuite.run(result);
        return result;
    }
}
