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
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(NSString.AsStringMarshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAccessibilityGlobals/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIAccessibilityGlobals.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static void postNotification(UIAccessibilityNotification notification, NSObject argument) {
        postNotification(notification.value(), argument);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementDidFinishNotification", optional=true)
    public static native NSString AnnouncementDidFinishNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyStringValue", optional=true)
    public static native NSString AnnouncementKeyStringValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyWasSuccessful", optional=true)
    public static native NSString AnnouncementKeyWasSuccessful();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityNotificationSwitchControlIdentifier", optional=true)
    public static native String SwitchControlIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityVoiceOverStatusChanged", optional=true)
    public static native NSString VoiceOverStatusChangedNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityMonoAudioStatusDidChangeNotification", optional=true)
    public static native NSString MonoAudioStatusDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityClosedCaptioningStatusDidChangeNotification", optional=true)
    public static native NSString ClosedCaptioningStatusDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityInvertColorsStatusDidChangeNotification", optional=true)
    public static native NSString InvertColorsStatusDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityGuidedAccessStatusDidChangeNotification", optional=true)
    public static native NSString GuidedAccessStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityBoldTextStatusDidChangeNotification", optional=true)
    public static native NSString BoldTextStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityGrayscaleStatusDidChangeNotification", optional=true)
    public static native NSString GrayscaleStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityReduceTransparencyStatusDidChangeNotification", optional=true)
    public static native NSString ReduceTransparencyStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityReduceMotionStatusDidChangeNotification", optional=true)
    public static native NSString ReduceMotionStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityDarkerSystemColorsStatusDidChangeNotification", optional=true)
    public static native NSString DarkerSystemColorsStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySwitchControlStatusDidChangeNotification", optional=true)
    public static native NSString SwitchControlStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeakSelectionStatusDidChangeNotification", optional=true)
    public static native NSString SpeakSelectionStatusDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeakScreenStatusDidChangeNotification", optional=true)
    public static native NSString SpeakScreenStatusDidChangeNotification();
    
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
    protected static native void postNotification(int notification, NSObject argument);
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
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsBoldTextEnabled", optional=true)
    public static native boolean isBoldTextEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsGrayscaleEnabled", optional=true)
    public static native boolean isGrayscaleEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsReduceTransparencyEnabled", optional=true)
    public static native boolean isReduceTransparencyEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsReduceMotionEnabled", optional=true)
    public static native boolean isReduceMotionEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityDarkerSystemColorsEnabled", optional=true)
    public static native boolean areDarkerSystemColorsEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsSwitchControlRunning", optional=true)
    public static native boolean isSwitchControlRunning();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsSpeakSelectionEnabled", optional=true)
    public static native boolean isSpeakSelectionEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="UIAccessibilityIsSpeakScreenEnabled", optional=true)
    public static native boolean isSpeakScreenEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="UIAccessibilityRequestGuidedAccessSession", optional=true)
    public static native void requestGuidedAccessSession(boolean enable, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
