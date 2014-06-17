/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/tsccm/AbstractConnPool.java $
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

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.IdleConnectionHandler;


/**
 * An abstract connection pool.
 * It is used by the {@link ThreadSafeClientConnManager}.
 * The abstract pool includes a {@link #poolLock}, which is used to
 * synchronize access to the internal pool datastructures.
 * Don't use <code>synchronized</code> for that purpose!
 */
public abstract class AbstractConnPool implements RefQueueHandler {

    private final Log log = LogFactory.getLog(getClass());

    /**
     * The global lock for this pool.
     */
    protected final Lock poolLock;


    /**
     * References to issued connections.
     * Objects in this set are of class
     * {@link BasicPoolEntryRef BasicPoolEntryRef},
     * and point to the pool entry for the issued connection.
     * GCed connections are detected by the missing pool entries.
     */
    protected Set<BasicPoolEntryRef> issuedConnections;

    /** The handler for idle connections. */
    protected IdleConnectionHandler idleConnHandler;

    /** The current total number of connections. */
    protected int numConnections;

    /**
     * A reference queue to track loss of pool entries to GC.
     * The same queue is used to track loss of the connection manager,
     * so we cannot specialize the type.
     */
    protected ReferenceQueue<Object> refQueue;

    /** A worker (thread) to track loss of pool entries to GC. */
    private RefQueueWorker refWorker;


    /** Indicates whether this pool is shut down. */
    protected volatile boolean isShutDown;

    /**
     * Creates a new connection pool.
     */
    protected AbstractConnPool() {
        issuedConnections = new HashSet<BasicPoolEntryRef>();
        idleConnHandler = new IdleConnectionHandler();

        boolean fair = false; //@@@ check parameters to decide
        poolLock = new ReentrantLock(fair);
    }


    /**
     * Enables connection garbage collection (GC).
     * This method must be called immediately after creating the
     * connection pool. It is not possible to enable connection GC
     * after pool entries have been created. Neither is it possible
     * to disable connection GC.
     *
     * @throws IllegalStateException
     *         if connection GC is already enabled, or if it cannot be
     *         enabled because there already are pool entries
     */
    public void enableConnectionGC()
        throws IllegalStateException {

        if (refQueue != null) {
            throw new IllegalStateException("Connection GC already enabled.");
        }
        poolLock.lock();
        try {
            if (numConnections > 0) { //@@@ is this check sufficient?
                throw new IllegalStateException("Pool already in use.");
            }
        } finally {
            poolLock.unlock();
        }

        refQueue  = new ReferenceQueue<Object>();
        refWorker = new RefQueueWorker(refQueue, this);
        Thread t = new Thread(refWorker); //@@@ use a thread factory
        t.setDaemon(true);
        t.setName("RefQueueWorker@" + this);
        t.start();
    }


    /**
     * Obtains a pool entry with a connection within the given timeout.
     *
     * @param route     the route for which to get the connection
     * @param timeout   the timeout, 0 or negative for no timeout
     * @param tunit     the unit for the <code>timeout</code>,
     *                  may be <code>null</code> only if there is no timeout
     *
     * @return  pool entry holding a connection for the route
     *
     * @throws ConnectionPoolTimeoutException
     *         if the timeout expired
     * @throws InterruptedException
     *         if the calling thread was interrupted
     */
    public final
        BasicPoolEntry getEntry(
                HttpRoute route, 
                Object state,
                long timeout, 
                TimeUnit tunit)
                    throws ConnectionPoolTimeoutException, InterruptedException {
        return requestPoolEntry(route, state).getPoolEntry(timeout, tunit);
    }
    
    /**
     * Returns a new {@link PoolEntryRequest}, from which a {@link BasicPoolEntry}
     * can be obtained, or the request can be aborted.
     */
    public abstract PoolEntryRequest requestPoolEntry(HttpRoute route, Object state);


    /**
     * Returns an entry into the pool.
     * The connection of the entry is expected to be in a suitable state,
     * either open and re-usable, or closed. The pool will not make any
     * attempt to determine whether it can be re-used or not.
     *
     * @param entry     the entry for the connection to release
     * @param reusable  <code>true</code> if the entry is deemed 
     *                  reusable, <code>false</code> otherwise.
     * @param validDuration The duration that the entry should remain free and reusable.
     * @param timeUnit The unit of time the duration is measured in.
     */
    public abstract void freeEntry(BasicPoolEntry entry, boolean reusable, long validDuration, TimeUnit timeUnit)
        ;



    // non-javadoc, see interface RefQueueHandler
// BEGIN android-changed
    public void handleReference(Reference ref) {
// END android-changed
        poolLock.lock();
        try {

            if (ref instanceof BasicPoolEntryRef) {
                // check if the GCed pool entry was still in use
                //@@@ find a way to detect this without lookup
                //@@@ flag in the BasicPoolEntryRef, to be reset when freed?
                final boolean lost = issuedConnections.remove(ref);
                if (lost) {
                    final HttpRoute route =
                        ((BasicPoolEntryRef)ref).getRoute();
                    if (log.isDebugEnabled()) {
                        log.debug("Connection garbage collected. " + route);
                    }
                    handleLostEntry(route);
                }
            }

        } finally {
            poolLock.unlock();
        }
    }


    /**
     * Handles cleaning up for a lost pool entry with the given route.
     * A lost pool entry corresponds to a connection that was
     * garbage collected instead of being properly released.
     *
     * @param route     the route of the pool entry that was lost
     */
    protected abstract void handleLostEntry(HttpRoute route)
        ;


    /**
     * Closes idle connections.
     *
     * @param idletime  the time the connections should have been idle
     *                  in order to be closed now
     * @param tunit     the unit for the <code>idletime</code>
     */
    public void closeIdleConnections(long idletime, TimeUnit tunit) {

        // idletime can be 0 or negative, no problem there
        if (tunit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        }

        poolLock.lock();
        try {
            idleConnHandler.closeIdleConnections(tunit.toMillis(idletime));
        } finally {
            poolLock.unlock();
        }
    }
    
    public void closeExpiredConnections() {
        poolLock.lock();
        try {
            idleConnHandler.closeExpiredConnections();
        } finally {
            poolLock.unlock();
        }
    }

        
    //@@@ revise this cleanup stuff (closeIdle+deleteClosed), it's not good

    /**
     * Deletes all entries for closed connections.
     */
    public abstract void deleteClosedConnections()
        ;


    /**
     * Shuts down this pool and all associated resources.
     * Overriding methods MUST call the implementation here!
     */
    public void shutdown() {

        poolLock.lock();
        try {

            if (isShutDown)
                return;

            // no point in monitoring GC anymore
            if (refWorker != null)
                refWorker.shutdown();

            // close all connections that are issued to an application
            Iterator<BasicPoolEntryRef> iter = issuedConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntryRef per = iter.next();
                iter.remove();
                BasicPoolEntry entry = per.get();
                if (entry != null) {
                    closeConnection(entry.getConnection());
                }
            }

            // remove all references to connections
            //@@@ use this for shutting them down instead?
            idleConnHandler.removeAll();

            isShutDown = true;

        } finally {
            poolLock.unlock();
        }
    }


    /**
     * Closes a connection from this pool.
     *
     * @param conn      the connection to close, or <code>null</code>
     */
    protected void closeConnection(final OperatedClientConnection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException ex) {
                log.debug("I/O error closing connection", ex);
            }
        }
    }





} // class AbstractConnPool

