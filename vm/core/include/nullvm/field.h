#ifndef NULLVM_FIELD_H
#define NULLVM_FIELD_H

#define FIELD_IS_PUBLIC(f) (IS_PUBLIC((f)->access))
#define FIELD_IS_PRIVATE(f) (IS_PRIVATE((f)->access))
#define FIELD_IS_PROTECTED(f) (IS_PROTECTED((f)->access))
#define FIELD_IS_STATIC(f) (IS_STATIC((f)->access))
#define FIELD_IS_FINAL(f) (IS_FINAL((f)->access))
#define FIELD_IS_PACKAGE_PRIVATE(f) (IS_PACKAGE_PRIVATE((f)->access))

extern Field* nvmGetField(Env* env, Class* clazz, char* name, char* desc);

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

extern Object* nvmGetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jboolean nvmGetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jbyte nvmGetByteClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jchar nvmGetCharClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jshort nvmGetShortClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jint nvmGetIntClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jlong nvmGetLongClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jfloat nvmGetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern jdouble nvmGetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field);
extern void nvmSetObjectClassFieldValue(Env* env, Class* clazz, ClassField* field, Object* value);
extern void nvmSetBooleanClassFieldValue(Env* env, Class* clazz, ClassField* field, jboolean value);
extern void nvmSetByteClassFieldValue(Env* env, Class* clazz, ClassField* field, jbyte value);
extern void nvmSetCharClassFieldValue(Env* env, Class* clazz, ClassField* field, jchar value);
extern void nvmSetShortClassFieldValue(Env* env, Class* clazz, ClassField* field, jshort value);
extern void nvmSetIntClassFieldValue(Env* env, Class* clazz, ClassField* field, jint value);
extern void nvmSetLongClassFieldValue(Env* env, Class* clazz, ClassField* field, jlong value);
extern void nvmSetFloatClassFieldValue(Env* env, Class* clazz, ClassField* field, jfloat value);
extern void nvmSetDoubleClassFieldValue(Env* env, Class* clazz, ClassField* field, jdouble value);

#endif

