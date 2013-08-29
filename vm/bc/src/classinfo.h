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
#ifndef CLASSINFO_H
#define CLASSINFO_H

#include <robovm.h>

#define CI_PUBLIC 0x1
#define CI_FINAL 0x2
#define CI_INTERFACE 0x4
#define CI_ABSTRACT 0x8
#define CI_SYNTHETIC 0x10
#define CI_ANNOTATION 0x20
#define CI_ENUM 0x40
#define CI_ATTRIBUTES 0x80
#define CI_ERROR 0x100
#define CI_INITIALIZED 0x200
#define CI_FINALIZABLE 0x400

#define CI_ERROR_TYPE_NONE 0x0
#define CI_ERROR_TYPE_NO_CLASS_DEF_FOUND 0x1
#define CI_ERROR_TYPE_ILLEGAL_ACCESS 0x2
#define CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE 0x3

typedef struct {
    Class* clazz;
    jint flags;
    const char* className;
    void* initializer;
    TypeInfo* typeInfo;
    VITable* vitable;
    ITables* itables;
    jint classDataSize;
    jint instanceDataSize;
    jint instanceDataOffset;
    unsigned short classRefCount;
    unsigned short instanceRefCount;
} ClassInfoHeader;

typedef struct {
    Class* clazz;
    jint flags;
    const char* className;
    jint errorType;
    const char* errorMessage;
} ClassInfoError;

typedef struct {
    ClassInfoHeader header;
    jint access;
    jint interfaceCount;
    jint fieldCount;
    jint methodCount;
    char* superclassName;
    void* attributes;
} ClassInfo;

typedef struct {
    jint flags;
    jint access;
    const char* name;
    const char* desc;
    void* attributes;
    jint offset;
} FieldInfo;

typedef struct {
    jint flags;
    jint vtableIndex;
    jint access;
    const char* name;
    const char* desc;
    void* attributes;
    jint size;
    void* impl;
    void* synchronizedImpl;
    void** targetFnPtr;
    void* callbackImpl;
} MethodInfo;

extern void readClassInfo(void** p, ClassInfo* result);
extern const char* readInterfaceName(void** p);
extern void readFieldInfo(void** p, FieldInfo* result);
extern void readMethodInfo(void** p, MethodInfo* result);

static inline void skipInterfaceNames(void** p, ClassInfo* ci) {
    jint i;
    for (i = 0; i < ci->interfaceCount; i++) {
        readInterfaceName(p);
    }
}

static inline void skipFieldInfos(void** p, ClassInfo* ci) {
    jint i;
    for (i = 0; i < ci->fieldCount; i++) {
        readFieldInfo(p, NULL);
    }
}

#endif
