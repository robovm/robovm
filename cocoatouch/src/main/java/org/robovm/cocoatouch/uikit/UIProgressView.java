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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html">UIProgressView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIProgressView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIProgressView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIProgressView /*</name>*/.class);

    public UIProgressView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIProgressView(SkipInit skipInit) { super(skipInit); }
    public UIProgressView() {}
    
    private static final Selector initWithProgressViewStyle$ = Selector.register("initWithProgressViewStyle:");
    @Bridge private native static @Pointer long objc_initWithProgressViewStyle(UIProgressView __self__, Selector __cmd__, UIProgressViewStyle style);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/initWithProgressViewStyle:">- (id)initWithProgressViewStyle:(UIProgressViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIProgressView(UIProgressViewStyle style) {
        super((SkipInit) null);
        initObject(objc_initWithProgressViewStyle(this, initWithProgressViewStyle$, style));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector progress = Selector.register("progress");
    @Bridge private native static float objc_getProgress(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static float objc_getProgressSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getProgress() {
        if (customClass) { return objc_getProgressSuper(getSuper(), progress); } else { return objc_getProgress(this, progress); }
    }
    
    private static final Selector setProgress$ = Selector.register("setProgress:");
    @Bridge private native static void objc_setProgress(UIProgressView __self__, Selector __cmd__, float progress);
    @Bridge private native static void objc_setProgressSuper(ObjCSuper __super__, Selector __cmd__, float progress);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setProgress(float progress) {
        if (customClass) { objc_setProgressSuper(getSuper(), setProgress$, progress); } else { objc_setProgress(this, setProgress$, progress); }
    }
    
    private static final Selector progressImage = Selector.register("progressImage");
    @Bridge private native static UIImage objc_getProgressImage(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getProgressImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getProgressImage() {
        if (customClass) { return objc_getProgressImageSuper(getSuper(), progressImage); } else { return objc_getProgressImage(this, progressImage); }
    }
    
    private static final Selector setProgressImage$ = Selector.register("setProgressImage:");
    @Bridge private native static void objc_setProgressImage(UIProgressView __self__, Selector __cmd__, UIImage progressImage);
    @Bridge private native static void objc_setProgressImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage progressImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgressImage(UIImage progressImage) {
        if (customClass) { objc_setProgressImageSuper(getSuper(), setProgressImage$, progressImage); } else { objc_setProgressImage(this, setProgressImage$, progressImage); }
    }
    
    private static final Selector progressTintColor = Selector.register("progressTintColor");
    @Bridge private native static UIColor objc_getProgressTintColor(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getProgressTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getProgressTintColor() {
        if (customClass) { return objc_getProgressTintColorSuper(getSuper(), progressTintColor); } else { return objc_getProgressTintColor(this, progressTintColor); }
    }
    
    private static final Selector setProgressTintColor$ = Selector.register("setProgressTintColor:");
    @Bridge private native static void objc_setProgressTintColor(UIProgressView __self__, Selector __cmd__, UIColor progressTintColor);
    @Bridge private native static void objc_setProgressTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor progressTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgressTintColor(UIColor progressTintColor) {
        if (customClass) { objc_setProgressTintColorSuper(getSuper(), setProgressTintColor$, progressTintColor); } else { objc_setProgressTintColor(this, setProgressTintColor$, progressTintColor); }
    }
    
    private static final Selector progressViewStyle = Selector.register("progressViewStyle");
    @Bridge private native static UIProgressViewStyle objc_getProgressViewStyle(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static UIProgressViewStyle objc_getProgressViewStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIProgressViewStyle getProgressViewStyle() {
        if (customClass) { return objc_getProgressViewStyleSuper(getSuper(), progressViewStyle); } else { return objc_getProgressViewStyle(this, progressViewStyle); }
    }
    
    private static final Selector setProgressViewStyle$ = Selector.register("setProgressViewStyle:");
    @Bridge private native static void objc_setProgressViewStyle(UIProgressView __self__, Selector __cmd__, UIProgressViewStyle progressViewStyle);
    @Bridge private native static void objc_setProgressViewStyleSuper(ObjCSuper __super__, Selector __cmd__, UIProgressViewStyle progressViewStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setProgressViewStyle(UIProgressViewStyle progressViewStyle) {
        if (customClass) { objc_setProgressViewStyleSuper(getSuper(), setProgressViewStyle$, progressViewStyle); } else { objc_setProgressViewStyle(this, setProgressViewStyle$, progressViewStyle); }
    }
    
    private static final Selector trackImage = Selector.register("trackImage");
    @Bridge private native static UIImage objc_getTrackImage(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getTrackImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getTrackImage() {
        if (customClass) { return objc_getTrackImageSuper(getSuper(), trackImage); } else { return objc_getTrackImage(this, trackImage); }
    }
    
    private static final Selector setTrackImage$ = Selector.register("setTrackImage:");
    @Bridge private native static void objc_setTrackImage(UIProgressView __self__, Selector __cmd__, UIImage trackImage);
    @Bridge private native static void objc_setTrackImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage trackImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTrackImage(UIImage trackImage) {
        if (customClass) { objc_setTrackImageSuper(getSuper(), setTrackImage$, trackImage); } else { objc_setTrackImage(this, setTrackImage$, trackImage); }
    }
    
    private static final Selector trackTintColor = Selector.register("trackTintColor");
    @Bridge private native static UIColor objc_getTrackTintColor(UIProgressView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTrackTintColor() {
        if (customClass) { return objc_getTrackTintColorSuper(getSuper(), trackTintColor); } else { return objc_getTrackTintColor(this, trackTintColor); }
    }
    
    private static final Selector setTrackTintColor$ = Selector.register("setTrackTintColor:");
    @Bridge private native static void objc_setTrackTintColor(UIProgressView __self__, Selector __cmd__, UIColor trackTintColor);
    @Bridge private native static void objc_setTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor trackTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTrackTintColor(UIColor trackTintColor) {
        if (customClass) { objc_setTrackTintColorSuper(getSuper(), setTrackTintColor$, trackTintColor); } else { objc_setTrackTintColor(this, setTrackTintColor$, trackTintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setProgress$animated$ = Selector.register("setProgress:animated:");
    @Bridge private native static void objc_setProgress(UIProgressView __self__, Selector __cmd__, float progress, boolean animated);
    @Bridge private native static void objc_setProgressSuper(ObjCSuper __super__, Selector __cmd__, float progress, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/setProgress:animated:">- (void)setProgress:(float)progress animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgress(float progress, boolean animated) {
        if (customClass) { objc_setProgressSuper(getSuper(), setProgress$animated$, progress, animated); } else { objc_setProgress(this, setProgress$animated$, progress, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("progress") public static float getProgress(UIProgressView __self__, Selector __cmd__) { return __self__.getProgress(); }
        @Callback @BindSelector("setProgress:") public static void setProgress(UIProgressView __self__, Selector __cmd__, float progress) { __self__.setProgress(progress); }
        @Callback @BindSelector("progressImage") public static UIImage getProgressImage(UIProgressView __self__, Selector __cmd__) { return __self__.getProgressImage(); }
        @Callback @BindSelector("setProgressImage:") public static void setProgressImage(UIProgressView __self__, Selector __cmd__, UIImage progressImage) { __self__.setProgressImage(progressImage); }
        @Callback @BindSelector("progressTintColor") public static UIColor getProgressTintColor(UIProgressView __self__, Selector __cmd__) { return __self__.getProgressTintColor(); }
        @Callback @BindSelector("setProgressTintColor:") public static void setProgressTintColor(UIProgressView __self__, Selector __cmd__, UIColor progressTintColor) { __self__.setProgressTintColor(progressTintColor); }
        @Callback @BindSelector("progressViewStyle") public static UIProgressViewStyle getProgressViewStyle(UIProgressView __self__, Selector __cmd__) { return __self__.getProgressViewStyle(); }
        @Callback @BindSelector("setProgressViewStyle:") public static void setProgressViewStyle(UIProgressView __self__, Selector __cmd__, UIProgressViewStyle progressViewStyle) { __self__.setProgressViewStyle(progressViewStyle); }
        @Callback @BindSelector("trackImage") public static UIImage getTrackImage(UIProgressView __self__, Selector __cmd__) { return __self__.getTrackImage(); }
        @Callback @BindSelector("setTrackImage:") public static void setTrackImage(UIProgressView __self__, Selector __cmd__, UIImage trackImage) { __self__.setTrackImage(trackImage); }
        @Callback @BindSelector("trackTintColor") public static UIColor getTrackTintColor(UIProgressView __self__, Selector __cmd__) { return __self__.getTrackTintColor(); }
        @Callback @BindSelector("setTrackTintColor:") public static void setTrackTintColor(UIProgressView __self__, Selector __cmd__, UIColor trackTintColor) { __self__.setTrackTintColor(trackTintColor); }
        @Callback @BindSelector("setProgress:animated:") public static void setProgress(UIProgressView __self__, Selector __cmd__, float progress, boolean animated) { __self__.setProgress(progress, animated); }
    }
    /*</callbacks>*/

}
