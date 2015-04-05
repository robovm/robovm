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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUParameter/*</name>*/ 
    extends /*<extends>*/Struct<AUParameter>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUParameterPtr extends Ptr<AUParameter, AUParameterPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AUParameter() {}
    public AUParameter(AudioUnit audioUnit, AUParameterID parameterID, AUScope scope, int element) {
        this.setAudioUnit(audioUnit);
        this.setParameterID(parameterID);
        this.setScope(scope);
        this.setElement(element);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native AudioUnit getAudioUnit();
    @StructMember(0) public native AUParameter setAudioUnit(AudioUnit audioUnit);
    @StructMember(1) public native AUParameterID getParameterID();
    @StructMember(1) public native AUParameter setParameterID(AUParameterID parameterID);
    @StructMember(2) public native AUScope getScope();
    @StructMember(2) public native AUParameter setScope(AUScope scope);
    @StructMember(3) public native int getElement();
    @StructMember(3) public native AUParameter setElement(int element);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
