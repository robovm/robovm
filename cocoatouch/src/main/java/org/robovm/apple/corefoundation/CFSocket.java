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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSocket/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface CFSocketCallback {
        void invoke(CFSocket socket, CFSocketCallBackType callBackType, CFData address, VoidPtr data);
    }
    
    private static final java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<CFSocketCallback> callbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFSocket.class.getDeclaredMethod("cbInvoke", CFSocket.class, CFSocketCallBackType.class, CFData.class, VoidPtr.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<ptr>*/public static class CFSocketPtr extends Ptr<CFSocket, CFSocketPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFSocket.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFSocket() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(CFSocket socket, CFSocketCallBackType callBackType, CFData address, VoidPtr data, @Pointer long info) {
        CFSocketCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(info);
        }
        callback.invoke(socket, callBackType, address, data);
    }
    
    public static CFSocket create(int protocolFamily, int socketType, int protocol, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        return create(null, protocolFamily, socketType, protocol, callBackTypes, callback);
    }
    public static CFSocket create(CFAllocator allocator, int protocolFamily, int socketType, int protocol, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        long refconId = CFSocket.refconId.getAndIncrement();
        CFSocketContext context = new CFSocketContext();
        context.setInfo(refconId);
        CFSocket result = create(allocator, protocolFamily, socketType, protocol, callBackTypes, new FunctionPtr(cbInvoke), context);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
        }
        return result;
    }
    public static CFSocket create(int sock, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        return create(null, sock, callBackTypes, callback);
    }
    public static CFSocket create(CFAllocator allocator, int sock, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        long refconId = CFSocket.refconId.getAndIncrement();
        CFSocketContext context = new CFSocketContext();
        context.setInfo(refconId);
        CFSocket result = create(allocator, sock, callBackTypes, new FunctionPtr(cbInvoke), context);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
        }
        return result;
    }
    public static CFSocket create(CFSocketSignature signature, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        return create(null, signature, callBackTypes, callback);
    }
    public static CFSocket create(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, CFSocketCallback callback) {
        long refconId = CFSocket.refconId.getAndIncrement();
        CFSocketContext context = new CFSocketContext();
        context.setInfo(refconId);
        CFSocket result = create(allocator, signature, callBackTypes, new FunctionPtr(cbInvoke), context);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
        }
        return result;
    }
    public static CFSocket createConnectedToSocketSignature(CFSocketSignature signature, CFSocketCallBackType callBackTypes, CFSocketCallback callback, double timeout) {
        return createConnectedToSocketSignature(null, signature, callBackTypes, callback, timeout);
    }
    public static CFSocket createConnectedToSocketSignature(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, CFSocketCallback callback, double timeout) {
        long refconId = CFSocket.refconId.getAndIncrement();
        CFSocketContext context = new CFSocketContext();
        context.setInfo(refconId);
        CFSocket result = createConnectedToSocketSignature(allocator, signature, callBackTypes, new FunctionPtr(cbInvoke), context, timeout);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
        }
        return result;
    }
    
    
    public CFRunLoopSource createRunLoopSource(@MachineSizedSInt long order) {
        return createRunLoopSource(null, this, order);
    }
    public CFRunLoopSource createRunLoopSource(CFAllocator allocator, @MachineSizedSInt long order) {
        return createRunLoopSource(allocator, this, order);
    }
    /*<methods>*/
    @Bridge(symbol="CFSocketGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFSocketCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFSocket create(CFAllocator allocator, int protocolFamily, int socketType, int protocol, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateWithNative", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFSocket create(CFAllocator allocator, int sock, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateWithSocketSignature", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFSocket create(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateConnectedToSocketSignature", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFSocket createConnectedToSocketSignature(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context, double timeout);
    @Bridge(symbol="CFSocketSetAddress", optional=true)
    public native CFSocketError setAddress(CFData address);
    @Bridge(symbol="CFSocketConnectToAddress", optional=true)
    public native CFSocketError connectToAddress(CFData address, double timeout);
    @Bridge(symbol="CFSocketInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFSocketIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFSocketCopyAddress", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData getAddress();
    @Bridge(symbol="CFSocketCopyPeerAddress", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFData getPeerAddress();
    @Bridge(symbol="CFSocketGetNative", optional=true)
    public native int getNative();
    @Bridge(symbol="CFSocketCreateRunLoopSource", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFSocket s, @MachineSizedSInt long order);
    @Bridge(symbol="CFSocketGetSocketFlags", optional=true)
    public native CFSocketFlags getSocketFlags();
    @Bridge(symbol="CFSocketSetSocketFlags", optional=true)
    public native void setSocketFlags(CFSocketFlags flags);
    @Bridge(symbol="CFSocketDisableCallBacks", optional=true)
    public native void disableCallBacks(@MachineSizedUInt long callBackTypes);
    @Bridge(symbol="CFSocketEnableCallBacks", optional=true)
    public native void enableCallBacks(@MachineSizedUInt long callBackTypes);
    @Bridge(symbol="CFSocketSendData", optional=true)
    public native CFSocketError sendData(CFData address, CFData data, double timeout);
    @Bridge(symbol="CFSocketRegisterValue", optional=true)
    public static native CFSocketError registerValue(CFSocketSignature nameServerSignature, double timeout, String name, CFType value);
    @Bridge(symbol="CFSocketCopyRegisteredValue", optional=true)
    public static native CFSocketError getRegisteredValue(CFSocketSignature nameServerSignature, double timeout, String name, CFType.CFTypePtr value, CFData.CFDataPtr nameServerAddress);
    @Bridge(symbol="CFSocketRegisterSocketSignature", optional=true)
    public static native CFSocketError registerSocketSignature(CFSocketSignature nameServerSignature, double timeout, String name, CFSocketSignature signature);
    @Bridge(symbol="CFSocketCopyRegisteredSocketSignature", optional=true)
    public static native CFSocketError getRegisteredSocketSignature(CFSocketSignature nameServerSignature, double timeout, String name, CFSocketSignature signature, CFData.CFDataPtr nameServerAddress);
    @Bridge(symbol="CFSocketUnregister", optional=true)
    public static native CFSocketError unregister(CFSocketSignature nameServerSignature, double timeout, String name);
    @Bridge(symbol="CFSocketSetDefaultNameRegistryPortNumber", optional=true)
    public static native void setDefaultNameRegistryPortNumber(short port);
    @Bridge(symbol="CFSocketGetDefaultNameRegistryPortNumber", optional=true)
    public static native short getDefaultNameRegistryPortNumber();
    /*</methods>*/
}
