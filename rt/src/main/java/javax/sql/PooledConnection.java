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

/**
 * An interface which provides facilities for handling connections to a database
 * which are pooled.
 * <p>
 * Typically, a {@code PooledConnection} is recycled when it is no longer
 * required by an application, rather than being closed and discarded. The
 * reason for treating connections in this way is that it can be an expensive
 * process both to establish a connection to a database and to destroy the
 * connection. Reusing connections through a pool is a way of improving system
 * performance and reducing overhead.
 * <p>
 * It is not intended that an application uses the {@code PooledConnection}
 * interface directly. The {@code PooledConnection} interface is intended for
 * use by a component called a connection pool manager, typically part of the
 * infrastructure that supports use of the database by applications.
 * <p>
 * Applications obtain connections to the database by calling the
 * {@link DataSource#getConnection} method. Behind the scenes, the connection
 * pool manager will get a {@code PooledConnection} object from its connection
 * pool and passes back a connection object that wraps or references the {@code
 * PooledConnection} object. A new {@code PooledConnection} object will only be
 * created if the pool is empty.
 * <p>
 * When the application is finished using a {@code PooledConnection}, the
 * application calls the {@link Connection#close} method. The connection pool
 * manager is notified via a {@link ConnectionEvent} from the connection that
 * this has happened (the pool manager registers itself with the connection
 * before the connection is given to the application). The pool manager removes
 * the underlying {@code PooledConnection} object from the connection and
 * returns it to the pool for reuse - the {@code PooledConnection} is thus
 * recycled rather than being destroyed.
 * <p>
 * The connection to the database represented by the {@code PooledConnection} is
 * kept open until the {@code PooledConnection} object itself is deactivated by
 * the connection pool manager, which calls {@code PooledConnection.close()}.
 * This is typically done if there are too many inactive connections in the
 * pool, if the {@code PooledConnection} encounters a problem that makes it
 * unusable or if the whole system is being shut down.
 */
public interface PooledConnection {

    /**
     * Registers the supplied {@code ConnectionEventListener} with this {@code
     * PooledConnection}. Once registered, the {@code ConnectionEventListener}
     * will receive {@link ConnectionEvent} events when they occur in the
     * {@code PooledConnection}.
     *
     * @param theListener
     *            an object which implements the {@code ConnectionEventListener}
     *            interface.
     */
    public void addConnectionEventListener(ConnectionEventListener theListener);

    /**
     * Closes the connection to the database held by this {@code
     * PooledConnection}. This method should not be called directly by
     * application code - it is intended only for the connection pool manager
     * component.
     *
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public void close() throws SQLException;

    /**
     * Creates a connection to the database. This method is typically called by
     * the connection pool manager when an application invokes the method
     * {@code DataSource.getConnection()} and there are no {@code
     * PooledConnection} objects available in the connection pool.
     *
     * @return a {@code Connection} object.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection() throws SQLException;

    /**
     * Unregisters the supplied {@code ConnectionEventListener} from this {@code
     * PooledConnection}. Once unregistered, the {@code ConnectionEventListener}
     * will no longer receive events occurring in the {@code PooledConnection}.
     *
     * @param theListener
     *            an object which implements the {@code ConnectionEventListener}
     *            interface. This object should have previously been registered
     *            with the {@code PooledConnection} using the {@code
     *            addConnectionEventListener} method.
     */
    public void removeConnectionEventListener(
            ConnectionEventListener theListener);

    /**
     * Add a StatementEventListener to this PooledConnection object.
     *
     * @param listener
     *            A StatementEventListener object which is to be added with this
     *            PooledConnection object
     * @since 1.6
     */
    public void addStatementEventListener(StatementEventListener listener);

    /**
     * Remove a StatementEventListener from this PooledConnection object.
     *
     * @param listener
     *            A StatementEventListener object which is to be removed form
     *            this PooledConnection object
     * @since 1.6
     */
    public void removeStatementEventListener(StatementEventListener listener);
}
