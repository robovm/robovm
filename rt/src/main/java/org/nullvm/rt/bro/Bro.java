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

import org.nullvm.rt.VM;

/**
 *
 * @version $Id$
 */
public class Bro {

    private static final Map<Class<? extends Runtime>, Runtime> runtimes = 
        new HashMap<Class<? extends Runtime>, Runtime>();
    
    public static void bind() {
        bind(VM.getStackClasses(0, 1)[0]);
    }
    
    public static void bind(Class<?> c) {
        Library library = c.getAnnotation(Library.class);
        if (library == null) {
            throw new IllegalArgumentException("No @" + Library.class.getName() 
                    + " annotation found on class " + c.getName());
        }
        Runtime runtime = getRuntime(library.runtime());
        for (Method method : c.getDeclaredMethods()) {
            Bridge bridge = method.getAnnotation(Bridge.class);
            if (bridge != null) {
                long f = runtime.resolveBridge(library, bridge, method);
                bind(method, f);
            }
        }
    }
    
    private static native void bind(Method method, long function);
    
    private static Runtime getRuntime(Class<? extends Runtime> runtimeClass) {
        synchronized (runtimes) {
            Runtime runtime = runtimes.get(runtimeClass);
            if (runtime == null) {
                try {
                    runtime = runtimeClass.newInstance();
                } catch (Exception e) {
                    throw (UnsatisfiedLinkError) new UnsatisfiedLinkError(
                            "Failed to create Runtime").initCause(e);
                }
                runtimes.put(runtimeClass,runtime);
            }
            return runtime;
        }
    }
}
