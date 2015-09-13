package simple.unit.testing;

import freemarker.template.TemplateException;
import simple.unit.testing.core.BaseTestsRunner;
import simple.unit.testing.core.ResultReport;
import simple.unit.testing.core.TestResult;
import simple.unit.testing.core.TestsRunner;
import simple.unit.testing.dtos.JavaFiles;
import simple.unit.testing.util.Helper;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException, TemplateException {
        File directory = Helper.getDirectoryFromArguments(args);
        JavaFiles javaFiles = Helper.getAllJavaFilesFromPath(directory);

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        Iterable<? extends JavaFileObject> compilationUnit = fileManager.getJavaFileObjectsFromFiles(javaFiles.getJavaFiles());
        JavaCompiler.CompilationTask compileTask = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnit);

        if (compileTask.call()) {
            List<Class> classes = Helper.getClasses(directory, javaFiles);

            TestsRunner runner = new BaseTestsRunner(classes);
            TestResult result = runner.start();
            ResultReport report = new ResultReport(result);
            report.createReport();
        } else {
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                System.out.format("Error on line %d in %s%n", diagnostic.getLineNumber(), diagnostic.getSource().toUri());
            }
        }
        fileManager.close();
    }
}
