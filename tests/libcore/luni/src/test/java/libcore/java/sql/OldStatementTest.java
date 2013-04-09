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

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

public final class OldStatementTest extends OldSQLTest {

    public void testAddBatch() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.addBatch("INSERT INTO zoo VALUES (3,'Tuzik','dog')");
            st.addBatch("INSERT INTO zoo VALUES (4,'Mashka','cat')");

            int[] updateCounts = st.executeBatch();
            assertEquals(2, updateCounts.length);
            assertEquals(1, updateCounts[0]);
            assertEquals(1, updateCounts[1]);
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.addBatch("");
            st.executeBatch();
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.addBatch(null);
            st.executeBatch();
        } catch (SQLException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    public void testClearWarnings() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute("select animals from zoo");
        } catch (SQLException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
        try {
            st = conn.createStatement();
            st.clearWarnings();
            SQLWarning w = st.getWarnings();
            assertNull(w);
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    public void testGetWarnings() throws SQLException {

        Statement st = null;
        int errorCode1 = -1;
        int errorCode2 = -1;

        try {
            st = conn.createStatement();
            st.execute("select animals from zoooo");
            fail("SQLException was not thrown");
        } catch (SQLException e) {
            // expected
            errorCode1 = e.getErrorCode();
        }

        SQLWarning wrs = st.getWarnings();
        assertNull(wrs);

        /*
        Statement st = null;
        int errorCode1 = -1;
        int errorCode2 = -1;

        try {
            st = conn.createStatement();
            st.execute("select animals from zoooo");
        } catch (SQLException e) {
            // expected
            errorCode1 = e.getErrorCode();
        }
        try {
            SQLWarning wrs = st.getWarnings();
            assertNull(wrs);
        } catch (Exception e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
        try {
            st.execute("select horse from zoooooo");
        } catch (SQLException e) {
            // expected
            errorCode2 = e.getErrorCode();
        }

        try {
            SQLWarning wrs = st.getWarnings();
            assertEquals(errorCode1, wrs.getErrorCode());
            assertNotNull(wrs.getNextWarning());
            assertEquals(errorCode2, wrs.getErrorCode());
        } catch (Exception e) {
            fail("Unexpected Exception: " + e.getMessage());
        }

        try {
            st.close();
        } catch (SQLException ee) {
        }
        */

    }

    public void testClearBatch() throws SQLException {
        Statement st = null;

        try {
            st = conn.createStatement();
            st.addBatch("INSERT INTO zoo VALUES (3,'Tuzik','dog'); ");
            st.addBatch("INSERT INTO zoo VALUES (4,'Mashka','cat')");

            st.clearBatch();

            int[] updateCounts = st.executeBatch();

            for (int i = 0; i < updateCounts.length; i++) {
                assertEquals(0, updateCounts[i]);
            }
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.addBatch("");
            st.executeBatch();
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.addBatch(null);
            st.executeBatch();
        } catch (SQLException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // TODO not pass on SQLite and RI.
    public void testExecute() throws SQLException {

        String[] queries = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "select animal_id, address from hutch where animal_id=1;",
                "create view address as select address from hutch where animal_id=2",
                "drop view address;", "drop table hutch;" };
        boolean[] results = {false, false, false, false, false, true, false,
                false, false};

        for (int i = 0; i < queries.length; i++) {
            Statement st = null;
            try {
                st = conn.createStatement();
                boolean res = st.execute(queries[i]);
                assertEquals("different result for statement no. "+i, results[i], res);
            } catch (SQLException e) {
                fail("SQLException is thrown: " + e.getMessage());
            } finally {
                try {
                    st.close();
                } catch (Exception ee) {
                }
            }
        }

        String[] inc_queries = {
                "update zoo_zoo set name='Masha', family='cat' where id=5;",
                "drop table hutchNO",
                "insert into hutch (id, animal_id, address) values (1, 2, 10);",
                "select animal_id, from hutch where animal_id=1;",
                "drop view address;", "drop table hutch;", "", null };

        for (int i = 0; i < inc_queries.length; i++) {
            Statement st = null;
            try {
                st = conn.createStatement();
                st.execute(inc_queries[i]);
                fail("SQLException is not thrown for query: " + inc_queries[i]);
            } catch (SQLException e) {
                // expected
            } finally {
                try {
                    st.close();
                } catch (SQLException ee) {
                }
            }
        }
    }

    // TODO not supported
   public void testExecute_String_int() {
        String[] queries = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "select animal_id, address from hutch where animal_id=1;",
                "create view address as select address from hutch where animal_id=2",
                "drop view address;", "drop table hutch;" };

        for (int i = 0; i < queries.length; i++) {
            Statement st = null;
            try {
                st = conn.createStatement();
                st.execute(queries[i], Statement.NO_GENERATED_KEYS);

                ResultSet rs = st.getGeneratedKeys();
                assertFalse(rs.next());

            } catch (SQLException e) {
                // ok
            } finally {
                try {
                    st.close();
                } catch (SQLException ee) {
                }
            }
        }

        for (int i = 0; i < queries.length; i++) {
            Statement st = null;
            try {
                st = conn.createStatement();
                st.execute(queries[i], Statement.RETURN_GENERATED_KEYS);
                fail("Exception expected: Not supported");
                /*
                ResultSet rs = st.getGeneratedKeys();
                fail("Revise test implemenation for feature impl. has changed");
                assertFalse(rs.next());
                */
            } catch (SQLException e) {
                //ok
            } finally {
                try {
                    st.close();
                } catch (SQLException ee) {
                }
            }
        }
    }

    // statement.close() does not wrap up
    public void testGetConnection() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            assertSame(conn, st.getConnection());
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st.close();
            st.getConnection();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // statement.close() does not wrap up
    public void testGetFetchDirection() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            assertEquals(ResultSet.FETCH_UNKNOWN, st.getFetchDirection());
        }  finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.setFetchDirection(ResultSet.FETCH_FORWARD);
            assertEquals(ResultSet.FETCH_FORWARD, st.getFetchDirection());
            fail("Exception expected: not supported");
        } catch (SQLException e) {
            // ok
        }  finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st.getFetchDirection();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // TODO not supported
    public void testSetFetchDirection() {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.setFetchDirection(ResultSet.FETCH_FORWARD);
            st.executeQuery("select * from zoo;");
            fail("Revise test implemenation for feature impl. has changed");
//            assertEquals(ResultSet.FETCH_FORWARD, st.getFetchDirection());
        } catch (SQLException e) {
//            fail("SQLException is thrown: " + e.getMessage());
            //ok
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // statement.close() does not wrap up
    public void testGetFetchSize() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute("select * from zoo;");
            assertEquals(1, st.getFetchSize());
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st.close();
            st.getFetchSize();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // TODO not supported
    public void testSetFetchSize() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            int rows = 100;
            for (int i = 0; i < rows; i++) {
                try {
                    st.setFetchSize(i);
                    assertEquals(i, st.getFetchSize());
                } catch (SQLException sqle) {
                    // getFetchSize() hardcoded to 1.
                    assertEquals("fetch size not 1", sqle.getMessage());
                }
            }
            /*
            try {
                st.setFetchSize(-1);
                fail("SQLException is not thrown");
            } catch (SQLException sqle) {
                // expected
            }
            */

        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // TODO not supported
    public void testSetMaxFieldSize() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            for (int i = 0; i < 300; i += 50) {
                try {
                    st.setMaxFieldSize(i);
                    assertEquals(i, st.getMaxFieldSize());
                    fail("Revise test implemenation for feature impl. has changed");
                } catch (SQLException sqle) {
                    assertEquals("not supported", sqle.getMessage());
                }
            }
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // TODO not supported
    public void testGetMaxFieldSize() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            for (int i = 200; i < 500; i += 50) {
                try {
                    st.setMaxFieldSize(i);
                    fail("Revise test implemenation for feature impl. has changed");
                } catch (SQLException sqle) {
                    assertEquals("not supported", sqle.getMessage());
                }
            }
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    public void testMaxRows() throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            for (int i = 0; i < 300; i += 50) {
                st.setMaxRows(i);
                assertEquals(i, st.getMaxRows());
                ResultSet r = st.executeQuery("select * from zoo;");
                int rowCount = 0;
                while (r.next()) {
                    ++rowCount;
                }
                if (i == 0) {
                    // 0 means unlimited.
                    assertTrue("rowCount=" + rowCount + " i=" + i, rowCount > i);
                } else {
                    assertTrue("rowCount=" + rowCount + " i=" + i, rowCount <= i);
                }
                r.close();
            }
            try {
                st.setMaxRows(-1);
                fail("SQLException isn't thrown");
            } catch (SQLException sqle) {
                // expecteds
            }
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    /** not passed according to spec; should release resources immediately */
    public void testClose() throws SQLException {
        Statement st = null;
        ResultSet res = null;
        try {
            String[] queries = {
                    "update zoo set name='Masha', family='cat' where id=2;",
                    "insert into zoo (id, name, family) values (3, 'Vorobey', 'sparrow');",
                    "insert into zoo (id, name, family) values (4, 'Slon', 'elephant');",
                    "select * from zoo"};
            st = conn.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.execute(queries[i]);
            }
            res = st.getResultSet();
            assertNotNull(res);
            assertTrue(res.next());
            st.close();
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        // test release of resources:
        // this code should throw an exception as the db is not available
        // anymore in fact every resource which is used afterwards should throw
        // an SQLException.
        try {
            res.next();
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    // TODO not supported
    public void testExecute_String_intArray() {
        Statement st = null;
        try {
            String[] queries = {
                    "update zoo set name='Masha', family='cat' where id=2;",
                    "insert zoo(id, name, family) values (3, 'Vorobey', 'sparrow');",
                    "insert zoo(id, name, family) values (4, 'Slon', 'elephant');",
                    "select * from zoo" };
            Vector<int[]> array = new Vector<int[]>();
            array.addElement(null);
            array.addElement(new int[] { 1, 2, 3 });
            array.addElement(new int[] { 1, 2, 10, 100 });
            array.addElement(new int[] {});

            st = conn.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.execute(queries[i], (int[]) array.elementAt(i));
                fail("SQLException expected: not supported");
            }
            /*
            fail("Revise test implemenation for feature impl. has changed");
            assertNotNull(st.getResultSet());
            st.close();
            assertNull(st.getResultSet());
            */
        } catch (SQLException e) {
            // ok: not supported
//            fail("SQLException is thrown: " + e.getMessage());
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    public void testExecute_String_StringArray() {
        Statement st = null;
        try {
            String[] queries = {
                    "update zoo set name='Masha', family='cat' where id=2;",
                    "insert zoo(id, name, family) values (3, 'Vorobey', 'sparrow');",
                    "insert zoo(id, name, family) values (4, 'Slon', 'elephant');",
                    "select * from zoo" };
            Vector<String[]> array = new Vector<String[]>();
            array.addElement(null);
            array.addElement(new String[] { "", "", "", "", "", "", "", "" });
            array.addElement(new String[] { "field 1", "", "field2" });
            array.addElement(new String[] { "id", "family", "name" });

            st = conn.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.execute(queries[i], (String[]) array.elementAt(i));
                fail("Exception expected: not supported");
            }
            fail("Revise test implemenation for feature impl. has changed");
            assertNotNull(st.getResultSet());
            st.close();
            assertNull(st.getResultSet());
        } catch (SQLException e) {
            // ok: not supported
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // always returns 1 for no. of updates
    public void testExecuteBatch() throws SQLException {

        String[] queries = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "create view address as select address from hutch where animal_id=2",
                "drop view address;", "drop table hutch;" };

        String[] wrongQueries = {
                "update zoo set name='Masha', family='cat' where;",
                "drop table if exists hutch;",
                "create view address as select address from hutch where animal_id=2;",
                "drop view address;", "drop table hutch;" };

        int[] result = { 1, 1, 1, 1, 1, 1, 1, 1 };
        Statement st = null;

        //Exception test
        try {
            st = conn.createStatement();
            assertEquals(0, st.executeBatch().length);
            for (int i = 0; i < wrongQueries.length; i++) {
                st.addBatch(wrongQueries[i]);
            }
            st.executeBatch();
            fail("BatchupdateException expected");
        } catch (BatchUpdateException e) {
            //ok
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            assertEquals(0, st.executeBatch().length);
            for (int i = 0; i < queries.length; i++) {
                st.addBatch(queries[i]);
            }
            int[] resArray = st.executeBatch();
            assertTrue(java.util.Arrays.equals(result, resArray));
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st = conn.createStatement();
            st.addBatch("select * from zoo");
            st.executeBatch();
            fail("Exception expected");
        } catch (BatchUpdateException bue) {
            // ok select returns a resultSet
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
        //Exception test
        try {
            st.close();
            st.executeBatch();
            fail("SQLException not thrown");
        } catch (SQLException e) {
            //ok
        }
    }

    // Does throw an exception on non select statement.
    public void testExecuteQuery_String() throws SQLException {

        String[] queries1 = { "select * from zoo",
                "select name, family from zoo where id = 1" };

        String[] queries2 = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "create view address as select address from hutch where animal_id=2",
                "drop view address;", "drop table hutch;", "select from zoo" };

        Statement st = null;

        try {
            st = conn.createStatement();
            for (int i = 0; i < queries1.length; i++) {
                try {
                    ResultSet rs = st.executeQuery(queries1[i]);
                    assertNotNull(rs);
                } catch (SQLException sqle) {
                    fail("SQLException is thrown for query: " + queries1[i]);
                }
            }
        } finally {
            try {
                st.close();
            } catch (Exception ee) {
            }
        }

        // queries which do not produce a ResultSet -> exception testing

        try {
            st = conn.createStatement();
            for (int i = 0; i < queries2.length; i++) {
                try {
                    ResultSet rs = st.executeQuery(queries2[i]);
                    assertNotNull(rs);
                    fail("SQLException is not thrown for query: " + queries2[i]);
                } catch (SQLException sqle) {
                    // expected
                }
            }
        } finally {
            try {
                st.close();
            } catch (Exception ee) {
            }
        }
    }

    /**
     * Spec is not precise enough: should be: number of rows affected. eg. to be
     * consistent for deletes: 'delete from s1;' should be different from
     * 'delete from s1 where c1 = 1;'
     */
    public void testExecuteUpdate_String() throws SQLException {

        String[] queries1 = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "create view address as select address from hutch where animal_id=2;",
                "drop view address;", "drop table hutch;"};

        String queries2 = "select * from zoo;";

        Statement st = null;
        try {
            st = conn.createStatement();
            for (int i = 0; i < queries1.length; i++) {
                int count = st.executeUpdate(queries1[i]);
                assertTrue(count > 0);
            }

            assertEquals(0, st.executeUpdate(queries2));

        } finally {
            try {
                st.close();
            } catch (Exception ee) {
            }
        }

        // test return value for specific numbers

        Statement stat = conn.createStatement();

        // there are 0 rows created therefore 0 should be returned.
        assertEquals(0 ,stat.executeUpdate("create table s1 (c1);"));

        assertEquals(1, stat.executeUpdate("insert into s1 values (0);"));
        assertEquals(1, stat.executeUpdate("insert into s1 values (1);"));
        assertEquals(1, stat.executeUpdate("insert into s1 values (2);"));
        assertEquals(1,stat.executeUpdate("delete from s1 where c1 = 1;"));
        assertEquals(2, stat.executeUpdate("update s1 set c1 = 5;"));

        // analogous to statement before, delete all should return 2
        assertEquals(2,stat.executeUpdate("delete from s1;"));

        // there are no rows in table: 0 should be returned
        assertEquals(0, stat.executeUpdate("drop table s1;"));

        stat.executeUpdate("create table s1 (c1);");
        stat.executeUpdate("insert into s1 values (0);");
        stat.executeUpdate("insert into s1 values (1);");
        stat.executeUpdate("insert into s1 values (2);");

        // there are 3 rows in table: 3 should be returned
        assertEquals(3, stat.executeUpdate("drop table s1;"));

        stat.close();
    }

    // TODO executeUpdate(String sql, int[] columnIndexes) is not supported
    public void testExecuteUpdate_String_intArray() throws SQLException {
        Statement st = null;
        try {
            String[] queries1 = {
                    "update zoo set name='Masha', family='cat' where id=2;",
                    "drop table if exists hutch",
                    "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                    "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                    "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                    "create view address as select address from hutch where animal_id=2",
                    "drop view address;", "drop table hutch;" };

            Vector<int[]> array = new Vector<int[]>();
            array.addElement(null);
            array.addElement(new int[] { 1, 2, 3 });
            array.addElement(new int[] { 1, 2, 10, 100 });
            array.addElement(new int[] {});
            array.addElement(new int[] { 100, 200 });
            array.addElement(new int[] { -1, 0 });
            array.addElement(new int[] { 0, 0, 0, 1, 2, 3 });
            array.addElement(new int[] { -100, -200 });

            st = conn.createStatement();
            for (int i = 0; i < queries1.length; i++) {
                st.executeUpdate(queries1[i], (int[]) array.elementAt(i));
                fail("Exception expected");
            }
        } catch (SQLFeatureNotSupportedException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // executeUpdate(String sql, int autoGeneratedKeys) is not supported
    public void testExecuteUpdate_String_int() {
        String[] queries = {
                "update zoo set name='Masha', family='cat' where id=2;",
                "drop table if exists hutch",
                "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                "select animal_id, address from hutch where animal_id=1;",
                "create view address as select address from hutch where animal_id=2",
                "drop view address;", "drop table hutch;" };

            Statement st = null;
            ResultSet rs = null;
            try {
                st = conn.createStatement();
                st.executeUpdate(queries[1], Statement.NO_GENERATED_KEYS);
                rs = st.getGeneratedKeys();
                assertFalse(rs.next());
                fail("Exception expected: not supported");
            } catch (SQLException e) {
                //ok
            } finally {
                try {
                    rs.close();
                    st.close();
                } catch (Exception ee) {
                }
            }

            try {
                st = conn.createStatement();
                st.executeUpdate(queries[1], Statement.RETURN_GENERATED_KEYS);
                rs = st.getGeneratedKeys();
                assertTrue(rs.next());
                fail("Exception expected: not supported");
            } catch (SQLException e) {
                //ok
            } finally {
                try {
                    rs.close();
                    st.close();
                } catch (Exception ee) {
                }
            }
    }

    // TODO executeUpdate(String sql, String[] columnNames) is not supported
    public void testExecuteUpdate_String_StringArray() throws SQLException {
        Statement st = null;
        try {
            String[] queries = {
                    "update zoo set name='Masha', family='cat' where id=2;",
                    "drop table if exists hutch",
                    "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));",
                    "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');",
                    "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');",
                    "create view address as select address from hutch where animal_id=2",
                    "drop view address;", "drop table hutch;" };

            Vector<String[]> array = new Vector<String[]>();
            array.addElement(null);
            array.addElement(new String[] { "", "", "", "", "", "", "", "" });
            array.addElement(new String[] { "field 1", "", "field2" });
            array.addElement(new String[] { "id", "family", "name" });
            array
                    .addElement(new String[] { "id", null, "family", null,
                            "name" });
            array.addElement(new String[] { "id", " ", "name" });
            array.addElement(new String[] { null, null, null, null });
            array.addElement(new String[] { " ", "123 21", "~!@#$%^&*()_+ ",
                    null });

            st = conn.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.executeUpdate(queries[i], (String[]) array.elementAt(i));
                fail("Revise test implemenation for feature impl. has changed");
            }
        } catch (SQLFeatureNotSupportedException e) {
            // expected
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
    }

    // statement.close() does not wrap up
    public void testGetUpdateCount() throws SQLException {
        Statement st = null;
        try {
            String query = "update zoo set name='Masha', family='cat' where id=2;";
            st = conn.createStatement();
            st.executeUpdate(query);
            assertEquals(1, st.getUpdateCount());
            query = "update zoo set name='Masha', family='cat' where id=5;";
            st.executeUpdate(query);
            assertEquals(0, st.getUpdateCount());
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }
        // statment closed : Exception test
        try {
            st.getUpdateCount();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    public void testGeneratedKeys() throws SQLException {
        Statement st = null;
        try {
            String insert = "insert into zoo (id, name, family) values (8, 'Tuzik', 'dog');";
            st = conn.createStatement();
            assertNull(st.getGeneratedKeys());
            fail("Fail: statement does not fail");
        } catch (SQLFeatureNotSupportedException e) {
            // expected
        }
    }

    // TODO setCursorName() is not supported
    public void testSetCursorName() throws SQLException {
        Statement st = null;
        try {
            String select = "select * from zoo";
            st = conn.createStatement();
            st.setCursorName("test");
            fail("Fail: statement does not fail");
        } catch (SQLFeatureNotSupportedException e) {
            // expected
        }
    }

    // TODO setEscapeProcessing() is not supported
    public void testSetEscapeProcessing() {
        Statement st = null;
        try {
            String select = "select * from zoo";
            st = conn.createStatement();
            st.setEscapeProcessing(true);
            fail("Fail: statement does not fail");
        } catch (SQLException e) {
          assertEquals("not supported", e.getMessage());
        }
    }

    public void testSetQueryTimeout() throws SQLException {
        Statement st = conn.createStatement();
        st.setQueryTimeout(2);
        assertEquals(2, st.getQueryTimeout());

        try {
            st = conn.createStatement();
            st.setQueryTimeout(-1);
            fail("SQLException not thrown");
        } catch (SQLException expected) {
            // expected
        }

        try {
            st = conn.createStatement();
            st.close();
            st.setQueryTimeout(3);
            fail("SQLException not thrown");
        } catch (SQLException expected) {
            // expected
        }
    }

    // not fully supported
    public void testGetResultSetType() {
        Statement st = null;
        // test default value
        try {
            st = conn.createStatement();
            st.getResultSetType();
            assertEquals(ResultSet.TYPE_SCROLL_INSENSITIVE, st
                    .getResultSetType());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }

        // failing tests
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            st.getResultSetType();
            assertEquals(ResultSet.TYPE_SCROLL_SENSITIVE, st.getResultSetType());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }

        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            st.getResultSetType();
            assertEquals(ResultSet.TYPE_SCROLL_SENSITIVE, st.getResultSetType());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }

        try {
            st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);
            st.getResultSetType();
            assertEquals(ResultSet.TYPE_FORWARD_ONLY, st.getResultSetType());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }
    }

    // Test for default value fails
    public void testGetResultSetHoldability() {

        // test default value
        Statement st = null;
        try {
            st = conn.createStatement();
            assertEquals(ResultSet.CLOSE_CURSORS_AT_COMMIT, st
                    .getResultSetHoldability());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }

        // failing tests
        try {
            conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            fail("Exception expected: not supported");
        } catch (SQLException e) {
            // ok: not supported
        }

        try {
            conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            fail("Exception expected: not supported");
            /*
            st.getResultSetHoldability();
            assertEquals(ResultSet.CLOSE_CURSORS_AT_COMMIT, st
                    .getResultSetHoldability());
            */
        } catch (SQLException expected) {
        }
    }

    // Not supported
    public void testGetResultSetConcurrency() {
        Statement st = null;

        // test default value
        try {
            st = conn.createStatement();
            st.getResultSetConcurrency();
            assertEquals(ResultSet.CONCUR_READ_ONLY, st
                    .getResultSetConcurrency());
        } catch (SQLException e) {
            assertEquals("not supported", e.getMessage());
        }

     // failing tests

        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            st.getResultSetConcurrency();
            assertEquals(ResultSet.CONCUR_UPDATABLE, st.getResultSetConcurrency());
            fail("Exception expected: not supported");
        } catch (SQLException e) {
            //ok

        }

        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            st.getResultSetConcurrency();
            assertEquals(ResultSet.CONCUR_READ_ONLY, st.getResultSetConcurrency());
            fail("Exception expected: not supported");
        } catch (SQLException e) {
            //ok;
        }
    }

    // Does not return null on update count > 0 (not a select statement)
    public void testGetResultSet() throws SQLException {
        Statement st = null;
        ResultSet res = null;

        st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        st.execute("create table test (c1);");
        res = st.getResultSet();
        assertNull(res);

        st = conn.createStatement();
        String select = "select * from zoo where id == 4;";
        String insert =  "insert into zoo (id, name, family) values (4, 'Vorobuy', 'bear');";
        st.execute(insert);
        st.execute(select);
        assertEquals(-1, st.getUpdateCount());
        res = st.getResultSet();
        assertNotNull(res);
        res.next();
        assertEquals(4,res.getInt(1));
        assertEquals("Vorobuy",res.getString(2));
        assertEquals("bear",res.getString(3));
//            assertEquals(0, st.getUpdateCount()); not supported
        assertFalse(res.next());

        st = conn.createStatement();
        insert = "insert into zoo (id, name, family) values (3, 'Vorobey', 'sparrow');";
        st.execute(insert);
        res = st.getResultSet();
        // statement is an update and should return null according to spec.
        if (st.getUpdateCount() > 0)  {
            assertNull(res);
        }

        try {
            st.close();
            st.getResultSet();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // An other value is returned than was set (X * 1000)
    public void testGetQueryTimeout() throws SQLException {
        Statement st = null;
        st = conn.createStatement();
        st.setQueryTimeout(2000);
        assertEquals(2000, st.getQueryTimeout());

        st = conn.createStatement();
        assertEquals(0,st.getQueryTimeout());

        try {
            st.close();
            st.getQueryTimeout();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // not supported
    public void testGetMoreResults() throws SQLException {
        Statement st = null;
        ResultSet res1 = null;
        ResultSet res2 = null;
        String[] queries = {
                "insert into zoo values (3,'John','bird');",
                "update zoo set name='Masha', family='cat' where id=3;",
                "update zoo set name='Masha', family='bear' where id=3;"};

       try {
            st = conn.createStatement();
            st.execute(queries[0]);
            assertFalse(st.getMoreResults());

            try {
                st.getResultSet();
                fail("Exception expected");
            } catch (SQLException e) {
                //ok
            }
        } finally {
            try {
                st.close();
            } catch (SQLException ee) {
            }
        }

        try {
            st.getMoreResults();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // Bug in implementation of cancel: Does not fulfill spec.
    public void testCancel() throws SQLException {
        Statement st = null;
        st = conn.prepareStatement("insert into zoo values (7,'Speedy Gonzales','Mouse');");

        CancelThread c = new CancelThread(st);
        InsertThread ins = new InsertThread((PreparedStatement)st);

        try {
            ins.t.join();
            c.t.join();
        } catch (InterruptedException e) {
            fail("Error in test setup: ");
        } catch (Exception e){
            // Insert thread may throw an exception
            // that it could not complete statement
        }

        // both threads have terminated and cancel should have cancelled the insert statement.
        ResultSet res = st.executeQuery("select * from zoo where id=7");
        assertFalse(res.next());

        try {
            st.close();
            st.cancel();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    class CancelThread implements Runnable{
        Thread t;
        Statement stmt;
        CancelThread (Statement aSt) {
           this.stmt = aSt;
           t = new Thread(this,"Cancel thread");
           t.start();
        }

        public void run() {
            Logger.global.info("*Cancel* thread started");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e1) {
                fail("Error in test setup");
                e1.printStackTrace();
            }
            try {
                Logger.global.info("*Cancel* thread, about to do stmt.cancel()");
                stmt.cancel();
                Logger.global.info("*Cancel* thread, stmt.cancel() done");
            } catch (SQLException e) {
                fail("Error in test setup");
                e.printStackTrace();
            }
            Logger.global.info("*Cancel* thread terminated");
        }
    }

    class InsertThread implements Runnable{
        Thread t;
        PreparedStatement stmt;
        InsertThread (PreparedStatement aSt) {
           this.stmt = aSt;
           t = new Thread(this,"Insert thread");
           t.start();
        }

        public void run() {
            Logger.global.info("*Insert* thread started");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e1) {
                fail("Error in test setup");
                e1.printStackTrace();
            }
            try {
                Logger.global.info("*Insert* thread, about to do insertion");
                stmt.execute();
                stmt.execute();
                Logger.global.info("*Insert* thread inserted");
            } catch (SQLException e) {
                fail("Error in test setup");
                e.printStackTrace();
            }
            Logger.global.info("*Insert* thread terminated");
        }
    }
}
