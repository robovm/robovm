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
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSObject.NSObjectPtr;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFArray/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.toList(NativeObject.class);
        }
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static long toNative(List<?> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray o = null;
            if (l instanceof CFArray) {
                o = (CFArray) l;
            } else {
                o = CFArray.create((List<CFType>) l);
            }
            return CFType.Marshaler.toNative(o, flags);
        }
    }
    
    public static class AsStringListMarshaler {
        @MarshalsPointer
        public static List<String> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringList();
        }
        @MarshalsPointer
        public static long toNative(List<String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(CFArray.fromStrings(l), flags);
        }
    }
    
    /*<ptr>*/public static class CFArrayPtr extends Ptr<CFArray, CFArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFArray() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static <T extends NativeObject> CFArray create(Collection<T> objects) {
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
    public static CFArray create(NSObject ... objects) {
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
    
    public static CFArray create(CFType ... objects) {
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
    
    @SuppressWarnings("unchecked")
    public <T extends NativeObject> T[] toArray(Class<T> type) {
        T[] result = (T[]) java.lang.reflect.Array.newInstance(type, (int) getCount());
        for (int i = 0; i < result.length; i++) {
            result[i] = get(i, type);
        }
        return result;
    }
    
    public <T extends NativeObject> List<T> toList(Class<T> type) {
        int size = (int) size();
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(get(i, type));
        }
        return result;
    }
    
    public <T extends NativeObject> T get(@MachineSizedSInt long index, Class<T> type) {
        return getValueAtIndex(index).as(type);
    }
    public @MachineSizedSInt long size() {
        return getCount();
    }
    
    public void add(NativeObject value) {
        throw new UnsupportedOperationException("CFArray is immutable. Use CFMutableArray instead!");
    }
    public void insert(@MachineSizedSInt long idx, NativeObject value) {
        throw new UnsupportedOperationException("CFArray is immutable. Use CFMutableArray instead!");
    }
    public void replace(@MachineSizedSInt long idx, NativeObject value) {
        throw new UnsupportedOperationException("CFArray is immutable. Use CFMutableArray instead!");
    }
    public void remove(@MachineSizedSInt long idx) {
        throw new UnsupportedOperationException("CFArray is immutable. Use CFMutableArray instead!");
    }
    public void clear() {
        throw new UnsupportedOperationException("CFArray is immutable. Use CFMutableArray instead!");
    }
    
    
    /**
     * Use this method to convert a CFArray of CFString items to a List of String items. 
     * @return
     */
    public List<String> asStringList() {
        List<String> list = new ArrayList<>();
        if (size() == 0) 
            return list;
        
        for (int i = 0; i < size(); i++) {
            list.add(get(i, CFString.class).toString());
        }
        return list;
    }
    
    public static CFArray fromStrings (String... strings) {
        int length = strings.length;
        CFString[] cfStrings = new CFString[length];

        for (int i = 0; i < length; i++) {
            cfStrings[i] = new CFString(strings[i]);
        }
        return CFArray.create(cfStrings);
    }

    public static CFArray fromStrings (Collection<String> strings) {
        CFString[] cfStrings = new CFString[strings.size()];

        int i = 0;
        for (String s : strings) {
            cfStrings[i] = new CFString(s);
            i++;
        }
        return CFArray.create(cfStrings);
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeArrayCallBacks", optional=true)
    public static native @ByVal CFArrayCallBacks getTypeCallBacks();
    
    @Bridge(symbol="CFArrayGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFArrayCreate", optional=true)
    protected static native CFArray create(CFAllocator allocator, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFArrayCallBacks callBacks);
    @Bridge(symbol="CFArrayCreateCopy", optional=true)
    protected static native CFArray createCopy(CFAllocator allocator, CFArray theArray);
    @Bridge(symbol="CFArrayGetCount", optional=true)
    protected native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFArrayGetCountOfValue", optional=true)
    protected native @MachineSizedSInt long getCountOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayContainsValue", optional=true)
    protected native boolean containsValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayGetValueAtIndex", optional=true)
    protected native VoidPtr getValueAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFArrayGetValues", optional=true)
    protected native void getValues(@ByVal CFRange range, VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFArrayApplyFunction", optional=true)
    protected native void applyFunction(@ByVal CFRange range, FunctionPtr applier, VoidPtr context);
    @Bridge(symbol="CFArrayGetFirstIndexOfValue", optional=true)
    protected native @MachineSizedSInt long getFirstIndexOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayGetLastIndexOfValue", optional=true)
    protected native @MachineSizedSInt long getLastIndexOfValue(@ByVal CFRange range, VoidPtr value);
    @Bridge(symbol="CFArrayBSearchValues", optional=true)
    protected native @MachineSizedSInt long bSearchValues(@ByVal CFRange range, VoidPtr value, FunctionPtr comparator, VoidPtr context);
    /*</methods>*/
}
