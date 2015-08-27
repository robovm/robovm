#include <jni.h>

JNIEXPORT jint JNICALL Java_org_robovm_rt_DynamicJNITest_add(JNIEnv* env, jclass cls, jint a, jint b) {
	return a + b;
}

JNIEXPORT jint JNICALL Java_org_robovm_rt_DynamicJNITest_mul__II(JNIEnv* env, jclass cls, jint a, jint b) {
	return a * b;
}

JNIEXPORT jint JNICALL Java_org_robovm_rt_DynamicJNITest_sub__II(JNIEnv* env, jclass cls, jint a, jint b) {
	return a - b;
}

JNIEXPORT jfloat JNICALL Java_org_robovm_rt_DynamicJNITest_sub__FF(JNIEnv* env, jclass cls, jfloat a, jfloat b) {
	return a - b;
}

JNIEXPORT void JNICALL Java_org_robovm_rt_DynamicJNITest_noArgsShort(JNIEnv* env, jclass cls) {
	jfieldID called = (*env)->GetStaticFieldID(env, cls, "called", "Z");
	if (!called) return;
	(*env)->SetStaticBooleanField(env, cls, called, JNI_TRUE);
}

JNIEXPORT void JNICALL Java_org_robovm_rt_DynamicJNITest_noArgsLong__(JNIEnv* env, jclass cls) {
	jfieldID called = (*env)->GetStaticFieldID(env, cls, "called", "Z");
	if (!called) return;
	(*env)->SetStaticBooleanField(env, cls, called, JNI_TRUE);
}

static void boundUsingRegisterNatives(JNIEnv* env, jclass cls) {
	jfieldID called = (*env)->GetStaticFieldID(env, cls, "called", "Z");
	if (!called) return;
	(*env)->SetStaticBooleanField(env, cls, called, JNI_TRUE);
}

JNIEXPORT void JNICALL Java_org_robovm_rt_DynamicJNITest_registerNatives(JNIEnv* env, jclass cls) {
	JNINativeMethod methods[1];
	methods[0].name = "boundUsingRegisterNatives";
	methods[0].signature = "()V";
	methods[0].fnPtr = (void*) boundUsingRegisterNatives;
	(*env)->RegisterNatives(env, cls, methods, 1);
}
