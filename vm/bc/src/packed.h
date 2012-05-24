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
#ifndef PACKED_H
#define PACKED_H

#include <robovm.h>

static inline jbyte readByte(void** p) {
    jbyte v = *(jbyte*) *p;
    *p += sizeof(jbyte);
    return v;
}

static inline jchar readChar(void** p) {
    jchar v = *(jchar*) *p;
    *p += sizeof(jchar);
    return v;
}

static inline jshort readShort(void** p) {
    jshort v = *(jshort*) *p;
    *p += sizeof(jshort);
    return v;
}

static inline jint readInt(void** p) {
    jint v = *(jint*) *p;
    *p += sizeof(jint);
    return v;
}

static inline jlong readLong(void** p) {
    jlong v = *(jlong*) *p;
    *p += sizeof(jlong);
    return v;
}

static inline jfloat readFloat(void** p) {
    jfloat v = *(jfloat*) *p;
    *p += sizeof(jfloat);
    return v;
}

static inline jdouble readDouble(void** p) {
    jdouble v = *(jdouble*) *p;
    *p += sizeof(jdouble);
    return v;
}

static inline char* readString(void** p) {
    char* v = *(char**) *p;
    *p += sizeof(char*);
    return v;
}

static inline void* readPtr(void** p) {
    void* v = *(void**) *p;
    *p += sizeof(void*);
    return v;
}

static inline void writeInt(void** p, jint v) {
    *(jint*) *p = v;
    *p += sizeof(jint);
}

#endif

