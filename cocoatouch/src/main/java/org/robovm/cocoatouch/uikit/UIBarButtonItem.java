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
public class /*<name>*/ UIBarButtonItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBarButtonItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UIBarButtonItem() {}
    @Bind("initWithBarButtonSystemItem:target:action:") public UIBarButtonItem(@Type("UIBarButtonSystemItem") UIBarButtonSystemItem systemItem, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    @Bind("initWithCustomView:") public UIBarButtonItem(@Type("UIView *") UIView customView) {}
    @Bind("initWithImage:landscapeImagePhone:style:target:action:") public UIBarButtonItem(@Type("UIImage *") UIImage image, @Type("UIImage *") UIImage landscapeImagePhone, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    @Bind("initWithImage:style:target:action:") public UIBarButtonItem(@Type("UIImage *") UIImage image, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    @Bind("initWithTitle:style:target:action:") public UIBarButtonItem(@Type("NSString *") String title, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("action") public native @Type("SEL") Selector getAction();
    @Bind("setAction:") public native void setAction(@Type("SEL") Selector v);
    @Bind("customView") public native @Type("UIView *") UIView getCustomView();
    @Bind("setCustomView:") public native void setCustomView(@Type("UIView *") UIView v);
    @Bind("possibleTitles") public native @Type("NSSet *") NSSet getPossibleTitles();
    @Bind("setPossibleTitles:") public native void setPossibleTitles(@Type("NSSet *") NSSet v);
    @Bind("style") public native @Type("UIBarButtonItemStyle") UIBarButtonItemStyle getStyle();
    @Bind("setStyle:") public native void setStyle(@Type("UIBarButtonItemStyle") UIBarButtonItemStyle v);
    @Bind("target") public native @Type("id") NSObject getTarget();
    @Bind("setTarget:") public native void setTarget(@Type("id") NSObject v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("width") public native @Type("CGFloat") float getWidth();
    @Bind("setWidth:") public native void setWidth(@Type("CGFloat") float v);
    /*</properties>*/
    /*<methods>*/
    @Bind("backgroundImageForState:style:barMetrics:") public native @Type("UIImage *") UIImage backgroundImageForState(@Type("UIControlState") UIControlState state, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("backButtonBackgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackButtonBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getBackButtonBackgroundVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("backButtonTitlePositionAdjustmentForBarMetrics:") public native @Type("UIOffset") UIOffset getBackButtonTitlePositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("backgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("backgroundVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getBackgroundVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("titlePositionAdjustmentForBarMetrics:") public native @Type("UIOffset") UIOffset getTitlePositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackButtonBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackButtonBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setBackButtonBackgroundVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackButtonTitlePositionAdjustment:forBarMetrics:") public native @Type("void") void setBackButtonTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackgroundImage:forState:style:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setBackgroundVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setBackgroundVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setTitlePositionAdjustment:forBarMetrics:") public native @Type("void") void setTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /*</methods>*/

}
