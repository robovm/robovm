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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCoder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSCoderPtr extends Ptr<NSCoder, NSCoderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCoder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCoder() {}
    protected NSCoder(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "systemVersion")
    public native int getSystemVersion();
    @Property(selector = "allowsKeyedCoding")
    public native boolean isAllowsKeyedCoding();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "requiresSecureCoding")
    public native boolean isRequiresSecureCoding();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "allowedClasses")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<ObjCClass> getAllowedClasses();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /* UIKit extensions */
    
    public void encodeCGPoint(CGPoint point, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGPoint(this, point, key);
    }
    public void encodeCGSize(CGSize size, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGSize(this, size, key);
    }
    public void encodeCGRect(CGRect rect, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGRect(this, rect, key);
    }
    public void encodeCGAffineTransform(CGAffineTransform transform, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGAffineTransform(this, transform, key);
    }
    public void encodeUIEdgeInsets(UIEdgeInsets insets, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeUIEdgeInsets(this, insets, key);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void encodeUIOffset(UIOffset offset, String key) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeUIOffset(this, offset, key);
    }
    public CGPoint decodeCGPoint(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeCGPoint(this, key);
    }
    public CGSize decodeCGSize(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeCGSize(this, key);
    }
    public CGRect decodeCGRect(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeCGRect(this, key);
    }
    public CGAffineTransform decodeCGAffineTransform(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeCGAffineTransform(this, key);
    }
    public UIEdgeInsets decodeUIEdgeInsets(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeUIEdgeInsets(this, key);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset decodeUIOffset(String key) {
        return org.robovm.apple.uikit.NSCoderExtensions.decodeUIOffset(this, key);
    }
    
    /* AVFoundation extensions */
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void encodeCMTime(CMTime time, String key) {
        org.robovm.apple.avfoundation.NSCoderExtensions.encodeCMTime(this, time, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTime decodeCMTime(String key) {
        return org.robovm.apple.avfoundation.NSCoderExtensions.decodeCMTime(this, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void encodeCMTimeRange(CMTimeRange timeRange, String key) {
        org.robovm.apple.avfoundation.NSCoderExtensions.encodeCMTimeRange(this, timeRange, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeRange decodeCMTimeRange(String key) {
        return org.robovm.apple.avfoundation.NSCoderExtensions.decodeCMTimeRange(this, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void encodeCMTimeMapping(CMTimeMapping timeMapping, String key) {
        org.robovm.apple.avfoundation.NSCoderExtensions.encodeCMTimeMapping(this, timeMapping, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeMapping decodeCMTimeMapping(String key) {
        return org.robovm.apple.avfoundation.NSCoderExtensions.decodeCMTimeMapping(this, key);
    }
    
    /*<methods>*/
    @Method(selector = "encodeDataObject:")
    public native void encodeDataObject(NSData data);
    @Method(selector = "decodeDataObject")
    public native NSData decodeDataObject();
    @Method(selector = "versionForClassName:")
    public native @MachineSizedSInt long getVersionForClassName(String className);
    @Method(selector = "encodeObject:")
    public native void encodeObject(NSObject object);
    @Method(selector = "encodeRootObject:")
    public native void encodeRootObject(NSObject rootObject);
    @Method(selector = "encodeBycopyObject:")
    public native void encodeBycopyObject(NSObject anObject);
    @Method(selector = "encodeByrefObject:")
    public native void encodeByrefObject(NSObject anObject);
    @Method(selector = "encodeConditionalObject:")
    public native void encodeConditionalObject(NSObject object);
    @Method(selector = "encodeBytes:length:")
    public native void encodeBytes(VoidPtr byteaddr, @MachineSizedUInt long length);
    @Method(selector = "decodeObject")
    public native NSObject decodeObject();
    @Method(selector = "decodeBytesWithReturnedLength:")
    public native VoidPtr decodeBytes(MachineSizedUIntPtr lengthp);
    @Method(selector = "setObjectZone:")
    public native void setObjectZone(NSZone zone);
    @Method(selector = "objectZone")
    public native NSZone getObjectZone();
    @Method(selector = "encodeObject:forKey:")
    public native void encodeObject(NSObject objv, String key);
    @Method(selector = "encodeConditionalObject:forKey:")
    public native void encodeConditionalObject(NSObject objv, String key);
    @Method(selector = "encodeBool:forKey:")
    public native void encodeBool(boolean boolv, String key);
    @Method(selector = "encodeInt:forKey:")
    public native void encodeInt(int intv, String key);
    @Method(selector = "encodeInt32:forKey:")
    public native void encodeInt32(int intv, String key);
    @Method(selector = "encodeInt64:forKey:")
    public native void encodeInt64(long intv, String key);
    @Method(selector = "encodeFloat:forKey:")
    public native void encodeFloat(float realv, String key);
    @Method(selector = "encodeDouble:forKey:")
    public native void encodeDouble(double realv, String key);
    @Method(selector = "encodeBytes:length:forKey:")
    public native void encodeBytes(BytePtr bytesp, @MachineSizedUInt long lenv, String key);
    @Method(selector = "containsValueForKey:")
    public native boolean containsValue(String key);
    @Method(selector = "decodeObjectForKey:")
    public native NSObject decodeObject(String key);
    @Method(selector = "decodeBoolForKey:")
    public native boolean decodeBool(String key);
    @Method(selector = "decodeIntForKey:")
    public native int decodeInt(String key);
    @Method(selector = "decodeInt32ForKey:")
    public native int decodeInt32(String key);
    @Method(selector = "decodeInt64ForKey:")
    public native long decodeInt64(String key);
    @Method(selector = "decodeFloatForKey:")
    public native float decodeFloat(String key);
    @Method(selector = "decodeDoubleForKey:")
    public native double decodeDouble(String key);
    @Method(selector = "decodeBytesForKey:returnedLength:")
    public native BytePtr decodeBytes(String key, MachineSizedUIntPtr lengthp);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "encodeInteger:forKey:")
    public native void encodeInteger(@MachineSizedSInt long intv, String key);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "decodeIntegerForKey:")
    public native @MachineSizedSInt long decodeInteger(String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClass:forKey:")
    public native NSObject decodeObject(ObjCClass aClass, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClasses:forKey:")
    public native NSObject decodeObject(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<ObjCClass> classes, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodePropertyListForKey:")
    public native NSObject decodePropertyList(String key);
    /*</methods>*/
}
