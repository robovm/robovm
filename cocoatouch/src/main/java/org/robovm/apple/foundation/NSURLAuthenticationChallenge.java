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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLAuthenticationChallenge/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLAuthenticationChallengePtr extends Ptr<NSURLAuthenticationChallenge, NSURLAuthenticationChallengePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLAuthenticationChallenge.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLAuthenticationChallenge() {}
    protected NSURLAuthenticationChallenge(SkipInit skipInit) { super(skipInit); }
    public NSURLAuthenticationChallenge(NSURLProtectionSpace space, NSURLCredential credential, @MachineSizedSInt long previousFailureCount, NSURLResponse response, NSError error, NSURLAuthenticationChallengeSender sender) { super((SkipInit) null); initObject(init(space, credential, previousFailureCount, response, error, sender)); }
    public NSURLAuthenticationChallenge(NSURLAuthenticationChallenge challenge, NSURLAuthenticationChallengeSender sender) { super((SkipInit) null); initObject(init(challenge, sender)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "protectionSpace")
    public native NSURLProtectionSpace getProtectionSpace();
    @Property(selector = "proposedCredential")
    public native NSURLCredential getProposedCredential();
    @Property(selector = "previousFailureCount")
    public native @MachineSizedSInt long getPreviousFailureCount();
    @Property(selector = "failureResponse")
    public native NSURLResponse getFailureResponse();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "sender")
    public native NSURLAuthenticationChallengeSender getSender();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithProtectionSpace:proposedCredential:previousFailureCount:failureResponse:error:sender:")
    protected native @Pointer long init(NSURLProtectionSpace space, NSURLCredential credential, @MachineSizedSInt long previousFailureCount, NSURLResponse response, NSError error, NSURLAuthenticationChallengeSender sender);
    @Method(selector = "initWithAuthenticationChallenge:sender:")
    protected native @Pointer long init(NSURLAuthenticationChallenge challenge, NSURLAuthenticationChallengeSender sender);
    /*</methods>*/
}
