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
    extends /*<extends>*/CFBitVector/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableBitVectorPtr extends Ptr<CFMutableBitVector, CFMutableBitVectorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableBitVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMutableBitVector() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBitVectorCreateMutable")
    public static native CFMutableBitVector createMutable(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFBitVectorCreateMutableCopy")
    public static native CFMutableBitVector createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFBitVector bv);
    @Bridge(symbol="CFBitVectorSetCount")
    public native void setCount(@MachineSizedSInt long count);
    @Bridge(symbol="CFBitVectorFlipBitAtIndex")
    public native void flipBitAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFBitVectorFlipBits")
    public native void flipBits(@ByVal CFRange range);
    @Bridge(symbol="CFBitVectorSetBitAtIndex")
    public native void setBitAtIndex(@MachineSizedSInt long idx, int value);
    @Bridge(symbol="CFBitVectorSetBits")
    public native void setBits(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorSetAllBits")
    public native void setAllBits(int value);
    /*</methods>*/
}
