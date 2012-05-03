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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface provided by a {@code RowSet} object to let either a {@code
 * RowSetReader} or a {@code RowSetWriter} access its internal state, thereby
 * providing facilities to read and update the state of the {@code RowSet}.
 */
public interface RowSetInternal {

    /**
     * Gets the connection associated with this {@code RowSet} object.
     *
     * @return the connection or {@code null}.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection() throws SQLException;

    /**
     * Gets the {@code ResultSet} that was the original (unmodified) content of
     * the {@code RowSet}.
     * <p>
     * The {@code ResultSet}'s cursor is positioned before the first row of
     * data.
     *
     * @return the {@code ResultSet} that contained the original data value of
     *         the {@code RowSet}.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public ResultSet getOriginal() throws SQLException;

    /**
     * Gets the original value of the current row only. If the current row did
     * not have an original value, then an empty value is returned.
     *
     * @return a {@code ResultSet} containing the value of the current row only.
     * @throws SQLException
     *             if there is a problem accessing the database, or if the
     *             cursor is not on a valid row (before the first row, after the
     *             last one or pointing to the insert row).
     */
    public ResultSet getOriginalRow() throws SQLException;

    /**
     * Gets the parameter values that have been set for this {@code RowSet}'s
     * command.
     *
     * @return the values of parameters that have been set.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Object[] getParams() throws SQLException;

    /**
     * Sets {@code RowSetMetaData} for this {@code RowSet}. The {@code
     * RowSetMetaData} is used by a {@code RowSetReader} to set values giving
     * information about the {@code RowSet}'s columns.
     *
     * @param theMetaData
     *            holds the metadata about the {@code RowSet}'s columns.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public void setMetaData(RowSetMetaData theMetaData) throws SQLException;
}
