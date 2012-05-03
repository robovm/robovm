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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EventObject;

/**
 * A statement event that a PreparedStatement is closed
 *
 * @since 1.6
 */
public class StatementEvent extends EventObject {

    private static final long serialVersionUID = -8089573731826608315L;

    private SQLException exception;

    private PreparedStatement statement;

    /**
     * the constructor
     *
     * @param con
     *            the statment related connection
     * @param statement
     *            the statement to be closed
     * @param exception
     *            the exception to throw
     */
    public StatementEvent(PooledConnection con, PreparedStatement statement,
            SQLException exception) {
        super(con);
        this.statement = statement;
        this.exception = exception;
    }

    /**
     * the constructor with null exception
     *
     * @param con
     *            the statment related connection
     * @param statement
     *            the statement to be closed
     */
    public StatementEvent(PooledConnection con, PreparedStatement statement) {
        this(con, statement, null);
    }

    /**
     * Returns the statement of this event
     */
    public PreparedStatement getStatement() {
        return this.statement;
    }

    /**
     * Returns the exception to be thrown
     *
     * @return the exception of this event
     */
    public SQLException getSQLException() {
        return this.exception;
    }
}