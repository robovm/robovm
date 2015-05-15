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
package org.robovm.apple.twitter;

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
import org.robovm.apple.social.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 * @deprecated Deprecated in iOS 6.0.
 */
@Deprecated
/*</javadoc>*/
/*<annotations>*/@Library("Twitter") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/TWRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class TWRequestPtr extends Ptr<TWRequest, TWRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(TWRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public TWRequest() {}
    protected TWRequest(SkipInit skipInit) { super(skipInit); }
    public TWRequest(NSURL url, NSDictionary<NSString, ?> parameters, TWRequestMethod requestMethod) { super((SkipInit) null); initObject(init(url, parameters, requestMethod)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "account")
    public native ACAccount getAccount();
    @Property(selector = "setAccount:")
    public native void setAccount(ACAccount v);
    @Property(selector = "requestMethod")
    public native TWRequestMethod getRequestMethod();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "parameters")
    public native NSDictionary<NSString, ?> getParameters();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:parameters:requestMethod:")
    protected native @Pointer long init(NSURL url, NSDictionary<NSString, ?> parameters, TWRequestMethod requestMethod);
    @Method(selector = "addMultiPartData:withName:type:")
    public native void addMultiPartData(NSData data, String name, String type);
    @Method(selector = "signedURLRequest")
    public native NSURLRequest getSignedURLRequest();
    @Method(selector = "performRequestWithHandler:")
    public native void performRequest(@Block VoidBlock3<NSData, NSHTTPURLResponse, NSError> handler);
    /*</methods>*/
}
