#include <nullvm.h>
#include "assert.h"

int main(int argc, char* argv[]) {
    nvmStartup();

    jclass* ArrayOpcodes = nvmGetClass("org/nullvm/compiler/tests/opcode/ArrayOpcodes", "org_nullvm_compiler_tests_opcode_ArrayOpcodes", NULL);
    jint_array* (*newarray_int)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_int", "(I)[I", ArrayOpcodes);
    jbyte_array* (*newarray_byte)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_byte", "(I)[B", ArrayOpcodes);
    jshort_array* (*newarray_short)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_short", "(I)[S", ArrayOpcodes);
    jchar_array* (*newarray_char)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_char", "(I)[C", ArrayOpcodes);
    jboolean_array* (*newarray_boolean)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_boolean", "(I)[Z", ArrayOpcodes);
    jfloat_array* (*newarray_float)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_float", "(I)[F", ArrayOpcodes);
    jlong_array* (*newarray_long)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_long", "(I)[J", ArrayOpcodes);
    jdouble_array* (*newarray_double)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_double", "(I)[D", ArrayOpcodes);
    jobject_array* (*newarray_object)(jclass*, jint) = j_get_method_impl(ArrayOpcodes, "newarray_object", "(I)[Ljava/lang/Object;", ArrayOpcodes);
    jobject_array* (*multianewarray_int)(jclass*, jint, jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int", "(III)[[[I", ArrayOpcodes);
    jobject_array* (*multianewarray_int_partial)(jclass*, jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_int_partial", "(II)[[[I", ArrayOpcodes);
    jobject_array* (*multianewarray_byte)(jclass*, jint, jint) = j_get_method_impl(ArrayOpcodes, "multianewarray_byte", "(II)[[B", ArrayOpcodes);
    jint (*arraylength_int)(jclass*, jint_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_int", "([I)I", ArrayOpcodes);
    jint (*arraylength_byte)(jclass*, jbyte_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_byte", "([B)I", ArrayOpcodes);
    jint (*arraylength_short)(jclass*, jshort_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_short", "([S)I", ArrayOpcodes);
    jint (*arraylength_char)(jclass*, jchar_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_char", "([C)I", ArrayOpcodes);
    jint (*arraylength_boolean)(jclass*, jboolean_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_boolean", "([Z)I", ArrayOpcodes);
    jint (*arraylength_float)(jclass*, jfloat_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_float", "([F)I", ArrayOpcodes);
    jint (*arraylength_long)(jclass*, jlong_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_long", "([J)I", ArrayOpcodes);
    jint (*arraylength_double)(jclass*, jdouble_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_double", "([D)I", ArrayOpcodes);
    jint (*arraylength_object)(jclass*, jobject_array*) = j_get_method_impl(ArrayOpcodes, "arraylength_object", "([Ljava/lang/Object;)I", ArrayOpcodes);
    jint (*iaload)(jclass*, jint_array*, jint) = j_get_method_impl(ArrayOpcodes, "iaload", "([II)I", ArrayOpcodes);
    void (*iastore)(jclass*, jint_array*, jint, jint) = j_get_method_impl(ArrayOpcodes, "iastore", "([III)V", ArrayOpcodes);
    jbyte (*baload_byte)(jclass*, jbyte_array*, jint) = j_get_method_impl(ArrayOpcodes, "baload_byte", "([BI)B", ArrayOpcodes);
    void (*bastore_byte)(jclass*, jbyte_array*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_byte", "([BIB)V", ArrayOpcodes);
    jboolean (*baload_boolean)(jclass*, jboolean_array*, jint) = j_get_method_impl(ArrayOpcodes, "baload_boolean", "([ZI)Z", ArrayOpcodes);
    void (*bastore_boolean)(jclass*, jboolean_array*, jint, jbyte) = j_get_method_impl(ArrayOpcodes, "bastore_boolean", "([ZIZ)V", ArrayOpcodes);
    jshort (*saload)(jclass*, jshort_array*, jint) = j_get_method_impl(ArrayOpcodes, "saload", "([SI)S", ArrayOpcodes);
    void (*sastore)(jclass*, jshort_array*, jint, jshort) = j_get_method_impl(ArrayOpcodes, "sastore", "([SIS)V", ArrayOpcodes);
    jchar (*caload)(jclass*, jchar_array*, jint) = j_get_method_impl(ArrayOpcodes, "caload", "([CI)C", ArrayOpcodes);
    void (*castore)(jclass*, jchar_array*, jint, jchar) = j_get_method_impl(ArrayOpcodes, "castore", "([CIC)V", ArrayOpcodes);
    jfloat (*faload)(jclass*, jfloat_array*, jint) = j_get_method_impl(ArrayOpcodes, "faload", "([FI)F", ArrayOpcodes);
    void (*fastore)(jclass*, jfloat_array*, jint, jfloat) = j_get_method_impl(ArrayOpcodes, "fastore", "([FIF)V", ArrayOpcodes);
    jlong (*laload)(jclass*, jlong_array*, jint) = j_get_method_impl(ArrayOpcodes, "laload", "([JI)J", ArrayOpcodes);
    void (*lastore)(jclass*, jlong_array*, jint, jlong) = j_get_method_impl(ArrayOpcodes, "lastore", "([JIJ)V", ArrayOpcodes);
    jdouble (*daload)(jclass*, jdouble_array*, jint) = j_get_method_impl(ArrayOpcodes, "daload", "([DI)D", ArrayOpcodes);
    void (*dastore)(jclass*, jdouble_array*, jint, jdouble) = j_get_method_impl(ArrayOpcodes, "dastore", "([DID)V", ArrayOpcodes);
    jobject* (*aaload)(jclass*, jobject_array*, jint) = j_get_method_impl(ArrayOpcodes, "aaload", "([Ljava/lang/Object;I)Ljava/lang/Object;", ArrayOpcodes);
    void (*aastore)(jclass*, jobject_array*, jint, jobject*) = j_get_method_impl(ArrayOpcodes, "aastore", "([Ljava/lang/Object;ILjava/lang/Object;)V", ArrayOpcodes);

    jobject* a = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));
    jobject* b = nvmNewInstance(nvmGetClass("java/lang/Object", "java_lang_Object", NULL));

    jint_array* intArray = newarray_int(ArrayOpcodes, 3);
    assertNotNull("newarray_int", (jobject*) intArray);
    assertEqualsInt("arraylength_int", 3, arraylength_int(ArrayOpcodes, intArray));
    assertEqualsInt("iaload", 0, iaload(ArrayOpcodes, intArray, 0));
    assertEqualsInt("iaload", 0, iaload(ArrayOpcodes, intArray, 1));
    assertEqualsInt("iaload", 0, iaload(ArrayOpcodes, intArray, 2));
    iastore(ArrayOpcodes, intArray, 0, 1);
    iastore(ArrayOpcodes, intArray, 1, 0xffffffff);
    iastore(ArrayOpcodes, intArray, 2, 0x80000000);
    assertEqualsInt("iaload", 1, iaload(ArrayOpcodes, intArray, 0));
    assertEqualsInt("iaload", 0xffffffff, iaload(ArrayOpcodes, intArray, 1));
    assertEqualsInt("iaload", 0x80000000, iaload(ArrayOpcodes, intArray, 2));
    assertEqualsInt("arraylength_int", 0, arraylength_int(ArrayOpcodes, newarray_int(ArrayOpcodes, 0)));

    jbyte_array* byteArray = newarray_byte(ArrayOpcodes, 3);
    assertNotNull("newarray_byte", (jobject*) byteArray);
    assertEqualsInt("arraylength_byte", 3, arraylength_byte(ArrayOpcodes, byteArray));
    assertEqualsInt("baload_byte", 0, baload_byte(ArrayOpcodes, byteArray, 0));
    assertEqualsInt("baload_byte", 0, baload_byte(ArrayOpcodes, byteArray, 1));
    assertEqualsInt("baload_byte", 0, baload_byte(ArrayOpcodes, byteArray, 2));
    bastore_byte(ArrayOpcodes, byteArray, 0, 1);
    bastore_byte(ArrayOpcodes, byteArray, 1, (jbyte) 0x7f);
    bastore_byte(ArrayOpcodes, byteArray, 2, (jbyte) 0x80);
    assertEqualsInt("baload_byte", 1, baload_byte(ArrayOpcodes, byteArray, 0));
    assertEqualsInt("baload_byte", (jbyte) 0x7f, baload_byte(ArrayOpcodes, byteArray, 1));
    assertEqualsInt("baload_byte", (jbyte) 0x80, baload_byte(ArrayOpcodes, byteArray, 2));

    jshort_array* shortArray = newarray_short(ArrayOpcodes, 3);
    assertNotNull("newarray_short", (jobject*) shortArray);
    assertEqualsInt("arraylength_short", 3, arraylength_short(ArrayOpcodes, shortArray));
    assertEqualsInt("saload", 0, saload(ArrayOpcodes, shortArray, 0));
    assertEqualsInt("saload", 0, saload(ArrayOpcodes, shortArray, 1));
    assertEqualsInt("saload", 0, saload(ArrayOpcodes, shortArray, 2));
    sastore(ArrayOpcodes, shortArray, 0, 1);
    sastore(ArrayOpcodes, shortArray, 1, (jshort) 0x7fff);
    sastore(ArrayOpcodes, shortArray, 2, (jshort) 0x8000);
    assertEqualsInt("saload", 1, saload(ArrayOpcodes, shortArray, 0));
    assertEqualsInt("saload", (jshort) 0x7fff, saload(ArrayOpcodes, shortArray, 1));
    assertEqualsInt("saload", (jshort) 0x8000, saload(ArrayOpcodes, shortArray, 2));

    jchar_array* charArray = newarray_char(ArrayOpcodes, 3);
    assertNotNull("newarray_char", (jobject*) charArray);
    assertEqualsInt("arraylength_char", 3, arraylength_char(ArrayOpcodes, charArray));
    assertEqualsInt("caload", 0, caload(ArrayOpcodes, charArray, 0));
    assertEqualsInt("caload", 0, caload(ArrayOpcodes, charArray, 1));
    assertEqualsInt("caload", 0, caload(ArrayOpcodes, charArray, 2));
    castore(ArrayOpcodes, charArray, 0, 1);
    castore(ArrayOpcodes, charArray, 1, 0x7fff);
    castore(ArrayOpcodes, charArray, 2, 0x8000);
    assertEqualsInt("caload", 1, caload(ArrayOpcodes, charArray, 0));
    assertEqualsInt("caload", 0x7fff, caload(ArrayOpcodes, charArray, 1));
    assertEqualsInt("caload", 0x8000, caload(ArrayOpcodes, charArray, 2));

    jboolean_array* booleanArray = newarray_boolean(ArrayOpcodes, 2);
    assertNotNull("newarray_boolean", (jobject*) booleanArray);
    assertEqualsInt("arraylength_boolean", 2, arraylength_boolean(ArrayOpcodes, booleanArray));
    assertEqualsInt("baload_boolean", 0, baload_boolean(ArrayOpcodes, booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(ArrayOpcodes, booleanArray, 1));
    bastore_boolean(ArrayOpcodes, booleanArray, 0, 1);
    bastore_boolean(ArrayOpcodes, booleanArray, 1, 0);
    assertEqualsInt("baload_boolean", 1, baload_boolean(ArrayOpcodes, booleanArray, 0));
    assertEqualsInt("baload_boolean", 0, baload_boolean(ArrayOpcodes, booleanArray, 1));

    jfloat_array* floatArray = newarray_float(ArrayOpcodes, 3);
    assertNotNull("newarray_float", (jobject*) floatArray);
    assertEqualsInt("arraylength_float", 3, arraylength_float(ArrayOpcodes, floatArray));
    assertEqualsFloat("faload", 0.0f, faload(ArrayOpcodes, floatArray, 0));
    assertEqualsFloat("faload", 0.0f, faload(ArrayOpcodes, floatArray, 1));
    assertEqualsFloat("faload", 0.0f, faload(ArrayOpcodes, floatArray, 2));
    fastore(ArrayOpcodes, floatArray, 0, 1.25f);
    fastore(ArrayOpcodes, floatArray, 1, 2.5f);
    fastore(ArrayOpcodes, floatArray, 2, 3.75f);
    assertEqualsFloat("faload", 1.25f, faload(ArrayOpcodes, floatArray, 0));
    assertEqualsFloat("faload", 2.5f, faload(ArrayOpcodes, floatArray, 1));
    assertEqualsFloat("faload", 3.75f, faload(ArrayOpcodes, floatArray, 2));

    jlong_array* longArray = newarray_long(ArrayOpcodes, 3);
    assertNotNull("newarray_long", (jobject*) longArray);
    assertEqualsInt("arraylength_long", 3, arraylength_long(ArrayOpcodes, longArray));
    assertEqualsLong("laload", 0, laload(ArrayOpcodes, longArray, 0));
    assertEqualsLong("laload", 0, laload(ArrayOpcodes, longArray, 1));
    assertEqualsLong("laload", 0, laload(ArrayOpcodes, longArray, 2));
    lastore(ArrayOpcodes, longArray, 0, 1);
    lastore(ArrayOpcodes, longArray, 1, 0xffffffffffffffff);
    lastore(ArrayOpcodes, longArray, 2, 0x8000000000000000);
    assertEqualsLong("laload", 1, laload(ArrayOpcodes, longArray, 0));
    assertEqualsLong("laload", 0xffffffffffffffff, laload(ArrayOpcodes, longArray, 1));
    assertEqualsLong("laload", 0x8000000000000000, laload(ArrayOpcodes, longArray, 2));

    jdouble_array* doubleArray = newarray_double(ArrayOpcodes, 3);
    assertNotNull("newarray_double", (jobject*) doubleArray);
    assertEqualsInt("arraylength_double", 3, arraylength_double(ArrayOpcodes, doubleArray));
    assertEqualsDouble("daload", 0.0, daload(ArrayOpcodes, doubleArray, 0));
    assertEqualsDouble("daload", 0.0, daload(ArrayOpcodes, doubleArray, 1));
    assertEqualsDouble("daload", 0.0, daload(ArrayOpcodes, doubleArray, 2));
    dastore(ArrayOpcodes, doubleArray, 0, 1.25);
    dastore(ArrayOpcodes, doubleArray, 1, 2.5);
    dastore(ArrayOpcodes, doubleArray, 2, 3.75);
    assertEqualsDouble("daload", 1.25, daload(ArrayOpcodes, doubleArray, 0));
    assertEqualsDouble("daload", 2.5, daload(ArrayOpcodes, doubleArray, 1));
    assertEqualsDouble("daload", 3.75, daload(ArrayOpcodes, doubleArray, 2));

    jobject_array* objectArray = newarray_object(ArrayOpcodes, 3);
    assertNotNull("newarray_object", (jobject*) objectArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(ArrayOpcodes, objectArray));
    assertNull("aaload", aaload(ArrayOpcodes, objectArray, 0));
    assertNull("aaload", aaload(ArrayOpcodes, objectArray, 1));
    assertNull("aaload", aaload(ArrayOpcodes, objectArray, 2));
    aastore(ArrayOpcodes, objectArray, 0, a);
    aastore(ArrayOpcodes, objectArray, 1, NULL);
    aastore(ArrayOpcodes, objectArray, 2, b);
    assertSameObject("aaload", a, aaload(ArrayOpcodes, objectArray, 0));
    assertNull("aaload", aaload(ArrayOpcodes, objectArray, 1));
    assertSameObject("aaload", b, aaload(ArrayOpcodes, objectArray, 2));

    jobject_array* multiIntArray;
    jobject_array* multiByteArray;
    int i, j, k;

    multiIntArray = multianewarray_int(ArrayOpcodes, 4, 3, 2);
    assertNotNull("multianewarray_int", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 4, arraylength_object(ArrayOpcodes, multiIntArray));
    for (i = 0; i < 4; i++) {
        jobject* o1 = aaload(ArrayOpcodes, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object(ArrayOpcodes, (jobject_array*) o1));
        for (j = 0; j < 3; j++) {
            jobject* o2 = aaload(ArrayOpcodes, (jobject_array*) o1, j);
            assertNotNull("multiIntArray[i][j] != null", o2);
            assertEqualsInt("multiIntArray[i][j].length == 3", 2, arraylength_int(ArrayOpcodes, (jint_array*) o2));
            for (k = 0; k < 2; k++) {
                assertEqualsInt("multiIntArray[i][j][k] == 0", 0, iaload(ArrayOpcodes, (jint_array*) o2, k));
            }
        }
    }

    multiIntArray = multianewarray_int(ArrayOpcodes, 2, 0, 0);
    assertNotNull("multianewarray_int", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(ArrayOpcodes, multiIntArray));
    for (i = 0; i < 2; i++) {
        jobject* o1 = aaload(ArrayOpcodes, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 0", 0, arraylength_object(ArrayOpcodes, (jobject_array*) o1));
    }

    multiIntArray = multianewarray_int_partial(ArrayOpcodes, 2, 3);
    assertNotNull("multianewarray_int_partial", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 2, arraylength_object(ArrayOpcodes, multiIntArray));
    for (i = 0; i < 2; i++) {
        jobject* o1 = aaload(ArrayOpcodes, multiIntArray, i);
        assertNotNull("multiIntArray[i] != null", o1);
        assertEqualsInt("multiIntArray[i].length == 3", 3, arraylength_object(ArrayOpcodes, (jobject_array*) o1));
        for (j = 0; j < 3; j++) {
            assertNull("multiIntArray[i][j] == null", aaload(ArrayOpcodes, (jobject_array*) o1, j));
        }
    }

    multiIntArray = multianewarray_int_partial(ArrayOpcodes, 0, 0);
    assertNotNull("multianewarray_int_partial", (jobject*) multiIntArray);
    assertEqualsInt("arraylength_object", 0, arraylength_object(ArrayOpcodes, multiIntArray));

    multiByteArray = multianewarray_byte(ArrayOpcodes, 3, 2);
    assertNotNull("multianewarray_byte", (jobject*) multiByteArray);
    assertEqualsInt("arraylength_object", 3, arraylength_object(ArrayOpcodes, multiByteArray));
    for (i = 0; i < 3; i++) {
        jobject* o1 = aaload(ArrayOpcodes, multiByteArray, i);
        assertNotNull("multiByteArray[i] != null", o1);
        assertEqualsInt("multiByteArray[i].length == 2", 2, arraylength_byte(ArrayOpcodes, (jbyte_array*) o1));
        for (j = 0; j < 2; j++) {
            assertEqualsInt("multiByteArray[i][j] == 0", 0, baload_byte(ArrayOpcodes, (jbyte_array*) o1, j));
        }
    }

    print_test_result();

    return 0;
}

