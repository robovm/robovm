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

public class DeleteFunctionalityTest extends TestCase {

    private static Connection conn = null;

    private static Statement statement = null;

    public void setUp() throws Exception {
        super.setUp();
        Support_SQL.loadDriver();
        conn = Support_SQL.getConnection();
        statement = conn.createStatement();
        createTestTables();
        DatabaseCreator.fillParentTable(conn);
    }

    public void tearDown() throws Exception {
        deleteTestTables();
        statement.close();
        conn.close();
        super.tearDown();
    }


    public void createTestTables() {
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
                        .equals(DatabaseCreator.TEST_TABLE5)) {
                    statement.execute(DatabaseCreator.DROP_TABLE5);
                }
            }
            userTab.close();
            statement.execute(DatabaseCreator.CREATE_TABLE_PARENT);
            statement.execute(DatabaseCreator.CREATE_TABLE_FKSTRICT);
            statement.execute(DatabaseCreator.CREATE_TABLE_FKCASCADE);
            statement.execute(DatabaseCreator.CREATE_TABLE5);
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    public void deleteTestTables() {
        try {
            statement.execute(DatabaseCreator.DROP_TABLE_FKCASCADE);
            statement.execute(DatabaseCreator.DROP_TABLE_FKSTRICT);
            statement.execute(DatabaseCreator.DROP_TABLE_PARENT);
            statement.execute(DatabaseCreator.DROP_TABLE5);
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    /**
     * DeleteFunctionalityTest#testDelete1(). Deletes row with no
     *        referencing ones and RESTRICT action
     */
    public void testDelete1() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        statement.execute("DELETE FROM " + DatabaseCreator.PARENT_TABLE
                + " WHERE id = 3;");
    }

    /**
     * DeleteFunctionalityTest#testDelete2(). Attempts to delete row with
     *        referencing ones and RESTRICT action - expecting SQLException
     *  TODO foreign key functionality is not supported
     */
/*    public void testDelete2() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        try {
            statement.execute("DELETE FROM " + DatabaseCreator.PARENT_TABLE
                    + " WHERE id = 1;");
            fail("expecting SQLException");
        } catch (SQLException ex) {
            // expected
        }
    }
*/
    /**
     * DeleteFunctionalityTest#testDelete3(). Deletes all referencing
     *        rows and then deletes referenced one
     */
    public void testDelete3() throws SQLException {
        statement.execute("DELETE FROM " + DatabaseCreator.FKSTRICT_TABLE
                + " WHERE name_id = 1;");
        statement.execute("DELETE FROM " + DatabaseCreator.FKSTRICT_TABLE
                + " WHERE id = 1;");
    }

    /**
     * DeleteFunctionalityTest#testDelete4(). Deletes row with no
     *        referencing ones and CASCADE action
     */
    public void testDelete4() throws SQLException {
        DatabaseCreator.fillFKCascadeTable(conn);
        statement.execute("DELETE FROM " + DatabaseCreator.PARENT_TABLE
                + " WHERE id = 3;");
    }

    /**
     * DeleteFunctionalityTest#testDelete5(). Attempts to delete row with
     *        referencing ones and CASCADE action - expecting all referencing
     *        rows will also be deleted
     */
    public void testDelete5() throws SQLException {
        statement.execute("DELETE FROM " + DatabaseCreator.PARENT_TABLE
                + " WHERE id = 1;");

        ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM "
                + DatabaseCreator.FKCASCADE_TABLE + " WHERE name_id = 1;");
        r.next();
        assertEquals("Should be no rows", 0, r.getInt(1));
        r.close();
    }

    /**
     * DeleteFunctionalityTest#testDelete6().
     *  TODO Foreign key functionality is not supported
     */
    @KnownFailure("not supported")
    public void testDelete6() throws SQLException {
        DatabaseCreator.fillFKStrictTable(conn);
        statement.execute("DELETE FROM " + DatabaseCreator.FKSTRICT_TABLE
                + " WHERE name_id = ANY (SELECT id FROM "
                + DatabaseCreator.PARENT_TABLE + " WHERE id > 1)");
        ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM "
                + DatabaseCreator.FKSTRICT_TABLE + " WHERE name_id = 1;");
        r.next();
        assertEquals("Should be 2 rows", 2, r.getInt(1));
        r.close();
    }

    /**
     * DeleteFunctionalityTest#testDelete7(). Deletes rows using
     *        PreparedStatement
     */
    public void testDelete7() throws SQLException {
        DatabaseCreator.fillTestTable5(conn);
        PreparedStatement stat = conn.prepareStatement("DELETE FROM "
                + DatabaseCreator.TEST_TABLE5 + " WHERE testID = ?");
        stat.setInt(1, 1);
        stat.execute();
        stat.setInt(1, 2);
        stat.execute();
        ResultSet r = statement.executeQuery("SELECT COUNT(*) FROM "
                + DatabaseCreator.TEST_TABLE5 + " WHERE testID < 3 ");
        r.next();
        assertEquals(0, r.getInt(1));
        r.close();
        stat.close();
    }
}
