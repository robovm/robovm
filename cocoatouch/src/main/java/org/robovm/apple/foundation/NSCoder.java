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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    @Method(selector = "encodeValueOfObjCType:at:")
    public native void encodeValueOfObjCType$at$(BytePtr type, VoidPtr addr);
    @Method(selector = "encodeDataObject:")
    public native void encodeDataObject$(NSData data);
    @Method(selector = "decodeValueOfObjCType:at:")
    public native void decodeValueOfObjCType$at$(BytePtr type, VoidPtr data);
    @Method(selector = "decodeDataObject")
    public native NSData decodeDataObject();
    @Method(selector = "versionForClassName:")
    public native @MachineSizedSInt long versionForClassName$(String className);
    @Method(selector = "encodeObject:")
    public native void encodeObject$(NSObject object);
    @Method(selector = "encodeRootObject:")
    public native void encodeRootObject$(NSObject rootObject);
    @Method(selector = "encodeBycopyObject:")
    public native void encodeBycopyObject$(NSObject anObject);
    @Method(selector = "encodeByrefObject:")
    public native void encodeByrefObject$(NSObject anObject);
    @Method(selector = "encodeConditionalObject:")
    public native void encodeConditionalObject$(NSObject object);
    @Method(selector = "encodeArrayOfObjCType:count:at:")
    public native void encodeArrayOfObjCType$count$at$(BytePtr type, @MachineSizedUInt long count, VoidPtr array);
    @Method(selector = "encodeBytes:length:")
    public native void encodeBytes$length$(VoidPtr byteaddr, @MachineSizedUInt long length);
    @Method(selector = "decodeObject")
    public native NSObject decodeObject();
    @Method(selector = "decodeArrayOfObjCType:count:at:")
    public native void decodeArrayOfObjCType$count$at$(BytePtr itemType, @MachineSizedUInt long count, VoidPtr array);
    @Method(selector = "decodeBytesWithReturnedLength:")
    public native VoidPtr decodeBytesWithReturnedLength$(MachineSizedUIntPtr lengthp);
    @Method(selector = "setObjectZone:")
    public native void setObjectZone$(NSZone zone);
    @Method(selector = "objectZone")
    public native NSZone objectZone();
    @Method(selector = "systemVersion")
    public native int systemVersion();
    @Method(selector = "allowsKeyedCoding")
    public native boolean allowsKeyedCoding();
    @Method(selector = "encodeObject:forKey:")
    public native void encodeObject$forKey$(NSObject objv, String key);
    @Method(selector = "encodeConditionalObject:forKey:")
    public native void encodeConditionalObject$forKey$(NSObject objv, String key);
    @Method(selector = "encodeBool:forKey:")
    public native void encodeBool$forKey$(boolean boolv, String key);
    @Method(selector = "encodeInt:forKey:")
    public native void encodeInt$forKey$(int intv, String key);
    @Method(selector = "encodeInt32:forKey:")
    public native void encodeInt32$forKey$(int intv, String key);
    @Method(selector = "encodeInt64:forKey:")
    public native void encodeInt64$forKey$(long intv, String key);
    @Method(selector = "encodeFloat:forKey:")
    public native void encodeFloat$forKey$(float realv, String key);
    @Method(selector = "encodeDouble:forKey:")
    public native void encodeDouble$forKey$(double realv, String key);
    @Method(selector = "encodeBytes:length:forKey:")
    public native void encodeBytes$length$forKey$(BytePtr bytesp, @MachineSizedUInt long lenv, String key);
    @Method(selector = "containsValueForKey:")
    public native boolean containsValueForKey$(String key);
    @Method(selector = "decodeObjectForKey:")
    public native NSObject decodeObjectForKey$(String key);
    @Method(selector = "decodeBoolForKey:")
    public native boolean decodeBoolForKey$(String key);
    @Method(selector = "decodeIntForKey:")
    public native int decodeIntForKey$(String key);
    @Method(selector = "decodeInt32ForKey:")
    public native int decodeInt32ForKey$(String key);
    @Method(selector = "decodeInt64ForKey:")
    public native long decodeInt64ForKey$(String key);
    @Method(selector = "decodeFloatForKey:")
    public native float decodeFloatForKey$(String key);
    @Method(selector = "decodeDoubleForKey:")
    public native double decodeDoubleForKey$(String key);
    @Method(selector = "decodeBytesForKey:returnedLength:")
    public native BytePtr decodeBytesForKey$returnedLength$(String key, MachineSizedUIntPtr lengthp);
    @Method(selector = "encodeInteger:forKey:")
    public native void encodeInteger$forKey$(@MachineSizedSInt long intv, String key);
    @Method(selector = "decodeIntegerForKey:")
    public native @MachineSizedSInt long decodeIntegerForKey$(String key);
    @Method(selector = "requiresSecureCoding")
    public native boolean requiresSecureCoding();
    @Method(selector = "decodeObjectOfClass:forKey:")
    public native NSObject decodeObjectOfClass$forKey$(ObjCClass aClass, String key);
    @Method(selector = "decodeObjectOfClasses:forKey:")
    public native NSObject decodeObjectOfClasses$forKey$(NSSet<?> classes, String key);
    @Method(selector = "decodePropertyListForKey:")
    public native NSObject decodePropertyListForKey$(String key);
    @Method(selector = "allowedClasses")
    public native NSSet<?> allowedClasses();
    /*</methods>*/
}
