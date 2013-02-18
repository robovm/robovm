// Copyright 2008 The Android Open Source Project



/**
 * Test comparison operators.
 */
public class Compare {

    /*
     * Test the integer comparisons in various ways.
     */
    static void testIntCompare(int minus, int plus, int plus2, int zero) {
        System.out.println("IntMath.testIntCompare");

        if (minus > plus)
            assert(false);
        if (minus >= plus)
            assert(false);
        if (plus < minus)
            assert(false);
        if (plus <= minus)
            assert(false);
        if (plus == minus)
            assert(false);
        if (plus != plus2)
            assert(false);

        /* try a branch-taken */
        if (plus != minus) {
            assert(true);
        } else {
            assert(false);
        }

        if (minus > 0)
            assert(false);
        if (minus >= 0)
            assert(false);
        if (plus < 0)
            assert(false);
        if (plus <= 0)
            assert(false);
        if (plus == 0)
            assert(false);
        if (zero != 0)
            assert(false);

        if (zero == 0) {
            assert(true);
        } else {
            assert(false);
        }
    }

    /*
     * Test cmp-long.
     *
     * minus=-5, alsoMinus=0xFFFFFFFF00000009, plus=4, alsoPlus=8
     */
    static void testLongCompare(long minus, long alsoMinus, long plus,
        long alsoPlus) {

        System.out.println("IntMath.testLongCompare");
        if (minus > plus)
            assert(false);
        if (plus < minus)
            assert(false);
        if (plus == minus)
            assert(false);

        if (plus >= plus+1)
            assert(false);
        if (minus >= minus+1)
            assert(false);

        /* try a branch-taken */
        if (plus != minus) {
            assert(true);
        } else {
            assert(false);
        }

        /* compare when high words are equal but low words differ */
        if (plus > alsoPlus)
            assert(false);
        if (alsoPlus < plus)
            assert(false);
        if (alsoPlus == plus)
            assert(false);

        /* high words are equal, low words have apparently different signs */
        if (minus < alsoMinus)      // bug!
            assert(false);
        if (alsoMinus > minus)
            assert(false);
        if (alsoMinus == minus)
            assert(false);
    }

    /*
     * Test cmpl-float and cmpg-float.
     */
    static void testFloatCompare(float minus, float plus, float plus2,
        float nan) {

        System.out.println("IntMath.testFloatCompare");
        if (minus > plus)
            assert(false);
        if (plus < minus)
            assert(false);
        if (plus == minus)
            assert(false);
        if (plus != plus2)
            assert(false);

        if (plus <= nan)
            assert(false);
        if (plus >= nan)
            assert(false);
        if (minus <= nan)
            assert(false);
        if (minus >= nan)
            assert(false);
        if (nan >= plus)
            assert(false);
        if (nan <= plus)
            assert(false);

        if (nan == nan)
            assert(false);
    }

    static void testDoubleCompare(double minus, double plus, double plus2,
        double nan) {

        System.out.println("IntMath.testDoubleCompare");
        if (minus > plus)
            assert(false);
        if (plus < minus)
            assert(false);
        if (plus == minus)
            assert(false);
        if (plus != plus2)
            assert(false);

        if (plus <= nan)
            assert(false);
        if (plus >= nan)
            assert(false);
        if (minus <= nan)
            assert(false);
        if (minus >= nan)
            assert(false);
        if (nan >= plus)
            assert(false);
        if (nan <= plus)
            assert(false);

        if (nan == nan)
            assert(false);
    }

    public static void run() {
        testIntCompare(-5, 4, 4, 0);
        testLongCompare(-5L, -4294967287L, 4L, 8L);

        testFloatCompare(-5.0f, 4.0f, 4.0f, (1.0f/0.0f) / (1.0f/0.0f));
        testDoubleCompare(-5.0, 4.0, 4.0, (1.0/0.0) / (1.0/0.0));
    }
}
