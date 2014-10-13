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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableCharacterSet/*</name>*/ 
    extends /*<extends>*/NSCharacterSet/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableCharacterSetPtr extends Ptr<NSMutableCharacterSet, NSMutableCharacterSetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableCharacterSet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableCharacterSet() {}
    protected NSMutableCharacterSet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addCharactersInRange:")
    public native void addCharacters(@ByVal NSRange aRange);
    @Method(selector = "removeCharactersInRange:")
    public native void removeCharacters(@ByVal NSRange aRange);
    @Method(selector = "addCharactersInString:")
    public native void addCharacters(String aString);
    @Method(selector = "removeCharactersInString:")
    public native void removeCharacters(String aString);
    @Method(selector = "formUnionWithCharacterSet:")
    public native void formUnion(NSCharacterSet otherSet);
    @Method(selector = "formIntersectionWithCharacterSet:")
    public native void formIntersection(NSCharacterSet otherSet);
    @Method(selector = "invert")
    public native void invert();
    @Method(selector = "controlCharacterSet")
    public static native NSMutableCharacterSet controlCharacterSet();
    @Method(selector = "whitespaceCharacterSet")
    public static native NSMutableCharacterSet whitespaceCharacterSet();
    @Method(selector = "whitespaceAndNewlineCharacterSet")
    public static native NSMutableCharacterSet whitespaceAndNewlineCharacterSet();
    @Method(selector = "decimalDigitCharacterSet")
    public static native NSMutableCharacterSet decimalDigitCharacterSet();
    @Method(selector = "letterCharacterSet")
    public static native NSMutableCharacterSet letterCharacterSet();
    @Method(selector = "lowercaseLetterCharacterSet")
    public static native NSMutableCharacterSet lowercaseLetterCharacterSet();
    @Method(selector = "uppercaseLetterCharacterSet")
    public static native NSMutableCharacterSet uppercaseLetterCharacterSet();
    @Method(selector = "nonBaseCharacterSet")
    public static native NSMutableCharacterSet nonBaseCharacterSet();
    @Method(selector = "alphanumericCharacterSet")
    public static native NSMutableCharacterSet alphanumericCharacterSet();
    @Method(selector = "decomposableCharacterSet")
    public static native NSMutableCharacterSet decomposableCharacterSet();
    @Method(selector = "illegalCharacterSet")
    public static native NSMutableCharacterSet illegalCharacterSet();
    @Method(selector = "punctuationCharacterSet")
    public static native NSMutableCharacterSet punctuationCharacterSet();
    @Method(selector = "capitalizedLetterCharacterSet")
    public static native NSMutableCharacterSet capitalizedLetterCharacterSet();
    @Method(selector = "symbolCharacterSet")
    public static native NSMutableCharacterSet symbolCharacterSet();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "newlineCharacterSet")
    public static native NSMutableCharacterSet newlineCharacterSet();
    @Method(selector = "characterSetWithRange:")
    public static native NSMutableCharacterSet characterSetWithRange$(@ByVal NSRange aRange);
    @Method(selector = "characterSetWithCharactersInString:")
    public static native NSMutableCharacterSet characterSetWithCharactersInString$(String aString);
    @Method(selector = "characterSetWithBitmapRepresentation:")
    public static native NSMutableCharacterSet characterSetWithBitmapRepresentation$(NSData data);
    @Method(selector = "characterSetWithContentsOfFile:")
    public static native NSMutableCharacterSet characterSetWithContentsOfFile$(String fName);
    /*</methods>*/
}
