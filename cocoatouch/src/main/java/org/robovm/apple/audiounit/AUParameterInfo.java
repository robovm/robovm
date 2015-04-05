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
package org.robovm.apple.audiounit;

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
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUParameterInfo/*</name>*/ 
    extends /*<extends>*/Struct<AUParameterInfo>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUParameterInfoPtr extends Ptr<AUParameterInfo, AUParameterInfoPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) private native @Array({52}) ByteBuffer getName();
    @StructMember(0) private native AUParameterInfo setName(@Array({52}) ByteBuffer name);
    @StructMember(1) public native String getUnitName();
    @StructMember(1) public native AUParameterInfo setUnitName(String unitName);
    @StructMember(2) public native int getClumpID();
    @StructMember(2) public native AUParameterInfo setClumpID(int clumpID);
    @StructMember(3) public native String getNameString();
    @StructMember(3) public native AUParameterInfo setNameString(String nameString);
    @StructMember(4) public native AUParameterUnit getUnit();
    @StructMember(4) public native AUParameterInfo setUnit(AUParameterUnit unit);
    @StructMember(5) public native float getMinValue();
    @StructMember(5) public native AUParameterInfo setMinValue(float minValue);
    @StructMember(6) public native float getMaxValue();
    @StructMember(6) public native AUParameterInfo setMaxValue(float maxValue);
    @StructMember(7) public native float getDefaultValue();
    @StructMember(7) public native AUParameterInfo setDefaultValue(float defaultValue);
    @StructMember(8) public native AUParameterFlags getFlags();
    @StructMember(8) public native AUParameterInfo setFlags(AUParameterFlags flags);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
