/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.util.Properties;
import junit.framework.TestCase;

public final class OldDriverPropertyInfoTest extends TestCase {

    static final String validName = "testname";
    static final String validValue = "testvalue";
    static final String connectionURL = "jdbc:sqlite:/" + "Test.db";
    static final String classname = "SQLite.JDBCDriver";

    public void testDriverPropertyInfoStringString() {
        DriverPropertyInfo aDriverPropertyInfo = new DriverPropertyInfo(
                validName, validValue);

        assertNotNull(aDriverPropertyInfo);

        assertEquals(aDriverPropertyInfo.name,validName);
        assertEquals(aDriverPropertyInfo.value,validValue);

        aDriverPropertyInfo = new DriverPropertyInfo(null, null);

        assertNotNull(aDriverPropertyInfo);
        assertNull(aDriverPropertyInfo.name);
        assertNull(aDriverPropertyInfo.value);
    }

    public void testPublicFields() throws Exception {
        Class.forName(classname).newInstance();
        Properties props = new Properties();
        Driver d = DriverManager.getDriver(connectionURL);
        DriverPropertyInfo[] info = d.getPropertyInfo(connectionURL,
                props);
        // get the property metadata
        String name = info[0].name;
        assertNotNull(name);
        assertEquals(name, "encoding");
        String[] choices = info[0].choices;
        assertNull(choices);
        boolean required = info[0].required;
        assertFalse(required);
        String description = info[0].description;
        assertNull(description);
    }
}
