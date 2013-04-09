/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Builds a configured class loader. The constructed class loader can load a
 * private copy of a class in addition to the copy in the application class
 * loader. It can also refuse to load a class altogether, even if that class
 * exists on the classpath.
 */
public final class ClassLoaderBuilder {
    private ClassLoader parent = ClassLoaderBuilder.class.getClassLoader();
    private final Set<String> prefixesToNotInherit = new HashSet<String>();
    private final Set<String> prefixesToLoad = new HashSet<String>();

    public ClassLoaderBuilder parent(ClassLoader parent) {
        this.parent = parent;
        return this;
    }

    /**
     * @param classNamePrefix the prefix of classes that can be loaded by both
     *     the constructed class loader and the application class loader.
     */
    public ClassLoaderBuilder withPrivateCopy(String classNamePrefix) {
        prefixesToLoad.add(classNamePrefix);
        return this;
    }

    /**
     * @param classNamePrefix the prefix of classes that will not be loaded by
     *     this class loader. Attempts to load such classes will fail at runtime
     *     with a NoClassDefFoundError.
     */
    public ClassLoaderBuilder without(String classNamePrefix) {
        prefixesToNotInherit.add(classNamePrefix);
        return this;
    }

    public ClassLoader build() {
        // make a defensive copy in case this builder is reused!
        final Set<String> prefixesToNotInherit = new HashSet<String>(this.prefixesToNotInherit);
        final Set<String> prefixesToLoad = new HashSet<String>(this.prefixesToLoad);

        /*
         * To load two copies of a given class in the VM, we end up creating two
         * new class loaders: a bridge class loader and a leaf class loader.
         *
         * The bridge class loader is a child of the application class loader.
         * It never loads any classes. All it does is decide when to delegate to
         * the application class loader (which has a copy of everything) and
         * when to fail.
         *
         * The leaf class loader is a child of the bridge class loader. It
         * uses the same classpath as the application class loader. It loads
         * anything that its parent failed on.
         */

        ClassLoader bridge = new ClassLoader(parent) {
            @Override protected Class<?> loadClass(String className, boolean resolve)
                    throws ClassNotFoundException {
                for (String prefix : prefixesToLoad) {
                    if (className.startsWith(prefix)) {
                        /* ClassNotFoundException causes the child loader to load the class. */
                        throw new ClassNotFoundException();
                    }
                }

                for (String prefix : prefixesToNotInherit) {
                    if (className.startsWith(prefix)) {
                        /* NoClassDefFoundError prevents the class from being loaded at all. */
                        throw new NoClassDefFoundError();
                    }
                }

                return super.loadClass(className, resolve);
            }
        };

        try {
            // first try to create a PathClassLoader for a dalvik VM...
            String classPath = getApplicationClassPath();
            return (ClassLoader) Class.forName("dalvik.system.PathClassLoader")
                    .getConstructor(String.class, ClassLoader.class)
                    .newInstance(classPath, bridge);
        } catch (Exception ignored) {
        }

        // fall back to a URLClassLoader on a JVM
        List<URL> classpath = new ArrayList<URL>();
        classpath.addAll(classpathToUrls("java.class.path"));
        classpath.addAll(classpathToUrls("sun.boot.class.path"));
        return new URLClassLoader(classpath.toArray(new URL[classpath.size()]), bridge);
    }

    /**
     * Returns a path containing the application's classes. When running in the
     * Android framework this will be the APK file; otherwise it's the runtime's
     * reported class path.
     */
    private String getApplicationClassPath() {
        String manifestFile = "AndroidManifest.xml";
        String suffix = "!/" + manifestFile;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL manifest = classLoader.getResource(manifestFile);
        if (manifest != null) {
            String manifestString = manifest.toString();
            if (manifestString.endsWith(suffix)) {
                return manifestString.substring(0, manifestString.length() - suffix.length());
            }
        }
        return System.getProperty("java.class.path");
    }

    private List<URL> classpathToUrls(String propertyName) {
        try {
            String classpath = System.getProperty(propertyName);
            List<URL> result = new ArrayList<URL>();
            for (String pathElement : classpath.split(File.pathSeparator)) {
                result.add(new File(pathElement).toURI().toURL());
            }
            return result;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
