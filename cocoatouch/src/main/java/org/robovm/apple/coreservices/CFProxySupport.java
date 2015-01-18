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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFProxySupport/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    public interface AutoConfigurationClientCallback {
        void invoke(List<CFProxy> proxyList, CFError error);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static Map<Long, AutoConfigurationClientCallback> callbacks = new HashMap<Long, AutoConfigurationClientCallback>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFProxySupport.class.getDeclaredMethod("cbInvoke", long.class, CFArray.class, CFError.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CFProxySupport.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(@Pointer long refcon, CFArray proxyList0, CFError error) {
        AutoConfigurationClientCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        List<CFProxy> proxyList = new ArrayList<CFProxy>();
        for (int i = 0; i < proxyList0.size(); i++) {
            proxyList.add(new CFProxy(proxyList0.get(i, CFDictionary.class)));
        }
        callback.invoke(proxyList, error);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static List<CFProxy> getProxies(String proxyAutoConfigurationScript, NSURL targetURL) {
        CFArray proxies0 = getProxies0(proxyAutoConfigurationScript, targetURL, null);
        if (proxies0 != null) {
            List<CFProxy> proxies = new ArrayList<CFProxy>();
            for (int i = 0; i < proxies0.size(); i++) {
                proxies.add(new CFProxy(proxies0.get(i, CFDictionary.class)));
            }
            return proxies;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFRunLoopSource executeProxyAutoConfigurationScript(String proxyAutoConfigurationScript, NSURL targetURL, AutoConfigurationClientCallback callback) {
        long refconId = CFProxySupport.refconId.getAndIncrement();
        CFStreamClientContext context = new CFStreamClientContext();
        context.setInfo(refconId);
        CFRunLoopSource source = executeProxyAutoConfigurationScript(proxyAutoConfigurationScript, targetURL, new FunctionPtr(cbInvoke), context);
        if (source != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
            return source;
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFRunLoopSource executeProxyAutoConfigurationURL(NSURL proxyAutoConfigURL, NSURL targetURL, AutoConfigurationClientCallback callback) {
        long refconId = CFProxySupport.refconId.getAndIncrement();
        CFStreamClientContext context = new CFStreamClientContext();
        context.setInfo(refconId);
        CFRunLoopSource source = executeProxyAutoConfigurationURL(proxyAutoConfigURL, targetURL, new FunctionPtr(cbInvoke), context);
        if (source != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
            return source;
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetworkCopySystemProxySettings", optional=true)
    public static native CFSystemProxySettings getSystemProxySettings();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetworkCopyProxiesForAutoConfigurationScript", optional=true)
    protected static native CFArray getProxies0(String proxyAutoConfigurationScript, NSURL targetURL, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetworkExecuteProxyAutoConfigurationScript", optional=true)
    protected static native CFRunLoopSource executeProxyAutoConfigurationScript(String proxyAutoConfigurationScript, NSURL targetURL, FunctionPtr cb, CFStreamClientContext clientContext);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetworkExecuteProxyAutoConfigurationURL", optional=true)
    protected static native CFRunLoopSource executeProxyAutoConfigurationURL(NSURL proxyAutoConfigURL, NSURL targetURL, FunctionPtr cb, CFStreamClientContext clientContext);
    /*</methods>*/
}
