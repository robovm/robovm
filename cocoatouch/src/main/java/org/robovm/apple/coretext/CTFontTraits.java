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
/*<visibility>*/public/*</visibility>*/ class CTFontTraits 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    public CTFontTraits() {
        this.data = CFMutableDictionary.create();
    }
    protected CTFontTraits(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CTFontTraits.class); }/*</bind>*/
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
    public CTFontSymbolicTraits getSymbolicTrait() {
        if (data.containsKey(SymbolicTrait())) {
            CFNumber val = data.get(SymbolicTrait(), CFNumber.class);
            return new CTFontSymbolicTraits(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setSymbolicTrait(CTFontSymbolicTraits trait) {
        data.put(SymbolicTrait(), CFNumber.valueOf((int)trait.value()));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getWeightTrait() {
        if (data.containsKey(WeightTrait())) {
            CFNumber val = data.get(WeightTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setWeightTrait(float trait) {
        data.put(WeightTrait(), CFNumber.valueOf(trait));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getWidthTrait() {
        if (data.containsKey(WidthTrait())) {
            CFNumber val = data.get(WidthTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setWidthTrait(float trait) {
        data.put(WidthTrait(), CFNumber.valueOf(trait));
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getSlantTrait() {
        if (data.containsKey(SlantTrait())) {
            CFNumber val = data.get(SlantTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public void setSlantTrait(float trait) {
        data.put(SlantTrait(), CFNumber.valueOf(trait));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontSymbolicTrait", optional=true)
    protected static native CFString SymbolicTrait();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontWeightTrait", optional=true)
    protected static native CFString WeightTrait();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontWidthTrait", optional=true)
    protected static native CFString WidthTrait();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCTFontSlantTrait", optional=true)
    protected static native CFString SlantTrait();
    /*</methods>*/
}
