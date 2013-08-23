/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.rt.bro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;

/**
 *
 * @version $Id$
 */
public final class Runtime {

    private static final Map<String, Long> handles = new HashMap<String, Long>();
    private static final List<String> searchPaths;
    
    static {
        List<String> paths = new ArrayList<String>();
        String home = System.getProperty("user.home");

        if (Bro.IS_LINUX) {
            String ldLibPath = System.getenv("LD_LIBRARY_PATH");
            if (ldLibPath != null) {
                paths.addAll(Arrays.asList(ldLibPath.split(Pattern.quote(File.pathSeparator))));
            }
            File ldSoConf = new File("/etc/ld.so.conf");
            try {
                readLdSoConf(ldSoConf, paths);
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        if (Bro.IS_DARWIN) {
            String dyLdFwPath = System.getenv("DYLD_FRAMEWORK_PATH");
            if (dyLdFwPath != null) {
                paths.addAll(Arrays.asList(dyLdFwPath.split(Pattern.quote(File.pathSeparator))));
            }
            String dyLdLibPath = System.getenv("DYLD_LIBRARY_PATH");
            if (dyLdLibPath != null) {
                paths.addAll(Arrays.asList(dyLdLibPath.split(Pattern.quote(File.pathSeparator))));
            }
            String dyLdFallbackFwPath = System.getenv("DYLD_FALLBACK_FRAMEWORK_PATH");
            if (dyLdFallbackFwPath != null) {
                paths.addAll(Arrays.asList(dyLdFallbackFwPath.split(Pattern.quote(File.pathSeparator))));
            }
            if (home != null) {
                paths.add(new File(home, "Library/Frameworks").getAbsolutePath());
            }
            paths.add("/Library/Frameworks");
            paths.add("/Network/Library/Frameworks");
            paths.add("/System/Library/Frameworks");
            String dyLdFallbackLibPath = System.getenv("DYLD_FALLBACK_LIBRARY_PATH");
            if (dyLdFallbackLibPath != null) {
                paths.addAll(Arrays.asList(dyLdFallbackLibPath.split(Pattern.quote(File.pathSeparator))));
            }
            if (home != null) {
                paths.add(new File(home, "lib").getAbsolutePath());
            }
        }
        String basePath = System.getProperty("org.robovm.base.path");
        if (basePath != null) {
            paths.add(basePath);
        }
        String javaLibPath = System.getProperty("java.library.path");
        if (javaLibPath != null) {
            paths.addAll(Arrays.asList(javaLibPath.split(Pattern.quote(File.pathSeparator))));
        }
        paths.add("/usr/local/lib");
        paths.add("/lib");
        paths.add("/usr/lib");
        
        if (Bro.IS_DARWIN) {
            paths.add("/usr/lib/system");
            String dyLdRootPath = System.getenv("DYLD_ROOT_PATH");
            if (dyLdRootPath != null) {
                List<String> oldSearchPaths = new ArrayList<String>(paths);
                paths.clear();
                for (String root : dyLdRootPath.split(Pattern.quote(File.pathSeparator))) {
                    while (root.endsWith("/")) {
                        root = root.substring(0,  root.length() - 1);
                    }
                    for (String path : oldSearchPaths) {
                        File f = new File(root + path);
                        String absPath = f.getAbsolutePath();
                        if (!paths.contains(absPath) && f.exists() && f.isDirectory()) {
                            paths.add(absPath);
                        }
                    }
                }
                paths.addAll(oldSearchPaths);
            }
        }
        
        searchPaths = uniq(paths);
    }

    private static List<String> uniq(List<String> l) {
        Set<String> seen = new HashSet<String>();
        List<String> result = new ArrayList<String>();
        for (String s : l) {
            if (!seen.contains(s)) {
                result.add(s);
                seen.add(s);
            }
        }
        return result;
    }
    
    private static void readLdSoConf(File f, List<String> paths) throws IOException {
        if (!f.exists() || !f.isFile()) {
            return;
        }
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("/")) {
                    paths.add(line);
                } else if (line.startsWith("include ") || line.startsWith("include\t")) {
                    String pattern = line.substring("include ".length()).trim();
                    int wcIdx = pattern.indexOf('*');
                    if (wcIdx != -1) {
                        File dir = new File(pattern.substring(0, wcIdx));
                        if (dir.exists() && dir.isDirectory()) {
                            for (File child : dir.listFiles()) {
                                if (wcIdx == pattern.length() - 1 
                                        || child.getName().endsWith(pattern.substring(wcIdx + 1))) {
                                    readLdSoConf(child, paths);
                                }
                            }
                        }
                    } else {
                        readLdSoConf(new File(pattern), paths);
                    }
                }
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Throwable t) {}
            }
        }

    }
    
    public static void addSearchPath(String path) {
        searchPaths.add(path);
    }
    
    public static void loadLibrary(String name) {
        getHandle(name);
    }
    
    public static void loadLibrary(Library library) {
        getHandle(library.value());
    }
    
    public static long resolveBridge(Library library, Bridge bridge, Method method) {
        if (library == null) {
            throw new IllegalArgumentException("No @" + Library.class.getName() 
                    + " annotation found on class " + method.getDeclaringClass().getName());
        }
        
        long handle = getHandle(library.value());
        String symbol = bridge.symbol();
        if (symbol == null || "".equals(symbol)) {
            symbol = method.getName();
        }
        long f = Dl.resolve(handle, symbol);
        if (f == 0L) {
            throw new UnsatisfiedLinkError("Failed to resolve native function " 
                    + "for method " + method + " with bridge annotation " + bridge 
                    + " in library " + library);
        }
        return f;
    }

    public static long resolveBridge(String libraryName, String symbol, Method method) {
        long handle = getHandle(libraryName);
        long f = Dl.resolve(handle, symbol);
        if (f == 0L) {
            throw new UnsatisfiedLinkError("Failed to resolve native function " + symbol
                    + "for method " + method + " in library " + libraryName);
        }
        return f;
    }

    protected static long getHandle(String name) {
        synchronized (handles) {
            Long handle = handles.get(name);
            if (handle == null) {
                if (Library.INTERNAL.equals(name)) {
                    handle = Dl.open(null);
                } else {
                    String libName = System.mapLibraryName(name);
                    for (String searchPath : searchPaths) {
                        File f = new File(searchPath, libName);
                        if (f.exists()) {
                            handle = Dl.open(f.getAbsolutePath());
                            if (handle != 0L) {
                                break;
                            }
                        }
                        if ("libc.so".equals(libName)) {
                            // Assume glibc 2.x
                            f = new File(searchPath, "libc.so.6");
                            if (f.exists()) {
                                handle = Dl.open(f.getAbsolutePath());
                                if (handle != 0L) {
                                    break;
                                }
                            }
                            f = new File(searchPath, "libc.so.6.1");
                            if (f.exists()) {
                                handle = Dl.open(f.getAbsolutePath());
                                if (handle != 0L) {
                                    break;
                                }
                            }
                        }
                        if (Bro.IS_DARWIN) {
                            File fwDir = new File(searchPath, name + ".framework");
                            if (fwDir.exists()) {
                                f = new File(fwDir, name);
                                handle = Dl.open(f.getAbsolutePath());
                                if (handle != 0L) {
                                    break;
                                }
                            }
                        }
                    }
                    if (handle == null || handle == 0L) {
                        handle = Dl.open(name);
                        if (handle == 0L) {
                            handle = Dl.open(libName);
                        }
                    }
                }
                if (handle == null || handle == 0L) {
                    throw new UnsatisfiedLinkError("Library '" + name + "' not found");
                }
                handles.put(name, handle);
            }
            return handle;
        }
    }
    
}
