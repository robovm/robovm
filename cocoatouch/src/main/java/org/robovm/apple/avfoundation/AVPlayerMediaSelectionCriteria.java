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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayerMediaSelectionCriteria/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPlayerMediaSelectionCriteriaPtr extends Ptr<AVPlayerMediaSelectionCriteria, AVPlayerMediaSelectionCriteriaPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPlayerMediaSelectionCriteria.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPlayerMediaSelectionCriteria() {}
    protected AVPlayerMediaSelectionCriteria(SkipInit skipInit) { super(skipInit); }
    public AVPlayerMediaSelectionCriteria(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> preferredLanguages, @org.robovm.rt.bro.annotation.Marshaler(AVMediaCharacteristic.AsListMarshaler.class) List<AVMediaCharacteristic> preferredMediaCharacteristics) { super((SkipInit) null); initObject(init(preferredLanguages, preferredMediaCharacteristics)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "preferredLanguages")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPreferredLanguages();
    @Property(selector = "preferredMediaCharacteristics")
    public native @org.robovm.rt.bro.annotation.Marshaler(AVMediaCharacteristic.AsListMarshaler.class) List<AVMediaCharacteristic> getPreferredMediaCharacteristics();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPreferredLanguages:preferredMediaCharacteristics:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> preferredLanguages, @org.robovm.rt.bro.annotation.Marshaler(AVMediaCharacteristic.AsListMarshaler.class) List<AVMediaCharacteristic> preferredMediaCharacteristics);
    /*</methods>*/
}
