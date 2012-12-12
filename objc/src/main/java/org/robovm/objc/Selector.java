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
package org.robovm.objc;

import java.util.HashMap;
import java.util.Map;

import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.ptr.BytePtr;

/**
 * Represents an Objective-C selector.
 */
public final class Selector extends NativeObject {

    private static final Map<String, Selector> selectors = new HashMap<String, Selector>();

    private Selector() {
    }

    public String getName() {
        return ObjCRuntime.sel_getName(this).toStringAsciiZ();
    }
    
    @Override
    public String toString() {
    	return getName();
    }
    
    public static Selector register(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        synchronized (selectors) {
            Selector sel = selectors.get(name);
            if (sel == null) {
                sel = ObjCRuntime.sel_registerName(BytePtr.toBytePtrAsciiZ(name));
                if (sel == null) {
                    // sel_registerName should never return nil
                    throw new IllegalStateException("Objective-C failed to register selector '" + name + "'");
                }
                selectors.put(name, sel);
            }
            return sel;
        }
    }
    
}
