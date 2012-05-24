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
#include <vmi.h>
#include <string.h>

// Defined in init.c
extern HyPortLibrary portLibrary;

extern struct VMInterfaceFunctions_ vmiImpl;
VMInterface vmi = &vmiImpl;

static UDATA JNICALL _HyVMLSAllocKeys(JNIEnv* env, UDATA* pInitCount, ...) {
    return 0;
}
static void JNICALL _HyVMLSFreeKeys(JNIEnv* env, UDATA* pInitCount, ...) {
}
static void* JNICALL _HyVMLSGet(JNIEnv* env, void *key) {
    return key;
}
static void* JNICALL _HyVMLSSet(JNIEnv* env, void **pKey, void *value) {
    *pKey = value;
    return value;
}

HyVMLSFunctionTable vmls = {
    &_HyVMLSAllocKeys,
    &_HyVMLSFreeKeys,
    &_HyVMLSGet,
    &_HyVMLSSet
};

static vmiError JNICALL _CheckVersion(VMInterface* vmi, vmiVersion* version) {
    return VMI_ERROR_UNIMPLEMENTED;
}
static JavaVM* JNICALL _GetJavaVM(VMInterface* vmi) {
    return NULL;
}
static HyPortLibrary* JNICALL _GetPortLibrary(VMInterface* vmi) {
    return &portLibrary;
}
static HyVMLSFunctionTable* JNICALL _GetVMLSFunctions(VMInterface* vmi) {
    return &vmls;
}
static HyZipCachePool* JNICALL _GetZipCachePool(VMInterface* vmi) {
    return NULL;
}
static JavaVMInitArgs* JNICALL _GetInitArgs(VMInterface* vmi) {
    return NULL;
}
static vmiError JNICALL _GetSystemProperty(VMInterface* vmi, char* key, char** valuePtr) {
    return VMI_ERROR_UNIMPLEMENTED;
}
static vmiError JNICALL _SetSystemProperty(VMInterface* vmi, char* key, char* value) {
    return VMI_ERROR_UNIMPLEMENTED;
}
static vmiError JNICALL _CountSystemProperties(VMInterface* vmi, int* countPtr) {
    return VMI_ERROR_UNIMPLEMENTED;
}
static vmiError JNICALL _IterateSystemProperties(VMInterface* vmi, vmiSystemPropertyIterator iterator, void* userData) {
    return VMI_ERROR_UNIMPLEMENTED;
}

struct VMInterfaceFunctions_ vmiImpl = {
    &_CheckVersion,
    &_GetJavaVM,
    &_GetPortLibrary,
    &_GetVMLSFunctions,
    &_GetZipCachePool,
    &_GetInitArgs,
    &_GetSystemProperty,
    &_SetSystemProperty,
    &_CountSystemProperties,
    &_IterateSystemProperties
};

jboolean rvmInitVMI(Env* env) {
    return TRUE;
}

VMInterface* JNICALL VMI_GetVMIFromJavaVM(JavaVM* vm) {
    return &vmi;
}    

VMInterface* JNICALL VMI_GetVMIFromJNIEnv(JNIEnv* env) {
    return &vmi;
}

