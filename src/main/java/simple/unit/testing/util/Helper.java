package simple.unit.testing.util;

import simple.unit.testing.dtos.JavaFile;
import simple.unit.testing.dtos.JavaFiles;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

public class Helper {

    public static File getDirectoryFromArguments(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Illegal number of arguments");
        }
        File directory = new File(args[0]);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Please specify folder path for unit tests");
        }
        return directory;
    }

    public static List<Class> getClasses(File directory, JavaFiles javaFiles) throws MalformedURLException, ClassNotFoundException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{directory.toURI().toURL()});
        List<Class> classes = new LinkedList<Class>();
        for (String className : javaFiles.getFullClassNames()) {
            classes.add(classLoader.loadClass(className));
        }
        return classes;
    }

    public static JavaFiles getAllJavaFilesFromPath(File dir) {
        JavaFiles javaFiles = new JavaFiles();
        loadAllJavaFilesFromPath(dir, javaFiles, "");
        return javaFiles;
    }

    private static void loadAllJavaFilesFromPath(File dir, JavaFiles javaFiles, String path) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                javaFiles.add(new JavaFile(file, path + stripSuffix(file.getName())));
            } else if (file.isDirectory()) {
                loadAllJavaFilesFromPath(file, javaFiles, path + file.getName() + '.');
            }
        }
    }

    private static String stripSuffix(String string) {
        return string.substring(0, string.lastIndexOf('.'));
    }
}
