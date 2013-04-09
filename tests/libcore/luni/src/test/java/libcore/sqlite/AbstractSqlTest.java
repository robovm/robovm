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

import SQLite.Exception;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.TestCase;


/**
 * This class provides SQL unit test, which can be used by subclasses eg. to
 * test JDBC drivers.
 */
abstract class AbstractSqlTest extends TestCase {

    /**
     * The first connection.
     */
    private Connection firstConnection;

    /**
     * The second connection.
     */
    private Connection secondConnection;

    /**
     * The statement from the first connection.
     */
    private Statement firstStmt;

    /**
     * The statement from the second connection.
     */
    private Statement secondStmt;

    /**
     * The values of the first column "one".
     */
    private final String[] ones = {"hello!", "goodbye"};

    /**
     * The values of the second column "two".
     */
    private final short[] twos = {10, 20};

    /**
     * The updated values of the first column "one".
     */
    private final String[] ones_updated;

    /** Creates a new instance of this class */
    public AbstractSqlTest() {
        super();
        ones_updated = new String[ones.length];
        for (int i = 0; i < ones.length; i++) {
            ones_updated[i] = ones[i] + twos[i];
        }
    }

    /**
     * Sets up a unit test, by creating two statements from two connections and
     * creating a test table.
     *
     * @exception SQLException if there is a problem accessing the database
     * @throws Exception
     * @exception Exception may be thrown by subclasses
     */
    @Override
    protected void setUp() throws java.lang.Exception {
        Class.forName(getDriverClassName()).newInstance();
        firstConnection = DriverManager.getConnection(getConnectionURL());
        firstConnection.setTransactionIsolation(getTransactionIsolation());
        secondConnection = DriverManager.getConnection(getConnectionURL());
        secondConnection.setTransactionIsolation(getTransactionIsolation());
        firstStmt = firstConnection.createStatement();
        firstStmt.execute("create table tbl1(one varchar(10), two smallint)");
        secondStmt = secondConnection.createStatement();
    }

    /**
     * Tears down a unit test, by setting the auto commit property of the first
     * connection back to true, dropping the test table and closing the two
     * connections.
     */
    @Override
    protected void tearDown() throws SQLException {
        firstStmt.close();
        secondStmt.close();
        firstConnection.setAutoCommit(true);
        firstStmt = firstConnection.createStatement();
        firstStmt.execute("drop table tbl1");
        firstStmt.close();
        firstConnection.close();
        secondConnection.close();
    }

    /**
     * Adds some rows to the test table and asserts that the rows can be
     * retrieved again.
     *
     * @throws SQLException if there is a problem accessing the database
     */
    private void autoCommitInsertSelect() throws SQLException {
        firstStmt.getConnection().setAutoCommit(true);
        for (int i = 0; i < ones.length; i++) {
            firstStmt.execute("insert into tbl1 values('" + ones[i] + "',"
                    + twos[i] + ")");
        }
        assertAllFromTbl1(firstStmt, ones, twos);
    }

    /**
     * Asserts that the expected values can be selected from the test table.
     *
     * @param stmt the statement to be used for the selection of the data
     * @param ones the expected values of the column 'one'
     * @param twos the expected values of the column 'two'
     * @throws SQLException if there is a problem accessing the database
     */
    private void assertAllFromTbl1(Statement stmt, String[] ones, short[] twos)
            throws SQLException {
        ResultSet rs = stmt.executeQuery("select * from tbl1");
        int i = 0;
        for (; rs.next(); i++) {
            assertTrue(i < ones.length);
            assertEquals(ones[i], rs.getString("one"));
            assertEquals(twos[i], rs.getShort("two"));
        }
        assertTrue(i == ones.length);
    }

    public void testAutoCommitInsertSelect() throws SQLException{
        autoCommitInsertSelect();
    }

    /**
     * Tests the following sequence after successful insertion of some test
     * data:
     * - update data from connection one
     * - select data from connection two (-> should have the old values)
     * - commit data from connection one
     * - select data from connection two (-> should have the new values)
     *
     * @throws SQLException if there is a problem accessing the database
     */
    public void testUpdateSelectCommitSelect() throws SQLException {
        autoCommitInsertSelect();
        firstStmt.getConnection().setAutoCommit(false);
        updateOnes(firstStmt, ones_updated, twos);
        assertAllFromTbl1(secondStmt, ones, twos);
        firstStmt.getConnection().commit();
        assertAllFromTbl1(secondStmt, ones_updated, twos);
    }

    /**
     * Tests the following sequence after successful insertion of some test
     * data:
     * - update data from connection one
     * - select data from connection two (-> should have the old values)
     * - rollback data from connection one
     * - select data from connection two (-> should still have the old values)
     *
     * @throws SQLException if there is a problem accessing the database
     */
    public void testUpdateSelectRollbackSelect() throws SQLException {
        autoCommitInsertSelect();
        firstStmt.getConnection().setAutoCommit(false);
        updateOnes(firstStmt, ones_updated, twos);
        assertAllFromTbl1(secondStmt, ones, twos);
        firstStmt.getConnection().rollback();
        assertAllFromTbl1(secondStmt, ones, twos);
    }

    /**
     * Updates the values in column 'one'
     * @param stmt the statement to be used to update the data
     * @param ones_updated the updated valus of column 'one'
     * @param twos the reference values of column 'two'
     * @throws SQLException if there is a problem accessing the database
     */
    private void updateOnes(Statement stmt, String[] ones_updated, short[] twos)
            throws SQLException {
        for (int i = 0; i < ones_updated.length; i++) {
            stmt.execute("UPDATE tbl1 SET one = '" + ones_updated[i]
                    + "' WHERE two = " + twos[i]);
        }
    }

    protected abstract String getConnectionURL();

    protected abstract String getDriverClassName();

    protected abstract int getTransactionIsolation();

}
