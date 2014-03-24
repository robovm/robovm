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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchSource/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchSourcePtr extends Ptr<DispatchSource, DispatchSourcePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchSource.class); }/*</bind>*/
    /*<constants>*/
    public static final int MACH_SEND_DEAD = 0x1;
    public static final int MEMORYPRESSURE_NORMAL = 0x01;
    public static final int MEMORYPRESSURE_WARN = 0x02;
    public static final int MEMORYPRESSURE_CRITICAL = 0x04;
    public static final int PROC_EXIT = 0x80000000;
    public static final int PROC_FORK = 0x40000000;
    public static final int PROC_EXEC = 0x20000000;
    public static final int PROC_SIGNAL = 0x08000000;
    public static final int VNODE_DELETE = 0x1;
    public static final int VNODE_WRITE = 0x2;
    public static final int VNODE_EXTEND = 0x4;
    public static final int VNODE_ATTRIB = 0x8;
    public static final int VNODE_LINK = 0x10;
    public static final int VNODE_RENAME = 0x20;
    public static final int VNODE_REVOKE = 0x40;
    public static final int TIMER_STRICT = 0x1;
    /*</constants>*/
    /*<constructors>*/
    protected DispatchSource() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="dispatch_source_create")
    public static native DispatchSource create(DispatchSourceType type, @MachineSizedUInt long handle, @MachineSizedUInt long mask, DispatchQueue queue);
    @Bridge(symbol="dispatch_source_set_event_handler")
    public native void setEventHandler(@Block Runnable handler);
    @Bridge(symbol="dispatch_source_set_event_handler_f")
    public native void setEventHandlerF(FunctionPtr handler);
    @Bridge(symbol="dispatch_source_set_cancel_handler")
    public native void setCancelHandler(@Block Runnable cancel_handler);
    @Bridge(symbol="dispatch_source_set_cancel_handler_f")
    public native void setCancelHandlerF(FunctionPtr cancel_handler);
    @Bridge(symbol="dispatch_source_cancel")
    public native void cancel();
    @Bridge(symbol="dispatch_source_testcancel")
    public native @MachineSizedSInt long testcancel();
    @Bridge(symbol="dispatch_source_get_handle")
    public native @MachineSizedUInt long getSourceHandle();
    @Bridge(symbol="dispatch_source_get_mask")
    public native @MachineSizedUInt long getMask();
    @Bridge(symbol="dispatch_source_get_data")
    public native @MachineSizedUInt long getData();
    @Bridge(symbol="dispatch_source_merge_data")
    public native void mergeData(@MachineSizedUInt long value);
    @Bridge(symbol="dispatch_source_set_timer")
    public native void setTimer(long start, long interval, long leeway);
    @Bridge(symbol="dispatch_source_set_registration_handler")
    public native void setRegistrationHandler(@Block Runnable registration_handler);
    @Bridge(symbol="dispatch_source_set_registration_handler_f")
    public native void setRegistrationHandlerF(FunctionPtr registration_handler);
    /*</methods>*/
}
