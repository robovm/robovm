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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/
import org.robovm.apple.coreservices.CFFTPStreamProperty;
import org.robovm.apple.coreservices.CFSocketStreamProperty;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFWriteStream/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface ClientCallback {
        void invoke(CFReadStream stream, CFStreamEventType eventType);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<ClientCallback> clientCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbClient;
    
    static {
        try {
            cbClient = CFWriteStream.class.getDeclaredMethod("cbClient", CFReadStream.class, CFStreamEventType.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<ptr>*/public static class CFWriteStreamPtr extends Ptr<CFWriteStream, CFWriteStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFWriteStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFWriteStream() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    private long localRefconId;
    
    @Callback
    private static void cbClient(CFReadStream stream, CFStreamEventType eventType, @Pointer long refcon) {
        ClientCallback callback = null;
        synchronized (clientCallbacks) {
            callback = clientCallbacks.get(refcon);
        }
        callback.invoke(stream, eventType);
    }
    
    public static CFWriteStream create(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        return create(null, VM.getArrayValuesAddress(bytes), bytes.length);
    }
    public static CFWriteStream create(ByteBuffer bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = CFData.getEffectiveAddress(bytes) + bytes.position();
        return create(null, handle, bytes.remaining());
    }
    public static CFWriteStream create(CFURL fileURL) {
        return create(null, fileURL);
    }

    public long write(BytePtr buffer, long len) {
        return write(buffer.getHandle(), len);
    }
    public long write(ByteBuffer bytes) {
        long handle = CFData.getEffectiveAddress(bytes) + bytes.position();
        return write(handle, bytes.remaining());
    }
    public long write(byte[] bytes) {
        return write(bytes, 0, bytes.length);
    }
    public long write(byte[] bytes, int offset, int length) {
        CFMutableData.checkOffsetAndCount(bytes.length, offset, length);
        if (length == 0) {
            return 0;
        }
        return write(VM.getArrayValuesAddress(bytes) + offset, length);
    }
    
    public boolean setClientCallback(CFStreamEventType streamEvents, ClientCallback callback) {
        localRefconId = refconId.getAndIncrement();
        CFStreamClientContext context = new CFStreamClientContext();
        context.setInfo(localRefconId);
        boolean result = setClient(streamEvents, new FunctionPtr(cbClient), context);
        if (result) {
            synchronized (clientCallbacks) {
                clientCallbacks.put(localRefconId, callback);
            }
        }
        return result;
    }
    
    public CFType getProperty(CFStreamProperty property) {
        return getProperty(property.value());
    }
    public CFType getProperty(CFSocketStreamProperty property) {
        return getProperty(property.value());
    }
    public CFType getProperty(CFFTPStreamProperty property) {
        return getProperty(property.value());
    }
    public boolean setProperty(CFStreamProperty property, CFType propertyValue) {
        return setProperty(property.value(), propertyValue);
    }
    public boolean setProperty(CFSocketStreamProperty property, CFType propertyValue) {
        return setProperty(property.value(), propertyValue);
    }
    public boolean setProperty(CFFTPStreamProperty property, CFType propertyValue) {
        return setProperty(property.value(), propertyValue);
    }
    
    public void scheduleInRunLoop(CFRunLoop runLoop, CFRunLoopMode runLoopMode) {
        scheduleInRunLoop(runLoop, runLoopMode.value());
    }
    public void unscheduleFromRunLoop(CFRunLoop runLoop, CFRunLoopMode runLoopMode) {
        unscheduleFromRunLoop(runLoop, runLoopMode.value());
    }
    /*<methods>*/
    @Bridge(symbol="CFWriteStreamGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFWriteStreamCreateWithBuffer", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFWriteStream create(CFAllocator alloc, @Pointer long buffer, @MachineSizedSInt long bufferCapacity);
    @Bridge(symbol="CFWriteStreamCreateWithFile", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFWriteStream create(CFAllocator alloc, CFURL fileURL);
    @Bridge(symbol="CFWriteStreamGetStatus", optional=true)
    public native CFStreamStatus getStatus();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFWriteStreamCopyError", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFError getError();
    @Bridge(symbol="CFWriteStreamOpen", optional=true)
    public native boolean openStream();
    @Bridge(symbol="CFWriteStreamClose", optional=true)
    public native void closeStream();
    @Bridge(symbol="CFWriteStreamCanAcceptBytes", optional=true)
    public native boolean canAcceptBytes();
    @Bridge(symbol="CFWriteStreamWrite", optional=true)
    private native @MachineSizedSInt long write(@Pointer long buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFWriteStreamCopyProperty", optional=true)
    private native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getProperty(CFString propertyName);
    @Bridge(symbol="CFWriteStreamSetProperty", optional=true)
    private native boolean setProperty(CFString propertyName, CFType propertyValue);
    @Bridge(symbol="CFWriteStreamSetClient", optional=true)
    private native boolean setClient(CFStreamEventType streamEvents, FunctionPtr clientCB, CFStreamClientContext clientContext);
    @Bridge(symbol="CFWriteStreamScheduleWithRunLoop", optional=true)
    public native void scheduleInRunLoop(CFRunLoop runLoop, String runLoopMode);
    @Bridge(symbol="CFWriteStreamUnscheduleFromRunLoop", optional=true)
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, String runLoopMode);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFWriteStreamSetDispatchQueue", optional=true)
    public native void setDispatchQueue(DispatchQueue q);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFWriteStreamCopyDispatchQueue", optional=true)
    public native DispatchQueue getDispatchQueue();
    @Bridge(symbol="CFWriteStreamGetError", optional=true)
    public native @ByVal CFStreamError getStreamError();
    /*</methods>*/
}
