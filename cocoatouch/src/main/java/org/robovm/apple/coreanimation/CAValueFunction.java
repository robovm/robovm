/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAValueFunction/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CAValueFunctionPtr extends Ptr<CAValueFunction, CAValueFunctionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAValueFunction.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAValueFunction() {}
    protected CAValueFunction(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCAValueFunctionRotateX")
    public static native String FunctionRotateX();
    @GlobalValue(symbol="kCAValueFunctionRotateY")
    public static native String FunctionRotateY();
    @GlobalValue(symbol="kCAValueFunctionRotateZ")
    public static native String FunctionRotateZ();
    @GlobalValue(symbol="kCAValueFunctionScale")
    public static native String FunctionScale();
    @GlobalValue(symbol="kCAValueFunctionScaleX")
    public static native String FunctionScaleX();
    @GlobalValue(symbol="kCAValueFunctionScaleY")
    public static native String FunctionScaleY();
    @GlobalValue(symbol="kCAValueFunctionScaleZ")
    public static native String FunctionScaleZ();
    @GlobalValue(symbol="kCAValueFunctionTranslate")
    public static native String FunctionTranslate();
    @GlobalValue(symbol="kCAValueFunctionTranslateX")
    public static native String FunctionTranslateX();
    @GlobalValue(symbol="kCAValueFunctionTranslateY")
    public static native String FunctionTranslateY();
    @GlobalValue(symbol="kCAValueFunctionTranslateZ")
    public static native String FunctionTranslateZ();
    
    @Method(selector = "functionWithName:")
    public static native NSObject functionWithName$(String name);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
