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
#ifndef ROBOVM_ARRAY_H
#define ROBOVM_ARRAY_H

extern BooleanArray* rvmNewBooleanArray(Env* env, jint length);
extern ByteArray* rvmNewByteArray(Env* env, jint length);
extern CharArray* rvmNewCharArray(Env* env, jint length);
extern ShortArray* rvmNewShortArray(Env* env, jint length);
extern IntArray* rvmNewIntArray(Env* env, jint length);
extern LongArray* rvmNewLongArray(Env* env, jint length);
extern FloatArray* rvmNewFloatArray(Env* env, jint length);
extern DoubleArray* rvmNewDoubleArray(Env* env, jint length);
extern ObjectArray* rvmNewObjectArray(Env* env, jint length, Class* elementClass, Class* arrayClass, Object* init);
extern Array* rvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* type);
extern Array* rvmCloneArray(Env* env, Array* array);
extern jint rvmGetArrayDimensions(Env* env, Array* array);

#endif

