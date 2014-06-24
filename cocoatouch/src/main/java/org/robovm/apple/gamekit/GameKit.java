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
package org.robovm.apple.gamekit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("GameKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GameKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(GameKit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GKErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    @GlobalValue(symbol="GKPlayerDidChangeNotificationName", optional=true)
    public static native NSString PlayerDidChangeNotificationName();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="GKTurnTimeoutDefault", optional=true)
    public static native double TurnTimeoutDefault();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="GKTurnTimeoutNone", optional=true)
    public static native double TurnTimeoutNone();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="GKExchangeTimeoutDefault", optional=true)
    public static native double ExchangeTimeoutDefault();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="GKExchangeTimeoutNone", optional=true)
    public static native double ExchangeTimeoutNone();
    /**
     * @since Available in iOS 4.1 and later.
     */
    @GlobalValue(symbol="GKPlayerAuthenticationDidChangeNotificationName", optional=true)
    public static native NSString PlayerAuthenticationDidChangeNotificationName();
    @GlobalValue(symbol="GKSessionErrorDomain", optional=true)
    public static native NSString SessionErrorDomain();
    @GlobalValue(symbol="GKVoiceChatServiceErrorDomain", optional=true)
    public static native NSString VoiceChatServiceErrorDomain();
    /*</methods>*/
}
