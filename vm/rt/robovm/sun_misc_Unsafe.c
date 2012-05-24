/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include <robovm.h>
#include "reflection_helpers.h"

jlong Java_sun_misc_Unsafe_objectFieldOffset0(Env* env, Object* unsafe, Object* fieldObject) {
    InstanceField* field = (InstanceField*) getFieldFromFieldObject(env, fieldObject);
    if (!field) return 0;
    return field->offset;
}

jboolean Java_sun_misc_Unsafe_compareAndSwapInt(Env* env, Object* unsafe, Object* object, jlong fieldOffset, jint expected, jint update) {
    jint* address = (jint*) (((jbyte*) object) + fieldOffset);
    return rvmCompareAndSwapInt(address, expected, update);
}

