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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUNodeInteraction/*</name>*/ 
    extends /*<extends>*/Struct<AUNodeInteraction>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUNodeInteractionPtr extends Ptr<AUNodeInteraction, AUNodeInteractionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AUNodeInteraction() {}
    public AUNodeInteraction(AUNodeInteractionType nodeInteractionType) {
        this.setNodeInteractionType(nodeInteractionType);
    }
    /*</constructors>*/
    public AUNodeInteraction(AUNodeInteractionType nodeInteractionType, AUNodeInteractionInfo nodeInteraction) {
        this.setNodeInteractionType(nodeInteractionType);
        this.setNodeInteraction(nodeInteraction);
    }
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native AUNodeInteractionType getNodeInteractionType();
    @StructMember(0) public native AUNodeInteraction setNodeInteractionType(AUNodeInteractionType nodeInteractionType);
    /*</members>*/
    @StructMember(1) public native @ByVal AUNodeInteractionInfo getNodeInteraction();
    @StructMember(1) public native AUNodeInteraction setNodeInteraction(@ByVal AUNodeInteractionInfo nodeInteraction);
    /*<methods>*//*</methods>*/
}
