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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActivityIndicatorView /*</name>*/.class);

    /*<constructors>*/
    protected UIActivityIndicatorView(SkipInit skipInit) { super(skipInit); }
    public UIActivityIndicatorView() {}
    
    private static final Selector initWithActivityIndicatorStyle$ = Selector.register("initWithActivityIndicatorStyle:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithActivityIndicatorStyle(UIActivityIndicatorView __self__, Selector __cmd__, UIActivityIndicatorViewStyle style);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/initWithActivityIndicatorStyle:">- (id)initWithActivityIndicatorStyle:(UIActivityIndicatorViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIActivityIndicatorView(UIActivityIndicatorViewStyle style) {
        super((SkipInit) null);
        objc_initWithActivityIndicatorStyle(this, initWithActivityIndicatorStyle$, style);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("activityIndicatorViewStyle") public native UIActivityIndicatorViewStyle getActivityIndicatorViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/activityIndicatorViewStyle">@property UIActivityIndicatorViewStyle activityIndicatorViewStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setActivityIndicatorViewStyle:") public native void setActivityIndicatorViewStyle(UIActivityIndicatorViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("color") public native UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/color">@property (readwrite, nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setColor:") public native void setColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesWhenStopped") public native boolean isHidesWhenStopped();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instp/UIActivityIndicatorView/hidesWhenStopped">@property BOOL hidesWhenStopped</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesWhenStopped:") public native void setHidesWhenStopped(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector isAnimating = Selector.register("isAnimating");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAnimatingSuper(ObjCSuper __super__, UIActivityIndicatorView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/isAnimating">- (BOOL)isAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAnimating() {
        if (customClass) { return objc_isAnimatingSuper(getSuper(), this, isAnimating); } else { return objc_isAnimating(this, isAnimating); }
    }
    
    private static final Selector startAnimating = Selector.register("startAnimating");
    @Bridge(symbol = "objc_msgSend") private native static void objc_startAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_startAnimatingSuper(ObjCSuper __super__, UIActivityIndicatorView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/startAnimating">- (void)startAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void startAnimating() {
        if (customClass) { objc_startAnimatingSuper(getSuper(), this, startAnimating); } else { objc_startAnimating(this, startAnimating); }
    }
    
    private static final Selector stopAnimating = Selector.register("stopAnimating");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stopAnimating(UIActivityIndicatorView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_stopAnimatingSuper(ObjCSuper __super__, UIActivityIndicatorView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityIndicatorView_Class/Reference/UIActivityIndicatorView.html#//apple_ref/occ/instm/UIActivityIndicatorView/stopAnimating">- (void)stopAnimating</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopAnimating() {
        if (customClass) { objc_stopAnimatingSuper(getSuper(), this, stopAnimating); } else { objc_stopAnimating(this, stopAnimating); }
    }
    /*</methods>*/

}
