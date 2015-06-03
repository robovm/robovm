/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableSet/*</name>*/ 
    extends /*<extends>*/CFSet/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableSetPtr extends Ptr<CFMutableSet, CFMutableSetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableSet() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFMutableSet create() {
        return create(null, 0, getTypeCallBacks());
    }
    public static CFMutableSet create(long capacity) {
        return create(null, capacity, getTypeCallBacks());
    }
    public static CFMutableSet createCopy(long capacity, CFSet theSet) {
        return createCopy(null, capacity, theSet);
    }
    
    public void add(NativeObject value) {
        addValue(value.as(VoidPtr.class));
    }
    public void remove(NativeObject value) {
        removeValue(value.as(VoidPtr.class));
    }
    /*<methods>*/
    @Bridge(symbol="CFSetCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableSet create(CFAllocator allocator, @MachineSizedSInt long capacity, CFSetCallBacks callBacks);
    @Bridge(symbol="CFSetCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableSet createCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFSet theSet);
    @Bridge(symbol="CFSetAddValue", optional=true)
    private native void addValue(VoidPtr value);
    @Bridge(symbol="CFSetReplaceValue", optional=true)
    private native void replaceValue(VoidPtr value);
    @Bridge(symbol="CFSetSetValue", optional=true)
    private native void setValue(VoidPtr value);
    @Bridge(symbol="CFSetRemoveValue", optional=true)
    private native void removeValue(VoidPtr value);
    @Bridge(symbol="CFSetRemoveAllValues", optional=true)
    public native void clear();
    /*</methods>*/
}
