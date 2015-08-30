/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFSocketStreamProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSocketStreamProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFSocketStreamProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFSocketStreamProperty toObject(Class<CFSocketStreamProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFSocketStreamProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFSocketStreamProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFSocketStreamProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFSocketStreamProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFSocketStreamProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFSocketStreamProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFSocketStreamProperty o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFSocketStreamProperty SSLContext = new CFSocketStreamProperty("SSLContext");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SSLPeerTrust = new CFSocketStreamProperty("SSLPeerTrust");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SSLSettings = new CFSocketStreamProperty("SSLSettings");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFSocketStreamProperty NetworkServiceType = new CFSocketStreamProperty("NetworkServiceType");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFSocketStreamProperty NoCellular = new CFSocketStreamProperty("NoCellular");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CFSocketStreamProperty ConnectionIsCellular = new CFSocketStreamProperty("ConnectionIsCellular");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SOCKSProxy = new CFSocketStreamProperty("SOCKSProxy");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty ProxyLocalBypass = new CFSocketStreamProperty("ProxyLocalBypass");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SocketSecurityLevel = new CFSocketStreamProperty("SocketSecurityLevel");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty ShouldCloseNativeSocket = new CFSocketStreamProperty("ShouldCloseNativeSocket");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SocketRemoteHost = new CFSocketStreamProperty("SocketRemoteHost");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFSocketStreamProperty SocketRemoteNetService = new CFSocketStreamProperty("SocketRemoteNetService");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CFSocketStreamProperty SocketExtendedBackgroundIdleMode = new CFSocketStreamProperty("SocketExtendedBackgroundIdleMode");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPResponseHeader = new CFSocketStreamProperty("HTTPResponseHeader");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPFinalURL = new CFSocketStreamProperty("HTTPFinalURL");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPFinalRequest = new CFSocketStreamProperty("HTTPFinalRequest");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPProxy = new CFSocketStreamProperty("HTTPProxy");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPProxyHost = new CFSocketStreamProperty("HTTPProxyHost");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPProxyPort = new CFSocketStreamProperty("HTTPProxyPort");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPSProxyHost = new CFSocketStreamProperty("HTTPSProxyHost");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPSProxyPort = new CFSocketStreamProperty("HTTPSProxyPort");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPShouldAutoredirect = new CFSocketStreamProperty("HTTPShouldAutoredirect");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPAttemptPersistentConnection = new CFSocketStreamProperty("HTTPAttemptPersistentConnection");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final CFSocketStreamProperty HTTPRequestBytesWrittenCount = new CFSocketStreamProperty("HTTPRequestBytesWrittenCount");
    /*</constants>*/
    
    private static /*<name>*/CFSocketStreamProperty/*</name>*/[] values = new /*<name>*/CFSocketStreamProperty/*</name>*/[] {/*<value_list>*/SSLContext, SSLPeerTrust, SSLSettings, NetworkServiceType, NoCellular, ConnectionIsCellular, SOCKSProxy, ProxyLocalBypass, SocketSecurityLevel, ShouldCloseNativeSocket, SocketRemoteHost, SocketRemoteNetService, SocketExtendedBackgroundIdleMode, HTTPResponseHeader, HTTPFinalURL, HTTPFinalRequest, HTTPProxy, HTTPProxyHost, HTTPProxyPort, HTTPSProxyHost, HTTPSProxyPort, HTTPShouldAutoredirect, HTTPAttemptPersistentConnection, HTTPRequestBytesWrittenCount/*</value_list>*/};
    
    /*<name>*/CFSocketStreamProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFSocketStreamProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFSocketStreamProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFSocketStreamProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySSLContext", optional=true)
        public static native CFString SSLContext();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySSLPeerTrust", optional=true)
        public static native CFString SSLPeerTrust();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySSLSettings", optional=true)
        public static native CFString SSLSettings();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFStreamNetworkServiceType", optional=true)
        public static native CFString NetworkServiceType();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyNoCellular", optional=true)
        public static native CFString NoCellular();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyConnectionIsCellular", optional=true)
        public static native CFString ConnectionIsCellular();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySOCKSProxy", optional=true)
        public static native CFString SOCKSProxy();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyProxyLocalBypass", optional=true)
        public static native CFString ProxyLocalBypass();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySocketSecurityLevel", optional=true)
        public static native CFString SocketSecurityLevel();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertyShouldCloseNativeSocket", optional=true)
        public static native CFString ShouldCloseNativeSocket();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySocketRemoteHost", optional=true)
        public static native CFString SocketRemoteHost();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySocketRemoteNetService", optional=true)
        public static native CFString SocketRemoteNetService();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="kCFStreamPropertySocketExtendedBackgroundIdleMode", optional=true)
        public static native CFString SocketExtendedBackgroundIdleMode();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPResponseHeader", optional=true)
        public static native CFString HTTPResponseHeader();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPFinalURL", optional=true)
        public static native CFString HTTPFinalURL();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPFinalRequest", optional=true)
        public static native CFString HTTPFinalRequest();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPProxy", optional=true)
        public static native CFString HTTPProxy();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPProxyHost", optional=true)
        public static native CFString HTTPProxyHost();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPProxyPort", optional=true)
        public static native CFString HTTPProxyPort();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPSProxyHost", optional=true)
        public static native CFString HTTPSProxyHost();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPSProxyPort", optional=true)
        public static native CFString HTTPSProxyPort();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPShouldAutoredirect", optional=true)
        public static native CFString HTTPShouldAutoredirect();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPAttemptPersistentConnection", optional=true)
        public static native CFString HTTPAttemptPersistentConnection();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kCFStreamPropertyHTTPRequestBytesWrittenCount", optional=true)
        public static native CFString HTTPRequestBytesWrittenCount();
        /*</values>*/
    }
}
