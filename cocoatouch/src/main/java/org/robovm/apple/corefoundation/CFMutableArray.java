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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableArray/*</name>*/ 
    extends /*<extends>*/CFArray/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableArrayPtr extends Ptr<CFMutableArray, CFMutableArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableArray() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFMutableArray create() {
        return create(null, 0, getTypeCallBacks());
    }
    
    @Override
    public void add(NativeObject value) {
        appendValue(value.as(VoidPtr.class));
    }
    @Override
    public void insert(@MachineSizedSInt long idx, NativeObject value) {
        insertValueAtIndex(idx, value.as(VoidPtr.class));
    }
    @Override
    public void replace(@MachineSizedSInt long idx, NativeObject value) {
        setValueAtIndex(idx, value.as(VoidPtr.class));
    }
    @Override
    public void remove(@MachineSizedSInt long idx) {
        removeValueAtIndex(idx);
    }
    @Override
    public void clear() {
        removeAllValues();
    }
    /*<methods>*/
    @Bridge(symbol="CFArrayCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableArray create(CFAllocator allocator, @MachineSizedSInt long capacity, CFArrayCallBacks callBacks);
    @Bridge(symbol="CFArrayCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableArray createCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFArray theArray);
    @Bridge(symbol="CFArrayAppendValue", optional=true)
    private native void appendValue(VoidPtr value);
    @Bridge(symbol="CFArrayInsertValueAtIndex", optional=true)
    private native void insertValueAtIndex(@MachineSizedSInt long idx, VoidPtr value);
    @Bridge(symbol="CFArraySetValueAtIndex", optional=true)
    private native void setValueAtIndex(@MachineSizedSInt long idx, VoidPtr value);
    @Bridge(symbol="CFArrayRemoveValueAtIndex", optional=true)
    private native void removeValueAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFArrayRemoveAllValues", optional=true)
    private native void removeAllValues();
    @Bridge(symbol="CFArrayReplaceValues", optional=true)
    private native void replaceValues(@ByVal CFRange range, VoidPtr.VoidPtrPtr newValues, @MachineSizedSInt long newCount);
    @Bridge(symbol="CFArrayExchangeValuesAtIndices", optional=true)
    private native void exchangeValuesAtIndices(@MachineSizedSInt long idx1, @MachineSizedSInt long idx2);
    @Bridge(symbol="CFArraySortValues", optional=true)
    private native void sortValues(@ByVal CFRange range, FunctionPtr comparator, VoidPtr context);
    @Bridge(symbol="CFArrayAppendArray", optional=true)
    private native void appendArray(CFArray otherArray, @ByVal CFRange otherRange);
    /*</methods>*/
}
