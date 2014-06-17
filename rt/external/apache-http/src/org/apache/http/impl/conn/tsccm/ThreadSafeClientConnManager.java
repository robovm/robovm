/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager.java $
 * $Revision: 673450 $
 * $Date: 2008-07-02 10:35:05 -0700 (Wed, 02 Jul 2008) $
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

package org.apache.http.impl.conn.tsccm;

import dalvik.system.SocketTagger;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;



/**
 * Manages a pool of {@link OperatedClientConnection client connections}.
 * <p>
 * This class is derived from <code>MultiThreadedHttpConnectionManager</code>
 * in HttpClient 3. See there for original authors.
 * </p>
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 * @author <a href="mailto:becke@u.washington.edu">Michael Becke</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version $Revision: 673450 $ $Date: 2008-07-02 10:35:05 -0700 (Wed, 02 Jul 2008) $
 *
 * @since 4.0
 */
public class ThreadSafeClientConnManager implements ClientConnectionManager {

    private final Log log = LogFactory.getLog(getClass());

    /** The schemes supported by this connection manager. */
    protected SchemeRegistry schemeRegistry; 
    
    /** The pool of connections being managed. */
    protected final AbstractConnPool connectionPool;

    /** The operator for opening and updating connections. */
    protected ClientConnectionOperator connOperator;
    


    /**
     * Creates a new thread safe connection manager.
     *
     * @param params    the parameters for this manager
     * @param schreg    the scheme registry
     */
    public ThreadSafeClientConnManager(HttpParams params,
                                       SchemeRegistry schreg) {

        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.schemeRegistry = schreg;
        this.connOperator   = createConnectionOperator(schreg);
        this.connectionPool = createConnectionPool(params);

    } // <constructor>

    
    @Override
    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }


    /**
     * Hook for creating the connection pool.
     *
     * @return  the connection pool to use
     */
    protected AbstractConnPool createConnectionPool(final HttpParams params) {

        AbstractConnPool acp = new ConnPoolByRoute(connOperator, params);
        boolean conngc = true; //@@@ check parameters to decide
        if (conngc) {
            acp.enableConnectionGC();
        }
        return acp;
    }


    /**
     * Hook for creating the connection operator.
     * It is called by the constructor.
     * Derived classes can override this method to change the
     * instantiation of the operator.
     * The default implementation here instantiates
     * {@link DefaultClientConnectionOperator DefaultClientConnectionOperator}.
     *
     * @param schreg    the scheme registry to use, or <code>null</code>
     *
     * @return  the connection operator to use
     */
    protected ClientConnectionOperator
        createConnectionOperator(SchemeRegistry schreg) {

        return new DefaultClientConnectionOperator(schreg);
    }


    // non-javadoc, see interface ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    
    public ClientConnectionRequest requestConnection(
            final HttpRoute route, 
            final Object state) {
        
        final PoolEntryRequest poolRequest = connectionPool.requestPoolEntry(
                route, state);
        
        return new ClientConnectionRequest() {
            
            public void abortRequest() {
                poolRequest.abortRequest();
            }
            
            public ManagedClientConnection getConnection(
                    long timeout, TimeUnit tunit) throws InterruptedException,
                    ConnectionPoolTimeoutException {
                if (route == null) {
                    throw new IllegalArgumentException("Route may not be null.");
                }

                if (log.isDebugEnabled()) {
                    log.debug("ThreadSafeClientConnManager.getConnection: "
                        + route + ", timeout = " + timeout);
                }

                BasicPoolEntry entry = poolRequest.getPoolEntry(timeout, tunit);
                // BEGIN android-changed
                // When using a recycled Socket, we need to re-tag it with any
                // updated statistics options.
                try {
                    final Socket socket = entry.getConnection().getSocket();
                    if (socket != null) {
                        SocketTagger.get().tag(socket);
                    }
                } catch (IOException iox) {
                    log.debug("Problem tagging socket.", iox);
                }
                // END android-changed
                return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, entry);
            }
            
        };
        
    }

    
    // non-javadoc, see interface ClientConnectionManager
    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {

        if (!(conn instanceof BasicPooledConnAdapter)) {
            throw new IllegalArgumentException
                ("Connection class mismatch, " +
                 "connection not obtained from this manager.");
        }
        BasicPooledConnAdapter hca = (BasicPooledConnAdapter) conn;
        if ((hca.getPoolEntry() != null) && (hca.getManager() != this)) {
            throw new IllegalArgumentException
                ("Connection not obtained from this manager.");
        }

        try {
            // BEGIN android-changed
            // When recycling a Socket, we un-tag it to avoid collecting
            // statistics from future users.
            final BasicPoolEntry entry = (BasicPoolEntry) hca.getPoolEntry();
            final Socket socket = entry.getConnection().getSocket();
            if (socket != null) {
                SocketTagger.get().untag(socket);
            }
            // END android-changed

            // make sure that the response has been read completely
            if (hca.isOpen() && !hca.isMarkedReusable()) {
                if (log.isDebugEnabled()) {
                    log.debug
                        ("Released connection open but not marked reusable.");
                }
                // In MTHCM, there would be a call to
                // SimpleHttpConnectionManager.finishLastResponse(conn);
                // Consuming the response is handled outside in 4.0.

                // make sure this connection will not be re-used
                // Shut down rather than close, we might have gotten here
                // because of a shutdown trigger.
                // Shutdown of the adapter also clears the tracked route.
                hca.shutdown();
            }
        } catch (IOException iox) {
            //@@@ log as warning? let pass?
            if (log.isDebugEnabled())
                log.debug("Exception shutting down released connection.",
                          iox);
        } finally {
            BasicPoolEntry entry = (BasicPoolEntry) hca.getPoolEntry();
            boolean reusable = hca.isMarkedReusable();
            hca.detach();
            if (entry != null) {
                connectionPool.freeEntry(entry, reusable, validDuration, timeUnit);
            }
        }
    }


    // non-javadoc, see interface ClientConnectionManager
    public void shutdown() {
        connectionPool.shutdown();
    }


    /**
     * Gets the total number of pooled connections for the given route.
     * This is the total number of connections that have been created and
     * are still in use by this connection manager for the route.
     * This value will not exceed the maximum number of connections per host.
     * 
     * @param route     the route in question
     *
     * @return  the total number of pooled connections for that route
     */
    public int getConnectionsInPool(HttpRoute route) {
        return ((ConnPoolByRoute)connectionPool).getConnectionsInPool(
                route);
    }


    /**
     * Gets the total number of pooled connections.  This is the total number of 
     * connections that have been created and are still in use by this connection 
     * manager.  This value will not exceed the maximum number of connections
     * in total.
     * 
     * @return the total number of pooled connections
     */
    public int getConnectionsInPool() {
        synchronized (connectionPool) {
            return connectionPool.numConnections; //@@@
        }
    }


    // non-javadoc, see interface ClientConnectionManager
    public void closeIdleConnections(long idleTimeout, TimeUnit tunit) {
        // combine these two in a single call?
        connectionPool.closeIdleConnections(idleTimeout, tunit);
        connectionPool.deleteClosedConnections();
    }
    
    public void closeExpiredConnections() {
        connectionPool.closeExpiredConnections();
        connectionPool.deleteClosedConnections();
    }


} // class ThreadSafeClientConnManager

