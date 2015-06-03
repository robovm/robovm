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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionDataSourceDescription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioSessionDataSourceDescriptionPtr extends Ptr<AVAudioSessionDataSourceDescription, AVAudioSessionDataSourceDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioSessionDataSourceDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioSessionDataSourceDescription() {}
    protected AVAudioSessionDataSourceDescription(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "dataSourceID")
    public native NSNumber getDataSourceID();
    @Property(selector = "dataSourceName")
    public native String getDataSourceName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "location")
    public native AVAudioSessionLocation getLocation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "orientation")
    public native AVAudioSessionOrientation getOrientation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "supportedPolarPatterns")
    public native @org.robovm.rt.bro.annotation.Marshaler(AVAudioSessionPolarPattern.AsListMarshaler.class) List<AVAudioSessionPolarPattern> getSupportedPolarPatterns();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "selectedPolarPattern")
    public native AVAudioSessionPolarPattern getSelectedPolarPattern();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredPolarPattern")
    public native AVAudioSessionPolarPattern getPreferredPolarPattern();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean setPreferredPolarPattern(AVAudioSessionPolarPattern pattern) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredPolarPattern(pattern, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredPolarPattern:error:")
    private native boolean setPreferredPolarPattern(AVAudioSessionPolarPattern pattern, NSError.NSErrorPtr outError);
    /*</methods>*/
}
