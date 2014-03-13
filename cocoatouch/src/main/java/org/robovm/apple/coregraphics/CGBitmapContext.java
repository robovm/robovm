/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGBitmapContext/*</name>*/ 
    extends /*<extends>*/CGContext/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGBitmapContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public static CGBitmapContext create(long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo) {
        return create(null, width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo);
    }
    
    /*<methods>*/
    @Bridge(symbol="CGBitmapContextCreateWithData")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGBitmapContext create(VoidPtr data, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, FunctionPtr releaseCallback, VoidPtr releaseInfo);
    @Bridge(symbol="CGBitmapContextCreate")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGBitmapContext create(VoidPtr data, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo);
    @Bridge(symbol="CGBitmapContextGetData")
    public native VoidPtr getData();
    @Bridge(symbol="CGBitmapContextGetWidth")
    public native @MachineSizedUInt long getWidth();
    @Bridge(symbol="CGBitmapContextGetHeight")
    public native @MachineSizedUInt long getHeight();
    @Bridge(symbol="CGBitmapContextGetBitsPerComponent")
    public native @MachineSizedUInt long getBitsPerComponent();
    @Bridge(symbol="CGBitmapContextGetBitsPerPixel")
    public native @MachineSizedUInt long getBitsPerPixel();
    @Bridge(symbol="CGBitmapContextGetBytesPerRow")
    public native @MachineSizedUInt long getBytesPerRow();
    @Bridge(symbol="CGBitmapContextGetColorSpace")
    public native CGColorSpace getColorSpace();
    @Bridge(symbol="CGBitmapContextGetAlphaInfo")
    public native CGImageAlphaInfo getAlphaInfo();
    @Bridge(symbol="CGBitmapContextGetBitmapInfo")
    public native CGBitmapInfo getBitmapInfo();
    @Bridge(symbol="CGBitmapContextCreateImage")
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage toImage();
    /*</methods>*/
}
