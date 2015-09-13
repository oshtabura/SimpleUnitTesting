package simple.unit.testing.core;

public class AssertionFailedError extends Error {
    public AssertionFailedError() {
    }

    public AssertionFailedError(String message) {
        super(message);
    }

}
