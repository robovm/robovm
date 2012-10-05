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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html">UIActivityIndicatorView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActivityIndicatorView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityIndicatorView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIActivityIndicatorView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/initWithActivityIndicatorStyle:">- (id)initWithActivityIndicatorStyle:(UIActivityIndicatorViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithActivityIndicatorStyle:") public UIActivityIndicatorView(@Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("activityIndicatorViewStyle") public native @Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle getActivityIndicatorViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setActivityIndicatorViewStyle:") public native void setActivityIndicatorViewStyle(@Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesWhenStopped") public native @Type("BOOL") boolean isHidesWhenStopped();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesWhenStopped:") public native void setHidesWhenStopped(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isAnimating") public native @Type("BOOL") boolean isAnimating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("startAnimating") public native @Type("void") void startAnimating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("stopAnimating") public native @Type("void") void stopAnimating();
    /*</methods>*/

}
