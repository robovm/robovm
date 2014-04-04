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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDictionary/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDictionaryPtr extends Ptr<CFDictionary, CFDictionaryPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFDictionary() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks DictionaryKeyCallBacks();
    @GlobalValue(symbol="kCFCopyStringDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks CopyStringDictionaryKeyCallBacks();
    @GlobalValue(symbol="kCFTypeDictionaryValueCallBacks", optional=true)
    public static native @ByVal CFDictionaryValueCallBacks DictionaryValueCallBacks();
    
    @Bridge(symbol="CFDictionaryGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDictionaryCreate", optional=true)
    public static native CFDictionary create(CFAllocator allocator, VoidPtr.VoidPtrPtr keys, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFDictionaryKeyCallBacks keyCallBacks, CFDictionaryValueCallBacks valueCallBacks);
    @Bridge(symbol="CFDictionaryCreateCopy", optional=true)
    public static native CFDictionary createCopy(CFAllocator allocator, CFDictionary theDict);
    @Bridge(symbol="CFDictionaryGetCount", optional=true)
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFDictionaryGetCountOfKey", optional=true)
    public native @MachineSizedSInt long getCountOfKey(VoidPtr key);
    @Bridge(symbol="CFDictionaryGetCountOfValue", optional=true)
    public native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFDictionaryContainsKey", optional=true)
    public native boolean containsKey(VoidPtr key);
    @Bridge(symbol="CFDictionaryContainsValue", optional=true)
    public native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFDictionaryGetValue", optional=true)
    public native VoidPtr getValue(VoidPtr key);
    @Bridge(symbol="CFDictionaryGetValueIfPresent", optional=true)
    public native boolean getValueIfPresent(VoidPtr key, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFDictionaryGetKeysAndValues", optional=true)
    public native void getKeysAndValues(VoidPtr.VoidPtrPtr keys, VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFDictionaryApplyFunction", optional=true)
    public native void applyFunction(FunctionPtr applier, VoidPtr context);
    /*</methods>*/
}
