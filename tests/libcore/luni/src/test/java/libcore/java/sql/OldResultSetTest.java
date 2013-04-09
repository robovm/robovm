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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tests.support.DatabaseCreator;

public final class OldResultSetTest extends OldSQLTest {

    ResultSet target = null;
    ResultSet emptyTarget = null;
    ResultSet scrollableTarget = null;
    ResultSet writableTarget = null;
    Statement stForward = null;
    Statement stScrollable = null;
    Statement stWritable = null;
    final String selectAllAnimals = "select id, name from zoo";
    final String selectEmptyTable = "select * from "+DatabaseCreator.SIMPLE_TABLE1;

    @Override public void setUp() throws Exception {
        super.setUp();

        conn.setAutoCommit(false);
        stForward = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        stForward.execute(selectAllAnimals);
        target = stForward.getResultSet();
        assertNotNull(target);

        // empty table
        stForward = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        stForward.execute(DatabaseCreator.CREATE_TABLE_SIMPLE1);
        stForward.execute(selectEmptyTable);
        emptyTarget = stForward.getResultSet();
    }

    public void tearDown() throws SQLException {
        super.tearDown();
        target.close();
        stForward.close();
    }

    public void testAbsolute() throws SQLException {
        assertTrue(target.isBeforeFirst());
        assertFalse(target.absolute(0));
        assertTrue(target.absolute(1));
        assertTrue(target.isFirst());
        assertTrue(target.absolute(-1));
        assertTrue(target.isLast());
        target.next();
        assertTrue(target.isAfterLast());
    }

    // res.close() does not wrap up
    public void testAfterLast() throws SQLException {
        target.afterLast();
        assertTrue(target.isAfterLast());
        assertFalse(target.next());

        emptyTarget.afterLast();
        assertFalse(emptyTarget.isAfterLast());

        try {
            target.close();
            target.beforeFirst();
            fail("Should get SQLException");
        } catch (SQLException e) {
        }
    }

    // statement.close() does not wrap up
    public void testBeforeFirst() throws SQLException {
        target.beforeFirst();
        assertTrue(target.isBeforeFirst());
        assertTrue(target.next());
        assertFalse(target.isBeforeFirst());

        emptyTarget.beforeFirst();
        assertFalse(emptyTarget.isBeforeFirst());

        try {
            target.close();
            target.beforeFirst();
            fail("Should get SQLException");
        } catch (SQLException e) {
        }
    }

    /**
     * According to the JDBC spec close has to "Releases this ResultSet
     * object's database and JDBC resources immediately", and this implies
     * the fields should be released as well (so that garbage collection
     *  can take place)
     */
    public void testClose1() {
        try {
            target.close();
            target.next();
            fail("Should get SQLException");
        } catch (SQLException e) {
            //ok
        }
    }

    /**
     * Test that exception in one prepared statement does not affect second
     * statement. (Atomicity Rule)
     */
    public void testClose() throws SQLException {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            Statement s = conn.createStatement();
            s.addBatch("create table t1 (a text);");

            s.addBatch("insert into t1 values('abc');");
            s.addBatch("insert into t1 values('def');");
            s.addBatch("insert into t1 values('ghi');");
            s.executeBatch();
            s.close();

            conn.commit();
            ps1 = conn.prepareStatement("select * from t1");
            ps2 = conn.prepareStatement("select * from t1 whe a like '?000'");

            ResultSet rs1 = ps1.executeQuery();
            try {
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    // do nothing
                }
                fail("Should get SQLException");
            } catch (SQLException sqle) {
                // ok : Division by zero
            }

            // Although exception happened on ps2 rs1 should still work
            // Isolation property if ACID rules

            while (rs1.next()) {
                // do nothing: switching of rows should be possible
            }

            conn.commit();

            rs1.close();
            ps1.close();
            ps2.close();
        } finally {
            try {
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                conn.rollback();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void testFindColumn() throws SQLException {
        assertEquals(1, target.findColumn("id"));
        assertEquals(2, target.findColumn("name"));

        try {
            target.findColumn("bla");
            fail("Should get SQLException");
        } catch (SQLException e) {
            // ok
        }
    }

    // statement.close() does not wrap up
    public void testtestFirst() throws SQLException {
        assertFalse(emptyTarget.first());
        assertTrue(target.first());

        try {
            target.close();
            // releases all resources such that it can be finalized!
            target.first();
            fail("Should get SQLException");
        } catch (SQLException e) {
        }
    }

    // statement.close() does not wrap up
    public void testtestIsAfterLast() throws SQLException {
        assertFalse(target.isAfterLast());
        target.absolute(-1); // last
        target.next();
        assertTrue(target.isAfterLast());
        assertFalse(emptyTarget.isAfterLast());

        try {
            target.close();
            // releases all resources such that it can be finalized!
            target.isAfterLast();
            fail("Should get SQLException");
        } catch (SQLException e) {
        }
    }

    // In Second code block assertion fails. statement.close() does not wrap up
    public void testtestIsBeforeFirst() throws SQLException {
        assertTrue(target.isBeforeFirst());
        assertTrue(target.next());
        assertFalse(target.isBeforeFirst());
        assertTrue(target.isFirst());
        assertTrue(emptyTarget.isBeforeFirst());

        try {
            target.close();
            // releases all resources such that it can be finalized!
            target.isBeforeFirst();
            fail("Should get SQLException");
        } catch (SQLException e) {
            //ok
        }
    }

    // statement.close() does not wrap up
    public void testtestIsFirst() throws SQLException {
        assertFalse(target.isFirst());
        target.first();
        assertTrue(target.isFirst());
        target.next();
        assertFalse(target.isFirst());

        assertFalse(emptyTarget.isFirst());

        try {
            target.close();
            // releases all resources such that it can be finalized!
            target.isFirst();
            fail("Should get SQLException");
        } catch (SQLException e) {
        }
    }

    /**
     * Second block first assertion fails. Is Last should evaluate true if the
     * row on which the cursor is actually provides a result.statment.close()
     * does not wrap up
     */
    public void testtestIsLast() throws SQLException {
        assertFalse(target.isLast());
        target.absolute(-1);
        assertTrue(target.isLast());

        //check default value no valid row
        assertFalse(emptyTarget.isLast());
        assertFalse(emptyTarget.next());
        assertFalse(emptyTarget.isLast());

        try {
            target.close();
            target.isLast();
            fail("Should get SQLException");
        } catch (SQLException e) {
            // ok
        }
    }

    // statement.close() does not wrap up
    public void testtestLast() throws SQLException {
        assertFalse(target.isLast());
        target.last();
        assertTrue(target.isLast());

        try {
            target.close();
            target.last();
            fail("Should get SQLException");
        } catch (SQLException e) {
            // ok
        }
    }

    /**
     * SQLException checking test fails. Clearing of warnings and closed streams
     * not supported.
     */
    public void testNext() throws SQLException {
        //before first - first
        assertTrue(target.next());
        //first - second
        assertTrue(target.next());
        //after last
        assertFalse(target.next());
        assertTrue(target.isAfterLast());
        // one more
        assertFalse(target.next());

        assertFalse(emptyTarget.next());

        target.close();
        try {
            target.next();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    public void testPrevious() throws SQLException {
        target.first();
        target.previous();
        assertTrue(target.isBeforeFirst());

        target.last();
        target.next();
        target.previous();
        assertFalse(target.isAfterLast());

        target.close();
        try {
            target.previous();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // no exception is thrown when moving cursor backwards on forward only statement
    public void testRelative() throws SQLException {

        // forward only
        int initialRow = target.getRow();
        assertFalse(target.relative(0));
        assertEquals(initialRow, target.getRow());

        assertTrue(target.relative(1));
        assertTrue(target.isFirst());
        assertEquals(1, target.getRow());

        assertTrue(target.relative(1));
        assertFalse(target.isFirst());
        assertEquals(2, target.getRow());
        assertFalse(target.relative(2));

        try {
            // should not be able to scroll backwards in forward only RS
            target.relative(-2);
            assertEquals(2,target.getRow());
            fail("Should get SQLException");
        } catch (SQLException e) {
            // ok
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertFalse(emptyTarget.relative(Integer.MAX_VALUE));
        assertTrue(emptyTarget.isAfterLast());
    }

    // Scrollable resultSet. Not supported
    public void testRelativeScrollableResultSet() throws SQLException {
     // scrollable resultSet
        int initialRow = scrollableTarget.getRow();
        assertFalse(scrollableTarget.relative(0));
        assertEquals(initialRow, scrollableTarget.getRow());

        assertTrue(scrollableTarget.relative(1));
        assertTrue(scrollableTarget.isFirst());
        assertEquals(1, scrollableTarget.getRow());

        assertTrue(scrollableTarget.relative(1));
        assertFalse(scrollableTarget.isFirst());

        assertEquals(2, scrollableTarget.getRow());
        assertFalse(scrollableTarget.relative(2));
        scrollableTarget.relative(-2);
        assertEquals(2,scrollableTarget.getRow());

        assertFalse(scrollableTarget.relative(Integer.MIN_VALUE));
        assertTrue(scrollableTarget.isBeforeFirst());

        stScrollable.close();
        try {
            scrollableTarget.relative(1);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // not supported
    public void testUpdateObjectStringObject() throws SQLException {
        writableTarget.next();
        writableTarget.updateObject("family","bird");

        try {
           target.next();
           target.updateObject("family","bird");
           fail("SQLException was not thrown");
        } catch (SQLException e) {
           fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Only exception testing. Missing testing for wrong type
     */
    public void testUpdateStringStringString() throws Exception {
        writableTarget.next();
        writableTarget.updateString("family","bird");

         // non writable target.
         try {
            target.next();
            target.updateString("family","bird");
            fail("SQLException was not thrown");
         } catch (SQLException e) {
            //ok
         }


         // writable but wrong type
        target.updateString(1,"test");

        target.close();

        // Exception test
        try {
            target.updateString("family", "test");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    /**
     * Test method for {@link java.sql.ResultSet#wasNull()}.
     * Spec sais: if something was read... -> if nothing was read it should be false
     */
    public void testWasNull() throws SQLException {
        // Check default: select statement executed but no get on target called yet
        // Either false or throw an exception.
        try {
            assertFalse(target.wasNull());
        } catch (SQLException e) {
            //ok
        }

        stForward.execute("insert into zoo values(8,null,null);");
        stForward.execute(selectAllAnimals);
        target = stForward.getResultSet();
        assertNotNull(target);
        assertTrue(target.last());
        assertNull(target.getObject(2));
        assertTrue(target.wasNull());
        assertNotNull(target.getObject(1));
        assertFalse(target.wasNull());

        target.close();
        try {
            target.wasNull();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }
}
