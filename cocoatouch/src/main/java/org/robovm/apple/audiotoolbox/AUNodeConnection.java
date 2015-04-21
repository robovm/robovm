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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUNodeConnection/*</name>*/ 
    extends /*<extends>*/Struct<AUNodeConnection>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUNodeConnectionPtr extends Ptr<AUNodeConnection, AUNodeConnectionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AUNodeConnection() {}
    public AUNodeConnection(int sourceNode, int sourceOutputNumber, int destNode, int destInputNumber) {
        this.setSourceNode(sourceNode);
        this.setSourceOutputNumber(sourceOutputNumber);
        this.setDestNode(destNode);
        this.setDestInputNumber(destInputNumber);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getSourceNode();
    @StructMember(0) public native AUNodeConnection setSourceNode(int sourceNode);
    @StructMember(1) public native int getSourceOutputNumber();
    @StructMember(1) public native AUNodeConnection setSourceOutputNumber(int sourceOutputNumber);
    @StructMember(2) public native int getDestNode();
    @StructMember(2) public native AUNodeConnection setDestNode(int destNode);
    @StructMember(3) public native int getDestInputNumber();
    @StructMember(3) public native AUNodeConnection setDestInputNumber(int destInputNumber);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
