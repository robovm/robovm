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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKViewPtr extends Ptr<SKView, SKViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKView() {}
    protected SKView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    @Property(selector = "showsFPS")
    public native boolean isShowsFPS();
    @Property(selector = "setShowsFPS:")
    public native void setShowsFPS(boolean v);
    @Property(selector = "showsDrawCount")
    public native boolean isShowsDrawCount();
    @Property(selector = "setShowsDrawCount:")
    public native void setShowsDrawCount(boolean v);
    @Property(selector = "showsNodeCount")
    public native boolean isShowsNodeCount();
    @Property(selector = "setShowsNodeCount:")
    public native void setShowsNodeCount(boolean v);
    @Property(selector = "showsPhysics")
    public native boolean isShowsPhysics();
    @Property(selector = "setShowsPhysics:")
    public native void setShowsPhysics(boolean v);
    @Property(selector = "isAsynchronous")
    public native boolean isAsynchronous();
    @Property(selector = "setAsynchronous:")
    public native void setAsynchronous(boolean v);
    @Property(selector = "ignoresSiblingOrder")
    public native boolean isIgnoresSiblingOrder();
    @Property(selector = "setIgnoresSiblingOrder:")
    public native void setIgnoresSiblingOrder(boolean v);
    @Property(selector = "frameInterval")
    public native @MachineSizedSInt long getFrameInterval();
    @Property(selector = "setFrameInterval:")
    public native void setFrameInterval(@MachineSizedSInt long v);
    @Property(selector = "scene")
    public native SKScene getScene();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "presentScene:")
    public native void presentScene(SKScene scene);
    @Method(selector = "presentScene:transition:")
    public native void presentScene(SKScene scene, SKTransition transition);
    @Method(selector = "textureFromNode:")
    public native SKTexture getTextureFromNode(SKNode node);
    @Method(selector = "convertPoint:toScene:")
    public native @ByVal CGPoint convertPointToScene(@ByVal CGPoint point, SKScene scene);
    @Method(selector = "convertPoint:fromScene:")
    public native @ByVal CGPoint convertPointFromScene(@ByVal CGPoint point, SKScene scene);
    /*</methods>*/
}
