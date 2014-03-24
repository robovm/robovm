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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableURLRequest/*</name>*/ 
    extends /*<extends>*/NSURLRequest/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableURLRequestPtr extends Ptr<NSMutableURLRequest, NSMutableURLRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableURLRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableURLRequest() {}
    protected NSMutableURLRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setURL:")
    public native void setURL$(NSURL URL);
    @Method(selector = "setCachePolicy:")
    public native void setCachePolicy$(NSURLRequestCachePolicy policy);
    @Method(selector = "setTimeoutInterval:")
    public native void setTimeoutInterval$(double seconds);
    @Method(selector = "setMainDocumentURL:")
    public native void setMainDocumentURL$(NSURL URL);
    @Method(selector = "setNetworkServiceType:")
    public native void setNetworkServiceType$(NSURLRequestNetworkServiceType networkServiceType);
    @Method(selector = "setAllowsCellularAccess:")
    public native void setAllowsCellularAccess$(boolean allow);
    @Method(selector = "setHTTPMethod:")
    public native void setHTTPMethod$(String method);
    @Method(selector = "setAllHTTPHeaderFields:")
    public native void setAllHTTPHeaderFields$(NSDictionary<?, ?> headerFields);
    @Method(selector = "setValue:forHTTPHeaderField:")
    public native void setValue$forHTTPHeaderField$(String value, String field);
    @Method(selector = "addValue:forHTTPHeaderField:")
    public native void addValue$forHTTPHeaderField$(String value, String field);
    @Method(selector = "setHTTPBody:")
    public native void setHTTPBody$(NSData data);
    @Method(selector = "setHTTPBodyStream:")
    public native void setHTTPBodyStream$(NSInputStream inputStream);
    @Method(selector = "setHTTPShouldHandleCookies:")
    public native void setHTTPShouldHandleCookies$(boolean should);
    @Method(selector = "setHTTPShouldUsePipelining:")
    public native void setHTTPShouldUsePipelining$(boolean shouldUsePipelining);
    /*</methods>*/
}
