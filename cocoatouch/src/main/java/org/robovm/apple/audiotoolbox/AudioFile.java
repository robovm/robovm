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
    public static native AudioFileError createWithURL(CFURL inFileRef, int inFileType, AudioStreamBasicDescription inFormat, int inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenURL", optional=true)
    public static native AudioFileError openURL(CFURL inFileRef, byte inPermissions, int inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileInitializeWithCallbacks", optional=true)
    public static native AudioFileError initializeWithCallbacks(VoidPtr inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, int inFileType, AudioStreamBasicDescription inFormat, int inFlags, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOpenWithCallbacks", optional=true)
    public static native AudioFileError openWithCallbacks(VoidPtr inClientData, FunctionPtr inReadFunc, FunctionPtr inWriteFunc, FunctionPtr inGetSizeFunc, FunctionPtr inSetSizeFunc, int inFileTypeHint, AudioFile.AudioFilePtr outAudioFile);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileClose", optional=true)
    public native AudioFileError closeFile();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileOptimize", optional=true)
    public native AudioFileError optimize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileReadBytes", optional=true)
    public native AudioFileError readBytes(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWriteBytes", optional=true)
    public native AudioFileError writeBytes(boolean inUseCache, long inStartingByte, IntPtr ioNumBytes, VoidPtr inBuffer);
    /**
     * @since Available in iOS 2.2 and later.
     */
    @Bridge(symbol="AudioFileReadPacketData", optional=true)
    public native AudioFileError readPacketData(boolean inUseCache, IntPtr ioNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="AudioFileReadPackets", optional=true)
    public native AudioFileError readPackets(boolean inUseCache, IntPtr outNumBytes, AudioStreamPacketDescription outPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr outBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileWritePackets", optional=true)
    public native AudioFileError writePackets(boolean inUseCache, int inNumBytes, AudioStreamPacketDescription inPacketDescriptions, long inStartingPacket, IntPtr ioNumPackets, VoidPtr inBuffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileCountUserData", optional=true)
    public native AudioFileError countUserData(int inUserDataID, IntPtr outNumberItems);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserDataSize", optional=true)
    public native AudioFileError getUserDataSize(int inUserDataID, int inIndex, IntPtr outUserDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetUserData", optional=true)
    public native AudioFileError getUserData(int inUserDataID, int inIndex, IntPtr ioUserDataSize, VoidPtr outUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetUserData", optional=true)
    public native AudioFileError setUserData(int inUserDataID, int inIndex, int inUserDataSize, VoidPtr inUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileRemoveUserData", optional=true)
    public native AudioFileError removeUserData(int inUserDataID, int inIndex);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetPropertyInfo", optional=true)
    public native AudioFileError getPropertyInfo(int inPropertyID, IntPtr outDataSize, IntPtr isWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetProperty", optional=true)
    public native AudioFileError getProperty(int inPropertyID, IntPtr ioDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileSetProperty", optional=true)
    public native AudioFileError setProperty(int inPropertyID, int inDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfoSize", optional=true)
    public static native AudioFileError getGlobalInfoSize(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileGetGlobalInfo", optional=true)
    public static native AudioFileError getGlobalInfo(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamOpen", optional=true)
    public static native AudioFileError streamOpen(VoidPtr inClientData, FunctionPtr inPropertyListenerProc, FunctionPtr inPacketsProc, int inFileTypeHint, AudioFileStream.AudioFileStreamPtr outAudioFileStream);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamParseBytes", optional=true)
    public static native AudioFileError streamParseBytes(AudioFileStream inAudioFileStream, int inDataByteSize, VoidPtr inData, int inFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSeek", optional=true)
    public static native AudioFileError streamSeek(AudioFileStream inAudioFileStream, long inPacketOffset, LongPtr outDataByteOffset, IntPtr ioFlags);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetPropertyInfo", optional=true)
    public static native AudioFileError streamGetPropertyInfo(AudioFileStream inAudioFileStream, int inPropertyID, IntPtr outPropertyDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamGetProperty", optional=true)
    public static native AudioFileError streamGetProperty(AudioFileStream inAudioFileStream, int inPropertyID, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamSetProperty", optional=true)
    public static native AudioFileError streamSetProperty(AudioFileStream inAudioFileStream, int inPropertyID, int inPropertyDataSize, VoidPtr inPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioFileStreamClose", optional=true)
    public static native AudioFileError streamClose(AudioFileStream inAudioFileStream);
    /*</methods>*/
}
