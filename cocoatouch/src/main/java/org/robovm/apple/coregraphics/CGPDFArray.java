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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFArray/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPDFArrayPtr extends Ptr<CGPDFArray, CGPDFArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGPDFArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGPDFArray() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFObject getObject(long index) {
        CGPDFObject.CGPDFObjectPtr ptr = new CGPDFObject.CGPDFObjectPtr();
        if (getObject(index, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasBoolean(long index) {
        BooleanPtr ptr = new BooleanPtr();
        return getBoolean(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean getBoolean(long index) {
        BooleanPtr ptr = new BooleanPtr();
        if (getBoolean(index, ptr)) {
            return ptr.get();
        }
        return false;
    }
    public boolean hasLong(long index) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        return getInteger(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getLong(long index) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        if (getInteger(index, ptr)) {
            return ptr.get();
        }
        return 0;
    }
    public boolean hasDouble(long index) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        return getNumber(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getDouble(long index) {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        if (getNumber(index, ptr)) {
            return ptr.get();
        }
        return 0;
    }
    public boolean hasName(long index) {
        BytePtr.BytePtrPtr ptr = new BytePtr.BytePtrPtr();
        return getName(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getName(long index) {
        BytePtr.BytePtrPtr ptr = new BytePtr.BytePtrPtr();
        if (getName(index, ptr)) {
            return ptr.get().toStringZ();
        }
        return null;
    }
    public boolean hasString(long index) {
        CGPDFString.CGPDFStringPtr ptr = new CGPDFString.CGPDFStringPtr();
        return getString(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFString getString(long index) {
        CGPDFString.CGPDFStringPtr ptr = new CGPDFString.CGPDFStringPtr();
        if (getString(index, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasArray(long index) {
        CGPDFArray.CGPDFArrayPtr ptr = new CGPDFArray.CGPDFArrayPtr();
        return getArray(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFArray getArray(long index) {
        CGPDFArray.CGPDFArrayPtr ptr = new CGPDFArray.CGPDFArrayPtr();
        if (getArray(index, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasDictionary(long index) {
        CGPDFDictionary.CGPDFDictionaryPtr ptr = new CGPDFDictionary.CGPDFDictionaryPtr();
        return getDictionary(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFDictionary getDictionary(long index) {
        CGPDFDictionary.CGPDFDictionaryPtr ptr = new CGPDFDictionary.CGPDFDictionaryPtr();
        if (getDictionary(index, ptr)) {
            return ptr.get();
        }
        return null;
    }
    public boolean hasStream(long index) {
        CGPDFStream.CGPDFStreamPtr ptr = new CGPDFStream.CGPDFStreamPtr();
        return getStream(index, ptr);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFStream getStream(long index) {
        CGPDFStream.CGPDFStreamPtr ptr = new CGPDFStream.CGPDFStreamPtr();
        if (getStream(index, ptr)) {
            return ptr.get();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetCount", optional=true)
    public native @MachineSizedUInt long size();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetObject", optional=true)
    private native boolean getObject(@MachineSizedUInt long index, CGPDFObject.CGPDFObjectPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetNull", optional=true)
    public native boolean isNull(@MachineSizedUInt long index);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetBoolean", optional=true)
    private native boolean getBoolean(@MachineSizedUInt long index, BooleanPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetInteger", optional=true)
    private native boolean getInteger(@MachineSizedUInt long index, MachineSizedSIntPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetNumber", optional=true)
    private native boolean getNumber(@MachineSizedUInt long index, MachineSizedFloatPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetName", optional=true)
    private native boolean getName(@MachineSizedUInt long index, BytePtr.BytePtrPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetString", optional=true)
    private native boolean getString(@MachineSizedUInt long index, CGPDFString.CGPDFStringPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetArray", optional=true)
    private native boolean getArray(@MachineSizedUInt long index, CGPDFArray.CGPDFArrayPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetDictionary", optional=true)
    private native boolean getDictionary(@MachineSizedUInt long index, CGPDFDictionary.CGPDFDictionaryPtr value);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPDFArrayGetStream", optional=true)
    private native boolean getStream(@MachineSizedUInt long index, CGPDFStream.CGPDFStreamPtr value);
    /*</methods>*/
}
