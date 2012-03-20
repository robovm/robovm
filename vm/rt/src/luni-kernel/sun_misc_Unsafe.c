#include <nullvm.h>
#include "reflection_helpers.h"

jlong Java_sun_misc_Unsafe_objectFieldOffset(Env* env, Object* unsafe, Object* fieldObject) {
    InstanceField* field = (InstanceField*) getFieldFromFieldObject(env, fieldObject);
    if (!field) return 0;
    return field->offset;
}

jboolean Java_sun_misc_Unsafe_compareAndSwapInt(Env* env, Object* unsafe, Object* object, jlong fieldOffset, jint expected, jint update) {
    jint* address = (jint*) (((jbyte*) object) + fieldOffset);
    return nvmCompareAndSwapInt(address, expected, update);
}

