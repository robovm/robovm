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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioFile/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioFilePtr extends Ptr<AVAudioFile, AVAudioFilePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioFile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioFile() {}
    protected AVAudioFile(SkipInit skipInit) { super(skipInit); }
    public AVAudioFile(NSURL fileURL) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(fileURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    public AVAudioFile(NSURL fileURL, AVAudioCommonFormat format, boolean interleaved) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(fileURL, format, interleaved, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    public AVAudioFile(NSURL fileURL, AVAudioSettings settings) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(fileURL, settings, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    public AVAudioFile(NSURL fileURL, AVAudioSettings settings, AVAudioCommonFormat format, boolean interleaved) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(fileURL, settings, format, interleaved, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "url")
    public native NSURL getUrl();
    @Property(selector = "fileFormat")
    public native AVAudioFormat getFileFormat();
    @Property(selector = "processingFormat")
    public native AVAudioFormat getProcessingFormat();
    @Property(selector = "length")
    public native long getLength();
    @Property(selector = "framePosition")
    public native long getFramePosition();
    @Property(selector = "setFramePosition:")
    public native void setFramePosition(long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initForReading:error:")
    private native @Pointer long init(NSURL fileURL, NSError.NSErrorPtr outError);
    @Method(selector = "initForReading:commonFormat:interleaved:error:")
    private native @Pointer long init(NSURL fileURL, AVAudioCommonFormat format, boolean interleaved, NSError.NSErrorPtr outError);
    @Method(selector = "initForWriting:settings:error:")
    private native @Pointer long init(NSURL fileURL, AVAudioSettings settings, NSError.NSErrorPtr outError);
    @Method(selector = "initForWriting:settings:commonFormat:interleaved:error:")
    private native @Pointer long init(NSURL fileURL, AVAudioSettings settings, AVAudioCommonFormat format, boolean interleaved, NSError.NSErrorPtr outError);
    public boolean readIntoBuffer(AVAudioPCMBuffer buffer) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = readIntoBuffer(buffer, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "readIntoBuffer:error:")
    private native boolean readIntoBuffer(AVAudioPCMBuffer buffer, NSError.NSErrorPtr outError);
    public boolean readIntoBuffer(AVAudioPCMBuffer buffer, int frames) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = readIntoBuffer(buffer, frames, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "readIntoBuffer:frameCount:error:")
    private native boolean readIntoBuffer(AVAudioPCMBuffer buffer, int frames, NSError.NSErrorPtr outError);
    public boolean writeFromBuffer(AVAudioPCMBuffer buffer) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = writeFromBuffer(buffer, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "writeFromBuffer:error:")
    private native boolean writeFromBuffer(AVAudioPCMBuffer buffer, NSError.NSErrorPtr outError);
    /*</methods>*/
}
