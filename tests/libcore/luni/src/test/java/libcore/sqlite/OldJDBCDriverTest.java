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

import SQLite.JDBCDriver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;


public final class OldJDBCDriverTest extends OldJDBCDriverFunctionalTest {

    /**
     * The SQLite db file.
     */
    private JDBCDriver jDriver;

    private Driver returnedDriver;

    @Override public void setUp() throws java.lang.Exception {
        super.setUp();
        returnedDriver = DriverManager.getDriver(getConnectionURL());
        if (returnedDriver instanceof JDBCDriver) {
            this.jDriver = (JDBCDriver) returnedDriver;
        }
    }

    public void testJDBCDriver() {
        assertTrue(returnedDriver instanceof JDBCDriver);
    }

    public void testAcceptsURL() {
        try {
            if (this.jDriver != null) {
                assertTrue(jDriver.acceptsURL(getConnectionURL()));
            } else {
                fail("no Driver available");
            }
        } catch (SQLException e) {
            fail("Driver does not accept URL");
            e.printStackTrace();
        }
    }

    public void testConnect() {
        try {
            if (this.jDriver != null) {
                Connection c = jDriver.connect(getConnectionURL(), null);
                assertFalse(c.isClosed());
                DriverManager.getConnection(getConnectionURL());
            } else {
                fail("no Driver available");
            }
        } catch (SQLException e) {
            fail("Driver does not connect");
            e.printStackTrace();
        }
    }

    public void testGetMajorVersion() {
        if (this.jDriver != null) {
            assertTrue(jDriver.getMajorVersion() > 0);
        } else {
            fail("no Driver available");
        }
    }

   public void testGetMinorVersion() {
        if (this.jDriver != null) {
            assertTrue(jDriver.getMinorVersion() > 0);
        } else {
            fail("no version information available");
        }
    }

   public void testGetPropertyInfo() throws SQLException {
        DriverPropertyInfo[] info = null;
       if (this.jDriver != null) {
           info = jDriver.getPropertyInfo(getConnectionURL(), null);
           assertNotNull(info);
           assertTrue(info.length > 0);
       } else {
           fail("no Driver available");
       }

        assertNotNull(info);

    }

    public void testJdbcCompliant() {
        if (this.jDriver != null) {
            assertFalse(jDriver.jdbcCompliant());
        } else {
            fail("no version information available");
        }
    }
}
