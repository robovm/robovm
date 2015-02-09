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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNetService/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNetServicePtr extends Ptr<CFNetService, CFNetServicePtr> {}/*</ptr>*/
    
    public interface ClientCallback {
        void invoke(CFNetService service, CFStreamError error);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static Map<Long, ClientCallback> callbacks = new HashMap<Long, ClientCallback>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFNetService.class.getDeclaredMethod("cbInvoke", CFNetService.class, CFStreamError.CFStreamErrorPtr.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CFNetService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNetService() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(CFNetService service, CFStreamError.CFStreamErrorPtr error, @Pointer long refcon) {
        ClientCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        CFStreamError err = null;
        if (error != null) err = error.get();
        callback.invoke(service, err);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetService create(String domain, String serviceType, String name, int port) {
        long refconId = CFNetService.refconId.getAndIncrement();
        CFNetService host = create(null, domain, serviceType, name, port);
        if (host != null) host.localRefconId = refconId;
        return host; 
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetService createCopy(CFNetService service) {
        CFNetService s = createCopy(null, service);
        if (service != null) s.localRefconId = service.localRefconId;
        return s;
    }
    /**
    * @since Available in iOS 2.0 and later.
    */
    public static NSDictionary<NSString, NSData> parseTXTData(NSData txtRecord) {
        return parseTXTData(null, txtRecord);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static NSData createTXTData(NSDictionary<NSString, NSData> keyValuePairs) {
        return createTXTData(null, keyValuePairs);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void setCallback(ClientCallback callback) {
        CFNetServiceClientContext context = new CFNetServiceClientContext();
        context.setInfo(localRefconId);
        synchronized (callbacks) {
            callbacks.put(localRefconId, callback);
        }
        setCallback(new FunctionPtr(cbInvoke), context);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFReadStream createSocketReadStream() {
        CFReadStream.CFReadStreamPtr ptr = new CFReadStream.CFReadStreamPtr();
        createSocketStreamPair(null, this, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFWriteStream createSocketWriteStream() {
        CFWriteStream.CFWriteStreamPtr ptr = new CFWriteStream.CFWriteStreamPtr();
        createSocketStreamPair(null, this, null, ptr);
        return ptr.get();
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void scheduleInRunLoop(CFRunLoop runLoop, CFRunLoopMode runLoopMode) {
        scheduleInRunLoop(runLoop, runLoopMode.value());
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void removeFromRunLoop(CFRunLoop runLoop, CFRunLoopMode runLoopMode) {
        scheduleInRunLoop(runLoop, runLoopMode.value());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFNetService create(CFAllocator alloc, String domain, String serviceType, String name, int port);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFNetService createCopy(CFAllocator alloc, CFNetService service);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetDomain", optional=true)
    public native String getDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetType", optional=true)
    public native String getType();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetName", optional=true)
    public native String getName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean register(CFNetServiceOptions options) throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       boolean result = register(options, ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceRegisterWithOptions", optional=true)
    private native boolean register(CFNetServiceOptions options, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean resolve(double timeout) throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       boolean result = resolve(timeout, ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceResolveWithTimeout", optional=true)
    private native boolean resolve(double timeout, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceCancel", optional=true)
    public native void cancel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetTargetHost", optional=true)
    public native String getTargetHost();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetPortNumber", optional=true)
    public native int getPortNumber();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetAddressing", optional=true)
    public native NSArray<NSData> getAddressing();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceGetTXTData", optional=true)
    public native NSData getTXTData();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceSetTXTData", optional=true)
    public native boolean setTXTData(NSData txtRecord);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceCreateDictionaryWithTXTData", optional=true)
    protected static native NSDictionary<NSString, NSData> parseTXTData(CFAllocator alloc, NSData txtRecord);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceCreateTXTDataWithDictionary", optional=true)
    protected static native NSData createTXTData(CFAllocator alloc, NSDictionary<NSString, NSData> keyValuePairs);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceSetClient", optional=true)
    private native boolean setCallback(FunctionPtr clientCB, CFNetServiceClientContext clientContext);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceScheduleWithRunLoop", optional=true)
    public native void scheduleInRunLoop(CFRunLoop runLoop, String runLoopMode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceUnscheduleFromRunLoop", optional=true)
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, String runLoopMode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFStreamCreatePairWithSocketToNetService", optional=true)
    private static native void createSocketStreamPair(CFAllocator alloc, CFNetService service, CFReadStream.CFReadStreamPtr readStream, CFWriteStream.CFWriteStreamPtr writeStream);
    /*</methods>*/
}
