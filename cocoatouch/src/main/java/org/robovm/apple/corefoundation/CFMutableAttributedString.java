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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/
import org.robovm.apple.coremedia.CMTextMarkupAttribute;
import org.robovm.apple.coremedia.CMTextMarkupAttributes;
import org.robovm.apple.uikit.NSAttributedStringAttribute;
import org.robovm.apple.uikit.NSAttributedStringAttributes;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableAttributedString/*</name>*/ 
    extends /*<extends>*/CFAttributedString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableAttributedStringPtr extends Ptr<CFMutableAttributedString, CFMutableAttributedStringPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableAttributedString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableAttributedString() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFMutableAttributedString createCopy(@MachineSizedSInt long maxLength, CFAttributedString aStr) {
        return createCopy(null, maxLength, aStr);
    }
    public static CFMutableAttributedString create(@MachineSizedSInt long maxLength) {
        return create(null, maxLength);
    }
    public void setAttributes(@ByVal CFRange range, NSAttributedStringAttributes replacement, boolean clearOtherAttributes) {
        if (replacement == null) {
            setAttributesDictionary(range, null, clearOtherAttributes);
        } else {
            setAttributesDictionary(range, replacement.getDictionary().as(CFDictionary.class), clearOtherAttributes);
        }
    }
    public void setAttributes(@ByVal CFRange range, CMTextMarkupAttributes replacement, boolean clearOtherAttributes) {
        if (replacement == null) {
            setAttributesDictionary(range, null, clearOtherAttributes);
        } else {
            setAttributesDictionary(range, replacement.getDictionary(), clearOtherAttributes);
        }
    }
    public void setAttribute(@ByVal CFRange range, NSAttributedStringAttribute attribute, CFType value) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        setAttribute(range, attribute.value().as(CFString.class), value);
    }
    public void setAttribute(@ByVal CFRange range, CMTextMarkupAttribute attribute, CFType value) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        setAttribute(range, attribute.value(), value);
    }
    public void removeAttribute(@ByVal CFRange range, NSAttributedStringAttribute attribute) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        removeAttribute(range, attribute.value().as(CFString.class));
    }
    public void removeAttribute(@ByVal CFRange range, CMTextMarkupAttribute attribute) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        removeAttribute(range, attribute.value());
    }
    /*<methods>*/
    @Bridge(symbol="CFAttributedStringCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableAttributedString createCopy(CFAllocator alloc, @MachineSizedSInt long maxLength, CFAttributedString aStr);
    @Bridge(symbol="CFAttributedStringCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableAttributedString create(CFAllocator alloc, @MachineSizedSInt long maxLength);
    @Bridge(symbol="CFAttributedStringReplaceString", optional=true)
    public native void replaceString(@ByVal CFRange range, String replacement);
    @Bridge(symbol="CFAttributedStringGetMutableString", optional=true)
    public static native CFMutableString getMutableString(CFAttributedString aStr);
    @Bridge(symbol="CFAttributedStringSetAttributes", optional=true)
    public native void setAttributesDictionary(@ByVal CFRange range, CFDictionary replacement, boolean clearOtherAttributes);
    @Bridge(symbol="CFAttributedStringSetAttribute", optional=true)
    public native void setAttribute(@ByVal CFRange range, CFString attrName, CFType value);
    @Bridge(symbol="CFAttributedStringRemoveAttribute", optional=true)
    public native void removeAttribute(@ByVal CFRange range, CFString attrName);
    @Bridge(symbol="CFAttributedStringReplaceAttributedString", optional=true)
    public native void replaceAttributedString(@ByVal CFRange range, CFAttributedString replacement);
    @Bridge(symbol="CFAttributedStringBeginEditing", optional=true)
    public native void beginEditing();
    @Bridge(symbol="CFAttributedStringEndEditing", optional=true)
    public native void endEditing();
    /*</methods>*/
}
