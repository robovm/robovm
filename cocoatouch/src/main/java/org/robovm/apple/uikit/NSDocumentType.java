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
@Marshaler(/*<name>*/NSDocumentType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDocumentType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSDocumentType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSDocumentType toObject(Class<NSDocumentType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSDocumentType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSDocumentType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSDocumentType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSDocumentType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSDocumentType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSDocumentType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSDocumentType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSDocumentType Plain = new NSDocumentType("Plain");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSDocumentType RTF = new NSDocumentType("RTF");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSDocumentType RTFD = new NSDocumentType("RTFD");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSDocumentType HTML = new NSDocumentType("HTML");
    /*</constants>*/
    
    private static /*<name>*/NSDocumentType/*</name>*/[] values = new /*<name>*/NSDocumentType/*</name>*/[] {/*<value_list>*/Plain, RTF, RTFD, HTML/*</value_list>*/};
    
    /*<name>*/NSDocumentType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSDocumentType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSDocumentType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSDocumentType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("UIKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSPlainTextDocumentType", optional=true)
        public static native NSString Plain();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSRTFTextDocumentType", optional=true)
        public static native NSString RTF();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSRTFDTextDocumentType", optional=true)
        public static native NSString RTFD();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSHTMLTextDocumentType", optional=true)
        public static native NSString HTML();
        /*</values>*/
    }
}
