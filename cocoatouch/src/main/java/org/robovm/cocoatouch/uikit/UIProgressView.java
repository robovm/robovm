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

    /*<constructors>*/
    public UIProgressView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/initWithProgressViewStyle:">- (id)initWithProgressViewStyle:(UIProgressViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithProgressViewStyle:") public UIProgressView(@Type("UIProgressViewStyle") UIProgressViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("progress") public native @Type("float") float getProgress();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progress">@property(nonatomic) float progress</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setProgress:") public native void setProgress(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("progressImage") public native @Type("UIImage *") UIImage getProgressImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressImage">@property(nonatomic, retain) UIImage *progressImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProgressImage:") public native void setProgressImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("progressTintColor") public native @Type("UIColor *") UIColor getProgressTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressTintColor">@property(nonatomic, retain) UIColor *progressTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProgressTintColor:") public native void setProgressTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("progressViewStyle") public native @Type("UIProgressViewStyle") UIProgressViewStyle getProgressViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/progressViewStyle">@property(nonatomic) UIProgressViewStyle progressViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setProgressViewStyle:") public native void setProgressViewStyle(@Type("UIProgressViewStyle") UIProgressViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("trackImage") public native @Type("UIImage *") UIImage getTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackImage">@property(nonatomic, retain) UIImage *trackImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTrackImage:") public native void setTrackImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("trackTintColor") public native @Type("UIColor *") UIColor getTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIProgressView/trackTintColor">@property(nonatomic, retain) UIColor *trackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTrackTintColor:") public native void setTrackTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIProgressView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIProgressView/setProgress:animated:">- (void)setProgress:(float)progress animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProgress:animated:") public native @Type("void") void setProgress(@Type("float") float progress, @Type("BOOL") boolean animated);
    /*</methods>*/

}
