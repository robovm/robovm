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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/SCNShadable/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "shaderModifiers")
    @org.robovm.rt.bro.annotation.Marshaler(SCNShaderModifierEntryPoint.AsStringMapMarshaler.class) Map<SCNShaderModifierEntryPoint, String> getShaderModifiers();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setShaderModifiers:")
    void setShaderModifiers(@org.robovm.rt.bro.annotation.Marshaler(SCNShaderModifierEntryPoint.AsStringMapMarshaler.class) Map<SCNShaderModifierEntryPoint, String> v);
    @Property(selector = "program")
    SCNProgram getProgram();
    @Property(selector = "setProgram:")
    void setProgram(SCNProgram v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "handleBindingOfSymbol:usingBlock:")
    void handleBindingOfSymbol(String symbol, @Block VoidBlock4<Integer, Integer, SCNNode, SCNRenderer> block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "handleUnbindingOfSymbol:usingBlock:")
    void handleUnbindingOfSymbol(String symbol, @Block VoidBlock4<Integer, Integer, SCNNode, SCNRenderer> block);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
