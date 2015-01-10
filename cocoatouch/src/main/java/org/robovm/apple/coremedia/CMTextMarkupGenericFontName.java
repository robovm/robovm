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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CMTextMarkupGenericFontName.Marshaler.class)
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextMarkupGenericFontName/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CMTextMarkupGenericFontName toObject(Class<CMTextMarkupGenericFontName> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMTextMarkupGenericFontName.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextMarkupGenericFontName o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTextMarkupGenericFontName.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Default = new CMTextMarkupGenericFontName("DefaultValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Serif = new CMTextMarkupGenericFontName("SerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName SansSerif = new CMTextMarkupGenericFontName("SansSerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Monospace = new CMTextMarkupGenericFontName("MonospaceValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName ProportionalSerif = new CMTextMarkupGenericFontName("ProportionalSerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName ProportionalSansSerif = new CMTextMarkupGenericFontName("ProportionalSansSerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName MonospaceSerif = new CMTextMarkupGenericFontName("MonospaceSerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName MonospaceSansSerif = new CMTextMarkupGenericFontName("MonospaceSansSerifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Casual = new CMTextMarkupGenericFontName("CasualValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Cursive = new CMTextMarkupGenericFontName("CursiveValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName Fantasy = new CMTextMarkupGenericFontName("FantasyValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupGenericFontName SmallCapital = new CMTextMarkupGenericFontName("SmallCapitalValue");
    
    private static CMTextMarkupGenericFontName[] values = new CMTextMarkupGenericFontName[] {Default, Serif, SansSerif, Monospace, 
        ProportionalSerif, ProportionalSansSerif, MonospaceSerif, MonospaceSansSerif, Casual, Cursive, Fantasy, SmallCapital};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CMTextMarkupGenericFontName(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CMTextMarkupGenericFontName valueOf(CFString value) {
        for (CMTextMarkupGenericFontName v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMTextMarkupGenericFontName/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Default", optional=true)
    protected static native CFString DefaultValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Serif", optional=true)
    protected static native CFString SerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_SansSerif", optional=true)
    protected static native CFString SansSerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Monospace", optional=true)
    protected static native CFString MonospaceValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSerif", optional=true)
    protected static native CFString ProportionalSerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSansSerif", optional=true)
    protected static native CFString ProportionalSansSerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSerif", optional=true)
    protected static native CFString MonospaceSerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSansSerif", optional=true)
    protected static native CFString MonospaceSansSerifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Casual", optional=true)
    protected static native CFString CasualValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Cursive", optional=true)
    protected static native CFString CursiveValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Fantasy", optional=true)
    protected static native CFString FantasyValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_SmallCapital", optional=true)
    protected static native CFString SmallCapitalValue();
    /*</methods>*/
}
