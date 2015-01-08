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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBitVector/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFBitVectorPtr extends Ptr<CFBitVector, CFBitVectorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFBitVector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFBitVector() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBitVectorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBitVectorCreate", optional=true)
    public static native CFBitVector create(CFAllocator allocator, BytePtr bytes, @MachineSizedSInt long numBits);
    @Bridge(symbol="CFBitVectorCreateCopy", optional=true)
    public static native CFBitVector createCopy(CFAllocator allocator, CFBitVector bv);
    @Bridge(symbol="CFBitVectorGetCount", optional=true)
    public native @MachineSizedSInt long getCount();
    @Bridge(symbol="CFBitVectorGetCountOfBit", optional=true)
    public native @MachineSizedSInt long getCountOfBit(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorContainsBit", optional=true)
    public native boolean containsBit(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorGetBitAtIndex", optional=true)
    public native int getBitAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFBitVectorGetBits", optional=true)
    public native void getBits(@ByVal CFRange range, BytePtr bytes);
    @Bridge(symbol="CFBitVectorGetFirstIndexOfBit", optional=true)
    public native @MachineSizedSInt long getFirstIndexOfBit(@ByVal CFRange range, int value);
    @Bridge(symbol="CFBitVectorGetLastIndexOfBit", optional=true)
    public native @MachineSizedSInt long getLastIndexOfBit(@ByVal CFRange range, int value);
    /*</methods>*/
}
