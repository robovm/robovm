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
@Marshaler(/*<name>*/SecTrustResult/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecTrustResult/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecTrustResult toObject(Class<SecTrustResult> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecTrustResult(o);
        }
        @MarshalsPointer
        public static long toNative(SecTrustResult o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecTrustResult> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecTrustResult> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecTrustResult(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecTrustResult> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecTrustResult i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecTrustResult(CFDictionary data) {
        super(data);
    }
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
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDate getEvaluationDate() {
        if (has(Keys.EvaluationDate())) {
            NSDate val = get(Keys.EvaluationDate(), NSDate.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isExtendedValidation() {
        if (has(Keys.ExtendedValidation())) {
            CFBoolean val = get(Keys.ExtendedValidation(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getOrganizationName() {
        if (has(Keys.OrganizationName())) {
            CFString val = get(Keys.OrganizationName(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SecTrustResultType getResultValue() {
        if (has(Keys.ResultValue())) {
            CFNumber val = get(Keys.ResultValue(), CFNumber.class);
            return SecTrustResultType.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isRevocationChecked() {
        if (has(Keys.RevocationChecked())) {
            CFBoolean val = get(Keys.RevocationChecked(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSDate getRevocationValidUntilDate() {
        if (has(Keys.RevocationValidUntilDate())) {
            NSDate val = get(Keys.RevocationValidUntilDate(), NSDate.class);
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
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustEvaluationDate", optional=true)
        public static native CFType EvaluationDate();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustExtendedValidation", optional=true)
        public static native CFType ExtendedValidation();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustOrganizationName", optional=true)
        public static native CFType OrganizationName();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustResultValue", optional=true)
        public static native CFType ResultValue();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustRevocationChecked", optional=true)
        public static native CFType RevocationChecked();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecTrustRevocationValidUntilDate", optional=true)
        public static native CFType RevocationValidUntilDate();
    }
    /*</keys>*/
}
