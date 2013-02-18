public class Switch {
    /**
     * Test switch() blocks
     */
    private static void testSwitch() {
        System.out.println("Switch.testSwitch");

        int a = 1;

        switch (a) {
            case -1: assert(false); break;
            case 0: assert(false); break;
            case 1: /*correct*/ break;
            case 2: assert(false); break;
            case 3: assert(false); break;
            case 4: assert(false); break;
            default: assert(false); break;
        }
        switch (a) {
            case 3: assert(false); break;
            case 4: assert(false); break;
            default: /*correct*/ break;
        }

        a = 0x12345678;

        switch (a) {
            case 0x12345678: /*correct*/ break;
            case 0x12345679: assert(false); break;
            default: assert(false); break;
        }
        switch (a) {
            case 57: assert(false); break;
            case -6: assert(false); break;
            case 0x12345678: /*correct*/ break;
            case 22: assert(false); break;
            case 3: assert(false); break;
            default: assert(false); break;
        }
        switch (a) {
            case -6: assert(false); break;
            case 3: assert(false); break;
            default: /*correct*/ break;
        }

        a = -5;
        switch (a) {
            case 12: assert(false); break;
            case -5: /*correct*/ break;
            case 0: assert(false); break;
            default: assert(false); break;
        }

        switch (a) {
            default: /*correct*/ break;
        }
    }

    public static void run() {
        testSwitch();
    }
}
