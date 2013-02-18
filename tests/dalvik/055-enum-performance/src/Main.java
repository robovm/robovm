import otherpackage.OtherPackagePublicEnum;

public class Main {
    /** used by {@link #basisCall} */
    static private int basisTestValue = 12;

    static public void main(String[] args) throws Exception {
        boolean timing = (args.length >= 1) && args[0].equals("--timing");
        run(timing);
    }

    static public void run(boolean timing) {
        preTest();

        long time0 = System.nanoTime();
        int count1 = test1(500);
        long time1 = System.nanoTime();
        int count2 = test2(500);
        long time2 = System.nanoTime();
        int count3 = test3(500);
        long time3 = System.nanoTime();
        int count4 = basis(500);
        long time4 = System.nanoTime();

        System.out.println("basis: performed " + count4 + " iterations");
        System.out.println("test1: performed " + count1 + " iterations");
        System.out.println("test2: performed " + count2 + " iterations");
        System.out.println("test3: performed " + count3 + " iterations");

        double msec1 = (time1 - time0) / (double) count1 / 1000000;
        double msec2 = (time2 - time1) / (double) count2 / 1000000;
        double msec3 = (time3 - time2) / (double) count3 / 1000000;
        double basisMsec = (time4 - time3) / (double) count4 / 1000000;

        double avg = (msec1 + msec2 + msec3) / 3;
        if (avg < (basisMsec * 10)) {
            System.out.println("Timing is acceptable.");
        } else {
            System.out.println("Iterations are taking too long!");
            timing = true;
        }

        if (timing) {
            System.out.printf("basis time: %.3g msec\n", basisMsec);
            System.out.printf("test1: %.3g msec per iteration\n", msec1);
            System.out.printf("test2: %.3g msec per iteration\n", msec2);
            System.out.printf("test3: %.3g msec per iteration\n", msec3);
        }

    }

    static public void preTest() {
        /*
         * This is meant to ensure that the basic enum functionality
         * really is working.
         */

        Class<SamePackagePublicEnum> c = SamePackagePublicEnum.class;

        System.out.println(Enum.valueOf(c, "FOUR"));
        System.out.println(Enum.valueOf(c, "ONE"));
        System.out.println(Enum.valueOf(c, "FOURTEEN"));
        System.out.println(Enum.valueOf(c, "NINE"));
        System.out.println(Enum.valueOf(c, "FIVE"));
        System.out.println(Enum.valueOf(c, "TWELVE"));

        System.out.println(Enum.valueOf(c, "ZERO").getClass().getName());
    }

    static final String[] BASIS_COMPARE_ARRAY = {
        "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
        "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN",
        "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"
    };

    static public int basis(int iters) {
        for (int i = iters; i > 0; i--) {
            basisValueOf("ZERO");
            basisValueOf("ONE");
            basisValueOf("TWO");
            basisValueOf("THREE");
            basisValueOf("FOUR");
            basisValueOf("FIVE");
            basisValueOf("SIX");
            basisValueOf("SEVEN");
            basisValueOf("EIGHT");
            basisValueOf("NINE");
            basisValueOf("TEN");
            basisValueOf("ELEVEN");
            basisValueOf("TWELVE");
            basisValueOf("THIRTEEN");
            basisValueOf("FOURTEEN");
            basisValueOf("FIFTEEN");
            basisValueOf("SIXTEEN");
            basisValueOf("SEVENTEEN");
            basisValueOf("EIGHTEEN");
            basisValueOf("NINETEEN");
        }

        return iters * 20;
    }

    static String basisValueOf(String key) {
        for (String s : BASIS_COMPARE_ARRAY) {
            if (s.equals(key)) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }

    static public int test1(int iters) {
        Class<SamePackagePublicEnum> c = SamePackagePublicEnum.class;
        for (int i = iters; i > 0; i--) {
            Enum.valueOf(c, "ZERO");
            Enum.valueOf(c, "ONE");
            Enum.valueOf(c, "TWO");
            Enum.valueOf(c, "THREE");
            Enum.valueOf(c, "FOUR");
            Enum.valueOf(c, "FIVE");
            Enum.valueOf(c, "SIX");
            Enum.valueOf(c, "SEVEN");
            Enum.valueOf(c, "EIGHT");
            Enum.valueOf(c, "NINE");
            Enum.valueOf(c, "TEN");
            Enum.valueOf(c, "ELEVEN");
            Enum.valueOf(c, "TWELVE");
            Enum.valueOf(c, "THIRTEEN");
            Enum.valueOf(c, "FOURTEEN");
            Enum.valueOf(c, "FIFTEEN");
            Enum.valueOf(c, "SIXTEEN");
            Enum.valueOf(c, "SEVENTEEN");
            Enum.valueOf(c, "EIGHTEEN");
            Enum.valueOf(c, "NINETEEN");
        }

        return iters * 20;
    }

    static public int test2(int iters) {
        Class<SamePackagePrivateEnum> c = SamePackagePrivateEnum.class;
        for (int i = iters; i > 0; i--) {
            Enum.valueOf(c, "ZERO");
            Enum.valueOf(c, "ONE");
            Enum.valueOf(c, "TWO");
            Enum.valueOf(c, "THREE");
            Enum.valueOf(c, "FOUR");
            Enum.valueOf(c, "FIVE");
            Enum.valueOf(c, "SIX");
            Enum.valueOf(c, "SEVEN");
            Enum.valueOf(c, "EIGHT");
            Enum.valueOf(c, "NINE");
            Enum.valueOf(c, "TEN");
            Enum.valueOf(c, "ELEVEN");
            Enum.valueOf(c, "TWELVE");
            Enum.valueOf(c, "THIRTEEN");
            Enum.valueOf(c, "FOURTEEN");
            Enum.valueOf(c, "FIFTEEN");
            Enum.valueOf(c, "SIXTEEN");
            Enum.valueOf(c, "SEVENTEEN");
            Enum.valueOf(c, "EIGHTEEN");
            Enum.valueOf(c, "NINETEEN");
        }

        return iters * 20;
    }

    static public int test3(int iters) {
        Class<OtherPackagePublicEnum> c = OtherPackagePublicEnum.class;
        for (int i = iters; i > 0; i--) {
            Enum.valueOf(c, "ZERO");
            Enum.valueOf(c, "ONE");
            Enum.valueOf(c, "TWO");
            Enum.valueOf(c, "THREE");
            Enum.valueOf(c, "FOUR");
            Enum.valueOf(c, "FIVE");
            Enum.valueOf(c, "SIX");
            Enum.valueOf(c, "SEVEN");
            Enum.valueOf(c, "EIGHT");
            Enum.valueOf(c, "NINE");
            Enum.valueOf(c, "TEN");
            Enum.valueOf(c, "ELEVEN");
            Enum.valueOf(c, "TWELVE");
            Enum.valueOf(c, "THIRTEEN");
            Enum.valueOf(c, "FOURTEEN");
            Enum.valueOf(c, "FIFTEEN");
            Enum.valueOf(c, "SIXTEEN");
            Enum.valueOf(c, "SEVENTEEN");
            Enum.valueOf(c, "EIGHTEEN");
            Enum.valueOf(c, "NINETEEN");
        }

        return iters * 20;
    }
}
