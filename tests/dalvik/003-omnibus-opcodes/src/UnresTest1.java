/*
 * Test failure to resolve class members.
 */
class UnresTest1 {
    public static void run() {
        System.out.println("UnresTest1...");

        UnresStuff stuff = new UnresStuff();
        try {
            int x = stuff.instField;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }
        try {       // hit the same one a second time
            int x = stuff.instField;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }
        try {
            stuff.instField = 5;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }

        try {
            double d = stuff.wideInstField;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }
        try {
            stuff.wideInstField = 0.0;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }

        try {
            int y = UnresStuff.staticField;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }
        try {
            UnresStuff.staticField = 17;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }

        try {
            double d = UnresStuff.wideStaticField;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }
        try {
            UnresStuff.wideStaticField = 1.0;
            assert(false);
        } catch (NoSuchFieldError nsfe) {
            // good
        }

        try {
            stuff.virtualMethod();
            assert(false);
        } catch (NoSuchMethodError nsfe) {
            // good
        }
        try {
            UnresStuff.staticMethod();
            assert(false);
        } catch (NoSuchMethodError nsfe) {
            // good
        }
    }
}
