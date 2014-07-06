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
package org.robovm.apple.mapkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("MapKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMapRect/*</name>*/ 
    extends /*<extends>*/Struct<MKMapRect>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMapRectPtr extends Ptr<MKMapRect, MKMapRectPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MKMapRect.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMapRect() {}
    public MKMapRect(MKMapPoint origin, MKMapSize size) {
        this.origin(origin);
        this.size(size);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal MKMapPoint origin();
    @StructMember(0) public native MKMapRect origin(@ByVal MKMapPoint origin);
    @StructMember(1) public native @ByVal MKMapSize size();
    @StructMember(1) public native MKMapRect size(@ByVal MKMapSize size);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectUnion", optional=true)
    public native @ByVal MKMapRect union(@ByVal MKMapRect rect2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectIntersection", optional=true)
    public native @ByVal MKMapRect intersection(@ByVal MKMapRect rect2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectInset", optional=true)
    public native @ByVal MKMapRect inset(double dx, double dy);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectOffset", optional=true)
    public native @ByVal MKMapRect offset(double dx, double dy);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectDivide", optional=true)
    public native void divide(MKMapRect slice, MKMapRect remainder, double amount, CGRectEdge edge);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectContainsPoint", optional=true)
    public native boolean containsPoint(@ByVal MKMapPoint point);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectContainsRect", optional=true)
    public native boolean containsRect(@ByVal MKMapRect rect2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectIntersectsRect", optional=true)
    public native boolean intersectsRect(@ByVal MKMapRect rect2);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectSpans180thMeridian", optional=true)
    public native boolean isSpanning180thMeridian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapRectRemainder", optional=true)
    public native @ByVal MKMapRect remainder();
    /*</methods>*/
}
