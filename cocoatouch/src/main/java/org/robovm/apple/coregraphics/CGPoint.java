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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPoint/*</name>*/ 
    extends /*<extends>*/Struct<CGPoint>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPointPtr extends Ptr<CGPoint, CGPointPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGPoint() {}
    public CGPoint(@MachineSizedFloat double x, @MachineSizedFloat double y) {
        this.setX(x);
        this.setY(y);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedFloat double getX();
    @StructMember(0) public native CGPoint setX(@MachineSizedFloat double x);
    @StructMember(1) public native @MachineSizedFloat double getY();
    @StructMember(1) public native CGPoint setY(@MachineSizedFloat double y);
    /*</members>*/
    
    public static CGPoint fromString(String string) {
        return UIGeometry.stringToCGPoint(string);
    }
    
    public static CGPoint fromDictionary(NSDictionary<NSString, NSNumber> dict) {
        CGPoint p = new CGPoint();
        if (!fromDictionary(dict, p)) {
            throw new IllegalArgumentException("Failed to create CGPoint from dictionary " + dict);
        }
        return p;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGPoint && equalToPoint(this, (CGPoint) obj);
    }
    
    @Override
    public String toString() {
        return UIGeometry.toString(this);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGPointZero", optional=true)
    public static native @ByVal CGPoint Zero();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean equalToPoint(CGPoint point2) { return equalToPoint(this, point2); }
    @Bridge(symbol="CGPointEqualToPoint", optional=true)
    private static native boolean equalToPoint(@ByVal CGPoint point1, @ByVal CGPoint point2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDictionary<NSString, NSNumber> toDictionary() { return toDictionary(this); }
    @Bridge(symbol="CGPointCreateDictionaryRepresentation", optional=true)
    private static native NSDictionary<NSString, NSNumber> toDictionary(@ByVal CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPointMakeWithDictionaryRepresentation", optional=true)
    private static native boolean fromDictionary(NSDictionary<NSString, NSNumber> dict, CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint apply(CGAffineTransform t) { return apply(this, t); }
    @Bridge(symbol="CGPointApplyAffineTransform", optional=true)
    private static native @ByVal CGPoint apply(@ByVal CGPoint point, @ByVal CGAffineTransform t);
    /*</methods>*/
}
