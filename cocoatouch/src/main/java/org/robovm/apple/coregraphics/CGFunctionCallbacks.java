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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/CGFunctionCallbacks/*</name>*/ 
    extends /*<extends>*/Struct<CGFunctionCallbacks>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGFunctionCallbacksPtr extends Ptr<CGFunctionCallbacks, CGFunctionCallbacksPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGFunctionCallbacks() {}
    public CGFunctionCallbacks(int version, FunctionPtr evaluate, FunctionPtr releaseInfo) {
        this.setVersion(version);
        this.setEvaluate(evaluate);
        this.setReleaseInfo(releaseInfo);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native CGFunctionCallbacks setVersion(int version);
    @StructMember(1) public native FunctionPtr getEvaluate();
    @StructMember(1) public native CGFunctionCallbacks setEvaluate(FunctionPtr evaluate);
    @StructMember(2) public native FunctionPtr getReleaseInfo();
    @StructMember(2) public native CGFunctionCallbacks setReleaseInfo(FunctionPtr releaseInfo);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
