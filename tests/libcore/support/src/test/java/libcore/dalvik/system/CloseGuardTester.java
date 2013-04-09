/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.dalvik.system;

import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import junit.framework.Assert;
import libcore.java.lang.ref.FinalizationTester;

public final class CloseGuardTester implements Closeable {

    private final List<LogRecord> logRecords = new CopyOnWriteArrayList<LogRecord>();
    private final Logger logger = Logger.getLogger(CloseGuard.class.getName());

    private final Handler logWatcher = new Handler() {
        @Override public void close() {}
        @Override public void flush() {}
        @Override public void publish(LogRecord record) {
            logRecords.add(record);
        }
    };

    public CloseGuardTester() {
        /*
         * Collect immediately before we start monitoring the CloseGuard logs.
         * This lowers the chance that we'll report an unrelated leak.
         */
        FinalizationTester.induceFinalization();
        logger.addHandler(logWatcher);
    }

    public void assertEverythingWasClosed() {
        FinalizationTester.induceFinalization();

        if (!logRecords.isEmpty()) {
            // print the log records with the output of this test
            for (LogRecord leak : logRecords) {
                new ConsoleHandler().publish(leak);
            }
            Assert.fail("CloseGuard detected unclosed resources!");
        }
    }

    @Override public void close() throws IOException {
        Logger.getLogger(CloseGuard.class.getName()).removeHandler(logWatcher);
    }
}
