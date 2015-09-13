package simple.unit.testing.core;

import simple.unit.testing.annotations.Ignore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestMethod {
    private Method method;

    public TestMethod(Method method) {
        this.method = method;
    }

    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    public Method getMethod() {
        return method;
    }

    public String getName() {
        return method.getName();
    }

    public boolean isIgnored() {
        return method.isAnnotationPresent(Ignore.class);
    }
}
