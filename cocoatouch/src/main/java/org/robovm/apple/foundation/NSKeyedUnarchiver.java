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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSKeyedUnarchiver/*</name>*/ 
    extends /*<extends>*/NSCoder/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSKeyedUnarchiverPtr extends Ptr<NSKeyedUnarchiver, NSKeyedUnarchiverPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSKeyedUnarchiver.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSKeyedUnarchiver() {}
    protected NSKeyedUnarchiver(SkipInit skipInit) { super(skipInit); }
    public NSKeyedUnarchiver(NSData data) { super((SkipInit) null); initObject(initForReadingWithData$(data)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initForReadingWithData:")
    protected native @Pointer long initForReadingWithData$(NSData data);
    @Method(selector = "setDelegate:")
    public native void setDelegate$(NSKeyedUnarchiverDelegate delegate);
    @Method(selector = "delegate")
    public native NSKeyedUnarchiverDelegate delegate();
    @Method(selector = "finishDecoding")
    public native void finishDecoding();
    @Method(selector = "setClass:forClassName:")
    public native void setClass$forClassName$(ObjCClass cls, String codedName);
    @Method(selector = "classForClassName:")
    public native ObjCClass classForClassName$(String codedName);
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
    @Method(selector = "setRequiresSecureCoding:")
    public native void setRequiresSecureCoding$(boolean b);
    @Method(selector = "unarchiveObjectWithData:")
    public static native NSObject unarchiveObjectWithData$(NSData data);
    @Method(selector = "unarchiveObjectWithFile:")
    public static native NSObject unarchiveObjectWithFile$(String path);
    @Method(selector = "setClass:forClassName:")
    public static native void setDefaultClass(ObjCClass cls, String codedName);
    @Method(selector = "classForClassName:")
    public static native ObjCClass getDefaultClass(String codedName);
    /*</methods>*/
}
