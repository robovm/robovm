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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html">UISplitViewController Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISplitViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISplitViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISplitViewController /*</name>*/.class);

    /*<constructors>*/
    protected UISplitViewController(SkipInit skipInit) { super(skipInit); }
    public UISplitViewController() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UISplitViewControllerDelegate objc_getDelegate(UISplitViewController __self__, Selector __cmd__);
    @Bridge private native static UISplitViewControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/delegate">@property(nonatomic, assign) id &amp;lt;UISplitViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UISplitViewControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UISplitViewController __self__, Selector __cmd__, UISplitViewControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UISplitViewControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/delegate">@property(nonatomic, assign) id &amp;lt;UISplitViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelegate(UISplitViewControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector presentsWithGesture = Selector.register("presentsWithGesture");
    @Bridge private native static boolean objc_isPresentsWithGesture(UISplitViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPresentsWithGestureSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/presentsWithGesture">@property (nonatomic) BOOL presentsWithGesture</a>
     * @since Available in iOS 5.1 and later.
     */
    public boolean isPresentsWithGesture() {
        if (customClass) { return objc_isPresentsWithGestureSuper(getSuper(), presentsWithGesture); } else { return objc_isPresentsWithGesture(this, presentsWithGesture); }
    }
    
    private static final Selector setPresentsWithGesture$ = Selector.register("setPresentsWithGesture:");
    @Bridge private native static void objc_setPresentsWithGesture(UISplitViewController __self__, Selector __cmd__, boolean presentsWithGesture);
    @Bridge private native static void objc_setPresentsWithGestureSuper(ObjCSuper __super__, Selector __cmd__, boolean presentsWithGesture);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/presentsWithGesture">@property (nonatomic) BOOL presentsWithGesture</a>
     * @since Available in iOS 5.1 and later.
     */
    public void setPresentsWithGesture(boolean presentsWithGesture) {
        if (customClass) { objc_setPresentsWithGestureSuper(getSuper(), setPresentsWithGesture$, presentsWithGesture); } else { objc_setPresentsWithGesture(this, setPresentsWithGesture$, presentsWithGesture); }
    }
    
    private static final Selector viewControllers = Selector.register("viewControllers");
    @Bridge private native static NSArray objc_getViewControllers(UISplitViewController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getViewControllersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getViewControllers() {
        if (customClass) { return objc_getViewControllersSuper(getSuper(), viewControllers); } else { return objc_getViewControllers(this, viewControllers); }
    }
    
    private static final Selector setViewControllers$ = Selector.register("setViewControllers:");
    @Bridge private native static void objc_setViewControllers(UISplitViewController __self__, Selector __cmd__, NSArray viewControllers);
    @Bridge private native static void objc_setViewControllersSuper(ObjCSuper __super__, Selector __cmd__, NSArray viewControllers);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setViewControllers(NSArray viewControllers) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), setViewControllers$, viewControllers); } else { objc_setViewControllers(this, setViewControllers$, viewControllers); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("delegate") public static UISplitViewControllerDelegate getDelegate(UISplitViewController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UISplitViewController __self__, Selector __cmd__, UISplitViewControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("presentsWithGesture") public static boolean isPresentsWithGesture(UISplitViewController __self__, Selector __cmd__) { return __self__.isPresentsWithGesture(); }
        @Callback @BindSelector("setPresentsWithGesture:") public static void setPresentsWithGesture(UISplitViewController __self__, Selector __cmd__, boolean presentsWithGesture) { __self__.setPresentsWithGesture(presentsWithGesture); }
        @Callback @BindSelector("viewControllers") public static NSArray getViewControllers(UISplitViewController __self__, Selector __cmd__) { return __self__.getViewControllers(); }
        @Callback @BindSelector("setViewControllers:") public static void setViewControllers(UISplitViewController __self__, Selector __cmd__, NSArray viewControllers) { __self__.setViewControllers(viewControllers); }
    }
    /*</callbacks>*/

}
