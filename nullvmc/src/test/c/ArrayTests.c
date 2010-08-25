#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    Options options;
    Env* env = nvmStartup(&options);

    Class* ArrayOpcodes = nvmFindClass(env, "org/nullvm/compiler/tests/opcode/ArrayOpcodes");
    IntArray* (*newarray_int)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_int", "(I)[I")->impl;
    ByteArray* (*newarray_byte)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_byte", "(I)[B")->impl;
    ShortArray* (*newarray_short)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_short", "(I)[S")->impl;
    CharArray* (*newarray_char)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_char", "(I)[C")->impl;
    BooleanArray* (*newarray_boolean)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_boolean", "(I)[Z")->impl;
    FloatArray* (*newarray_float)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_float", "(I)[F")->impl;
    LongArray* (*newarray_long)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_long", "(I)[J")->impl;
    DoubleArray* (*newarray_double)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_double", "(I)[D")->impl;
    ObjectArray* (*newarray_object)(Env*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "newarray_object", "(I)[Ljava/lang/Object;")->impl;
    ObjectArray* (*multianewarray_int)(Env*, jint, jint, jint) = nvmGetClassMethod(env, ArrayOpcodes, "multianewarray_int", "(III)[[[I")->impl;
    ObjectArray* (*multianewarray_int_partial)(Env*, jint, jint) = nvmGetClassMethod(env, ArrayOpcodes, "multianewarray_int_partial", "(II)[[[I")->impl;
    ObjectArray* (*multianewarray_byte)(Env*, jint, jint) = nvmGetClassMethod(env, ArrayOpcodes, "multianewarray_byte", "(II)[[B")->impl;
    jint (*arraylength_int)(Env*, IntArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_int", "([I)I")->impl;
    jint (*arraylength_byte)(Env*, ByteArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_byte", "([B)I")->impl;
    jint (*arraylength_short)(Env*, ShortArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_short", "([S)I")->impl;
    jint (*arraylength_char)(Env*, CharArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_char", "([C)I")->impl;
    jint (*arraylength_boolean)(Env*, BooleanArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_boolean", "([Z)I")->impl;
    jint (*arraylength_float)(Env*, FloatArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_float", "([F)I")->impl;
    jint (*arraylength_long)(Env*, LongArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_long", "([J)I")->impl;
    jint (*arraylength_double)(Env*, DoubleArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_double", "([D)I")->impl;
    jint (*arraylength_object)(Env*, ObjectArray*) = nvmGetClassMethod(env, ArrayOpcodes, "arraylength_object", "([Ljava/lang/Object;)I")->impl;
    jint (*iaload)(Env*, IntArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "iaload", "([II)I")->impl;
    void (*iastore)(Env*, IntArray*, jint, jint) = nvmGetClassMethod(env, ArrayOpcodes, "iastore", "([III)V")->impl;
    jbyte (*baload_byte)(Env*, ByteArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "baload_byte", "([BI)B")->impl;
    void (*bastore_byte)(Env*, ByteArray*, jint, jbyte) = nvmGetClassMethod(env, ArrayOpcodes, "bastore_byte", "([BIB)V")->impl;
    jboolean (*baload_boolean)(Env*, BooleanArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "baload_boolean", "([ZI)Z")->impl;
    void (*bastore_boolean)(Env*, BooleanArray*, jint, jbyte) = nvmGetClassMethod(env, ArrayOpcodes, "bastore_boolean", "([ZIZ)V")->impl;
    jshort (*saload)(Env*, ShortArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "saload", "([SI)S")->impl;
    void (*sastore)(Env*, ShortArray*, jint, jshort) = nvmGetClassMethod(env, ArrayOpcodes, "sastore", "([SIS)V")->impl;
    jchar (*caload)(Env*, CharArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "caload", "([CI)C")->impl;
    void (*castore)(Env*, CharArray*, jint, jchar) = nvmGetClassMethod(env, ArrayOpcodes, "castore", "([CIC)V")->impl;
    jfloat (*faload)(Env*, FloatArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "faload", "([FI)F")->impl;
    void (*fastore)(Env*, FloatArray*, jint, jfloat) = nvmGetClassMethod(env, ArrayOpcodes, "fastore", "([FIF)V")->impl;
    jlong (*laload)(Env*, LongArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "laload", "([JI)J")->impl;
    void (*lastore)(Env*, LongArray*, jint, jlong) = nvmGetClassMethod(env, ArrayOpcodes, "lastore", "([JIJ)V")->impl;
    jdouble (*daload)(Env*, DoubleArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "daload", "([DI)D")->impl;
    void (*dastore)(Env*, DoubleArray*, jint, jdouble) = nvmGetClassMethod(env, ArrayOpcodes, "dastore", "([DID)V")->impl;
    Object* (*aaload)(Env*, ObjectArray*, jint) = nvmGetClassMethod(env, ArrayOpcodes, "aaload", "([Ljava/lang/Object;I)Ljava/lang/Object;")->impl;
    void (*aastore)(Env*, ObjectArray*, jint, Object*) = nvmGetClassMethod(env, ArrayOpcodes, "aastore", "([Ljava/lang/Object;ILjava/lang/Object;)V")->impl;

    Object* a = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));
    Object* b = nvmAllocateObject(env, nvmFindClass(env, "java/lang/Object"));

    IntArray* intArray = newarray_int(env, 3);
    assertNotNull("newarray_int", (Object*) intArray);
    assertEqualsInt("arraylength_int", 3, arraylength_int(env, intArray));
    assertEqualsInt("iaload", 0, iaload(env, intArray, 0));
    assertEqualsInt("iaload", 0, iaload(env, intArray, 1));
    assertEqualsInt("iaload", 0, iaload(env, intArray, 2));
    iastore(env, intArray, 0, 1);
    iastore(env, intArray, 1, 0xffffffff);
    iastore(env, intArray, 2, 0x80000000);
    assertEqualsInt("iaload", 1, iaload(env, intArray, 0));
    assertEqualsInt("iaload", 0xffffffff, iaload(env, intArray, 1));
    assertEqualsInt("iaload", 0x80000000, iaload(env, intArray, 2));
    assertEqualsInt("arraylength_int", 0, arraylength_int(env, newarray_int(env, 0)));

    ByteArray* byteArray = newarray_byte(env, 3);
    assertNotNull("newarray_byte", (Object*) byteArray);
    assertEqualsInt("arraylength_byte", 3, arraylength_byte(env, byteArray));
    assertEqualsInt("baload_byte", 0, baload_byte(env, byteArray, 0));
    assertEqualsInt("baload_byte", 0, baload_byte(env, byteArray, 1));
    assertEqualsInt("baload_byte", 0, baload_byte(env, byteArray, 2));
    bastore_byte(env, byteArray, 0, 1);
    bastore_byte(env, byteArray, 1, (jbyte) 0x7f);
    bastore_byte(env, byteArray, 2, (jbyte) 0x80);
    assertEqualsInt("baload_byte", 1, baload_byte(env, byteArray, 0));
    assertEqualsInt("baload_byte", (jbyte) 0x7f, baload_byte(env, byteArray, 1));
    assertEqualsInt("baload_byte", (jbyte) 0x80, baload_byte(env, byteArray, 2));

    ShortArray* shortArray = newarray_short(env, 3);
    assertNotNull("newarray_short", (Object*) shortArray);
    assertEqualsInt("arraylength_short", 3, arraylength_short(env, shortArray));
    assertEqualsInt("saload", 0, saload(env, shortArray, 0));
    assertEqualsInt("saload", 0, saload(env, shortArray, 1));
    assertEqualsInt("saload", 0, saload(env, shortArray, 2));
    sastore(env, shortArray, 0, 1);
    sastore(env, shortArray, 1, (jshort) 0x7fff);
    sastore(env, shortArray, 2, (jshort) 0x8000);
    assertEqualsInt("saload", 1, saload(env, shortArray, 0));
    assertEqualsInt("saload", (jshort) 0x7fff, saload(env, shortArray, 1));
    assertEqualsInt("saload", (jshort) 0x8000, saload(env, shortArray, 2));

    CharArray* charArray = newarray_char(env, 3);
    assertNotNull("newarray_char", (Object*) charArray);
    assertEqualsInt("arraylength_char", 3, arraylength_char(env, charArray));
    assertEqualsInt("caload", 0, caload(env, charArray, 0));
    assertEqualsInt("caload", 0, caload(env, charArray, 1));
    assertEqualsInt("caload", 0, caload(env, charArray, 2));
    castore(env, charArray, 0, 1);
    castore(env, charArray, 1, 0x7fff);
    castore(env, charArray, 2, 0x8000);
    assertEqualsInt("caload", 1, caload(env, charArray, 0));
    assertEqualsInt("caload", 0x7fff, caload(env, charArray, 1));
    assertEqualsInt("caload", 0x8000, caload(env, charArray, 2));

    BooleanArray* booleanArray = newarray_boolean(env, 2);
    assertNotNull("newarray_boolean", (Object*) booleanArray);
    assertEqualsInt("arraylength_boolean", 2, arraylength_boolean(env, booleanArray));
    assertEqualsInt("baload_boolean", 0, baload_boolean(env, booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(env, booleanArray, 1));
    bastore_boolean(env, booleanArray, 0, 1);
    bastore_boolean(env, booleanArray, 1, 0);
    assertEqualsInt("baload_boolean", 1, baload_boolean(env, booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(env, booleanArray, 1));

    FloatArray* floatArray = newarray_float(env, 3);
    assertNotNull("newarray_float", (Object*) floatArray);
    assertEqualsInt("arraylength_float", 3, arraylength_float(env, floatArray));
    assertEqualsFloat("faload", 0.0f, faload(env, floatArray, 0));
    assertEqualsFloat("faload", 0.0f, faload(env, floatArray, 1));
    assertEqualsFloat("faload", 0.0f, faload(env, floatArray, 2));
    fastore(env, floatArray, 0, 1.25f);
    fastore(env, floatArray, 1, 2.5f);
    fastore(env, floatArray, 2, 3.75f);
    assertEqualsFloat("faload", 1.25f, faload(env, floatArray, 0));
    assertEqualsFloat("faload", 2.5f, faload(env, floatArray, 1));
    assertEqualsFloat("faload", 3.75f, faload(env, floatArray, 2));

    LongArray* longArray = newarray_long(env, 3);
    assertNotNull("newarray_long", (Object*) longArray);
    assertEqualsInt("arraylength_long", 3, arraylength_long(env, longArray));
    assertEqualsLong("laload", 0, laload(env, longArray, 0));
    assertEqualsLong("laload", 0, laload(env, longArray, 1));
    assertEqualsLong("laload", 0, laload(env, longArray, 2));
    lastore(env, longArray, 0, 1);
    lastore(env, longArray, 1, 0xffffffffffffffff);
    lastore(env, longArray, 2, 0x8000000000000000);
    assertEqualsLong("laload", 1, laload(env, longArray, 0));
    assertEqualsLong("laload", 0xffffffffffffffff, laload(env, longArray, 1));
    assertEqualsLong("laload", 0x8000000000000000, laload(env, longArray, 2));

    DoubleArray* doubleArray = newarray_double(env, 3);
    assertNotNull("newarray_double", (Object*) doubleArray);
    assertEqualsInt("arraylength_double", 3, arraylength_double(env, doubleArray));
    assertEqualsDouble("daload", 0.0, daload(env, doubleArray, 0));
    assertEqualsDouble("daload", 0.0, daload(env, doubleArray, 1));
    assertEqualsDouble("daload", 0.0, daload(env, doubleArray, 2));
    dastore(env, doubleArray, 0, 1.25);
    dastore(env, doubleArray, 1, 2.5);
    dastore(env, doubleArray, 2, 3.75);
    assertEqualsDouble("daload", 1.25, daload(env, doubleArray, 0));
    assertEqualsDouble("daload", 2.5, daload(env, doubleArray, 1));
    assertEqualsDouble("daload", 3.75, daload(env, doubleArray, 2));

    ObjectArray* objectArray = newarray_object(env, 3);
    assertNotNull("newarray_object", (Object*) objectArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(env, objectArray));
    assertNull("aaload", aaload(env, objectArray, 0));
    assertNull("aaload", aaload(env, objectArray, 1));
    assertNull("aaload", aaload(env, objectArray, 2));
    aastore(env, objectArray, 0, a);
    aastore(env, objectArray, 1, NULL);
    aastore(env, objectArray, 2, b);
    assertSameObject("aaload", a, aaload(env, objectArray, 0));
    assertNull("aaload", aaload(env, objectArray, 1));
    assertSameObject("aaload", b, aaload(env, objectArray, 2));

    ObjectArray* multiIntArray;
    ObjectArray* multiByteArray;
    int i, j, k;

    multiIntArray = multianewarray_int(env, 4, 3, 2);
    assertNotNull("multianewarray_int", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 4, arraylength_object(env, multiIntArray));
    for (i = 0; i < 4; i++) {
        Object* o1 = aaload(env, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object(env, (ObjectArray*) o1));
        for (j = 0; j < 3; j++) {
            Object* o2 = aaload(env, (ObjectArray*) o1, j);
            assertNotNull("multiIntArray[i][j] != null", o2);
            assertEqualsInt("multiIntArray[i][j].length == 3", 2, arraylength_int(env, (IntArray*) o2));
            for (k = 0; k < 2; k++) {
                assertEqualsInt("multiIntArray[i][j][k] == 0", 0, iaload(env, (IntArray*) o2, k));
            }
        }
    }

    multiIntArray = multianewarray_int(env, 2, 0, 0);
    assertNotNull("multianewarray_int", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(env, multiIntArray));
    for (i = 0; i < 2; i++) {
        Object* o1 = aaload(env, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 0", 0, arraylength_object(env, (ObjectArray*) o1));
    }

    multiIntArray = multianewarray_int_partial(env, 2, 3);
    assertNotNull("multianewarray_int_partial", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(env, multiIntArray));
    for (i = 0; i < 2; i++) {
        Object* o1 = aaload(env, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object(env, (ObjectArray*) o1));
        for (j = 0; j < 3; j++) {
            assertNull("multiIntArray[i][j] == null", aaload(env, (ObjectArray*) o1, j));
        }
    }

    multiIntArray = multianewarray_int_partial(env, 0, 0);
    assertNotNull("multianewarray_int_partial", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 0, arraylength_object(env, multiIntArray));

    multiByteArray = multianewarray_byte(env, 3, 2);
    assertNotNull("multianewarray_byte", (Object*) multiByteArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(env, multiByteArray));
    for (i = 0; i < 3; i++) {
        Object* o1 = aaload(env, multiByteArray, i);
        assertNotNull("multiByteArray[i] != null", o1);
        assertEqualsInt("multiByteArray[i].length == 2", 2, arraylength_byte(env, (ByteArray*) o1));
        for (j = 0; j < 2; j++) {
            assertEqualsInt("multiByteArray[i][j] == 0", 0, baload_byte(env, (ByteArray*) o1, j));
        }
    }

    print_test_result();

    return 0;
}

