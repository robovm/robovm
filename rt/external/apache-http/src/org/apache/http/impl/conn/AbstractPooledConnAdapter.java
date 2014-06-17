/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/AbstractPooledConnAdapter.java $
 * $Revision: 658775 $
 * $Date: 2008-05-21 10:30:45 -0700 (Wed, 21 May 2008) $
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

package org.apache.http.impl.conn;


import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.OperatedClientConnection;



/**
 * Abstract adapter from pool {@link AbstractPoolEntry entries} to
 * {@link org.apache.http.conn.ManagedClientConnection managed}
 * client connections.
 * The connection in the pool entry is used to initialize the base class.
 * In addition, methods to establish a route are delegated to the
 * pool entry. {@link #shutdown shutdown} and {@link #close close}
 * will clear the tracked route in the pool entry and call the
 * respective method of the wrapped connection.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version   $Revision: 658775 $ $Date: 2008-05-21 10:30:45 -0700 (Wed, 21 May 2008) $
 *
 * @since 4.0
 */
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {

    /** The wrapped pool entry. */
    protected volatile AbstractPoolEntry poolEntry;


    /**
     * Creates a new connection adapter.
     *
     * @param manager   the connection manager
     * @param entry     the pool entry for the connection being wrapped
     */
    protected AbstractPooledConnAdapter(ClientConnectionManager manager,
                                        AbstractPoolEntry entry) {
        super(manager, entry.connection);
        this.poolEntry = entry;
    }


    /**
     * Asserts that this adapter is still attached.
     *
     * @throws IllegalStateException
     *      if it is {@link #detach detach}ed
     */
    protected final void assertAttached() {
        if (poolEntry == null) {
            throw new IllegalStateException("Adapter is detached.");
        }
    }

    /**
     * Detaches this adapter from the wrapped connection.
     * This adapter becomes useless.
     */
    @Override
    protected void detach() {
        super.detach();
        poolEntry = null;
    }


    // non-javadoc, see interface ManagedHttpConnection
    public HttpRoute getRoute() {

        assertAttached();
        return (poolEntry.tracker == null) ?
            null : poolEntry.tracker.toRoute();
    }

    // non-javadoc, see interface ManagedHttpConnection
    public void open(HttpRoute route,
                     HttpContext context, HttpParams params)
        throws IOException {

        assertAttached();
        poolEntry.open(route, context, params);
    }


    // non-javadoc, see interface ManagedHttpConnection
    public void tunnelTarget(boolean secure, HttpParams params)
        throws IOException {

        assertAttached();
        poolEntry.tunnelTarget(secure, params);
    }


    // non-javadoc, see interface ManagedHttpConnection
    public void tunnelProxy(HttpHost next, boolean secure, HttpParams params)
        throws IOException {

        assertAttached();
        poolEntry.tunnelProxy(next, secure, params);
    }


    // non-javadoc, see interface ManagedHttpConnection
    public void layerProtocol(HttpContext context, HttpParams params)
        throws IOException {

        assertAttached();
        poolEntry.layerProtocol(context, params);
    }



    // non-javadoc, see interface HttpConnection        
    public void close() throws IOException {
        if (poolEntry != null)
            poolEntry.shutdownEntry();

        OperatedClientConnection conn = getWrappedConnection();
        if (conn != null) {
            conn.close();
        }
    }

    // non-javadoc, see interface HttpConnection        
    public void shutdown() throws IOException {
        if (poolEntry != null)
            poolEntry.shutdownEntry();

        OperatedClientConnection conn = getWrappedConnection();
        if (conn != null) {
            conn.shutdown();
        }
    }

    
    // non-javadoc, see interface ManagedClientConnection        
    public Object getState() {
        assertAttached();
        return poolEntry.getState();
    }


    // non-javadoc, see interface ManagedClientConnection        
    public void setState(final Object state) {
        assertAttached();
        poolEntry.setState(state);
    }
    

} // class AbstractPooledConnAdapter
