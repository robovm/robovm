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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPStream/*</name>*/ 
    extends /*<extends>*/NSInputStream/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFHTTPStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static NSInputStream create(CFHTTPMessage request) {
        return create(null, request);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static NSInputStream create(CFHTTPMessage requestHeaders, NSInputStream requestBody) {
        return create(null, requestHeaders, requestBody);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Bridge(symbol="CFReadStreamCreateForHTTPRequest", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSInputStream create(CFAllocator alloc, CFHTTPMessage request);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Bridge(symbol="CFReadStreamCreateForStreamedHTTPRequest", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSInputStream create(CFAllocator alloc, CFHTTPMessage requestHeaders, NSInputStream requestBody);
    /*</methods>*/
}
