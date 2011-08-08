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

#include "hycomp.h"

/* Insist that only the IEEE version is built. See also fdlibm.h */
#define HY_FIXED_VERSION fdlibm_ieee

/*	FDLIBM only uses endian for word order, not byte order.
	If this platform does not use the standard word order for doubles
	then pretend we are the other endian.
*/
#ifdef HY_LITTLE_ENDIAN
#ifdef HY_PLATFORM_DOUBLE_ORDER
#define HY_FDLIBM_LITTLE_ENDIAN
#endif
#else
#ifndef HY_PLATFORM_DOUBLE_ORDER
#define HY_FDLIBM_LITTLE_ENDIAN
#endif
#endif


#define acos fdlibm_acos
#define asin fdlibm_asin
#define atan fdlibm_atan
#define atan2 fdlibm_atan2
#define cos fdlibm_cos
#define sin fdlibm_sin
#define tan fdlibm_tan

#define cosh fdlibm_cosh
#define sinh fdlibm_sinh
#define tanh fdlibm_tanh

#define exp fdlibm_exp
#define frexp fdlibm_frexp
#define ldexp fdlibm_ldexp
#define log fdlibm_log
#define log10 fdlibm_log10
#define modf fdlibm_modf

#define pow fdlibm_pow
#define sqrt fdlibm_sqrt

#define ceil fdlibm_ceil
#define fabs fdlibm_fabs
#define floor fdlibm_floor
#define fmod fdlibm_fmod

#define erf fdlibm_erf
#define erfc fdlibm_erfc
#define gamma fdlibm_gamma
#define hypot fdlibm_hypot
#define isnan fdlibm_isnan
#define finite fdlibm_finite
#define j0 fdlibm_j0
#define j1 fdlibm_j1
#define jn fdlibm_jn
#define lgamma fdlibm_lgamma
#define y0 fdlibm_y0
#define y1 fdlibm_y1
#define yn fdlibm_yn

#define acosh fdlibm_acosh
#define asinh fdlibm_asinh
#define atanh fdlibm_atanh
#define cbrt fdlibm_cbrt
#define logb fdlibm_logb
#define nextafter fdlibm_nextafter
#define remainder fdlibm_remainder
#define scalb fdlibm_scalb

#define matherr fdlibm_matherr
#define significand fdlibm_significand
#define copysign fdlibm_copysign
#define ilogb fdlibm_ilogb
#define rint fdlibm_rint
#define scalbn fdlibm_scalbn
#define expm1 fdlibm_expm1
#define log1p fdlibm_log1p
#define gamma_r fdlibm_gamma_r
#define lgamma_r fdlibm_lgamma_r

