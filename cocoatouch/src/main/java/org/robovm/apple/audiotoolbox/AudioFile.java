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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFile/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFilePtr extends Ptr<AudioFile, AudioFilePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioFile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioFile() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileCreateWithURL", optional=true)
    public static native OSStatus createWithURL(NSURL inFileRef, int inFileType, AudioStreamBasicDescription inFormat, int inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenURL", optional=true)
    public static native OSStatus openURL(NSURL inFileRef, byte inPermissions, int inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileInitializeWithCallbacks", optional=true)
    public static native OSStatus initializeWithCallbacks(VoidPtr inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, int inFileType, AudioStreamBasicDescription inFormat, int inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenWithCallbacks", optional=true)
    public static native OSStatus openWithCallbacks(VoidPtr inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, int inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileClose", optional=true)
    public native OSStatus closeFile();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOptimize", optional=true)
    public native OSStatus optimize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileReadBytes", optional=true)
    public native OSStatus readBytes(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWriteBytes", optional=true)
    public native OSStatus writeBytes(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, VoidPtr inBuffer);
    /**
     * @since Available in iOS 2.2 and later.
     */
    @Bridge(symbol="AudioFileReadPacketData", optional=true)
    public native OSStatus readPacketData(boolean inUseCache, IntPtr ioNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="AudioFileReadPackets", optional=true)
    public native OSStatus readPackets(boolean inUseCache, IntPtr outNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWritePackets", optional=true)
    public native OSStatus writePackets(boolean inUseCache, int inNumBytes, AudioStreamPacketDescription inPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr inBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileCountUserData", optional=true)
    public native OSStatus countUserData(int inUserDataID, IntPtr outNumberItems);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserDataSize", optional=true)
    public native OSStatus getUserDataSize(int inUserDataID, int inIndex, IntPtr outUserDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserData", optional=true)
    public native OSStatus getUserData(int inUserDataID, int inIndex, IntPtr ioUserDataSize, VoidPtr outUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetUserData", optional=true)
    public native OSStatus setUserData(int inUserDataID, int inIndex, int inUserDataSize, VoidPtr inUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileRemoveUserData", optional=true)
    public native OSStatus removeUserData(int inUserDataID, int inIndex);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetPropertyInfo", optional=true)
    public native OSStatus getPropertyInfo(int inPropertyID, IntPtr outDataSize, IntPtr isWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetProperty", optional=true)
    public native OSStatus getProperty(int inPropertyID, IntPtr ioDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetProperty", optional=true)
    public native OSStatus setProperty(int inPropertyID, int inDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfoSize", optional=true)
    public static native OSStatus getGlobalInfoSize(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfo", optional=true)
    public static native OSStatus getGlobalInfo(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamOpen", optional=true)
    public static native OSStatus streamOpen(VoidPtr inClientData, FunctionPtr inPropertyListenerProc, FunctionPtr inPacketsProc, int inFileTypeHint, AudioFileStream.AudioFileStreamPtr outAudioFileStream);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamParseBytes", optional=true)
    public static native OSStatus streamParseBytes(AudioFileStream inAudioFileStream, int inDataByteSize, VoidPtr inData, int inFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSeek", optional=true)
    public static native OSStatus streamSeek(AudioFileStream inAudioFileStream, long inPacketOffset, LongPtr outDataByteOffset, IntPtr ioFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetPropertyInfo", optional=true)
    public static native OSStatus streamGetPropertyInfo(AudioFileStream inAudioFileStream, int inPropertyID, IntPtr outPropertyDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetProperty", optional=true)
    public static native OSStatus streamGetProperty(AudioFileStream inAudioFileStream, int inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSetProperty", optional=true)
    public static native OSStatus streamSetProperty(AudioFileStream inAudioFileStream, int inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamClose", optional=true)
    public static native OSStatus streamClose(AudioFileStream inAudioFileStream);
    /*</methods>*/
}
