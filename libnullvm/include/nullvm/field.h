#ifndef NULLVM_FIELD_H
#define NULLVM_FIELD_H

/**
 * Returns the class field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IncompatibleClassChangeError if the field hasn't got the ACC_STATIC modifier.
 */
extern ClassField* nvmGetClassField(Env* env, Class* clazz, char* name, char* desc);

/**
 * Returns the instance field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field has got the ACC_STATIC modifier.
 */
extern InstanceField* nvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc);

extern Object* nvmGetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jboolean nvmGetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jbyte nvmGetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jchar nvmGetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jshort nvmGetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jint nvmGetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jlong nvmGetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jfloat nvmGetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern jdouble nvmGetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field);
extern void nvmSetObjectInstanceFieldValue(Env* env, Object* obj, InstanceField* field, Object* value);
extern void nvmSetBooleanInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jboolean value);
extern void nvmSetByteInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jbyte value);
extern void nvmSetCharInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jchar value);
extern void nvmSetShortInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jshort value);
extern void nvmSetIntInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jint value);
extern void nvmSetLongInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jlong value);
extern void nvmSetFloatInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jfloat value);
extern void nvmSetDoubleInstanceFieldValue(Env* env, Object* obj, InstanceField* field, jdouble value);

#endif

