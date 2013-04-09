/*
 * Copyright (C) 2007 Google Inc.
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


import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Tests based on
 * <a href="http://java.sun.com/products/jdbc/download.html">JDBC 1.0 API spec</a> Table 1.0
 */
public final class OldResultSetGetterTests extends OldSQLTest {

    String queryAllSelect = "select * from type";

    ResultSet res = null;

    Statement st = null;

    // Judgement concerning support is based on the result of ResultSet.getOject
    // and Table 1 of JDBC 1.0 spec.
    static boolean booleanSupported = false;
    static boolean blobSupported = false;
    static boolean bigIntSupported = false;
    static boolean smallIntSupported = false;
    static boolean mediumIntSupported = false;
    static boolean realSupported = false;
    static boolean floatSupported = false;
    static boolean dateSupported = false;
    static boolean timeSupported = false;
    static boolean timeStampSupported = false;
    static boolean dateTimeSupported = false;
    static boolean urlSupported= false;
    static boolean tinyIntSupported = false;
    static boolean decimalSupported = false;
    static boolean numericSupported = false;

    static List<String> colNames = Arrays.asList("BoolVal", "IntVal", "LongVal",
            "Bint", "Tint", "Sint", "Mint", "IntegerVal", "RealVal",
            "DoubleVal", "FloatVal", "DecVal", "NumVal", "charStr",
            "dateVal", "timeVal", "TS", "DT", "TBlob", "BlobVal", "MBlob",
            "LBlob", "TText", "TextVal", "MText", "LText", "MaxLongVal",
            "MinLongVal", "validURL", "invalidURL");

    static List<String> values = Arrays.asList("1", "-1", "22", "2", "33",
         "3","1","2","3.9","23.2","33.3","44",
        "5", "test string", "1799-05-26", "12:35:45", "2007-10-09 14:28:02.0",
        "1221-09-22 10:11:55","1","2","3","4","Test text message tiny",
        "Test text", "Test text message medium",
        "Test text message long");

    static boolean[] supported = new boolean[]{
        booleanSupported,
        true,
        true,
        bigIntSupported,
        tinyIntSupported,
        smallIntSupported,
        mediumIntSupported,
        true,
        realSupported,
        true,
        floatSupported,
        decimalSupported,
        numericSupported,
        true,
        dateSupported,
        timeSupported,
        timeStampSupported,
        dateTimeSupported,
        blobSupported,
        blobSupported,
        blobSupported,
        blobSupported,
        true,
        true,
        true,
        true,
        bigIntSupported,
        bigIntSupported,
        urlSupported,
        urlSupported
      };

    // Not supported: BIT,VARBINARY, LONGVARBINARY, BINARY, VARCHAR, LONGVARCHAR
    static Class[] typeMap = new Class[]{
            java.lang.String.class, //
            java.lang.Integer.class,//Types.INTEGER,
            java.lang.Integer.class, //Types.LONG, not a JDBC 1.0 type
            java.lang.Long.class,  // Types.BIGINT,
            java.lang.Byte.class,            // Types.TINYINT,
            java.lang.Short.class, // Types.SMALLINT,
            java.lang.Integer.class, //Types.MEDIUMINT, , not a JDBC 1.0 type
            java.lang.Integer.class, // Types.Integer
            java.lang.Float.class,   // Types.REAL,
            java.lang.Double.class,  // Types.FLOAT,
            java.lang.Double.class, // Types.DOUBLE,
            java.math.BigDecimal.class, // Types.DECIMAL,
            java.math.BigDecimal.class, // Types.NUMERIC,
            java.lang.String.class,     // Types.CHAR
            java.sql.Date.class,        // Types.DATE,
            java.sql.Time.class,        // Types.TIME,
            java.sql.Timestamp.class,  // Types.TIMESTAMP,
            java.sql.Date.class,       // types datetime, not a JDBC 1.0 type
            java.sql.Blob.class,       // Types.BLOB, not a JDBC 1.0 type
            java.sql.Blob.class,       // Types.BLOB, not a JDBC 1.0 type
            java.sql.Blob.class,       // Types.BLOB, not a JDBC 1.0 type
            java.sql.Blob.class,       // Types.BLOB, not a JDBC 1.0 type
            java.lang.String.class,    // not a JDBC 1.0 type
            java.lang.String.class,    // not a JDBC 1.0 type
            java.lang.String.class,    // not a JDBC 1.0 type
            java.lang.String.class,    // not a JDBC 1.0 type
            java.lang.Long.class,      // Types.BIGINT,
            java.lang.Long.class,      // Types.BIGINT,
            java.net.URL.class,        // not a JDBC 1.0 type
            java.net.URL.class         // not a JDBC 1.0 type


    };

    // first inserted row : actual values
    // second inserted row: null values
    String[] queries = {
            "create table type (" +

            " BoolVal BOOLEAN," + " IntVal INT," + " LongVal LONG,"
                    + " Bint BIGINT," + " Tint TINYINT," + " Sint SMALLINT,"
                    + " Mint MEDIUMINT, " +

                    " IntegerVal INTEGER, " + " RealVal REAL, "
                    + " DoubleVal DOUBLE, " + " FloatVal FLOAT, "
                    + " DecVal DECIMAL, " +

                    " NumVal NUMERIC, " + " charStr CHAR(20), "
                    + " dateVal DATE, " + " timeVal TIME, " + " TS TIMESTAMP, "
                    +

                    " DT DATETIME, " + " TBlob TINYBLOB, " + " BlobVal BLOB, "
                    + " MBlob MEDIUMBLOB, " + " LBlob LONGBLOB, " +

                    " TText TINYTEXT, " + " TextVal TEXT, "
                    + " MText MEDIUMTEXT, " + " LText LONGTEXT, " +

                    " MaxLongVal BIGINT, MinLongVal BIGINT, "+

                    " validURL URL, invalidURL URL "+

                    ");"
             ,

            "insert into type (BoolVal, IntVal, LongVal, Bint, Tint, Sint, Mint,"
                    + "IntegerVal, RealVal, DoubleVal, FloatVal, DecVal,"
                    + "NumVal, charStr, dateVal, timeVal, TS,"
                    + "DT, TBlob, BlobVal, MBlob, LBlob,"
                    + "TText, TextVal, MText, LText, MaxLongVal, MinLongVal,"
                    + " validURL, invalidURL"
                    + ") "
                    + "values (1, -1, 22, 2, 33,"
                    + "3, 1, 2, 3.9, 23.2, 33.3, 44,"
                    + "5, 'test string', '1799-05-26', '12:35:45', '2007-10-09 14:28:02.0',"
                    + "'1221-09-22 10:11:55', 1, 2, 3, 4,"
                    + "'Test text message tiny', 'Test text',"
                    + " 'Test text message medium', 'Test text message long', "
                    + Long.MAX_VALUE+", "+Long.MIN_VALUE+", "
                    + "'http://www.android.com', 'helloWorld' "+
                    ");"
            ,

           "insert into type (BoolVal, IntVal, LongVal, Bint, Tint, Sint, Mint,"
                    + "IntegerVal, RealVal, DoubleVal, FloatVal, DecVal,"
                    + "NumVal, charStr, dateVal, timeVal, TS,"
                    + "DT, TBlob, BlobVal, MBlob, LBlob,"
                    + "TText, TextVal, MText, LText, MaxLongVal, MinLongVal,"
                    +" validURL, invalidURL"
                    + ") "
                    + "values (null, null, null, null, null,"
                    + "null, null, null, null, null, null, null,"
                    + "null, null, null, null, null,"
                    + "null, null, null, null, null,"
                    + "null, null, null, null,null, null, null, null);"
    };

    @Override
    public void setUp() throws Exception {
        super.setUp();
        conn.setAutoCommit(false);
        st = conn.createStatement();
        for (int i = 0; i < queries.length; i++) {
            st.execute(queries[i]);
        }
        res = st.executeQuery(queryAllSelect);
        assertTrue(res.next());
    }

    public void tearDown() throws SQLException {
        try {
            st.execute("drop table if exists type");
            st.close();
            res.close();
        } finally {
            try {
                st.close();
                res.close();
            } catch (SQLException ee) {
            }
        }
        super.tearDown();
    }

    public void testGetBytesInt() throws SQLException {
        int i = 1;
        // null value
        i = 1;
        res.next();
        for (String t : values) {
            assertNull(res.getBytes(i));
            i++;
        }

        try {
            res.close();
            res.getBytes(24);
            fail("Should get Exception");
        } catch (SQLException e) {
            //ok
        }

    }

    public void testGetBytesIntVarbinary() throws SQLException {
        Statement st = null;
        Statement stQuery = null;
        PreparedStatement stPrep = null;
        ResultSet res = null;

        // setup
        try {
            String testString = "HelloWorld";
            st = conn.createStatement();
            st.executeUpdate("create table testBinary (VARBINARY value);");
            stPrep = conn
                    .prepareStatement("insert into testBinary values (?);");
            stPrep.setBytes(1, testString.getBytes());
            stPrep.execute();

            stQuery = conn.createStatement();
            res = stQuery.executeQuery("select * from testBinary");
            assertTrue(res.next());
            byte[] output = res.getBytes(1);
            String helloTest = new String(output);
            assertNotNull(helloTest);
            assertEquals(testString, helloTest);
        } finally {
            if (res != null) res.close();
            if (stPrep != null) stPrep.close();
            if (st != null) st.close();
            if (stQuery != null) stQuery.close();
        }
    }

    public void testGetBytesIntBinary() throws SQLException {

        Statement st = null;
        Statement stQuery = null;
        PreparedStatement stPrep = null;
        ResultSet res = null;


        // setup

        String testString = "HelloWorld";
        st = conn.createStatement();
        st.executeUpdate("create table testBinary (BINARY value);");
        stPrep = conn.prepareStatement("insert into testBinary values (?);");
        stPrep.setBytes(1, testString.getBytes());
        stPrep.execute();
        try {
            stQuery = conn.createStatement();
            res = stQuery.executeQuery("select * from testBinary");
            assertTrue(res.next());
            byte[] output = res.getBytes(1);
            String helloTest = new String(output);
            assertNotNull(helloTest);
            assertEquals(testString, helloTest);
        } finally {
            if (res != null) res.close();
            if (stPrep != null) stPrep.close();
            if (st != null) st.close();
            if (stQuery != null) stQuery.close();
        }
    }

    public void testGetBytesString() throws SQLException {
        int i = 1;

        // null value
        res.next();
        for (String t : colNames) {
            assertNull(res.getBytes(t));
        }

        try {
            res.close();
            res.getBytes(colNames.get(24));
            fail("Should get Exception");
        } catch (SQLException e) {
            //ok
        }
    }

    // last assertion fails: invalid conversion. Test passes on RI
    public void testGetBytesStringVarbinary() throws SQLException {
        Statement st = null;
        Statement stQuery = null;
        PreparedStatement stPrep = null;
        ResultSet res = null;

        // setup
        try {
            String testString = "HelloWorld";
            st = conn.createStatement();
            st.executeUpdate("create table testBinary (VARBINARY value);");
            stPrep = conn
                    .prepareStatement("insert into testBinary values (?);");
            stPrep.setBytes(1, testString.getBytes());
            stPrep.execute();

            stQuery = conn.createStatement();
            res = stQuery.executeQuery("select value from testBinary");
            assertTrue(res.next());
            byte[] output = res.getBytes("value");
            String helloTest = new String(output);
            assertNotNull(helloTest);
            assertEquals(testString, helloTest);
        } finally {
            if (res != null) res.close();
            if (stPrep != null) stPrep.close();
            if (st != null) st.close();
            if (stQuery != null) stQuery.close();
        }

    }

     // last assertion fails: invalid conversion. Test passes on RI
    public void testGetBytesStringBinary() throws SQLException {
        Statement st = null;
        Statement stQuery = null;
        PreparedStatement stPrep = null;
        ResultSet res = null;

        // setup

        String testString = "HelloWorld";
        st = conn.createStatement();
        st.executeUpdate("create table testBinary (BINARY value);");
        stPrep = conn.prepareStatement("insert into testBinary values (?);");
        stPrep.setBytes(1, testString.getBytes());
        stPrep.execute();
        try {
            stQuery = conn.createStatement();
            res = stQuery.executeQuery("select value from testBinary");
            assertTrue(res.next());
            byte[] output = res.getBytes("value");
            String helloTest = new String(output);
            assertNotNull(helloTest);
            assertEquals(testString, helloTest);
        } finally {
            if (res != null) res.close();
            if (stPrep != null) stPrep.close();
            if (st != null) st.close();
            if (stQuery != null) stQuery.close();
        }
    }

    public void testGetConcurrency() throws SQLException {
        assertEquals(ResultSet.CONCUR_UPDATABLE, res.getConcurrency());
    }

    public void testGetDateInt() throws SQLException {
        GregorianCalendar testCal = new GregorianCalendar(1799, Calendar.MAY, 26, 0, 0);
        Date input = new Date(testCal.getTimeInMillis());
        Date d = res.getDate(15);
        assertEquals(input.toString(),"1799-05-26");
        assertEquals(input,d);

        try {
            d = res.getDate(500);
            fail("Should get exception");
        } catch (SQLException e) {
            //ok
        }

        // null value
        assertTrue(res.next());
        d = res.getDate(15);
        assertNull(d);
    }

    public void testGetDateIntCalendar() throws SQLException {
        GregorianCalendar testCal = new GregorianCalendar(1799, Calendar.MAY, 26, 0, 0);
        Date input = new Date(testCal.getTimeInMillis());
        Date d = res.getDate(15, testCal);

        assertEquals(input.toString(),"1799-05-26");
        assertEquals(input,d);

        try {
            d = res.getDate(500, testCal);
            fail("Should get exception");
        } catch (SQLException e) {
            //ok
        }


        // null value
        assertTrue(res.next());
        d = res.getDate(15,testCal);
        assertNull(d);
    }

    public void testGetDateString() throws SQLException {
        GregorianCalendar testCal = new GregorianCalendar(1799, Calendar.MAY, 26, 0, 0);
        Date input = new Date(testCal.getTimeInMillis());
        Date d = res.getDate("dateVal");
        assertEquals(input.toString(),"1799-05-26");
        assertEquals(input,d);

        try {
            d = res.getDate("bla");
            fail("Should get exception");
        } catch (SQLException e) {
            //ok
        }

        // null value
        assertTrue(res.next());
        d = res.getDate("dateVal");
        assertNull(d);
    }

    public void testGetDateStringCalendar() throws SQLException {
        GregorianCalendar testCal = new GregorianCalendar(1799, Calendar.MAY, 26, 0, 0);
        Date input = new Date(testCal.getTimeInMillis());
        Date d = res.getDate("dateVal", testCal);
        assertEquals(input.toString(),"1799-05-26");
        assertEquals(input,d);

        try {
            res.getDate("bla", testCal);
            fail("Should get exception");
        } catch (SQLException e) {
            //ok
        }

        // null value
        assertTrue(res.next());
        d = res.getDate("dateVal",testCal);
        assertNull(d);
    }

    public void testGetDoubleInt() throws SQLException {
        double output = 0.0;
        double[] input = {2.0, 3.9 , 23.2};

        output = res.getDouble(8);
        assertEquals(input[0],output);

        output = res.getDouble(9);
        assertEquals(input[1],output);

        output = res.getDouble(10);
        assertEquals(input[2],output);

        try  {
            res.getDouble(500);
        } catch (SQLException e) {
            //ok
        }

        // null value
        res.next();
        output = res.getDouble(8);
        assertEquals(0.0,output);

        output = res.getDouble(9);
        assertEquals(0.0,output);

        output = res.getDouble(10);
        assertEquals(0.0,output);
    }

    public void testGetDoubleString() throws SQLException {
        double input = 23.2;
        double output = 0.0;

        output = res.getDouble("DoubleVal");
        assertEquals (input,output);

        try{
            res.getDouble("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }

        // null value
        assertTrue(res.next());
        output = res.getDouble("DoubleVal");
        assertEquals (0.0 , output);
    }

    public void testGetFloatInt() throws SQLException {
        float defaultF = 0.0f;
        float[] input = {3.9f, 23.2f, 33.3f};

        float output = res.getFloat(9);
        assertEquals(input[0], output);

        output = res.getFloat(10);
        assertEquals(input[1], output);

        output = res.getFloat(11);
        assertEquals(input[2], output);

        try {
            res.getFloat(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        output = res.getFloat(8);
        assertEquals(defaultF, output);

        output = res.getFloat(9);
        assertEquals(defaultF, output);

        output = res.getFloat(10);
        assertEquals(defaultF, output);
    }

    public void testGetFloatString() throws SQLException {
        float defaultF = 0.0f;
        String[] input = {"RealVal", "DoubleVal", "FloatVal"};
        float[] inputF = {3.9f, 23.2f, 33.3f};

        float output = res.getFloat(input[0]);
        assertEquals(inputF[0], output);

        output = res.getFloat(input[1]);
        assertEquals(inputF[1], output);

        output = res.getFloat(input[2]);
        assertEquals(inputF[2], output);

        try {
            res.getFloat(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        output = res.getFloat(8);
        assertEquals(defaultF, output);

        output = res.getFloat(9);
        assertEquals(defaultF, output);

        output = res.getFloat(10);
        assertEquals(defaultF, output);
    }

    public void testGetIntInt() throws SQLException {
        // real input val -1, 22, 2, 33,3, 1, 2
        List<Integer> input = Arrays.asList(1, -1, 22, 2, 33,3, 1, 2);
        ListIterator<Integer> it = input.listIterator();
        Double test2 = new Double(23.2);
        for (int i = 1;i<9;i++ ) {
            assertEquals(it.next().intValue(),res.getInt(i));
        }

        try {
            res.getInt(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        for (int i = 2;i<11;i++ ) {
            assertEquals(0,res.getInt(i));
        }
    }

    public void testGetIntString() throws SQLException {
        List<String> inputS = Arrays.asList("BoolVal", "IntVal", "LongVal",
                "Bint", "Tint", "Sint", "Mint", "IntegerVal");
        ListIterator<String> itS = inputS.listIterator();
        List<Integer> input = Arrays.asList(1, -1, 22, 2, 33, 3, 1, 2);
        ListIterator<Integer> it = input.listIterator();
        while (it.hasNext()) {
            assertEquals(it.next().intValue(), res.getInt(itS.next()));
        }

        try {
            res.getInt("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        for (String s : inputS) {
            assertEquals(0, res.getInt(s));
        }
    }

    public void testGetLongInt() throws SQLException {
        long maxVal = Long.MAX_VALUE;
        long minVal = Long.MIN_VALUE;

        assertEquals(maxVal, res.getLong(27));
        assertEquals(minVal, res.getLong(28));

        try {
            res.getInt(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        assertEquals(0, res.getLong(27));
        assertEquals(0, res.getLong(28));
    }

    public void testGetLongString() throws SQLException {
        long maxVal = Long.MAX_VALUE;
        long minVal = Long.MIN_VALUE;
        assertEquals(maxVal, res.getLong("MaxLongVal"));
        assertEquals(minVal, res.getLong("MinLongVal"));

        try {
            res.getInt("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        assertEquals(0,res.getLong("MaxLongVal"));
        assertEquals(0,res.getLong("MinLongVal"));
    }

    /**
     * Test method for {@link java.sql.ResultSet#getMetaData()}.
     * type mappings according to
     * http://java.sun.com/j2se/1.3/docs/guide/jdbc/spec/jdbc-spec.frame8.html
     * Not supported datatypes are not checked.
     *
     * Wrong value returned for Long: java.lang.String (VARCHAR)
     */
    public void testGetMetaData() throws SQLException {
        /*
         * List<String> types = Arrays.asList("BOOLEAN", "INT", "LONG",
         * "BIGINT", "TINYINT", "SMALLINT", "MEDIUMINT", "INTEGER", "REAL",
         * "DOUBLE", "FLOAT", "DECIMAL", "NUMERIC", "CHAR(20)", "DATE", "TIME",
         * "TIMESTAMP", "DATETIME", "TINYBLOB", "BLOB", "MEDIUMBLOB",
         * "LONGBLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "BIGINT",
         * "BIGINT","URL","URL");
         */
        List<String> types = Arrays.asList("VARCHAR", "INTEGER", "INTEGER",
                "BIGINT", "SMALLINT", "SHORT", "INTEGER", "INTEGER", "FLOAT",
                "DOUBLE", "DOUBLE", "DECIMAL", "NUMERIC", "VARCHAR", "DATE",
                "TIME", "TIMESTAMP", "DATETIME", "BLOB", "BLOB", "BLOB",
                "BLOB", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "BIGINT",
                "BIGINT", "URL", "URL");



        ListIterator<String> it = types.listIterator();
        ListIterator<String> colNameIt = colNames.listIterator();
        ResultSetMetaData meta = res.getMetaData();
        assertNotNull(meta);
        assertEquals("Error in test setup. Columns do not match", types
                .size(), meta.getColumnCount());
        for (int i = 1; i < 31; i++) {
            String colName = colNameIt.next();
            String type = it.next();
            if (supported[i - 1]) {
                assertTrue("Wrong column name at " + i, colName
                        .equalsIgnoreCase(meta.getColumnName(i)));
                assertTrue("Wrong type at " + i+" required" +type+ " but is "+meta.getColumnTypeName(i), type.equalsIgnoreCase(meta
                        .getColumnTypeName(i)));
            }
        }
    }

    // Wrong value returned for Long: java.lang.String
    public void testGetObjectInt() throws SQLException {
        for (int i = 1; i <= typeMap.length; i++) {
            if (supported[i-1]) {
                Object value = res.getObject(i);
                assertTrue("value " + value.getClass().getName()
                        + " does not correspond " + typeMap[i-1] + "at "+i, value
                        .getClass().equals(typeMap[i-1]));
            }
        }

        try {
            res.getObject(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

        res.next();
        for (int i = 1; i <= typeMap.length; i++) {
            Object value = res.getObject(i);
            assertNull(value);
        }
    }

    // Wrong value returned for Long: java.lang.String
    public void testGetObjectString() throws SQLException {
        ListIterator<String> colNameIt = colNames.listIterator();
        for (int i = 1; i <= typeMap.length; i++) {
            String name = colNameIt.next();
            if (supported[i-1]) {
                Object value = res.getObject(name);
                assertTrue("value " + value.getClass().getName()
                        + " for "+name+" does not correspond " + typeMap[i-1] + "at "+i, value
                        .getClass().equals(typeMap[i-1]));
            }
        }

        try {
            res.getObject("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }


        colNameIt = colNames.listIterator();
        res.next();
        for (int i = 1; i <= typeMap.length; i++) {
                Object value = res.getObject(colNameIt.next());
               assertNull(value);
        }
    }

    // If there is no current row 0 must be returned. res.close() does not wrap up
    public void testGetRow() throws SQLException {
        assertEquals(1, res.getRow());
        assertTrue(res.isFirst());
        res.next();
        assertEquals(2, res.getRow());
        assertTrue(res.isLast());
        res.next();
        assertTrue(res.isAfterLast());
        assertEquals(0, res.getRow());

        try {
            res.close();
            res.getRow();
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetShortInt() throws SQLException {
        short shorty = res.getShort(6);
        assertEquals(3,shorty);

        res.next();
        shorty = res.getShort(6);
        assertEquals(0,shorty);

        try {
            res.getShort(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    public void testGetShortString() throws SQLException {
        short shorty = res.getShort("Sint");
        assertEquals(3,shorty);

        res.next();
        shorty = res.getShort("Sint");
        assertEquals(0,shorty);

        try {
            res.getShort("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    /**
     * According to spec info.getStatement should return null but an exception
     * is thrown: stale result set.
     */
    public void testGetStatement() throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet info = meta.getTypeInfo();
        Statement statement2 = info.getStatement();
        assertNull(statement2);

        statement2 = res.getStatement();
        assertEquals(st, statement2);

       // exception testing
        try {
            res.close();
            res.getStatement();
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    public void testGetStringInt() throws SQLException {
        List<String> texts = Arrays.asList("Test text message tiny",
                "Test text", "Test text message medium",
                "Test text message long");
        int i = 23;

        //text and exception testing
        for (String t : texts) {
            assertEquals(t, res.getString(i));
            i++;
        }

        // the rest: everything should work with getString

        texts = Arrays.asList("1", "-1", "22", "2", "33",
         "3","1","2","3.9","23.2","33.3","44",
        "5", "test string", "1799-05-26", "12:35:45", "2007-10-09 14:28:02.0",
        "1221-09-22 10:11:55","1","2","3","4");
        i= 1;

        for (String t : texts) {
            assertEquals(t, res.getString(i));
            i++;
        }

        //null testing
        i = 1;
        res.next();
        for (String t : values) {
            assertNull(res.getString(i));
            i++;
        }

        // exception testing
        try {
            res.getString(500);
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }

    }

    public void testGetStringString() throws SQLException {
        ListIterator<String> colNameIt = colNames.listIterator();
        for (String t : values) {
            assertEquals(t, res.getString(colNameIt.next()));
        }

        res.next();
        for (String name: colNames) {
            assertNull(res.getString(name));
        }

        try {
            res.getString("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // getTime should return Time value for a TIMESTAMP type but returns null
    public void testGetTimeInt() throws SQLException {
        // values "12:35:45", "2007-10-09 14:28:02.0", "1221-09-22 10:11:55"

        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 35);
        cal.set(Calendar.SECOND, 45);
        cal.set(Calendar.MILLISECOND, 0);
        // set with calendar value (correct init time: since epoch)
        long millis = cal.getTime().getTime();
        Time t1 = new java.sql.Time(millis);
        assertNotNull("t1", t1);


        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis2 = cal2.getTime().getTime();
        Time t2 = new java.sql.Time(millis2);

        int i = 16;

        Time resTime = res.getTime(i);
        assertNotNull("Pos " + i + " null", resTime);
        assertEquals(t1.toString(), resTime.toString());
        assertEquals(t1.getTime(), resTime.getTime());
        assertEquals(t1, resTime);
        // Compatibility Test: TIMESTAMP value
        i = 17;

        resTime = res.getTime(i);
        assertNotNull("Pos " + i + " null", resTime);
        assertEquals(t2.toString(), resTime.toString());
        assertEquals(t2.getTime(), resTime.getTime());
        assertEquals(t2, resTime);

        i = 16;
        res.next();
        assertNull(res.getTime(i));

        try {
            res.getTime(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

     // getTime on TIMESTAMP value fails: returns null
    public void testGetTimeIntCalendar() throws SQLException {
        List<Time> times = new LinkedList<Time>();
        List<Calendar> cals = new LinkedList<Calendar>();
        // Arrays.asList("12:35:45", "2007-10-09 14:28:02.0",
        // "1221-09-22 10:11:55");

        Calendar cal1 = new GregorianCalendar();
        cal1.clear();
        cal1.set(Calendar.HOUR_OF_DAY, 12);
        cal1.set(Calendar.MINUTE, 35);
        cal1.set(Calendar.SECOND, 45);
        cal1.set(Calendar.MILLISECOND, 0);

        long millis = cal1.getTime().getTime();
        Time t1 = new java.sql.Time(millis);

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis2 = cal2.getTime().getTime();
        Time t2 = new java.sql.Time(millis2);

        // TIME value

        int i = 16;

        Time timeRes = res.getTime(i,new GregorianCalendar());
        assertNotNull(timeRes);
        assertEquals(t1.toString(), timeRes.toString());
        assertEquals(t1.getTime(), timeRes.getTime());
        assertEquals(t1, timeRes);

        // TIMESTAMP value
        i = 17;

         timeRes = res.getTime(i,new GregorianCalendar());
         assertNotNull(timeRes);
         assertEquals(t2.toString(), timeRes.toString());
         assertEquals(t2.getTime(), timeRes.getTime());
         assertEquals(t2, timeRes);

         res.next();
         for (Calendar c : cals) {
             assertNull(res.getTime(16,c));
             i++;
         }

        try {
            res.getTime(500,Calendar.getInstance());
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // getTime should return a Time value for a TIMESTAMP type but returns null
    public void testGetTimeString() throws SQLException {
        List<Time> times = new LinkedList<Time>();

        List<String> stringTimes = Arrays.asList("timeVal", "TS", "DT");
        Iterator<String> it = stringTimes.iterator();

        // Arrays.asList("12:35:45", "2007-10-09 14:28:02.0",
        // "1221-09-22 10:11:55");

        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 35);
        cal.set(Calendar.SECOND, 45);
        cal.set(Calendar.MILLISECOND, 0);

        long millis = cal.getTime().getTime();
        Time t1 = new java.sql.Time(millis);

        String col = it.next();

        Time timeRes = res.getTime(col);
        assertNotNull(timeRes);
        assertEquals(t1.toString(), timeRes.toString());
        assertEquals(t1.getTime(), timeRes.getTime());
        assertEquals(t1, timeRes);

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis2 = cal.getTime().getTime();
        Time t2 = new java.sql.Time(millis2);

        col = it.next();

        timeRes = res.getTime(col);
        assertNotNull(timeRes);
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.getTime(), timeRes.getTime());
        assertEquals(t2, timeRes);

        res.next();
        assertNull(res.getTime(col));

        try {
            res.getTime("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            //ok
        }
    }

    // getTime on TIMESTAMP value fails: returns null
    public void testGetTimeStringCalendar() throws SQLException {
        List<Time> times = new LinkedList<Time>();

        List<String> stringTimes = Arrays.asList("timeVal", "TS", "DT");
        Iterator<String> it = stringTimes.iterator();
        List<Calendar> cals = new LinkedList<Calendar>();

        // Arrays.asList("12:35:45", "2007-10-09 14:28:02.0",
        // "1221-09-22 10:11:55");

        Calendar cal1 = new GregorianCalendar();
        cal1.clear();
        cal1.set(Calendar.HOUR_OF_DAY, 12);
        cal1.set(Calendar.MINUTE, 35);
        cal1.set(Calendar.SECOND, 45);
        cal1.set(Calendar.MILLISECOND, 0);

        long millis = cal1.getTime().getTime();
        Time t1 = new java.sql.Time(millis);

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis2 = cal2.getTime().getTime();
        Time t2 = new java.sql.Time(millis2);

        // TIME value
        String col = it.next();

        Time timeRes = res.getTime(col, new GregorianCalendar());
        assertNotNull(timeRes);
        assertEquals(t1.toString(), timeRes.toString());
        assertEquals(t1.getTime(), timeRes.getTime());
        assertEquals(t1, res.getTime(col));
        //TIMESTAMP value
        col = it.next();

        timeRes = res.getTime(col, new GregorianCalendar());
        assertNotNull(timeRes);
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.getTime(), timeRes.getTime());
        assertEquals(t2, res.getTime(col));

        res.next();
        assertNull(res.getTime(stringTimes.get(0), new GregorianCalendar()));

        try {
            res.getTime("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetTimestampInt() throws SQLException {
        List<Timestamp> times = new LinkedList<Timestamp>();

        List<String> stringTimes = Arrays.asList("timeVal", "TS", "DT");
        Iterator<String> it = stringTimes.iterator();
        List<Calendar> cals = new LinkedList<Calendar>();

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis = cal2.getTime().getTime();
        Timestamp t2 = new Timestamp(millis);
        times.add(t2);

        Calendar cal3 = new GregorianCalendar();
        cal3.set(Calendar.YEAR, 1221);
        cal3.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal3.set(Calendar.DATE, 22);
        cal3.set(Calendar.HOUR_OF_DAY, 10);
        cal3.set(Calendar.MINUTE, 11);
        cal3.set(Calendar.SECOND, 55);
        cal3.set(Calendar.MILLISECOND, 0);

        millis = cal3.getTime().getTime();
        Timestamp t3 = new Timestamp(millis);
        times.add(t3);
        // TIMESTAMP value
        int i = 17;

        Timestamp timeRes = res.getTimestamp(i);
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.getTime(), timeRes.getTime());
        assertEquals(t2, timeRes);
        // DATE value
        i = 18;
        timeRes = res.getTimestamp(i);
        assertEquals(t3.toString(), timeRes.toString());
        assertEquals(t3.getTime(), timeRes.getTime());
        assertEquals(t3, timeRes);

        res.next();
        assertNull(res.getTime(i));

        try {
            res.getTime(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetTimestampIntCalendar() throws SQLException {
        List<Timestamp> times = new LinkedList<Timestamp>();

        List<String> stringTimes = Arrays.asList("timeVal", "TS", "DT");
        Iterator<String> it = stringTimes.iterator();
//        List<Calendar> cals = new LinkedList<Calendar>();

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis = cal2.getTime().getTime();
        Timestamp t2 = new Timestamp(millis);
        times.add(t2);
        //
         Calendar cal3 = new GregorianCalendar();
          cal3.set(Calendar.YEAR, 1221);
          cal3.set(Calendar.MONTH, Calendar.SEPTEMBER);
          cal3.set(Calendar.DATE, 22);
         cal3.set(Calendar.HOUR_OF_DAY, 10);
         cal3.set(Calendar.MINUTE, 11);
         cal3.set(Calendar.SECOND, 55);
         cal3.set(Calendar.MILLISECOND, 0);

         millis = cal3.getTime().getTime();
         Timestamp t3 = new Timestamp(millis);
         times.add(t3);

//         cals.add(cal1);
//         cals.add(cal2);
//         cals.add(cal3);
//
//        ListIterator<Calendar> calIt = cals.listIterator();

        int i = 17;

        Timestamp timeRes = res.getTimestamp(i,new GregorianCalendar());
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2, timeRes);

        i = 18;

        timeRes = res.getTimestamp(i,new GregorianCalendar());
        assertEquals(t3.toString(), timeRes.toString());
        assertEquals(t3, timeRes);

        res.next();
        assertNull(res.getTime(17,cal2));
        assertNull(res.getTime(18,cal3));

        try {
            res.getTime(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetTimestampString() throws SQLException {
        List<Timestamp> times = new LinkedList<Timestamp>();

        List<String> stringTimes = Arrays.asList( "TS", "DT");
        Iterator<String> it = stringTimes.iterator();
//        List<Calendar> cals = new LinkedList<Calendar>();

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis = cal2.getTime().getTime();
        Timestamp t2 = new Timestamp(millis);
        times.add(t2);
        //
         Calendar cal3 = new GregorianCalendar();
          cal3.set(Calendar.YEAR, 1221);
          cal3.set(Calendar.MONTH, Calendar.SEPTEMBER);
          cal3.set(Calendar.DATE, 22);
         cal3.set(Calendar.HOUR_OF_DAY, 10);
         cal3.set(Calendar.MINUTE, 11);
         cal3.set(Calendar.SECOND, 55);
         cal3.set(Calendar.MILLISECOND, 0);

         millis = cal3.getTime().getTime();
         Timestamp t3 = new Timestamp(millis);
         times.add(t3);

        String col = it.next();

        Timestamp timeRes = res.getTimestamp(col);
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.getTime(), timeRes.getTime());
        assertEquals(t2, timeRes);
        // DATE value
        col = it.next();

        timeRes = res.getTimestamp(col);
        assertEquals(t3.toString(), timeRes.toString());
        assertEquals(t3.toString(), timeRes.toString());
        assertEquals(t3.getTime(), timeRes.getTime());
        assertEquals(t3, timeRes);

        res.next();
        assertNull(res.getTime(stringTimes.get(0)));
        assertNull(res.getTime(stringTimes.get(1)));

        try {
            res.getTime(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetTimestampStringCalendar() throws SQLException {
        List<Timestamp> times = new LinkedList<Timestamp>();

        List<String> stringTimes = Arrays.asList( "TS", "DT");
        Iterator<String> it = stringTimes.iterator();

        Calendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.YEAR, 2007);
        cal2.set(Calendar.MONTH, Calendar.OCTOBER);
        cal2.set(Calendar.DATE, 9);
        cal2.set(Calendar.HOUR_OF_DAY, 14);
        cal2.set(Calendar.MINUTE, 28);
        cal2.set(Calendar.SECOND, 02);
        cal2.set(Calendar.MILLISECOND, 0);

        long millis = cal2.getTime().getTime();
        Timestamp t2 = new Timestamp(millis);
        times.add(t2);
        //
         Calendar cal3 = new GregorianCalendar();
          cal3.set(Calendar.YEAR, 1221);
          cal3.set(Calendar.MONTH, Calendar.SEPTEMBER);
          cal3.set(Calendar.DATE, 22);
         cal3.set(Calendar.HOUR_OF_DAY, 10);
         cal3.set(Calendar.MINUTE, 11);
         cal3.set(Calendar.SECOND, 55);
         cal3.set(Calendar.MILLISECOND, 0);

         millis = cal3.getTime().getTime();
         Timestamp t3 = new Timestamp(millis);
         times.add(t3);

        Timestamp timeRes = res.getTimestamp(stringTimes.get(0),cal2);
        assertEquals(t2.toString(), timeRes.toString());
        assertEquals(t2.getTime(), timeRes.getTime());
        assertEquals(t2, timeRes);
            // DATE value
        timeRes = res.getTimestamp(stringTimes.get(1),cal3);
        assertEquals(t3.toString(), timeRes.toString());
        assertEquals(t3.getTime(), timeRes.getTime());
        assertEquals(t3, timeRes);

        // calIt = cals.listIterator();

        res.next();
        assertNull(res.getTime(stringTimes.get(0),cal2));
        assertNull(res.getTime(stringTimes.get(1),cal3));

        try {
            res.getTime(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    // res.close() does not wrap up
    public void testGetType() throws SQLException {
        assertEquals(ResultSet.TYPE_FORWARD_ONLY, res.getType());

        try {
            st.close();
            res.getType();
            fail("Exception not thrown.");
        } catch (SQLException e) {
            //ok
        }

    }

    public void testGetURLInt() throws SQLException, MalformedURLException {
        URL input = new URL("http://www.android.com");
        URL validURL = res.getURL(29);
        assertEquals(input, validURL);

        try {
            URL invalidURL = res.getURL(30);
            assertNull(invalidURL);
        } catch (SQLException e) {
            // ok
        }

        res.next();
        assertNull(res.getURL(29));
        assertNull(res.getURL(30));

        try {
            res.getURL(500);
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }

    public void testGetURLString() throws MalformedURLException, SQLException {
        URL input = new URL("http://www.android.com");
        URL validURL = res.getURL("validURL");
        assertEquals(input, validURL);

        try {
            URL invalidURL = res.getURL("invalidURL");
            assertNull(invalidURL);
        } catch (SQLException e) {
            // ok
        }

        res.next();
        assertNull(res.getURL("validURL"));
        assertNull(res.getURL("invalidURL"));

        try {
            res.getURL("bla");
            fail("Exception expected");
        } catch (SQLException e) {
            // ok
        }
    }
}
