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
package tests.java.sql;

import dalvik.annotation.KnownFailure;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import tests.support.DatabaseCreator;
import tests.support.Support_SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class DatabaseMetaDataTest extends TestCase {
    private static String VIEW_NAME = "myView";

    private static String CREATE_VIEW_QUERY = "CREATE VIEW " + VIEW_NAME
            + " AS SELECT * FROM " + DatabaseCreator.TEST_TABLE1;

    private static String DROP_VIEW_QUERY = "DROP VIEW " + VIEW_NAME;

    protected static Connection conn;

    protected static DatabaseMetaData meta;

    protected static Statement statement;

    protected static Statement statementForward;

    private static int id = 1;

    public void setUp() throws Exception {
        super.setUp();
        Support_SQL.loadDriver();
        try {
            conn = Support_SQL.getConnection();
            meta = conn.getMetaData();
            statement = conn.createStatement();
            statementForward = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            createTestTables();
        } catch (SQLException e) {
            System.out.println("Error in test setup: "+e.getMessage());
        }
    }

    protected void tearDown() throws Exception {
        try {
            conn = Support_SQL.getConnection();
            meta = conn.getMetaData();
            statement = conn.createStatement();
            deleteTestTables();
        } catch (SQLException e) {
            System.out.println("Error in teardown: "+e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        super.tearDown();
    }

            private void createTestTables() {
                try {
                    ResultSet userTab = meta.getTables(null, null, null, null);
                    while (userTab.next()) {
                        String tableName = userTab.getString("TABLE_NAME");
                        if (tableName.equals(DatabaseCreator.TEST_TABLE1)) {
                            statement.execute(DatabaseCreator.DROP_TABLE1);
                        } else if (tableName
                                .equals(DatabaseCreator.TEST_TABLE3)) {
                            statement.execute(DatabaseCreator.DROP_TABLE3);
                        } else if (tableName.equals(VIEW_NAME)) {
                            statement.execute(DROP_VIEW_QUERY);
                        }
                    }
                    userTab.close();
                    statement.execute(DatabaseCreator.CREATE_TABLE3);
                    statement.execute(DatabaseCreator.CREATE_TABLE1);
                    statement.execute(CREATE_VIEW_QUERY);
                    meta = conn.getMetaData();
                } catch (SQLException e) {
                    fail("Unexpected SQLException " + e.toString());
                }
            }

            private void deleteTestTables() {
                try {
                    statement.execute(DatabaseCreator.DROP_TABLE1);
                    statement.execute(DatabaseCreator.DROP_TABLE3);
                    statement.execute(DROP_VIEW_QUERY);
                } catch (SQLException e) {
                    fail("Unexpected SQLException " + e.toString());
                } finally {
                    try {
                    if (! conn.isClosed()) {
                        conn.close();
                    }
                    } catch (SQLException e) {

                    }
                }
            }
    /*
    public void setUp() {
        try {
            super.setUp();
            try {
                conn = Support_SQL.getConnection();
                statement = conn.createStatement();
                statementForward = conn.createStatement(
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);
                meta = conn.getMetaData();

                assertFalse(conn.isClosed());
            } catch (SQLException e) {
                fail("Unexpected SQLException " + e.toString());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    */

    /**
     * {@link java.sql.DatabaseMetaData #getBestRowIdentifier(java.lang.String,
     *        java.lang.String, java.lang.String, int, boolean) }
     */
    public void test_getBestRowIdentifierLjava_lang_StringLjava_lang_StringLjava_lang_StringIZ()
            throws SQLException {
        ResultSet result = statementForward.executeQuery("SELECT * FROM "
                + DatabaseCreator.TEST_TABLE1);

        //Updatable ResultSet not supported, converted to normal insert statement
        statementForward.executeUpdate("INSERT INTO " + DatabaseCreator.TEST_TABLE1
                + " (id, field1) VALUES( 1234567, 'test1');");
        /* not supported
        try {
         result.moveToInsertRow();
         result.updateInt("id", 1234567);
         result.updateString("field1", "test1");
         result.insertRow();
         } catch (SQLException e) {
         fail("Unexpected SQLException " + e.toString());
         }
         */


        result.close();

        ResultSet rs = meta.getBestRowIdentifier(null, null,
                DatabaseCreator.TEST_TABLE1, DatabaseMetaData.bestRowSession,
                true);
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 8, col);
        String[] columnNames = {
                "SCOPE", "COLUMN_NAME", "DATA_TYPE", "TYPE_NAME",
                "COLUMN_SIZE", "BUFFER_LENGTH", "DECIMAL_DIGITS",
                "PSEUDO_COLUMN"};
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
        assertEquals("Incorrect scope", DatabaseMetaData.bestRowSession, rs
                .getShort("SCOPE"));
        assertEquals("Incorrect column name", "_ROWID_", rs.getString("COLUMN_NAME"));
        assertEquals("Incorrect data type", java.sql.Types.INTEGER, rs.getInt("DATA_TYPE"));
        assertEquals("Incorrect type name", "INTEGER", rs.getString("TYPE_NAME"));
        rs.close();

     // Exception testing
        conn.close();

        try {
            meta.getColumns(null, null,
            DatabaseCreator.TEST_TABLE1, "%");
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData #getColumns(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String)
     *
     */
    @KnownFailure("Not supported : pattern with %")
    public void test_getColumnsArbitrary() throws SQLException {
        ResultSet setAllNull = null;
        ResultSet setMixed = null;
        ResultSet allArbitrary = null;
        String[] tablesName = {DatabaseCreator.TEST_TABLE1,
                DatabaseCreator.TEST_TABLE3};
        Arrays.sort(tablesName);
        int setSize = 0;
        try {
            allArbitrary = meta.getColumns("%","%","%","%");
            assertNotNull(allArbitrary);
            checkColumnsShape(allArbitrary);
            setSize = crossCheckGetColumnsAndResultSetMetaData(allArbitrary, false);
            assertEquals(6, setSize);

            setMixed = meta.getColumns(null, null,"%","%");
            assertNotNull(setMixed);
            checkColumnsShape(setMixed);
            setSize = crossCheckGetColumnsAndResultSetMetaData(setMixed, false);
            assertEquals(6, setSize);

        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Exception testing
        conn.close();

        try {
            meta.getColumns(null, null,
                    DatabaseCreator.TEST_TABLE1, "%");
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData #getColumns(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String)
     *
     */
    @KnownFailure("Not supported ops applied: test fails on arguments: '', '', '%', '%' ")
    public void test_getColumnsTableWithNoCatalogSchema() throws SQLException{

        try {
            ResultSet noSchemaTable = meta.getColumns("", "",
                    DatabaseCreator.TEST_TABLE1, "fkey");
            assertNotNull(noSchemaTable);
            noSchemaTable.last();
            int size = noSchemaTable.getRow();
            assertEquals(
                    "Does not support empty string as input parameter or Wildcard %",
                    1, size);



        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            ResultSet noSchemaTable = meta.getColumns("", "",
                    DatabaseCreator.TEST_TABLE1, "%");
            assertNotNull(noSchemaTable);
            noSchemaTable.last();
            int size = noSchemaTable.getRow();
            assertEquals(
                    "Does not support empty string as input parameter or Wildcard %",
                    5, size);



        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            ResultSet noSchemaTable = meta.getColumns("", "", "%", "%");
            assertNotNull(noSchemaTable);
            noSchemaTable.last();
            int size = noSchemaTable.getRow();
            assertEquals(
                    "Does not support double Wildcard '%' as input",
                    6, size);

        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Exception checking
        conn.close();

        try {
            meta.getColumns(null, null,
                    DatabaseCreator.TEST_TABLE1, "%");
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }



    /**
     * java.sql.DatabaseMetaData #getColumns(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String)
     *
     */
    @KnownFailure("Wildcard operator does not seem wo work correctly.")
    public void test_getColumnsSpecific() throws SQLException {
        String[] tablesName = {
                DatabaseCreator.TEST_TABLE1, DatabaseCreator.TEST_TABLE3};
        String[] fields = {"id", "field1", "field2", "field3", "fkey"};
        String[] nullable = {"YES", "NO",""};
        int[] nullableInt = {
                DatabaseMetaData.columnNoNulls,
                DatabaseMetaData.columnNullable,
                DatabaseMetaData.columnNullableUnknown};
        Arrays.sort(tablesName);
        Arrays.sort(fields);
        Arrays.sort(nullableInt);
        Arrays.sort(nullable);
        int countSingle = 0;
        int countAll1 = 0;
        int countAll2 = 0;

        try {
            ResultSet rs = meta.getColumns(null, null,
                    DatabaseCreator.TEST_TABLE1, "%");

            while (rs.next()) {
                assertTrue("Invalid table name", Arrays.binarySearch(
                        tablesName, rs.getString("TABLE_NAME")) > -1);
                assertTrue("Invalid field name", Arrays.binarySearch(fields, rs
                        .getString("COLUMN_NAME")) > -1);
                assertTrue("Invalid nullable value", Arrays.binarySearch(
                        nullable, rs.getString("IS_NULLABLE")) > -1);
                assertTrue("Invalid nullable code", Arrays.binarySearch(
                        nullableInt, rs.getInt("NULLABLE")) > -1);
                countSingle++;
            }
            assertEquals("Not all results are found", 5, countSingle);
            rs.close();

        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            ResultSet rs = meta.getColumns(null, null, "%"+DatabaseCreator.CREATE_TABLE1.substring(0, 3)+"%","%" );
            while (rs.next()) {
                assertTrue("Wrong table name", Arrays.binarySearch(tablesName,
                        rs.getString("TABLE_NAME")) > -1);
                countAll1++;
            }
            assertEquals("Not all results are found", 6, countAll1);
            rs.close();

        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            ResultSet rs = meta.getColumns(null, null, "%TEST_%", "%");

            while (rs.next()) {
                assertTrue("Wrong table name", Arrays.binarySearch(tablesName,
                        rs.getString("TABLE_NAME")) > -1);
                countAll2++;
            }
            assertEquals("Not all results are found", 6, countAll2);
            rs.close();

        } catch (SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

     // Exception checking
        conn.close();

        try {
            meta.getColumns(null, null,
                    DatabaseCreator.TEST_TABLE1, "%");
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }


    }



    /**
     * java.sql.DatabaseMetaData#getConnection()
     */
    public void test_getConnection() throws SQLException {
        assertEquals("Incorrect connection value", conn, meta.getConnection());

        // Exception checking
        conn.close();

        try {
            Connection con = meta.getConnection();
            assertTrue(con.isClosed());
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData #getCrossReference(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String,
     *        java.lang.String, java.lang.String)
     */
    @KnownFailure("(Ticket 91) Tables apply foreign key constraint. Catalogs not supported")
    public void test_getCrossReferenceLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        ResultSet rs = meta.getCrossReference(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE3, conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1);
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows do not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 14, col);
        String[] columnNames = { "PKTABLE_CAT", "PKTABLE_SCHEM",
                "PKTABLE_NAME", "PKCOLUMN_NAME", "FKTABLE_CAT",
                "FKTABLE_SCHEM", "FKTABLE_NAME", "FKCOLUMN_NAME", "KEY_SEQ",
                "UPDATE_RULE", "DELETE_RULE", "FK_NAME", "PK_NAME",
                "DEFERRABILITY" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
//      TODO getCatalog is not supported
        assertEquals("Incorrect primary key table catalog", conn.getCatalog(),
                rs.getString("PKTABLE_CAT"));
        assertEquals("Incorrect primary key table schema", "", rs
                .getString("PKTABLE_SCHEM"));
        assertEquals("Incorrect primary key table name",
                DatabaseCreator.TEST_TABLE3, rs.getString("PKTABLE_NAME"));
        assertEquals("Incorrect primary key column name", "fkey", rs
                .getString("PKCOLUMN_NAME"));
        // TODO getCatalog is not supported
        assertEquals("Incorrect foreign key table catalog", conn.getCatalog(),
                rs.getString("FKTABLE_CAT"));
        assertEquals("Incorrect foreign key table schema", "", rs
                .getString("FKTABLE_SCHEM"));
        assertEquals("Incorrect foreign key table name",
                DatabaseCreator.TEST_TABLE1, rs.getString("FKTABLE_NAME"));
        assertEquals("Incorrect foreign key column name", "fk", rs
                .getString("FKCOLUMN_NAME"));
        assertEquals("Incorrect sequence number within foreign key", 1, rs
                .getShort("KEY_SEQ"));
        assertEquals("Incorrect update rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("UPDATE_RULE"));
        assertEquals("Incorrect delete rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("DELETE_RULE"));
        assertNull("Incorrect foreign key name", rs.getString("FK_NAME"));
        assertNull("Incorrect primary key name", rs.getString("PK_NAME"));
        assertEquals("Incorrect deferrability",
                DatabaseMetaData.importedKeyNotDeferrable, rs
                        .getShort("DEFERRABILITY"));
        rs.close();

     // Exception checking
        conn.close();

        try {
            meta.getCrossReference(conn.getCatalog(), null,
                    DatabaseCreator.TEST_TABLE3, conn.getCatalog(), null,
                    DatabaseCreator.TEST_TABLE1);
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }

        // Exception checking
        conn.close();

        try {
            meta.getCrossReference(conn.getCatalog(), null,
                    DatabaseCreator.TEST_TABLE3, conn.getCatalog(), null,
                    DatabaseCreator.TEST_TABLE1);
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData#getDatabaseMajorVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getDatabaseMajorVersion() throws SQLException {
        assertTrue("Incorrdct database major version", meta
                .getDatabaseMajorVersion() >= 0);

        // Exception checking
        conn.close();

        try {
            meta.getDatabaseMajorVersion();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }

    }

    /**
     * java.sql.DatabaseMetaData#getDatabaseMinorVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getDatabaseMinorVersion() throws SQLException {
        assertTrue("Incorrect database minor version", meta
                .getDatabaseMinorVersion() >= 0);

     // Exception checking
        conn.close();

        try {
            meta.getDatabaseMinorVersion();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData#getDatabaseProductName()
     */
    @KnownFailure("Ticket 98")
    public void test_getDatabaseProductName() throws SQLException {
        assertTrue("Incorrect database product name", !"".equals(meta
                .getDatabaseProductName().trim()));

        // Exception checking
        conn.close();

        try {
            meta.getDatabaseProductName();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }

    }

    /**
     * java.sql.DatabaseMetaData#getDatabaseProductVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getDatabaseProductVersion() throws SQLException {
        assertTrue("Incorrect database product version", !"".equals(meta
                .getDatabaseProductVersion().trim()));
        // Exception checking
        conn.close();

        try {
            meta.getDatabaseProductVersion();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData#getDefaultTransactionIsolation()
     */
    @KnownFailure("Ticket 98")
    public void test_getDefaultTransactionIsolation() throws SQLException {
        int defaultLevel = meta.getDefaultTransactionIsolation();
        switch (defaultLevel) {
        case Connection.TRANSACTION_NONE:
        case Connection.TRANSACTION_READ_COMMITTED:
        case Connection.TRANSACTION_READ_UNCOMMITTED:
        case Connection.TRANSACTION_REPEATABLE_READ:
        case Connection.TRANSACTION_SERIALIZABLE:
            // these levels are OK
            break;
        default:
            fail("Incorrect value of default transaction isolation level");
        }

        // Exception checking
        conn.close();

        try {
            meta.getDefaultTransactionIsolation();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData#getDriverMajorVersion()
     */
    public void test_getDriverMajorVersion()  throws SQLException {
        assertTrue("Incorrect driver major version", meta
                .getDriverMajorVersion() >= 0);
    }

    /**
     * java.sql.DatabaseMetaData#getDriverMinorVersion()
     */
    public void test_getDriverMinorVersion() {
        assertTrue("Incorrect driver minor version", meta
                .getDriverMinorVersion() >= 0);
    }

    /**
     * java.sql.DatabaseMetaData#getDriverName()
     */
    @KnownFailure("Ticket 98")
    public void test_getDriverName() throws SQLException {
        String driverName = meta.getDriverName();
        assertTrue("Incorrect driver name", driverName.trim().startsWith(
                "SQLite"));

        // Exception checking
        conn.close();

        try {
            meta.getDriverName();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * java.sql.DatabaseMetaData#getDriverVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getDriverVersion() throws SQLException {
        assertTrue("Incorrect driver version", !"".equals(meta
                .getDriverVersion().trim()));

      //Exception checking
        conn.close();

         try {
             meta.getDriverVersion();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    @KnownFailure("Keys are not supported: Ticket 91")
    public void test_getImportedKeysLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        ResultSet rs = meta.getImportedKeys(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1);
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows do not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 14, col);
        String[] columnNames = { "PKTABLE_CAT", "PKTABLE_SCHEM",
                "PKTABLE_NAME", "PKCOLUMN_NAME", "FKTABLE_CAT",
                "FKTABLE_SCHEM", "FKTABLE_NAME", "FKCOLUMN_NAME", "KEY_SEQ",
                "UPDATE_RULE", "DELETE_RULE", "FK_NAME", "PK_NAME",
                "DEFERRABILITY" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
//      TODO getCatalog is not supported
        assertEquals("Incorrect primary key table catalog", conn.getCatalog(),
                rs.getString("PKTABLE_CAT"));
        assertEquals("Incorrect primary key table schema", "", rs
                .getString("PKTABLE_SCHEM"));
        assertEquals("Incorrect primary key table name",
                DatabaseCreator.TEST_TABLE3, rs.getString("PKTABLE_NAME"));
        assertEquals("Incorrect primary key column name", "fkey", rs
                .getString("PKCOLUMN_NAME"));
        assertEquals("Incorrect foreign key table catalog", conn.getCatalog(),
                rs.getString("FKTABLE_CAT"));
        assertEquals("Incorrect foreign key table schema", "", rs
                .getString("FKTABLE_SCHEM"));
        assertEquals("Incorrect foreign key table name",
                DatabaseCreator.TEST_TABLE1, rs.getString("FKTABLE_NAME"));
        assertEquals("Incorrect foreign key column name", "fk", rs
                .getString("FKCOLUMN_NAME"));
        assertEquals("Incorrect sequence number within foreign key", 1, rs
                .getShort("KEY_SEQ"));
        assertEquals("Incorrect update rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("UPDATE_RULE"));
        assertEquals("Incorrect delete rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("DELETE_RULE"));
 //       assertNotNull("Incorrect foreign key name", rs.getString("FK_NAME"));
        assertEquals("Incorrect primary key name", null, rs
                .getString("PK_NAME"));
        assertEquals("Incorrect deferrability",
                DatabaseMetaData.importedKeyNotDeferrable, rs
                        .getShort("DEFERRABILITY"));
        rs.close();

      //Exception checking
        conn.close();

         try {
             meta.getImportedKeys(conn.getCatalog(), null,
                     DatabaseCreator.TEST_TABLE1);
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getMaxCursorNameLength()
     */
    public void test_getMaxCursorNameLength() throws SQLException {
        int nameLength = meta.getMaxCursorNameLength();
        if (nameLength > 0) {
            try {
                statement.setCursorName(new String(new byte[nameLength + 1]));
                fail("Expected SQLException was not thrown");
            } catch (SQLException e) {
                // expected
            }
        } else if (nameLength < 0) {
            fail("Incorrect length of cursor name");
        }
    }

    /**
     * java.sql.DatabaseMetaData#getJDBCMinorVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getJDBCMinorVersion() throws SQLException {
        assertTrue("Incorrect JDBC minor version",
                meta.getJDBCMinorVersion() >= 0);

      //Exception checking
        conn.close();

         try {
             meta.getJDBCMinorVersion();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getJDBCMajorVersion()
     */
    @KnownFailure("Ticket 98")
    public void test_getJDBCMajorVersion() throws SQLException {
        assertTrue("Incorrect JDBC major version",
                meta.getJDBCMajorVersion() >= 0);

      //Exception checking
        conn.close();

         try {
             meta.getJDBCMajorVersion();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#getNumericFunctions()
     */
    @KnownFailure("Not supported feature, Ticket 98. Broken because "+
            "NUMERIC_FUNCTIONS not complete. When fixed change to @KnownFailure")
    public void test_getNumericFunctions() throws SQLException {
        escapedFunctions(NUMERIC_FUNCTIONS, meta.getNumericFunctions());


      //Exception checking
        conn.close();

         try {
             meta.getNumericFunctions();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData #getPrimaryKeys(java.lang.String,
     *        java.lang.String, java.lang.String)
     */
    @KnownFailure(" Ticket 91 : relies on not supported features: getCatalog, keys")
    public void test_getPrimaryKeysLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        ResultSet rs = meta.getPrimaryKeys(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1);
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 6, col);
        String[] columnNames = { "TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME",
                "COLUMN_NAME", "KEY_SEQ", "PK_NAME" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
        assertEquals("Incorrect table catalogue", conn.getCatalog(), rs
                .getString("TABLE_CAT").toLowerCase());
        assertEquals("Incorrect table schema", "", rs
                .getString("TABLE_SCHEM"));
        assertEquals("Incorrect table name", DatabaseCreator.TEST_TABLE1, rs
                .getString("TABLE_NAME").toLowerCase());
        assertEquals("Incorrect column name", "id", rs.getString("COLUMN_NAME")
                .toLowerCase());
        assertEquals("Incorrect sequence number", 1, rs.getShort("KEY_SEQ"));
        assertEquals("Incorrect primary key name", "primary", rs.getString(
                "PK_NAME").toLowerCase());
        rs.close();

      //Exception checking
        conn.close();

         try {
             meta.getPrimaryKeys(conn.getCatalog(), null,
                     DatabaseCreator.TEST_TABLE1);
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getResultSetHoldability()
     */
    @KnownFailure("Ticket 98")
    public void test_getResultSetHoldability() throws SQLException {
        int hdb = meta.getResultSetHoldability();
        switch (hdb) {
        case ResultSet.HOLD_CURSORS_OVER_COMMIT:
        case ResultSet.CLOSE_CURSORS_AT_COMMIT:
            // these holdabilities are OK
            break;
        default:
            fail("Incorrect value of holdability");
        }
        assertFalse("Incorrect result set holdability", meta
                .supportsResultSetHoldability(hdb));

      //Exception checking
        conn.close();

         try {
             meta.getResultSetHoldability();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getSQLKeywords()
     */
    @KnownFailure("Ticket 98")
    public void test_getSQLKeywords() throws SQLException {
        assertTrue("Incorrect SQL keywords", !"".equals(meta.getSQLKeywords()
                .trim()));

      //Exception checking
        conn.close();

         try {
             meta.getSQLKeywords();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getSQLStateType()
     */
    @KnownFailure("Ticket 98")
    public void test_getSQLStateType() throws SQLException {
        int type = meta.getSQLStateType();
        switch (type) {
        case DatabaseMetaData.sqlStateSQL99:
        case DatabaseMetaData.sqlStateXOpen:
            // these types are OK
            break;
        default:
            fail("Incorrect SQL state types");
        }


      //Exception checking
        conn.close();

         try {
             meta.getSQLStateType();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getSchemas()
     */
    @KnownFailure("Ticket 98")
    public void test_getSchemas() throws SQLException {
        ResultSet rs = meta.getSchemas();
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows do not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 1, col);
        String[] columnNames = { "TABLE_SCHEM", "TABLE_CATALOG" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
        rs.close();


      //Exception checking
        conn.close();

         try {
             meta.getSchemas();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getSearchStringEscape()
     */
    @KnownFailure("Ticket 98")
    public void test_getSearchStringEscape() throws SQLException {
        assertTrue("Incorrect search string escape", !"".equals(meta
                .getSearchStringEscape().trim()));

      //Exception checking
        conn.close();

         try {
             meta.getSearchStringEscape();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getStringFunctions()
     */
    @KnownFailure("not supported")
    public void test_getStringFunctions() throws SQLException {
        escapedFunctions(STRING_FUNCTIONS, meta.getStringFunctions());


      //Exception checking
        conn.close();

         try {
             meta.getStringFunctions();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }


    }


    /**
     * java.sql.DatabaseMetaData#getSystemFunctions()
     */
    @KnownFailure("not supported")
    public void test_getSystemFunctions() throws SQLException {
        escapedFunctions(SYSTEM_FUNCTIONS, meta.getSystemFunctions());


      //Exception checking
        conn.close();

         try {
             meta.getSystemFunctions();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#getTableTypes()
     */
    @KnownFailure("Ticket 98")
    public void test_getTableTypes() throws SQLException {
        String[] tableTypes = { "LOCAL TEMPORARY", "TABLE", "VIEW" };
        ResultSet rs = meta.getTableTypes();

        while (rs.next()) {
            assertTrue("Wrong table type", Arrays.binarySearch(tableTypes, rs
                    .getString("TABLE_TYPE")) > -1);
        }
        rs.close();


      //Exception checking
        conn.close();

         try {
             meta.getTableTypes();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData #getTables(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String[])
     */
    @KnownFailure("If no schema is associated: returns empty string where actually null be returned?. Ticket 98")
    public void test_getTablesLjava_lang_StringLjava_lang_StringLjava_lang_String$Ljava_lang_String()
            throws SQLException {
        String[] tablesName = {
                VIEW_NAME, DatabaseCreator.TEST_TABLE1,
                DatabaseCreator.TEST_TABLE3};
        String[] tablesType = {"TABLE", "VIEW"};
        Arrays.sort(tablesName);
        Arrays.sort(tablesType);

        // case 1. get all tables. There are two tables and one view in the
        // database
        ResultSet rs = meta.getTables(null, null, null, null);
        while (rs.next()) {
            assertTrue("Wrong table name", Arrays.binarySearch(tablesName, rs
                    .getString("TABLE_NAME")) > -1);
        //No Schema associated
            assertNull("Wrong table schema: "+rs.getString("TABLE_SCHEM"), rs.getString("TABLE_SCHEM"));
            assertTrue("Wrong table type", Arrays.binarySearch(tablesType, rs
                    .getString("TABLE_TYPE")) > -1);
            assertEquals("Wrong parameter REMARKS", "", rs.getString("REMARKS"));
        }
        rs.close();

        // case 2. get tables with specified types. There are no tables of such
        // types
        rs = meta.getTables(conn.getCatalog(), null, null, new String[] {
                "SYSTEM TABLE", "LOCAL TEMPORARY" });
        assertFalse("Some tables exist", rs.next());
        rs.close();

        // case 3. get tables with specified types. There is a table of such
        // types
        rs = meta.getTables(conn.getCatalog(), null, null, new String[] {
                "VIEW", "LOCAL TEMPORARY" });

        assertTrue("No tables exist", rs.next());
        assertEquals("Wrong table name", VIEW_NAME, rs.getString("TABLE_NAME"));
        assertNull("Wrong table schema: "+rs.getString("TABLE_SCHEM"), rs.getString("TABLE_SCHEM"));
        assertEquals("Wrong table type", "VIEW", rs.getString("TABLE_TYPE"));
        assertEquals("Wrong parameter REMARKS", "", rs.getString("REMARKS"));
        assertFalse("Wrong size of result set", rs.next());
        assertFalse("Some tables exist", rs.next());
        rs.close();

        // case 4. get all tables using tables pattern.
        // There are two tables and one view in the database
        rs = meta.getTables(null, null, "%", null);

        while (rs.next()) {
            assertTrue("Wrong table name", Arrays.binarySearch(tablesName, rs
                    .getString("TABLE_NAME")) > -1);
            assertNull("Wrong table schema ", rs.getString("TABLE_SCHEM"));
            assertTrue("Wrong table type", Arrays.binarySearch(tablesType, rs
                    .getString("TABLE_TYPE")) > -1);
            assertEquals("Wrong parameter REMARKS", "", rs.getString("REMARKS"));
        }
        rs.close();


      //Exception checking
        conn.close();

         try {
             meta.getTables(null, null, null, null);
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#getTimeDateFunctions()
     */
    @KnownFailure("not supported")
    public void test_getTimeDateFunctions() throws SQLException {

        escapedFunctions(TIMEDATE_FUNCTIONS, meta.getTimeDateFunctions());


      //Exception checking
        conn.close();

         try {
             meta.getTimeDateFunctions();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getTypeInfo()
     */
    @KnownFailure("not supported")
    public void test_getTypeInfo() throws SQLException {
        insertNewRecord();

        ResultSet rs = meta.getTypeInfo();

        final String[] names = { "TYPE_NAME", "DATA_TYPE", "PRECISION",
                "LITERAL_PREFIX", "LITERAL_SUFFIX", "CREATE_PARAMS",
                "NULLABLE", "CASE_SENSITIVE", "SEARCHABLE",
                "UNSIGNED_ATTRIBUTE", "FIXED_PREC_SCALE", "AUTO_INCREMENT",
                "LOCAL_TYPE_NAME", "MINIMUM_SCALE", "MAXIMUM_SCALE",
                "SQL_DATA_TYPE", "SQL_DATETIME_SUB", "NUM_PREC_RADIX" };
        Arrays.sort(names);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            assertTrue("wrong column was return", Arrays.binarySearch(names, rs
                    .getMetaData().getColumnName(i + 1)) > -1);
        }

        int[] types = { Types.ARRAY, Types.BIGINT, Types.BINARY, Types.BIT,
                Types.BLOB, Types.BOOLEAN, Types.CHAR, Types.CLOB,
                Types.DATALINK, Types.DATE, Types.DECIMAL, Types.DISTINCT,
                Types.DOUBLE, Types.FLOAT, Types.INTEGER, Types.JAVA_OBJECT,
                Types.LONGVARBINARY, Types.LONGVARCHAR, Types.NULL,
                Types.NUMERIC, Types.OTHER, Types.REAL, Types.REF,
                Types.SMALLINT, Types.STRUCT, Types.TIME, Types.TIMESTAMP,
                Types.TINYINT, Types.VARBINARY, Types.VARCHAR };
        Arrays.sort(types);

        while (rs.next()) {
            assertTrue("wrong type was return ", Arrays.binarySearch(types, rs
                    .getInt("DATA_TYPE")) > -1);
        }
        rs.close();

      //Exception checking
        conn.close();

         try {
             meta.getTypeInfo();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#getURL()
     */
    @KnownFailure("Ticket 98")
    public void test_getURL() throws SQLException {
        assertEquals("Wrong url", Support_SQL.sqlUrl, meta.getURL());

      //Exception checking
        conn.close();

         try {
             meta.getURL();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    @KnownFailure("Ticket 98")
    public void s() throws SQLException {
      assertEquals("Wrong user name", Support_SQL.sqlUser, meta.getUserName());

      //Exception checking
        conn.close();

         try {
             meta.getUserName();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#insertsAreDetected(int)
     */
    @KnownFailure("Ticket 98")
    public void test_insertsAreDetectedI() throws SQLException {
        assertFalse(
                "visible row insert can be detected for TYPE_FORWARD_ONLY type",
                meta.insertsAreDetected(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse(
                "visible row insert can be detected for TYPE_SCROLL_INSENSITIVE type",
                meta.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse(
                "visible row insert can be detected for TYPE_SCROLL_SENSITIVE type",
                meta.insertsAreDetected(ResultSet.TYPE_SCROLL_SENSITIVE));


      //Exception checking
        conn.close();

         try {
             meta.insertsAreDetected(ResultSet.TYPE_SCROLL_SENSITIVE);
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#isReadOnly()
     */
    @KnownFailure("Ticket 98")
    public void test_isReadOnly() throws SQLException {
        assertFalse("database is not read-only", meta.isReadOnly());


      //Exception checking
        conn.close();

         try {
             meta.isReadOnly();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#othersDeletesAreVisible(int)
     */
    @KnownFailure("Ticket 98")
    public void test_othersDeletesAreVisibleI() throws SQLException {
        assertFalse(
                "deletes made by others are visible for TYPE_FORWARD_ONLY type",
                meta.othersDeletesAreVisible(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse(
                "deletes made by others are visible for TYPE_SCROLL_INSENSITIVE type",
                meta.othersDeletesAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse(
                "deletes made by others are visible for TYPE_SCROLL_SENSITIVE type",
                meta.othersDeletesAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));


      //Exception checking
        conn.close();

         try {
             assertFalse("inserts made by others are visible for unknown type", meta
                     .othersDeletesAreVisible(ResultSet.CONCUR_READ_ONLY));
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#othersInsertsAreVisible(int)
     */
    @KnownFailure("Ticket 98")
    public void test_othersInsertsAreVisibleI() throws SQLException {
        assertFalse(
                "inserts made by others are visible for TYPE_FORWARD_ONLY type",
                meta.othersInsertsAreVisible(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse(
                "inserts made by others are visible for TYPE_SCROLL_INSENSITIVE type",
                meta.othersInsertsAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse(
                "inserts made by others are visible for TYPE_SCROLL_SENSITIVE type",
                meta.othersInsertsAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));


      //Exception checking
        conn.close();

         try {
             assertFalse("inserts made by others are visible for unknown type", meta
                     .othersInsertsAreVisible(ResultSet.CONCUR_READ_ONLY));
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#othersUpdatesAreVisible(int)
     */
    @KnownFailure("Ticket 98")
    public void test_othersUpdatesAreVisibleI() throws SQLException {
        assertFalse(
                "updates made by others are visible for TYPE_FORWARD_ONLY type",
                meta.othersUpdatesAreVisible(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse(
                "updates made by others are visible for TYPE_SCROLL_INSENSITIVE type",
                meta.othersUpdatesAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse(
                "updates made by others are visible for TYPE_SCROLL_SENSITIVE type",
                meta.othersUpdatesAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));

      //Exception checking

         try {
             assertFalse("updates made by others are visible for unknown type", meta
                     .othersUpdatesAreVisible(ResultSet.CONCUR_READ_ONLY));
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#storesMixedCaseQuotedIdentifiers()
     */
    public void test_storesMixedCaseQuotedIdentifiers() throws SQLException {
        String quote = meta.getIdentifierQuoteString();



        insertNewRecord();

        String selectQuery = "SELECT " + quote + "fieLD1" + quote + " FROM "
                + DatabaseCreator.TEST_TABLE1;

        try {
            statement.executeQuery(selectQuery);
            if (!meta.storesMixedCaseIdentifiers()) {
                fail("mixed case is supported");
            }
        } catch (SQLException e) {
            if (meta.storesMixedCaseQuotedIdentifiers()) {
                fail("quoted case is not supported");
            }
        }

        //Exception checking
        /*
        conn.close();

         try {
             meta.storesMixedCaseIdentifiers();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

         conn.close();

         try {
             meta.storesMixedCaseQuotedIdentifiers();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    @KnownFailure("Ticket 98")
    public void testGetIdentifierQuoteString() throws SQLException {
       assertNotNull(
               meta.getIdentifierQuoteString()
               );

       //Exception test
       /*
       conn.close();
       try {
           meta.getIdentifierQuoteString();
           fail("Should throw exception");
       } catch (SQLException e) {
           //ok
       }
       */

    }


    /**
     * java.sql.DatabaseMetaData#supportsColumnAliasing()
     */
    @KnownFailure("not supported. SQLException checking test fails")
    public void test_supportsColumnAliasing() throws SQLException {
        insertNewRecord();

        String alias = "FIELD3";
        String selectQuery = "SELECT field1 AS " + alias + " FROM "
                + DatabaseCreator.TEST_TABLE1;
        ResultSet rs = statement.executeQuery(selectQuery);
        ResultSetMetaData rsmd = rs.getMetaData();

        if (meta.supportsColumnAliasing()) {
            // supports aliasing
            assertEquals("Wrong count of columns", 1, rsmd.getColumnCount());
            assertEquals("Aliasing is not supported", alias, rsmd
                    .getColumnLabel(1));
        } else {
            // doesn't support aliasing
            assertEquals("Aliasing is supported", 0, rsmd.getColumnCount());
        }
        rs.close();

      //Exception checking
        conn.close();

         try {
             meta.supportsColumnAliasing();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#supportsExpressionsInOrderBy()
     */
    @KnownFailure("exception test fails")
    public void test_supportsExpressionsInOrderBy() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT * FROM " + DatabaseCreator.TEST_TABLE1
                + " ORDER BY id + field3";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsExpressionsInOrderBy()) {
                fail("Expressions in order by are supported");
            }
        } catch (SQLException e) {
            if (meta.supportsExpressionsInOrderBy()) {
                fail("Expressions in order by are not supported");
            }
        }

      //Exception checking
        conn.close();

         try {
             meta.supportsExpressionsInOrderBy();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#supportsGroupBy()
     */
    @KnownFailure("exception test fails")
    public void test_supportsGroupBy() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT * FROM " + DatabaseCreator.TEST_TABLE1
                + " GROUP BY field3";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsGroupBy()) {
                fail("group by are supported");
            }
        } catch (SQLException e) {
            if (meta.supportsGroupBy()) {
                fail("group by are not supported");
            }
        }

      //Exception checking
        conn.close();

         try {
             meta.supportsGroupBy();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }


    /**
     * java.sql.DatabaseMetaData#supportsGroupByUnrelated()
     */
    @KnownFailure("exception test fails")
    public void test_supportsGroupByUnrelated() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT field1, field2 FROM "
                + DatabaseCreator.TEST_TABLE1 + " GROUP BY field3";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsGroupByUnrelated()) {
                fail("unrelated columns in group by are supported");
            }
        } catch (SQLException e) {
            if (meta.supportsGroupByUnrelated()) {
                fail("unrelated columns in group by are not supported");
            }
        }

        //Exception checking
        conn.close();

         try {
             meta.supportsGroupByUnrelated();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsNonNullableColumns()
     */
    @KnownFailure("Ticket 98")
    public void test_supportsNonNullableColumns() throws SQLException {
        assertTrue(
                "columns in this database may not be defined as non-nullable",
                meta.supportsNonNullableColumns());
        statementForward.execute("create table companies(id integer not null);");
        statementForward.execute("drop table companies");


      //Exception checking
        conn.close();

         try {
             meta.supportsNonNullableColumns();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsOrderByUnrelated()
     */
    @KnownFailure("exception test fails")
    public void test_supportsOrderByUnrelated() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT field1, field2 FROM "
                + DatabaseCreator.TEST_TABLE1 + " ORDER BY id + field3";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsOrderByUnrelated()) {
                fail("unrelated columns in order by are supported");
            }
        } catch (SQLException e) {
            if (meta.supportsOrderByUnrelated()) {
                fail("unrelated columns in order by are not supported");
            }
        }

      //Exception checking
        conn.close();

         try {
             meta.supportsOrderByUnrelated();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsSelectForUpdate()
     */
    @KnownFailure("exception test fails")
    public void test_supportsSelectForUpdate() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT field1 FROM "
                + DatabaseCreator.TEST_TABLE1 + " FOR UPDATE";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsSelectForUpdate()) {
                fail("select for update are supported");
            }
        } catch (SQLException e) {
            if (!meta.supportsSelectForUpdate()) {
                fail("select for update are not supported");
            }
        }


      //Exception checking
        conn.close();

         try {
             meta.supportsSelectForUpdate();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsSubqueriesInExists()
     */
    @KnownFailure("exception test fails")
    public void test_supportsSubqueriesInExists() throws SQLException {
        insertNewRecord();

        String selectQuery = "SELECT field1 FROM "
                + DatabaseCreator.TEST_TABLE1
                + " WHERE EXISTS(SELECT field2 FROM "
                + DatabaseCreator.TEST_TABLE1 + ")";

        try {
            statement.executeQuery(selectQuery);
            if (!meta.supportsSubqueriesInExists()) {
                fail("Subqueries in exists are supported");
            }
        } catch (SQLException e) {
            if (meta.supportsSubqueriesInExists()) {
                fail("Subqueries in exists are not supported");
            }
        }


      //Exception checking
        conn.close();

         try {
             meta.supportsSubqueriesInExists();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsTableCorrelationNames()
     */
    @KnownFailure("exception test fails")
    public void test_supportsTableCorrelationNames() throws SQLException {

        insertNewRecord();
        assertFalse(conn.isClosed());

        String corelationName = "TABLE_NAME";
        String selectQuery = "SELECT * FROM " + DatabaseCreator.TEST_TABLE1
                + " AS " + corelationName;
        ResultSet rs = statementForward.executeQuery(selectQuery);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numOfColumn = rsmd.getColumnCount();

        for (int i = 0; i < numOfColumn; i++) {
            if (meta.supportsTableCorrelationNames()) {
                assertEquals("Corelation names is now supported",
                        corelationName, rsmd.getTableName(i + 1));
            } else {
                assertEquals("Corelation names is supported",
                        DatabaseCreator.TEST_TABLE1, rsmd.getTableName(i + 1));
            }
        }


      //Exception checking
        conn.close();

         try {
             meta.supportsTableCorrelationNames();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }

    }

    /**
     * java.sql.DatabaseMetaData#supportsTransactionIsolationLevel(int)
     */
    public void test_supportsTransactionIsolationLevelI() throws SQLException {
        assertFalse("database supports TRANSACTION_NONE isolation level", meta
                .supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE));
        // TODO only Connection.TRANSACTION_SERIALIZABLE is supported
//        assertTrue(
//                "database doesn't supports TRANSACTION_READ_COMMITTED isolation level",
//                meta
//                        .supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED));
//        assertTrue(
//                "database doesn't supports TRANSACTION_READ_UNCOMMITTED isolation level",
//                meta
//                        .supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
//        assertTrue(
//               "database doesn't supports TRANSACTION_REPEATABLE_READ isolation level",
//                meta
//                        .supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
        assertTrue(
                "database doesn't supports TRANSACTION_SERIALIZABLE isolation level",
                meta
                        .supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));


      //Exception checking

         try {
             assertFalse("database supports unknown isolation level", meta
                     .supportsTransactionIsolationLevel(Integer.MAX_VALUE));;
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#updatesAreDetected(int)
     */
    public void test_updatesAreDetectedI() throws SQLException {
        assertFalse(
                "visible row update can be detected for TYPE_FORWARD_ONLY type",
                meta.updatesAreDetected(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse(
                "visible row update can be detected for TYPE_SCROLL_INSENSITIVE type",
                meta.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse(
                "visible row update can be detected for TYPE_SCROLL_SENSITIVE type",
                meta.updatesAreDetected(ResultSet.TYPE_SCROLL_SENSITIVE));
        assertFalse("visible row update can be detected for unknown type", meta
                .updatesAreDetected(100));

        //Exception checking
       conn.close();

        try {
            meta.updatesAreDetected(ResultSet.CLOSE_CURSORS_AT_COMMIT);
            assertFalse("visible row update can be detected for unknown type", meta
                    .updatesAreDetected(ResultSet.CLOSE_CURSORS_AT_COMMIT));

        } catch (SQLException e) {
            //ok
        }
    }


    protected static void insertNewRecord() throws SQLException {
        if (conn.isClosed()) {
            System.out.println("DatabaseMetaDataTest.insertNewRecord() : closed");
        }

        String insertQuery = "INSERT INTO " + DatabaseCreator.TEST_TABLE1
                + " (id, field1, field2, field3) VALUES(" + id + ", '"
                + "value" + id + "', " + id + ", " + id + ")";
        id++;
        statement.execute(insertQuery);
    }

    //BEGIN APACHE-DERBY

    /**
     * Test Method from Apache Derby Project
     * Class
     * org.apache.derbyTesting.functionTests.tests.jdbcapi.DatabaseMetaDataTest
     *
     * Compare a ResultSet from getColumns() with ResultSetMetaData returned
     * from a SELECT * against the table. This method handles situations where a
     * full set of the columns are in the ResultSet. The first action is to call
     * rs.next(). The ResultSet will be closed by this method.
     *
     * @param rs
     *            resultset to crossCheck
     * @param partial
     *            used to indicate if ordinal position should get checked
     * @return the number of rows in the resultSet
     * @throws SQLException
     */
    private int crossCheckGetColumnsAndResultSetMetaData(ResultSet rs,
            boolean partial)
    throws SQLException
    {
        Statement s = conn.createStatement();
        while (rs.next())
        {
            String schema = rs.getString("TABLE_SCHEM");
            String table = rs.getString("TABLE_NAME");

            ResultSet rst = s.executeQuery(
                "SELECT * FROM " + schema+"."+table);
            ResultSetMetaData rsmdt = rst.getMetaData();


            for (int col = 1; col <= rsmdt.getColumnCount() ; col++)
            {
                if (!partial) {
                    if (col != 1)
                        assertTrue(rs.next());

                    assertEquals("ORDINAL_POSITION",
                            col, rs.getInt("ORDINAL_POSITION"));
                }

                assertEquals("TABLE_CAT",
                        "", rs.getString("TABLE_CAT"));
                assertEquals("TABLE_SCHEM",
                        schema, rs.getString("TABLE_SCHEM"));
                assertEquals("TABLE_NAME",
                        table, rs.getString("TABLE_NAME"));

                crossCheckGetColumnRowAndResultSetMetaData(rs, rsmdt);
                if (partial)
                    break;

            }
            rst.close();


        }
        int count = rs.getRow();
        rs.close();
        s.close();
        return count;
    }

    /**
     * * Test Method from Apache Derby Project
     * Class
     * org.apache.derbyTesting.functionTests.tests.jdbcapi.DatabaseMetaDataTest
     *
     * Cross check a single row from getColumns() with ResultSetMetaData
     * for a SELECT * from the same table.
     * @param rs ResultSet from getColumns already positioned on the row.
     * @param rsmdt ResultSetMetaData for the SELECT *
     * @param odbc 0 for JDBC call, 1 for ODBC. Needed to allow for difference
     *    in using BUFFER_LENGTH (ODBC) or no(JDBC).
     * @throws SQLException
     */
    public static void crossCheckGetColumnRowAndResultSetMetaData(
            ResultSet rs, ResultSetMetaData rsmdt)
        throws SQLException
    {
        int col = rs.getInt("ORDINAL_POSITION");

        assertEquals("RSMD.getCatalogName",
                rsmdt.getCatalogName(col), rs.getString("TABLE_CAT"));
        assertEquals("RSMD.getSchemaName",
                rsmdt.getSchemaName(col), rs.getString("TABLE_SCHEM"));
        assertEquals("RSMD.getTableName",
                rsmdt.getTableName(col), rs.getString("TABLE_NAME"));

        assertEquals("COLUMN_NAME",
                rsmdt.getColumnName(col), rs.getString("COLUMN_NAME"));

        // DERBY-2285 BOOLEAN columns appear different on
        // network client.
        // meta returns BOOLEAN
        // RSMD returns SMALLINT
        int metaColumnType = rs.getInt("DATA_TYPE");
        if (metaColumnType == Types.BOOLEAN )
        {
            assertEquals("TYPE_NAME",
                    "BOOLEAN", rs.getString("TYPE_NAME"));
            assertEquals("TYPE_NAME",
                    "SMALLINT", rsmdt.getColumnTypeName(col));

            assertEquals("DATA_TYPE",
                    Types.SMALLINT, rsmdt.getColumnType(col));
        }
        else if (metaColumnType == Types.JAVA_OBJECT)
        {
            // meta returns JAVA_OBJECT
            // RSMD returns LONGVARBINARY!
            assertEquals("DATA_TYPE",
                    Types.LONGVARBINARY, rsmdt.getColumnType(col));
        }
        else if (metaColumnType == Types.VARBINARY )
        {
            // meta returns different type name to RSMD
            assertEquals("DATA_TYPE",
                    Types.VARBINARY, rsmdt.getColumnType(col));
        }
        else if (metaColumnType == Types.BINARY )
        {
            // meta returns different type name to RSMD
            assertEquals("DATA_TYPE",
                    Types.BINARY, rsmdt.getColumnType(col));
        }
        else if (metaColumnType == Types.NUMERIC )
        {
            // DERBY-584 inconsistency in numeric & decimal
            assertEquals("DATA_TYPE",
                    Types.DECIMAL, rsmdt.getColumnType(col));

            assertEquals("TYPE_NAME",
                    "DECIMAL", rsmdt.getColumnTypeName(col));

            assertEquals("TYPE_NAME",
                    "NUMERIC", rs.getString("TYPE_NAME"));
        }
        else
        {
            assertEquals("DATA_TYPE",
                rsmdt.getColumnType(col), rs.getInt("DATA_TYPE"));
            assertEquals("TYPE_NAME",
                rsmdt.getColumnTypeName(col), rs.getString("TYPE_NAME"));
        }

        /*
        if (metaColumnType != Types.JAVA_OBJECT) {
        System.out.println("TYPE " + rs.getInt("DATA_TYPE"));
        System.out.println(JDBC.escape(schema, table) + " " + rs.getString("COLUMN_NAME"));
        assertEquals("COLUMN_SIZE",
                rsmdt.getPrecision(col), rs.getInt("COLUMN_SIZE"));
        }
        */

        /*
        assertEquals("DECIMAL_DIGITS",
                rsmdt.getScale(col), rs.getInt("DECIMAL_DIGITS"));
        */

        // This assumes the constants defined by meta and ResultSet
        // for nullability are equal. They are by inspection
        // and since they are static final and part of a defined
        // api by definition they cannot change. We also
        // check statically this is true in the testConstants fixture.
        assertEquals("NULLABLE",
                rsmdt.isNullable(col), rs.getInt("NULLABLE"));

        // REMARKS set to empty string by Derby
        assertEquals("REMARKS", "", rs.getString("REMARKS"));


        // IS_NULLABLE
        switch (rsmdt.isNullable(col))
        {
        case ResultSetMetaData.columnNoNulls:
            assertEquals("IS_NULLABLE", "NO", rs.getString("IS_NULLABLE"));
            break;
        case ResultSetMetaData.columnNullable:
            assertEquals("IS_NULLABLE", "YES", rs.getString("IS_NULLABLE"));
            break;
        case ResultSetMetaData.columnNullableUnknown:
            assertEquals("IS_NULLABLE", "", rs.getString("IS_NULLABLE"));
            break;
        default:
            fail("invalid return from rsmdt.isNullable(col)");
        }

        // SCOPE not supported
        assertNull("SCOPE_CATLOG", rs.getString("SCOPE_CATLOG"));
        assertNull("SCOPE_SCHEMA", rs.getString("SCOPE_SCHEMA"));
        assertNull("SCOPE_TABLE", rs.getString("SCOPE_TABLE"));

        // DISTINCT not supported
        assertEquals("SOURCE_DATA_TYPE", 0, rs.getShort("SOURCE_DATA_TYPE"));
        assertTrue(rs.wasNull());

        // IS_AUTOINCREMENT added in JDBC 4.0
       assertEquals("IS_AUTOINCREMENT",
               rsmdt.isAutoIncrement(col) ? "YES" : "NO",
               rs.getString("IS_AUTOINCREMENT"));
       assertFalse(rs.wasNull());
    }

    /*
     * Check the shape of the ResultSet from any getColumns call.
     */
    private void checkColumnsShape(ResultSet rs) throws SQLException {
        int[] columnTypes = new int[] {
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.SMALLINT, Types.VARCHAR, Types.INTEGER, Types.INTEGER,
                Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
                Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER,
                Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.SMALLINT, Types.VARCHAR};

        assertMetaDataResultSet(rs, new String[] {
                "TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME",
                "DATA_TYPE", "TYPE_NAME", "COLUMN_SIZE", "BUFFER_LENGTH",
                "DECIMAL_DIGITS", "NUM_PREC_RADIX", "NULLABLE", "REMARKS",
                "COLUMN_DEF", "SQL_DATA_TYPE", "SQL_DATETIME_SUB",
                "CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE",
                "SCOPE_CATLOG", "SCOPE_SCHEMA", "SCOPE_TABLE",
                "SOURCE_DATA_TYPE", "IS_AUTOINCREMENT"}, columnTypes, null);

    }

    public static void assertMetaDataResultSet(ResultSet rs,
            String[] columnNames, int[] columnTypes,
            boolean[] nullability) throws SQLException
    {
     // see ResultSetGetterTest, getType() -> this test fails currently
        assertEquals(ResultSet.TYPE_FORWARD_ONLY, rs.getType());
        assertEquals(ResultSet.CONCUR_READ_ONLY, rs.getConcurrency());

        if (columnNames != null)
            assertColumnNames(rs, columnNames);
        if (columnTypes != null)
            assertColumnTypes(rs, columnTypes);
        if (nullability != null)
            assertNullability(rs, nullability);
    }

    /**
     * * Test Method from Apache Derby Project
     *   Class
     * org.apache.derbyTesting.functionTests.tests.jdbcapi.DatabaseMetaDataTest
     *
     * Takes a result set and an array of expected colum names (as
     * Strings)  and asserts that the column names in the result
     * set metadata match the number, order, and names of those
     * in the array.
     *
     * @param rs ResultSet for which we're checking column names.
     * @param expectedColNames Array of expected column names.
     */
    public static void assertColumnNames(ResultSet rs,
        String [] expectedColNames) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int actualCols = rsmd.getColumnCount();

        for (int i = 0; i < actualCols; i++)
        {
        assertEquals("Column names do not match:",
                expectedColNames[i], rsmd.getColumnName(i+1));
        }

        assertEquals("Unexpected column count:",
        expectedColNames.length, rsmd.getColumnCount());
    }

    /**
     * Test Method from Apache Derby Project
     * Class
     * org.apache.derbyTesting.functionTests.tests.jdbcapi.DatabaseMetaDataTest
     *
     * Takes a result set and an array of expected column types
     * from java.sql.Types
     * and asserts that the column types in the result
     * set metadata match the number, order, and names of those
     * in the array.
     *
     * No length information for variable length types
     * can be passed. For ResultSets from JDBC DatabaseMetaData
     * the specification only indicates the types of the
     * columns, not the length.
     *
     * @param rs ResultSet for which we're checking column names.
     * @param expectedTypes Array of expected column types.
     */
    public static void assertColumnTypes(ResultSet rs,
        int[] expectedTypes) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int actualCols = rsmd.getColumnCount();

        assertEquals("Unexpected column count:",
                expectedTypes.length, rsmd.getColumnCount());

        for (int i = 0; i < actualCols; i++)
        {
       assertEquals("Column types do not match for column " + (i+1),
                    expectedTypes[i], rsmd.getColumnType(i+1));
        }
    }

    /**
     * Check the nullability of the column definitions for
     * the ResultSet matches the expected values.
     * @param rs
     * @param nullability
     * @throws SQLException
     */
    public static void assertNullability(ResultSet rs,
            boolean[] nullability) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int actualCols = rsmd.getColumnCount();

        assertEquals("Unexpected column count:",
                nullability.length, rsmd.getColumnCount());

        for (int i = 0; i < actualCols; i++)
        {
            int expected = nullability[i] ?
               ResultSetMetaData.columnNullable : ResultSetMetaData.columnNoNulls;
       assertEquals("Column nullability do not match for column " + (i+1),
                    expected, rsmd.isNullable(i+1));
        }
    }

    //BEGIN Apache Derby DatabaseMetaDataTest

     /*
      * Escaped function testing TODO complete this list
      */
     private static final String[][] NUMERIC_FUNCTIONS = {
             // Section C.1 JDBC 3.0 spec.
             {"ABS", "-25.67"},

//             {"ACOS", "0.0707"}, {"ASIN", "0.997"},
//             {"ATAN", "14.10"}, {"ATAN2", "0.56", "1.2"}, {"CEILING", "3.45"},
//             {"COS", "1.2"}, {"COT", "3.4"}, {"DEGREES", "2.1"}, {"EXP", "2.3"},
//             {"FLOOR", "3.22"}, {"LOG", "34.1"}, {"LOG10", "18.7"},
//             {"MOD", "124", "7"}, {"PI"}, {"POWER", "2", "3"},
//             {"RADIANS", "54"}, {"RAND", "17"},

             {"ROUND", "345.345", "1"}

//             {"SIGN", "-34"}, {"SIN", "0.32"}, {"SQRT", "6.22"},
//             {"TAN", "0.57",}, {"TRUNCATE", "345.395", "1"}

             };

     private static final String[][] TIMEDATE_FUNCTIONS = {
             // Section C.3 JDBC 3.0 spec.

             {"date","'now'"}

             //TODO Complete list

     };

     private static final String[][] SYSTEM_FUNCTIONS = {
     // Section C.4 JDBC 3.0 spec.
             {"IFNULL", "'this'", "'that'"}, {"USER"}
             };

     /*
      * TODO complete or check this list
      */
     private static final String[][] STRING_FUNCTIONS = {
             // Section C.2 JDBC 3.0 spec.
//             {"ASCII", "'Yellow'"}, {"CHAR", "65"},
//             {"CONCAT", "'hello'", "'there'"},
//             {"DIFFERENCE", "'Pires'", "'Piers'"},
//             {"INSERT", "'Bill Clinton'", "4", "'William'"},
//             {"LCASE", "'Fernando Alonso'"}, {"LEFT", "'Bonjour'", "3"},
//             {"LENGTH", "'four    '"}, {"LOCATE", "'jour'", "'Bonjour'"},
             {"LTRIM", "'   left trim   '"},
//               {"REPEAT", "'echo'", "3"},
//             {"REPLACE", "'to be or not to be'", "'be'", "'England'"},
//             {"RTRIM", "'  right trim   '"}, {"SOUNDEX", "'Derby'"},
//             {"SPACE", "12"},
//             {"SUBSTRING", "'Ruby the Rubicon Jeep'", "10", "7",},
//             {"UCASE", "'Fernando Alonso'"}
     };

     /**
      * Six combinations of valid identifiers with mixed case, to see how the
      * various pattern matching and returned values handle them. This test only
      * creates objects in these schemas.
      */
     private static final String[] IDS = {
             "one_meta_test", "TWO_meta_test", "ThReE_meta_test",
             "\"four_meta_test\"", "\"FIVE_meta_test\"", "\"sIx_meta_test\""};

     /**
      * All the builtin schemas.
      */
     private static final String[] BUILTIN_SCHEMAS = {
             //TODO: Are there any other built in schemas?

     };

     public static String getStoredIdentifier(String sqlIdentifier) {
         if (sqlIdentifier.charAt(0) == '"')
             return sqlIdentifier.substring(1, sqlIdentifier.length() - 1);
         else
             return sqlIdentifier.toUpperCase();
     }

     /**
      * Test getSchemas() without modifying the database.
      *
      * @throws SQLException
      */
     public void testGetSchemasReadOnly() throws SQLException {

         ResultSet rs = meta.getSchemas();
         checkSchemas(rs, new String[0]);
     }


     /**
      * Check the returned information from a getSchemas(). The passed in
      * String[] expected is a list of the schemas expected to be present in the
      * returned set. The returned set may contain additional schemas which will
      * be ignored, thus this test can be used regardless of the database state.
      * The builtin schemas are automatically checked and must not be part of the
      * passed in list.
      */
     public static void checkSchemas(ResultSet rs, String[] userExpected)
             throws SQLException {

         // Add in the system schemas
         String[] expected = new String[BUILTIN_SCHEMAS.length
                 + userExpected.length];

         System.arraycopy(BUILTIN_SCHEMAS, 0, expected, 0,
                 BUILTIN_SCHEMAS.length);
         System.arraycopy(userExpected, 0, expected, BUILTIN_SCHEMAS.length,
                 userExpected.length);

         // Remove any quotes from user schemas and upper case
         // those without quotes.
         for (int i = BUILTIN_SCHEMAS.length; i < expected.length; i++) {
             expected[i] = getStoredIdentifier(expected[i]);
         }

         // output is ordered by TABLE_SCHEM
         Arrays.sort(expected);

         int nextMatch = 0;

         while (rs.next()) {
             String schema = rs.getString("TABLE_SCHEM");
             assertNotNull(schema);

             // Catalogs not supported
//             assertNull(rs.getString("TABLE_CATALOG"));

             if (nextMatch < expected.length) {
                 if (expected[nextMatch].equals(schema)) nextMatch++;
             }
         }
         rs.close();
         assertEquals("Schemas missing ", expected.length, nextMatch);
     }

     private void assertMatchesPattern(String pattern, String result) {
         if (!doesMatch(pattern, 0, result, 0)) {
             fail("Bad pattern matching:" + pattern + " result:" + result);
         }

     }

     /**
      * See if a string matches the pattern as defined by DatabaseMetaData. By
      * passing in non-zero values can check sub-sets of the pattern against the
      * sub strings of the result. <BR>
      * _ matches a single character <BR>
      * % matches zero or more characters <BR>
      * Other characters match themselves.
      *
      * @param pattern
      *            Pattern
      * @param pp
      *            Position in pattern to start the actual pattern from
      * @param result
      *            result string
      * @param rp
      *            position in result to starting checking
      * @return true if a match is found
      */
     private boolean doesMatch(String pattern, int pp, String result, int rp) {
         // Find a match
         for (;;) {
             if (pp == pattern.length() && rp == result.length()) return true;

             // more characters to match in the result but
             // no more pattern.
             if (pp == pattern.length()) return false;

             char pc = pattern.charAt(pp);
             if (pc == '_') {
                 // need to match a single character but
                 // exhausted result, so no match.
                 if (rp == result.length()) return false;

                 pp++;
                 rp++;
             } else if (pc == '%') {
                 // % at end, complete match regardless of
                 // position of result since % matches zero or more.
                 if (pp == pattern.length() - 1) {
                     return true;
                 }

                 // Brut force, we have a pattern like %X
                 // and we are say in the third character of
                 // abCdefgX
                 // then start a 'CdefgX' and look for a match,
                 // then 'defgX' etc.
                 for (int sp = rp; sp < result.length(); sp++) {
                     if (doesMatch(pattern, pp + 1, result, sp)) {
                         // Have a match for the pattern after the %
                         // which means we have a match for the pattern
                         // with the % since we can match 0 or mor characters
                         // with %.
                         return true;
                     }
                 }

                 // Could not match the pattern after the %
                 return false;
             } else {
                 // need to match a single character but
                 // exhausted result, so no match.
                 if (rp == result.length()) return false;

                 // Single character, must match exactly.
                 if (pc != result.charAt(rp)) {
                     // Computer says no.
                     return false;
                 }
                 pp++;
                 rp++;
             }

         }

     }


     /**
      * Check that the list of escaped functions provided by the driver is a
      * strict subet of the specified set, the list does not contain duplicates,
      * all the functions listed can be executed and that if a function is not in
      * the list but is specified it cannot be executed.
      */
     private void escapedFunctions(String[][] specList, String metaDataList)
             throws SQLException {

         boolean[] seenFunction = new boolean[specList.length];

         StringTokenizer st = new StringTokenizer(metaDataList, ",");
         int counter = 0;
         while (st.hasMoreTokens()) {
             counter++;
             String function = st.nextToken();

             // find this function in the list
             boolean isSpecFunction = false;
             for (int f = 0; f < specList.length; f++) {
                 String[] specDetails = specList[f];
                 if (function.equals(specDetails[0])) {
                     // Matched spec.
                     if (seenFunction[f])
                         fail("Function in list twice: " + function);
                     seenFunction[f] = true;
                     isSpecFunction = true;
                     executeEscaped(specDetails);
                     break;
                 }
             }

             if (!isSpecFunction) {
                 fail("Non-JDBC spec function in list: " + function);
             }
         }

         // Now see if any speced functions are not in the metadata list
         assertSame("Function missing in metadata impl",specList.length, counter);
         for (int f = 0; f < specList.length; f++) {
             if (seenFunction[f]) continue;
             String[] specDetails = specList[f];

             // bug DERBY-723 CHAR maps to wrong function
             if ("CHAR".equals(specDetails[0])) continue;
             try {
                 executeEscaped(specDetails);
                 fail("function works but not declared in list: "
                         + specDetails[0]);
             } catch (SQLException e) {
                 //ok
             }
         }
     }

     /**
      * Test we can execute a function listed as a supported
      * JDBC escaped function. We don't care about the actual
      * return value, that should be tested elsewhere in
      * the specific test of a function.
      */
     private void executeEscaped(String[] specDetails)
         throws SQLException
     {

         String sql = "SELECT " + specDetails[0] + "(";

         for (int p = 0; p < specDetails.length - 1; p++)
         {
             if (p != 0)
                 sql = sql + ", ";

             sql = sql + specDetails[p + 1];
         }

         sql = sql + ") ;";

         System.out.println("DatabaseMetaDataTest.executeEscaped() "+sql);
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);

         assertNotNull("not supported function: "+sql,rs);

         rs.close();
         st.close();
     }

  //END APACHE-DERBY
}
