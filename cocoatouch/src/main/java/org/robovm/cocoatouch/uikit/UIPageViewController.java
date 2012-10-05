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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html">UIPageViewController Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPageViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPageViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPageViewController /*</name>*/.class);

    /*<constructors>*/
    protected UIPageViewController(SkipInit skipInit) { super(skipInit); }
    public UIPageViewController() {}
    
    private static final Selector initWithTransitionStyle$navigationOrientation$options$ = Selector.register("initWithTransitionStyle:navigationOrientation:options:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTransitionStyle(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, NSDictionary options);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/initWithTransitionStyle:navigationOrientation:options:">- (id)initWithTransitionStyle:(UIPageViewControllerTransitionStyle)style navigationOrientation:(UIPageViewControllerNavigationOrientation)navigationOrientation options:(NSDictionary *)options</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewController(UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, NSDictionary options) {
        super((SkipInit) null);
        objc_initWithTransitionStyle(this, initWithTransitionStyle$navigationOrientation$options$, style, navigationOrientation, options);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("dataSource") public native UIPageViewControllerDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(UIPageViewControllerDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("delegate") public native UIPageViewControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIPageViewControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isDoubleSided") public native boolean isDoubleSided();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDoubleSided:") public native void setDoubleSided(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/gestureRecognizers">@property(nonatomic, readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("gestureRecognizers") public native NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/navigationOrientation">@property(nonatomic, readonly) UIPageViewControllerNavigationOrientation navigationOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("navigationOrientation") public native UIPageViewControllerNavigationOrientation getNavigationOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/spineLocation">@property(nonatomic, readonly) UIPageViewControllerSpineLocation spineLocation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spineLocation") public native UIPageViewControllerSpineLocation getSpineLocation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/transitionStyle">@property(nonatomic, readonly) UIPageViewControllerTransitionStyle transitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("transitionStyle") public native UIPageViewControllerTransitionStyle getTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/viewControllers">@property(nonatomic, readonly) NSArray *viewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("viewControllers") public native NSArray getViewControllers();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setViewControllers$direction$animated$completion$ = Selector.register("setViewControllers:direction:animated:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setViewControllers(UIPageViewController __self__, Selector __cmd__, NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setViewControllersSuper(ObjCSuper __super__, UIPageViewController __self__, Selector __cmd__, NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/setViewControllers:direction:animated:completion:">- (void)setViewControllers:(NSArray *)viewControllers direction:(UIPageViewControllerNavigationDirection)direction animated:(BOOL)animated completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setViewControllers(NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), this, setViewControllers$direction$animated$completion$, viewControllers, direction, animated, completion); } else { objc_setViewControllers(this, setViewControllers$direction$animated$completion$, viewControllers, direction, animated, completion); }
    }
    /*</methods>*/

}
