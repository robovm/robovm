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
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSObject.NSObjectPtr;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSet/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFSetPtr extends Ptr<CFSet, CFSetPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFSet() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static <T extends NativeObject> CFSet create(Collection<T> objects) {
        if (objects == null) {
            throw new NullPointerException("objects");
        }
        if (objects.size() == 0 || objects.iterator().next() instanceof CFType) {
            return create(objects.toArray(new CFType[objects.size()]));
        }
        if (objects.iterator().next() instanceof NSObject) {
            return create(objects.toArray(new NSObject[objects.size()]));
        }
        throw new IllegalArgumentException("items can only be of type CFType or NSObject!");
    }
    public static CFSet create(NSObject ... objects) {
        if (objects == null) {
            throw new NullPointerException("objects");
        }
        if (objects.length == 0) {
            return create(null, null, 0, getTypeCallBacks());
        }
        NSObjectPtr values = Struct.allocate(NSObjectPtr.class, objects.length);
        values.set(objects);
        return create(null, values.as(VoidPtr.VoidPtrPtr.class), objects.length, getTypeCallBacks());
    }
    public static CFSet create(CFType ... objects) {
        if (objects == null) {
            throw new NullPointerException("objects");
        }
        if (objects.length == 0) {
            return create(null, null, 0, getTypeCallBacks());
        }
        CFTypePtr values = Struct.allocate(CFTypePtr.class, objects.length);
        values.set(objects);
        return create(null, values.as(VoidPtr.VoidPtrPtr.class), objects.length, getTypeCallBacks());
    }
    
    public boolean contains(NativeObject value) {
        return containsValue(value.as(VoidPtr.class));
    }
    public @MachineSizedSInt long size() {
        return getCount();
    }
    
    public void add(NativeObject value) {
        throw new UnsupportedOperationException("CFSet is immutable. Use CFMutableSet instead!");
    }
    public void remove(NativeObject value) {
        throw new UnsupportedOperationException("CFSet is immutable. Use CFMutableSet instead!");
    }
    public void clear() {
        throw new UnsupportedOperationException("CFSet is immutable. Use CFMutableSet instead!");
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeSetCallBacks", optional=true)
    public static native @ByVal CFSetCallBacks getTypeCallBacks();
    @GlobalValue(symbol="kCFCopyStringSetCallBacks", optional=true)
    public static native @ByVal CFSetCallBacks getCopyStringCallBacks();
    
    @Bridge(symbol="CFSetGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFSetCreate", optional=true)
    protected static native CFSet create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFSetCallBacks callBacks);
    @Bridge(symbol="CFSetCreateCopy", optional=true)
    protected static native CFSet createCopy(CFAllocator allocator, CFSet theSet);
    @Bridge(symbol="CFSetGetCount", optional=true)
    protected native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFSetGetCountOfValue", optional=true)
    protected native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFSetContainsValue", optional=true)
    protected native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFSetGetValue", optional=true)
    protected native VoidPtr getValue(VoidPtr value);
    @Bridge(symbol="CFSetGetValueIfPresent", optional=true)
    protected native boolean getValueIfPresent(VoidPtr candidate, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFSetGetValues", optional=true)
    protected native void getValues(VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFSetApplyFunction", optional=true)
    protected native void applyFunction(FunctionPtr applier, VoidPtr context);
    /*</methods>*/
}
