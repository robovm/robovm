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
    /*<methods>*/
    @GlobalValue(symbol="kCFRunLoopDefaultMode")
    public static native CFString ModeDefault();
    @GlobalValue(symbol="kCFRunLoopCommonModes")
    public static native CFString ModeCommon();
    
    @Bridge(symbol="CFRunLoopGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFRunLoopGetCurrent")
    public static native CFRunLoop getCurrent();
    @Bridge(symbol="CFRunLoopGetMain")
    public static native CFRunLoop getMain();
    @Bridge(symbol="CFRunLoopCopyCurrentMode")
    public native CFString copyCurrentMode();
    @Bridge(symbol="CFRunLoopCopyAllModes")
    public native CFArray copyAllModes();
    @Bridge(symbol="CFRunLoopAddCommonMode")
    public native void addCommonMode(CFString mode);
    @Bridge(symbol="CFRunLoopGetNextTimerFireDate")
    public native double getNextTimerFireDate(CFString mode);
    @Bridge(symbol="CFRunLoopRun")
    public static native void run();
    @Bridge(symbol="CFRunLoopRunInMode")
    public static native int runInMode(CFString mode, double seconds, boolean returnAfterSourceHandled);
    @Bridge(symbol="CFRunLoopIsWaiting")
    public native boolean isWaiting();
    @Bridge(symbol="CFRunLoopWakeUp")
    public native void wakeUp();
    @Bridge(symbol="CFRunLoopStop")
    public native void stop();
    @Bridge(symbol="CFRunLoopPerformBlock")
    public native void performBlock(CFType mode, @Block Runnable block);
    @Bridge(symbol="CFRunLoopContainsSource")
    public native boolean containsSource(CFRunLoopSource source, CFString mode);
    @Bridge(symbol="CFRunLoopAddSource")
    public native void addSource(CFRunLoopSource source, CFString mode);
    @Bridge(symbol="CFRunLoopRemoveSource")
    public native void removeSource(CFRunLoopSource source, CFString mode);
    @Bridge(symbol="CFRunLoopContainsObserver")
    public native boolean containsObserver(CFRunLoopObserver observer, CFString mode);
    @Bridge(symbol="CFRunLoopAddObserver")
    public native void addObserver(CFRunLoopObserver observer, CFString mode);
    @Bridge(symbol="CFRunLoopRemoveObserver")
    public native void removeObserver(CFRunLoopObserver observer, CFString mode);
    @Bridge(symbol="CFRunLoopContainsTimer")
    public native boolean containsTimer(CFRunLoopTimer timer, CFString mode);
    @Bridge(symbol="CFRunLoopAddTimer")
    public native void addTimer(CFRunLoopTimer timer, CFString mode);
    @Bridge(symbol="CFRunLoopRemoveTimer")
    public native void removeTimer(CFRunLoopTimer timer, CFString mode);
    /*</methods>*/
}
