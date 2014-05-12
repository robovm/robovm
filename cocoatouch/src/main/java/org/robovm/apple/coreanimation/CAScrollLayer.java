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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAScrollLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAScrollLayerPtr extends Ptr<CAScrollLayer, CAScrollLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAScrollLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAScrollLayer() {}
    protected CAScrollLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "scrollMode")
    public native String getScrollMode();
    @Property(selector = "setScrollMode:")
    public native void setScrollMode(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollNone", optional=true)
    public static native String ScrollNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollVertically", optional=true)
    public static native String ScrollVertically();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollHorizontally", optional=true)
    public static native String ScrollHorizontally();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollBoth", optional=true)
    public static native String ScrollBoth();
    
    @Method(selector = "scrollToPoint:")
    public native void scrollToPoint$(@ByVal CGPoint p);
    @Method(selector = "scrollToRect:")
    public native void scrollToRect$(@ByVal CGRect r);
    /*</methods>*/
}
