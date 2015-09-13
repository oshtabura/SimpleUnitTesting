package simple.unit.testing.core;

import simple.unit.testing.annotations.AfterEach;
import simple.unit.testing.annotations.BeforeEach;
import simple.unit.testing.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class TestClass {
    private final Class testClass;

    private Map<Class, List<TestMethod>> methodsWithAnnotations = new HashMap<Class, List<TestMethod>>();

    public TestClass(Class testClass) {
        this.testClass = testClass;
        for (Method method : this.testClass.getDeclaredMethods())
            addToAnnotationLists(new TestMethod(method), methodsWithAnnotations);
    }

    public List<TestMethod> getTestMethods() {
        return getAnnotatedMethods(Test.class);
    }

    public List<TestMethod> getBeforeEachMethods() {
        return getAnnotatedMethods(BeforeEach.class);
    }

    public List<TestMethod> getAfterEachMethods() {
        return getAnnotatedMethods(AfterEach.class);
    }

    private void addToAnnotationLists(TestMethod testMethod, Map<Class, List<TestMethod>> map) {
        for (Annotation annotation : testMethod.getAnnotations()) {
            Class type = annotation.annotationType();
            List<TestMethod> members = getAnnotatedMembers(map, type);
            members.add(testMethod);
        }
    }

    private List<TestMethod> getAnnotatedMethods(Class annotationClass) {
        List<TestMethod> methods = getAnnotatedMembers(methodsWithAnnotations, annotationClass);
        return methods == null ? Collections.<TestMethod>emptyList() : methods;
    }

    private List<TestMethod> getAnnotatedMembers(Map<Class, List<TestMethod>> map, Class type) {
        if (!map.containsKey(type))
            map.put(type, new ArrayList<TestMethod>());
        return map.get(type);
    }

    public Class getJavaClass() {
        return testClass;
    }

    public String getName() {
        return testClass.getName();
    }
}