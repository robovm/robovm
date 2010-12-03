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

package org.apache.harmony.luni.util;


public final class NumberConverter {

	private int setCount; // number of times u and k have been gotten

	private int getCount; // number of times u and k have been set

	private int[] uArray = new int[64];

	private int firstK;

	private final static double invLogOfTenBaseTwo = Math.log(2.0)
			/ Math.log(10.0);

	private final static long[] TEN_TO_THE = new long[20];

	static {
		TEN_TO_THE[0] = 1L;
		for (int i = 1; i < TEN_TO_THE.length; ++i) {
			long previous = TEN_TO_THE[i - 1];
			TEN_TO_THE[i] = (previous << 1) + (previous << 3);
		}
	}

	private static NumberConverter getConverter() {
		return new NumberConverter();
	}

	public static String convert(double input) {
		return getConverter().convertD(input);
	}

	public static String convert(float input) {
		return getConverter().convertF(input);
	}

	public String convertD(double inputNumber) {
		int p = 1023 + 52; // the power offset (precision)
		long signMask = 0x8000000000000000L; // the mask to get the sign of
		// the number
		long eMask = 0x7FF0000000000000L; // the mask to get the power bits
		long fMask = 0x000FFFFFFFFFFFFFL; // the mask to get the significand
		// bits

		long inputNumberBits = Double.doubleToLongBits(inputNumber);
		// the value of the sign... 0 is positive, ~0 is negative
		String signString = (inputNumberBits & signMask) == 0 ? "" : "-";
		// the value of the 'power bits' of the inputNumber
		int e = (int) ((inputNumberBits & eMask) >> 52);
		// the value of the 'significand bits' of the inputNumber
		long f = inputNumberBits & fMask;
		boolean mantissaIsZero = f == 0;
		int pow = 0, numBits = 52;

		if (e == 2047)
			return mantissaIsZero ? signString + "Infinity" : "NaN";
		if (e == 0) {
			if (mantissaIsZero)
				return signString + "0.0";
			if (f == 1)
				// special case to increase precision even though 2 *
				// Double.MIN_VALUE is 1.0e-323
				return signString + "4.9E-324";
			pow = 1 - p; // a denormalized number
			long ff = f;
			while ((ff & 0x0010000000000000L) == 0) {
				ff = ff << 1;
				numBits--;
			}
		} else {
			// 0 < e < 2047
			// a "normalized" number
			f = f | 0x0010000000000000L;
			pow = e - p;
		}

		if (-59 < pow && pow < 6 || (pow == -59 && !mantissaIsZero))
			longDigitGenerator(f, pow, e == 0, mantissaIsZero, numBits);
		else
			bigIntDigitGeneratorInstImpl(f, pow, e == 0, mantissaIsZero,
					numBits);

		if (inputNumber >= 1e7D || inputNumber <= -1e7D
				|| (inputNumber > -1e-3D && inputNumber < 1e-3D))
			return signString + freeFormatExponential();
		
		return signString + freeFormat();
	}

	public String convertF(float inputNumber) {
		int p = 127 + 23; // the power offset (precision)
		int signMask = 0x80000000; // the mask to get the sign of the number
		int eMask = 0x7F800000; // the mask to get the power bits
		int fMask = 0x007FFFFF; // the mask to get the significand bits

		int inputNumberBits = Float.floatToIntBits(inputNumber);
		// the value of the sign... 0 is positive, ~0 is negative
		String signString = (inputNumberBits & signMask) == 0 ? "" : "-";
		// the value of the 'power bits' of the inputNumber
		int e = (inputNumberBits & eMask) >> 23;
		// the value of the 'significand bits' of the inputNumber
		int f = inputNumberBits & fMask;
		boolean mantissaIsZero = f == 0;
		int pow = 0, numBits = 23;

		if (e == 255)
			return mantissaIsZero ? signString + "Infinity" : "NaN";
		if (e == 0) {
			if (mantissaIsZero)
				return signString + "0.0";
			pow = 1 - p; // a denormalized number
			if (f < 8) { // want more precision with smallest values
				f = f << 2;
				pow -= 2;
			}
			int ff = f;
			while ((ff & 0x00800000) == 0) {
				ff = ff << 1;
				numBits--;
			}
		} else {
			// 0 < e < 255
			// a "normalized" number
			f = f | 0x00800000;
			pow = e - p;
		}

		if (-59 < pow && pow < 35 || (pow == -59 && !mantissaIsZero))
			longDigitGenerator(f, pow, e == 0, mantissaIsZero, numBits);
		else
			bigIntDigitGeneratorInstImpl(f, pow, e == 0, mantissaIsZero,
					numBits);
		if (inputNumber >= 1e7f || inputNumber <= -1e7f
				|| (inputNumber > -1e-3f && inputNumber < 1e-3f))
			return signString + freeFormatExponential();
		
		return signString + freeFormat();
	}

	private String freeFormatExponential() {
		// corresponds to process "Free-Format Exponential"
		char[] formattedDecimal = new char[25];
		formattedDecimal[0] = (char) ('0' + uArray[getCount++]);
		formattedDecimal[1] = '.';
		// the position the next character is to be inserted into
		// formattedDecimal
		int charPos = 2;

		int k = firstK;
		int expt = k;
		while (true) {
			k--;
			if (getCount >= setCount)
				break;

			formattedDecimal[charPos++] = (char) ('0' + uArray[getCount++]);
		}

		if (k == expt - 1)
			formattedDecimal[charPos++] = '0';
		formattedDecimal[charPos++] = 'E';
		return new String(formattedDecimal, 0, charPos)
				+ Integer.toString(expt);
	}

	private String freeFormat() {
		// corresponds to process "Free-Format"
		char[] formattedDecimal = new char[25];
		// the position the next character is to be inserted into
		// formattedDecimal
		int charPos = 0;
		int k = firstK;
		if (k < 0) {
			formattedDecimal[0] = '0';
			formattedDecimal[1] = '.';
			charPos += 2;
			for (int i = k + 1; i < 0; i++)
				formattedDecimal[charPos++] = '0';
		}

		int U = uArray[getCount++];
		do {
			if (U != -1)
				formattedDecimal[charPos++] = (char) ('0' + U);
			else if (k >= -1)
				formattedDecimal[charPos++] = '0';

			if (k == 0)
				formattedDecimal[charPos++] = '.';

			k--;
			U = getCount < setCount ? uArray[getCount++] : -1;
		} while (U != -1 || k >= -1);
		return new String(formattedDecimal, 0, charPos);
	}

	private native void bigIntDigitGeneratorInstImpl(long f, int e,
			boolean isDenormalized, boolean mantissaIsZero, int p);

	private void longDigitGenerator(long f, int e, boolean isDenormalized,
			boolean mantissaIsZero, int p) {
		long R, S, M;
		if (e >= 0) {
			M = 1l << e;
			if (!mantissaIsZero) {
				R = f << (e + 1);
				S = 2;
			} else {
				R = f << (e + 2);
				S = 4;
			}
		} else {
			M = 1;
			if (isDenormalized || !mantissaIsZero) {
				R = f << 1;
				S = 1l << (1 - e);
			} else {
				R = f << 2;
				S = 1l << (2 - e);
			}
		}

		int k = (int) Math.ceil((e + p - 1) * invLogOfTenBaseTwo - 1e-10);

		if (k > 0) {
			S = S * TEN_TO_THE[k];
		} else if (k < 0) {
			long scale = TEN_TO_THE[-k];
			R = R * scale;
			M = M == 1 ? scale : M * scale;
		}

		if (R + M > S) { // was M_plus
			firstK = k;
		} else {
			firstK = k - 1;
			R = R * 10;
			M = M * 10;
		}

		getCount = setCount = 0; // reset indices
		boolean low, high;
		int U;
		long[] Si = new long[] { S, S << 1, S << 2, S << 3 };
		while (true) {
			// set U to be floor (R / S) and R to be the remainder
			// using a kind of "binary search" to find the answer.
			// It's a lot quicker than actually dividing since we know
			// the answer will be between 0 and 10
			U = 0;
			long remainder;
			for (int i = 3; i >= 0; i--) {
				remainder = R - Si[i];
				if (remainder >= 0) {
					R = remainder;
					U += 1 << i;
				}
			}

			low = R < M; // was M_minus
			high = R + M > S; // was M_plus

			if (low || high)
				break;

			R = R * 10;
			M = M * 10;
			uArray[setCount++] = U;
		}
		if (low && !high)
			uArray[setCount++] = U;
		else if (high && !low)
			uArray[setCount++] = U + 1;
		else if ((R << 1) < S)
			uArray[setCount++] = U;
		else
			uArray[setCount++] = U + 1;
	}
}
