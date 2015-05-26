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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMutableAttributedString/*</name>*/ 
    extends /*<extends>*/NSAttributedString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMutableAttributedStringPtr extends Ptr<NSMutableAttributedString, NSMutableAttributedStringPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMutableAttributedString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMutableAttributedString() {}
    protected NSMutableAttributedString(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public NSMutableAttributedString(NSAttributedString attrStr) {
        super(attrStr);
    }
    public NSMutableAttributedString(String str, NSAttributedStringAttributes attrs) {
        super(str, attrs);
    }
    public NSMutableAttributedString(String str, CMTextMarkupAttributes attrs) {
        super(str, attrs);
    }
    public NSMutableAttributedString(String str, CTAttributedStringAttributes attrs) {
        super(str, attrs);
    }
    public NSMutableAttributedString(String str) {
        super(str);
    }    
    
    /*<properties>*/
    @Property(selector = "mutableString")
    public native NSMutableString getMutableString();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void addAttribute(String name, NSObject value, @ByVal NSRange range) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        addAttribute(new NSString(name), value, range);
    }
    public void addAttribute(NSAttributedStringAttribute attribute, NSObject value, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        addAttribute(attribute.value(), value, range);
    }
    public void addAttribute(CMTextMarkupAttribute attribute, NSObject value, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        addAttribute(attribute.value().as(NSString.class), value, range);
    }
    public void addAttribute(CTAttributedStringAttribute attribute, NSObject value, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        addAttribute(attribute.value().as(NSString.class), value, range);
    }
    
    public void addAttributes(NSAttributedStringAttributes attrs, @ByVal NSRange range) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        addAttributes(attrs.getDictionary(), range);
    }
    public void addAttributes(CMTextMarkupAttributes attrs, @ByVal NSRange range) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        addAttributes(attrs.getDictionary().as(NSDictionary.class), range);
    }
    public void addAttributes(CTAttributedStringAttributes attrs, @ByVal NSRange range) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        addAttributes(attrs.getDictionary().as(NSDictionary.class), range);
    }
    
    public void removeAttribute(String name, @ByVal NSRange range) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        removeAttribute(new NSString(name), range);
    }
    public void removeAttribute(NSAttributedStringAttribute attribute, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        removeAttribute(attribute.value(), range);
    }
    public void removeAttribute(CMTextMarkupAttribute attribute, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        removeAttribute(attribute.value().as(NSString.class), range);
    }
    public void removeAttribute(CTAttributedStringAttribute attribute, @ByVal NSRange range) {
        if (attribute == null) {
            throw new NullPointerException("attribute");
        }
        removeAttribute(attribute.value().as(NSString.class), range);
    }
    
    /* UIKit extensions */
    /**
     * 
     * @param url
     * @param opts
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public boolean read(NSURL url, NSAttributedStringDocumentAttributes opts) throws NSErrorException {
        return NSMutableAttributedStringExtensions.readFromFileURL(this, url, opts);
    }
    /**
     * 
     * @param data
     * @param opts
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    @WeaklyLinked
    public boolean read(NSData data, NSAttributedStringDocumentAttributes opts) throws NSErrorException {
        return NSMutableAttributedStringExtensions.readFromData(this, data, opts);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void fixAttributes(NSRange range) {
        NSMutableAttributedStringExtensions.fixAttributesInRange(this, range);
    }
    /*<methods>*/
    @Method(selector = "replaceCharactersInRange:withString:")
    public native void replace(@ByVal NSRange range, String str);
    @Method(selector = "setAttributes:range:")
    public native void setAttributes(NSDictionary<NSString, NSObject> attrs, @ByVal NSRange range);
    @Method(selector = "addAttribute:value:range:")
    public native void addAttribute(NSString name, NSObject value, @ByVal NSRange range);
    @Method(selector = "addAttributes:range:")
    public native void addAttributes(NSDictionary<NSString, NSObject> attrs, @ByVal NSRange range);
    @Method(selector = "removeAttribute:range:")
    public native void removeAttribute(NSString name, @ByVal NSRange range);
    @Method(selector = "replaceCharactersInRange:withAttributedString:")
    public native void replace(@ByVal NSRange range, NSAttributedString attrString);
    @Method(selector = "insertAttributedString:atIndex:")
    public native void insert(NSAttributedString attrString, @MachineSizedUInt long loc);
    @Method(selector = "appendAttributedString:")
    public native void append(NSAttributedString attrString);
    @Method(selector = "deleteCharactersInRange:")
    public native void delete(@ByVal NSRange range);
    @Method(selector = "setAttributedString:")
    public native void setAttributedString(NSAttributedString attrString);
    @Method(selector = "beginEditing")
    public native void beginEditing();
    @Method(selector = "endEditing")
    public native void endEditing();
    /*</methods>*/
}
