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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayerItemVideoOutput/*</name>*/ 
    extends /*<extends>*/AVPlayerItemOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPlayerItemVideoOutputPtr extends Ptr<AVPlayerItemVideoOutput, AVPlayerItemVideoOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPlayerItemVideoOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPlayerItemVideoOutput() {}
    protected AVPlayerItemVideoOutput(SkipInit skipInit) { super(skipInit); }
    public AVPlayerItemVideoOutput(CVPixelBufferAttributes pixelBufferAttributes) { super((SkipInit) null); initObject(init(pixelBufferAttributes)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native AVPlayerItemOutputPullDelegate getDelegate();
    @Property(selector = "delegateQueue")
    public native DispatchQueue getDelegateQueue();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPixelBufferAttributes:")
    protected native @Pointer long init(CVPixelBufferAttributes pixelBufferAttributes);
    @Method(selector = "hasNewPixelBufferForItemTime:")
    public native boolean hasNewPixelBufferForItemTime(@ByVal CMTime itemTime);
    @Method(selector = "copyPixelBufferForItemTime:itemTimeForDisplay:")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CVPixelBuffer getPixelBufferForItemTime(@ByVal CMTime itemTime, CMTime outItemTimeForDisplay);
    @Method(selector = "setDelegate:queue:")
    public native void setDelegate(AVPlayerItemOutputPullDelegate delegate, DispatchQueue delegateQueue);
    @Method(selector = "requestNotificationOfMediaDataChangeWithAdvanceInterval:")
    public native void requestNotificationOfMediaDataChange(double interval);
    /*</methods>*/
}
