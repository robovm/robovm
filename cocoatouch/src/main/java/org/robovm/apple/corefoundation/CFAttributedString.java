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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/
import org.robovm.apple.coremedia.CMTextMarkupAttribute;
import org.robovm.apple.coremedia.CMTextMarkupAttributes;
import org.robovm.apple.uikit.NSAttributedStringAttribute;
import org.robovm.apple.uikit.NSAttributedStringAttributes;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFAttributedString/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFAttributedStringPtr extends Ptr<CFAttributedString, CFAttributedStringPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFAttributedString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFAttributedString() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFAttributedString create(String str, NSAttributedStringAttributes attributes) {
        return create(null, str, attributes);
    }
    public static CFAttributedString create(CFAllocator alloc, String str, NSAttributedStringAttributes attributes) {
        return create(null, str, attributes.getDictionary().as(CFDictionary.class));
    }
    public static CFAttributedString create(String str, CMTextMarkupAttributes attributes) {
        return create(null, str, attributes);
    }
    public static CFAttributedString create(CFAllocator alloc, String str, CMTextMarkupAttributes attributes) {
        return create(null, str, attributes.getDictionary());
    }

    public CFType getAttribute(long loc, NSAttributedStringAttribute attrName, CFRange effectiveRange) {
        return getAttribute(loc, attrName.value().as(CFString.class), effectiveRange);
    }
    public CFType getAttribute(long loc, CMTextMarkupAttribute attrName, CFRange effectiveRange) {
        return getAttribute(loc, attrName.value(), effectiveRange);
    }
    
    public NSAttributedStringAttributes getAttributes(long loc, CFRange effectiveRange) {
        return new NSAttributedStringAttributes(getAttributesDictionary(loc, effectiveRange).as(NSDictionary.class));
    }
    public CMTextMarkupAttributes getTextMarkupAttributes(long loc, CFRange effectiveRange) {
        return new CMTextMarkupAttributes(getAttributesDictionary(loc, effectiveRange));
    }
    /*<methods>*/
    @Bridge(symbol="CFAttributedStringGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFAttributedStringCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFAttributedString create(CFAllocator alloc, String str, CFDictionary attributes);
    @Bridge(symbol="CFAttributedStringCreateWithSubstring", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFAttributedString createWithSubstring(CFAllocator alloc, CFAttributedString aStr, @ByVal CFRange range);
    @Bridge(symbol="CFAttributedStringCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFAttributedString createCopy(CFAllocator alloc, CFAttributedString aStr);
    @Bridge(symbol="CFAttributedStringGetString", optional=true)
    public native String getString();
    @Bridge(symbol="CFAttributedStringGetLength", optional=true)
    public native @MachineSizedSInt long length();
    @Bridge(symbol="CFAttributedStringGetAttributes", optional=true)
    public native CFDictionary getAttributesDictionary(@MachineSizedSInt long loc, CFRange effectiveRange);
    @Bridge(symbol="CFAttributedStringGetAttribute", optional=true)
    public native CFType getAttribute(@MachineSizedSInt long loc, CFString attrName, CFRange effectiveRange);
    /*</methods>*/
}
