/*
 * Copyright (C) 2011 The NullVM Open Source Project
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
package org.nullvm.rt.bro;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.nullvm.rt.bro.annotation.Bridge;
import org.nullvm.rt.bro.annotation.Library;

/**
 *
 * @version $Id$
 */
public abstract class Runtime {

    private static final Map<String, Long> handles = new HashMap<String, Long>();
    private static final List<String> searchPaths = new ArrayList<String>();
    
    static {
        String home = System.getProperty("user.home");

        if (Bro.IS_LINUX) {
            String ldLibPath = System.getenv("LD_LIBRARY_PATH");
            if (ldLibPath != null) {
                searchPaths.addAll(Arrays.asList(ldLibPath.split(Pattern.quote(File.pathSeparator))));
            }
        }
        if (Bro.IS_DARWIN) {
            String dyLdFwPath = System.getenv("DYLD_FRAMEWORK_PATH");
            if (dyLdFwPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFwPath.split(Pattern.quote(File.pathSeparator))));
            }
            String dyLdLibPath = System.getenv("DYLD_LIBRARY_PATH");
            if (dyLdLibPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdLibPath.split(Pattern.quote(File.pathSeparator))));
            }
            String dyLdFallbackFwPath = System.getenv("DYLD_FALLBACK_FRAMEWORK_PATH");
            if (dyLdFallbackFwPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFallbackFwPath.split(Pattern.quote(File.pathSeparator))));
            }
            if (home != null) {
                searchPaths.add(new File(home, "Library/Frameworks").getAbsolutePath());
            }
            searchPaths.add("/Library/Frameworks");
            searchPaths.add("/Network/Library/Frameworks");
            searchPaths.add("/System/Library/Frameworks");
            String dyLdFallbackLibPath = System.getenv("DYLD_FALLBACK_LIBRARY_PATH");
            if (dyLdFallbackLibPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFallbackLibPath.split(Pattern.quote(File.pathSeparator))));
            }
            if (home != null) {
                searchPaths.add(new File(home, "lib").getAbsolutePath());
            }
        }
        String basePath = System.getProperty("org.nullvm.base.path");
        if (basePath != null) {
            searchPaths.add(basePath);
        }
        String javaLibPath = System.getProperty("java.library.path");
        if (javaLibPath != null) {
            searchPaths.addAll(Arrays.asList(javaLibPath.split(Pattern.quote(File.pathSeparator))));
        }
        searchPaths.add("/usr/local/lib");
        searchPaths.add("/lib");
        searchPaths.add("/usr/lib");
        
        if (Bro.IS_DARWIN) {
            String dyLdRootPath = System.getenv("DYLD_ROOT_PATH");
            if (dyLdRootPath != null) {
                List<String> oldSearchPaths = new ArrayList<String>(searchPaths);
                searchPaths.clear();
                for (String root : dyLdRootPath.split(Pattern.quote(File.pathSeparator))) {
                    while (root.endsWith("/")) {
                        root = root.substring(0,  root.length() - 1);
                    }
                    for (String path : oldSearchPaths) {
                        File f = new File(root + path);
                        String absPath = f.getAbsolutePath();
                        if (!searchPaths.contains(absPath) && f.exists() && f.isDirectory()) {
                            searchPaths.add(absPath);
                        }
                    }
                }
                searchPaths.addAll(oldSearchPaths);
            }
        }
    }
    
    public static void addSearchPath(String path) {
        searchPaths.add(path);
    }
    
    public abstract void loadLibrary(Library library);
    
    public abstract long resolveBridge(Library library, Bridge bridge, Method method);
    
    protected static long getHandle(Library library) {
        synchronized (handles) {
            Long handle = handles.get(library.value());
            if (handle == null) {
                String libName = System.mapLibraryName(library.value());
                for (String searchPath : searchPaths) {
                    File f = new File(searchPath, libName);
                    if (f.exists()) {
                        handle = Dl.open(f.getAbsolutePath());
                        if (handle != 0L) {
                            break;
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
                    }
                    if (Bro.IS_DARWIN) {
                        File fwDir = new File(searchPath, library.value() + ".framework");
                        if (fwDir.exists()) {
                            f = new File(fwDir, library.value());
                            if (f.exists()) {
                                handle = Dl.open(f.getAbsolutePath());
                                if (handle != 0L) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (handle == null || handle == 0L) {
                    handle = Dl.open(library.value());
                    if (handle == 0L) {
                        handle = Dl.open(libName);
                    }
                }
                if (handle == null || handle == 0L) {
                    throw new UnsatisfiedLinkError("Library '" + library.value() + "' not found");
                }
                handles.put(library.value(), handle);
            }
            return handle;
        }
    }
    
}
