/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("dispatch")/*</annotations>*/
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
    /*<methods>*/
    @GlobalValue(symbol="_dispatch_main_q")
    public static native DispatchQueue getMainQueue();
    
    @Bridge(symbol="dispatch_async")
    public native void async(@Block Runnable block);
    @Bridge(symbol="dispatch_async_f")
    public native void asyncF(VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_sync")
    public native void sync(@Block Runnable block);
    @Bridge(symbol="dispatch_sync_f")
    public native void syncF(VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_apply")
    public static native void apply(@MachineSizedUInt long iterations, DispatchQueue queue, ObjCBlock block);
    @Bridge(symbol="dispatch_apply_f")
    public static native void applyF(@MachineSizedUInt long iterations, DispatchQueue queue, VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_get_current_queue")
    public static native DispatchQueue getCurrentQueue();
    @Bridge(symbol="dispatch_get_global_queue")
    public static native DispatchQueue getGlobalQueue(@MachineSizedSInt long priority, @MachineSizedUInt long flags);
    @Bridge(symbol="dispatch_queue_create")
    public static native DispatchQueue create(BytePtr label, DispatchQueueAttr attr);
    @Bridge(symbol="dispatch_queue_get_label")
    public native BytePtr getLabel();
    @Bridge(symbol="dispatch_after")
    public static native void after(long when, DispatchQueue queue, @Block Runnable block);
    @Bridge(symbol="dispatch_after_f")
    public static native void afterF(long when, DispatchQueue queue, VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_barrier_async")
    public native void barrierAsync(@Block Runnable block);
    @Bridge(symbol="dispatch_barrier_async_f")
    public native void barrierAsyncF(VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_barrier_sync")
    public native void barrierSync(@Block Runnable block);
    @Bridge(symbol="dispatch_barrier_sync_f")
    public native void barrierSyncF(VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_queue_set_specific")
    public native void setSpecific(VoidPtr key, VoidPtr context, FunctionPtr destructor);
    @Bridge(symbol="dispatch_queue_get_specific")
    public native VoidPtr getSpecific(VoidPtr key);
    /*</methods>*/
}
