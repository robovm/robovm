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
package org.robovm.apple.social;

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
import org.robovm.apple.social.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Social") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SLRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SLRequestPtr extends Ptr<SLRequest, SLRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SLRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SLRequest() {}
    protected SLRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
//    @Property(selector = "account")
//    public native ACAccount getAccount();
//    @Property(selector = "setAccount:", strongRef = true)
//    public native ACAccount setAccount(ACAccount account);
    @Property(selector = "requestMethod")
    public native SLRequestMethod getRequestMethod();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "parameters")
    public native NSDictionary<?, ?> getParameters();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "requestForServiceType:requestMethod:URL:parameters:")
    public static native SLRequest create(String serviceType, SLRequestMethod requestMethod, NSURL url, NSDictionary<?, ?> parameters);
    @Method(selector = "addMultipartData:withName:type:filename:")
    public native void addMultipartData(NSData data, String name, String type, String filename);
    @Method(selector = "preparedURLRequest")
    public native NSURLRequest getPreparedURLRequest();
    @Method(selector = "performRequestWithHandler:")
    public native void performRequest(@Block VoidBlock3<NSData, NSHTTPURLResponse, NSError> handler);
    /*</methods>*/
}
