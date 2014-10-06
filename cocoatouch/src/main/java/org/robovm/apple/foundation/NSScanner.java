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
    public int scanInt() {
        IntPtr ptr = new IntPtr();
        if (!scanInt(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @MachineSizedSInt double scanInteger() {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        if (!scanInteger(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    public long scanLong() {
        LongPtr ptr = new LongPtr();
        if (!scanLongLong(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public long scanUnsignedLong() {
        LongPtr ptr = new LongPtr();
        if (!scanUnsignedLongLong(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    public float scanFloat() {
        FloatPtr ptr = new FloatPtr();
        if (!scanFloat(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    public double scanDouble() {
        DoublePtr ptr = new DoublePtr();
        if (!scanDouble(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    public int scanHexInt() {
        IntPtr ptr = new IntPtr();
        if (!scanHexInt(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long scanHexLong() {
        LongPtr ptr = new LongPtr();
        if (!scanHexLongLong(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public float scanHexFloat() {
        FloatPtr ptr = new FloatPtr();
        if (!scanHexFloat(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double scanHexDouble() {
        DoublePtr ptr = new DoublePtr();
        if (!scanHexDouble(ptr)) {
            return 0;
        }
        return ptr.get();
    }
    public String scanString(String string) {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        if (!scanString(string, ptr)) {
            return null;
        }
        return ptr.get().toString();
    }
    public String scanCharacters(NSCharacterSet set) {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        if (!scanCharactersFromSet(set, ptr)) {
            return null;
        }
        return ptr.get().toString();
    }
    public String scanUpToString(String string) {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        if (!scanUpToString(string, ptr)) {
            return null;
        }
        return ptr.get().toString();
    }
    public String scanUpToCharacters(NSCharacterSet set) {
        NSString.NSStringPtr ptr = new NSString.NSStringPtr();
        if (!scanUpToCharactersFromSet(set, ptr)) {
            return null;
        }
        return ptr.get().toString();
    }
    public NSDecimal scanDecimal() {
        NSDecimal.NSDecimalPtr ptr = new NSDecimal.NSDecimalPtr();
        if (!scanDecimal(ptr)) {
            return null;
        }
        return ptr.get();
    }
    /*<methods>*/
    @Method(selector = "string")
    public native String getString();
    @Method(selector = "scanLocation")
    public native @MachineSizedUInt long getScanLocation();
    @Method(selector = "setScanLocation:")
    public native void setScanLocation(@MachineSizedUInt long pos);
    @Method(selector = "setCharactersToBeSkipped:")
    public native void setCharactersToBeSkipped(NSCharacterSet set);
    @Method(selector = "setCaseSensitive:")
    public native void setCaseSensitive(boolean flag);
    @Method(selector = "setLocale:")
    public native void setLocale(NSLocale locale);
    @Method(selector = "charactersToBeSkipped")
    public native NSCharacterSet getCharactersToBeSkipped();
    @Method(selector = "caseSensitive")
    public native boolean isCaseSensitive();
    @Method(selector = "locale")
    public native NSLocale getLocale();
    @Method(selector = "scanInt:")
    private native boolean scanInt(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanInteger:")
    private native boolean scanInteger(MachineSizedSIntPtr result);
    @Method(selector = "scanLongLong:")
    private native boolean scanLongLong(LongPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "scanUnsignedLongLong:")
    private native boolean scanUnsignedLongLong(LongPtr result);
    @Method(selector = "scanFloat:")
    private native boolean scanFloat(FloatPtr result);
    @Method(selector = "scanDouble:")
    private native boolean scanDouble(DoublePtr result);
    @Method(selector = "scanHexInt:")
    private native boolean scanHexInt(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexLongLong:")
    private native boolean scanHexLongLong(LongPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexFloat:")
    private native boolean scanHexFloat(FloatPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "scanHexDouble:")
    private native boolean scanHexDouble(DoublePtr result);
    @Method(selector = "scanString:intoString:")
    private native boolean scanString(String string, NSString.NSStringPtr result);
    @Method(selector = "scanCharactersFromSet:intoString:")
    private native boolean scanCharactersFromSet(NSCharacterSet set, NSString.NSStringPtr result);
    @Method(selector = "scanUpToString:intoString:")
    private native boolean scanUpToString(String string, NSString.NSStringPtr result);
    @Method(selector = "scanUpToCharactersFromSet:intoString:")
    private native boolean scanUpToCharactersFromSet(NSCharacterSet set, NSString.NSStringPtr result);
    @Method(selector = "isAtEnd")
    public native boolean isAtEnd();
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String string);
    @Method(selector = "scannerWithString:")
    public static native NSScanner create(String string);
    @Method(selector = "localizedScannerWithString:")
    public static native NSScanner createLocalized(String string);
    @Method(selector = "scanDecimal:")
    private native boolean scanDecimal(NSDecimal.NSDecimalPtr dcm);
    /*</methods>*/
}
