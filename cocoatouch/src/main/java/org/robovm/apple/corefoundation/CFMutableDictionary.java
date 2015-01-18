/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableDictionary/*</name>*/ 
    extends /*<extends>*/CFDictionary/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableDictionaryPtr extends Ptr<CFMutableDictionary, CFMutableDictionaryPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableDictionary() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFMutableDictionary create() {
        return create(0);
    }
    public static CFMutableDictionary create(long capacity) {
        return createMutable(null, capacity, getTypeKeyCallBacks(), getTypeValueCallBacks());
    }
    
    public void put(NativeObject key, NativeObject value) {
        setValue(key.as(VoidPtr.class), value.as(VoidPtr.class));
    }
    public void remove(NativeObject key) {
        removeValue(key.as(VoidPtr.class));
    }
    public void clear() {
        removeAllValues();
    }
    /*<methods>*/
    @Bridge(symbol="CFDictionaryCreateMutable", optional=true)
    protected static native CFMutableDictionary createMutable(CFAllocator allocator, @MachineSizedSInt long capacity, CFDictionaryKeyCallBacks keyCallBacks, CFDictionaryValueCallBacks valueCallBacks);
    @Bridge(symbol="CFDictionaryCreateMutableCopy", optional=true)
    protected static native CFMutableDictionary createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFDictionary theDict);
    @Bridge(symbol="CFDictionaryAddValue", optional=true)
    protected native void addValue(VoidPtr key, VoidPtr value);
    @Bridge(symbol="CFDictionarySetValue", optional=true)
    protected native void setValue(VoidPtr key, VoidPtr value);
    @Bridge(symbol="CFDictionaryReplaceValue", optional=true)
    protected native void replaceValue(VoidPtr key, VoidPtr value);
    @Bridge(symbol="CFDictionaryRemoveValue", optional=true)
    protected native void removeValue(VoidPtr key);
    @Bridge(symbol="CFDictionaryRemoveAllValues", optional=true)
    protected native void removeAllValues();
    /*</methods>*/
}
