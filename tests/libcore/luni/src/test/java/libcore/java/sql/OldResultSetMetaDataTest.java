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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public final class OldResultSetMetaDataTest extends OldSQLTest {

    ResultSetMetaData rsmd = null;
    Statement st = null;
    ResultSet rs = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        try {
            conn.setAutoCommit(false);
            assertFalse(conn.getAutoCommit());
            String query = "select * from zoo";
            st = conn.createStatement();
            st.execute(query);
            rs = st.getResultSet();
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            fail("Couldn't get ResultSetMetaData object");
        }
    }

    @Override
    public void tearDown() throws SQLException {
        try {
            rs.close();
            st.close();
        } catch (SQLException e) {
            fail("Couldn't close Statement object");
        }
        super.tearDown();
    }

    // not supported
    public void testGetCatalogName() throws SQLException {
        try {
            assertNotNull(rsmd.getCatalogName(1));
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            conn.close();
            rsmd.getCatalogName(0);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    public void testGetColumnClassName() {
        try {
            assertNotNull(rsmd);
            assertEquals(Short.class.getName(), rsmd.getColumnClassName(1));
            assertEquals(String.class.getName(), rsmd.getColumnClassName(2));
            assertEquals(String.class.getName(), rsmd.getColumnClassName(3));
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            String name  = rsmd.getColumnClassName(0);
            assertNull(name);
        } catch (SQLException e) {
            fail("SQLException is thrown");
        }

        try {
            String name  = rsmd.getColumnClassName(4);
            assertNull(name);
        } catch (SQLException e) {
            fail("SQLException is thrown");
        }
    }

    // SQLException checking test fails
    public void testGetColumnCount() {
        try {
            assertEquals(3, rsmd.getColumnCount());
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            rs.close();
            rsmd.getColumnCount();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

    }

    // Column label has format TABLE.COLUMN expected: COLUMN
    public void testGetColumnLabel() {
        String[] labels = { "id", "name", "family" };
        try {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String label = rsmd.getColumnLabel(i + 1);
                assertTrue("expected "+labels[i] + "got "+label,labels[i].contains(label));
            }
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            String label = rsmd.getColumnLabel(0);
            fail("SQLException expected");
        } catch (SQLException e) {
            //ok
        }

        try {
            String label = rsmd.getColumnLabel(5);
            fail("SQLException expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // Column label has format TABLE.COLUMN expected: COLUMN
    public void testGetColumnName() {
        String[] labels = { "id", "name", "family" };
        try {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i + 1);
                assertEquals(labels[i], name);
            }
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            String label = rsmd.getColumnName(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            //ok
        }

        try {
            String label = rsmd.getColumnName(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            //ok
        }
    }

    /**
     * For extensive tests see: ResultSetGetterTest.testGetMetaData
     */
    public void testGetColumnType() {
        int[] types = { Types.SMALLINT, Types.VARCHAR, Types.VARCHAR};
        try {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                int type = rsmd.getColumnType(i + 1);
                assertEquals(types[i], type);
            }
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            rsmd.getColumnType(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getColumnType(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    /**
     * for extensive tests see: ResultSetGetterTest.testGetMetaData
     */
    public void testGetColumnTypeName() {
        try {
            assertTrue("smallint".equalsIgnoreCase(rsmd.getColumnTypeName(1)));
            assertTrue("varchar".equalsIgnoreCase(rsmd.getColumnTypeName(2)));
            assertTrue("varchar".equalsIgnoreCase(rsmd.getColumnTypeName(3)));
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        try {
            rsmd.getColumnTypeName(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getColumnTypeName(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    // For int = 0, exception expected
    public void testGetTableName() throws SQLException {
        try {
            assertEquals("zoo", rsmd.getTableName(1));
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }
        Statement st1 = null;
        ResultSet rs1 = null;
        try {

            String create = "create table hutch (id integer not null, animal_id integer, address char(20), primary key (id));";
            String insert1 = "insert into hutch (id, animal_id, address) values (1, 2, 'Birds-house, 1');";
            String insert2 = "insert into hutch (id, animal_id, address) values (2, 1, 'Horse-house, 5');";
            String query = "select name, animal_id from hutch, zoo where zoo.id = 1" ;
            st1 = conn.createStatement();
            st1.executeUpdate(create);
            st1.executeUpdate(insert1);
            st1.executeUpdate(insert2);

            rs1 = st1.executeQuery(query);
            assertNotNull(rs1);
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            assertEquals("zoo", rsmd1.getTableName(1));
            assertEquals("hutch", rsmd1.getTableName(2));
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        } finally {
            try {
                if (rs1 != null) rs1.close();
                if (st1 != null) {
                    st1.executeUpdate("drop table if exists hutch");
                    st1.close();
                }
            } catch (SQLException sqle) {
            }
        }
        //Exception Text
        try {
            String name = rsmd.getTableName(0);
            fail("SQLException Expected");
        } catch (SQLException e) {
            // ok
        }
    }

    // not supported
    public void testGetPrecision() throws SQLException {
        Statement st2 = null;
        Statement st3 = null;
        ResultSetMetaData rsmd2 = null;
        try {
        int precisionNum = 10;
        int scale = 3;
        int precicisionReal = 10;
        String createTable = "create table DecimalNumbers ( valueDouble DOUBLE,"+
        "valueFloat FLOAT , scaleTest NUMERIC("+precisionNum+","+scale+"),"+
        " valueReal REAL("+precicisionReal+")  );";
        String insert = "insert into DecimalNumbers values (1.5, 20.55, 30.666, 100000);";
        String select = "select * from DecimalNumbers;";
        st2 = conn.createStatement();
        st2.executeUpdate(createTable);
        st2.executeUpdate(insert);

        st2.close();

        st3 = conn.createStatement();
        rs = st3.executeQuery(select);
        assertTrue(rs.next());
        rsmd2 = rs.getMetaData();

        assertNotNull(rsmd2);
        assertEquals(precicisionReal, rsmd2.getPrecision(4));
        assertEquals(precisionNum,rsmd2.getPrecision(3));
        assertTrue(rsmd2.getPrecision(2) > 0);
        assertTrue(rsmd2.getPrecision(1) > 0);

        // non numeric field
        try {
            rsmd.getPrecision(3);
        } catch (SQLException e1) {
            System.out.println("ResultSetMetaDataTest.testGetPrecision()"+e1.getMessage());
            e1.printStackTrace();
        }


        try {
            rsmd.getPrecision(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getPrecision(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }

        try {
            rs.close();
            rsmd.getPrecision(1);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
        } finally  {
            if (st2 != null) st2.close();
            if (st3 != null) st3.close();
        }
    }

    /**
     * Always returns 0, exception tests fail no positive test case for
     * black-box test possible: no default value indicated.
     *
     * Not supported
     */
    public void testGetScale() throws SQLException {
        try {
        int scale = 3;
        String createTable = "create table DecimalNumbers ( valueDouble DOUBLE,"+
        "valueFloat FLOAT , scaleTest NUMERIC(10,"+scale+")  );";
        String insert = "insert into DecimalNumbers values (1.5, 20.55, 30.666);";
        String select = "select * from DecimalNumbers;";

        Statement st = conn.createStatement();
        st.executeUpdate(createTable);
        st.executeUpdate(insert);

        rs = st.executeQuery(select);
        ResultSetMetaData rsmd2 = rs.getMetaData();

        assertNotNull(rsmd2);
        assertEquals(scale,rsmd2.getScale(3));
        assertTrue(rsmd2.getScale(1) > 0);
        assertTrue(rsmd2.getScale(2) > 0);

        try {
            rsmd.getScale(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getScale(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }


        try {
            conn.close();
            rsmd.getScale(1);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
        } finally  {
            st.cancel();
        }
    }

    // not supported
    public void testGetSchema() {
        try {
            assertNull("Functionality is now supported. Change test",rsmd.getSchemaName(2));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.testGetScale()"+e1.getMessage());
            e1.printStackTrace();
        }

        try {
            rsmd.getSchemaName(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getSchemaName(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }

        try {
            conn.close();
            rsmd.getSchemaName(2);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

    }

    /**
     * Tests fail: always returns false, failing statements commented out.
     * Feature only partially implemented.Missing: Test positive case
     *
     * Not supported.
     */
    public void testisAutoIncrement() {
        try {
            assertFalse(rsmd.isAutoIncrement(1));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.testGetScale()"+e1.getMessage());
            e1.printStackTrace();
        }

        /*
        // Exception testing

        try {
            rsmd.isAutoIncrement(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.isAutoIncrement(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        */

        try {
            conn.close();
            rsmd.getSchemaName(2);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

    }

    // not supported
    public void testIsCaseSensitive() {
        try {
            assertFalse(rsmd.isCaseSensitive(1));
            assertFalse(rsmd.isCaseSensitive(2));
            assertFalse(rsmd.isCaseSensitive(3));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.testGetScale()"+e1.getMessage());
            e1.printStackTrace();
        }

        /*
        // Exception testing

        try {
            rsmd.isCaseSensitive(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.isCaseSensitive(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        */

        try {
            conn.close();
            rsmd.isCaseSensitive(1);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    /**
     * Tests fail: always returns false. Exceptions and tests non Numeric fields
     * fail, failing statements commented out. Feature only partially
     * implemented. May be an optional feature.
     *
     * Not supported.
     */
    public void testIsCurrency() {
        try {
            assertFalse(rsmd.isCurrency(1));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.testGetScale()"+e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing
        try {
            rsmd.isCurrency(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.isCurrency(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }

        try {
            rs.close();
            rsmd.isCurrency(1);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // not supported
    public void testIsDefinitlyWritable() {
        try {
            assertTrue(rsmd.isDefinitelyWritable(1));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.testisDefinitelyWritable()"
                    + e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing

        try {
            rsmd.isDefinitelyWritable(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.isDefinitelyWritable(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    /**
     * Tests fail: always returns ResultSetMetaData.columnNullableUnknown.
     * Exceptions fail, failing statements commented out. Feature only
     * partially implemented. May be an optional feature.
     *
     * Not supported.
     */
    public void testIsNullable() {
        try {
            assertEquals(ResultSetMetaData.columnNullable, rsmd
                    .isNullable(1));
            assertEquals(ResultSetMetaData.columnNullable, rsmd
                    .isNullable(2));
            assertEquals(ResultSetMetaData.columnNullable, rsmd
                    .isNullable(3));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.isNullable()" + e1.getMessage());
            e1.printStackTrace();
        }

        /*
        // Exception testing

        try {
            rsmd.isNullable(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.isNullable(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        */

    }

    /**
     * Cannot know from blackbox test if readonly or writable. Exceptions fail,
     * Feature only partially implemented.
     */
    public void testIsReadOnly() {
        try {
            assertFalse(rsmd.isReadOnly(1));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.isReadOnly" + e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing

        try {
            rsmd.isReadOnly(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    /**
     * Tests fail: always returns false. Exceptions fail, Feature only partially
     * implemented. Missing: test for searchable field.
     */
    public void testIsSearchable() {
        try {
            assertTrue(rsmd.isSearchable(1));
            assertTrue(rsmd.isSearchable(2));
            assertTrue(rsmd.isSearchable(3));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.isReadOnly" + e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing

        try {
            rsmd.isSearchable(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    /**
     * Tests fail: always returns false. Exceptions and tests on non numeric
     * fields fail, Feature only partially implemented. Missing: test positive
     * result
     */
    public void testIsSigned() {
        try {
            assertFalse(rsmd.isSigned(1));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.isSigned" + e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing

        try {
            rsmd.isSigned(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

    /**
     * Analogous to is Readonly.  Exceptions and tests on non numeric fields
     * fail, Failing statements commented out. Feature only partially
     * implemented.
     */
    public void testIsWritable() {
        try {
            assertTrue(rsmd.isWritable(1));
            assertTrue(rsmd.isWritable(2));
            assertTrue(rsmd.isWritable(3));
        } catch (SQLException e1) {
            fail("ResultSetMetaDataTest.isWritable" + e1.getMessage());
            e1.printStackTrace();
        }

        // Exception testing

        try {
            rsmd.isWritable(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }


    /**
     * Tests fail. always returns 0. Missing case where display size greater than 0
     */
    public void testGetColumnDisplaySize() {
        try {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                int size = rsmd.getColumnDisplaySize(i + 1);
                assertTrue(size > 0);
            }
        } catch (SQLException e) {
            fail("SQLException is thrown: " + e.getMessage());
        }

        // Exception testing

        try {
            rsmd.getColumnDisplaySize(0);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
        try {
            rsmd.getColumnDisplaySize(5);
            fail("SQLException is not thrown");
        } catch (SQLException e) {
            // expected
        }
    }

}
