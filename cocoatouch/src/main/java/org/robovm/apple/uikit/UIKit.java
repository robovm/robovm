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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIKit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputUpArrow", optional=true)
    public static native String KeyInputUpArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputDownArrow", optional=true)
    public static native String KeyInputDownArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputLeftArrow", optional=true)
    public static native String KeyInputLeftArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputRightArrow", optional=true)
    public static native String KeyInputRightArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputEscape", optional=true)
    public static native String KeyInputEscape();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFamilyAttribute", optional=true)
    public static native NSString FontDescriptorFamilyAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorNameAttribute", optional=true)
    public static native NSString FontDescriptorNameAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFaceAttribute", optional=true)
    public static native NSString FontDescriptorFaceAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorSizeAttribute", optional=true)
    public static native NSString FontDescriptorSizeAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorVisibleNameAttribute", optional=true)
    public static native NSString FontDescriptorVisibleNameAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorMatrixAttribute", optional=true)
    public static native NSString FontDescriptorMatrixAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorCharacterSetAttribute", optional=true)
    public static native NSString FontDescriptorCharacterSetAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorCascadeListAttribute", optional=true)
    public static native NSString FontDescriptorCascadeListAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorTraitsAttribute", optional=true)
    public static native NSString FontDescriptorTraitsAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFixedAdvanceAttribute", optional=true)
    public static native NSString FontDescriptorFixedAdvanceAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorFeatureSettingsAttribute", optional=true)
    public static native NSString FontDescriptorFeatureSettingsAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontDescriptorTextStyleAttribute", optional=true)
    public static native NSString FontDescriptorTextStyleAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontSymbolicTrait", optional=true)
    public static native NSString FontSymbolicTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontWeightTrait", optional=true)
    public static native NSString FontWeightTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontWidthTrait", optional=true)
    public static native NSString FontWidthTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontSlantTrait", optional=true)
    public static native NSString FontSlantTrait();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureTypeIdentifierKey", optional=true)
    public static native NSString FontFeatureTypeIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureSelectorIdentifierKey", optional=true)
    public static native NSString FontFeatureSelectorIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleHeadline", optional=true)
    public static native String FontTextStyleHeadline();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleBody", optional=true)
    public static native String FontTextStyleBody();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleSubheadline", optional=true)
    public static native String FontTextStyleSubheadline();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleFootnote", optional=true)
    public static native String FontTextStyleFootnote();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleCaption1", optional=true)
    public static native String FontTextStyleCaption1();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleCaption2", optional=true)
    public static native String FontTextStyleCaption2();
    @GlobalValue(symbol="UIEdgeInsetsZero", optional=true)
    public static native @ByVal UIEdgeInsets EdgeInsetsZero();
    @GlobalValue(symbol="UIOffsetZero", optional=true)
    public static native @ByVal UIOffset OffsetZero();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIViewNoIntrinsicMetric", optional=true)
    public static native @MachineSizedFloat double ViewNoIntrinsicMetric();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingCompressedSize", optional=true)
    public static native @ByVal CGSize LayoutFittingCompressedSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingExpandedSize", optional=true)
    public static native @ByVal CGSize LayoutFittingExpandedSize();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIScrollViewDecelerationRateNormal", optional=true)
    public static native @MachineSizedFloat double ScrollViewDecelerationRateNormal();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIScrollViewDecelerationRateFast", optional=true)
    public static native @MachineSizedFloat double ScrollViewDecelerationRateFast();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTabColumnTerminatorsAttributeName", optional=true)
    public static native NSString TabColumnTerminatorsAttributeName();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeFont", optional=true)
    public static native NSString TextAttributeFont();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextColor", optional=true)
    public static native NSString TextAttributeTextColor();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextShadowColor", optional=true)
    public static native NSString TextAttributeTextShadowColor();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="UITextAttributeTextShadowOffset", optional=true)
    public static native NSString TextAttributeTextShadowOffset();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UITableViewIndexSearch", optional=true)
    public static native NSString TableViewIndexSearch();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UITableViewAutomaticDimension", optional=true)
    public static native @MachineSizedFloat double TableViewAutomaticDimension();
    @GlobalValue(symbol="UITableViewSelectionDidChangeNotification", optional=true)
    public static native NSString TableViewSelectionDidChangeNotification();
    @GlobalValue(symbol="UIAccessibilityTraitNone", optional=true)
    public static native long AccessibilityTraitNone();
    @GlobalValue(symbol="UIAccessibilityTraitButton", optional=true)
    public static native long AccessibilityTraitButton();
    @GlobalValue(symbol="UIAccessibilityTraitLink", optional=true)
    public static native long AccessibilityTraitLink();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityTraitHeader", optional=true)
    public static native long AccessibilityTraitHeader();
    @GlobalValue(symbol="UIAccessibilityTraitSearchField", optional=true)
    public static native long AccessibilityTraitSearchField();
    @GlobalValue(symbol="UIAccessibilityTraitImage", optional=true)
    public static native long AccessibilityTraitImage();
    @GlobalValue(symbol="UIAccessibilityTraitSelected", optional=true)
    public static native long AccessibilityTraitSelected();
    @GlobalValue(symbol="UIAccessibilityTraitPlaysSound", optional=true)
    public static native long AccessibilityTraitPlaysSound();
    @GlobalValue(symbol="UIAccessibilityTraitKeyboardKey", optional=true)
    public static native NSString AccessibilityTraitKeyboardKey();
    @GlobalValue(symbol="UIAccessibilityTraitKeyboardKey", optional=true)
    public static native void AccessibilityTraitKeyboardKey(NSString v);
    @GlobalValue(symbol="UIAccessibilityTraitStaticText", optional=true)
    public static native long AccessibilityTraitStaticText();
    @GlobalValue(symbol="UIAccessibilityTraitSummaryElement", optional=true)
    public static native long AccessibilityTraitSummaryElement();
    @GlobalValue(symbol="UIAccessibilityTraitNotEnabled", optional=true)
    public static native long AccessibilityTraitNotEnabled();
    @GlobalValue(symbol="UIAccessibilityTraitUpdatesFrequently", optional=true)
    public static native long AccessibilityTraitUpdatesFrequently();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityTraitStartsMediaSession", optional=true)
    public static native long AccessibilityTraitStartsMediaSession();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityTraitAdjustable", optional=true)
    public static native long AccessibilityTraitAdjustable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityTraitAllowsDirectInteraction", optional=true)
    public static native long AccessibilityTraitAllowsDirectInteraction();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityTraitCausesPageTurn", optional=true)
    public static native long AccessibilityTraitCausesPageTurn();
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification", optional=true)
    public static native NSString AccessibilityScreenChangedNotification();
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification", optional=true)
    public static native void AccessibilityScreenChangedNotification(NSString v);
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification", optional=true)
    public static native NSString AccessibilityLayoutChangedNotification();
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification", optional=true)
    public static native void AccessibilityLayoutChangedNotification(NSString v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification", optional=true)
    public static native NSString AccessibilityAnnouncementNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification", optional=true)
    public static native void AccessibilityAnnouncementNotification(NSString v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementDidFinishNotification", optional=true)
    public static native NSString AccessibilityAnnouncementDidFinishNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyStringValue", optional=true)
    public static native NSString AccessibilityAnnouncementKeyStringValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyWasSuccessful", optional=true)
    public static native NSString AccessibilityAnnouncementKeyWasSuccessful();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification", optional=true)
    public static native NSString AccessibilityPageScrolledNotification();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification", optional=true)
    public static native void AccessibilityPageScrolledNotification(NSString v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePunctuation", optional=true)
    public static native NSString AccessibilitySpeechAttributePunctuation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributeLanguage", optional=true)
    public static native NSString AccessibilitySpeechAttributeLanguage();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePitch", optional=true)
    public static native NSString AccessibilitySpeechAttributePitch();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityVoiceOverStatusChanged", optional=true)
    public static native NSString AccessibilityVoiceOverStatusChanged();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityMonoAudioStatusDidChangeNotification", optional=true)
    public static native NSString AccessibilityMonoAudioStatusDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityClosedCaptioningStatusDidChangeNotification", optional=true)
    public static native NSString AccessibilityClosedCaptioningStatusDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityInvertColorsStatusDidChangeNotification", optional=true)
    public static native NSString AccessibilityInvertColorsStatusDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityGuidedAccessStatusDidChangeNotification", optional=true)
    public static native NSString AccessibilityGuidedAccessStatusDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToFacebook", optional=true)
    public static native String ActivityTypePostToFacebook();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToTwitter", optional=true)
    public static native String ActivityTypePostToTwitter();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToWeibo", optional=true)
    public static native String ActivityTypePostToWeibo();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeMessage", optional=true)
    public static native String ActivityTypeMessage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeMail", optional=true)
    public static native String ActivityTypeMail();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePrint", optional=true)
    public static native String ActivityTypePrint();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeCopyToPasteboard", optional=true)
    public static native String ActivityTypeCopyToPasteboard();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeAssignToContact", optional=true)
    public static native String ActivityTypeAssignToContact();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeSaveToCameraRoll", optional=true)
    public static native String ActivityTypeSaveToCameraRoll();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeAddToReadingList", optional=true)
    public static native String ActivityTypeAddToReadingList();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToFlickr", optional=true)
    public static native String ActivityTypePostToFlickr();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToVimeo", optional=true)
    public static native String ActivityTypePostToVimeo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypePostToTencentWeibo", optional=true)
    public static native String ActivityTypePostToTencentWeibo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIActivityTypeAirDrop", optional=true)
    public static native String ActivityTypeAirDrop();
    @GlobalValue(symbol="UIDeviceOrientationDidChangeNotification", optional=true)
    public static native NSString DeviceOrientationDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIDeviceBatteryStateDidChangeNotification", optional=true)
    public static native NSString DeviceBatteryStateDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIDeviceBatteryLevelDidChangeNotification", optional=true)
    public static native NSString DeviceBatteryLevelDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIDeviceProximityStateDidChangeNotification", optional=true)
    public static native NSString DeviceProximityStateDidChangeNotification();
    @GlobalValue(symbol="UITextInputTextBackgroundColorKey", optional=true)
    public static native NSString TextInputTextBackgroundColorKey();
    @GlobalValue(symbol="UITextInputTextColorKey", optional=true)
    public static native NSString TextInputTextColorKey();
    @GlobalValue(symbol="UITextInputTextFontKey", optional=true)
    public static native NSString TextInputTextFontKey();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="UITextInputCurrentInputModeDidChangeNotification", optional=true)
    public static native NSString TextInputCurrentInputModeDidChangeNotification();
    @GlobalValue(symbol="UITextFieldTextDidBeginEditingNotification", optional=true)
    public static native NSString TextFieldTextDidBeginEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidEndEditingNotification", optional=true)
    public static native NSString TextFieldTextDidEndEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidChangeNotification", optional=true)
    public static native NSString TextFieldTextDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIBackgroundTaskInvalid", optional=true)
    public static native @MachineSizedUInt long BackgroundTaskInvalid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIMinimumKeepAliveTimeout", optional=true)
    public static native double MinimumKeepAliveTimeout();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalMinimum", optional=true)
    public static native double ApplicationBackgroundFetchIntervalMinimum();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalNever", optional=true)
    public static native double ApplicationBackgroundFetchIntervalNever();
    @GlobalValue(symbol="UITrackingRunLoopMode", optional=true)
    public static native String TrackingRunLoopMode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationDidEnterBackgroundNotification", optional=true)
    public static native NSString ApplicationDidEnterBackgroundNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationWillEnterForegroundNotification", optional=true)
    public static native NSString ApplicationWillEnterForegroundNotification();
    @GlobalValue(symbol="UIApplicationDidFinishLaunchingNotification", optional=true)
    public static native NSString ApplicationDidFinishLaunchingNotification();
    @GlobalValue(symbol="UIApplicationDidBecomeActiveNotification", optional=true)
    public static native NSString ApplicationDidBecomeActiveNotification();
    @GlobalValue(symbol="UIApplicationWillResignActiveNotification", optional=true)
    public static native NSString ApplicationWillResignActiveNotification();
    @GlobalValue(symbol="UIApplicationDidReceiveMemoryWarningNotification", optional=true)
    public static native NSString ApplicationDidReceiveMemoryWarningNotification();
    @GlobalValue(symbol="UIApplicationWillTerminateNotification", optional=true)
    public static native NSString ApplicationWillTerminateNotification();
    @GlobalValue(symbol="UIApplicationSignificantTimeChangeNotification", optional=true)
    public static native NSString ApplicationSignificantTimeChangeNotification();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarOrientationNotification", optional=true)
    public static native NSString ApplicationWillChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarOrientationNotification", optional=true)
    public static native NSString ApplicationDidChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationStatusBarOrientationUserInfoKey", optional=true)
    public static native NSString ApplicationStatusBarOrientationUserInfoKey();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarFrameNotification", optional=true)
    public static native NSString ApplicationWillChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarFrameNotification", optional=true)
    public static native NSString ApplicationDidChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationStatusBarFrameUserInfoKey", optional=true)
    public static native NSString ApplicationStatusBarFrameUserInfoKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationBackgroundRefreshStatusDidChangeNotification", optional=true)
    public static native NSString ApplicationBackgroundRefreshStatusDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsURLKey", optional=true)
    public static native NSString ApplicationLaunchOptionsURLKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsSourceApplicationKey", optional=true)
    public static native NSString ApplicationLaunchOptionsSourceApplicationKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsRemoteNotificationKey", optional=true)
    public static native NSString ApplicationLaunchOptionsRemoteNotificationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocalNotificationKey", optional=true)
    public static native NSString ApplicationLaunchOptionsLocalNotificationKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsAnnotationKey", optional=true)
    public static native NSString ApplicationLaunchOptionsAnnotationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationProtectedDataWillBecomeUnavailable", optional=true)
    public static native NSString ApplicationProtectedDataWillBecomeUnavailable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationProtectedDataDidBecomeAvailable", optional=true)
    public static native NSString ApplicationProtectedDataDidBecomeAvailable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocationKey", optional=true)
    public static native NSString ApplicationLaunchOptionsLocationKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsNewsstandDownloadsKey", optional=true)
    public static native NSString ApplicationLaunchOptionsNewsstandDownloadsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothCentralsKey", optional=true)
    public static native NSString ApplicationLaunchOptionsBluetoothCentralsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothPeripheralsKey", optional=true)
    public static native NSString ApplicationLaunchOptionsBluetoothPeripheralsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraSmall", optional=true)
    public static native String ContentSizeCategoryExtraSmall();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategorySmall", optional=true)
    public static native String ContentSizeCategorySmall();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryMedium", optional=true)
    public static native String ContentSizeCategoryMedium();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryLarge", optional=true)
    public static native String ContentSizeCategoryLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraLarge", optional=true)
    public static native String ContentSizeCategoryExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraLarge", optional=true)
    public static native String ContentSizeCategoryExtraExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraExtraLarge", optional=true)
    public static native String ContentSizeCategoryExtraExtraExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityMedium", optional=true)
    public static native String ContentSizeCategoryAccessibilityMedium();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityLarge", optional=true)
    public static native String ContentSizeCategoryAccessibilityLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraLarge", optional=true)
    public static native String ContentSizeCategoryAccessibilityExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraLarge", optional=true)
    public static native String ContentSizeCategoryAccessibilityExtraExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraExtraLarge", optional=true)
    public static native String ContentSizeCategoryAccessibilityExtraExtraExtraLarge();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryDidChangeNotification", optional=true)
    public static native NSString ContentSizeCategoryDidChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIContentSizeCategoryNewValueKey", optional=true)
    public static native NSString ContentSizeCategoryNewValueKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationUserDidTakeScreenshotNotification", optional=true)
    public static native NSString ApplicationUserDidTakeScreenshotNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIStateRestorationViewControllerStoryboardKey", optional=true)
    public static native NSString StateRestorationViewControllerStoryboardKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationBundleVersionKey", optional=true)
    public static native NSString ApplicationStateRestorationBundleVersionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationUserInterfaceIdiomKey", optional=true)
    public static native NSString ApplicationStateRestorationUserInterfaceIdiomKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationTimestampKey", optional=true)
    public static native NSString ApplicationStateRestorationTimestampKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationSystemVersionKey", optional=true)
    public static native NSString ApplicationStateRestorationSystemVersionKey();
    @GlobalValue(symbol="UICollectionElementKindSectionHeader", optional=true)
    public static native String CollectionElementKindSectionHeader();
    @GlobalValue(symbol="UICollectionElementKindSectionFooter", optional=true)
    public static native String CollectionElementKindSectionFooter();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIDocumentStateChangedNotification", optional=true)
    public static native NSString DocumentStateChangedNotification();
    @GlobalValue(symbol="UINavigationControllerHideShowBarDuration", optional=true)
    public static native @MachineSizedFloat double NavigationControllerHideShowBarDuration();
    @GlobalValue(symbol="UIImagePickerControllerMediaType", optional=true)
    public static native NSString ImagePickerControllerMediaType();
    @GlobalValue(symbol="UIImagePickerControllerOriginalImage", optional=true)
    public static native NSString ImagePickerControllerOriginalImage();
    @GlobalValue(symbol="UIImagePickerControllerEditedImage", optional=true)
    public static native NSString ImagePickerControllerEditedImage();
    @GlobalValue(symbol="UIImagePickerControllerCropRect", optional=true)
    public static native NSString ImagePickerControllerCropRect();
    @GlobalValue(symbol="UIImagePickerControllerMediaURL", optional=true)
    public static native NSString ImagePickerControllerMediaURL();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerReferenceURL", optional=true)
    public static native NSString ImagePickerControllerReferenceURL();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerMediaMetadata", optional=true)
    public static native NSString ImagePickerControllerMediaMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UILocalNotificationDefaultSoundName", optional=true)
    public static native String LocalNotificationDefaultSoundName();
    @GlobalValue(symbol="UIMenuControllerWillShowMenuNotification", optional=true)
    public static native NSString MenuControllerWillShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidShowMenuNotification", optional=true)
    public static native NSString MenuControllerDidShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerWillHideMenuNotification", optional=true)
    public static native NSString MenuControllerWillHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidHideMenuNotification", optional=true)
    public static native NSString MenuControllerDidHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerMenuFrameDidChangeNotification", optional=true)
    public static native NSString MenuControllerMenuFrameDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UINibExternalObjects", optional=true)
    public static native NSString NibExternalObjects();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @GlobalValue(symbol="UINibProxiedObjectsKey", optional=true)
    public static native NSString NibProxiedObjectsKey();
    @GlobalValue(symbol="UIPageViewControllerOptionSpineLocationKey", optional=true)
    public static native NSString PageViewControllerOptionSpineLocationKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIPageViewControllerOptionInterPageSpacingKey", optional=true)
    public static native NSString PageViewControllerOptionInterPageSpacingKey();
    @GlobalValue(symbol="UIPasteboardNameGeneral", optional=true)
    public static native String PasteboardNameGeneral();
    @GlobalValue(symbol="UIPasteboardNameFind", optional=true)
    public static native String PasteboardNameFind();
    @GlobalValue(symbol="UIPasteboardChangedNotification", optional=true)
    public static native NSString PasteboardChangedNotification();
    @GlobalValue(symbol="UIPasteboardChangedTypesAddedKey", optional=true)
    public static native NSString PasteboardChangedTypesAddedKey();
    @GlobalValue(symbol="UIPasteboardChangedTypesRemovedKey", optional=true)
    public static native NSString PasteboardChangedTypesRemovedKey();
    @GlobalValue(symbol="UIPasteboardRemovedNotification", optional=true)
    public static native NSString PasteboardRemovedNotification();
    @GlobalValue(symbol="UIPasteboardTypeListString", optional=true)
    public static native NSArray<NSString> PasteboardTypeListString();
    @GlobalValue(symbol="UIPasteboardTypeListURL", optional=true)
    public static native NSArray<NSString> PasteboardTypeListURL();
    @GlobalValue(symbol="UIPasteboardTypeListImage", optional=true)
    public static native NSArray<NSString> PasteboardTypeListImage();
    @GlobalValue(symbol="UIPasteboardTypeListColor", optional=true)
    public static native NSArray<NSString> PasteboardTypeListColor();
    @GlobalValue(symbol="UIPrintErrorDomain", optional=true)
    public static native String PrintErrorDomain();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIScreenDidConnectNotification", optional=true)
    public static native NSString ScreenDidConnectNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIScreenDidDisconnectNotification", optional=true)
    public static native NSString ScreenDidDisconnectNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIScreenModeDidChangeNotification", optional=true)
    public static native NSString ScreenModeDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIScreenBrightnessDidChangeNotification", optional=true)
    public static native NSString ScreenBrightnessDidChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextFromViewControllerKey", optional=true)
    public static native NSString TransitionContextFromViewControllerKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextToViewControllerKey", optional=true)
    public static native NSString TransitionContextToViewControllerKey();
    @GlobalValue(symbol="UITextViewTextDidBeginEditingNotification", optional=true)
    public static native NSString TextViewTextDidBeginEditingNotification();
    @GlobalValue(symbol="UITextViewTextDidChangeNotification", optional=true)
    public static native NSString TextViewTextDidChangeNotification();
    @GlobalValue(symbol="UITextViewTextDidEndEditingNotification", optional=true)
    public static native NSString TextViewTextDidEndEditingNotification();
    @GlobalValue(symbol="UIWindowLevelNormal", optional=true)
    public static native @MachineSizedFloat double WindowLevelNormal();
    @GlobalValue(symbol="UIWindowLevelAlert", optional=true)
    public static native @MachineSizedFloat double WindowLevelAlert();
    @GlobalValue(symbol="UIWindowLevelStatusBar", optional=true)
    public static native @MachineSizedFloat double WindowLevelStatusBar();
    @GlobalValue(symbol="UIWindowDidBecomeVisibleNotification", optional=true)
    public static native NSString WindowDidBecomeVisibleNotification();
    @GlobalValue(symbol="UIWindowDidBecomeHiddenNotification", optional=true)
    public static native NSString WindowDidBecomeHiddenNotification();
    @GlobalValue(symbol="UIWindowDidBecomeKeyNotification", optional=true)
    public static native NSString WindowDidBecomeKeyNotification();
    @GlobalValue(symbol="UIWindowDidResignKeyNotification", optional=true)
    public static native NSString WindowDidResignKeyNotification();
    @GlobalValue(symbol="UIKeyboardWillShowNotification", optional=true)
    public static native NSString KeyboardWillShowNotification();
    @GlobalValue(symbol="UIKeyboardDidShowNotification", optional=true)
    public static native NSString KeyboardDidShowNotification();
    @GlobalValue(symbol="UIKeyboardWillHideNotification", optional=true)
    public static native NSString KeyboardWillHideNotification();
    @GlobalValue(symbol="UIKeyboardDidHideNotification", optional=true)
    public static native NSString KeyboardDidHideNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameBeginUserInfoKey", optional=true)
    public static native NSString KeyboardFrameBeginUserInfoKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameEndUserInfoKey", optional=true)
    public static native NSString KeyboardFrameEndUserInfoKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationDurationUserInfoKey", optional=true)
    public static native NSString KeyboardAnimationDurationUserInfoKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationCurveUserInfoKey", optional=true)
    public static native NSString KeyboardAnimationCurveUserInfoKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardWillChangeFrameNotification", optional=true)
    public static native NSString KeyboardWillChangeFrameNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardDidChangeFrameNotification", optional=true)
    public static native NSString KeyboardDidChangeFrameNotification();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterBeginUserInfoKey", optional=true)
    public static native NSString KeyboardCenterBeginUserInfoKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterEndUserInfoKey", optional=true)
    public static native NSString KeyboardCenterEndUserInfoKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardBoundsUserInfoKey", optional=true)
    public static native NSString KeyboardBoundsUserInfoKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSFontAttributeName", optional=true)
    public static native NSString FontAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSParagraphStyleAttributeName", optional=true)
    public static native NSString ParagraphStyleAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSForegroundColorAttributeName", optional=true)
    public static native NSString ForegroundColorAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorAttributeName", optional=true)
    public static native NSString BackgroundColorAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSLigatureAttributeName", optional=true)
    public static native NSString LigatureAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSKernAttributeName", optional=true)
    public static native NSString KernAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughStyleAttributeName", optional=true)
    public static native NSString StrikethroughStyleAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineStyleAttributeName", optional=true)
    public static native NSString UnderlineStyleAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeColorAttributeName", optional=true)
    public static native NSString StrokeColorAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSStrokeWidthAttributeName", optional=true)
    public static native NSString StrokeWidthAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSShadowAttributeName", optional=true)
    public static native NSString ShadowAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextEffectAttributeName", optional=true)
    public static native NSString TextEffectAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSAttachmentAttributeName", optional=true)
    public static native NSString AttachmentAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSLinkAttributeName", optional=true)
    public static native NSString LinkAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBaselineOffsetAttributeName", optional=true)
    public static native NSString BaselineOffsetAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSUnderlineColorAttributeName", optional=true)
    public static native NSString UnderlineColorAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSStrikethroughColorAttributeName", optional=true)
    public static native NSString StrikethroughColorAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSObliquenessAttributeName", optional=true)
    public static native NSString ObliquenessAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSExpansionAttributeName", optional=true)
    public static native NSString ExpansionAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSWritingDirectionAttributeName", optional=true)
    public static native NSString WritingDirectionAttributeName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSVerticalGlyphFormAttributeName", optional=true)
    public static native NSString VerticalGlyphFormAttributeName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextEffectLetterpressStyle", optional=true)
    public static native NSString TextEffectLetterpressStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPlainTextDocumentType", optional=true)
    public static native NSString PlainTextDocumentType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSRTFTextDocumentType", optional=true)
    public static native NSString RTFTextDocumentType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSRTFDTextDocumentType", optional=true)
    public static native NSString RTFDTextDocumentType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSHTMLTextDocumentType", optional=true)
    public static native NSString HTMLTextDocumentType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionOrientation", optional=true)
    public static native NSString TextLayoutSectionOrientation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionRange", optional=true)
    public static native NSString TextLayoutSectionRange();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDocumentTypeDocumentAttribute", optional=true)
    public static native NSString DocumentTypeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCharacterEncodingDocumentAttribute", optional=true)
    public static native NSString CharacterEncodingDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultAttributesDocumentAttribute", optional=true)
    public static native NSString DefaultAttributesDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperSizeDocumentAttribute", optional=true)
    public static native NSString PaperSizeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPaperMarginDocumentAttribute", optional=true)
    public static native NSString PaperMarginDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewSizeDocumentAttribute", optional=true)
    public static native NSString ViewSizeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewZoomDocumentAttribute", optional=true)
    public static native NSString ViewZoomDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSViewModeDocumentAttribute", optional=true)
    public static native NSString ViewModeDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSReadOnlyDocumentAttribute", optional=true)
    public static native NSString ReadOnlyDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSBackgroundColorDocumentAttribute", optional=true)
    public static native NSString BackgroundColorDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSHyphenationFactorDocumentAttribute", optional=true)
    public static native NSString HyphenationFactorDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSDefaultTabIntervalDocumentAttribute", optional=true)
    public static native NSString DefaultTabIntervalDocumentAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextLayoutSectionsAttribute", optional=true)
    public static native NSString TextLayoutSectionsAttribute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextStorageWillProcessEditingNotification", optional=true)
    public static native NSString TextStorageWillProcessEditingNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSTextStorageDidProcessEditingNotification", optional=true)
    public static native NSString TextStorageDidProcessEditingNotification();
    /*</methods>*/
}
