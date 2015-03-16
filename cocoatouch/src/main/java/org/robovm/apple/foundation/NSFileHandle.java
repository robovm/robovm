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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileHandle/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeReadCompletion(NSFileHandle object, final VoidBlock2<NSFileHandle, NSData> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ReadCompletionNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSData d = null;
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(NotificationDataItem())) {
                        d = (NSData)data.get(NotificationDataItem());
                    }
                    block.invoke((NSFileHandle)a.getObject(), d);
                }
            });
        }
        public static NSObject observeReadToEndOfFileCompletion(NSFileHandle object, final VoidBlock2<NSFileHandle, NSData> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ReadToEndOfFileCompletionNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSData d = null;
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(NotificationDataItem())) {
                        d = (NSData)data.get(NotificationDataItem());
                    }
                    block.invoke((NSFileHandle)a.getObject(), d);
                }
            });
        }
        public static NSObject observeConnectionAccepted(NSFileHandle object, final VoidBlock2<NSFileHandle, NSFileHandle> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ConnectionAcceptedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSFileHandle f = null;
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(NotificationDataItem())) {
                        f = (NSFileHandle)data.get(NotificationFileHandleItem());
                    }
                    block.invoke((NSFileHandle)a.getObject(), f);
                }
            });
        }
        public static NSObject observeDataAvailable(NSFileHandle object, final VoidBlock1<NSFileHandle> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DataAvailableNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSFileHandle)a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSFileHandlePtr extends Ptr<NSFileHandle, NSFileHandlePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileHandle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileHandle() {}
    protected NSFileHandle(SkipInit skipInit) { super(skipInit); }
    public NSFileHandle(int fd, boolean closeopt) { super((SkipInit) null); initObject(init(fd, closeopt)); }
    public NSFileHandle(NSCoder coder) { super((SkipInit) null); initObject(init(coder)); }
    public NSFileHandle(int fd) { super((SkipInit) null); initObject(init(fd)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "availableData")
    public native NSData getAvailableData();
    @Property(selector = "offsetInFile")
    public native long getOffsetInFile();
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
    @Property(selector = "fileDescriptor")
    public native int getFileDescriptor();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public void readInBackgroundAndNotify(NSRunLoopMode...modes) {
        List<String> list = new ArrayList<>();
        for (NSRunLoopMode mode : modes) {
            list.add(mode.value());
        }
        readInBackgroundAndNotify(list);
    }
    public void readToEndOfFileInBackgroundAndNotify(NSRunLoopMode...modes) {
        List<String> list = new ArrayList<>();
        for (NSRunLoopMode mode : modes) {
            list.add(mode.value());
        }
        readToEndOfFileInBackgroundAndNotify(list);
    }
    public void acceptConnectionInBackgroundAndNotify(NSRunLoopMode...modes) {
        List<String> list = new ArrayList<>();
        for (NSRunLoopMode mode : modes) {
            list.add(mode.value());
        }
        acceptConnectionInBackgroundAndNotify(list);
    }
    public void waitForDataInBackgroundAndNotify(NSRunLoopMode...modes) {
        List<String> list = new ArrayList<>();
        for (NSRunLoopMode mode : modes) {
            list.add(mode.value());
        }
        waitForDataInBackgroundAndNotify(list);
    }
    
    /**
     * 
     * @param url
     * @throws NSErrorException
     * @since Available in iOS 4.0 and later.
     */
    public static NSFileHandle createForReading(NSURL url) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSFileHandle result = createForReading(url, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param url
     * @throws NSErrorException
     * @since Available in iOS 4.0 and later.
     */
    public static NSFileHandle createForWriting(NSURL url) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSFileHandle result = createForWriting(url, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param url
     * @throws NSErrorException
     * @since Available in iOS 4.0 and later.
     */
    public static NSFileHandle createForUpdating(NSURL url) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSFileHandle result = createForUpdating(url, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    
    /*<methods>*/
    @GlobalValue(symbol="NSFileHandleReadCompletionNotification", optional=true)
    public static native NSString ReadCompletionNotification();
    @GlobalValue(symbol="NSFileHandleReadToEndOfFileCompletionNotification", optional=true)
    public static native NSString ReadToEndOfFileCompletionNotification();
    @GlobalValue(symbol="NSFileHandleConnectionAcceptedNotification", optional=true)
    public static native NSString ConnectionAcceptedNotification();
    @GlobalValue(symbol="NSFileHandleDataAvailableNotification", optional=true)
    public static native NSString DataAvailableNotification();
    @GlobalValue(symbol="NSFileHandleNotificationDataItem", optional=true)
    protected static native NSString NotificationDataItem();
    @GlobalValue(symbol="NSFileHandleNotificationFileHandleItem", optional=true)
    protected static native NSString NotificationFileHandleItem();
    
    @Method(selector = "readDataToEndOfFile")
    public native NSData readDataToEndOfFile();
    @Method(selector = "readDataOfLength:")
    public native NSData readData(@MachineSizedUInt long length);
    @Method(selector = "writeData:")
    public native void writeData(NSData data);
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
    @Method(selector = "initWithFileDescriptor:closeOnDealloc:")
    protected native @Pointer long init(int fd, boolean closeopt);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder coder);
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
    protected static native NSFileHandle createForReading(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileHandleForWritingToURL:error:")
    protected static native NSFileHandle createForWriting(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileHandleForUpdatingURL:error:")
    protected static native NSFileHandle createForUpdating(NSURL url, NSError.NSErrorPtr error);
    @Method(selector = "readInBackgroundAndNotifyForModes:")
    public native void readInBackgroundAndNotify(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> modes);
    @Method(selector = "readInBackgroundAndNotify")
    public native void readInBackgroundAndNotify();
    @Method(selector = "readToEndOfFileInBackgroundAndNotifyForModes:")
    public native void readToEndOfFileInBackgroundAndNotify(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> modes);
    @Method(selector = "readToEndOfFileInBackgroundAndNotify")
    public native void readToEndOfFileInBackgroundAndNotify();
    @Method(selector = "acceptConnectionInBackgroundAndNotifyForModes:")
    public native void acceptConnectionInBackgroundAndNotify(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> modes);
    @Method(selector = "acceptConnectionInBackgroundAndNotify")
    public native void acceptConnectionInBackgroundAndNotify();
    @Method(selector = "waitForDataInBackgroundAndNotifyForModes:")
    public native void waitForDataInBackgroundAndNotify(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> modes);
    @Method(selector = "waitForDataInBackgroundAndNotify")
    public native void waitForDataInBackgroundAndNotify();
    @Method(selector = "initWithFileDescriptor:")
    protected native @Pointer long init(int fd);
    /*</methods>*/
}
