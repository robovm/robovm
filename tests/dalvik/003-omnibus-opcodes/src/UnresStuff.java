/*
 * Unresolved classes / fields / methods in a resolved class.
 *
 * "happy" version.
 */

public class UnresStuff {
    public int instField;

    public static int staticField;

    public double wideInstField;
    public static double wideStaticField;

    public void virtualMethod() {
        System.out.println("unres!");
    }

    public static void staticMethod() {
        System.out.println("unres!");
    }
}
