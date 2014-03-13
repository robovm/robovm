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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSocketSignature/*</name>*/ 
    extends /*<extends>*/Struct<CFSocketSignature>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFSocketSignaturePtr extends Ptr<CFSocketSignature, CFSocketSignaturePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFSocketSignature() {}
    public CFSocketSignature(int protocolFamily, int socketType, int protocol, CFData address) {
        this.protocolFamily(protocolFamily);
        this.socketType(socketType);
        this.protocol(protocol);
        this.address(address);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int protocolFamily();
    @StructMember(0) public native CFSocketSignature protocolFamily(int protocolFamily);
    @StructMember(1) public native int socketType();
    @StructMember(1) public native CFSocketSignature socketType(int socketType);
    @StructMember(2) public native int protocol();
    @StructMember(2) public native CFSocketSignature protocol(int protocol);
    @StructMember(3) public native CFData address();
    @StructMember(3) public native CFSocketSignature address(CFData address);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
