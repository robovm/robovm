#ifndef NULLVM_STRING_H
#define NULLVM_STRING_H

extern Object* nvmNewString(Env* env, jchar* s);
extern Object* nvmNewStringUTF(Env* env, char* s);
extern Object* nvmNewStringAscii(Env* env, char* s);

#endif

