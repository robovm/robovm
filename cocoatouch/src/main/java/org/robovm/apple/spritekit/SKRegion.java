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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKRegion/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKRegionPtr extends Ptr<SKRegion, SKRegionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKRegion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKRegion() {}
    protected SKRegion(SkipInit skipInit) { super(skipInit); }
    public SKRegion(float radius) { super((SkipInit) null); initObject(init(radius)); }
    public SKRegion(@ByVal CGSize size) { super((SkipInit) null); initObject(init(size)); }
    public SKRegion(CGPath path) { super((SkipInit) null); initObject(init(path)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "path")
    public native CGPath getPath();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRadius:")
    protected native @Pointer long init(float radius);
    @Method(selector = "initWithSize:")
    protected native @Pointer long init(@ByVal CGSize size);
    @Method(selector = "initWithPath:")
    protected native @Pointer long init(CGPath path);
    @Method(selector = "inverseRegion")
    public native SKRegion inverseRegion();
    @Method(selector = "regionByUnionWithRegion:")
    public native SKRegion newRegionByUnionWithRegion(SKRegion region);
    @Method(selector = "regionByDifferenceFromRegion:")
    public native SKRegion newRegionByDifferenceFromRegion(SKRegion region);
    @Method(selector = "regionByIntersectionWithRegion:")
    public native SKRegion newRegionByIntersectionWithRegion(SKRegion region);
    @Method(selector = "containsPoint:")
    public native boolean containsPoint(@ByVal CGPoint point);
    @Method(selector = "infiniteRegion")
    public static native SKRegion createInfiniteRegion();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
