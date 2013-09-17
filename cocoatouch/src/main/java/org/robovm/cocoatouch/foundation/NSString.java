/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

import java.lang.reflect.Field;
import org.robovm.rt.VM;

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSString_Class/Reference/NSString.html">NSString Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSString /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSString /*</name>*/.class);
        
        try {
            STRING_VALUE_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("value")));
            STRING_OFFSET_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("offset")));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    private static String EMPTY_STRING = "";
    private static final long STRING_VALUE_OFFSET;    
    private static final long STRING_OFFSET_OFFSET;    
    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSString /*</name>*/.class);

    public NSString(String s) {
        this(copyChars(s), s.length(), true);
    }
    
    private static long copyChars(String s) {
        int len = s.length();
        int offset = VM.getInt(VM.getObjectAddress(s) + STRING_OFFSET_OFFSET);
        char[] value = (char[]) VM.getObject(VM.getObjectAddress(s) + STRING_VALUE_OFFSET);
        long dest = VM.malloc(len << 1);
        VM.memcpy(dest, VM.getArrayValuesAddress(value) + (offset << 1), len << 1);
        return dest;
    }
    
    @Override
    public String toString() {
        int len = length();
        if (len == 0) {
            return EMPTY_STRING;
        }
        char[] chars = new char[len];
        getCharacters(VM.getArrayValuesAddress(chars), new NSRange(0, len));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    /*<constructors>*/
    protected NSString(SkipInit skipInit) { super(skipInit); }
    public NSString() {}
    
    private static final Selector initWithCharactersNoCopy$length$freeWhenDone$ = Selector.register("initWithCharactersNoCopy:length:freeWhenDone:");
    @Bridge private native static @Pointer long objc_initWithCharactersNoCopy(NSString __self__, Selector __cmd__, @Pointer long characters, int length, boolean flag);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSString_Class/Reference/NSString.html#//apple_ref/occ/instm/NSString/initWithCharactersNoCopy:length:freeWhenDone:">- (id)initWithCharactersNoCopy:(unichar *)characters length:(NSUInteger)length freeWhenDone:(BOOL)flag</a>
     * @since Available in iOS 2.0 and later.
     */
    private NSString(@Pointer long characters, int length, boolean flag) {
        super((SkipInit) null);
        initObject(objc_initWithCharactersNoCopy(this, initWithCharactersNoCopy$length$freeWhenDone$, characters, length, flag));
    }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector characterAtIndex$ = Selector.register("characterAtIndex:");
    @Bridge private native static char objc_characterAtIndex(NSString __self__, Selector __cmd__, int index);
    @Bridge private native static char objc_characterAtIndexSuper(ObjCSuper __super__, Selector __cmd__, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSString_Class/Reference/NSString.html#//apple_ref/occ/instm/NSString/characterAtIndex:">- (unichar)characterAtIndex:(NSUInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    public char characterAtIndex(int index) {
        if (customClass) { return objc_characterAtIndexSuper(getSuper(), characterAtIndex$, index); } else { return objc_characterAtIndex(this, characterAtIndex$, index); }
    }
    
    private static final Selector getCharacters$range$ = Selector.register("getCharacters:range:");
    @Bridge private native static void objc_getCharacters(NSString __self__, Selector __cmd__, @Pointer long buffer, @ByVal NSRange aRange);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSString_Class/Reference/NSString.html#//apple_ref/occ/instm/NSString/getCharacters:range:">- (void)getCharacters:(unichar *)buffer range:(NSRange)aRange</a>
     * @since Available in iOS 2.0 and later.
     */
    private void getCharacters(@Pointer long buffer, NSRange aRange) {
        objc_getCharacters(this, getCharacters$range$, buffer, aRange);
    }
    
    private static final Selector length = Selector.register("length");
    @Bridge private native static int objc_length(NSString __self__, Selector __cmd__);
    @Bridge private native static int objc_lengthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSString_Class/Reference/NSString.html#//apple_ref/occ/instm/NSString/length">- (NSUInteger)length</a>
     * @since Available in iOS 2.0 and later.
     */
    public int length() {
        if (customClass) { return objc_lengthSuper(getSuper(), length); } else { return objc_length(this, length); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("characterAtIndex:") public static char characterAtIndex(NSString __self__, Selector __cmd__, int index) { return __self__.characterAtIndex(index); }
        @Callback @BindSelector("length") public static int length(NSString __self__, Selector __cmd__) { return __self__.length(); }
    }
    /*</callbacks>*/

    public static class AsStringMarshaler {
        @SuppressWarnings("rawtypes")
        public static Object toObject(Class cls, long handle, boolean copy) {
            NSString o = ObjCObject.toObjCObject(NSString.class, handle);
            return o != null ? o.toString() : null;
        }
        public static void updateObject(Object o, long handle) {
        }
        public static @Pointer long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            return new NSString((String) o).getHandle();
        }
        public static void updateNative(Object o, long handle) {
        }
    }
}
