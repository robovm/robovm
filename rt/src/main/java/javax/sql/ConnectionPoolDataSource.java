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

import java.sql.SQLException;

/**
 * An interface for the creation of {@code ConnectionPoolDataSource} objects.
 * Used internally within the package.
 * <p>
 * A class which implements the {@code ConnectionPoolDataSource} interface is
 * typically registered with a JNDI naming service directory and is retrieved
 * from there by name.
 */
public interface ConnectionPoolDataSource extends CommonDataSource {

    /**
     * Creates a connection to a database which can then be used as a pooled
     * connection.
     *
     * @return a {@code PooledConnection} which represents the connection to the
     *         database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public PooledConnection getPooledConnection() throws SQLException;

    /**
     * Creates a connection to a database, using the supplied user name and
     * password, which can then be used as a pooled connection.
     *
     * @param theUser
     *            the a user name for the database login.
     * @param thePassword
     *            the password associated with the user identified by {@code
     *            theUser}.
     * @return a {@code PooledConnection} object which represents the connection
     *         to the database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public PooledConnection getPooledConnection(String theUser,
            String thePassword) throws SQLException;
}
