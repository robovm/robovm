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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFArray/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFArrayPtr extends Ptr<CFArray, CFArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFArray() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFArrayGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFArrayCreate", optional=true)
    public static native CFArray create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFArrayCallBacks callBacks);
    @Bridge(symbol="CFArrayCreateCopy", optional=true)
    public static native CFArray createCopy(CFAllocator allocator, CFArray theArray);
    @Bridge(symbol="CFArrayGetCount", optional=true)
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFArrayGetCountOfValue", optional=true)
    public native @MachineSizedSInt long getCountOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayContainsValue", optional=true)
    public native boolean containsValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayGetValueAtIndex", optional=true)
    public native VoidPtr getValueAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFArrayGetValues", optional=true)
    public native void getValues(@ByVal CFRange range, VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFArrayApplyFunction", optional=true)
    public native void applyFunction(@ByVal CFRange range, FunctionPtr applier, VoidPtr context);
    @Bridge(symbol="CFArrayGetFirstIndexOfValue", optional=true)
    public native @MachineSizedSInt long getFirstIndexOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayGetLastIndexOfValue", optional=true)
    public native @MachineSizedSInt long getLastIndexOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayBSearchValues", optional=true)
    public native @MachineSizedSInt long bSearchValues(@ByVal CFRange range, VoidPtr value, FunctionPtr comparator, VoidPtr context);
    /*</methods>*/
}
