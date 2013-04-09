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

package libcore.sqlite;

import SQLite.Blob;
import SQLite.Database;
import SQLite.Exception;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import tests.support.Support_SQL;

public final class OldBlobTest extends OldSQLiteTest {

    private static Blob testBlob = null;

    private static Database db = null;

    public void setUp() throws java.lang.Exception {
        super.setUp();
        testBlob = new Blob();

        super.setUp();
        Support_SQL.loadDriver();
        db = new Database();
        db.open(dbFile.getPath(), 0);

        db.exec("create table B(id integer primary key, val blob)",null);
        db.exec("insert into B values(1, zeroblob(128))", null);
        db.exec("insert into B values(2, zeroblob(128))", null);
        db.exec("insert into B values(3, zeroblob(128))", null);

        // can not fill Blob with data at this point...
        /*
        File resources = Support_Resources.createTempFolder();
        BufferedReader r = null;
        try {
            Class c = Class.forName(this.getClass().getName());
            assertNotNull(c);
            file = Class.forName(this.getClass().getName())
                    .getResourceAsStream("/blob.c");
            r = new BufferedReader(new InputStreamReader(file));
        } catch (NullPointerException e) {
            fail("Should not throw NullPointerException reading file"
                    + e.getMessage());
        }
        OutputStream out = testBlob.getOutputStream();
        String s = null;
        while ((s = r.readLine()) != null) {
            out.write(r.readLine().getBytes());
        }
        out.flush();
        out.close();
        testBlob.close();
        */
    }

    @Override public void tearDown() throws java.lang.Exception {
        testBlob.close();
        super.tearDown();
    }

    /**
     * db.open_blob is not supported.
     */
    public void testBlob() throws Exception, IOException {
        byte[] b = new byte[4];
        byte[] b128 = new byte[128];
        for (int i = 0; i < b128.length; i++) {
        b128[i] = (byte) i;
        }
        Blob blob = db.open_blob(dbFile.getPath(), "B", "val", 1, true);
        try {

        OutputStream os = blob.getOutputStream();
        os.write(b128);
        os.close();

        InputStream is = blob.getInputStream();
        is.skip(96);
        assertEquals(4,is.read(b));
        is.close();
        } finally {
        blob.close();
        }
    }

    public void testGetInputStream() {
        InputStream in = testBlob.getInputStream();
        try {
            in.read();
            fail("Exception not thrown for invalid Blob.");
        } catch (Throwable e) {
            //ok
        }
    }

    public void testGetOutputStream() {
        OutputStream out = testBlob.getOutputStream();

        try {
           out.write(null);
           fail("Write operation unsupported");
        } catch (Throwable e) {
            assertEquals("Write operation unsupported", e.getMessage());
        }
    }

    public void testClose() {
        assertNotNull(testBlob);

        testBlob.close();
        // inputStream either null or some error occurs
        try {
            // TODO This does look a bit weird. Revisit later.
            assertNull(testBlob.getInputStream());
        } catch (Throwable e) {
            //ok
        }
    }
}
