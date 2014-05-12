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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableBitVector/*</name>*/ 
    extends /*<extends>*/CFBitVector/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableBitVectorPtr extends Ptr<CFMutableBitVector, CFMutableBitVectorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableBitVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableBitVector() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBitVectorCreateMutable", optional=true)
    public static native CFMutableBitVector createMutable(CFAllocator allocator, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFBitVectorCreateMutableCopy", optional=true)
    public static native CFMutableBitVector createMutableCopy(CFAllocator allocator, @MachineSizedSInt long capacity, CFBitVector bv);
    @Bridge(symbol="CFBitVectorSetCount", optional=true)
    public native void setCount(@MachineSizedSInt long count);
    @Bridge(symbol="CFBitVectorFlipBitAtIndex", optional=true)
    public native void flipBitAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFBitVectorFlipBits", optional=true)
    public native void flipBits(@ByVal CFRange range);
    @Bridge(symbol="CFBitVectorSetBitAtIndex", optional=true)
    public native void setBitAtIndex(@MachineSizedSInt long idx, int value);
    @Bridge(symbol="CFBitVectorSetBits", optional=true)
    public native void setBits(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorSetAllBits", optional=true)
    public native void setAllBits(int value);
    /*</methods>*/
}
