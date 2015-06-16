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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFRunLoop/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFRunLoopPtr extends Ptr<CFRunLoop, CFRunLoopPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFRunLoop.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFRunLoop() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public void addCommonMode(CFRunLoopMode mode) {
        addCommonMode(mode.value().toString());
    }
    public double getNextTimerFireDate(CFRunLoopMode mode) {
        return getNextTimerFireDate(mode.value().toString());
    }
    public static CFRunLoopExitReason runInMode(CFRunLoopMode mode, double seconds, boolean returnAfterSourceHandled) {
        return runInMode(mode.value().toString(), seconds, returnAfterSourceHandled);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void performBlock(String mode, Runnable block) {
        performBlock(new CFString(mode), block);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void performBlock(List<String> mode, Runnable block) {
        performBlock(CFArray.fromStrings(mode), block);
    }
    public boolean containsSource(CFRunLoopSource source, CFRunLoopMode mode) {
        return containsSource(source, mode.value().toString());
    }
    public void addSource(CFRunLoopSource source, CFRunLoopMode mode) {
        addSource(source, mode.value().toString());
    }
    public void removeSource(CFRunLoopSource source, CFRunLoopMode mode) {
        removeSource(source, mode.value().toString());
    }
    public boolean containsObserver(CFRunLoopObserver observer, CFRunLoopMode mode) {
        return containsObserver(observer, mode.value().toString());
    }
    public void addObserver(CFRunLoopObserver observer, CFRunLoopMode mode) {
        addObserver(observer, mode.value().toString());
    }
    public void removeObserver(CFRunLoopObserver observer, CFRunLoopMode mode) {
        removeObserver(observer, mode.value().toString());
    }
    public boolean containsTimer(CFRunLoopTimer timer, CFRunLoopMode mode) {
        return containsTimer(timer, mode.value().toString());
    }
    public void addTimer(CFRunLoopTimer timer, CFRunLoopMode mode) {
        addTimer(timer, mode.value().toString());
    }
    public void removeTimer(CFRunLoopTimer timer, CFRunLoopMode mode) {
        removeTimer(timer, mode.value().toString());
    }
    /*<methods>*/
    @Bridge(symbol="CFRunLoopGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFRunLoopGetCurrent", optional=true)
    public static native CFRunLoop getCurrent();
    @Bridge(symbol="CFRunLoopGetMain", optional=true)
    public static native CFRunLoop getMain();
    @Bridge(symbol="CFRunLoopCopyCurrentMode", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCurrentMode();
    @Bridge(symbol="CFRunLoopCopyAllModes", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getAllModes();
    @Bridge(symbol="CFRunLoopAddCommonMode", optional=true)
    public native void addCommonMode(String mode);
    @Bridge(symbol="CFRunLoopGetNextTimerFireDate", optional=true)
    public native double getNextTimerFireDate(String mode);
    @Bridge(symbol="CFRunLoopRun", optional=true)
    public static native void run();
    @Bridge(symbol="CFRunLoopRunInMode", optional=true)
    public static native CFRunLoopExitReason runInMode(String mode, double seconds, boolean returnAfterSourceHandled);
    @Bridge(symbol="CFRunLoopIsWaiting", optional=true)
    public native boolean isWaiting();
    @Bridge(symbol="CFRunLoopWakeUp", optional=true)
    public native void wakeUp();
    @Bridge(symbol="CFRunLoopStop", optional=true)
    public native void stop();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFRunLoopPerformBlock", optional=true)
    private native void performBlock(CFType mode, @Block Runnable block);
    @Bridge(symbol="CFRunLoopContainsSource", optional=true)
    public native boolean containsSource(CFRunLoopSource source, String mode);
    @Bridge(symbol="CFRunLoopAddSource", optional=true)
    public native void addSource(CFRunLoopSource source, String mode);
    @Bridge(symbol="CFRunLoopRemoveSource", optional=true)
    public native void removeSource(CFRunLoopSource source, String mode);
    @Bridge(symbol="CFRunLoopContainsObserver", optional=true)
    public native boolean containsObserver(CFRunLoopObserver observer, String mode);
    @Bridge(symbol="CFRunLoopAddObserver", optional=true)
    public native void addObserver(CFRunLoopObserver observer, String mode);
    @Bridge(symbol="CFRunLoopRemoveObserver", optional=true)
    public native void removeObserver(CFRunLoopObserver observer, String mode);
    @Bridge(symbol="CFRunLoopContainsTimer", optional=true)
    public native boolean containsTimer(CFRunLoopTimer timer, String mode);
    @Bridge(symbol="CFRunLoopAddTimer", optional=true)
    public native void addTimer(CFRunLoopTimer timer, String mode);
    @Bridge(symbol="CFRunLoopRemoveTimer", optional=true)
    public native void removeTimer(CFRunLoopTimer timer, String mode);
    /*</methods>*/
}
