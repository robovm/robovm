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
package org.robovm.apple.audiounit;

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
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioUnit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicDevice/*</name>*/ 
    extends /*<extends>*/AudioComponentInstance/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MusicDevice.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static MusicDevice create(AudioComponent component) {
        AudioComponentInstance result = AudioComponentInstance.create(component);
        if (result != null) {
            return result.as(MusicDevice.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean canDo(MusicDeviceSelector inSelectorID) {
        return super.canDo((short) inSelectorID.value());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public OSStatus sysEx(byte[] data) {
        return sysEx(VM.getArrayValuesAddress(data), data.length);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int startNote(int groupID, int offsetSampleFrame, MusicDeviceNoteParams params) {
        IntPtr result = new IntPtr();
        startNote(0xFFFFFFFF, groupID, result, offsetSampleFrame, params);
        return result.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicDeviceMIDIEvent", optional=true)
    public native OSStatus midiEvent(int status, int data1, int data2, int offsetSampleFrame);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicDeviceSysEx", optional=true)
    protected native OSStatus sysEx(@Pointer long data, int length);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicDeviceStartNote", optional=true)
    protected native OSStatus startNote(int inInstrument, int inGroupID, IntPtr outNoteInstanceID, int inOffsetSampleFrame, MusicDeviceNoteParams inParams);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicDeviceStopNote", optional=true)
    public native OSStatus stopNote(int groupID, int noteInstanceID, int offsetSampleFrame);
    /*</methods>*/
}
