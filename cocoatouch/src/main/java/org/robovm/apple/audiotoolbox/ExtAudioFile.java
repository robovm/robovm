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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ExtAudioFile/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ExtAudioFilePtr extends Ptr<ExtAudioFile, ExtAudioFilePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(ExtAudioFile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected ExtAudioFile() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileOpenURL", optional=true)
    public static native OSStatus openURL(CFURL inURL, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWrapAudioFileID", optional=true)
    public static native OSStatus wrapAudioFileID(AudioFile inFileID, boolean inForWriting, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileCreateWithURL", optional=true)
    public static native OSStatus createWithURL(CFURL inURL, int inFileType, AudioStreamBasicDescription inStreamDesc, AudioChannelLayout inChannelLayout, int inFlags, ExtAudioFile.ExtAudioFilePtr outExtAudioFile);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileRead", optional=true)
    public native OSStatus read(IntPtr ioNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWrite", optional=true)
    public native OSStatus write(int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileWriteAsync", optional=true)
    public native OSStatus writeAsync(int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileSeek", optional=true)
    public native OSStatus seek(long inFrameOffset);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileTell", optional=true)
    public native OSStatus tell(LongPtr outFrameOffset);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileGetPropertyInfo", optional=true)
    public native OSStatus getPropertyInfo(int inPropertyID, IntPtr outSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileGetProperty", optional=true)
    public native OSStatus getProperty(int inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.1 and later.
     */
    @Bridge(symbol="ExtAudioFileSetProperty", optional=true)
    public native OSStatus setProperty(int inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /*</methods>*/
}
