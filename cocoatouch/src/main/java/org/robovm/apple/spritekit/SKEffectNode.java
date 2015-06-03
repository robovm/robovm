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

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKEffectNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKEffectNodePtr extends Ptr<SKEffectNode, SKEffectNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKEffectNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKEffectNode() {}
    protected SKEffectNode(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @WeaklyLinked
    @Property(selector = "filter")
    public native CIFilter getFilter();
    @WeaklyLinked
    @Property(selector = "setFilter:")
    public native void setFilter(CIFilter v);
    @Property(selector = "shouldCenterFilter")
    public native boolean shouldCenterFilter();
    @Property(selector = "setShouldCenterFilter:")
    public native void setShouldCenterFilter(boolean v);
    @Property(selector = "shouldEnableEffects")
    public native boolean shouldEnableEffects();
    @Property(selector = "setShouldEnableEffects:")
    public native void setShouldEnableEffects(boolean v);
    @Property(selector = "shouldRasterize")
    public native boolean shouldRasterize();
    @Property(selector = "setShouldRasterize:")
    public native void setShouldRasterize(boolean v);
    @Property(selector = "blendMode")
    public native SKBlendMode getBlendMode();
    @Property(selector = "setBlendMode:")
    public native void setBlendMode(SKBlendMode v);
    @Property(selector = "shader")
    public native SKShader getShader();
    @Property(selector = "setShader:")
    public native void setShader(SKShader v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
