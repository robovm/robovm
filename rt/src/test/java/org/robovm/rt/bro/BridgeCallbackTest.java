/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.rt.bro;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Tests {@link Bridge} and {@link Callback} methods.
 */
public class BridgeCallbackTest {

    public static final class Point extends Struct<Point> {
        @StructMember(0)
        public native int x();
        @StructMember(1)
        public native int y();
        @StructMember(0)
        public native Point x(int x);
        @StructMember(1)
        public native Point y(int y);
    }
    
    @Bridge
    public static native int addInts(int x, int y);
    @Callback
    public static int addInts_cb(int x, int y) {
        return x + y;
    }

    @Bridge
    public static native Point addPoints(Point p1, Point p2);
    @Callback
    public static Point addPoints_cb(Point p1, Point p2) {
        return new Point().x(p1.x() + p2.x()).y(p1.y() + p2.y());
    }
    
    @Bridge
    public static native void scalePoint1(Point p, int scale);
    @Callback
    public static void scalePoint1_cb(Point p, int scale) {
        p.x(p.x() * scale);
        p.y(p.y() * scale);
    }

    @Bridge
    public static native void scalePoint2(@ByVal Point p, int scale);
    @Callback
    public static void scalePoint2_cb(@ByVal Point p, int scale) {
        p.x(p.x() * scale);
        p.y(p.y() * scale);
    }
    
    private static Method find(String name) {
        for (Method m : BridgeCallbackTest.class.getDeclaredMethods()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    
    static {
        for (Method m : BridgeCallbackTest.class.getDeclaredMethods()) {
            if (m.getAnnotation(Bridge.class) != null) {
                Method callbackMethod = find(m.getName() + "_cb");
                if (callbackMethod != null) {
                    VM.bindBridgeMethod(m, VM.getCallbackMethodImpl(callbackMethod));
                }
            }
        }
    }
    
    @Test
    public void testPrimitiveParameters() {
        assertEquals(8, addInts(5, 3));
    }

    @Test
    public void testMarshalStructParametersAndReturnValue() {
        Point p1 = new Point().x(1).y(2);
        Point p2 = new Point().x(3).y(4);
        Point sum = addPoints(p1, p2);
        assertEquals(4, sum.x());
        assertEquals(6, sum.y());
    }
    
    @Test
    public void testMarshalStructParametersByRef() {
        Point p = new Point().x(1).y(2);
        scalePoint1(p, 5);
        assertEquals(5, p.x());
        assertEquals(10, p.y());
    }
    
    @Test
    public void testMarshalStructParametersByVal() {
        Point p = new Point().x(1).y(2);
        scalePoint2(p, 5);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
    }
}
