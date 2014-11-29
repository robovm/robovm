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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFormat/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CIFormat.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CIFormat ARGB8 = new CIFormat("ARGB8Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFormat BGRA8 = new CIFormat("BGRA8Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFormat RGBA8 = new CIFormat("RGBA8Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CIFormat RGBAf = new CIFormat("RGBAfValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CIFormat RGBAh = new CIFormat("RGBAhValue");
    
    private static CIFormat[] values = new CIFormat[] {BGRA8, RGBA8, RGBAh, ARGB8, RGBAf};
    private final LazyGlobalValue<Integer> lazyGlobalValue;
    
    private CIFormat(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public int value() {
        return lazyGlobalValue.value();
    }
    
    public static CIFormat valueOf(int value) {
        for (CIFormat v : values) {
            if (v.value() == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFormat/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIFormatARGB8", optional=true)
    protected static native int ARGB8Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIFormatBGRA8", optional=true)
    protected static native int BGRA8Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIFormatRGBA8", optional=true)
    protected static native int RGBA8Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCIFormatRGBAf", optional=true)
    protected static native int RGBAfValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCIFormatRGBAh", optional=true)
    protected static native int RGBAhValue();
    /*</methods>*/
}
