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

/**
 *
 * @version $Id$
 */
public class CRuntime extends Runtime {
    
    public long resolveBridge(Library library, Bridge bridge, Method method) {
        long handle = getHandle(library);
        String symbol = bridge.symbol();
        if (symbol == null || "".equals(symbol)) {
            symbol = method.getName();
        }
        long f = Dl.resolve(handle, symbol);
        if (f == 0L) {
            throw new UnsatisfiedLinkError("Failed to resolve native function " 
                    + "for method " + method + " with bridge annotation " + bridge);
        }
        return f;
    }
    
}
