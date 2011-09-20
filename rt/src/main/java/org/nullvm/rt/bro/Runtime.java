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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @version $Id$
 */
public abstract class Runtime {

    private static final Map<String, Long> handles = new HashMap<String, Long>();
    
    public abstract long resolveBridge(Library library, Bridge bridge, Method method);
    
    protected static long getHandle(Library library) {
        synchronized (handles) {
            Long handle = handles.get(library.value());
            if (handle == null) {
                String libName = mapLibraryName(library.value());
                handle = Dl.open(libName);
                if (handle == 0L) {
                    throw new UnsatisfiedLinkError("Library " + libName + " not found");
                }
                handles.put(library.value(), handle);
            }
            return handle;
        }
    }
    
    protected static String mapLibraryName(String name) {
        String libName = System.mapLibraryName(name);
        if ("libc.so".equals(libName)) {
            // Assume glibc 2.x
            return "libc.so.6";
        }
        return libName;
    }
}
