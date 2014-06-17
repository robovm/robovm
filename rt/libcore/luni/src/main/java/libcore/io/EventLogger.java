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

public final class EventLogger {

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
        public void report (int code, Object... list);
    }

    /**
     * Default Reporter which reports events to the log.
     */
    private static final class DefaultReporter implements Reporter {
        @Override
        public void report (int code, Object... list) {
            StringBuilder sb = new StringBuilder();
            sb.append(code);
            for (Object o : list) {
                sb.append(",");
                sb.append(o.toString());
            }
            System.out.println(sb);
        }
    }

    public static void writeEvent(int code, Object... list) {
        getReporter().report(code, list);
    }
}
