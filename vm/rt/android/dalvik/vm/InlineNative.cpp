/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2012 Trillian AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <math.h>
#include <jni.h>

// RoboVM note: isnanf is not available on Darwin
#if defined(__APPLE__)
#   define isnanf(X) isnan(X)
#endif

/*
 * ===========================================================================
 *      java.lang.Math
 * ===========================================================================
 */

union Convert32 {
    jint arg;
    float ff;
};

union Convert64 {
    jlong arg;
	jlong ll;
    double dd;
};

/*
 * public static float abs(float)
 */
extern "C" float Java_java_lang_Math_abs__float(JNIEnv*, jclass, float f)
{
    Convert32 convert;
    convert.ff = f;
    /* clear the sign bit; assumes a fairly common fp representation */
    convert.arg &= 0x7fffffff;
    return convert.ff;
}

/*
 * public static double abs(double)
 */
extern "C" double Java_java_lang_Math_abs__double(JNIEnv*, jclass, double d)
{
    Convert64 convert;
    convert.dd = d;
    /* clear the sign bit in the (endian-dependent) high word */
    convert.ll &= 0x7fffffffffffffffULL;
    return convert.dd;
}

/*
 * public static double sqrt(double)
 *
 * With ARM VFP enabled, gcc turns this into an fsqrtd instruction, followed
 * by an fcmpd of the result against itself.  If it doesn't match (i.e.
 * it's NaN), the libm sqrt() is invoked.
 */
extern "C" double Java_java_lang_Math_sqrt(JNIEnv*, jclass, double d)
{
    return sqrt(d);
}

/*
 * public static double cos(double)
 */
extern "C" double Java_java_lang_Math_cos(JNIEnv*, jclass, double d)
{
    return cos(d);
}

/*
 * public static double sin(double)
 */
extern "C" double Java_java_lang_Math_sin(JNIEnv*, jclass, double d)
{
    return sin(d);
}

/*
 * ===========================================================================
 *      java.lang.Float
 * ===========================================================================
 */

extern "C" jint Java_java_lang_Float_floatToIntBits(JNIEnv*, jclass, float value)
{
    Convert32 convert;
    convert.arg = value;
    return isnanf(convert.ff) ? 0x7fc00000 : convert.arg;
}

extern "C" jint Java_java_lang_Float_floatToRawIntBits(JNIEnv*, jclass, float value)
{
    Convert32 convert;
    convert.ff = value;
    return convert.arg;
}

extern "C" float Java_java_lang_Float_intBitsToFloat(JNIEnv*, jclass, jint bits)
{
    Convert32 convert;
    convert.arg = bits;
    return convert.ff;
}

/*
 * ===========================================================================
 *      java.lang.Double
 * ===========================================================================
 */

extern "C" jlong Java_java_lang_Double_doubleToLongBits(JNIEnv*, jclass, double value)
{
    Convert64 convert;
    convert.dd = value;
    return isnan(convert.dd) ? 0x7ff8000000000000LL : convert.ll;
}

extern "C" jlong Java_java_lang_Double_doubleToRawLongBits(JNIEnv*, jclass, double value)
{
    Convert64 convert;
    convert.dd = value;
    return convert.ll;
}

extern "C" double Java_java_lang_Double_longBitsToDouble(JNIEnv*, jclass, jlong bits)
{
    Convert64 convert;
    convert.ll = bits;
    return convert.dd;
}

