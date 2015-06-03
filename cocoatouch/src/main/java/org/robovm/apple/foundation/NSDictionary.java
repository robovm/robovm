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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDictionary/*</name>*/ <K extends NSObject, V extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSPropertyList, NSFastEnumeration, Map<K, V>/*</implements>*/ {

    public static class NSDictionaryPtr<K extends NSObject, V extends NSObject> extends Ptr<NSDictionary<K, V>, NSDictionaryPtr<K, V>> {}
    
    public static class AsStringMapMarshaler {
        @MarshalsPointer
        public static Map<String, ? extends NSObject> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<?, ?> o = (NSDictionary<?, ?>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.asStringMap();
        }
        @MarshalsPointer
        public static long toNative(Map<String, ? extends NSObject> l, long flags) {
            if (l == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSDictionary.fromStringMap(l), flags);
        }
    }
    
    public static class AsStringStringMapMarshaler {
        @MarshalsPointer
        public static Map<String, String> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<?, ?> o = (NSDictionary<?, ?>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<?, ?> e : o.entrySet()) {
                map.put(e.getKey().toString(), e.getValue().toString());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<String, String> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSDictionary<NSString, NSString> o = new NSMutableDictionary<>();
            for (Map.Entry<String, String> e : l.entrySet()) {
                o.put(new NSString(e.getKey()), new NSString(e.getValue()));
            }
            
            return NSObject.Marshaler.toNative(o, flags);
        }
    }
    
    public static class AsStringNumberMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<String, Number> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, NSNumber> o = (NSDictionary<NSString, NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            Map<String, Number> map = new HashMap<>();
            for (Map.Entry<NSString, NSNumber> e : o.entrySet()) {
                map.put(e.getKey().toString(), e.getValue().doubleValue());
            }
            
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<String, Number> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSDictionary<NSString, NSNumber> o = new NSMutableDictionary<>();
            for (Map.Entry<String, Number> e : l.entrySet()) {
                Number value = e.getValue();
                NSNumber number = null;
                
                if (value instanceof Byte) {
                   number = NSNumber.valueOf((byte) value);
                } else if (value instanceof Short) {
                    number = NSNumber.valueOf((short) value);
                } else if (value instanceof Integer) {
                    number = NSNumber.valueOf((int) value);
                } else if (value instanceof Long) {
                    number = NSNumber.valueOf((long) value);
                } else if (value instanceof Float) {
                    number = NSNumber.valueOf((float) value);
                } else if (value instanceof Double) {
                    number = NSNumber.valueOf((double) value);
                } else {
                    throw new UnsupportedOperationException("Only values of type Number (byte, short, int, long, float, double) are allowed!");
                }
                
                o.put(new NSString(e.getKey()), number);
            }
            
            return NSObject.Marshaler.toNative(o, flags);
        }
    }
    
    static class KeySet<K extends NSObject> extends AbstractSet<K> {
        private final NSDictionary<K, ? extends NSObject> map;
        
        KeySet(NSDictionary<K, ? extends NSObject> map) {
            this.map = map;
        }

        @Override
        public Iterator<K> iterator() {
            final Iterator<K> it = map.getAllKeys().iterator();
            return new Iterator<K>() {
                private K last = null;
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }
                @Override
                public K next() {
                    last = it.next();
                    return last;
                }
                @Override
                public void remove() {
                    if (last == null) {
                        throw new IllegalStateException();
                    }
                    if (map.get(last) == null) {
                        throw new ConcurrentModificationException();
                    }
                    map.remove(last);
                    last = null;
                }
            };
        }

        @Override
        public int size() {
            return (int) map.getCount();
        }
    }
    
    static class Entry<K extends NSObject, V extends NSObject> implements Map.Entry<K, V> {
        private final NSDictionary<K, V> map;
        private final K key;
        private final V value;
        
        Entry(NSDictionary<K, V> map, K key, V value) {
            this.map = map;
            this.key = key;
            this.value = value;
        }

        public V setValue(V v) {
            if (value != map.get(key)) {
                throw new ConcurrentModificationException();
            }
            return map.put(key, v);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object instanceof Map.Entry) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) object;
                return (key == null ? entry.getKey() == null : key.equals(entry.getKey()))
                        && (value == null ? entry.getValue() == null : value.equals(entry.getValue()));
            }
            return false;
        }
        
        public int hashCode() {
            return key.hashCode() ^ value.hashCode();
        }

        public String toString() {
            return key + "=" + value;
        }
    }
    
    static class EntrySet<K extends NSObject, V extends NSObject> extends AbstractSet<Map.Entry<K, V>> {
        private final NSDictionary<K, V> map;

        EntrySet(NSDictionary<K, V> map) {
            this.map = map;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            final Iterator<K> keysIt = map.keySet().iterator();
            return new Iterator<Map.Entry<K, V>>() {
                private Map.Entry<K, V> entry = null;

                @Override
                public boolean hasNext() {
                    return keysIt.hasNext();
                }

                @Override
                public Map.Entry<K, V> next() {
                    final K key = keysIt.next();
                    final V value = map.get(key);
                    if (value == null) {
                        throw new ConcurrentModificationException();
                    }
                    entry = new Entry<K, V>(map, key, value);
                    return entry;
                }

                @Override
                public void remove() {
                    if (entry == null) {
                        throw new IllegalStateException();
                    }
                    Object value = map.get(entry.getKey());
                    if (entry.getValue() != value) {
                        throw new ConcurrentModificationException();
                    }
                    keysIt.remove();
                    entry = null;
                }
                
            };
        }

        @Override
        public int size() {
            return (int) map.getCount();
        }
        
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDictionary() {}
    protected NSDictionary(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/

    @SuppressWarnings("unchecked")
    public NSDictionary(K k, V v) {
        super((SkipInit) null);
        initObject(init(new NSArray<V>(v), new NSArray<K>(k)));
    }

    @SuppressWarnings("unchecked")
    public NSDictionary(K k1, V v1, K k2, V v2) {
        super((SkipInit) null);
        initObject(init(new NSArray<V>(v1, v2), new NSArray<K>(k1, k2)));
    }

    @SuppressWarnings("unchecked")
    public NSDictionary(K k1, V v1, K k2, V v2, K k3, V v3) {
        super((SkipInit) null);
        initObject(init(new NSArray<V>(v1, v2, v3), new NSArray<K>(k1, k2, k3)));
    }

    @SuppressWarnings("unchecked")
    public NSDictionary(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        super((SkipInit) null);
        initObject(init(new NSArray<V>(v1, v2, v3, v4), new NSArray<K>(k1, k2, k3, k4)));
    }

    @SuppressWarnings("unchecked")
    public NSDictionary(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        super((SkipInit) null);
        initObject(init(new NSArray<V>(v1, v2, v3, v4, v5), new NSArray<K>(k1, k2, k3, k4, k5)));
    }

    public NSDictionary(Map<K, V> m) {
        super((SkipInit) null);
        if (m instanceof NSDictionary) {
            initObject(init((NSDictionary<K, V>) m));
        } else {
            Set<K> keys = m.keySet();
            List<V> objects = new ArrayList<V>(keys.size());
            for (K key : keys) {
                V value = m.get(key);
                checkNull(key, value);
                objects.add(value);
            }
            initObject(init(new NSArray<V>(objects), new NSArray<K>(keys)));
        }
    }
    
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    @Property(selector = "allKeys")
    protected native NSArray<K> getAllKeys();
    @Property(selector = "allValues")
    protected native NSArray<V> getAllValues();
    @Property(selector = "descriptionInStringsFileFormat")
    public native String toFileFormatString();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    protected static void checkNull(Object key, Object value) {
        if (key == null || value == null) {
            throw new NullPointerException("null keys or values are not " 
                    + "allowed in NSDictionary. Use NSNull instead.");
        }
    }
    
    public boolean containsKey(Object key) {
        return get(key) != null;
    }
    public boolean containsValue(Object value) {
        if (!(value instanceof NSObject)) {
            return false;
        }
        NSArray<V> values = getAllValues();
        int count = (int) values.getCount();
        for (int i = 0; i < count; i++) {
            NSObject o = values.getObjectAt(i);
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet<K, V>(this);
    }
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if (!(key instanceof NSObject)) {
            return null;
        }
        return (V) getObject((K) key);
    }
    public boolean isEmpty() {
        return getCount() == 0;
    }
    public Set<K> keySet() {
        return new KeySet<K>(this);
    }
    public int size() {
        return (int) getCount();
    }
    public Collection<V> values() {
        return getAllValues();
    }
    public void clear() {
        throw new UnsupportedOperationException("NSDictionary is immutable");
    }
    public V put(K key, V value) {
        throw new UnsupportedOperationException("NSDictionary is immutable");
    }
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("NSDictionary is immutable");
    }
    public V remove(Object key) {
        throw new UnsupportedOperationException("NSDictionary is immutable");
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof NSDictionary && equalsTo((NSDictionary<?, ?>) obj);
    }
    
    public static NSDictionary<?, ?> read(java.io.File file) {
        return read(file.getAbsolutePath());
    }

    public boolean write(java.io.File file, boolean useAuxiliaryFile) {
        return writeFile(file.getAbsolutePath(), useAuxiliaryFile);
    }
    
    /**
     * Use this method to convert a NSDictionary with NSString keys to a Map with String keys. 
     * Keys of this NSDictionary must be of type NSString, otherwise an exception will be thrown.
     * @return
     * @throws UnsupportedOperationException when the dictionary keys are not of type NSString.
     */
    public Map<String, V> asStringMap() {
        Map<String, V> map = new HashMap<>();
        if (size() == 0) 
            return map;
        if (!(getAllKeys().get(0) instanceof NSString)) 
            throw new UnsupportedOperationException("keys must be of type NSString");
        
        for (java.util.Map.Entry<K, V> e : entrySet()) {
            map.put(e.getKey().toString(), e.getValue());
        }
        return map;
    }
    
    public static <V extends NSObject> NSDictionary<NSString, V> fromStringMap (Map<String, V> map) {
        Map<NSString, V> dictionary = new HashMap<>();
        for (Map.Entry<String, V> entry : map.entrySet()) {
            dictionary.put(new NSString(entry.getKey()), entry.getValue());
        }
        return new NSDictionary<NSString, V>(dictionary);
    }
    
    /*<methods>*/
    @Method(selector = "objectForKey:")
    protected native V getObject(K aKey);
    @Method(selector = "isEqualToDictionary:")
    protected native boolean equalsTo(NSDictionary<?, ?> otherDictionary);
    @Method(selector = "writeToFile:atomically:")
    protected native boolean writeFile(String path, boolean useAuxiliaryFile);
    @Method(selector = "writeToURL:atomically:")
    public native boolean write(NSURL url, boolean atomically);
    @Method(selector = "initWithDictionary:")
    protected native @Pointer long init(NSDictionary<?, ?> otherDictionary);
    @Method(selector = "initWithObjects:forKeys:")
    protected native @Pointer long init(NSArray<?> objects, NSArray<?> keys);
    @Method(selector = "dictionaryWithContentsOfFile:")
    protected static native NSDictionary<?, ?> read(String path);
    @Method(selector = "dictionaryWithContentsOfURL:")
    public static native NSDictionary<?, ?> read(NSURL url);
    /*</methods>*/
}
