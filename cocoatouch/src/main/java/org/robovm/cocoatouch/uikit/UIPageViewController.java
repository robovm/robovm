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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html">UIPageViewController Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPageViewController /*</name>*/ 
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
    @Bridge private native static @Pointer long objc_initWithTransitionStyle(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, NSDictionary options);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/initWithTransitionStyle:navigationOrientation:options:">- (id)initWithTransitionStyle:(UIPageViewControllerTransitionStyle)style navigationOrientation:(UIPageViewControllerNavigationOrientation)navigationOrientation options:(NSDictionary *)options</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewController(UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, NSDictionary options) {
        super((SkipInit) null);
        initObject(objc_initWithTransitionStyle(this, initWithTransitionStyle$navigationOrientation$options$, style, navigationOrientation, options));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector dataSource = Selector.register("dataSource");
    @Bridge private native static UIPageViewControllerDataSource objc_getDataSource(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static UIPageViewControllerDataSource objc_getDataSourceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewControllerDataSource getDataSource() {
        if (customClass) { return objc_getDataSourceSuper(getSuper(), dataSource); } else { return objc_getDataSource(this, dataSource); }
    }
    
    private static final Selector setDataSource$ = Selector.register("setDataSource:");
    @Bridge private native static void objc_setDataSource(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerDataSource dataSource);
    @Bridge private native static void objc_setDataSourceSuper(ObjCSuper __super__, Selector __cmd__, UIPageViewControllerDataSource dataSource);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/dataSource">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDataSource(UIPageViewControllerDataSource dataSource) {
        if (customClass) { objc_setDataSourceSuper(getSuper(), setDataSource$, dataSource); } else { objc_setDataSource(this, setDataSource$, dataSource); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIPageViewControllerDelegate objc_getDelegate(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static UIPageViewControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIPageViewControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/delegate">@property(nonatomic, assign) id&amp;lt;UIPageViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDelegate(UIPageViewControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isDoubleSided = Selector.register("isDoubleSided");
    @Bridge private native static boolean objc_isDoubleSided(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDoubleSidedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isDoubleSided() {
        if (customClass) { return objc_isDoubleSidedSuper(getSuper(), isDoubleSided); } else { return objc_isDoubleSided(this, isDoubleSided); }
    }
    
    private static final Selector setDoubleSided$ = Selector.register("setDoubleSided:");
    @Bridge private native static void objc_setDoubleSided(UIPageViewController __self__, Selector __cmd__, boolean doubleSided);
    @Bridge private native static void objc_setDoubleSidedSuper(ObjCSuper __super__, Selector __cmd__, boolean doubleSided);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/doubleSided">@property(nonatomic, getter=isDoubleSided) BOOL doubleSided</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDoubleSided(boolean doubleSided) {
        if (customClass) { objc_setDoubleSidedSuper(getSuper(), setDoubleSided$, doubleSided); } else { objc_setDoubleSided(this, setDoubleSided$, doubleSided); }
    }
    
    private static final Selector gestureRecognizers = Selector.register("gestureRecognizers");
    @Bridge private native static NSArray objc_getGestureRecognizers(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getGestureRecognizersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/gestureRecognizers">@property(nonatomic, readonly) NSArray *gestureRecognizers</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getGestureRecognizers() {
        if (customClass) { return objc_getGestureRecognizersSuper(getSuper(), gestureRecognizers); } else { return objc_getGestureRecognizers(this, gestureRecognizers); }
    }
    
    private static final Selector navigationOrientation = Selector.register("navigationOrientation");
    @Bridge private native static UIPageViewControllerNavigationOrientation objc_getNavigationOrientation(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static UIPageViewControllerNavigationOrientation objc_getNavigationOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/navigationOrientation">@property(nonatomic, readonly) UIPageViewControllerNavigationOrientation navigationOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewControllerNavigationOrientation getNavigationOrientation() {
        if (customClass) { return objc_getNavigationOrientationSuper(getSuper(), navigationOrientation); } else { return objc_getNavigationOrientation(this, navigationOrientation); }
    }
    
    private static final Selector spineLocation = Selector.register("spineLocation");
    @Bridge private native static UIPageViewControllerSpineLocation objc_getSpineLocation(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static UIPageViewControllerSpineLocation objc_getSpineLocationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/spineLocation">@property(nonatomic, readonly) UIPageViewControllerSpineLocation spineLocation</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewControllerSpineLocation getSpineLocation() {
        if (customClass) { return objc_getSpineLocationSuper(getSuper(), spineLocation); } else { return objc_getSpineLocation(this, spineLocation); }
    }
    
    private static final Selector transitionStyle = Selector.register("transitionStyle");
    @Bridge private native static UIPageViewControllerTransitionStyle objc_getTransitionStyle(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static UIPageViewControllerTransitionStyle objc_getTransitionStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/transitionStyle">@property(nonatomic, readonly) UIPageViewControllerTransitionStyle transitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPageViewControllerTransitionStyle getTransitionStyle() {
        if (customClass) { return objc_getTransitionStyleSuper(getSuper(), transitionStyle); } else { return objc_getTransitionStyle(this, transitionStyle); }
    }
    
    private static final Selector viewControllers = Selector.register("viewControllers");
    @Bridge private native static NSArray objc_getViewControllers(UIPageViewController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getViewControllersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instp/UIPageViewController/viewControllers">@property(nonatomic, readonly) NSArray *viewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getViewControllers() {
        if (customClass) { return objc_getViewControllersSuper(getSuper(), viewControllers); } else { return objc_getViewControllers(this, viewControllers); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setViewControllers$direction$animated$completion$ = Selector.register("setViewControllers:direction:animated:completion:");
    @Bridge private native static void objc_setViewControllers(UIPageViewController __self__, Selector __cmd__, NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion);
    @Bridge private native static void objc_setViewControllersSuper(ObjCSuper __super__, Selector __cmd__, NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerClassReferenceClassRef/UIPageViewControllerClassReference.html#//apple_ref/occ/instm/UIPageViewController/setViewControllers:direction:animated:completion:">- (void)setViewControllers:(NSArray *)viewControllers direction:(UIPageViewControllerNavigationDirection)direction animated:(BOOL)animated completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setViewControllers(NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), setViewControllers$direction$animated$completion$, viewControllers, direction, animated, completion); } else { objc_setViewControllers(this, setViewControllers$direction$animated$completion$, viewControllers, direction, animated, completion); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("dataSource") public static UIPageViewControllerDataSource getDataSource(UIPageViewController __self__, Selector __cmd__) { return __self__.getDataSource(); }
        @Callback @BindSelector("setDataSource:") public static void setDataSource(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerDataSource dataSource) { __self__.setDataSource(dataSource); }
        @Callback @BindSelector("delegate") public static UIPageViewControllerDelegate getDelegate(UIPageViewController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIPageViewController __self__, Selector __cmd__, UIPageViewControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isDoubleSided") public static boolean isDoubleSided(UIPageViewController __self__, Selector __cmd__) { return __self__.isDoubleSided(); }
        @Callback @BindSelector("setDoubleSided:") public static void setDoubleSided(UIPageViewController __self__, Selector __cmd__, boolean doubleSided) { __self__.setDoubleSided(doubleSided); }
        @Callback @BindSelector("gestureRecognizers") public static NSArray getGestureRecognizers(UIPageViewController __self__, Selector __cmd__) { return __self__.getGestureRecognizers(); }
        @Callback @BindSelector("navigationOrientation") public static UIPageViewControllerNavigationOrientation getNavigationOrientation(UIPageViewController __self__, Selector __cmd__) { return __self__.getNavigationOrientation(); }
        @Callback @BindSelector("spineLocation") public static UIPageViewControllerSpineLocation getSpineLocation(UIPageViewController __self__, Selector __cmd__) { return __self__.getSpineLocation(); }
        @Callback @BindSelector("transitionStyle") public static UIPageViewControllerTransitionStyle getTransitionStyle(UIPageViewController __self__, Selector __cmd__) { return __self__.getTransitionStyle(); }
        @Callback @BindSelector("viewControllers") public static NSArray getViewControllers(UIPageViewController __self__, Selector __cmd__) { return __self__.getViewControllers(); }
        @Callback @BindSelector("setViewControllers:direction:animated:completion:") public static void setViewControllers(UIPageViewController __self__, Selector __cmd__, NSArray viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, VoidBooleanBlock completion) { __self__.setViewControllers(viewControllers, direction, animated, completion); }
    }
    /*</callbacks>*/

}
