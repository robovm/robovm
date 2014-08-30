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
        return createMutable(null, 0, CoreFoundation.TypeSetCallBacks());
    }
    
    public void add(NativeObject value) {
        addValue(value.as(VoidPtr.class));
    }
    public void remove(NativeObject value) {
        removeValue(value.as(VoidPtr.class));
    }
    public void clear() {
        removeAllValues();
    }
    /*<methods>*/
    @Bridge(symbol="CFSetCreateMutable", optional=true)
    protected static native CFMutableSet createMutable(CFAllocator allocator, @MachineSizedSInt long capacity, CFSetCallBacks callBacks);
    @Bridge(symbol="CFSetCreateMutableCopy", optional=true)
    protected static native CFMutableSet createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFSet theSet);
    @Bridge(symbol="CFSetAddValue", optional=true)
    protected native void addValue(VoidPtr value);
    @Bridge(symbol="CFSetReplaceValue", optional=true)
    protected native void replaceValue(VoidPtr value);
    @Bridge(symbol="CFSetSetValue", optional=true)
    protected native void setValue(VoidPtr value);
    @Bridge(symbol="CFSetRemoveValue", optional=true)
    protected native void removeValue(VoidPtr value);
    @Bridge(symbol="CFSetRemoveAllValues", optional=true)
    protected native void removeAllValues();
    /*</methods>*/
}
