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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNetServiceMonitor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNetServiceMonitorPtr extends Ptr<CFNetServiceMonitor, CFNetServiceMonitorPtr> {}/*</ptr>*/
    
    public interface ClientCallback {
        void invoke(CFNetServiceMonitor monitor, CFNetService service, CFNetServiceMonitorType infoType, CFData data, CFStreamError error);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static Map<Long, ClientCallback> callbacks = new HashMap<Long, ClientCallback>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFNetServiceMonitor.class.getDeclaredMethod("cbInvoke", CFNetServiceMonitor.class, CFNetService.class, CFNetServiceMonitorType.class, CFData.class, CFStreamError.CFStreamErrorPtr.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CFNetServiceMonitor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNetServiceMonitor() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(CFNetServiceMonitor monitor, CFNetService service, CFNetServiceMonitorType infoType, CFData data, CFStreamError.CFStreamErrorPtr error, @Pointer long refcon) {
        ClientCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        CFStreamError err = null;
        if (error != null) err = error.get();
        callback.invoke(monitor, service, infoType, data, err);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetServiceMonitor create(CFNetService theService, ClientCallback clientCB) {
        long refconId = CFNetServiceMonitor.refconId.getAndIncrement();
        CFNetServiceClientContext context = new CFNetServiceClientContext();
        context.setInfo(refconId);
        synchronized (callbacks) {
            callbacks.put(refconId, clientCB);
        }
        CFNetServiceMonitor service = create(null, theService, new FunctionPtr(cbInvoke), context);
        if (service != null) service.localRefconId = refconId;
        return service;
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
    public void unscheduleFromRunLoop(CFRunLoop runLoop, CFRunLoopMode runLoopMode) {
        unscheduleFromRunLoop(runLoop, runLoopMode.value());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFNetServiceMonitor create(CFAllocator alloc, CFNetService theService, FunctionPtr clientCB, CFNetServiceClientContext clientContext);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorInvalidate", optional=true)
    public native void invalidate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean start(CFNetServiceMonitorType recordType) throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       boolean result = start(recordType, ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorStart", optional=true)
    private native boolean start(CFNetServiceMonitorType recordType, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void stop() throws CFStreamErrorException {
       CFStreamError.CFStreamErrorPtr ptr = new CFStreamError.CFStreamErrorPtr();
       stop(ptr);
       if (ptr.get() != null) { throw new CFStreamErrorException(ptr.get()); }
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorStop", optional=true)
    private native void stop(CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorScheduleWithRunLoop", optional=true)
    public native void scheduleInRunLoop(CFRunLoop runLoop, String runLoopMode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceMonitorUnscheduleFromRunLoop", optional=true)
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, String runLoopMode);
    /*</methods>*/
}
