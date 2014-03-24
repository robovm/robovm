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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBinaryHeap/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFBinaryHeapPtr extends Ptr<CFBinaryHeap, CFBinaryHeapPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFBinaryHeap.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFBinaryHeap() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFStringBinaryHeapCallBacks")
    public static native @ByVal CFBinaryHeapCallBacks StringBinaryHeapCallBacks();
    
    @Bridge(symbol="CFBinaryHeapGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBinaryHeapCreate")
    public static native CFBinaryHeap create(CFAllocator allocator, @MachineSizedSInt long capacity, CFBinaryHeapCallBacks callBacks, CFBinaryHeapCompareContext compareContext);
    @Bridge(symbol="CFBinaryHeapCreateCopy")
    public static native CFBinaryHeap createCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFBinaryHeap heap);
    @Bridge(symbol="CFBinaryHeapGetCount")
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFBinaryHeapGetCountOfValue")
    public native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFBinaryHeapContainsValue")
    public native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFBinaryHeapGetMinimum")
    public native VoidPtr getMinimum();
    @Bridge(symbol="CFBinaryHeapGetMinimumIfPresent")
    public native boolean getMinimumIfPresent(VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFBinaryHeapGetValues")
    public native void getValues(VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFBinaryHeapApplyFunction")
    public native void applyFunction(FunctionPtr applier, VoidPtr context);
    @Bridge(symbol="CFBinaryHeapAddValue")
    public native void addValue(VoidPtr value);
    @Bridge(symbol="CFBinaryHeapRemoveMinimumValue")
    public native void removeMinimumValue();
    @Bridge(symbol="CFBinaryHeapRemoveAllValues")
    public native void removeAllValues();
    /*</methods>*/
}
