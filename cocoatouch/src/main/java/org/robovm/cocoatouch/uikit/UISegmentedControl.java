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
public class /*<name>*/ UISegmentedControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISegmentedControl /*</name>*/.class);
    }

    /*<constructors>*/
    public UISegmentedControl() {}
    @Bind("initWithItems:") public UISegmentedControl(@Type("NSArray *") NSArray items) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("apportionsSegmentWidthsByContent") public native @Type("BOOL") boolean isApportionsSegmentWidthsByContent();
    @Bind("setApportionsSegmentWidthsByContent:") public native void setApportionsSegmentWidthsByContent(@Type("BOOL") boolean v);
    @Bind("segmentedControlStyle") public native @Type("UISegmentedControlStyle") UISegmentedControlStyle getControlStyle();
    @Bind("setSegmentedControlStyle:") public native void setControlStyle(@Type("UISegmentedControlStyle") UISegmentedControlStyle v);
    @Bind("isMomentary") public native @Type("BOOL") boolean isMomentary();
    @Bind("setMomentary:") public native void setMomentary(@Type("BOOL") boolean v);
    @Bind("numberOfSegments") public native @Type("NSUInteger") int getNumberOfSegments();
    @Bind("selectedSegmentIndex") public native @Type("NSInteger") int getSelectedSegment();
    @Bind("setSelectedSegmentIndex:") public native void setSelectedSegment(@Type("NSInteger") int v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    @Bind("backgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("dividerImageForLeftSegmentState:rightSegmentState:barMetrics:") public native @Type("UIImage *") UIImage getDividerImage(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("contentOffsetForSegmentAtIndex:") public native @Type("CGSize") CGSize getSegmentContentOffset(@Type("NSUInteger") int segment);
    @Bind("contentPositionAdjustmentForSegmentType:barMetrics:") public native @Type("UIOffset") UIOffset getSegmentContentPositionAdjustment(@Type("UISegmentedControlSegment") UISegmentedControlSegment leftCenterRightOrAlone, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("imageForSegmentAtIndex:") public native @Type("UIImage *") UIImage getSegmentImage(@Type("NSUInteger") int segment);
    @Bind("titleForSegmentAtIndex:") public native @Type("NSString *") String getSegmentTitle(@Type("NSUInteger") int segment);
    @Bind("widthForSegmentAtIndex:") public native @Type("CGFloat") float getSegmentWidth(@Type("NSUInteger") int segment);
    @Bind("titleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes(@Type("UIControlState") UIControlState state);
    @Bind("insertSegmentWithImage:atIndex:animated:") public native @Type("void") void insertSegment(@Type("UIImage *") UIImage image, @Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    @Bind("insertSegmentWithTitle:atIndex:animated:") public native @Type("void") void insertSegment(@Type("NSString *") String title, @Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    @Bind("isEnabledForSegmentAtIndex:") public native @Type("BOOL") boolean isSegmentEnabled(@Type("NSUInteger") int segment);
    @Bind("removeAllSegments") public native @Type("void") void removeAllSegments();
    @Bind("removeSegmentAtIndex:animated:") public native @Type("void") void removeSegmentAtIndex(@Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    @Bind("setBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setContentOffset:forSegmentAtIndex:") public native @Type("void") void setContentOffset(@Type("CGSize") CGSize offset, @Type("NSUInteger") int segment);
    @Bind("setContentPositionAdjustment:forSegmentType:barMetrics:") public native @Type("void") void setContentPositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UISegmentedControlSegment") UISegmentedControlSegment leftCenterRightOrAlone, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:") public native @Type("void") void setDividerImage(@Type("UIImage *") UIImage dividerImage, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setEnabled:forSegmentAtIndex:") public native @Type("void") void setEnabled(@Type("BOOL") boolean enabled, @Type("NSUInteger") int segment);
    @Bind("setImage:forSegmentAtIndex:") public native @Type("void") void setImage(@Type("UIImage *") UIImage image, @Type("NSUInteger") int segment);
    @Bind("setTitle:forSegmentAtIndex:") public native @Type("void") void setTitle(@Type("NSString *") String title, @Type("NSUInteger") int segment);
    @Bind("setTitleTextAttributes:forState:") public native @Type("void") void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    @Bind("setWidth:forSegmentAtIndex:") public native @Type("void") void setWidth(@Type("CGFloat") float width, @Type("NSUInteger") int segment);
    /*</methods>*/

}
