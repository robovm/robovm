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

    /*<constructors>*/
    public UIPageViewController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/initWithTransitionStyle:navigationOrientation:options:">- (id)initWithTransitionStyle:(UIPageViewControllerTransitionStyle)style navigationOrientation:(UIPageViewControllerNavigationOrientation)navigationOrientation options:(NSDictionary *)options</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithTransitionStyle:navigationOrientation:options:") public UIPageViewController(@Type("UIPageViewControllerTransitionStyle") UIPageViewControllerTransitionStyle style, @Type("UIPageViewControllerNavigationOrientation") UIPageViewControllerNavigationOrientation navigationOrientation, @Type("NSDictionary *") NSDictionary options) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("dataSource") public native @Type("id<UIPageViewControllerDataSource>") UIPageViewControllerDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UIPageViewControllerDataSource>") UIPageViewControllerDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIPageViewControllerDelegate>") UIPageViewControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPageViewControllerDelegate>") UIPageViewControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isDoubleSided") public native @Type("BOOL") boolean isDoubleSided();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDoubleSided:") public native void setDoubleSided(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/gestureRecognizers">@property(nonatomic, readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/navigationOrientation">@property(nonatomic, readonly) UIPageViewControllerNavigationOrientation navigationOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("navigationOrientation") public native @Type("UIPageViewControllerNavigationOrientation") UIPageViewControllerNavigationOrientation getNavigationOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/spineLocation">@property(nonatomic, readonly) UIPageViewControllerSpineLocation spineLocation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spineLocation") public native @Type("UIPageViewControllerSpineLocation") UIPageViewControllerSpineLocation getSpineLocation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/transitionStyle">@property(nonatomic, readonly) UIPageViewControllerTransitionStyle transitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("transitionStyle") public native @Type("UIPageViewControllerTransitionStyle") UIPageViewControllerTransitionStyle getTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/viewControllers">@property(nonatomic, readonly) NSArray *viewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("viewControllers") public native @Type("NSArray *") NSArray getViewControllers();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/setViewControllers:direction:animated:completion:">- (void)setViewControllers:(NSArray *)viewControllers direction:(UIPageViewControllerNavigationDirection)direction animated:(BOOL)animated completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setViewControllers:direction:animated:completion:") public native @Type("void") void setViewControllers(@Type("NSArray *") NSArray viewControllers, @Type("UIPageViewControllerNavigationDirection") UIPageViewControllerNavigationDirection direction, @Type("BOOL") boolean animated, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /*</methods>*/

}
