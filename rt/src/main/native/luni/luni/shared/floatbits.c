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

#include <string.h>
#include <math.h>
#include "vmi.h"
#include "fltconst.h"

#define DOUBLE_SIGN_MASK				HYCONST64(0x8000000000000000)
#define DOUBLE_EXPONENT_MASK		HYCONST64(0x7FF0000000000000)
#define DOUBLE_MANTISSA_MASK		HYCONST64(0x000FFFFFFFFFFFFF)
#define DOUBLE_NAN_BITS					(DOUBLE_EXPONENT_MASK | HYCONST64(0x0008000000000000))

JNIEXPORT jlong JNICALL
Java_java_lang_Double_doubleToLongBits (JNIEnv * env, jclass cls,
                                        jdouble doubleValue)
{
  jlong longValue = *(jlong *) & doubleValue;
  if ((longValue & DOUBLE_EXPONENT_MASK) == DOUBLE_EXPONENT_MASK)
    {
      if (longValue & DOUBLE_MANTISSA_MASK)
        {
          return DOUBLE_NAN_BITS;
        }
    }
  return longValue;
}

JNIEXPORT jlong JNICALL
Java_java_lang_Double_doubleToRawLongBits (JNIEnv * env, jclass cls,
                                           jdouble doubleValue)
{
  return *(jlong *) & doubleValue;
}

JNIEXPORT jdouble JNICALL
Java_java_lang_Double_longBitsToDouble (JNIEnv * env, jclass cls,
                                        jlong longValue)
{
  return *(jdouble *) & longValue;
}

JNIEXPORT jint JNICALL
Java_java_lang_Float_floatToIntBits (JNIEnv * env, jclass cls,
                                     jfloat floatValue)
{
  jint intValue = *(jint *) & floatValue;
  if ((intValue & SINGLE_EXPONENT_MASK) == SINGLE_EXPONENT_MASK)
    {
      if (intValue & SINGLE_MANTISSA_MASK)
        {
          return SINGLE_NAN_BITS;
        }
    }
  return intValue;
}

JNIEXPORT jint JNICALL
Java_java_lang_Float_floatToRawIntBits (JNIEnv * env, jclass cls,
                                        jfloat floatValue)
{
  return *(jint *) & floatValue;
}

JNIEXPORT jfloat JNICALL
Java_java_lang_Float_intBitsToFloat (JNIEnv * env, jclass cls, jint intValue)
{
  return *(jfloat *) & intValue;
}
