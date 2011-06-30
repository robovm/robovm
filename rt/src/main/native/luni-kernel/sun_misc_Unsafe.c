#include <nullvm.h>
#include "reflection_helpers.h"

static Class* java_lang_reflect_Field = NULL;
static InstanceField* java_lang_reflect_Field_field = NULL;

jlong Java_sun_misc_Unsafe_objectFieldOffset(Env* env, Object* unsafe, Object* fieldObject) {
    InstanceField* field = (InstanceField*) getFieldFromFieldObject(env, fieldObject);
    if (!field) return 0;
    return field->offset;
}

jboolean Java_sun_misc_Unsafe_compareAndSwapInt(Env* env, Object* unsafe, Object* object, jlong fieldOffset, jint expected, jint update) {
    jint* address = (jint*) (((jbyte*) object) + fieldOffset);
    return nvmCompareAndSwapInt(address, expected, update);
}

