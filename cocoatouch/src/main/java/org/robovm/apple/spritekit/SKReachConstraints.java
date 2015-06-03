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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKReachConstraints/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKReachConstraintsPtr extends Ptr<SKReachConstraints, SKReachConstraintsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKReachConstraints.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKReachConstraints() {}
    protected SKReachConstraints(SkipInit skipInit) { super(skipInit); }
    public SKReachConstraints(@MachineSizedFloat double lowerAngleLimit, @MachineSizedFloat double upperAngleLimit) { super((SkipInit) null); initObject(init(lowerAngleLimit, upperAngleLimit)); }
    public SKReachConstraints(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "lowerAngleLimit")
    public native @MachineSizedFloat double getLowerAngleLimit();
    @Property(selector = "setLowerAngleLimit:")
    public native void setLowerAngleLimit(@MachineSizedFloat double v);
    @Property(selector = "upperAngleLimit")
    public native @MachineSizedFloat double getUpperAngleLimit();
    @Property(selector = "setUpperAngleLimit:")
    public native void setUpperAngleLimit(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLowerAngleLimit:upperAngleLimit:")
    protected native @Pointer long init(@MachineSizedFloat double lowerAngleLimit, @MachineSizedFloat double upperAngleLimit);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
