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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFStreamSocketSOCKSVersion/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFStreamSocketSOCKSVersion/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFStreamSocketSOCKSVersion toObject(Class<CFStreamSocketSOCKSVersion> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFStreamSocketSOCKSVersion.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFStreamSocketSOCKSVersion o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFStreamSocketSOCKSVersion> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFStreamSocketSOCKSVersion> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFStreamSocketSOCKSVersion.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFStreamSocketSOCKSVersion> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFStreamSocketSOCKSVersion o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSOCKSVersion _4 = new CFStreamSocketSOCKSVersion("_4");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSOCKSVersion _5 = new CFStreamSocketSOCKSVersion("_5");
    /*</constants>*/
    
    private static /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/[] values = new /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/[] {/*<value_list>*/_4, _5/*</value_list>*/};
    
    /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFStreamSocketSOCKSVersion/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFStreamSocketSOCKSVersion/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CFNetwork") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSOCKSVersion4", optional=true)
        public static native CFString _4();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSOCKSVersion5", optional=true)
        public static native CFString _5();
        /*</values>*/
    }
}
