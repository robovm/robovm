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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNetServiceDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSNetServiceDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("netServiceWillPublish:")
    public void willPublish(NSNetService sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("netServiceDidPublish:")
    public void didPublish(NSNetService sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("netService:didNotPublish:")
    public void didNotPublish(NSNetService sender, NSNetServiceErrorInfo errorDict) { throw new UnsupportedOperationException(); }
    @NotImplemented("netServiceWillResolve:")
    public void willResolve(NSNetService sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("netServiceDidResolveAddress:")
    public void didResolve(NSNetService sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("netService:didNotResolve:")
    public void didNotResolve(NSNetService sender, NSNetServiceErrorInfo errorDict) { throw new UnsupportedOperationException(); }
    @NotImplemented("netServiceDidStop:")
    public void didStop(NSNetService sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("netService:didUpdateTXTRecordData:")
    public void didUpdateTXTRecordData(NSNetService sender, NSData data) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("netService:didAcceptConnectionWithInputStream:outputStream:")
    public void didAcceptConnection(NSNetService sender, NSInputStream inputStream, NSOutputStream outputStream) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
