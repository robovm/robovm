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
package org.robovm.cocoatouch.coreanimation;

/*<imports>*/
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.foundation.*;
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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html">CADisplayLink Class Reference</a>
 *   @since Available in iOS 3.1 and later.
 * </div>
 */
/*<library>*/@Library("QuartzCore")/*</library>*/
@NativeClass public class /*<name>*/ CADisplayLink /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ CADisplayLink /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ CADisplayLink /*</name>*/.class);

    /*<constructors>*/
    protected CADisplayLink(SkipInit skipInit) { super(skipInit); }
    public CADisplayLink() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector duration = Selector.register("duration");
    @Bridge private native static double objc_getDuration(CADisplayLink __self__, Selector __cmd__);
    @Bridge private native static double objc_getDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/duration">@property(readonly, nonatomic) CFTimeInterval duration</a>
     * @since Available in iOS 3.1 and later.
     */
    public double getDuration() {
        if (customClass) { return objc_getDurationSuper(getSuper(), duration); } else { return objc_getDuration(this, duration); }
    }
    
    private static final Selector frameInterval = Selector.register("frameInterval");
    @Bridge private native static int objc_getFrameInterval(CADisplayLink __self__, Selector __cmd__);
    @Bridge private native static int objc_getFrameIntervalSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/frameInterval">@property(nonatomic) NSInteger frameInterval</a>
     * @since Available in iOS 3.1 and later.
     */
    public int getFrameInterval() {
        if (customClass) { return objc_getFrameIntervalSuper(getSuper(), frameInterval); } else { return objc_getFrameInterval(this, frameInterval); }
    }
    
    private static final Selector setFrameInterval$ = Selector.register("setFrameInterval:");
    @Bridge private native static void objc_setFrameInterval(CADisplayLink __self__, Selector __cmd__, int frameInterval);
    @Bridge private native static void objc_setFrameIntervalSuper(ObjCSuper __super__, Selector __cmd__, int frameInterval);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/frameInterval">@property(nonatomic) NSInteger frameInterval</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setFrameInterval(int frameInterval) {
        if (customClass) { objc_setFrameIntervalSuper(getSuper(), setFrameInterval$, frameInterval); } else { objc_setFrameInterval(this, setFrameInterval$, frameInterval); }
    }
    
    private static final Selector isPaused = Selector.register("isPaused");
    @Bridge private native static boolean objc_isPaused(CADisplayLink __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPausedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/paused">@property(getter=isPaused, nonatomic) BOOL paused</a>
     * @since Available in iOS 3.1 and later.
     */
    public boolean isPaused() {
        if (customClass) { return objc_isPausedSuper(getSuper(), isPaused); } else { return objc_isPaused(this, isPaused); }
    }
    
    private static final Selector setPaused$ = Selector.register("setPaused:");
    @Bridge private native static void objc_setPaused(CADisplayLink __self__, Selector __cmd__, boolean paused);
    @Bridge private native static void objc_setPausedSuper(ObjCSuper __super__, Selector __cmd__, boolean paused);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/paused">@property(getter=isPaused, nonatomic) BOOL paused</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setPaused(boolean paused) {
        if (customClass) { objc_setPausedSuper(getSuper(), setPaused$, paused); } else { objc_setPaused(this, setPaused$, paused); }
    }
    
    private static final Selector timestamp = Selector.register("timestamp");
    @Bridge private native static double objc_getTimestamp(CADisplayLink __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimestampSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/CADisplayLink/timestamp">@property(readonly, nonatomic) CFTimeInterval timestamp</a>
     * @since Available in iOS 3.1 and later.
     */
    public double getTimestamp() {
        if (customClass) { return objc_getTimestampSuper(getSuper(), timestamp); } else { return objc_getTimestamp(this, timestamp); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector displayLinkWithTarget$selector$ = Selector.register("displayLinkWithTarget:selector:");
    @Bridge private native static CADisplayLink objc_create(ObjCClass __self__, Selector __cmd__, NSObject target, Selector sel);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/clm/CADisplayLink/displayLinkWithTarget:selector:">+ (CADisplayLink *)displayLinkWithTarget:(id)target selector:(SEL)sel</a>
     * @since Available in iOS 3.1 and later.
     */
    public static CADisplayLink create(NSObject target, Selector sel) {
        return objc_create(objCClass, displayLinkWithTarget$selector$, target, sel);
    }
    
    private static final Selector addToRunLoop$forMode$ = Selector.register("addToRunLoop:forMode:");
    @Bridge private native static void objc_addToRunLoop(CADisplayLink __self__, Selector __cmd__, NSRunLoop runloop, String mode);
    @Bridge private native static void objc_addToRunLoopSuper(ObjCSuper __super__, Selector __cmd__, NSRunLoop runloop, String mode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instm/CADisplayLink/addToRunLoop:forMode:">- (void)addToRunLoop:(NSRunLoop *)runloop forMode:(NSString *)mode</a>
     * @since Available in iOS 3.1 and later.
     */
    public void addToRunLoop(NSRunLoop runloop, String mode) {
        if (customClass) { objc_addToRunLoopSuper(getSuper(), addToRunLoop$forMode$, runloop, mode); } else { objc_addToRunLoop(this, addToRunLoop$forMode$, runloop, mode); }
    }
    
    private static final Selector invalidate = Selector.register("invalidate");
    @Bridge private native static void objc_invalidate(CADisplayLink __self__, Selector __cmd__);
    @Bridge private native static void objc_invalidateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instm/CADisplayLink/invalidate">- (void)invalidate</a>
     * @since Available in iOS 3.1 and later.
     */
    public void invalidate() {
        if (customClass) { objc_invalidateSuper(getSuper(), invalidate); } else { objc_invalidate(this, invalidate); }
    }
    
    private static final Selector removeFromRunLoop$forMode$ = Selector.register("removeFromRunLoop:forMode:");
    @Bridge private native static void objc_removeFromRunLoop(CADisplayLink __self__, Selector __cmd__, NSRunLoop runloop, String mode);
    @Bridge private native static void objc_removeFromRunLoopSuper(ObjCSuper __super__, Selector __cmd__, NSRunLoop runloop, String mode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/CoreAnimation_framework/../../../QuartzCore/Reference/CADisplayLink_ClassRef/Reference/Reference.html#//apple_ref/occ/instm/CADisplayLink/removeFromRunLoop:forMode:">- (void)removeFromRunLoop:(NSRunLoop *)runloop forMode:(NSString *)mode</a>
     * @since Available in iOS 3.1 and later.
     */
    public void removeFromRunLoop(NSRunLoop runloop, String mode) {
        if (customClass) { objc_removeFromRunLoopSuper(getSuper(), removeFromRunLoop$forMode$, runloop, mode); } else { objc_removeFromRunLoop(this, removeFromRunLoop$forMode$, runloop, mode); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("duration") public static double getDuration(CADisplayLink __self__, Selector __cmd__) { return __self__.getDuration(); }
        @Callback @BindSelector("frameInterval") public static int getFrameInterval(CADisplayLink __self__, Selector __cmd__) { return __self__.getFrameInterval(); }
        @Callback @BindSelector("setFrameInterval:") public static void setFrameInterval(CADisplayLink __self__, Selector __cmd__, int frameInterval) { __self__.setFrameInterval(frameInterval); }
        @Callback @BindSelector("isPaused") public static boolean isPaused(CADisplayLink __self__, Selector __cmd__) { return __self__.isPaused(); }
        @Callback @BindSelector("setPaused:") public static void setPaused(CADisplayLink __self__, Selector __cmd__, boolean paused) { __self__.setPaused(paused); }
        @Callback @BindSelector("timestamp") public static double getTimestamp(CADisplayLink __self__, Selector __cmd__) { return __self__.getTimestamp(); }
        @Callback @BindSelector("addToRunLoop:forMode:") public static void addToRunLoop(CADisplayLink __self__, Selector __cmd__, NSRunLoop runloop, String mode) { __self__.addToRunLoop(runloop, mode); }
        @Callback @BindSelector("invalidate") public static void invalidate(CADisplayLink __self__, Selector __cmd__) { __self__.invalidate(); }
        @Callback @BindSelector("removeFromRunLoop:forMode:") public static void removeFromRunLoop(CADisplayLink __self__, Selector __cmd__, NSRunLoop runloop, String mode) { __self__.removeFromRunLoop(runloop, mode); }
    }
    /*</callbacks>*/

}
