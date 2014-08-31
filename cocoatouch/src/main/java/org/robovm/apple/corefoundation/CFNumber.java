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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNumber/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNumberPtr extends Ptr<CFNumber, CFNumberPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNumber.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNumber() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public byte byteValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.SInt8Type, ptr);
        return ptr.as(BytePtr.class).get();
    }
    public short shortValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.SInt16Type, ptr);
        return ptr.as(ShortPtr.class).get();
    }
    public char charValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.SInt16Type, ptr);
        return ptr.as(CharPtr.class).get();
    }
    public int intValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.SInt32Type, ptr);
        return ptr.as(IntPtr.class).get();
    }
    public long longValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.SInt8Type, ptr);
        return ptr.as(LongPtr.class).get();
    }
    public float floatValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.Float32Type, ptr);
        return ptr.as(FloatPtr.class).get();
    }
    public double doubleValue() {
        VoidPtr ptr = new VoidPtr();
        getValue(CFNumberType.Float64Type, ptr);
        return ptr.as(DoublePtr.class).get();
    }
    public boolean booleanValue() {
        int b = intValue();
        if (b == 0) return false;
        return true;
    }
    public static CFNumber valueOf(byte value) {
        return create(null, CFNumberType.SInt8Type, new BytePtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(short value) {
        return create(null, CFNumberType.SInt16Type, new ShortPtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(char value) {
        return create(null, CFNumberType.SInt16Type, new CharPtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(int value) {
        return create(null, CFNumberType.SInt32Type, new IntPtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(long value) {
        return create(null, CFNumberType.SInt64Type, new LongPtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(float value) {
        return create(null, CFNumberType.Float32Type, new FloatPtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(double value) {
        return create(null, CFNumberType.Float64Type, new DoublePtr(value).as(VoidPtr.class));
    }
    public static CFNumber valueOf(boolean value) {
        return create(null, CFNumberType.SInt32Type, new IntPtr(value ? 1 : 0).as(VoidPtr.class));
    }
    /*<methods>*/
    @Bridge(symbol="CFNumberGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNumberCreate", optional=true)
    protected static native CFNumber create(CFAllocator allocator, CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberGetType", optional=true)
    protected native CFNumberType getType();
    @Bridge(symbol="CFNumberGetByteSize", optional=true)
    protected native @MachineSizedSInt long getByteSize();
    @Bridge(symbol="CFNumberIsFloatType", optional=true)
    protected native boolean isFloatType();
    @Bridge(symbol="CFNumberGetValue", optional=true)
    protected native boolean getValue(CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberCompare", optional=true)
    protected native CFComparisonResult compare(CFNumber otherNumber, VoidPtr context);
    /*</methods>*/
}
