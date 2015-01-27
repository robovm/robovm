/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
@Marshaler(/*<name>*/CFHTTPAuthenticationCredentials/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPAuthenticationCredentials/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFHTTPAuthenticationCredentials toObject(Class<CFHTTPAuthenticationCredentials> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CFHTTPAuthenticationCredentials(o);
        }
        @MarshalsPointer
        public static long toNative(CFHTTPAuthenticationCredentials o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFHTTPAuthenticationCredentials> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFHTTPAuthenticationCredentials> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CFHTTPAuthenticationCredentials(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFHTTPAuthenticationCredentials> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFHTTPAuthenticationCredentials i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CFHTTPAuthenticationCredentials(CFDictionary data) {
        super(data);
    }
    public CFHTTPAuthenticationCredentials() {}
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
    public CFHTTPAuthenticationCredentials set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUsername() {
        if (has(Keys.Username())) {
            CFString val = get(Keys.Username(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFHTTPAuthenticationCredentials setUsername(String username) {
        set(Keys.Username(), new CFString(username));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPassword() {
        if (has(Keys.Password())) {
            CFString val = get(Keys.Password(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFHTTPAuthenticationCredentials setPassword(String password) {
        set(Keys.Password(), new CFString(password));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getAccountDomain() {
        if (has(Keys.AccountDomain())) {
            CFString val = get(Keys.AccountDomain(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFHTTPAuthenticationCredentials setAccountDomain(String accountDomain) {
        set(Keys.AccountDomain(), new CFString(accountDomain));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CFNetwork")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationUsername", optional=true)
        public static native CFString Username();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationPassword", optional=true)
        public static native CFString Password();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationAccountDomain", optional=true)
        public static native CFString AccountDomain();
    }
    /*</keys>*/
}
