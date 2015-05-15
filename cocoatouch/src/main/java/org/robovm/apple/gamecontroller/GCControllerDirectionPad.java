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
package org.robovm.apple.gamecontroller;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameController") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCControllerDirectionPad/*</name>*/ 
    extends /*<extends>*/GCControllerElement/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCControllerDirectionPadPtr extends Ptr<GCControllerDirectionPad, GCControllerDirectionPadPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GCControllerDirectionPad.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCControllerDirectionPad() {}
    protected GCControllerDirectionPad(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "valueChangedHandler")
    public native @Block VoidBlock3<GCControllerDirectionPad, Float, Float> getValueChangedHandler();
    @Property(selector = "setValueChangedHandler:")
    public native void setValueChangedHandler(@Block VoidBlock3<GCControllerDirectionPad, Float, Float> v);
    @Property(selector = "xAxis")
    public native GCControllerAxisInput getXAxis();
    @Property(selector = "yAxis")
    public native GCControllerAxisInput getYAxis();
    @Property(selector = "up")
    public native GCControllerButtonInput getUp();
    @Property(selector = "down")
    public native GCControllerButtonInput getDown();
    @Property(selector = "left")
    public native GCControllerButtonInput getLeft();
    @Property(selector = "right")
    public native GCControllerButtonInput getRight();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
