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
import org.robovm.apple.coreservices.CFSocketStreamProperty;
import org.robovm.apple.coreservices.CFFTPStreamProperty;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFReadStream/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface ClientCallback {
        void invoke(CFReadStream stream, CFStreamEventType eventType);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static final Map<Long, ClientCallback> clientCallbacks = new HashMap<>();
    private static final java.lang.reflect.Method cbClient;
    
    static {
        try {
            cbClient = CFWriteStream.class.getDeclaredMethod("cbClient", CFReadStream.class, CFStreamEventType.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<ptr>*/public static class CFReadStreamPtr extends Ptr<CFReadStream, CFReadStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFReadStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFReadStream() {}
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
    
    public static CFReadStream create(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        return create(null, VM.getArrayValuesAddress(bytes), bytes.length, null);
    }
    public static CFReadStream create(ByteBuffer bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes");
        }
        long handle = CFData.getEffectiveAddress(bytes) + bytes.position();
        return create(null, handle, bytes.remaining(), null);
    }
    public static CFReadStream create(CFURL fileURL) {
        return create(null, fileURL);
    }
    
    public long read(BytePtr buffer, long len) {
        return read(buffer.getHandle(), len);
    }
    public long read(ByteBuffer bytes) {
        long handle = CFData.getEffectiveAddress(bytes) + bytes.position();
        return read(handle, bytes.remaining());
    }
    public long read(byte[] bytes) {
        return read(bytes, 0, bytes.length);
    }
    public long read(byte[] bytes, int offset, int length) {
        CFMutableData.checkOffsetAndCount(bytes.length, offset, length);
        if (length == 0) {
            return 0;
        }
        return read(VM.getArrayValuesAddress(bytes) + offset, length);
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
    @Bridge(symbol="CFReadStreamGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFReadStreamCreateWithBytesNoCopy", optional=true)
    protected static native CFReadStream create(CFAllocator alloc, @Pointer long bytes, @MachineSizedSInt long length, CFAllocator bytesDeallocator);
    @Bridge(symbol="CFReadStreamCreateWithFile", optional=true)
    protected static native CFReadStream create(CFAllocator alloc, CFURL fileURL);
    @Bridge(symbol="CFReadStreamGetStatus", optional=true)
    public native CFStreamStatus getStatus();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFReadStreamCopyError", optional=true)
    public native CFError getError();
    @Bridge(symbol="CFReadStreamOpen", optional=true)
    public native boolean openStream();
    @Bridge(symbol="CFReadStreamClose", optional=true)
    public native void closeStream();
    @Bridge(symbol="CFReadStreamHasBytesAvailable", optional=true)
    public native boolean hasBytesAvailable();
    @Bridge(symbol="CFReadStreamRead", optional=true)
    private native @MachineSizedSInt long read(@Pointer long buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFReadStreamGetBuffer", optional=true)
    private native BytePtr getBuffer(@MachineSizedSInt long maxBytesToRead, MachineSizedSIntPtr numBytesRead);
    @Bridge(symbol="CFReadStreamCopyProperty", optional=true)
    private native CFType getProperty(CFString propertyName);
    @Bridge(symbol="CFReadStreamSetProperty", optional=true)
    private native boolean setProperty(CFString propertyName, CFType propertyValue);
    @Bridge(symbol="CFReadStreamSetClient", optional=true)
    private native boolean setClient(CFStreamEventType streamEvents, FunctionPtr clientCB, CFStreamClientContext clientContext);
    @Bridge(symbol="CFReadStreamScheduleWithRunLoop", optional=true)
    public native void scheduleInRunLoop(CFRunLoop runLoop, String runLoopMode);
    @Bridge(symbol="CFReadStreamUnscheduleFromRunLoop", optional=true)
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, String runLoopMode);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFReadStreamSetDispatchQueue", optional=true)
    public native void setDispatchQueue(DispatchQueue q);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFReadStreamCopyDispatchQueue", optional=true)
    public native DispatchQueue getDispatchQueue();
    @Bridge(symbol="CFReadStreamGetError", optional=true)
    public native @ByVal CFStreamError getStreamError();
    /*</methods>*/
}
