package simple.unit.testing.core;

import java.util.LinkedList;
import java.util.List;

public class TestSuite implements Test {
    private List<Test> tests = new LinkedList<Test>();

    public TestSuite(List<Class> classes) {
        for (Class currentClass : classes) {
            tests.add(new TestSuite(currentClass));
        }
    }

    public TestSuite(Class cls) {
        TestClass testClass = new TestClass(cls);
        for (TestMethod testMethod : testClass.getTestMethods()) {
            try {
                tests.add(new TestCase(testClass, testMethod));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(TestResult result) {
        for (Test test : tests) {
            test.run(result);
        }
    }
}
