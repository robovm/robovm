/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMachPort/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface MachPortCallback {
        CFData invoke(CFMachPort port, VoidPtr msg, long size);
    }
    public interface InvalidationCallback {
        void invalidate(CFMachPort port);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static final Map<Long, MachPortCallback> portCallbacks = new HashMap<>();
    private static final java.lang.reflect.Method cbPort;
    private static final java.lang.reflect.Method cbInvalidate;
    
    static {
        try {
            cbPort = CFMachPort.class.getDeclaredMethod("cbPort", CFMachPort.class, VoidPtr.class, long.class, long.class);
            cbInvalidate = CFMachPort.class.getDeclaredMethod("cbInvalidate", CFMachPort.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    private InvalidationCallback invalidationCallback;
    
    /*<ptr>*/public static class CFMachPortPtr extends Ptr<CFMachPort, CFMachPortPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMachPort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMachPort() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbPort(CFMachPort port, VoidPtr msg, @MachineSizedSInt long size, @Pointer long refcon) {
        MachPortCallback callback = null;
        synchronized (portCallbacks) {
            callback = portCallbacks.get(refcon);
        }
        callback.invoke(port, msg, size);
    }
    @Callback
    private void cbInvalidate(CFMachPort port, @Pointer long refcon) {
        if (invalidationCallback != null) {
            invalidationCallback.invalidate(port);
        }
    }
    
    public static CFMachPort create(MachPortCallback callback) {
        long refcon = refconId.getAndIncrement();
        CFMachPortContext context = new CFMachPortContext();
        context.setInfo(refcon);
        BooleanPtr ptr = new BooleanPtr();
        CFMachPort result = create(null, new FunctionPtr(cbPort), context, ptr);
        if (result != null) {
            synchronized (portCallbacks) {
                portCallbacks.put(refcon, callback);
            }
        }
        return result;
    }
    public static CFMachPort create(int portNum, MachPortCallback callback) {
        long refcon = refconId.getAndIncrement();
        CFMachPortContext context = new CFMachPortContext();
        context.setInfo(refcon);
        BooleanPtr ptr = new BooleanPtr();
        CFMachPort result = create(null, portNum, new FunctionPtr(cbPort), context, ptr);
        if (result != null) {
            synchronized (portCallbacks) {
                portCallbacks.put(refcon, callback);
            }
        }
        return result;
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
    public static CFRunLoopSource createRunLoopSource(CFMachPort port, @MachineSizedSInt long order) {
        return createRunLoopSource(null, port, order);
    }
    /*<methods>*/
    @Bridge(symbol="CFMachPortGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFMachPortCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMachPort create(CFAllocator allocator, FunctionPtr callout, CFMachPortContext context, BooleanPtr shouldFreeInfo);
    @Bridge(symbol="CFMachPortCreateWithPort", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMachPort create(CFAllocator allocator, int portNum, FunctionPtr callout, CFMachPortContext context, BooleanPtr shouldFreeInfo);
    @Bridge(symbol="CFMachPortGetPort", optional=true)
    public native int getPort();
    @Bridge(symbol="CFMachPortInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFMachPortIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFMachPortGetInvalidationCallBack", optional=true)
    private native FunctionPtr getInvalidationCallBack0();
    @Bridge(symbol="CFMachPortSetInvalidationCallBack", optional=true)
    private native void setInvalidationCallBack0(FunctionPtr callout);
    @Bridge(symbol="CFMachPortCreateRunLoopSource", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFMachPort port, @MachineSizedSInt long order);
    /*</methods>*/
}
