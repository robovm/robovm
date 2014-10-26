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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.NSDictionary.Entry;
import org.robovm.apple.foundation.NSDictionary.EntrySet;
import org.robovm.apple.foundation.NSDictionary.KeySet;
/*</imports>*/
import org.robovm.apple.foundation.NSDictionary.NSDictionaryPtr;
import org.robovm.apple.foundation.NSObject.SkipInit;

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMapTable/*</name>*/ <K extends NSObject, V extends NSObject>
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/, Map<K, V> {

    public static class NSMapTablePtr<K extends NSObject, V extends NSObject> extends Ptr<NSMapTable<K, V>, NSMapTablePtr<K, V>> {}
    
    static class KeySet<K extends NSObject> extends AbstractSet<K> {
        private final NSMapTable<K, ? extends NSObject> map;
        
        KeySet(NSMapTable<K, ? extends NSObject> map) {
            this.map = map;
        }

        @Override
        public Iterator<K> iterator() {
            return new NSEnumerator.Iterator<K>(map.keyEnumerator()) {
                void remove(int index, K o) {
                    map.removeObjectForKey$(o);
                }
            };
        }

        @Override
        public int size() {
            return (int) map.getCount();
        }
    }
    
    static class Entry<K extends NSObject, V extends NSObject> implements Map.Entry<K, V> {
        private final NSMapTable<K, V> map;
        private final K key;
        private final V value;
        
        Entry(NSMapTable<K, V> map, K key, V value) {
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
        private final NSMapTable<K, V> map;

        EntrySet(NSMapTable<K, V> map) {
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
    
    /*<bind>*/static { ObjCRuntime.bind(NSMapTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMapTable() {}
    protected NSMapTable(SkipInit skipInit) { super(skipInit); }
    public NSMapTable(NSMapTableOptions keyOptions, NSMapTableOptions valueOptions, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(init(keyOptions, valueOptions, initialCapacity)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "count")
    protected native @MachineSizedUInt long getCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    private static void checkNull(Object key, Object value) {
        if (key == null || value == null) {
            throw new NullPointerException("null keys or values are not " 
                    + "allowed in NSMapTable. Use NSNull instead.");
        }
    }
    
    public boolean containsKey(Object key) {
        return get(key) != null;
    }
    public boolean containsValue(Object value) {
        if (!(value instanceof NSObject)) {
            return false;
        }
        NSArray<V> values = objectEnumerator().getAllObjects();
        int count = (int) values.getCount();
        for (int i = 0; i < count; i++) {
            NSObject o = values.objectAtIndex$(i);
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
        return (V) objectForKey$((K) key);
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
        return objectEnumerator().getAllObjects();
    }
    @Override
    public void clear() {
        removeAllObjects();
    }
    @Override
    public V remove(Object key) {
        if (!(key instanceof NSObject)) {
            return null;
        }
        V oldValue = get(key);
        removeObjectForKey$((NSObject) key);
        return oldValue;
    }
    @Override
    public V put(K key, V value) {
        checkNull(key, value);
        V oldValue = get(key);
        setObject$forKey$(value, key);
        return oldValue;
    }
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    public NSMapTable(Map<K, V> m) {
        putAll(m);
    }
    
    public NSMapTable(Map<K, V> m, NSMapTableOptions keyOptions, NSMapTableOptions valueOptions, @MachineSizedUInt long initialCapacity) {
        super((SkipInit) null);
        initObject(init(keyOptions, valueOptions, initialCapacity));
        putAll(m);
    }
    
    /*<methods>*/
    @Method(selector = "initWithKeyOptions:valueOptions:capacity:")
    protected native @Pointer long init(NSMapTableOptions keyOptions, NSMapTableOptions valueOptions, @MachineSizedUInt long initialCapacity);
    @Method(selector = "objectForKey:")
    protected native NSObject objectForKey$(NSObject aKey);
    @Method(selector = "removeObjectForKey:")
    protected native void removeObjectForKey$(NSObject aKey);
    @Method(selector = "setObject:forKey:")
    protected native void setObject$forKey$(NSObject anObject, NSObject aKey);
    @Method(selector = "keyEnumerator")
    protected native NSEnumerator<K> keyEnumerator();
    @Method(selector = "objectEnumerator")
    protected native NSEnumerator<V> objectEnumerator();
    @Method(selector = "removeAllObjects")
    protected native void removeAllObjects();
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<K, V> asDictionary();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
