// Copyright 2008 The Android Open Source Project

import java.io.Serializable;
import java.util.Arrays;

/**
 * Exercise some class-related instructions.
 */
public class Classes {
    int mSome;

    public void subFunc(boolean wantSub) {
        assert(!wantSub);
    }

    void checkCast(Object thisRef, Object moreRef, Object nullRef) {
        System.out.println("Classes.checkCast");

        Classes classes;
        MoreClasses more;

        classes = (Classes) thisRef;
        assert(thisRef instanceof Classes);
        classes = (Classes) moreRef;
        assert(moreRef instanceof Classes);

        more = (MoreClasses) moreRef;
        assert(moreRef instanceof MoreClasses);
        assert(!(thisRef instanceof MoreClasses));

        try {
            more = (MoreClasses) thisRef;
            assert(false);
        } catch (ClassCastException cce) {
            //System.out.println("  class cast msg: " + cce.getMessage());
            //Dalvik throws terser message than Hotspot VM
            assert(cce.getMessage().regionMatches(false, 0, "Classes", 0, 7));
        }
        assert(!(thisRef instanceof MoreClasses));

        /* hopefully these classes cause a resolve */
        try {
            java.math.RoundingMode mode = (java.math.RoundingMode) thisRef;
            assert(false);
        } catch (ClassCastException cce) {
            //System.out.println("  class cast msg: " + cce.getMessage());
            //Dalvik throws terser message than Hotspot VM
            assert(cce.getMessage().regionMatches(false, 0, "Classes", 0, 7));
        }
        assert(!(thisRef instanceof java.math.BigDecimal));

        /* try some stuff with a null reference */
        classes = (Classes) nullRef;
        classes = (MoreClasses) nullRef;
        more = (MoreClasses) nullRef;
        assert(!(nullRef instanceof Classes));

    }


    static void xTests(Object x) {
        assert(  x instanceof Classes);
        assert(!(x instanceof MoreClasses));
    }
    static void yTests(Object y) {
        assert(  y instanceof Classes);
        assert(  y instanceof MoreClasses);
    }
    static void xarTests(Object xar) {
        assert(  xar instanceof Object);
        assert(!(xar instanceof Classes));
        assert(  xar instanceof Classes[]);
        assert(!(xar instanceof MoreClasses[]));
        assert(  xar instanceof Object[]);
        assert(!(xar instanceof Object[][]));
    }
    static void yarTests(Object yar) {
        assert(  yar instanceof Classes[]);
        assert(  yar instanceof MoreClasses[]);
    }
    static void xarararTests(Object xararar) {
        assert(  xararar instanceof Object);
        assert(  xararar instanceof Object[]);
        assert(!(xararar instanceof Classes));
        assert(!(xararar instanceof Classes[]));
        assert(!(xararar instanceof Classes[][]));
        assert(  xararar instanceof Classes[][][]);
        assert(!(xararar instanceof MoreClasses[][][]));
        assert(  xararar instanceof Object[][][]);
        assert(  xararar instanceof Serializable);
        assert(  xararar instanceof Serializable[]);
        assert(  xararar instanceof Serializable[][]);
        assert(!(xararar instanceof Serializable[][][]));
    }
    static void yarararTests(Object yararar) {
        assert(  yararar instanceof Classes[][][]);
        assert(  yararar instanceof MoreClasses[][][]);
    }
    static void iarTests(Object iar) {
        assert(  iar instanceof Object);
        assert(!(iar instanceof Object[]));
    }
    static void iararTests(Object iarar) {
        assert(  iarar instanceof Object);
        assert(  iarar instanceof Object[]);
        assert(!(iarar instanceof Object[][]));
    }

    /*
     * Exercise filled-new-array and test instanceof on arrays.
     *
     * We call out instead of using "instanceof" directly to avoid
     * compiler optimizations.
     */
    static void arrayInstance() {
        System.out.println("Classes.arrayInstance");

        Classes x = new Classes();
        Classes[] xar = new Classes[1];
        Classes[][] xarar = new Classes[1][1];
        Classes[][][] xararar = new Classes[1][2][3];
        MoreClasses y = new MoreClasses();
        MoreClasses[] yar = new MoreClasses[3];
        MoreClasses[][] yarar = new MoreClasses[2][3];
        MoreClasses[][][] yararar = new MoreClasses[1][2][3];
        int[] iar = new int[1];
        int[][] iarar = new int[1][1];
        Object test;

        xTests(x);
        yTests(y);
        xarTests(xar);
        yarTests(yar);
        xarararTests(xararar);
        yarararTests(yararar);
        iarTests(iar);
        iararTests(iarar);

        yararar[0] = yarar;
        yararar[0][0] = yar;
        yararar[0][1] = yar;
        yararar[0][0][0] = y;
        yararar[0][0][1] = y;
        yararar[0][0][2] = y;
        yararar[0][1][0] = y;
        yararar[0][1][1] = y;
        yararar[0][1][2] = y;

        String strForm;

        String[][][][] multi1 = new String[2][3][2][1];
        multi1[0] = new String[2][3][2];
        multi1[0][1] = new String[3][2];
        multi1[0][1][2] = new String[2];
        multi1[0][1][2][1] = "HELLO-1";
        strForm = Arrays.deepToString(multi1);

        String[][][][][] multi2 = new String[5][2][3][2][1];
        multi2[0] = new String[5][2][3][2];
        multi2[0][1] = new String[5][2][3];
        multi2[0][1][2] = new String[5][2];
        multi2[0][1][2][1] = new String[5];
        multi2[0][1][2][1][4] = "HELLO-2";
        strForm = Arrays.deepToString(multi2);


        String[][][][][][] multi3 = new String[2][5][2][3][2][1];
        multi3[0] = new String[2][][][][];
        multi3[0][1] = new String[3][][][];
        multi3[0][1][2] = new String[2][][];
        multi3[0][1][2][1] = new String[5][];
        multi3[0][1][2][1][4] = new String[2];
        multi3[0][1][2][1][4][1] = "HELLO-3";
        strForm = Arrays.deepToString(multi3);

        // build up pieces
        String[][][][][][] multi4 = new String[1][][][][][];
        multi4[0] = new String[2][][][][];
        multi4[0][1] = new String[3][][][];
        multi4[0][1][2] = new String[2][][];
        multi4[0][1][2][1] = new String[5][];
        multi4[0][1][2][1][4] = new String[2];
        multi4[0][1][2][1][4][1] = "HELLO-4";
        strForm = Arrays.deepToString(multi4);

        /* this is expected to fail; 1073921584 * 4 overflows 32 bits */
        try {
            String[][][][][] multiX = new String[5][2][3][2][1073921584];
            assert(false);
        } catch (Error e) {
            //System.out.println("  Got expected failure: " + e);
        }

    }

    public static void run() {
        Classes classes = new Classes();
        MoreClasses more = new MoreClasses();
        classes.checkCast(classes, more, null);

        more.subFunc(true);
        more.superFunc(false);
        arrayInstance();
    }
}

class MoreClasses extends Classes {
    int mMore;

    public MoreClasses() {}

    public void subFunc(boolean wantSub) {
        assert(wantSub);
    }

    public void superFunc(boolean wantSub) {
        super.subFunc(wantSub);
    }
}
