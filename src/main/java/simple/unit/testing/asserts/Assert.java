package simple.unit.testing.asserts;


import simple.unit.testing.core.AssertionFailedError;

public class Assert {
    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;
        throwNotEqualsError(message, expected, actual);
    }

    public static void assertEquals(String message, String expected, String actual) {
        if (expected == null && actual == null)
            return;
        if (expected != null && expected.equals(actual))
            return;
        throwNotEqualsError(message, expected, actual);
    }

    public static void assertEquals(String expected, String actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, double expected, double actual) {
        if (Double.compare(expected, actual) == 0)
            return;
        throwNotEqualsError(message, expected, actual);
    }

    public static void assertEquals(double expected, double actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, float expected, float actual) {
        if (Float.compare(expected, actual) == 0)
            return;
        throwNotEqualsError(message, expected, actual);
    }

    public static void assertEquals(float expected, float actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, long expected, long actual) {
        assertEquals(message, new Long(expected), new Long(actual));
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, boolean expected, boolean actual) {
        assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual));
    }

    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, new Byte(expected), new Byte(actual));
    }

    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, char expected, char actual) {
        assertEquals(message, new Character(expected), new Character(actual));
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, short expected, short actual) {
        assertEquals(message, new Short(expected), new Short(actual));
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, int expected, int actual) {
        assertEquals(message, new Integer(expected), new Integer(actual));
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition)
            throwError(message);
    }

    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    public static void fail() {
        throwError(null);
    }

    public static void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    public static void assertNull(Object object) {
        assertNull(null, object);
    }

    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    private static void throwNotEqualsError(String message, Object expected, Object actual) {
        String errorDescription = message == null ? "" : message + " ";
        throwError(errorDescription + "expected - " + expected + ", actual - " + actual);
    }

    private static void throwError(String message) {
        if (message == null) {
            throw new AssertionFailedError();
        }
        throw new AssertionFailedError(message);
    }
}