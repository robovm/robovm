/*
 * Copyright (C) 2013-2015 RoboVM AB
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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityElement/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAccessibilityIdentification/*</implements>*/ {

    /*<ptr>*/public static class UIAccessibilityElementPtr extends Ptr<UIAccessibilityElement, UIAccessibilityElementPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIAccessibilityElement.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIAccessibilityElement() {}
    protected UIAccessibilityElement(SkipInit skipInit) { super(skipInit); }
    public UIAccessibilityElement(UIAccessibilityContainer container) { super((SkipInit) null); initObject(init(container)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "accessibilityContainer")
    public native UIAccessibilityContainer getAccessibilityContainer();
    @Property(selector = "setAccessibilityContainer:", strongRef = true)
    public native void setAccessibilityContainer(UIAccessibilityContainer v);
    @Property(selector = "isAccessibilityElement")
    public native boolean isAccessibilityElement();
    @Property(selector = "setIsAccessibilityElement:")
    public native void setAccessibilityElement(boolean v);
    @Property(selector = "accessibilityLabel")
    public native String getAccessibilityLabel();
    @Property(selector = "setAccessibilityLabel:")
    public native void setAccessibilityLabel(String v);
    @Property(selector = "accessibilityHint")
    public native String getAccessibilityHint();
    @Property(selector = "setAccessibilityHint:")
    public native void setAccessibilityHint(String v);
    @Property(selector = "accessibilityValue")
    public native String getAccessibilityValue();
    @Property(selector = "setAccessibilityValue:")
    public native void setAccessibilityValue(String v);
    @Property(selector = "accessibilityFrame")
    public native @ByVal CGRect getAccessibilityFrame();
    @Property(selector = "setAccessibilityFrame:")
    public native void setAccessibilityFrame(@ByVal CGRect v);
    @Property(selector = "accessibilityTraits")
    public native UIAccessibilityTraits getAccessibilityTraits();
    @Property(selector = "setAccessibilityTraits:", strongRef = true)
    public native void setAccessibilityTraits(UIAccessibilityTraits v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityIdentifier")
    public native String getAccessibilityIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityIdentifier:")
    public native void setAccessibilityIdentifier(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAccessibilityContainer:")
    protected native @Pointer long init(UIAccessibilityContainer container);
    /*</methods>*/
}
