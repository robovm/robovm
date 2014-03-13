/*
 * Copyright (C) 2014 Trillian AB
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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSURLAuthenticationChallengeSender/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "useCredential:forAuthenticationChallenge:")
    void useCredential$forAuthenticationChallenge$(NSURLCredential credential, NSURLAuthenticationChallenge challenge);
    @Method(selector = "continueWithoutCredentialForAuthenticationChallenge:")
    void continueWithoutCredentialForAuthenticationChallenge$(NSURLAuthenticationChallenge challenge);
    @Method(selector = "cancelAuthenticationChallenge:")
    void cancelAuthenticationChallenge$(NSURLAuthenticationChallenge challenge);
    @Method(selector = "performDefaultHandlingForAuthenticationChallenge:")
    void performDefaultHandlingForAuthenticationChallenge$(NSURLAuthenticationChallenge challenge);
    @Method(selector = "rejectProtectionSpaceAndContinueWithChallenge:")
    void rejectProtectionSpaceAndContinueWithChallenge$(NSURLAuthenticationChallenge challenge);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
