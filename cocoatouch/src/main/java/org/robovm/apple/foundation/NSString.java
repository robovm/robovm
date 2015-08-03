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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass @WeaklyLinked/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSString/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSPropertyList/*</implements>*/ {

    /*<ptr>*/public static class NSStringPtr extends Ptr<NSString, NSStringPtr> {}/*</ptr>*/

    public static class AsStringMarshaler {
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            if (handle != 0) {
                long length = length(handle, sel_length);
                if (length == 0) {
                    return EMPTY_STRING;
                }
                char[] chars = new char[(int) length];
                getCharacters(handle, sel_getCharacters, VM.getArrayValuesAddress(chars), new NSRange(0, length));
                return VM.newStringNoCopy(chars, 0, (int) length);
            }
            return null;
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            if (o == null) {
                return 0L;
            }
            long handle = create(getChars(o), o.length());
            // retainCount is now 1
            retain(handle); // Make sure the retainCount is 1 when we exit this try block
            // retainCount is now 2
            if ((flags & MarshalerFlags.CALL_TYPE_CALLBACK) > 0) {
                // NSStrings returned by callbacks should be autoreleased
                autorelease(handle);
            }
            return handle; // retainCount is 1 after the return
        }
        @AfterBridgeCall
        public static void afterJavaToNative(String before, long after, long flags) {
            if (after != 0) {
                // after is the handle of the NSString returned by toNative().
                // We've already disposed the Java peer so we have to release the
                // ObjC NSString now.
                release(after);
            }
        }
    }
    
    private static final String EMPTY_STRING = "";
    private static final long STRING_VALUE_OFFSET;    
    private static final long STRING_OFFSET_OFFSET;   
    private static final Selector sel_length = Selector.register("length");
    private static final Selector sel_getCharacters = Selector.register("getCharacters:range:");
    /*<bind>*/static { ObjCRuntime.bind(NSString.class); }/*</bind>*/
    static {
        try {
            STRING_VALUE_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("value")));
            STRING_OFFSET_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("offset")));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
    /*<constants>*//*</constants>*/
    
    public NSString(String s) {
        super((SkipInit) null);
        initObject(init(getChars(s), s.length()));
    }
    
    /*<constructors>*/
    public NSString() {}
    protected NSString(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "length")
    protected native @MachineSizedUInt long length();
    /*</properties>*/
    /*<members>*//*</members>*/

    protected static long getChars(String s) {
        int offset = VM.getInt(VM.getObjectAddress(s) + STRING_OFFSET_OFFSET);
        char[] value = (char[]) VM.getObject(VM.getObjectAddress(s) + STRING_VALUE_OFFSET);
        return VM.getArrayValuesAddress(value) + (offset << 1);
    }
    
    @Override
    public String toString() {
        int len = (int) length();
        if (len == 0) {
            return EMPTY_STRING;
        }
        char[] chars = new char[len];
        getCharacters(VM.getArrayValuesAddress(chars), new NSRange(0, len));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    @Bridge protected static native @MachineSizedUInt long length(@Pointer long handle, Selector sel);
    @Bridge protected static native void getCharacters(@Pointer long handle, Selector sel, @Pointer long buffer, @ByVal NSRange aRange);

    public static String getLocalizedString(String key) {
        return NSBundle.getMainBundle().getLocalizedString(key, "", null);
    }
    
    /* UIKit extensions */
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize getSize(UIFont font) {
        return NSStringExtensions.getSize(this.getHandle(), font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize getSize(UIFont font, @MachineSizedFloat double width, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this.getHandle(), font, width, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(CGPoint point, UIFont font) {
        return NSStringExtensions.draw(this.getHandle(), point, font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(CGPoint point, @MachineSizedFloat double width, UIFont font, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.draw(this.getHandle(), point, width, font, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize getSize(UIFont font, @ByVal CGSize size) {
        return NSStringExtensions.getSize(this.getHandle(), font, size);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize getSize(UIFont font, @ByVal CGSize size, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this.getHandle(), font, size, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(@ByVal CGRect rect, UIFont font) {
        return NSStringExtensions.draw(this.getHandle(), rect, font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(NSString thiz, @ByVal CGRect rect, UIFont font, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.draw(this.getHandle(), rect, font, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(CGRect rect, UIFont font, NSLineBreakMode lineBreakMode, NSTextAlignment alignment) {
        return NSStringExtensions.draw(this.getHandle(), rect, font, lineBreakMode, alignment);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize getSize(UIFont font, @MachineSizedFloat double minFontSize, @MachineSizedFloat double width, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this.getHandle(), font, minFontSize, null, width, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @WeaklyLinked
    public CGSize draw(CGPoint point, @MachineSizedFloat double width, UIFont font, @MachineSizedFloat double fontSize, NSLineBreakMode lineBreakMode, UIBaselineAdjustment baselineAdjustment) {
        return NSStringExtensions.draw(this.getHandle(), point, width, font, fontSize, lineBreakMode, baselineAdjustment);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGSize getSize(NSDictionary<NSString, NSObject> attrs) {
        return NSStringExtensions.getSize(this.getHandle(), attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGSize getSize(NSAttributedStringAttributes attrs) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        return NSStringExtensions.getSize(this.getHandle(), attrs.getDictionary());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGSize getSize(CMTextMarkupAttributes attrs) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        return NSStringExtensions.getSize(this.getHandle(), attrs.getDictionary().as(NSDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGSize getSize(CTAttributedStringAttributes attrs) {
        if (attrs == null) {
            throw new NullPointerException("attrs");
        }
        return NSStringExtensions.getSize(this.getHandle(), attrs.getDictionary().as(NSDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGSize getSize(String str, NSDictionary<NSString, NSObject> attrs) {
        return NSStringExtensions.getSize(create(getChars(str), str.length()), attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGSize getSize(String str, NSAttributedStringAttributes attrs) {
        return NSStringExtensions.getSize(create(getChars(str), str.length()), attrs.getDictionary());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGSize getSize(String str, CMTextMarkupAttributes attrs) {
        return NSStringExtensions.getSize(create(getChars(str), str.length()), attrs.getDictionary().as(NSDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGSize getSize(String str, CTAttributedStringAttributes attrs) {
        return NSStringExtensions.getSize(create(getChars(str), str.length()), attrs.getDictionary().as(NSDictionary.class));
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGPoint point, NSDictionary<NSString, NSObject> attrs) {
        NSStringExtensions.draw(this.getHandle(), point, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGPoint point, NSAttributedStringAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), point, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), point, attrs.getDictionary());
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGPoint point, CMTextMarkupAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), point, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), point, attrs.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGPoint point, CTAttributedStringAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), point, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), point, attrs.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGPoint point, NSDictionary<NSString, NSObject> attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), point, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGPoint point, NSAttributedStringAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), point, attrs.getDictionary());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGPoint point, CMTextMarkupAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), point, attrs.getDictionary().as(NSDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGPoint point, CTAttributedStringAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), point, attrs.getDictionary().as(NSDictionary.class));
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSDictionary<NSString, NSObject> attrs) {
        NSStringExtensions.draw(this.getHandle(), rect, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSAttributedStringAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), rect, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, attrs.getDictionary());
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, CMTextMarkupAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), rect, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, attrs.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, CTAttributedStringAttributes attrs) {
        if (attrs == null) {
            NSStringExtensions.draw(this.getHandle(), rect, (NSDictionary<NSString, NSObject>)null);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, attrs.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSDictionary<NSString, NSObject> attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSAttributedStringAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, attrs.getDictionary());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, CMTextMarkupAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, attrs.getDictionary().as(NSDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, CTAttributedStringAttributes attrs) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, attrs.getDictionary().as(NSDictionary.class));
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSStringDrawingOptions options, NSDictionary<NSString, NSObject> attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(this.getHandle(), rect, options, attributes, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            NSStringExtensions.draw(this.getHandle(), rect, options, null, context);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, options, attributes.getDictionary(), context);
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSStringDrawingOptions options, CMTextMarkupAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            NSStringExtensions.draw(this.getHandle(), rect, options, null, context);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, options, attributes.getDictionary().as(NSDictionary.class), context);
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void draw(CGRect rect, NSStringDrawingOptions options, CTAttributedStringAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            NSStringExtensions.draw(this.getHandle(), rect, options, null, context);
        } else {
            NSStringExtensions.draw(this.getHandle(), rect, options, attributes.getDictionary().as(NSDictionary.class), context);
        }
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSStringDrawingOptions options, NSDictionary<NSString, NSObject> attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, options, attributes, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, options, attributes.getDictionary(), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSStringDrawingOptions options, CMTextMarkupAttributes attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void draw(String str, CGRect rect, NSStringDrawingOptions options, CTAttributedStringAttributes attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(create(getChars(str), str.length()), rect, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGRect getBoundingRect(CGSize size, NSStringDrawingOptions options, NSDictionary<NSString, NSObject> attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, attributes, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGRect getBoundingRect(CGSize size, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, null, context);
        }
        return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, attributes.getDictionary(), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGRect getBoundingRect(CGSize size, NSStringDrawingOptions options, CMTextMarkupAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, null, context);
        }
        return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public CGRect getBoundingRect(CGSize size, NSStringDrawingOptions options, CTAttributedStringAttributes attributes, NSStringDrawingContext context) {
        if (attributes == null) {
            return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, null, context);
        }
        return NSStringExtensions.getBoundingRect(this.getHandle(), size, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGRect getBoundingRect(String str, CGSize size, NSStringDrawingOptions options, NSDictionary<NSString, NSObject> attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(create(getChars(str), str.length()), size, options, attributes, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGRect getBoundingRect(String str, CGSize size, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(create(getChars(str), str.length()), size, options, attributes.getDictionary(), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGRect getBoundingRect(String str, CGSize size, NSStringDrawingOptions options, CMTextMarkupAttributes attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(create(getChars(str), str.length()), size, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static CGRect getBoundingRect(String str, CGSize size, NSStringDrawingOptions options, CTAttributedStringAttributes attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(create(getChars(str), str.length()), size, options, attributes.getDictionary().as(NSDictionary.class), context);
    }
    
    /* Convenience methods */
    public static String readFile(File path, NSStringEncoding enc) throws NSErrorException {
        return readFile(path.getAbsolutePath(), enc);
    }
    
    private static final Selector sel_hasPrefix$ = Selector.register("hasPrefix:");
    @Bridge protected static native String hasPrefix(@Pointer long handle, Selector sel, String prefix);
    public static String hasPrefix(String str, String prefix) {
        long handle = create(getChars(str), str.length());
        return hasPrefix(handle, sel_hasPrefix$, prefix);
    }
    
    private static final Selector sel_hasSuffix$ = Selector.register("hasSuffix:");
    @Bridge protected static native String hasSuffix(@Pointer long handle, Selector sel, String suffix);
    public static String hasSuffix(String str, String suffix) {
        long handle = create(getChars(str), str.length());
        return hasSuffix(handle, sel_hasSuffix$, suffix);
    }
    
    private static final Selector sel_dataUsingEncoding$allowLossyConversion$ = Selector.register("dataUsingEncoding:allowLossyConversion:");
    @Bridge protected static native NSData toData(@Pointer long handle, Selector sel, NSStringEncoding encoding, boolean lossy);
    public static NSData toData(String str, NSStringEncoding encoding, boolean lossy) {
        long handle = create(getChars(str), str.length());
        return toData(handle, sel_dataUsingEncoding$allowLossyConversion$, encoding, lossy);
    }
    
    private static final Selector sel_dataUsingEncoding$ = Selector.register("dataUsingEncoding:");
    @Bridge protected static native NSData toData(@Pointer long handle, Selector sel, NSStringEncoding encoding);
    public static NSData toData(String str, NSStringEncoding encoding) {
        long handle = create(getChars(str), str.length());
        return toData(handle, sel_dataUsingEncoding$, encoding);
    }
    
    private static final Selector sel_stringByAppendingPathComponent$ = Selector.register("stringByAppendingPathComponent:");
    @Bridge protected static native String appendPathComponent(@Pointer long handle, Selector sel, String component);
    public static String appendPathComponent(String str, String component) {
        long handle = create(getChars(str), str.length());
        return appendPathComponent(handle, sel_stringByAppendingPathComponent$, component);
    }
    
    private static final Selector sel_stringByAppendingPathExtension$ = Selector.register("stringByAppendingPathExtension:");
    @Bridge protected static native String appendPathExtension(@Pointer long handle, Selector sel, String extension);
    public static String appendPathExtension(String str, String extension) {
        long handle = create(getChars(str), str.length());
        return appendPathExtension(handle, sel_stringByAppendingPathExtension$, extension);
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    private static final Selector sel_stringByAddingPercentEncodingWithAllowedCharacters$ = Selector.register("stringByAddingPercentEncodingWithAllowedCharacters:");
    @Bridge protected static native String addPercentEncoding(@Pointer long handle, Selector sel, NSCharacterSet allowedCharacters);
    public static String addPercentEncoding(String str, NSCharacterSet allowedCharacters) {
        long handle = create(getChars(str), str.length());
        return addPercentEncoding(handle, sel_stringByAddingPercentEncodingWithAllowedCharacters$, allowedCharacters);
    }
    
    private static final Selector sel_stringByAddingPercentEscapesUsingEncoding$ = Selector.register("stringByAddingPercentEscapesUsingEncoding:");
    @Bridge protected static native String addPercentEscapes(@Pointer long handle, Selector sel, NSStringEncoding encoding);
    public static String addPercentEscapes(String str, NSStringEncoding encoding) {
        long handle = create(getChars(str), str.length());
        return addPercentEscapes(handle, sel_stringByAddingPercentEscapesUsingEncoding$, encoding);
    }
    
    private static final Selector sel_stringByReplacingPercentEscapesUsingEncoding$ = Selector.register("stringByReplacingPercentEscapesUsingEncoding:");
    @Bridge protected static native String replacePercentEscapes(@Pointer long handle, Selector sel, NSStringEncoding encoding);
    public static String replacePercentEscapes(String str, NSStringEncoding encoding) {
        long handle = create(getChars(str), str.length());
        return replacePercentEscapes(handle, sel_stringByReplacingPercentEscapesUsingEncoding$, encoding);
    }
    
    private static final Selector sel_rangeOfString$ = Selector.register("rangeOfString:");
    @Bridge protected static native @ByVal NSRange rangeOf(@Pointer long handle, Selector sel, String string);
    public static NSRange rangeOf(String str, String search) {
        long handle = create(getChars(str), str.length());
        return rangeOf(handle, sel_rangeOfString$, search);
    }
    
    
    
    /*<methods>*/
    @Method(selector = "characterAtIndex:")
    protected native short getCharactersAt(@MachineSizedUInt long index);
    @Method(selector = "getCharacters:range:")
    protected native void getCharacters(@Pointer long buffer, @ByVal NSRange aRange);
    @Method(selector = "hasPrefix:")
    public native boolean hasPrefix(String aString);
    @Method(selector = "hasSuffix:")
    public native boolean hasSuffix(String aString);
    @Method(selector = "dataUsingEncoding:allowLossyConversion:")
    public native NSData toData(NSStringEncoding encoding, boolean lossy);
    @Method(selector = "dataUsingEncoding:")
    public native NSData toData(NSStringEncoding encoding);
    @Method(selector = "initWithCharacters:length:")
    protected native @Pointer long init(@Pointer long characters, @MachineSizedUInt long length);
    public boolean writeURL(NSURL url, boolean atomically, NSStringEncoding enc) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = writeURL(url, atomically, enc, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "writeToURL:atomically:encoding:error:")
    private native boolean writeURL(NSURL url, boolean atomically, NSStringEncoding enc, NSError.NSErrorPtr error);
    public boolean writeFile(String path, boolean atomically, NSStringEncoding enc) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = writeFile(path, atomically, enc, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "writeToFile:atomically:encoding:error:")
    private native boolean writeFile(String path, boolean atomically, NSStringEncoding enc, NSError.NSErrorPtr error);
    @Method(selector = "stringWithCharacters:length:")
    protected static native @Pointer long create(@Pointer long characters, @MachineSizedUInt long length);
    public static String readURL(NSURL url, NSStringEncoding enc) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       String result = readURL(url, enc, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "stringWithContentsOfURL:encoding:error:")
    private static native String readURL(NSURL url, NSStringEncoding enc, NSError.NSErrorPtr error);
    public static String readFile(String path, NSStringEncoding enc) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       String result = readFile(path, enc, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "stringWithContentsOfFile:encoding:error:")
    private static native String readFile(String path, NSStringEncoding enc, NSError.NSErrorPtr error);
    @Method(selector = "stringByAppendingPathComponent:")
    public native String appendPathComponent(String str);
    @Method(selector = "stringByAppendingPathExtension:")
    public native String appendPathExtension(String str);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "stringByAddingPercentEncodingWithAllowedCharacters:")
    public native String addPercentEncoding(NSCharacterSet allowedCharacters);
    @Method(selector = "stringByAddingPercentEscapesUsingEncoding:")
    public native String addPercentEscapes(NSStringEncoding enc);
    @Method(selector = "stringByReplacingPercentEscapesUsingEncoding:")
    public native String replacePercentEscapes(NSStringEncoding enc);
    /*</methods>*/
}
