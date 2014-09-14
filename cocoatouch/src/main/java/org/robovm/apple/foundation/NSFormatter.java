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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFormatter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSFormatterPtr extends Ptr<NSFormatter, NSFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFormatter() {}
    protected NSFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringForObjectValue:")
    public native String stringForObjectValue$(NSObject obj);
    @Method(selector = "attributedStringForObjectValue:withDefaultAttributes:")
    public native NSAttributedString attributedStringForObjectValue$withDefaultAttributes$(NSObject obj, NSDictionary<?, ?> attrs);
    @Method(selector = "editingStringForObjectValue:")
    public native String editingStringForObjectValue$(NSObject obj);
    @Method(selector = "getObjectValue:forString:errorDescription:")
    public native boolean getObjectValue$forString$errorDescription$(NSObject obj, String string, NSString.NSStringPtr error);
    @Method(selector = "isPartialStringValid:newEditingString:errorDescription:")
    public native boolean isPartialStringValid$newEditingString$errorDescription$(String partialString, NSString.NSStringPtr newString, NSString.NSStringPtr error);
    @Method(selector = "isPartialStringValid:proposedSelectedRange:originalString:originalSelectedRange:errorDescription:")
    public native boolean isPartialStringValid$proposedSelectedRange$originalString$originalSelectedRange$errorDescription$(NSString.NSStringPtr partialStringPtr, NSRange proposedSelRangePtr, String origString, @ByVal NSRange origSelRange, NSString.NSStringPtr error);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
