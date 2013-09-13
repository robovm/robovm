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
package org.robovm.cocoatouch.glkit;

/*<imports>*/
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.foundation.*;
import org.robovm.cocoatouch.opengles.*;
import org.robovm.cocoatouch.uikit.*;
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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html">GLKViewController Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("GLKit")/*</library>*/
@NativeClass public class /*<name>*/ GLKViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ GLKViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ GLKViewController /*</name>*/.class);

    /*<constructors>*/
    protected GLKViewController(SkipInit skipInit) { super(skipInit); }
    public GLKViewController() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static GLKViewControllerDelegate objc_getDelegate(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static GLKViewControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/delegate">@property(nonatomic, assign) id&amp;lt;GLKViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(GLKViewController __self__, Selector __cmd__, GLKViewControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, GLKViewControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/delegate">@property(nonatomic, assign) id&amp;lt;GLKViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDelegate(GLKViewControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector framesDisplayed = Selector.register("framesDisplayed");
    @Bridge private native static int objc_getFramesDisplayed(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static int objc_getFramesDisplayedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/framesDisplayed">@property(nonatomic, readonly) NSInteger framesDisplayed</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getFramesDisplayed() {
        if (customClass) { return objc_getFramesDisplayedSuper(getSuper(), framesDisplayed); } else { return objc_getFramesDisplayed(this, framesDisplayed); }
    }
    
    private static final Selector framesPerSecond = Selector.register("framesPerSecond");
    @Bridge private native static int objc_getFramesPerSecond(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static int objc_getFramesPerSecondSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/framesPerSecond">@property(nonatomic, readonly) NSInteger framesPerSecond</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getFramesPerSecond() {
        if (customClass) { return objc_getFramesPerSecondSuper(getSuper(), framesPerSecond); } else { return objc_getFramesPerSecond(this, framesPerSecond); }
    }
    
    private static final Selector pauseOnWillResignActive = Selector.register("pauseOnWillResignActive");
    @Bridge private native static boolean objc_isPauseOnWillResignActive(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPauseOnWillResignActiveSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/pauseOnWillResignActive">@property(nonatomic) BOOL pauseOnWillResignActive</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isPauseOnWillResignActive() {
        if (customClass) { return objc_isPauseOnWillResignActiveSuper(getSuper(), pauseOnWillResignActive); } else { return objc_isPauseOnWillResignActive(this, pauseOnWillResignActive); }
    }
    
    private static final Selector setPauseOnWillResignActive$ = Selector.register("setPauseOnWillResignActive:");
    @Bridge private native static void objc_setPauseOnWillResignActive(GLKViewController __self__, Selector __cmd__, boolean pauseOnWillResignActive);
    @Bridge private native static void objc_setPauseOnWillResignActiveSuper(ObjCSuper __super__, Selector __cmd__, boolean pauseOnWillResignActive);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/pauseOnWillResignActive">@property(nonatomic) BOOL pauseOnWillResignActive</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPauseOnWillResignActive(boolean pauseOnWillResignActive) {
        if (customClass) { objc_setPauseOnWillResignActiveSuper(getSuper(), setPauseOnWillResignActive$, pauseOnWillResignActive); } else { objc_setPauseOnWillResignActive(this, setPauseOnWillResignActive$, pauseOnWillResignActive); }
    }
    
    private static final Selector isPaused = Selector.register("isPaused");
    @Bridge private native static boolean objc_isPaused(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPausedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/paused">@property(nonatomic, getter=isPaused) BOOL paused</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isPaused() {
        if (customClass) { return objc_isPausedSuper(getSuper(), isPaused); } else { return objc_isPaused(this, isPaused); }
    }
    
    private static final Selector setPaused$ = Selector.register("setPaused:");
    @Bridge private native static void objc_setPaused(GLKViewController __self__, Selector __cmd__, boolean paused);
    @Bridge private native static void objc_setPausedSuper(ObjCSuper __super__, Selector __cmd__, boolean paused);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/paused">@property(nonatomic, getter=isPaused) BOOL paused</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPaused(boolean paused) {
        if (customClass) { objc_setPausedSuper(getSuper(), setPaused$, paused); } else { objc_setPaused(this, setPaused$, paused); }
    }
    
    private static final Selector preferredFramesPerSecond = Selector.register("preferredFramesPerSecond");
    @Bridge private native static int objc_getPreferredFramesPerSecond(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static int objc_getPreferredFramesPerSecondSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/preferredFramesPerSecond">@property(nonatomic) NSInteger preferredFramesPerSecond</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getPreferredFramesPerSecond() {
        if (customClass) { return objc_getPreferredFramesPerSecondSuper(getSuper(), preferredFramesPerSecond); } else { return objc_getPreferredFramesPerSecond(this, preferredFramesPerSecond); }
    }
    
    private static final Selector setPreferredFramesPerSecond$ = Selector.register("setPreferredFramesPerSecond:");
    @Bridge private native static void objc_setPreferredFramesPerSecond(GLKViewController __self__, Selector __cmd__, int preferredFramesPerSecond);
    @Bridge private native static void objc_setPreferredFramesPerSecondSuper(ObjCSuper __super__, Selector __cmd__, int preferredFramesPerSecond);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/preferredFramesPerSecond">@property(nonatomic) NSInteger preferredFramesPerSecond</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPreferredFramesPerSecond(int preferredFramesPerSecond) {
        if (customClass) { objc_setPreferredFramesPerSecondSuper(getSuper(), setPreferredFramesPerSecond$, preferredFramesPerSecond); } else { objc_setPreferredFramesPerSecond(this, setPreferredFramesPerSecond$, preferredFramesPerSecond); }
    }
    
    private static final Selector resumeOnDidBecomeActive = Selector.register("resumeOnDidBecomeActive");
    @Bridge private native static boolean objc_isResumeOnDidBecomeActive(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isResumeOnDidBecomeActiveSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/resumeOnDidBecomeActive">@property(nonatomic) BOOL resumeOnDidBecomeActive</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isResumeOnDidBecomeActive() {
        if (customClass) { return objc_isResumeOnDidBecomeActiveSuper(getSuper(), resumeOnDidBecomeActive); } else { return objc_isResumeOnDidBecomeActive(this, resumeOnDidBecomeActive); }
    }
    
    private static final Selector setResumeOnDidBecomeActive$ = Selector.register("setResumeOnDidBecomeActive:");
    @Bridge private native static void objc_setResumeOnDidBecomeActive(GLKViewController __self__, Selector __cmd__, boolean resumeOnDidBecomeActive);
    @Bridge private native static void objc_setResumeOnDidBecomeActiveSuper(ObjCSuper __super__, Selector __cmd__, boolean resumeOnDidBecomeActive);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/resumeOnDidBecomeActive">@property(nonatomic) BOOL resumeOnDidBecomeActive</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setResumeOnDidBecomeActive(boolean resumeOnDidBecomeActive) {
        if (customClass) { objc_setResumeOnDidBecomeActiveSuper(getSuper(), setResumeOnDidBecomeActive$, resumeOnDidBecomeActive); } else { objc_setResumeOnDidBecomeActive(this, setResumeOnDidBecomeActive$, resumeOnDidBecomeActive); }
    }
    
    private static final Selector timeSinceFirstResume = Selector.register("timeSinceFirstResume");
    @Bridge private native static double objc_getTimeSinceFirstResume(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimeSinceFirstResumeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/timeSinceFirstResume">@property(nonatomic, readonly) NSTimeInterval timeSinceFirstResume</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getTimeSinceFirstResume() {
        if (customClass) { return objc_getTimeSinceFirstResumeSuper(getSuper(), timeSinceFirstResume); } else { return objc_getTimeSinceFirstResume(this, timeSinceFirstResume); }
    }
    
    private static final Selector timeSinceLastDraw = Selector.register("timeSinceLastDraw");
    @Bridge private native static double objc_getTimeSinceLastDraw(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimeSinceLastDrawSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/timeSinceLastDraw">@property(nonatomic, readonly) NSTimeInterval timeSinceLastDraw</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getTimeSinceLastDraw() {
        if (customClass) { return objc_getTimeSinceLastDrawSuper(getSuper(), timeSinceLastDraw); } else { return objc_getTimeSinceLastDraw(this, timeSinceLastDraw); }
    }
    
    private static final Selector timeSinceLastResume = Selector.register("timeSinceLastResume");
    @Bridge private native static double objc_getTimeSinceLastResume(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimeSinceLastResumeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/timeSinceLastResume">@property(nonatomic, readonly) NSTimeInterval timeSinceLastResume</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getTimeSinceLastResume() {
        if (customClass) { return objc_getTimeSinceLastResumeSuper(getSuper(), timeSinceLastResume); } else { return objc_getTimeSinceLastResume(this, timeSinceLastResume); }
    }
    
    private static final Selector timeSinceLastUpdate = Selector.register("timeSinceLastUpdate");
    @Bridge private native static double objc_getTimeSinceLastUpdate(GLKViewController __self__, Selector __cmd__);
    @Bridge private native static double objc_getTimeSinceLastUpdateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewController_ClassRef/Reference/Reference.html#//apple_ref/occ/instp/GLKViewController/timeSinceLastUpdate">@property(nonatomic, readonly) NSTimeInterval timeSinceLastUpdate</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getTimeSinceLastUpdate() {
        if (customClass) { return objc_getTimeSinceLastUpdateSuper(getSuper(), timeSinceLastUpdate); } else { return objc_getTimeSinceLastUpdate(this, timeSinceLastUpdate); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("delegate") public static GLKViewControllerDelegate getDelegate(GLKViewController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(GLKViewController __self__, Selector __cmd__, GLKViewControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("framesDisplayed") public static int getFramesDisplayed(GLKViewController __self__, Selector __cmd__) { return __self__.getFramesDisplayed(); }
        @Callback @BindSelector("framesPerSecond") public static int getFramesPerSecond(GLKViewController __self__, Selector __cmd__) { return __self__.getFramesPerSecond(); }
        @Callback @BindSelector("pauseOnWillResignActive") public static boolean isPauseOnWillResignActive(GLKViewController __self__, Selector __cmd__) { return __self__.isPauseOnWillResignActive(); }
        @Callback @BindSelector("setPauseOnWillResignActive:") public static void setPauseOnWillResignActive(GLKViewController __self__, Selector __cmd__, boolean pauseOnWillResignActive) { __self__.setPauseOnWillResignActive(pauseOnWillResignActive); }
        @Callback @BindSelector("isPaused") public static boolean isPaused(GLKViewController __self__, Selector __cmd__) { return __self__.isPaused(); }
        @Callback @BindSelector("setPaused:") public static void setPaused(GLKViewController __self__, Selector __cmd__, boolean paused) { __self__.setPaused(paused); }
        @Callback @BindSelector("preferredFramesPerSecond") public static int getPreferredFramesPerSecond(GLKViewController __self__, Selector __cmd__) { return __self__.getPreferredFramesPerSecond(); }
        @Callback @BindSelector("setPreferredFramesPerSecond:") public static void setPreferredFramesPerSecond(GLKViewController __self__, Selector __cmd__, int preferredFramesPerSecond) { __self__.setPreferredFramesPerSecond(preferredFramesPerSecond); }
        @Callback @BindSelector("resumeOnDidBecomeActive") public static boolean isResumeOnDidBecomeActive(GLKViewController __self__, Selector __cmd__) { return __self__.isResumeOnDidBecomeActive(); }
        @Callback @BindSelector("setResumeOnDidBecomeActive:") public static void setResumeOnDidBecomeActive(GLKViewController __self__, Selector __cmd__, boolean resumeOnDidBecomeActive) { __self__.setResumeOnDidBecomeActive(resumeOnDidBecomeActive); }
        @Callback @BindSelector("timeSinceFirstResume") public static double getTimeSinceFirstResume(GLKViewController __self__, Selector __cmd__) { return __self__.getTimeSinceFirstResume(); }
        @Callback @BindSelector("timeSinceLastDraw") public static double getTimeSinceLastDraw(GLKViewController __self__, Selector __cmd__) { return __self__.getTimeSinceLastDraw(); }
        @Callback @BindSelector("timeSinceLastResume") public static double getTimeSinceLastResume(GLKViewController __self__, Selector __cmd__) { return __self__.getTimeSinceLastResume(); }
        @Callback @BindSelector("timeSinceLastUpdate") public static double getTimeSinceLastUpdate(GLKViewController __self__, Selector __cmd__) { return __self__.getTimeSinceLastUpdate(); }
    }
    /*</callbacks>*/

}
