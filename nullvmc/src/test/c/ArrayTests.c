#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    Class* ArrayOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ArrayOpcodes", "org_nullvm_compiler_tests_opcode_ArrayOpcodes", NULL);
    IntArray* (*newarray_int)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_int", "(I)[I", ArrayOpcodes);
    ByteArray* (*newarray_byte)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_byte", "(I)[B", ArrayOpcodes);
    ShortArray* (*newarray_short)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_short", "(I)[S", ArrayOpcodes);
    CharArray* (*newarray_char)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_char", "(I)[C", ArrayOpcodes);
    BooleanArray* (*newarray_boolean)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_boolean", "(I)[Z", ArrayOpcodes);
    FloatArray* (*newarray_float)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_float", "(I)[F", ArrayOpcodes);
    LongArray* (*newarray_long)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_long", "(I)[J", ArrayOpcodes);
    DoubleArray* (*newarray_double)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_double", "(I)[D", ArrayOpcodes);
    ObjectArray* (*newarray_object)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_object", "(I)[Ljava/lang/Object;", ArrayOpcodes);
    ObjectArray* (*multianewarray_int)(jint, jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int", "(III)[[[I", ArrayOpcodes);
    ObjectArray* (*multianewarray_int_partial)(jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int_partial", "(II)[[[I", ArrayOpcodes);
    ObjectArray* (*multianewarray_byte)(jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_byte", "(II)[[B", ArrayOpcodes);
    jint (*arraylength_int)(IntArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_int", "([I)I", ArrayOpcodes);
    jint (*arraylength_byte)(ByteArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_byte", "([B)I", ArrayOpcodes);
    jint (*arraylength_short)(ShortArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_short", "([S)I", ArrayOpcodes);
    jint (*arraylength_char)(CharArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_char", "([C)I", ArrayOpcodes);
    jint (*arraylength_boolean)(BooleanArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_boolean", "([Z)I", ArrayOpcodes);
    jint (*arraylength_float)(FloatArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_float", "([F)I", ArrayOpcodes);
    jint (*arraylength_long)(LongArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_long", "([J)I", ArrayOpcodes);
    jint (*arraylength_double)(DoubleArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_double", "([D)I", ArrayOpcodes);
    jint (*arraylength_object)(ObjectArray*) = j_get_method_impl(ArrayOpcodes, "arraylength_object", "([Ljava/lang/Object;)I", ArrayOpcodes);
    jint (*iaload)(IntArray*, jint) = j_get_method_impl(ArrayOpcodes, "iaload", "([II)I", ArrayOpcodes);
    void (*iastore)(IntArray*, jint, jint) = j_get_method_impl(ArrayOpcodes, "iastore", "([III)V", ArrayOpcodes);
    jbyte (*baload_byte)(ByteArray*, jint) = j_get_method_impl(ArrayOpcodes, "baload_byte", "([BI)B", ArrayOpcodes);
    void (*bastore_byte)(ByteArray*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_byte", "([BIB)V", ArrayOpcodes);
    jboolean (*baload_boolean)(BooleanArray*, jint) = j_get_method_impl(ArrayOpcodes, "baload_boolean", "([ZI)Z", ArrayOpcodes);
    void (*bastore_boolean)(BooleanArray*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_boolean", "([ZIZ)V", ArrayOpcodes);
    jshort (*saload)(ShortArray*, jint) = j_get_method_impl(ArrayOpcodes, "saload", "([SI)S", ArrayOpcodes);
    void (*sastore)(ShortArray*, jint, jshort) = j_get_method_impl(ArrayOpcodes, "sastore", "([SIS)V", ArrayOpcodes);
    jchar (*caload)(CharArray*, jint) = j_get_method_impl(ArrayOpcodes, "caload", "([CI)C", ArrayOpcodes);
    void (*castore)(CharArray*, jint, jchar) = j_get_method_impl(ArrayOpcodes, "castore", "([CIC)V", ArrayOpcodes);
    jfloat (*faload)(FloatArray*, jint) = j_get_method_impl(ArrayOpcodes, "faload", "([FI)F", ArrayOpcodes);
    void (*fastore)(FloatArray*, jint, jfloat) = j_get_method_impl(ArrayOpcodes, "fastore", "([FIF)V", ArrayOpcodes);
    jlong (*laload)(LongArray*, jint) = j_get_method_impl(ArrayOpcodes, "laload", "([JI)J", ArrayOpcodes);
    void (*lastore)(LongArray*, jint, jlong) = j_get_method_impl(ArrayOpcodes, "lastore", "([JIJ)V", ArrayOpcodes);
    jdouble (*daload)(DoubleArray*, jint) = j_get_method_impl(ArrayOpcodes, "daload", "([DI)D", ArrayOpcodes);
    void (*dastore)(DoubleArray*, jint, jdouble) = j_get_method_impl(ArrayOpcodes, "dastore", "([DID)V", ArrayOpcodes);
    Object* (*aaload)(ObjectArray*, jint) = j_get_method_impl(ArrayOpcodes, "aaload", "([Ljava/lang/Object;I)Ljava/lang/Object;", ArrayOpcodes);
    void (*aastore)(ObjectArray*, jint, Object*) = j_get_method_impl(ArrayOpcodes, "aastore", "([Ljava/lang/Object;ILjava/lang/Object;)V", ArrayOpcodes);

    Object* a = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));
    Object* b = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));

    IntArray* intArray = newarray_int(3);
    assertNotNull("newarray_int", (Object*) intArray);
    assertEqualsInt("arraylength_int", 3, arraylength_int(intArray));
    assertEqualsInt("iaload", 0, iaload(intArray, 0));
    assertEqualsInt("iaload", 0, iaload(intArray, 1));
    assertEqualsInt("iaload", 0, iaload(intArray, 2));
    iastore(intArray, 0, 1);
    iastore(intArray, 1, 0xffffffff);
    iastore(intArray, 2, 0x80000000);
    assertEqualsInt("iaload", 1, iaload(intArray, 0));
    assertEqualsInt("iaload", 0xffffffff, iaload(intArray, 1));
    assertEqualsInt("iaload", 0x80000000, iaload(intArray, 2));
    assertEqualsInt("arraylength_int", 0, arraylength_int(newarray_int(0)));

    ByteArray* byteArray = newarray_byte(3);
    assertNotNull("newarray_byte", (Object*) byteArray);
    assertEqualsInt("arraylength_byte", 3, arraylength_byte(byteArray));
    assertEqualsInt("baload_byte", 0, baload_byte(byteArray, 0));
    assertEqualsInt("baload_byte", 0, baload_byte(byteArray, 1));
    assertEqualsInt("baload_byte", 0, baload_byte(byteArray, 2));
    bastore_byte(byteArray, 0, 1);
    bastore_byte(byteArray, 1, (jbyte) 0x7f);
    bastore_byte(byteArray, 2, (jbyte) 0x80);
    assertEqualsInt("baload_byte", 1, baload_byte(byteArray, 0));
    assertEqualsInt("baload_byte", (jbyte) 0x7f, baload_byte(byteArray, 1));
    assertEqualsInt("baload_byte", (jbyte) 0x80, baload_byte(byteArray, 2));

    ShortArray* shortArray = newarray_short(3);
    assertNotNull("newarray_short", (Object*) shortArray);
    assertEqualsInt("arraylength_short", 3, arraylength_short(shortArray));
    assertEqualsInt("saload", 0, saload(shortArray, 0));
    assertEqualsInt("saload", 0, saload(shortArray, 1));
    assertEqualsInt("saload", 0, saload(shortArray, 2));
    sastore(shortArray, 0, 1);
    sastore(shortArray, 1, (jshort) 0x7fff);
    sastore(shortArray, 2, (jshort) 0x8000);
    assertEqualsInt("saload", 1, saload(shortArray, 0));
    assertEqualsInt("saload", (jshort) 0x7fff, saload(shortArray, 1));
    assertEqualsInt("saload", (jshort) 0x8000, saload(shortArray, 2));

    CharArray* charArray = newarray_char(3);
    assertNotNull("newarray_char", (Object*) charArray);
    assertEqualsInt("arraylength_char", 3, arraylength_char(charArray));
    assertEqualsInt("caload", 0, caload(charArray, 0));
    assertEqualsInt("caload", 0, caload(charArray, 1));
    assertEqualsInt("caload", 0, caload(charArray, 2));
    castore(charArray, 0, 1);
    castore(charArray, 1, 0x7fff);
    castore(charArray, 2, 0x8000);
    assertEqualsInt("caload", 1, caload(charArray, 0));
    assertEqualsInt("caload", 0x7fff, caload(charArray, 1));
    assertEqualsInt("caload", 0x8000, caload(charArray, 2));

    BooleanArray* booleanArray = newarray_boolean(2);
    assertNotNull("newarray_boolean", (Object*) booleanArray);
    assertEqualsInt("arraylength_boolean", 2, arraylength_boolean(booleanArray));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 1));
    bastore_boolean(booleanArray, 0, 1);
    bastore_boolean(booleanArray, 1, 0);
    assertEqualsInt("baload_boolean", 1, baload_boolean(booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 1));

    FloatArray* floatArray = newarray_float(3);
    assertNotNull("newarray_float", (Object*) floatArray);
    assertEqualsInt("arraylength_float", 3, arraylength_float(floatArray));
    assertEqualsFloat("faload", 0.0f, faload(floatArray, 0));
    assertEqualsFloat("faload", 0.0f, faload(floatArray, 1));
    assertEqualsFloat("faload", 0.0f, faload(floatArray, 2));
    fastore(floatArray, 0, 1.25f);
    fastore(floatArray, 1, 2.5f);
    fastore(floatArray, 2, 3.75f);
    assertEqualsFloat("faload", 1.25f, faload(floatArray, 0));
    assertEqualsFloat("faload", 2.5f, faload(floatArray, 1));
    assertEqualsFloat("faload", 3.75f, faload(floatArray, 2));

    LongArray* longArray = newarray_long(3);
    assertNotNull("newarray_long", (Object*) longArray);
    assertEqualsInt("arraylength_long", 3, arraylength_long(longArray));
    assertEqualsLong("laload", 0, laload(longArray, 0));
    assertEqualsLong("laload", 0, laload(longArray, 1));
    assertEqualsLong("laload", 0, laload(longArray, 2));
    lastore(longArray, 0, 1);
    lastore(longArray, 1, 0xffffffffffffffff);
    lastore(longArray, 2, 0x8000000000000000);
    assertEqualsLong("laload", 1, laload(longArray, 0));
    assertEqualsLong("laload", 0xffffffffffffffff, laload(longArray, 1));
    assertEqualsLong("laload", 0x8000000000000000, laload(longArray, 2));

    DoubleArray* doubleArray = newarray_double(3);
    assertNotNull("newarray_double", (Object*) doubleArray);
    assertEqualsInt("arraylength_double", 3, arraylength_double(doubleArray));
    assertEqualsDouble("daload", 0.0, daload(doubleArray, 0));
    assertEqualsDouble("daload", 0.0, daload(doubleArray, 1));
    assertEqualsDouble("daload", 0.0, daload(doubleArray, 2));
    dastore(doubleArray, 0, 1.25);
    dastore(doubleArray, 1, 2.5);
    dastore(doubleArray, 2, 3.75);
    assertEqualsDouble("daload", 1.25, daload(doubleArray, 0));
    assertEqualsDouble("daload", 2.5, daload(doubleArray, 1));
    assertEqualsDouble("daload", 3.75, daload(doubleArray, 2));

    ObjectArray* objectArray = newarray_object(3);
    assertNotNull("newarray_object", (Object*) objectArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(objectArray));
    assertNull("aaload", aaload(objectArray, 0));
    assertNull("aaload", aaload(objectArray, 1));
    assertNull("aaload", aaload(objectArray, 2));
    aastore(objectArray, 0, a);
    aastore(objectArray, 1, NULL);
    aastore(objectArray, 2, b);
    assertSameObject("aaload", a, aaload(objectArray, 0));
    assertNull("aaload", aaload(objectArray, 1));
    assertSameObject("aaload", b, aaload(objectArray, 2));

    ObjectArray* multiIntArray;
    ObjectArray* multiByteArray;
    int i, j, k;

    multiIntArray = multianewarray_int(4, 3, 2);
    assertNotNull("multianewarray_int", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 4, arraylength_object(multiIntArray));
    for (i = 0; i < 4; i++) {
        Object* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object((ObjectArray*) o1));
        for (j = 0; j < 3; j++) {
            Object* o2 = aaload((ObjectArray*) o1, j);
            assertNotNull("multiIntArray[i][j] != null", o2);
            assertEqualsInt("multiIntArray[i][j].length == 3", 2, arraylength_int((IntArray*) o2));
            for (k = 0; k < 2; k++) {
                assertEqualsInt("multiIntArray[i][j][k] == 0", 0, iaload((IntArray*) o2, k));
            }
        }
    }

    multiIntArray = multianewarray_int(2, 0, 0);
    assertNotNull("multianewarray_int", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(multiIntArray));
    for (i = 0; i < 2; i++) {
        Object* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 0", 0, arraylength_object((ObjectArray*) o1));
    }

    multiIntArray = multianewarray_int_partial(2, 3);
    assertNotNull("multianewarray_int_partial", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(multiIntArray));
    for (i = 0; i < 2; i++) {
        Object* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object((ObjectArray*) o1));
        for (j = 0; j < 3; j++) {
            assertNull("multiIntArray[i][j] == null", aaload((ObjectArray*) o1, j));
        }
    }

    multiIntArray = multianewarray_int_partial(0, 0);
    assertNotNull("multianewarray_int_partial", (Object*) multiIntArray);
    assertEqualsInt("arraylength_object", 0, arraylength_object(multiIntArray));

    multiByteArray = multianewarray_byte(3, 2);
    assertNotNull("multianewarray_byte", (Object*) multiByteArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(multiByteArray));
    for (i = 0; i < 3; i++) {
        Object* o1 = aaload(multiByteArray, i);
        assertNotNull("multiByteArray[i] != null", o1);
        assertEqualsInt("multiByteArray[i].length == 2", 2, arraylength_byte((ByteArray*) o1));
        for (j = 0; j < 2; j++) {
            assertEqualsInt("multiByteArray[i][j] == 0", 0, baload_byte((ByteArray*) o1, j));
        }
    }

    print_test_result();

    return 0;
}

