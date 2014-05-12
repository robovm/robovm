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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAKeyframeAnimation/*</name>*/ 
    extends /*<extends>*/CAPropertyAnimation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAKeyframeAnimationPtr extends Ptr<CAKeyframeAnimation, CAKeyframeAnimationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAKeyframeAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAKeyframeAnimation() {}
    protected CAKeyframeAnimation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "values")
    public native NSArray<?> getValues();
    @Property(selector = "setValues:")
    public native void setValues(NSArray<?> v);
    @Property(selector = "path")
    public native CGPath getPath();
    @Property(selector = "setPath:")
    public native void setPath(CGPath v);
    @Property(selector = "keyTimes")
    public native NSArray<?> getKeyTimes();
    @Property(selector = "setKeyTimes:")
    public native void setKeyTimes(NSArray<?> v);
    @Property(selector = "timingFunctions")
    public native NSArray<?> getTimingFunctions();
    @Property(selector = "setTimingFunctions:")
    public native void setTimingFunctions(NSArray<?> v);
    @Property(selector = "calculationMode")
    public native String getCalculationMode();
    @Property(selector = "setCalculationMode:")
    public native void setCalculationMode(String v);
    @Property(selector = "tensionValues")
    public native NSArray<NSNumber> getTensionValues();
    @Property(selector = "setTensionValues:")
    public native void setTensionValues(NSArray<NSNumber> v);
    @Property(selector = "continuityValues")
    public native NSArray<?> getContinuityValues();
    @Property(selector = "setContinuityValues:")
    public native void setContinuityValues(NSArray<?> v);
    @Property(selector = "biasValues")
    public native NSArray<?> getBiasValues();
    @Property(selector = "setBiasValues:")
    public native void setBiasValues(NSArray<?> v);
    @Property(selector = "rotationMode")
    public native String getRotationMode();
    @Property(selector = "setRotationMode:")
    public native void setRotationMode(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
