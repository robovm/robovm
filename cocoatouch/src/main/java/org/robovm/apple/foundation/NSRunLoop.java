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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSRunLoop/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSRunLoopPtr extends Ptr<NSRunLoop, NSRunLoopPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSRunLoop.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSRunLoop() {}
    protected NSRunLoop(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "currentMode")
    public native String getCurrentMode();
    @Method(selector = "addTimer:forMode:")
    public native void addTimer$forMode$(NSTimer timer, String mode);
    @Method(selector = "limitDateForMode:")
    public native NSDate limitDateForMode$(String mode);
    @Method(selector = "acceptInputForMode:beforeDate:")
    public native void acceptInputForMode$beforeDate$(String mode, NSDate limitDate);
    @Method(selector = "currentRunLoop")
    public static native NSRunLoop getCurrent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "mainRunLoop")
    public static native NSRunLoop getMain();
    @Method(selector = "run")
    public native void run();
    @Method(selector = "runUntilDate:")
    public native void runUntil(NSDate limitDate);
    @Method(selector = "runMode:beforeDate:")
    public native boolean runUntil(String mode, NSDate limitDate);
    @Method(selector = "performSelector:target:argument:order:modes:")
    public native void perform(Selector aSelector, NSObject target, NSObject arg, @MachineSizedUInt long order, NSArray<?> modes);
    @Method(selector = "cancelPerformSelector:target:argument:")
    public native void cancel(Selector aSelector, NSObject target, NSObject arg);
    @Method(selector = "cancelPerformSelectorsWithTarget:")
    public native void cancel(NSObject target);
    /*</methods>*/
}
