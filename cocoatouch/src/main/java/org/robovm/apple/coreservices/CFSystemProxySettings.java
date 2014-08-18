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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSystemProxySettings/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<?, ?> settings;
    
    protected CFSystemProxySettings(NSDictionary<?, ?> settings) {
        this.settings = settings;
    }
    /*<bind>*/static { Bro.bind(CFSystemProxySettings.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHTTPProxyEnabled() {
        if (settings.containsKey(HTTPEnable())) {
            NSNumber val = (NSNumber)settings.get(HTTPEnable());
            if (val.intValue() != 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getHTTPProxyPort() {
        if (settings.containsKey(HTTPPort())) {
            NSNumber val = (NSNumber)settings.get(HTTPPort());
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHTTPProxyHost() {
        if (settings.containsKey(HTTPProxy())) {
            NSString val = (NSString)settings.get(HTTPProxy());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAutoConfigurationEnabled() {
        if (settings.containsKey(ProxyAutoConfigEnable())) {
            NSNumber val = (NSNumber)settings.get(ProxyAutoConfigEnable());
            if (val.intValue() != 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getAutoConfigurationURL() {
        if (settings.containsKey(ProxyAutoConfigURLString())) {
            NSString val = (NSString)settings.get(ProxyAutoConfigURLString());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAutoConfigurationJavaScript() {
        if (settings.containsKey(ProxyAutoConfigJavaScript())) {
            NSString val = (NSString)settings.get(ProxyAutoConfigJavaScript());
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPEnable", optional=true)
    protected static native NSString HTTPEnable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPPort", optional=true)
    protected static native NSString HTTPPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPProxy", optional=true)
    protected static native NSString HTTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigEnable", optional=true)
    protected static native NSString ProxyAutoConfigEnable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigURLString", optional=true)
    protected static native NSString ProxyAutoConfigURLString();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigJavaScript", optional=true)
    protected static native NSString ProxyAutoConfigJavaScript();
    /*</methods>*/
}
