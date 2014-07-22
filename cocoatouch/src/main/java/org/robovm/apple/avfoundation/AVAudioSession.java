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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioSessionPtr extends Ptr<AVAudioSession, AVAudioSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioSession() {}
    protected AVAudioSession(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "category")
    public native String getCategory();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "categoryOptions")
    public native AVAudioSessionCategoryOptions getCategoryOptions();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "mode")
    public native String getMode();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isOtherAudioPlaying")
    public native boolean isOtherAudioPlaying();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "currentRoute")
    public native AVAudioSessionRouteDescription getCurrentRoute();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredInput")
    public native AVAudioSessionPortDescription getPreferredInput();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "availableInputs")
    public native NSArray<AVAudioSessionPortDescription> getAvailableInputs();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "preferredSampleRate")
    public native double getPreferredSampleRate();
    @Property(selector = "preferredIOBufferDuration")
    public native double getPreferredIOBufferDuration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredInputNumberOfChannels")
    public native @MachineSizedSInt long getPreferredInputNumberOfChannels();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredOutputNumberOfChannels")
    public native @MachineSizedSInt long getPreferredOutputNumberOfChannels();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "maximumInputNumberOfChannels")
    public native @MachineSizedSInt long getMaximumInputNumberOfChannels();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "maximumOutputNumberOfChannels")
    public native @MachineSizedSInt long getMaximumOutputNumberOfChannels();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inputGain")
    public native float getInputGain();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isInputGainSettable")
    public native boolean isInputGainSettable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isInputAvailable")
    public native boolean isInputAvailable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inputDataSources")
    public native NSArray<AVAudioSessionDataSourceDescription> getInputDataSources();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inputDataSource")
    public native AVAudioSessionDataSourceDescription getInputDataSource();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputDataSources")
    public native NSArray<AVAudioSessionDataSourceDescription> getOutputDataSources();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputDataSource")
    public native AVAudioSessionDataSourceDescription getOutputDataSource();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "sampleRate")
    public native double getSampleRate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inputNumberOfChannels")
    public native @MachineSizedSInt long getInputNumberOfChannels();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputNumberOfChannels")
    public native @MachineSizedSInt long getOutputNumberOfChannels();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputVolume")
    public native float getOutputVolume();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "inputLatency")
    public native double getInputLatency();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputLatency")
    public native double getOutputLatency();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "IOBufferDuration")
    public native double getIOBufferDuration();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "delegate")
    public native AVAudioSessionDelegate getDelegate();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(AVAudioSessionDelegate v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "inputIsAvailable")
    public native boolean isInputIsAvailable();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "currentHardwareSampleRate")
    public native double getCurrentHardwareSampleRate();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "currentHardwareInputNumberOfChannels")
    public native @MachineSizedSInt long getCurrentHardwareInputNumberOfChannels();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "currentHardwareOutputNumberOfChannels")
    public native @MachineSizedSInt long getCurrentHardwareOutputNumberOfChannels();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "preferredHardwareSampleRate")
    public native double getPreferredHardwareSampleRate();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setActive:error:")
    public native boolean setActive(boolean active, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setActive:withOptions:error:")
    public native boolean setActive(boolean active, AVAudioSessionSetActiveOptions options, NSError.NSErrorPtr outError);
    @Method(selector = "setCategory:error:")
    public native boolean setCategory(String category, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setCategory:withOptions:error:")
    public native boolean setCategory(String category, AVAudioSessionCategoryOptions options, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "requestRecordPermission:")
    public native void requestRecordPermission(@Block VoidBooleanBlock response);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setMode:error:")
    public native boolean setMode(String mode, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "overrideOutputAudioPort:error:")
    public native boolean overrideOutputAudioPort(AVAudioSessionPortOverride portOverride, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredInput:error:")
    public native boolean setPreferredInput(AVAudioSessionPortDescription inPort, NSError.NSErrorPtr outError);
    @Method(selector = "sharedInstance")
    public static native AVAudioSession sharedInstance();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setPreferredSampleRate:error:")
    public native boolean setPreferredSampleRate(double sampleRate, NSError.NSErrorPtr outError);
    @Method(selector = "setPreferredIOBufferDuration:error:")
    public native boolean setPreferredIOBufferDuration(double duration, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredInputNumberOfChannels:error:")
    public native boolean setPreferredInputNumberOfChannels(@MachineSizedSInt long count, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredOutputNumberOfChannels:error:")
    public native boolean setPreferredOutputNumberOfChannels(@MachineSizedSInt long count, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setInputGain:error:")
    public native boolean setInputGain(float gain, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setInputDataSource:error:")
    public native boolean setInputDataSource(AVAudioSessionDataSourceDescription dataSource, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setOutputDataSource:error:")
    public native boolean setOutputDataSource(AVAudioSessionDataSourceDescription dataSource, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "setActive:withFlags:error:")
    public native boolean setActive(boolean active, @MachineSizedSInt long flags, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "setPreferredHardwareSampleRate:error:")
    public native boolean setPreferredHardwareSampleRate(double sampleRate, NSError.NSErrorPtr outError);
    /*</methods>*/
}
