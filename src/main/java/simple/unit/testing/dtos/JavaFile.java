package simple.unit.testing.dtos;

import java.io.File;

public class JavaFile {
    private File file;
    private String fullClassName;

    public JavaFile(File file, String fullClassName) {
        this.file = file;
        this.fullClassName = fullClassName;
    }

    public File getFile() {
        return file;
    }

    public String getFullClassName() {
        return fullClassName;
    }
}
