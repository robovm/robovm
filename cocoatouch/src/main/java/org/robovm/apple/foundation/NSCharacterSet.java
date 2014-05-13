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
    
    public static NSCharacterSet createFromFile(File file) {
        if (file == null) {
            throw new NullPointerException("file");
        }
        return characterSetWithContentsOfFile$(file.getAbsolutePath());
    }
    
    /*<methods>*/
    @Method(selector = "characterIsMember:")
    public native boolean isMember(short aCharacter);
    @Method(selector = "bitmapRepresentation")
    public native NSData getBitmapRepresentation();
    @Method(selector = "invertedSet")
    public native NSCharacterSet getInvertedSet();
    @Method(selector = "longCharacterIsMember:")
    public native boolean isMember(int theLongChar);
    @Method(selector = "isSupersetOfSet:")
    public native boolean isSuperset(NSCharacterSet theOtherSet);
    @Method(selector = "hasMemberInPlane:")
    public native boolean hasMemberInPlane(byte thePlane);
    @Method(selector = "controlCharacterSet")
    public static native NSCharacterSet getControlCharacterSet();
    @Method(selector = "whitespaceCharacterSet")
    public static native NSCharacterSet getWhitespaceCharacterSet();
    @Method(selector = "whitespaceAndNewlineCharacterSet")
    public static native NSCharacterSet getWhitespaceAndNewlineCharacterSet();
    @Method(selector = "decimalDigitCharacterSet")
    public static native NSCharacterSet getDecimalDigitCharacterSet();
    @Method(selector = "letterCharacterSet")
    public static native NSCharacterSet getLetterCharacterSet();
    @Method(selector = "lowercaseLetterCharacterSet")
    public static native NSCharacterSet getLowercaseLetterCharacterSet();
    @Method(selector = "uppercaseLetterCharacterSet")
    public static native NSCharacterSet getUppercaseLetterCharacterSet();
    @Method(selector = "nonBaseCharacterSet")
    public static native NSCharacterSet getNonBaseCharacterSet();
    @Method(selector = "alphanumericCharacterSet")
    public static native NSCharacterSet getAlphanumericCharacterSet();
    @Method(selector = "decomposableCharacterSet")
    public static native NSCharacterSet getDecomposableCharacterSet();
    @Method(selector = "illegalCharacterSet")
    public static native NSCharacterSet getIllegalCharacterSet();
    @Method(selector = "punctuationCharacterSet")
    public static native NSCharacterSet getPunctuationCharacterSet();
    @Method(selector = "capitalizedLetterCharacterSet")
    public static native NSCharacterSet getCapitalizedLetterCharacterSet();
    @Method(selector = "symbolCharacterSet")
    public static native NSCharacterSet getSymbolCharacterSet();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "newlineCharacterSet")
    public static native NSCharacterSet getNewlineCharacterSet();
    @Method(selector = "characterSetWithRange:")
    public static native NSCharacterSet createFromRange(@ByVal NSRange aRange);
    @Method(selector = "characterSetWithCharactersInString:")
    public static native NSCharacterSet createFromCharacters(String aString);
    @Method(selector = "characterSetWithBitmapRepresentation:")
    public static native NSCharacterSet createFromBitmapRepresentation(NSData data);
    @Method(selector = "characterSetWithContentsOfFile:")
    private static native NSCharacterSet characterSetWithContentsOfFile$(String fName);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLUserAllowedCharacterSet")
    public static native NSCharacterSet getURLUserAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLPasswordAllowedCharacterSet")
    public static native NSCharacterSet getURLPasswordAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLHostAllowedCharacterSet")
    public static native NSCharacterSet getURLHostAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLPathAllowedCharacterSet")
    public static native NSCharacterSet getURLPathAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLQueryAllowedCharacterSet")
    public static native NSCharacterSet getURLQueryAllowedCharacterSet();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "URLFragmentAllowedCharacterSet")
    public static native NSCharacterSet getURLFragmentAllowedCharacterSet();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
