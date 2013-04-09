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

package tests.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Support_SQL {

    public static String sqlDriver = null;

    public static String sqlLogin = null;

    public static String sqlCatalog = null;

    public static String sqlHost = null;

    public static String sqlUrl = null;

    public static String sqlPassword = null;

    public static String sqlUser = null;

    public static int sqlMaxConnections = 5;

    public static int sqlMaxTasks = 1;

    private static File dbFile = null;

    public static void loadDriver() {
        try {
            InputStream in = Class.forName("tests.support.Support_SQL")
                    .getResourceAsStream("/connection.properties");
            loadProperties(in);
            in.close();

            String tmp = System.getProperty("java.io.tmpdir");
            File tmpDir = new File(tmp);
            if (tmpDir.isDirectory()) {
                dbFile = File.createTempFile("sqliteTest", ".db", tmpDir);
                dbFile.deleteOnExit();
            } else {
                System.err.println("java.io.tmpdir does not exist");
            }
            Class.forName("SQLite.JDBCDriver").newInstance();

            // overwrite sqlUrl to point to valid directory
            sqlUrl = "jdbc:sqlite:/" + dbFile.getPath();

            Class.forName(sqlDriver).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(Support_SQL.sqlUrl,
                    Support_SQL.sqlLogin, Support_SQL.sqlPassword);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect. url=" + Support_SQL.sqlUrl + ", sqlLogin="
                    + Support_SQL.sqlLogin + ", sqlPassword=" + Support_SQL.sqlPassword, e);
        }
    }

    public static Connection getConnection(String url, String login,
            String password) throws SQLException {

        return DriverManager.getConnection(url, login, password);
    }

    public static boolean isEqual(byte[] b1, int off1, byte[] b2, int off2,
            int len) {
        for (int i = 0; i < len; ++i)
            if (b1[i + off1] != b2[i + off2])
                return false;
        return true;
    }

    private static void loadProperties(InputStream fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(fileName);
        sqlDriver = properties.getProperty("sqlDriver");
        sqlLogin = properties.getProperty("sqlLogin");
        sqlCatalog = properties.getProperty("sqlCatalog");
        sqlHost = properties.getProperty("sqlHost");
        sqlUrl = properties.getProperty("sqlUrlPrefix") + sqlHost + "/"
                + sqlCatalog;
        sqlPassword = properties.getProperty("sqlPassword");
        sqlUser = properties.getProperty("sqlUser");
        sqlMaxConnections = Integer.parseInt(properties
                .getProperty("sqlMaxConnections"));
        sqlMaxTasks = Integer.parseInt(properties.getProperty("sqlMaxTasks"));
    }

    public static String getFilename() {
        return dbFile.getPath();
    }
}
