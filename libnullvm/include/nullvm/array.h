#ifndef NULLVM_ARRAY_H
#define NULLVM_ARRAY_H

extern BooleanArray* nvmNewBooleanArray(Env* env, jint length);
extern ByteArray* nvmNewByteArray(Env* env, jint length);
extern CharArray* nvmNewCharArray(Env* env, jint length);
extern ShortArray* nvmNewShortArray(Env* env, jint length);
extern IntArray* nvmNewIntArray(Env* env, jint length);
extern LongArray* nvmNewLongArray(Env* env, jint length);
extern FloatArray* nvmNewFloatArray(Env* env, jint length);
extern DoubleArray* nvmNewDoubleArray(Env* env, jint length);
extern ObjectArray* nvmNewObjectArray(Env* env, jint length, Class* elementClass, Class* arrayClass, Object* init);
extern Array* nvmNewMultiArray(Env* env, jint dims, jint* lengths, Class* type);
extern Array* nvmCloneArray(Env* env, Array* array);
extern jint nvmGetArrayDimensions(Env* env, Array* array);

#endif

