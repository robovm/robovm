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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDecimalNumberHandler/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSDecimalNumberBehaviors, NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSDecimalNumberHandlerPtr extends Ptr<NSDecimalNumberHandler, NSDecimalNumberHandlerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDecimalNumberHandler.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSDecimalNumberHandler(SkipInit skipInit) { super(skipInit); }
    public NSDecimalNumberHandler(NSRoundingMode roundingMode, short scale, boolean exact, boolean overflow, boolean underflow, boolean divideByZero) { super((SkipInit) null); initObject(initWithRoundingMode$scale$raiseOnExactness$raiseOnOverflow$raiseOnUnderflow$raiseOnDivideByZero$(roundingMode, scale, exact, overflow, underflow, divideByZero)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRoundingMode:scale:raiseOnExactness:raiseOnOverflow:raiseOnUnderflow:raiseOnDivideByZero:")
    protected native @Pointer long initWithRoundingMode$scale$raiseOnExactness$raiseOnOverflow$raiseOnUnderflow$raiseOnDivideByZero$(NSRoundingMode roundingMode, short scale, boolean exact, boolean overflow, boolean underflow, boolean divideByZero);
    @Method(selector = "defaultDecimalNumberHandler")
    public static native NSDecimalNumberHandler getDefaultDecimalNumberHandler();
    @Method(selector = "roundingMode")
    public native NSRoundingMode getRoundingMode();
    @Method(selector = "scale")
    public native short getScale();
    @Method(selector = "exceptionDuringOperation:error:leftOperand:rightOperand:")
    public native NSDecimalNumber exceptionDuringOperation(Selector operation, NSCalculationError error, NSDecimalNumber leftOperand, NSDecimalNumber rightOperand);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
