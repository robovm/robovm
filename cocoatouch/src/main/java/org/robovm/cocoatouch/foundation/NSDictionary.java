/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html">NSDictionary Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSDictionary /*</name>*/ <K extends NSObject, V extends NSObject>
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ implements Map<K, V> {

    static {
        ObjCRuntime.bind(/*<name>*/ NSDictionary /*</name>*/.class);
    }

    static class KeySet<K extends NSObject> extends AbstractSet<K> {
        private final NSDictionary<K, ? extends NSObject> map;
        
        KeySet(NSDictionary<K, ? extends NSObject> map) {
            this.map = map;
        }

        @Override
        public Iterator<K> iterator() {
            final Iterator<K> it = map.allKeys().iterator();
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
            return map.count();
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
                return (key == null ? entry.getKey() == null : key.equals(entry
                        .getKey()))
                        && (value == null ? entry.getValue() == null : value
                                .equals(entry.getValue()));
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

                @SuppressWarnings("serial")
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
            return map.count();
        }
        
    }
    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSDictionary /*</name>*/.class);

    /*<constructors>*/
    protected NSDictionary(SkipInit skipInit) { super(skipInit); }
    public NSDictionary() {}
    
    /*</constructors>*/
    
    public NSDictionary(Map<K, V> m) {
        super((SkipInit) null);
        if (m instanceof NSDictionary) {
            initObject(objc_initWithDictionary(this, initWithDictionary$, (NSDictionary<K, V>) m));
        } else {
            Set<K> keys = m.keySet();
            List<V> objects = new ArrayList<V>(keys.size());
            for (K key : keys) {
                objects.add(m.get(key));
            }
            initObject(objc_initWithObjects(this, initWithObjects$forKeys$, new NSArray<V>(objects), new NSArray<K>(keys)));
        }
    }
    
    /*<properties>*/
    
    /*</properties>*/

    public void clear() {
        throw new UnsupportedOperationException("NSDictionary is immutable");
    }
    public boolean containsKey(Object key) {
        return get(key) != null;
    }
    public boolean containsValue(Object value) {
        if (!(value instanceof NSObject)) {
            return false;
        }
        NSArray<V> values = allValues();
        int count = values.count();
        for (int i = 0; i < count; i++) {
            NSObject o = values.objectAtIndex(i);
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet<K, V>(this);
    }
    public V get(Object key) {
        if (!(key instanceof NSObject)) {
            return null;
        }
        return (V) objectForKey((K) key);
    }
    public boolean isEmpty() {
        return count() == 0;
    }
    public Set<K> keySet() {
        return new KeySet<K>(this);
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
    public int size() {
        return count();
    }
    public Collection<V> values() {
        return allValues();
    }
    
    /*<methods>*/
    
    private static final Selector dictionaryWithContentsOfFile$ = Selector.register("dictionaryWithContentsOfFile:");
    @Bridge private native static NSDictionary objc_fromFile(ObjCClass __self__, Selector __cmd__, String path);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/clm/NSDictionary/dictionaryWithContentsOfFile:">+ (id)dictionaryWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSDictionary fromFile(String path) {
        return objc_fromFile(objCClass, dictionaryWithContentsOfFile$, path);
    }
    
    private static final Selector dictionaryWithContentsOfURL$ = Selector.register("dictionaryWithContentsOfURL:");
    @Bridge private native static NSDictionary objc_fromUrl(ObjCClass __self__, Selector __cmd__, NSURL aURL);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/clm/NSDictionary/dictionaryWithContentsOfURL:">+ (id)dictionaryWithContentsOfURL:(NSURL *)aURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSDictionary fromUrl(NSURL aURL) {
        return objc_fromUrl(objCClass, dictionaryWithContentsOfURL$, aURL);
    }
    
    private static final Selector allKeys = Selector.register("allKeys");
    @Bridge private native static NSArray objc_allKeys(NSDictionary __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_allKeysSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/allKeys">- (NSArray *)allKeys</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSArray allKeys() {
        if (customClass) { return objc_allKeysSuper(getSuper(), allKeys); } else { return objc_allKeys(this, allKeys); }
    }
    
    private static final Selector allValues = Selector.register("allValues");
    @Bridge private native static NSArray objc_allValues(NSDictionary __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_allValuesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/allValues">- (NSArray *)allValues</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSArray allValues() {
        if (customClass) { return objc_allValuesSuper(getSuper(), allValues); } else { return objc_allValues(this, allValues); }
    }
    
    private static final Selector count = Selector.register("count");
    @Bridge private native static int objc_count(NSDictionary __self__, Selector __cmd__);
    @Bridge private native static int objc_countSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/count">- (NSUInteger)count</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int count() {
        if (customClass) { return objc_countSuper(getSuper(), count); } else { return objc_count(this, count); }
    }
    
    private static final Selector initWithDictionary$ = Selector.register("initWithDictionary:");
    @Bridge private native static @Pointer long objc_initWithDictionary(NSDictionary __self__, Selector __cmd__, NSDictionary otherDictionary);
    @Bridge private native static @Pointer long objc_initWithDictionarySuper(ObjCSuper __super__, Selector __cmd__, NSDictionary otherDictionary);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/initWithDictionary:">- (id)initWithDictionary:(NSDictionary *)otherDictionary</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithDictionary(NSDictionary otherDictionary) {
        if (customClass) { return objc_initWithDictionarySuper(getSuper(), initWithDictionary$, otherDictionary); } else { return objc_initWithDictionary(this, initWithDictionary$, otherDictionary); }
    }
    
    private static final Selector initWithObjects$forKeys$ = Selector.register("initWithObjects:forKeys:");
    @Bridge private native static @Pointer long objc_initWithObjects(NSDictionary __self__, Selector __cmd__, NSArray objects, NSArray keys);
    @Bridge private native static @Pointer long objc_initWithObjectsSuper(ObjCSuper __super__, Selector __cmd__, NSArray objects, NSArray keys);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/initWithObjects:forKeys:">- (id)initWithObjects:(NSArray *)objects forKeys:(NSArray *)keys</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithObjects(NSArray objects, NSArray keys) {
        if (customClass) { return objc_initWithObjectsSuper(getSuper(), initWithObjects$forKeys$, objects, keys); } else { return objc_initWithObjects(this, initWithObjects$forKeys$, objects, keys); }
    }
    
    private static final Selector objectForKey$ = Selector.register("objectForKey:");
    @Bridge private native static NSObject objc_objectForKey(NSDictionary __self__, Selector __cmd__, NSObject aKey);
    @Bridge private native static NSObject objc_objectForKeySuper(ObjCSuper __super__, Selector __cmd__, NSObject aKey);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/objectForKey:">- (id)objectForKey:(id)aKey</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject objectForKey(NSObject aKey) {
        if (customClass) { return objc_objectForKeySuper(getSuper(), objectForKey$, aKey); } else { return objc_objectForKey(this, objectForKey$, aKey); }
    }
    
    private static final Selector writeToFile$atomically$ = Selector.register("writeToFile:atomically:");
    @Bridge private native static boolean objc_writeToFile(NSDictionary __self__, Selector __cmd__, String path, boolean flag);
    @Bridge private native static boolean objc_writeToFileSuper(ObjCSuper __super__, Selector __cmd__, String path, boolean flag);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/writeToFile:atomically:">- (BOOL)writeToFile:(NSString *)path atomically:(BOOL)flag</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean writeToFile(String path, boolean flag) {
        if (customClass) { return objc_writeToFileSuper(getSuper(), writeToFile$atomically$, path, flag); } else { return objc_writeToFile(this, writeToFile$atomically$, path, flag); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allKeys") public static NSArray allKeys(NSDictionary __self__, Selector __cmd__) { return __self__.allKeys(); }
        @Callback @BindSelector("allValues") public static NSArray allValues(NSDictionary __self__, Selector __cmd__) { return __self__.allValues(); }
        @Callback @BindSelector("count") public static int count(NSDictionary __self__, Selector __cmd__) { return __self__.count(); }
        @Callback @BindSelector("initWithDictionary:") public static @Pointer long initWithDictionary(NSDictionary __self__, Selector __cmd__, NSDictionary otherDictionary) { return __self__.initWithDictionary(otherDictionary); }
        @Callback @BindSelector("initWithObjects:forKeys:") public static @Pointer long initWithObjects(NSDictionary __self__, Selector __cmd__, NSArray objects, NSArray keys) { return __self__.initWithObjects(objects, keys); }
        @Callback @BindSelector("objectForKey:") public static NSObject objectForKey(NSDictionary __self__, Selector __cmd__, NSObject aKey) { return __self__.objectForKey(aKey); }
        @Callback @BindSelector("writeToFile:atomically:") public static boolean writeToFile(NSDictionary __self__, Selector __cmd__, String path, boolean flag) { return __self__.writeToFile(path, flag); }
    }
    /*</callbacks>*/

}

