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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontVariationAxes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    public CTFontVariationAxes() {
        this.data = CFMutableDictionary.create();
    }
    protected CTFontVariationAxes(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CTFontVariationAxes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.2 and later.
     */
    public long getIdentifier() {
        if (data.containsKey(IdentifierKey())) {
            CFNumber val = data.get(IdentifierKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setIdentifier(long identifier) {
        data.put(IdentifierKey(), CFNumber.valueOf(identifier));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getMinimumValue() {
        if (data.containsKey(MinimumValueKey())) {
            CFNumber val = data.get(MinimumValueKey(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setMinimumValue(double value) {
        data.put(MinimumValueKey(), CFNumber.valueOf(value));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getMaximumValue() {
        if (data.containsKey(MaximumValueKey())) {
            CFNumber val = data.get(MaximumValueKey(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setMaximumValue(double value) {
        data.put(MaximumValueKey(), CFNumber.valueOf(value));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getDefaultValue() {
        if (data.containsKey(DefaultValueKey())) {
            CFNumber val = data.get(DefaultValueKey(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setDefaultValue(double value) {
        data.put(DefaultValueKey(), CFNumber.valueOf(value));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getLocalizedName() {
        if (data.containsKey(NameKey())) {
            CFString val = data.get(NameKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setLocalizedName(String name) {
        data.put(NameKey(), new CFString(name));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAxisIdentifierKey", optional=true)
    protected static native CFString IdentifierKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAxisMinimumValueKey", optional=true)
    protected static native CFString MinimumValueKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAxisMaximumValueKey", optional=true)
    protected static native CFString MaximumValueKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAxisDefaultValueKey", optional=true)
    protected static native CFString DefaultValueKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontVariationAxisNameKey", optional=true)
    protected static native CFString NameKey();
    /*</methods>*/
}
