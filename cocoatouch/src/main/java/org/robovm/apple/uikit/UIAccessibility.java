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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIAccessibility/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "isAccessibilityElement")
    boolean isIsAccessibilityElement();
    @Property(selector = "setIsAccessibilityElement:")
    void setIsAccessibilityElement(boolean v);
    @Property(selector = "accessibilityLabel")
    String getAccessibilityLabel();
    @Property(selector = "setAccessibilityLabel:")
    void setAccessibilityLabel(String v);
    @Property(selector = "accessibilityHint")
    String getAccessibilityHint();
    @Property(selector = "setAccessibilityHint:")
    void setAccessibilityHint(String v);
    @Property(selector = "accessibilityValue")
    String getAccessibilityValue();
    @Property(selector = "setAccessibilityValue:")
    void setAccessibilityValue(String v);
    @Property(selector = "accessibilityTraits")
    long getAccessibilityTraits();
    @Property(selector = "setAccessibilityTraits:")
    void setAccessibilityTraits(long v);
    @Property(selector = "accessibilityFrame")
    @ByVal CGRect getAccessibilityFrame();
    @Property(selector = "setAccessibilityFrame:")
    void setAccessibilityFrame(@ByVal CGRect v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "accessibilityPath")
    UIBezierPath getAccessibilityPath();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAccessibilityPath:")
    void setAccessibilityPath(UIBezierPath v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityActivationPoint")
    @ByVal CGPoint getAccessibilityActivationPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityActivationPoint:")
    void setAccessibilityActivationPoint(@ByVal CGPoint v);
    @Property(selector = "accessibilityLanguage")
    String getAccessibilityLanguage();
    @Property(selector = "setAccessibilityLanguage:")
    void setAccessibilityLanguage(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityElementsHidden")
    boolean isAccessibilityElementsHidden();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityElementsHidden:")
    void setAccessibilityElementsHidden(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityViewIsModal")
    boolean isAccessibilityViewIsModal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityViewIsModal:")
    void setAccessibilityViewIsModal(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "shouldGroupAccessibilityChildren")
    boolean isShouldGroupAccessibilityChildren();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setShouldGroupAccessibilityChildren:")
    void setShouldGroupAccessibilityChildren(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
