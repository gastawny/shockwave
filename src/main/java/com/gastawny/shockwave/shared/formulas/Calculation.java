package com.gastawny.shockwave.shared.formulas;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Calculation {

    private static volatile List<Class<? extends Calculable>> processorClasses = null;

    public static String runAll(String input) {
        if (processorClasses == null) {
            synchronized (Calculation.class) {
                if (processorClasses == null) {
                    List<Class<? extends Calculable>> classes = new ArrayList<>();
                    String packageName = "com.gastawny.shockwave.shared.formulas.calculations";
                    try {
                        for (Class<?> cls : findClasses(packageName)) {
                            if (Calculable.class.isAssignableFrom(cls) && !Modifier.isAbstract(cls.getModifiers())) {
                                @SuppressWarnings("unchecked")
                                Class<? extends Calculable> c = (Class<? extends Calculable>) cls;
                                classes.add(c);
                            }
                        }
                    } catch (IOException _) {
                    }
                    processorClasses = Collections.unmodifiableList(classes);
                }
            }
        }

        String res = input;
        for (Class<? extends Calculable> procClass : processorClasses) {
            try {
                Calculable inst = procClass.getDeclaredConstructor().newInstance();
                res = inst.execute(res);
            } catch (Throwable _) {
            }
        }

        return res;
    }

    private static List<Class<?>> findClasses(String packageName) throws IOException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = cl.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String protocol = resource.getProtocol();
            try {
                if ("file".equals(protocol)) {
                    URI uri = resource.toURI();
                    File dir = new File(uri);
                    findClassesInDirectory(packageName, dir, classes);
                } else if ("jar".equals(protocol)) {
                    JarURLConnection conn = (JarURLConnection) resource.openConnection();
                    try (JarFile jar = conn.getJarFile()) {
                        findClassesInJar(path, jar, classes);
                    }
                }
            } catch (Exception _) {
            }
        }
        return classes;
    }

    private static void findClassesInDirectory(String packageName, File directory, List<Class<?>> classes) {
        if (!directory.exists()) return;
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                findClassesInDirectory(packageName + "." + file.getName(), file, classes);
            } else if (file.getName().endsWith(".class") && !file.getName().contains("$")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (Throwable ignored) {
                }
            }
        }
    }

    private static void findClassesInJar(String path, JarFile jar, List<Class<?>> classes) {
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.startsWith(path) && name.endsWith(".class") && !name.contains("$")) {
                String className = name.replace('/', '.').substring(0, name.length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (Throwable ignored) {
                }
            }
        }
    }
}
