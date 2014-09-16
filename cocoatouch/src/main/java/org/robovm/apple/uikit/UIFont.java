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
package org.robovm.apple.uikit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFont/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIFontPtr extends Ptr<UIFont, UIFontPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIFont.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIFont() {}
    protected UIFont(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "familyName")
    public native String getFamilyName();
    @Property(selector = "fontName")
    public native String getFontName();
    @Property(selector = "pointSize")
    public native @MachineSizedFloat double getPointSize();
    @Property(selector = "ascender")
    public native @MachineSizedFloat double getAscender();
    @Property(selector = "descender")
    public native @MachineSizedFloat double getDescender();
    @Property(selector = "capHeight")
    public native @MachineSizedFloat double getCapHeight();
    @Property(selector = "xHeight")
    public native @MachineSizedFloat double getXHeight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "lineHeight")
    public native @MachineSizedFloat double getLineHeight();
    @Property(selector = "leading")
    public native @MachineSizedFloat double getLeading();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFont getPreferredFont(UIFontTextStyle style) {
        return getPreferredFont(style.value());
    }
    /*<methods>*/
    @Method(selector = "fontWithSize:")
    public native UIFont getFontWithSize(@MachineSizedFloat double fontSize);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fontDescriptor")
    public native UIFontDescriptor getFontDescriptor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "preferredFontForTextStyle:")
    protected static native UIFont getPreferredFont(NSString style);
    @Method(selector = "fontWithName:size:")
    public static native UIFont getFont(String fontName, @MachineSizedFloat double fontSize);
    @Method(selector = "familyNames")
    public static native NSArray<NSString> getFamilyNames();
    @Method(selector = "fontNamesForFamilyName:")
    public static native NSArray<NSString> getFontNamesForFamilyName(String familyName);
    @Method(selector = "systemFontOfSize:")
    public static native UIFont getSystemFont(@MachineSizedFloat double fontSize);
    @Method(selector = "boldSystemFontOfSize:")
    public static native UIFont getBoldSystemFont(@MachineSizedFloat double fontSize);
    @Method(selector = "italicSystemFontOfSize:")
    public static native UIFont getItalicSystemFont(@MachineSizedFloat double fontSize);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fontWithDescriptor:size:")
    public static native UIFont getFont(UIFontDescriptor descriptor, @MachineSizedFloat double pointSize);
    @Method(selector = "labelFontSize")
    public static native @MachineSizedFloat double getLabelFontSize();
    @Method(selector = "buttonFontSize")
    public static native @MachineSizedFloat double getButtonFontSize();
    @Method(selector = "smallSystemFontSize")
    public static native @MachineSizedFloat double getSmallSystemFontSize();
    @Method(selector = "systemFontSize")
    public static native @MachineSizedFloat double getSystemFontSize();
    /*</methods>*/
}
