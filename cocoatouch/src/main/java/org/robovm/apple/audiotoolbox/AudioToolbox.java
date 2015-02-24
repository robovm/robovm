/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioToolbox/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioToolbox.class); }/*</bind>*/
    /*<constants>*/
    public static final int Constant__kAudioFileMarkerType_Generic = 0;
    public static final int Constant__kAudioFormatProperty_HardwareCodecCapabilities = 1752654691;
    public static final int Constant__kAudioSessionSetActiveFlag_NotifyOthersOnDeactivation = 1;
    public static final int Constant__kAudioSessionCategory_UserInterfaceSoundEffects = 1969841784;
    public static final int Constant__kAudioSessionCategory_LiveAudio = 1818850917;
    public static final int Constant__kAudioSessionProperty_AudioRoute = 1919907188;
    public static final int Constant__kAudioConverterPropertyCanResumeFromInterruption = 1668441705;
    public static final int Constant__kAudioConverterErr_HardwareInUse = 1752656245;
    public static final int Constant__kAudioConverterErr_NoHardwarePermission = 1885696621;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_RouteChangeKey_Reason", optional=true)
    public static native CFString Value__kAudioSession_RouteChangeKey_Reason();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_AudioRouteChangeKey_PreviousRouteDescription", optional=true)
    public static native CFString Value__kAudioSession_AudioRouteChangeKey_PreviousRouteDescription();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_AudioRouteChangeKey_CurrentRouteDescription", optional=true)
    public static native CFString Value__kAudioSession_AudioRouteChangeKey_CurrentRouteDescription();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_AudioRouteKey_Inputs", optional=true)
    public static native CFString Value__kAudioSession_AudioRouteKey_Inputs();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_AudioRouteKey_Outputs", optional=true)
    public static native CFString Value__kAudioSession_AudioRouteKey_Outputs();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_AudioRouteKey_Type", optional=true)
    public static native CFString Value__kAudioSession_AudioRouteKey_Type();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionInputRoute_LineIn", optional=true)
    public static native CFString Value__kAudioSessionInputRoute_LineIn();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionInputRoute_BuiltInMic", optional=true)
    public static native CFString Value__kAudioSessionInputRoute_BuiltInMic();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionInputRoute_HeadsetMic", optional=true)
    public static native CFString Value__kAudioSessionInputRoute_HeadsetMic();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionInputRoute_BluetoothHFP", optional=true)
    public static native CFString Value__kAudioSessionInputRoute_BluetoothHFP();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionInputRoute_USBAudio", optional=true)
    public static native CFString Value__kAudioSessionInputRoute_USBAudio();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_LineOut", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_LineOut();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_Headphones", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_Headphones();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_BluetoothHFP", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_BluetoothHFP();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_BluetoothA2DP", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_BluetoothA2DP();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_BuiltInReceiver", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_BuiltInReceiver();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_BuiltInSpeaker", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_BuiltInSpeaker();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_USBAudio", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_USBAudio();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_HDMI", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_HDMI();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSessionOutputRoute_AirPlay", optional=true)
    public static native CFString Value__kAudioSessionOutputRoute_AirPlay();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_InputSourceKey_ID", optional=true)
    public static native CFString Value__kAudioSession_InputSourceKey_ID();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_InputSourceKey_Description", optional=true)
    public static native CFString Value__kAudioSession_InputSourceKey_Description();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_OutputDestinationKey_ID", optional=true)
    public static native CFString Value__kAudioSession_OutputDestinationKey_ID();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kAudioSession_OutputDestinationKey_Description", optional=true)
    public static native CFString Value__kAudioSession_OutputDestinationKey_Description();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFormatGetPropertyInfo", optional=true)
    public static native OSStatus function__AudioFormatGetPropertyInfo(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outPropertyDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFormatGetProperty", optional=true)
    public static native OSStatus function__AudioFormatGetProperty(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /*</methods>*/
}
