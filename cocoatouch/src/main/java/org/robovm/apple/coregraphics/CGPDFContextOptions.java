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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
@Marshaler(/*<name>*/CGPDFContextOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFContextOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGPDFContextOptions toObject(Class<CGPDFContextOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGPDFContextOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CGPDFContextOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGPDFContextOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGPDFContextOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGPDFContextOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGPDFContextOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGPDFContextOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGPDFContextOptions(CFDictionary data) {
        super(data);
    }
    public CGPDFContextOptions() {}
    /*</constructors>*/

    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setKeywords(String...keywords) {
        CFArray list = CFMutableArray.create();
        for (String keyword : keywords) {
            list.add(new CFString(keyword));
        }
        set(Keys.Keywords(), list);
        return this;
    }
    
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
    public CGPDFContextOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (has(Keys.Title())) {
            CFString val = get(Keys.Title(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setTitle(String title) {
        set(Keys.Title(), new CFString(title));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getAuthor() {
        if (has(Keys.Author())) {
            CFString val = get(Keys.Author(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAuthor(String author) {
        set(Keys.Author(), new CFString(author));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getSubject() {
        if (has(Keys.Subject())) {
            CFString val = get(Keys.Subject(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setSubject(String subject) {
        set(Keys.Subject(), new CFString(subject));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getKeywords() {
        if (has(Keys.Keywords())) {
            CFString val = get(Keys.Keywords(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setKeywords(String keywords) {
        set(Keys.Keywords(), new CFString(keywords));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getCreator() {
        if (has(Keys.Creator())) {
            CFString val = get(Keys.Creator(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setCreator(String creator) {
        set(Keys.Creator(), new CFString(creator));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getOwnerPassword() {
        if (has(Keys.OwnerPassword())) {
            CFString val = get(Keys.OwnerPassword(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setOwnerPassword(String ownerPassword) {
        set(Keys.OwnerPassword(), new CFString(ownerPassword));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUserPassword() {
        if (has(Keys.UserPassword())) {
            CFString val = get(Keys.UserPassword(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setUserPassword(String userPassword) {
        set(Keys.UserPassword(), new CFString(userPassword));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getEncryptionKeyLength() {
        if (has(Keys.EncryptionKeyLength())) {
            CFNumber val = get(Keys.EncryptionKeyLength(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setEncryptionKeyLength(int encryptionKeyLength) {
        set(Keys.EncryptionKeyLength(), CFNumber.valueOf(encryptionKeyLength));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean allowsPrinting() {
        if (has(Keys.AllowsPrinting())) {
            CFBoolean val = get(Keys.AllowsPrinting(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAllowsPrinting(boolean allowsPrinting) {
        set(Keys.AllowsPrinting(), CFBoolean.valueOf(allowsPrinting));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean allowsCopying() {
        if (has(Keys.AllowsCopying())) {
            CFBoolean val = get(Keys.AllowsCopying(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAllowsCopying(boolean allowsCopying) {
        set(Keys.AllowsCopying(), CFBoolean.valueOf(allowsCopying));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreGraphics")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextTitle", optional=true)
        public static native CFString Title();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextAuthor", optional=true)
        public static native CFString Author();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextSubject", optional=true)
        public static native CFString Subject();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextKeywords", optional=true)
        public static native CFString Keywords();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextCreator", optional=true)
        public static native CFString Creator();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextOwnerPassword", optional=true)
        public static native CFString OwnerPassword();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextUserPassword", optional=true)
        public static native CFString UserPassword();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextEncryptionKeyLength", optional=true)
        public static native CFString EncryptionKeyLength();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextAllowsPrinting", optional=true)
        public static native CFString AllowsPrinting();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextAllowsCopying", optional=true)
        public static native CFString AllowsCopying();
    }
    /*</keys>*/
}
