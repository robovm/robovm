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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGRect/*</name>*/ 
    extends /*<extends>*/Struct<CGRect>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGRectPtr extends Ptr<CGRect, CGRectPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGRect.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CGRect() {}
    public CGRect(CGPoint origin, CGSize size) {
        this.setOrigin(origin);
        this.setSize(size);
    }
    /*</constructors>*/
    
    public CGRect(double x, double y, double width, double height) {
        getOrigin().setX(x).setY(y);
        getSize().setWidth(width).setHeight(height);
    }
    
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CGPoint getOrigin();
    @StructMember(0) public native CGRect setOrigin(@ByVal CGPoint origin);
    @StructMember(1) public native @ByVal CGSize getSize();
    @StructMember(1) public native CGRect setSize(@ByVal CGSize size);
    /*</members>*/
    
    public static CGRect fromString(String string) {
        return UIGeometry.stringToCGRect(string);
    }
    public static CGRect fromDictionary(NSDictionary<NSString, NSNumber> dict) {
        CGRect r = new CGRect();
        if (!fromDictionary(dict, r)) {
            throw new IllegalArgumentException("Failed to create CGRect from dictionary " + dict);
        }
        return r;
    }

    public CGRect inset(UIEdgeInsets insets) {
        getOrigin().setX(getOrigin().getX() + insets.getLeft());
        getOrigin().setY(getOrigin().getY() + insets.getTop());
        getSize().setWidth(getSize().getWidth() - (insets.getLeft() + insets.getRight()));
        getSize().setHeight(getSize().getHeight() - (insets.getTop() + insets.getBottom()));
        return this;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGRect && equalsTo(this, (CGRect) obj);
    }
    @Override
    public String toString() {
        return UIGeometry.toString(this);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGRectZero", optional=true)
    public static native @ByVal CGRect Zero();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGRectNull", optional=true)
    public static native @ByVal CGRect Null();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CGRectInfinite", optional=true)
    public static native @ByVal CGRect Infinite();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMinX() { return getMinX(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMinX", optional=true)
    private static native @MachineSizedFloat double getMinX(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMidX() { return getMidX(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMidX", optional=true)
    private static native @MachineSizedFloat double getMidX(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMaxX() { return getMaxX(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMaxX", optional=true)
    private static native @MachineSizedFloat double getMaxX(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMinY() { return getMinY(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMinY", optional=true)
    private static native @MachineSizedFloat double getMinY(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMidY() { return getMidY(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMidY", optional=true)
    private static native @MachineSizedFloat double getMidY(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getMaxY() { return getMaxY(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetMaxY", optional=true)
    private static native @MachineSizedFloat double getMaxY(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getWidth() { return getWidth(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetWidth", optional=true)
    private static native @MachineSizedFloat double getWidth(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedFloat double getHeight() { return getHeight(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectGetHeight", optional=true)
    private static native @MachineSizedFloat double getHeight(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean equalsTo(CGRect rect2) { return equalsTo(this, rect2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectEqualToRect", optional=true)
    private static native boolean equalsTo(@ByVal CGRect rect1, @ByVal CGRect rect2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect standardize() { return standardize(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectStandardize", optional=true)
    private static native @ByVal CGRect standardize(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEmpty() { return isEmpty(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIsEmpty", optional=true)
    private static native boolean isEmpty(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isNull() { return isNull(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIsNull", optional=true)
    private static native boolean isNull(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isInfinite() { return isInfinite(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIsInfinite", optional=true)
    private static native boolean isInfinite(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect inset(@MachineSizedFloat double dx, @MachineSizedFloat double dy) { return inset(this, dx, dy); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectInset", optional=true)
    private static native @ByVal CGRect inset(@ByVal CGRect rect, @MachineSizedFloat double dx, @MachineSizedFloat double dy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect integral() { return integral(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIntegral", optional=true)
    private static native @ByVal CGRect integral(@ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect union(CGRect r2) { return union(this, r2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectUnion", optional=true)
    private static native @ByVal CGRect union(@ByVal CGRect r1, @ByVal CGRect r2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect intersection(CGRect r2) { return intersection(this, r2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIntersection", optional=true)
    private static native @ByVal CGRect intersection(@ByVal CGRect r1, @ByVal CGRect r2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect offset(@MachineSizedFloat double dx, @MachineSizedFloat double dy) { return offset(this, dx, dy); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectOffset", optional=true)
    private static native @ByVal CGRect offset(@ByVal CGRect rect, @MachineSizedFloat double dx, @MachineSizedFloat double dy);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void divide(CGRect slice, CGRect remainder, @MachineSizedFloat double amount, CGRectEdge edge) { divide(this, slice, remainder, amount, edge); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectDivide", optional=true)
    private static native void divide(@ByVal CGRect rect, CGRect slice, CGRect remainder, @MachineSizedFloat double amount, CGRectEdge edge);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean contains(CGPoint point) { return contains(this, point); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectContainsPoint", optional=true)
    private static native boolean contains(@ByVal CGRect rect, @ByVal CGPoint point);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean contains(CGRect rect2) { return contains(this, rect2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectContainsRect", optional=true)
    private static native boolean contains(@ByVal CGRect rect1, @ByVal CGRect rect2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean intersects(CGRect rect2) { return intersects(this, rect2); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectIntersectsRect", optional=true)
    private static native boolean intersects(@ByVal CGRect rect1, @ByVal CGRect rect2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDictionary<NSString, NSNumber> toDictionary() { return toDictionary(this); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectCreateDictionaryRepresentation", optional=true)
    private static native NSDictionary<NSString, NSNumber> toDictionary(@ByVal CGRect p0);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectMakeWithDictionaryRepresentation", optional=true)
    private static native boolean fromDictionary(NSDictionary<NSString, NSNumber> dict, CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect apply(CGAffineTransform t) { return apply(this, t); }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGRectApplyAffineTransform", optional=true)
    private static native @ByVal CGRect apply(@ByVal CGRect rect, @ByVal CGAffineTransform t);
    /*</methods>*/
}
