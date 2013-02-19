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

package java.nio;

import java.io.FileDescriptor;
import java.nio.channels.FileChannel;

/**
 * @hide internal use only
 */
public final class NioUtils {
    private NioUtils() {
    }

    /**
     * Gets the start address of a direct buffer.
     * <p>
     * This method corresponds to the JNI function:
     *
     * <pre>
     *    void* GetDirectBufferAddress(JNIEnv* env, jobject buf);
     * </pre>
     *
     * @param buf
     *            the direct buffer whose address shall be returned must not be
     *            <code>null</code>.
     * @return the address of the buffer given, or zero if the buffer is not a
     *         direct Buffer.
     */
    public static int getDirectBufferAddress(Buffer buffer) {
        return buffer.effectiveDirectAddress;
    }

    public static void freeDirectBuffer(ByteBuffer buffer) {
        if (buffer == null) {
            return;
        }
        if (buffer instanceof DirectByteBuffer) {
            ((DirectByteBuffer) buffer).free();
        } else if (buffer instanceof MappedByteBuffer) {
            ((MappedByteBufferAdapter) buffer).free();
        } else {
            throw new AssertionError();
        }
    }

    /**
     * Returns the int file descriptor from within the given FileChannel 'fc'.
     */
    public static FileDescriptor getFD(FileChannel fc) {
        return ((FileChannelImpl) fc).getFD();
    }

    /**
     * Helps bridge between io and nio.
     */
    public static FileChannel newFileChannel(Object stream, FileDescriptor fd, int mode) {
        return new FileChannelImpl(stream, fd, mode);
    }

    /**
     * Exposes the array backing a non-direct ByteBuffer, even if the ByteBuffer is read-only.
     * Normally, attempting to access the array backing a read-only buffer throws.
     */
    public static byte[] unsafeArray(ByteBuffer b) {
        return ((HeapByteBuffer) b).backingArray;
    }

    /**
     * Exposes the array offset for the array backing a non-direct ByteBuffer,
     * even if the ByteBuffer is read-only.
     */
    public static int unsafeArrayOffset(ByteBuffer b) {
        return ((HeapByteBuffer) b).offset;
    }
}
