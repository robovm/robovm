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
public class LongTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsLong("lreturn", -100, LongOpcodes.lreturn(-100));
        assertEqualsLong("lreturn", 0x7fffffffffffffffL, LongOpcodes.lreturn(0x7fffffffffffffffL));
        assertEqualsLong("lreturn", 0x8000000000000000L, LongOpcodes.lreturn(0x8000000000000000L));

        assertEqualsLong("lstore", -1000000000000L, LongOpcodes.lstore(-1000000000000L));
        assertEqualsLong("lstore", 1000000000000L, LongOpcodes.lstore(1000000000000L));

        assertEqualsLong("lconst_0", 0, LongOpcodes.lconst_0());
        assertEqualsLong("lconst_1", 1, LongOpcodes.lconst_1());

        assertEqualsLong("ladd", 0, LongOpcodes.ladd(0, 0));
        assertEqualsLong("ladd", 30, LongOpcodes.ladd(20, 10));
        assertEqualsLong("ladd", -10, LongOpcodes.ladd(-20, 10));
        assertEqualsLong("ladd", 10, LongOpcodes.ladd(20, -10));
        assertEqualsLong("ladd", -30, LongOpcodes.ladd(-20, -10));
        assertEqualsLong("ladd", 0x7fffffffffffffffL, LongOpcodes.ladd(0x8000000000000000L, -1));  
        assertEqualsLong("ladd", 0x8000000000000000L, LongOpcodes.ladd(0x7fffffffffffffffL, 1));  

        assertEqualsLong("lsub", 0, LongOpcodes.lsub(0, 0));
        assertEqualsLong("lsub", 10, LongOpcodes.lsub(20, 10));
        assertEqualsLong("lsub", -30, LongOpcodes.lsub(-20, 10));
        assertEqualsLong("lsub", 30, LongOpcodes.lsub(20, -10));
        assertEqualsLong("lsub", -10, LongOpcodes.lsub(-20, -10));
        assertEqualsLong("lsub", 0x7fffffffffffffffL, LongOpcodes.lsub(0x8000000000000000L, 1));  
        assertEqualsLong("lsub", 0x8000000000000000L, LongOpcodes.lsub(0x7fffffffffffffffL, -1));  

        assertEqualsLong("lmul", 0, LongOpcodes.lmul(0, 0));
        assertEqualsLong("lmul", 0, LongOpcodes.lmul(10, 0));
        assertEqualsLong("lmul", 0, LongOpcodes.lmul(0, 10));
        assertEqualsLong("lmul", 39483, LongOpcodes.lmul(123, 321));
        assertEqualsLong("lmul", 39483, LongOpcodes.lmul(321, 123));
        assertEqualsLong("lmul", -242, LongOpcodes.lmul(-11, 22));
        assertEqualsLong("lmul", -242, LongOpcodes.lmul(11, -22));
        assertEqualsLong("lmul", 242, LongOpcodes.lmul(-11, -22));
        assertEqualsLong("lmul", 1, LongOpcodes.lmul(0x7fffffffffffffffL, 0x7fffffffffffffffL));
        assertEqualsLong("lmul", 3000000000000000000L, LongOpcodes.lmul(-2000000000, -1500000000));
        assertEqualsLong("lmul", 6011292615620689920L, LongOpcodes.lmul(-2000000000000L, -1500000000000L));

        assertEqualsLong("ldiv", 0, LongOpcodes.ldiv(0, 10));
        assertEqualsLong("ldiv", 0, LongOpcodes.ldiv(0, -10));
        assertEqualsLong("ldiv", 5, LongOpcodes.ldiv(10, 2));
        assertEqualsLong("ldiv", 5, LongOpcodes.ldiv(11, 2));
        assertEqualsLong("ldiv", -5, LongOpcodes.ldiv(10, -2));
        assertEqualsLong("ldiv", -5, LongOpcodes.ldiv(-10, 2));
        assertEqualsLong("ldiv", 5, LongOpcodes.ldiv(-10, -2));
        assertEqualsLong("ldiv", 0, LongOpcodes.ldiv(0xffffffffffffffffL, 2));
        assertEqualsLong("ldiv", 0x1999999999999999L, LongOpcodes.ldiv(0x6666666666666666L, 4));
        assertEqualsLong("ldiv", 0x3fffffffffffffffL, LongOpcodes.ldiv(0x7fffffffffffffffL, 2));
//        assertEqualsLong("ldiv", 0, LongOpcodes.ldiv(10, 0));

        assertEqualsLong("lrem", 0, LongOpcodes.lrem(0, 10));
        assertEqualsLong("lrem", 1, LongOpcodes.lrem(11, 10));
        assertEqualsLong("lrem", 9, LongOpcodes.lrem(19, 10));
        assertEqualsLong("lrem", -9, LongOpcodes.lrem(-19, 10));
        assertEqualsLong("lrem", 9, LongOpcodes.lrem(19, -10));
        assertEqualsLong("lrem", -9, LongOpcodes.lrem(-19, -10));
        assertEqualsLong("lrem", 0, LongOpcodes.lrem(20, 10));
        assertEqualsLong("lrem", 100, LongOpcodes.lrem(4000000000100L, 4000000000000L));
//        assertEqualsLong("lrem", 0, LongOpcodes.lrem(100, 0));

        assertEqualsLong("lneg", 0, LongOpcodes.lneg(0));
        assertEqualsLong("lneg", -1, LongOpcodes.lneg(1));
        assertEqualsLong("lneg", 1, LongOpcodes.lneg(-1));
        assertEqualsLong("lneg", 0x8000000000000001L, LongOpcodes.lneg(0x7fffffffffffffffL));
        assertEqualsLong("lneg", 0x8000000000000000L, LongOpcodes.lneg(0x8000000000000000L));

        assertEqualsLong("lshl", 0, LongOpcodes.lshl(0, 0));
        assertEqualsLong("lshl", 0, LongOpcodes.lshl(0, 10));
        assertEqualsLong("lshl", 1, LongOpcodes.lshl(1, 0));
        assertEqualsLong("lshl", 2, LongOpcodes.lshl(1, 1));
        assertEqualsLong("lshl", 4, LongOpcodes.lshl(1, 2));
        assertEqualsLong("lshl", 0x8000000000000000L, LongOpcodes.lshl(1, 63));
        assertEqualsLong("lshl", 0xffffffff80000000L, LongOpcodes.lshl(0xffffffffffffffffL, 31));
        assertEqualsLong("lshl", 1, LongOpcodes.lshl(1, 64));
        assertEqualsLong("lshl", 2, LongOpcodes.lshl(1, 65));
        assertEqualsLong("lshl", 4, LongOpcodes.lshl(1, 66));
        assertEqualsLong("lshl", 0x8000000000000000L, LongOpcodes.lshl(1, -1));

        assertEqualsLong("lshr", 0, LongOpcodes.lshr(0, 0));
        assertEqualsLong("lshr", 0, LongOpcodes.lshr(0, 1));
        assertEqualsLong("lshr", 1, LongOpcodes.lshr(1, 0));
        assertEqualsLong("lshr", 0, LongOpcodes.lshr(1, 1));
        assertEqualsLong("lshr", 16, LongOpcodes.lshr(32, 1));
        assertEqualsLong("lshr", 8, LongOpcodes.lshr(32, 2));
        assertEqualsLong("lshr", 4, LongOpcodes.lshr(32, 3));
        assertEqualsLong("lshr", -1, LongOpcodes.lshr(-1, 1));
        assertEqualsLong("lshr", -1, LongOpcodes.lshr(-1, 2));
        assertEqualsLong("lshr", -4, LongOpcodes.lshr(-16, 2));
        assertEqualsLong("lshr", 0xf800000000000000L, LongOpcodes.lshr(0x8000000000000000L, 4));
        assertEqualsLong("lshr", 0x8000000000000000L, LongOpcodes.lshr(0x8000000000000000L, 64));
        assertEqualsLong("lshr", 0xc000000000000000L, LongOpcodes.lshr(0x8000000000000000L, 65));
        assertEqualsLong("lshr", 0xe000000000000000L, LongOpcodes.lshr(0x8000000000000000L, 66));
        assertEqualsLong("lshr", -1L, LongOpcodes.lshr(0x8000000000000000L, -1));

        assertEqualsLong("lushr", 0, LongOpcodes.lushr(0, 0));
        assertEqualsLong("lushr", 0, LongOpcodes.lushr(0, 1));
        assertEqualsLong("lushr", 1, LongOpcodes.lushr(1, 0));
        assertEqualsLong("lushr", 0, LongOpcodes.lushr(1, 1));
        assertEqualsLong("lushr", 16, LongOpcodes.lushr(32, 1));
        assertEqualsLong("lushr", 8, LongOpcodes.lushr(32, 2));
        assertEqualsLong("lushr", 4, LongOpcodes.lushr(32, 3));
        assertEqualsLong("lushr", 0x7fffffffffffffffL, LongOpcodes.lushr(-1, 1));
        assertEqualsLong("lushr", 0x0800000000000000L, LongOpcodes.lushr(0x8000000000000000L, 4));
        assertEqualsLong("lushr", 0x8000000000000000L, LongOpcodes.lushr(0x8000000000000000L, 64));
        assertEqualsLong("lushr", 0x4000000000000000L, LongOpcodes.lushr(0x8000000000000000L, 65));
        assertEqualsLong("lushr", 0x2000000000000000L, LongOpcodes.lushr(0x8000000000000000L, 66));
        assertEqualsLong("lushr", 1, LongOpcodes.lushr(0x8000000000000000L, -1));

        assertEqualsLong("land", 0, LongOpcodes.land(0, 0));
        assertEqualsLong("land", 0, LongOpcodes.land(0, 0xffffffffffffffffL));
        assertEqualsLong("land", 0, LongOpcodes.land(0xffffffffffffffffL, 0));
        assertEqualsLong("land", 0xffffffffffffffffL, LongOpcodes.land(0xffffffffffffffffL, 0xffffffffffffffffL));
        assertEqualsLong("land", 0x2222222222222222L, LongOpcodes.land(0x3333333333333333L, 0x6666666666666666L));
        assertEqualsLong("land", 0x0000000110000000L, LongOpcodes.land(0xffffffff10000000L, 0x00000001ffffffffL));

        assertEqualsLong("lor", 0, LongOpcodes.lor(0, 0));
        assertEqualsLong("lor", 0xffffffffffffffffL, LongOpcodes.lor(0, 0xffffffffffffffffL));
        assertEqualsLong("lor", 0xffffffffffffffffL, LongOpcodes.lor(0xffffffffffffffffL, 0));
        assertEqualsLong("lor", 0xffffffffffffffffL, LongOpcodes.lor(0xffffffffffffffffL, 0xffffffffffffffffL));
        assertEqualsLong("lor", 0x7777777777777777L, LongOpcodes.lor(0x3333333333333333L, 0x6666666666666666L));
        assertEqualsLong("lor", 0xffffffffffffffffL, LongOpcodes.lor(0xffffffff10000000L, 0x00000001ffffffffL));

        assertEqualsLong("lxor", 0, LongOpcodes.lxor(0, 0));
        assertEqualsLong("lxor", 0xffffffffffffffffL, LongOpcodes.lxor(0, 0xffffffffffffffffL));
        assertEqualsLong("lxor", 0xffffffffffffffffL, LongOpcodes.lxor(0xffffffffffffffffL, 0));
        assertEqualsLong("lxor", 0, LongOpcodes.lxor(0xffffffffffffffffL, 0xffffffffffffffffL));
        assertEqualsLong("lxor", 0x5555555555555555L, LongOpcodes.lxor(0x3333333333333333L, 0x6666666666666666L));
        assertEqualsLong("lxor", 0xfffffffeefffffffL, LongOpcodes.lxor(0xffffffff10000000L, 0x00000001ffffffffL));

        assertEqualsLong("i2l", 0L, LongOpcodes.i2l(0));
        assertEqualsLong("i2l", 0x7fffffffL, LongOpcodes.i2l(0x7fffffff));
        assertEqualsLong("i2l", 0xffffffffffffffffL, LongOpcodes.i2l(0xffffffff));
        assertEqualsLong("i2l", 0xffffffff80000000L, LongOpcodes.i2l(0x80000000));

        assertEqualsInt("l2i", 0, LongOpcodes.l2i(0));
        assertEqualsInt("l2i", 0x7fffffff, LongOpcodes.l2i(0x7fffffff));
        assertEqualsInt("l2i", 0xffffffff, LongOpcodes.l2i(0xffffffffffffffffL));
        assertEqualsInt("l2i", 0x9abcdef0, LongOpcodes.l2i(0x123456789abcdef0L));

        assertEqualsInt("lcmp", 0, LongOpcodes.lcmp(0, 0));
        assertEqualsInt("lcmp", 0, LongOpcodes.lcmp(0x8000000000000000L, 0x8000000000000000L));
        assertEqualsInt("lcmp", 0, LongOpcodes.lcmp(0x7fffffffffffffffL, 0x7fffffffffffffffL));
        assertEqualsInt("lcmp", 1, LongOpcodes.lcmp(0x8000000000000001L, 0x8000000000000000L));
        assertEqualsInt("lcmp", 1, LongOpcodes.lcmp(0x7fffffffffffffffL, 0x7ffffffffffffffeL));
        assertEqualsInt("lcmp", -1, LongOpcodes.lcmp(0x8000000000000000L, 0x8000000000000001L));
        assertEqualsInt("lcmp", -1, LongOpcodes.lcmp(0x7ffffffffffffffeL, 0x7fffffffffffffffL));

        assertEqualsLong("ldc(long)", 0x8000000000000000L, LongOpcodes.ldc_min_long());
        assertEqualsLong("ldc(long)", 0x7fffffffffffffffL, LongOpcodes.ldc_max_long());
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new LongTests().run());
    }
    
}
