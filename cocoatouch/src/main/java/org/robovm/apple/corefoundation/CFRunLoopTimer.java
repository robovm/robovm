/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static CFRunLoopTimer create(double fireDate, double interval, @MachineSizedUInt long flags, @MachineSizedSInt long order, @Block VoidBlock1<CFRunLoopTimer> block) {
        return create(null, fireDate, interval, flags, order, block);
    }
    /*<methods>*/
    @Bridge(symbol="CFRunLoopTimerGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFRunLoopTimerCreateWithHandler", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopTimer create(CFAllocator allocator, double fireDate, double interval, @MachineSizedUInt long flags, @MachineSizedSInt long order, @Block VoidBlock1<CFRunLoopTimer> block);
    @Bridge(symbol="CFRunLoopTimerGetNextFireDate", optional=true)
    public native double getNextFireDate();
    @Bridge(symbol="CFRunLoopTimerSetNextFireDate", optional=true)
    public native void setNextFireDate(double fireDate);
    @Bridge(symbol="CFRunLoopTimerGetInterval", optional=true)
    public native double getInterval();
    @Bridge(symbol="CFRunLoopTimerDoesRepeat", optional=true)
    public native boolean doesRepeat();
    @Bridge(symbol="CFRunLoopTimerGetOrder", optional=true)
    public native @MachineSizedSInt long getOrder();
    @Bridge(symbol="CFRunLoopTimerInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFRunLoopTimerIsValid", optional=true)
    public native boolean isValid();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFRunLoopTimerGetTolerance", optional=true)
    public native double getTolerance();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFRunLoopTimerSetTolerance", optional=true)
    public native void setTolerance(double tolerance);
    /*</methods>*/
}
