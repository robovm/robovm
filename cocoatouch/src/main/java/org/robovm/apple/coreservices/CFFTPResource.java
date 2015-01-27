/*
 * Copyright (C) 2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
@Marshaler(/*<name>*/CFFTPResource/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFFTPResource/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFFTPResource toObject(Class<CFFTPResource> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CFFTPResource(o);
        }
        @MarshalsPointer
        public static long toNative(CFFTPResource o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFFTPResource> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFFTPResource> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CFFTPResource(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFFTPResource> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFFTPResource i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CFFTPResource(CFDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getMode() {
        if (has(Keys.Mode())) {
            CFNumber val = get(Keys.Mode(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getName() {
        if (has(Keys.Name())) {
            CFString val = get(Keys.Name(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getOwner() {
        if (has(Keys.Owner())) {
            CFString val = get(Keys.Owner(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getGroup() {
        if (has(Keys.Group())) {
            CFString val = get(Keys.Group(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getLink() {
        if (has(Keys.Link())) {
            CFString val = get(Keys.Link(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getSize() {
        if (has(Keys.Size())) {
            CFNumber val = get(Keys.Size(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public long getType() {
        if (has(Keys.Type())) {
            CFNumber val = get(Keys.Type(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CFNetwork")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceMode", optional=true)
        public static native CFString Mode();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceName", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceOwner", optional=true)
        public static native CFString Owner();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceGroup", optional=true)
        public static native CFString Group();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceLink", optional=true)
        public static native CFString Link();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceSize", optional=true)
        public static native CFString Size();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceType", optional=true)
        public static native CFString Type();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFFTPResourceModDate", optional=true)
        public static native CFString ModDate();
    }
    /*</keys>*/
}
