/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

#include "jni.h"
/* #include "fltconst.h" */
#if defined(__P)
#undef __P
#endif /* defined(__P) */
#include "fdlibm.h"

jdouble internal_ceil (jdouble arg1);
jdouble internal_log (jdouble arg1);
jdouble internal_cos (jdouble arg1);
jdouble internal_pow (jdouble arg1, jdouble arg2);
jdouble internal_sqrt (jdouble arg1);
void traceCall (char *name, double *arg1, double *arg2, double *result);
jdouble internal_atan (jdouble arg1);
jdouble internal_atan2 (jdouble arg1, jdouble arg2);
jdouble internal_asin (jdouble arg1);
jdouble internal_IEEEremainder (jdouble arg1, jdouble arg2);
jdouble internal_floor (jdouble arg1);
jdouble internal_acos (jdouble arg1);
jdouble internal_exp (jdouble arg1);
jdouble internal_tan (jdouble arg1);
jdouble internal_sin (jdouble arg1);
jdouble internal_rint (jdouble arg1);
jdouble internal_cbrt (jdouble arg1);
jdouble internal_sinh (jdouble arg1);
jdouble internal_cosh (jdouble arg1);
jdouble internal_tanh (jdouble arg1);
jdouble internal_expm1(jdouble arg1);
jdouble internal_hypot(jdouble arg1, jdouble arg2);
jdouble internal_log1p(jdouble arg1);
jdouble internal_log10(jdouble arg1);
jdouble internal_nextafter(jdouble arg1,jdouble arg2);
jfloat  internal_nextafterf(jfloat arg1,jfloat arg2);

jdouble
internal_acos (jdouble arg1)
{
  jdouble result;

  result = acos (arg1);

  return result;
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_acos (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_acos (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_acos (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_acos (arg1);
}

jdouble
internal_asin (jdouble arg1)
{
  jdouble result;

  result = asin (arg1);

  return result;
}

jdouble
internal_atan (jdouble arg1)
{
  jdouble result;

  result = atan (arg1);

  return result;
}

jdouble
internal_atan2 (jdouble arg1, jdouble arg2)
{
  jdouble result;

  result = atan2 (arg1, arg2);

  return result;
}

jdouble
internal_cbrt (jdouble arg1)
{
  jdouble result;

  result = cbrt (arg1);

  return result;
}

jdouble
internal_ceil (jdouble arg1)
{
  jdouble result;

  result = ceil (arg1);

  return result;
}

jdouble
internal_cos (jdouble arg1)
{
  jdouble result;

  result = cos (arg1);

  return result;
}

jdouble
internal_cosh (jdouble arg1)
{
  jdouble result;

  result = cosh (arg1);

  return result;
}

jdouble
internal_exp (jdouble arg1)
{
  jdouble result;

  result = exp (arg1);

  return result;
}

jdouble
internal_expm1 (jdouble arg1)
{
  jdouble result;

  result = expm1 (arg1);

  return result;
}

jdouble
internal_floor (jdouble arg1)
{
  jdouble result;

  result = floor (arg1);

  return result;
}

jdouble
internal_hypot (jdouble arg1, jdouble arg2)
{
  jdouble result;

  result = hypot (arg1, arg2);

  return result;
}

jdouble
internal_IEEEremainder (jdouble arg1, jdouble arg2)
{
  jdouble result;

  result = remainder (arg1, arg2);

  return result;
}

jdouble
internal_log (jdouble arg1)
{
  jdouble result;

  result = log (arg1);

  return result;
}

jdouble
internal_log10 (jdouble arg1)
{
  jdouble result;

  result = log10 (arg1);

  return result;
}

jdouble
internal_log1p (jdouble arg1)
{
  jdouble result;

  result = log1p (arg1);

  return result;
}

jdouble
internal_nextafter(jdouble arg1,jdouble arg2){
  jdouble result;

  result = nextafter (arg1,arg2);

  return result;
}

/*
 * Please note: this method is just for Float.ulp() use, not necessarilly
 * has same behavior with nextafter(double, double)
 */
jfloat
internal_nextafterf(jfloat arg1,jfloat arg2){
	jint hx = *(jint*)&arg1;		
	jint hy = *(jint*)&arg2;		

	if (!(hx&0x7fffffff)){        /* arg1 == 0 */
      *(jint*)&arg1 = (hy & 0x80000000) | 0x1;
      return arg1;
    }

	if((hx > 0) ^ (hx > hy)){          /* |arg1| < |arg2| */
        hx += 1;
	}else{
        hx -= 1;
    }
    *(jint*)&arg1 = hx;
	return arg1;
}

jdouble
internal_pow (jdouble arg1, jdouble arg2)
{
  jdouble result;

  result = pow (arg1, arg2);

  return result;
}

jdouble
internal_rint (jdouble arg1)
{
  jdouble result;

  result = rint (arg1);

  return result;
}

jdouble
internal_sin (jdouble arg1)
{
  jdouble result;

  result = sin (arg1);

  return result;
}

jdouble
internal_sinh (jdouble arg1)
{
  jdouble result;

  result = sinh (arg1);

  return result;
}

jdouble
internal_sqrt (jdouble arg1)
{
  jdouble result;

  result = sqrt (arg1);

  return result;
}

jdouble
internal_tan (jdouble arg1)
{
  jdouble result;

  result = tan (arg1);

  return result;
}

jdouble
internal_tanh (jdouble arg1)
{
  jdouble result;

  result = tanh (arg1);

  return result;
}


JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_asin (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_asin (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_asin (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_asin (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_atan (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_atan (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_atan (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_atan (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_atan2 (JNIEnv * env, jclass jclazz, jdouble arg1,
                                 jdouble arg2)
{
  return internal_atan2 (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_atan2 (JNIEnv * env, jclass jclazz, jdouble arg1,
                           jdouble arg2)
{
  return internal_atan2 (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_cbrt (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cbrt (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_cbrt (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cbrt (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_ceil (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_ceil (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_ceil (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_ceil (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_cos (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cos (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_cos (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cos (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_cosh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cosh (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_cosh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_cosh (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_exp (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_exp (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_exp (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_exp (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_expm1 (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_expm1 (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_expm1 (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_expm1 (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_floor (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_floor (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_floor (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_floor (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_hypot (JNIEnv * env, jclass jclazz,
                                         jdouble arg1, jdouble arg2)
{
  return internal_hypot (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_hypot (JNIEnv * env, jclass jclazz, jdouble arg1,
                                   jdouble arg2)
{
  return internal_hypot (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_IEEEremainder (JNIEnv * env, jclass jclazz,
                                         jdouble arg1, jdouble arg2)
{
  return internal_IEEEremainder (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_IEEEremainder (JNIEnv * env, jclass jclazz, jdouble arg1,
                                   jdouble arg2)
{
  return internal_IEEEremainder (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_log (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_log (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_log10 (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log10 (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_log10 (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log10 (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_log1p (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log1p (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_log1p(JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_log1p (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_nextafter (JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2)
{
  return internal_nextafter(arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_nextafter (JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2)
{
  return internal_nextafter(arg1, arg2);
}

JNIEXPORT jfloat JNICALL
Java_java_lang_StrictMath_nextafterf (JNIEnv * env, jclass jclazz, jfloat arg1, jfloat arg2)
{
  return internal_nextafterf(arg1, arg2);
}

JNIEXPORT jfloat JNICALL
Java_java_lang_Math_nextafterf (JNIEnv * env, jclass jclazz, jfloat arg1, jfloat arg2)
{
  return internal_nextafterf(arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_pow (JNIEnv * env, jclass jclazz, jdouble arg1,
                               jdouble arg2)
{
  return internal_pow (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_pow (JNIEnv * env, jclass jclazz, jdouble arg1,
                         jdouble arg2)
{
  return internal_pow (arg1, arg2);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_rint (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_rint (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_rint (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_rint (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_sin (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sin (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_sin (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sin (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_sinh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sinh (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_sinh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sinh (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_sqrt (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sqrt (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_sqrt (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_sqrt (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_tan (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_tan (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_tan (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_tan (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_StrictMath_tanh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_tanh (arg1);
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Math_tanh (JNIEnv * env, jclass jclazz, jdouble arg1)
{
  return internal_tanh (arg1);
}

