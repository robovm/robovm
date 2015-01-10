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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AURenderCallbackStruct/*</name>*/ 
    extends /*<extends>*/Struct<AURenderCallbackStruct>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AURenderCallbackStructPtr extends Ptr<AURenderCallbackStruct, AURenderCallbackStructPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AURenderCallbackStruct() {}
    public AURenderCallbackStruct(FunctionPtr inputProc, VoidPtr inputProcRefCon) {
        this.setInputProc(inputProc);
        this.setInputProcRefCon(inputProcRefCon);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native FunctionPtr getInputProc();
    @StructMember(0) public native AURenderCallbackStruct setInputProc(FunctionPtr inputProc);
    @StructMember(1) public native VoidPtr getInputProcRefCon();
    @StructMember(1) public native AURenderCallbackStruct setInputProcRefCon(VoidPtr inputProcRefCon);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
