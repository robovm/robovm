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
 * An object that registers to be notified of events that occur on
 * PreparedStatements that are in the Statement pool.
 */
public interface StatementEventListener extends EventListener {
    /**
     * The driver calls this method on all StatementEventListeners registered on
     * the connection when it detects that a PreparedStatement is closed.
     *
     * @param event
     *            an StatementEvent object describing the event of statement
     *            closed
     */
    public void statementClosed(StatementEvent event);

    /**
     * The driver calls this method on all StatementEventListeners registered on
     * the connection when it detects that a PreparedStatement is invalid,
     * before a SQLException is thrown
     *
     * @param event
     *            an StatementEvent object describing the event of statement
     *            error occurred
     */
    public void statementErrorOccurred(StatementEvent event);
}
