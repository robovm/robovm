/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
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
/*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/UILayoutFittingSize/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UILayoutFittingSize/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CGSize>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/UILayoutFittingSize/*</name>*/.class); }

    /*<marshalers>*/
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
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UILayoutFittingSize Compressed = new UILayoutFittingSize("Compressed");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final UILayoutFittingSize Expanded = new UILayoutFittingSize("Expanded");
    /*</constants>*/
    
    private static /*<name>*/UILayoutFittingSize/*</name>*/[] values = new /*<name>*/UILayoutFittingSize/*</name>*/[] {/*<value_list>*/Compressed, Expanded/*</value_list>*/};
    
    /*<name>*/UILayoutFittingSize/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/UILayoutFittingSize/*</name>*/ valueOf(/*<type>*/@ByVal CGSize/*</type>*/ value) {
        for (/*<name>*/UILayoutFittingSize/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UILayoutFittingSize/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="UILayoutFittingCompressedSize", optional=true)
        public static native @ByVal CGSize Compressed();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="UILayoutFittingExpandedSize", optional=true)
        public static native @ByVal CGSize Expanded();
        /*</values>*/
    }
}
