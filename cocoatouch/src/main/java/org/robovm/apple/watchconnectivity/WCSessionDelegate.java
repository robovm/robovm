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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/WCSessionDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "sessionWatchStateDidChange:")
    void watchStateDidChange(WCSession session);
    @Method(selector = "sessionReachabilityDidChange:")
    void reachabilityDidChange(WCSession session);
    @Method(selector = "session:didReceiveMessage:")
    void didReceiveMessage(WCSession session, NSDictionary<NSString, ?> message);
    @Method(selector = "session:didReceiveMessage:replyHandler:")
    void didReceiveMessage(WCSession session, NSDictionary<NSString, ?> message, @Block VoidBlock1<NSDictionary<NSString, ?>> replyHandler);
    @Method(selector = "session:didReceiveMessageData:")
    void didReceiveMessageData(WCSession session, NSData messageData);
    @Method(selector = "session:didReceiveMessageData:replyHandler:")
    void didReceiveMessageData(WCSession session, NSData messageData, @Block VoidBlock1<NSData> replyHandler);
    @Method(selector = "session:didReceiveApplicationContext:")
    void didReceiveApplicationContext(WCSession session, NSDictionary<NSString, ?> applicationContext);
    @Method(selector = "session:didFinishUserInfoTransfer:error:")
    void didFinishUserInfoTransfer(WCSession session, WCSessionUserInfoTransfer userInfoTransfer, NSError error);
    @Method(selector = "session:didReceiveUserInfo:")
    void didReceiveUserInfo(WCSession session, NSDictionary<NSString, ?> userInfo);
    @Method(selector = "session:didFinishFileTransfer:error:")
    void didFinishFileTransfer(WCSession session, WCSessionFileTransfer fileTransfer, NSError error);
    @Method(selector = "session:didReceiveFile:")
    void didReceiveFile(WCSession session, WCSessionFile file);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
