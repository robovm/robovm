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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileHandle/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFileHandlePtr extends Ptr<NSFileHandle, NSFileHandlePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileHandle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileHandle() {}
    protected NSFileHandle(SkipInit skipInit) { super(skipInit); }
    public NSFileHandle(int fd, boolean closeopt) { super((SkipInit) null); initObject(initWithFileDescriptor$closeOnDealloc$(fd, closeopt)); }
    public NSFileHandle(int fd) { super((SkipInit) null); initObject(initWithFileDescriptor$(fd)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "readabilityHandler")
    public native @Block VoidBlock1<NSFileHandle> getReadabilityHandler();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setReadabilityHandler:")
    public native void setReadabilityHandler(@Block VoidBlock1<NSFileHandle> v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "writeabilityHandler")
    public native @Block VoidBlock1<NSFileHandle> getWriteabilityHandler();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setWriteabilityHandler:")
    public native void setWriteabilityHandler(@Block VoidBlock1<NSFileHandle> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "availableData")
    public native NSData getAvailableData();
    @Method(selector = "readDataToEndOfFile")
    public native NSData readDataToEndOfFile();
    @Method(selector = "readDataOfLength:")
    public native NSData readData(@MachineSizedUInt long length);
    @Method(selector = "writeData:")
    public native void writeData(NSData data);
    @Method(selector = "offsetInFile")
    public native long getOffsetInFile();
    @Method(selector = "seekToEndOfFile")
    public native long seekToEndOfFile();
    @Method(selector = "seekToFileOffset:")
    public native void seekToFileOffset(long offset);
    @Method(selector = "truncateFileAtOffset:")
    public native void truncateFile(long offset);
    @Method(selector = "synchronizeFile")
    public native void synchronizeFile();
    @Method(selector = "closeFile")
    public native void closeFile();
    @Method(selector = "fileHandleWithStandardInput")
    public static native NSFileHandle getStandardInput();
    @Method(selector = "fileHandleWithStandardOutput")
    public static native NSFileHandle getStandardOutput();
    @Method(selector = "fileHandleWithStandardError")
    public static native NSFileHandle getStandardError();
    @Method(selector = "fileHandleWithNullDevice")
    public static native NSFileHandle getNullDevice();
    @Method(selector = "fileHandleForReadingAtPath:")
    public static native NSFileHandle createForReading(String path);
    @Method(selector = "fileHandleForWritingAtPath:")
    public static native NSFileHandle createForWriting(String path);
    @Method(selector = "fileHandleForUpdatingAtPath:")
    public static native NSFileHandle createForUpdating(String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileHandleForReadingFromURL:error:")
    public static native NSFileHandle createForReading(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileHandleForWritingToURL:error:")
    public static native NSFileHandle createForWriting(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileHandleForUpdatingURL:error:")
    public static native NSFileHandle createForUpdating(NSURL url, NSError.NSErrorPtr error);
    @Method(selector = "readInBackgroundAndNotifyForModes:")
    public native void readInBackgroundAndNotify(NSArray<?> modes);
    @Method(selector = "readInBackgroundAndNotify")
    public native void readInBackgroundAndNotify();
    @Method(selector = "readToEndOfFileInBackgroundAndNotifyForModes:")
    public native void readToEndOfFileInBackgroundAndNotify(NSArray<?> modes);
    @Method(selector = "readToEndOfFileInBackgroundAndNotify")
    public native void readToEndOfFileInBackgroundAndNotify();
    @Method(selector = "acceptConnectionInBackgroundAndNotifyForModes:")
    public native void acceptConnectionInBackgroundAndNotify(NSArray<?> modes);
    @Method(selector = "acceptConnectionInBackgroundAndNotify")
    public native void acceptConnectionInBackgroundAndNotify();
    @Method(selector = "waitForDataInBackgroundAndNotifyForModes:")
    public native void waitForDataInBackgroundAndNotify(NSArray<?> modes);
    @Method(selector = "waitForDataInBackgroundAndNotify")
    public native void waitForDataInBackgroundAndNotify();
    @Method(selector = "initWithFileDescriptor:closeOnDealloc:")
    protected native @Pointer long initWithFileDescriptor$closeOnDealloc$(int fd, boolean closeopt);
    @Method(selector = "initWithFileDescriptor:")
    protected native @Pointer long initWithFileDescriptor$(int fd);
    @Method(selector = "fileDescriptor")
    public native int getFileDescriptor();
    /*</methods>*/
}
