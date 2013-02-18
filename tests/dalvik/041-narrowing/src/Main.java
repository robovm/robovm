public class Main {
    public static void main(String[] args) {
        test_printNarrowing();
    }

    public static void test_printNarrowing() {

        System.out.println();
        System.out.println("Double.POSITIVE_INFINITY = "
                + Long.toHexString(Double.doubleToRawLongBits(Double.POSITIVE_INFINITY)));
        System.out.println("Double.NEGATIVE_INFINITY = "
                + Long.toHexString(Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY)));
        System.out.println("Float.POSITIVE_INFINITY  = "
                + Long.toHexString(Double.doubleToRawLongBits(Float.POSITIVE_INFINITY)));
        System.out.println("Float.NEGATIVE_INFINITY  = "
                + Long.toHexString(Double.doubleToRawLongBits(Float.NEGATIVE_INFINITY)));
        System.out.println("Double.NaN               = "
                + Long.toHexString(Double.doubleToRawLongBits(Double.NaN)));
        System.out.println("Float.NaN                = "
                + Long.toHexString(Double.doubleToRawLongBits(Float.NaN)));
        double dbl2 = Double.NaN;
        System.out.println();
        System.out.println("(byte) Double.NaN  =               "
                + (Long.toHexString((byte)dbl2).equals("0") ? "00" : Long.toHexString((byte)dbl2)
                        .substring(6)) + " expected:               00");
        System.out.println("(short) Double.NaN =             "
                + (Integer.toHexString((short)dbl2).equals("0") ? "0000" : Integer.toHexString(
                        (short)dbl2).substring(4)) + " expected:             0000");
        System.out.println("(int) Double.NaN   =         "
                + (Integer.toHexString((int)dbl2).equals("0") ? "00000000" : Integer
                        .toHexString((int)dbl2)) + " expected:         00000000");
        System.out.println("(long) Double.NaN  = "
                + (Long.toHexString((long)dbl2).equals("0") ? "0000000000000000" : Long
                        .toHexString((long)dbl2)) + " expected: 0000000000000000");
        float fl2 = Float.NaN;
        System.out.println();
        System.out.println("(byte) Float.NaN  =               "
                + (Long.toHexString((byte)fl2).equals("0") ? "00" : Long.toHexString((byte)fl2)
                        .substring(6)) + " expected:               00");
        System.out.println("(short) Float.NaN =             "
                + (Integer.toHexString((short)fl2).equals("0") ? "0000" : Integer.toHexString(
                        (short)fl2).substring(4)) + " expected:             0000");
        System.out.println("(int) Float.NaN   =         "
                + (Integer.toHexString((int)fl2).equals("0") ? "00000000" : Integer
                        .toHexString((int)fl2)) + " expected:         00000000");
        System.out.println("(long) Float.NaN  = "
                + (Long.toHexString((long)fl2).equals("0") ? "0000000000000000" : Long
                        .toHexString((long)fl2)) + " expected: 0000000000000000");
        double dbl3 = Double.POSITIVE_INFINITY;
        System.out.println();
        System.out.println("(byte) Double.POSITIVE_INFINITY  =               "
                + (Integer.toHexString((byte)dbl3).equals("0") ? "00" : Integer.toHexString(
                        (byte)dbl3).substring(6)) + " expected:               ff");
        System.out.println("(short) Double.POSITIVE_INFINITY =             "
                + (Integer.toHexString((short)dbl3).equals("0") ? "0000" : Integer.toHexString(
                        (short)dbl3).substring(4)) + " expected:             ffff");
        System.out.println("(int) Double.POSITIVE_INFINITY   =         "
                + Integer.toHexString((int)dbl3) + " expected:         7fffffff");
        System.out.println("(long) Double.POSITIVE_INFINITY  = " + Long.toHexString((long)dbl3)
                + " expected: 7fffffffffffffff");
        double dbl4 = Double.NEGATIVE_INFINITY;
        System.out.println();
        System.out.println("(byte) Double.NEGATIVE_INFINITY  = "
                + (Long.toHexString((byte)dbl4).equals("0") ? "              00" : Long
                        .toHexString((byte)dbl4)) + " expected:               00");
        System.out.println("(short) Double.NEGATIVE_INFINITY = "
                + (Integer.toHexString((short)dbl4).equals("0") ? "            0000" : Long
                        .toHexString((short)dbl4)) + " expected:             0000");
        System.out.println("(int) Double.NEGATIVE_INFINITY   =         "
                + Integer.toHexString((int)dbl4) + " expected:         80000000");
        System.out.println("(long) Double.NEGATIVE_INFINITY  = " + Long.toHexString((long)dbl4)
                + " expected: 8000000000000000");
        float fl3 = Float.POSITIVE_INFINITY;
        System.out.println();
        System.out.println("(byte) Float.POSITIVE_INFINITY   =               "
                + (Integer.toHexString((byte)fl3).equals("0") ? "00" : Integer.toHexString(
                        (byte)fl3).substring(6)) + " expected:               ff");
        System.out.println("(short) Float.POSITIVE_INFINITY  =             "
                + (Integer.toHexString((short)fl3).equals("0") ? "0000" : Integer.toHexString(
                        (short)fl3).substring(4)) + " expected:             ffff");
        System.out.println("(int) Float.POSITIVE_INFINITY    =         "
                + Integer.toHexString((int)fl3) + " expected:         7fffffff");
        System.out.println("(long) Float.POSITIVE_INFINITY   = " + Long.toHexString((long)fl3)
                + " expected: 7fffffffffffffff");
        float fl4 = Float.NEGATIVE_INFINITY;
        System.out.println();
        System.out.println("(byte) Float.NEGATIVE_INFINITY   = "
                + (Long.toHexString((byte)fl4).equals("0") ? "              00" : Long
                        .toHexString((byte)fl4)) + " expected:               00");
        System.out.println("(short) Float.NEGATIVE_INFINITY  = "
                + (Integer.toHexString((short)fl4).equals("0") ? "            0000" : Long
                        .toHexString((short)fl4)) + " expected:             0000");
        System.out.println("(int) Float.NEGATIVE_INFINITY    =         "
                + Integer.toHexString((int)fl4) + " expected:         80000000");
        System.out.println("(long) Float.NEGATIVE_INFINITY   = " + Long.toHexString((long)fl4)
                + " expected: 8000000000000000");
        System.out.println();
    }
}
