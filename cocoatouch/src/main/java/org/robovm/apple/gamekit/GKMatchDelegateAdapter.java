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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKMatchDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GKMatchDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("match:didReceiveData:fromRemotePlayer:")
    public void didReceiveData(GKMatch match, NSData data, GKPlayer player) {}
    /**
     * @since Available in iOS 9.0 and later.
     */
    @NotImplemented("match:didReceiveData:forRecipient:fromRemotePlayer:")
    public void didReceiveData(GKMatch match, NSData data, GKPlayer recipient, GKPlayer player) {}
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("match:didReceiveData:fromPlayer:")
    public void didReceiveData(GKMatch match, NSData data, String playerID) {}
    /**
     * @since Available in iOS 4.1 and later.
     */
    @NotImplemented("match:player:didChangeConnectionState:")
    public void didChangeConnectionState(GKMatch match, GKPlayer player, GKPlayerConnectionState state) {}
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("match:player:didChangeState:")
    public void didChangeState(GKMatch match, String playerID, GKPlayerConnectionState state) {}
    /**
     * @since Available in iOS 4.1 and later.
     */
    @NotImplemented("match:didFailWithError:")
    public void didFail(GKMatch match, NSError error) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("match:shouldReinviteDisconnectedPlayer:")
    public boolean shouldReinviteDisconnectedPlayer(GKMatch match, GKPlayer player) { return false; }
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("match:shouldReinvitePlayer:")
    public boolean shouldReinvitePlayer(GKMatch match, String playerID) { return false; }
    /*</methods>*/
}
