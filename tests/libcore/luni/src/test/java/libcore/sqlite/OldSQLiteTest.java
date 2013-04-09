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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import junit.framework.TestCase;

public abstract class OldSQLiteTest extends TestCase {

    public static Connection conn;

    public static File dbFile = null;

    @Override public void setUp() throws Exception {
        String tmp = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(tmp);
        try {
            if (tmpDir.isDirectory()) {
                dbFile = File.createTempFile("sqliteTest", ".db", tmpDir);
                dbFile.deleteOnExit();
            } else {
                System.out.println("ctsdir does not exist");
            }

            Class.forName("SQLite.JDBCDriver").newInstance();

            if (!dbFile.exists()) {
                Logger.global.severe("DB file could not be created. Tests can not be executed.");
            } else {
                conn = DriverManager.getConnection("jdbc:sqlite:/" + dbFile.getPath());
            }
            assertNotNull("Error creating connection", conn);
        } catch (IOException e) {
            System.out.println("Problem creating test file in " + tmp);
        }
    }

    @Override public void tearDown() throws java.lang.Exception {
        if (!conn.isClosed()) {
            conn.close();
        }
        super.tearDown();
    }
}
