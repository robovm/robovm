#ifndef NULLVM_FIELD_H
#define NULLVM_FIELD_H

/**
 * Returns the class field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IncompatibleClassChangeError if the field hasn't got the ACC_STATIC modifier.
 */
extern Field* nvmGetClassField(Env* env, Class* clazz, char* name, char* desc);

/**
 * Returns the instance field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field has got the ACC_STATIC modifier.
 */
extern Field* nvmGetInstanceField(Env* env, Class* clazz, char* name, char* desc);

extern void* nvmCreateFieldGetter(Env* env, Class* clazz, Field* field);
extern void* nvmCreateFieldSetter(Env* env, Class* clazz, Field* field);

#endif

