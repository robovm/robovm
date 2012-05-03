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

import java.io.Serializable;
import java.sql.SQLException;
import java.util.EventObject;

/**
 * Sent when specific events happen on a {@link PooledConnection} object. These
 * events are a facility to report when an application closes the pooled
 * connection or when an error occurs in the pooled connection.
 */
public class ConnectionEvent extends EventObject implements Serializable {

    private static final long serialVersionUID = -4843217645290030002L;

    private SQLException ex;

    /**
     * Creates a connection event initialized with the supplied {@code
     * PooledConnection} reporting that the application has closed the
     * connection.
     *
     * @param theConnection
     *            the connection for which this event is created.
     */
    public ConnectionEvent(PooledConnection theConnection) {
        super(theConnection);
    }

    /**
     * Creates a {@code ConnectionEvent} initialized with the supplied {@code
     * PooledConnection} and with the supplied {@code SQLException} indicating
     * that an error has occurred within the {@code PooledConnection}.
     *
     * @param theConnection
     *            the connection for which this event is created.
     * @param theException
     *            information about the state of error that has occurred on the
     *            application side.
     */
    public ConnectionEvent(PooledConnection theConnection,
            SQLException theException) {
        super(theConnection);
        ex = theException;
    }

    /**
     * Gets the {@code SQLException} which holds information about the error
     * which occurred in the {@code PooledConnection}.
     *
     * @return a {@code SQLException} containing information about the error.
     *         May be {@code null} if no error has occurred.
     */
    public SQLException getSQLException() {
        return ex;
    }
}
