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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioEnvironmentDistanceAttenuationParameters/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioEnvironmentDistanceAttenuationParametersPtr extends Ptr<AVAudioEnvironmentDistanceAttenuationParameters, AVAudioEnvironmentDistanceAttenuationParametersPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioEnvironmentDistanceAttenuationParameters.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioEnvironmentDistanceAttenuationParameters() {}
    protected AVAudioEnvironmentDistanceAttenuationParameters(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "distanceAttenuationModel")
    public native AVAudioEnvironmentDistanceAttenuationModel getDistanceAttenuationModel();
    @Property(selector = "setDistanceAttenuationModel:")
    public native void setDistanceAttenuationModel(AVAudioEnvironmentDistanceAttenuationModel v);
    @Property(selector = "referenceDistance")
    public native float getReferenceDistance();
    @Property(selector = "setReferenceDistance:")
    public native void setReferenceDistance(float v);
    @Property(selector = "maximumDistance")
    public native float getMaximumDistance();
    @Property(selector = "setMaximumDistance:")
    public native void setMaximumDistance(float v);
    @Property(selector = "rolloffFactor")
    public native float getRolloffFactor();
    @Property(selector = "setRolloffFactor:")
    public native void setRolloffFactor(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
