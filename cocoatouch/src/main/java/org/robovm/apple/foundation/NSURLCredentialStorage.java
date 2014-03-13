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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLCredentialStorage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLCredentialStoragePtr extends Ptr<NSURLCredentialStorage, NSURLCredentialStoragePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLCredentialStorage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLCredentialStorage() {}
    protected NSURLCredentialStorage(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSURLCredentialStorageChangedNotification")
    public static native String NotificationChanged();
    @GlobalValue(symbol="NSURLCredentialStorageRemoveSynchronizableCredentials")
    public static native NSString KeyRemoveSynchronizableCredentials();
    
    @Method(selector = "credentialsForProtectionSpace:")
    public native NSDictionary<?, ?> credentialsForProtectionSpace$(NSURLProtectionSpace space);
    @Method(selector = "allCredentials")
    public native NSDictionary<?, ?> allCredentials();
    @Method(selector = "setCredential:forProtectionSpace:")
    public native void setCredential$forProtectionSpace$(NSURLCredential credential, NSURLProtectionSpace space);
    @Method(selector = "removeCredential:forProtectionSpace:")
    public native void removeCredential$forProtectionSpace$(NSURLCredential credential, NSURLProtectionSpace space);
    @Method(selector = "removeCredential:forProtectionSpace:options:")
    public native void removeCredential$forProtectionSpace$options$(NSURLCredential credential, NSURLProtectionSpace space, NSDictionary<?, ?> options);
    @Method(selector = "defaultCredentialForProtectionSpace:")
    public native NSURLCredential defaultCredentialForProtectionSpace$(NSURLProtectionSpace space);
    @Method(selector = "setDefaultCredential:forProtectionSpace:")
    public native void setDefaultCredential$forProtectionSpace$(NSURLCredential credential, NSURLProtectionSpace space);
    @Method(selector = "sharedCredentialStorage")
    public static native NSURLCredentialStorage sharedCredentialStorage();
    /*</methods>*/
}
