#ifndef ROBOVM_STRING_H
#define ROBOVM_STRING_H

extern jboolean rvmInitStrings(Env* env);
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

