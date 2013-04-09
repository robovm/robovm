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

import SQLite.Database;
import SQLite.Exception;
import SQLite.Function;
import SQLite.FunctionContext;
import SQLite.Stmt;
import SQLite.TableResult;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Statement;
import tests.support.DatabaseCreator;

public final class OldFunctionContextTest extends OldSQLiteTest {

    private Database db = null;

    @Override public void setUp() throws java.lang.Exception {
        super.setUp();
        db = new Database();
        db.open(dbFile.getPath(), 0);
        Statement st = conn.createStatement();
        st.execute(DatabaseCreator.CREATE_TABLE2);
        st.execute(DatabaseCreator.CREATE_TABLE_SIMPLE1);
        st.close();
    }

    public void testSet_resultString() throws Exception {
        TestFCString testString = new TestFCString();
        db.exec("insert into " + DatabaseCreator.TEST_TABLE2
                + " (ftext) values ('TestInput')", null);
        db.create_function("test", 1, testString);
        TableResult res = db.get_table("select test(ftext) from "
                + DatabaseCreator.TEST_TABLE2);
        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertEquals("TestInput", val);
    }

    public void testSet_resultInt() throws Exception {
        TestFCInt testInt = new TestFCInt();
        db.exec("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + "  values (1,'" + testInt.intVal + "',3)", null);
        db.create_function("testInt", 1, testInt);
        TableResult res = db.get_table("select testInt(speed) from "
                + DatabaseCreator.SIMPLE_TABLE1);
        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertEquals(testInt.intVal, Integer.parseInt(val));
    }

    public void testSet_resultDouble() throws Exception {
        SinFunc testD = new SinFunc();
        db.exec("insert into " + DatabaseCreator.TEST_TABLE2
                + " (fdouble)  values (" + testD.testDouble + ")", null);
        db.create_function("testDouble", 1, testD);
        TableResult res = db.get_table("select testDouble(fdouble) from "
                + DatabaseCreator.TEST_TABLE2);
        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertEquals(testD.testDouble, Double.parseDouble(val));

        assertTrue(testD.functionCalled);
    }

    public void testSet_error() throws Exception {
        TestFCError testError = new TestFCError();
        SinFunc testD = new SinFunc();
        db.exec("insert into " + DatabaseCreator.TEST_TABLE2
                + " (fdouble)  values (" + testD.testDouble + ")", null);
        db.create_function("testError", 1, testError);

        try {
        TableResult res = db.get_table("select testError(fdouble) from "
                + DatabaseCreator.TEST_TABLE2);
        fail("Should get Exception");
        } catch (Exception e) {
            assertEquals("error in step", e.getMessage());
        }

        assertFalse(testD.functionCalled);
    }

    public void testSet_resultByteArray() throws Exception, UnsupportedEncodingException {
        Stmt st = null;
        TestFCByteArray testBinArrayFnc = new TestFCByteArray();
        String expected = "";
        expected = "X'" + getHexString(testBinArrayFnc.byteVal) + "'";

        // setup
        db.exec("create table testBinaryData (binVal BINARY) ;", null);

        try {
        st = db.prepare("insert into testBinaryData values (?)");
        st.bind(1, testBinArrayFnc.byteVal);
        st.step();


        db.create_function("testBinArray", 1, testBinArrayFnc);
        TableResult res = db
                .get_table("select testBinArray(binVal) from testBinaryData");

        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertTrue(expected.equalsIgnoreCase(val));

        assertTrue(testBinArrayFnc.functionCalled);

        } finally {
            //teardown
            db.exec("drop table testBinaryData;", null);
        }
    }

    /**
     * ZeroBlob not supported
     */
    public void testSet_result_zeroblob() throws Exception,
            UnsupportedEncodingException {
        Stmt st = null;
        TestFCZeroBlob testZeroBlobFnc = new TestFCZeroBlob();
        byte[] byteVal = {(byte) 1, (byte) 2, (byte) 3};


        // setup
        db.exec("create table testBinaryData (binVal BINARY) ;", null);

        try {
        st = db.prepare("insert into testBinaryData values (?)");
        st.bind(1, byteVal);
        st.step();


        db.create_function("testZeroBlob", 0, testZeroBlobFnc);
        TableResult res = db
                .get_table("select testZeroBlob() from testBinaryData");
        TableResult res2 = db.get_table("select zeroblob("
                + testZeroBlobFnc.numBytes + ") from testBinaryData");

        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertNotNull(val);

        assertEquals(((String[]) res2.rows.elementAt(0))[0], val);
        assertTrue(testZeroBlobFnc.functionCalled);

        } finally  {
         // teardown
            db.exec("drop table if exists testBinaryData;", null);
        }
    }

    /**
     * Test Method results in a segmentation fault
     */
    public void testCount() throws SQLException, Exception {
        TestFCCount countTest = new TestFCCount();
        int inputCount = 10;

        assertFalse(countTest.functionCalled);

        DatabaseCreator.fillTestTable2(conn, inputCount);
        db.create_function("testCount", 0, countTest);
        // the invokation of testCount leads to a Segmentation fault
        /*
        TableResult res = db
                .get_table("select testCount() from "+DatabaseCreator.TEST_TABLE2);

        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];

        assertTrue(countTest.functionCalled);
        assertEquals(inputCount,Integer.parseInt(val));
        */

    }

    class TestFCError implements Function {
        public boolean functionCalled = false;
        public String errorMsg = "FunctionError";

        public void function(FunctionContext fc, String args[]) {
            functionCalled = true;
            fc.set_error(errorMsg);
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestFCCount implements Function {
        public boolean functionCalled = false;
        public int noOfRows = 0;

        public void function(FunctionContext fc, String args[]) {
            functionCalled = true;
            noOfRows = fc.count();
            fc.set_result(noOfRows);
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestFCZeroBlob implements Function {
        public int numBytes = 16;
        public boolean functionCalled = false;

        public void function(FunctionContext fc, String args[]) {
            functionCalled = true;
            fc.set_result_zeroblob(numBytes);
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestFCString implements Function {
        public String testString = "TestString";
        public boolean functionCalled;

        public void function(FunctionContext fc, String args[]) {
            assertNotNull(args);
            functionCalled = true;
            fc.set_result(args[0]);
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestFCInt implements Function {
        public int intVal = Integer.MAX_VALUE;
        public boolean functionCalled;

        public void function(FunctionContext fc, String args[]) {
            assertNotNull(args);
            functionCalled = true;
            fc.set_result(Integer.parseInt(args[0]));
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestFCByteArray implements Function {
        public byte[] byteVal = {(byte)  1, (byte) 2, (byte) 3};
        public boolean functionCalled;

        public void function(FunctionContext fc, String args[]) {
            assertNotNull(args);
            functionCalled = true;
            fc.set_result(args[0].getBytes());
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class SinFunc implements Function {
        public Double testDouble = 3.0;
        public boolean functionCalled = false;

        public void function(FunctionContext fc, String args[]) {
            Double d = new Double(args[0]);
            functionCalled = true;
            fc.set_result(d.doubleValue());
        }

        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    static final byte[] HEX_CHAR_TABLE = {
            (byte)'0', (byte)'1', (byte)'2', (byte)'3',
            (byte)'4', (byte)'5', (byte)'6', (byte)'7',
            (byte)'8', (byte)'9', (byte)'a', (byte)'b',
            (byte)'c', (byte)'d', (byte)'e', (byte)'f'
          };

    public static String getHexString(byte[] raw)
            throws UnsupportedEncodingException {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, "ASCII");
    }
}
