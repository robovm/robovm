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

#include "vmi.h"
#include <string.h>

/* macros for Endian portability
		at() mangles array indices to count 1,0,3,2,5,4,... on BigEndian platforms
		atx() mangles array indices to count 1,0,3,2,5,4,... on BigEndian platforms, except 64 bit platforms
					where counting slots must still go in numerical order
		copysize() mangles array sizes to copy so on BigEndian platforms, 5 becomes 6 because
			the 5th word in memory is skipped and the 5th word of the number is stored in the 6th memory word
*/

#ifdef HY_LITTLE_ENDIAN
#define at(i) (i)
#define copysize(i) (i)
#else
#define at(i) ((i)^1)
#define copysize(i) (((i)+1)&~1)
#endif


#define U64_ADD(addr1, addr2) (*(U_64*)(addr1) += *(U_64*)(addr2))
#define U64_GREATER(addr1, addr2) (*(U_64*)(addr1) > *(U_64*)(addr2))
#define U64_SUBTRACT_LONG(addr1, u32) (*(U_64*)(addr1) -= (U_64)(u32))
#define LONG_MULT(i1,i2,res) (*(U_64*)res = (U_64)i1 * (U_64)i2)
#define LONG_DIV(u64, div) ((U_32)(*(U_64*)(u64) / (U_32)(div)))
#define LONG_REM(u64, div) ((U_32)(*(U_64*)(u64) % (U_32)(div)))

#ifndef HY_WORD64
/* 32 bit */
#define IS_NEGATIVE(ar,len)     (((I_32*)ar)[at(len*2-1)] < 0)
#define atx(i) (at(i))
#else
/* 64 bit */
#define IS_NEGATIVE(ar,len)	(((I_64*)ar)[len-1] < 0L)
#define atx(i) (i)
#endif

#define GET_LENGTH(obj) ((*env)->GetArrayLength(env,obj))
#define GET_ELEMENTS(obj) ((U_32*)(*env)->GetLongArrayElements(env, obj, NULL))
#define RELEASE_ELEMENTS(obj,ar,save) ((*env)->ReleaseLongArrayElements(env, obj, (jlong*)ar, save ? 0 : JNI_ABORT))
#define GET_ELEMENTS_CRITICAL(obj) ((U_32*)(*env)->GetPrimitiveArrayCritical(env, obj, NULL))
#define RELEASE_ELEMENTS_CRITICAL(obj,ar,save) ((*env)->ReleasePrimitiveArrayCritical(env, obj, (jlong*)ar, save ? 0 : JNI_ABORT))
#define NEW_OBJECT(length) ((*env)->NewLongArray(env,length))

jlongArray internalBigIntegerNormalize
PROTOTYPE ((JNIEnv * env, jlongArray src1));
jlongArray internalBigIntegerAdd
PROTOTYPE ((JNIEnv * env, jlongArray src1, jlongArray src2));
static void RIGHT_SHIFT PROTOTYPE ((void *arIn, IDATA lenIn, IDATA shiftval));
jlongArray internalBigIntegerNeg PROTOTYPE ((JNIEnv * env, jlongArray src1));
static U_32 internalIntegerSubtractWithCarry
PROTOTYPE ((U_32 a1, U_32 a2, U_32 * carry));
jlongArray grow PROTOTYPE ((JNIEnv * env, jlongArray src1, jlong element));
static void LEFT_SHIFT PROTOTYPE ((void *arIn, IDATA lenIn, IDATA shiftval));
static U_32 internalIntegerAddWithCarry
PROTOTYPE ((U_32 a1, U_32 a2, U_32 * carry));

static void
LEFT_SHIFT (void *arIn, IDATA lenIn, IDATA shiftval)
{
  IDATA oldAt, newAt, shiftvalr, i, len = (lenIn + 1) / 2;
  U_64 *ar = (U_64 *) arIn;

  oldAt = 0, newAt = shiftval / 64;
  shiftval = shiftval % 64;
  shiftvalr = 64 - shiftval;

  if (shiftval == 0)
    {
      /* straight copy */
      for (i = len - 1; i >= newAt; i--)
        ar[i] = ar[i - newAt];
    }
  else
    {
      for (i = len - 1; i > newAt; i--)
        ar[i] =
          (ar[i - newAt] << shiftval) | (ar[i - newAt - 1] >> shiftvalr);
      /* zero extend low word */
      ar[newAt] = (ar[0] << shiftval);
    }
  for (i = 0; i < newAt; i++)
    ar[newAt] = 0;
}
static void
RIGHT_SHIFT (void *arIn, IDATA lenIn, IDATA shiftval)
{
  IDATA oldAt, newAt, shiftvalr, len = (lenIn + 1) / 2;
  U_64 *ar = (U_64 *) arIn;

  oldAt = shiftval / 64, newAt = 0;
  shiftval = shiftval % 64;
  shiftvalr = 64 - shiftval;

  if (shiftval == 0)
    {
      /* straight copy */
      for (; oldAt < len; oldAt++, newAt++)
        ar[newAt] = ar[oldAt];
    }
  else
    {
      for (; oldAt < len - 1; oldAt++, newAt++)
        ar[newAt] = (ar[oldAt] >> shiftval) | (ar[oldAt + 1] << shiftvalr);
      /* sign extend high word */
      ar[newAt++] = (((I_64) ar[oldAt]) >> shiftval);
    }
  for (; newAt < len; newAt++)
    ar[newAt] = 0;
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_addImpl (JNIEnv * env, jclass cls,
                                               jlongArray src1,
                                               jlongArray src2)
{
  return internalBigIntegerAdd (env, src1, src2);
}

JNIEXPORT jint JNICALL
Java_org_apache_harmony_math_util_BigInteger_compImpl (JNIEnv * env, jclass cls,
                                                jlongArray src1,
                                                jlongArray src2)
{
  /* return -1, 0, 1 if src1 is less than, equal to, or greater than src2 */
  IDATA len1, len2;
  UDATA *ar1, *ar2;
  jint result = 0;
  IDATA neg1 = 0, neg2 = 0, i;

  len1 = GET_LENGTH (src1);
  len2 = GET_LENGTH (src2);

  if (!(ar1 = (UDATA *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  if (!(ar2 = (UDATA *) GET_ELEMENTS_CRITICAL (src2)))
    goto release1;

  neg1 = IS_NEGATIVE (ar1, len1);
  neg2 = IS_NEGATIVE (ar2, len2);

  len1 *= 2;
  len2 *= 2;

  if (neg1 != neg2)             /* different signs */
    result = neg2 ? 1 : -1;
  else
    {
      if (len1 != len2)
        {
          if (neg1 == 0)        /* positive/zero case */
            result = len1 > len2 ? 1 : -1;
          else                  /* negative case */
            result = len1 > len2 ? -1 : 1;
          goto release2;
        }
      else
        {                       /* len1==len2 */
          if (ar1[atx (len1 - 1)] != ar2[atx (len1 - 1)])
            {
              result =
                ((IDATA *) ar1)[atx (len1 - 1)] >
                ((IDATA *) ar2)[atx (len1 - 1)] ? 1 : -1;
              goto release2;
            }
          for (i = len1 - 2; i >= 0; i--)
            if (ar1[atx (i)] != ar2[atx (i)])
              {
                result = ar1[atx (i)] > ar2[atx (i)] ? 1 : -1;
                goto release2;
              }
        }
    }
release2:
  RELEASE_ELEMENTS_CRITICAL (src2, ar2, 0);
release1:
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
done:
  return result;
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_divImpl (JNIEnv * env, jclass cls,
                                               jlongArray topObject,
                                               jlongArray bottomObject)
{
  /* ASSUME that bottomObject is nonzero, ie check and throw was done in java */
  IDATA topLength, bottomLength, topSize, bottomSize, resultLength,
    resultSize;
  jlongArray resultObject = NULL;
  U_32 *topStart = NULL, *bottomStart = NULL, *resultStart = NULL;
  IDATA topAt, bottomAt, resultAt;
  IDATA isNegative = 0;
  I_32 signedTemp;
  IDATA shift, tempNeg;

  U_32 j;
  IDATA fromPtr;
  IDATA fromPtr2;
  IDATA multPtr;
  U_32 v1;
  U_32 v2;
  IDATA highDigit;

  topLength = GET_LENGTH (topObject);
  bottomLength = GET_LENGTH (bottomObject);

  /* make both integers positive, store if the result will be positive or negative */
  if (!(topStart = GET_ELEMENTS_CRITICAL (topObject)))
    goto error;
  tempNeg = IS_NEGATIVE (topStart, topLength);
  RELEASE_ELEMENTS_CRITICAL (topObject, topStart, 0);
  topStart = NULL;
  if (tempNeg)
    {
      isNegative ^= 1;
      if (!(topObject = internalBigIntegerNeg (env, topObject)))
        goto error;
      topLength = GET_LENGTH (topObject);
    }

  if (!(bottomStart = GET_ELEMENTS_CRITICAL (bottomObject)))
    goto error;
  tempNeg = IS_NEGATIVE (bottomStart, bottomLength);
  RELEASE_ELEMENTS_CRITICAL (bottomObject, bottomStart, 0);
  bottomStart = NULL;
  if (tempNeg)
    {
      isNegative ^= 1;
      if (!(bottomObject = internalBigIntegerNeg (env, bottomObject)))
        goto error;
      bottomLength = GET_LENGTH (bottomObject);
    }

  if (!(topStart = GET_ELEMENTS (topObject)))
    goto error;
  if (!(bottomStart = GET_ELEMENTS (bottomObject)))
    goto error;

  /* Get the magnitude of the bottom integer */
  bottomSize = bottomLength * 2;
  while (!bottomStart[at (bottomSize - 1)])
    bottomSize--;

  /* Get the magnitude of the top integer, will need a 0 on the end */
  topSize = topLength * 2;
  while (topSize > 1 && !topStart[at (topSize - 1)]
         && !topStart[at (topSize - 2)])
    topSize--;

  /* easy case if the bottom is a U32 */
  if (bottomSize == 1)
    {
      U_64 temp;
      U_32 divisor = bottomStart[at (0)];

      if (topSize > 1 && !topStart[at (topSize - 1)])
        topSize--;

      /* Add extra int on the end for sign */
      resultSize = topSize + 1;
      resultLength = (resultSize + 1) / 2;
      if (!(resultObject = NEW_OBJECT (resultLength)))
        goto error;
      if (!(resultStart = GET_ELEMENTS (resultObject)))
        goto error;

      topAt = topSize - 1;
      resultAt = resultSize - 2;
      HIGH_LONG (temp) = 0;
      do
        {
          LOW_LONG (temp) = topStart[at (topAt--)];
          resultStart[at (resultAt--)] = LONG_DIV (&temp, divisor);
          HIGH_LONG (temp) = LONG_REM (&temp, divisor);
        }
      while (!(--topSize == 0));

      goto finished;
    }

  /* ensure top starts with a zero int */
  if (topStart[at (topSize - 1)] != 0)
    {
      if (topSize >= topLength * 2)
        {                       /* allocate extra space */
          IDATA i;
          U_32 *tempPtr;
          jlongArray tempObject;
          if (!(tempObject = NEW_OBJECT (topLength + 1)))
            goto error;         /* initialized to zero */
          if (!(tempPtr = GET_ELEMENTS (tempObject)))
            goto error;
          for (i = 0; i < copysize (topSize); i++)
            tempPtr[i] = topStart[i];
          RELEASE_ELEMENTS (topObject, topStart, 0);
          topObject = tempObject;
          topStart = tempPtr;
          topLength++;
        }
      topSize++;
    }

  /* If the bottom int has a larger size than the top int, the result
   *         is 0.  Subtract one from the topSize before the comparison to account
   *                 for the 0 added to the end. */

  if (bottomSize > (topSize - 1))
    {
      /* release objects */
      RELEASE_ELEMENTS (topObject, topStart, 0);
      topStart = NULL;
      RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
      bottomStart = NULL;
      if (!(resultObject = NEW_OBJECT (1)))
        goto error;             /* initialized to zero */
      return resultObject;
    }

  /* Shift the bottom int until its high bit is set */

  highDigit = bottomSize - 1;
  signedTemp = (I_32) bottomStart[at (highDigit)];
  shift = 0;
  while (signedTemp >= 0)
    ++shift, signedTemp <<= 1;
  LEFT_SHIFT (bottomStart, bottomSize, shift);
  LEFT_SHIFT (topStart, topSize, shift);
  v1 = bottomStart[at (highDigit)];
  v2 = bottomStart[at (highDigit - 1)];
  j = topSize - bottomSize;

  /* Create the result object.  It must have an extra long added for sign */
  resultSize = j + 1;
  resultLength = (resultSize + 1) / 2;
  if (!(resultObject = NEW_OBJECT (resultLength)))
    goto error;
  if (!(resultStart = GET_ELEMENTS (resultObject)))
    goto error;

  /* Initialize */
  bottomAt = 0;
  topAt = 0;
  fromPtr = topAt + topSize - 1;
  fromPtr2 = fromPtr - 1;
  resultAt = j;
  multPtr = topAt + j;

  /* Calculate the digits of the result */
  do
    {
      U_64 t1;
      U_64 t2;
      U_32 qHat;
      IDATA from;
      IDATA to;
      U_32 size;
      U_32 carry;

      /* Calculate qHat */

      if ((HIGH_LONG (t1) = topStart[at (fromPtr--)]) == v1)
        {
          qHat = 0xFFFFFFFF;
          --fromPtr2;
        }
      else
        {
          LOW_LONG (t1) = topStart[at (fromPtr)];
          qHat = LONG_DIV (&t1, v1);
          HIGH_LONG (t2) = LONG_REM (&t1, v1);
          LOW_LONG (t2) = topStart[at (--fromPtr2)];
          LONG_MULT (qHat, v2, &t1);
          while (U64_GREATER (&t1, &t2))
            {
              --qHat;
              carry = 0;
              HIGH_LONG (t2) =
                internalIntegerAddWithCarry (HIGH_LONG (t2), v1, &carry);
              /* Stop if t2 grows larger than a U_64 */
              if (carry)
                {
                  break;
                }
              U64_SUBTRACT_LONG (&t1, v2);
            }
        }
      resultStart[at (--resultAt)] = qHat;

      /* Subtract factored part */

      from = bottomAt;
      to = --multPtr;
      size = bottomSize;
      LOW_LONG (t1) = 0;
      carry = 0;
      do
        {
          HIGH_LONG (t1) = 0;
          LONG_MULT (bottomStart[at (from++)], qHat, &t2);
          U64_ADD (&t1, &t2);
          topStart[at (to)] =
            internalIntegerSubtractWithCarry (topStart[at (to)],
                                              LOW_LONG (t1), &carry);
          to++;
          LOW_LONG (t1) = HIGH_LONG (t1);
        }
      while (!(--size == 0));
      topStart[at (to)] =
        internalIntegerSubtractWithCarry (topStart[at (to)], LOW_LONG (t1),
                                          &carry);

      /* Check for add back */

      if (carry)
        {
          --resultStart[at (resultAt)];
          carry = 0;
          from = bottomAt;
          to = multPtr;
          size = bottomSize;
          do
            {
              topStart[at (to)] =
                internalIntegerAddWithCarry (topStart[at (to)],
                                             bottomStart[at (from++)],
                                             &carry);
              to++;
            }
          while (!(--size == 0));
          topStart[at (to)] = 0;
        }
    }
  while (!(--j == 0));

finished:
  /* release objects */
  RELEASE_ELEMENTS (topObject, topStart, 0);
  RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
  RELEASE_ELEMENTS (resultObject, resultStart, 1);

  /* ensure result is normalized */
  if (!(resultObject = internalBigIntegerNormalize (env, resultObject)))
    return NULL;
  /* negate the result if inputs had opposite signs */
  if (isNegative)
    if (!(resultObject = internalBigIntegerNeg (env, resultObject)))
      return NULL;
  return resultObject;

error:
  if (topStart)
    RELEASE_ELEMENTS (topObject, topStart, 0);
  if (bottomStart)
    RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
  if (resultStart)
    RELEASE_ELEMENTS (resultObject, resultStart, 0);
  return NULL;
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_mulImpl (JNIEnv * env, jclass cls,
                                               jlongArray src1,
                                               jlongArray src2)
{
  IDATA len1, len2, lenR;
  jlongArray resultObject = NULL;
  U_32 *ar1 = NULL, *ar2 = NULL, *arR = NULL;
  IDATA isNegative = 0, tempNeg;
  IDATA shortAt, resultAt;

  IDATA shortSize, longSize;
  U_32 *longer, *shorter, *result;

  len1 = GET_LENGTH (src1);
  len2 = GET_LENGTH (src2);

  /* make both integers positive, store if the result will be positive or negative */
  if (!(ar1 = GET_ELEMENTS_CRITICAL (src1)))
    goto error;
  tempNeg = IS_NEGATIVE (ar1, len1);
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
  ar1 = NULL;
  if (tempNeg)
    {
      isNegative ^= 1;
      if (!(src1 = internalBigIntegerNeg (env, src1)))
        goto error;
      len1 = GET_LENGTH (src1);
    }

  if (!(ar2 = GET_ELEMENTS_CRITICAL (src2)))
    goto error;
  tempNeg = IS_NEGATIVE (ar2, len2);
  RELEASE_ELEMENTS_CRITICAL (src2, ar2, 0);
  ar2 = NULL;
  if (tempNeg)
    {
      isNegative ^= 1;
      if (!(src2 = internalBigIntegerNeg (env, src2)))
        goto error;
      len2 = GET_LENGTH (src2);
    }
  if (!(ar1 = GET_ELEMENTS (src1)))
    goto error;
  if (!(ar2 = GET_ELEMENTS (src2)))
    goto error;

  /* make ar1 the longer integer */
  if (len2 > len1)
    {
      void *temp;
      IDATA tempint;
      temp = src1;
      src1 = src2;
      src2 = temp;
      temp = ar1;
      ar1 = ar2;
      ar2 = temp;
      tempint = len1;
      len1 = len2;
      len2 = tempint;
    }

  /* allocate new long of length len1+len2 */
  lenR = len1 + len2;
  if (!(resultObject = NEW_OBJECT (lenR)))
    goto error;
  if (!(arR = GET_ELEMENTS (resultObject)))
    goto error;

  /* Perform long multiplication with the second integer "on the bottom" */
  shorter = ar2;
  shortAt = 0;
  longer = ar1;
  shortSize = len2 * 2;
  longSize = len1 * 2;
  result = arR;
  resultAt = 0;
  do
    {
      U_32 fromShort = shorter[at (shortAt++)];
      IDATA longAt = 0;
      IDATA toAt = resultAt++;
      U_32 size = longSize;
      U_32 addCarry = 0;
      U_32 multCarry = 0;
      U_64 mult;
      U_32 carry;

      do
        {
          LONG_MULT (fromShort, longer[at (longAt++)], &mult);
          carry = 0;
          LOW_LONG (mult) =
            internalIntegerAddWithCarry (LOW_LONG (mult), multCarry, &carry);
          HIGH_LONG (mult) =
            internalIntegerAddWithCarry (HIGH_LONG (mult), 0, &carry);
          multCarry = HIGH_LONG (mult);
          carry = addCarry;
          result[at (toAt)] =
            internalIntegerAddWithCarry (result[at (toAt)], LOW_LONG (mult),
                                         &carry);
          toAt++;
          addCarry = carry;
        }
      while (!(--size == 0));
      result[at (toAt++)] = multCarry + addCarry;
    }
  while (!(--shortSize == 0));

  RELEASE_ELEMENTS (src1, ar1, 0);
  RELEASE_ELEMENTS (src2, ar2, 0);
  RELEASE_ELEMENTS (resultObject, arR, 1);

  /* ensure result is normalized */
  if (!(resultObject = internalBigIntegerNormalize (env, resultObject)))
    return NULL;
  /* negate the result if inputs had opposite signs */
  if (isNegative)
    if (!(resultObject = internalBigIntegerNeg (env, resultObject)))
      return NULL;
  return resultObject;

error:
  if (ar1)
    RELEASE_ELEMENTS (src1, ar1, 0);
  if (ar2)
    RELEASE_ELEMENTS (src2, ar2, 0);
  if (arR)
    RELEASE_ELEMENTS (resultObject, arR, 0);
  return NULL;
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_negImpl (JNIEnv * env, jclass cls,
                                               jlongArray src)
{
  return internalBigIntegerNeg (env, src);
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_remImpl (JNIEnv * env, jclass cls,
                                               jlongArray topObject,
                                               jlongArray bottomObject)
{
  /* ASSUME that bottomObject is nonzero, ie check and throw was done in java */
  IDATA topLength, bottomLength, topSize, bottomSize, resultLength,
    resultSize;
  jlongArray resultObject = NULL;
  U_32 *topStart = NULL, *bottomStart = NULL, *resultStart = NULL;
  IDATA topAt, bottomAt, resultAt, i, topZeroAdded = 0, tempNeg;
  I_32 signedTemp;

  BOOLEAN firstNegative = 0;
  U_32 j;
  IDATA fromPtr, fromPtr2, multPtr;
  U_32 v1;
  U_32 v2;
  UDATA shift;
  IDATA highDigit;

  topLength = GET_LENGTH (topObject);
  bottomLength = GET_LENGTH (bottomObject);

  /* make both integers positive, store if the result will be positive or negative */
  if (!(topStart = GET_ELEMENTS_CRITICAL (topObject)))
    goto error;
  tempNeg = IS_NEGATIVE (topStart, topLength);
  RELEASE_ELEMENTS_CRITICAL (topObject, topStart, 0);
  topStart = NULL;
  if (tempNeg)
    {
      firstNegative = 1;
      if (!(topObject = internalBigIntegerNeg (env, topObject)))
        goto error;
      topLength = GET_LENGTH (topObject);
    }

  if (!(bottomStart = GET_ELEMENTS_CRITICAL (bottomObject)))
    goto error;
  tempNeg = IS_NEGATIVE (bottomStart, bottomLength);
  RELEASE_ELEMENTS_CRITICAL (bottomObject, bottomStart, 0);
  bottomStart = NULL;
  if (tempNeg)
    {
      if (!(bottomObject = internalBigIntegerNeg (env, bottomObject)))
        goto error;
      bottomLength = GET_LENGTH (bottomObject);
    }

  if (!(topStart = GET_ELEMENTS (topObject)))
    goto error;
  if (!(bottomStart = GET_ELEMENTS (bottomObject)))
    goto error;

  /* Get the magnitude of the bottom integer */
  bottomSize = bottomLength * 2;
  while (!bottomStart[at (bottomSize - 1)])
    bottomSize--;

  /* Get the magnitude of the top integer, will need a 0 on the end */
  topSize = topLength * 2;
  while (topSize > 1 && !topStart[at (topSize - 1)]
         && !topStart[at (topSize - 2)])
    topSize--;

  /* easy case of divide by a U32 - answer is a U32 */
  if (bottomSize == 1)
    {
      U_64 temp;
      U_32 divisor = bottomStart[at (0)];

      if (topSize > 1 && !topStart[at (topSize - 1)])
        topSize--;

      resultLength = resultSize = 1;
      if (!(resultObject = NEW_OBJECT (resultLength)))
        goto error;
      if (!(resultStart = GET_ELEMENTS (resultObject)))
        goto error;

      topAt = topSize - 1;

      HIGH_LONG (temp) = 0;
      do
        {
          LOW_LONG (temp) = topStart[at (topAt--)];
          HIGH_LONG (temp) = LONG_REM (&temp, divisor);
        }
      while (!(--topSize == 0));

      resultStart[at (0)] = HIGH_LONG (temp);

      RELEASE_ELEMENTS (topObject, topStart, 0);
      goto finished;
    }

  /* ensure top starts with a zero int */
  if (topStart[at (topSize - 1)] != 0)
    {
      if (topSize >= topLength * 2)
        {                       /* allocate extra space */
          U_32 *tempPtr;
          jlongArray tempObject;
          if (!(tempObject = NEW_OBJECT (topLength + 1)))
            goto error;         /* initialized to zero */
          if (!(tempPtr = GET_ELEMENTS (tempObject)))
            goto error;
          for (i = 0; i < copysize (topSize); i++)
            tempPtr[i] = topStart[i];
          RELEASE_ELEMENTS (topObject, topStart, 0);
          topObject = tempObject;
          topStart = tempPtr;
          topLength++;
          topZeroAdded = 1;
        }
      topSize++;
    }

  /**
	* If the bottom int is larger than the top int, the result is the top
    * int.  Subtract one from the topSize before comparison to account for
    * the 0 added to the end.
    */
  if (bottomSize > (topSize - 1))
    {
      /* clean up and ensure we are returning a new long[] with the same value as that passed in */
      RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
      bottomStart = NULL;

      if (!topZeroAdded)
        {
          if (firstNegative)
            {
              /* un-negate to get a copy */
              RELEASE_ELEMENTS (topObject, topStart, 0);
              topStart = NULL;
              if (!(resultObject = internalBigIntegerNeg (env, topObject)))
                goto error;
            }
          else
            {
              /**
				* we still have the original top, so must copy before returning because
                * we don't want to return the passed in object 
                */
              if (!(resultObject = NEW_OBJECT (topLength)))
                goto error;
              if (!(resultStart = GET_ELEMENTS (resultObject)))
                goto error;
              for (i = 0; i < copysize (topSize); i++)
                resultStart[i] = topStart[i];
              RELEASE_ELEMENTS (topObject, topStart, 0);
              RELEASE_ELEMENTS (resultObject, resultStart, 1);
            }
        }
      else if (topZeroAdded)
        {
          /* else topObject is already a copy, normalize it and return */
          RELEASE_ELEMENTS (topObject, topStart, 1);
          topStart = NULL;
          if (!(resultObject = internalBigIntegerNormalize (env, topObject)))
            goto error;
          if (firstNegative)
            if (!(resultObject = internalBigIntegerNeg (env, resultObject)))
              goto error;
        }
      return resultObject;
    }

  /* Shift the bottom int until its high bit is set */

  highDigit = bottomSize - 1;
  shift = 0;
  signedTemp = (I_32) bottomStart[at (highDigit)];
  while (signedTemp >= 0)
    ++shift, signedTemp <<= 1;
  LEFT_SHIFT (bottomStart, bottomSize, shift);
  LEFT_SHIFT (topStart, topSize, shift);
  v1 = bottomStart[at (highDigit)];
  v2 = bottomStart[at (highDigit - 1)];
  j = topSize - bottomSize;

  /* Initialize */

/**
  * algorithm wants to make the result a modified (in place) top,
  * so allocate new result and copy top into it, point top at the result 
  */
  resultLength = topLength;
  if (!(resultObject = NEW_OBJECT (resultLength)))
    goto error;
  if (!(resultStart = GET_ELEMENTS (resultObject)))
    goto error;
  resultSize = topSize;
  for (i = 0; i < resultLength * 2; i++)
    resultStart[i] = topStart[i];

  /* release top object now, then point at result object */
  RELEASE_ELEMENTS (topObject, topStart, 0);
  topStart = resultStart;
  topAt = 0;

  resultAt = resultSize;
  bottomAt = 0;
  fromPtr = topAt + topSize - 1;
  fromPtr2 = fromPtr - 1;
  multPtr = topAt + j;

  /* Calculate the digits of the result */

  do
    {
      U_64 t1;
      U_64 t2;
      U_32 qHat;
      IDATA from, to;
      U_32 size;
      U_32 carry;

      /* Calculate qHat */

      if ((HIGH_LONG (t1) = topStart[at (fromPtr--)]) == v1)
        {
          qHat = 0xFFFFFFFF;
          --fromPtr2;
        }
      else
        {
          LOW_LONG (t1) = topStart[at (fromPtr)];
          qHat = LONG_DIV (&t1, v1);
          HIGH_LONG (t2) = LONG_REM (&t1, v1);
          LOW_LONG (t2) = topStart[at (--fromPtr2)];
          LONG_MULT (qHat, v2, &t1);
          while (U64_GREATER (&t1, &t2))
            {
              --qHat;
              carry = 0;
              HIGH_LONG (t2) =
                internalIntegerAddWithCarry (HIGH_LONG (t2), v1, &carry);
              /* Stop if t2 grows larger than a U_64 */
              if (carry)
                {
                  break;
                }
              U64_SUBTRACT_LONG (&t1, v2);
            }
        }

      /* Subtract factored part */

      from = bottomAt;
      to = --multPtr;
      size = bottomSize;
      LOW_LONG (t1) = 0;
      carry = 0;
      do
        {
          HIGH_LONG (t1) = 0;
          LONG_MULT (bottomStart[at (from++)], qHat, &t2);
          U64_ADD (&t1, &t2);
          topStart[at (to)] =
            internalIntegerSubtractWithCarry (topStart[at (to)],
                                              LOW_LONG (t1), &carry);
          to++;
          LOW_LONG (t1) = HIGH_LONG (t1);
        }
      while (!(--size == 0));
      topStart[at (to)] =
        internalIntegerSubtractWithCarry (topStart[at (to)], LOW_LONG (t1),
                                          &carry);

      /* Check for add back */

      if (carry)
        {
          carry = 0;
          from = bottomAt;
          to = multPtr;
          size = bottomSize;
          do
            {
              topStart[at (to)] =
                internalIntegerAddWithCarry (topStart[at (to)],
                                             bottomStart[at (from++)],
                                             &carry);
              to++;
            }
          while (!(--size == 0));
          topStart[at (to)] = 0;
        }
    }
  while (!(--j == 0));

  /* Shift the result int back */
  RIGHT_SHIFT (resultStart, resultSize, shift);

finished:
  /* release objects */
  RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
  RELEASE_ELEMENTS (resultObject, resultStart, 1);

  /* ensure result is normalized */
  if (!(resultObject = internalBigIntegerNormalize (env, resultObject)))
    return NULL;
  /* If top integer was negative, the result must be negated.     */
  if (firstNegative)
    if (!(resultObject = internalBigIntegerNeg (env, resultObject)))
      return NULL;
  return resultObject;

error:
  if (topStart)
    RELEASE_ELEMENTS (topObject, topStart, 0);
  if (bottomStart)
    RELEASE_ELEMENTS (bottomObject, bottomStart, 0);
  if (resultStart)
    RELEASE_ELEMENTS (resultObject, resultStart, 0);
  return NULL;

}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_shlImpl (JNIEnv * env, jclass cls,
                                               jlongArray src, jint shiftval)
{
  IDATA len, lenR;
  jlongArray resultObject = NULL;
  U_64 *ar = NULL, *shifted = NULL;
  IDATA oldAt, newAt, shiftvalr, shortenedFlag = 0;

  len = GET_LENGTH (src);

  if (shiftval > 0)
    {                           /* shift left */
      oldAt = 0, newAt = shiftval / 64;
      shiftval = shiftval % 64;
      shiftvalr = 64 - shiftval;
      lenR = len + newAt + 1;

      /* attempt to pre-normalize */
      if (!(ar = (U_64 *) GET_ELEMENTS_CRITICAL (src)))
        goto error;
      if (shiftval == 0
          || ((((I_64) ar[len - 1]) >> shiftvalr) == 0
              && (((I_64) ar[len - 1]) << shiftval) > 0))
        shortenedFlag = 1, lenR--;
      RELEASE_ELEMENTS_CRITICAL (src, ar, 0);
      ar = NULL;

      if (!(resultObject = NEW_OBJECT (lenR)))
        goto error;
      if (!(ar = (U_64 *) GET_ELEMENTS_CRITICAL (src)))
        goto error;
      if (!(shifted = (U_64 *) GET_ELEMENTS_CRITICAL (resultObject)))
        goto error;
      if (shiftval == 0)
        {
          /* straight copy */
          for (; oldAt < len; oldAt++, newAt++)
            shifted[newAt] = ar[oldAt];
        }
      else
        {
          /* zero extend low word */
          shifted[newAt++] = (ar[oldAt++] << shiftval);
          for (; oldAt < len; oldAt++, newAt++)
            shifted[newAt] =
              (ar[oldAt] << shiftval) | (ar[oldAt - 1] >> shiftvalr);
          /* sign extend high word */
          if (!shortenedFlag)
            shifted[newAt] = (((I_64) ar[oldAt - 1]) >> shiftvalr);
        }

    }
  else if (shiftval < 0)
    {                           /* shift right */
      shiftval = -shiftval;
      oldAt = shiftval / 64, newAt = 0;
      shiftval = shiftval % 64;
      shiftvalr = 64 - shiftval;
      if (oldAt >= len)
        {
          /* shifting off the end */
          if (!(resultObject = NEW_OBJECT (1)))
            goto error;
          if (!(shifted = (U_64 *) GET_ELEMENTS_CRITICAL (resultObject)))
            goto error;
          if (!(ar = (U_64 *) GET_ELEMENTS_CRITICAL (src)))
            goto error;
          shifted[0] = (((I_64) ar[len - 1]) < 0) ? -1 : 0;
          RELEASE_ELEMENTS_CRITICAL (src, ar, 0);
          RELEASE_ELEMENTS_CRITICAL (resultObject, shifted, 1);
          return resultObject;
        }
      else
        {
          lenR = len - oldAt;
          if (lenR <= 1)
            lenR = 1;
          else
            {
              /* attempt to pre-normalize */
              if (!(ar = (U_64 *) GET_ELEMENTS_CRITICAL (src)))
                goto error;
              if (shiftval != 0
                  && ((((I_64) ar[len - 1]) >> shiftval) == 0
                      && (((I_64) ar[len - 1]) << shiftvalr) > 0))
                shortenedFlag = 1, lenR--;
              RELEASE_ELEMENTS_CRITICAL (src, ar, 0);
              ar = NULL;
            }

          if (!(resultObject = NEW_OBJECT (lenR)))
            goto error;
          if (!(ar = (U_64 *) GET_ELEMENTS_CRITICAL (src)))
            goto error;
          if (!(shifted = (U_64 *) GET_ELEMENTS_CRITICAL (resultObject)))
            goto error;
          if (shiftval == 0)
            {
              /* straight copy */
              for (; oldAt < len; oldAt++, newAt++)
                shifted[newAt] = ar[oldAt];
            }
          else
            {
              for (; oldAt < len - 1; oldAt++, newAt++)
                shifted[newAt] =
                  (ar[oldAt] >> shiftval) | (ar[oldAt + 1] << shiftvalr);
              /* sign extend high word */
              if (!shortenedFlag)
                shifted[newAt] = (((I_64) ar[oldAt]) >> shiftval);
            }
        }
    }
  else
    {
      /* shift by zero - do nothing */
      return src;
    }

  RELEASE_ELEMENTS_CRITICAL (src, ar, 0);
  RELEASE_ELEMENTS_CRITICAL (resultObject, shifted, 1);

  /* ensure result is normalized */
  if (!(resultObject = internalBigIntegerNormalize (env, resultObject)))
    return NULL;
  return resultObject;

error:
  if (ar)
    RELEASE_ELEMENTS_CRITICAL (src, ar, 0);
  if (shifted)
    RELEASE_ELEMENTS_CRITICAL (resultObject, shifted, 0);
  return NULL;
}

JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_math_util_BigInteger_subImpl (JNIEnv * env, jclass cls,
                                               jlongArray src1,
                                               jlongArray src2)
{
  jlongArray negSrc2;
  negSrc2 = internalBigIntegerNeg (env, src2);
  if (!negSrc2)                 /* OutOfMemory exception was thrown */
    return NULL;
  /* add returns normalized number, so we don't need to renormalize */
  return internalBigIntegerAdd (env, src1, negSrc2);
}

static U_32
internalIntegerAddWithCarry (U_32 a1, U_32 a2, U_32 * carry)
{
  U_32 result = a1 + a2 + *carry;
  *carry = ((a1 > result) || ((a1 == result) && (*carry == 1))) ? 1 : 0;
  return (result);
}

static U_32
internalIntegerSubtractWithCarry (U_32 a1, U_32 a2, U_32 * carry)
{
  U_32 result = a1 - a2 - *carry;
  *carry = ((a2 > a1) || ((a2 == a1) && (*carry == 1))) ? 1 : 0;
  return (result);
}

jlongArray
internalBigIntegerNormalize (JNIEnv * env, jlongArray src1)
{
  IDATA len1, lenR;
  jlong *ar1, *arR;
  IDATA neg1 = 0;
  jlongArray resultObject = NULL;

  len1 = GET_LENGTH (src1);
  if (!(ar1 = (jlong *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  neg1 = IS_NEGATIVE (ar1, len1);

  lenR = len1;
  if (neg1)
    {
      while (lenR >= 2 && ar1[lenR - 1] == -1L && ar1[lenR - 2] < 0L)
        {
          lenR--;
        }
    }
  else
    {
      while (lenR >= 2 && ar1[lenR - 1] == 0L && ar1[lenR - 2] >= 0L)
        {
          lenR--;
        }
    }
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
  if (lenR == len1)
    return src1;

  if (!(resultObject = NEW_OBJECT (lenR)))
    goto done;
  if (!(ar1 = (jlong *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  if (!(arR = (jlong *) GET_ELEMENTS_CRITICAL (resultObject)))
    goto release1;
  memcpy (arR, ar1, lenR * sizeof (jlong));
  RELEASE_ELEMENTS_CRITICAL (resultObject, arR, 0);

release1:
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
done:
  return resultObject;
}

jlongArray
grow (JNIEnv * env, jlongArray src1, jlong element)
{
  IDATA len1;
  jlong *ar1, *arR;
  jlongArray resultObject = NULL;

  len1 = GET_LENGTH (src1);
  if (!(resultObject = NEW_OBJECT (len1 + 1)))
    goto done;

  if (!(ar1 = (jlong *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  if (!(arR = (jlong *) GET_ELEMENTS_CRITICAL (resultObject)))
    goto release1;
  memcpy (arR, ar1, len1 * sizeof (jlong));
  arR[len1] = element;
  RELEASE_ELEMENTS_CRITICAL (resultObject, arR, 0);

release1:
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
done:
  return resultObject;
}

jlongArray
internalBigIntegerAdd (JNIEnv * env, jlongArray src1, jlongArray src2)
{
  IDATA len1, len2;
  U_64 *ar1, *ar2, *arR, s, s2, r;
  IDATA neg1, neg2, negR, i;
  jlongArray resultObject = NULL;
  U_32 carry = 0;

  len1 = GET_LENGTH (src1);
  len2 = GET_LENGTH (src2);
  if (len2 > len1)
    {
      /* Swap src1 and src2, so src1 is longer */
      void *temp;
      IDATA tempint;
      temp = src1;
      src1 = src2;
      src2 = temp;
      tempint = len1;
      len1 = len2;
      len2 = tempint;
    }

  if (!(resultObject = NEW_OBJECT (len1)))
    goto done;

  if (!(ar1 = (U_64 *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  if (!(ar2 = (U_64 *) GET_ELEMENTS_CRITICAL (src2)))
    goto release1;
  if (!(arR = (U_64 *) GET_ELEMENTS_CRITICAL (resultObject)))
    goto release2;

  for (i = 0; i < len2; i++)
    {
      arR[i] = r = (s = ar1[i]) + ar2[i] + carry;
      carry = s > r || (s == r && carry == 1);
    }

  neg2 = IS_NEGATIVE (ar2, len2);
  s2 = neg2 ? -1 : 0;
  for (i = len2; i < len1; i++)
    {
      arR[i] = r = (s = ar1[i]) + s2 + carry;
      carry = s > r || (s == r && carry == 1);
    }

  neg1 = IS_NEGATIVE (ar1, len1);
  RELEASE_ELEMENTS_CRITICAL (src2, ar2, 0);
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);

  negR = IS_NEGATIVE (arR, len1);
  RELEASE_ELEMENTS_CRITICAL (resultObject, arR, 0);

  if (!neg1 && !neg2)
    {
      if (negR)
        {
          resultObject = grow (env, resultObject, 0);
        }
    }
  else if (neg1 && neg2)
    {
      if (!negR)
        {
          resultObject = grow (env, resultObject, -1);
        }
    }
  return internalBigIntegerNormalize (env, resultObject);

release2:
  RELEASE_ELEMENTS_CRITICAL (src2, ar2, 0);
release1:
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
done:
  return resultObject;
}

jlongArray
internalBigIntegerNeg (JNIEnv * env, jlongArray src1)
{
  IDATA len1;
  U_64 *ar1, *arN, *arO;
  IDATA i;
  jlongArray negObject, oneObject;
  jlongArray resultObject = NULL;

  len1 = GET_LENGTH (src1);
  if (!(negObject = NEW_OBJECT (len1)))
    goto done;
  if (!(oneObject = NEW_OBJECT (1)))
    goto done;

  if (!(ar1 = (U_64 *) GET_ELEMENTS_CRITICAL (src1)))
    goto done;
  if (!(arN = (U_64 *) GET_ELEMENTS_CRITICAL (negObject)))
    goto release1;
  if (!(arO = (U_64 *) GET_ELEMENTS_CRITICAL (oneObject)))
    goto releaseN;
  arO[0] = 1;
  RELEASE_ELEMENTS_CRITICAL (oneObject, arO, 0);

  for (i = 0; i < len1; i++)
    {
      arN[i] = ~ar1[i];
    }
  RELEASE_ELEMENTS_CRITICAL (negObject, arN, 0);
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);

  resultObject = internalBigIntegerAdd (env, negObject, oneObject);
  return resultObject;

releaseN:
  RELEASE_ELEMENTS_CRITICAL (negObject, arN, 0);
release1:
  RELEASE_ELEMENTS_CRITICAL (src1, ar1, 0);
done:
  return resultObject;
}
