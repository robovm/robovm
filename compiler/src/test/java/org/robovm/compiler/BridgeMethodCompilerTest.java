/*
 * Copyright (C) 2015 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.StructureType;

import static org.robovm.compiler.llvm.Type.*;

/**
 * Tests {@link BridgeMethodCompiler}.
 */
public class BridgeMethodCompilerTest {

    @Test
    public void testCreateBridgeCWrapperNoReturnNoParameters() {
        assertEquals(
                "void f(void* target) {\n" +
                "    ((void (*)(void)) target)();\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperPrimitiveReturn() {
        assertEquals(
                "int f(void* target) {\n" +
                "    return ((int (*)(void)) target)();\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(I32), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperPrimitiveParameters() {
        assertEquals(
                "void f(void* target, char p0, short p1, int p2, long long p3, float p4, double p5) {\n" +
                "    ((void (*)(char, short, int, long long, float, double)) target)(p0, p1, p2, p3, p4, p5);\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, I8, I16, I32, I64, FLOAT, DOUBLE), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperPointerParameters() {
        assertEquals(
                "void f(void* target, void* p0, void* p1) {\n" +
                "    ((void (*)(void*, void*)) target)(p0, p1);\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, I8_PTR, new PointerType(new StructureType(I32))), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperPointerReturn() {
        assertEquals(
                "void* f(void* target) {\n" +
                "    return ((void* (*)(void)) target)();\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(I8_PTR), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperIgnoresEmptyStructAsFirstMember() {
        assertEquals(
                "void f(void* target, void* p0) {\n" +
                "    struct f_0001 {int m1;};\n" +
                "    ((void (*)(struct f_0001)) target)(*((struct f_0001*)p0));\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, 
                                new StructureType(new StructureType(), I32)), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperSmallStructByValParameter() {
        assertEquals(
                "void f(void* target, void* p0) {\n" +
                "    struct f_0001 {int m0;};\n" +
                "    ((void (*)(struct f_0001)) target)(*((struct f_0001*)p0));\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, 
                                new StructureType(I32)), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperNestedStructByValParameter() {
        assertEquals(
                "void f(void* target, void* p0) {\n" +
                "    struct f_0001_0001 {int m0;};\n" +
                "    struct f_0001_0000 {int m0;};\n" +
                "    struct f_0001 {struct f_0001_0000 m0;struct f_0001_0001 m1;};\n" +
                "    ((void (*)(struct f_0001)) target)(*((struct f_0001*)p0));\n" +
                "}\n",
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, 
                                new StructureType(
                                        new StructureType(I32), 
                                        new StructureType(I32))), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperComplexNestedStructByValParameter() {
        assertEquals(
                "void f(void* target, void* p0) {\n" +
                "    struct f_0001_0003 {void* m0;void* m1;};\n" +
                "    struct f_0001_0002 {float m0;double m1;};\n" +
                "    struct f_0001_0001 {int m0;long long m1;};\n" +
                "    struct f_0001_0000 {char m0;short m1;};\n" +
                "    struct f_0001 {struct f_0001_0000 m0;struct f_0001_0001 m1;struct f_0001_0002 m2;struct f_0001_0003 m3;};\n" +
                "    ((void (*)(struct f_0001)) target)(*((struct f_0001*)p0));\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(VOID, 
                                new StructureType(
                                        new StructureType(I8, I16), 
                                        new StructureType(I32, I64), 
                                        new StructureType(FLOAT, DOUBLE), 
                                        new StructureType(I8_PTR, 
                                                new PointerType(new StructureType(I32))))), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperSmallStructByValReturn() {
        assertEquals(
                "void f(void* target, void* ret) {\n" +
                "    struct f_0000 {int m0;};\n" +
                "    *((struct f_0000*)ret) = ((struct f_0000 (*)(void)) target)();\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(new StructureType(I32)), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperNestedStructByValReturn() {
        assertEquals(
                "void f(void* target, void* ret) {\n" +
                "    struct f_0000_0001 {int m0;};\n" +
                "    struct f_0000_0000 {int m0;};\n" +
                "    struct f_0000 {struct f_0000_0000 m0;struct f_0000_0001 m1;};\n" +
                "    *((struct f_0000*)ret) = ((struct f_0000 (*)(void)) target)();\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(new StructureType(new StructureType(I32), new StructureType(I32))), "f"));
    }
    @Test
    public void testCreateBridgeCWrapperComplexNestedStructByValReturnAndParameter() {
        StructureType structType = new StructureType(
                new StructureType(I8, I16), 
                new StructureType(I32, I64), 
                new StructureType(FLOAT, DOUBLE), 
                new ArrayType(100, I32),
                new ArrayType(10, new StructureType(FLOAT, FLOAT)),
                new ArrayType(5, new ArrayType(10, I32)),
                new StructureType(I8_PTR, 
                        new PointerType(new StructureType(I32))));
        assertEquals(
                "void f(void* target, void* ret, void* p0) {\n" +
                "    struct f_0001_0006 {void* m0;void* m1;};\n" +
                "    struct f_0001_0004 {float m0;float m1;};\n" +
                "    struct f_0001_0002 {float m0;double m1;};\n" +
                "    struct f_0001_0001 {int m0;long long m1;};\n" +
                "    struct f_0001_0000 {char m0;short m1;};\n" +
                "    struct f_0001 {struct f_0001_0000 m0;struct f_0001_0001 m1;struct f_0001_0002 m2;int m3[100];struct f_0001_0004 m4[10];int m5[5][10];struct f_0001_0006 m6;};\n" +
                "    struct f_0000_0006 {void* m0;void* m1;};\n" +
                "    struct f_0000_0004 {float m0;float m1;};\n" +
                "    struct f_0000_0002 {float m0;double m1;};\n" +
                "    struct f_0000_0001 {int m0;long long m1;};\n" +
                "    struct f_0000_0000 {char m0;short m1;};\n" +
                "    struct f_0000 {struct f_0000_0000 m0;struct f_0000_0001 m1;struct f_0000_0002 m2;int m3[100];struct f_0000_0004 m4[10];int m5[5][10];struct f_0000_0006 m6;};\n" +
                "    *((struct f_0000*)ret) = ((struct f_0000 (*)(struct f_0001)) target)(*((struct f_0001*)p0));\n" +
                "}\n", 
                BridgeMethodCompiler.createBridgeCWrapper(
                        new FunctionType(structType, structType), "f"));
    }
}
