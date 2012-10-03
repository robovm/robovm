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
public class /*<name>*/ UIPopoverController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPopoverController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPopoverController() {}
    @Bind("initWithContentViewController:") public UIPopoverController(@Type("UIViewController *") UIViewController viewController) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("contentViewController") public native @Type("UIViewController *") UIViewController getContentViewController();
    @Bind("setContentViewController:") public native void setContentViewController(@Type("UIViewController *") UIViewController v);
    @Bind("delegate") public native @Type("id <UIPopoverControllerDelegate>") UIPopoverControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id <UIPopoverControllerDelegate>") UIPopoverControllerDelegate v);
    @Bind("passthroughViews") public native @Type("NSArray *") NSArray getPassthroughViews();
    @Bind("setPassthroughViews:") public native void setPassthroughViews(@Type("NSArray *") NSArray v);
    @Bind("popoverArrowDirection") public native @Type("UIPopoverArrowDirection") UIPopoverArrowDirection getPopoverArrowDirection();
    @Bind("popoverBackgroundViewClass") public native @Type("Class") ObjCClass getPopoverBackgroundViewClass();
    @Bind("setPopoverBackgroundViewClass:") public native void setPopoverBackgroundViewClass(@Type("Class") ObjCClass v);
    @Bind("popoverContentSize") public native @Type("CGSize") CGSize getPopoverContentSize();
    @Bind("setPopoverContentSize:") public native void setPopoverContentSize(@Type("CGSize") CGSize v);
    @Bind("popoverLayoutMargins") public native @Type("UIEdgeInsets") UIEdgeInsets getPopoverLayoutMargins();
    @Bind("setPopoverLayoutMargins:") public native void setPopoverLayoutMargins(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("isPopoverVisible") public native @Type("BOOL") boolean isPopoverVisible();
    /*</properties>*/
    /*<methods>*/
    @Bind("dismissPopoverAnimated:") public native @Type("void") void dismiss(@Type("BOOL") boolean animated);
    @Bind("presentPopoverFromBarButtonItem:permittedArrowDirections:animated:") public native @Type("void") void presentFromBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("UIPopoverArrowDirection") EnumSet<UIPopoverArrowDirection> arrowDirections, @Type("BOOL") boolean animated);
    @Bind("presentPopoverFromRect:inView:permittedArrowDirections:animated:") public native @Type("void") void presentFromRectInView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("UIPopoverArrowDirection") EnumSet<UIPopoverArrowDirection> arrowDirections, @Type("BOOL") boolean animated);
    @Bind("setContentViewController:animated:") public native @Type("void") void setContentViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    @Bind("setPopoverContentSize:animated:") public native @Type("void") void setPopoverContentSize(@Type("CGSize") CGSize size, @Type("BOOL") boolean animated);
    /*</methods>*/

}
