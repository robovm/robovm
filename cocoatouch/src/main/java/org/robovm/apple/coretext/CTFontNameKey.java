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
package org.robovm.apple.coretext;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontNameKey/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFontNameKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Copyright = new CTFontNameKey("CopyrightValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Family = new CTFontNameKey("FamilyValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey SubFamily = new CTFontNameKey("SubFamilyValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Style = new CTFontNameKey("StyleValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Unique = new CTFontNameKey("UniqueValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Full = new CTFontNameKey("FullValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Version = new CTFontNameKey("VersionValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey PostScript = new CTFontNameKey("PostScriptValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Trademark = new CTFontNameKey("TrademarkValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Manufacturer = new CTFontNameKey("ManufacturerValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Designer = new CTFontNameKey("DesignerValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey Description = new CTFontNameKey("DescriptionValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey VendorURL = new CTFontNameKey("VendorURLValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey DesignerURL = new CTFontNameKey("DesignerURLValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey License = new CTFontNameKey("LicenseValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey LicenseURL = new CTFontNameKey("LicenseURLValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey SampleText = new CTFontNameKey("SampleTextValue");
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final CTFontNameKey PostScriptCID = new CTFontNameKey("PostScriptCIDValue");
    private static CTFontNameKey[] values = new CTFontNameKey[]{ };
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CTFontNameKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CTFontNameKey valueOf(NSString value) {
        for (CTFontNameKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTFontNameKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontCopyrightNameKey", optional=true)
    protected static native CFString CopyrightValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFamilyNameKey", optional=true)
    protected static native CFString FamilyValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontSubFamilyNameKey", optional=true)
    protected static native CFString SubFamilyValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontStyleNameKey", optional=true)
    protected static native CFString StyleValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontUniqueNameKey", optional=true)
    protected static native CFString UniqueValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontFullNameKey", optional=true)
    protected static native CFString FullValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVersionNameKey", optional=true)
    protected static native CFString VersionValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontPostScriptNameKey", optional=true)
    protected static native CFString PostScriptValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontTrademarkNameKey", optional=true)
    protected static native CFString TrademarkValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontManufacturerNameKey", optional=true)
    protected static native CFString ManufacturerValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontDesignerNameKey", optional=true)
    protected static native CFString DesignerValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontDescriptionNameKey", optional=true)
    protected static native CFString DescriptionValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVendorURLNameKey", optional=true)
    protected static native CFString VendorURLValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontDesignerURLNameKey", optional=true)
    protected static native CFString DesignerURLValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontLicenseNameKey", optional=true)
    protected static native CFString LicenseValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontLicenseURLNameKey", optional=true)
    protected static native CFString LicenseURLValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontSampleTextNameKey", optional=true)
    protected static native CFString SampleTextValue();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontPostScriptCIDNameKey", optional=true)
    protected static native CFString PostScriptCIDValue();
    /*</methods>*/
}
