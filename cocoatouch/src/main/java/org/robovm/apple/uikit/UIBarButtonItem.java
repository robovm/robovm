/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIBarButtonItem/*</name>*/ 
    extends /*<extends>*/UIBarItem/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIBarButtonItemPtr extends Ptr<UIBarButtonItem, UIBarButtonItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIBarButtonItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    public interface OnClickListener {
        void onClick(UIBarButtonItem barButtonItem);
    }
    
    private static final Selector handleClick = Selector.register("handleClick");
    private static class ListenerWrapper extends NSObject {
        private final OnClickListener listener;
        private ListenerWrapper(OnClickListener listener) {
            this.listener = listener;
        }
        @Method(selector = "handleClick")
        private void handleClick(UIBarButtonItem barButtonItem) {
            listener.onClick(barButtonItem);
        }
    }
    
    public UIBarButtonItem(UIImage image, UIBarButtonItemStyle style, OnClickListener listener) { 
        super((SkipInit) null);
        ListenerWrapper l = new ListenerWrapper(listener);
        initObject(initWithImage$style$target$action$(image, style, l, handleClick));
        this.addStrongRef(l);
    }
    public UIBarButtonItem(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, OnClickListener listener) {
        super((SkipInit) null);
        ListenerWrapper l = new ListenerWrapper(listener);
        initObject(initWithImage$landscapeImagePhone$style$target$action$(image, landscapeImagePhone, style, l, handleClick));
        this.addStrongRef(l);
    }
    public UIBarButtonItem(String title, UIBarButtonItemStyle style, OnClickListener listener) {
        super((SkipInit) null);
        ListenerWrapper l = new ListenerWrapper(listener);
        initObject(initWithTitle$style$target$action$(title, style, l, handleClick));
        this.addStrongRef(l);
    }
    public UIBarButtonItem(UIBarButtonSystemItem systemItem, OnClickListener listener) {
        super((SkipInit) null);
        ListenerWrapper l = new ListenerWrapper(listener);
        initObject(initWithBarButtonSystemItem$target$action$(systemItem, l, handleClick));
        this.addStrongRef(l);
    }
    
    /*<constructors>*/
    public UIBarButtonItem() {}
    protected UIBarButtonItem(SkipInit skipInit) { super(skipInit); }
    public UIBarButtonItem(UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action) { super((SkipInit) null); initObject(initWithImage$style$target$action$(image, style, target, action)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIBarButtonItem(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action) { super((SkipInit) null); initObject(initWithImage$landscapeImagePhone$style$target$action$(image, landscapeImagePhone, style, target, action)); }
    public UIBarButtonItem(String title, UIBarButtonItemStyle style, NSObject target, Selector action) { super((SkipInit) null); initObject(initWithTitle$style$target$action$(title, style, target, action)); }
    public UIBarButtonItem(UIBarButtonSystemItem systemItem, NSObject target, Selector action) { super((SkipInit) null); initObject(initWithBarButtonSystemItem$target$action$(systemItem, target, action)); }
    public UIBarButtonItem(UIView customView) { super((SkipInit) null); initObject(initWithCustomView$(customView)); }
    /*</constructors>*/
    
    public void setOnClickListener(OnClickListener listener) {
        NSObject t = getTarget();
        if (t instanceof ListenerWrapper) {
            this.removeStrongRef(t);
        }
        ListenerWrapper l = new ListenerWrapper(listener);
        setAction(handleClick);
        setTarget(l);
        this.addStrongRef(l);
    }
    
    /*<properties>*/
    @Property(selector = "style")
    public native UIBarButtonItemStyle getStyle();
    @Property(selector = "setStyle:")
    public native void setStyle(UIBarButtonItemStyle v);
    @Property(selector = "width")
    public native @MachineSizedFloat double getWidth();
    @Property(selector = "setWidth:")
    public native void setWidth(@MachineSizedFloat double v);
    @Property(selector = "possibleTitles")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringListMarshaler.class) List<String> getPossibleTitles();
    @Property(selector = "setPossibleTitles:")
    public native void setPossibleTitles(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringListMarshaler.class) List<String> v);
    @Property(selector = "customView")
    public native UIView getCustomView();
    @Property(selector = "setCustomView:")
    public native void setCustomView(UIView v);
    @Property(selector = "action")
    protected native Selector getAction();
    @Property(selector = "setAction:")
    protected native void setAction(Selector v);
    @Property(selector = "target")
    protected native NSObject getTarget();
    @Property(selector = "setTarget:", strongRef = true)
    protected native void setTarget(NSObject v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithImage:style:target:action:")
    protected native @Pointer long initWithImage$style$target$action$(UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithImage:landscapeImagePhone:style:target:action:")
    protected native @Pointer long initWithImage$landscapeImagePhone$style$target$action$(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action);
    @Method(selector = "initWithTitle:style:target:action:")
    protected native @Pointer long initWithTitle$style$target$action$(String title, UIBarButtonItemStyle style, NSObject target, Selector action);
    @Method(selector = "initWithBarButtonSystemItem:target:action:")
    protected native @Pointer long initWithBarButtonSystemItem$target$action$(UIBarButtonSystemItem systemItem, NSObject target, Selector action);
    @Method(selector = "initWithCustomView:")
    protected native @Pointer long initWithCustomView$(UIView customView);
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
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setBackgroundImage:forState:style:barMetrics:")
    public native void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "backgroundImageForState:style:barMetrics:")
    public native UIImage getBackgroundImage(UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setBackgroundVerticalPositionAdjustment:forBarMetrics:")
    public native void setBackgroundVerticalPositionAdjustment(@MachineSizedFloat double adjustment, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "backgroundVerticalPositionAdjustmentForBarMetrics:")
    public native @MachineSizedFloat double getBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setTitlePositionAdjustment:forBarMetrics:")
    public native void setTitlePositionAdjustment(@ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "titlePositionAdjustmentForBarMetrics:")
    public native @ByVal UIOffset getTitlePositionAdjustment(UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setBackButtonBackgroundImage:forState:barMetrics:")
    public native void setBackButtonBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "backButtonBackgroundImageForState:barMetrics:")
    public native UIImage getBackButtonBackgroundImage(UIControlState state, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setBackButtonTitlePositionAdjustment:forBarMetrics:")
    public native void setBackButtonTitlePositionAdjustment(@ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "backButtonTitlePositionAdjustmentForBarMetrics:")
    public native @ByVal UIOffset getBackButtonTitlePositionAdjustment(UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:")
    public native void setBackButtonBackgroundVerticalPositionAdjustment(@MachineSizedFloat double adjustment, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:")
    public native @MachineSizedFloat double getBackButtonBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
