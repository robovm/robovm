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
public class DoubleTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertEqualsDouble("dreturn", 0.0, DoubleOpcodes.dreturn(0.0));
        assertEqualsDouble("dreturn", 0x1.fffffffffffffP+1023, DoubleOpcodes.dreturn(0x1.fffffffffffffP+1023));

        assertEqualsDouble("dstore", -0.12345, DoubleOpcodes.dstore(-0.12345));
        assertEqualsDouble("dstore", 0.12345, DoubleOpcodes.dstore(0.12345));

        assertEqualsDouble("dconst_0", 0.0, DoubleOpcodes.dconst_0());
        assertEqualsDouble("dconst_1", 1.0, DoubleOpcodes.dconst_1());

        assertEqualsDouble("dadd", 0.0, DoubleOpcodes.dadd(0.0, 0.0));
        assertEqualsDouble("dadd", -0.5, DoubleOpcodes.dadd(-0.25, -0.25));
        assertEqualsDouble("dadd", 0.75, DoubleOpcodes.dadd(0.25, 0.5));
        assertEqualsDouble("dadd", 0.75, DoubleOpcodes.dadd(0.5, 0.25));
        assertEqualsDouble("dadd", 0.25, DoubleOpcodes.dadd(0.5, -0.25));

        assertEqualsDouble("dsub", 0.0, DoubleOpcodes.dsub(0.0, 0.0));
        assertEqualsDouble("dsub", 0.0, DoubleOpcodes.dsub(-0.25, -0.25));
        assertEqualsDouble("dsub", -0.25, DoubleOpcodes.dsub(0.25, 0.5));
        assertEqualsDouble("dsub", 0.25, DoubleOpcodes.dsub(0.5, 0.25));
        assertEqualsDouble("dsub", 0.75, DoubleOpcodes.dsub(0.5, -0.25));

        assertEqualsDouble("dmul", 0.0, DoubleOpcodes.dmul(0.0, 0.0));
        assertEqualsDouble("dmul", 1.25, DoubleOpcodes.dmul(0.5, 2.5));
        assertEqualsDouble("dmul", 1.25, DoubleOpcodes.dmul(2.5, 0.5));
        assertEqualsDouble("dmul", -1.25, DoubleOpcodes.dmul(-2.5, 0.5));
        assertEqualsDouble("dmul", -1.25, DoubleOpcodes.dmul(2.5, -0.5));
        assertEqualsDouble("dmul", 1.25, DoubleOpcodes.dmul(-2.5, -0.5));
        assertEqualsDouble("dmul", 1.0/0.0, DoubleOpcodes.dmul(0x1.fffffffffffffP+1023, 2.0));
        assertEqualsDouble("dmul", 1.0/0.0, DoubleOpcodes.dmul(1.0f/0.0, 2.0));
        assertEqualsDouble("dmul", -1.0/0.0, DoubleOpcodes.dmul(2.0, -1.0f/0.0));
        assertEqualsDouble("dmul", 0.0/0.0, DoubleOpcodes.dmul(2.0, 0.0f/0.0));

        assertEqualsDouble("ddiv", 0.0/0.0, DoubleOpcodes.ddiv(0.0, 0.0));
        assertEqualsDouble("ddiv", 1.0/0.0, DoubleOpcodes.ddiv(2.0, 0.0));
        assertEqualsDouble("ddiv", -1.0/0.0, DoubleOpcodes.ddiv(-2.0, 0.0));
        assertEqualsDouble("ddiv", 0.2, DoubleOpcodes.ddiv(0.5, 2.5));
        assertEqualsDouble("ddiv", 5.0, DoubleOpcodes.ddiv(2.5, 0.5));
        assertEqualsDouble("ddiv", -5.0, DoubleOpcodes.ddiv(-2.5, 0.5));
        assertEqualsDouble("ddiv", -5.0, DoubleOpcodes.ddiv(2.5, -0.5));
        assertEqualsDouble("ddiv", 5.0, DoubleOpcodes.ddiv(-2.5, -0.5));
        assertEqualsDouble("ddiv", 1.0/0.0, DoubleOpcodes.ddiv(0x1.fffffffffffffP+1023, 0.5));

        assertEqualsDouble("drem", 0.0, DoubleOpcodes.drem(0.0, 1.0));
        assertEqualsDouble("drem", 0.0/0.0, DoubleOpcodes.drem(0.0, 0.0));
        assertEqualsDouble("drem", 0.0/0.0, DoubleOpcodes.drem(1.0, 0.0));
        assertEqualsDouble("drem", 1.25, DoubleOpcodes.drem(5.75, 1.5));
        assertEqualsDouble("drem", -1.25, DoubleOpcodes.drem(-5.75, 1.5));
        assertEqualsDouble("drem", 1.25, DoubleOpcodes.drem(5.75, -1.5));
        assertEqualsDouble("drem", -1.25, DoubleOpcodes.drem(-5.75, -1.5));

        assertEqualsDouble("dneg", -0.0, DoubleOpcodes.dneg(0.0));
        assertEqualsDouble("dneg", -1.0, DoubleOpcodes.dneg(1.0));
        assertEqualsDouble("dneg", 1.0, DoubleOpcodes.dneg(-1.0));
        assertEqualsDouble("dneg", -0x0.0000000000001P-1022, DoubleOpcodes.dneg(0x0.0000000000001P-1022));
        assertEqualsDouble("dneg", -0x1.fffffffffffffP+1023, DoubleOpcodes.dneg(0x1.fffffffffffffP+1023));
        assertEqualsDouble("dneg", -1.0/0.0, DoubleOpcodes.dneg(1.0/0.0));
        assertEqualsDouble("dneg", 0.0/0.0, DoubleOpcodes.dneg(0.0/0.0));

        assertEqualsDouble("ldc(double)", 4.9e-324, DoubleOpcodes.ldc_min_double());
        assertEqualsDouble("ldc(double)", 1.7976931348623157e+308, DoubleOpcodes.ldc_max_double());

        assertEqualsDouble("i2d", 0.0, DoubleOpcodes.i2d(0));
        assertEqualsDouble("i2d", -0x1.0p31, DoubleOpcodes.i2d(0x80000000));
        assertEqualsDouble("i2d", 0x1.fffffffcp30, DoubleOpcodes.i2d(0x7fffffff));

        assertEqualsDouble("l2d", 0.0, DoubleOpcodes.l2d(0));
        assertEqualsDouble("l2d", -0x1.0p63, DoubleOpcodes.l2d(0x8000000000000000L));
        assertEqualsDouble("l2d", 0x1.0p63, DoubleOpcodes.l2d(0x7fffffffffffffffL));

        assertEqualsDouble("f2d", 0.0, DoubleOpcodes.f2d(0.0f));
        assertEqualsDouble("f2d", 0.0/0.0, DoubleOpcodes.f2d(0.0f/0.0f));
        assertEqualsDouble("f2d", -1.0/0.0, DoubleOpcodes.f2d(-1.0f/0.0f));
        assertEqualsDouble("f2d", 1.0/0.0, DoubleOpcodes.f2d(1.0f/0.0f));
        assertEqualsDouble("f2d", -0x1.fffffeP+127, DoubleOpcodes.f2d(-0x1.fffffeP+127f));
        assertEqualsDouble("f2d", 0x1.fffffeP+127, DoubleOpcodes.f2d(0x1.fffffeP+127f));

        assertEqualsInt("d2i", 0, DoubleOpcodes.d2i(0.0));
        assertEqualsInt("d2i", 0, DoubleOpcodes.d2i(0.0f/0.0));
        assertEqualsInt("d2i", -128, DoubleOpcodes.d2i(-128.75));
        assertEqualsInt("d2i", 128, DoubleOpcodes.d2i(128.75));
        assertEqualsInt("d2i", 0x80000000, DoubleOpcodes.d2i(-1.0/0.0));
        assertEqualsInt("d2i", 0x7fffffff, DoubleOpcodes.d2i(1.0/0.0));
        assertEqualsInt("d2i", 0x80000000, DoubleOpcodes.d2i(-0x1.fffffffffffffP+1023));
        assertEqualsInt("d2i", 0x7fffffff, DoubleOpcodes.d2i(0x1.fffffffffffffP+1023));

        assertEqualsLong("d2l", 0L, DoubleOpcodes.d2l(0.0));
        assertEqualsLong("d2l", 0L, DoubleOpcodes.d2l(0.0f/0.0));
        assertEqualsLong("d2l", -128L, DoubleOpcodes.d2l(-128.75));
        assertEqualsLong("d2l", 128L, DoubleOpcodes.d2l(128.75));
        assertEqualsLong("d2l", 0x8000000000000000L, DoubleOpcodes.d2l(-1.0/0.0));
        assertEqualsLong("d2l", 0x7fffffffffffffffL, DoubleOpcodes.d2l(1.0/0.0));
        assertEqualsLong("d2l", 0x8000000000000000L, DoubleOpcodes.d2l(-0x1.fffffffffffffP+1023));
        assertEqualsLong("d2l", 0x7fffffffffffffffL, DoubleOpcodes.d2l(0x1.fffffffffffffP+1023));

        assertEqualsFloat("d2f", 0.0f, DoubleOpcodes.d2f(0.0));
        assertEqualsFloat("d2f", 0.0f/0.0f, DoubleOpcodes.d2f(0.0/0.0));
        assertEqualsFloat("d2f", -0.0f, DoubleOpcodes.d2f(-0x0.0000000000001P-1022));
        assertEqualsFloat("d2f", 0.0f, DoubleOpcodes.d2f(0x0.0000000000001P-1022));
        assertEqualsFloat("d2f", -1.0f/0.0f, DoubleOpcodes.d2f(-1.0/0.0));
        assertEqualsFloat("d2f", 1.0f/0.0f, DoubleOpcodes.d2f(1.0/0.0));
        assertEqualsFloat("d2f", -1.0f/0.0f, DoubleOpcodes.d2f(-0x1.fffffffffffffP+1023));
        assertEqualsFloat("d2f", 1.0f/0.0f, DoubleOpcodes.d2f(0x1.fffffffffffffP+1023));
        assertEqualsFloat("d2f", -1.0f/0.0f, DoubleOpcodes.d2f(-0x1.ffffffP+127));
        assertEqualsFloat("d2f", 1.0f/0.0f, DoubleOpcodes.d2f(0x1.ffffffP+127));
        assertEqualsFloat("d2f", -0x1.fffffeP+127f, DoubleOpcodes.d2f(-0x1.fffffeP+127));
        assertEqualsFloat("d2f", 0x1.fffffeP+127f, DoubleOpcodes.d2f(0x1.fffffeP+127));

        assertEqualsInt("dcmpl", 0, DoubleOpcodes.dcmpl(0.0, 0.0));
        assertEqualsInt("dcmpl", 0, DoubleOpcodes.dcmpl(0.0, -0.0));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(0.0/0.0, 0.0));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(0.0, 0.0/0.0));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(0.0/0.0, 0.0/0.0));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(-0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
        assertEqualsInt("dcmpl", 1, DoubleOpcodes.dcmpl(-0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
        assertEqualsInt("dcmpl", 1, DoubleOpcodes.dcmpl(0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(-0x0.0000000000001P-1022, 0.0));
        assertEqualsInt("dcmpl", 1, DoubleOpcodes.dcmpl(0.0, -0x0.0000000000001P-1022));
        assertEqualsInt("dcmpl", 1, DoubleOpcodes.dcmpl(0x0.0000000000001P-1022, 0.0));
        assertEqualsInt("dcmpl", -1, DoubleOpcodes.dcmpl(0.0, 0x0.0000000000001P-1022));

        assertEqualsInt("dcmpg", 0, DoubleOpcodes.dcmpg(0.0, 0.0));
        assertEqualsInt("dcmpg", 0, DoubleOpcodes.dcmpg(0.0, -0.0));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0.0/0.0, 0.0));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0.0, 0.0/0.0));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0.0/0.0, 0.0/0.0));
        assertEqualsInt("dcmpg", -1, DoubleOpcodes.dcmpg(-0x1.fffffffffffffP+1023, -0x1.ffffffffffffeP+1023));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(-0x1.ffffffffffffeP+1023, -0x1.fffffffffffffP+1023));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0x1.fffffffffffffP+1023, 0x1.ffffffffffffeP+1023));
        assertEqualsInt("dcmpg", -1, DoubleOpcodes.dcmpg(0x1.ffffffffffffeP+1023, 0x1.fffffffffffffP+1023));
        assertEqualsInt("dcmpg", -1, DoubleOpcodes.dcmpg(-0x0.0000000000001P-1022, 0.0));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0.0, -0x0.0000000000001P-1022));
        assertEqualsInt("dcmpg", 1, DoubleOpcodes.dcmpg(0x0.0000000000001P-1022, 0.0));
        assertEqualsInt("dcmpg", -1, DoubleOpcodes.dcmpg(0.0, 0x0.0000000000001P-1022));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new DoubleTests().run());
    }
    
}
