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
@Marshaler(CMTextMarkupCharacterEdgeStyle.Marshaler.class)
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextMarkupCharacterEdgeStyle/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CMTextMarkupCharacterEdgeStyle toObject(Class<CMTextMarkupCharacterEdgeStyle> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMTextMarkupCharacterEdgeStyle.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextMarkupCharacterEdgeStyle o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTextMarkupCharacterEdgeStyle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupCharacterEdgeStyle None = new CMTextMarkupCharacterEdgeStyle("NoneValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupCharacterEdgeStyle Raised = new CMTextMarkupCharacterEdgeStyle("RaisedValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupCharacterEdgeStyle Depressed = new CMTextMarkupCharacterEdgeStyle("DepressedValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupCharacterEdgeStyle Uniform = new CMTextMarkupCharacterEdgeStyle("UniformValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupCharacterEdgeStyle DropShadow = new CMTextMarkupCharacterEdgeStyle("DropShadowValue");
    
    private static CMTextMarkupCharacterEdgeStyle[] values = new CMTextMarkupCharacterEdgeStyle[] {None, Raised, Depressed, 
        Uniform, DropShadow};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CMTextMarkupCharacterEdgeStyle(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CMTextMarkupCharacterEdgeStyle valueOf(CFString value) {
        for (CMTextMarkupCharacterEdgeStyle v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMTextMarkupCharacterEdgeStyle/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_None", optional=true)
    protected static native CFString NoneValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Raised", optional=true)
    protected static native CFString RaisedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Depressed", optional=true)
    protected static native CFString DepressedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Uniform", optional=true)
    protected static native CFString UniformValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_DropShadow", optional=true)
    protected static native CFString DropShadowValue();
    /*</methods>*/
}
