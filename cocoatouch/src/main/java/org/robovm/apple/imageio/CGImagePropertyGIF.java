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
package org.robovm.apple.imageio;

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
@Marshaler(CGImagePropertyGIF.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyGIF/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyGIF toObject(Class<CGImagePropertyGIF> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyGIF.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyGIF o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyGIF.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGIF LoopCount = new CGImagePropertyGIF("LoopCountKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGIF DelayTime = new CGImagePropertyGIF("DelayTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGIF ImageColorMap = new CGImagePropertyGIF("ImageColorMapKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGIF HasGlobalColorMap = new CGImagePropertyGIF("HasGlobalColorMapKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGIF UnclampedDelayTime = new CGImagePropertyGIF("UnclampedDelayTimeKey");
    
    private static CGImagePropertyGIF[] values = new CGImagePropertyGIF[] {LoopCount, DelayTime, ImageColorMap, 
        HasGlobalColorMap, UnclampedDelayTime};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyGIF(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyGIF valueOf(CFString value) {
        for (CGImagePropertyGIF v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyGIF/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFLoopCount", optional=true)
    protected static native CFString LoopCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFDelayTime", optional=true)
    protected static native CFString DelayTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFImageColorMap", optional=true)
    protected static native CFString ImageColorMapKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFHasGlobalColorMap", optional=true)
    protected static native CFString HasGlobalColorMapKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFUnclampedDelayTime", optional=true)
    protected static native CFString UnclampedDelayTimeKey();
    /*</methods>*/
}
