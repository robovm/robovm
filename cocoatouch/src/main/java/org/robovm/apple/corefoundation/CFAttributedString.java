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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

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
    @WeaklyLinked
    public static CFAttributedString create(String str, NSAttributedStringAttributes attributes) {
        return create(null, str, attributes);
    }
    @WeaklyLinked
    public static CFAttributedString create(CFAllocator alloc, String str, NSAttributedStringAttributes attributes) {
        if (attributes == null) {
            return create(alloc, str, (CFDictionary)null);
        }
        return create(alloc, str, attributes.getDictionary().as(CFDictionary.class));
    }
    @WeaklyLinked
    public static CFAttributedString create(String str, CMTextMarkupAttributes attributes) {
        return create(null, str, attributes);
    }
    @WeaklyLinked
    public static CFAttributedString create(CFAllocator alloc, String str, CMTextMarkupAttributes attributes) {
        if (attributes == null) {
            return create(alloc, str, (CFDictionary)null);
        }
        return create(alloc, str, attributes.getDictionary());
    }
    @WeaklyLinked
    public static CFAttributedString create(String str, CTAttributedStringAttributes attributes) {
        return create(null, str, attributes);
    }
    @WeaklyLinked
    public static CFAttributedString create(CFAllocator alloc, String str, CTAttributedStringAttributes attributes) {
        if (attributes == null) {
            return create(alloc, str, (CFDictionary)null);
        }
        return create(alloc, str, attributes.getDictionary());
    }
    @WeaklyLinked
    public CFType getAttribute(long loc, NSAttributedStringAttribute attrName, CFRange effectiveRange) {
        if (attrName == null) {
            throw new NullPointerException("attrName");
        }
        return getAttribute(loc, attrName.value().as(CFString.class), effectiveRange);
    }
    @WeaklyLinked
    public CFType getAttribute(long loc, CMTextMarkupAttribute attrName, CFRange effectiveRange) {
        if (attrName == null) {
            throw new NullPointerException("attrName");
        }
        return getAttribute(loc, attrName.value(), effectiveRange);
    }
    @WeaklyLinked
    public CFType getAttribute(long loc, CTAttributedStringAttribute attrName, CFRange effectiveRange) {
        if (attrName == null) {
            throw new NullPointerException("attrName");
        }
        return getAttribute(loc, attrName.value(), effectiveRange);
    }
    @WeaklyLinked
    public NSAttributedStringAttributes getAttributes(long loc, CFRange effectiveRange) {
        CFDictionary dict = getAttributesDictionary(loc, effectiveRange);
        if (dict == null) return null;
        return new NSAttributedStringAttributes(dict.as(NSDictionary.class));
    }
    @WeaklyLinked
    public CMTextMarkupAttributes getTextMarkupAttributes(long loc, CFRange effectiveRange) {
        CFDictionary dict = getAttributesDictionary(loc, effectiveRange);
        if (dict == null) return null;
        return new CMTextMarkupAttributes(dict);
    }
    @WeaklyLinked
    public CTAttributedStringAttributes getCoreTextAttributes(long loc, CFRange effectiveRange) {
        CFDictionary dict = getAttributesDictionary(loc, effectiveRange);
        if (dict == null) return null;
        return new CTAttributedStringAttributes(null);
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
