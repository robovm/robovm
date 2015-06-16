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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecPolicyProperties/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecPolicyProperties/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecPolicyProperties toObject(Class<SecPolicyProperties> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecPolicyProperties(o);
        }
        @MarshalsPointer
        public static long toNative(SecPolicyProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecPolicyProperties> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecPolicyProperties> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecPolicyProperties(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecPolicyProperties> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecPolicyProperties i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecPolicyProperties(CFDictionary data) {
        super(data);
    }
    public SecPolicyProperties() {}
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
    public SecPolicyProperties set(CFType key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getOid() {
        if (has(Keys.Oid())) {
            CFString val = get(Keys.Oid(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecPolicyProperties setOid(String oid) {
        set(Keys.Oid(), new CFString(oid));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getName() {
        if (has(Keys.Name())) {
            CFString val = get(Keys.Name(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecPolicyProperties setName(String name) {
        set(Keys.Name(), new CFString(name));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isClient() {
        if (has(Keys.Client())) {
            CFBoolean val = get(Keys.Client(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecPolicyProperties setClient(boolean client) {
        set(Keys.Client(), CFBoolean.valueOf(client));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecRevocationPolicyFlags getRevocationFlags() {
        if (has(Keys.RevocationFlags())) {
            CFNumber val = get(Keys.RevocationFlags(), CFNumber.class);
            return new SecRevocationPolicyFlags(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecPolicyProperties setRevocationFlags(SecRevocationPolicyFlags revocationFlags) {
        set(Keys.RevocationFlags(), CFNumber.valueOf(revocationFlags.value()));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyOid", optional=true)
        public static native CFType Oid();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyName", optional=true)
        public static native CFType Name();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyClient", optional=true)
        public static native CFType Client();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyRevocationFlags", optional=true)
        public static native CFType RevocationFlags();
    }
    /*</keys>*/
}
