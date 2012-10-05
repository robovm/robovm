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
import org.robovm.objc.bind.*;
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("progress") public native float getProgress();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setProgress:") public native void setProgress(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("progressImage") public native UIImage getProgressImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProgressImage:") public native void setProgressImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("progressTintColor") public native UIColor getProgressTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProgressTintColor:") public native void setProgressTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("progressViewStyle") public native UIProgressViewStyle getProgressViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setProgressViewStyle:") public native void setProgressViewStyle(UIProgressViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("trackImage") public native UIImage getTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTrackImage:") public native void setTrackImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("trackTintColor") public native UIColor getTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTrackTintColor:") public native void setTrackTintColor(UIColor v);
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
