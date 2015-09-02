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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeInterruption(final VoidBlock1<AVAudioSessionInterruptionNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(InterruptionNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<?, ?> userInfo = a.getUserInfo();
                    AVAudioSessionInterruptionNotification data = null;
                    if (userInfo != null) {
                        data = new AVAudioSessionInterruptionNotification(userInfo);
                    }
                    block.invoke(data);
                }
            });
        }
        
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeRouteChange(final VoidBlock1<AVAudioSessionRouteChangeNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(RouteChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<?, ?> userInfo = a.getUserInfo();
                    AVAudioSessionRouteChangeNotification data = null;
                    if (userInfo != null) {
                        data = new AVAudioSessionRouteChangeNotification(userInfo);
                    }
                    block.invoke(data);
                }
            });
        }
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeMediaServicesWereLost(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(MediaServicesWereLostNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeMediaServicesWereReset(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(MediaServicesWereResetNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        /**
         * @since Available in iOS 8.0 and later.
         */
        public static NSObject observeSilenceSecondaryAudioHint(final VoidBlock1<AVAudioSessionSilenceSecondaryAudioHintType> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(SilenceSecondaryAudioHintNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<?, ?> userInfo = a.getUserInfo();
                    AVAudioSessionSilenceSecondaryAudioHintType type = null;
                    if (userInfo.containsKey(SilenceSecondaryAudioHintTypeKey())) {
                        NSNumber val = (NSNumber)userInfo.get(SilenceSecondaryAudioHintTypeKey());
                        type = AVAudioSessionSilenceSecondaryAudioHintType.valueOf(val.intValue());
                    }
                    block.invoke(type);
                }
            });
        }
    }
    
    /*<ptr>*/public static class AVAudioSessionPtr extends Ptr<AVAudioSession, AVAudioSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioSession() {}
    protected AVAudioSession(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "category")
    public native AVAudioSessionCategory getCategory();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "categoryOptions")
    public native AVAudioSessionCategoryOptions getCategoryOptions();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "mode")
    public native AVAudioSessionMode getMode();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isOtherAudioPlaying")
    public native boolean isOtherAudioPlaying();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "secondaryAudioShouldBeSilencedHint")
    public native boolean secondaryAudioShouldBeSilencedHint();
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
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionNotification", optional=true)
    public static native NSString InterruptionNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionRouteChangeNotification", optional=true)
    public static native NSString RouteChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionMediaServicesWereLostNotification", optional=true)
    public static native NSString MediaServicesWereLostNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionMediaServicesWereResetNotification", optional=true)
    public static native NSString MediaServicesWereResetNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionSilenceSecondaryAudioHintNotification", optional=true)
    public static native NSString SilenceSecondaryAudioHintNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionSilenceSecondaryAudioHintTypeKey", optional=true)
    protected static native NSString SilenceSecondaryAudioHintTypeKey();
    
    public boolean setActive(boolean active) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setActive(active, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "setActive:error:")
    private native boolean setActive(boolean active, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setActive(boolean active, AVAudioSessionSetActiveOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setActive(active, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setActive:withOptions:error:")
    private native boolean setActive(boolean active, AVAudioSessionSetActiveOptions options, NSError.NSErrorPtr outError);
    public boolean setCategory(AVAudioSessionCategory category) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setCategory(category, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "setCategory:error:")
    private native boolean setCategory(AVAudioSessionCategory category, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setCategory(AVAudioSessionCategory category, AVAudioSessionCategoryOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setCategory(category, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setCategory:withOptions:error:")
    private native boolean setCategory(AVAudioSessionCategory category, AVAudioSessionCategoryOptions options, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "recordPermission")
    public native AVAudioSessionRecordPermission getRecordPermission();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "requestRecordPermission:")
    public native void requestRecordPermission(@Block VoidBooleanBlock response);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean setMode(AVAudioSessionMode mode) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setMode(mode, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setMode:error:")
    private native boolean setMode(AVAudioSessionMode mode, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean overrideOutputAudioPort(AVAudioSessionPortOverride portOverride) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = overrideOutputAudioPort(portOverride, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "overrideOutputAudioPort:error:")
    private native boolean overrideOutputAudioPort(AVAudioSessionPortOverride portOverride, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean setPreferredInput(AVAudioSessionPortDescription inPort) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredInput(inPort, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredInput:error:")
    private native boolean setPreferredInput(AVAudioSessionPortDescription inPort, NSError.NSErrorPtr outError);
    @Method(selector = "sharedInstance")
    public static native AVAudioSession getSharedInstance();
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setPreferredSampleRate(double sampleRate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredSampleRate(sampleRate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setPreferredSampleRate:error:")
    private native boolean setPreferredSampleRate(double sampleRate, NSError.NSErrorPtr outError);
    public boolean setPreferredIOBufferDuration(double duration) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredIOBufferDuration(duration, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "setPreferredIOBufferDuration:error:")
    private native boolean setPreferredIOBufferDuration(double duration, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean setPreferredInputNumberOfChannels(@MachineSizedSInt long count) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredInputNumberOfChannels(count, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredInputNumberOfChannels:error:")
    private native boolean setPreferredInputNumberOfChannels(@MachineSizedSInt long count, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean setPreferredOutputNumberOfChannels(@MachineSizedSInt long count) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredOutputNumberOfChannels(count, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setPreferredOutputNumberOfChannels:error:")
    private native boolean setPreferredOutputNumberOfChannels(@MachineSizedSInt long count, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setInputGain(float gain) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setInputGain(gain, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setInputGain:error:")
    private native boolean setInputGain(float gain, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setInputDataSource(AVAudioSessionDataSourceDescription dataSource) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setInputDataSource(dataSource, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setInputDataSource:error:")
    private native boolean setInputDataSource(AVAudioSessionDataSourceDescription dataSource, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setOutputDataSource(AVAudioSessionDataSourceDescription dataSource) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setOutputDataSource(dataSource, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setOutputDataSource:error:")
    private native boolean setOutputDataSource(AVAudioSessionDataSourceDescription dataSource, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public boolean setActive(boolean active, @MachineSizedSInt long flags) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setActive(active, flags, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "setActive:withFlags:error:")
    private native boolean setActive(boolean active, @MachineSizedSInt long flags, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public boolean setPreferredHardwareSampleRate(double sampleRate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setPreferredHardwareSampleRate(sampleRate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "setPreferredHardwareSampleRate:error:")
    private native boolean setPreferredHardwareSampleRate(double sampleRate, NSError.NSErrorPtr outError);
    /*</methods>*/
}
