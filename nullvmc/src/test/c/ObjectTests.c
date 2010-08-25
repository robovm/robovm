#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* ObjectOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/ObjectOpcodes");
    Object* (*aconst_null)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "aconst_null", "()Ljava/lang/Object;")->impl;
    void (*putstatic_boolean)(Env*, jboolean) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_boolean", "(Z)V")->impl;
    jboolean (*getstatic_boolean)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_boolean", "()Z")->impl;
    void (*putstatic_byte)(Env*, jbyte) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_byte", "(B)V")->impl;
    jbyte (*getstatic_byte)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_byte", "()B")->impl;
    void (*putstatic_int)(Env*, jint) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_int", "(I)V")->impl;
    jint (*getstatic_int)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_int", "()I")->impl;
    void (*putstatic_long)(Env*, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_long", "(J)V")->impl;
    jlong (*getstatic_long)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_long", "()J")->impl;
    jlong (*invokestatic)(Env*, jbyte, jshort, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "invokestatic", "(BSIJ)J")->impl;
    Object* (*new_object)(Env*) = nvmGetClassMethod(env, ObjectOpcodes, "new_object", "()Ljava/lang/Object;")->impl;
    Object* (*new_ObjectOpcodes)(Env*, jboolean, jbyte, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "new_ObjectOpcodes", "(ZBIJ)Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;")->impl;
    void (*putfield_boolean)(Env*, Object*, jboolean) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;Z)V")->impl;
    jboolean (*getfield_boolean)(Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)Z")->impl;
    void (*putfield_byte)(Env*, Object*, jbyte) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;B)V")->impl;
    jbyte (*getfield_byte)(Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)B")->impl;
    void (*putfield_int)(Env*, Object*, jint) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;I)V")->impl;
    jint (*getfield_int)(Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)I")->impl;
    void (*putfield_long)(Env*, Object*, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;J)V")->impl;
    jlong (*getfield_long)(Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)J")->impl;
    jlong (*invokevirtual)(Env*, Object*, jbyte, jshort, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "invokevirtual", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;BSIJ)J")->impl;

    assertNull("aconst_null", aconst_null(env));

    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(env));
    putstatic_boolean(env, 1);
    assertEqualsInt("getstatic(boolean)", 1, getstatic_boolean(env));
    putstatic_boolean(env, 0);
    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(env));

    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(env));
    putstatic_byte(env, (jbyte) 0x80);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x80, getstatic_byte(env));
    putstatic_byte(env, (jbyte) 0x7f);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x7f, getstatic_byte(env));
    putstatic_byte(env, 0);
    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(env));

    assertEqualsInt("getstatic(int)", 0, getstatic_int(env));
    putstatic_int(env, 0x80000000);
    assertEqualsInt("getstatic(int)", 0x80000000, getstatic_int(env));
    putstatic_int(env, 0x7fffffff);
    assertEqualsInt("getstatic(int)", 0x7fffffff, getstatic_int(env));
    putstatic_int(env, 0);
    assertEqualsInt("getstatic(int)", 0, getstatic_int(env));

    assertEqualsLong("getstatic(long)", 0, getstatic_long(env));
    putstatic_long(env, 0x8000000000000000);
    assertEqualsLong("getstatic(long)", 0x8000000000000000, getstatic_long(env));
    putstatic_long(env, 0x7fffffffffffffff);
    assertEqualsLong("getstatic(long)", 0x7fffffffffffffff, getstatic_long(env));
    putstatic_long(env, 0);
    assertEqualsLong("getstatic(long)", 0, getstatic_long(env));

    assertEqualsLong("invokestatic", 0xFF813FE0, invokestatic(env, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    assertNotNull("new_object", new_object(env));

    Object* t = nvmAllocateObject(env, ObjectOpcodes);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(env, t));
    putfield_boolean(env, t, 1);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(env, t));
    putfield_boolean(env, t, 0);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(env, t));

    assertEqualsInt("getfield(byte)", 0, getfield_byte(env, t));
    putfield_byte(env, t, (jbyte) 0x80);
    assertEqualsInt("getfield(byte)", (jbyte) 0x80, getfield_byte(env, t));
    putfield_byte(env, t, (jbyte) 0x7f);
    assertEqualsInt("getfield(byte)", (jbyte) 0x7f, getfield_byte(env, t));
    putfield_byte(env, t, 0);
    assertEqualsInt("getfield(byte)", 0, getfield_byte(env, t));

    assertEqualsInt("getfield(int)", 0, getfield_int(env, t));
    putfield_int(env, t, 0x80000000);
    assertEqualsInt("getfield(int)", 0x80000000, getfield_int(env, t));
    putfield_int(env, t, 0x7fffffff);
    assertEqualsInt("getfield(int)", 0x7fffffff, getfield_int(env, t));
    putfield_int(env, t, 0);
    assertEqualsInt("getfield(int)", 0, getfield_int(env, t));

    assertEqualsLong("getfield(long)", 0, getfield_long(env, t));
    putfield_long(env, t, 0x8000000000000000);
    assertEqualsLong("getfield(long)", 0x8000000000000000, getfield_long(env, t));
    putfield_long(env, t, 0x7fffffffffffffff);
    assertEqualsLong("getfield(long)", 0x7fffffffffffffff, getfield_long(env, t));
    putfield_long(env, t, 0);
    assertEqualsLong("getfield(long)", 0, getfield_long(env, t));

    Object* u = new_ObjectOpcodes(env, 1, 0x81, 0x80000001, 0x8000000000000001);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(env, u));
    assertEqualsInt("getfield(byte)", (jbyte) 0x81, getfield_byte(env, u));
    assertEqualsInt("getfield(int)", 0x80000001, getfield_int(env, u));
    assertEqualsLong("getfield(long)", 0x8000000000000001, getfield_long(env, u));

    assertEqualsLong("invokevirtual", 0x1007F3E23, invokevirtual(env, u, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    print_test_result();

    return 0;
}

