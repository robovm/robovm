#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* ObjectOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/ObjectOpcodes");
    Object* (*aconst_null)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "aconst_null", "()Ljava/lang/Object;")->impl;
    void (*putstatic_boolean)(void*, Env*, jboolean) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_boolean", "(Z)V")->impl;
    jboolean (*getstatic_boolean)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_boolean", "()Z")->impl;
    void (*putstatic_byte)(void*, Env*, jbyte) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_byte", "(B)V")->impl;
    jbyte (*getstatic_byte)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_byte", "()B")->impl;
    void (*putstatic_int)(void*, Env*, jint) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_int", "(I)V")->impl;
    jint (*getstatic_int)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_int", "()I")->impl;
    void (*putstatic_long)(void*, Env*, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "putstatic_long", "(J)V")->impl;
    jlong (*getstatic_long)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "getstatic_long", "()J")->impl;
    jlong (*invokestatic)(void*, Env*, jbyte, jshort, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "invokestatic", "(BSIJ)J")->impl;
    Object* (*new_object)(void*, Env*) = nvmGetClassMethod(env, ObjectOpcodes, "new_object", "()Ljava/lang/Object;")->impl;
    Object* (*new_ObjectOpcodes)(void*, Env*, jboolean, jbyte, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "new_ObjectOpcodes", "(ZBIJ)Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;")->impl;
    void (*putfield_boolean)(void*, Env*, Object*, jboolean) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;Z)V")->impl;
    jboolean (*getfield_boolean)(void*, Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_boolean", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)Z")->impl;
    void (*putfield_byte)(void*, Env*, Object*, jbyte) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;B)V")->impl;
    jbyte (*getfield_byte)(void*, Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_byte", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)B")->impl;
    void (*putfield_int)(void*, Env*, Object*, jint) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;I)V")->impl;
    jint (*getfield_int)(void*, Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_int", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)I")->impl;
    void (*putfield_long)(void*, Env*, Object*, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "putfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;J)V")->impl;
    jlong (*getfield_long)(void*, Env*, Object*) = nvmGetClassMethod(env, ObjectOpcodes, "getfield_long", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;)J")->impl;
    jlong (*invokevirtual)(void*, Env*, Object*, jbyte, jshort, jint, jlong) = nvmGetClassMethod(env, ObjectOpcodes, "invokevirtual", "(Lorg/nullvm/compiler/tests/opcode/ObjectOpcodes;BSIJ)J")->impl;

    assertNull("aconst_null", aconst_null(NULL, env));

    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(NULL, env));
    putstatic_boolean(NULL, env, 1);
    assertEqualsInt("getstatic(boolean)", 1, getstatic_boolean(NULL, env));
    putstatic_boolean(NULL, env, 0);
    assertEqualsInt("getstatic(boolean)", 0, getstatic_boolean(NULL, env));

    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(NULL, env));
    putstatic_byte(NULL, env, (jbyte) 0x80);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x80, getstatic_byte(NULL, env));
    putstatic_byte(NULL, env, (jbyte) 0x7f);
    assertEqualsInt("getstatic(byte)", (jbyte) 0x7f, getstatic_byte(NULL, env));
    putstatic_byte(NULL, env, 0);
    assertEqualsInt("getstatic(byte)", 0, getstatic_byte(NULL, env));

    assertEqualsInt("getstatic(int)", 0, getstatic_int(NULL, env));
    putstatic_int(NULL, env, 0x80000000);
    assertEqualsInt("getstatic(int)", 0x80000000, getstatic_int(NULL, env));
    putstatic_int(NULL, env, 0x7fffffff);
    assertEqualsInt("getstatic(int)", 0x7fffffff, getstatic_int(NULL, env));
    putstatic_int(NULL, env, 0);
    assertEqualsInt("getstatic(int)", 0, getstatic_int(NULL, env));

    assertEqualsLong("getstatic(long)", 0, getstatic_long(NULL, env));
    putstatic_long(NULL, env, 0x8000000000000000);
    assertEqualsLong("getstatic(long)", 0x8000000000000000, getstatic_long(NULL, env));
    putstatic_long(NULL, env, 0x7fffffffffffffff);
    assertEqualsLong("getstatic(long)", 0x7fffffffffffffff, getstatic_long(NULL, env));
    putstatic_long(NULL, env, 0);
    assertEqualsLong("getstatic(long)", 0, getstatic_long(NULL, env));

    assertEqualsLong("invokestatic", 0xFF813FE0, invokestatic(NULL, env, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    assertNotNull("new_object", new_object(NULL, env));

    Object* t = nvmAllocateObject(env, ObjectOpcodes);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(NULL, env, t));
    putfield_boolean(NULL, env, t, 1);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(NULL, env, t));
    putfield_boolean(NULL, env, t, 0);
    assertEqualsInt("getfield(boolean)", 0, getfield_boolean(NULL, env, t));

    assertEqualsInt("getfield(byte)", 0, getfield_byte(NULL, env, t));
    putfield_byte(NULL, env, t, (jbyte) 0x80);
    assertEqualsInt("getfield(byte)", (jbyte) 0x80, getfield_byte(NULL, env, t));
    putfield_byte(NULL, env, t, (jbyte) 0x7f);
    assertEqualsInt("getfield(byte)", (jbyte) 0x7f, getfield_byte(NULL, env, t));
    putfield_byte(NULL, env, t, 0);
    assertEqualsInt("getfield(byte)", 0, getfield_byte(NULL, env, t));

    assertEqualsInt("getfield(int)", 0, getfield_int(NULL, env, t));
    putfield_int(NULL, env, t, 0x80000000);
    assertEqualsInt("getfield(int)", 0x80000000, getfield_int(NULL, env, t));
    putfield_int(NULL, env, t, 0x7fffffff);
    assertEqualsInt("getfield(int)", 0x7fffffff, getfield_int(NULL, env, t));
    putfield_int(NULL, env, t, 0);
    assertEqualsInt("getfield(int)", 0, getfield_int(NULL, env, t));

    assertEqualsLong("getfield(long)", 0, getfield_long(NULL, env, t));
    putfield_long(NULL, env, t, 0x8000000000000000);
    assertEqualsLong("getfield(long)", 0x8000000000000000, getfield_long(NULL, env, t));
    putfield_long(NULL, env, t, 0x7fffffffffffffff);
    assertEqualsLong("getfield(long)", 0x7fffffffffffffff, getfield_long(NULL, env, t));
    putfield_long(NULL, env, t, 0);
    assertEqualsLong("getfield(long)", 0, getfield_long(NULL, env, t));

    Object* u = new_ObjectOpcodes(NULL, env, 1, 0x81, 0x80000001, 0x8000000000000001);
    assertEqualsInt("getfield(boolean)", 1, getfield_boolean(NULL, env, u));
    assertEqualsInt("getfield(byte)", (jbyte) 0x81, getfield_byte(NULL, env, u));
    assertEqualsInt("getfield(int)", 0x80000001, getfield_int(NULL, env, u));
    assertEqualsLong("getfield(long)", 0x8000000000000001, getfield_long(NULL, env, u));

    assertEqualsLong("invokevirtual", 0x1007F3E23, invokevirtual(NULL, env, u, 0x7f, 0x7fff, 0x7fffffff, 0x7fffffffffffffff));

    print_test_result();

    return 0;
}

