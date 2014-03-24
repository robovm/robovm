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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFRunLoopTimer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFRunLoopTimerPtr extends Ptr<CFRunLoopTimer, CFRunLoopTimerPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFRunLoopTimer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFRunLoopTimer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFRunLoopTimerGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFRunLoopTimerCreate")
    public static native CFRunLoopTimer create(CFAllocator allocator, double fireDate, double interval, @MachineSizedUInt long flags, @MachineSizedSInt long order, FunctionPtr callout, CFRunLoopTimerContext context);
    @Bridge(symbol="CFRunLoopTimerCreateWithHandler")
    public static native CFRunLoopTimer createWithHandler(CFAllocator allocator, double fireDate, double interval, @MachineSizedUInt long flags, @MachineSizedSInt long order, ObjCBlock block);
    @Bridge(symbol="CFRunLoopTimerGetNextFireDate")
    public native double getNextFireDate();
    @Bridge(symbol="CFRunLoopTimerSetNextFireDate")
    public native void setNextFireDate(double fireDate);
    @Bridge(symbol="CFRunLoopTimerGetInterval")
    public native double getInterval();
    @Bridge(symbol="CFRunLoopTimerDoesRepeat")
    public native boolean doesRepeat();
    @Bridge(symbol="CFRunLoopTimerGetOrder")
    public native @MachineSizedSInt long getOrder();
    @Bridge(symbol="CFRunLoopTimerInvalidate")
    public native void invalidate();
    @Bridge(symbol="CFRunLoopTimerIsValid")
    public native boolean isValid();
    @Bridge(symbol="CFRunLoopTimerGetContext")
    public native void getContext(CFRunLoopTimerContext context);
    @Bridge(symbol="CFRunLoopTimerGetTolerance")
    public native double getTolerance();
    @Bridge(symbol="CFRunLoopTimerSetTolerance")
    public native void setTolerance(double tolerance);
    /*</methods>*/
}
