/*
 * Copyright (C) 2007 The Android Open Source Project
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
import java.sql.SQLException;

/**
 * Tests the SQLite.JDBCDriver.
 */
public class OldJDBCDriverFunctionalTest extends AbstractSqlTest {
    private  File dbFile = null;
    private String connectionURL = "empty";

    @Override protected void tearDown() throws SQLException {
        super.tearDown();
        dbFile.delete();
    }

    @Override protected String getConnectionURL() {
        if (connectionURL.equals("empty")) {
            String tmp = System.getProperty("java.io.tmpdir");
            File tmpDir = new File(tmp);
            if (tmpDir.isDirectory()) {
                try {
                    dbFile = File.createTempFile("JDBCDriverFunctionalTest", ".db", tmpDir);
                } catch (IOException e) {
                    System.err.println("error creating temporary DB file.");
                }
                dbFile.deleteOnExit();
            } else {
                System.err.println("java.io.tmpdir does not exist");
            }

            connectionURL = "jdbc:sqlite:/" + dbFile.getPath();
        }

        return connectionURL;
    }

    @Override protected String getDriverClassName() {
        return "SQLite.JDBCDriver";
    }

    @Override protected int getTransactionIsolation() {
        return Connection.TRANSACTION_SERIALIZABLE;
    }
}
