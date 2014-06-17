/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/BasicManagedEntity.java $
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;


/**
 * An entity that releases a {@link ManagedClientConnection connection}.
 * A {@link ManagedClientConnection} will
 * typically <i>not</i> return a managed entity, but you can replace
 * the unmanaged entity in the response with a managed one.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version $Revision: 672367 $
 *
 * @since 4.0
 */
public class BasicManagedEntity extends HttpEntityWrapper
    implements ConnectionReleaseTrigger, EofSensorWatcher {

    /** The connection to release. */
    protected ManagedClientConnection managedConn;

    /** Whether to keep the connection alive. */
    protected final boolean attemptReuse;


    /**
     * Creates a new managed entity that can release a connection.
     *
     * @param entity    the entity of which to wrap the content.
     *                  Note that the argument entity can no longer be used
     *                  afterwards, since the content will be taken by this
     *                  managed entity.
     * @param conn      the connection to release
     * @param reuse     whether the connection should be re-used
     */
    public BasicManagedEntity(HttpEntity entity,
                              ManagedClientConnection conn,
                              boolean reuse) {
        super(entity);

        if (conn == null)
            throw new IllegalArgumentException
                ("Connection may not be null.");

        this.managedConn = conn;
        this.attemptReuse = reuse;
    }


    // non-javadoc, see interface HttpEntity
    @Override
    public boolean isRepeatable() {
        return false;
    }


    // non-javadoc, see interface HttpEntity
    @Override
    public InputStream getContent() throws IOException {

        return new EofSensorInputStream(wrappedEntity.getContent(), this);
    }


    // non-javadoc, see interface HttpEntity
    @Override
    public void consumeContent() throws IOException {

        if (managedConn == null)
            return;

        try {
            if (attemptReuse) {
                // this will not trigger a callback from EofSensorInputStream
                wrappedEntity.consumeContent();
                managedConn.markReusable();
            }
        } finally {
            releaseManagedConnection();
        }
    }

    
    // non-javadoc, see interface HttpEntity
    @Override
    public void writeTo(final OutputStream outstream) throws IOException {
        super.writeTo(outstream);
        consumeContent();
    }


    // non-javadoc, see interface ConnectionReleaseTrigger
    public void releaseConnection()
        throws IOException {

        this.consumeContent();
    }


    // non-javadoc, see interface ConnectionReleaseTrigger
    public void abortConnection()
        throws IOException {

        if (managedConn != null) {
            try {
                managedConn.abortConnection();
            } finally {
                managedConn = null;
            }
        }
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean eofDetected(InputStream wrapped)
        throws IOException {

        try {
            if (attemptReuse && (managedConn != null)) {
                // there may be some cleanup required, such as
                // reading trailers after the response body:
                wrapped.close();
                managedConn.markReusable();
            }
        } finally {
            releaseManagedConnection();
        }
        return false;
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean streamClosed(InputStream wrapped)
        throws IOException {

        try {
            if (attemptReuse && (managedConn != null)) {
                // this assumes that closing the stream will
                // consume the remainder of the response body:
                wrapped.close();
                managedConn.markReusable();
            }
        } finally {
            releaseManagedConnection();
        }
        return false;
    }


    // non-javadoc, see interface EofSensorWatcher
    public boolean streamAbort(InputStream wrapped)
        throws IOException {

        if (managedConn != null) {
            managedConn.abortConnection();
        }
        return false;
    }


    /**
     * Releases the connection gracefully.
     * The connection attribute will be nullified.
     * Subsequent invocations are no-ops.
     *
     * @throws IOException      in case of an IO problem.
     *         The connection attribute will be nullified anyway.
     */
    protected void releaseManagedConnection()
        throws IOException {

        if (managedConn != null) {
            try {
                managedConn.releaseConnection();
            } finally {
                managedConn = null;
            }
        }
    }

} // class BasicManagedEntity
