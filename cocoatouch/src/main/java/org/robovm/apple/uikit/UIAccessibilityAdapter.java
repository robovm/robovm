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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAccessibility/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("isAccessibilityElement")
    public boolean isAccessibilityElement() { return false; }
    @NotImplemented("setIsAccessibilityElement:")
    public void setAccessibilityElement(boolean v) {}
    @NotImplemented("accessibilityLabel")
    public String getAccessibilityLabel() { return null; }
    @NotImplemented("setAccessibilityLabel:")
    public void setAccessibilityLabel(String v) {}
    @NotImplemented("accessibilityHint")
    public String getAccessibilityHint() { return null; }
    @NotImplemented("setAccessibilityHint:")
    public void setAccessibilityHint(String v) {}
    @NotImplemented("accessibilityValue")
    public String getAccessibilityValue() { return null; }
    @NotImplemented("setAccessibilityValue:")
    public void setAccessibilityValue(String v) {}
    @NotImplemented("accessibilityTraits")
    public UIAccessibilityTraits getAccessibilityTraits() { return null; }
    @NotImplemented("setAccessibilityTraits:")
    public void setAccessibilityTraits(UIAccessibilityTraits v) {}
    @NotImplemented("accessibilityFrame")
    public @ByVal CGRect getAccessibilityFrame() { return null; }
    @NotImplemented("setAccessibilityFrame:")
    public void setAccessibilityFrame(@ByVal CGRect v) {}
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("accessibilityPath")
    public UIBezierPath getAccessibilityPath() { return null; }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("setAccessibilityPath:")
    public void setAccessibilityPath(UIBezierPath v) {}
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("accessibilityActivationPoint")
    public @ByVal CGPoint getAccessibilityActivationPoint() { return null; }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("setAccessibilityActivationPoint:")
    public void setAccessibilityActivationPoint(@ByVal CGPoint v) {}
    @NotImplemented("accessibilityLanguage")
    public String getAccessibilityLanguage() { return null; }
    @NotImplemented("setAccessibilityLanguage:")
    public void setAccessibilityLanguage(String v) {}
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("accessibilityElementsHidden")
    public boolean areAccessibilityElementsHidden() { return false; }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("setAccessibilityElementsHidden:")
    public void setAccessibilityElementsHidden(boolean v) {}
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("accessibilityViewIsModal")
    public boolean isAccessibilityViewModal() { return false; }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("setAccessibilityViewIsModal:")
    public void setAccessibilityViewModal(boolean v) {}
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("shouldGroupAccessibilityChildren")
    public boolean shouldGroupAccessibilityChildren() { return false; }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("setShouldGroupAccessibilityChildren:")
    public void setShouldGroupAccessibilityChildren(boolean v) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("accessibilityNavigationStyle")
    public UIAccessibilityNavigationStyle getAccessibilityNavigationStyle() { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("setAccessibilityNavigationStyle:")
    public void setAccessibilityNavigationStyle(UIAccessibilityNavigationStyle v) {}
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
