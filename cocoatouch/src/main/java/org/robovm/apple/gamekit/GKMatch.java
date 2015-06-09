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
package org.robovm.apple.gamekit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKMatch/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKMatchPtr extends Ptr<GKMatch, GKMatchPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKMatch.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKMatch() {}
    protected GKMatch(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "players")
    public native NSArray<GKPlayer> getPlayers();
    @Property(selector = "delegate")
    public native GKMatchDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(GKMatchDelegate v);
    @Property(selector = "expectedPlayerCount")
    public native @MachineSizedUInt long getExpectedPlayerCount();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "playerIDs")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPlayerIDs();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean sendDataToPlayers(NSData data, NSArray<GKPlayer> players, GKMatchSendDataMode mode) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = sendDataToPlayers(data, players, mode, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "sendData:toPlayers:dataMode:error:")
    private native boolean sendDataToPlayers(NSData data, NSArray<GKPlayer> players, GKMatchSendDataMode mode, NSError.NSErrorPtr error);
    public boolean sendDataToAllPlayers(NSData data, GKMatchSendDataMode mode) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = sendDataToAllPlayers(data, mode, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "sendDataToAllPlayers:withDataMode:error:")
    private native boolean sendDataToAllPlayers(NSData data, GKMatchSendDataMode mode, NSError.NSErrorPtr error);
    @Method(selector = "disconnect")
    public native void disconnect();
    @Method(selector = "voiceChatWithName:")
    public native GKVoiceChat getVoiceChat(String name);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "chooseBestHostingPlayerWithCompletionHandler:")
    public native void chooseBestHostingPlayer(@Block VoidBlock1<GKPlayer> completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "rematchWithCompletionHandler:")
    public native void rematch(@Block VoidBlock2<GKMatch, NSError> completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "chooseBestHostPlayerWithCompletionHandler:")
    public native void chooseBestHostPlayer(@Block VoidBlock1<String> completionHandler);
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public boolean sendDataToPlayers(NSData data, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> playerIDs, GKMatchSendDataMode mode) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = sendDataToPlayers(data, playerIDs, mode, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "sendData:toPlayers:withDataMode:error:")
    private native boolean sendDataToPlayers(NSData data, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> playerIDs, GKMatchSendDataMode mode, NSError.NSErrorPtr error);
    /*</methods>*/
}
