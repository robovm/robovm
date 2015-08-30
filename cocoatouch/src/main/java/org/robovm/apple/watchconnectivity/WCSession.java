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
package org.robovm.apple.watchconnectivity;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WatchConnectivity") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WCSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WCSessionPtr extends Ptr<WCSession, WCSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WCSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WCSession() {}
    protected WCSession(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native WCSessionDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(WCSessionDelegate v);
    @Property(selector = "isPaired")
    public native boolean isPaired();
    @Property(selector = "isWatchAppInstalled")
    public native boolean isWatchAppInstalled();
    @Property(selector = "isComplicationEnabled")
    public native boolean isComplicationEnabled();
    @Property(selector = "watchDirectoryURL")
    public native NSURL getWatchDirectoryURL();
    @Property(selector = "isReachable")
    public native boolean isReachable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "iOSDeviceNeedsUnlockAfterRebootForReachability")
    public native boolean doesIOSDeviceNeedUnlockAfterRebootForReachability();
    @Property(selector = "applicationContext")
    public native NSDictionary<NSString, ?> getApplicationContext();
    @Property(selector = "receivedApplicationContext")
    public native NSDictionary<NSString, ?> getReceivedApplicationContext();
    @Property(selector = "outstandingUserInfoTransfers")
    public native NSArray<WCSessionUserInfoTransfer> getOutstandingUserInfoTransfers();
    @Property(selector = "outstandingFileTransfers")
    public native NSArray<WCSessionFileTransfer> getOutstandingFileTransfers();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "activateSession")
    public native void activateSession();
    @Method(selector = "sendMessage:replyHandler:errorHandler:")
    public native void sendMessage(NSDictionary<NSString, ?> message, @Block VoidBlock1<NSDictionary<NSString, ?>> replyHandler, @Block VoidBlock1<NSError> errorHandler);
    @Method(selector = "sendMessageData:replyHandler:errorHandler:")
    public native void sendMessageData(NSData data, @Block VoidBlock1<NSData> replyHandler, @Block VoidBlock1<NSError> errorHandler);
    public boolean updateApplicationContext(NSDictionary<NSString, ?> applicationContext) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = updateApplicationContext(applicationContext, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "updateApplicationContext:error:")
    private native boolean updateApplicationContext(NSDictionary<NSString, ?> applicationContext, NSError.NSErrorPtr error);
    @Method(selector = "transferUserInfo:")
    public native WCSessionUserInfoTransfer transferUserInfo(NSDictionary<NSString, ?> userInfo);
    @Method(selector = "transferCurrentComplicationUserInfo:")
    public native WCSessionUserInfoTransfer transferCurrentComplicationUserInfo(NSDictionary<NSString, ?> userInfo);
    @Method(selector = "transferFile:metadata:")
    public native WCSessionFileTransfer transferFile(NSURL file, NSDictionary<NSString, ?> metadata);
    @Method(selector = "isSupported")
    public static native boolean isSupported();
    @Method(selector = "defaultSession")
    public static native WCSession getDefaultSession();
    /*</methods>*/
}
