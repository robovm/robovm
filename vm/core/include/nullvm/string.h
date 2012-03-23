#ifndef NULLVM_STRING_H
#define NULLVM_STRING_H

extern jboolean nvmInitStrings(Env* env);
extern Object* nvmNewString(Env* env, const jchar* chars, jint length);
extern Object* nvmNewStringUTF(Env* env, const char* s, jint length);
extern Object* nvmNewStringAscii(Env* env, const char* s, jint length);
extern Object* nvmNewInternedStringUTF(Env* env, const char* s, jint length);
extern Object* nvmInternString(Env* env, Object* str);
extern jint nvmGetStringLength(Env* env, Object* str);
extern jchar* nvmGetStringChars(Env* env, Object* str);
extern jint nvmGetStringUTFLength(Env* env, Object* str);
extern char* nvmGetStringUTFChars(Env* env, Object* str);
extern void nvmGetStringRegion(Env* env, Object* str, jint start, jint len, jchar* buf);
extern void nvmGetStringUTFRegion(Env* env, Object* str, jint start, jint len, char* buf);

#endif

