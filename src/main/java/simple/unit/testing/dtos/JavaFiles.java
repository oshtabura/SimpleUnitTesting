package simple.unit.testing.dtos;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class JavaFiles {
    private List<JavaFile> files;

    public JavaFiles() {
        this.files = new LinkedList<JavaFile>();
    }

    public void add(JavaFile file) {
        files.add(file);
    }

    public List<File> getJavaFiles() {
        List<File> javaFiles = new LinkedList<File>();
        for (JavaFile file : files) {
            javaFiles.add(file.getFile());
        }
        return javaFiles;
    }

    public List<String> getFullClassNames() {
        List<String> fullClassNames = new LinkedList<String>();
        for (JavaFile file : files) {
            fullClassNames.add(file.getFullClassName());
        }
        return fullClassNames;
    }
}
