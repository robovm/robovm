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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="UIAccessibilityTraitNone")
    public static native long TraitNone();
    @GlobalValue(symbol="UIAccessibilityTraitButton")
    public static native long TraitButton();
    @GlobalValue(symbol="UIAccessibilityTraitLink")
    public static native long TraitLink();
    @GlobalValue(symbol="UIAccessibilityTraitHeader")
    public static native long TraitHeader();
    @GlobalValue(symbol="UIAccessibilityTraitSearchField")
    public static native long TraitSearchField();
    @GlobalValue(symbol="UIAccessibilityTraitImage")
    public static native long TraitImage();
    @GlobalValue(symbol="UIAccessibilityTraitSelected")
    public static native long TraitSelected();
    @GlobalValue(symbol="UIAccessibilityTraitPlaysSound")
    public static native long TraitPlaysSound();
    @GlobalValue(symbol="UIAccessibilityTraitKeyboardKey")
    public static native long TraitKeyboardKey();
    @GlobalValue(symbol="UIAccessibilityTraitStaticText")
    public static native long TraitStaticText();
    @GlobalValue(symbol="UIAccessibilityTraitSummaryElement")
    public static native long TraitSummaryElement();
    @GlobalValue(symbol="UIAccessibilityTraitNotEnabled")
    public static native long TraitNotEnabled();
    @GlobalValue(symbol="UIAccessibilityTraitUpdatesFrequently")
    public static native long TraitUpdatesFrequently();
    @GlobalValue(symbol="UIAccessibilityTraitStartsMediaSession")
    public static native long TraitStartsMediaSession();
    @GlobalValue(symbol="UIAccessibilityTraitAdjustable")
    public static native long TraitAdjustable();
    @GlobalValue(symbol="UIAccessibilityTraitAllowsDirectInteraction")
    public static native long TraitAllowsDirectInteraction();
    @GlobalValue(symbol="UIAccessibilityTraitCausesPageTurn")
    public static native long TraitCausesPageTurn();
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification")
    public static native int NotificationScreenChanged();
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification")
    public static native int NotificationLayoutChanged();
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification")
    public static native int NotificationAnnouncement();
    @GlobalValue(symbol="UIAccessibilityAnnouncementDidFinishNotification")
    public static native String NotificationAnnouncementDidFinish();
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyStringValue")
    public static native NSString AnnouncementKeyStringValue();
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyWasSuccessful")
    public static native NSString AnnouncementKeyWasSuccessful();
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification")
    public static native int NotificationPageScrolled();
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePunctuation")
    public static native NSString SpeechAttributePunctuation();
    @GlobalValue(symbol="UIAccessibilitySpeechAttributeLanguage")
    public static native NSString SpeechAttributeLanguage();
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePitch")
    public static native NSString SpeechAttributePitch();
    @GlobalValue(symbol="UIAccessibilityVoiceOverStatusChanged")
    public static native String NotificationVoiceOverStatusChanged();
    @GlobalValue(symbol="UIAccessibilityMonoAudioStatusDidChangeNotification")
    public static native String NotificationMonoAudioStatusDidChange();
    @GlobalValue(symbol="UIAccessibilityClosedCaptioningStatusDidChangeNotification")
    public static native String NotificationClosedCaptioningStatusDidChange();
    @GlobalValue(symbol="UIAccessibilityInvertColorsStatusDidChangeNotification")
    public static native String NotificationInvertColorsStatusDidChange();
    @GlobalValue(symbol="UIAccessibilityGuidedAccessStatusDidChangeNotification")
    public static native String NotificationGuidedAccessStatusDidChange();
    
    @Bridge(symbol="UIAccessibilityZoomFocusChanged")
    public static native void zoomFocusChanged(UIAccessibilityZoomType type, @ByVal CGRect frame, UIView view);
    @Bridge(symbol="UIAccessibilityRegisterGestureConflictWithZoom")
    public static native void registerGestureConflictWithZoom();
    @Bridge(symbol="UIAccessibilityConvertFrameToScreenCoordinates")
    public static native @ByVal CGRect convertFrameToScreenCoordinates(@ByVal CGRect rect, UIView view);
    @Bridge(symbol="UIAccessibilityConvertPathToScreenCoordinates")
    public static native UIBezierPath convertPathToScreenCoordinates(UIBezierPath path, UIView view);
    @Bridge(symbol="UIAccessibilityPostNotification")
    public static native void postNotification(int notification, NSObject argument);
    @Bridge(symbol="UIAccessibilityIsVoiceOverRunning")
    public static native boolean isVoiceOverRunning();
    @Bridge(symbol="UIAccessibilityIsMonoAudioEnabled")
    public static native boolean isMonoAudioEnabled();
    @Bridge(symbol="UIAccessibilityIsClosedCaptioningEnabled")
    public static native boolean isClosedCaptioningEnabled();
    @Bridge(symbol="UIAccessibilityIsInvertColorsEnabled")
    public static native boolean isInvertColorsEnabled();
    @Bridge(symbol="UIAccessibilityIsGuidedAccessEnabled")
    public static native boolean isGuidedAccessEnabled();
    @Bridge(symbol="UIAccessibilityRequestGuidedAccessSession")
    public static native void requestGuidedAccessSession(boolean enable, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
