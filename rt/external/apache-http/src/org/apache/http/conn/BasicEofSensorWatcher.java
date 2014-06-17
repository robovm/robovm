/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/BasicEofSensorWatcher.java $
 * $Revision $
 * $Date: 2008-06-27 12:49:20 -0700 (Fri, 27 Jun 2008) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.conn;

import java.io.InputStream;
import java.io.IOException;


/**
 * Basic implementation of {@link EofSensorWatcher EofSensorWatcher}.
 * The underlying connection is released on close or EOF.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version $Revision: 672367 $
 *
 * @since 4.0
 */
public class BasicEofSensorWatcher implements EofSensorWatcher {


    /** The connection to auto-release. */
    protected ManagedClientConnection managedConn;

    /** Whether to keep the connection alive. */
    protected boolean attemptReuse;



    /**
     * Creates a new watcher for auto-releasing a connection.
     *
     * @param conn      the connection to auto-release
     * @param reuse     whether the connection should be re-used
     */
    public BasicEofSensorWatcher(ManagedClientConnection conn,
                                 boolean reuse) {
        if (conn == null)
            throw new IllegalArgumentException
                ("Connection may not be null.");

        managedConn = conn;
        attemptReuse = reuse;
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean eofDetected(InputStream wrapped)
        throws IOException {

        try {
            if (attemptReuse) {
                // there may be some cleanup required, such as
                // reading trailers after the response body:
                wrapped.close();
                managedConn.markReusable();
            }
        } finally {
            managedConn.releaseConnection();
        }
        return false;
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean streamClosed(InputStream wrapped)
        throws IOException {

        try {
            if (attemptReuse) {
                // this assumes that closing the stream will
                // consume the remainder of the response body:
                wrapped.close();
                managedConn.markReusable();
            }
        } finally {
            managedConn.releaseConnection();
        }
        return false;
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean streamAbort(InputStream wrapped)
        throws IOException {

        managedConn.abortConnection();
        return false;
    }

} // class BasicEofSensorWatcher
