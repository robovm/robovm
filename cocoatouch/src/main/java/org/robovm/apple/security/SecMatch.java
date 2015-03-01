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
@Marshaler(/*<name>*/SecMatch/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecMatch/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecMatch toObject(Class<SecMatch> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecMatch(o);
        }
        @MarshalsPointer
        public static long toNative(SecMatch o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecMatch> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecMatch> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecMatch(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecMatch> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecMatch i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecMatch(CFDictionary data) {
        super(data);
    }
    public SecMatch() {}
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
    public SecMatch set(CFType key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecPolicy getPolicy() {
        if (has(Keys.Policy())) {
            SecPolicy val = get(Keys.Policy(), SecPolicy.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setPolicy(SecPolicy policy) {
        set(Keys.Policy(), policy);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSArray<NSData> getIssuers() {
        if (has(Keys.Issuers())) {
            NSArray<NSData> val = get(Keys.Issuers(), NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setIssuers(NSArray<NSData> issuers) {
        set(Keys.Issuers(), issuers);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getEmailAddressIfPresent() {
        if (has(Keys.EmailAddressIfPresent())) {
            CFString val = get(Keys.EmailAddressIfPresent(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setEmailAddressIfPresent(String emailAddressIfPresent) {
        set(Keys.EmailAddressIfPresent(), new CFString(emailAddressIfPresent));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getSubjectContains() {
        if (has(Keys.SubjectContains())) {
            CFString val = get(Keys.SubjectContains(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setSubjectContains(String subjectContains) {
        set(Keys.SubjectContains(), new CFString(subjectContains));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCaseInsensitive() {
        if (has(Keys.CaseInsensitive())) {
            CFBoolean val = get(Keys.CaseInsensitive(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setCaseInsensitive(boolean caseInsensitive) {
        set(Keys.CaseInsensitive(), CFBoolean.valueOf(caseInsensitive));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isTrustedOnly() {
        if (has(Keys.TrustedOnly())) {
            CFBoolean val = get(Keys.TrustedOnly(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setTrustedOnly(boolean trustedOnly) {
        set(Keys.TrustedOnly(), CFBoolean.valueOf(trustedOnly));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getValidOnDate() {
        if (has(Keys.ValidOnDate())) {
            NSDate val = get(Keys.ValidOnDate(), NSDate.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setValidOnDate(NSDate validOnDate) {
        set(Keys.ValidOnDate(), validOnDate);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatchLimit getLimit() {
        if (has(Keys.Limit())) {
            CFType val = get(Keys.Limit(), CFType.class);
            return SecMatchLimit.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecMatch setLimit(SecMatchLimit limit) {
        set(Keys.Limit(), limit.value());
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
        @GlobalValue(symbol="kSecMatchPolicy", optional=true)
        public static native CFType Policy();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchItemList", optional=true)
        public static native CFType ItemList();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchSearchList", optional=true)
        public static native CFType SearchList();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchIssuers", optional=true)
        public static native CFType Issuers();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchEmailAddressIfPresent", optional=true)
        public static native CFType EmailAddressIfPresent();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchSubjectContains", optional=true)
        public static native CFType SubjectContains();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchCaseInsensitive", optional=true)
        public static native CFType CaseInsensitive();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchTrustedOnly", optional=true)
        public static native CFType TrustedOnly();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchValidOnDate", optional=true)
        public static native CFType ValidOnDate();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecMatchLimit", optional=true)
        public static native CFType Limit();
    }
    /*</keys>*/
}
