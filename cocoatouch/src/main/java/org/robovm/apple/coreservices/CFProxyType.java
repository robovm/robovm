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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFProxyType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFProxyType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType None = new CFProxyType() {
        public NSString value() {
            return NoneValue();
        }
    };
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType HTTP = new CFProxyType() {
        public NSString value() {
            return HTTPValue();
        }
    };
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType HTTPS = new CFProxyType() {
        public NSString value() {
            return HTTPSValue();
        }
    };
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType SOCKS = new CFProxyType() {
        public NSString value() {
            return SOCKSValue();
        }
    };
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType FTP = new CFProxyType() {
        public NSString value() {
            return FTPValue();
        }
    };
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFProxyType AutoConfigurationURL = new CFProxyType() {
        public NSString value() {
            return AutoConfigurationURLValue();
        }
    };
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static CFProxyType AutoConfigurationJavaScript = new CFProxyType() {
        public NSString value() {
            return AutoConfigurationJavaScriptValue();
        }
    };
    private static CFProxyType[] values = new CFProxyType[] {None, HTTP, HTTPS, SOCKS, FTP, AutoConfigurationURL, AutoConfigurationJavaScript};
    
    private CFProxyType() {
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return null;
    }
    
    public static CFProxyType valueOf(NSString value) {
        if (value == null) throw new NullPointerException("Value cannot be null!");
        for (CFProxyType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFProxyType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeNone", optional=true)
    protected static native NSString NoneValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeHTTP", optional=true)
    protected static native NSString HTTPValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeHTTPS", optional=true)
    protected static native NSString HTTPSValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeSOCKS", optional=true)
    protected static native NSString SOCKSValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeFTP", optional=true)
    protected static native NSString FTPValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeAutoConfigurationURL", optional=true)
    protected static native NSString AutoConfigurationURLValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFProxyTypeAutoConfigurationJavaScript", optional=true)
    protected static native NSString AutoConfigurationJavaScriptValue();
    /*</methods>*/
}
