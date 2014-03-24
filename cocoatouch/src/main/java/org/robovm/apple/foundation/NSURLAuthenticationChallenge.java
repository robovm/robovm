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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    public NSURLAuthenticationChallenge(NSURLProtectionSpace space, NSURLCredential credential, @MachineSizedSInt long previousFailureCount, NSURLResponse response, NSError error, NSURLAuthenticationChallengeSender sender) { super((SkipInit) null); initObject(initWithProtectionSpace$proposedCredential$previousFailureCount$failureResponse$error$sender$(space, credential, previousFailureCount, response, error, sender)); }
    public NSURLAuthenticationChallenge(NSURLAuthenticationChallenge challenge, NSURLAuthenticationChallengeSender sender) { super((SkipInit) null); initObject(initWithAuthenticationChallenge$sender$(challenge, sender)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithProtectionSpace:proposedCredential:previousFailureCount:failureResponse:error:sender:")
    protected native @Pointer long initWithProtectionSpace$proposedCredential$previousFailureCount$failureResponse$error$sender$(NSURLProtectionSpace space, NSURLCredential credential, @MachineSizedSInt long previousFailureCount, NSURLResponse response, NSError error, NSURLAuthenticationChallengeSender sender);
    @Method(selector = "initWithAuthenticationChallenge:sender:")
    protected native @Pointer long initWithAuthenticationChallenge$sender$(NSURLAuthenticationChallenge challenge, NSURLAuthenticationChallengeSender sender);
    @Method(selector = "protectionSpace")
    public native NSURLProtectionSpace protectionSpace();
    @Method(selector = "proposedCredential")
    public native NSURLCredential proposedCredential();
    @Method(selector = "previousFailureCount")
    public native @MachineSizedSInt long previousFailureCount();
    @Method(selector = "failureResponse")
    public native NSURLResponse failureResponse();
    @Method(selector = "error")
    public native NSError error();
    @Method(selector = "sender")
    public native NSURLAuthenticationChallengeSender sender();
    /*</methods>*/
}
