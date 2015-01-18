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
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CABasicAnimation/*</name>*/ 
    extends /*<extends>*/CAPropertyAnimation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CABasicAnimationPtr extends Ptr<CABasicAnimation, CABasicAnimationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CABasicAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CABasicAnimation() {}
    protected CABasicAnimation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public double getNumericFromValue() {
        NSObject val = getFromValue();
        if (val instanceof NSNumber) {
            return ((NSNumber) val).doubleValue();
        }
        return 0;
    }
    public void setNumericFromValue(double value) {
        setFromValue(NSNumber.valueOf(value));
    }
    public double getNumericToValue() {
        NSObject val = getToValue();
        if (val instanceof NSNumber) {
            return ((NSNumber) val).doubleValue();
        }
        return 0;
    }
    public void setNumericToValue(double value) {
        setToValue(NSNumber.valueOf(value));
    }
    public double getNumericByValue() {
        NSObject val = getByValue();
        if (val instanceof NSNumber) {
            return ((NSNumber) val).doubleValue();
        }
        return 0;
    }
    public void setNumericByValue(double value) {
        setByValue(NSNumber.valueOf(value));
    }
    /*<properties>*/
    @Property(selector = "fromValue")
    public native NSObject getFromValue();
    @Property(selector = "setFromValue:")
    public native void setFromValue(NSObject v);
    @Property(selector = "toValue")
    public native NSObject getToValue();
    @Property(selector = "setToValue:")
    public native void setToValue(NSObject v);
    @Property(selector = "byValue")
    public native NSObject getByValue();
    @Property(selector = "setByValue:")
    public native void setByValue(NSObject v);
    /*</properties>*/
    /*<members>*//*</members>*/

    @Method(selector = "animationWithKeyPath:")
    public static native CABasicAnimation create(String path);

    @Method(selector = "animation")
    public static native CABasicAnimation create();
    /*<methods>*/
    
    /*</methods>*/
}
