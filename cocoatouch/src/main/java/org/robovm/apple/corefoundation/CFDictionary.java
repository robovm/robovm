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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/
import org.robovm.apple.foundation.NSObject.NSObjectPtr;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDictionary/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsMapMarshaler {
        @MarshalsPointer
        public static Map<?, ?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asMap(NativeObject.class, NativeObject.class);
        }
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static long toNative(Map<?, ?> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFDictionary o = CFDictionary.create((Map<NativeObject, NativeObject>)l);
            return CFType.Marshaler.toNative(o, flags);
        }
    }
    
    public static class AsStringMapMarshaler {
        @MarshalsPointer
        public static Map<String, ?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringMap(NativeObject.class);
        }
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static long toNative(Map<String, ?> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(CFDictionary.fromStringMap((Map<String, ? extends NativeObject>)l), flags);
        }
    }
    
    public static class AsStringStringMapMarshaler {
        @MarshalsPointer
        public static Map<String, String> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringStringMap();
        }
        @MarshalsPointer
        public static long toNative(Map<String, String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(CFDictionary.fromStringStringMap(l), flags);
        }
    }
    
    static final java.lang.reflect.Method cbPutAll;
    
    static {
        try {
            cbPutAll = CFDictionary.class.getDeclaredMethod("cbPutAll", VoidPtr.class, VoidPtr.class, CFMutableDictionary.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<ptr>*/public static class CFDictionaryPtr extends Ptr<CFDictionary, CFDictionaryPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFDictionary() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbPutAll(VoidPtr key, VoidPtr value, CFMutableDictionary thiz) {
        thiz.put(key, value);
    }
    
    
    public static <K extends NativeObject, V extends NativeObject> CFDictionary create(Map<K, V> m) {
        if (m == null) {
            throw new NullPointerException("m");
        }
        if (m.size() == 0) {
            return create(null, null, null, 0, getTypeKeyCallBacks(), getTypeValueCallBacks());
        }
        return create(m.keySet(), m.values());
    }
    
    public static <K extends NativeObject, V extends NativeObject> CFDictionary create(Collection<K> k, Collection<V> v) {
        if (k == null) {
            throw new NullPointerException("k");
        }
        if (v == null) {
            throw new NullPointerException("v");
        }
        if (k.size() == 0) {
            return create(null, null, null, 0, getTypeKeyCallBacks(), getTypeValueCallBacks());
        }
        if (k.iterator().next() instanceof NSObject) {
            return create(k.toArray(new NSObject[k.size()]), v.toArray(new NSObject[v.size()]));
        } else if (k.iterator().next() instanceof CFType) {
            return create(k.toArray(new CFType[k.size()]), v.toArray(new CFType[v.size()]));
        }
        throw new IllegalArgumentException("items can only be of type CFType or NSObject!");
    }
    
    public static CFDictionary create(NativeObject[] k, NativeObject[] v) {
        if (k == null) {
            throw new NullPointerException("k");
        }
        if (v == null) {
            throw new NullPointerException("v");
        }
        if (k.length == 0) {
            return create(null, null, null, 0, getTypeKeyCallBacks(), getTypeValueCallBacks());
        }
        if (k[0] instanceof NSObject) {
            NSObjectPtr keys = Struct.allocate(NSObjectPtr.class, k.length);
            keys.set((NSObject[])k);
            NSObjectPtr values = Struct.allocate(NSObjectPtr.class, v.length);
            values.set((NSObject[])v);
            return create(null, keys.as(VoidPtr.VoidPtrPtr.class), values.as(VoidPtr.VoidPtrPtr.class), v.length, getTypeKeyCallBacks(), getTypeValueCallBacks());
        } else if (k[0] instanceof CFType) {
            CFTypePtr keys = Struct.allocate(CFTypePtr.class, k.length);
            keys.set((CFType[])k);
            CFTypePtr values = Struct.allocate(CFTypePtr.class, v.length);
            values.set((CFType[])v);
            return create(null, keys.as(VoidPtr.VoidPtrPtr.class), values.as(VoidPtr.VoidPtrPtr.class), v.length, getTypeKeyCallBacks(), getTypeValueCallBacks());
        }
        throw new IllegalArgumentException("items can only be of type CFType or NSObject!");
    }
    
    public <K extends NativeObject, V extends NativeObject> Map<K, V> asMap(Class<K> keyType, Class<V> valueType) {
        Map<K, V> map = new HashMap<>();
        VoidPtr.VoidPtrPtr keys = new VoidPtr.VoidPtrPtr();
        VoidPtr.VoidPtrPtr values = new VoidPtr.VoidPtrPtr();
        getKeysAndValues(keys, values);
        VoidPtr kp = keys.get();
        VoidPtr vp = values.get();
        map.put(kp.as(keyType), vp.as(valueType));
        for (long i = 1, n = size(); i < n; i++) {
            VoidPtr k = kp.next();
            VoidPtr v = vp.next();
            map.put(k.as(keyType), v.as(valueType));
        }
        return map;
    }
    public <V extends NativeObject> Map<String, V> asStringMap(Class<V> valueType) {
        Map<String, V> map = new HashMap<>();
        VoidPtr.VoidPtrPtr keys = new VoidPtr.VoidPtrPtr();
        VoidPtr.VoidPtrPtr values = new VoidPtr.VoidPtrPtr();
        getKeysAndValues(keys, values);
        VoidPtr kp = keys.get();
        VoidPtr vp = values.get();
        map.put(kp.as(CFString.class).toString(), vp.as(valueType));
        for (long i = 1, n = size(); i < n; i++) {
            VoidPtr.VoidPtrPtr k = keys.next();
            VoidPtr.VoidPtrPtr v = values.next();
            map.put(k.get().as(CFString.class).toString(), v.get().as(valueType));
        }
        return map;
    }
    public Map<String, String> asStringStringMap() {
        Map<String, String> map = new HashMap<>();
        VoidPtr.VoidPtrPtr keys = new VoidPtr.VoidPtrPtr();
        VoidPtr.VoidPtrPtr values = new VoidPtr.VoidPtrPtr();
        getKeysAndValues(keys, values);
        VoidPtr kp = keys.get();
        VoidPtr vp = values.get();
        map.put(kp.as(CFString.class).toString(), vp.as(CFString.class).toString());
        for (long i = 1, n = size(); i < n; i++) {
            VoidPtr.VoidPtrPtr k = keys.next();
            VoidPtr.VoidPtrPtr v = values.next();
            map.put(k.get().as(CFString.class).toString(), v.get().as(CFString.class).toString());
        }
        return map;
    }
    
    public static <V extends NativeObject> CFDictionary fromStringMap(Map<String, V> m) {
        List<CFString> keys = new ArrayList<>();
        for (String key : m.keySet()) {
            keys.add(new CFString(key));
        }
        return create(keys, m.values());
    }
    public static CFDictionary fromStringStringMap(Map<String, String> m) {
        CFString[] keys = new CFString[m.size()];
        CFString[] values = new CFString[m.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : m.entrySet()) {
            keys[i] = new CFString(entry.getKey());
            values[i] = new CFString(entry.getValue());
            i++;
        }
        return create(keys, values);
    }
    
    public <T extends NativeObject> T get(NativeObject key, Class<T> type) {
        return getValue(key.as(VoidPtr.class)).as(type);
    }
    public boolean containsKey(NativeObject key) {
        return containsKey(key.as(VoidPtr.class));
    }
    public boolean containsValue(NativeObject value) {
        return containsValue(value.as(VoidPtr.class));
    }
    public @MachineSizedSInt long size() {
        return getCount();
    }
    
    public void put(NativeObject key, NativeObject value) {
        throw new UnsupportedOperationException("CFDictionary is immutable. Use CFMutableDictionary instead!");
    }
    public void putAll(CFDictionary dict) {
        throw new UnsupportedOperationException("CFDictionary is immutable. Use CFMutableDictionary instead!");
    }
    public void remove(NativeObject key) {
        throw new UnsupportedOperationException("CFDictionary is immutable. Use CFMutableDictionary instead!");
    }
    public void clear() {
        throw new UnsupportedOperationException("CFDictionary is immutable. Use CFMutableDictionary instead!");
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFTypeDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks getTypeKeyCallBacks();
    @GlobalValue(symbol="kCFCopyStringDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks getCopyStringKeyCallBacks();
    @GlobalValue(symbol="kCFTypeDictionaryValueCallBacks", optional=true)
    public static native @ByVal CFDictionaryValueCallBacks getTypeValueCallBacks();
    
    @Bridge(symbol="CFDictionaryGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDictionaryCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDictionary create(CFAllocator allocator, VoidPtr.VoidPtrPtr keys, VoidPtr.VoidPtrPtr values, @MachineSizedSInt long numValues, CFDictionaryKeyCallBacks keyCallBacks, CFDictionaryValueCallBacks valueCallBacks);
    @Bridge(symbol="CFDictionaryCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDictionary createCopy(CFAllocator allocator, CFDictionary theDict);
    @Bridge(symbol="CFDictionaryGetCount", optional=true)
    protected native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFDictionaryGetCountOfKey", optional=true)
    protected native @MachineSizedSInt long getCountOfKey(VoidPtr key);
    @Bridge(symbol="CFDictionaryGetCountOfValue", optional=true)
    protected native @MachineSizedSInt long getCountOfValue(VoidPtr value);
    @Bridge(symbol="CFDictionaryContainsKey", optional=true)
    protected native boolean containsKey(VoidPtr key);
    @Bridge(symbol="CFDictionaryContainsValue", optional=true)
    protected native boolean containsValue(VoidPtr value);
    @Bridge(symbol="CFDictionaryGetValue", optional=true)
    protected native VoidPtr getValue(VoidPtr key);
    @Bridge(symbol="CFDictionaryGetValueIfPresent", optional=true)
    protected native boolean getValueIfPresent(VoidPtr key, VoidPtr.VoidPtrPtr value);
    @Bridge(symbol="CFDictionaryGetKeysAndValues", optional=true)
    protected native void getKeysAndValues(VoidPtr.VoidPtrPtr keys, VoidPtr.VoidPtrPtr values);
    @Bridge(symbol="CFDictionaryApplyFunction", optional=true)
    protected native void applyFunction(FunctionPtr applier, @Pointer long context);
    /*</methods>*/
}
