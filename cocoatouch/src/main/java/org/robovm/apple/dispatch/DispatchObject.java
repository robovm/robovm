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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchObject/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchObjectPtr extends Ptr<DispatchObject, DispatchObjectPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected DispatchObject() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="dispatch_retain")
    public native void retain();
    @Bridge(symbol="dispatch_release")
    public native void release();
    @Bridge(symbol="dispatch_get_context")
    public native VoidPtr getContext();
    @Bridge(symbol="dispatch_set_context")
    public native void setContext(VoidPtr context);
    @Bridge(symbol="dispatch_set_finalizer_f")
    public native void setFinalizerF(FunctionPtr finalizer);
    @Bridge(symbol="dispatch_suspend")
    public native void suspend();
    @Bridge(symbol="dispatch_resume")
    public native void resume();
    @Bridge(symbol="dispatch_set_target_queue")
    public native void setTargetQueue(DispatchQueue queue);
    /*</methods>*/
}
