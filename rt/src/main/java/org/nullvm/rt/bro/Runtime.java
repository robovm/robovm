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
        if (Bro.IS_LINUX) {
            String ldLibPath = System.getenv("LD_LIBRARY_PATH");
            if (ldLibPath != null) {
                searchPaths.addAll(Arrays.asList(ldLibPath.split(Pattern.quote(File.separator))));
            }
        }
        if (Bro.IS_DARWIN) {
            String dyLdFwPath = System.getenv("DYLD_FRAMEWORK_PATH");
            if (dyLdFwPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFwPath.split(Pattern.quote(File.separator))));
            }
            String dyLdLibPath = System.getenv("DYLD_LIBRARY_PATH");
            if (dyLdLibPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdLibPath.split(Pattern.quote(File.separator))));
            }
            String dyLdFallbackFwPath = System.getenv("DYLD_FALLBACK_FRAMEWORK_PATH");
            if (dyLdFallbackFwPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFallbackFwPath.split(Pattern.quote(File.separator))));
            }
            searchPaths.add("~/Library/Frameworks");
            searchPaths.add("/Library/Frameworks");
            searchPaths.add("/Network/Library/Frameworks");
            searchPaths.add("/System/Library/Frameworks");
            String dyLdFallbackLibPath = System.getenv("DYLD_FALLBACK_LIBRARY_PATH");
            if (dyLdFallbackLibPath != null) {
                searchPaths.addAll(Arrays.asList(dyLdFallbackLibPath.split(Pattern.quote(File.separator))));
            }
            searchPaths.add("~/lib");
        }
        String basePath = System.getProperty("org.nullvm.base.path");
        if (basePath != null) {
            searchPaths.add(basePath);
        }
        searchPaths.addAll(Arrays.asList(System.getProperty("java.library.path", "").split(Pattern.quote(File.separator))));
        searchPaths.add("/usr/local/lib");
        searchPaths.add("/lib");
        searchPaths.add("/usr/lib");
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
                handle = Dl.open(libName);
                if (handle == 0L) {
                    if ("libc.so".equals(libName)) {
                        // Assume glibc 2.x
                        handle = Dl.open("libc.so.6");
                        if (handle == 0L) {
                            handle = Dl.open("libc.so.6.1");
                        }
                    }
                }
                if (handle == 0L) {
                    for (String searchPath : searchPaths) {
                        File f = new File(searchPath, libName);
                        if (f.exists()) {
                            handle = Dl.open(f.getAbsolutePath());
                            if (handle != 0L) {
                                break;
                            }
                        }
                        if (Bro.IS_DARWIN) {
                            File fwDir = new File(searchPath, library.value() + ".framework");
                            if (fwDir.exists()) {
                                handle = Dl.open(new File(fwDir, library.value()).getAbsolutePath());
                                if (handle != 0L) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (handle == 0L) {
                    throw new UnsatisfiedLinkError("Library '" + library.value() + "' not found");
                }
                handles.put(library.value(), handle);
            }
            return handle;
        }
    }
    
}
