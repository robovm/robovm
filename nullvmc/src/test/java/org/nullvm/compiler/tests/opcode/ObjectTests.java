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
public class ObjectTests extends AbstractOpcodeTests {
    
    public String run() {
        
        assertNull("aconst_null", ObjectOpcodes.aconst_null());

        assertEqualsInt("getstatic(boolean)", 0, ObjectOpcodes.getstatic_boolean() ? 1 : 0);
        ObjectOpcodes.putstatic_boolean(true);
        assertEqualsInt("getstatic(boolean)", 1, ObjectOpcodes.getstatic_boolean() ? 1 : 0);
        ObjectOpcodes.putstatic_boolean(false);
        assertEqualsInt("getstatic(boolean)", 0, ObjectOpcodes.getstatic_boolean() ? 1 : 0);

        assertEqualsInt("getstatic(byte)", 0, ObjectOpcodes.getstatic_byte());
        ObjectOpcodes.putstatic_byte((byte) 0x80);
        assertEqualsInt("getstatic(byte)", (byte) 0x80, ObjectOpcodes.getstatic_byte());
        ObjectOpcodes.putstatic_byte((byte) 0x7f);
        assertEqualsInt("getstatic(byte)", (byte) 0x7f, ObjectOpcodes.getstatic_byte());
        ObjectOpcodes.putstatic_byte((byte) 0);
        assertEqualsInt("getstatic(byte)", 0, ObjectOpcodes.getstatic_byte());

        assertEqualsInt("getstatic(int)", 0, ObjectOpcodes.getstatic_int());
        ObjectOpcodes.putstatic_int(0x80000000);
        assertEqualsInt("getstatic(int)", 0x80000000, ObjectOpcodes.getstatic_int());
        ObjectOpcodes.putstatic_int(0x7fffffff);
        assertEqualsInt("getstatic(int)", 0x7fffffff, ObjectOpcodes.getstatic_int());
        ObjectOpcodes.putstatic_int(0);
        assertEqualsInt("getstatic(int)", 0, ObjectOpcodes.getstatic_int());

        assertEqualsLong("getstatic(long)", 0, ObjectOpcodes.getstatic_long());
        ObjectOpcodes.putstatic_long(0x8000000000000000L);
        assertEqualsLong("getstatic(long)", 0x8000000000000000L, ObjectOpcodes.getstatic_long());
        ObjectOpcodes.putstatic_long(0x7fffffffffffffffL);
        assertEqualsLong("getstatic(long)", 0x7fffffffffffffffL, ObjectOpcodes.getstatic_long());
        ObjectOpcodes.putstatic_long(0);
        assertEqualsLong("getstatic(long)", 0, ObjectOpcodes.getstatic_long());

        assertEqualsLong("invokestatic", 0xFF813FE0L, ObjectOpcodes.invokestatic((byte) 0x7f, (short) 0x7fff, 0x7fffffff, 0x7fffffffffffffffL));

        assertNotNull("new_object", ObjectOpcodes.new_object());

        ObjectOpcodes t = new ObjectOpcodes();
        assertEqualsInt("getfield(boolean)", 0, ObjectOpcodes.getfield_boolean(t) ? 1 : 0);
        ObjectOpcodes.putfield_boolean(t, true);
        assertEqualsInt("getfield(boolean)", 1, ObjectOpcodes.getfield_boolean(t) ? 1 : 0);
        ObjectOpcodes.putfield_boolean(t, false);
        assertEqualsInt("getfield(boolean)", 0, ObjectOpcodes.getfield_boolean(t) ? 1 : 0);

        assertEqualsInt("getfield(byte)", 0, ObjectOpcodes.getfield_byte(t));
        ObjectOpcodes.putfield_byte(t, (byte) 0x80);
        assertEqualsInt("getfield(byte)", (byte) 0x80, ObjectOpcodes.getfield_byte(t));
        ObjectOpcodes.putfield_byte(t, (byte) 0x7f);
        assertEqualsInt("getfield(byte)", (byte) 0x7f, ObjectOpcodes.getfield_byte(t));
        ObjectOpcodes.putfield_byte(t, (byte) 0);
        assertEqualsInt("getfield(byte)", 0, ObjectOpcodes.getfield_byte(t));

        assertEqualsInt("getfield(int)", 0, ObjectOpcodes.getfield_int(t));
        ObjectOpcodes.putfield_int(t, 0x80000000);
        assertEqualsInt("getfield(int)", 0x80000000, ObjectOpcodes.getfield_int(t));
        ObjectOpcodes.putfield_int(t, 0x7fffffff);
        assertEqualsInt("getfield(int)", 0x7fffffff, ObjectOpcodes.getfield_int(t));
        ObjectOpcodes.putfield_int(t, 0);
        assertEqualsInt("getfield(int)", 0, ObjectOpcodes.getfield_int(t));

        assertEqualsLong("getfield(long)", 0, ObjectOpcodes.getfield_long(t));
        ObjectOpcodes.putfield_long(t, 0x8000000000000000L);
        assertEqualsLong("getfield(long)", 0x8000000000000000L, ObjectOpcodes.getfield_long(t));
        ObjectOpcodes.putfield_long(t, 0x7fffffffffffffffL);
        assertEqualsLong("getfield(long)", 0x7fffffffffffffffL, ObjectOpcodes.getfield_long(t));
        ObjectOpcodes.putfield_long(t, 0);
        assertEqualsLong("getfield(long)", 0, ObjectOpcodes.getfield_long(t));

        ObjectOpcodes u = ObjectOpcodes.new_ObjectOpcodes(true, (byte) 0x81, 0x80000001, 0x8000000000000001L);
        assertEqualsInt("getfield(boolean)", 1, ObjectOpcodes.getfield_boolean(u) ? 1 : 0);
        assertEqualsInt("getfield(byte)", (byte) 0x81, ObjectOpcodes.getfield_byte(u));
        assertEqualsInt("getfield(int)", 0x80000001, ObjectOpcodes.getfield_int(u));
        assertEqualsLong("getfield(long)", 0x8000000000000001L, ObjectOpcodes.getfield_long(u));

        assertEqualsLong("invokevirtual", 0x1007F3E23L, ObjectOpcodes.invokevirtual(u, (byte) 0x7f, (short) 0x7fff, 0x7fffffff, 0x7fffffffffffffffL));
        
        return getTestResult();
    }
    
    public static void main(String[] args) {
        System.out.print(new ObjectTests().run());
    }
    
}
