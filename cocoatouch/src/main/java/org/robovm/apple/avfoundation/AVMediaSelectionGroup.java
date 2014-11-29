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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMediaSelectionGroup/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMediaSelectionGroupPtr extends Ptr<AVMediaSelectionGroup, AVMediaSelectionGroupPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMediaSelectionGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMediaSelectionGroup() {}
    protected AVMediaSelectionGroup(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "options")
    public native NSArray<AVMediaSelectionOption> getOptions();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "defaultOption")
    public native AVMediaSelectionOption getDefaultOption();
    @Property(selector = "allowsEmptySelection")
    public native boolean isAllowsEmptySelection();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "mediaSelectionOptionWithPropertyList:")
    public native AVMediaSelectionOption getMediaSelectionOption(NSObject plist);
    @Method(selector = "playableMediaSelectionOptionsFromArray:")
    public static native NSArray<AVMediaSelectionOption> getPlayableMediaSelectionOptions(NSArray<AVMediaSelectionOption> mediaSelectionOptions);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "mediaSelectionOptionsFromArray:filteredAndSortedAccordingToPreferredLanguages:")
    public static native NSArray<AVMediaSelectionOption> getMediaSelectionOptions(NSArray<AVMediaSelectionOption> mediaSelectionOptions, NSArray<NSString> preferredLanguages);
    @Method(selector = "mediaSelectionOptionsFromArray:withLocale:")
    public static native NSArray<AVMediaSelectionOption> getMediaSelectionOption(NSArray<AVMediaSelectionOption> mediaSelectionOptions, NSLocale locale);
    @Method(selector = "mediaSelectionOptionsFromArray:withMediaCharacteristics:")
    public static native NSArray<AVMediaSelectionOption> getMediaSelectionOptionsWithMediaCharacteristics(NSArray<AVMediaSelectionOption> mediaSelectionOptions, NSArray<NSString> mediaCharacteristics);
    @Method(selector = "mediaSelectionOptionsFromArray:withoutMediaCharacteristics:")
    public static native NSArray<AVMediaSelectionOption> getMediaSelectionOptionsWithoutMediaCharacteristics(NSArray<AVMediaSelectionOption> mediaSelectionOptions, NSArray<NSString> mediaCharacteristics);
    /*</methods>*/
}
