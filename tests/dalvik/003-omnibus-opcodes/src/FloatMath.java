// Copyright 2006 The Android Open Source Project

/**
 * Test arithmetic operations.
 */
public class FloatMath {

    static void convTest() {
        System.out.println("FloatMath.convTest");

        float f;
        double d;
        int i;
        long l;

        /* float --> int */
        f = 1234.5678f;
        i = (int) f;
        assert(i == 1234);

        f = -1234.5678f;
        i = (int) f;
        assert(i == -1234);

        /* float --> long */
        f = 1238.5678f;
        l = (long) f;
        assert(l == 1238);

        f = -1238.5678f;
        l = (long) f;
        assert(l == -1238);

        /* float --> double */
        f = 1238.5678f;
        d = (double) f;
        assert(d > 1238.567 && d < 1238.568);

        /* double --> int */
        d = 1234.5678;
        i = (int) d;
        assert(i == 1234);

        d = -1234.5678;
        i = (int) d;
        assert(i == -1234);

        /* double --> long */
        d = 5678956789.0123;
        l = (long) d;
        assert(l == 5678956789L);

        d = -5678956789.0123;
        l = (long) d;
        assert(l == -5678956789L);

        /* double --> float */
        d = 1238.5678;
        f = (float) d;
        assert(f > 1238.567 && f < 1238.568);

        /* int --> long */
        i = 7654;
        l = (long) i;
        assert(l == 7654L);

        i = -7654;
        l = (long) i;
        assert(l == -7654L);

        /* int --> float */
        i = 1234;
        f = (float) i;
        assert(f > 1233.9f && f < 1234.1f);

        i = -1234;
        f = (float) i;
        assert(f < -1233.9f && f > -1234.1f);

        /* int --> double */
        i = 1238;
        d = (double) i;
        assert(d > 1237.9f && d < 1238.1f);

        i = -1238;
        d = (double) i;
        assert(d < -1237.9f && d > -1238.1f);

        /* long --> int (with truncation) */
        l = 5678956789L;
        i = (int) l;
        assert(i == 1383989493);

        l = -5678956789L;
        i = (int) l;
        assert(i == -1383989493);

        /* long --> float */
        l = 5678956789L;
        f = (float) l;
        assert(f > 5.6789564E9 && f < 5.6789566E9);

        l = -5678956789L;
        f = (float) l;
        assert(f < -5.6789564E9 && f > -5.6789566E9);

        /* long --> double */
        l = 6678956789L;
        d = (double) l;
        assert(d > 6.6789567E9 && d < 6.6789568E9);

        l = -6678956789L;
        d = (double) l;
        assert(d < -6.6789567E9 && d > -6.6789568E9);
    }

    /*
     * We pass in the arguments and return the results so the compiler
     * doesn't do the math for us.
     */
    static float[] floatOperTest(float x, float y) {
        System.out.println("FloatMath.floatOperTest");

        float[] results = new float[9];

        /* this seems to generate "op-float" instructions */
        results[0] = x + y;
        results[1] = x - y;
        results[2] = x * y;
        results[3] = x / y;
        results[4] = x % -y;

        /* this seems to generate "op-float/2addr" instructions */
        results[8] = x + (((((x + y) - y) * y) / y) % y);

        return results;
    }
    static void floatOperCheck(float[] results) {
        assert(results[0] > 69996.99f && results[0] < 69997.01f);
        assert(results[1] > 70002.99f && results[1] < 70003.01f);
        assert(results[2] > -210000.01f && results[2] < -209999.99f);
        assert(results[3] > -23333.34f && results[3] < -23333.32f);
        assert(results[4] > 0.999f && results[4] < 1.001f);
        assert(results[8] > 70000.99f && results[8] < 70001.01f);
    }

    /*
     * We pass in the arguments and return the results so the compiler
     * doesn't do the math for us.
     */
    static double[] doubleOperTest(double x, double y) {
        System.out.println("FloatMath.doubleOperTest");

        double[] results = new double[9];

        /* this seems to generate "op-double" instructions */
        results[0] = x + y;
        results[1] = x - y;
        results[2] = x * y;
        results[3] = x / y;
        results[4] = x % -y;

        /* this seems to generate "op-double/2addr" instructions */
        results[8] = x + (((((x + y) - y) * y) / y) % y);

        return results;
    }
    static void doubleOperCheck(double[] results) {
        assert(results[0] > 69996.99 && results[0] < 69997.01);
        assert(results[1] > 70002.99 && results[1] < 70003.01);
        assert(results[2] > -210000.01 && results[2] < -209999.99);
        assert(results[3] > -23333.34 && results[3] < -23333.32);
        assert(results[4] > 0.999 && results[4] < 1.001);
        assert(results[8] > 70000.99 && results[8] < 70001.01);
    }

    /*
     * Try to cause some unary operations.
     */
    static float unopTest(float f) {
        f = -f;
        return f;
    }

    static int[] convI(long l, float f, double d, float zero) {
        int[] results = new int[6];
        results[0] = (int) l;
        results[1] = (int) f;
        results[2] = (int) d;
        results[3] = (int) (1.0f / zero);       // +inf
        results[4] = (int) (-1.0f / zero);      // -inf
        results[5] = (int) ((1.0f / zero) / (1.0f / zero)); // NaN
        return results;
    }
    static void checkConvI(int[] results) {
        System.out.println("FloatMath.checkConvI");
        assert(results[0] == 0x44332211);
        assert(results[1] == 123);
        assert(results[2] == -3);
        assert(results[3] == 0x7fffffff);
        assert(results[4] == 0x80000000);
        assert(results[5] == 0);
    }

    static long[] convL(int i, float f, double d, double zero) {
        long[] results = new long[6];
        results[0] = (long) i;
        results[1] = (long) f;
        results[2] = (long) d;
        results[3] = (long) (1.0 / zero);       // +inf
        results[4] = (long) (-1.0 / zero);      // -inf
        results[5] = (long) ((1.0 / zero) / (1.0 / zero));  // NaN
        return results;
    }
    static void checkConvL(long[] results) {
        System.out.println("FloatMath.checkConvL");
        assert(results[0] == 0xFFFFFFFF88776655L);
        assert(results[1] == 123);
        assert(results[2] == -3);
        assert(results[3] == 0x7fffffffffffffffL);
        assert(results[4] == 0x8000000000000000L);
        assert(results[5] == 0);
    }

    static float[] convF(int i, long l, double d) {
        float[] results = new float[3];
        results[0] = (float) i;
        results[1] = (float) l;
        results[2] = (float) d;
        return results;
    }
    static void checkConvF(float[] results) {
        System.out.println("FloatMath.checkConvF");
        // TODO: assert values
        for (int i = 0; i < results.length; i++)
            System.out.println(" " + i + ": " + results[i]);
        System.out.println("-2.0054409E9, -8.6133031E18, -3.1415927");
    }

    static double[] convD(int i, long l, float f) {
        double[] results = new double[3];
        results[0] = (double) i;
        results[1] = (double) l;
        results[2] = (double) f;
        return results;
    }
    static void checkConvD(double[] results) {
        System.out.println("FloatMath.checkConvD");
        // TODO: assert values
        for (int i = 0; i < results.length; i++)
            System.out.println(" " + i + ": " + results[i]);
        System.out.println("-2.005440939E9, -8.6133032459203287E18, 123.4560012817382");
    }

    static void checkConsts() {
        System.out.println("FloatMath.checkConsts");

        float f = 10.0f;        // const/special
        assert(f > 9.9 && f < 10.1);

        double d = 10.0;        // const-wide/special
        assert(d > 9.9 && d < 10.1);
    }

    /*
     * Determine if two floating point numbers are approximately equal.
     *
     * (Assumes that floating point is generally working, so we can't use
     * this for the first set of tests.)
     */
    static boolean approxEqual(float a, float b, float maxDelta) {
        if (a > b)
            return (a - b) < maxDelta;
        else
            return (b - a) < maxDelta;
    }
    static boolean approxEqual(double a, double b, double maxDelta) {
        if (a > b)
            return (a - b) < maxDelta;
        else
            return (b - a) < maxDelta;
    }

    /*
     * Test some java.lang.Math functions.
     *
     * The method arguments are positive values.
     */
    static void jlmTests(float ff, double dd) {
        System.out.println("FloatMath.jlmTests");

        assert(approxEqual(Math.abs(ff), ff, 0.001f));
        assert(approxEqual(Math.abs(-ff), ff, 0.001f));
        assert(approxEqual(Math.min(ff, -5.0f), -5.0f, 0.001f));
        assert(approxEqual(Math.max(ff, -5.0f), ff, 0.001f));

        assert(approxEqual(Math.abs(dd), dd, 0.001));
        assert(approxEqual(Math.abs(-dd), dd, 0.001));
        assert(approxEqual(Math.min(dd, -5.0), -5.0, 0.001));
        assert(approxEqual(Math.max(dd, -5.0), dd, 0.001));

        double sq = Math.sqrt(dd);
        assert(approxEqual(sq*sq, dd, 0.001));

        assert(approxEqual(0.5403023058681398, Math.cos(1.0), 0.00000001));
        assert(approxEqual(0.8414709848078965, Math.sin(1.0), 0.00000001));
    }

    public static void run() {
        convTest();

        float[] floatResults;
        double[] doubleResults;
        int[] intResults;
        long[] longResults;

        floatResults = floatOperTest(70000.0f, -3.0f);
        floatOperCheck(floatResults);
        doubleResults = doubleOperTest(70000.0, -3.0);
        doubleOperCheck(doubleResults);

        intResults = convI(0x8877665544332211L, 123.456f, -3.1415926535, 0.0f);
        checkConvI(intResults);
        longResults = convL(0x88776655, 123.456f, -3.1415926535, 0.0);
        checkConvL(longResults);
        floatResults = convF(0x88776655, 0x8877665544332211L, -3.1415926535);
        checkConvF(floatResults);
        doubleResults = convD(0x88776655, 0x8877665544332211L, 123.456f);
        checkConvD(doubleResults);

        unopTest(123.456f);

        checkConsts();

        jlmTests(3.14159f, 123456.78987654321);
    }
}
