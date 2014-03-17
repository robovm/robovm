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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchGroup/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchGroupPtr extends Ptr<DispatchGroup, DispatchGroupPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected DispatchGroup() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="dispatch_group_create")
    public static native DispatchGroup create();
    @Bridge(symbol="dispatch_group_async")
    public native void async(DispatchQueue queue, ObjCBlock block);
    @Bridge(symbol="dispatch_group_async_f")
    public native void asyncF(DispatchQueue queue, VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_group_wait")
    public native @MachineSizedSInt long await(long timeout);
    @Bridge(symbol="dispatch_group_notify")
    public native void notify(DispatchQueue queue, ObjCBlock block);
    @Bridge(symbol="dispatch_group_notify_f")
    public native void notifyF(DispatchQueue queue, VoidPtr context, FunctionPtr work);
    @Bridge(symbol="dispatch_group_enter")
    public native void enter();
    @Bridge(symbol="dispatch_group_leave")
    public native void leave();
    /*</methods>*/
}
