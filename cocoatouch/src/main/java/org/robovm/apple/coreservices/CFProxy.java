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
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFProxy/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<?, ?> data;
    
    protected CFProxy(NSDictionary<?, ?> data) {
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
            NSString val = (NSString)data.get(HostNameKey());
            return CFProxyType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHost() {
        if (data.containsKey(HostNameKey())) {
            NSString val = (NSString)data.get(HostNameKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (data.containsKey(PortNumberKey())) {
            NSNumber val = (NSNumber)data.get(PortNumberKey());
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSURL getAutoConfigurationURL() {
        if (data.containsKey(AutoConfigurationURLKey())) {
            NSURL val = (NSURL)data.get(AutoConfigurationURLKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAutoConfigurationJavaScript() {
        if (data.containsKey(AutoConfigurationJavaScriptKey())) {
            NSString val = (NSString)data.get(AutoConfigurationJavaScriptKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUsername() {
        if (data.containsKey(UsernameKey())) {
            NSString val = (NSString)data.get(UsernameKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPassword() {
        if (data.containsKey(PasswordKey())) {
            NSString val = (NSString)data.get(PasswordKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
//    public CFHTTPMessage getAutoConfigurationHTTPResponse() {
//        if (data.containsKey(AutoConfigurationHTTPResponseKey())) {
//            CFHTTPMessage val = (CFHTTPMessage)data.get(AutoConfigurationHTTPResponseKey()); TODO
//            return val;
//        }
//        return null;
//    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeKey", optional=true)
    protected static native NSString TypeKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyHostNameKey", optional=true)
    protected static native NSString HostNameKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyPortNumberKey", optional=true)
    protected static native NSString PortNumberKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationURLKey", optional=true)
    protected static native NSString AutoConfigurationURLKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationJavaScriptKey", optional=true)
    protected static native NSString AutoConfigurationJavaScriptKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyUsernameKey", optional=true)
    protected static native NSString UsernameKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyPasswordKey", optional=true)
    protected static native NSString PasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyAutoConfigurationHTTPResponseKey", optional=true)
    protected static native NSString AutoConfigurationHTTPResponseKey();
    /*</methods>*/
}
