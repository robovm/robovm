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

import java.util.EventListener;

/**
 * An interface used to send notification of events occurring in the context of
 * a {@link RowSet}. To receive the notification events, an object must
 * implement the {@code RowSetListener} interface and then register itself with
 * the {@code RowSet} of interest using the
 * {@link RowSet#addRowSetListener(RowSetListener)} method.
 */
public interface RowSetListener extends EventListener {

    /**
     * Notifies the listener that the {@code RowSet}'s cursor in {@code
     * theEvent.getSource} has moved.
     *
     * @param theEvent
     *            a {@code RowSetEvent} that contains information about the
     *            {@code RowSet} involved. This information can be used to
     *            retrieve information about the change, such as the updated
     *            data values.
     */
    public void cursorMoved(RowSetEvent theEvent);

    /**
     * Notifies the listener that one of the {@code RowSet}'s rows in {@code
     * theEvent.getSource} has changed.
     *
     * @param theEvent
     *            a {@code RowSetEvent} that contains information about the
     *            {@code RowSet} involved. This information can be used to
     *            retrieve information about the change, such as the new cursor
     *            position.
     */
    public void rowChanged(RowSetEvent theEvent);

    /**
     * Notifies the listener that the {@code RowSet}'s entire contents in
     * {@code theEvent.getSource} have been updated (an example is the execution
     * of a command which retrieves new data from the database).
     *
     * @param theEvent
     *            a {@code RowSetEvent} that contains information about the
     *            {@code RowSet} involved. This information can be used to
     *            retrieve information about the change, such as the updated
     *            rows of data.
     */
    public void rowSetChanged(RowSetEvent theEvent);
}
