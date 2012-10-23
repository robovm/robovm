/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html">UIProgressView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIProgressView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIProgressView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIProgressView /*</name>*/.class);

    /*<constructors>*/
    protected UIProgressView(SkipInit skipInit) { super(skipInit); }
    public UIProgressView() {}
    
    private static final Selector initWithProgressViewStyle$ = Selector.register("initWithProgressViewStyle:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithProgressViewStyle(UIProgressView __self__, Selector __cmd__, UIProgressViewStyle style);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/initWithProgressViewStyle:">- (id)initWithProgressViewStyle:(UIProgressViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIProgressView(UIProgressViewStyle style) {
        super((SkipInit) null);
        objc_initWithProgressViewStyle(this, initWithProgressViewStyle$, style);
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector progress = Selector.register("progress");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getProgress(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getProgressSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getProgress() {
        if (customClass) { return objc_getProgressSuper(getSuper(), this, progress); } else { return objc_getProgress(this, progress); }
    }
    
    private static final Selector setProgress$ = Selector.register("setProgress:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setProgress(UIProgressView __self__, Selector __cmd__, float progress);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setProgressSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, float progress);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setProgress(float progress) {
        if (customClass) { objc_setProgressSuper(getSuper(), this, setProgress$, progress); } else { objc_setProgress(this, setProgress$, progress); }
    }
    
    private static final Selector progressImage = Selector.register("progressImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getProgressImage(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getProgressImageSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getProgressImage() {
        if (customClass) { return objc_getProgressImageSuper(getSuper(), this, progressImage); } else { return objc_getProgressImage(this, progressImage); }
    }
    
    private static final Selector setProgressImage$ = Selector.register("setProgressImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setProgressImage(UIProgressView __self__, Selector __cmd__, UIImage progressImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setProgressImageSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, UIImage progressImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgressImage(UIImage progressImage) {
        if (customClass) { objc_setProgressImageSuper(getSuper(), this, setProgressImage$, progressImage); } else { objc_setProgressImage(this, setProgressImage$, progressImage); }
    }
    
    private static final Selector progressTintColor = Selector.register("progressTintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getProgressTintColor(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getProgressTintColorSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getProgressTintColor() {
        if (customClass) { return objc_getProgressTintColorSuper(getSuper(), this, progressTintColor); } else { return objc_getProgressTintColor(this, progressTintColor); }
    }
    
    private static final Selector setProgressTintColor$ = Selector.register("setProgressTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setProgressTintColor(UIProgressView __self__, Selector __cmd__, UIColor progressTintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setProgressTintColorSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, UIColor progressTintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgressTintColor(UIColor progressTintColor) {
        if (customClass) { objc_setProgressTintColorSuper(getSuper(), this, setProgressTintColor$, progressTintColor); } else { objc_setProgressTintColor(this, setProgressTintColor$, progressTintColor); }
    }
    
    private static final Selector progressViewStyle = Selector.register("progressViewStyle");
    @Bridge(symbol = "objc_msgSend") private native static UIProgressViewStyle objc_getProgressViewStyle(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIProgressViewStyle objc_getProgressViewStyleSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIProgressViewStyle getProgressViewStyle() {
        if (customClass) { return objc_getProgressViewStyleSuper(getSuper(), this, progressViewStyle); } else { return objc_getProgressViewStyle(this, progressViewStyle); }
    }
    
    private static final Selector setProgressViewStyle$ = Selector.register("setProgressViewStyle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setProgressViewStyle(UIProgressView __self__, Selector __cmd__, UIProgressViewStyle progressViewStyle);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setProgressViewStyleSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, UIProgressViewStyle progressViewStyle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setProgressViewStyle(UIProgressViewStyle progressViewStyle) {
        if (customClass) { objc_setProgressViewStyleSuper(getSuper(), this, setProgressViewStyle$, progressViewStyle); } else { objc_setProgressViewStyle(this, setProgressViewStyle$, progressViewStyle); }
    }
    
    private static final Selector trackImage = Selector.register("trackImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getTrackImage(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getTrackImageSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getTrackImage() {
        if (customClass) { return objc_getTrackImageSuper(getSuper(), this, trackImage); } else { return objc_getTrackImage(this, trackImage); }
    }
    
    private static final Selector setTrackImage$ = Selector.register("setTrackImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTrackImage(UIProgressView __self__, Selector __cmd__, UIImage trackImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTrackImageSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, UIImage trackImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTrackImage(UIImage trackImage) {
        if (customClass) { objc_setTrackImageSuper(getSuper(), this, setTrackImage$, trackImage); } else { objc_setTrackImage(this, setTrackImage$, trackImage); }
    }
    
    private static final Selector trackTintColor = Selector.register("trackTintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getTrackTintColor(UIProgressView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getTrackTintColorSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTrackTintColor() {
        if (customClass) { return objc_getTrackTintColorSuper(getSuper(), this, trackTintColor); } else { return objc_getTrackTintColor(this, trackTintColor); }
    }
    
    private static final Selector setTrackTintColor$ = Selector.register("setTrackTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTrackTintColor(UIProgressView __self__, Selector __cmd__, UIColor trackTintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTrackTintColorSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, UIColor trackTintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTrackTintColor(UIColor trackTintColor) {
        if (customClass) { objc_setTrackTintColorSuper(getSuper(), this, setTrackTintColor$, trackTintColor); } else { objc_setTrackTintColor(this, setTrackTintColor$, trackTintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setProgress$animated$ = Selector.register("setProgress:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setProgress(UIProgressView __self__, Selector __cmd__, float progress, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setProgressSuper(ObjCSuper __super__, UIProgressView __self__, Selector __cmd__, float progress, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/setProgress:animated:">- (void)setProgress:(float)progress animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProgress(float progress, boolean animated) {
        if (customClass) { objc_setProgressSuper(getSuper(), this, setProgress$animated$, progress, animated); } else { objc_setProgress(this, setProgress$animated$, progress, animated); }
    }
    /*</methods>*/

}
