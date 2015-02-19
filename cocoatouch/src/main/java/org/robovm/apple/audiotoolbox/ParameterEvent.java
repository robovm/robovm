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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ParameterEvent/*</name>*/ 
    extends /*<extends>*/Struct<ParameterEvent>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ParameterEventPtr extends Ptr<ParameterEvent, ParameterEventPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ParameterEvent() {}
    public ParameterEvent(int parameterID, int scope, int element, float value) {
        this.setParameterID(parameterID);
        this.setScope(scope);
        this.setElement(element);
        this.setValue(value);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getParameterID();
    @StructMember(0) public native ParameterEvent setParameterID(int parameterID);
    @StructMember(1) public native int getScope();
    @StructMember(1) public native ParameterEvent setScope(int scope);
    @StructMember(2) public native int getElement();
    @StructMember(2) public native ParameterEvent setElement(int element);
    @StructMember(3) public native float getValue();
    @StructMember(3) public native ParameterEvent setValue(float value);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
