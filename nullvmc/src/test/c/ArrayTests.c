#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* ArrayOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ArrayOpcodes", "org_nullvm_compiler_tests_opcode_ArrayOpcodes", NULL);
    jint_array* (*newarray_int)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_int", "(I)[I", ArrayOpcodes);
    jbyte_array* (*newarray_byte)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_byte", "(I)[B", ArrayOpcodes);
    jshort_array* (*newarray_short)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_short", "(I)[S", ArrayOpcodes);
    jchar_array* (*newarray_char)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_char", "(I)[C", ArrayOpcodes);
    jboolean_array* (*newarray_boolean)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_boolean", "(I)[Z", ArrayOpcodes);
    jfloat_array* (*newarray_float)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_float", "(I)[F", ArrayOpcodes);
    jlong_array* (*newarray_long)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_long", "(I)[J", ArrayOpcodes);
    jdouble_array* (*newarray_double)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_double", "(I)[D", ArrayOpcodes);
    jobject_array* (*newarray_object)(jint) = j_get_method_impl(ArrayOpcodes, "newarray_object", "(I)[Ljava/lang/Object;", ArrayOpcodes);
    jobject_array* (*multianewarray_int)(jint, jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int", "(III)[[[I", ArrayOpcodes);
    jobject_array* (*multianewarray_int_partial)(jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int_partial", "(II)[[[I", ArrayOpcodes);
    jobject_array* (*multianewarray_byte)(jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_byte", "(II)[[B", ArrayOpcodes);
    jint (*arraylength_int)(jint_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_int", "([I)I", ArrayOpcodes);
    jint (*arraylength_byte)(jbyte_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_byte", "([B)I", ArrayOpcodes);
    jint (*arraylength_short)(jshort_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_short", "([S)I", ArrayOpcodes);
    jint (*arraylength_char)(jchar_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_char", "([C)I", ArrayOpcodes);
    jint (*arraylength_boolean)(jboolean_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_boolean", "([Z)I", ArrayOpcodes);
    jint (*arraylength_float)(jfloat_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_float", "([F)I", ArrayOpcodes);
    jint (*arraylength_long)(jlong_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_long", "([J)I", ArrayOpcodes);
    jint (*arraylength_double)(jdouble_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_double", "([D)I", ArrayOpcodes);
    jint (*arraylength_object)(jobject_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_object", "([Ljava/lang/Object;)I", ArrayOpcodes);
    jint (*iaload)(jint_array*, jint) = j_get_method_impl(ArrayOpcodes, "iaload", "([II)I", ArrayOpcodes);
    void (*iastore)(jint_array*, jint, jint) = j_get_method_impl(ArrayOpcodes, "iastore", "([III)V", ArrayOpcodes);
    jbyte (*baload_byte)(jbyte_array*, jint) = j_get_method_impl(ArrayOpcodes, "baload_byte", "([BI)B", ArrayOpcodes);
    void (*bastore_byte)(jbyte_array*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_byte", "([BIB)V", ArrayOpcodes);
    jboolean (*baload_boolean)(jboolean_array*, jint) = j_get_method_impl(ArrayOpcodes, "baload_boolean", "([ZI)Z", ArrayOpcodes);
    void (*bastore_boolean)(jboolean_array*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_boolean", "([ZIZ)V", ArrayOpcodes);
    jshort (*saload)(jshort_array*, jint) = j_get_method_impl(ArrayOpcodes, "saload", "([SI)S", ArrayOpcodes);
    void (*sastore)(jshort_array*, jint, jshort) = j_get_method_impl(ArrayOpcodes, "sastore", "([SIS)V", ArrayOpcodes);
    jchar (*caload)(jchar_array*, jint) = j_get_method_impl(ArrayOpcodes, "caload", "([CI)C", ArrayOpcodes);
    void (*castore)(jchar_array*, jint, jchar) = j_get_method_impl(ArrayOpcodes, "castore", "([CIC)V", ArrayOpcodes);
    jfloat (*faload)(jfloat_array*, jint) = j_get_method_impl(ArrayOpcodes, "faload", "([FI)F", ArrayOpcodes);
    void (*fastore)(jfloat_array*, jint, jfloat) = j_get_method_impl(ArrayOpcodes, "fastore", "([FIF)V", ArrayOpcodes);
    jlong (*laload)(jlong_array*, jint) = j_get_method_impl(ArrayOpcodes, "laload", "([JI)J", ArrayOpcodes);
    void (*lastore)(jlong_array*, jint, jlong) = j_get_method_impl(ArrayOpcodes, "lastore", "([JIJ)V", ArrayOpcodes);
    jdouble (*daload)(jdouble_array*, jint) = j_get_method_impl(ArrayOpcodes, "daload", "([DI)D", ArrayOpcodes);
    void (*dastore)(jdouble_array*, jint, jdouble) = j_get_method_impl(ArrayOpcodes, "dastore", "([DID)V", ArrayOpcodes);
    jobject* (*aaload)(jobject_array*, jint) = j_get_method_impl(ArrayOpcodes, "aaload", "([Ljava/lang/Object;I)Ljava/lang/Object;", ArrayOpcodes);
    void (*aastore)(jobject_array*, jint, jobject*) = j_get_method_impl(ArrayOpcodes, "aastore", "([Ljava/lang/Object;ILjava/lang/Object;)V", ArrayOpcodes);

    jobject* a = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));
    jobject* b = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));

    jint_array* intArray = newarray_int(3);
    assertNotNull("newarray_int", (jobject*) intArray);
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

    jbyte_array* byteArray = newarray_byte(3);
    assertNotNull("newarray_byte", (jobject*) byteArray);
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

    jshort_array* shortArray = newarray_short(3);
    assertNotNull("newarray_short", (jobject*) shortArray);
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

    jchar_array* charArray = newarray_char(3);
    assertNotNull("newarray_char", (jobject*) charArray);
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

    jboolean_array* booleanArray = newarray_boolean(2);
    assertNotNull("newarray_boolean", (jobject*) booleanArray);
    assertEqualsInt("arraylength_boolean", 2, arraylength_boolean(booleanArray));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 1));
    bastore_boolean(booleanArray, 0, 1);
    bastore_boolean(booleanArray, 1, 0);
    assertEqualsInt("baload_boolean", 1, baload_boolean(booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(booleanArray, 1));

    jfloat_array* floatArray = newarray_float(3);
    assertNotNull("newarray_float", (jobject*) floatArray);
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

    jlong_array* longArray = newarray_long(3);
    assertNotNull("newarray_long", (jobject*) longArray);
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

    jdouble_array* doubleArray = newarray_double(3);
    assertNotNull("newarray_double", (jobject*) doubleArray);
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

    jobject_array* objectArray = newarray_object(3);
    assertNotNull("newarray_object", (jobject*) objectArray);
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

    jobject_array* multiIntArray;
    jobject_array* multiByteArray;
    int i, j, k;

    multiIntArray = multianewarray_int(4, 3, 2);
    assertNotNull("multianewarray_int", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 4, arraylength_object(multiIntArray));
    for (i = 0; i < 4; i++) {
        jobject* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object((jobject_array*) o1));
        for (j = 0; j < 3; j++) {
            jobject* o2 = aaload((jobject_array*) o1, j);
            assertNotNull("multiIntArray[i][j] != null", o2);
            assertEqualsInt("multiIntArray[i][j].length == 3", 2, arraylength_int((jint_array*) o2));
            for (k = 0; k < 2; k++) {
                assertEqualsInt("multiIntArray[i][j][k] == 0", 0, iaload((jint_array*) o2, k));
            }
        }
    }

    multiIntArray = multianewarray_int(2, 0, 0);
    assertNotNull("multianewarray_int", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(multiIntArray));
    for (i = 0; i < 2; i++) {
        jobject* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 0", 0, arraylength_object((jobject_array*) o1));
    }

    multiIntArray = multianewarray_int_partial(2, 3);
    assertNotNull("multianewarray_int_partial", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(multiIntArray));
    for (i = 0; i < 2; i++) {
        jobject* o1 = aaload(multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object((jobject_array*) o1));
        for (j = 0; j < 3; j++) {
            assertNull("multiIntArray[i][j] == null", aaload((jobject_array*) o1, j));
        }
    }

    multiIntArray = multianewarray_int_partial(0, 0);
    assertNotNull("multianewarray_int_partial", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 0, arraylength_object(multiIntArray));

    multiByteArray = multianewarray_byte(3, 2);
    assertNotNull("multianewarray_byte", (jobject*) multiByteArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(multiByteArray));
    for (i = 0; i < 3; i++) {
        jobject* o1 = aaload(multiByteArray, i);
        assertNotNull("multiByteArray[i] != null", o1);
        assertEqualsInt("multiByteArray[i].length == 2", 2, arraylength_byte((jbyte_array*) o1));
        for (j = 0; j < 2; j++) {
            assertEqualsInt("multiByteArray[i][j] == 0", 0, baload_byte((jbyte_array*) o1, j));
        }
    }

    print_test_result();

    return 0;
}

