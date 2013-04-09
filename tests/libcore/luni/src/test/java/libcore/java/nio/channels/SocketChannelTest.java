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

package libcore.java.nio.channels;

import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest extends junit.framework.TestCase {
    public void test_read_intoReadOnlyByteArrays() throws Exception {
        ByteBuffer readOnly = ByteBuffer.allocate(1).asReadOnlyBuffer();
        ServerSocket ss = new ServerSocket(0);
        ss.setReuseAddress(true);
        SocketChannel sc = SocketChannel.open(ss.getLocalSocketAddress());
        try {
            sc.read(readOnly);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            sc.read(new ByteBuffer[] { readOnly });
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            sc.read(new ByteBuffer[] { readOnly }, 0, 1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
}
