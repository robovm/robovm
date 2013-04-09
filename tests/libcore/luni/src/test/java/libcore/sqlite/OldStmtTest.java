/*
 * Copyright (C) 2008 The Android Open Source Project
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

import SQLite.Constants;
import SQLite.Database;
import SQLite.Stmt;
import SQLite.TableResult;
import java.sql.Connection;
import tests.support.DatabaseCreator;
import tests.support.Support_SQL;

public class OldStmtTest extends OldSQLiteTest {

    private Database db;
    private Stmt st;

    private static final String CREATE_ALL_TYPES = "create table type ("
            + " BoolVal BOOLEAN,"
            + " IntVal INT,"
            + " LongVal LONG,"
            + " Bint BIGINT,"
            + " Tint TINYINT,"
            + " Sint SMALLINT,"
            + " Mint MEDIUMINT,"
            + " IntegerVal INTEGER,"
            + " RealVal REAL,"
            + " DoubleVal DOUBLE,"
            + " FloatVal FLOAT,"
            + " DecVal DECIMAL,"
            + " NumVal NUMERIC,"
            + " charStr CHAR(20),"
            + " dateVal DATE,"
            + " timeVal TIME,"
            + " TS TIMESTAMP,"
            + " DT DATETIME,"
            + " TBlob TINYBLOB,"
            + " BlobVal BLOB,"
            + " MBlob MEDIUMBLOB,"
            + " LBlob LONGBLOB,"
            + " TText TINYTEXT,"
            + " TextVal TEXT,"
            + " MText MEDIUMTEXT,"
            + " LText LONGTEXT,"
            + " MaxLongVal BIGINT,"
            + " MinLongVal BIGINT,"
            + " validURL URL,"
            + " invalidURL URL);";

    static final String INSERT_ALL_TYPES = "insert into type ("
            + "BoolVal, IntVal, LongVal, Bint, Tint, Sint, Mint,IntegerVal, RealVal, DoubleVal, "
            + "FloatVal, DecVal,NumVal, charStr, dateVal, timeVal, TS,DT, TBlob, BlobVal, MBlob, "
            + "LBlob,TText, TextVal, MText, LText, MaxLongVal, MinLongVal, validURL, invalidURL) "
            + "values (1, -1, 22, 2, 33,3, 1, 2, 3.9, 23.2, 33.3, 44,5, 'test string', '1799-05-26',"
            + "'12:35:45', '2007-10-09 14:28:02.0','1221-09-22 10:11:55', 1, 2, 3, 4,"
            + "'Test text message tiny', 'Test text', 'Test text message medium',"
            + "'Test text message long', " + Long.MAX_VALUE + ", " + Long.MIN_VALUE + ","
            + "null, null);";

    static final String ALL_TYPES_TABLE = "type";

    @Override public void setUp() throws Exception {
        super.setUp();
        Support_SQL.loadDriver();
        db = new Database();
        db.open(dbFile.getPath(), 0);
        db.exec(DatabaseCreator.CREATE_TABLE_SIMPLE1, null);
        DatabaseCreator.fillSimpleTable1(conn);

        st = new Stmt();
    }

    @Override public void tearDown() throws Exception {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
            }
        }
        db.close();
        Connection con = Support_SQL.getConnection();
        con.close();
        super.tearDown();
    }

    public void testStmt() throws Exception {
        db.prepare("");

        try {
            st.step();
            fail("Cannot execute non prepared Stmt");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testPrepare() throws Exception {
        try {
            st = db.prepare("");
            st.prepare();
            fail("statement is closed");
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }

        st = new Stmt();
        st = db.prepare("select * from " + DatabaseCreator.SIMPLE_TABLE1);
        assertFalse(st.prepare());
        st = new Stmt();
        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        assertFalse(st.prepare());
        st = new Stmt();
        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        st.bind(1, 1);
        st.bind(2, 10);
        st.bind(3, 30);
        assertFalse(st.prepare());
        st = db.prepare("select * from " + DatabaseCreator.SIMPLE_TABLE1
                + "; " + "delete from " + DatabaseCreator.SIMPLE_TABLE1
                + " where id = 5; " + "insert into "
                + DatabaseCreator.SIMPLE_TABLE1 + " values(5, 10, 20); "
                + "select * from " + DatabaseCreator.SIMPLE_TABLE1 + ";");
        assertTrue(st.prepare());
        assertTrue(st.prepare());
        assertTrue(st.prepare());
        assertFalse(st.prepare());
    }

    public void testStep() throws Exception {
        try {
            st.step();
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }

        st = new Stmt();
        st = db.prepare("select name from sqlite_master where type = 'table'");
        st.step();
    }

    public void testClose() throws Exception {
        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        st.close();

        try {
            st.step();
            fail("Test fails");
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }
    }

    public void testReset() throws Exception {
        db.exec("create table TEST (res integer not null)", null);

        st = db.prepare("insert into TEST values (:one);");
        st.bind(1, 1);
        st.step();

        // verify that parameter is still bound
        st.reset();
        assertEquals(1,st.bind_parameter_count());
        st.step();

        TableResult count = db.get_table("select count(*) from TEST where res=1", null);

        String[] row0 = (String[]) count.rows.elementAt(0);
        assertEquals(2, Integer.parseInt(row0[0]));
    }

    public void testClear_bindings() {
        try {
            st.clear_bindings();
        } catch (SQLite.Exception expected) {
            assertEquals("unsupported", expected.getMessage());
        }
    }

    public void testBindIntInt() throws Exception {
        int input = 0;
        int maxVal = Integer.MAX_VALUE;
        int minVal = Integer.MIN_VALUE;

        db.exec("create table TEST (res integer)", null);
        st = db.prepare("insert into TEST values (:one);");
        st.bind(1, input);
        st.step();

        st.reset();
        st.bind(1,maxVal);
        st.step();

        st.reset();
        st.bind(1,minVal);
        st.step();

        TableResult r = db.get_table("select * from TEST");

        String[] row0 = (String[]) r.rows.elementAt(0);
        assertEquals(input,Integer.parseInt(row0[0]));

        String[] row1 = (String[]) r.rows.elementAt(1);
        assertEquals(maxVal,Integer.parseInt(row1[0]));

        String[] row2 = (String[]) r.rows.elementAt(2);
        assertEquals(minVal,Integer.parseInt(row2[0]));

        try {
            st.close();
            st.bind(1,Integer.MIN_VALUE);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testBindIntLong() throws Exception {
        long input = 0;
        long maxVal = Long.MAX_VALUE;
        long minVal = Long.MIN_VALUE;

        db.exec("create table TEST (res long)", null);
        st = db.prepare("insert into TEST values (:one);");
        st.bind(1, input);
        st.step();

        st.reset();
        st.bind(1,maxVal);
        st.step();

        st.reset();
        st.bind(1,minVal);
        st.step();

        TableResult r = db.get_table("select * from TEST");

        String[] row0 = (String[]) r.rows.elementAt(0);
        assertEquals(input,Long.parseLong(row0[0]));

        String[] row1 = (String[]) r.rows.elementAt(1);
        assertEquals(maxVal,Long.parseLong(row1[0]));

        String[] row2 = (String[]) r.rows.elementAt(2);
        assertEquals(minVal,Long.parseLong(row2[0]));

        try {
            st.close();
            st.bind(1,Long.MIN_VALUE);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testBindIntDouble() throws Exception {
        double input = 0.0;
        double maxVal = Double.MAX_VALUE;
        double minVal = Double.MIN_VALUE;
        double negInf = Double.NEGATIVE_INFINITY;
        double posInf = Double.POSITIVE_INFINITY;
        double nan = Double.NaN;

        db.exec("create table TEST (res double)", null);
        st = db.prepare("insert into TEST values (:one);");
        st.bind(1, input);
        st.step();

        st.reset();
        st.bind(1, maxVal);
        st.step();

        st.reset();
        st.bind(1, minVal);
        st.step();

        st.reset();
        st.bind(1, negInf);
        st.step();

        st.reset();
        st.bind(1, posInf);
        st.step();

        st.reset();
        st.bind(1, nan);
        st.step();


        TableResult r = db.get_table("select * from TEST");

        String[] row0 = (String[]) r.rows.elementAt(0);
        assertTrue(Double.compare(input, Double.parseDouble(row0[0])) == 0);

        String[] row1 = (String[]) r.rows.elementAt(1);
        assertFalse(Double.compare(maxVal, Double.parseDouble(row1[0])) == 0);
        assertTrue(Double.compare(maxVal, Double.parseDouble(row1[0])) < 0);
        assertTrue(Double.isInfinite(Double.parseDouble(row1[0])));

        String[] row2 = (String[]) r.rows.elementAt(2);
        assertTrue(Double.compare(minVal, Double.parseDouble(row2[0])) == 0);

        String[] row3 = (String[]) r.rows.elementAt(3);
        assertEquals("Double.NEGATIVE_INFINITY SQLite representation",
                "-Inf", row3[0]);

        String[] row4 = (String[]) r.rows.elementAt(4);
        assertEquals("Double.POSITIVE_INFINITY SQLite representation",
                "Inf", row4[0]);

        String[] row5 = (String[]) r.rows.elementAt(4);
        assertEquals("Double.Nan SQLite representation", "Inf", row5[0]);

        try {
            st.close();
            st.bind(1,0.0);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testBindIntByteArray() throws Exception {
        String name = "Hello World";
        byte[] b = name.getBytes();
        String stringInHex = "";

        db.exec(DatabaseCreator.CREATE_TABLE_PARENT, null);
        st = db.prepare("insert into " + DatabaseCreator.PARENT_TABLE
                + " values (:one, :two);");
        st.bind(1, 2);
        st.bind(2, b);
        st.step();

        //compare what was stored with input based on Hex representation
        // since type of column is CHAR
        TableResult r = db.get_table("select * from "
                + DatabaseCreator.PARENT_TABLE);
        String[] row = (String[]) r.rows.elementAt(0);

        for (byte aByte : b) {
            stringInHex += Integer.toHexString(aByte);
        }
        stringInHex = "X'" + stringInHex + "'";
        assertTrue(stringInHex.equalsIgnoreCase(row[1]));

        try {
            st.close();
            st.bind(1,name.getBytes());
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testBindIntString() throws Exception {
        String name = "Hello World";
        db.exec(DatabaseCreator.CREATE_TABLE_PARENT, null);
        st = db.prepare("insert into " + DatabaseCreator.PARENT_TABLE
                + " values (:one, :two);");
        st.bind(1, 2);
        st.bind(2, name);
        st.step();

        TableResult r = db.get_table("select * from "
                + DatabaseCreator.PARENT_TABLE);
        String[] row = (String[]) r.rows.elementAt(0);
        assertEquals(name,row[1]);

        try {
            st.close();
            st.bind(1,name);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }
    }

    public void testBindInt() throws Exception {
        try {
            st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values (:one,:two,:three)");
            st.bind(4);
            st.bind(1, 4);
            st.bind(2, 10);
            st.bind(3, 30);
            st.step();
            fail();
        } catch (SQLite.Exception expected) {
            // What happens if null is bound to non existing variable position
            assertEquals("parameter position out of bounds", expected.getMessage());
        }

        // functional tests

        try {
            st.reset();
            st.bind(1);
            st.bind(2, 10);
            st.bind(3, 30);
            st.step();
            fail();
        } catch (SQLite.Exception expected) {
            // What happens if null is bound to NON NULL field
            assertEquals("SQL logic error or missing database", expected.getMessage());
        }

        st.reset();
        st.bind(1, 3);
        st.bind(2);
        st.bind(3, 30);
        st.step();
    }

    public void testBind_zeroblob() {
        try {
            st.bind_zeroblob(1, 128);
            fail();
        } catch (SQLite.Exception expected) {
            assertEquals("unsupported", expected.getMessage());
        }
    }

    public void testBind_parameter_count() throws Exception {
        try {
            st.bind_parameter_count();
            fail();
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }

        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        assertEquals(3, st.bind_parameter_count());

        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (?, ?, ?)");
        assertEquals(3, st.bind_parameter_count());

        st = db.prepare("select * from " + DatabaseCreator.SIMPLE_TABLE1);
        assertEquals(0, st.bind_parameter_count());

        try {
            st.close();
            st.bind_parameter_count();
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
        }

    }

    public void testBind_parameter_name() {
        try {
            st.bind_parameter_name(1);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }

        try {
            st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values (:one,:two,:three)");
            assertEquals(":one", st.bind_parameter_name(1));
            assertEquals(":two", st.bind_parameter_name(2));
            assertEquals(":three", st.bind_parameter_name(3));
            st.bind_parameter_name(4);
            fail();
        } catch (SQLite.Exception expected) {
            assertEquals("parameter position out of bounds", expected.getMessage());
        }
    }

    public void testBind_parameter_index() throws Exception {
        try {
            st.bind_parameter_index("");
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("stmt already closed", expected.getMessage());
        }

        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        assertEquals(3, st.bind_parameter_index(":three"));

        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (:one,:two,:three)");
        assertEquals(0, st.bind_parameter_index(":t"));

        st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " values (?, ?, ?)");
        assertEquals(0, st.bind_parameter_index("?"));
    }

    public void testColumn_int() throws Exception {
        db.exec(CREATE_ALL_TYPES, null);
        db.exec(INSERT_ALL_TYPES, null);

        Object columnObject;
        int intColumn;
        String selectStmt = "select * from "+DatabaseCreator.SIMPLE_TABLE1;

        st = db.prepare(selectStmt);
        st.step();
        // select 'speed' value
        columnObject = st.column(1);
        intColumn = st.column_int(1);
        assertNotNull(intColumn);

        assertTrue("Integer".equalsIgnoreCase(st.column_decltype(1)));
        int stSpeed = Integer.parseInt(columnObject.toString());
        assertNotNull(stSpeed);
        assertEquals( intColumn, stSpeed);
        assertEquals(10,stSpeed);

        selectStmt = "select TextVal from "+ ALL_TYPES_TABLE;

        st = db.prepare(selectStmt);
        st.step();
        st.column_int(0);
    }

    public void testColumn_long() throws Exception {
        Object columnObject;
        long longColumn;
        String selectStmt = "select * from "+DatabaseCreator.SIMPLE_TABLE1;
        st = db.prepare(selectStmt);
        st.step();
        columnObject = st.column(1);
        longColumn = st.column_long(1);
        assertNotNull(longColumn);
        // column declared as integer
        assertTrue("Integer".equalsIgnoreCase(st.column_decltype(1)));
        int stSpeed = Integer.parseInt(columnObject.toString());
        assertNotNull(stSpeed);
        assertEquals( longColumn, stSpeed);

        try {
            st.column_long(4);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("column out of bounds", expected.getMessage());
        }

        try {
            st.column_long(-1);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("column out of bounds", expected.getMessage());
        }
    }

    public void testColumn_double() throws Exception {
        db.exec(CREATE_ALL_TYPES, null);
        db.exec(INSERT_ALL_TYPES, null);

        double doubleColumn;
        double actualVal = 23.2;
        String selectStmt = "select DoubleVal from "+ ALL_TYPES_TABLE;

        st = db.prepare(selectStmt);
        st.step();
        // select double value
        doubleColumn = st.column_double(0);
        assertNotNull(doubleColumn);

        assertTrue("DOUBLE".equalsIgnoreCase(st.column_decltype(0)));
        assertNotNull(doubleColumn);
        assertEquals( actualVal, doubleColumn);

        // Exception test
        selectStmt = "select dateVal from "+ ALL_TYPES_TABLE;

        st = db.prepare(selectStmt);
        st.step();
        // select double value
        st.column_double(0);
    }

    public void testColumn_bytes() throws Exception {
        db.exec("create table B(id integer primary key, val blob)",null);
        db.exec("insert into B values(1, zeroblob(128))", null);
        st = db.prepare("select val from B where id = 1");
        assertTrue(st.step());
        st.column_bytes(0);
    }

    public void testColumn_string() throws Exception {
        db.exec(CREATE_ALL_TYPES, null);
        db.exec(INSERT_ALL_TYPES, null);

        String stringColumn;
        String actualVal = "test string";
        String selectStmt = "select charStr from "+ ALL_TYPES_TABLE;

        st = db.prepare(selectStmt);
        st.step();
        // select string value
        stringColumn = st.column_string(0);
        assertNotNull(stringColumn);

        assertTrue("CHAR(20)".equalsIgnoreCase(st.column_decltype(0)));
        assertNotNull(stringColumn);
        assertEquals( actualVal, stringColumn);

        // Exception test
        selectStmt = "select DoubleVal from "+ ALL_TYPES_TABLE;

        st = db.prepare(selectStmt);
        st.step();
        st.column_string(0);
    }

    public void testColumn_type() throws Exception {
        db.exec(CREATE_ALL_TYPES, null);
        db.exec(INSERT_ALL_TYPES, null);
        st = db.prepare("select * from " + ALL_TYPES_TABLE);
        st.step();

        // Exception test
        try {
            st.column_type(100);
            fail();
        } catch (SQLite.Exception expected) {
        }

        /*
        Dictionary

        public static final int SQLITE_INTEGER = 1;
        public static final int SQLITE_FLOAT = 2;
        public static final int SQLITE_BLOB = 4;
        public static final int SQLITE_NULL = 5;
        public static final int SQLITE3_TEXT = 3;
        public static final int SQLITE_NUMERIC = -1;
        */

        assertEquals(Constants.SQLITE3_TEXT, st.column_type(23)); // ok TEXT
        assertEquals(Constants.SQLITE3_TEXT, st.column_type(13)); // CHAR(20)

        assertEquals(Constants.SQLITE_FLOAT, st.column_type(8));
        assertEquals(Constants.SQLITE_FLOAT, st.column_type(9));
        assertEquals(Constants.SQLITE_FLOAT, st.column_type(10)); // FLOAT

        for (int i = 0; i < 8; i++) {
            assertEquals("Expected Integer at position " + i,
                    Constants.SQLITE_INTEGER, st.column_type(i));
        }

        assertEquals(Constants.SQLITE_NULL, st.column_type(28));
        assertEquals(Constants.SQLITE_NULL, st.column_type(29));

        // Failing tests
        assertTrue("INTEGER".equalsIgnoreCase(st.column_decltype(12)));
        assertEquals(Constants.SQLITE_INTEGER, st.column_type(12));

        assertTrue("FLOAT".equalsIgnoreCase(st.column_decltype(11)));
        assertEquals(Constants.SQLITE_FLOAT, st.column_type(11)); // FLOAT ->
                                                                  // got INTEGER
        assertTrue("BLOB".equalsIgnoreCase(st.column_decltype(19)));
        assertEquals(Constants.SQLITE_BLOB, st.column_type(19)); // Blob got
                                                                 // INTEGER

    }

    /**
     * Wrong value is returned in case of a prepared statement to which a '*' bound
     */
    public void testColumn_count() throws Exception {
        String selectStmt = "select * from "+DatabaseCreator.SIMPLE_TABLE1;
        st = db.prepare(selectStmt);

        assertEquals(3, st.column_count());

        st.step();
        int columnCount = st.column_count();
        assertNotNull(columnCount);
        assertEquals( 3, columnCount);

        // actual prepared statement
        selectStmt = "select ? from "+DatabaseCreator.SIMPLE_TABLE1;
        st = db.prepare(selectStmt);

        assertEquals(3, st.column_count());

        st.bind(1, "*");
        st.step();
        columnCount = st.column_count();
        assertNotNull(columnCount);
        assertEquals( 3, columnCount);
    }

    public void testColumn() throws Exception {
        Object columnObject;
        int intColumn;
        String selectStmt = "select * from "+DatabaseCreator.SIMPLE_TABLE1;
        db.get_table(selectStmt);
        st = db.prepare(selectStmt);
        st.step();
        columnObject = st.column(1);
        intColumn = st.column_int(1);
        assertNotNull(intColumn);
        assertTrue("Integer".equalsIgnoreCase(st.column_decltype(1)));
        int stSpeed = Integer.parseInt(columnObject.toString());
        assertNotNull(stSpeed);
        assertEquals( intColumn, stSpeed);

        try {
            assertNotNull(columnObject);
            ((Integer) columnObject).intValue();
            fail("Cast to Integer should fail");
        } catch (ClassCastException expected) {
        }

        try {
            st.column(4);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("column out of bounds", expected.getMessage());
        }

        try {
            st.column(-1);
            fail("Exception expected");
        } catch (SQLite.Exception expected) {
            assertEquals("column out of bounds", expected.getMessage());
        }
    }

    public void testColumn_table_name() {
        try {
            st = db.prepare("select * from " + DatabaseCreator.SIMPLE_TABLE1);
            st.column_table_name(1);
            fail("Function is now supported.");
        } catch (SQLite.Exception expected) {
            assertEquals("unsupported", expected.getMessage());
        }
    }

    public void testColumn_database_name() {
        try {
            st = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values (:one,:two,:three)");
            st.column_database_name(1);
            fail("Function is now supported.");
        } catch (SQLite.Exception expected) {
            assertEquals("unsupported", expected.getMessage());
        }
    }

    public void testColumn_decltype() throws Exception {
        db.exec(CREATE_ALL_TYPES, null);
        db.exec(INSERT_ALL_TYPES, null);
        st = db.prepare("select * from " + ALL_TYPES_TABLE);
        st.step();

        // Exception test
        try {
            st.column_decltype(100);
            fail();
        } catch (SQLite.Exception expected) {
        }

        assertTrue(st.column_decltype(0), "BOOLEAN".equalsIgnoreCase(st
                .column_decltype(0)));
        assertTrue(st.column_decltype(1), "INT".equalsIgnoreCase(st
                .column_decltype(1)));
        assertTrue(st.column_decltype(2), "LONG".equalsIgnoreCase(st
                .column_decltype(2)));
        assertTrue(st.column_decltype(3), "BIGINT".equalsIgnoreCase(st
                .column_decltype(3)));
        assertTrue(st.column_decltype(4), "TINYINT".equalsIgnoreCase(st
                .column_decltype(4)));
        assertTrue(st.column_decltype(5), "SMALLINT".equalsIgnoreCase(st
                .column_decltype(5)));
        assertTrue(st.column_decltype(6), "MEDIUMINT".equalsIgnoreCase(st
                .column_decltype(6)));
        assertTrue(st.column_decltype(7), "INTEGER".equalsIgnoreCase(st
                .column_decltype(7)));
        assertTrue(st.column_decltype(8), "REAL".equalsIgnoreCase(st
                .column_decltype(8)));
        assertTrue(st.column_decltype(9), "DOUBLE".equalsIgnoreCase(st
                .column_decltype(9)));
        assertTrue(st.column_decltype(10), "FLOAT".equalsIgnoreCase(st
                .column_decltype(10)));
        assertTrue(st.column_decltype(11), "DECIMAL".equalsIgnoreCase(st
                .column_decltype(11)));
        assertTrue(st.column_decltype(12), "NUMERIC".equalsIgnoreCase(st
                .column_decltype(12)));
        assertTrue(st.column_decltype(13), "CHAR(20)".equalsIgnoreCase(st
                .column_decltype(13)));

        assertTrue(st.column_decltype(19), "BLOB".equalsIgnoreCase(st
                .column_decltype(19)));

        assertTrue(st.column_decltype(23), "TEXT".equalsIgnoreCase(st
                .column_decltype(23)));
        assertTrue(st.column_decltype(28), "URL".equalsIgnoreCase(st
                .column_decltype(28)));
        assertTrue(st.column_decltype(29), "URL".equalsIgnoreCase(st
                .column_decltype(29)));
    }

    public void testColumn_origin_name() {
        try {
            st = db.prepare("select * from " + DatabaseCreator.SIMPLE_TABLE1);
            st.column_origin_name(1);
            fail("Function is now supported.");
        } catch (SQLite.Exception expected) {
            assertEquals("unsupported", expected.getMessage());
        }
    }
}
