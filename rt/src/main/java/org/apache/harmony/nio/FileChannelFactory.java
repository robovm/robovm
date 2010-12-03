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

package org.apache.harmony.nio;

import java.nio.channels.FileChannel;

import org.apache.harmony.nio.internal.ReadOnlyFileChannel;
import org.apache.harmony.nio.internal.ReadWriteFileChannel;
import org.apache.harmony.nio.internal.WriteOnlyFileChannel;
import org.apache.harmony.nio.internal.nls.Messages;
import org.apache.harmony.luni.platform.IFileSystem;

/**
 * A simple factory to provide a generic way to create FileChannel
 * implementation from within the java.io package.
 */
public class FileChannelFactory {
    public static FileChannel getFileChannel(Object stream, long fd, int mode) {
        switch (mode) {
            case IFileSystem.O_RDONLY:
                return new ReadOnlyFileChannel(stream, fd);
            case IFileSystem.O_WRONLY:
                return new WriteOnlyFileChannel(stream, fd);
            case IFileSystem.O_RDWR:
                return new ReadWriteFileChannel(stream, fd);
            case IFileSystem.O_RDWRSYNC:
                return new ReadWriteFileChannel(stream, fd);
            case IFileSystem.O_APPEND:
                return new WriteOnlyFileChannel(stream, fd, true);
            default:
                // nio.09=Unknown file channel type: {0}
                throw new RuntimeException(Messages.getString("nio.09", mode)); //$NON-NLS-1$
        }
    }
}
