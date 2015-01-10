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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStream/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSStreamPtr extends Ptr<NSStream, NSStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSStream() {}
    protected NSStream(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native NSStreamDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSStreamDelegate v);
    @Property(selector = "streamStatus")
    public native NSStreamStatus getStreamStatus();
    @Property(selector = "streamError")
    public native NSError getStreamError();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setProperty(NSStreamProperty key, NSObject value) {
        setProperty(value, key);
    }
    
    /* Convenience methods */
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSocketSecurityLevel getSocketSecurityLevel() {
        NSString val = (NSString) getProperty(NSStreamProperty.SocketSecurityLevel);
        return NSStreamSocketSecurityLevel.valueOf(val);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStream setSocketSecurityLevel(NSStreamSocketSecurityLevel securityLevel) {
        setProperty(NSStreamProperty.SocketSecurityLevel, securityLevel.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSStreamSOCKSProxyConfiguration getSOCKSProxyConfiguration() {
        NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) getProperty(NSStreamProperty.SOCKSProxyConfiguration);
        return new NSStreamSOCKSProxyConfiguration(val);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStream setSOCKSProxyConfiguration(NSStreamSOCKSProxyConfiguration configuration) {
        setProperty(NSStreamProperty.SOCKSProxyConfiguration, configuration.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSData getDataWrittenToMemoryStream() {
        NSData val = (NSData) getProperty(NSStreamProperty.DataWrittenToMemoryStream);
        return val;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStream setDataWrittenToMemoryStream(NSData data) {
        setProperty(NSStreamProperty.DataWrittenToMemoryStream, data);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getCurrentOffset() {
        NSNumber val = (NSNumber) getProperty(NSStreamProperty.FileCurrentOffset);
        return val.longValue();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStream setCurrentOffset(long offset) {
        setProperty(NSStreamProperty.FileCurrentOffset, NSNumber.valueOf(offset));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSStreamNetworkServiceType getNetworkServiceType() {
        NSString val = (NSString) getProperty(NSStreamProperty.NetworkServiceType);
        return NSStreamNetworkServiceType.valueOf(val);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSStream setNetworkServiceType(NSStreamNetworkServiceType serviceType) {
        setProperty(NSStreamProperty.NetworkServiceType, serviceType.value());
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static NSInputStream getInputStreamToHost(String hostname, @MachineSizedSInt long port) {
        NSInputStream.NSInputStreamPtr ptr = new NSInputStream.NSInputStreamPtr();
        getStreamsToHost(hostname, port, ptr, new NSOutputStream.NSOutputStreamPtr());
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static NSOutputStream getOutputStreamToHost(String hostname, @MachineSizedSInt long port) {
        NSOutputStream.NSOutputStreamPtr ptr = new NSOutputStream.NSOutputStreamPtr();
        getStreamsToHost(hostname, port, new NSInputStream.NSInputStreamPtr(), ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static NSInputStream getBoundInputStream(@MachineSizedUInt long bufferSize) {
        NSInputStream.NSInputStreamPtr ptr = new NSInputStream.NSInputStreamPtr();
        getBoundStreams(bufferSize, ptr, new NSOutputStream.NSOutputStreamPtr());
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static NSOutputStream getBoundOutputStream(@MachineSizedUInt long bufferSize) {
        NSOutputStream.NSOutputStreamPtr ptr = new NSOutputStream.NSOutputStreamPtr();
        getBoundStreams(bufferSize, new NSInputStream.NSInputStreamPtr(), ptr);
        return ptr.get();
    }
    
    
    public void scheduleInRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        scheduleInRunLoop(aRunLoop, mode.value());
    }
    public void removeFromRunLoop(NSRunLoop aRunLoop, NSRunLoopMode mode) {
        removeFromRunLoop(aRunLoop, mode.value());
    }
    /*<methods>*/
    @Method(selector = "open")
    public native void open();
    @Method(selector = "close")
    public native void close();
    @Method(selector = "propertyForKey:")
    public native NSObject getProperty(NSStreamProperty key);
    @Method(selector = "setProperty:forKey:")
    protected native boolean setProperty(NSObject property, NSStreamProperty key);
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop(NSRunLoop aRunLoop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop aRunLoop, String mode);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getStreamsToHostWithName:port:inputStream:outputStream:")
    protected static native void getStreamsToHost(String hostname, @MachineSizedSInt long port, NSInputStream.NSInputStreamPtr inputStream, NSOutputStream.NSOutputStreamPtr outputStream);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getBoundStreamsWithBufferSize:inputStream:outputStream:")
    protected static native void getBoundStreams(@MachineSizedUInt long bufferSize, NSInputStream.NSInputStreamPtr inputStream, NSOutputStream.NSOutputStreamPtr outputStream);
    /*</methods>*/
}
