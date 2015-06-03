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
package org.robovm.apple.avfoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMediaSelectionOption/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMediaSelectionOptionPtr extends Ptr<AVMediaSelectionOption, AVMediaSelectionOptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMediaSelectionOption.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMediaSelectionOption() {}
    protected AVMediaSelectionOption(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "mediaType")
    public native AVMediaType getMediaType();
    @Property(selector = "mediaSubTypes")
    public native NSArray<NSNumber> getMediaSubTypes();
    @Property(selector = "isPlayable")
    public native boolean isPlayable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "extendedLanguageTag")
    public native String getExtendedLanguageTag();
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "commonMetadata")
    public native NSArray<AVMetadataItem> getCommonMetadata();
    @Property(selector = "availableMetadataFormats")
    public native @org.robovm.rt.bro.annotation.Marshaler(AVMetadataFormat.AsListMarshaler.class) List<AVMetadataFormat> getAvailableMetadataFormats();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "displayName")
    public native String getDisplayName();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "hasMediaCharacteristic:")
    public native boolean hasMediaCharacteristic(AVMediaCharacteristic mediaCharacteristic);
    @Method(selector = "metadataForFormat:")
    public native NSArray<AVMetadataItem> getMetadata(AVMetadataFormat format);
    @Method(selector = "associatedMediaSelectionOptionInMediaSelectionGroup:")
    public native AVMediaSelectionOption getAssociatedMediaSelectionOption(AVMediaSelectionGroup mediaSelectionGroup);
    @Method(selector = "propertyList")
    public native NSPropertyList getPropertyList();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "displayNameWithLocale:")
    public native String getDisplayName(NSLocale locale);
    /*</methods>*/
}
