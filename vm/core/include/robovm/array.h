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

