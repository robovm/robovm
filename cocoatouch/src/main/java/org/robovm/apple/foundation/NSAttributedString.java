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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/
import org.robovm.rt.annotation.WeaklyLinked;
import org.robovm.apple.coretext.CTAttributedStringAttribute;
import org.robovm.apple.coretext.CTAttributedStringAttributes;

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass @WeaklyLinked/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedString/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSAttributedStringPtr extends Ptr<NSAttributedString, NSAttributedStringPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAttributedString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSAttributedString() {}
    protected NSAttributedString(SkipInit skipInit) { super(skipInit); }
    public NSAttributedString(String str) { super((SkipInit) null); initObject(init(str)); }
    public NSAttributedString(String str, NSDictionary<NSString, NSObject> attrs) { super((SkipInit) null); initObject(init(str, attrs)); }
    public NSAttributedString(NSAttributedString attrStr) { super((SkipInit) null); initObject(init(attrStr)); }
    /*</constructors>*/
    public NSAttributedString(String str, NSAttributedStringAttributes attrs) {
        super((SkipInit)null);
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        initObject(init(str, attrs.getDictionary()));
    }
    @SuppressWarnings("unchecked")
    public NSAttributedString(String str, CMTextMarkupAttributes attrs) {
        super((SkipInit)null);
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        initObject(init(str, attrs.getDictionary().as(NSDictionary.class)));
    }
    @SuppressWarnings("unchecked")
    public NSAttributedString(String str, CTAttributedStringAttributes attrs) {
        super((SkipInit)null);
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        initObject(init(str, attrs.getDictionary().as(NSDictionary.class)));
    }
    /*<properties>*/
    @Property(selector = "string")
    public native String getString();
    @Property(selector = "length")
    public native @MachineSizedUInt long length();
    /*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NSAttributedString)) {
            return false;
        }
        return equalsTo((NSAttributedString) obj);
    }
    
    public NSObject getAttribute(String name, @MachineSizedUInt long location, NSRange range) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        return getAttribute(new NSString(name), location, range);
    }
    public NSObject getAttribute(NSAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value(), location, range);
    }
    public NSObject getAttribute(CMTextMarkupAttribute attribute, @MachineSizedUInt long location, NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value().as(NSString.class), location, range);
    }
    public NSObject getAttribute(CTAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value().as(NSString.class), location, range);
    }
    
    public NSObject getAttribute(String name, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        return getAttribute(new NSString(name), location, range, rangeLimit);
    }
    public NSObject getAttribute(NSAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value(), location, range, rangeLimit);
    }
    public NSObject getAttribute(CMTextMarkupAttribute attribute, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value().as(NSString.class), location, range, rangeLimit);
    }
    public NSObject getAttribute(CTAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        return getAttribute(attribute.value().as(NSString.class), location, range, rangeLimit);
    }

    public NSAttributedStringAttributes getAttributes(long location, NSRange range) {
        return new NSAttributedStringAttributes(getAttributesDictionary(location, range));
    }
    public NSAttributedStringAttributes getAttributes(long location, NSRange range, NSRange rangeLimit) {
        return new NSAttributedStringAttributes(getAttributesDictionary(location, range, rangeLimit));
    }
    public CMTextMarkupAttributes getTextMarkupAttributes(long location, NSRange range) {
        return new CMTextMarkupAttributes(getAttributesDictionary(location, range).as(CFDictionary.class));
    }
    public CMTextMarkupAttributes getTextMarkupAttributes(long location, NSRange range, NSRange rangeLimit) {
        return new CMTextMarkupAttributes(getAttributesDictionary(location, range, rangeLimit).as(CFDictionary.class));
    }
    public CTAttributedStringAttributes getCoreTextAttributes(long location, NSRange range) {
        return new CTAttributedStringAttributes(getAttributesDictionary(location, range).as(CFDictionary.class));
    }
    public CTAttributedStringAttributes getCoreTextAttributes(long location, NSRange range, NSRange rangeLimit) {
        return new CTAttributedStringAttributes(getAttributesDictionary(location, range, rangeLimit).as(CFDictionary.class));
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(String name, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, BooleanPtr> block) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        enumerateAttribute(new NSString(name), enumerationRange, opts, block);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(NSAttributedStringAttribute attribute, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, BooleanPtr> block) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        enumerateAttribute(attribute.value(), enumerationRange, opts, block);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(CMTextMarkupAttribute attribute, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, BooleanPtr> block) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        enumerateAttribute(attribute.value().as(NSString.class), enumerationRange, opts, block);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(CTAttributedStringAttribute attribute, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, BooleanPtr> block) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        enumerateAttribute(attribute.value().as(NSString.class), enumerationRange, opts, block);
    }
    
    /* UIKit extensions */
    /**
     * 
     * @param url
     * @param options
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public static NSAttributedString create(NSURL url, NSAttributedStringDocumentAttributes options) throws NSErrorException {
        return NSAttributedStringExtensions.createFromURL(url, options);
    }
    /**
     * 
     * @param data
     * @param options
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public static NSAttributedString create(NSData data, NSAttributedStringDocumentAttributes options) throws NSErrorException {
        return NSAttributedStringExtensions.createFromData(data, options);
    }
    /**
     * 
     * @param range
     * @param dict
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public NSData getData(NSRange range, NSAttributedStringDocumentAttributes dict) throws NSErrorException {
        return NSAttributedStringExtensions.getData(this, range, dict);
    }
    /**
     * 
     * @param range
     * @param dict
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public NSFileWrapper getFileWrapper(NSRange range, NSAttributedStringDocumentAttributes dict) throws NSErrorException {
        return NSAttributedStringExtensions.getFileWrapper(this, range, dict);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @WeaklyLinked
    public CGSize getSize() {
        return NSAttributedStringExtensions.getSize(this);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @WeaklyLinked
    public void draw(CGPoint point) {
        NSAttributedStringExtensions.draw(this, point);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect) {
        NSAttributedStringExtensions.draw(this, rect);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @WeaklyLinked
    public void draw(@ByVal CGRect rect, NSStringDrawingOptions options, NSStringDrawingContext context) {
        NSAttributedStringExtensions.draw(this, rect, options, context);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @WeaklyLinked
    public CGRect getBoundingRect(@ByVal CGSize size, NSStringDrawingOptions options, NSStringDrawingContext context) {
        return NSAttributedStringExtensions.getBoundingRect(this, size, options, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static NSAttributedString create(NSTextAttachment attachment) {
        return NSAttributedStringExtensions.create(attachment);
    }
    /*<methods>*/
    @Method(selector = "attributesAtIndex:effectiveRange:")
    public native NSDictionary<NSString, NSObject> getAttributesDictionary(@MachineSizedUInt long location, NSRange range);
    @Method(selector = "attribute:atIndex:effectiveRange:")
    public native NSObject getAttribute(NSString attrName, @MachineSizedUInt long location, NSRange range);
    @Method(selector = "attributedSubstringFromRange:")
    public native NSAttributedString substring(@ByVal NSRange range);
    @Method(selector = "attributesAtIndex:longestEffectiveRange:inRange:")
    public native NSDictionary<NSString, NSObject> getAttributesDictionary(@MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "attribute:atIndex:longestEffectiveRange:inRange:")
    public native NSObject getAttribute(NSString attrName, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "isEqualToAttributedString:")
    public native boolean equalsTo(NSAttributedString other);
    @Method(selector = "initWithString:")
    protected native @Pointer long init(String str);
    @Method(selector = "initWithString:attributes:")
    protected native @Pointer long init(String str, NSDictionary<NSString, NSObject> attrs);
    @Method(selector = "initWithAttributedString:")
    protected native @Pointer long init(NSAttributedString attrStr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumerateAttributesInRange:options:usingBlock:")
    public native void enumerateAttributes(@ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, @Block("(,@ByVal,)") VoidBlock3<NSDictionary<NSString, NSObject>, NSRange, BooleanPtr> block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumerateAttribute:inRange:options:usingBlock:")
    public native void enumerateAttribute(NSString attrName, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, @Block("(,@ByVal,)") VoidBlock3<NSObject, NSRange, BooleanPtr> block);
    /*</methods>*/
}
