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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import tests.support.DatabaseCreator;
import tests.support.Support_SQL;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UpdateFunctionalityTest extends TestCase {

    private static final int numberOfRecords = 20;

    private static Connection conn;

    private static Statement statement;

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
        DatabaseCreator.fillTestTable1(conn, numberOfRecords);
        DatabaseCreator.fillTestTable2(conn, numberOfRecords);
    }

    public void tearDown() throws Exception {
        deleteTestTables();
        statement.close();
        conn.close();

        super.tearDown();
    }

    protected void createTestTables() {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet userTab = meta.getTables(null, null, null, null);

            while (userTab.next()) {
                String tableName = userTab.getString("TABLE_NAME");
                if (tableName.equals(DatabaseCreator.TEST_TABLE1)) {
                    statement.execute(DatabaseCreator.DROP_TABLE1);
                } else if (tableName
                        .equals(DatabaseCreator.TEST_TABLE2)) {
                    statement.execute(DatabaseCreator.DROP_TABLE2);
                } else if (tableName
                        .equals(DatabaseCreator.TEST_TABLE3)) {
                    statement.execute(DatabaseCreator.DROP_TABLE3);
                }
            }
            userTab.close();

            statement.execute(DatabaseCreator.CREATE_TABLE3);
            statement.execute(DatabaseCreator.CREATE_TABLE2);
            statement.execute(DatabaseCreator.CREATE_TABLE1);

        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    protected void deleteTestTables() {
        try {
            statement.execute(DatabaseCreator.DROP_TABLE1);
            statement.execute(DatabaseCreator.DROP_TABLE2);
            statement.execute(DatabaseCreator.DROP_TABLE3);
        } catch (SQLException e) {
            fail("Unexpected SQLException " + e.toString());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate1(). Updates all values in one
     *        column in the table
     */
    public void testUpdate1() {
        String newValue = "newValue";
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field1='" + newValue + "'";
        try {
            int num = statement.executeUpdate(updateQuery);
            assertEquals("Not all records in the database were updated",
                    numberOfRecords, num);
            String selectQuery = "SELECT field1 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
                assertEquals("The field field1 was not updated", newValue,
                        result.getString("field1"));
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate2(). Updates values in one
     *        column in the table using where condition in update command
     */
    public void testUpdate2() {
        String newValue = "newValue";
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field1='" + newValue + "' WHERE (id > 2) and (id < 10)";
        try {
            int num = statement.executeUpdate(updateQuery);
            int expectedUpdated = 7;
            assertEquals("Not all records in the database were updated",
                    expectedUpdated, num);
            String selectQuery = "SELECT * FROM " + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
                int id = result.getInt("id");
                String field1 = result.getString("field1");
                if ((id > 2) && (id < 10)) {
                    assertEquals("The field field1 was not updated", newValue,
                            field1);
                } else {
                    assertEquals("The field field1 was not updated",
                            DatabaseCreator.defaultString + id, field1);
                }
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate3(). Updates values in a several
     *        columns in the table
     */
    public void testUpdate3() {
        int newValue1 = -1;
        int newValue2 = -2;
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2=" + newValue1 + ", field3=" + newValue2;
        try {
            int num = statement.executeUpdate(updateQuery);
            assertEquals("Not all records in the database were updated",
                    numberOfRecords, num);
            String selectQuery = "SELECT field2, field3 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
           // TODO getBigDecimal is not supported
//                assertEquals("The field field2 was not updated", newValue1,
//                        result.getBigDecimal("field2").intValue());
//                assertEquals("The field field3 was not updated", newValue2,
//                        result.getBigDecimal("field3").intValue());
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate4(). Updates values in a several
     *        columns in the table using where condition in update command
     */
    public void testUpdate4() {
        int newValue1 = -1;
        int newValue2 = -2;
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2=" + newValue1 + ", field3=" + newValue2
                + " WHERE id > 10";
        try {
            int num = statement.executeUpdate(updateQuery);
            int expectedUpdated = 9;
            assertEquals("Not all records in the database were updated",
                    expectedUpdated, num);
            String selectQuery = "SELECT id, field2, field3 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
                int id = result.getInt("id");
                // TODO getBigDecimal is not supported
//                int value2 = result.getBigDecimal("field2").intValue();
//                int value3 = result.getBigDecimal("field3").intValue();
//                if (id > expectedUpdated + 1) {
//                    assertEquals("The field field2 was not updated", newValue1,
//                            value2);
//                    assertEquals("The field field3 was not updated", newValue2,
//                            value3);
//                } else {
//                    assertEquals("The field field2 was not updated", id, value2);
//                    assertEquals("The field field3 was not updated", id, value3);
//                }
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate5(). Updates values in one
     *        columns in the table using condition
     */
    public void testUpdate5() {
        int factor = 3;
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2=field2 *" + factor;
        try {
            String selectQuery = "SELECT field2 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            HashSet<BigDecimal> values = new HashSet<BigDecimal>();
            // TODO getBigDecimal is not supported
//            while (result.next()) {
//                values.add(BigDecimal.valueOf(result.getBigDecimal("field2")
//                        .intValue()
//                        * factor));
//            }

            int num = statement.executeUpdate(updateQuery);
            assertEquals("Not all records in the database were updated",
                    numberOfRecords, num);
            result = statement.executeQuery(selectQuery);
            // TODO getBigDecimal is not supported
//            while (result.next()) {
//                BigDecimal value = result.getBigDecimal("field2");
//                assertTrue("Wrong value of field2 after update"
//                        + value.intValue(), values.remove(value));
//            }
            assertTrue("Not all records were updated", values.isEmpty());

            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate6(). Sets value of field2 to
     *        default
     */
    public void testUpdate6() {
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2='1'";
        try {

            int num = statement.executeUpdate(updateQuery);
            assertEquals("Not all records in the database were updated",
                    numberOfRecords, num);
            String selectQuery = "SELECT field2 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            // TODO getBigDecimal is not supported
//            while (result.next()) {
//                assertEquals("value of field2 should be default ",
//                        DatabaseCreator.defaultInt, result.getBigDecimal(
//                                "field2").intValue());
//            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate7(). Updates records in the
     *        table using subquery in update command
     */
    public void testUpdate7() {
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2='1' WHERE id < ( SELECT COUNT(*) FROM "
                + DatabaseCreator.TEST_TABLE2 + " WHERE finteger > 15)";
        try {
            int num = statement.executeUpdate(updateQuery);
            int expectedUpdated = 4;
            assertEquals("Not all records in the database were updated",
                    expectedUpdated, num);
            String selectQuery = "SELECT id, field2 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
           // TODO getBigDecimal is not supported
//                int value = result.getBigDecimal("field2").intValue();
//                int id = result.getInt("id");
//                if (id < expectedUpdated) {
//                    assertEquals("value of field2 should be default ",
//                            DatabaseCreator.defaultInt, value);
//                } else {
//                    assertEquals("wrong value of field2", id, value);
//                }
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    /**
     * UpdateFunctionalityTest#testUpdate8(). Sets value of field2 to
     *        NULL
     */
    public void testUpdate8() {
        String updateQuery = "UPDATE " + DatabaseCreator.TEST_TABLE1
                + " SET field2=NULL";
        try {

            int num = statement.executeUpdate(updateQuery);
            assertEquals("Not all records in the database were updated",
                    numberOfRecords, num);
            String selectQuery = "SELECT field2 FROM "
                    + DatabaseCreator.TEST_TABLE1;
            ResultSet result = statement.executeQuery(selectQuery);
            while (result.next()) {
                assertNull("value of field2 should be NULL", result
                        .getObject("field2"));
            }
            result.close();
        } catch (SQLException e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }
}
