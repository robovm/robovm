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
/*<annotations>*/@Library("dispatch")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchSourceType/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchSourceTypePtr extends Ptr<DispatchSourceType, DispatchSourceTypePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchSourceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected DispatchSourceType() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_data_add", optional=true)
    public static native DispatchSourceType DataAdd();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_data_or", optional=true)
    public static native DispatchSourceType DataOr();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_mach_send", optional=true)
    public static native DispatchSourceType MachSend();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_mach_recv", optional=true)
    public static native DispatchSourceType MachRecv();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_proc", optional=true)
    public static native DispatchSourceType Proc();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_read", optional=true)
    public static native DispatchSourceType Read();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_signal", optional=true)
    public static native DispatchSourceType Signal();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_timer", optional=true)
    public static native DispatchSourceType Timer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_vnode", optional=true)
    public static native DispatchSourceType Vnode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="_dispatch_source_type_write", optional=true)
    public static native DispatchSourceType Write();
    /*</methods>*/
}
