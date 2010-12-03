/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.harmony.nio.internal;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * The concrete implementation of an NIO file lock object.
 */
final class FileLockImpl extends FileLock {

    // Remembers if this lock has been released via the API.
    private boolean isReleased = false;

    /**
     * Answers a new file lock object with the given parameters.
     * 
     * @param channel
     *            the file channel hosting the lock.
     * @param position
     *            the start position of the lock, in bytes
     * @param size
     *            the length of the lock, in bytes
     * @param shared
     *            whether this lock is shared (true) or exclusive (false)
     */
    public FileLockImpl(FileChannel channel, long position, long size,
            boolean shared) {
        super(channel, position, size, shared);
    }

    /**
     * Tests to see if the lock is valid. A lock can be invalidated if the
     * channel it is acquired on is closed or if it is released. (non-Javadoc)
     * 
     * @see java.nio.channels.FileLock#isValid()
     */
    @Override
    public boolean isValid() {
        return !isReleased && channel().isOpen();
    }

    /**
     * Releases the file lock on the channel that acquired it. Releasing an
     * invalid lock has no effect.
     * 
     * @see java.nio.channels.FileLock#release()
     */
    @Override
    public void release() throws IOException {
        if (!channel().isOpen()) {
            throw new ClosedChannelException();
        }

        if (!isReleased) {
            ((FileChannelImpl) channel()).release(this);
            isReleased = true;
        }
    }
}
