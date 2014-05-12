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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSString/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSStringPtr extends Ptr<NSString, NSStringPtr> {}/*</ptr>*/

    public static class AsStringMarshaler {
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            NSString o = ObjCObject.toObjCObject(NSString.class, handle);
            try {
                return o != null ? o.toString() : null;
            } finally {
                if (o != null) {
                    o.dispose();
                }
            }
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSString s = new NSString(o);
            try {
                // retainCount is now 1
                s.retain(); // Make sure the retainCount is 1 when we exit this try block
                // retainCount is now 2
                if ((flags & MarshalerFlags.CALL_TYPE_CALLBACK) > 0) {
                    // NSStrings returned by callbacks should be autoreleased
                    s.autorelease();
                }
                return s.getHandle(); // retainCount is 1 after the return
            } finally {
                s.dispose();
            }
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
    
    private static String EMPTY_STRING = "";
    private static final long STRING_VALUE_OFFSET;    
    private static final long STRING_OFFSET_OFFSET;    
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
        initObject(initWithCharactersNoCopy$length$freeWhenDone$(copyChars(s), s.length(), true));
    }
    
    /*<constructors>*/
    public NSString() {}
    protected NSString(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
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
        int len = (int) length();
        if (len == 0) {
            return EMPTY_STRING;
        }
        char[] chars = new char[len];
        getCharacters$range$(VM.getArrayValuesAddress(chars), new NSRange(0, len));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    /*<methods>*/
    @Method(selector = "length")
    private native @MachineSizedUInt long length();
    @Method(selector = "characterAtIndex:")
    private native short characterAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "getCharacters:range:")
    private native void getCharacters$range$(@Pointer long buffer, @ByVal NSRange aRange);
    @Method(selector = "initWithCharactersNoCopy:length:freeWhenDone:")
    private native @Pointer long initWithCharactersNoCopy$length$freeWhenDone$(@Pointer long characters, @MachineSizedUInt long length, boolean freeBuffer);
    /*</methods>*/
}
