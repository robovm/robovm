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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Dispatch/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Dispatch.class); }/*</bind>*/
    /*<constants>*/
    public static final int API_VERSION = 20140804;
    protected static final long TIME_NOW = 0L;
    protected static final long TIME_FOREVER = ~0L;
    public static final int BLOCK_BARRIER = 1;
    public static final int BLOCK_DETACHED = 2;
    public static final int BLOCK_ASSIGN_CURRENT = 4;
    public static final int BLOCK_NO_QOS_CLASS = 8;
    public static final int BLOCK_INHERIT_QOS_CLASS = 16;
    public static final int BLOCK_ENFORCE_QOS_CLASS = 32;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_time", optional=true)
    protected static native long time(long when, long delta);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_walltime", optional=true)
    protected static native long walltime(timespec when, long delta);
    @Bridge(symbol="dispatch_wait", optional=true)
    public static native @MachineSizedSInt long wait(VoidPtr object, long timeout);
    @Bridge(symbol="dispatch_notify", optional=true)
    public static native void notify(VoidPtr object, @ByVal DispatchObject queue, @Block Runnable notification_block);
    @Bridge(symbol="dispatch_cancel", optional=true)
    public static native void cancel(VoidPtr object);
    @Bridge(symbol="dispatch_testcancel", optional=true)
    public static native @MachineSizedSInt long testcancel(VoidPtr object);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_main", optional=true)
    public static native void main();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_create", optional=true)
    public static native @Block Runnable blockCreate(@MachineSizedUInt long flags, @Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_create_with_qos_class", optional=true)
    public static native @Block Runnable blockCreateWithQosClass(@MachineSizedUInt long flags, int qos_class, int relative_priority, @Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_perform", optional=true)
    public static native void blockPerform(@MachineSizedUInt long flags, @Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_wait", optional=true)
    public static native @MachineSizedSInt long blockWait(@Block Runnable block, long timeout);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_notify", optional=true)
    public static native void blockNotify(@Block Runnable block, DispatchQueue queue, @Block Runnable notification_block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_cancel", optional=true)
    public static native void blockCancel(@Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="dispatch_block_testcancel", optional=true)
    public static native @MachineSizedSInt long blockTestcancel(@Block Runnable block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_once", optional=true)
    public static native void once(MachineSizedSIntPtr predicate, @Block Runnable block);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_read", optional=true)
    public static native void read(int fd, @MachineSizedUInt long length, DispatchQueue queue, @Block VoidBlock2<DispatchData, Integer> handler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_write", optional=true)
    public static native void write(int fd, DispatchData data, DispatchQueue queue, @Block VoidBlock2<DispatchData, Integer> handler);
    /*</methods>*/
}
