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

package java.lang;

import java.util.Random;

/**
 * Class StrictMath provides basic math constants and operations such as
 * trigonometric functions, hyperbolic functions, exponential, logarithms, etc.
 * <p>
 * In contrast to class {@link Math}, the methods in this class return exactly
 * the same results on all platforms. Algorithms based on these methods thus
 * behave the same (e.g. regarding numerical convergence) on all platforms,
 * complying with the slogan "write once, run everywhere". On the other side,
 * the implementation of class StrictMath may be less efficient than that of
 * class Math, as class StrictMath cannot utilize platform specific features
 * such as an extended precision math co-processors.
 * <p>
 * The methods in this class are specified using the "Freely Distributable Math
 * Library" (fdlibm), version 5.3.
 * <p>
 * <a href="http://www.netlib.org/fdlibm/">http://www.netlib.org/fdlibm/</a>
 */
public final class StrictMath {
    private static final int FLOAT_EXPONENT_BIAS = 127;

    private static final int FLOAT_EXPONENT_MASK = 0x7F800000;

    private static final int DOUBLE_EXPONENT_BITS = 12;

    private static final int DOUBLE_MANTISSA_BITS = 52;
    
    private static final int FLOAT_EXPONENT_BITS = 9;
    
    private static final int FLOAT_MANTISSA_BITS = 23;  

    private static final int DOUBLE_EXPONENT_BIAS = 1023;

    private static final long DOUBLE_EXPONENT_MASK = 0x7ff0000000000000L;

    private static final int FLOAT_MANTISSA_MASK = 0x007fffff;

    private static final int FLOAT_SIGN_MASK = 0x80000000;

    private static final long DOUBLE_MANTISSA_MASK = 0x000fffffffffffffL;

    private static final long DOUBLE_SIGN_MASK = 0x8000000000000000L;

    /**
     * The double value closest to e, the base of the natural logarithm.
     */
    public final static double E = Math.E;

    /**
     * The double value closest to pi, the ratio of a circle's circumference to
     * its diameter.
     */
    public final static double PI = Math.PI;

    private static java.util.Random random;

    /**
     * Prevents this class from being instantiated.
     */
    private StrictMath() {
    }

    /**
     * Returns the absolute value of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code abs(-0.0) = +0.0}</li>
     * <li>{@code abs(+infinity) = +infinity}</li>
     * <li>{@code abs(-infinity) = +infinity}</li>
     * <li>{@code abs(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose absolute value has to be computed.
     * @return the absolute value of the argument.
     */
    public static double abs(double d) {
        long bits = Double.doubleToLongBits(d);
        bits &= 0x7fffffffffffffffL;
        return Double.longBitsToDouble(bits);
    }

    /**
     * Returns the absolute value of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code abs(-0.0) = +0.0}</li>
     * <li>{@code abs(+infinity) = +infinity}</li>
     * <li>{@code abs(-infinity) = +infinity}</li>
     * <li>{@code abs(NaN) = NaN}</li>
     * </ul>
     *
     * @param f
     *            the value whose absolute value has to be computed.
     * @return the argument if it is positive, otherwise the negation of the
     *         argument.
     */
    public static float abs(float f) {
        int bits = Float.floatToIntBits(f);
        bits &= 0x7fffffff;
        return Float.intBitsToFloat(bits);
    }

    /**
     * Returns the absolute value of the argument.
     * <p>
     * If the argument is {@code Integer.MIN_VALUE}, {@code Integer.MIN_VALUE}
     * is returned.
     *
     * @param i
     *            the value whose absolute value has to be computed.
     * @return the argument if it is positive, otherwise the negation of the
     *         argument.
     */
    public static int abs(int i) {
        return i >= 0 ? i : -i;
    }

    /**
     * Returns the absolute value of the argument.
     * <p>
     * If the argument is {@code Long.MIN_VALUE}, {@code Long.MIN_VALUE} is
     * returned.
     *
     * @param l
     *            the value whose absolute value has to be computed.
     * @return the argument if it is positive, otherwise the negation of the
     *         argument.
     */
    public static long abs(long l) {
        return l >= 0 ? l : -l;
    }

    /**
     * Returns the closest double approximation of the arc cosine of the
     * argument within the range {@code [0..pi]}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code acos((anything > 1) = NaN}</li>
     * <li>{@code acos((anything < -1) = NaN}</li>
     * <li>{@code acos(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value to compute arc cosine of.
     * @return the arc cosine of the argument.
     */
    public static native double acos(double d);

    /**
     * Returns the closest double approximation of the arc sine of the argument
     * within the range {@code [-pi/2..pi/2]}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code asin((anything > 1)) = NaN}</li>
     * <li>{@code asin((anything < -1)) = NaN}</li>
     * <li>{@code asin(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose arc sine has to be computed.
     * @return the arc sine of the argument.
     */
    public static native double asin(double d);

    /**
     * Returns the closest double approximation of the arc tangent of the
     * argument within the range {@code [-pi/2..pi/2]}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code atan(+0.0) = +0.0}</li>
     * <li>{@code atan(-0.0) = -0.0}</li>
     * <li>{@code atan(+infinity) = +pi/2}</li>
     * <li>{@code atan(-infinity) = -pi/2}</li>
     * <li>{@code atan(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose arc tangent has to be computed.
     * @return the arc tangent of the argument.
     */
    public static native double atan(double d);

    /**
     * Returns the closest double approximation of the arc tangent of
     * {@code y/x} within the range {@code [-pi..pi]}. This is the angle of the
     * polar representation of the rectangular coordinates (x,y).
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code atan2((anything), NaN ) = NaN;}</li>
     * <li>{@code atan2(NaN , (anything) ) = NaN;}</li>
     * <li>{@code atan2(+0.0, +(anything but NaN)) = +0.0}</li>
     * <li>{@code atan2(-0.0, +(anything but NaN)) = -0.0}</li>
     * <li>{@code atan2(+0.0, -(anything but NaN)) = +pi}</li>
     * <li>{@code atan2(-0.0, -(anything but NaN)) = -pi}</li>
     * <li>{@code atan2(+(anything but 0 and NaN), 0) = +pi/2}</li>
     * <li>{@code atan2(-(anything but 0 and NaN), 0) = -pi/2}</li>
     * <li>{@code atan2(+(anything but infinity and NaN), +infinity)} {@code =}
     * {@code +0.0}</li>
     * <li>{@code atan2(-(anything but infinity and NaN), +infinity)} {@code =}
     * {@code -0.0}</li>
     * <li>{@code atan2(+(anything but infinity and NaN), -infinity) = +pi}</li>
     * <li>{@code atan2(-(anything but infinity and NaN), -infinity) = -pi}</li>
     * <li>{@code atan2(+infinity, +infinity ) = +pi/4}</li>
     * <li>{@code atan2(-infinity, +infinity ) = -pi/4}</li>
     * <li>{@code atan2(+infinity, -infinity ) = +3pi/4}</li>
     * <li>{@code atan2(-infinity, -infinity ) = -3pi/4}</li>
     * <li>{@code atan2(+infinity, (anything but,0, NaN, and infinity))}
     * {@code =} {@code +pi/2}</li>
     * <li>{@code atan2(-infinity, (anything but,0, NaN, and infinity))}
     * {@code =} {@code -pi/2}</li>
     * </ul>
     *
     * @param y
     *            the numerator of the value whose atan has to be computed.
     * @param x
     *            the denominator of the value whose atan has to be computed.
     * @return the arc tangent of {@code y/x}.
     */
    public static native double atan2(double y, double x);
    
    /**
     * Returns the closest double approximation of the cube root of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code cbrt(+0.0) = +0.0}</li>
     * <li>{@code cbrt(-0.0) = -0.0}</li>
     * <li>{@code cbrt(+infinity) = +infinity}</li>
     * <li>{@code cbrt(-infinity) = -infinity}</li>
     * <li>{@code cbrt(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose cube root has to be computed.
     * @return the cube root of the argument.
     */
    public static native double cbrt(double d);

    /**
     * Returns the double conversion of the most negative (closest to negative
     * infinity) integer value which is greater than the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code ceil(+0.0) = +0.0}</li>
     * <li>{@code ceil(-0.0) = -0.0}</li>
     * <li>{@code ceil((anything in range (-1,0)) = -0.0}</li>
     * <li>{@code ceil(+infinity) = +infinity}</li>
     * <li>{@code ceil(-infinity) = -infinity}</li>
     * <li>{@code ceil(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose closest integer value has to be computed.
     * @return the ceiling of the argument.
     */
    public static native double ceil(double d);
    
    
    /**
     * Returns the closest double approximation of the hyperbolic cosine of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code cosh(+infinity) = +infinity}</li>
     * <li>{@code cosh(-infinity) = +infinity}</li>
     * <li>{@code cosh(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose hyperbolic cosine has to be computed.
     * @return the hyperbolic cosine of the argument.
     */
    public static native double cosh(double d);

    /**
     * Returns the closest double approximation of the cosine of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code cos(+infinity) = NaN}</li>
     * <li>{@code cos(-infinity) = NaN}</li>
     * <li>{@code cos(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the angle whose cosine has to be computed, in radians.
     * @return the cosine of the argument.
     */
    public static native double cos(double d);

    /**
     * Returns the closest double approximation of the raising "e" to the power
     * of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code exp(+infinity) = +infinity}</li>
     * <li>{@code exp(-infinity) = +0.0}</li>
     * <li>{@code exp(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose exponential has to be computed.
     * @return the exponential of the argument.
     */
    public static native double exp(double d);
    
    /**
     * Returns the closest double approximation of <i>{@code e}</i><sup>
     * {@code d}</sup>{@code - 1}. If the argument is very close to 0, it is
     * much more accurate to use {@code expm1(d)+1} than {@code exp(d)} (due to
     * cancellation of significant digits).
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code expm1(+0.0) = +0.0}</li>
     * <li>{@code expm1(-0.0) = -0.0}</li>
     * <li>{@code expm1(+infinity) = +infinity}</li>
     * <li>{@code expm1(-infinity) = -1.0}</li>
     * <li>{@code expm1(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value to compute the <i>{@code e}</i><sup>{@code d}</sup>
     *            {@code - 1} of.
     * @return the <i>{@code e}</i><sup>{@code d}</sup>{@code - 1} value
     *         of the argument.
     */
    public static native double expm1(double d);

    /**
     * Returns the double conversion of the most positive (closest to
     * positive infinity) integer value which is less than the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code floor(+0.0) = +0.0}</li>
     * <li>{@code floor(-0.0) = -0.0}</li>
     * <li>{@code floor(+infinity) = +infinity}</li>
     * <li>{@code floor(-infinity) = -infinity}</li>
     * <li>{@code floor(NaN) = NaN}</li>
     * </ul>
     *
     * @param d the value whose closest integer value has to be computed.
     * @return the floor of the argument.
     */
    public static native double floor(double d);
    
    /**
     * Returns {@code sqrt(}<i>{@code x}</i><sup>{@code 2}</sup>{@code +}
     * <i> {@code y}</i><sup>{@code 2}</sup>{@code )}. The final result is
     * without medium underflow or overflow.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code hypot(+infinity, (anything including NaN)) = +infinity}</li>
     * <li>{@code hypot(-infinity, (anything including NaN)) = +infinity}</li>
     * <li>{@code hypot((anything including NaN), +infinity) = +infinity}</li>
     * <li>{@code hypot((anything including NaN), -infinity) = +infinity}</li>
     * <li>{@code hypot(NaN, NaN) = NaN}</li>
     * </ul>
     *
     * @param x
     *            a double number.
     * @param y
     *            a double number.
     * @return the {@code sqrt(}<i>{@code x}</i><sup>{@code 2}</sup>{@code +}
     *         <i> {@code y}</i><sup>{@code 2}</sup>{@code )} value of the
     *         arguments.
     */
    public static native double hypot(double x, double y);

    /**
     * Returns the remainder of dividing {@code x} by {@code y} using the IEEE
     * 754 rules. The result is {@code x-round(x/p)*p} where {@code round(x/p)}
     * is the nearest integer (rounded to even), but without numerical
     * cancellation problems.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code IEEEremainder((anything), 0) = NaN}</li>
     * <li>{@code IEEEremainder(+infinity, (anything)) = NaN}</li>
     * <li>{@code IEEEremainder(-infinity, (anything)) = NaN}</li>
     * <li>{@code IEEEremainder(NaN, (anything)) = NaN}</li>
     * <li>{@code IEEEremainder((anything), NaN) = NaN}</li>
     * <li>{@code IEEEremainder(x, +infinity) = x } where x is anything but
     * +/-infinity</li>
     * <li>{@code IEEEremainder(x, -infinity) = x } where x is anything but
     * +/-infinity</li>
     * </ul>
     *
     * @param x
     *            the numerator of the operation.
     * @param y
     *            the denominator of the operation.
     * @return the IEEE754 floating point reminder of {@code x/y}.
     */
    public static native double IEEEremainder(double x, double y);

    /**
     * Returns the closest double approximation of the natural logarithm of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code log(+0.0) = -infinity}</li>
     * <li>{@code log(-0.0) = -infinity}</li>
     * <li>{@code log((anything < 0) = NaN}</li>
     * <li>{@code log(+infinity) = +infinity}</li>
     * <li>{@code log(-infinity) = NaN}</li>
     * <li>{@code log(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose log has to be computed.
     * @return the natural logarithm of the argument.
     */
    public static native double log(double d);
    
    /**
     * Returns the closest double approximation of the base 10 logarithm of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code log10(+0.0) = -infinity}</li>
     * <li>{@code log10(-0.0) = -infinity}</li>
     * <li>{@code log10((anything < 0) = NaN}</li>
     * <li>{@code log10(+infinity) = +infinity}</li>
     * <li>{@code log10(-infinity) = NaN}</li>
     * <li>{@code log10(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose base 10 log has to be computed.
     * @return the natural logarithm of the argument.
     */
    public static native double log10(double d);
    
    /**
     * Returns the closest double approximation of the natural logarithm of the
     * sum of the argument and 1. If the argument is very close to 0, it is much
     * more accurate to use {@code log1p(d)} than {@code log(1.0+d)} (due to
     * numerical cancellation).
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code log1p(+0.0) = +0.0}</li>
     * <li>{@code log1p(-0.0) = -0.0}</li>
     * <li>{@code log1p((anything < 1)) = NaN}</li>
     * <li>{@code log1p(-1.0) = -infinity}</li>
     * <li>{@code log1p(+infinity) = +infinity}</li>
     * <li>{@code log1p(-infinity) = NaN}</li>
     * <li>{@code log1p(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value to compute the {@code ln(1+d)} of.
     * @return the natural logarithm of the sum of the argument and 1.
     */
    public static native double log1p(double d);

    /**
     * Returns the most positive (closest to positive infinity) of the two
     * arguments.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code max(NaN, (anything)) = NaN}</li>
     * <li>{@code max((anything), NaN) = NaN}</li>
     * <li>{@code max(+0.0, -0.0) = +0.0}</li>
     * <li>{@code max(-0.0, +0.0) = +0.0}</li>
     * </ul>
     *
     * @param d1
     *            the first argument.
     * @param d2
     *            the second argument.
     * @return the larger of {@code d1} and {@code d2}.
     */
    public static double max(double d1, double d2) {
        if (d1 > d2)
            return d1;
        if (d1 < d2)
            return d2;
        /* if either arg is NaN, return NaN */
        if (d1 != d2)
            return Double.NaN;
        /* max( +0.0,-0.0) == +0.0 */
        if (d1 == 0.0
                && ((Double.doubleToLongBits(d1) & Double.doubleToLongBits(d2)) & 0x8000000000000000L) == 0)
            return 0.0;
        return d1;
    }

    /**
     * Returns the most positive (closest to positive infinity) of the two
     * arguments.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code max(NaN, (anything)) = NaN}</li>
     * <li>{@code max((anything), NaN) = NaN}</li>
     * <li>{@code max(+0.0, -0.0) = +0.0}</li>
     * <li>{@code max(-0.0, +0.0) = +0.0}</li>
     * </ul>
     *
     * @param f1
     *            the first argument.
     * @param f2
     *            the second argument.
     * @return the larger of {@code f1} and {@code f2}.
     */
    public static float max(float f1, float f2) {
        if (f1 > f2)
            return f1;
        if (f1 < f2)
            return f2;
        /* if either arg is NaN, return NaN */
        if (f1 != f2)
            return Float.NaN;
        /* max( +0.0,-0.0) == +0.0 */
        if (f1 == 0.0f
                && ((Float.floatToIntBits(f1) & Float.floatToIntBits(f2)) & 0x80000000) == 0)
            return 0.0f;
        return f1;
    }

    /**
     * Returns the most positive (closest to positive infinity) of the two
     * arguments.
     *
     * @param i1
     *            the first argument.
     * @param i2
     *            the second argument.
     * @return the larger of {@code i1} and {@code i2}.
     */
    public static int max(int i1, int i2) {
        return i1 > i2 ? i1 : i2;
    }

    /**
     * Returns the most positive (closest to positive infinity) of the two
     * arguments.
     *
     * @param l1
     *            the first argument.
     * @param l2
     *            the second argument.
     * @return the larger of {@code l1} and {@code l2}.
     */
    public static long max(long l1, long l2) {
        return l1 > l2 ? l1 : l2;
    }

    /**
     * Returns the most negative (closest to negative infinity) of the two
     * arguments.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code min(NaN, (anything)) = NaN}</li>
     * <li>{@code min((anything), NaN) = NaN}</li>
     * <li>{@code min(+0.0, -0.0) = -0.0}</li>
     * <li>{@code min(-0.0, +0.0) = -0.0}</li>
     * </ul>
     *
     * @param d1
     *            the first argument.
     * @param d2
     *            the second argument.
     * @return the smaller of {@code d1} and {@code d2}.
     */
    public static double min(double d1, double d2) {
        if (d1 > d2)
            return d2;
        if (d1 < d2)
            return d1;
        /* if either arg is NaN, return NaN */
        if (d1 != d2)
            return Double.NaN;
        /* min( +0.0,-0.0) == -0.0 */
        if (d1 == 0.0
                && ((Double.doubleToLongBits(d1) | Double.doubleToLongBits(d2)) & 0x8000000000000000l) != 0)
            return 0.0 * (-1.0);
        return d1;
    }

    /**
     * Returns the most negative (closest to negative infinity) of the two
     * arguments.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code min(NaN, (anything)) = NaN}</li>
     * <li>{@code min((anything), NaN) = NaN}</li>
     * <li>{@code min(+0.0, -0.0) = -0.0}</li>
     * <li>{@code min(-0.0, +0.0) = -0.0}</li>
     * </ul>
     *
     * @param f1
     *            the first argument.
     * @param f2
     *            the second argument.
     * @return the smaller of {@code f1} and {@code f2}.
     */
    public static float min(float f1, float f2) {
        if (f1 > f2)
            return f2;
        if (f1 < f2)
            return f1;
        /* if either arg is NaN, return NaN */
        if (f1 != f2)
            return Float.NaN;
        /* min( +0.0,-0.0) == -0.0 */
        if (f1 == 0.0f
                && ((Float.floatToIntBits(f1) | Float.floatToIntBits(f2)) & 0x80000000) != 0)
            return 0.0f * (-1.0f);
        return f1;
    }

    /**
     * Returns the most negative (closest to negative infinity) of the two
     * arguments.
     *
     * @param i1
     *            the first argument.
     * @param i2
     *            the second argument.
     * @return the smaller of {@code i1} and {@code i2}.
     */
    public static int min(int i1, int i2) {
        return i1 < i2 ? i1 : i2;
    }

    /**
     * Returns the most negative (closest to negative infinity) of the two
     * arguments.
     *
     * @param l1
     *            the first argument.
     * @param l2
     *            the second argument.
     * @return the smaller of {@code l1} and {@code l2}.
     */
    public static long min(long l1, long l2) {
        return l1 < l2 ? l1 : l2;
    }

    /**
     * Returns the closest double approximation of the result of raising
     * {@code x} to the power of {@code y}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code pow((anything), +0.0) = 1.0}</li>
     * <li>{@code pow((anything), -0.0) = 1.0}</li>
     * <li>{@code pow(x, 1.0) = x}</li>
     * <li>{@code pow((anything), NaN) = NaN}</li>
     * <li>{@code pow(NaN, (anything except 0)) = NaN}</li>
     * <li>{@code pow(+/-(|x| > 1), +infinity) = +infinity}</li>
     * <li>{@code pow(+/-(|x| > 1), -infinity) = +0.0}</li>
     * <li>{@code pow(+/-(|x| < 1), +infinity) = +0.0}</li>
     * <li>{@code pow(+/-(|x| < 1), -infinity) = +infinity}</li>
     * <li>{@code pow(+/-1.0 , +infinity) = NaN}</li>
     * <li>{@code pow(+/-1.0 , -infinity) = NaN}</li>
     * <li>{@code pow(+0.0, (+anything except 0, NaN)) = +0.0}</li>
     * <li>{@code pow(-0.0, (+anything except 0, NaN, odd integer)) = +0.0}</li>
     * <li>{@code pow(+0.0, (-anything except 0, NaN)) = +infinity}</li>
     * <li>{@code pow(-0.0, (-anything except 0, NAN, odd integer))} {@code =}
     * {@code +infinity}</li>
     * <li>{@code pow(-0.0, (odd integer)) = -pow( +0 , (odd integer) )}</li>
     * <li>{@code pow(+infinity, (+anything except 0, NaN)) = +infinity}</li>
     * <li>{@code pow(+infinity, (-anything except 0, NaN)) = +0.0}</li>
     * <li>{@code pow(-infinity, (anything)) = -pow(0, (-anything))}</li>
     * <li>{@code pow((-anything), (integer))} {@code =}
     * {@code pow(-1,(integer))*pow(+anything,integer)}</li>
     * <li>{@code pow((-anything except 0 and infinity), (non-integer))}
     * {@code =} {@code NAN}</li>
     * </ul>
     *
     * @param x
     *            the base of the operation.
     * @param y
     *            the exponent of the operation.
     * @return {@code x} to the power of {@code y}.
     */
    public static native double pow(double x, double y);

    /**
     * Returns a pseudo-random number between 0.0 (inclusive) and 1.0
     * (exclusive).
     *
     * @return a pseudo-random number.
     */
    public static double random() {
        if (random == null)
            random = new Random();
        return random.nextDouble();
    }

    /**
     * Returns the double conversion of the result of rounding the argument to
     * an integer. Tie breaks are rounded towards even.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code rint(+0.0) = +0.0}</li>
     * <li>{@code rint(-0.0) = -0.0}</li>
     * <li>{@code rint(+infinity) = +infinity}</li>
     * <li>{@code rint(-infinity) = -infinity}</li>
     * <li>{@code rint(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value to be rounded.
     * @return the closest integer to the argument (as a double).
     */
    public static native double rint(double d);

    /**
     * Returns the result of rounding the argument to an integer. The result is
     * equivalent to {@code (long) Math.floor(d+0.5)}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code round(+0.0) = +0.0}</li>
     * <li>{@code round(-0.0) = +0.0}</li>
     * <li>{@code round((anything > Long.MAX_VALUE) = Long.MAX_VALUE}</li>
     * <li>{@code round((anything < Long.MIN_VALUE) = Long.MIN_VALUE}</li>
     * <li>{@code round(+infinity) = Long.MAX_VALUE}</li>
     * <li>{@code round(-infinity) = Long.MIN_VALUE}</li>
     * <li>{@code round(NaN) = +0.0}</li>
     * </ul>
     *
     * @param d
     *            the value to be rounded.
     * @return the closest integer to the argument.
     */
    public static long round(double d) {
        // check for NaN
        if (d != d)
            return 0L;
        return (long) Math.floor(d + 0.5d);
    }

    /**
     * Returns the result of rounding the argument to an integer. The result is
     * equivalent to {@code (int) Math.floor(f+0.5)}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code round(+0.0) = +0.0}</li>
     * <li>{@code round(-0.0) = +0.0}</li>
     * <li>{@code round((anything > Integer.MAX_VALUE) = Integer.MAX_VALUE}</li>
     * <li>{@code round((anything < Integer.MIN_VALUE) = Integer.MIN_VALUE}</li>
     * <li>{@code round(+infinity) = Integer.MAX_VALUE}</li>
     * <li>{@code round(-infinity) = Integer.MIN_VALUE}</li>
     * <li>{@code round(NaN) = +0.0}</li>
     * </ul>
     *
     * @param f
     *            the value to be rounded.
     * @return the closest integer to the argument.
     */
    public static int round(float f) {
        // check for NaN
        if (f != f)
            return 0;
        return (int) Math.floor(f + 0.5f);
    }
    
    /**
     * Returns the signum function of the argument. If the argument is less than
     * zero, it returns -1.0. If the argument is greater than zero, 1.0 is
     * returned. If the argument is either positive or negative zero, the
     * argument is returned as result.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code signum(+0.0) = +0.0}</li>
     * <li>{@code signum(-0.0) = -0.0}</li>
     * <li>{@code signum(+infinity) = +1.0}</li>
     * <li>{@code signum(-infinity) = -1.0}</li>
     * <li>{@code signum(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose signum has to be computed.
     * @return the value of the signum function.
     */
    public static double signum(double d){
        if(Double.isNaN(d)){
            return Double.NaN;
        }
        double sig = d;
        if(d > 0){
            sig = 1.0;
        }else if (d < 0){
            sig = -1.0;
        }
        return sig;
    }
    
    /**
     * Returns the signum function of the argument. If the argument is less than
     * zero, it returns -1.0. If the argument is greater than zero, 1.0 is
     * returned. If the argument is either positive or negative zero, the
     * argument is returned as result.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code signum(+0.0) = +0.0}</li>
     * <li>{@code signum(-0.0) = -0.0}</li>
     * <li>{@code signum(+infinity) = +1.0}</li>
     * <li>{@code signum(-infinity) = -1.0}</li>
     * <li>{@code signum(NaN) = NaN}</li>
     * </ul>
     *
     * @param f
     *            the value whose signum has to be computed.
     * @return the value of the signum function.
     */
    public static float signum(float f){
        if(Float.isNaN(f)){
            return Float.NaN;
        }
        float sig = f;
        if(f > 0){
            sig = 1.0f;
        }else if (f < 0){
            sig = -1.0f;
        }
        return sig;
    }

    /**
     * Returns the closest double approximation of the hyperbolic sine of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code sinh(+0.0) = +0.0}</li>
     * <li>{@code sinh(-0.0) = -0.0}</li>
     * <li>{@code sinh(+infinity) = +infinity}</li>
     * <li>{@code sinh(-infinity) = -infinity}</li>
     * <li>{@code sinh(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose hyperbolic sine has to be computed.
     * @return the hyperbolic sine of the argument.
     */
    public static native double sinh(double d);
    
    /**
     * Returns the closest double approximation of the sine of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code sin(+0.0) = +0.0}</li>
     * <li>{@code sin(-0.0) = -0.0}</li>
     * <li>{@code sin(+infinity) = NaN}</li>
     * <li>{@code sin(-infinity) = NaN}</li>
     * <li>{@code sin(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the angle whose sin has to be computed, in radians.
     * @return the sine of the argument.
     */
    public static native double sin(double d);

    /**
     * Returns the closest double approximation of the square root of the
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code sqrt(+0.0) = +0.0}</li>
     * <li>{@code sqrt(-0.0) = -0.0}</li>
     * <li>{@code sqrt( (anything < 0) ) = NaN}</li>
     * <li>{@code sqrt(+infinity) = +infinity}</li>
     * <li>{@code sqrt(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose square root has to be computed.
     * @return the square root of the argument.
     */
    public static native double sqrt(double d);

    /**
     * Returns the closest double approximation of the tangent of the argument.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code tan(+0.0) = +0.0}</li>
     * <li>{@code tan(-0.0) = -0.0}</li>
     * <li>{@code tan(+infinity) = NaN}</li>
     * <li>{@code tan(-infinity) = NaN}</li>
     * <li>{@code tan(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the angle whose tangens has to be computed, in radians.
     * @return the tangent of the argument.
     */
    public static native double tan(double d);

    /**
     * Returns the closest double approximation of the hyperbolic tangent of the
     * argument. The absolute value is always less than 1.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code tanh(+0.0) = +0.0}</li>
     * <li>{@code tanh(-0.0) = -0.0}</li>
     * <li>{@code tanh(+infinity) = +1.0}</li>
     * <li>{@code tanh(-infinity) = -1.0}</li>
     * <li>{@code tanh(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the value whose hyperbolic tangent has to be computed.
     * @return the hyperbolic tangent of the argument
     */
    public static native double tanh(double d);
    
    /**
     * Returns the measure in degrees of the supplied radian angle. The result
     * is {@code angrad * 180 / pi}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code toDegrees(+0.0) = +0.0}</li>
     * <li>{@code toDegrees(-0.0) = -0.0}</li>
     * <li>{@code toDegrees(+infinity) = +infinity}</li>
     * <li>{@code toDegrees(-infinity) = -infinity}</li>
     * <li>{@code toDegrees(NaN) = NaN}</li>
     * </ul>
     *
     * @param angrad
     *            an angle in radians.
     * @return the degree measure of the angle.
     */
    public static double toDegrees(double angrad) {
        return angrad * 180d / PI;
    }

    /**
     * Returns the measure in radians of the supplied degree angle. The result
     * is {@code angdeg / 180 * pi}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code toRadians(+0.0) = +0.0}</li>
     * <li>{@code toRadians(-0.0) = -0.0}</li>
     * <li>{@code toRadians(+infinity) = +infinity}</li>
     * <li>{@code toRadians(-infinity) = -infinity}</li>
     * <li>{@code toRadians(NaN) = NaN}</li>
     * </ul>
     *
     * @param angdeg
     *            an angle in degrees.
     * @return the radian measure of the angle.
     */
    public static double toRadians(double angdeg) {
        return angdeg / 180d * PI;
    }
    
    /**
     * Returns the argument's ulp (unit in the last place). The size of a ulp of
     * a double value is the positive distance between this value and the double
     * value next larger in magnitude. For non-NaN {@code x},
     * {@code ulp(-x) == ulp(x)}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code ulp(+0.0) = Double.MIN_VALUE}</li>
     * <li>{@code ulp(-0.0) = Double.MIN_VALUE}</li>
     * <li>{@code ulp(+infintiy) = infinity}</li>
     * <li>{@code ulp(-infintiy) = infinity}</li>
     * <li>{@code ulp(NaN) = NaN}</li>
     * </ul>
     *
     * @param d
     *            the floating-point value to compute ulp of.
     * @return the size of a ulp of the argument.
     */
    public static double ulp(double d) {
        // special cases
        if (Double.isInfinite(d)) {
            return Double.POSITIVE_INFINITY;
        } else if (d == Double.MAX_VALUE || d == -Double.MAX_VALUE) {
            return pow(2, 971);
        }
        d = Math.abs(d);
        return nextafter(d, Double.MAX_VALUE) - d;
    }

    /**
     * Returns the argument's ulp (unit in the last place). The size of a ulp of
     * a float value is the positive distance between this value and the float
     * value next larger in magnitude. For non-NaN {@code x},
     * {@code ulp(-x) == ulp(x)}.
     * <p>
     * Special cases:
     * <ul>
     * <li>{@code ulp(+0.0) = Float.MIN_VALUE}</li>
     * <li>{@code ulp(-0.0) = Float.MIN_VALUE}</li>
     * <li>{@code ulp(+infintiy) = infinity}</li>
     * <li>{@code ulp(-infintiy) = infinity}</li>
     * <li>{@code ulp(NaN) = NaN}</li>
     * </ul>
     *
     * @param f
     *            the floating-point value to compute ulp of.
     * @return the size of a ulp of the argument.
     */
    public static float ulp(float f) {
        // special cases
        if (Float.isNaN(f)) {
            return Float.NaN;
        } else if (Float.isInfinite(f)) {
            return Float.POSITIVE_INFINITY;
        } else if (f == Float.MAX_VALUE || f == -Float.MAX_VALUE) {
            return (float) pow(2, 104);
        }
        f = Math.abs(f);
        return nextafterf(f, Float.MAX_VALUE) - f;
    }

    private native static double nextafter(double x, double y);

    private native static float nextafterf(float x, float y); 
    
    /**
     * Answers a result of the magnitude of the first given double value and the
     * sign of the seconde given double value.
     * 
     * @param magnitude
     *            the double value whose magnitude should be used
     * @param sign
     *            the double value whose sign should be used
     * @return a result of the magnitude of the first given double value and the
     *         sign of the seconde given double value.
     *         
     * @since 1.6
     */
    public static double copySign(double magnitude, double sign) {
        long mbits = Double.doubleToRawLongBits(magnitude);
        long sbits = Double.doubleToLongBits(sign);
        return Double.longBitsToDouble((mbits & ~DOUBLE_SIGN_MASK) | (sbits & DOUBLE_SIGN_MASK) );
    }

    /**
     * Answers a result of the magnitude of the first given float value and the
     * sign of the seconde given float value.
     * 
     * @param magnitude
     *            the float value whose magnitude should be used
     * @param sign
     *            the float value whose sign should be used
     * @return a result with the magnitude of the first given float value and
     *         the sign of the seconde given float value.
     *         
     * @since 1.6
     */
    public static float copySign(float magnitude, float sign) {
        int mbits = Float.floatToRawIntBits(magnitude);
        int sbits = Float.floatToIntBits(sign);
        return Float.intBitsToFloat((mbits & ~FLOAT_SIGN_MASK)| (sbits & FLOAT_SIGN_MASK) );
    }
    
    /**
     * Answers the exponent of a float.
     * 
     * @param f
     *            the given float
     * @return the exponent of the float.
     * 
     * @since 1.6
     */
    public static int getExponent(float f){
    	int bits = Float.floatToRawIntBits(f);
        bits = (bits & FLOAT_EXPONENT_MASK) >> FLOAT_MANTISSA_BITS;
        return bits - FLOAT_EXPONENT_BIAS;
    }
    
    /**
     * Answers the exponent of a double.
     * 
     * @param d
     *            the given double
     * @return the exponent of the double.
     * 
     * @since 1.6
     */
    public static int getExponent(double d){
    	long bits = Double.doubleToRawLongBits(d);
        bits = (bits & DOUBLE_EXPONENT_MASK) >> DOUBLE_MANTISSA_BITS;
        return (int) bits - DOUBLE_EXPONENT_BIAS;
    }
    
    /**
     * Answers a double next to the first given double value in the direction of
     * the second given double.
     * 
     * @param start
     *            the double value to start
     * @param direction
     *            the double indicating the direction
     * @return a double next to the first given double value in the direction of
     *         the second given double.
     *         
     * @since 1.6
     */
    public static double nextAfter(double start, double direction) {
        if (0 == start && 0 == direction) {
            return direction;
        }
        return nextafter(start, direction);
    }

    /**
     * Answers a float next to the first given float value in the direction of
     * the second given double value.
     * 
     * @param start
     *            the float value to start
     * @param direction
     *            the double indicating the direction
     * @return a float next to the first given float value in the direction of
     *         the second given double.
     *         
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public static float nextAfter(float start, double direction) {
        if (Float.isNaN(start) || Double.isNaN(direction)) {
            return Float.NaN;
        }
        if (0 == start && 0 == direction) {
            return new Float(direction);
        }
        if ((start == Float.MIN_VALUE && direction < start)
                || (start == -Float.MIN_VALUE && direction > start)) {
            return (start > 0 ? 0f : -0f);
        }
        if (Float.isInfinite(start) && (direction != start)) {
            return (start > 0 ? Float.MAX_VALUE : -Float.MAX_VALUE);
        }
        if ((start == Float.MAX_VALUE && direction > start)
                || (start == -Float.MAX_VALUE && direction < start)) {
            return (start > 0 ? Float.POSITIVE_INFINITY
                    : Float.NEGATIVE_INFINITY);
        }
        if (direction > start) {
            if (start > 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) + 1);
            }
            if (start < 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) - 1);
            }
            return +Float.MIN_VALUE;
        }
        if (direction < start) {
            if (start > 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) - 1);
            }
            if (start < 0) {
                return Float.intBitsToFloat(Float.floatToIntBits(start) + 1);
            }
            return -Float.MIN_VALUE;
        }
        return Double.valueOf(direction).floatValue();
    }

    /**
     * Answers the next larger double value to d.
     * 
     * @param d
     *            the double value to start
     * @return the next larger double value of d.
     * 
     * @since 1.6
     */
    public static double nextUp(double d) {
        if (Double.isNaN(d)) {
            return Double.NaN;
        }
        if ((d == Double.POSITIVE_INFINITY)) {
            return Double.POSITIVE_INFINITY;
        }
        if (0 == d) {
            return Double.MIN_VALUE;
        } else if (0 < d) {
            return Double.longBitsToDouble(Double.doubleToLongBits(d) + 1);
        } else {
            return Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
        }
    }
    
    /**
     * Answers the next larger float value to d.
     * 
     * @param f
     *            the float value to start
     * @return the next larger float value of d.
     * 
     * @since 1.6
     */
    public static float nextUp(float f) {
        if (Float.isNaN(f)) {
            return Float.NaN;
        }
        if ((f == Float.POSITIVE_INFINITY)) {
            return Float.POSITIVE_INFINITY;
        }
        if (0 == f) {
            return Float.MIN_VALUE;
        } else if (0 < f) {
            return Float.intBitsToFloat(Float.floatToIntBits(f) + 1);
        } else {
            return Float.intBitsToFloat(Float.floatToIntBits(f) - 1);
        }
    }
    
    /**
     * Answers a double value of d 2^scaleFactor, the result may be rounded.
     * 
     * @param d
     *            the base number
     * @param scaleFactor
     *            the power number
     * @return d 2^scaleFactor
     * 
     * @since 1.6
     */
    @SuppressWarnings("boxing")
    public static double scalb(double d, int scaleFactor) {
        if (Double.isNaN(d) || Double.isInfinite(d) || 0 == d) {
            return d;
        }
        // change double to long for calculation
        long bits = Double.doubleToLongBits(d);
        // the sign of the results must be the same of given d
        Long sign = bits & DOUBLE_SIGN_MASK;
        // calculates the factor of the result
        long factor = (int) ((Double.doubleToLongBits(d) & DOUBLE_EXPONENT_MASK) >> DOUBLE_MANTISSA_BITS)
                - DOUBLE_EXPONENT_BIAS + scaleFactor;

        // calcutes the factor of sub-normal values
        int subNormalFactor = Long.numberOfLeadingZeros(bits
                & ~DOUBLE_SIGN_MASK)
                - DOUBLE_EXPONENT_BITS;
        if (subNormalFactor < 0) {
            // not sub-normal values
            subNormalFactor = 0;
        }
        if (Math.abs(d) < Double.MIN_NORMAL) {
            factor = factor - subNormalFactor;
        }
        if (factor > Double.MAX_EXPONENT) {
            return (d > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
        }

        long result;
        // if result is a sub-normal
        if (factor < -DOUBLE_EXPONENT_BIAS) {
            // the number of digits that shifts
            long digits = factor + DOUBLE_EXPONENT_BIAS + subNormalFactor;
            if (Math.abs(d) < Double.MIN_NORMAL) {
                // origin d is already sub-normal
                result = shiftLongBits(bits & DOUBLE_MANTISSA_MASK, digits);
            } else {
                // origin d is not sub-normal, change mantissa to sub-normal
                result = shiftLongBits(bits & DOUBLE_MANTISSA_MASK
                        | 0x0010000000000000L, digits - 1);
            }
        } else {
            if (Math.abs(d) >= Double.MIN_NORMAL) {
                // common situation
                result = ((factor + DOUBLE_EXPONENT_BIAS) << DOUBLE_MANTISSA_BITS)
                        | (bits & DOUBLE_MANTISSA_MASK);
            } else {
                // origin d is sub-normal, change mantissa to normal style
                result = ((factor + DOUBLE_EXPONENT_BIAS) << DOUBLE_MANTISSA_BITS)
                        | ((bits << (subNormalFactor + 1)) & DOUBLE_MANTISSA_MASK);
            }
        }
        return Double.longBitsToDouble(result | sign);
    }

    /**
     * Answers a float value of d 2^scaleFactor, the result may be rounded.
     * 
     * @param d
     *            the base number
     * @param scaleFactor
     *            the power number
     * @return d 2^scaleFactor
     * 
     * @since 1.6
     */
    public static float scalb(float d, int scaleFactor) {
        if (Float.isNaN(d) || Float.isInfinite(d) || 0 == d) {
            return d;
        }
        int bits = Float.floatToIntBits(d);
        int sign = bits & FLOAT_SIGN_MASK;
        int factor = ((Float.floatToIntBits(d) & FLOAT_EXPONENT_MASK) >> FLOAT_MANTISSA_BITS)
                - FLOAT_EXPONENT_BIAS + scaleFactor;
        // calcutes the factor of sub-normal values
        int subNormalFactor = Integer.numberOfLeadingZeros(bits
                & ~FLOAT_SIGN_MASK)
                - FLOAT_EXPONENT_BITS;
        if (subNormalFactor < 0) {
            // not sub-normal values
            subNormalFactor = 0;
        }
        if (Math.abs(d) < Float.MIN_NORMAL) {
            factor = factor - subNormalFactor;
        }
        if (factor > Float.MAX_EXPONENT) {
            return (d > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);
        }

        int result;
        // if result is a sub-normal
        if (factor < -FLOAT_EXPONENT_BIAS) {
            // the number of digits that shifts
            int digits = factor + FLOAT_EXPONENT_BIAS + subNormalFactor;
            if (Math.abs(d) < Float.MIN_NORMAL) {
                // origin d is already sub-normal
                result = shiftIntBits(bits & FLOAT_MANTISSA_MASK, digits);
            } else {
                // origin d is not sub-normal, change mantissa to sub-normal
                result = shiftIntBits(bits & FLOAT_MANTISSA_MASK | 0x00800000,
                        digits - 1);
            }
        } else {
            if (Math.abs(d) >= Float.MIN_NORMAL) {
                // common situation
                result = ((factor + FLOAT_EXPONENT_BIAS) << FLOAT_MANTISSA_BITS)
                        | (bits & FLOAT_MANTISSA_MASK);
            } else {
                // origin d is sub-normal, change mantissa to normal style
                result = ((factor + FLOAT_EXPONENT_BIAS) << FLOAT_MANTISSA_BITS)
                        | ((bits << (subNormalFactor + 1)) & FLOAT_MANTISSA_MASK);
            }
        }
        return Float.intBitsToFloat(result | sign);
    }

    // Shifts integer bits as float, if the digits is positive, left-shift; if
    // not, shift to right and calculate its carry.
    private static int shiftIntBits(int bits, int digits) {
        if (digits > 0) {
            return bits << digits;
        }
        // change it to positive
        int absdigits = -digits;
        if (Integer.numberOfLeadingZeros(bits & ~FLOAT_SIGN_MASK) <= (32 - absdigits)) {
            // some bits will remain after shifting, calculates its carry
            if ((((bits >> (absdigits - 1)) & 0x1) == 0)
                    || Integer.numberOfTrailingZeros(bits) == (absdigits - 1)) {
                return bits >> absdigits;
            }
            return ((bits >> absdigits) + 1);
        }
        return 0;
    }

    // Shifts long bits as double, if the digits is positive, left-shift; if
    // not, shift to right and calculate its carry.
    private static long shiftLongBits(long bits, long digits) {
        if (digits > 0) {
            return bits << digits;
        }
        // change it to positive
        long absdigits = -digits;
        if (Long.numberOfLeadingZeros(bits & ~DOUBLE_SIGN_MASK) <= (64 - absdigits)) {
            // some bits will remain after shifting, calculates its carry
            if ((((bits >> (absdigits - 1)) & 0x1) == 0)
                    || Long.numberOfTrailingZeros(bits) == (absdigits - 1)) {
                return bits >> absdigits;
            }
            return ((bits >> absdigits) + 1);
        }
        return 0;
    }
}
