/*
 * Copyright (C) 2012 Trillian AB
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
#ifndef ROBOVM_MEMORY_H
#define ROBOVM_MEMORY_H

extern jboolean rvmInitMemory(Env* env);
extern Class* rvmAllocateMemoryForClass(Env* env, jint classDataSize);
extern Object* rvmAllocateMemoryForObject(Env* env, Class* clazz);
extern void rvmRegisterFinalizer(Env* env, Object* obj);
extern void rvmRegisterReference(Env* env, Object* reference, Object* referent);
extern void rvmRegisterDisappearingLink(Env* env, void** address, Object* obj);
extern void rvmUnregisterDisappearingLink(Env* env, void** address);
extern Array* rvmAllocateMemoryForArray(Env* env, jint length, jint elementSize);
extern void* rvmAllocateMemory(Env* env, int size);
extern void* rvmAllocateMemoryUncollectable(Env* env, int size);
extern void* rvmAllocateMemoryAtomic(Env* env, int size);
extern void rvmFreeMemory(void* m);
extern void* rvmCopyMemory(Env* env, const void* src, int size);
extern void* rvmCopyMemoryZ(Env* env, const char* src);
extern Object* rvmNewDirectByteBuffer(Env* env, void* address, jlong capacity);

#endif

