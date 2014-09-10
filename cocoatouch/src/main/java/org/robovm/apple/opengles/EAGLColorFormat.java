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
package org.robovm.apple.opengles;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("OpenGLES")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EAGLColorFormat/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(EAGLColorFormat.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final EAGLColorFormat RGBA8 = new EAGLColorFormat("RGBA8Value");
    public static final EAGLColorFormat RGB565 = new EAGLColorFormat("RGB565Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final EAGLColorFormat SRGBA8 = new EAGLColorFormat("SRGBA8Value");
    private static EAGLColorFormat[] values = new EAGLColorFormat[] {RGBA8, RGB565, SRGBA8};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private EAGLColorFormat(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static EAGLColorFormat valueOf(NSString value) {
        for (EAGLColorFormat v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/EAGLColorFormat/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kEAGLColorFormatRGBA8", optional=true)
    protected static native NSString RGBA8Value();
    @GlobalValue(symbol="kEAGLColorFormatRGB565", optional=true)
    protected static native NSString RGB565Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kEAGLColorFormatSRGBA8", optional=true)
    protected static native NSString SRGBA8Value();
    /*</methods>*/
}
