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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKSpriteNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKSpriteNodePtr extends Ptr<SKSpriteNode, SKSpriteNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKSpriteNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKSpriteNode() {}
    protected SKSpriteNode(SkipInit skipInit) { super(skipInit); }
    public SKSpriteNode(SKTexture texture, UIColor color, @ByVal CGSize size) { super((SkipInit) null); initObject(init(texture, color, size)); }
    public SKSpriteNode(SKTexture texture) { super((SkipInit) null); initObject(init(texture)); }
    public SKSpriteNode(String name) { super((SkipInit) null); initObject(init(name)); }
    public SKSpriteNode(UIColor color, @ByVal CGSize size) { super((SkipInit) null); initObject(init(color, size)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "texture")
    public native SKTexture getTexture();
    @Property(selector = "setTexture:")
    public native void setTexture(SKTexture v);
    @Property(selector = "centerRect")
    public native @ByVal CGRect getCenterRect();
    @Property(selector = "setCenterRect:")
    public native void setCenterRect(@ByVal CGRect v);
    @Property(selector = "colorBlendFactor")
    public native @MachineSizedFloat double getColorBlendFactor();
    @Property(selector = "setColorBlendFactor:")
    public native void setColorBlendFactor(@MachineSizedFloat double v);
    @Property(selector = "color")
    public native UIColor getColor();
    @Property(selector = "setColor:")
    public native void setColor(UIColor v);
    @Property(selector = "blendMode")
    public native SKBlendMode getBlendMode();
    @Property(selector = "setBlendMode:")
    public native void setBlendMode(SKBlendMode v);
    @Property(selector = "anchorPoint")
    public native @ByVal CGPoint getAnchorPoint();
    @Property(selector = "setAnchorPoint:")
    public native void setAnchorPoint(@ByVal CGPoint v);
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "setSize:")
    public native void setSize(@ByVal CGSize v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTexture:color:size:")
    protected native @Pointer long init(SKTexture texture, UIColor color, @ByVal CGSize size);
    @Method(selector = "initWithTexture:")
    protected native @Pointer long init(SKTexture texture);
    @Method(selector = "initWithImageNamed:")
    protected native @Pointer long init(String name);
    @Method(selector = "initWithColor:size:")
    protected native @Pointer long init(UIColor color, @ByVal CGSize size);
    @Method(selector = "spriteNodeWithTexture:size:")
    public static native SKSpriteNode create(SKTexture texture, @ByVal CGSize size);
    @Method(selector = "spriteNodeWithTexture:")
    public static native SKSpriteNode create(SKTexture texture);
    @Method(selector = "spriteNodeWithImageNamed:")
    public static native SKSpriteNode create(String name);
    @Method(selector = "spriteNodeWithColor:size:")
    public static native SKSpriteNode create(UIColor color, @ByVal CGSize size);
    /*</methods>*/
}
