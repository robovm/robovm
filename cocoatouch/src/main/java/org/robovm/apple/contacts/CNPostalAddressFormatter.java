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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNPostalAddressFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNPostalAddressFormatterPtr extends Ptr<CNPostalAddressFormatter, CNPostalAddressFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNPostalAddressFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNPostalAddressFormatter() {}
    protected CNPostalAddressFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "style")
    public native CNPostalAddressFormatterStyle getStyle();
    @Property(selector = "setStyle:")
    public native void setStyle(CNPostalAddressFormatterStyle v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSAttributedString format(CNPostalAddress postalAddress, NSAttributedStringAttributes attributes) {
        return format(postalAddress, attributes.getDictionary());
    }
    public NSAttributedString format(CNPostalAddress postalAddress, CTAttributedStringAttributes attributes) {
        return format(postalAddress, attributes.getDictionary().as(NSDictionary.class));
    }
    public NSAttributedString format(CNPostalAddress postalAddress, CMTextMarkupAttributes attributes) {
        return format(postalAddress, attributes.getDictionary().as(NSDictionary.class));
    }
    public static NSAttributedString format(CNPostalAddress postalAddress, CNPostalAddressFormatterStyle style, NSAttributedStringAttributes attributes) {
        return format(postalAddress, style, attributes.getDictionary());
    }
    public static NSAttributedString format(CNPostalAddress postalAddress, CNPostalAddressFormatterStyle style, CTAttributedStringAttributes attributes) {
        return format(postalAddress, style, attributes.getDictionary().as(NSDictionary.class));
    }
    public static NSAttributedString format(CNPostalAddress postalAddress, CNPostalAddressFormatterStyle style, CMTextMarkupAttributes attributes) {
        return format(postalAddress, style, attributes.getDictionary().as(NSDictionary.class));
    }
    /*<methods>*/
    @Method(selector = "stringFromPostalAddress:")
    public native String format(CNPostalAddress postalAddress);
    @Method(selector = "attributedStringFromPostalAddress:withDefaultAttributes:")
    public native NSAttributedString format(CNPostalAddress postalAddress, NSDictionary attributes);
    @Method(selector = "stringFromPostalAddress:style:")
    public static native String format(CNPostalAddress postalAddress, CNPostalAddressFormatterStyle style);
    @Method(selector = "attributedStringFromPostalAddress:style:withDefaultAttributes:")
    public static native NSAttributedString format(CNPostalAddress postalAddress, CNPostalAddressFormatterStyle style, NSDictionary attributes);
    /*</methods>*/
}
