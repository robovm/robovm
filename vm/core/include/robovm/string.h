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
#ifndef ROBOVM_STRING_H
#define ROBOVM_STRING_H

extern jboolean rvmInitStrings(Env* env);
extern Object* rvmNewStringNoCopy(Env* env, CharArray* value, jint offset, jint length);
extern Object* rvmNewString(Env* env, const jchar* chars, jint length);
extern Object* rvmNewStringUTF(Env* env, const char* s, jint length);
extern Object* rvmNewStringAscii(Env* env, const char* s, jint length);
extern Object* rvmNewInternedStringUTF(Env* env, const char* s, jint length);
extern Object* rvmInternString(Env* env, Object* str);
extern jint rvmGetStringLength(Env* env, Object* str);
extern jchar* rvmGetStringChars(Env* env, Object* str);
extern jint rvmGetStringUTFLength(Env* env, Object* str);
extern char* rvmGetStringUTFChars(Env* env, Object* str);
extern void rvmGetStringRegion(Env* env, Object* str, jint start, jint len, jchar* buf);
extern void rvmGetStringUTFRegion(Env* env, Object* str, jint start, jint len, char* buf);

#endif

