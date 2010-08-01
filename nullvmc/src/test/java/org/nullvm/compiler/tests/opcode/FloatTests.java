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
public class FloatTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsFloat("freturn", 0.0f, FloatOpcodes.freturn(0.0f));
        assertEqualsFloat("freturn", 0x1.fffffeP+127f, FloatOpcodes.freturn(0x1.fffffeP+127f));

        assertEqualsFloat("fstore", -0.12345f, FloatOpcodes.fstore(-0.12345f));
        assertEqualsFloat("fstore", 0.12345f, FloatOpcodes.fstore(0.12345f));

        assertEqualsFloat("fconst_0", 0.0f, FloatOpcodes.fconst_0());
        assertEqualsFloat("fconst_1", 1.0f, FloatOpcodes.fconst_1());
        assertEqualsFloat("fconst_2", 2.0f, FloatOpcodes.fconst_2());

        assertEqualsFloat("fadd", 0.0f, FloatOpcodes.fadd(0.0f, 0.0f));
        assertEqualsFloat("fadd", -0.5f, FloatOpcodes.fadd(-0.25f, -0.25f));
        assertEqualsFloat("fadd", 0.75f, FloatOpcodes.fadd(0.25f, 0.5f));
        assertEqualsFloat("fadd", 0.75f, FloatOpcodes.fadd(0.5f, 0.25f));
        assertEqualsFloat("fadd", 0.25f, FloatOpcodes.fadd(0.5f, -0.25f));
        assertEqualsFloat("fadd", -1.0f/0.0f, FloatOpcodes.fadd(-0x1.fffffeP+127f, -0x1.0P+127f));
        assertEqualsFloat("fadd", 1.0f/0.0f, FloatOpcodes.fadd(0x1.fffffeP+127f, 0x1.0P+127f));

        assertEqualsFloat("fsub", 0.0f, FloatOpcodes.fsub(0.0f, 0.0f));
        assertEqualsFloat("fsub", 0.0f, FloatOpcodes.fsub(-0.25f, -0.25f));
        assertEqualsFloat("fsub", -0.25f, FloatOpcodes.fsub(0.25f, 0.5f));
        assertEqualsFloat("fsub", 0.25f, FloatOpcodes.fsub(0.5f, 0.25f));
        assertEqualsFloat("fsub", 0.75f, FloatOpcodes.fsub(0.5f, -0.25f));
        assertEqualsFloat("fsub", -1.0f/0.0f, FloatOpcodes.fsub(-0x1.fffffeP+127f, 0x1.0P+127f));
        assertEqualsFloat("fsub", 1.0f/0.0f, FloatOpcodes.fsub(0x1.fffffeP+127f, -0x1.0P+127f));

        assertEqualsFloat("fmul", 0.0f, FloatOpcodes.fmul(0.0f, 0.0f));
        assertEqualsFloat("fmul", 1.25f, FloatOpcodes.fmul(0.5f, 2.5f));
        assertEqualsFloat("fmul", 1.25f, FloatOpcodes.fmul(2.5f, 0.5f));
        assertEqualsFloat("fmul", -1.25f, FloatOpcodes.fmul(-2.5f, 0.5f));
        assertEqualsFloat("fmul", -1.25f, FloatOpcodes.fmul(2.5f, -0.5f));
        assertEqualsFloat("fmul", 1.25f, FloatOpcodes.fmul(-2.5f, -0.5f));
        assertEqualsFloat("fmul", 1.0f/0.0f, FloatOpcodes.fmul(0x1.fffffeP+127f, 2.0f));
        assertEqualsFloat("fmul", 1.0f/0.0f, FloatOpcodes.fmul(1.0f/0.0f, 2.0f));
        assertEqualsFloat("fmul", -1.0f/0.0f, FloatOpcodes.fmul(2.0f, -1.0f/0.0f));
        assertEqualsFloat("fmul", 0.0f/0.0f, FloatOpcodes.fmul(2.0f, 0.0f/0.0f));

        assertEqualsFloat("fdiv", 0.0f/0.0f, FloatOpcodes.fdiv(0.0f, 0.0f));
        assertEqualsFloat("fdiv", 1.0f/0.0f, FloatOpcodes.fdiv(2.0f, 0.0f));
        assertEqualsFloat("fdiv", -1.0f/0.0f, FloatOpcodes.fdiv(-2.0f, 0.0f));
        assertEqualsFloat("fdiv", 0.2f, FloatOpcodes.fdiv(0.5f, 2.5f));
        assertEqualsFloat("fdiv", 5.0f, FloatOpcodes.fdiv(2.5f, 0.5f));
        assertEqualsFloat("fdiv", -5.0f, FloatOpcodes.fdiv(-2.5f, 0.5f));
        assertEqualsFloat("fdiv", -5.0f, FloatOpcodes.fdiv(2.5f, -0.5f));
        assertEqualsFloat("fdiv", 5.0f, FloatOpcodes.fdiv(-2.5f, -0.5f));
        assertEqualsFloat("fdiv", 1.0f/0.0f, FloatOpcodes.fdiv(0x1.fffffeP+127f, 0.5f));

        assertEqualsFloat("frem", 0.0f, FloatOpcodes.frem(0.0f, 1.0f));
        assertEqualsFloat("frem", 0.0f/0.0f, FloatOpcodes.frem(0.0f, 0.0f));
        assertEqualsFloat("frem", 0.0f/0.0f, FloatOpcodes.frem(1.0f, 0.0f));
        assertEqualsFloat("frem", 1.25f, FloatOpcodes.frem(5.75f, 1.5f));
        assertEqualsFloat("frem", -1.25f, FloatOpcodes.frem(-5.75f, 1.5f));
        assertEqualsFloat("frem", 1.25f, FloatOpcodes.frem(5.75f, -1.5f));
        assertEqualsFloat("frem", -1.25f, FloatOpcodes.frem(-5.75f, -1.5f));

        assertEqualsFloat("fneg", 0.0f, FloatOpcodes.fneg(0.0f));
        assertEqualsFloat("fneg", -1.0f, FloatOpcodes.fneg(1.0f));
        assertEqualsFloat("fneg", 1.0f, FloatOpcodes.fneg(-1.0f));
        assertEqualsFloat("fneg", -0x0.000002P-126f, FloatOpcodes.fneg(0x0.000002P-126f));
        assertEqualsFloat("fneg", -0x1.fffffeP+127f, FloatOpcodes.fneg(0x1.fffffeP+127f));
        assertEqualsFloat("fneg", -1.0f/0.0f, FloatOpcodes.fneg(1.0f/0.0f));
        assertEqualsFloat("fneg", 0.0f/0.0f, FloatOpcodes.fneg(0.0f/0.0f));

        assertEqualsFloat("ldc(float)", 1.4e-45f, FloatOpcodes.ldc_min_float());
        assertEqualsFloat("ldc(float)", 3.4028235e+38f, FloatOpcodes.ldc_max_float());

        assertEqualsFloat("i2f", 0.0f, FloatOpcodes.i2f(0));
        assertEqualsFloat("i2f", -0x1.0p31f, FloatOpcodes.i2f(0x80000000));
        assertEqualsFloat("i2f", 0x1.0p31f, FloatOpcodes.i2f(0x7fffffff));

        assertEqualsInt("f2i", 0, FloatOpcodes.f2i(0.0f));
        assertEqualsInt("f2i", 0, FloatOpcodes.f2i(0.0f/0.0f));
        assertEqualsInt("f2i", -128, FloatOpcodes.f2i(-128.75f));
        assertEqualsInt("f2i", 128, FloatOpcodes.f2i(128.75f));
        assertEqualsInt("f2i", 0x80000000, FloatOpcodes.f2i(-1.0f/0.0f));
        assertEqualsInt("f2i", 0x7fffffff, FloatOpcodes.f2i(1.0f/0.0f));
        assertEqualsInt("f2i", 0x80000000, FloatOpcodes.f2i(-0x1.fffffeP+127f));
        assertEqualsInt("f2i", 0x7fffffff, FloatOpcodes.f2i(0x1.fffffeP+127f));

        assertEqualsFloat("l2f", 0.0f, FloatOpcodes.l2f(0));
        assertEqualsFloat("l2f", -0x1.0p63f, FloatOpcodes.l2f(0x8000000000000000L));
        assertEqualsFloat("l2f", 0x1.0p63f, FloatOpcodes.l2f(0x7fffffffffffffffL));

        assertEqualsLong("f2l", 0L, FloatOpcodes.f2l(0.0f));
        assertEqualsLong("f2l", 0L, FloatOpcodes.f2l(0.0f/0.0f));
        assertEqualsLong("f2l", -128L, FloatOpcodes.f2l(-128.75f));
        assertEqualsLong("f2l", 128L, FloatOpcodes.f2l(128.75f));
        assertEqualsLong("f2l", 0x8000000000000000L, FloatOpcodes.f2l(-1.0f/0.0f));
        assertEqualsLong("f2l", 0x7fffffffffffffffL, FloatOpcodes.f2l(1.0f/0.0f));
        assertEqualsLong("f2l", 0x8000000000000000L, FloatOpcodes.f2l(-0x1.fffffeP+127f));
        assertEqualsLong("f2l", 0x7fffffffffffffffL, FloatOpcodes.f2l(0x1.fffffeP+127f));

        assertEqualsInt("fcmpl", 0, FloatOpcodes.fcmpl(0.0f, 0.0f));
        assertEqualsInt("fcmpl", 0, FloatOpcodes.fcmpl(0.0f, -0.0f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(0.0f/0.0f, 0.0f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(0.0f, 0.0f/0.0f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(0.0f/0.0f, 0.0f/0.0f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(-0x1.fffffeP+127f, -0x1.fffffdP+127f));
        assertEqualsInt("fcmpl", 1, FloatOpcodes.fcmpl(-0x1.fffffdP+127f, -0x1.fffffeP+127f));
        assertEqualsInt("fcmpl", 1, FloatOpcodes.fcmpl(0x1.fffffeP+127f, 0x1.fffffdP+127f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(0x1.fffffdP+127f, 0x1.fffffeP+127f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(-0x0.000002P-126f, 0.0f));
        assertEqualsInt("fcmpl", 1, FloatOpcodes.fcmpl(0.0f, -0x0.000002P-126f));
        assertEqualsInt("fcmpl", 1, FloatOpcodes.fcmpl(0x0.000002P-126f, 0.0f));
        assertEqualsInt("fcmpl", -1, FloatOpcodes.fcmpl(0.0f, 0x0.000002P-126f));

        assertEqualsInt("fcmpg", 0, FloatOpcodes.fcmpg(0.0f, 0.0f));
        assertEqualsInt("fcmpg", 0, FloatOpcodes.fcmpg(0.0f, -0.0f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0.0f/0.0f, 0.0f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0.0f, 0.0f/0.0f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0.0f/0.0f, 0.0f/0.0f));
        assertEqualsInt("fcmpg", -1, FloatOpcodes.fcmpg(-0x1.fffffeP+127f, -0x1.fffffdP+127f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(-0x1.fffffdP+127f, -0x1.fffffeP+127f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0x1.fffffeP+127f, 0x1.fffffdP+127f));
        assertEqualsInt("fcmpg", -1, FloatOpcodes.fcmpg(0x1.fffffdP+127f, 0x1.fffffeP+127f));
        assertEqualsInt("fcmpg", -1, FloatOpcodes.fcmpg(-0x0.000002P-126f, 0.0f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0.0f, -0x0.000002P-126f));
        assertEqualsInt("fcmpg", 1, FloatOpcodes.fcmpg(0x0.000002P-126f, 0.0f));
        assertEqualsInt("fcmpg", -1, FloatOpcodes.fcmpg(0.0f, 0x0.000002P-126f));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new FloatTests().run());
    }
    
}
