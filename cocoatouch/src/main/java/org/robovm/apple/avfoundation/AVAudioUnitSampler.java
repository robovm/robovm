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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnitSampler/*</name>*/ 
    extends /*<extends>*/AVAudioUnitMIDIInstrument/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioUnitSamplerPtr extends Ptr<AVAudioUnitSampler, AVAudioUnitSamplerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioUnitSampler.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioUnitSampler() {}
    protected AVAudioUnitSampler(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "stereoPan")
    public native float getStereoPan();
    @Property(selector = "setStereoPan:")
    public native void setStereoPan(float v);
    @Property(selector = "masterGain")
    public native float getMasterGain();
    @Property(selector = "setMasterGain:")
    public native void setMasterGain(float v);
    @Property(selector = "globalTuning")
    public native float getGlobalTuning();
    @Property(selector = "setGlobalTuning:")
    public native void setGlobalTuning(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param bankURL
     * @param program
     * @param bankMSB
     * @param bankLSB
     * @return
     * @throws NSErrorException
     */
    public boolean loadSoundBankInstrument(NSURL bankURL, byte program, byte bankMSB, byte bankLSB) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = loadSoundBankInstrument(bankURL, program, bankMSB, bankLSB, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param instrumentURL
     * @return
     * @throws NSErrorException
     */
    public boolean loadInstrument(NSURL instrumentURL) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = loadInstrument(instrumentURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param audioFiles
     * @return
     * @throws NSErrorException
     */
    public boolean loadAudioFiles(NSArray<NSURL> audioFiles) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = loadAudioFiles(audioFiles, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "loadSoundBankInstrumentAtURL:program:bankMSB:bankLSB:error:")
    protected native boolean loadSoundBankInstrument(NSURL bankURL, byte program, byte bankMSB, byte bankLSB, NSError.NSErrorPtr outError);
    @Method(selector = "loadInstrumentAtURL:error:")
    protected native boolean loadInstrument(NSURL instrumentURL, NSError.NSErrorPtr outError);
    @Method(selector = "loadAudioFilesAtURLs:error:")
    protected native boolean loadAudioFiles(NSArray<NSURL> audioFiles, NSError.NSErrorPtr outError);
    /*</methods>*/
}
