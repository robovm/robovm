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


package libcore.sqlite;

import SQLite.Database;
import SQLite.Function;
import SQLite.FunctionContext;
import SQLite.JDBC2z.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.TestCase;
import tests.support.Support_SQL;


/**
 * Test that statements honor their timeout.
 */
public final class QueryTimeoutTest extends TestCase {

    private static final String EXEC_QUERY
            = "insert into t_copy select a from t_orig where DELAY(2,1)=1";

    private static final String FETCH_QUERY = "select a from t_orig where DELAY(2,1)=1";

    private Connection connection;

    @Override public void setUp() throws Exception {
        Support_SQL.loadDriver();
        connection = Support_SQL.getConnection();

        exec("drop table if exists t_orig;");
        exec("drop table if exists t_copy;");
        exec("create table t_orig (a int)");
        exec("create table t_copy (a int)");

        for (int i = 0; i < 7; i++) {
            exec("insert into t_orig values (" + i + ");");
        }

        Database database = ((JDBCConnection) connection).getSQLiteDatabase();
        database.create_function("DELAY", 2, new Function() {
            @Override public void function(FunctionContext functionContext, String[] args) {
                try {
                    int seconds = Integer.parseInt(args[0]);
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException ignored) {
                }
                functionContext.set_result(Integer.parseInt(args[1]));
            }
            @Override public void last_step(FunctionContext functionContext) {
            }
            @Override public void step(FunctionContext functionContext, String[] args) {
            }
        });

        connection.setAutoCommit(true);
    }

    @Override public void tearDown() throws Exception {
        connection.close();
    }

    private void exec(String queryString) throws Exception {
        System.out.println("Executing " + queryString);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(queryString);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void testPreparedStatementFetch() throws Exception {
        PreparedStatement statement = connection.prepareStatement(FETCH_QUERY);
        statement.setQueryTimeout(1);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
            }
            fail();
        } catch (SQLException expected) {
        } finally {
            statement.close();
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void testPreparedStatementUpdate() throws Exception {
        PreparedStatement statement = connection.prepareStatement(EXEC_QUERY);
        try {
            statement.setQueryTimeout(1);
            statement.execute();
            fail();
        } catch (SQLException expected) {
        } finally {
            statement.close();
        }
    }

    public void testInvalidTimeout() throws Exception {
        connection.setAutoCommit(true);
        PreparedStatement statement = connection.prepareStatement("select 'hello'");

        try {
            statement.setQueryTimeout(-1);
            fail();
        } catch (SQLException expected) {
        }

        ResultSet resultSet = statement.executeQuery();
        resultSet.close();
        statement.close();
    }

    public void testExecuteUpdate() throws Exception {
        Statement statement = connection.createStatement();
        try {
            statement.setQueryTimeout(1);
            statement.executeUpdate(EXEC_QUERY);
            fail();
        } catch (SQLException expected) {
        } finally {
            statement.close();
        }
    }

    public void testTimeoutAndStatementReuse() throws Exception {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(1);
        for (int i = 0; i < 3; i++) {
            try {
                ResultSet resultSet = statement.executeQuery(FETCH_QUERY);
                while (resultSet.next()) {
                }
                fail();
            } catch (SQLException expected) {
            }
        }
        statement.close();
    }
}
