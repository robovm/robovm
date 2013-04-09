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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tests.support.DatabaseCreator;
import tests.support.Support_SQL;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UpdateFunctionalityTest2 extends TestCase {

    private static Connection conn = null;

    private static Statement statement = null;

    public void setUp() throws Exception {
        super.setUp();
        Support_SQL.loadDriver();
        try {
            conn = Support_SQL.getConnection();
            statement = conn.createStatement();
            createTestTables();
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
        DatabaseCreator.fillParentTable(conn);
        DatabaseCreator.fillSimpleTable3(conn);
        DatabaseCreator.fillSimpleTable1(conn);
    }

    public void tearDown() throws Exception {
        deleteTestTables();
        statement.close();
        conn.close();
        super.tearDown();
    }

    private void createTestTables() {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet userTab = meta.getTables(null, null, null, null);

            while (userTab.next()) {
                String tableName = userTab.getString("TABLE_NAME");
                if (tableName.equals(DatabaseCreator.PARENT_TABLE)) {
                    statement
                            .execute(DatabaseCreator.DROP_TABLE_PARENT);
                } else if (tableName
                        .equals(DatabaseCreator.FKCASCADE_TABLE)) {
                    statement
                            .execute(DatabaseCreator.DROP_TABLE_FKCASCADE);
                } else if (tableName
                        .equals(DatabaseCreator.FKSTRICT_TABLE)) {
                    statement
                            .execute(DatabaseCreator.DROP_TABLE_FKSTRICT);
                } else if (tableName
                        .equals(DatabaseCreator.SIMPLE_TABLE1)) {
                    statement
                            .execute(DatabaseCreator.DROP_TABLE_SIMPLE1);
                } else if (tableName
                        .equals(DatabaseCreator.SIMPLE_TABLE3)) {
                    statement
                            .execute(DatabaseCreator.DROP_TABLE_SIMPLE3);
                } else if (tableName
                        .equals(DatabaseCreator.TEST_TABLE5)) {
                    statement.execute(DatabaseCreator.DROP_TABLE5);
                }
            }
            userTab.close();
            statement.execute(DatabaseCreator.CREATE_TABLE_PARENT);
            statement.execute(DatabaseCreator.CREATE_TABLE_FKSTRICT);
            statement.execute(DatabaseCreator.CREATE_TABLE_FKCASCADE);
            statement.execute(DatabaseCreator.CREATE_TABLE_SIMPLE3);
            statement.execute(DatabaseCreator.CREATE_TABLE_SIMPLE1);
            statement.execute(DatabaseCreator.CREATE_TABLE5);
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    private void deleteTestTables() {
        try {
            statement.execute(DatabaseCreator.DROP_TABLE_FKCASCADE);
            statement.execute(DatabaseCreator.DROP_TABLE_FKSTRICT);
            statement.execute(DatabaseCreator.DROP_TABLE_PARENT);
            statement.execute(DatabaseCreator.DROP_TABLE_SIMPLE3);
            statement.execute(DatabaseCreator.DROP_TABLE_SIMPLE1);
            statement.execute(DatabaseCreator.DROP_TABLE5);
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    /**
     * UpdateFunctionalityTest2#testUpdate1(). Updates row with no
     *        referencing ones and RESTRICT action
     */
    public void testUpdate1() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        statement.execute("UPDATE " + DatabaseCreator.PARENT_TABLE
                + " SET id = 4 WHERE id = 3");
    }

    /**
     * UpdateFunctionalityTest2#testUpdate2(). Attempts to update row
     *        with referencing ones and RESTRICT action - expecting SQLException
     *
     *  TODO not supported
     */
    @KnownFailure("not supported")
    public void testUpdate2() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        try {
            statement.executeUpdate("UPDATE " + DatabaseCreator.PARENT_TABLE
                    + " SET id = 5 WHERE id = 1;");
            fail("expecting SQLException");
        } catch (SQLException ex) {
            // expected

        }
    }

    /**
     * UpdateFunctionalityTest2#testUpdate3(). Deletes all referencing
     *        rows and then updates referenced one
     */
    public void testUpdate3() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        statement.execute("DELETE FROM " + DatabaseCreator.FKSTRICT_TABLE
                + " WHERE name_id = 1;");
        statement.execute("UPDATE " + DatabaseCreator.PARENT_TABLE
                + " SET id = 5 WHERE id = 1;");
    }

    /**
     * UpdateFunctionalityTest2#testUpdate4(). Attempts to set incorrect
     *        foreign key value - expecting SQLException
     *
     *  TODO foreign key functionality is not supported
     */
    @KnownFailure("not supported")
    public void testUpdate4() throws SQLException {
       DatabaseCreator.fillFKStrictTable(conn);
        try {
            statement.executeUpdate("UPDATE " + DatabaseCreator.FKSTRICT_TABLE
                    + " SET name_id = 6 WHERE name_id = 2");
            fail("expecting SQLException");
        } catch (SQLException ex) {
            // expected
        }
    }

    /**
     * UpdateFunctionalityTest2#testUpdate5(). Updates row with
     *        referencing ones and CASCADE action - expecting that all
     *        referencing rows will also be updated
     */
    public void testUpdate5() throws SQLException {
        DatabaseCreator.fillFKCascadeTable(conn);
        statement.executeUpdate("UPDATE " + DatabaseCreator.PARENT_TABLE
                + " SET id = 5 WHERE id = 1;");

        ResultSet r = statement.executeQuery("SELECT COUNT(*) " + "FROM "
                + DatabaseCreator.FKCASCADE_TABLE + " WHERE name_id = 1;");
        r.next();
        assertEquals("Should be 2 rows", 2, r.getInt(1));
        r = statement.executeQuery("SELECT COUNT(*) " + "FROM "
                + DatabaseCreator.FKCASCADE_TABLE + " WHERE name_id = 5;");
        r.next();
        assertEquals("Should be 0 rows", 0, r.getInt(1));
        r.close();
    }

    /**
     * UpdateFunctionalityTest2#testUpdate6(). Attempts to set incorrect
     *        foreign key value to row with CASCADE action - expecting
     *        SQLException
     *
     *  TODO Foreign key functionality is not supported
     */
    @KnownFailure("not supported")
    public void testUpdate6() throws SQLException {
        DatabaseCreator.fillFKCascadeTable(conn);
        try {
            statement.executeUpdate("UPDATE " + DatabaseCreator.FKCASCADE_TABLE
                    + " SET name_id = 6 WHERE name_id = 2");
            fail("expecting SQLException");
        } catch (SQLException ex) {
            // expected
        }
    }

    /**
     * UpdateFunctionalityTest2#testUpdate7(). Updates table using
     *        subquery in WHERE clause
     *
     *  TODO Foreign key functionality is not supported
     */
    @KnownFailure("not supported")
   public void testUpdate7() throws SQLException {

        DatabaseCreator.fillFKStrictTable(conn);
        statement.executeUpdate("UPDATE " + DatabaseCreator.FKSTRICT_TABLE
                + " SET value = 'updated' WHERE name_id = ANY (SELECT id FROM "
                + DatabaseCreator.PARENT_TABLE + " WHERE id > 1)");
        ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM "
                + DatabaseCreator.FKSTRICT_TABLE + " WHERE value = 'updated';");
        r.next();
        assertEquals("Should be 1 row", 1, r.getInt(1));
        r.close();
    }

    /**
     * UpdateFunctionalityTest2#testUpdate8(). Updates table using scalar
     *        subquery as new field value
     */
    public void testUpdate8() throws SQLException {
        statement.executeUpdate("UPDATE " + DatabaseCreator.SIMPLE_TABLE3
                + " SET speed = (SELECT MAX(speed) FROM "
                + DatabaseCreator.SIMPLE_TABLE1
                + ") WHERE id = (SELECT id FROM "
                + DatabaseCreator.SIMPLE_TABLE1
                + " WHERE speed = (SELECT MAX(speed) FROM "
                + DatabaseCreator.SIMPLE_TABLE1 + "))");
        ResultSet r = statement.executeQuery("SELECT id FROM "
                + DatabaseCreator.SIMPLE_TABLE3
                + " WHERE speed = (SELECT MAX(speed) FROM "
                + DatabaseCreator.SIMPLE_TABLE1 + ");");
        r.next();
        assertEquals("Incorrect id updated", 1, r.getInt(1));
        r.close();
    }

    /**
     * UpdateFunctionalityTest2#testUpdate9(). Updates table using
     *        PreparedStatement
     */
    public void testUpdate9() throws SQLException {
        DatabaseCreator.fillTestTable5(conn);
        PreparedStatement stat = conn.prepareStatement("UPDATE "
                + DatabaseCreator.TEST_TABLE5
                + " SET testValue = ? WHERE testID = ?");
        stat.setString(1, "1");
        stat.setInt(2, 1);
        stat.execute();
        stat.setString(1, "2");
        stat.setInt(2, 2);
        stat.execute();
        ResultSet r = statement.executeQuery("SELECT testId, testValue FROM "
                + DatabaseCreator.TEST_TABLE5
                + " WHERE testID < 3 ORDER BY testID");
        while (r.next()) {
            assertEquals("Incorrect value was returned", new Integer(r
                    .getInt(1)).toString(), r.getString(2));
        }
        r.close();
        stat.close();
    }
}
