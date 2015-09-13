package simple.unit.testing.dtos;

public class TestReport {
    private String name;
    private String status;
    private String errorDescription;

    public TestReport(String name, String status, String errorDescription) {
        this.name = name;
        this.status = status;
        this.errorDescription = errorDescription;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
