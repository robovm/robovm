/*
 * Copyright (C) 2012 RoboVM
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

import org.nullvm.rt.bro.annotation.Bridge;
import org.nullvm.rt.bro.annotation.Library;

/**
 *
 * @version $Id$
 */
public class CRuntime extends Runtime {
    
    @Override
    public void loadLibrary(Library library) {
        getHandle(library);
    }
    
    public long resolveBridge(Library library, Bridge bridge, Method method) {
        if (library == null) {
            throw new IllegalArgumentException("No @" + Library.class.getName() 
                    + " annotation found on class " + method.getDeclaringClass().getName());
        }
        
        long handle = getHandle(library);
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
    
}
