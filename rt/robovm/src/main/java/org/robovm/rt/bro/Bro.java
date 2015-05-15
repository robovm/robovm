/*
 * Copyright (C) 2012 RoboVM AB
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

import java.lang.reflect.Method;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.GlobalValue;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.ptr.VoidPtr;

/**
 *
 * @version $Id$
 */
public class Bro {
    public static final boolean IS_DARWIN;
    public static final boolean IS_LINUX;
    public static final boolean IS_X86;
    public static final boolean IS_ARM;
    public static final boolean IS_32BIT;
    public static final boolean IS_64BIT;

    static {
        String os = System.getProperty("os.name", "").toLowerCase();
        String arch = System.getProperty("os.arch", "").toLowerCase();
        IS_DARWIN = os.contains("mac") || os.contains("ios");
        IS_LINUX = !IS_DARWIN && os.contains("linux");
        IS_X86 = arch.contains("x86");
        IS_ARM = !IS_X86 && (arch.contains("arm") || arch.contains("aarch64"));
        IS_64BIT = VoidPtr.sizeOf() == 8;
        IS_32BIT = !IS_64BIT;
    }
    
    public static void bind() {
        bind(VM.getStackClasses(0, 1)[0]);
    }
    
    public static void bind(Class<?> c) {
        Library library = c.getAnnotation(Library.class);
        if (library != null) {
            Runtime.loadLibrary(library);
        }
        for (Method method : c.getDeclaredMethods()) {
            Bridge bridge = method.getAnnotation(Bridge.class);
            if (bridge != null && !bridge.dynamic() && !VM.isBridgeMethodBound(method)) {
                long f = Runtime.resolveBridge(library, bridge, method);
                if (f != 0L) {
                    VM.bindBridgeMethod(method, f);
                }
            } else {
                GlobalValue globalValue = method.getAnnotation(GlobalValue.class);
                if (globalValue != null && !VM.isBridgeMethodBound(method)) {
                    long f = Runtime.resolveGlobalValue(library, globalValue, method);
                    if (f != 0L) {
                        VM.bindBridgeMethod(method, f);
                    }
                }
            }
        }
    }
    
    public static void addSearchPath(String path) {
        Runtime.addSearchPath(path);
    }
}
