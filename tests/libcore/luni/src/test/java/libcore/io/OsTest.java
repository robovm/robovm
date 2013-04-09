/*
 * Copyright (C) 2011 The Android Open Source Project
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

import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import junit.framework.TestCase;

import static libcore.io.OsConstants.*;

public class OsTest extends TestCase {
    public void testIsSocket() throws Exception {
        File f = new File("/dev/null");
        FileInputStream fis = new FileInputStream(f);
        assertFalse(S_ISSOCK(Libcore.os.fstat(fis.getFD()).st_mode));
        fis.close();

        ServerSocket s = new ServerSocket();
        assertTrue(S_ISSOCK(Libcore.os.fstat(s.getImpl$().getFD$()).st_mode));
        s.close();
    }
}
