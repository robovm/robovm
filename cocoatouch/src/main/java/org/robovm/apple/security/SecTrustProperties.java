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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecTrustProperties/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecTrustProperties/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecTrustProperties toObject(Class<SecTrustProperties> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SecTrustProperties(o);
        }
        @MarshalsPointer
        public static long toNative(SecTrustProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecTrustProperties> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecTrustProperties> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SecTrustProperties(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecTrustProperties> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecTrustProperties i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SecTrustProperties(CFDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFType key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFType key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getTitle() {
        if (has(Keys.Title())) {
            CFString val = get(Keys.Title(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getError() {
        if (has(Keys.Error())) {
            CFString val = get(Keys.Error(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Security")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPropertyTypeTitle", optional=true)
        public static native CFType Title();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPropertyTypeError", optional=true)
        public static native CFType Error();
    }
    /*</keys>*/
}
