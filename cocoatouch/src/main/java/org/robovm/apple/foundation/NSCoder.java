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
    public native boolean allowsKeyedCoding();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "requiresSecureCoding")
    public native boolean requiresSecureCoding();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "allowedClasses")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<ObjCClass> getAllowedClasses();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /* UIKit extensions */
    
    public void encodeCGPoint(String key, CGPoint point) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGPoint(this, point, key);
    }
    public void encodeCGSize(String key, CGSize size) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGSize(this, size, key);
    }
    public void encodeCGRect(String key, CGRect rect) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGRect(this, rect, key);
    }
    public void encodeCGAffineTransform(String key, CGAffineTransform transform) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeCGAffineTransform(this, transform, key);
    }
    public void encodeUIEdgeInsets(String key, UIEdgeInsets insets) {
        org.robovm.apple.uikit.NSCoderExtensions.encodeUIEdgeInsets(this, insets, key);
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void encodeUIOffset(String key, UIOffset offset) {
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
    public void encodeCMTime(String key, CMTime time) {
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
    public void encodeCMTimeRange(String key, CMTimeRange timeRange) {
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
    public void encodeCMTimeMapping(String key, CMTimeMapping timeMapping) {
        org.robovm.apple.avfoundation.NSCoderExtensions.encodeCMTimeMapping(this, timeMapping, key);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTimeMapping decodeCMTimeMapping(String key) {
        return org.robovm.apple.avfoundation.NSCoderExtensions.decodeCMTimeMapping(this, key);
    }
    
    public void encodeBytes(byte[] bytes) {
        encodeBytes0(VM.getArrayValuesAddress(bytes), bytes.length);
    }
    public byte[] decodeBytes() {
        MachineSizedUIntPtr lengthPtr = new MachineSizedUIntPtr();
        BytePtr bytePtr = decodeBytes0(lengthPtr);
        return bytePtr.toByteArray((int)lengthPtr.get());
    }
    public void encodeObject(String key, NSObject value) {
        encodeObject0(value, key);
    }
    public void encodeConditionalObject(String key, NSObject value) {
        encodeConditionalObject0(value, key);
    }
    public void encodeBoolean(String key, boolean value) {
        encodeBool0(value, key);
    }
    public void encodeInteger(String key, int value) {
        encodeInt320(value, key);
    }
    public void encodeLong(String key, long value) {
        encodeInt640(value, key);
    }
    public void encodeFloat(String key, float value) {
        encodeFloat0(value, key);
    }
    public void encodeDouble(String key, double value) {
        encodeDouble0(value, key);
    }
    public void encodeBytes(String key, byte[] bytes) {
        encodeBytes0(VM.getArrayValuesAddress(bytes), bytes.length, key);
    }
    public NSObject decodeObject(String key) {
        return decodeObject0(key);
    }
    public boolean decodeBoolean(String key) {
        return decodeBool0(key);
    }
    public int decodeInteger(String key) {
        return decodeInt320(key);
    }
    public long decodeLong(String key) {
        return decodeInt640(key);
    }
    public float decodeFloat(String key) {
        return decodeFloat0(key);
    }
    public double decodeDouble(String key) {
        return decodeDouble0(key);
    }
    public byte[] decodeBytes(String key) {
        MachineSizedUIntPtr lengthPtr = new MachineSizedUIntPtr();
        BytePtr bytesPtr = decodeBytes0(key, lengthPtr);
        return bytesPtr.toByteArray((int)lengthPtr.get());
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSObject decodeObject(String key, Class<? extends NSObject> clazz) {
        return decodeObject0(clazz, key);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSObject decodeObject(String key, List<ObjCClass> clazzes) {
        return decodeObject0(clazzes, key);
    }
    
    public void encodeString(String key, String value) {
        encodeObject(key, value == null ? null : new NSString(value));
    }
    public String decodeString(String key) {
        NSObject value = decodeObject(key);
        if (value instanceof NSString) {
            return ((NSString)value).toString();
        }
        return null;
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
    protected native void encodeBytes0(@Pointer long byteaddr, @MachineSizedUInt long length);
    @Method(selector = "decodeObject")
    public native NSObject decodeObject();
    @Method(selector = "decodeBytesWithReturnedLength:")
    protected native BytePtr decodeBytes0(MachineSizedUIntPtr lengthp);
    @Method(selector = "setObjectZone:")
    public native void setObjectZone(NSZone zone);
    @Method(selector = "objectZone")
    public native NSZone getObjectZone();
    @Method(selector = "encodeObject:forKey:")
    protected native void encodeObject0(NSObject objv, String key);
    @Method(selector = "encodeConditionalObject:forKey:")
    protected native void encodeConditionalObject0(NSObject objv, String key);
    @Method(selector = "encodeBool:forKey:")
    protected native void encodeBool0(boolean boolv, String key);
    @Method(selector = "encodeInt:forKey:")
    protected native void encodeInt0(int intv, String key);
    @Method(selector = "encodeInt32:forKey:")
    protected native void encodeInt320(int intv, String key);
    @Method(selector = "encodeInt64:forKey:")
    protected native void encodeInt640(long intv, String key);
    @Method(selector = "encodeFloat:forKey:")
    protected native void encodeFloat0(float realv, String key);
    @Method(selector = "encodeDouble:forKey:")
    protected native void encodeDouble0(double realv, String key);
    @Method(selector = "encodeBytes:length:forKey:")
    protected native void encodeBytes0(@Pointer long bytesp, @MachineSizedUInt long lenv, String key);
    @Method(selector = "containsValueForKey:")
    public native boolean containsValue(String key);
    @Method(selector = "decodeObjectForKey:")
    protected native NSObject decodeObject0(String key);
    @Method(selector = "decodeBoolForKey:")
    protected native boolean decodeBool0(String key);
    @Method(selector = "decodeIntForKey:")
    protected native int decodeInt0(String key);
    @Method(selector = "decodeInt32ForKey:")
    protected native int decodeInt320(String key);
    @Method(selector = "decodeInt64ForKey:")
    protected native long decodeInt640(String key);
    @Method(selector = "decodeFloatForKey:")
    protected native float decodeFloat0(String key);
    @Method(selector = "decodeDoubleForKey:")
    protected native double decodeDouble0(String key);
    @Method(selector = "decodeBytesForKey:returnedLength:")
    protected native BytePtr decodeBytes0(String key, MachineSizedUIntPtr lengthp);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "encodeInteger:forKey:")
    protected native void encodeInteger0(@MachineSizedSInt long intv, String key);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "decodeIntegerForKey:")
    protected native @MachineSizedSInt long decodeInteger0(String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClass:forKey:")
    protected native NSObject decodeObject0(Class<? extends NSObject> aClass, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClasses:forKey:")
    protected native NSObject decodeObject0(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<ObjCClass> classes, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodePropertyListForKey:")
    protected native NSObject decodePropertyList0(String key);
    /*</methods>*/
}
