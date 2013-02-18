public class Main {
    static public void main(String[] args) throws Exception {
        int millis = 1000;

        if (args.length != 0) {
            millis = Integer.parseInt(args[0]);
        }

        System.out.println("Sleeping " + millis + " msec...");

        long start = System.currentTimeMillis();
        Thread.sleep(millis);
        long elapsed = System.currentTimeMillis() - start;
        long offBy = Math.abs(elapsed - millis);

        System.out.println("Done sleeping");

        if (offBy > 250) {
            System.out.println("Actually slept about " + elapsed + " msec...");
        }
    }
}
