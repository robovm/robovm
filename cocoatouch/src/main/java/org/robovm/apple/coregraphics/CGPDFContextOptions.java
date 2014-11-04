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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGPDFContextOptions.Marshaler.class)
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFContextOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    private CGPDFContextOptions(CFDictionary data) {
        this.data = data;
    }
    public CGPDFContextOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGPDFContextOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (data.containsKey(TitleKey())) {
            CFString val = data.get(TitleKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setTitle(String title) {
        data.put(TitleKey(), new CFString(title));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getAuthor() {
        if (data.containsKey(AuthorKey())) {
            CFString val = data.get(AuthorKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAuthor(String author) {
        data.put(AuthorKey(), new CFString(author));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getSubject() {
        if (data.containsKey(SubjectKey())) {
            CFString val = data.get(SubjectKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setSubject(String subject) {
        data.put(SubjectKey(), new CFString(subject));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getKeywords() {
        if (data.containsKey(KeywordsKey())) {
            CFString val = data.get(KeywordsKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setKeywoards(String keywords) {
        data.put(KeywordsKey(), new CFString(keywords));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setKeywords(String...keywords) {
        CFArray list = CFMutableArray.create();
        for (String keyword : keywords) {
            list.add(new CFString(keyword));
        }
        data.put(KeywordsKey(), list);
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getCreator() {
        if (data.containsKey(CreatorKey())) {
            CFString val = data.get(CreatorKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setCreator(String creator) {
        data.put(CreatorKey(), new CFString(creator));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getOwnerPassword() {
        if (data.containsKey(OwnerPasswordKey())) {
            CFString val = data.get(OwnerPasswordKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setOwnerPassword(String password) {
        data.put(OwnerPasswordKey(), new CFString(password));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUserPassword() {
        if (data.containsKey(UserPasswordKey())) {
            CFString val = data.get(UserPasswordKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setUserPassword(String password) {
        data.put(UserPasswordKey(), new CFString(password));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getEncryptionKeyLength() {
        if (data.containsKey(EncryptionKeyLengthKey())) {
            CFNumber val = data.get(EncryptionKeyLengthKey(), CFNumber.class);
            return val.intValue();
        }
        return 40;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setEncryptionKeyLength(int keyLength) {
        data.put(EncryptionKeyLengthKey(), CFNumber.valueOf(keyLength));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAllowsPrinting() {
        if (data.containsKey(AllowsPrintingKey())) {
            CFBoolean val = data.get(AllowsPrintingKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return true;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAllowsPrinting(boolean allowsPrinting) {
        data.put(AllowsPrintingKey(), CFBoolean.valueOf(allowsPrinting));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAllowsCopying() {
        if (data.containsKey(AllowsCopyingKey())) {
            CFBoolean val = data.get(AllowsCopyingKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return true;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFContextOptions setAllowsCopying(boolean allowsCopying) {
        data.put(AllowsCopyingKey(), CFBoolean.valueOf(allowsCopying));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextTitle", optional=true)
    protected static native CFString TitleKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextAuthor", optional=true)
    protected static native CFString AuthorKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextSubject", optional=true)
    protected static native CFString SubjectKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextKeywords", optional=true)
    protected static native CFString KeywordsKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextCreator", optional=true)
    protected static native CFString CreatorKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextOwnerPassword", optional=true)
    protected static native CFString OwnerPasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextUserPassword", optional=true)
    protected static native CFString UserPasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextEncryptionKeyLength", optional=true)
    protected static native CFString EncryptionKeyLengthKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextAllowsPrinting", optional=true)
    protected static native CFString AllowsPrintingKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextAllowsCopying", optional=true)
    protected static native CFString AllowsCopyingKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
