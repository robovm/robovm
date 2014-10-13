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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UILayoutFittingSize.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UILayoutFittingSize/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static UILayoutFittingSize toObject(Class<UILayoutFittingSize> cls, long handle, long flags) {
            CGSize o = (CGSize) Struct.Marshaler.toObject(CGSize.class, handle, flags);
            if (o == null) {
                return null;
            }
            return UILayoutFittingSize.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(UILayoutFittingSize o, long flags) {
            if (o == null) {
                return 0L;
            }
            return Struct.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UILayoutFittingSize.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UILayoutFittingSize Compressed = new UILayoutFittingSize("CompressedValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UILayoutFittingSize Expanded = new UILayoutFittingSize("ExpandedValue");
    private static UILayoutFittingSize[] values = new UILayoutFittingSize[] {Compressed, Expanded};
    
    private final LazyGlobalValue<CGSize> lazyGlobalValue;
    
    private UILayoutFittingSize(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public @ByVal CGSize value() {
        return lazyGlobalValue.value();
    }
    
    public static UILayoutFittingSize valueOf(@ByVal CGSize value) {
        for (UILayoutFittingSize v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UILayoutFittingSize/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingCompressedSize", optional=true)
    protected static native @ByVal CGSize CompressedValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UILayoutFittingExpandedSize", optional=true)
    protected static native @ByVal CGSize ExpandedValue();
    /*</methods>*/
}
