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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
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
    
    /*</properties>*/
    /*<members>*//*</members>*/
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
    @Method(selector = "systemVersion")
    public native int getSystemVersion();
    @Method(selector = "allowsKeyedCoding")
    public native boolean allowsKeyedCoding();
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
    @Method(selector = "requiresSecureCoding")
    public native boolean requiresSecureCoding();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClass:forKey:")
    public native NSObject decodeObject(ObjCClass aClass, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeObjectOfClasses:forKey:")
    public native NSObject decodeObject(NSSet<?> classes, String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodePropertyListForKey:")
    public native NSObject decodePropertyList(String key);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "allowedClasses")
    public native NSSet<?> getAllowedClasses();
    /*</methods>*/
}
