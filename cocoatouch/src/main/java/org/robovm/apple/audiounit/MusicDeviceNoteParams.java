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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicDeviceNoteParams/*</name>*/ 
    extends /*<extends>*/Struct<MusicDeviceNoteParams>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicDeviceNoteParamsPtr extends Ptr<MusicDeviceNoteParams, MusicDeviceNoteParamsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    public MusicDeviceNoteParams() {}
    public MusicDeviceNoteParams(float pitch, float velocity, NoteParamsControlValue[] controls) {
        this.setPitch(pitch);
        this.setVelocity(velocity);
        setControls(controls);
    }
    
    public void setControls(NoteParamsControlValue[] controls) {
        this.setArgCount(controls.length + 2);
        getControls0().set(controls);
    }
    public NoteParamsControlValue[] getControls() {
        return getControls0().get().toArray(getArgCount() - 2);
    }
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) private native int getArgCount();
    @StructMember(0) private native MusicDeviceNoteParams setArgCount(int argCount);
    @StructMember(1) public native float getPitch();
    @StructMember(1) public native MusicDeviceNoteParams setPitch(float pitch);
    @StructMember(2) public native float getVelocity();
    @StructMember(2) public native MusicDeviceNoteParams setVelocity(float velocity);
    /*</members>*/
    @StructMember(3) protected native @ByVal NoteParamsControlValue.NoteParamsControlValuePtr getControls0();
    /*<methods>*//*</methods>*/
}
