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
package org.robovm.apple.contacts;

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
/*</imports>*/
import org.robovm.apple.uikit.NSAttributedStringAttributes;
import org.robovm.apple.coremedia.CMTextMarkupAttributes;
import org.robovm.apple.coretext.CTAttributedStringAttributes;

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNContactFormatterPtr extends Ptr<CNContactFormatter, CNContactFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNContactFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNContactFormatter() {}
    protected CNContactFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "style")
    public native CNContactFormatterStyle getStyle();
    @Property(selector = "setStyle:")
    public native void setStyle(CNContactFormatterStyle v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSAttributedString format(CNContact contact, NSAttributedStringAttributes attributes) {
        return format(contact, attributes.getDictionary());
    }
    public NSAttributedString format(CNContact contact, CTAttributedStringAttributes attributes) {
        return format(contact, attributes.getDictionary().as(NSDictionary.class));
    }
    public NSAttributedString format(CNContact contact, CMTextMarkupAttributes attributes) {
        return format(contact, attributes.getDictionary().as(NSDictionary.class));
    }
    public static NSAttributedString format(CNContact contact, CNContactFormatterStyle style, NSAttributedStringAttributes attributes) {
        return format(contact, style, attributes.getDictionary());
    }
    public static NSAttributedString format(CNContact contact, CNContactFormatterStyle style, CTAttributedStringAttributes attributes) {
        return format(contact, style, attributes.getDictionary().as(NSDictionary.class));
    }
    public static NSAttributedString format(CNContact contact, CNContactFormatterStyle style, CMTextMarkupAttributes attributes) {
        return format(contact, style, attributes.getDictionary().as(NSDictionary.class));
    }
    /*<methods>*/
    @Method(selector = "stringFromContact:")
    public native String format(CNContact contact);
    @Method(selector = "attributedStringFromContact:defaultAttributes:")
    public native NSAttributedString format(CNContact contact, NSDictionary attributes);
    @Method(selector = "descriptorForRequiredKeysForStyle:")
    public static native String getDescriptorForRequiredKeys(CNContactFormatterStyle style);
    @Method(selector = "stringFromContact:style:")
    public static native String format(CNContact contact, CNContactFormatterStyle style);
    @Method(selector = "attributedStringFromContact:style:defaultAttributes:")
    public static native NSAttributedString format(CNContact contact, CNContactFormatterStyle style, NSDictionary attributes);
    @Method(selector = "nameOrderForContact:")
    public static native CNContactDisplayNameOrder getNameOrder(CNContact contact);
    @Method(selector = "delimiterForContact:")
    public static native String getDelimiter(CNContact contact);
    /*</methods>*/
}
