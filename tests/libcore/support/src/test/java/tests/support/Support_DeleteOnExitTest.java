package tests.support;

import java.io.File;
import java.io.FileOutputStream;

public class Support_DeleteOnExitTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        file1.deleteOnExit();
        file2.deleteOnExit();
        Runtime.getRuntime().exit(0);
    }

}
