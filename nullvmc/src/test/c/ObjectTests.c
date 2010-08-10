#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    Class* ObjectOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ObjectOpcodes", "org_nullvm_compiler_tests_opcode_ObjectOpcodes", NULL);
    Object* (*aconst_null)(void) = j_get_method_impl(ObjectOpcodes, "aconst_null", "()Ljava/lang/Object;", ObjectOpcodes);
    void (*putstatic_boolean)(jboolean) = j_get_method_impl(ObjectOpcodes, "putstatic_boolean", "(Z)V", ObjectOpcodes);
    jboolean (*getstatic_boolean)(void) = j_get_method_impl(ObjectOpcodes, "getstatic_boolean", "()Z", ObjectOpcodes);
    void (*putstatic_byte)(jbyte) = j_get_method_impl(ObjectOpcodes, "putstatic_byte", "(B)V", ObjectOpcodes);
    jbyte (*getstatic_byte)(void) = j_get_method_impl(ObjectOpcodes, "getstatic_byte", "()B", ObjectOpcodes);
    void (*putstatic_int)(jint) = j_get_method_impl(ObjectOpcodes, "putstatic_int", "(I)V", ObjectOpcodes);
    jint (*getstatic_int)(void) = j_get_method_impl(ObjectOpcodes, "getstatic_int", "()I", ObjectOpcodes);
    void (*putstatic_long)(jlong) = j_get_method_impl(ObjectOpcodes, "putstatic_long", "(J)V", ObjectOpcodes);
    jlong (*getstatic_long)(void) = j_get_method_impl(ObjectOpcodes, "getstatic_long", "()J", ObjectOpcodes);
    jlong (*invokestatic)(jbyte, jshort, jint, jlong) = j_get_method_impl(ObjectOpcodes, "invokestatic", "(BSIJ)J", ObjectOpcodes);
    Object* (*new_object)(void) = j_get_method_impl(ObjectOpcodes, "new_object", "()Ljava/lang/Object;", ObjectOpcodes);
    Object* (*new_ObjectOpcodes)(jboolean, jbyte, jint, jlong) = j_get_method_impl(ObjectOpcodes, "new_ObjectOpcodes", "(ZBIJ)Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;", ObjectOpcodes);
    void (*putfield_boolean)(Object*, jboolean) = j_get_method_impl(ObjectOpcodes, "putfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;Z)V", ObjectOpcodes);
    jboolean (*getfield_boolean)(Object*) = j_get_method_impl(ObjectOpcodes, "getfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)Z", ObjectOpcodes);
    void (*putfield_byte)(Object*, jbyte) = j_get_method_impl(ObjectOpcodes, "putfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;B)V", ObjectOpcodes);
    jbyte (*getfield_byte)(Object*) = j_get_method_impl(ObjectOpcodes, "getfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)B", ObjectOpcodes);
    void (*putfield_int)(Object*, jint) = j_get_method_impl(ObjectOpcodes, "putfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;I)V", ObjectOpcodes);
    jint (*getfield_int)(Object*) = j_get_method_impl(ObjectOpcodes, "getfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)I", ObjectOpcodes);
    void (*putfield_long)(Object*, jlong) = j_get_method_impl(ObjectOpcodes, "putfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;J)V", ObjectOpcodes);
    jlong (*getfield_long)(Object*) = j_get_method_impl(ObjectOpcodes, "getfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)J", ObjectOpcodes);
    jlong (*invokevirtual)(Object*, jbyte, jshort, jint, jlong) = j_get_method_impl(ObjectOpcodes, "invokevirtual", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;BSIJ)J", ObjectOpcodes);

    assertNull("aconst_null", aconst_null());

    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean());
    putstatic_boolean(1);
    assertEqualsInt("getstatic(boolean)", 1, getstatic_boolean());
    putstatic_boolean(0);
    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean());

    assertEqualsInt("getstatic(byte)", 0, getstatic_byte());
    putstatic_byte((jbyte) 0x80);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x80, getstatic_byte());
    putstatic_byte((jbyte) 0x7f);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x7f, getstatic_byte());
    putstatic_byte(0);
    assertEqualsInt("getstatic(byte)", 0, getstatic_byte());

    assertEqualsInt("getstatic(int)", 0, getstatic_int());
    putstatic_int(0x80000000);
    assertEqualsInt("getstatic(int)", 0x80000000, getstatic_int());
    putstatic_int(0x7fffffff);
    assertEqualsInt("getstatic(int)", 0x7fffffff, getstatic_int());
    putstatic_int(0);
    assertEqualsInt("getstatic(int)", 0, getstatic_int());

    assertEqualsLong("getstatic(long)", 0, getstatic_long());
    putstatic_long(0x8000000000000000);
    assertEqualsLong("getstatic(long)", 0x8000000000000000, getstatic_long());
    putstatic_long(0x7fffffffffffffff);
    assertEqualsLong("getstatic(long)", 0x7fffffffffffffff, getstatic_long());
    putstatic_long(0);
    assertEqualsLong("getstatic(long)", 0, getstatic_long());

    assertEqualsLong("invokestatic", 0xFF813FE0, invokestatic(0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    assertNotNull("new_object", new_object());

    Object* t = nvmNewInstance(ObjectOpcodes);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(t));
    putfield_boolean(t, 1);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(t));
    putfield_boolean(t, 0);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(t));

    assertEqualsInt("getfield(byte)", 0, getfield_byte(t));
    putfield_byte(t, (jbyte) 0x80);
    assertEqualsInt("getfield(byte)", (jbyte) 0x80, getfield_byte(t));
    putfield_byte(t, (jbyte) 0x7f);
    assertEqualsInt("getfield(byte)", (jbyte) 0x7f, getfield_byte(t));
    putfield_byte(t, 0);
    assertEqualsInt("getfield(byte)", 0, getfield_byte(t));

    assertEqualsInt("getfield(int)", 0, getfield_int(t));
    putfield_int(t, 0x80000000);
    assertEqualsInt("getfield(int)", 0x80000000, getfield_int(t));
    putfield_int(t, 0x7fffffff);
    assertEqualsInt("getfield(int)", 0x7fffffff, getfield_int(t));
    putfield_int(t, 0);
    assertEqualsInt("getfield(int)", 0, getfield_int(t));

    assertEqualsLong("getfield(long)", 0, getfield_long(t));
    putfield_long(t, 0x8000000000000000);
    assertEqualsLong("getfield(long)", 0x8000000000000000, getfield_long(t));
    putfield_long(t, 0x7fffffffffffffff);
    assertEqualsLong("getfield(long)", 0x7fffffffffffffff, getfield_long(t));
    putfield_long(t, 0);
    assertEqualsLong("getfield(long)", 0, getfield_long(t));

    Object* u = new_ObjectOpcodes(1, 0x81, 0x80000001, 0x8000000000000001);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(u));
    assertEqualsInt("getfield(byte)", (jbyte) 0x81, getfield_byte(u));
    assertEqualsInt("getfield(int)", 0x80000001, getfield_int(u));
    assertEqualsLong("getfield(long)", 0x8000000000000001, getfield_long(u));

    assertEqualsLong("invokevirtual", 0x1007F3E23, invokevirtual(u, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    print_test_result();

    return 0;
}

