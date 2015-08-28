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
package org.robovm.apple.spritekit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
import org.robovm.apple.gameplaykit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKRange/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKRangePtr extends Ptr<SKRange, SKRangePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKRange.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKRange() {}
    protected SKRange(SkipInit skipInit) { super(skipInit); }
    public SKRange(@MachineSizedFloat double lower, @MachineSizedFloat double upper) { super((SkipInit) null); initObject(init(lower, upper)); }
    public SKRange(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "lowerLimit")
    public native @MachineSizedFloat double getLowerLimit();
    @Property(selector = "setLowerLimit:")
    public native void setLowerLimit(@MachineSizedFloat double v);
    @Property(selector = "upperLimit")
    public native @MachineSizedFloat double getUpperLimit();
    @Property(selector = "setUpperLimit:")
    public native void setUpperLimit(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLowerLimit:upperLimit:")
    protected native @Pointer long init(@MachineSizedFloat double lower, @MachineSizedFloat double upper);
    @Method(selector = "rangeWithLowerLimit:upperLimit:")
    public static native SKRange create(@MachineSizedFloat double lower, @MachineSizedFloat double upper);
    @Method(selector = "rangeWithLowerLimit:")
    public static native SKRange createWithLowerLimit(@MachineSizedFloat double lower);
    @Method(selector = "rangeWithUpperLimit:")
    public static native SKRange createWithUpperLimit(@MachineSizedFloat double upper);
    @Method(selector = "rangeWithConstantValue:")
    public static native SKRange createWithConstantValue(@MachineSizedFloat double value);
    @Method(selector = "rangeWithValue:variance:")
    public static native SKRange createWithValue(@MachineSizedFloat double value, @MachineSizedFloat double variance);
    @Method(selector = "rangeWithNoLimits")
    public static native SKRange createWithNoLimits();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
