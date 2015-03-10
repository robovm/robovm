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
package org.robovm.apple.watchkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.mapkit.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WatchKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKInterfaceObject/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKInterfaceObjectPtr extends Ptr<WKInterfaceObject, WKInterfaceObjectPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKInterfaceObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKInterfaceObject() {}
    protected WKInterfaceObject(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "interfaceProperty")
    public native String getInterfaceProperty();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setHidden:")
    public native void setHidden(boolean hidden);
    @Method(selector = "setAlpha:")
    public native void setAlpha(@MachineSizedFloat double alpha);
    @Method(selector = "setWidth:")
    public native void setWidth(@MachineSizedFloat double width);
    @Method(selector = "setHeight:")
    public native void setHeight(@MachineSizedFloat double height);
    @Method(selector = "setAccessibilityLabel:")
    public native void setAccessibilityLabel(String accessibilityLabel);
    @Method(selector = "setAccessibilityHint:")
    public native void setAccessibilityHint(String accessibilityHint);
    @Method(selector = "setAccessibilityValue:")
    public native void setAccessibilityValue(String accessibilityValue);
    @Method(selector = "setIsAccessibilityElement:")
    public native void setIsAccessibilityElement(boolean isAccessibilityElement);
    @Method(selector = "setAccessibilityTraits:")
    public native void setAccessibilityTraits(UIAccessibilityTraits accessibilityTraits);
    @Method(selector = "setAccessibilityImageRegions:")
    public native void setAccessibilityImageRegions(NSArray<WKAccessibilityImageRegion> accessibilityImageRegions);
    /*</methods>*/
}
