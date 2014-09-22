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
@Marshaler(CTFontTraits.Marshaler.class)
/*<annotations>*/@Library("CoreText")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class CTFontTraits 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CTFontTraits toObject(Class<CTFontTraits> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontTraits(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontTraits o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CTFontTraits(CFDictionary data) {
        this.data = data;
    }
    public CTFontTraits() {
        this.data = CFMutableDictionary.create();
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
    public CTFontTraits setSymbolicTrait(CTFontSymbolicTraits trait) {
        data.put(SymbolicTrait(), CFNumber.valueOf((int)trait.value()));
        return this;
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
    public CTFontTraits setWeightTrait(float trait) {
        data.put(WeightTrait(), CFNumber.valueOf(trait));
        return this;
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
    public CTFontTraits setWidthTrait(float trait) {
        data.put(WidthTrait(), CFNumber.valueOf(trait));
        return this;
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
    public CTFontTraits setSlantTrait(float trait) {
        data.put(SlantTrait(), CFNumber.valueOf(trait));
        return this;
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
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
