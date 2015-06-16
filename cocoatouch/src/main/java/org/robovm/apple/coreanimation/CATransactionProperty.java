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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CATransactionProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CATransactionProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CATransactionProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CATransactionProperty toObject(Class<CATransactionProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CATransactionProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CATransactionProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CATransactionProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CATransactionProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CATransactionProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CATransactionProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CATransactionProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransactionProperty AnimationDuration = new CATransactionProperty("AnimationDuration");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CATransactionProperty DisableActions = new CATransactionProperty("DisableActions");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CATransactionProperty AnimationTimingFunction = new CATransactionProperty("AnimationTimingFunction");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CATransactionProperty CompletionBlock = new CATransactionProperty("CompletionBlock");
    /*</constants>*/
    
    private static /*<name>*/CATransactionProperty/*</name>*/[] values = new /*<name>*/CATransactionProperty/*</name>*/[] {/*<value_list>*/AnimationDuration, DisableActions, AnimationTimingFunction, CompletionBlock/*</value_list>*/};
    
    /*<name>*/CATransactionProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CATransactionProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CATransactionProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CATransactionProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("QuartzCore") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransactionAnimationDuration", optional=true)
        public static native NSString AnimationDuration();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCATransactionDisableActions", optional=true)
        public static native NSString DisableActions();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCATransactionAnimationTimingFunction", optional=true)
        public static native NSString AnimationTimingFunction();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCATransactionCompletionBlock", optional=true)
        public static native NSString CompletionBlock();
        /*</values>*/
    }
}
