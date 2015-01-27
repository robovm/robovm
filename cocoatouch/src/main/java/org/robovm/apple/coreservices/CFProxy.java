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
@Marshaler(/*<name>*/CFProxy/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFProxy/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFProxy toObject(Class<CFProxy> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CFProxy(o);
        }
        @MarshalsPointer
        public static long toNative(CFProxy o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFProxy> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFProxy> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CFProxy(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFProxy> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFProxy i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CFProxy(CFDictionary data) {
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
    public CFProxyType getType() {
        if (has(Keys.Type())) {
            CFString val = get(Keys.Type(), CFString.class);
            return CFProxyType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHost() {
        if (has(Keys.HostName())) {
            CFString val = get(Keys.HostName(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (has(Keys.PortNumber())) {
            CFNumber val = get(Keys.PortNumber(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSURL getAutoConfigurationURL() {
        if (has(Keys.AutoConfigurationURL())) {
            NSURL val = get(Keys.AutoConfigurationURL(), NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAutoConfigurationJavaScript() {
        if (has(Keys.AutoConfigurationJavaScript())) {
            CFString val = get(Keys.AutoConfigurationJavaScript(), CFString.class);
            return val.toString();
        }
        return null;
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
    public CFHTTPMessage getAutoConfigurationHTTPResponse() {
        if (has(Keys.AutoConfigurationHTTPResponse())) {
            CFHTTPMessage val = get(Keys.AutoConfigurationHTTPResponse(), CFHTTPMessage.class);
            return val;
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CFNetwork")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyTypeKey", optional=true)
        public static native CFString Type();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyHostNameKey", optional=true)
        public static native CFString HostName();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyPortNumberKey", optional=true)
        public static native CFString PortNumber();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyAutoConfigurationURLKey", optional=true)
        public static native CFString AutoConfigurationURL();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCFProxyAutoConfigurationJavaScriptKey", optional=true)
        public static native CFString AutoConfigurationJavaScript();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyUsernameKey", optional=true)
        public static native CFString Username();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyPasswordKey", optional=true)
        public static native CFString Password();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFProxyAutoConfigurationHTTPResponseKey", optional=true)
        public static native CFString AutoConfigurationHTTPResponse();
    }
    /*</keys>*/
}
