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
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
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
