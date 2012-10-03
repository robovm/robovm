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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPageViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPageViewController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPageViewController() {}
    @Bind("initWithTransitionStyle:navigationOrientation:options:") public UIPageViewController(@Type("UIPageViewControllerTransitionStyle") UIPageViewControllerTransitionStyle style, @Type("UIPageViewControllerNavigationOrientation") UIPageViewControllerNavigationOrientation navigationOrientation, @Type("NSDictionary *") NSDictionary options) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("dataSource") public native @Type("id<UIPageViewControllerDataSource>") UIPageViewControllerDataSource getDataSource();
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UIPageViewControllerDataSource>") UIPageViewControllerDataSource v);
    @Bind("delegate") public native @Type("id<UIPageViewControllerDelegate>") UIPageViewControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPageViewControllerDelegate>") UIPageViewControllerDelegate v);
    @Bind("isDoubleSided") public native @Type("BOOL") boolean isDoubleSided();
    @Bind("setDoubleSided:") public native void setDoubleSided(@Type("BOOL") boolean v);
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    @Bind("navigationOrientation") public native @Type("UIPageViewControllerNavigationOrientation") UIPageViewControllerNavigationOrientation getNavigationOrientation();
    @Bind("spineLocation") public native @Type("UIPageViewControllerSpineLocation") UIPageViewControllerSpineLocation getSpineLocation();
    @Bind("transitionStyle") public native @Type("UIPageViewControllerTransitionStyle") UIPageViewControllerTransitionStyle getTransitionStyle();
    @Bind("viewControllers") public native @Type("NSArray *") NSArray getViewControllers();
    /*</properties>*/
    /*<methods>*/
    @Bind("setViewControllers:direction:animated:completion:") public native @Type("void") void setViewControllers(@Type("NSArray *") NSArray viewControllers, @Type("UIPageViewControllerNavigationDirection") UIPageViewControllerNavigationDirection direction, @Type("BOOL") boolean animated, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /*</methods>*/

}
