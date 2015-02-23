/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/
import org.robovm.apple.corefoundation.CFDictionary;
import org.robovm.apple.coremedia.CMTextMarkupAttributes;

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISegmentedControl/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UISegmentedControlPtr extends Ptr<UISegmentedControl, UISegmentedControlPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISegmentedControl.class); }/*</bind>*/
    /*<constants>*/
    public static final int NoSegment = -1;
    /*</constants>*/
    /*<constructors>*/
    public UISegmentedControl() {}
    protected UISegmentedControl(SkipInit skipInit) { super(skipInit); }
    public UISegmentedControl(NSArray<?> items) { super((SkipInit) null); initObject(init(items)); }
    /*</constructors>*/
    
    public UISegmentedControl(CGRect frame) {
        super(frame);
    }
    
    public UISegmentedControl(String... items) {
        super((SkipInit) null);
        initObject(init(NSArray.fromStrings(items)));
    }
    public UISegmentedControl(UIImage... items) {
        super((SkipInit) null);
        initObject(init(new NSArray<UIImage>(items)));
    }
    /*<properties>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "segmentedControlStyle")
    public native UISegmentedControlStyle getControlStyle();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setSegmentedControlStyle:")
    public native void setControlStyle(UISegmentedControlStyle v);
    @Property(selector = "isMomentary")
    public native boolean isMomentary();
    @Property(selector = "setMomentary:")
    public native void setMomentary(boolean v);
    @Property(selector = "numberOfSegments")
    public native @MachineSizedUInt long getNumberOfSegments();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "apportionsSegmentWidthsByContent")
    public native boolean apportionsSegmentWidthsByContent();
    /**
     * @since Available in iOS 5.0 and later.
     */
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextAttributes(NSAttributedStringAttributes attributes, UIControlState state) {
        if (attributes == null) {
            setTitleTextAttributesDictionary(null, state);
        } else {
            setTitleTextAttributesDictionary(attributes.getDictionary(), state);
        }
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextMarkupAttributes(CMTextMarkupAttributes attributes, UIControlState state) {
        if (attributes == null) {
            setTitleTextAttributesDictionary(null, state);
        } else {
            setTitleTextAttributesDictionary(attributes.getDictionary().as(NSDictionary.class), state);
        }
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleCoreTextAttributes(CTAttributedStringAttributes attributes, UIControlState state) {
        if (attributes == null) {
            setTitleTextAttributesDictionary(null, state);
        } else {
            setTitleTextAttributesDictionary(attributes.getDictionary().as(NSDictionary.class), state);
        }
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSAttributedStringAttributes getTitleTextAttributes(UIControlState state) {
        NSDictionary<NSString, NSObject> dict = getTitleTextAttributesDictionary(state);
        if (dict == null) return null;
        return new NSAttributedStringAttributes(dict);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CMTextMarkupAttributes getTitleTextMarkupAttributes(UIControlState state) {
        NSDictionary<NSString, NSObject> dict = getTitleTextAttributesDictionary(state);
        if (dict == null) return null; 
        return new CMTextMarkupAttributes(dict.as(CFDictionary.class));
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CTAttributedStringAttributes getTitleCoreTextAttributes(UIControlState state) {
        NSDictionary<NSString, NSObject> dict = getTitleTextAttributesDictionary(state);
        if (dict == null) return null; 
        return new CTAttributedStringAttributes(dict.as(CFDictionary.class));
    }
    /*<methods>*/
    @Method(selector = "initWithItems:")
    protected native @Pointer long init(NSArray<?> items);
    @Method(selector = "insertSegmentWithTitle:atIndex:animated:")
    public native void insertSegment(String title, @MachineSizedUInt long segment, boolean animated);
    @Method(selector = "insertSegmentWithImage:atIndex:animated:")
    public native void insertSegment(UIImage image, @MachineSizedUInt long segment, boolean animated);
    @Method(selector = "removeSegmentAtIndex:animated:")
    public native void removeSegment(@MachineSizedUInt long segment, boolean animated);
    @Method(selector = "removeAllSegments")
    public native void removeAllSegments();
    @Method(selector = "setTitle:forSegmentAtIndex:")
    public native void setTitle(String title, @MachineSizedUInt long segment);
    @Method(selector = "titleForSegmentAtIndex:")
    public native String getTitle(@MachineSizedUInt long segment);
    @Method(selector = "setImage:forSegmentAtIndex:")
    public native void setImage(UIImage image, @MachineSizedUInt long segment);
    @Method(selector = "imageForSegmentAtIndex:")
    public native UIImage getImage(@MachineSizedUInt long segment);
    @Method(selector = "setWidth:forSegmentAtIndex:")
    public native void setWidth(@MachineSizedFloat double width, @MachineSizedUInt long segment);
    @Method(selector = "widthForSegmentAtIndex:")
    public native @MachineSizedFloat double getWidth(@MachineSizedUInt long segment);
    @Method(selector = "setContentOffset:forSegmentAtIndex:")
    public native void setContentOffset(@ByVal CGSize offset, @MachineSizedUInt long segment);
    @Method(selector = "contentOffsetForSegmentAtIndex:")
    public native @ByVal CGSize getContentOffset(@MachineSizedUInt long segment);
    @Method(selector = "setEnabled:forSegmentAtIndex:")
    public native void setEnabled(boolean enabled, @MachineSizedUInt long segment);
    @Method(selector = "isEnabledForSegmentAtIndex:")
    public native boolean isEnabled(@MachineSizedUInt long segment);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setBackgroundImage:forState:barMetrics:")
    public native void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "backgroundImageForState:barMetrics:")
    public native UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:")
    public native void setDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "dividerImageForLeftSegmentState:rightSegmentState:barMetrics:")
    public native UIImage getDividerImage(UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setTitleTextAttributes:forState:")
    public native void setTitleTextAttributesDictionary(NSDictionary<NSString, NSObject> attributes, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "titleTextAttributesForState:")
    public native NSDictionary<NSString, NSObject> getTitleTextAttributesDictionary(UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setContentPositionAdjustment:forSegmentType:barMetrics:")
    public native void setContentPositionAdjustment(@ByVal UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "contentPositionAdjustmentForSegmentType:barMetrics:")
    public native @ByVal UIOffset getContentPositionAdjustment(UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /*</methods>*/
}
