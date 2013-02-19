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
import java.util.EventObject;

/**
 * An event which is sent when specific events happen to a {@link RowSet}
 * object. The events are sent to inform registered listeners that changes have
 * occurred to the {@code RowSet}. The events covered are:
 * <ol>
 * <li>A single row in the {@code RowSet} changes.</li>
 * <li>The whole set of data in the {@code RowSet} changes.</li>
 * <li>The {@code RowSet} cursor position changes.</li>
 * </ol>
 * <p>
 * The event contains a reference to the {@code RowSet} object which generated
 * the message so that the listeners can extract whatever information they need
 * from that reference.
 */
public class RowSetEvent extends EventObject implements Serializable {

    private static final long serialVersionUID = -1875450876546332005L;

    /**
     * Creates a {@code RowSetEvent} object containing a reference to the
     * {@link RowSet} object that generated the event. Information about the
     * changes that have occurred to the {@code RowSet} can be extracted from
     * the {@code RowSet} using one or more of the query methods available on
     * the {@code RowSet}.
     *
     * @param theSource
     *            the {@code RowSet} which generated the event.
     */
    public RowSetEvent(RowSet theSource) {
        super(theSource);
    }
}
