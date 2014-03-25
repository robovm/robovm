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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
        this.origin(origin);
        this.size(size);
    }
    /*</constructors>*/
    
    public CGRect(double x, double y, double width, double height) {
        origin().x(x).y(y);
        size().width(width).height(height);
    }
    
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CGPoint origin();
    @StructMember(0) public native CGRect origin(@ByVal CGPoint origin);
    @StructMember(1) public native @ByVal CGSize size();
    @StructMember(1) public native CGRect size(@ByVal CGSize size);
    /*</members>*/
    
    public CGRect applyAffineTransform(CGAffineTransform t) {
        return applyAffineTransform(this, t);
    }

    public double getMinX() {
        return getMinX(this);
    }

    public double getMidX() {
        return getMidX(this);
    }

    public double getMaxX() {
        return getMaxX(this);
    }

    public double getMinY() {
        return getMinY(this);
    }

    public double getMidY() {
        return getMidY(this);
    }

    public double getMaxY() {
        return getMaxY(this);
    }

    public double getWidth() {
        return getWidth(this);
    }

    public double getHeight() {
        return getHeight(this);
    }

    public CGRect standardize() {
        return standardize(this);
    }

    public boolean isEmpty() {
        return isEmpty(this);
    }

    public boolean isNull() {
        return isNull(this);
    }

    public boolean isInfinite() {
        return isInfinite(this);
    }

    public CGRect inset(double dx, double dy) {
        return inset(this, dx, dy);
    }

    public CGRect integral() {
        return integral(this);
    }

    public CGRect offset(double dx, double dy) {
        return offset(this, dx, dy);
    }

    public boolean containsPoint(CGPoint point) {
        return containsPoint(this, point);
    }

    public boolean containsRect(CGRect rect2) {
        return containsRect(this, rect2);
    }

    public boolean intersectsRect(CGRect rect2) {
        return intersectsRect(this, rect2);
    }

    public CGRect union(CGRect r2) {
        return union(this, r2);
    }
    
    public CGRect intersection(CGRect r2) {
        return intersection(this, r2);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof CGRect && equalToRect(this, (CGRect) obj);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="CGRectZero")
    public static native @ByVal CGRect Zero();
    @GlobalValue(symbol="CGRectNull")
    public static native @ByVal CGRect Null();
    @GlobalValue(symbol="CGRectInfinite")
    public static native @ByVal CGRect Infinite();
    
    @Bridge(symbol="CGRectGetMinX")
    protected static native @MachineSizedFloat double getMinX(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetMidX")
    protected static native @MachineSizedFloat double getMidX(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetMaxX")
    protected static native @MachineSizedFloat double getMaxX(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetMinY")
    protected static native @MachineSizedFloat double getMinY(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetMidY")
    protected static native @MachineSizedFloat double getMidY(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetMaxY")
    protected static native @MachineSizedFloat double getMaxY(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetWidth")
    protected static native @MachineSizedFloat double getWidth(@ByVal CGRect rect);
    @Bridge(symbol="CGRectGetHeight")
    protected static native @MachineSizedFloat double getHeight(@ByVal CGRect rect);
    @Bridge(symbol="CGRectEqualToRect")
    protected static native boolean equalToRect(@ByVal CGRect rect1, @ByVal CGRect rect2);
    @Bridge(symbol="CGRectStandardize")
    protected static native @ByVal CGRect standardize(@ByVal CGRect rect);
    @Bridge(symbol="CGRectIsEmpty")
    protected static native boolean isEmpty(@ByVal CGRect rect);
    @Bridge(symbol="CGRectIsNull")
    protected static native boolean isNull(@ByVal CGRect rect);
    @Bridge(symbol="CGRectIsInfinite")
    protected static native boolean isInfinite(@ByVal CGRect rect);
    @Bridge(symbol="CGRectInset")
    protected static native @ByVal CGRect inset(@ByVal CGRect rect, @MachineSizedFloat double dx, @MachineSizedFloat double dy);
    @Bridge(symbol="CGRectIntegral")
    protected static native @ByVal CGRect integral(@ByVal CGRect rect);
    @Bridge(symbol="CGRectUnion")
    protected static native @ByVal CGRect union(@ByVal CGRect r1, @ByVal CGRect r2);
    @Bridge(symbol="CGRectIntersection")
    protected static native @ByVal CGRect intersection(@ByVal CGRect r1, @ByVal CGRect r2);
    @Bridge(symbol="CGRectOffset")
    protected static native @ByVal CGRect offset(@ByVal CGRect rect, @MachineSizedFloat double dx, @MachineSizedFloat double dy);
    @Bridge(symbol="CGRectDivide")
    public static native void divide(@ByVal CGRect rect, CGRect slice, CGRect remainder, @MachineSizedFloat double amount, CGRectEdge edge);
    @Bridge(symbol="CGRectContainsPoint")
    protected static native boolean containsPoint(@ByVal CGRect rect, @ByVal CGPoint point);
    @Bridge(symbol="CGRectContainsRect")
    protected static native boolean containsRect(@ByVal CGRect rect1, @ByVal CGRect rect2);
    @Bridge(symbol="CGRectIntersectsRect")
    protected static native boolean intersectsRect(@ByVal CGRect rect1, @ByVal CGRect rect2);
    @Bridge(symbol="CGRectApplyAffineTransform")
    protected static native @ByVal CGRect applyAffineTransform(@ByVal CGRect rect, @ByVal CGAffineTransform t);
    /*</methods>*/
}
