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
package org.robovm.apple.coreaudiokit;

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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreAudioKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAInterAppAudioTransportView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAInterAppAudioTransportViewPtr extends Ptr<CAInterAppAudioTransportView, CAInterAppAudioTransportViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAInterAppAudioTransportView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAInterAppAudioTransportView() {}
    protected CAInterAppAudioTransportView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "isPlaying")
    public native boolean isPlaying();
    @Property(selector = "isRecording")
    public native boolean isRecording();
    @Property(selector = "isConnected")
    public native boolean isConnected();
    @Property(selector = "labelColor")
    public native UIColor getLabelColor();
    @Property(selector = "setLabelColor:")
    public native void setLabelColor(UIColor v);
    @Property(selector = "currentTimeLabelFont")
    public native UIFont getCurrentTimeLabelFont();
    @Property(selector = "setCurrentTimeLabelFont:")
    public native void setCurrentTimeLabelFont(UIFont v);
    @Property(selector = "rewindButtonColor")
    public native UIColor getRewindButtonColor();
    @Property(selector = "setRewindButtonColor:")
    public native void setRewindButtonColor(UIColor v);
    @Property(selector = "playButtonColor")
    public native UIColor getPlayButtonColor();
    @Property(selector = "setPlayButtonColor:")
    public native void setPlayButtonColor(UIColor v);
    @Property(selector = "pauseButtonColor")
    public native UIColor getPauseButtonColor();
    @Property(selector = "setPauseButtonColor:")
    public native void setPauseButtonColor(UIColor v);
    @Property(selector = "recordButtonColor")
    public native UIColor getRecordButtonColor();
    @Property(selector = "setRecordButtonColor:")
    public native void setRecordButtonColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setOutputAudioUnit:")
    public native void setOutputAudioUnit(AudioUnit au);
    /*</methods>*/
}
