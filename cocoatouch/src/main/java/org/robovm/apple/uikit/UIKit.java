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
    @GlobalValue(symbol="UIKeyInputUpArrow")
    public static native String Value__UIKeyInputUpArrow();
    @GlobalValue(symbol="UIKeyInputDownArrow")
    public static native String Value__UIKeyInputDownArrow();
    @GlobalValue(symbol="UIKeyInputLeftArrow")
    public static native String Value__UIKeyInputLeftArrow();
    @GlobalValue(symbol="UIKeyInputRightArrow")
    public static native String Value__UIKeyInputRightArrow();
    @GlobalValue(symbol="UIKeyInputEscape")
    public static native String Value__UIKeyInputEscape();
    @GlobalValue(symbol="UIFontDescriptorFamilyAttribute")
    public static native String Value__UIFontDescriptorFamilyAttribute();
    @GlobalValue(symbol="UIFontDescriptorNameAttribute")
    public static native String Value__UIFontDescriptorNameAttribute();
    @GlobalValue(symbol="UIFontDescriptorFaceAttribute")
    public static native String Value__UIFontDescriptorFaceAttribute();
    @GlobalValue(symbol="UIFontDescriptorSizeAttribute")
    public static native String Value__UIFontDescriptorSizeAttribute();
    @GlobalValue(symbol="UIFontDescriptorVisibleNameAttribute")
    public static native String Value__UIFontDescriptorVisibleNameAttribute();
    @GlobalValue(symbol="UIFontDescriptorMatrixAttribute")
    public static native String Value__UIFontDescriptorMatrixAttribute();
    @GlobalValue(symbol="UIFontDescriptorCharacterSetAttribute")
    public static native String Value__UIFontDescriptorCharacterSetAttribute();
    @GlobalValue(symbol="UIFontDescriptorCascadeListAttribute")
    public static native String Value__UIFontDescriptorCascadeListAttribute();
    @GlobalValue(symbol="UIFontDescriptorTraitsAttribute")
    public static native String Value__UIFontDescriptorTraitsAttribute();
    @GlobalValue(symbol="UIFontDescriptorFixedAdvanceAttribute")
    public static native String Value__UIFontDescriptorFixedAdvanceAttribute();
    @GlobalValue(symbol="UIFontDescriptorFeatureSettingsAttribute")
    public static native String Value__UIFontDescriptorFeatureSettingsAttribute();
    @GlobalValue(symbol="UIFontDescriptorTextStyleAttribute")
    public static native String Value__UIFontDescriptorTextStyleAttribute();
    @GlobalValue(symbol="UIFontSymbolicTrait")
    public static native String Value__UIFontSymbolicTrait();
    @GlobalValue(symbol="UIFontWeightTrait")
    public static native String Value__UIFontWeightTrait();
    @GlobalValue(symbol="UIFontWidthTrait")
    public static native String Value__UIFontWidthTrait();
    @GlobalValue(symbol="UIFontSlantTrait")
    public static native String Value__UIFontSlantTrait();
    @GlobalValue(symbol="UIFontFeatureTypeIdentifierKey")
    public static native String Value__UIFontFeatureTypeIdentifierKey();
    @GlobalValue(symbol="UIFontFeatureSelectorIdentifierKey")
    public static native String Value__UIFontFeatureSelectorIdentifierKey();
    @GlobalValue(symbol="UIFontTextStyleHeadline")
    public static native String Value__UIFontTextStyleHeadline();
    @GlobalValue(symbol="UIFontTextStyleBody")
    public static native String Value__UIFontTextStyleBody();
    @GlobalValue(symbol="UIFontTextStyleSubheadline")
    public static native String Value__UIFontTextStyleSubheadline();
    @GlobalValue(symbol="UIFontTextStyleFootnote")
    public static native String Value__UIFontTextStyleFootnote();
    @GlobalValue(symbol="UIFontTextStyleCaption1")
    public static native String Value__UIFontTextStyleCaption1();
    @GlobalValue(symbol="UIFontTextStyleCaption2")
    public static native String Value__UIFontTextStyleCaption2();
    @GlobalValue(symbol="UIEdgeInsetsZero")
    public static native @ByVal UIEdgeInsets Value__UIEdgeInsetsZero();
    @GlobalValue(symbol="UIOffsetZero")
    public static native @ByVal UIOffset Value__UIOffsetZero();
    @GlobalValue(symbol="UIViewNoIntrinsicMetric")
    public static native @MachineSizedFloat double Value__UIViewNoIntrinsicMetric();
    @GlobalValue(symbol="UILayoutFittingCompressedSize")
    public static native @ByVal CGSize Value__UILayoutFittingCompressedSize();
    @GlobalValue(symbol="UILayoutFittingExpandedSize")
    public static native @ByVal CGSize Value__UILayoutFittingExpandedSize();
    @GlobalValue(symbol="UIScrollViewDecelerationRateNormal")
    public static native @MachineSizedFloat double Value__UIScrollViewDecelerationRateNormal();
    @GlobalValue(symbol="UIScrollViewDecelerationRateFast")
    public static native @MachineSizedFloat double Value__UIScrollViewDecelerationRateFast();
    @GlobalValue(symbol="UITextAttributeFont")
    public static native String Value__UITextAttributeFont();
    @GlobalValue(symbol="UITextAttributeTextColor")
    public static native String Value__UITextAttributeTextColor();
    @GlobalValue(symbol="UITextAttributeTextShadowColor")
    public static native String Value__UITextAttributeTextShadowColor();
    @GlobalValue(symbol="UITextAttributeTextShadowOffset")
    public static native String Value__UITextAttributeTextShadowOffset();
    @GlobalValue(symbol="UITableViewIndexSearch")
    public static native String Value__UITableViewIndexSearch();
    @GlobalValue(symbol="UITableViewAutomaticDimension")
    public static native @MachineSizedFloat double Value__UITableViewAutomaticDimension();
    @GlobalValue(symbol="UITableViewSelectionDidChangeNotification")
    public static native String Value__UITableViewSelectionDidChangeNotification();
    @GlobalValue(symbol="UIAccessibilityTraitNone")
    public static native long Value__UIAccessibilityTraitNone();
    @GlobalValue(symbol="UIAccessibilityTraitNone")
    public static native void Value__UIAccessibilityTraitNone(long v);
    @GlobalValue(symbol="UIAccessibilityTraitButton")
    public static native long Value__UIAccessibilityTraitButton();
    @GlobalValue(symbol="UIAccessibilityTraitButton")
    public static native void Value__UIAccessibilityTraitButton(long v);
    @GlobalValue(symbol="UIAccessibilityTraitLink")
    public static native long Value__UIAccessibilityTraitLink();
    @GlobalValue(symbol="UIAccessibilityTraitLink")
    public static native void Value__UIAccessibilityTraitLink(long v);
    @GlobalValue(symbol="UIAccessibilityTraitHeader")
    public static native long Value__UIAccessibilityTraitHeader();
    @GlobalValue(symbol="UIAccessibilityTraitHeader")
    public static native void Value__UIAccessibilityTraitHeader(long v);
    @GlobalValue(symbol="UIAccessibilityTraitSearchField")
    public static native long Value__UIAccessibilityTraitSearchField();
    @GlobalValue(symbol="UIAccessibilityTraitSearchField")
    public static native void Value__UIAccessibilityTraitSearchField(long v);
    @GlobalValue(symbol="UIAccessibilityTraitImage")
    public static native long Value__UIAccessibilityTraitImage();
    @GlobalValue(symbol="UIAccessibilityTraitImage")
    public static native void Value__UIAccessibilityTraitImage(long v);
    @GlobalValue(symbol="UIAccessibilityTraitSelected")
    public static native long Value__UIAccessibilityTraitSelected();
    @GlobalValue(symbol="UIAccessibilityTraitSelected")
    public static native void Value__UIAccessibilityTraitSelected(long v);
    @GlobalValue(symbol="UIAccessibilityTraitPlaysSound")
    public static native long Value__UIAccessibilityTraitPlaysSound();
    @GlobalValue(symbol="UIAccessibilityTraitPlaysSound")
    public static native void Value__UIAccessibilityTraitPlaysSound(long v);
    @GlobalValue(symbol="UIAccessibilityTraitKeyboardKey")
    public static native long Value__UIAccessibilityTraitKeyboardKey();
    @GlobalValue(symbol="UIAccessibilityTraitKeyboardKey")
    public static native void Value__UIAccessibilityTraitKeyboardKey(long v);
    @GlobalValue(symbol="UIAccessibilityTraitStaticText")
    public static native long Value__UIAccessibilityTraitStaticText();
    @GlobalValue(symbol="UIAccessibilityTraitStaticText")
    public static native void Value__UIAccessibilityTraitStaticText(long v);
    @GlobalValue(symbol="UIAccessibilityTraitSummaryElement")
    public static native long Value__UIAccessibilityTraitSummaryElement();
    @GlobalValue(symbol="UIAccessibilityTraitSummaryElement")
    public static native void Value__UIAccessibilityTraitSummaryElement(long v);
    @GlobalValue(symbol="UIAccessibilityTraitNotEnabled")
    public static native long Value__UIAccessibilityTraitNotEnabled();
    @GlobalValue(symbol="UIAccessibilityTraitNotEnabled")
    public static native void Value__UIAccessibilityTraitNotEnabled(long v);
    @GlobalValue(symbol="UIAccessibilityTraitUpdatesFrequently")
    public static native long Value__UIAccessibilityTraitUpdatesFrequently();
    @GlobalValue(symbol="UIAccessibilityTraitUpdatesFrequently")
    public static native void Value__UIAccessibilityTraitUpdatesFrequently(long v);
    @GlobalValue(symbol="UIAccessibilityTraitStartsMediaSession")
    public static native long Value__UIAccessibilityTraitStartsMediaSession();
    @GlobalValue(symbol="UIAccessibilityTraitStartsMediaSession")
    public static native void Value__UIAccessibilityTraitStartsMediaSession(long v);
    @GlobalValue(symbol="UIAccessibilityTraitAdjustable")
    public static native long Value__UIAccessibilityTraitAdjustable();
    @GlobalValue(symbol="UIAccessibilityTraitAdjustable")
    public static native void Value__UIAccessibilityTraitAdjustable(long v);
    @GlobalValue(symbol="UIAccessibilityTraitAllowsDirectInteraction")
    public static native long Value__UIAccessibilityTraitAllowsDirectInteraction();
    @GlobalValue(symbol="UIAccessibilityTraitAllowsDirectInteraction")
    public static native void Value__UIAccessibilityTraitAllowsDirectInteraction(long v);
    @GlobalValue(symbol="UIAccessibilityTraitCausesPageTurn")
    public static native long Value__UIAccessibilityTraitCausesPageTurn();
    @GlobalValue(symbol="UIAccessibilityTraitCausesPageTurn")
    public static native void Value__UIAccessibilityTraitCausesPageTurn(long v);
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification")
    public static native int Value__UIAccessibilityScreenChangedNotification();
    @GlobalValue(symbol="UIAccessibilityScreenChangedNotification")
    public static native void Value__UIAccessibilityScreenChangedNotification(int v);
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification")
    public static native int Value__UIAccessibilityLayoutChangedNotification();
    @GlobalValue(symbol="UIAccessibilityLayoutChangedNotification")
    public static native void Value__UIAccessibilityLayoutChangedNotification(int v);
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification")
    public static native int Value__UIAccessibilityAnnouncementNotification();
    @GlobalValue(symbol="UIAccessibilityAnnouncementNotification")
    public static native void Value__UIAccessibilityAnnouncementNotification(int v);
    @GlobalValue(symbol="UIAccessibilityAnnouncementDidFinishNotification")
    public static native String Value__UIAccessibilityAnnouncementDidFinishNotification();
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyStringValue")
    public static native String Value__UIAccessibilityAnnouncementKeyStringValue();
    @GlobalValue(symbol="UIAccessibilityAnnouncementKeyWasSuccessful")
    public static native String Value__UIAccessibilityAnnouncementKeyWasSuccessful();
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification")
    public static native int Value__UIAccessibilityPageScrolledNotification();
    @GlobalValue(symbol="UIAccessibilityPageScrolledNotification")
    public static native void Value__UIAccessibilityPageScrolledNotification(int v);
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePunctuation")
    public static native String Value__UIAccessibilitySpeechAttributePunctuation();
    @GlobalValue(symbol="UIAccessibilitySpeechAttributeLanguage")
    public static native String Value__UIAccessibilitySpeechAttributeLanguage();
    @GlobalValue(symbol="UIAccessibilitySpeechAttributePitch")
    public static native String Value__UIAccessibilitySpeechAttributePitch();
    @GlobalValue(symbol="UIAccessibilityVoiceOverStatusChanged")
    public static native String Value__UIAccessibilityVoiceOverStatusChanged();
    @GlobalValue(symbol="UIAccessibilityMonoAudioStatusDidChangeNotification")
    public static native String Value__UIAccessibilityMonoAudioStatusDidChangeNotification();
    @GlobalValue(symbol="UIAccessibilityClosedCaptioningStatusDidChangeNotification")
    public static native String Value__UIAccessibilityClosedCaptioningStatusDidChangeNotification();
    @GlobalValue(symbol="UIAccessibilityInvertColorsStatusDidChangeNotification")
    public static native String Value__UIAccessibilityInvertColorsStatusDidChangeNotification();
    @GlobalValue(symbol="UIAccessibilityGuidedAccessStatusDidChangeNotification")
    public static native String Value__UIAccessibilityGuidedAccessStatusDidChangeNotification();
    @GlobalValue(symbol="UIActivityTypePostToFacebook")
    public static native String Value__UIActivityTypePostToFacebook();
    @GlobalValue(symbol="UIActivityTypePostToTwitter")
    public static native String Value__UIActivityTypePostToTwitter();
    @GlobalValue(symbol="UIActivityTypePostToWeibo")
    public static native String Value__UIActivityTypePostToWeibo();
    @GlobalValue(symbol="UIActivityTypeMessage")
    public static native String Value__UIActivityTypeMessage();
    @GlobalValue(symbol="UIActivityTypeMail")
    public static native String Value__UIActivityTypeMail();
    @GlobalValue(symbol="UIActivityTypePrint")
    public static native String Value__UIActivityTypePrint();
    @GlobalValue(symbol="UIActivityTypeCopyToPasteboard")
    public static native String Value__UIActivityTypeCopyToPasteboard();
    @GlobalValue(symbol="UIActivityTypeAssignToContact")
    public static native String Value__UIActivityTypeAssignToContact();
    @GlobalValue(symbol="UIActivityTypeSaveToCameraRoll")
    public static native String Value__UIActivityTypeSaveToCameraRoll();
    @GlobalValue(symbol="UIActivityTypeAddToReadingList")
    public static native String Value__UIActivityTypeAddToReadingList();
    @GlobalValue(symbol="UIActivityTypePostToFlickr")
    public static native String Value__UIActivityTypePostToFlickr();
    @GlobalValue(symbol="UIActivityTypePostToVimeo")
    public static native String Value__UIActivityTypePostToVimeo();
    @GlobalValue(symbol="UIActivityTypePostToTencentWeibo")
    public static native String Value__UIActivityTypePostToTencentWeibo();
    @GlobalValue(symbol="UIActivityTypeAirDrop")
    public static native String Value__UIActivityTypeAirDrop();
    @GlobalValue(symbol="UIDeviceOrientationDidChangeNotification")
    public static native String Value__UIDeviceOrientationDidChangeNotification();
    @GlobalValue(symbol="UIDeviceBatteryStateDidChangeNotification")
    public static native String Value__UIDeviceBatteryStateDidChangeNotification();
    @GlobalValue(symbol="UIDeviceBatteryLevelDidChangeNotification")
    public static native String Value__UIDeviceBatteryLevelDidChangeNotification();
    @GlobalValue(symbol="UIDeviceProximityStateDidChangeNotification")
    public static native String Value__UIDeviceProximityStateDidChangeNotification();
    @GlobalValue(symbol="UITextInputTextBackgroundColorKey")
    public static native String Value__UITextInputTextBackgroundColorKey();
    @GlobalValue(symbol="UITextInputTextColorKey")
    public static native String Value__UITextInputTextColorKey();
    @GlobalValue(symbol="UITextInputTextFontKey")
    public static native String Value__UITextInputTextFontKey();
    @GlobalValue(symbol="UITextInputCurrentInputModeDidChangeNotification")
    public static native String Value__UITextInputCurrentInputModeDidChangeNotification();
    @GlobalValue(symbol="UITextFieldTextDidBeginEditingNotification")
    public static native String Value__UITextFieldTextDidBeginEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidEndEditingNotification")
    public static native String Value__UITextFieldTextDidEndEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidChangeNotification")
    public static native String Value__UITextFieldTextDidChangeNotification();
    @GlobalValue(symbol="UIApplicationInvalidInterfaceOrientationException")
    public static native String Value__UIApplicationInvalidInterfaceOrientationException();
    @GlobalValue(symbol="UIBackgroundTaskInvalid")
    public static native @MachineSizedUInt long Value__UIBackgroundTaskInvalid();
    @GlobalValue(symbol="UIMinimumKeepAliveTimeout")
    public static native double Value__UIMinimumKeepAliveTimeout();
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalMinimum")
    public static native double Value__UIApplicationBackgroundFetchIntervalMinimum();
    @GlobalValue(symbol="UIApplicationBackgroundFetchIntervalNever")
    public static native double Value__UIApplicationBackgroundFetchIntervalNever();
    @GlobalValue(symbol="UITrackingRunLoopMode")
    public static native String Value__UITrackingRunLoopMode();
    @GlobalValue(symbol="UIApplicationDidEnterBackgroundNotification")
    public static native String Value__UIApplicationDidEnterBackgroundNotification();
    @GlobalValue(symbol="UIApplicationWillEnterForegroundNotification")
    public static native String Value__UIApplicationWillEnterForegroundNotification();
    @GlobalValue(symbol="UIApplicationDidFinishLaunchingNotification")
    public static native String Value__UIApplicationDidFinishLaunchingNotification();
    @GlobalValue(symbol="UIApplicationDidBecomeActiveNotification")
    public static native String Value__UIApplicationDidBecomeActiveNotification();
    @GlobalValue(symbol="UIApplicationWillResignActiveNotification")
    public static native String Value__UIApplicationWillResignActiveNotification();
    @GlobalValue(symbol="UIApplicationDidReceiveMemoryWarningNotification")
    public static native String Value__UIApplicationDidReceiveMemoryWarningNotification();
    @GlobalValue(symbol="UIApplicationWillTerminateNotification")
    public static native String Value__UIApplicationWillTerminateNotification();
    @GlobalValue(symbol="UIApplicationSignificantTimeChangeNotification")
    public static native String Value__UIApplicationSignificantTimeChangeNotification();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarOrientationNotification")
    public static native String Value__UIApplicationWillChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarOrientationNotification")
    public static native String Value__UIApplicationDidChangeStatusBarOrientationNotification();
    @GlobalValue(symbol="UIApplicationStatusBarOrientationUserInfoKey")
    public static native String Value__UIApplicationStatusBarOrientationUserInfoKey();
    @GlobalValue(symbol="UIApplicationWillChangeStatusBarFrameNotification")
    public static native String Value__UIApplicationWillChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationDidChangeStatusBarFrameNotification")
    public static native String Value__UIApplicationDidChangeStatusBarFrameNotification();
    @GlobalValue(symbol="UIApplicationStatusBarFrameUserInfoKey")
    public static native String Value__UIApplicationStatusBarFrameUserInfoKey();
    @GlobalValue(symbol="UIApplicationBackgroundRefreshStatusDidChangeNotification")
    public static native String Value__UIApplicationBackgroundRefreshStatusDidChangeNotification();
    @GlobalValue(symbol="UIApplicationLaunchOptionsURLKey")
    public static native String Value__UIApplicationLaunchOptionsURLKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsSourceApplicationKey")
    public static native String Value__UIApplicationLaunchOptionsSourceApplicationKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsRemoteNotificationKey")
    public static native String Value__UIApplicationLaunchOptionsRemoteNotificationKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocalNotificationKey")
    public static native String Value__UIApplicationLaunchOptionsLocalNotificationKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsAnnotationKey")
    public static native String Value__UIApplicationLaunchOptionsAnnotationKey();
    @GlobalValue(symbol="UIApplicationProtectedDataWillBecomeUnavailable")
    public static native String Value__UIApplicationProtectedDataWillBecomeUnavailable();
    @GlobalValue(symbol="UIApplicationProtectedDataDidBecomeAvailable")
    public static native String Value__UIApplicationProtectedDataDidBecomeAvailable();
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocationKey")
    public static native String Value__UIApplicationLaunchOptionsLocationKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsNewsstandDownloadsKey")
    public static native String Value__UIApplicationLaunchOptionsNewsstandDownloadsKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothCentralsKey")
    public static native String Value__UIApplicationLaunchOptionsBluetoothCentralsKey();
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothPeripheralsKey")
    public static native String Value__UIApplicationLaunchOptionsBluetoothPeripheralsKey();
    @GlobalValue(symbol="UIContentSizeCategoryExtraSmall")
    public static native String Value__UIContentSizeCategoryExtraSmall();
    @GlobalValue(symbol="UIContentSizeCategorySmall")
    public static native String Value__UIContentSizeCategorySmall();
    @GlobalValue(symbol="UIContentSizeCategoryMedium")
    public static native String Value__UIContentSizeCategoryMedium();
    @GlobalValue(symbol="UIContentSizeCategoryLarge")
    public static native String Value__UIContentSizeCategoryLarge();
    @GlobalValue(symbol="UIContentSizeCategoryExtraLarge")
    public static native String Value__UIContentSizeCategoryExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraLarge")
    public static native String Value__UIContentSizeCategoryExtraExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryExtraExtraExtraLarge")
    public static native String Value__UIContentSizeCategoryExtraExtraExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityMedium")
    public static native String Value__UIContentSizeCategoryAccessibilityMedium();
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityLarge")
    public static native String Value__UIContentSizeCategoryAccessibilityLarge();
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraLarge")
    public static native String Value__UIContentSizeCategoryAccessibilityExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraLarge")
    public static native String Value__UIContentSizeCategoryAccessibilityExtraExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryAccessibilityExtraExtraExtraLarge")
    public static native String Value__UIContentSizeCategoryAccessibilityExtraExtraExtraLarge();
    @GlobalValue(symbol="UIContentSizeCategoryDidChangeNotification")
    public static native String Value__UIContentSizeCategoryDidChangeNotification();
    @GlobalValue(symbol="UIContentSizeCategoryNewValueKey")
    public static native String Value__UIContentSizeCategoryNewValueKey();
    @GlobalValue(symbol="UIApplicationUserDidTakeScreenshotNotification")
    public static native String Value__UIApplicationUserDidTakeScreenshotNotification();
    @GlobalValue(symbol="UIStateRestorationViewControllerStoryboardKey")
    public static native String Value__UIStateRestorationViewControllerStoryboardKey();
    @GlobalValue(symbol="UIApplicationStateRestorationBundleVersionKey")
    public static native String Value__UIApplicationStateRestorationBundleVersionKey();
    @GlobalValue(symbol="UIApplicationStateRestorationUserInterfaceIdiomKey")
    public static native String Value__UIApplicationStateRestorationUserInterfaceIdiomKey();
    @GlobalValue(symbol="UIApplicationStateRestorationTimestampKey")
    public static native String Value__UIApplicationStateRestorationTimestampKey();
    @GlobalValue(symbol="UIApplicationStateRestorationSystemVersionKey")
    public static native String Value__UIApplicationStateRestorationSystemVersionKey();
    @GlobalValue(symbol="UIViewControllerHierarchyInconsistencyException")
    public static native String Value__UIViewControllerHierarchyInconsistencyException();
    @GlobalValue(symbol="UICollectionElementKindSectionHeader")
    public static native String Value__UICollectionElementKindSectionHeader();
    @GlobalValue(symbol="UICollectionElementKindSectionFooter")
    public static native String Value__UICollectionElementKindSectionFooter();
    @GlobalValue(symbol="UIDocumentStateChangedNotification")
    public static native String Value__UIDocumentStateChangedNotification();
    @GlobalValue(symbol="UINavigationControllerHideShowBarDuration")
    public static native @MachineSizedFloat double Value__UINavigationControllerHideShowBarDuration();
    @GlobalValue(symbol="UIImagePickerControllerMediaType")
    public static native String Value__UIImagePickerControllerMediaType();
    @GlobalValue(symbol="UIImagePickerControllerOriginalImage")
    public static native String Value__UIImagePickerControllerOriginalImage();
    @GlobalValue(symbol="UIImagePickerControllerEditedImage")
    public static native String Value__UIImagePickerControllerEditedImage();
    @GlobalValue(symbol="UIImagePickerControllerCropRect")
    public static native String Value__UIImagePickerControllerCropRect();
    @GlobalValue(symbol="UIImagePickerControllerMediaURL")
    public static native String Value__UIImagePickerControllerMediaURL();
    @GlobalValue(symbol="UIImagePickerControllerReferenceURL")
    public static native String Value__UIImagePickerControllerReferenceURL();
    @GlobalValue(symbol="UIImagePickerControllerMediaMetadata")
    public static native String Value__UIImagePickerControllerMediaMetadata();
    @GlobalValue(symbol="UILocalNotificationDefaultSoundName")
    public static native String Value__UILocalNotificationDefaultSoundName();
    @GlobalValue(symbol="UIMenuControllerWillShowMenuNotification")
    public static native String Value__UIMenuControllerWillShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidShowMenuNotification")
    public static native String Value__UIMenuControllerDidShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerWillHideMenuNotification")
    public static native String Value__UIMenuControllerWillHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidHideMenuNotification")
    public static native String Value__UIMenuControllerDidHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerMenuFrameDidChangeNotification")
    public static native String Value__UIMenuControllerMenuFrameDidChangeNotification();
    @GlobalValue(symbol="UINibExternalObjects")
    public static native String Value__UINibExternalObjects();
    @GlobalValue(symbol="UINibProxiedObjectsKey")
    public static native String Value__UINibProxiedObjectsKey();
    @GlobalValue(symbol="UIPageViewControllerOptionSpineLocationKey")
    public static native String Value__UIPageViewControllerOptionSpineLocationKey();
    @GlobalValue(symbol="UIPageViewControllerOptionInterPageSpacingKey")
    public static native String Value__UIPageViewControllerOptionInterPageSpacingKey();
    @GlobalValue(symbol="UIPasteboardNameGeneral")
    public static native String Value__UIPasteboardNameGeneral();
    @GlobalValue(symbol="UIPasteboardNameFind")
    public static native String Value__UIPasteboardNameFind();
    @GlobalValue(symbol="UIPasteboardChangedNotification")
    public static native String Value__UIPasteboardChangedNotification();
    @GlobalValue(symbol="UIPasteboardChangedTypesAddedKey")
    public static native String Value__UIPasteboardChangedTypesAddedKey();
    @GlobalValue(symbol="UIPasteboardChangedTypesRemovedKey")
    public static native String Value__UIPasteboardChangedTypesRemovedKey();
    @GlobalValue(symbol="UIPasteboardRemovedNotification")
    public static native String Value__UIPasteboardRemovedNotification();
    @GlobalValue(symbol="UIPasteboardTypeListString")
    public static native NSArray<?> Value__UIPasteboardTypeListString();
    @GlobalValue(symbol="UIPasteboardTypeListString")
    public static native void Value__UIPasteboardTypeListString(NSArray<?> v);
    @GlobalValue(symbol="UIPasteboardTypeListURL")
    public static native NSArray<?> Value__UIPasteboardTypeListURL();
    @GlobalValue(symbol="UIPasteboardTypeListURL")
    public static native void Value__UIPasteboardTypeListURL(NSArray<?> v);
    @GlobalValue(symbol="UIPasteboardTypeListImage")
    public static native NSArray<?> Value__UIPasteboardTypeListImage();
    @GlobalValue(symbol="UIPasteboardTypeListImage")
    public static native void Value__UIPasteboardTypeListImage(NSArray<?> v);
    @GlobalValue(symbol="UIPasteboardTypeListColor")
    public static native NSArray<?> Value__UIPasteboardTypeListColor();
    @GlobalValue(symbol="UIPasteboardTypeListColor")
    public static native void Value__UIPasteboardTypeListColor(NSArray<?> v);
    @GlobalValue(symbol="UIPrintErrorDomain")
    public static native String Value__UIPrintErrorDomain();
    @GlobalValue(symbol="UIScreenDidConnectNotification")
    public static native String Value__UIScreenDidConnectNotification();
    @GlobalValue(symbol="UIScreenDidDisconnectNotification")
    public static native String Value__UIScreenDidDisconnectNotification();
    @GlobalValue(symbol="UIScreenModeDidChangeNotification")
    public static native String Value__UIScreenModeDidChangeNotification();
    @GlobalValue(symbol="UIScreenBrightnessDidChangeNotification")
    public static native String Value__UIScreenBrightnessDidChangeNotification();
    @GlobalValue(symbol="UITransitionContextFromViewControllerKey")
    public static native String Value__UITransitionContextFromViewControllerKey();
    @GlobalValue(symbol="UITransitionContextToViewControllerKey")
    public static native String Value__UITransitionContextToViewControllerKey();
    @GlobalValue(symbol="UITextViewTextDidBeginEditingNotification")
    public static native String Value__UITextViewTextDidBeginEditingNotification();
    @GlobalValue(symbol="UITextViewTextDidChangeNotification")
    public static native String Value__UITextViewTextDidChangeNotification();
    @GlobalValue(symbol="UITextViewTextDidEndEditingNotification")
    public static native String Value__UITextViewTextDidEndEditingNotification();
    @GlobalValue(symbol="UIWindowLevelNormal")
    public static native @MachineSizedFloat double Value__UIWindowLevelNormal();
    @GlobalValue(symbol="UIWindowLevelAlert")
    public static native @MachineSizedFloat double Value__UIWindowLevelAlert();
    @GlobalValue(symbol="UIWindowLevelStatusBar")
    public static native @MachineSizedFloat double Value__UIWindowLevelStatusBar();
    @GlobalValue(symbol="UIWindowDidBecomeVisibleNotification")
    public static native String Value__UIWindowDidBecomeVisibleNotification();
    @GlobalValue(symbol="UIWindowDidBecomeHiddenNotification")
    public static native String Value__UIWindowDidBecomeHiddenNotification();
    @GlobalValue(symbol="UIWindowDidBecomeKeyNotification")
    public static native String Value__UIWindowDidBecomeKeyNotification();
    @GlobalValue(symbol="UIWindowDidResignKeyNotification")
    public static native String Value__UIWindowDidResignKeyNotification();
    @GlobalValue(symbol="UIKeyboardWillShowNotification")
    public static native String Value__UIKeyboardWillShowNotification();
    @GlobalValue(symbol="UIKeyboardDidShowNotification")
    public static native String Value__UIKeyboardDidShowNotification();
    @GlobalValue(symbol="UIKeyboardWillHideNotification")
    public static native String Value__UIKeyboardWillHideNotification();
    @GlobalValue(symbol="UIKeyboardDidHideNotification")
    public static native String Value__UIKeyboardDidHideNotification();
    @GlobalValue(symbol="UIKeyboardFrameBeginUserInfoKey")
    public static native String Value__UIKeyboardFrameBeginUserInfoKey();
    @GlobalValue(symbol="UIKeyboardFrameEndUserInfoKey")
    public static native String Value__UIKeyboardFrameEndUserInfoKey();
    @GlobalValue(symbol="UIKeyboardAnimationDurationUserInfoKey")
    public static native String Value__UIKeyboardAnimationDurationUserInfoKey();
    @GlobalValue(symbol="UIKeyboardAnimationCurveUserInfoKey")
    public static native String Value__UIKeyboardAnimationCurveUserInfoKey();
    @GlobalValue(symbol="UIKeyboardWillChangeFrameNotification")
    public static native String Value__UIKeyboardWillChangeFrameNotification();
    @GlobalValue(symbol="UIKeyboardDidChangeFrameNotification")
    public static native String Value__UIKeyboardDidChangeFrameNotification();
    @GlobalValue(symbol="UIKeyboardCenterBeginUserInfoKey")
    public static native String Value__UIKeyboardCenterBeginUserInfoKey();
    @GlobalValue(symbol="UIKeyboardCenterEndUserInfoKey")
    public static native String Value__UIKeyboardCenterEndUserInfoKey();
    @GlobalValue(symbol="UIKeyboardBoundsUserInfoKey")
    public static native String Value__UIKeyboardBoundsUserInfoKey();
    
    @Bridge(symbol="NSStringFromCGPoint")
    public static native String function__NSStringFromCGPoint(@ByVal CGPoint point);
    @Bridge(symbol="NSStringFromCGSize")
    public static native String function__NSStringFromCGSize(@ByVal CGSize size);
    @Bridge(symbol="NSStringFromCGRect")
    public static native String function__NSStringFromCGRect(@ByVal CGRect rect);
    @Bridge(symbol="NSStringFromCGAffineTransform")
    public static native String function__NSStringFromCGAffineTransform(@ByVal CGAffineTransform transform);
    @Bridge(symbol="NSStringFromUIEdgeInsets")
    public static native String function__NSStringFromUIEdgeInsets(@ByVal UIEdgeInsets insets);
    @Bridge(symbol="NSStringFromUIOffset")
    public static native String function__NSStringFromUIOffset(@ByVal UIOffset offset);
    @Bridge(symbol="CGPointFromString")
    public static native @ByVal CGPoint function__CGPointFromString(String string);
    @Bridge(symbol="CGSizeFromString")
    public static native @ByVal CGSize function__CGSizeFromString(String string);
    @Bridge(symbol="CGRectFromString")
    public static native @ByVal CGRect function__CGRectFromString(String string);
    @Bridge(symbol="CGAffineTransformFromString")
    public static native @ByVal CGAffineTransform function__CGAffineTransformFromString(String string);
    @Bridge(symbol="UIEdgeInsetsFromString")
    public static native @ByVal UIEdgeInsets function__UIEdgeInsetsFromString(String string);
    @Bridge(symbol="UIOffsetFromString")
    public static native @ByVal UIOffset function__UIOffsetFromString(String string);
    @Bridge(symbol="UIImagePNGRepresentation")
    public static native NSData function__UIImagePNGRepresentation(UIImage image);
    @Bridge(symbol="UIImageJPEGRepresentation")
    public static native NSData function__UIImageJPEGRepresentation(UIImage image, @MachineSizedFloat double compressionQuality);
    @Bridge(symbol="UIAccessibilityZoomFocusChanged")
    public static native void function__UIAccessibilityZoomFocusChanged(UIAccessibilityZoomType type, @ByVal CGRect frame, UIView view);
    @Bridge(symbol="UIAccessibilityRegisterGestureConflictWithZoom")
    public static native void function__UIAccessibilityRegisterGestureConflictWithZoom();
    @Bridge(symbol="UIGuidedAccessRestrictionStateForIdentifier")
    public static native UIGuidedAccessRestrictionState function__UIGuidedAccessRestrictionStateForIdentifier(String restrictionIdentifier);
    @Bridge(symbol="UIAccessibilityConvertFrameToScreenCoordinates")
    public static native @ByVal CGRect function__UIAccessibilityConvertFrameToScreenCoordinates(@ByVal CGRect rect, UIView view);
    @Bridge(symbol="UIAccessibilityConvertPathToScreenCoordinates")
    public static native UIBezierPath function__UIAccessibilityConvertPathToScreenCoordinates(UIBezierPath path, UIView view);
    @Bridge(symbol="UIAccessibilityPostNotification")
    public static native void function__UIAccessibilityPostNotification(int notification, NSObject argument);
    @Bridge(symbol="UIAccessibilityIsVoiceOverRunning")
    public static native boolean function__UIAccessibilityIsVoiceOverRunning();
    @Bridge(symbol="UIAccessibilityIsMonoAudioEnabled")
    public static native boolean function__UIAccessibilityIsMonoAudioEnabled();
    @Bridge(symbol="UIAccessibilityIsClosedCaptioningEnabled")
    public static native boolean function__UIAccessibilityIsClosedCaptioningEnabled();
    @Bridge(symbol="UIAccessibilityIsInvertColorsEnabled")
    public static native boolean function__UIAccessibilityIsInvertColorsEnabled();
    @Bridge(symbol="UIAccessibilityIsGuidedAccessEnabled")
    public static native boolean function__UIAccessibilityIsGuidedAccessEnabled();
    @Bridge(symbol="UIAccessibilityRequestGuidedAccessSession")
    public static native void function__UIAccessibilityRequestGuidedAccessSession(boolean enable, @Block VoidBooleanBlock completionHandler);
    @Bridge(symbol="UIApplicationMain")
    public static native int function__UIApplicationMain(int argc, CharPtr.CharPtrPtr argv, String principalClassName, String delegateClassName);
    @Bridge(symbol="UIGraphicsGetCurrentContext")
    public static native CGContext function__UIGraphicsGetCurrentContext();
    @Bridge(symbol="UIGraphicsPushContext")
    public static native void function__UIGraphicsPushContext(CGContext context);
    @Bridge(symbol="UIGraphicsPopContext")
    public static native void function__UIGraphicsPopContext();
    @Bridge(symbol="UIRectFillUsingBlendMode")
    public static native void function__UIRectFillUsingBlendMode(@ByVal CGRect rect, CGBlendMode blendMode);
    @Bridge(symbol="UIRectFill")
    public static native void function__UIRectFill(@ByVal CGRect rect);
    @Bridge(symbol="UIRectFrameUsingBlendMode")
    public static native void function__UIRectFrameUsingBlendMode(@ByVal CGRect rect, CGBlendMode blendMode);
    @Bridge(symbol="UIRectFrame")
    public static native void function__UIRectFrame(@ByVal CGRect rect);
    @Bridge(symbol="UIRectClip")
    public static native void function__UIRectClip(@ByVal CGRect rect);
    @Bridge(symbol="UIGraphicsBeginImageContext")
    public static native void function__UIGraphicsBeginImageContext(@ByVal CGSize size);
    @Bridge(symbol="UIGraphicsBeginImageContextWithOptions")
    public static native void function__UIGraphicsBeginImageContextWithOptions(@ByVal CGSize size, boolean opaque, @MachineSizedFloat double scale);
    @Bridge(symbol="UIGraphicsGetImageFromCurrentImageContext")
    public static native UIImage function__UIGraphicsGetImageFromCurrentImageContext();
    @Bridge(symbol="UIGraphicsEndImageContext")
    public static native void function__UIGraphicsEndImageContext();
    @Bridge(symbol="UIGraphicsBeginPDFContextToFile")
    public static native boolean function__UIGraphicsBeginPDFContextToFile(String path, @ByVal CGRect bounds, NSDictionary<?, ?> documentInfo);
    @Bridge(symbol="UIGraphicsBeginPDFContextToData")
    public static native void function__UIGraphicsBeginPDFContextToData(NSMutableData data, @ByVal CGRect bounds, NSDictionary<?, ?> documentInfo);
    @Bridge(symbol="UIGraphicsEndPDFContext")
    public static native void function__UIGraphicsEndPDFContext();
    @Bridge(symbol="UIGraphicsBeginPDFPage")
    public static native void function__UIGraphicsBeginPDFPage();
    @Bridge(symbol="UIGraphicsBeginPDFPageWithInfo")
    public static native void function__UIGraphicsBeginPDFPageWithInfo(@ByVal CGRect bounds, NSDictionary<?, ?> pageInfo);
    @Bridge(symbol="UIGraphicsGetPDFContextBounds")
    public static native @ByVal CGRect function__UIGraphicsGetPDFContextBounds();
    @Bridge(symbol="UIGraphicsSetPDFContextURLForRect")
    public static native void function__UIGraphicsSetPDFContextURLForRect(NSURL url, @ByVal CGRect rect);
    @Bridge(symbol="UIGraphicsAddPDFContextDestinationAtPoint")
    public static native void function__UIGraphicsAddPDFContextDestinationAtPoint(String name, @ByVal CGPoint point);
    @Bridge(symbol="UIGraphicsSetPDFContextDestinationForRect")
    public static native void function__UIGraphicsSetPDFContextDestinationForRect(String name, @ByVal CGRect rect);
    @Bridge(symbol="UIImageWriteToSavedPhotosAlbum")
    public static native void function__UIImageWriteToSavedPhotosAlbum(UIImage image, NSObject completionTarget, Selector completionSelector, VoidPtr contextInfo);
    @Bridge(symbol="UIVideoAtPathIsCompatibleWithSavedPhotosAlbum")
    public static native boolean function__UIVideoAtPathIsCompatibleWithSavedPhotosAlbum(String videoPath);
    @Bridge(symbol="UISaveVideoAtPathToSavedPhotosAlbum")
    public static native void function__UISaveVideoAtPathToSavedPhotosAlbum(String videoPath, NSObject completionTarget, Selector completionSelector, VoidPtr contextInfo);
    /*</methods>*/
}
