/*
 * Copyright (C) 2009 The Android Open Source Project
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

package dalvik.system;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An optimized handler for efficient publishing of basic log messages.
 * Implementers should also be subclasses of {@link java.util.logging.Handler}.
 *
 * <p>Unlike the default log handler, this API doesn't require intermediate
 * objects to be allocated for log handling. It also includes a short tag, which
 * may otherwise need to be calculated for each published message.
 *
 * @hide
 */
public interface DalvikLogHandler {

    /**
     * Publishes a log message. Unlike {@link
     * java.util.logging.Handler#publish(java.util.logging.LogRecord)}, this
     * method includes only the raw log message. Log messages that were created
     * with additional fields (parameters, source methods, etc.) will flow
     * through the conventional channels instead.
     *
     * @param tag the short (23 characters or fewer) logger tag identifying
     *      {@code logger}.
     */
    void publish(Logger source, String tag, Level level, String message);

    // TODO: support messages with throwables?
}