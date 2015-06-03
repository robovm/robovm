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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPlane/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNPlanePtr extends Ptr<SCNPlane, SCNPlanePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNPlane.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNPlane() {}
    protected SCNPlane(SkipInit skipInit) { super(skipInit); }
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
    @Property(selector = "widthSegmentCount")
    public native @MachineSizedSInt long getWidthSegmentCount();
    @Property(selector = "setWidthSegmentCount:")
    public native void setWidthSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "heightSegmentCount")
    public native @MachineSizedSInt long getHeightSegmentCount();
    @Property(selector = "setHeightSegmentCount:")
    public native void setHeightSegmentCount(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "cornerRadius")
    public native @MachineSizedFloat double getCornerRadius();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCornerRadius:")
    public native void setCornerRadius(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "cornerSegmentCount")
    public native @MachineSizedSInt long getCornerSegmentCount();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCornerSegmentCount:")
    public native void setCornerSegmentCount(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "planeWithWidth:height:")
    public static native SCNPlane create(@MachineSizedFloat double width, @MachineSizedFloat double height);
    /*</methods>*/
}
