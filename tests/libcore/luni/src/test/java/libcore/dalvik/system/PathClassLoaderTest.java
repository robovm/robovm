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

import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import junit.framework.TestCase;

public final class PathClassLoaderTest extends TestCase {

    /**
     * Make sure we're searching the application library path first.
     * http://b/issue?id=2933456
     */
    public void testLibraryPathSearchOrder() throws IOException {
        File tmp = new File(System.getProperty("java.io.tmpdir"));
        File systemLibPath = new File(tmp, "systemLibPath");
        File applicationLibPath = new File(tmp, "applicationLibPath");
        makeTempFile(systemLibPath, "libduplicated.so");
        File applicationLib = makeTempFile(applicationLibPath, "libduplicated.so");

        System.setProperty("java.library.path", systemLibPath.toString());
        PathClassLoader pathClassLoader = new PathClassLoader(applicationLibPath.toString(),
                applicationLibPath.toString(), getClass().getClassLoader());

        String path = pathClassLoader.findLibrary("duplicated");
        assertEquals(applicationLib.toString(), path);
    }

    private File makeTempFile(File directory, String name) throws IOException {
        directory.mkdirs();
        File result = new File(directory, name);
        FileOutputStream stream = new FileOutputStream(result);
        stream.close();
        assertTrue(result.exists());
        return result;
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
    }
}
