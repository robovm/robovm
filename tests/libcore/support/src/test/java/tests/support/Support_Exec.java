/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.support;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Support_Exec extends TestCase {

    private static final boolean againstDalvik
            = System.getProperty("java.vendor").contains("Android");

    /**
     * Returns a builder configured with the appropriate VM ("dalvikvm" or
     * "java") and arguments (as specified by the system property
     * {@code hy.test.vmargs}).
     */
    public static ProcessBuilder javaProcessBuilder()
            throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();

        // construct the name of executable file
        builder.command().add(againstDalvik ? "dalvikvm" : "java");

        // parse hy.test.vmargs if was given
        String testVMArgs = System.getProperty("hy.test.vmargs");
        if (testVMArgs != null) {
            builder.command().addAll(Arrays.asList(testVMArgs.split("\\s+")));
        }

        return builder;
    }

    /**
     * Returns a command-line ready path formed by joining the path elements
     * with the system path separator as a separator.
     */
    public static String createPath(String... elements) {
        StringBuilder result = new StringBuilder();
        for (String element : elements) {
            result.append(File.pathSeparator);
            result.append(element);
        }
        return result.toString();
    }

    /**
     * Starts the specified process, collects its output from standard out and
     * standard err, and returns. If the stream emits anything to standard err,
     * an AssertionFailedError will be thrown.
     *
     * <p>This method assumes the target process will complete within thirty
     * seconds. If it does not, an AssertionFailedError will be thrown.
     */
    public static String execAndGetOutput(ProcessBuilder builder) throws IOException {
        Process process = builder.start();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            Future<String> errFuture = executorService.submit(
                    streamToStringCallable(process.getErrorStream()));
            Future<String> outFuture = executorService.submit(
                    streamToStringCallable(process.getInputStream()));

            Throwable failure;
            String out = "";
            try {
                out = outFuture.get(30, TimeUnit.SECONDS);
                String err = errFuture.get(30, TimeUnit.SECONDS);
                failure = err.length() > 0
                        ? new AssertionFailedError("Unexpected err stream data:\n" + err)
                        : null;
            } catch (Exception e) {
                failure = e;
            }

            if (failure != null) {
                AssertionFailedError error = new AssertionFailedError(
                        "Failed to execute " + builder.command() + "; output was:\n" + out);
                error.initCause(failure);
                throw error;
            } else {
                return out;
            }
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * Starts the process described by 'builder', and asserts that it sees
     * 'expectedOut' on stdout and 'expectedErr' on stderr. Times out after
     * 10s.
     */
    public static void execAndCheckOutput(ProcessBuilder builder,
            String expectedOut, String expectedErr) throws Exception {
        Process process = builder.start();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            Future<String> errFuture =
                    executorService.submit(streamToStringCallable(process.getErrorStream()));
            Future<String> outFuture =
                    executorService.submit(streamToStringCallable(process.getInputStream()));
            assertEquals(expectedOut, outFuture.get(10, TimeUnit.SECONDS));
            assertEquals(expectedErr, errFuture.get(10, TimeUnit.SECONDS));
        } finally {
            executorService.shutdown();
            process.waitFor();
        }
    }

    private static Callable<String> streamToStringCallable(final InputStream in) {
        return new Callable<String>() {
            public String call() throws Exception {
                StringWriter writer = new StringWriter();
                Reader reader = new InputStreamReader(in);
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
                return writer.toString();
            }
        };
    }
}
