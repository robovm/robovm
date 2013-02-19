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
 * to get data from a database into its rows. The {@code RowSet} calls the
 * {@code RowSetReader} interface when the {@code RowSet}'s execute method is
 * invoked - a {@code RowSetReader} must first be registered with the {@code
 * RowSet} for this to work.
 *
 * @see RowSet
 */
public interface RowSetReader {

    /**
     * Reads new data into the {@code RowSet}. The calling {@code RowSet} object
     * must itself implement the {@code RowSetInternal} interface and the
     * {@code RowSetReader} must be registered as a reader on the
     * {@code RowSet}.
     * <p>
     * This method adds rows into the calling {@code RowSet}. The reader may
     * invoke any of the {@code RowSet}'s methods except for the {@code execute}
     * method (calling {@code execute} will cause an {@code SQLException} to be
     * thrown). However, when the reader calls the {@code RowSet}'s methods, no
     * events are sent to listeners - any listeners are informed by the calling
     * {@code RowSet}'s {@code execute} method once the reader returns from the
     * {@code readData} method.
     *
     * @param theCaller
     *            must be the calling {@code RowSet} object, which must have
     *            implemented the {@code RowSetInternal} interface.
     * @throws SQLException
     *             if a problem occurs accessing the database or if the reader
     *             calls the {@link RowSet#execute()} method.
     * @see RowSetInternal
     */
    public void readData(RowSetInternal theCaller) throws SQLException;
}
