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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

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
    /*</constructors>*/
    /*<properties>*/
    
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
        return org.robovm.apple.avfoundation.NSValueExtensions.getTimeValue(this);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeRange timeRangeValue() {
        return org.robovm.apple.avfoundation.NSValueExtensions.getTimeRangeValue(this);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeMapping timeMappingValue() {
        return org.robovm.apple.avfoundation.NSValueExtensions.getTimeMappingValue(this);
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
        return org.robovm.apple.coreanimation.NSValueExtensions.getTransform3DValue(this);
    }
    public static NSValue valueOf(CATransform3D t) {
        return org.robovm.apple.coreanimation.NSValueExtensions.create(t);
    }
    
    /*<methods>*/
    @Method(selector = "getValue:")
    protected native void getValue(VoidPtr value);
    @Method(selector = "objCType")
    protected native BytePtr objCType();
    @Method(selector = "valueWithBytes:objCType:")
    protected static native NSValue valueWithBytes$objCType$(VoidPtr value, BytePtr type);
    @Method(selector = "nonretainedObjectValue")
    public native NSObject objectValue();
    @Method(selector = "pointerValue")
    public native VoidPtr pointerValue();
    @Method(selector = "isEqualToValue:")
    public native boolean isEqualTo(NSValue value);
    @Method(selector = "valueWithNonretainedObject:")
    public static native NSValue valueOf(NSObject anObject);
    @Method(selector = "valueWithPointer:")
    public static native NSValue valueOf(VoidPtr pointer);
    @Method(selector = "rangeValue")
    public native @ByVal NSRange rangeValue();
    @Method(selector = "valueWithRange:")
    public static native NSValue valueOf(@ByVal NSRange range);
    /*</methods>*/
}
