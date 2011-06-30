#include <nullvm.h>

Object* createMethodObject(Env* env, Method* method);
Object* createFieldObject(Env* env, Field* field);
Object* createConstructorObject(Env* env, Method* method);
Field* getFieldFromFieldObject(Env* env, Object* fieldObject);
jvalue* validateAndUnwrapArgs(Env* env, ObjectArray* parameterTypes, ObjectArray* args);
jboolean unwrapBoolean(Env* env, Object* arg, jvalue* value);
jboolean unwrapByte(Env* env, Object* arg, jvalue* value);
jboolean unwrapChar(Env* env, Object* arg, jvalue* value);
jboolean unwrapShort(Env* env, Object* arg, jvalue* value);
jboolean unwrapInt(Env* env, Object* arg, jvalue* value);
jboolean unwrapLong(Env* env, Object* arg, jvalue* value);
jboolean unwrapFloat(Env* env, Object* arg, jvalue* value);
jboolean unwrapDouble(Env* env, Object* arg, jvalue* value);
jboolean unwrapPrimitive(Env* env, Object* arg, Class* parameterType, jvalue* value);
Object* wrapBoolean(Env* env, jboolean v);
Object* wrapByte(Env* env, jbyte v);
Object* wrapChar(Env* env, jchar v);
Object* wrapShort(Env* env, jshort v);
Object* wrapInt(Env* env, jint v);
Object* wrapLong(Env* env, jlong v);
Object* wrapFloat(Env* env, jfloat v);
Object* wrapDouble(Env* env, jdouble v);

