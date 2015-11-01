#include <jni.h>

JNIEXPORT jint JNICALL Java_org_robovm_rt_StaticJNITest_add(JNIEnv* env, jclass cls, jint a, jint b) {
	return a + b;
}

JNIEXPORT jint JNICALL Java_org_robovm_rt_StaticJNITest_mul__II(JNIEnv* env, jclass cls, jint a, jint b) {
	return a * b;
}

JNIEXPORT jint JNICALL Java_org_robovm_rt_StaticJNITest_sub__II(JNIEnv* env, jclass cls, jint a, jint b) {
	return a - b;
}

JNIEXPORT jfloat JNICALL Java_org_robovm_rt_StaticJNITest_sub__FF(JNIEnv* env, jclass cls, jfloat a, jfloat b) {
	return a - b;
}

JNIEXPORT void JNICALL Java_org_robovm_rt_StaticJNITest_noArgsShort(JNIEnv* env, jclass cls) {
	jfieldID called = (*env)->GetStaticFieldID(env, cls, "called", "Z");
	if (!called) return;
	(*env)->SetStaticBooleanField(env, cls, called, JNI_TRUE);
}

JNIEXPORT void JNICALL Java_org_robovm_rt_StaticJNITest_noArgsLong__(JNIEnv* env, jclass cls) {
	jfieldID called = (*env)->GetStaticFieldID(env, cls, "called", "Z");
	if (!called) return;
	(*env)->SetStaticBooleanField(env, cls, called, JNI_TRUE);
}
