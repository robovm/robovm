/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.coremidi;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIControlTransform/*</name>*/ 
    extends /*<extends>*/Struct<MIDIControlTransform>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIControlTransformPtr extends Ptr<MIDIControlTransform, MIDIControlTransformPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIControlTransform() {}
    public MIDIControlTransform(MIDITransformControlType controlType, MIDITransformControlType remappedControlType, short controlNumber, MIDITransformType transform, short param) {
        this.setControlType(controlType);
        this.setRemappedControlType(remappedControlType);
        this.setControlNumber(controlNumber);
        this.setTransform(transform);
        this.setParam(param);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDITransformControlType getControlType();
    @StructMember(0) public native MIDIControlTransform setControlType(MIDITransformControlType controlType);
    @StructMember(1) public native MIDITransformControlType getRemappedControlType();
    @StructMember(1) public native MIDIControlTransform setRemappedControlType(MIDITransformControlType remappedControlType);
    @StructMember(2) public native short getControlNumber();
    @StructMember(2) public native MIDIControlTransform setControlNumber(short controlNumber);
    @StructMember(3) public native MIDITransformType getTransform();
    @StructMember(3) public native MIDIControlTransform setTransform(MIDITransformType transform);
    @StructMember(4) public native short getParam();
    @StructMember(4) public native MIDIControlTransform setParam(short param);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
