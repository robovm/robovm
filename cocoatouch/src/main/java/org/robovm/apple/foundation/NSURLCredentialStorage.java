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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLCredentialStorage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeChanged(NSURLCredentialStorage object, final VoidBlock1<NSURLCredentialStorage> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ChangedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSURLCredentialStorage)a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSURLCredentialStoragePtr extends Ptr<NSURLCredentialStorage, NSURLCredentialStoragePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLCredentialStorage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLCredentialStorage() {}
    protected NSURLCredentialStorage(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "allCredentials")
    public native NSDictionary<?, ?> getAllCredentials();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSURLCredentialStorageChangedNotification", optional=true)
    public static native NSString ChangedNotification();
    
    @Method(selector = "credentialsForProtectionSpace:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSURLCredential> getCredentials(NSURLProtectionSpace space);
    @Method(selector = "setCredential:forProtectionSpace:")
    public native void setCredential(NSURLCredential credential, NSURLProtectionSpace space);
    @Method(selector = "removeCredential:forProtectionSpace:")
    public native void removeCredential(NSURLCredential credential, NSURLProtectionSpace space);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeCredential:forProtectionSpace:options:")
    public native void removeCredential(NSURLCredential credential, NSURLProtectionSpace space, NSURLCredentialStorageRemovalOptions options);
    @Method(selector = "defaultCredentialForProtectionSpace:")
    public native NSURLCredential getDefaultCredential(NSURLProtectionSpace space);
    @Method(selector = "setDefaultCredential:forProtectionSpace:")
    public native void setDefaultCredential(NSURLCredential credential, NSURLProtectionSpace space);
    @Method(selector = "sharedCredentialStorage")
    public static native NSURLCredentialStorage getSharedCredentialStorage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getCredentialsForProtectionSpace:task:completionHandler:")
    public native void getCredentialsForProtectionSpace$task$completionHandler$(NSURLProtectionSpace protectionSpace, NSURLSessionTask task, ObjCBlock completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setCredential:forProtectionSpace:task:")
    public native void setCredential$forProtectionSpace$task$(NSURLCredential credential, NSURLProtectionSpace protectionSpace, NSURLSessionTask task);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "removeCredential:forProtectionSpace:options:task:")
    public native void removeCredential$forProtectionSpace$options$task$(NSURLCredential credential, NSURLProtectionSpace protectionSpace, NSDictionary<?, ?> options, NSURLSessionTask task);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getDefaultCredentialForProtectionSpace:task:completionHandler:")
    public native void getDefaultCredentialForProtectionSpace$task$completionHandler$(NSURLProtectionSpace space, NSURLSessionTask task, ObjCBlock completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setDefaultCredential:forProtectionSpace:task:")
    public native void setDefaultCredential$forProtectionSpace$task$(NSURLCredential credential, NSURLProtectionSpace protectionSpace, NSURLSessionTask task);
    /*</methods>*/
}
