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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/CAAnimationExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAAnimationExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private CAAnimationExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "usesSceneTimeBase")
    public static native boolean usesSceneTimeBase(CAAnimation thiz);
    @Property(selector = "setUsesSceneTimeBase:")
    public static native void setUsesSceneTimeBase(CAAnimation thiz, boolean v);
    @Property(selector = "fadeInDuration")
    public static native @MachineSizedFloat double getFadeInDuration(CAAnimation thiz);
    @Property(selector = "setFadeInDuration:")
    public static native void setFadeInDuration(CAAnimation thiz, @MachineSizedFloat double v);
    @Property(selector = "fadeOutDuration")
    public static native @MachineSizedFloat double getFadeOutDuration(CAAnimation thiz);
    @Property(selector = "setFadeOutDuration:")
    public static native void setFadeOutDuration(CAAnimation thiz, @MachineSizedFloat double v);
    @Property(selector = "animationEvents")
    public static native NSArray<SCNAnimationEvent> getAnimationEvents(CAAnimation thiz);
    @Property(selector = "setAnimationEvents:")
    public static native void setAnimationEvents(CAAnimation thiz, NSArray<SCNAnimationEvent> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
