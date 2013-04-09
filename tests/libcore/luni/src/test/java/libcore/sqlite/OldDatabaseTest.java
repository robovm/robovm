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

import SQLite.Authorizer;
import SQLite.Blob;
import SQLite.BusyHandler;
import SQLite.Callback;
import SQLite.Constants;
import SQLite.Database;
import SQLite.Exception;
import SQLite.Function;
import SQLite.FunctionContext;
import SQLite.ProgressHandler;
import SQLite.Stmt;
import SQLite.TableResult;
import SQLite.Trace;
import SQLite.Vm;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import tests.support.DatabaseCreator;
import tests.support.MockFunction;
import tests.support.ThreadPool;

public final class OldDatabaseTest extends OldSQLiteTest {

    private static ErrorTracker tracker = null;

    private Statement statement;

    private Database db = null;

    private static final int numThreads = 10;

    private static final int numOfRecords = 30;

    @Override public void setUp() throws java.lang.Exception {
        super.setUp();
        assertNotNull("Could not establish DB connection",conn);
        tracker = new ErrorTracker();

        statement = conn.createStatement();

        // Cleanup tables if necessary

        DatabaseMetaData meta = conn.getMetaData();
        assertNotNull(meta);
        ResultSet userTab = meta.getTables(null, null, null, null);
        while (userTab.next()) {
            String tableName = userTab.getString("TABLE_NAME");
            this.statement.execute("drop table " + tableName);
        }

        // Create default test table
        statement.execute(DatabaseCreator.CREATE_TABLE_SIMPLE1);
        statement.close();

        db = new Database();
        db.open(dbFile.getPath(), 0);
        db.busy_handler(null);
    }

    public void tearDown() throws java.lang.Exception {
        try {
            db.close();
        } catch (Exception e) {
            if (!(e.getMessage().equals("database already closed"))) {
                System.err.println("Error closing DB " + dbFile.getPath());
            }
        }
        tracker.reset();
        super.tearDown();
    }

    public void testDatabase() throws Exception {
        // db closed
        Database db2 = new Database();
        db.close();
        db2 = new Database();
        db2.open(dbFile.getPath(), 0);
        db2.close();
        db.open(dbFile.getPath(), 0);
        //db is open
        db2.open(dbFile.getPath(), 0);
        db2.close();
    }

    public void testOpen() throws Exception {
        db.close();
        db.open(dbFile.getPath(), 0);
        // open second db while db1 still open
        Database db2 = new Database();
        db2.open(dbFile.getPath(), 0);
        db2.open(dbFile.getPath(), 0);
        db2.close();
        // open non db file
        try {
            URL file = OldDatabaseTest.class.getResource("/blob.c");
            db2.open(file.getPath(), 0);
            fail("Should not be able to open non db file");
        } catch (SQLite.Exception e) {
            assertEquals("unknown error in open", e.getMessage());
        }
    }

    public void testOpen_aux_file() {
        File temp = null;
        try {
            db.open_aux_file("");
            fail("open should fail");
        } catch (Exception e) {
            assertEquals("unsupported", e.getMessage());
        }

     /*
        try {
            temp = File.createTempFile("openAuxMethod", ".db");
            db.open_aux_file("");
            db.exec("create table AUX_TABLE", null);
            db.close();
        } catch (Exception e) {
            temp.delete();
            fail("Error handling temporary file "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            temp.delete();
            fail("Could not create temporary File");
            e.printStackTrace();
        }
        try {
            db.open(dbFile.getPath(),0);
            db.exec("select * from AUX_TABLE", null);
            fail("Statement should fail");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        temp.delete();
        */
    }

    public void testClose() throws Exception {
        try {
            db.close();
            db.get_table("test");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("database already closed"));
            try {
                db.open(dbFile.getPath(), 0);
            } catch (Exception e1) {
                fail("Database object could not be reopened after 'close': "
                        + e.getMessage());
                e1.printStackTrace();
            }
        }

        try {
            db.close();
            db.close();
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("database already closed"));
            db.open(dbFile.getPath(), 0);
        }
    }

    public void testExecStringCallback() throws Exception {
        TableResult res = new TableResult();
        db.exec("insert into " + DatabaseCreator.SIMPLE_TABLE1
                + " VALUES(1, 10, 20)", null);
        db.exec("select * from " + DatabaseCreator.SIMPLE_TABLE1, res);
        db.exec("delete from " + DatabaseCreator.SIMPLE_TABLE1 + " where 1", null);
        String row[] = (String[]) res.rows.elementAt(0);
        assertEquals(Integer.parseInt(row[0]), 1);
        assertEquals(Integer.parseInt(row[1]), 10);
        assertEquals(Integer.parseInt(row[2]), 20);
    }

    public void testExecStringCallbackStringArray() throws Exception {
        TableResult res = new TableResult();
        String args[] = new String[1];
        args[0] = "table";
        db.exec("select name from sqlite_master where type = '%q';", res, args);
        String[] s = (String[]) res.rows.elementAt(0);
        assertEquals(s[0], DatabaseCreator.SIMPLE_TABLE1);

        try {
            db.exec("select name from sqlite_master where type = ", res, args);
            fail("Testmethod should fail");
        } catch (Exception e) {
            // Ok
        }
    }

    public void testLast_insert_rowid() throws Exception {
        assertEquals(0, db.last_insert_rowid());
        db.exec("create table TEST5(id integer, firstname text, lastname text);", null);
        db.exec("insert into TEST5 values (1,'James','Bond');", null);
        db.exec("insert into TEST5 values (2,'Fiona','Apple');", null);
        assertEquals(2, db.last_insert_rowid());
        assertEquals(db.last_insert_rowid(), db.last_insert_rowid());

        db.exec("drop table TEST5;", null);
        assertEquals(2, db.last_insert_rowid());
    }

    /**
     * Reason for failure unknown: Database should be locked. Specification
     * of interrupt is scarce.
     */
    public void testInterrupt() throws Exception, SQLException {
        ThreadPool threadPool = new ThreadPool(numThreads);

        // initialization
        ResultSet userTabs;
        userTabs = conn.getMetaData().getTables(null, null, null, null);
        while (userTabs.next()) {
            String tableName = userTabs.getString("TABLE_NAME");
            if (tableName.equals(DatabaseCreator.TEST_TABLE1)) {
                statement.execute(DatabaseCreator.DROP_TABLE1);
            }
        }
        db.exec(DatabaseCreator.CREATE_TABLE3, null);
        db.exec(DatabaseCreator.CREATE_TABLE1, null);

        int id1 = numOfRecords - 3;
        threadPool.runTask(createTask1(id1, dbFile.getPath(), tracker));
        // should not be able to do any other insertions since task 1 holds lock
        int id2 = numOfRecords + 3;
        threadPool
                .runTask(createTask2Interrupt(id2, dbFile.getPath(), tracker));

        threadPool.join();

        List<String> errors = tracker.getErrors();
        System.out.println("Last error: " + db.error_message());
        if (errors.size() > 0) {
            assertEquals(errors.get(0), db.error_string(Constants.SQLITE_LOCKED));
            for (String s : errors) {
                Logger.global.info("INTERRUPT Error: " + s);
            }

        } else {
            fail("Should have one exception: database should be locked.");
        }

        // reset
        db.exec("delete from " + DatabaseCreator.TEST_TABLE1 + " where 1", null);
        db.exec("delete from " + DatabaseCreator.TEST_TABLE3 + " where 1", null);
    }

    /**
     * Returns wrong number for updates: returns value > 1 for select.
     */
    public void testChanges() throws Exception {
        TableResult res = new TableResult();
        assertTrue(db.changes() == 0);
        db.exec("INSERT INTO " + DatabaseCreator.SIMPLE_TABLE1
                + " VALUES(2, 5, 7);", null);
        int rows = (int) db.changes();
        assertEquals(1,db.changes());
        db.exec("update " + DatabaseCreator.SIMPLE_TABLE1
                + " set speed = 7, size= 5 where id = 2;", null);
        assertEquals(1,db.changes());
        db.exec("select * from " + DatabaseCreator.SIMPLE_TABLE1, res);
        assertEquals(0,db.changes());
        db.exec("INSERT INTO " + DatabaseCreator.SIMPLE_TABLE1
                + " VALUES(8, 5, 7);", null);
        db.exec("Update "+DatabaseCreator.SIMPLE_TABLE1+" set speed = 10;",null);
        assertTrue(db.changes() > 2);
    }

    /**
     * method test fails once in a while. Cannot be sure that exception is
     * thrown in every test execution.
     */
    public void testBusy_handler() throws SQLException, Exception {
        TestBusyHandler bh = new TestBusyHandler();
        db.busy_handler(bh);
        int counter = 0;
        ThreadPool threadPool = new ThreadPool(numThreads);

        // initialization
        ResultSet userTabs;
        userTabs = conn.getMetaData().getTables(null, null, null, null);
        while (userTabs.next()) {
            String tableName = userTabs.getString("TABLE_NAME");
            if (tableName.equals(DatabaseCreator.TEST_TABLE1)) {
                statement.execute(DatabaseCreator.DROP_TABLE1);
            }
        }
        db.exec(DatabaseCreator.CREATE_TABLE3, null);
        db.exec(DatabaseCreator.CREATE_TABLE1, null);


        try {
            conn.setAutoCommit(false);
            int id1 = numOfRecords - 3;
            threadPool.runTask(createTask1(id1, dbFile.getPath(), tracker));
            int id2 = numOfRecords + 3;
            threadPool.runTask(createTask2(id2, dbFile.getPath(), tracker));
            int oldID = 5;
            int newID = 100;
            threadPool.runTask(createTask3(oldID, dbFile.getPath(), newID,
                    tracker));

            threadPool.join();

            List<String> errors = tracker.getErrors();
            if (errors.size() > 0) {
//                 assertEquals(errors.get(0),
//                 db.error_string(Constants.SQLITE_LOCKED));
                for (String s: errors) {
                  System.out.println("Round 2 Error: "+s);
              }
            } else {
                fail("No error happened");
            }

            // reset


            db.exec("delete from " + DatabaseCreator.TEST_TABLE1 + " where 1",
                    null);
            db.exec("delete from " + DatabaseCreator.TEST_TABLE3 + " where 1",
                            null);
//
//            // increase timeout for retry
//            db.busy_timeout(1000);
//            db.busy_handler(bh);
//            tracker.reset();

//            threadPool = new ThreadPool(numThreads);
//
//            threadPool.runTask(createTask1(id1, dbFile.getPath(), tracker));
//            threadPool.runTask(createTask2(id2, dbFile.getPath(), tracker));
//
//            threadPool.join();
//
//            errors = tracker.getErrors();
//            if (errors.size() > 0) {
//                // assertEquals(errors.get(0),
//                // db.error_string(Constants.SQLITE_LOCKED));
//                for (String s: errors) {
//                    System.out.println("Round 2 Error"+s);
//                }
//            } else {
//                // ok
//                System.out.println("BUSY: No Error!");
//            }
//
//
        } finally {
            conn.setAutoCommit(true);
            db.exec(DatabaseCreator.DROP_TABLE1, null);
            db.exec(DatabaseCreator.DROP_TABLE3, null);
        }
    }

    /**
     * test fails. Cannot be sure that exception is thrown every time.
     * Database does not lock values.
     */
    public void testBusy_timeout() throws Exception, SQLException {
        int counter = 0;
        ThreadPool threadPool = new ThreadPool(numThreads);

        // initialization
        ResultSet userTabs = conn.getMetaData().getTables(null, null, null, null);
        while (userTabs.next()) {
            String tableName = userTabs.getString("TABLE_NAME");
            if (tableName.equals(DatabaseCreator.TEST_TABLE1)) {
                statement.execute(DatabaseCreator.DROP_TABLE1);
            }
        }
        db.exec(DatabaseCreator.CREATE_TABLE3, null);
        db.exec(DatabaseCreator.CREATE_TABLE1, null);

        // test run
        try {
            conn.setAutoCommit(false);

//            DatabaseCreator.fillTestTable1(conn, numOfRecords);
            // set to fail immediately if table is locked.
            db.busy_handler(null);
            db.busy_timeout(0);
            int id1 = numOfRecords - 3;

            threadPool.runTask(createTask2(id1, dbFile.getPath(), tracker));
            int id2 = numOfRecords + 3;
            threadPool.runTask(createTask1(id2, dbFile.getPath(), tracker));
            int oldID = 5;
            int newID = 100;
            threadPool.runTask(createTask3(oldID, dbFile.getPath(), newID,
                    tracker));

            threadPool.join();

            List<String> errors = tracker.getErrors();
            assertTrue("No error occurred on DB but should have",errors.size() > 0);

            assertEquals(errors.get(0),
            db.error_string(Constants.SQLITE_LOCKED));
            assertEquals(errors.get(0), "database is locked");

            // reset

            db.exec("delete from " + DatabaseCreator.TEST_TABLE1 + " where 1",
                    null);
            db.exec("delete from " + DatabaseCreator.TEST_TABLE3 + " where 1",
                            null);

            // increase timeout for retry
            db.busy_timeout(10000);
            db.busy_handler(null);
            tracker.reset();
            threadPool = new ThreadPool(numThreads);

            threadPool.runTask(createTask1(id1, dbFile.getPath(), tracker));
            threadPool.runTask(createTask2(id2, dbFile.getPath(), tracker));

            threadPool.join();

            errors = tracker.getErrors();
            if (errors.size() > 0) {
                fail("busy timeout should prevent from lock exception!");
                for (String s: errors) {
                    System.out.println("Round 2 Error"+s);
                }
            } else {
                // ok
            }
        } finally {
            conn.setAutoCommit(true);
            // cleanup
            db.exec(DatabaseCreator.DROP_TABLE1, null);
            db.exec(DatabaseCreator.DROP_TABLE3, null);
        }
    }

    public void testGet_tableString() throws Exception {
        TableResult emptyTable = new TableResult();
        //select from empty table
        TableResult res = db.get_table("select * from " + DatabaseCreator.SIMPLE_TABLE1);
        assertEquals(res.toString(), emptyTable.toString());
        //fill table-> t
//        DatabaseCreator.fillSimpleTable1(conn);
//        res = db.get_table("select * from "
//                + DatabaseCreator.SIMPLE_TABLE1);
//        assertFalse(emptyTable.toString().equals(res.toString()));

        db.exec("insert into " + DatabaseCreator.SIMPLE_TABLE1 + " VALUES(1, 10, 20)", null);
        res = db.get_table("select * from " + DatabaseCreator.SIMPLE_TABLE1);
        db.exec("delete from " + DatabaseCreator.SIMPLE_TABLE1
                + " where 1", null);
        String row[] = (String[]) res.rows.elementAt(0);
        assertEquals(Integer.parseInt(row[0]), 1);
        assertEquals(Integer.parseInt(row[1]), 10);
        assertEquals(Integer.parseInt(row[2]), 20);
    }

    public void testGet_tableStringStringArray() throws Exception {
        String args[] = new String[1];
        args[0] = "table";
        String argsFail[] = new String[1];
        try {
            db.get_table("select name from sqlite_master where type = ", argsFail);
            fail("Testmethod should fail");
        } catch (Exception e) {
        }

        TableResult res = db.get_table(
                "select name from sqlite_master where type = '%q'",
                args);
        String[] s = (String[]) res.rows.elementAt(0);
        assertEquals(s[0], DatabaseCreator.SIMPLE_TABLE1);
    }

    public void testGet_tableStringStringArrayTableResult() throws Exception {
        String args[] = new String[1];
        String argsFail[] = new String[1];
        TableResult res = new TableResult();
        TableResult defaultTableRes = new TableResult();
        args[0] = "table";

        try {
            db.get_table("select name from sqlite_master where type = '%q'", argsFail, res);
            assertEquals(defaultTableRes.toString(), res.toString());
        } catch (Exception e) {
            db.get_table("select name from sqlite_master where type = '%q'", args, res);
            String[] s = (String[]) res.rows.elementAt(0);
            assertEquals(s[0], DatabaseCreator.SIMPLE_TABLE1);
            System.out.println("DatabaseTest.testGet_tableStringStringArrayTableResult() "
                    + Arrays.toString(res.types));
        }
    }

    public void testComplete() {
        assertFalse(db.complete("create"));
        assertTrue(db.complete("create table TEST (res double);"));
    }

    public void testVersion() {
        String version = db.version();
        if (version != null) {
            assertTrue(Integer.parseInt(db.version().substring(0, 1)) > 0);
            assertEquals(db.version(), db.version());
        } else {
            fail("DB version info missing");
        }
    }

    public void testDbversion() throws Exception {
        String verNo = "";
        try {
            verNo = db.dbversion();
            db.close();
            assertEquals(db.dbversion(),"unknown");
            db.open(dbFile.getPath(), 0);
            assertEquals(verNo, db.dbversion());
        } catch (Exception e) {
            db.open(dbFile.getPath(), 0);
        }

        assertTrue(Integer.parseInt(verNo.substring(0, 1))>= 3 );

    }

    public void testCreate_function() throws Exception {
        double input = 1.0;
        db.exec("create table TEST (res double)", null);
        db.exec("insert into TEST values (" + Double.toString(input) + ")",
                null);
        TableResult res = new TableResult();
        Function sinFunc = (Function) new SinFunc();
        db.create_function("sin", 1, sinFunc);
        db.exec("select sin(res) from TEST WHERE res = "
                + Double.toString(input), res);
        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];
        double sinusVal = Double.parseDouble(val);
        double funcVal = Math.sin(input);

        assertTrue(Math.round(funcVal) == Math.round(sinusVal));
    }

    /**
     * Aggregation function not called.
     */
    public void testCreate_aggregate() throws Exception {
        TestTrace t = new TestTrace();
        MockFunction aggFunction = new MockFunction();
        db.exec("create table TEST(id integer, firstname text, lastname text)", null);
        db.exec("insert into TEST values(3, 'James', 'Bond'); ", null);
        db.exec("insert into TEST values(4, 'Fiona', 'Apple'); ", null);
        db.trace((Trace) t);
        db.create_aggregate("myaggfunc", 1, aggFunction);
        db.function_type("myaggfunc", Constants.SQLITE3_TEXT);
        db.exec("PRAGMA show_datatypes = on", null);

        assertFalse(aggFunction.functionCalled);
        assertFalse(aggFunction.stepCalled);
        assertFalse(aggFunction.lastStepCalled);
        db.exec("select myaggfunc(TEST.firstname) from TEST", t);
        assertTrue(aggFunction.stepCalled);
        assertTrue(aggFunction.lastStepCalled);
        assertTrue(aggFunction.functionCalled);

        assertEquals("James Fiona ",aggFunction.getAggValue());
        db.exec("drop table TEST", null);

        try {
            db.create_aggregate("myaggfunc", 0, null);
        } catch (Throwable e) {
            assertEquals("null SQLite.Function not allowed",e.getMessage());
        }

          try {
            db.create_aggregate("myaggfunc", 0, aggFunction);
        } catch (Throwable e) {
            assertEquals("wrong number of arguments to function myaggfunc()",e.getMessage());
        }
    }

    public void testFunction_type() throws Exception {
        double input = 1.0;
        TableResult res = new TableResult();
        Function sinFunc = (Function) new SinFunc();

        db.exec("PRAGMA show_datatypes = on", null);
        db.exec("create table TEST (res double)", null);
        db.exec("insert into TEST values (" + Double.toString(input) + ")",
                null);

        db.create_function("sin", 1, sinFunc);
        db.function_type("sin", Constants.SQLITE_FLOAT);
        res = db.get_table("select sin(res) from TEST WHERE res = "
                + Double.toString(input));

        String row[] = (String[]) res.rows.elementAt(0);
        String val = row[0];
        assertTrue("double".equalsIgnoreCase(res.types[0]));
        assertSame(Math.round(Math.sin(input)), Math.round(Double.parseDouble(val)));

        // function determines return type: test that Double type is returned.
        db.function_type("sin", Constants.SQLITE_BLOB);
        Stmt s = db.prepare("select sin(res) from TEST WHERE res = ?");
        s.bind(1, input);
        s.step();

        res = db.get_table("select sin(res) from TEST WHERE res = "
                + Double.toString(input));
        assertTrue("double".equalsIgnoreCase(res.types[0]));
        row = (String[]) res.rows.elementAt(0);
        val = row[0];
        assertSame(Math.round(Math.sin(input)), Math.round(Double.parseDouble(val)));
    }

    public void testLast_error() {
        assertEquals(db.last_error(), Constants.SQLITE_OK);
        try {
            db.exec("create table TEST (res double)",null);
            db.exec("create table TEST (res double)",null);
            fail("Error should have happened");
        } catch (Exception e) {
            assertEquals(db.last_error(),db.last_error());
            assertEquals(db.last_error(),Constants.SQLITE_ERROR);
        }
    }

    public void testSet_last_error() {
       assertEquals(db.last_error(), Constants.SQLITE_OK);
       try {
           db.exec("sel from test;", null);
       } catch (Exception e) {
           assertEquals(Constants.SQLITE_ERROR,db.last_error());
       }
    }

    public void testError_message() {
        String statement = "create table TEST (res double)";
        try {
            db.exec(statement,null);
            db.exec(statement,null);
            fail("DB Error expected");
        } catch (Exception e) {
            String dbError = db.error_message();
            assertTrue(e.getMessage().equals(dbError));

        }
    }

    public void testError_string() {
        TestTrace t = new TestTrace();
        assertEquals(db.last_error(), Constants.SQLITE_OK);
        String errorString = db.error_string(Constants.SQLITE_ERROR);
        try {
            db.trace((Trace) t);
            db.exec("create table TEST (res double)", t);
            db.exec("create table TEST (res double)", t);
        } catch (Exception e) {
            assertEquals(db.last_error(), Constants.SQLITE_ERROR);
            if (db.is3()) {
                assertEquals("Unsupported Method (sqlite 3): error_string", db
                        .error_string(db.last_error()), errorString);
            }
        }
    }

    /**
     * ASCII encoding does not work: a UTF encoded val is returned. Spec is not
     * sufficient. Might be that test impl is wrong or String constructor for
     * the ASCII encoding.
     */
    public void testSet_encoding() throws UnsupportedEncodingException, Exception {
        String input = "\u00bfMa\u00f1ana\u003f"; // ?Manana?
        TableResult res = new TableResult();
        String refOutput = null;
        Stmt stat = null;

        // DB setup
        db.exec("create table encodingTest (encoded text DEFAULT NULL);",
                null);
        stat = db
                .prepare("insert into encodingTest(encoded) values(:one);");
        stat.bind(1, input);
        stat.step();
        // stat.close();
        db.exec("select * from encodingTest;", res);
        String[] encInput = (String[]) res.rows.elementAt(0);
        String output = encInput[0];
        assertEquals(input, output);
        // db.exec("delete from encodingTest where 1", null);

     // tests for different encoding schemes
        String[] charsetNames = {"UTF-8", "UTF-16", "UTF-16BE", "UTF-16LE"};
        for (int i = 0; i < charsetNames.length; i++) {
            byte[] encInputBytes = input.getBytes(charsetNames[i]);
            db.set_encoding(charsetNames[i]);
            db.exec("select * from encodingTest;", res);
            String[] encOutput = (String[]) res.rows.elementAt(0);
            String inputAsString = new String(encInputBytes,charsetNames[i]);
            assertEquals(inputAsString, encOutput[0]);
        }

        // Default tests
        db.set_encoding("UTF-16");
        db.exec("select * from encodingTest;", res);
        String[] encOutput1 = (String[]) res.rows.elementAt(0);
        assertEquals("Got "+encOutput1[0]+" as UTF-16",input,encOutput1[0]);

        db.set_encoding("US-ASCII");
        db.exec("select * from encodingTest;", res);
        String[] encOutput2 = (String[]) res.rows.elementAt(0);
        assertEquals(new String(input.getBytes(),"US-ASCII"),encOutput2[0]);

        // DB teardown
        stat.close();
        db.exec("delete from encodingTest", null);

        // Default tests
        try {
            db.set_encoding("");
            fail("invalid input should fail");
        } catch (Exception e) {
            //ok
        }
    }

    /**
     * Callback never made for authorization. Results of private table are
     * returned withouth furhter checks.
     *
     * Test fails -> implemented correctly?
     */
    public void testSet_authorizer() throws Exception {
        TableResult resPriv = null;
        TableResult resPub = null;
        TableResult emptyTable = new TableResult();
        String insertPublic = "insert into public_table values(1,2)";
        String insertPrivate = "insert into private_table values(1,2)";
        // prepare, authorizer is not activated yet
        db.exec("create table public_table(c1 integer, c2 integer);", null);
        db.exec("create table private_table(c1 integer, c2 integer);", null);
        // inserts
        db.exec(insertPublic, null);
        db.exec(insertPrivate, null);
        // selects
        resPriv = db.get_table("select * from private_table");
        resPub = db.get_table("select * from public_table");

//        db.exec("delete from public_table where 1", null);
//        TableResult emptyPubTable = db.exec("select * from public");

        // set Authorizer (positive case): denies private table
        AuthorizerCallback cb = new AuthorizerCallback();
        db.set_authorizer(cb);
        //select

        db.exec("select * from private_table", cb);
        assertTrue(cb.wasCalled());

       /*
        TableResult res = db.get_table("select * from private_table");
        assertEquals(emptyTable.toString(),res.toString());
        assertFalse(emptyTable.equals(resPriv));

        res = db.get_table("select * from public_table");
        assertEquals(resPub,res);
        */

        // Try insert
        try {
            db.exec(insertPublic, null);
            fail("authorization failed");
        } catch (Exception e) {
        }

        try {
            db.exec(insertPrivate, null);
            fail("authorization failed");
        } catch (Exception e1) {
            // ok
        }
    }

    public void testTrace() throws Exception {
        String stmt = "create table TEST (res double);";
        TestTrace t = new TestTrace();
        assertFalse(t.traceCalled);
        assertEquals(db.last_error(),Constants.SQLITE_OK);
        db.trace((Trace) t);
        db.exec(stmt,t);
        assertTrue(t.traceCalled);
        assertEquals(t.getTrace(),stmt);

        try {
            db.close();
            db.exec(stmt,t);
            fail("Exception Expected");
        } catch (Exception e) {
            //ok
        }
    }

    public void testCompileString() throws Exception {
        db.compile("select name from sqlite_master;");
        try {
            db.compile("test");
            fail("Compiling of inaccurate statement does not fail.");
        } catch (Exception e) {
        }
    }

    public void testCompileStringStringArray() throws Exception {
        String args[] = new String[1];
        args[0] = "table";
        db.compile("select name from sqlite_master where type = '%q';",args);

        try {
            db.compile("test",null);
            fail("Compiling of inaccurate statement does not fail.");
        } catch (Exception e) {
        }
    }

    public void testPrepare() throws Exception {
        Stmt st = null;
        Stmt st2 = null;
        // test empty statement
        try {
            st = db.prepare("");
            assertEquals(0, st.bind_parameter_count());
            st.step();
            fail("stmt should not be prepared");
        } catch (Exception e) {
            assertEquals("stmt already closed", e.getMessage());
        }

        // test statement with unbound arguments
        try {
            st2 = db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values (:one,:two,:three)");
            assertEquals(3, st2.bind_parameter_count());
            assertEquals(3, st2.bind_parameter_index(":three"));
            assertEquals(":two", st2.bind_parameter_name(2));
        } finally {
            st2.close();
        }

        try {
            db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values(:one,:two,:three,:four);");
        } catch (Exception e) {
            assertEquals("table " + DatabaseCreator.SIMPLE_TABLE1
                    + " has 3 columns but 4 values were supplied", e
                    .getMessage());
        }

        try {
            db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values(5, '10, 20);");
        } catch (Exception e) {
            assertEquals("unrecognized token: \"'10, 20);\"", e.getMessage());
        }

        try {
            db.prepare("insert into " + DatabaseCreator.SIMPLE_TABLE1
                    + " values(5, 10 20);");
        } catch (Exception e) {
            assertEquals("near \"20\": syntax error", e.getMessage());
        }

    }

    /**
     * Not supported.
     */
    public void testOpen_blob() throws Exception, java.lang.Exception {
        Stmt statement2;
        Blob blobInput = new Blob();

        // Create test input Blob
        InputStream inStream = null;
        byte[] in = {(byte) 1, (byte) 2, (byte) 3, (byte) 4};

        // setup test input
        db.exec("create table TEST (res blob)",null);
        inStream = Class.forName(this.getClass().getName()).getResourceAsStream("/blob.c");
        assertNotNull(inStream);

        // insert byte array in db
        statement2 = db.prepare("insert into TEST(res) values (?)");
        statement2.bind(1, in);
        statement2.step();
        statement2.close();

        // read from db
        Blob blob = db.open_blob(dbFile.getPath(), "TEST", "res", 1, true);
        if (blob == null) {
            fail("Blob could not be retrieved");
        }
        //read from blob and compare values (positive case)
        InputStream is = blob.getInputStream();

        int i = 0;
        int outByte = 0;
        byte[] out = new byte[4];
        while ((outByte = is.read()) > -1) {
            out[i] = (byte) outByte;
            i++;
        }
        is.close();

        blob.close();

        assertTrue(Arrays.equals(in, out));

        //read from blob and compare values (default blob)
        db.exec("insert into TEST values(zeroblob(128))", null);
        Blob blob2 = db.open_blob(dbFile.getPath(), "TEST", "res", 2, true);
        is = blob2.getInputStream();
        for (i = 0; i < 128; i++)  {
           assertEquals(0, is.read());
        }
        is.close();
    }

    public void testIs3() {
        int ver = Integer.parseInt(db.version().substring(0,1));
        if (db.is3()) {
            assertTrue( ver == 3);
        } else {
            assertTrue(ver != 3);
        }
    }

    public void testProgress_handler() throws Exception {
        int inputVal = 3;
        TestProgressHandler prog = new TestProgressHandler();
        db.exec("create table TEST5(id integer, firstname text, lastname text)",null);
        Vm vm = db.compile("select * from TEST5; "
                + "insert into TEST5 values(3, 'James', 'Bond'); "
                + "delete from TEST5 where id = 3; "
                + "select * from TEST5");
        int stmt = 0;
        do {
            ++stmt;
            if (stmt > inputVal) {
                db.progress_handler(inputVal, prog);
            } else {
                assertEquals(0, prog.getCounts());
            }
            while (vm.step(prog)) {
            }
        } while (vm.compile());
        assertEquals(inputVal,prog.getCounts());

        // Boundary value test
        inputVal = 0;
        TestProgressHandler progBoundary = new TestProgressHandler();
        db.progress_handler(inputVal, progBoundary);
        Vm vm2 = db.compile("select * from TEST5; "
                + "insert into TEST5 values(3, 'James', 'Bond'); "
                + "delete from TEST5 where id = 3; "
                + "select * from TEST5");
        do {
            vm2.step(progBoundary);
        } while (vm2.compile());
        assertEquals(inputVal, progBoundary.getCounts());

        try {
            db.exec("drop table TEST5",null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    class SinFunc implements Function {
        public void function(FunctionContext fc, String args[]) {
            Double d = new Double(args[0]);
            fc.set_result(Math.sin(d.doubleValue()));
        }
        public void last_step(FunctionContext fc) {}
        public void step(FunctionContext fc, String[] args) {}
    }

    class TestTrace implements Trace,Callback {

        private StringBuffer buf = new StringBuffer();

        public boolean traceCalled = false;

        public String getTrace() {
            return buf.toString();
        }

        public void trace(String stmt) {
            traceCalled = true;
            buf.append(stmt);
        }

        public void columns(String[] coldata) {}

        public boolean newrow(String[] rowdata) {
            return false;
        }

        public void types(String[] types) {}
    }

    class AuthorizerCallback implements Authorizer, Callback {

        private boolean isAuthorizing = false;

        public boolean wasCalled() {
            return isAuthorizing;
        }

        public int authorize(int action, String arg1, String arg2, String arg3,
                String arg4) {
            Logger.global.info("DB authorization callback " + action + " " + arg1 + " " + arg2 + " "
                    + arg3 + " " + arg4 + " ");
            this.isAuthorizing = true;
            if (action != Constants.SQLITE_SELECT || arg1.contains("private_table")) {
                return Constants.SQLITE_DENY;
            } else {
                return Constants.SQLITE_OK;
            }
        }

        public void columns(String[] coldata) {}

        public boolean newrow(String[] rowdata) {
            return false;
        }

        public void types(String[] types) {}

    }

    class TestBusyHandler implements BusyHandler, Callback {

        public boolean busy(String table, int count) {
            return true;
        }

        public void columns(String[] coldata) {}

        public boolean newrow(String[] rowdata) {
            return false;
        }

        public void types(String[] types) {}
    }

    class TestProgressHandler implements ProgressHandler, Callback {

        private boolean progressed = false;

        private int counter = 0;

        public int getCounts() {
            return counter;
        }

        public boolean progress() {
            this.progressed = true;
            counter++;
            return true;
        }

        public void columns(String[] coldata) {}

        public boolean newrow(String[] rowdata) {
            return false;
        }

        public void types(String[] types) {}
    }

    /**
     * This method creates a Runnable that executes insert operation for the first table
     */
    private static Runnable createTask2Interrupt(final int id,
            final String dbName, final ErrorTracker errorTracker) {
        return new Runnable() {
            public void run() {
                Database db = new Database();
                try {
                    String value = DatabaseCreator.defaultString + id;

                    db.open(dbName, 0);
                    String insertQuery = "INSERT INTO "
                            + DatabaseCreator.TEST_TABLE1
                            + " (id, field1, field2, field3) VALUES(" + id
                            + ", '" + value + "', " + id + ", " + id + ")";
                    db.exec(insertQuery, null);
                } catch (Exception e) {
                    errorTracker.registerException(this, e);
                    try {
                        db.interrupt();
                        db.exec("DELETE FROM " + DatabaseCreator.SIMPLE_TABLE1
                                + " WHERE id=" + id, null);
                    } catch (Exception e1) {
                        errorTracker.registerException(this, e1);
                    }
                }
            }
        };
    }

    /**
     * This method creates a Runnable that executes delete operation for the first table
     */
    private static Runnable createTask1(final int id, final String dbName,
            final ErrorTracker errorTracker) {
        return new Runnable() {
            public void run() {
                try {
                    Database db = new Database();
                    db.open(dbName, 0);
                    db.exec("DELETE FROM "
                            + DatabaseCreator.SIMPLE_TABLE1 + " WHERE id=" + id, null);
                } catch (Exception e) {
                    errorTracker.registerException(this, e);
                }
            }
        };
    }

    /**
     * This method creates a Runnable that executes insert operation for the first table
     */
    private static Runnable createTask2(final int id, final String dbName,
            final ErrorTracker errorTracker) {
        return new Runnable() {
            public void run() {
                try {
                    String value = DatabaseCreator.defaultString + id;
                    Database db = new Database();
                    db.open(dbName, 0);
                    String insertQuery = "INSERT INTO "
                            + DatabaseCreator.TEST_TABLE1
                            + " (id, field1, field2, field3) VALUES(" + id
                            + ", '" + value + "', " + id + ", " + id + ")";
                    db.exec(insertQuery, null);
                } catch (Exception e) {
                    errorTracker.registerException(this, e);

                }
            }
        };
    }

    /**
     * This method creates a Runnable that executes update operation for the one record of the first
     * table
     */
    private static Runnable createTask3(final int oldID, final String dbName,
            final int newID, final ErrorTracker errorTracker) {
        return new Runnable() {
            public void run() {
                Database db = new Database();
                try {
                    db.open(dbName, 0);
                    String value = DatabaseCreator.defaultString + newID;
                    String updateQuery = "UPDATE "
                            + DatabaseCreator.TEST_TABLE1 + " SET id=" + newID
                            + ", field1='" + value + "', field2=" + newID
                            + ", field3=" + newID + " WHERE id=" + oldID;
                    db.exec(updateQuery, null);
                } catch (Exception e) {
                    errorTracker.registerException(this, e);
                }
            }
        };
    }

    private class ErrorTracker {

        private List<String> errors = new ArrayList<String>();

        public void registerException(Runnable runnable, Exception e) {
            System.out.println("Registered: " + e.getMessage());
            errors.add(e.getMessage());
        }

        public List<String> getErrors() {
            return errors;
        }

        public void reset() {
            errors.clear();
        }
    }
}
