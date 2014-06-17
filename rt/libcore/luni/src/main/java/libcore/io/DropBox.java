/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.io;

public final class DropBox {

    /**
     * Hook for customizing how events are reported.
     */
    private static volatile Reporter REPORTER = new DefaultReporter();

    /**
     * Used to replace default Reporter for logging events. Must be non-null.
     */
    public static void setReporter(Reporter reporter) {
        if (reporter == null) {
            throw new NullPointerException("reporter == null");
        }
        REPORTER = reporter;
    }

    /**
     * Returns non-null Reporter.
     */
    public static Reporter getReporter() {
        return REPORTER;
    }

    /**
     * Interface to allow customization of reporting behavior.
     */
    public static interface Reporter {
        public void addData(String tag, byte[] data, int flags);
        public void addText(String tag, String data);
    }

    /**
     * Default Reporter which reports events to the log.
     */
    private static final class DefaultReporter implements Reporter {

        public void addData(String tag, byte[] data, int flags) {
            System.out.println(tag + ": " + Base64.encode(data));
        }

        public void addText(String tag, String data) {
            System.out.println(tag + ": " + data);
        }
    }

    public static void addData(String tag, byte[] data, int flags) {
        getReporter().addData(tag, data, flags);
    }

    public static void addText(String tag, String data) {
        getReporter().addText(tag, data);
    }
}
