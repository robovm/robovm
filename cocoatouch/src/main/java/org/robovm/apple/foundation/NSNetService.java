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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNetService/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSNetServicePtr extends Ptr<NSNetService, NSNetServicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNetService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNetService() {}
    protected NSNetService(SkipInit skipInit) { super(skipInit); }
    public NSNetService(String domain, String type, String name, int port) { super((SkipInit) null); initObject(initWithDomain$type$name$port$(domain, type, name, port)); }
    public NSNetService(String domain, String type, String name) { super((SkipInit) null); initObject(initWithDomain$type$name$(domain, type, name)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSNetServiceDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSNetServiceDelegate v);
    @Property(selector = "includesPeerToPeer")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isIncludesPeerToPeer();
    @Property(selector = "setIncludesPeerToPeer:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setIncludesPeerToPeer(boolean v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "type")
    public native String getType();
    @Property(selector = "domain")
    public native String getDomain();
    @Property(selector = "hostName")
    public native String getHostName();
    @Property(selector = "addresses")
    public native NSArray<?> getAddresses();
    @Property(selector = "port")
    /**
     * @since Available in iOS 2.0 and later.
     */
    public native @MachineSizedSInt long getPort();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithDomain:type:name:port:")
    protected native @Pointer long initWithDomain$type$name$port$(String domain, String type, String name, int port);
    @Method(selector = "initWithDomain:type:name:")
    protected native @Pointer long initWithDomain$type$name$(String domain, String type, String name);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "publish")
    public native void publish();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "publishWithOptions:")
    public native void publishWithOptions$(NSNetServiceOptions options);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 2.0.
     */
    @Deprecated
    @Method(selector = "resolve")
    public native void resolve();
    @Method(selector = "stop")
    public native void stop();
    @Method(selector = "resolveWithTimeout:")
    public native void resolveWithTimeout$(double timeout);
    @Method(selector = "getInputStream:outputStream:")
    public native boolean getInputStream$outputStream$(NSInputStream.NSInputStreamPtr inputStream, NSOutputStream.NSOutputStreamPtr outputStream);
    @Method(selector = "setTXTRecordData:")
    public native boolean setTXTRecordData$(NSData recordData);
    @Method(selector = "TXTRecordData")
    public native NSData TXTRecordData();
    @Method(selector = "startMonitoring")
    public native void startMonitoring();
    @Method(selector = "stopMonitoring")
    public native void stopMonitoring();
    @Method(selector = "dictionaryFromTXTRecordData:")
    public static native NSDictionary<?, ?> dictionaryFromTXTRecordData$(NSData txtData);
    @Method(selector = "dataFromTXTRecordDictionary:")
    public static native NSData dataFromTXTRecordDictionary$(NSDictionary<?, ?> txtDictionary);
    /*</methods>*/
}
