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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioPCMBuffer/*</name>*/ 
    extends /*<extends>*/AVAudioBuffer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioPCMBufferPtr extends Ptr<AVAudioPCMBuffer, AVAudioPCMBufferPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioPCMBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioPCMBuffer() {}
    protected AVAudioPCMBuffer(SkipInit skipInit) { super(skipInit); }
    public AVAudioPCMBuffer(AVAudioFormat format, int frameCapacity) { super((SkipInit) null); initObject(init(format, frameCapacity)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "frameCapacity")
    public native int getFrameCapacity();
    @Property(selector = "frameLength")
    public native int getFrameLength();
    @Property(selector = "setFrameLength:")
    public native void setFrameLength(int v);
    @Property(selector = "stride")
    public native @MachineSizedUInt long getStride();
    @Property(selector = "floatChannelData")
    public native FloatPtr.FloatPtrPtr getFloatChannelData();
    @Property(selector = "int16ChannelData")
    public native ShortPtr.ShortPtrPtr getInt16ChannelData();
    @Property(selector = "int32ChannelData")
    public native IntPtr.IntPtrPtr getInt32ChannelData();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPCMFormat:frameCapacity:")
    protected native @Pointer long init(AVAudioFormat format, int frameCapacity);
    /*</methods>*/
}
