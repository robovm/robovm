/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/SecImportItem/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecImportItem/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecImportItem toObject(Class<SecImportItem> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecImportItem(o);
        }
        @MarshalsPointer
        public static long toNative(SecImportItem o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecImportItem> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecImportItem> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecImportItem(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecImportItem> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecImportItem i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecImportItem(CFDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getLabel() {
        if (has(Keys.Label())) {
            CFString val = get(Keys.Label(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getKeyID() {
        if (has(Keys.KeyID())) {
            NSData val = get(Keys.KeyID(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecTrust getTrust() {
        if (has(Keys.Trust())) {
            SecTrust val = get(Keys.Trust(), SecTrust.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public List<SecCertificate> getCertChain() {
        if (has(Keys.CertChain())) {
            CFArray val = get(Keys.CertChain(), CFArray.class);
            return val.toList(SecCertificate.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecIdentity getIdentity() {
        if (has(Keys.Identity())) {
            SecIdentity val = get(Keys.Identity(), SecIdentity.class);
            return val;
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecImportItemLabel", optional=true)
        public static native CFString Label();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecImportItemKeyID", optional=true)
        public static native CFString KeyID();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecImportItemTrust", optional=true)
        public static native CFString Trust();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecImportItemCertChain", optional=true)
        public static native CFString CertChain();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecImportItemIdentity", optional=true)
        public static native CFString Identity();
    }
    /*</keys>*/
}
