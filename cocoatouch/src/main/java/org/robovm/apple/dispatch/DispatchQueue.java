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
package org.robovm.apple.dispatch;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("System")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchQueue/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchQueuePtr extends Ptr<DispatchQueue, DispatchQueuePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchQueue.class); }/*</bind>*/
    /*<constants>*/
    public static final int PRIORITY_HIGH = 2;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_LOW = -2;
    public static final int PRIORITY_BACKGROUND = -32768;
    /*</constants>*/
    /*<constructors>*/
    protected DispatchQueue() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/

    /**
     * @since Available in iOS 4.0 and later.
     */
    public void apply(long iterations, VoidBlock1<Long> block) {
        apply(iterations, this, block);
    }

    /**
     * @since Available in iOS 4.0 and later.
     */
    public void after(long delay, java.util.concurrent.TimeUnit unit, Runnable block) {
        after(Dispatch.time(Dispatch.TIME_NOW, unit.toNanos(delay)), this, block);
    }

    /**
     * Submits the specified {@link Runnable} on this {@link DispatchQueue} at
     * the specified time.
     * 
     * @param when the time when to submit the {@link Runnable}.
     * @param block the {@link Runnable} to be run.
     * 
     * @since Available in iOS 4.0 and later.
     */
    public void at(Date when, Runnable block) {
        long ms = when.getTime();
        timespec ts = new timespec(ms / 1000, (ms % 1000) * 1000);
        after(Dispatch.walltime(ts, 0), this, block);
    }

    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_main_q", optional=true, dereference=false)
    public static native DispatchQueue getMainQueue();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_async", optional=true)
    public native void async(@Block Runnable block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_sync", optional=true)
    public native void sync(@Block Runnable block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_apply", optional=true)
    private static native void apply(@MachineSizedUInt long iterations, DispatchQueue queue, @Block("(@MachineSizedUInt)") VoidBlock1<Long> block);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Bridge(symbol="dispatch_get_current_queue", optional=true)
    public static native DispatchQueue getCurrentQueue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_get_global_queue", optional=true)
    public static native DispatchQueue getGlobalQueue(@MachineSizedSInt long priority, @MachineSizedUInt long flags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_queue_create", optional=true)
    public static native DispatchQueue create(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsUtf8ZMarshaler.class) String label, DispatchQueueAttr attr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_queue_get_label", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsUtf8ZMarshaler.class) String getLabel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_after", optional=true)
    private static native void after(long when, DispatchQueue queue, @Block Runnable block);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Bridge(symbol="dispatch_barrier_async", optional=true)
    public native void barrierAsync(@Block Runnable block);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Bridge(symbol="dispatch_barrier_sync", optional=true)
    public native void barrierSync(@Block Runnable block);
    /*</methods>*/
}
