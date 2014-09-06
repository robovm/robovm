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
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKTurnBasedExchange/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKTurnBasedExchangePtr extends Ptr<GKTurnBasedExchange, GKTurnBasedExchangePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKTurnBasedExchange.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKTurnBasedExchange() {}
    protected GKTurnBasedExchange(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "exchangeID")
    public native String getExchangeID();
    @Property(selector = "sender")
    public native GKTurnBasedParticipant getSender();
    @Property(selector = "recipients")
    public native NSArray<GKTurnBasedParticipant> getRecipients();
    @Property(selector = "status")
    public native GKTurnBasedExchangeStatus getStatus();
    @Property(selector = "message")
    public native String getMessage();
    @Property(selector = "data")
    public native NSData getData();
    @Property(selector = "sendDate")
    public native NSDate getSendDate();
    @Property(selector = "timeoutDate")
    public native NSDate getTimeoutDate();
    @Property(selector = "completionDate")
    public native NSDate getCompletionDate();
    @Property(selector = "replies")
    public native NSArray<?> getReplies();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="GKExchangeTimeoutDefault", optional=true)
    public static native double TimeoutDefault();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="GKExchangeTimeoutNone", optional=true)
    public static native double TimeoutNone();
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "cancelWithLocalizableMessageKey:arguments:completionHandler:")
    public native void cancel(String key, NSArray<NSString> arguments, @Block VoidBlock1<NSError> completionHandler);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "replyWithLocalizableMessageKey:arguments:data:completionHandler:")
    public native void reply(String key, NSArray<?> arguments, NSData data, @Block VoidBlock1<NSError> completionHandler);
    /*</methods>*/
}
