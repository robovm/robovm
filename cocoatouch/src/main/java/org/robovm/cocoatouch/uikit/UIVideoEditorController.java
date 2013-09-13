/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
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
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html">UIVideoEditorController Class Reference</a>
 *   @since Available in iOS 3.1 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIVideoEditorController /*</name>*/ 
    extends /*<extends>*/ UINavigationController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIVideoEditorController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIVideoEditorController /*</name>*/.class);

    /*<constructors>*/
    protected UIVideoEditorController(SkipInit skipInit) { super(skipInit); }
    public UIVideoEditorController() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIVideoEditorControllerDelegate objc_getDelegate(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge private native static UIVideoEditorControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIVideoEditorControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIVideoEditorController __self__, Selector __cmd__, UIVideoEditorControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIVideoEditorControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setDelegate(UIVideoEditorControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector videoMaximumDuration = Selector.register("videoMaximumDuration");
    @Bridge private native static double objc_getVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge private native static double objc_getVideoMaximumDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public double getVideoMaximumDuration() {
        if (customClass) { return objc_getVideoMaximumDurationSuper(getSuper(), videoMaximumDuration); } else { return objc_getVideoMaximumDuration(this, videoMaximumDuration); }
    }
    
    private static final Selector setVideoMaximumDuration$ = Selector.register("setVideoMaximumDuration:");
    @Bridge private native static void objc_setVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__, double videoMaximumDuration);
    @Bridge private native static void objc_setVideoMaximumDurationSuper(ObjCSuper __super__, Selector __cmd__, double videoMaximumDuration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoMaximumDuration(double videoMaximumDuration) {
        if (customClass) { objc_setVideoMaximumDurationSuper(getSuper(), setVideoMaximumDuration$, videoMaximumDuration); } else { objc_setVideoMaximumDuration(this, setVideoMaximumDuration$, videoMaximumDuration); }
    }
    
    private static final Selector videoPath = Selector.register("videoPath");
    @Bridge private native static String objc_getVideoPath(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge private native static String objc_getVideoPathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public String getVideoPath() {
        if (customClass) { return objc_getVideoPathSuper(getSuper(), videoPath); } else { return objc_getVideoPath(this, videoPath); }
    }
    
    private static final Selector setVideoPath$ = Selector.register("setVideoPath:");
    @Bridge private native static void objc_setVideoPath(UIVideoEditorController __self__, Selector __cmd__, String videoPath);
    @Bridge private native static void objc_setVideoPathSuper(ObjCSuper __super__, Selector __cmd__, String videoPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoPath(String videoPath) {
        if (customClass) { objc_setVideoPathSuper(getSuper(), setVideoPath$, videoPath); } else { objc_setVideoPath(this, setVideoPath$, videoPath); }
    }
    
    private static final Selector videoQuality = Selector.register("videoQuality");
    @Bridge private native static UIImagePickerControllerQualityType objc_getVideoQuality(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerQualityType objc_getVideoQualitySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIImagePickerControllerQualityType getVideoQuality() {
        if (customClass) { return objc_getVideoQualitySuper(getSuper(), videoQuality); } else { return objc_getVideoQuality(this, videoQuality); }
    }
    
    private static final Selector setVideoQuality$ = Selector.register("setVideoQuality:");
    @Bridge private native static void objc_setVideoQuality(UIVideoEditorController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    @Bridge private native static void objc_setVideoQualitySuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoQuality(UIImagePickerControllerQualityType videoQuality) {
        if (customClass) { objc_setVideoQualitySuper(getSuper(), setVideoQuality$, videoQuality); } else { objc_setVideoQuality(this, setVideoQuality$, videoQuality); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector canEditVideoAtPath$ = Selector.register("canEditVideoAtPath:");
    @Bridge private native static boolean objc_canEditVideo(ObjCClass __self__, Selector __cmd__, String videoPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/clm/UIVideoEditorController/canEditVideoAtPath:">+ (BOOL)canEditVideoAtPath:(NSString *)videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public static boolean canEditVideo(String videoPath) {
        return objc_canEditVideo(objCClass, canEditVideoAtPath$, videoPath);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("delegate") public static UIVideoEditorControllerDelegate getDelegate(UIVideoEditorController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIVideoEditorController __self__, Selector __cmd__, UIVideoEditorControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("videoMaximumDuration") public static double getVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__) { return __self__.getVideoMaximumDuration(); }
        @Callback @BindSelector("setVideoMaximumDuration:") public static void setVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__, double videoMaximumDuration) { __self__.setVideoMaximumDuration(videoMaximumDuration); }
        @Callback @BindSelector("videoPath") public static String getVideoPath(UIVideoEditorController __self__, Selector __cmd__) { return __self__.getVideoPath(); }
        @Callback @BindSelector("setVideoPath:") public static void setVideoPath(UIVideoEditorController __self__, Selector __cmd__, String videoPath) { __self__.setVideoPath(videoPath); }
        @Callback @BindSelector("videoQuality") public static UIImagePickerControllerQualityType getVideoQuality(UIVideoEditorController __self__, Selector __cmd__) { return __self__.getVideoQuality(); }
        @Callback @BindSelector("setVideoQuality:") public static void setVideoQuality(UIVideoEditorController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality) { __self__.setVideoQuality(videoQuality); }
    }
    /*</callbacks>*/

}
