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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSNetServiceDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "netServiceWillPublish:")
    void willPublish(NSNetService sender);
    @Method(selector = "netServiceDidPublish:")
    void didPublish(NSNetService sender);
    @Method(selector = "netService:didNotPublish:")
    void didNotPublish(NSNetService sender, NSDictionary<?, ?> errorDict);
    @Method(selector = "netServiceWillResolve:")
    void willResolve(NSNetService sender);
    @Method(selector = "netServiceDidResolveAddress:")
    void didResolve(NSNetService sender);
    @Method(selector = "netService:didNotResolve:")
    void didNotResolve(NSNetService sender, NSDictionary<?, ?> errorDict);
    @Method(selector = "netServiceDidStop:")
    void didStop(NSNetService sender);
    @Method(selector = "netService:didUpdateTXTRecordData:")
    void didUpdateTXTRecordData(NSNetService sender, NSData data);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "netService:didAcceptConnectionWithInputStream:outputStream:")
    void didAcceptConnection(NSNetService sender, NSInputStream inputStream, NSOutputStream outputStream);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
