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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMessagePort/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface MessagePortCallback {
        CFData invoke(CFMessagePort port, @MachineSizedSInt long msgid, CFData data);
    }
    public interface InvalidationCallback {
        void invalidate(CFMessagePort port);
    }
    
    private static final java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<MessagePortCallback> portCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbPort;
    private static final java.lang.reflect.Method cbInvalidate;
    
    static {
        try {
            cbPort = CFMessagePort.class.getDeclaredMethod("cbPort", CFMessagePort.class, long.class, CFData.class, long.class);
            cbInvalidate = CFMessagePort.class.getDeclaredMethod("cbInvalidate", CFMessagePort.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    private InvalidationCallback invalidationCallback;
    
    /*<ptr>*/public static class CFMessagePortPtr extends Ptr<CFMessagePort, CFMessagePortPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMessagePort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMessagePort() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbPort(CFMessagePort port, @MachineSizedSInt long msgid, CFData data, @Pointer long refcon) {
        MessagePortCallback callback = null;
        synchronized (portCallbacks) {
            callback = portCallbacks.get(refcon);
        }
        callback.invoke(port, msgid, data);
    }
    @Callback
    private void cbInvalidate(CFMessagePort port, @Pointer long refcon) {
        if (invalidationCallback != null) {
            invalidationCallback.invalidate(port);
        }
    }
    
    public static CFMessagePort createLocal(String name, MessagePortCallback callback) {
        long refcon = refconId.getAndIncrement();
        CFMessagePortContext context = new CFMessagePortContext();
        context.setInfo(refcon);
        BooleanPtr ptr = new BooleanPtr();
        CFMessagePort result = createLocal(null, name, new FunctionPtr(cbPort), context, ptr);
        if (result != null) {
            synchronized (portCallbacks) {
                portCallbacks.put(refcon, callback);
            }
        }
        return result;
    }
    public static CFMessagePort createRemote(String name) {
        return createRemote(null, name);
    }
    public InvalidationCallback getInvalidationCallBack() {
        return invalidationCallback;
    }
    public void setInvalidationCallBack(InvalidationCallback callback) {
        invalidationCallback = callback;
        if (callback == null) {
            setInvalidationCallBack0(null);
        } else {
            setInvalidationCallBack0(new FunctionPtr(cbInvalidate));
        }
    }
    public static CFRunLoopSource createRunLoopSource(CFMessagePort local, @MachineSizedSInt long order) {
        return createRunLoopSource(null, local, order);
    }
    
    /**
     * 
     * @param msgid
     * @param data
     * @param sendTimeout
     * @param rcvTimeout
     * @param replyMode
     * @return
     * @throws NSErrorException
     */
    public CFData sendRequest(int msgid, CFData data, double sendTimeout, double rcvTimeout, String replyMode) throws NSErrorException {
        CFData.CFDataPtr ptr = new CFData.CFDataPtr();
        CFMessagePortErrorCode err = sendRequest(msgid, data, sendTimeout, rcvTimeout, replyMode, ptr);
        if (err != CFMessagePortErrorCode.Success) {
            CFMessagePortError error = new CFMessagePortError(err);
            throw new NSErrorException(error);
        }
        return ptr.get();
    }
    /*<methods>*/
    @Bridge(symbol="CFMessagePortGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFMessagePortCreateLocal", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMessagePort createLocal(CFAllocator allocator, String name, FunctionPtr callout, CFMessagePortContext context, BooleanPtr shouldFreeInfo);
    @Bridge(symbol="CFMessagePortCreateRemote", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMessagePort createRemote(CFAllocator allocator, String name);
    @Bridge(symbol="CFMessagePortIsRemote", optional=true)
    public native boolean isRemote();
    @Bridge(symbol="CFMessagePortGetName", optional=true)
    public native String getName();
    @Bridge(symbol="CFMessagePortSetName", optional=true)
    public native boolean setName(String newName);
    @Bridge(symbol="CFMessagePortInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFMessagePortIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFMessagePortGetInvalidationCallBack", optional=true)
    private native FunctionPtr getInvalidationCallBack0();
    @Bridge(symbol="CFMessagePortSetInvalidationCallBack", optional=true)
    private native void setInvalidationCallBack0(FunctionPtr callout);
    @Bridge(symbol="CFMessagePortSendRequest", optional=true)
    protected native CFMessagePortErrorCode sendRequest(int msgid, CFData data, double sendTimeout, double rcvTimeout, String replyMode, CFData.CFDataPtr returnData);
    @Bridge(symbol="CFMessagePortCreateRunLoopSource", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFMessagePort local, @MachineSizedSInt long order);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="CFMessagePortSetDispatchQueue", optional=true)
    public native void setDispatchQueue(DispatchQueue queue);
    /*</methods>*/
}
