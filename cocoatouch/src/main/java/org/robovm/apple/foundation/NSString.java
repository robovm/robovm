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
package org.robovm.apple.foundation;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
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
                getCharacters$range$(handle, sel_getCharacters$range$, VM.getArrayValuesAddress(chars), new NSRange(0, length));
                return VM.newStringNoCopy(chars, 0, (int) length);
            }
            return null;
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            if (o == null) {
                return 0L;
            }
            long handle = stringWithCharacters$length$(getChars(o), o.length());
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
    private static final Selector sel_getCharacters$range$ = Selector.register("getCharacters:range:");
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
        initObject(initWithCharacters$length$(getChars(s), s.length()));
    }
    
    /*<constructors>*/
    public NSString() {}
    protected NSString(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "length")
    public native @MachineSizedUInt long getLength();
    @Property(selector = "doubleValue")
    public native double getDoubleValue();
    @Property(selector = "floatValue")
    public native float getFloatValue();
    @Property(selector = "intValue")
    public native int getIntValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "integerValue")
    public native @MachineSizedSInt long getIntegerValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "longLongValue")
    public native long getLongLongValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "boolValue")
    public native boolean isBoolValue();
    @Property(selector = "uppercaseString")
    public native String getUppercaseString();
    @Property(selector = "lowercaseString")
    public native String getLowercaseString();
    @Property(selector = "capitalizedString")
    public native String getCapitalizedString();
    @Property(selector = "description")
    public native String getDescription();
    @Property(selector = "hash")
    public native @MachineSizedUInt long getHash();
    @Property(selector = "fastestEncoding")
    public native NSStringEncoding getFastestEncoding();
    @Property(selector = "smallestEncoding")
    public native NSStringEncoding getSmallestEncoding();
    @Property(selector = "decomposedStringWithCanonicalMapping")
    public native String getDecomposedStringWithCanonicalMapping();
    @Property(selector = "precomposedStringWithCanonicalMapping")
    public native String getPrecomposedStringWithCanonicalMapping();
    @Property(selector = "decomposedStringWithCompatibilityMapping")
    public native String getDecomposedStringWithCompatibilityMapping();
    @Property(selector = "precomposedStringWithCompatibilityMapping")
    public native String getPrecomposedStringWithCompatibilityMapping();
    @Property(selector = "UTF8String")
    public native BytePtr getUTF8String();
    @Property(selector = "pathComponents")
    public native NSArray<?> getPathComponents();
    @Property(selector = "isAbsolutePath")
    public native boolean isAbsolutePath();
    @Property(selector = "lastPathComponent")
    public native String getLastPathComponent();
    @Property(selector = "stringByDeletingLastPathComponent")
    public native String getStringByDeletingLastPathComponent();
    @Property(selector = "pathExtension")
    public native String getPathExtension();
    @Property(selector = "stringByDeletingPathExtension")
    public native String getStringByDeletingPathExtension();
    @Property(selector = "stringByAbbreviatingWithTildeInPath")
    public native String getStringByAbbreviatingWithTildeInPath();
    @Property(selector = "stringByExpandingTildeInPath")
    public native String getStringByExpandingTildeInPath();
    @Property(selector = "stringByStandardizingPath")
    public native String getStringByStandardizingPath();
    @Property(selector = "stringByResolvingSymlinksInPath")
    public native String getStringByResolvingSymlinksInPath();
    @Property(selector = "fileSystemRepresentation")
    public native BytePtr getFileSystemRepresentation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "stringByRemovingPercentEncoding")
    public native String getStringByRemovingPercentEncoding();
    /*</properties>*/
    /*<members>*//*</members>*/

    private static long getChars(String s) {
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
        getCharacters$range$(VM.getArrayValuesAddress(chars), new NSRange(0, len));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    public static String getLocalizedString(String key) {
        return NSBundle.getMainBundle().getLocalizedString(key, "", null);
    }

    @Bridge protected static native @MachineSizedUInt long length(@Pointer long handle, Selector sel);
    @Bridge protected static native void getCharacters$range$(@Pointer long handle, Selector sel, @Pointer long buffer, @ByVal NSRange aRange);

    /* UIKit extensions */
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize getSize(UIFont font) {
        return NSStringExtensions.getSize(this, font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize getSize(UIFont font, @MachineSizedFloat double width, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this, font, width, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(CGPoint point, UIFont font) {
        return NSStringExtensions.draw(this, point, font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(CGPoint point, @MachineSizedFloat double width, UIFont font, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.draw(this, point, width, font, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize getSize(UIFont font, @ByVal CGSize size) {
        return NSStringExtensions.getSize(this, font, size);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize getSize(UIFont font, @ByVal CGSize size, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this, font, size, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(@ByVal CGRect rect, UIFont font) {
        return NSStringExtensions.draw(this, rect, font);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(NSString thiz, @ByVal CGRect rect, UIFont font, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.draw(this, rect, font, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(CGRect rect, UIFont font, NSLineBreakMode lineBreakMode, NSTextAlignment alignment) {
        return NSStringExtensions.draw(this, rect, font, lineBreakMode, alignment);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize getSize(UIFont font, @MachineSizedFloat double minFontSize, @MachineSizedFloat double width, NSLineBreakMode lineBreakMode) {
        return NSStringExtensions.getSize(this, font, minFontSize, null, width, lineBreakMode);
    }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CGSize draw(CGPoint point, @MachineSizedFloat double width, UIFont font, @MachineSizedFloat double fontSize, NSLineBreakMode lineBreakMode, UIBaselineAdjustment baselineAdjustment) {
        return NSStringExtensions.draw(this, point, width, font, fontSize, lineBreakMode, baselineAdjustment);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGSize getSize(NSAttributedStringAttributes attrs) {
        return NSStringExtensions.getSize(this, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void draw(CGPoint point, NSAttributedStringAttributes attrs) {
        NSStringExtensions.draw(this, point, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void draw(CGRect rect, NSAttributedStringAttributes attrs) {
        NSStringExtensions.draw(this, rect, attrs);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void draw(CGRect rect, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        NSStringExtensions.draw(this, rect, options, attributes, context);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGRect getBoundingRect(CGSize size, NSStringDrawingOptions options, NSAttributedStringAttributes attributes, NSStringDrawingContext context) {
        return NSStringExtensions.getBoundingRect(this, size, options, attributes, context);
    }

    
    /*<methods>*/
    @Method(selector = "characterAtIndex:")
    protected native short characterAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "getCharacters:range:")
    protected native void getCharacters$range$(@Pointer long buffer, @ByVal NSRange aRange);
    @Method(selector = "initWithCharacters:length:")
    protected native @Pointer long initWithCharacters$length$(@Pointer long characters, @MachineSizedUInt long length);
    @Method(selector = "stringWithCharacters:length:")
    protected static native @Pointer long stringWithCharacters$length$(@Pointer long characters, @MachineSizedUInt long length);
    @Method(selector = "stringByAddingPercentEscapesUsingEncoding:")
    protected native String stringByAddingPercentEscapesUsingEncoding$(NSStringEncoding enc);
    @Method(selector = "stringByReplacingPercentEscapesUsingEncoding:")
    protected native String stringByReplacingPercentEscapesUsingEncoding$(NSStringEncoding enc);
    /*</methods>*/
}
