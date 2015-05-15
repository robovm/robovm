/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFDictionary/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPDFDictionaryPtr extends Ptr<CGPDFDictionary, CGPDFDictionaryPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFDictionary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGPDFDictionary() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public boolean hasObject(String key) {
        CGPDFObject.CGPDFObjectPtr ptr = new CGPDFObject.CGPDFObjectPtr();
        return getObject(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFObject getObject(String key) {
        CGPDFObject.CGPDFObjectPtr ptr = new CGPDFObject.CGPDFObjectPtr();
        if (getObject(key, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasBoolean(String key) {
        BooleanPtr ptr = new BooleanPtr();
        return getBoolean(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean getBoolean(String key) {
        BooleanPtr ptr = new BooleanPtr();
        if (getBoolean(key, ptr)) {
            return ptr.get();
        }
        return false;
    }
    public boolean hasLong(String key) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        return getInteger(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getLong(String key) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        if (getInteger(key, ptr)) {
            return ptr.get();
        }
        return 0;
    }
    public boolean hasDouble(String key) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        return getNumber(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getDouble(String key) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        if (getNumber(key, ptr)) {
            return ptr.get();
        }
        return 0;
    }
    public boolean hasName(String key) {
        BytePtr.BytePtrPtr ptr = new BytePtr.BytePtrPtr();
        return getName(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getName(String key) {
        BytePtr.BytePtrPtr ptr = new BytePtr.BytePtrPtr();
        if (getName(key, ptr)) {
            return ptr.get().toStringZ();
        }
        return null;
    }
    public boolean hasString(String key) {
        CGPDFString.CGPDFStringPtr ptr = new CGPDFString.CGPDFStringPtr();
        return getString(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFString getString(String key) {
        CGPDFString.CGPDFStringPtr ptr = new CGPDFString.CGPDFStringPtr();
        if (getString(key, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasArray(String key) {
        CGPDFArray.CGPDFArrayPtr ptr = new CGPDFArray.CGPDFArrayPtr();
        return getArray(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFArray getArray(String key) {
        CGPDFArray.CGPDFArrayPtr ptr = new CGPDFArray.CGPDFArrayPtr();
        if (getArray(key, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasDictionary(String key) {
        CGPDFDictionary.CGPDFDictionaryPtr ptr = new CGPDFDictionary.CGPDFDictionaryPtr();
        return getDictionary(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFDictionary getDictionary(String key) {
        CGPDFDictionary.CGPDFDictionaryPtr ptr = new CGPDFDictionary.CGPDFDictionaryPtr();
        if (getDictionary(key, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasStream(String key) {
        CGPDFStream.CGPDFStreamPtr ptr = new CGPDFStream.CGPDFStreamPtr();
        return getStream(key, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFStream getStream(String key) {
        CGPDFStream.CGPDFStreamPtr ptr = new CGPDFStream.CGPDFStreamPtr();
        if (getStream(key, ptr)) {
            return ptr.get();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetCount", optional=true)
    public native @MachineSizedUInt long size();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetObject", optional=true)
    private native boolean getObject(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, CGPDFObject.CGPDFObjectPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetBoolean", optional=true)
    private native boolean getBoolean(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, BooleanPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetInteger", optional=true)
    private native boolean getInteger(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, MachineSizedSIntPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetNumber", optional=true)
    private native boolean getNumber(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, MachineSizedFloatPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetName", optional=true)
    private native boolean getName(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, BytePtr.BytePtrPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetString", optional=true)
    private native boolean getString(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, CGPDFString.CGPDFStringPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetArray", optional=true)
    private native boolean getArray(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, CGPDFArray.CGPDFArrayPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetDictionary", optional=true)
    private native boolean getDictionary(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, CGPDFDictionary.CGPDFDictionaryPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFDictionaryGetStream", optional=true)
    private native boolean getStream(@org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String key, CGPDFStream.CGPDFStreamPtr value);
    /*</methods>*/
}
