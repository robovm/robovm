/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.corefoundation;

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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableBitVector/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableBitVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBitVectorCreateMutable")
    public static native CFMutableBitVector createMutable(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFBitVectorCreateMutableCopy")
    public static native CFMutableBitVector createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFBitVector bv);
    @Bridge(symbol="CFBitVectorSetCount")
    public static native void setCount(CFBitVector bv, @MachineSizedSInt long count);
    @Bridge(symbol="CFBitVectorFlipBitAtIndex")
    public static native void flipBitAtIndex(CFBitVector bv, @MachineSizedSInt long idx);
    @Bridge(symbol="CFBitVectorFlipBits")
    public static native void flipBits(CFBitVector bv, @ByVal CFRange range);
    @Bridge(symbol="CFBitVectorSetBitAtIndex")
    public static native void setBitAtIndex(CFBitVector bv, @MachineSizedSInt long idx, int value);
    @Bridge(symbol="CFBitVectorSetBits")
    public static native void setBits(CFBitVector bv, @ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorSetAllBits")
    public static native void setAllBits(CFBitVector bv, int value);
    /*</methods>*/
}
