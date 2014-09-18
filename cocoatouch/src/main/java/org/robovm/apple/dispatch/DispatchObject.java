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
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_retain", optional=true)
    public native void retain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_release", optional=true)
    public native void release();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_suspend", optional=true)
    public native void suspend();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_resume", optional=true)
    public native void resume();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_set_target_queue", optional=true)
    public native void setTargetQueue(DispatchQueue queue);
    /*</methods>*/
}
