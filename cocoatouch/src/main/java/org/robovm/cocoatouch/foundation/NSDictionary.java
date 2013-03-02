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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html">NSDictionary Class Reference</a>
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

    static class EntrySet<K extends NSObject, V extends NSObject> extends AbstractSet<Map.Entry<K, V>> {
        private final NSDictionary<K, V> map;

        EntrySet(NSDictionary<K, V> map) {
            this.map = map;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            final Iterator<K> keysIt = map.keySet().iterator();
            return new Iterator<Map.Entry<K, V>>() {
                Map.Entry<K, V> entry = null;

                @Override
                public boolean hasNext() {
                    return keysIt.hasNext();
                }

                @SuppressWarnings("serial")
                @Override
                public Map.Entry<K, V> next() {
                    K key = keysIt.next();
                    entry = new SimpleEntry<K, V>(key, map.get(key)) {
                        public V setValue(V v) {
                            Object value = map.get(entry.getKey());
                            if (entry.getValue() != value) {
                                throw new ConcurrentModificationException();
                            }
                            return super.setValue(v);
                        }
                    };
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
            return map.size();
        }
        
    }
    
    static class MapAdapter<K extends NSObject, V extends NSObject> extends AbstractMap<K, V> {
        private final NSDictionary<K, V> map;
        EntrySet<K, V> entrySet = null;
        

        MapAdapter(NSDictionary<K, V> map) {
            this.map = map;
        }

        public boolean containsKey(Object key) {
            return get(key) != null;
        }
        
        @SuppressWarnings("unchecked")
        public V get(Object key) {
            if (key instanceof NSObject) {
                return (V) map.objectForKey((NSObject) key) ;
            }
            return null;
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            if (entrySet == null) {
                entrySet = new EntrySet<K, V>(map);
            }
            return entrySet;
        }
    }
    
    private static final boolean X86 = Bro.IS_X86;
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSDictionary /*</name>*/.class);

    private AbstractMap<K, V> adapter = createAdapter();

    /*<constructors>*/
    protected NSDictionary(SkipInit skipInit) { super(skipInit); }
    public NSDictionary() {}
    
    /*</constructors>*/
    
    public NSDictionary(Map<K, V> m) {
        super((SkipInit) null);
        if (m instanceof NSDictionary) {
            setHandle(objc_initWithDictionary(this, initWithDictionary$, (NSDictionary<K, V>) m));
        } else {
            Set<K> keys = m.keySet();
            List<V> objects = new ArrayList<V>(keys.size());
            for (K key : keys) {
                objects.add(m.get(key));
            }
            setHandle(objc_initWithObjects(this, initWithObjects$forKeys$, new NSArray<V>(objects), new NSArray<K>(keys)));
        }
    }
    
    /*<properties>*/
    
    /*</properties>*/

    protected AbstractMap<K, V> createAdapter() {
        return new MapAdapter<K, V>(this);
    }
    
    @Override
    protected void afterMarshaled() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        super.afterMarshaled();
    }
    
    public void clear() {
        adapter.clear();
    }
    public boolean containsKey(Object key) {
        return adapter.containsKey(key);
    }
    public boolean containsValue(Object value) {
        return adapter.containsValue(value);
    }
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return adapter.entrySet();
    }
    public V get(Object key) {
        return adapter.get(key);
    }
    public boolean isEmpty() {
        return adapter.isEmpty();
    }
    public Set<K> keySet() {
        return adapter.keySet();
    }
    public V put(K key, V value) {
        return adapter.put(key, value);
    }
    public void putAll(Map<? extends K, ? extends V> m) {
        adapter.putAll(m);
    }
    public V remove(Object key) {
        return adapter.remove(key);
    }
    public int size() {
        return adapter.size();
    }
    public Collection<V> values() {
        return adapter.values();
    }
    
    /*<methods>*/
    
    private static final Selector dictionaryWithContentsOfFile$ = Selector.register("dictionaryWithContentsOfFile:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_fromFile(ObjCClass __self__, Selector __cmd__, String path);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/clm/NSDictionary/dictionaryWithContentsOfFile:">+ (id)dictionaryWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSDictionary fromFile(String path) {
        return objc_fromFile(objCClass, dictionaryWithContentsOfFile$, path);
    }
    
    private static final Selector dictionaryWithContentsOfURL$ = Selector.register("dictionaryWithContentsOfURL:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_fromUrl(ObjCClass __self__, Selector __cmd__, NSURL aURL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/clm/NSDictionary/dictionaryWithContentsOfURL:">+ (id)dictionaryWithContentsOfURL:(NSURL *)aURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSDictionary fromUrl(NSURL aURL) {
        return objc_fromUrl(objCClass, dictionaryWithContentsOfURL$, aURL);
    }
    
    private static final Selector allKeys = Selector.register("allKeys");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_allKeys(NSDictionary __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_allKeysSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/allKeys">- (NSArray *)allKeys</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSArray allKeys() {
        if (customClass) { return objc_allKeysSuper(getSuper(), allKeys); } else { return objc_allKeys(this, allKeys); }
    }
    
    private static final Selector allValues = Selector.register("allValues");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_allValues(NSDictionary __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_allValuesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/allValues">- (NSArray *)allValues</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSArray allValues() {
        if (customClass) { return objc_allValuesSuper(getSuper(), allValues); } else { return objc_allValues(this, allValues); }
    }
    
    private static final Selector count = Selector.register("count");
    @Bridge(symbol = "objc_msgSend") private native static int objc_count(NSDictionary __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_countSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/count">- (NSUInteger)count</a>
     * @since Available in iOS 2.0 and later.
     */
    protected int count() {
        if (customClass) { return objc_countSuper(getSuper(), count); } else { return objc_count(this, count); }
    }
    
    private static final Selector initWithDictionary$ = Selector.register("initWithDictionary:");
    @Bridge(symbol = "objc_msgSend") private native static @Pointer long objc_initWithDictionary(NSDictionary __self__, Selector __cmd__, NSDictionary otherDictionary);
    @Bridge(symbol = "objc_msgSendSuper") private native static @Pointer long objc_initWithDictionarySuper(ObjCSuper __super__, Selector __cmd__, NSDictionary otherDictionary);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/initWithDictionary:">- (id)initWithDictionary:(NSDictionary *)otherDictionary</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithDictionary(NSDictionary otherDictionary) {
        if (customClass) { return objc_initWithDictionarySuper(getSuper(), initWithDictionary$, otherDictionary); } else { return objc_initWithDictionary(this, initWithDictionary$, otherDictionary); }
    }
    
    private static final Selector initWithObjects$forKeys$ = Selector.register("initWithObjects:forKeys:");
    @Bridge(symbol = "objc_msgSend") private native static @Pointer long objc_initWithObjects(NSDictionary __self__, Selector __cmd__, NSArray objects, NSArray keys);
    @Bridge(symbol = "objc_msgSendSuper") private native static @Pointer long objc_initWithObjectsSuper(ObjCSuper __super__, Selector __cmd__, NSArray objects, NSArray keys);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/initWithObjects:forKeys:">- (id)initWithObjects:(NSArray *)objects forKeys:(NSArray *)keys</a>
     * @since Available in iOS 2.0 and later.
     */
    protected @Pointer long initWithObjects(NSArray objects, NSArray keys) {
        if (customClass) { return objc_initWithObjectsSuper(getSuper(), initWithObjects$forKeys$, objects, keys); } else { return objc_initWithObjects(this, initWithObjects$forKeys$, objects, keys); }
    }
    
    private static final Selector objectForKey$ = Selector.register("objectForKey:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_objectForKey(NSDictionary __self__, Selector __cmd__, NSObject aKey);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_objectForKeySuper(ObjCSuper __super__, Selector __cmd__, NSObject aKey);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/objectForKey:">- (id)objectForKey:(id)aKey</a>
     * @since Available in iOS 2.0 and later.
     */
    protected NSObject objectForKey(NSObject aKey) {
        if (customClass) { return objc_objectForKeySuper(getSuper(), objectForKey$, aKey); } else { return objc_objectForKey(this, objectForKey$, aKey); }
    }
    
    private static final Selector writeToFile$atomically$ = Selector.register("writeToFile:atomically:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_writeToFile(NSDictionary __self__, Selector __cmd__, String path, boolean flag);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_writeToFileSuper(ObjCSuper __super__, Selector __cmd__, String path, boolean flag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSDictionary/writeToFile:atomically:">- (BOOL)writeToFile:(NSString *)path atomically:(BOOL)flag</a>
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

