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
@Marshaler(CFSystemProxySettings.Marshaler.class)
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSystemProxySettings/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFSystemProxySettings toObject(Class<CFSystemProxySettings> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CFSystemProxySettings(o);
        }
        @MarshalsPointer
        public static long toNative(CFSystemProxySettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CFSystemProxySettings(CFDictionary data) {
        this.data = data;
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
        if (data.containsKey(HTTPEnable())) {
            CFNumber val = data.get(HTTPEnable(), CFNumber.class);
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
        if (data.containsKey(HTTPPort())) {
            CFNumber val = data.get(HTTPPort(), CFNumber.class);
            return val.intValue();
        }
        return -1;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHTTPProxyHost() {
        if (data.containsKey(HTTPProxy())) {
            CFString val = data.get(HTTPProxy(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAutoConfigurationEnabled() {
        if (data.containsKey(ProxyAutoConfigEnable())) {
            CFNumber val = (CFNumber)data.get(ProxyAutoConfigEnable(), CFNumber.class);
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
        if (data.containsKey(ProxyAutoConfigURLString())) {
            CFString val = data.get(ProxyAutoConfigURLString(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getAutoConfigurationJavaScript() {
        if (data.containsKey(ProxyAutoConfigJavaScript())) {
            CFString val = data.get(ProxyAutoConfigJavaScript(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPEnable", optional=true)
    protected static native CFString HTTPEnable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPPort", optional=true)
    protected static native CFString HTTPPort();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesHTTPProxy", optional=true)
    protected static native CFString HTTPProxy();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigEnable", optional=true)
    protected static native CFString ProxyAutoConfigEnable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigURLString", optional=true)
    protected static native CFString ProxyAutoConfigURLString();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFNetworkProxiesProxyAutoConfigJavaScript", optional=true)
    protected static native CFString ProxyAutoConfigJavaScript();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
