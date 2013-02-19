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

package javax.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;

/**
 * An interface for the creation of {@code Connection} objects which represent a
 * connection to a database. This interface is an alternative to the {@code
 * java.sql.DriverManager}.
 * <p>
 * A class which implements the {@code DataSource} interface is typically
 * registered with a JNDI naming service directory and is retrieved from there
 * by name.
 * <p>
 * The {@code DataSource} interface is typically implemented by the writer of a
 * JDBC driver. There are three variants of the {@code DataSource} interface,
 * which produce connections with different characteristics:
 * <ol>
 * <li><i>Standard {@code DataSource}</i>: produces standard {@code Connection}
 * objects with no special features.</li>
 * <li><i>Connection Pool {@code DataSource}</i>: produces {@code
 * PooledConnection} objects which require a connection pool manager as an
 * intermediary component.</li>
 * <li><i>Distributed transaction {@code DataSource} ("XADataSource")</i>:
 * produces {@code XAConnection} objects which can be used to handle distributed
 * transactions which typically require an intermediary transaction manager
 * component. {@code XAConnection} objects also provide connection pooling
 * capabilities as well as distributed transaction capabilities.</li>
 * </ol>
 * <p>
 * Note that a JDBC driver which is accessed via the {@code DataSource}
 * interface is loaded via a JNDI lookup process. A driver loaded in this way
 * does not register itself with the {@code DriverManager}.
 */
public interface DataSource extends CommonDataSource, Wrapper {

    /**
     * Creates a connection to the database represented by this {@code
     * DataSource}.
     *
     * @return a {@code Connection} object which is a connection to the
     *         database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection() throws SQLException;

    /**
     * Creates a connection to the database represented by this {@code
     * DataSource}, using the supplied user name and password.
     *
     * @param theUsername
     *            the a user name for the database login.
     * @param thePassword
     *            the password associated with the user identified by {@code
     *            theUsername}.
     * @return the {@code Connection} object which is the connection to the
     *         database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection(String theUsername, String thePassword)
            throws SQLException;
}
