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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGLayer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGLayerPtr extends Ptr<CGLayer, CGLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGLayer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGLayerCreateWithContext", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGLayer create(CGContext context, @ByVal CGSize size, NSDictionary<?, ?> auxiliaryInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGLayerGetSize", optional=true)
    public native @ByVal CGSize getSize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGLayerGetContext", optional=true)
    public native CGContext getContext();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGLayerGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /*</methods>*/
}
