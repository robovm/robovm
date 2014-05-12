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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(NSString.AsStringMarshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityGlobals/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIAccessibilityGlobals.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="UIAccessibilityZoomFocusChanged", optional=true)
    public static native void zoomFocusChanged(UIAccessibilityZoomType type, @ByVal CGRect frame, UIView view);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="UIAccessibilityRegisterGestureConflictWithZoom", optional=true)
    public static native void registerGestureConflictWithZoom();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="UIAccessibilityConvertFrameToScreenCoordinates", optional=true)
    public static native @ByVal CGRect convertFrameToScreenCoordinates(@ByVal CGRect rect, UIView view);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="UIAccessibilityConvertPathToScreenCoordinates", optional=true)
    public static native UIBezierPath convertPathToScreenCoordinates(UIBezierPath path, UIView view);
    @Bridge(symbol="UIAccessibilityPostNotification", optional=true)
    public static native void postNotification(int notification, NSObject argument);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsVoiceOverRunning", optional=true)
    public static native boolean isVoiceOverRunning();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsMonoAudioEnabled", optional=true)
    public static native boolean isMonoAudioEnabled();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsClosedCaptioningEnabled", optional=true)
    public static native boolean isClosedCaptioningEnabled();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsInvertColorsEnabled", optional=true)
    public static native boolean isInvertColorsEnabled();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsGuidedAccessEnabled", optional=true)
    public static native boolean isGuidedAccessEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="UIAccessibilityRequestGuidedAccessSession", optional=true)
    public static native void requestGuidedAccessSession(boolean enable, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
