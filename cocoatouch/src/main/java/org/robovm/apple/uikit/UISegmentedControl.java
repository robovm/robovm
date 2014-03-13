/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISegmentedControl/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UISegmentedControlPtr extends Ptr<UISegmentedControl, UISegmentedControlPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISegmentedControl.class); }/*</bind>*/
    /*<constants>*/
    public static final int NoSegment = -1;
    /*</constants>*/
    /*<constructors>*/
    public UISegmentedControl() {}
    protected UISegmentedControl(SkipInit skipInit) { super(skipInit); }
    public UISegmentedControl(NSArray<?> items) { super((SkipInit) null); initObject(initWithItems$(items)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "segmentedControlStyle")
    public native UISegmentedControlStyle getControlStyle();
    @Property(selector = "setSegmentedControlStyle:")
    public native void setControlStyle(UISegmentedControlStyle v);
    @Property(selector = "isMomentary")
    public native boolean isMomentary();
    @Property(selector = "setMomentary:")
    public native void setMomentary(boolean v);
    @Property(selector = "numberOfSegments")
    public native @MachineSizedUInt long getNumberOfSegments();
    @Property(selector = "apportionsSegmentWidthsByContent")
    public native boolean isApportionsSegmentWidthsByContent();
    @Property(selector = "setApportionsSegmentWidthsByContent:")
    public native void setApportionsSegmentWidthsByContent(boolean v);
    @Property(selector = "selectedSegmentIndex")
    public native @MachineSizedSInt long getSelectedSegment();
    @Property(selector = "setSelectedSegmentIndex:")
    public native void setSelectedSegment(@MachineSizedSInt long v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:")
    protected native @Pointer long initWithItems$(NSArray<?> items);
    @Method(selector = "insertSegmentWithTitle:atIndex:animated:")
    public native void insertSegment(String title, @MachineSizedUInt long segment, boolean animated);
    @Method(selector = "insertSegmentWithImage:atIndex:animated:")
    public native void insertSegment(UIImage image, @MachineSizedUInt long segment, boolean animated);
    @Method(selector = "removeSegmentAtIndex:animated:")
    public native void removeSegmentAtIndex$animated$(@MachineSizedUInt long segment, boolean animated);
    @Method(selector = "removeAllSegments")
    public native void removeAllSegments();
    @Method(selector = "setTitle:forSegmentAtIndex:")
    public native void setTitle$forSegmentAtIndex$(String title, @MachineSizedUInt long segment);
    @Method(selector = "titleForSegmentAtIndex:")
    public native String getSegmentTitle(@MachineSizedUInt long segment);
    @Method(selector = "setImage:forSegmentAtIndex:")
    public native void setImage$forSegmentAtIndex$(UIImage image, @MachineSizedUInt long segment);
    @Method(selector = "imageForSegmentAtIndex:")
    public native UIImage getSegmentImage(@MachineSizedUInt long segment);
    @Method(selector = "setWidth:forSegmentAtIndex:")
    public native void setWidth$forSegmentAtIndex$(@MachineSizedFloat double width, @MachineSizedUInt long segment);
    @Method(selector = "widthForSegmentAtIndex:")
    public native @MachineSizedFloat double getSegmentWidth(@MachineSizedUInt long segment);
    @Method(selector = "setContentOffset:forSegmentAtIndex:")
    public native void setContentOffset$forSegmentAtIndex$(@ByVal CGSize offset, @MachineSizedUInt long segment);
    @Method(selector = "contentOffsetForSegmentAtIndex:")
    public native @ByVal CGSize getSegmentContentOffset(@MachineSizedUInt long segment);
    @Method(selector = "setEnabled:forSegmentAtIndex:")
    public native void setEnabled$forSegmentAtIndex$(boolean enabled, @MachineSizedUInt long segment);
    @Method(selector = "isEnabledForSegmentAtIndex:")
    public native boolean isSegmentEnabled(@MachineSizedUInt long segment);
    @Method(selector = "setBackgroundImage:forState:barMetrics:")
    public native void setBackgroundImage$forState$barMetrics$(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Method(selector = "backgroundImageForState:barMetrics:")
    public native UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics);
    @Method(selector = "setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:")
    public native void setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$(UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Method(selector = "dividerImageForLeftSegmentState:rightSegmentState:barMetrics:")
    public native UIImage getDividerImage(UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Method(selector = "setTitleTextAttributes:forState:")
    public native void setTitleTextAttributes$forState$(NSDictionary<?, ?> attributes, UIControlState state);
    @Method(selector = "titleTextAttributesForState:")
    public native NSDictionary<?, ?> getTitleTextAttributes(UIControlState state);
    @Method(selector = "setContentPositionAdjustment:forSegmentType:barMetrics:")
    public native void setContentPositionAdjustment$forSegmentType$barMetrics$(@ByVal UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    @Method(selector = "contentPositionAdjustmentForSegmentType:barMetrics:")
    public native @ByVal UIOffset getSegmentContentPositionAdjustment(UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /*</methods>*/
}
