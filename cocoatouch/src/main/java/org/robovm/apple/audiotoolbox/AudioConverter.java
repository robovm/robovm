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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioConverter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioConverterPtr extends Ptr<AudioConverter, AudioConverterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioConverter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterNew", optional=true)
    protected static native AudioConverterError create(AudioStreamBasicDescription inSourceFormat, AudioStreamBasicDescription inDestinationFormat, AudioConverter.AudioConverterPtr outAudioConverter);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterNewSpecific", optional=true)
    protected static native AudioConverterError create(AudioStreamBasicDescription inSourceFormat, AudioStreamBasicDescription inDestinationFormat, int inNumberClassDescriptions, AudioClassDescription inClassDescriptions, AudioConverter.AudioConverterPtr outAudioConverter);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterReset", optional=true)
    public native AudioConverterError reset();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterGetPropertyInfo", optional=true)
    protected native AudioConverterError getPropertyInfo(int inPropertyID, IntPtr outSize, BytePtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterGetProperty", optional=true)
    protected native AudioConverterError getProperty(int inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterSetProperty", optional=true)
    protected native AudioConverterError setProperty(int inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterConvertBuffer", optional=true)
    protected native AudioConverterError convertBuffer(int inInputDataSize, VoidPtr inInputData, IntPtr ioOutputDataSize, VoidPtr outOutputData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioConverterFillComplexBuffer", optional=true)
    protected native AudioConverterError fillComplexBuffer(FunctionPtr inInputDataProc, VoidPtr inInputDataProcUserData, IntPtr ioOutputDataPacketSize, AudioBufferList outOutputData, AudioStreamPacketDescription outPacketDescription);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="AudioConverterConvertComplexBuffer", optional=true)
    protected native AudioConverterError convertComplexBuffer(int inNumberPCMFrames, AudioBufferList inInputData, AudioBufferList outOutputData);
    /*</methods>*/
}
