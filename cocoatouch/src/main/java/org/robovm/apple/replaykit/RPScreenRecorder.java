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
package org.robovm.apple.replaykit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("ReplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/RPScreenRecorder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class RPScreenRecorderPtr extends Ptr<RPScreenRecorder, RPScreenRecorderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(RPScreenRecorder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected RPScreenRecorder(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native RPScreenRecorderDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(RPScreenRecorderDelegate v);
    @Property(selector = "isRecording")
    public native boolean isRecording();
    @Property(selector = "isMicrophoneEnabled")
    public native boolean isMicrophoneEnabled();
    @Property(selector = "isAvailable")
    public native boolean isAvailable();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "startRecordingWithMicrophoneEnabled:handler:")
    public native void startRecording(boolean microphoneEnabled, @Block VoidBlock1<NSError> handler);
    @Method(selector = "stopRecordingWithHandler:")
    public native void stopRecording(@Block VoidBlock2<RPPreviewViewController, NSError> handler);
    @Method(selector = "discardRecordingWithHandler:")
    public native void discardRecording(@Block Runnable handler);
    @Method(selector = "sharedRecorder")
    public static native RPScreenRecorder getSharedRecorder();
    /*</methods>*/
}
