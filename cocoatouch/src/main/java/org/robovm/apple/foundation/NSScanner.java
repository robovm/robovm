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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSScanner/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSScannerPtr extends Ptr<NSScanner, NSScannerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSScanner.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSScanner() {}
    protected NSScanner(SkipInit skipInit) { super(skipInit); }
    public NSScanner(String string) { super((SkipInit) null); initObject(initWithString$(string)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "string")
    public native String string();
    @Method(selector = "scanLocation")
    public native @MachineSizedUInt long scanLocation();
    @Method(selector = "setScanLocation:")
    public native void setScanLocation(@MachineSizedUInt long pos);
    @Method(selector = "setCharactersToBeSkipped:")
    public native void setCharactersToBeSkipped(NSCharacterSet set);
    @Method(selector = "setCaseSensitive:")
    public native void setCaseSensitive(boolean flag);
    @Method(selector = "setLocale:")
    public native void setLocale(NSObject locale);
    @Method(selector = "charactersToBeSkipped")
    public native NSCharacterSet charactersToBeSkipped();
    @Method(selector = "caseSensitive")
    public native boolean caseSensitive();
    @Method(selector = "locale")
    public native NSObject locale();
    @Method(selector = "scanInt:")
    public native boolean scanInt$(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanInteger:")
    public native boolean scanInteger$(MachineSizedSIntPtr result);
    @Method(selector = "scanLongLong:")
    public native boolean scanLongLong$(LongPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "scanUnsignedLongLong:")
    public native boolean scanUnsignedLongLong$(LongPtr result);
    @Method(selector = "scanFloat:")
    public native boolean scanFloat$(FloatPtr result);
    @Method(selector = "scanDouble:")
    public native boolean scanDouble$(DoublePtr result);
    @Method(selector = "scanHexInt:")
    public native boolean scanHexInt$(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexLongLong:")
    public native boolean scanHexLongLong$(LongPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexFloat:")
    public native boolean scanHexFloat$(FloatPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexDouble:")
    public native boolean scanHexDouble$(DoublePtr result);
    @Method(selector = "scanString:intoString:")
    public native boolean scanString$intoString$(String string, NSString.NSStringPtr result);
    @Method(selector = "scanCharactersFromSet:intoString:")
    public native boolean scanCharactersFromSet$intoString$(NSCharacterSet set, NSString.NSStringPtr result);
    @Method(selector = "scanUpToString:intoString:")
    public native boolean scanUpToString$intoString$(String string, NSString.NSStringPtr result);
    @Method(selector = "scanUpToCharactersFromSet:intoString:")
    public native boolean scanUpToCharactersFromSet$intoString$(NSCharacterSet set, NSString.NSStringPtr result);
    @Method(selector = "isAtEnd")
    public native boolean isAtEnd();
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String string);
    @Method(selector = "scannerWithString:")
    public static native NSObject scannerWithString$(String string);
    @Method(selector = "localizedScannerWithString:")
    public static native NSObject localizedScannerWithString$(String string);
    @Method(selector = "scanDecimal:")
    public native boolean scanDecimal$(NSDecimal dcm);
    /*</methods>*/
}
