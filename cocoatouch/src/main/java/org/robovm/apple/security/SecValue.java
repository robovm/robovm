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
package org.robovm.apple.security;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecValue/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecValue/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecValue toObject(Class<SecValue> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecValue(o);
        }
        @MarshalsPointer
        public static long toNative(SecValue o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecValue> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecValue> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecValue(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecValue> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecValue i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecValue(CFDictionary data) {
        super(data);
    }
    public SecValue() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFType key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFType key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public SecValue set(CFType key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getData() {
        if (has(Keys.Data())) {
            NSData val = get(Keys.Data(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecValue setData(NSData data) {
        set(Keys.Data(), data);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFType getRef() {
        if (has(Keys.Ref())) {
            CFType val = get(Keys.Ref(), CFType.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecValue setRef(CFType ref) {
        set(Keys.Ref(), ref);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getPersistentRef() {
        if (has(Keys.PersistentRef())) {
            NSData val = get(Keys.PersistentRef(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecValue setPersistentRef(NSData persistentRef) {
        set(Keys.PersistentRef(), persistentRef);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecValueData", optional=true)
        public static native CFType Data();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecValueRef", optional=true)
        public static native CFType Ref();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecValuePersistentRef", optional=true)
        public static native CFType PersistentRef();
    }
    /*</keys>*/
}
