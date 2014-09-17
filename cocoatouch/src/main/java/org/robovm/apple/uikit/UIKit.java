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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIKit.class); }/*</bind>*/
    /*<constants>*/
    public static final int SegmentedControlNoSegment = -1;
    public static final int AttachmentCharacter = 65532;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureTypeIdentifierKey", optional=true)
    public static native String Value__UIFontFeatureTypeIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontFeatureSelectorIdentifierKey", optional=true)
    public static native String Value__UIFontFeatureSelectorIdentifierKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIViewNoIntrinsicMetric", optional=true)
    public static native @MachineSizedFloat double Value__UIViewNoIntrinsicMetric();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingCompressedSize", optional=true)
    public static native @ByVal CGSize Value__UILayoutFittingCompressedSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingExpandedSize", optional=true)
    public static native @ByVal CGSize Value__UILayoutFittingExpandedSize();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UITableViewIndexSearch", optional=true)
    public static native String Value__UITableViewIndexSearch();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UITableViewAutomaticDimension", optional=true)
    public static native @MachineSizedFloat double Value__UITableViewAutomaticDimension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIBackgroundTaskInvalid", optional=true)
    public static native @MachineSizedUInt long Value__UIBackgroundTaskInvalid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIMinimumKeepAliveTimeout", optional=true)
    public static native double Value__UIMinimumKeepAliveTimeout();
    @GlobalValue(symbol="UITrackingRunLoopMode", optional=true)
    public static native String Value__UITrackingRunLoopMode();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIStateRestorationViewControllerStoryboardKey", optional=true)
    public static native String Value__UIStateRestorationViewControllerStoryboardKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationBundleVersionKey", optional=true)
    public static native String Value__UIApplicationStateRestorationBundleVersionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationUserInterfaceIdiomKey", optional=true)
    public static native String Value__UIApplicationStateRestorationUserInterfaceIdiomKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationTimestampKey", optional=true)
    public static native String Value__UIApplicationStateRestorationTimestampKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationStateRestorationSystemVersionKey", optional=true)
    public static native String Value__UIApplicationStateRestorationSystemVersionKey();
    @GlobalValue(symbol="UICollectionElementKindSectionHeader", optional=true)
    public static native String Value__UICollectionElementKindSectionHeader();
    @GlobalValue(symbol="UICollectionElementKindSectionFooter", optional=true)
    public static native String Value__UICollectionElementKindSectionFooter();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIDocumentStateChangedNotification", optional=true)
    public static native String Value__UIDocumentStateChangedNotification();
    @GlobalValue(symbol="UINavigationControllerHideShowBarDuration", optional=true)
    public static native @MachineSizedFloat double Value__UINavigationControllerHideShowBarDuration();
    @GlobalValue(symbol="UIImagePickerControllerMediaType", optional=true)
    public static native String Value__UIImagePickerControllerMediaType();
    @GlobalValue(symbol="UIImagePickerControllerOriginalImage", optional=true)
    public static native String Value__UIImagePickerControllerOriginalImage();
    @GlobalValue(symbol="UIImagePickerControllerEditedImage", optional=true)
    public static native String Value__UIImagePickerControllerEditedImage();
    @GlobalValue(symbol="UIImagePickerControllerCropRect", optional=true)
    public static native String Value__UIImagePickerControllerCropRect();
    @GlobalValue(symbol="UIImagePickerControllerMediaURL", optional=true)
    public static native String Value__UIImagePickerControllerMediaURL();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerReferenceURL", optional=true)
    public static native String Value__UIImagePickerControllerReferenceURL();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="UIImagePickerControllerMediaMetadata", optional=true)
    public static native String Value__UIImagePickerControllerMediaMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UILocalNotificationDefaultSoundName", optional=true)
    public static native String Value__UILocalNotificationDefaultSoundName();
    @GlobalValue(symbol="UIPasteboardNameGeneral", optional=true)
    public static native String Value__UIPasteboardNameGeneral();
    @GlobalValue(symbol="UIPasteboardNameFind", optional=true)
    public static native String Value__UIPasteboardNameFind();
    @GlobalValue(symbol="UIPasteboardChangedNotification", optional=true)
    public static native String Value__UIPasteboardChangedNotification();
    @GlobalValue(symbol="UIPasteboardChangedTypesAddedKey", optional=true)
    public static native String Value__UIPasteboardChangedTypesAddedKey();
    @GlobalValue(symbol="UIPasteboardChangedTypesRemovedKey", optional=true)
    public static native String Value__UIPasteboardChangedTypesRemovedKey();
    @GlobalValue(symbol="UIPasteboardRemovedNotification", optional=true)
    public static native String Value__UIPasteboardRemovedNotification();
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
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextFromViewControllerKey", optional=true)
    public static native String Value__UITransitionContextFromViewControllerKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextToViewControllerKey", optional=true)
    public static native String Value__UITransitionContextToViewControllerKey();
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="NSTextAlignmentToCTTextAlignment", optional=true)
    public static native CTTextAlignment convertNSTextAlignmentToCTTextAlignment(NSTextAlignment nsTextAlignment);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="NSTextAlignmentFromCTTextAlignment", optional=true)
    public static native NSTextAlignment convertCTTextAlignmentToNSTextAlignment(CTTextAlignment ctTextAlignment);
    /*</methods>*/
}
