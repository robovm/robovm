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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html">UIPanGestureRecognizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPanGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPanGestureRecognizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPanGestureRecognizer /*</name>*/.class);

    /*<constructors>*/
    protected UIPanGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UIPanGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPanGestureRecognizer/maximumNumberOfTouches">@property(nonatomic) NSUInteger maximumNumberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("maximumNumberOfTouches") public native int getMaximumNumberOfTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPanGestureRecognizer/maximumNumberOfTouches">@property(nonatomic) NSUInteger maximumNumberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMaximumNumberOfTouches:") public native void setMaximumNumberOfTouches(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPanGestureRecognizer/minimumNumberOfTouches">@property(nonatomic) NSUInteger minimumNumberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("minimumNumberOfTouches") public native int getMinimumNumberOfTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instp/UIPanGestureRecognizer/minimumNumberOfTouches">@property(nonatomic) NSUInteger minimumNumberOfTouches</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMinimumNumberOfTouches:") public native void setMinimumNumberOfTouches(int v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector translationInView$ = Selector.register("translationInView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getTranslation(UIPanGestureRecognizer __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getTranslationSuper(ObjCSuper __super__, UIPanGestureRecognizer __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPanGestureRecognizer/translationInView:">- (CGPoint)translationInView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getTranslation(UIView view) {
        if (customClass) { return objc_getTranslationSuper(getSuper(), this, translationInView$, view); } else { return objc_getTranslation(this, translationInView$, view); }
    }
    
    private static final Selector velocityInView$ = Selector.register("velocityInView:");
    @Bridge(symbol = "objc_msgSend") private native static CGPoint objc_getVelocity(UIPanGestureRecognizer __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGPoint objc_getVelocitySuper(ObjCSuper __super__, UIPanGestureRecognizer __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPanGestureRecognizer/velocityInView:">- (CGPoint)velocityInView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGPoint getVelocity(UIView view) {
        if (customClass) { return objc_getVelocitySuper(getSuper(), this, velocityInView$, view); } else { return objc_getVelocity(this, velocityInView$, view); }
    }
    
    private static final Selector setTranslation$inView$ = Selector.register("setTranslation:inView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTranslation(UIPanGestureRecognizer __self__, Selector __cmd__, CGPoint translation, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTranslationSuper(ObjCSuper __super__, UIPanGestureRecognizer __self__, Selector __cmd__, CGPoint translation, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPanGestureRecognizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UIPanGestureRecognizer/setTranslation:inView:">- (void)setTranslation:(CGPoint)translation inView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setTranslation(CGPoint translation, UIView view) {
        if (customClass) { objc_setTranslationSuper(getSuper(), this, setTranslation$inView$, translation, view); } else { objc_setTranslation(this, setTranslation$inView$, translation, view); }
    }
    /*</methods>*/

}
