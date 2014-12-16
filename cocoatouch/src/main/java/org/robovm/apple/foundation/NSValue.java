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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/
import org.robovm.apple.corelocation.CLLocationCoordinate2D;
import org.robovm.apple.mapkit.MKCoordinateSpan;
import org.robovm.apple.scenekit.SCNMatrix4;
import org.robovm.apple.scenekit.SCNVector3;
import org.robovm.apple.scenekit.SCNVector4;

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSValue/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSValuePtr extends Ptr<NSValue, NSValuePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSValue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSValue(SkipInit skipInit) { super(skipInit); }
    public NSValue(NSCoder aDecoder) { super((SkipInit) null); initObject(initWithCoder$(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "objCType")
    protected native BytePtr objCType();
    @Property(selector = "nonretainedObjectValue")
    public native NSObject objectValue();
    @Property(selector = "rangeValue")
    public native @ByVal NSRange rangeValue();
    /*</properties>*/
    /*<members>*//*</members>*/

    public String getObjCType() {
        BytePtr p = objCType();
        return p == null ? null : p.toStringAsciiZ();
    }

    public static NSValue valueOf(VoidPtr value, String type) {
        BytePtr p = type == null ? null : BytePtr.toBytePtrAsciiZ(type);
        return valueWithBytes$objCType$(value, p);
    }

    public VoidPtr getValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(ptr);
        return ptr;
    }
    
    /* UIKit Extensions */
    public CGPoint pointValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getPointValue(this);
    }
    public CGSize sizeValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getSizeValue(this);
    }
    public CGRect rectValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getRectValue(this);
    }
    public CGAffineTransform affineTransformValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getAffineTransformValue(this);
    }
    public UIEdgeInsets edgeInsetsValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getEdgeInsetsValue(this);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset offsetValue() {
        return org.robovm.apple.uikit.NSValueExtensions.getOffsetValue(this);
    }

    public static NSValue valueOf(CGPoint point) {
        return org.robovm.apple.uikit.NSValueExtensions.create(point);
    }
    public static NSValue valueOf(CGSize size) {
        return org.robovm.apple.uikit.NSValueExtensions.create(size);
    }
    public static NSValue valueOf(CGRect rect) {
        return org.robovm.apple.uikit.NSValueExtensions.create(rect);
    }
    public static NSValue valueOf(CGAffineTransform transform) {
        return org.robovm.apple.uikit.NSValueExtensions.create(transform);
    }
    public static NSValue valueOf(UIEdgeInsets insets) {
        return org.robovm.apple.uikit.NSValueExtensions.create(insets);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static NSValue valueOf(UIOffset insets) {
        return org.robovm.apple.uikit.NSValueExtensions.create(insets);
    }
    
    /* AVFoundation extensions */
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime timeValue() {
        return org.robovm.apple.avfoundation.NSValueExtensions.getCMTimeValue(this);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeRange timeRangeValue() {
        return org.robovm.apple.avfoundation.NSValueExtensions.getCMTimeRangeValue(this);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeMapping timeMappingValue() {
        return org.robovm.apple.avfoundation.NSValueExtensions.getCMTimeMappingValue(this);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSValue valueOf(CMTime time) {
        return org.robovm.apple.avfoundation.NSValueExtensions.create(time);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSValue valueOf(CMTimeRange timeRange) {
        return org.robovm.apple.avfoundation.NSValueExtensions.create(timeRange);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSValue valueOf(CMTimeMapping timeMapping) {
        return org.robovm.apple.avfoundation.NSValueExtensions.create(timeMapping);
    }
    
    /* CoreAnimation extensions */
    public CATransform3D transform3DValue() {
        return org.robovm.apple.coreanimation.NSValueExtensions.getCATransform3DValue(this);
    }
    public static NSValue valueOf(CATransform3D t) {
        return org.robovm.apple.coreanimation.NSValueExtensions.create(t);
    }
    
    /* MapKit extensions */
    public CLLocationCoordinate2D coordinateValue() {
        return org.robovm.apple.mapkit.NSValueExtensions.getMKCoordinateValue(this);
    }
    public MKCoordinateSpan getCoordinateSpanValue() {
        return org.robovm.apple.mapkit.NSValueExtensions.getMKCoordinateSpanValue(this);
    }
    public static NSValue valueOf(CLLocationCoordinate2D coordinate) {
        return org.robovm.apple.mapkit.NSValueExtensions.create(coordinate);
    }
    public static NSValue valueOf(MKCoordinateSpan span) {
        return org.robovm.apple.mapkit.NSValueExtensions.create(span);
    }
    
    /* SceneKit extensions */
    public SCNVector3 SCNVector3Value() {
        return org.robovm.apple.scenekit.NSValueExtensions.getSCNVector3Value(this);
    }
    public SCNVector4 SCNVector4Value() {
        return org.robovm.apple.scenekit.NSValueExtensions.getSCNVector4Value(this);
    }
    public SCNMatrix4 SCNMatrix4Value() {
        return org.robovm.apple.scenekit.NSValueExtensions.getSCNMatrix4Value(this);
    }
    public static NSValue valueOf(SCNVector3 vector3) {
        return org.robovm.apple.scenekit.NSValueExtensions.create(vector3);
    }
    public static NSValue valueOf(SCNVector4 vector4) {
        return org.robovm.apple.scenekit.NSValueExtensions.create(vector4);
    }
    public static NSValue valueOf(SCNMatrix4 matrix4) {
        return org.robovm.apple.scenekit.NSValueExtensions.create(matrix4);
    }
    /*<methods>*/
    @Method(selector = "getValue:")
    protected native void getValue(VoidPtr value);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long initWithCoder$(NSCoder aDecoder);
    @Method(selector = "valueWithBytes:objCType:")
    protected static native NSValue valueWithBytes$objCType$(VoidPtr value, BytePtr type);
    @Method(selector = "pointerValue")
    public native VoidPtr pointerValue();
    @Method(selector = "isEqualToValue:")
    public native boolean isEqualTo(NSValue value);
    @Method(selector = "valueWithNonretainedObject:")
    public static native NSValue valueOf(NSObject anObject);
    @Method(selector = "valueWithPointer:")
    public static native NSValue valueOf(VoidPtr pointer);
    @Method(selector = "valueWithRange:")
    public static native NSValue valueOf(@ByVal NSRange range);
    /*</methods>*/
}
