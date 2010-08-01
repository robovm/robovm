/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode;

/**
 *
 * @version $Id$
 */
public class IntTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsInt("ireturn", -100, IntOpcodes.ireturn(-100));
        assertEqualsInt("ireturn", 0x7fffffff, IntOpcodes.ireturn(0x7fffffff));
        assertEqualsInt("ireturn", 0x80000000, IntOpcodes.ireturn(0x80000000));

        assertEqualsInt("istore", -1000000, IntOpcodes.istore(-1000000));
        assertEqualsInt("istore", 1000000, IntOpcodes.istore(1000000));

        assertEqualsInt("iconst_m1", -1, IntOpcodes.iconst_m1());
        assertEqualsInt("iconst_0", 0, IntOpcodes.iconst_0());
        assertEqualsInt("iconst_1", 1, IntOpcodes.iconst_1());
        assertEqualsInt("iconst_2", 2, IntOpcodes.iconst_2());
        assertEqualsInt("iconst_3", 3, IntOpcodes.iconst_3());
        assertEqualsInt("iconst_4", 4, IntOpcodes.iconst_4());
        assertEqualsInt("iconst_5", 5, IntOpcodes.iconst_5());

        assertEqualsInt("iadd", 0, IntOpcodes.iadd(0, 0));
        assertEqualsInt("iadd", -2, IntOpcodes.iadd(-1, -1));
        assertEqualsInt("iadd", 30, IntOpcodes.iadd(10, 20));
        assertEqualsInt("iadd", 30, IntOpcodes.iadd(20, 10));
        assertEqualsInt("iadd", 4, IntOpcodes.iadd(5, -1));
        assertEqualsInt("iadd", 0x80000000, IntOpcodes.iadd(0x7fffffff, 1));

        assertEqualsInt("isub", 0, IntOpcodes.isub(0, 0));
        assertEqualsInt("isub", 0, IntOpcodes.isub(-1, -1));
        assertEqualsInt("isub", -10, IntOpcodes.isub(10, 20));
        assertEqualsInt("isub", 10, IntOpcodes.isub(20, 10));
        assertEqualsInt("isub", 6, IntOpcodes.isub(5, -1));
        assertEqualsInt("isub", 0x7fffffff, IntOpcodes.isub(0x80000000, 1));

        assertEqualsInt("imul", 0, IntOpcodes.imul(0, 0));
        assertEqualsInt("imul", 0, IntOpcodes.imul(10, 0));
        assertEqualsInt("imul", 0, IntOpcodes.imul(0, 10));
        assertEqualsInt("imul", 39483, IntOpcodes.imul(123, 321));
        assertEqualsInt("imul", 39483, IntOpcodes.imul(321, 123));
        assertEqualsInt("imul", -242, IntOpcodes.imul(-11, 22));
        assertEqualsInt("imul", -242, IntOpcodes.imul(11, -22));
        assertEqualsInt("imul", 242, IntOpcodes.imul(-11, -22));
        assertEqualsInt("imul", 1, IntOpcodes.imul(0x7fffffff, 0x7fffffff));
        assertEqualsInt("imul", -164888576, IntOpcodes.imul(-2000000000, -1500000000));

        assertEqualsInt("idiv", 0, IntOpcodes.idiv(0, 10));
        assertEqualsInt("idiv", 0, IntOpcodes.idiv(0, -10));
        assertEqualsInt("idiv", 5, IntOpcodes.idiv(10, 2));
        assertEqualsInt("idiv", 5, IntOpcodes.idiv(11, 2));
        assertEqualsInt("idiv", -5, IntOpcodes.idiv(10, -2));
        assertEqualsInt("idiv", -5, IntOpcodes.idiv(-10, 2));
        assertEqualsInt("idiv", 5, IntOpcodes.idiv(-10, -2));
        assertEqualsInt("idiv", 0, IntOpcodes.idiv(0xffffffff, 2));
        assertEqualsInt("idiv", 0x3fffffff, IntOpcodes.idiv(0x7fffffff, 2));
//        assertEqualsInt("idiv", 0, IntOpcodes.idiv(10, 0));

        assertEqualsInt("irem", 0, IntOpcodes.irem(0, 10));
        assertEqualsInt("irem", 1, IntOpcodes.irem(11, 10));
        assertEqualsInt("irem", 9, IntOpcodes.irem(19, 10));
        assertEqualsInt("irem", -9, IntOpcodes.irem(-19, 10));
        assertEqualsInt("irem", 9, IntOpcodes.irem(19, -10));
        assertEqualsInt("irem", -9, IntOpcodes.irem(-19, -10));
        assertEqualsInt("irem", 0, IntOpcodes.irem(20, 10));
//        assertEqualsInt("irem", 0, IntOpcodes.irem(100, 0));

        assertEqualsInt("ineg", 0, IntOpcodes.ineg(0));
        assertEqualsInt("ineg", -1, IntOpcodes.ineg(1));
        assertEqualsInt("ineg", 1, IntOpcodes.ineg(-1));
        assertEqualsInt("ineg", 0x80000001, IntOpcodes.ineg(0x7fffffff));
        assertEqualsInt("ineg", 0x80000000, IntOpcodes.ineg(0x80000000));

        assertEqualsInt("ishl", 0, IntOpcodes.ishl(0, 0));
        assertEqualsInt("ishl", 0, IntOpcodes.ishl(0, 10));
        assertEqualsInt("ishl", 1, IntOpcodes.ishl(1, 0));
        assertEqualsInt("ishl", 2, IntOpcodes.ishl(1, 1));
        assertEqualsInt("ishl", 4, IntOpcodes.ishl(1, 2));
        assertEqualsInt("ishl", 0x80000000, IntOpcodes.ishl(1, 31));
        assertEqualsInt("ishl", 0xffff8000, IntOpcodes.ishl(0xffffffff, 15));
        assertEqualsInt("ishl", 1, IntOpcodes.ishl(1, 32));
        assertEqualsInt("ishl", 2, IntOpcodes.ishl(1, 33));
        assertEqualsInt("ishl", 4, IntOpcodes.ishl(1, 34));
        assertEqualsInt("ishl", 0x80000000, IntOpcodes.ishl(1, -1));

        assertEqualsInt("ishr", 0, IntOpcodes.ishr(0, 0));
        assertEqualsInt("ishr", 0, IntOpcodes.ishr(0, 1));
        assertEqualsInt("ishr", 1, IntOpcodes.ishr(1, 0));
        assertEqualsInt("ishr", 0, IntOpcodes.ishr(1, 1));
        assertEqualsInt("ishr", 16, IntOpcodes.ishr(32, 1));
        assertEqualsInt("ishr", 8, IntOpcodes.ishr(32, 2));
        assertEqualsInt("ishr", 4, IntOpcodes.ishr(32, 3));
        assertEqualsInt("ishr", -1, IntOpcodes.ishr(-1, 1));
        assertEqualsInt("ishr", -1, IntOpcodes.ishr(-1, 2));
        assertEqualsInt("ishr", -4, IntOpcodes.ishr(-16, 2));
        assertEqualsInt("ishr", 0xf8000000, IntOpcodes.ishr(0x80000000, 4));
        assertEqualsInt("ishr", 0x80000000, IntOpcodes.ishr(0x80000000, 32));
        assertEqualsInt("ishr", 0xc0000000, IntOpcodes.ishr(0x80000000, 33));
        assertEqualsInt("ishr", 0xe0000000, IntOpcodes.ishr(0x80000000, 34));
        assertEqualsInt("ishr", -1, IntOpcodes.ishr(0x80000000, -1));

        assertEqualsInt("iushr", 0, IntOpcodes.iushr(0, 0));
        assertEqualsInt("iushr", 0, IntOpcodes.iushr(0, 1));
        assertEqualsInt("iushr", 1, IntOpcodes.iushr(1, 0));
        assertEqualsInt("iushr", 0, IntOpcodes.iushr(1, 1));
        assertEqualsInt("iushr", 16, IntOpcodes.iushr(32, 1));
        assertEqualsInt("iushr", 8, IntOpcodes.iushr(32, 2));
        assertEqualsInt("iushr", 4, IntOpcodes.iushr(32, 3));
        assertEqualsInt("iushr", 0x7fffffff, IntOpcodes.iushr(-1, 1));
        assertEqualsInt("iushr", 0x08000000, IntOpcodes.iushr(0x80000000, 4));
        assertEqualsInt("iushr", 0x80000000, IntOpcodes.iushr(0x80000000, 32));
        assertEqualsInt("iushr", 0x40000000, IntOpcodes.iushr(0x80000000, 33));
        assertEqualsInt("iushr", 0x20000000, IntOpcodes.iushr(0x80000000, 34));
        assertEqualsInt("iushr", 1, IntOpcodes.iushr(0x80000000, -1));

        assertEqualsInt("iand", 0, IntOpcodes.iand(0, 0));
        assertEqualsInt("iand", 0, IntOpcodes.iand(0, 0xffffffff));
        assertEqualsInt("iand", 0, IntOpcodes.iand(0xffffffff, 0));
        assertEqualsInt("iand", 0xffffffff, IntOpcodes.iand(0xffffffff, 0xffffffff));
        assertEqualsInt("iand", 0x22222222, IntOpcodes.iand(0x33333333, 0x66666666));
        assertEqualsInt("iand", 0x00011000, IntOpcodes.iand(0xffff1000, 0x0001ffff));

        assertEqualsInt("ior", 0, IntOpcodes.ior(0, 0));
        assertEqualsInt("ior", 0xffffffff, IntOpcodes.ior(0, 0xffffffff));
        assertEqualsInt("ior", 0xffffffff, IntOpcodes.ior(0xffffffff, 0));
        assertEqualsInt("ior", 0xffffffff, IntOpcodes.ior(0xffffffff, 0xffffffff));
        assertEqualsInt("ior", 0x77777777, IntOpcodes.ior(0x33333333, 0x66666666));
        assertEqualsInt("ior", 0xffffffff, IntOpcodes.ior(0xffff1000, 0x0001ffff));

        assertEqualsInt("ixor", 0, IntOpcodes.ixor(0, 0));
        assertEqualsInt("ixor", 0xffffffff, IntOpcodes.ixor(0, 0xffffffff));
        assertEqualsInt("ixor", 0xffffffff, IntOpcodes.ixor(0xffffffff, 0));
        assertEqualsInt("ixor", 0, IntOpcodes.ixor(0xffffffff, 0xffffffff));
        assertEqualsInt("ixor", 0x55555555, IntOpcodes.ixor(0x33333333, 0x66666666));
        assertEqualsInt("ixor", 0xfffeefff, IntOpcodes.ixor(0xffff1000, 0x0001ffff));

        assertEqualsInt("i2b", 0, IntOpcodes.i2b(0));
        assertEqualsInt("i2b", 0x7f, IntOpcodes.i2b(0x7f));
        assertEqualsInt("i2b", 0xffffffff, IntOpcodes.i2b(0xff));
        assertEqualsInt("i2b", 0xffffff80, IntOpcodes.i2b(0x80));
        assertEqualsInt("i2b", 0x78, IntOpcodes.i2b(0x12345678));
        assertEqualsInt("i2b", 0xfffffffb, IntOpcodes.i2b(0x123456fb));

        assertEqualsInt("i2c", 0, IntOpcodes.i2c(0));
        assertEqualsInt("i2c", 0x7fff, IntOpcodes.i2c(0x7fff));
        assertEqualsInt("i2c", 0xffff, IntOpcodes.i2c(0xffff));
        assertEqualsInt("i2c", 0x8000, IntOpcodes.i2c(0x8000));
        assertEqualsInt("i2c", 0x5678, IntOpcodes.i2c(0x12345678));
        assertEqualsInt("i2c", 0xfffb, IntOpcodes.i2c(0x1234fffb));

        assertEqualsInt("i2s", 0, IntOpcodes.i2s(0));
        assertEqualsInt("i2s", 0x7fff, IntOpcodes.i2s(0x7fff));
        assertEqualsInt("i2s", 0xffffffff, IntOpcodes.i2s(0xffff));
        assertEqualsInt("i2s", 0xffff8000, IntOpcodes.i2s(0x8000));
        assertEqualsInt("i2s", 0x5678, IntOpcodes.i2s(0x12345678));
        assertEqualsInt("i2s", 0xfffffffb, IntOpcodes.i2s(0x1234fffb));

        assertEqualsInt("bipush", -1, IntOpcodes.bipush_m1());
        assertEqualsInt("bipush", 127, IntOpcodes.bipush_127());

        assertEqualsInt("sipush", -1, IntOpcodes.sipush_m1());
        assertEqualsInt("sipush", 32767, IntOpcodes.sipush_32767());

        assertEqualsInt("ldc(int)", 0x80000000, IntOpcodes.ldc_min_int());
        assertEqualsInt("ldc(int)", 0x7fffffff, IntOpcodes.ldc_max_int());

        assertEqualsInt("iinc", 127, IntOpcodes.iinc_m128(255));
        assertEqualsInt("iinc", 255, IntOpcodes.iinc_127(128));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new IntTests().run());
    }
    
}
