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
package org.robovm.objc;

import org.robovm.objc.annotation.Block;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * Marshals {@link Runnable} instances. The ObjCBlockPlugin compiler plugin will
 * automatically use this as {@link Marshaler} for {@link Block} annotated
 * {@link Runnable} types. Also used as template for other auto-generated 
 * marshaler classes for other {@link Block} annotated types.
 */
public class RunnableAsObjCBlockMarshaler implements Runnable {

    private static ObjCBlock.Wrapper WRAPPER = 
            new ObjCBlock.Wrapper(RunnableAsObjCBlockMarshaler.class);
    
    private ObjCBlock objCBlock;
    
    private RunnableAsObjCBlockMarshaler(ObjCBlock objCBlock) {
        this.objCBlock = objCBlock;
    }
    
    @MarshalsPointer
    public static Runnable toObject(Class<?> cls, long handle, long flags) {
        if (handle == 0L) {
            return null;
        }
        ObjCBlock block = Struct.toStruct(ObjCBlock.class, handle);
        if (block.hasObject()) {
            return (Runnable) block.object();
        }
        return new RunnableAsObjCBlockMarshaler(block);
    }
    
    @MarshalsPointer
    public static long toNative(Runnable o, long flags) {
        if (o == null) {
            return 0L;
        }
        if (o instanceof RunnableAsObjCBlockMarshaler) {
            return ((RunnableAsObjCBlockMarshaler) o).objCBlock.getHandle();
        }
        // TODO: Retain if returned from callback?
        return WRAPPER.toObjCBlock(o).getHandle();
    }
    
    // The box()/unbox() methods are used as template methods for auto-generated
    // marshalers so they must be here even if this RunnableAsObjCBlockMarshaler
    // doesn't need them.
    
    private static Boolean box(boolean v) {
        return Boolean.valueOf(v);
    }
    private static Byte box(byte v) {
        return Byte.valueOf(v);
    }
    private static Short box(short v) {
        return Short.valueOf(v);
    }
    private static Character box(char v) {
        return Character.valueOf(v);
    }
    private static Integer box(int v) {
        return Integer.valueOf(v);
    }
    private static Long box(long v) {
        return Long.valueOf(v);
    }
    private static Float box(float v) {
        return Float.valueOf(v);
    }
    private static Double box(double v) {
        return Double.valueOf(v);
    }
    private static boolean unbox(Boolean v) {
        return v.booleanValue();
    }
    private static byte unbox(Byte v) {
        return v.byteValue();
    }
    private static short unbox(Short v) {
        return v.shortValue();
    }
    private static char unbox(Character v) {
        return v.charValue();
    }
    private static int unbox(Integer v) {
        return v.intValue();
    }
    private static long unbox(Long v) {
        return v.longValue();
    }
    private static float unbox(Float v) {
        return v.floatValue();
    }
    private static double unbox(Double v) {
        return v.doubleValue();
    }
    
    public void run() {
        invoke(objCBlock.invoke(), objCBlock);
    }
    
    @Bridge(dynamic = true)
    private static native void invoke(@Pointer long target, ObjCBlock block);
    
    @Callback
    private static void invoked(ObjCBlock block) {
        ((Runnable) block.object()).run();
    }
}
