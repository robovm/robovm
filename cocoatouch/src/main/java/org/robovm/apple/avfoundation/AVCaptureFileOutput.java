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
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureFileOutput/*</name>*/ 
    extends /*<extends>*/AVCaptureOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureFileOutputPtr extends Ptr<AVCaptureFileOutput, AVCaptureFileOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureFileOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureFileOutput() {}
    protected AVCaptureFileOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "outputFileURL")
    public native NSURL getOutputFileURL();
    @Property(selector = "isRecording")
    public native boolean isRecording();
    @Property(selector = "recordedDuration")
    public native @ByVal CMTime getRecordedDuration();
    @Property(selector = "recordedFileSize")
    public native long getRecordedFileSize();
    @Property(selector = "maxRecordedDuration")
    public native @ByVal CMTime getMaxRecordedDuration();
    @Property(selector = "setMaxRecordedDuration:")
    public native void setMaxRecordedDuration(@ByVal CMTime v);
    @Property(selector = "maxRecordedFileSize")
    public native long getMaxRecordedFileSize();
    @Property(selector = "setMaxRecordedFileSize:")
    public native void setMaxRecordedFileSize(long v);
    @Property(selector = "minFreeDiskSpaceLimit")
    public native long getMinFreeDiskSpaceLimit();
    @Property(selector = "setMinFreeDiskSpaceLimit:")
    public native void setMinFreeDiskSpaceLimit(long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "startRecordingToOutputFileURL:recordingDelegate:")
    public native void startRecording(NSURL outputFileURL, AVCaptureFileOutputRecordingDelegate delegate);
    @Method(selector = "stopRecording")
    public native void stopRecording();
    /*</methods>*/
}
