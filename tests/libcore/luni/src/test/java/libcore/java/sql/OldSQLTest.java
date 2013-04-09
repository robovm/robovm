/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.TestCase;

public abstract class OldSQLTest extends TestCase {
    static Connection conn;

    @Override public void setUp() throws Exception {
        getSQLiteConnection();
        createZoo();
    }

    protected File dbFile;

    protected void getSQLiteConnection() throws Exception {
        String tmp = System.getProperty("java.io.tmpdir");
        File tmpDir = new File(tmp);
        if (tmpDir.isDirectory()) {
            dbFile = File.createTempFile("sqliteTest", ".db", tmpDir);
            dbFile.deleteOnExit();
        } else {
            System.err.println("java.io.tmpdir does not exist");
        }

        Class.forName("SQLite.JDBCDriver").newInstance();
        conn = DriverManager.getConnection("jdbc:sqlite:/" + dbFile.getPath());
        assertNotNull("Connection created ", conn);
    }

    @Override public void tearDown() throws SQLException {
        Statement st = null;
        try {
            if (! conn.isClosed()) {
                st = conn.createStatement();
                st.execute("drop table if exists zoo");
            }
        } finally {
            try {
                if (st != null) {
                    st.close();
                    conn.close();
                }
            } catch(SQLException ee) {
                //ignore
            }
        }
    }

    public void createZoo() throws SQLException {
        String[] queries = {
                "create table zoo(id smallint,  name varchar(10), family varchar(10))",
                "insert into zoo values (1, 'Kesha', 'parrot')",
                "insert into zoo values (2, 'Yasha', 'sparrow')" };

        Statement st = null;
        try {
            st = conn.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.execute(queries[i]);
            }
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
             } catch (SQLException ee) {}
        }
    }

    public int getCount(ResultSet rs) {
        int count = 0;
        try {
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            fail("SQLException is thrown");
        }
        return count;
    }
}
