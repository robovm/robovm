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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableDictionary/*</name>*/ <K extends NSObject, V extends NSObject>
    extends /*<extends>*/NSDictionary/*</extends>*/ <K, V>
    /*<implements>*//*</implements>*/ {

    public static class NSMutableDictionaryPtr<K extends NSObject, V extends NSObject> extends Ptr<NSMutableDictionary<K, V>, NSMutableDictionaryPtr<K, V>> {}
    /*<bind>*/static { ObjCRuntime.bind(NSMutableDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSMutableDictionary(SkipInit skipInit) { super(skipInit); }
    public NSMutableDictionary(@MachineSizedUInt long numItems) { super((SkipInit) null); initObject(init(numItems)); }
    /*</constructors>*/
    
    public NSMutableDictionary() {
        super();
    }
    
    public NSMutableDictionary(K k, V v) {
        super(k, v);
    }

    public NSMutableDictionary(K k1, V v1, K k2, V v2) {
        super(k1, v1, k2, v2);
    }

    public NSMutableDictionary(K k1, V v1, K k2, V v2, K k3, V v3) {
        super(k1, v1, k2, v2, k3, v3);
    }

    public NSMutableDictionary(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        super(k1, v1, k2, v2, k3, v3, k4, v4);
    }

    public NSMutableDictionary(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        super(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
    }

    public NSMutableDictionary(Map<K, V> m) {
        super(m);
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
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
        removeObject((NSObject) key);
        return oldValue;
    }
    
    @Override
    public V put(K key, V value) {
        checkNull(key, value);
        V oldValue = get(key);
        setObject(value, key);
        return oldValue;
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    public static <V extends NSObject> NSMutableDictionary<NSString, V> fromStringMap (Map<String, V> map) {
        NSMutableDictionary<NSString, V> dictionary = new NSMutableDictionary<>();
        for (Map.Entry<String, V> entry : map.entrySet()) {
            dictionary.put(new NSString(entry.getKey()), entry.getValue());
        }
        return dictionary;
    }
    
    public static NSMutableDictionary<?, ?> read(java.io.File file) {
        return read(file.getAbsolutePath());
    }
    @Method(selector = "dictionaryWithContentsOfFile:")
    protected static native NSMutableDictionary<?, ?> read(String path);
    @Method(selector = "dictionaryWithContentsOfURL:")
    public static native NSMutableDictionary<?, ?> read(NSURL url);
    
    /*<methods>*/
    @Method(selector = "removeObjectForKey:")
    protected native void removeObject(NSObject aKey);
    @Method(selector = "setObject:forKey:")
    protected native void setObject(NSObject anObject, NSObject aKey);
    @Method(selector = "initWithCapacity:")
    protected native @Pointer long init(@MachineSizedUInt long numItems);
    @Method(selector = "removeAllObjects")
    protected native void removeAllObjects();
    /*</methods>*/
}
