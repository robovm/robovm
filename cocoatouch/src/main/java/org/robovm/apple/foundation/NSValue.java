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
    public NSValue() {}
    protected NSValue(SkipInit skipInit) { super(skipInit); }
    public NSValue(VoidPtr value, BytePtr type) { super((SkipInit) null); initObject(initWithBytes$objCType$(value, type)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "getValue:")
    public native void getValue$(VoidPtr value);
    @Method(selector = "objCType")
    public native BytePtr objCType();
    @Method(selector = "initWithBytes:objCType:")
    protected native @Pointer long initWithBytes$objCType$(VoidPtr value, BytePtr type);
    @Method(selector = "valueWithBytes:objCType:")
    public static native NSValue valueWithBytes$objCType$(VoidPtr value, BytePtr type);
    @Method(selector = "value:withObjCType:")
    public static native NSValue value$withObjCType$(VoidPtr value, BytePtr type);
    @Method(selector = "nonretainedObjectValue")
    public native NSObject nonretainedObjectValue();
    @Method(selector = "pointerValue")
    public native VoidPtr pointerValue();
    @Method(selector = "isEqualToValue:")
    public native boolean isEqualToValue$(NSValue value);
    @Method(selector = "valueWithNonretainedObject:")
    public static native NSValue valueWithNonretainedObject$(NSObject anObject);
    @Method(selector = "valueWithPointer:")
    public static native NSValue valueWithPointer$(VoidPtr pointer);
    @Method(selector = "rangeValue")
    public native @ByVal NSRange rangeValue();
    @Method(selector = "valueWithRange:")
    public static native NSValue valueWithRange$(@ByVal NSRange range);
    /*</methods>*/
}
