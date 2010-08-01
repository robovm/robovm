#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* ObjectOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ObjectOpcodes", "org_nullvm_compiler_tests_opcode_ObjectOpcodes", NULL);
    jobject* (*aconst_null)(jclass*) = j_get_method_impl(ObjectOpcodes, "aconst_null", "()Ljava/lang/Object;", ObjectOpcodes);
    void (*putstatic_boolean)(jclass*, jboolean) = j_get_method_impl(ObjectOpcodes, "putstatic_boolean", "(Z)V", ObjectOpcodes);
    jboolean (*getstatic_boolean)(jclass*) = j_get_method_impl(ObjectOpcodes, "getstatic_boolean", "()Z", ObjectOpcodes);
    void (*putstatic_byte)(jclass*, jbyte) = j_get_method_impl(ObjectOpcodes, "putstatic_byte", "(B)V", ObjectOpcodes);
    jbyte (*getstatic_byte)(jclass*) = j_get_method_impl(ObjectOpcodes, "getstatic_byte", "()B", ObjectOpcodes);
    void (*putstatic_int)(jclass*, jint) = j_get_method_impl(ObjectOpcodes, "putstatic_int", "(I)V", ObjectOpcodes);
    jint (*getstatic_int)(jclass*) = j_get_method_impl(ObjectOpcodes, "getstatic_int", "()I", ObjectOpcodes);
    void (*putstatic_long)(jclass*, jlong) = j_get_method_impl(ObjectOpcodes, "putstatic_long", "(J)V", ObjectOpcodes);
    jlong (*getstatic_long)(jclass*) = j_get_method_impl(ObjectOpcodes, "getstatic_long", "()J", ObjectOpcodes);
    jlong (*invokestatic)(jclass*, jbyte, jshort, jint, jlong) = j_get_method_impl(ObjectOpcodes, "invokestatic", "(BSIJ)J", ObjectOpcodes);
    jobject* (*new_object)(jclass*) = j_get_method_impl(ObjectOpcodes, "new_object", "()Ljava/lang/Object;", ObjectOpcodes);
    jobject* (*new_ObjectOpcodes)(jclass*, jboolean, jbyte, jint, jlong) = j_get_method_impl(ObjectOpcodes, "new_ObjectOpcodes", "(ZBIJ)Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;", ObjectOpcodes);
    void (*putfield_boolean)(jclass*, jobject*, jboolean) = j_get_method_impl(ObjectOpcodes, "putfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;Z)V", ObjectOpcodes);
    jboolean (*getfield_boolean)(jclass*, jobject*) = j_get_method_impl(ObjectOpcodes, "getfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)Z", ObjectOpcodes);
    void (*putfield_byte)(jclass*, jobject*, jbyte) = j_get_method_impl(ObjectOpcodes, "putfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;B)V", ObjectOpcodes);
    jbyte (*getfield_byte)(jclass*, jobject*) = j_get_method_impl(ObjectOpcodes, "getfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)B", ObjectOpcodes);
    void (*putfield_int)(jclass*, jobject*, jint) = j_get_method_impl(ObjectOpcodes, "putfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;I)V", ObjectOpcodes);
    jint (*getfield_int)(jclass*, jobject*) = j_get_method_impl(ObjectOpcodes, "getfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)I", ObjectOpcodes);
    void (*putfield_long)(jclass*, jobject*, jlong) = j_get_method_impl(ObjectOpcodes, "putfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;J)V", ObjectOpcodes);
    jlong (*getfield_long)(jclass*, jobject*) = j_get_method_impl(ObjectOpcodes, "getfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)J", ObjectOpcodes);
    jlong (*invokevirtual)(jclass*, jobject*, jbyte, jshort, jint, jlong) = j_get_method_impl(ObjectOpcodes, "invokevirtual", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;BSIJ)J", ObjectOpcodes);

    assertNull("aconst_null", aconst_null(ObjectOpcodes));

    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(ObjectOpcodes));
    putstatic_boolean(ObjectOpcodes, 1);
    assertEqualsInt("getstatic(boolean)", 1, getstatic_boolean(ObjectOpcodes));
    putstatic_boolean(ObjectOpcodes, 0);
    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(ObjectOpcodes));

    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(ObjectOpcodes));
    putstatic_byte(ObjectOpcodes, (jbyte) 0x80);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x80, getstatic_byte(ObjectOpcodes));
    putstatic_byte(ObjectOpcodes, (jbyte) 0x7f);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x7f, getstatic_byte(ObjectOpcodes));
    putstatic_byte(ObjectOpcodes, 0);
    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(ObjectOpcodes));

    assertEqualsInt("getstatic(int)", 0, getstatic_int(ObjectOpcodes));
    putstatic_int(ObjectOpcodes, 0x80000000);
    assertEqualsInt("getstatic(int)", 0x80000000, getstatic_int(ObjectOpcodes));
    putstatic_int(ObjectOpcodes, 0x7fffffff);
    assertEqualsInt("getstatic(int)", 0x7fffffff, getstatic_int(ObjectOpcodes));
    putstatic_int(ObjectOpcodes, 0);
    assertEqualsInt("getstatic(int)", 0, getstatic_int(ObjectOpcodes));

    assertEqualsLong("getstatic(long)", 0, getstatic_long(ObjectOpcodes));
    putstatic_long(ObjectOpcodes, 0x8000000000000000);
    assertEqualsLong("getstatic(long)", 0x8000000000000000, getstatic_long(ObjectOpcodes));
    putstatic_long(ObjectOpcodes, 0x7fffffffffffffff);
    assertEqualsLong("getstatic(long)", 0x7fffffffffffffff, getstatic_long(ObjectOpcodes));
    putstatic_long(ObjectOpcodes, 0);
    assertEqualsLong("getstatic(long)", 0, getstatic_long(ObjectOpcodes));

    assertEqualsLong("invokestatic", 0xFF813FE0, invokestatic(ObjectOpcodes, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    assertNotNull("new_object", new_object(ObjectOpcodes));

    jobject* t = nvmNewInstance(ObjectOpcodes);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(ObjectOpcodes, t));
    putfield_boolean(ObjectOpcodes, t, 1);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(ObjectOpcodes, t));
    putfield_boolean(ObjectOpcodes, t, 0);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(ObjectOpcodes, t));

    assertEqualsInt("getfield(byte)", 0, getfield_byte(ObjectOpcodes, t));
    putfield_byte(ObjectOpcodes, t, (jbyte) 0x80);
    assertEqualsInt("getfield(byte)", (jbyte) 0x80, getfield_byte(ObjectOpcodes, t));
    putfield_byte(ObjectOpcodes, t, (jbyte) 0x7f);
    assertEqualsInt("getfield(byte)", (jbyte) 0x7f, getfield_byte(ObjectOpcodes, t));
    putfield_byte(ObjectOpcodes, t, 0);
    assertEqualsInt("getfield(byte)", 0, getfield_byte(ObjectOpcodes, t));

    assertEqualsInt("getfield(int)", 0, getfield_int(ObjectOpcodes, t));
    putfield_int(ObjectOpcodes, t, 0x80000000);
    assertEqualsInt("getfield(int)", 0x80000000, getfield_int(ObjectOpcodes, t));
    putfield_int(ObjectOpcodes, t, 0x7fffffff);
    assertEqualsInt("getfield(int)", 0x7fffffff, getfield_int(ObjectOpcodes, t));
    putfield_int(ObjectOpcodes, t, 0);
    assertEqualsInt("getfield(int)", 0, getfield_int(ObjectOpcodes, t));

    assertEqualsLong("getfield(long)", 0, getfield_long(ObjectOpcodes, t));
    putfield_long(ObjectOpcodes, t, 0x8000000000000000);
    assertEqualsLong("getfield(long)", 0x8000000000000000, getfield_long(ObjectOpcodes, t));
    putfield_long(ObjectOpcodes, t, 0x7fffffffffffffff);
    assertEqualsLong("getfield(long)", 0x7fffffffffffffff, getfield_long(ObjectOpcodes, t));
    putfield_long(ObjectOpcodes, t, 0);
    assertEqualsLong("getfield(long)", 0, getfield_long(ObjectOpcodes, t));

    jobject* u = new_ObjectOpcodes(ObjectOpcodes, 1, 0x81, 0x80000001, 0x8000000000000001);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(ObjectOpcodes, u));
    assertEqualsInt("getfield(byte)", (jbyte) 0x81, getfield_byte(ObjectOpcodes, u));
    assertEqualsInt("getfield(int)", 0x80000001, getfield_int(ObjectOpcodes, u));
    assertEqualsLong("getfield(long)", 0x8000000000000001, getfield_long(ObjectOpcodes, u));

    assertEqualsLong("invokevirtual", 0x1007F3E23, invokevirtual(ObjectOpcodes, u, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    print_test_result();

    return 0;
}

