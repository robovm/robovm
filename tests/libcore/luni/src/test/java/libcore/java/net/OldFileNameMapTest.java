/*
 * Copyright (C) 2008 The Android Open Source Project
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

package libcore.java.net;

import junit.framework.TestCase;

import java.net.FileNameMap;
import java.net.URLConnection;

public class OldFileNameMapTest extends TestCase {

    public void test_getContentTypeFor() {
        String [] files = {"text", "txt", "htm", "html"};

        String [] mimeTypes = {"text/plain", "text/plain",
                "text/html", "text/html"};

        FileNameMap fileNameMap = URLConnection.getFileNameMap();

        for(int i = 0; i < files.length; i++) {
            String mimeType = fileNameMap.getContentTypeFor("test." + files[i]);
            assertEquals("getContentTypeFor returns incorrect MIME type for " +
                    files[i], mimeTypes[i], mimeType);
        }
    }
}
