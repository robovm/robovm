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
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNBox/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNBoxPtr extends Ptr<SCNBox, SCNBoxPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNBox.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNBox() {}
    protected SCNBox(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "width")
    public native @MachineSizedFloat double getWidth();
    @Property(selector = "setWidth:")
    public native void setWidth(@MachineSizedFloat double v);
    @Property(selector = "height")
    public native @MachineSizedFloat double getHeight();
    @Property(selector = "setHeight:")
    public native void setHeight(@MachineSizedFloat double v);
    @Property(selector = "length")
    public native @MachineSizedFloat double getLength();
    @Property(selector = "setLength:")
    public native void setLength(@MachineSizedFloat double v);
    @Property(selector = "chamferRadius")
    public native @MachineSizedFloat double getChamferRadius();
    @Property(selector = "setChamferRadius:")
    public native void setChamferRadius(@MachineSizedFloat double v);
    @Property(selector = "widthSegmentCount")
    public native @MachineSizedSInt long getWidthSegmentCount();
    @Property(selector = "setWidthSegmentCount:")
    public native void setWidthSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "heightSegmentCount")
    public native @MachineSizedSInt long getHeightSegmentCount();
    @Property(selector = "setHeightSegmentCount:")
    public native void setHeightSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "lengthSegmentCount")
    public native @MachineSizedSInt long getLengthSegmentCount();
    @Property(selector = "setLengthSegmentCount:")
    public native void setLengthSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "chamferSegmentCount")
    public native @MachineSizedSInt long getChamferSegmentCount();
    @Property(selector = "setChamferSegmentCount:")
    public native void setChamferSegmentCount(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "boxWithWidth:height:length:chamferRadius:")
    public static native SCNBox create(@MachineSizedFloat double width, @MachineSizedFloat double height, @MachineSizedFloat double length, @MachineSizedFloat double chamferRadius);
    /*</methods>*/
}
