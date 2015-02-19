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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFFileDescriptor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface CFFileDescriptorCallback {
        void invoke(CFFileDescriptor fileDescriptor, CFFileDescriptorCallBackType callBackTypes);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, CFFileDescriptorCallback> callbacks = new HashMap<Long, CFFileDescriptorCallback>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFFileDescriptor.class.getDeclaredMethod("cbInvoke", CFFileDescriptor.class, CFFileDescriptorCallBackType.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/public static class CFFileDescriptorPtr extends Ptr<CFFileDescriptor, CFFileDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFFileDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFFileDescriptor() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(CFFileDescriptor fileDescriptor, CFFileDescriptorCallBackType callBackTypes, @Pointer long info) {
        CFFileDescriptorCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(info);
        }
        callback.invoke(fileDescriptor, callBackTypes);
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFFileDescriptor create(int fd, boolean closeOnInvalidate, CFFileDescriptorCallback callback) {
        return create(null, fd, closeOnInvalidate, callback);
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFFileDescriptor create(CFAllocator allocator, int fd, boolean closeOnInvalidate, CFFileDescriptorCallback callback) {
        long refconId = CFFileDescriptor.refconId.getAndIncrement();
        CFFileDescriptorContext context = new CFFileDescriptorContext();
        context.setInfo(refconId);
        CFFileDescriptor result = create(allocator, fd, closeOnInvalidate, new FunctionPtr(cbInvoke), context);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(refconId, callback);
            }
        }
        return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFRunLoopSource createRunLoopSource(@MachineSizedSInt long order) {
        return createRunLoopSource(null, order);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CFRunLoopSource createRunLoopSource(CFAllocator allocator, @MachineSizedSInt long order) {
        return createRunLoopSource(allocator, this, order);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorCreate", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFFileDescriptor create(CFAllocator allocator, int fd, boolean closeOnInvalidate, FunctionPtr callout, CFFileDescriptorContext context);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorGetNativeDescriptor", optional=true)
    public native int getNativeDescriptor();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorEnableCallBacks", optional=true)
    public native void enableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorDisableCallBacks", optional=true)
    public native void disableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorInvalidate", optional=true)
    public native void invalidate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorIsValid", optional=true)
    public native boolean isValid();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorCreateRunLoopSource", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFFileDescriptor f, @MachineSizedSInt long order);
    /*</methods>*/
}
