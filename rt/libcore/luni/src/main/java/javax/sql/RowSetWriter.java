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
 * An interface which provides functionality for a disconnected {@code RowSet}
 * to put data updates back to the data source from which the {@code RowSet} was
 * originally populated. An object implementing this interface is called a
 * writer.
 * <p>
 * The writer must establish a connection to the {@code RowSet}'s database
 * before writing the data. The {@code RowSet} calling this interface must
 * implement the {@code RowSetInternal} interface.
 * <p>
 * The writer may encounter a situation where the updated data needs to be
 * written back to the database, but has already been updated there in the mean
 * time. How a conflict of this kind is handled is determined by the
 * implementation of this writer.
 *
 * @see RowSetInternal
 */
public interface RowSetWriter {

    /**
     * Writes changes made in the {@code RowSet}, which is associated with this
     * {@code RowSetWriter}, back to the database.
     *
     * @param theRowSet
     *            a row set that fulfills the following criteria:
     *            <ul>
     *            <li>it must implement the {@code RowSetInternal} interface,</li>
     *            <li>have this {@code RowSetWriter} registered with it,</li>
     *            <li>must call this method internally.</li>
     *            </ul>
     * @return {@code true} if the modified data was written, {@code false}
     *         otherwise (which typically implies some form of conflict).
     * @throws SQLException
     *             if a problem occurs accessing the database.
     */
    public boolean writeData(RowSetInternal theRowSet) throws SQLException;
}
