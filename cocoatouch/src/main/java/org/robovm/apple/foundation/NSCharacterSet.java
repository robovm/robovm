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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCharacterSet/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSCharacterSetPtr extends Ptr<NSCharacterSet, NSCharacterSetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCharacterSet() {}
    protected NSCharacterSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "characterIsMember:")
    public native boolean characterIsMember$(short aCharacter);
    @Method(selector = "bitmapRepresentation")
    public native NSData bitmapRepresentation();
    @Method(selector = "invertedSet")
    public native NSCharacterSet invertedSet();
    @Method(selector = "longCharacterIsMember:")
    public native boolean longCharacterIsMember$(int theLongChar);
    @Method(selector = "isSupersetOfSet:")
    public native boolean isSupersetOfSet$(NSCharacterSet theOtherSet);
    @Method(selector = "hasMemberInPlane:")
    public native boolean hasMemberInPlane$(byte thePlane);
    @Method(selector = "controlCharacterSet")
    public static native NSObject controlCharacterSet();
    @Method(selector = "whitespaceCharacterSet")
    public static native NSObject whitespaceCharacterSet();
    @Method(selector = "whitespaceAndNewlineCharacterSet")
    public static native NSObject whitespaceAndNewlineCharacterSet();
    @Method(selector = "decimalDigitCharacterSet")
    public static native NSObject decimalDigitCharacterSet();
    @Method(selector = "letterCharacterSet")
    public static native NSObject letterCharacterSet();
    @Method(selector = "lowercaseLetterCharacterSet")
    public static native NSObject lowercaseLetterCharacterSet();
    @Method(selector = "uppercaseLetterCharacterSet")
    public static native NSObject uppercaseLetterCharacterSet();
    @Method(selector = "nonBaseCharacterSet")
    public static native NSObject nonBaseCharacterSet();
    @Method(selector = "alphanumericCharacterSet")
    public static native NSObject alphanumericCharacterSet();
    @Method(selector = "decomposableCharacterSet")
    public static native NSObject decomposableCharacterSet();
    @Method(selector = "illegalCharacterSet")
    public static native NSObject illegalCharacterSet();
    @Method(selector = "punctuationCharacterSet")
    public static native NSObject punctuationCharacterSet();
    @Method(selector = "capitalizedLetterCharacterSet")
    public static native NSObject capitalizedLetterCharacterSet();
    @Method(selector = "symbolCharacterSet")
    public static native NSObject symbolCharacterSet();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "newlineCharacterSet")
    public static native NSObject newlineCharacterSet();
    @Method(selector = "characterSetWithRange:")
    public static native NSObject characterSetWithRange$(@ByVal NSRange aRange);
    @Method(selector = "characterSetWithCharactersInString:")
    public static native NSObject characterSetWithCharactersInString$(String aString);
    @Method(selector = "characterSetWithBitmapRepresentation:")
    public static native NSObject characterSetWithBitmapRepresentation$(NSData data);
    @Method(selector = "characterSetWithContentsOfFile:")
    public static native NSObject characterSetWithContentsOfFile$(String fName);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLUserAllowedCharacterSet")
    public static native NSObject URLUserAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLPasswordAllowedCharacterSet")
    public static native NSObject URLPasswordAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLHostAllowedCharacterSet")
    public static native NSObject URLHostAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLPathAllowedCharacterSet")
    public static native NSObject URLPathAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLQueryAllowedCharacterSet")
    public static native NSObject URLQueryAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLFragmentAllowedCharacterSet")
    public static native NSObject URLFragmentAllowedCharacterSet();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
