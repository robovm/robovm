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
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("dispatch")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchSourceType/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchSourceTypePtr extends Ptr<DispatchSourceType, DispatchSourceTypePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchSourceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="_dispatch_source_type_data_add")
    public static native DispatchSourceType DataAdd();
    @GlobalValue(symbol="_dispatch_source_type_data_or")
    public static native DispatchSourceType DataOr();
    @GlobalValue(symbol="_dispatch_source_type_mach_send")
    public static native DispatchSourceType MachSend();
    @GlobalValue(symbol="_dispatch_source_type_mach_recv")
    public static native DispatchSourceType MachRecv();
    @GlobalValue(symbol="_dispatch_source_type_memorypressure")
    public static native DispatchSourceType Memorypressure();
    @GlobalValue(symbol="_dispatch_source_type_proc")
    public static native DispatchSourceType Proc();
    @GlobalValue(symbol="_dispatch_source_type_read")
    public static native DispatchSourceType Read();
    @GlobalValue(symbol="_dispatch_source_type_signal")
    public static native DispatchSourceType Signal();
    @GlobalValue(symbol="_dispatch_source_type_timer")
    public static native DispatchSourceType Timer();
    @GlobalValue(symbol="_dispatch_source_type_vnode")
    public static native DispatchSourceType Vnode();
    @GlobalValue(symbol="_dispatch_source_type_write")
    public static native DispatchSourceType Write();
    /*</methods>*/
}
