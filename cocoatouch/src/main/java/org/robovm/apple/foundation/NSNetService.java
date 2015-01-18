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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
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
    public NSNetService(String domain, String type, String name, int port) { super((SkipInit) null); initObject(init(domain, type, name, port)); }
    public NSNetService(String domain, String type, String name) { super((SkipInit) null); initObject(init(domain, type, name)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSNetServiceDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSNetServiceDelegate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "includesPeerToPeer")
    public native boolean includesPeerToPeer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setIncludesPeerToPeer:")
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
    public native NSArray<NSData> getAddresses();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "port")
    public native @MachineSizedSInt long getPort();
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSInputStream getInputStream() {
        NSInputStream.NSInputStreamPtr ptr = new NSInputStream.NSInputStreamPtr();
        getStreams(ptr, null);
        return ptr.get();
    }
    public NSOutputStream getOutputStream() {
        NSOutputStream.NSOutputStreamPtr ptr = new NSOutputStream.NSOutputStreamPtr();
        getStreams(null, ptr);
        return ptr.get();
    }
    public void scheduleInRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        scheduleInRunLoop(aRunLoop, mode.value());
    }
    public void removeFromRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        removeFromRunLoop(aRunLoop, mode.value());
    }
    /*<methods>*/
    @Method(selector = "initWithDomain:type:name:port:")
    protected native @Pointer long init(String domain, String type, String name, int port);
    @Method(selector = "initWithDomain:type:name:")
    protected native @Pointer long init(String domain, String type, String name);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "publish")
    public native void publish();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "publishWithOptions:")
    public native void publish(NSNetServiceOptions options);
    @Method(selector = "stop")
    public native void stop();
    @Method(selector = "resolveWithTimeout:")
    public native void resolve(double timeout);
    @Method(selector = "getInputStream:outputStream:")
    protected native boolean getStreams(NSInputStream.NSInputStreamPtr inputStream, NSOutputStream.NSOutputStreamPtr outputStream);
    @Method(selector = "setTXTRecordData:")
    public native boolean setTXTRecordData(NSData recordData);
    @Method(selector = "TXTRecordData")
    public native NSData getTXTRecordData();
    @Method(selector = "startMonitoring")
    public native void startMonitoring();
    @Method(selector = "stopMonitoring")
    public native void stopMonitoring();
    @Method(selector = "dictionaryFromTXTRecordData:")
    public static native NSDictionary<?, ?> getDictionaryFromTXTRecordData(NSData txtData);
    @Method(selector = "dataFromTXTRecordDictionary:")
    public static native NSData getDataFromTXTRecordDictionary(NSDictionary<?, ?> txtDictionary);
    /*</methods>*/
}
