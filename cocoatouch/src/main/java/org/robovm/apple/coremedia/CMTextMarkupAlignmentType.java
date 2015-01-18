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
@Marshaler(CMTextMarkupAlignmentType.Marshaler.class)
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextMarkupAlignmentType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextMarkupAlignmentType toObject(Class<CMTextMarkupAlignmentType> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMTextMarkupAlignmentType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextMarkupAlignmentType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTextMarkupAlignmentType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupAlignmentType Start = new CMTextMarkupAlignmentType("StartValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupAlignmentType Middle = new CMTextMarkupAlignmentType("MiddleValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupAlignmentType End = new CMTextMarkupAlignmentType("EndValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupAlignmentType Left = new CMTextMarkupAlignmentType("LeftValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CMTextMarkupAlignmentType Right = new CMTextMarkupAlignmentType("RightValue");
    
    private static final CMTextMarkupAlignmentType[] values = new CMTextMarkupAlignmentType[] {Start, Middle, End, Left, Right};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CMTextMarkupAlignmentType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CMTextMarkupAlignmentType valueOf(CFString value) {
        for (CMTextMarkupAlignmentType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMTextMarkupAlignmentType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Start", optional=true)
    protected static native CFString StartValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Middle", optional=true)
    protected static native CFString MiddleValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_End", optional=true)
    protected static native CFString EndValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Left", optional=true)
    protected static native CFString LeftValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Right", optional=true)
    protected static native CFString RightValue();
    /*</methods>*/
}
