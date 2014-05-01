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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSThread/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSThreadPtr extends Ptr<NSThread, NSThreadPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSThread.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSThread() {}
    protected NSThread(SkipInit skipInit) { super(skipInit); }
    public NSThread(NSThread target, Selector selector, NSThread argument) { super((SkipInit) null); initObject(initWithTarget$selector$object$(target, selector, argument)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSWillBecomeMultiThreadedNotification", optional=true)
    public static native String NotificationWillBecomeMultiThreaded();
    @GlobalValue(symbol="NSDidBecomeSingleThreadedNotification", optional=true)
    public static native String NotificationDidBecomeSingleThreaded();
    @GlobalValue(symbol="NSThreadWillExitNotification", optional=true)
    public static native String NotificationThreadWillExit();
    
    @Method(selector = "threadDictionary")
    public native NSMutableDictionary<?, ?> getThreadDictionary();
    @Method(selector = "setName:")
    public native void setName(String n);
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "stackSize")
    public native @MachineSizedUInt long getStackSize();
    @Method(selector = "setStackSize:")
    public native void setStackSize(@MachineSizedUInt long s);
    @Method(selector = "initWithTarget:selector:object:")
    protected native @Pointer long initWithTarget$selector$object$(NSThread target, Selector selector, NSThread argument);
    @Method(selector = "isExecuting")
    public native boolean isExecuting();
    @Method(selector = "isFinished")
    public native boolean isFinished();
    @Method(selector = "isCancelled")
    public native boolean isCancelled();
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "start")
    public native void start();
    @Method(selector = "main")
    public native void main();
    @Method(selector = "currentThread")
    public static native NSThread getCurrentThread();
    @Method(selector = "detachNewThreadSelector:toTarget:withObject:")
    public static native void detachNewThread(Selector selector, NSObject target, NSObject argument);
    @Method(selector = "isMultiThreaded")
    public static native boolean isMultiThreaded();
    @Method(selector = "sleepUntilDate:")
    public static native void sleep(NSDate date);
    @Method(selector = "sleepForTimeInterval:")
    public static native void sleep(double ti);
    @Method(selector = "exit")
    public static native void exit();
    @Method(selector = "callStackSymbols")
    public static native NSArray<NSString> getCallStackSymbols();
    @Method(selector = "mainThread")
    public static native NSThread getMainThread();
    /*</methods>*/
}
