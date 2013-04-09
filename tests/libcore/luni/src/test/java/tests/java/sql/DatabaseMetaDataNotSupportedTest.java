/*
 * Copyright (C) 2008 The Android Open Source Project
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class DatabaseMetaDataNotSupportedTest extends TestCase {

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
            createTestTables();
        } catch (SQLException e) {
            System.out.println("Error in test setup: "+e.getMessage());
        }
    }

    public void tearDown() throws Exception {
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

    /**
     * java.sql.DatabaseMetaData#allProceduresAreCallable()
     */
    public void test_allProceduresAreCallable() throws SQLException {
        assertFalse(meta.allProceduresAreCallable());
    }

    @KnownFailure("Not supported ops applied")
    public void test_allTablesAreSelectable() throws SQLException {
        // grant SELECT privileges

        String query = "GRANT CREATE, SELECT ON " + DatabaseCreator.TEST_TABLE1
                + " TO " + Support_SQL.sqlUser;
        statement.execute(query);
        Connection userConn = Support_SQL.getConnection(Support_SQL.sqlUrl,
                Support_SQL.sqlUser, Support_SQL.sqlUser);
        DatabaseMetaData userMeta = userConn.getMetaData();
        ResultSet userTab = userMeta.getTables(null, null, null, null);

        assertTrue("Tables are not obtained", userTab.next());
        assertEquals("Incorrect name of obtained table",
                DatabaseCreator.TEST_TABLE1.toLowerCase(), userTab.getString(
                        "TABLE_NAME").toLowerCase());
        assertTrue("Not all of obtained tables are selectable", userMeta
                .allTablesAreSelectable());

        userTab.close();
        // revoke SELECT privileges
        query = "REVOKE SELECT ON " + DatabaseCreator.TEST_TABLE1 + " FROM "
                + Support_SQL.sqlUser;
        statement.execute(query);

        userTab = userMeta.getTables(null, null, null, null);

        assertTrue("Tables are not obtained", userTab.next());
        assertEquals("Incorrect name of obtained table",
                DatabaseCreator.TEST_TABLE1.toLowerCase(), userTab.getString(
                        "TABLE_NAME").toLowerCase());
        assertFalse("No SELECT privileges", userMeta.allTablesAreSelectable());

        userTab.close();
        // revoke CREATE privileges
        query = "REVOKE CREATE ON " + DatabaseCreator.TEST_TABLE1 + " FROM "
                + Support_SQL.sqlUser;
        statement.execute(query);
        userConn.close();
    }

    /**
     * java.sql.DatabaseMetaData#dataDefinitionCausesTransactionCommit()
     */
    public void test_dataDefinitionCausesTransactionCommit() throws SQLException {
        // NOT_FEASIBLE: SQLITE does not implement this functionality
    }

    /**
     * java.sql.DatabaseMetaData#dataDefinitionIgnoredInTransactions()
     */
    public void test_dataDefinitionIgnoredInTransactions() throws SQLException {
        assertFalse(meta.dataDefinitionIgnoredInTransactions());
    }

    /**
     * java.sql.DatabaseMetaData#deletesAreDetected(int)
     */
    public void test_deletesAreDetectedI() throws SQLException {
        assertFalse(meta.deletesAreDetected(0));
    }

    /**
     * java.sql.DatabaseMetaData#doesMaxRowSizeIncludeBlobs()
     */
    @KnownFailure("not supported")
    public void test_doesMaxRowSizeIncludeBlobs() throws SQLException {
        assertFalse(meta.doesMaxRowSizeIncludeBlobs());
    }

    /**
     * java.sql.DatabaseMetaData #getAttributes(java.lang.String,
     *        java.lang.String, java.lang.String, java.lang.String)
     */
    public void test_getAttributesLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
    }

    public void test_getCatalogs() throws SQLException {
        ResultSet rs = meta.getCatalogs();
        // NOT_FEASIBLE getCatalog is not supported
//        while (rs.next()) {
            //if (rs.getString("TABLE_CAT").equalsIgnoreCase(conn.getCatalog())) {
            //    rs.close();
            //    return;
            //}
//        }
        rs.close();
//        fail("Incorrect a set of catalogs");
    }

    /**
     * java.sql.DatabaseMetaData#getCatalogSeparator()
     */
    public void test_getCatalogSeparator() throws SQLException {
        assertTrue("Incorrect catalog separator", "".equals(meta
                .getCatalogSeparator().trim()));
    }

    /**
     * java.sql.DatabaseMetaData#getCatalogTerm()
     */
    public void test_getCatalogTerm() throws SQLException {
        assertTrue("Incorrect catalog term", "".equals(meta
                .getCatalogSeparator().trim()));
    }

    /**
     * java.sql.DatabaseMetaData#getExtraNameCharacters()
     */
    public void test_getExtraNameCharacters() throws SQLException {
        assertNotNull("Incorrect extra name characters", meta
                .getExtraNameCharacters());

    }

    @KnownFailure("not supported")
    public void test_getIndexInfoLjava_lang_StringLjava_lang_StringLjava_lang_StringZZ()
            throws SQLException {
        boolean unique = false;
        ResultSet rs = meta.getIndexInfo(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1, unique, true);
        ResultSetMetaData rsmd = rs.getMetaData();
        assertTrue("Rows do not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 13, col);
        String[] columnNames = { "TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME",
                "NON_UNIQUE", "INDEX_QUALIFIER", "INDEX_NAME", "TYPE",
                "ORDINAL_POSITION", "COLUMN_NAME", "ASC_OR_DESC",
                "CARDINALITY", "PAGES", "FILTER_CONDITION" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }

        assertEquals("Incorrect table catalog", conn.getCatalog(), rs
                .getString("TABLE_CAT"));
        assertEquals("Incorrect table schema", null, rs
                .getString("TABLE_SCHEM"));
        assertEquals("Incorrect table name", DatabaseCreator.TEST_TABLE1, rs
                .getString("TABLE_NAME"));
        assertEquals("Incorrect state of uniquess", unique, rs
                .getBoolean("NON_UNIQUE"));
        assertEquals("Incorrect index catalog", "", rs
                .getString("INDEX_QUALIFIER"));
        assertEquals("Incorrect index name", "primary", rs.getString(
                "INDEX_NAME").toLowerCase());
        assertEquals("Incorrect index type", DatabaseMetaData.tableIndexOther,
                rs.getShort("TYPE"));
        assertEquals("Incorrect column sequence number within index", 1, rs
                .getShort("ORDINAL_POSITION"));
        assertEquals("Incorrect column name", "id", rs.getString("COLUMN_NAME"));
        assertEquals("Incorrect column sort sequence", "a", rs.getString(
                "ASC_OR_DESC").toLowerCase());
        assertEquals("Incorrect cardinality", 1, rs.getInt("CARDINALITY"));
        assertEquals("Incorrect value of pages", 0, rs.getInt("PAGES"));
        assertEquals("Incorrect filter condition", null, rs
                .getString("FILTER_CONDITION"));
        rs.close();
    }

    @KnownFailure("not supported. Privileges are not supported.")
     public void test_getColumnPrivilegesLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        ResultSet rs = meta.getColumnPrivileges(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1, "id");
        ResultSetMetaData rsmd = rs.getMetaData();
        assertFalse("Rows are obtained", rs.next());
        rs.close();

        String query = "GRANT REFERENCES(id) ON " + DatabaseCreator.TEST_TABLE1
                + " TO " + Support_SQL.sqlLogin;
        statement.execute(query);

        rs = meta.getColumnPrivileges(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1, "id");
        rsmd = rs.getMetaData();
        assertTrue("Rows do not obtained", rs.next());
        int col = rsmd.getColumnCount();
        assertEquals("Incorrect number of columns", 8, col);
        String[] columnNames = { "TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME",
                "COLUMN_NAME", "GRANTOR", "GRANTEE", "PRIVILEGE",
                "IS_GRANTABLE" };
        for (int c = 1; c <= col; ++c) {
            assertEquals("Incorrect column name", columnNames[c - 1], rsmd
                    .getColumnName(c));
        }
        assertEquals("Incorrect table catalogue", conn.getCatalog(), rs
                .getString("TABLE_CAT").toLowerCase());
        assertEquals("Incorrect table schema", null, rs
                .getString("TABLE_SCHEM"));
        assertEquals("Incorrect table name", DatabaseCreator.TEST_TABLE1, rs
                .getString("TABLE_NAME").toLowerCase());
        assertEquals("Incorrect column name", "id", rs.getString("COLUMN_NAME")
                .toLowerCase());
        assertEquals("Incorrect grantor", Support_SQL.sqlLogin + "@"
                + Support_SQL.sqlHost, rs.getString("GRANTOR").toLowerCase());
        assertTrue("Incorrect grantee",
                rs.getString("GRANTEE").indexOf("root") != -1);
        assertEquals("Incorrect privilege", "references", rs.getString(
                "PRIVILEGE").toLowerCase());

        query = "REVOKE REFERENCES(id) ON " + DatabaseCreator.TEST_TABLE1
                + " FROM " + Support_SQL.sqlLogin;
        statement.execute(query);
        rs.close();
    }


    @KnownFailure("not supported")
     public void test_getExportedKeysLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        ResultSet rs = meta.getExportedKeys(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE3);
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

        assertEquals("Incorrect primary key table catalog", conn.getCatalog(),
                rs.getString("PKTABLE_CAT"));
        assertEquals("Incorrect primary key table schema", null, rs
                .getString("PKTABLE_SCHEM"));
        assertEquals("Incorrect primary key table name",
                DatabaseCreator.TEST_TABLE3, rs.getString("PKTABLE_NAME"));
        assertEquals("Incorrect primary key column name", "fk", rs
                .getString("PKCOLUMN_NAME"));
        assertEquals("Incorrect foreign key table catalog", conn.getCatalog(),
                rs.getString("FKTABLE_CAT"));
        assertEquals("Incorrect foreign key table schema", null, rs
                .getString("FKTABLE_SCHEM"));
        assertEquals("Incorrect foreign key table name",
                DatabaseCreator.TEST_TABLE1, rs.getString("FKTABLE_NAME"));
        assertEquals("Incorrect foreign key column name", "fkey", rs
                .getString("FKCOLUMN_NAME"));
        assertEquals("Incorrect sequence number within foreign key", 1, rs
                .getShort("KEY_SEQ"));
        assertEquals("Incorrect update rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("UPDATE_RULE"));
        assertEquals("Incorrect delete rule value",
                DatabaseMetaData.importedKeyNoAction, rs
                        .getShort("DELETE_RULE"));
        assertNotNull("Incorrect foreign key name", rs.getString("FK_NAME"));
        assertEquals("Incorrect primary key name", null, rs
                .getString("PK_NAME"));
        assertEquals("Incorrect deferrability",
                DatabaseMetaData.importedKeyNotDeferrable, rs
                        .getShort("DEFERRABILITY"));
        rs.close();
    }

    public void test_getProcedureColumnsLjava_lang_StringLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        meta.getProcedureColumns("", "", "", "");
    }

    public void test_getProceduresLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        // NOT_FEASIBLE: SQLITE does not implement this functionality
    }

    /**
     * java.sql.DatabaseMetaData#getProcedureTerm()
     */
    @KnownFailure("Exception test fails")
    public void test_getProcedureTerm() throws SQLException {
        assertTrue("Incorrect procedure term", "".equals(meta
                .getProcedureTerm().trim()));

      //Exception checking
        conn.close();

         try {
             meta.getProcedureTerm();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getSchemaTerm()
     */
    @KnownFailure("Exception test fails")
    public void test_getSchemaTerm() throws SQLException {
        String term = meta.getSchemaTerm();
        assertNotNull("Incorrect schema term", term );

        assertTrue("".equals(term));

      //Exception checking
        conn.close();

         try {
             meta.getSchemaTerm();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    public void test_getSuperTablesLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        // NOT_FEASIBLE: SQLITE does not implement this functionality
    }

    public void test_getSuperTypesLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        // NOT_FEASIBLE: SQLITE does not implement this functionality
    }

    @KnownFailure("not supported. Privileges are not supported.")
    public void test_getTablePrivilegesLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        // case 1. Get privileges when no privilegies exist for one table
        ResultSet privileges = meta.getTablePrivileges(conn.getCatalog(), "%",
                DatabaseCreator.TEST_TABLE3);
        assertFalse("Some privilegies exist", privileges.next());
        privileges.close();

        // case 2. Get privileges when no privilegies exist for all tables
        privileges = meta.getTablePrivileges(null, null, null);
        assertFalse("Some privilegies exist", privileges.next());
        privileges.close();

        // case 3. grant CREATE and SELECT privileges ang get them
        HashSet<String> expectedPrivs = new HashSet<String>();
        expectedPrivs.add("CREATE");
        expectedPrivs.add("SELECT");

        String query = "GRANT CREATE, SELECT ON " + DatabaseCreator.TEST_TABLE3
                + " TO " + Support_SQL.sqlUser;
        statement.execute(query);

        privileges = meta.getTablePrivileges(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE3);

        while (privileges.next()) {
            assertEquals("Wrong catalog name", Support_SQL.sqlCatalog,
                    privileges.getString("TABLE_CAT"));
            assertNull("Wrong schema", privileges.getString("TABLE_SCHEM"));
            assertEquals("Wrong table name", DatabaseCreator.TEST_TABLE3,
                    privileges.getString("TABLE_NAME"));
            assertTrue("Wrong privilege " + privileges.getString("PRIVILEGE"),
                    expectedPrivs.remove(privileges.getString("PRIVILEGE")));
            assertEquals("Wrong grantor", Support_SQL.sqlLogin + "@"
                    + Support_SQL.sqlHost, privileges.getString("GRANTOR"));
            assertEquals("Wrong grantee", Support_SQL.sqlUser + "@%",
                    privileges.getString("GRANTEE"));
            assertNull("Wrong value of IS_GRANTABLE", privileges
                    .getString("IS_GRANTABLE"));
        }
        privileges.close();
        assertTrue("Wrong privileges were returned", expectedPrivs.isEmpty());

        query = "REVOKE CREATE, SELECT ON " + DatabaseCreator.TEST_TABLE3
                + " FROM " + Support_SQL.sqlUser;
        statement.execute(query);

        // case 4. grant all privileges ang get them
        String[] privs = new String[] { "ALTER", "CREATE", "CREATE VIEW",
                "DELETE", "DROP", "INDEX", "INSERT", "REFERENCES", "SELECT",
                "SHOW VIEW", "UPDATE" };
        expectedPrivs = new HashSet<String>();
        for (int i = 0; i < privs.length; i++) {
            expectedPrivs.add(privs[i]);
        }
        query = "GRANT ALL ON " + DatabaseCreator.TEST_TABLE3 + " TO "
                + Support_SQL.sqlUser;
        statement.execute(query);

        privileges = meta.getTablePrivileges(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE3);

        while (privileges.next()) {
            assertEquals("Wrong catalog name", Support_SQL.sqlCatalog,
                    privileges.getString("TABLE_CAT"));
            assertNull("Wrong schema", privileges.getString("TABLE_SCHEM"));
            assertEquals("Wrong table name", DatabaseCreator.TEST_TABLE3,
                    privileges.getString("TABLE_NAME"));
            assertTrue("Wrong privilege " + privileges.getString("PRIVILEGE"),
                    expectedPrivs.remove(privileges.getString("PRIVILEGE")));
            assertEquals("Wrong grantor", Support_SQL.sqlLogin + "@"
                    + Support_SQL.sqlHost, privileges.getString("GRANTOR"));
            assertEquals("Wrong grantee", Support_SQL.sqlUser + "@%",
                    privileges.getString("GRANTEE"));
            assertNull("Wrong value of IS_GRANTABLE", privileges
                    .getString("IS_GRANTABLE"));
        }
        privileges.close();
        assertTrue("Wrong privileges were returned", expectedPrivs.isEmpty());

        query = "REVOKE ALL ON " + DatabaseCreator.TEST_TABLE3 + " FROM "
                + Support_SQL.sqlUser;
        statement.execute(query);

        // case 5. check no privelegies after revoke
        privileges = meta.getTablePrivileges(conn.getCatalog(), "%",
                DatabaseCreator.TEST_TABLE3);
        assertFalse("Some privilegies exist", privileges.next());
        privileges.close();

        privileges = meta.getTablePrivileges(null, null, null);
        assertFalse("Some privilegies exist", privileges.next());
        privileges.close();
    }

    public void test_getUDTsLjava_lang_StringLjava_lang_StringLjava_lang_String$I()
            throws SQLException {
        // NOT_FEASIBLE: JDBC does not implement this functionality
    }

    @KnownFailure("Not supported ops applied")
    public void test_getVersionColumnsLjava_lang_StringLjava_lang_StringLjava_lang_String()
            throws SQLException {
        DatabaseMetaDataTest.insertNewRecord();

        String triggerName = "updateTrigger";
        String triggerQuery = "CREATE TRIGGER " + triggerName
                + " AFTER UPDATE ON " + DatabaseCreator.TEST_TABLE1
                + " FOR EACH ROW BEGIN INSERT INTO "
                + DatabaseCreator.TEST_TABLE3 + " SET fk = 10; END;";
        statementForward.execute(triggerQuery);

        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field1='fffff' WHERE id=1";
        statementForward.execute(updateQuery);

        ResultSet rs = meta.getVersionColumns(conn.getCatalog(), null,
                DatabaseCreator.TEST_TABLE1);
        assertTrue("Result set is empty", rs.next());
        rs.close();
    }

    /**
     * java.sql.DatabaseMetaData#isCatalogAtStart()
     */
    @KnownFailure("Exception test fails")
    public void test_isCatalogAtStart() throws SQLException {
        assertFalse(
                "catalog doesn't appear at the start of a fully qualified table name",
                meta.isCatalogAtStart());

      //Exception checking
        conn.close();

         try {
             meta.isCatalogAtStart();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    @KnownFailure("not supported")
    public void test_locatorsUpdateCopy() throws SQLException {
        assertFalse(meta.locatorsUpdateCopy());
    }

    public void test_nullPlusNonNullIsNull() throws SQLException {
        assertFalse(meta.nullPlusNonNullIsNull());
    }

    public void test_nullsAreSortedAtEnd() throws SQLException {
        assertFalse(meta.nullsAreSortedAtEnd());
    }

    public void test_nullsAreSortedAtStart() throws SQLException {
        assertFalse(meta.nullsAreSortedAtStart());
    }

    public void test_nullsAreSortedHigh() throws SQLException {
        assertFalse(meta.nullsAreSortedHigh());
    }

    public void test_nullsAreSortedLow() throws SQLException {
        assertFalse(meta.nullsAreSortedLow());
    }

    /**
     * java.sql.DatabaseMetaData#ownDeletesAreVisible(int)
     */
    public void test_ownDeletesAreVisibleI() throws SQLException {
        // NOT_FEASIBLE not supported
//        assertFalse(
//                "result set's own deletes are visible for TYPE_FORWARD_ONLY type",
//                meta.ownDeletesAreVisible(ResultSet.TYPE_FORWARD_ONLY));
//        assertFalse(
//                "result set's own deletes are visible for TYPE_SCROLL_INSENSITIVE type",
//               meta.ownDeletesAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
//        assertFalse(
//                "result set's own deletes are visible for TYPE_SCROLL_SENSITIVE type",
//                meta.ownDeletesAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));
        assertFalse("result set's own deletes are visible for unknown type",
                meta.ownDeletesAreVisible(100));
    }

    /**
     * java.sql.DatabaseMetaData#ownInsertsAreVisible(int)
     */
    public void test_ownInsertsAreVisibleI() throws SQLException {
//        assertFalse(
//                "result set's own inserts are visible for TYPE_FORWARD_ONLY type",
//                meta.ownInsertsAreVisible(ResultSet.TYPE_FORWARD_ONLY));
//        assertFalse(
//                "result set's own inserts are visible for TYPE_SCROLL_INSENSITIVE type",
//                meta.ownInsertsAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
//        assertFalse(
//                "result set's own inserts are visible for TYPE_SCROLL_SENSITIVE type",
//                meta.ownInsertsAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));
        assertFalse("result set's own inserts are visible for unknown type",
                meta.ownInsertsAreVisible(100));
    }

    public void test_ownUpdatesAreVisibleI() throws SQLException {
        assertTrue(
                "result set's own updates are visible for TYPE_FORWARD_ONLY type",
                meta.ownUpdatesAreVisible(ResultSet.TYPE_FORWARD_ONLY));
        assertTrue(
                "result set's own updates are visible for TYPE_SCROLL_INSENSITIVE type",
                meta.ownUpdatesAreVisible(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertTrue(
                "result set's own updates are visible for TYPE_SCROLL_SENSITIVE type",
                meta.ownUpdatesAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));
        assertFalse("result set's own updates are visible for unknown type",
                meta.ownUpdatesAreVisible(100));
    }

    public void test_storesLowerCaseIdentifiers() throws SQLException {
        assertFalse(meta.storesLowerCaseIdentifiers());
    }

    public void test_storesLowerCaseQuotedIdentifiers() throws SQLException {
        assertFalse(meta.storesLowerCaseQuotedIdentifiers());
    }

    public void test_storesUpperCaseIdentifiers() throws SQLException {
        assertFalse(meta.storesUpperCaseIdentifiers());
    }

    public void test_storesUpperCaseQuotedIdentifiers() throws SQLException {
        assertFalse(meta.storesUpperCaseQuotedIdentifiers());
    }

    @KnownFailure("not supported")
    public void test_supportsANSI92EntryLevelSQL() throws SQLException {
        assertFalse(meta.supportsANSI92EntryLevelSQL());
    }

    public void test_supportsANSI92FullSQL() throws SQLException {
        assertFalse(meta.supportsANSI92FullSQL());
    }

    public void test_supportsANSI92IntermediateSQL() throws SQLException {
        assertFalse(meta.supportsANSI92IntermediateSQL());
    }

    public void test_supportsAlterTableWithAddColumn() throws SQLException {
        assertFalse(meta.supportsAlterTableWithAddColumn());
    }

    public void test_supportsAlterTableWithDropColumn() throws SQLException {
        assertFalse(meta.supportsAlterTableWithDropColumn());
    }

    public void test_supportsBatchUpdates() throws SQLException {
        assertTrue(meta.supportsBatchUpdates());
    }

    public void test_supportsCatalogsInDataManipulation() throws SQLException {
        assertFalse(meta.supportsCatalogsInDataManipulation());
    }

    public void test_supportsCatalogsInIndexDefinitions() throws SQLException {
        assertFalse(meta.supportsCatalogsInIndexDefinitions());
    }

    public void test_supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        assertFalse(meta.supportsCatalogsInPrivilegeDefinitions());
    }

    public void test_supportsCatalogsInProcedureCalls() throws SQLException {
        assertFalse(meta.supportsCatalogsInProcedureCalls());
    }

    public void test_supportsCatalogsInTableDefinitions() throws SQLException {
        assertFalse(meta.supportsCatalogsInTableDefinitions());
    }

    public void test_supportsConvert() throws SQLException {
        assertFalse(meta.supportsConvert());
    }

    public void test_supportsConvertII() throws SQLException {
        assertFalse(meta.supportsConvert());
    }

    public void test_supportsCoreSQLGrammar() throws SQLException {
        assertFalse(meta.supportsCoreSQLGrammar());
    }

    public void test_supportsCorrelatedSubqueries() throws SQLException {
        assertFalse(meta.supportsCorrelatedSubqueries());
    }

    @KnownFailure("not supported")
    public void test_supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        assertFalse(meta.supportsDataDefinitionAndDataManipulationTransactions());
    }

    public void test_supportsDataManipulationTransactionsOnly() throws SQLException {
        assertFalse(meta.supportsDataManipulationTransactionsOnly());
    }

    public void test_supportsDifferentTableCorrelationNames() throws SQLException {
        assertFalse(meta.supportsDifferentTableCorrelationNames());
    }

    public void test_supportsExtendedSQLGrammar() throws SQLException {
        assertFalse(meta.supportsExtendedSQLGrammar());
    }

    public void test_supportsFullOuterJoins() throws SQLException {
        assertFalse(meta.supportsFullOuterJoins());
    }

    public void test_supportsGetGeneratedKeys() throws SQLException {
        assertFalse(meta.supportsGetGeneratedKeys());
    }

    public void test_supportsGroupByBeyondSelect() throws SQLException {
        assertFalse(meta.supportsGroupByBeyondSelect());
    }

    public void test_supportsIntegrityEnhancementFacility() throws SQLException {
        assertFalse(meta.supportsIntegrityEnhancementFacility());
    }

    public void test_supportsLikeEscapeClause() throws SQLException {
        assertFalse(meta.supportsLikeEscapeClause());
    }

    public void test_supportsLimitedOuterJoins() throws SQLException {
        assertFalse(meta.supportsLimitedOuterJoins());
    }

    @KnownFailure("not supported")
    public void test_supportsMinimumSQLGrammar() throws SQLException {
        assertFalse(meta.supportsMinimumSQLGrammar());
    }

    public void test_supportsMixedCaseIdentifiers() throws SQLException {
        assertFalse(meta.supportsMixedCaseIdentifiers());
    }

    public void test_supportsMixedCaseQuotedIdentifiers() throws SQLException {
        assertFalse(meta.supportsMixedCaseQuotedIdentifiers());
    }

    public void test_supportsMultipleOpenResults() throws SQLException {
        assertFalse(meta.supportsMultipleOpenResults());
    }

    public void test_supportsMultipleResultSets() throws SQLException {
        assertFalse(meta.supportsMultipleResultSets());
    }

    public void test_supportsMultipleTransactions() throws SQLException {
        assertFalse(meta.supportsMultipleTransactions());
    }

    public void test_supportsNamedParameters() throws SQLException {
        assertFalse(meta.supportsNamedParameters());
    }

    public void test_supportsOpenCursorsAcrossCommit() throws SQLException {
        assertFalse(meta.supportsOpenCursorsAcrossCommit());
    }

    public void test_supportsOpenCursorsAcrossRollback() throws SQLException {
        assertFalse(meta.supportsOpenCursorsAcrossRollback());
    }

    public void test_supportsOpenStatementsAcrossCommit() throws SQLException {
        assertFalse(meta.supportsOpenStatementsAcrossCommit());
    }

    public void test_supportsOpenStatementsAcrossRollback() throws SQLException {
        assertFalse(meta.supportsOpenStatementsAcrossRollback());
    }

    public void test_supportsOuterJoins() throws SQLException {
        assertFalse(meta.supportsOuterJoins());
    }

    public void test_supportsPositionedDelete() throws SQLException {
        assertFalse(meta.supportsPositionedDelete());
    }

    public void test_supportsPositionedUpdate() throws SQLException {
        assertFalse(meta.supportsPositionedUpdate());
    }

    public void test_supportsResultSetConcurrencyII() throws SQLException {
        assertFalse(meta.supportsResultSetConcurrency(0,0));
    }

    public void test_supportsResultSetHoldabilityI() throws SQLException {
        assertFalse(meta.supportsResultSetHoldability(0));
    }

    @KnownFailure("not supported")
    public void test_supportsResultSetTypeI() throws SQLException {
        assertTrue("database supports TYPE_FORWARD_ONLY type", meta
                .supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
        assertFalse("database doesn't support TYPE_SCROLL_INSENSITIVE type",
                meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        assertFalse("database supports TYPE_SCROLL_SENSITIVE type", meta
                .supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
        assertFalse("database supports unknown type", meta
                .supportsResultSetType(100));
    }

    public void test_supportsSavepoints() throws SQLException {
        assertFalse(meta.supportsSavepoints());
    }

    public void test_supportsSchemasInDataManipulation() throws SQLException {
        assertFalse(meta.supportsSchemasInDataManipulation());
    }

    public void test_supportsSchemasInIndexDefinitions() throws SQLException {
        assertFalse(meta.supportsSchemasInIndexDefinitions());
    }

    public void test_supportsSchemasInPrivilegeDefinitions() throws SQLException {
        // NOT_FEASIBLE: SQLITE does not implement this functionality
    }

    public void test_supportsSchemasInProcedureCalls() throws SQLException {
        assertFalse(meta.supportsSchemasInProcedureCalls());
    }

    public void test_supportsSchemasInTableDefinitions() throws SQLException {
        assertFalse(meta.supportsSchemasInTableDefinitions());
    }

    public void test_supportsStatementPooling() throws SQLException {
        assertFalse(meta.supportsStatementPooling());
    }

    public void test_supportsStoredProcedures() throws SQLException {
        assertFalse(meta.supportsStoredProcedures());
    }

    @KnownFailure("not supported")
    public void test_supportsSubqueriesInComparisons() throws SQLException {
        assertFalse(meta.supportsSubqueriesInComparisons());
    }

    @KnownFailure("not supported")
    public void test_supportsSubqueriesInIns() throws SQLException {
        assertFalse(meta.supportsSubqueriesInIns());
    }

    public void test_supportsSubqueriesInQuantifieds() throws SQLException {
        assertFalse(meta.supportsSubqueriesInQuantifieds());
    }

    @KnownFailure("not supported")
    public void test_supportsTransactions() throws SQLException {
        assertFalse(meta.supportsTransactions());
    }

    public void test_supportsUnion() throws SQLException {
        assertTrue(meta.supportsUnion());
    }

    public void test_supportsUnionAll() throws SQLException {
        assertTrue(meta.supportsUnionAll());
    }

    public void test_usesLocalFilePerTable() throws SQLException {
        assertFalse(meta.usesLocalFilePerTable());
    }

    @KnownFailure("not supported")
    public void test_usesLocalFiles() throws SQLException {
        assertFalse(meta.usesLocalFiles());
    }

    /**
     * java.sql.DatabaseMetaData#getMaxBinaryLiteralLength()
     */
    public void test_getMaxBinaryLiteralLength() throws SQLException {
        assertTrue("Incorrect binary literal length", meta
                .getMaxBinaryLiteralLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxCatalogNameLength()
     */
    public void test_getMaxCatalogNameLength() throws SQLException {
        assertTrue("Incorrect name length", meta.getMaxCatalogNameLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxCharLiteralLength()
     */
    public void test_getMaxCharLiteralLength() throws SQLException {
        assertTrue("Incorrect char literal length", meta
                .getMaxCharLiteralLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnNameLength()
     */
    public void test_getMaxColumnNameLength() throws SQLException {
        assertTrue("Incorrect column name length", meta
                .getMaxColumnNameLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnsInGroupBy()
     */
    public void test_getMaxColumnsInGroupBy() throws SQLException {
        assertTrue("Incorrect number of columns",
                meta.getMaxColumnsInGroupBy() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnsInIndex()
     */
    public void test_getMaxColumnsInIndex() throws SQLException {
        assertTrue("Incorrect number of columns",
                meta.getMaxColumnsInIndex() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnsInOrderBy()
     */
    public void test_getMaxColumnsInOrderBy() throws SQLException {
        assertTrue("Incorrect number of columns",
                meta.getMaxColumnsInOrderBy() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnsInSelect()
     */
    public void test_getMaxColumnsInSelect() throws SQLException {
        assertTrue("Incorrect number of columns",
                meta.getMaxColumnsInSelect() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxColumnsInTable()
     */
    public void test_getMaxColumnsInTable() throws SQLException {
        assertTrue("Incorrect number of columns",
                meta.getMaxColumnsInTable() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxConnections()
     */
    public void test_getMaxConnections() throws SQLException {
        assertTrue("Incorrect number of connections",
                meta.getMaxConnections() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxIndexLength()
     */
    public void test_getMaxIndexLength() throws SQLException {
        assertTrue("Incorrect length of index", meta.getMaxIndexLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxProcedureNameLength()
     */
    public void test_getMaxProcedureNameLength() throws SQLException {
        assertTrue("Incorrect length of procedure name", meta
                .getMaxProcedureNameLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxRowSize()
     */
    public void test_getMaxRowSize() throws SQLException {
        assertTrue("Incorrect size of row", meta.getMaxRowSize() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxSchemaNameLength()
     */
    public void test_getMaxSchemaNameLength() throws SQLException {
        assertTrue("Incorrect length of schema name", meta
                .getMaxSchemaNameLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxStatementLength()
     */
    public void test_getMaxStatementLength() throws SQLException {
        assertTrue("Incorrect length of statement", meta
                .getMaxStatementLength() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxStatements()
     */
    public void test_getMaxStatements() throws SQLException {
        assertTrue("Incorrect number of statements",
                meta.getMaxStatements() == 0);
    }

    /**
     * java.sql.DatabaseMetaData#getMaxTableNameLength()
     */
    @KnownFailure("Exception test fails")
    public void test_getMaxTableNameLength() throws SQLException {
        assertTrue("Now supported", meta
                .getMaxTableNameLength() == 0);

      //Exception checking
        conn.close();

         try {
             meta.getMaxTableNameLength();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getMaxTablesInSelect()
     */
    @KnownFailure("Exception test fails")
    public void test_getMaxTablesInSelect() throws SQLException {
        assertTrue("Tables in select is now supported: change test implementation\"",
                meta.getMaxTablesInSelect() == 0);

      //Exception checking
        conn.close();

         try {
             meta.getMaxTablesInSelect();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }

    /**
     * java.sql.DatabaseMetaData#getMaxUserNameLength()
     */
    @KnownFailure("Exception test fails")
    public void test_getMaxUserNameLength() throws SQLException {
        assertTrue("Usernames are now supported: change test implementation",
                meta.getMaxUserNameLength() == 0);

      //Excpetion checking
        conn.close();

         try {
             meta.getMaxUserNameLength();
             fail("SQLException not thrown");
         } catch (SQLException e) {
             //ok
         }
    }


}
