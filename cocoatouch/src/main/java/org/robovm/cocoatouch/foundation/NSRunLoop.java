/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html">NSRunLoop Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSRunLoop /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSRunLoop /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSRunLoop /*</name>*/.class);

    /*<constructors>*/
    protected NSRunLoop(SkipInit skipInit) { super(skipInit); }
    public NSRunLoop() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector currentRunLoop = Selector.register("currentRunLoop");
    @Bridge private native static NSRunLoop objc_getCurrent(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/clm/NSRunLoop/currentRunLoop">+ (NSRunLoop *)currentRunLoop</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSRunLoop getCurrent() {
        return objc_getCurrent(objCClass, currentRunLoop);
    }
    
    private static final Selector mainRunLoop = Selector.register("mainRunLoop");
    @Bridge private native static NSRunLoop objc_getMain(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/clm/NSRunLoop/mainRunLoop">+ (NSRunLoop *)mainRunLoop</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSRunLoop getMain() {
        return objc_getMain(objCClass, mainRunLoop);
    }
    
    private static final Selector acceptInputForMode$beforeDate$ = Selector.register("acceptInputForMode:beforeDate:");
    @Bridge private native static void objc_acceptInputForMode(NSRunLoop __self__, Selector __cmd__, String mode, NSDate limitDate);
    @Bridge private native static void objc_acceptInputForModeSuper(ObjCSuper __super__, Selector __cmd__, String mode, NSDate limitDate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/acceptInputForMode:beforeDate:">- (void)acceptInputForMode:(NSString *)mode beforeDate:(NSDate *)limitDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void acceptInputForMode(String mode, NSDate limitDate) {
        if (customClass) { objc_acceptInputForModeSuper(getSuper(), acceptInputForMode$beforeDate$, mode, limitDate); } else { objc_acceptInputForMode(this, acceptInputForMode$beforeDate$, mode, limitDate); }
    }
    
    private static final Selector addTimer$forMode$ = Selector.register("addTimer:forMode:");
    @Bridge private native static void objc_addTimer(NSRunLoop __self__, Selector __cmd__, NSTimer aTimer, String mode);
    @Bridge private native static void objc_addTimerSuper(ObjCSuper __super__, Selector __cmd__, NSTimer aTimer, String mode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/addTimer:forMode:">- (void)addTimer:(NSTimer *)aTimer forMode:(NSString *)mode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void addTimer(NSTimer aTimer, String mode) {
        if (customClass) { objc_addTimerSuper(getSuper(), addTimer$forMode$, aTimer, mode); } else { objc_addTimer(this, addTimer$forMode$, aTimer, mode); }
    }
    
    private static final Selector cancelPerformSelector$target$argument$ = Selector.register("cancelPerformSelector:target:argument:");
    @Bridge private native static void objc_cancel(NSRunLoop __self__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument);
    @Bridge private native static void objc_cancelSuper(ObjCSuper __super__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/cancelPerformSelector:target:argument:">- (void)cancelPerformSelector:(SEL)aSelector target:(id)target argument:(id)anArgument</a>
     * @since Available in iOS 2.0 and later.
     */
    public void cancel(Selector aSelector, NSObject target, NSObject anArgument) {
        if (customClass) { objc_cancelSuper(getSuper(), cancelPerformSelector$target$argument$, aSelector, target, anArgument); } else { objc_cancel(this, cancelPerformSelector$target$argument$, aSelector, target, anArgument); }
    }
    
    private static final Selector cancelPerformSelectorsWithTarget$ = Selector.register("cancelPerformSelectorsWithTarget:");
    @Bridge private native static void objc_cancel(NSRunLoop __self__, Selector __cmd__, NSObject target);
    @Bridge private native static void objc_cancelSuper(ObjCSuper __super__, Selector __cmd__, NSObject target);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/cancelPerformSelectorsWithTarget:">- (void)cancelPerformSelectorsWithTarget:(id)target</a>
     * @since Available in iOS 2.0 and later.
     */
    public void cancel(NSObject target) {
        if (customClass) { objc_cancelSuper(getSuper(), cancelPerformSelectorsWithTarget$, target); } else { objc_cancel(this, cancelPerformSelectorsWithTarget$, target); }
    }
    
    private static final Selector currentMode = Selector.register("currentMode");
    @Bridge private native static String objc_getCurrentMode(NSRunLoop __self__, Selector __cmd__);
    @Bridge private native static String objc_getCurrentModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/currentMode">- (NSString *)currentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getCurrentMode() {
        if (customClass) { return objc_getCurrentModeSuper(getSuper(), currentMode); } else { return objc_getCurrentMode(this, currentMode); }
    }
    
    private static final Selector limitDateForMode$ = Selector.register("limitDateForMode:");
    @Bridge private native static NSDate objc_limitDateForMode(NSRunLoop __self__, Selector __cmd__, String mode);
    @Bridge private native static NSDate objc_limitDateForModeSuper(ObjCSuper __super__, Selector __cmd__, String mode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/limitDateForMode:">- (NSDate *)limitDateForMode:(NSString *)mode</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSDate limitDateForMode(String mode) {
        if (customClass) { return objc_limitDateForModeSuper(getSuper(), limitDateForMode$, mode); } else { return objc_limitDateForMode(this, limitDateForMode$, mode); }
    }
    
    private static final Selector performSelector$target$argument$order$modes$ = Selector.register("performSelector:target:argument:order:modes:");
    @Bridge private native static void objc_perform(NSRunLoop __self__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument, int order, NSArray modes);
    @Bridge private native static void objc_performSuper(ObjCSuper __super__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument, int order, NSArray modes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/performSelector:target:argument:order:modes:">- (void)performSelector:(SEL)aSelector target:(id)target argument:(id)anArgument order:(NSUInteger)order modes:(NSArray *)modes</a>
     * @since Available in iOS 2.0 and later.
     */
    public void perform(Selector aSelector, NSObject target, NSObject anArgument, int order, NSArray modes) {
        if (customClass) { objc_performSuper(getSuper(), performSelector$target$argument$order$modes$, aSelector, target, anArgument, order, modes); } else { objc_perform(this, performSelector$target$argument$order$modes$, aSelector, target, anArgument, order, modes); }
    }
    
    private static final Selector run = Selector.register("run");
    @Bridge private native static void objc_run(NSRunLoop __self__, Selector __cmd__);
    @Bridge private native static void objc_runSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/run">- (void)run</a>
     * @since Available in iOS 2.0 and later.
     */
    public void run() {
        if (customClass) { objc_runSuper(getSuper(), run); } else { objc_run(this, run); }
    }
    
    private static final Selector runMode$beforeDate$ = Selector.register("runMode:beforeDate:");
    @Bridge private native static boolean objc_runUntil(NSRunLoop __self__, Selector __cmd__, String mode, NSDate limitDate);
    @Bridge private native static boolean objc_runUntilSuper(ObjCSuper __super__, Selector __cmd__, String mode, NSDate limitDate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/runMode:beforeDate:">- (BOOL)runMode:(NSString *)mode beforeDate:(NSDate *)limitDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean runUntil(String mode, NSDate limitDate) {
        if (customClass) { return objc_runUntilSuper(getSuper(), runMode$beforeDate$, mode, limitDate); } else { return objc_runUntil(this, runMode$beforeDate$, mode, limitDate); }
    }
    
    private static final Selector runUntilDate$ = Selector.register("runUntilDate:");
    @Bridge private native static void objc_runUntil(NSRunLoop __self__, Selector __cmd__, NSDate limitDate);
    @Bridge private native static void objc_runUntilSuper(ObjCSuper __super__, Selector __cmd__, NSDate limitDate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSRunLoop_Class/Reference/Reference.html#//apple_ref/occ/instm/NSRunLoop/runUntilDate:">- (void)runUntilDate:(NSDate *)limitDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void runUntil(NSDate limitDate) {
        if (customClass) { objc_runUntilSuper(getSuper(), runUntilDate$, limitDate); } else { objc_runUntil(this, runUntilDate$, limitDate); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("acceptInputForMode:beforeDate:") public static void acceptInputForMode(NSRunLoop __self__, Selector __cmd__, String mode, NSDate limitDate) { __self__.acceptInputForMode(mode, limitDate); }
        @Callback @BindSelector("addTimer:forMode:") public static void addTimer(NSRunLoop __self__, Selector __cmd__, NSTimer aTimer, String mode) { __self__.addTimer(aTimer, mode); }
        @Callback @BindSelector("cancelPerformSelector:target:argument:") public static void cancel(NSRunLoop __self__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument) { __self__.cancel(aSelector, target, anArgument); }
        @Callback @BindSelector("cancelPerformSelectorsWithTarget:") public static void cancel(NSRunLoop __self__, Selector __cmd__, NSObject target) { __self__.cancel(target); }
        @Callback @BindSelector("currentMode") public static String getCurrentMode(NSRunLoop __self__, Selector __cmd__) { return __self__.getCurrentMode(); }
        @Callback @BindSelector("limitDateForMode:") public static NSDate limitDateForMode(NSRunLoop __self__, Selector __cmd__, String mode) { return __self__.limitDateForMode(mode); }
        @Callback @BindSelector("performSelector:target:argument:order:modes:") public static void perform(NSRunLoop __self__, Selector __cmd__, Selector aSelector, NSObject target, NSObject anArgument, int order, NSArray modes) { __self__.perform(aSelector, target, anArgument, order, modes); }
        @Callback @BindSelector("run") public static void run(NSRunLoop __self__, Selector __cmd__) { __self__.run(); }
        @Callback @BindSelector("runMode:beforeDate:") public static boolean runUntil(NSRunLoop __self__, Selector __cmd__, String mode, NSDate limitDate) { return __self__.runUntil(mode, limitDate); }
        @Callback @BindSelector("runUntilDate:") public static void runUntil(NSRunLoop __self__, Selector __cmd__, NSDate limitDate) { __self__.runUntil(limitDate); }
    }
    /*</callbacks>*/

}
