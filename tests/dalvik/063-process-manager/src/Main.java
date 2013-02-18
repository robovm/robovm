import java.util.Map;

public class Main {
    static public void main(String[] args) throws Exception {
        checkManager();
        for (int i = 1; i <= 2; i++) {
            System.out.println("\nspawning child #" + i);
            child();
            Thread.sleep(2000);
            checkManager();
        }
        System.out.println("\ndone!");
    }

    static private void child() throws Exception {
        System.out.println("spawning child");
        ProcessBuilder pb = new ProcessBuilder("sleep", "5");
        Process proc = pb.start();
        Thread.sleep(1000);
        checkManager();
        proc.waitFor();
        System.out.println("child died");
    }

    static private void checkManager() {
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        boolean found = false;

        for (Map.Entry<Thread, StackTraceElement[]> entry :
                 traces.entrySet()) {
            Thread t = entry.getKey();
            String name = t.getName();
            if (name.equals("java.lang.ProcessManager")) {
                System.out.println("process manager: " + t.getState());
                found = true;
            }
        }

        if (! found) {
            System.out.println("process manager: nonexistent");
        }
    }
}
