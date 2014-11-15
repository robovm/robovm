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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTimer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSTimerPtr extends Ptr<NSTimer, NSTimerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTimer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTimer() {}
    protected NSTimer(SkipInit skipInit) { super(skipInit); }
    public NSTimer(NSDate date, double ti, NSObject t, Selector s, NSObject ui, boolean rep) { super((SkipInit) null); initObject(init(date, ti, t, s, ui, rep)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "fireDate")
    public native NSDate getFireDate();
    @Property(selector = "setFireDate:")
    public native void setFireDate(NSDate v);
    @Property(selector = "timeInterval")
    public native double getTimeInterval();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "tolerance")
    public native double getTolerance();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTolerance:")
    public native void setTolerance(double v);
    @Property(selector = "isValid")
    public native boolean isValid();
    @Property(selector = "userInfo")
    public native NSObject getUserInfo();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFireDate:interval:target:selector:userInfo:repeats:")
    protected native @Pointer long init(NSDate date, double ti, NSObject t, Selector s, NSObject ui, boolean rep);
    @Method(selector = "fire")
    public native void fire();
    @Method(selector = "invalidate")
    public native void invalidate();
    @Method(selector = "timerWithTimeInterval:invocation:repeats:")
    public static native NSTimer create(double ti, NSInvocation invocation, boolean yesOrNo);
    @Method(selector = "scheduledTimerWithTimeInterval:invocation:repeats:")
    public static native NSTimer createScheduled(double ti, NSInvocation invocation, boolean yesOrNo);
    @Method(selector = "timerWithTimeInterval:target:selector:userInfo:repeats:")
    public static native NSTimer create(double ti, NSObject aTarget, Selector aSelector, NSObject userInfo, boolean yesOrNo);
    @Method(selector = "scheduledTimerWithTimeInterval:target:selector:userInfo:repeats:")
    public static native NSTimer createScheduled(double ti, NSObject aTarget, Selector aSelector, NSObject userInfo, boolean yesOrNo);
    /*</methods>*/
}
