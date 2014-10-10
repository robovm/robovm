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
    public static final long Constant__UIUserInterfaceSizeClassUnspecified = 0L;
    public static final long Constant__UIUserInterfaceSizeClassCompact = 1L;
    public static final long Constant__UIUserInterfaceSizeClassRegular = 2L;
    public static final long Constant__UITableViewRowActionStyleDefault = 0L;
    public static final long Constant__UITableViewRowActionStyleDestructive = 0L;
    public static final long Constant__UITableViewRowActionStyleNormal = 1L;
    public static final long Constant__UIAccessibilityNavigationStyleAutomatic = 0L;
    public static final long Constant__UIAccessibilityNavigationStyleSeparate = 1L;
    public static final long Constant__UIAccessibilityNavigationStyleCombined = 2L;
    public static final long Constant__UIAlertActionStyleDefault = 0L;
    public static final long Constant__UIAlertActionStyleCancel = 1L;
    public static final long Constant__UIAlertActionStyleDestructive = 2L;
    public static final long Constant__UIAlertControllerStyleActionSheet = 0L;
    public static final long Constant__UIAlertControllerStyleAlert = 1L;
    public static final long Constant__UIDocumentPickerModeImport = 0L;
    public static final long Constant__UIDocumentPickerModeOpen = 1L;
    public static final long Constant__UIDocumentPickerModeExportToService = 2L;
    public static final long Constant__UIDocumentPickerModeMoveToService = 3L;
    public static final long Constant__UIDocumentMenuOrderFirst = 0L;
    public static final long Constant__UIDocumentMenuOrderLast = 1L;
    public static final long Constant__UIBlurEffectStyleExtraLight = 0L;
    public static final long Constant__UIBlurEffectStyleLight = 1L;
    public static final long Constant__UIBlurEffectStyleDark = 2L;
    public static final long Constant__UIPrinterJobTypeUnknown = 0L;
    public static final long Constant__UIPrinterJobTypeDocument = 1L;
    public static final long Constant__UIPrinterJobTypeEnvelope = 2L;
    public static final long Constant__UIPrinterJobTypeLabel = 4L;
    public static final long Constant__UIPrinterJobTypePhoto = 8L;
    public static final long Constant__UIPrinterJobTypeReceipt = 16L;
    public static final long Constant__UIPrinterJobTypeRoll = 32L;
    public static final long Constant__UIPrinterJobTypeLargeFormat = 64L;
    public static final long Constant__UIPrinterJobTypePostcard = 128L;
    public static final long Constant__UISplitViewControllerDisplayModeAutomatic = 0L;
    public static final long Constant__UISplitViewControllerDisplayModePrimaryHidden = 1L;
    public static final long Constant__UISplitViewControllerDisplayModeAllVisible = 2L;
    public static final long Constant__UISplitViewControllerDisplayModePrimaryOverlay = 3L;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutPriorityRequired", optional=true)
    public static native float Value__UILayoutPriorityRequired();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutPriorityDefaultHigh", optional=true)
    public static native float Value__UILayoutPriorityDefaultHigh();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutPriorityDefaultLow", optional=true)
    public static native float Value__UILayoutPriorityDefaultLow();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutPriorityFittingSizeLevel", optional=true)
    public static native float Value__UILayoutPriorityFittingSizeLevel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIAccessibilityNotificationSwitchControlIdentifier", optional=true)
    public static native String Value__UIAccessibilityNotificationSwitchControlIdentifier();
    @GlobalValue(symbol="UITrackingRunLoopMode", optional=true)
    public static native String Value__UITrackingRunLoopMode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIApplicationOpenSettingsURLString", optional=true)
    public static native String Value__UIApplicationOpenSettingsURLString();
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
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UIViewControllerShowDetailTargetDidChangeNotification", optional=true)
    public static native String Value__UIViewControllerShowDetailTargetDidChangeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSUserActivityDocumentURLKey", optional=true)
    public static native String Value__NSUserActivityDocumentURLKey();
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
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextFromViewKey", optional=true)
    public static native String Value__UITransitionContextFromViewKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UITransitionContextToViewKey", optional=true)
    public static native String Value__UITransitionContextToViewKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="UISplitViewControllerAutomaticDimension", optional=true)
    public static native @MachineSizedFloat double Value__UISplitViewControllerAutomaticDimension();
    
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
