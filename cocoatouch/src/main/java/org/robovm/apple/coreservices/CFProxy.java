/*
 * Copyright (C) 2014 Trillian Mobile AB
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
@Marshaler(CFProxy.Marshaler.class)
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFProxy/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CFProxy(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CFProxy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFProxyType getType() {
        if (data.containsKey(HostNameKey())) {
            CFString val = data.get(HostNameKey(), CFString.class);
            return CFProxyType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHost() {
        if (data.containsKey(HostNameKey())) {
            CFString val = data.get(HostNameKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (data.containsKey(PortNumberKey())) {
            CFNumber val = (CFNumber)data.get(PortNumberKey(), CFNumber.class);
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSURL getAutoConfigurationURL() {
        if (data.containsKey(AutoConfigurationURLKey())) {
            NSURL val = data.get(AutoConfigurationURLKey(), NSURL.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAutoConfigurationJavaScript() {
        if (data.containsKey(AutoConfigurationJavaScriptKey())) {
            CFString val = data.get(AutoConfigurationJavaScriptKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUsername() {
        if (data.containsKey(UsernameKey())) {
            CFString val = data.get(UsernameKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPassword() {
        if (data.containsKey(PasswordKey())) {
            CFString val = data.get(PasswordKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFHTTPMessage getAutoConfigurationHTTPResponse() {
        if (data.containsKey(AutoConfigurationHTTPResponseKey())) {
            CFHTTPMessage val = data.get(AutoConfigurationHTTPResponseKey(), CFHTTPMessage.class);
            return val;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeKey", optional=true)
    protected static native CFString TypeKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyHostNameKey", optional=true)
    protected static native CFString HostNameKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyPortNumberKey", optional=true)
    protected static native CFString PortNumberKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationURLKey", optional=true)
    protected static native CFString AutoConfigurationURLKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationJavaScriptKey", optional=true)
    protected static native CFString AutoConfigurationJavaScriptKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyUsernameKey", optional=true)
    protected static native CFString UsernameKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyPasswordKey", optional=true)
    protected static native CFString PasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationHTTPResponseKey", optional=true)
    protected static native CFString AutoConfigurationHTTPResponseKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
