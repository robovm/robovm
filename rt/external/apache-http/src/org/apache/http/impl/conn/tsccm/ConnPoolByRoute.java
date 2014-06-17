/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/tsccm/ConnPoolByRoute.java $
 * $Revision: 677240 $
 * $Date: 2008-07-16 04:25:47 -0700 (Wed, 16 Jul 2008) $
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

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpParams;


/**
 * A connection pool that maintains connections by route.
 * This class is derived from <code>MultiThreadedHttpConnectionManager</code>
 * in HttpClient 3.x, see there for original authors. It implements the same
 * algorithm for connection re-use and connection-per-host enforcement:
 * <ul>
 * <li>connections are re-used only for the exact same route</li>
 * <li>connection limits are enforced per route rather than per host</li>
 * </ul>
 * Note that access to the pool datastructures is synchronized via the
 * {@link AbstractConnPool#poolLock poolLock} in the base class,
 * not via <code>synchronized</code> methods.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 * @author <a href="mailto:becke@u.washington.edu">Michael Becke</a>
 * @author and others
 */
public class ConnPoolByRoute extends AbstractConnPool {
        
    private final Log log = LogFactory.getLog(getClass());

    /** Connection operator for this pool */
    protected final ClientConnectionOperator operator;
    
    /** The list of free connections */
    protected Queue<BasicPoolEntry> freeConnections;

    /** The list of WaitingThreads waiting for a connection */
    protected Queue<WaitingThread> waitingThreads;

    /**
     * A map of route-specific pools.
     * Keys are of class {@link HttpRoute},
     * values of class {@link RouteSpecificPool}.
     */
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;

    protected final int maxTotalConnections;
    
    private final ConnPerRoute connPerRoute;
    
    /**
     * Creates a new connection pool, managed by route.
     */
    public ConnPoolByRoute(final ClientConnectionOperator operator, final HttpParams params) {
        super();
        if (operator == null) {
            throw new IllegalArgumentException("Connection operator may not be null");
        }
        this.operator = operator;
        
        freeConnections = createFreeConnQueue();
        waitingThreads  = createWaitingThreadQueue();
        routeToPool     = createRouteToPoolMap();
        maxTotalConnections = ConnManagerParams
            .getMaxTotalConnections(params);
        connPerRoute = ConnManagerParams
            .getMaxConnectionsPerRoute(params);
    }


    /**
     * Creates the queue for {@link #freeConnections}.
     * Called once by the constructor.
     *
     * @return  a queue
     */
    protected Queue<BasicPoolEntry> createFreeConnQueue() {
        return new LinkedList<BasicPoolEntry>();
    }

    /**
     * Creates the queue for {@link #waitingThreads}.
     * Called once by the constructor.
     *
     * @return  a queue
     */
    protected Queue<WaitingThread> createWaitingThreadQueue() {
        return new LinkedList<WaitingThread>();
    }

    /**
     * Creates the map for {@link #routeToPool}.
     * Called once by the constructor.
     *
     * @return  a map
     */
    protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        return new HashMap<HttpRoute, RouteSpecificPool>();
    }


    /**
     * Creates a new route-specific pool.
     * Called by {@link #getRoutePool} when necessary.
     *
     * @param route     the route
     *
     * @return  the new pool
     */
    protected RouteSpecificPool newRouteSpecificPool(HttpRoute route) {
        return new RouteSpecificPool(route, connPerRoute.getMaxForRoute(route));
    }


    /**
     * Creates a new waiting thread.
     * Called by {@link #getRoutePool} when necessary.
     *
     * @param cond      the condition to wait for
     * @param rospl     the route specific pool, or <code>null</code>
     *
     * @return  a waiting thread representation
     */
    protected WaitingThread newWaitingThread(Condition cond,
                                             RouteSpecificPool rospl) {
        return new WaitingThread(cond, rospl);
    }


    /**
     * Get a route-specific pool of available connections.
     *
     * @param route   the route
     * @param create    whether to create the pool if it doesn't exist
     *
     * @return  the pool for the argument route,
     *     never <code>null</code> if <code>create</code> is <code>true</code>
     */
    protected RouteSpecificPool getRoutePool(HttpRoute route,
                                             boolean create) {
        RouteSpecificPool rospl = null;
        poolLock.lock();
        try {

            rospl = routeToPool.get(route);
            if ((rospl == null) && create) {
                // no pool for this route yet (or anymore)
                rospl = newRouteSpecificPool(route);
                routeToPool.put(route, rospl);
            }

        } finally {
            poolLock.unlock();
        }

        return rospl;
    }


    //@@@ consider alternatives for gathering statistics
    public int getConnectionsInPool(HttpRoute route) {

        poolLock.lock();
        try {
            // don't allow a pool to be created here!
            RouteSpecificPool rospl = getRoutePool(route, false);
            return (rospl != null) ? rospl.getEntryCount() : 0;

        } finally {
            poolLock.unlock();
        }
    }
    
    @Override
    public PoolEntryRequest requestPoolEntry(
            final HttpRoute route,
            final Object state) {
        
        final WaitingThreadAborter aborter = new WaitingThreadAborter();
        
        return new PoolEntryRequest() {
        
            public void abortRequest() {
                poolLock.lock();
                try {
                    aborter.abort();
                } finally {
                    poolLock.unlock();
                }
            }
            
            public BasicPoolEntry getPoolEntry(
                    long timeout,
                    TimeUnit tunit)
                        throws InterruptedException, ConnectionPoolTimeoutException {
                return getEntryBlocking(route, state, timeout, tunit, aborter);
            }
            
        };
    }

    /**
     * Obtains a pool entry with a connection within the given timeout.
     * If a {@link WaitingThread} is used to block, {@link WaitingThreadAborter#setWaitingThread(WaitingThread)}
     * must be called before blocking, to allow the thread to be interrupted.
     *
     * @param route     the route for which to get the connection
     * @param timeout   the timeout, 0 or negative for no timeout
     * @param tunit     the unit for the <code>timeout</code>,
     *                  may be <code>null</code> only if there is no timeout
     * @param aborter   an object which can abort a {@link WaitingThread}.
     *
     * @return  pool entry holding a connection for the route
     *
     * @throws ConnectionPoolTimeoutException
     *         if the timeout expired
     * @throws InterruptedException
     *         if the calling thread was interrupted
     */
    protected BasicPoolEntry getEntryBlocking(
                                   HttpRoute route, Object state,
                                   long timeout, TimeUnit tunit,
                                   WaitingThreadAborter aborter)
        throws ConnectionPoolTimeoutException, InterruptedException {

        Date deadline = null;
        if (timeout > 0) {
            deadline = new Date
                (System.currentTimeMillis() + tunit.toMillis(timeout));
        }

        BasicPoolEntry entry = null;
        poolLock.lock();
        try {

            RouteSpecificPool rospl = getRoutePool(route, true);
            WaitingThread waitingThread = null;

            while (entry == null) {

                if (isShutDown) {
                    throw new IllegalStateException
                        ("Connection pool shut down.");
                }

                if (log.isDebugEnabled()) {
                    log.debug("Total connections kept alive: " + freeConnections.size()); 
                    log.debug("Total issued connections: " + issuedConnections.size()); 
                    log.debug("Total allocated connection: " + numConnections + " out of " + maxTotalConnections);
                }
                
                // the cases to check for:
                // - have a free connection for that route
                // - allowed to create a free connection for that route
                // - can delete and replace a free connection for another route
                // - need to wait for one of the things above to come true

                entry = getFreeEntry(rospl, state);
                if (entry != null) {
                    break;
                }
                
                boolean hasCapacity = rospl.getCapacity() > 0; 
                
                if (log.isDebugEnabled()) {
                    log.debug("Available capacity: " + rospl.getCapacity() 
                            + " out of " + rospl.getMaxEntries()
                            + " [" + route + "][" + state + "]");
                }
                
                if (hasCapacity && numConnections < maxTotalConnections) {

                    entry = createEntry(rospl, operator);

                } else if (hasCapacity && !freeConnections.isEmpty()) {

                    deleteLeastUsedEntry();
                    entry = createEntry(rospl, operator);

                } else {

                    if (log.isDebugEnabled()) {
                        log.debug("Need to wait for connection" +
                                " [" + route + "][" + state + "]");
                    }

                    if (waitingThread == null) {
                        waitingThread =
                            newWaitingThread(poolLock.newCondition(), rospl);
                        aborter.setWaitingThread(waitingThread);
                    }

                    boolean success = false;
                    try {
                        rospl.queueThread(waitingThread);
                        waitingThreads.add(waitingThread);
                        success = waitingThread.await(deadline);

                    } finally {
                        // In case of 'success', we were woken up by the
                        // connection pool and should now have a connection
                        // waiting for us, or else we're shutting down.
                        // Just continue in the loop, both cases are checked.
                        rospl.removeThread(waitingThread);
                        waitingThreads.remove(waitingThread);
                    }

                    // check for spurious wakeup vs. timeout
                    if (!success && (deadline != null) &&
                        (deadline.getTime() <= System.currentTimeMillis())) {
                        throw new ConnectionPoolTimeoutException
                            ("Timeout waiting for connection");
                    }
                }
            } // while no entry

        } finally {
            poolLock.unlock();
        }

        return entry;

    } // getEntry


    // non-javadoc, see base class AbstractConnPool
    @Override
    public void freeEntry(BasicPoolEntry entry, boolean reusable, long validDuration, TimeUnit timeUnit) {

        HttpRoute route = entry.getPlannedRoute();
        if (log.isDebugEnabled()) {
            log.debug("Freeing connection" +                                 
                    " [" + route + "][" + entry.getState() + "]");
        }

        poolLock.lock();
        try {
            if (isShutDown) {
                // the pool is shut down, release the
                // connection's resources and get out of here
                closeConnection(entry.getConnection());
                return;
            }

            // no longer issued, we keep a hard reference now
            issuedConnections.remove(entry.getWeakRef());

            RouteSpecificPool rospl = getRoutePool(route, true);

            if (reusable) {
                rospl.freeEntry(entry);
                freeConnections.add(entry);
                idleConnHandler.add(entry.getConnection(), validDuration, timeUnit);
            } else {
                rospl.dropEntry();
                numConnections--;
            }

            notifyWaitingThread(rospl);

        } finally {
            poolLock.unlock();
        }

    } // freeEntry



    /**
     * If available, get a free pool entry for a route.
     *
     * @param rospl       the route-specific pool from which to get an entry
     *
     * @return  an available pool entry for the given route, or
     *          <code>null</code> if none is available
     */
    protected BasicPoolEntry getFreeEntry(RouteSpecificPool rospl, Object state) {

        BasicPoolEntry entry = null;
        poolLock.lock();
        try {
            boolean done = false;
            while(!done) {

                entry = rospl.allocEntry(state);
    
                if (entry != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Getting free connection" 
                                + " [" + rospl.getRoute() + "][" + state + "]");
    
                    }
                    freeConnections.remove(entry);
                    boolean valid = idleConnHandler.remove(entry.getConnection());
                    if(!valid) {
                        // If the free entry isn't valid anymore, get rid of it
                        // and loop to find another one that might be valid.
                        if(log.isDebugEnabled())
                            log.debug("Closing expired free connection"
                                    + " [" + rospl.getRoute() + "][" + state + "]");
                        closeConnection(entry.getConnection());
                        // We use dropEntry instead of deleteEntry because the entry
                        // is no longer "free" (we just allocated it), and deleteEntry
                        // can only be used to delete free entries.
                        rospl.dropEntry();
                        numConnections--;
                    } else {
                        issuedConnections.add(entry.getWeakRef());
                        done = true;
                    }
    
                } else {
                    done = true;
                    if (log.isDebugEnabled()) {
                        log.debug("No free connections" 
                                + " [" + rospl.getRoute() + "][" + state + "]");
                    }
                }
            }
        } finally {
            poolLock.unlock();
        }

        return entry;
    }


    /**
     * Creates a new pool entry.
     * This method assumes that the new connection will be handed
     * out immediately.
     *
     * @param rospl       the route-specific pool for which to create the entry
     * @param op        the operator for creating a connection
     *
     * @return  the new pool entry for a new connection
     */
    protected BasicPoolEntry createEntry(RouteSpecificPool rospl,
                                         ClientConnectionOperator op) {

        if (log.isDebugEnabled()) {
            log.debug("Creating new connection [" + rospl.getRoute() + "]");
        }

        // the entry will create the connection when needed
        BasicPoolEntry entry =
            new BasicPoolEntry(op, rospl.getRoute(), refQueue);

        poolLock.lock();
        try {

            rospl.createdEntry(entry);
            numConnections++;

            issuedConnections.add(entry.getWeakRef());

        } finally {
            poolLock.unlock();
        }

        return entry;
    }

        
    /**
     * Deletes a given pool entry.
     * This closes the pooled connection and removes all references,
     * so that it can be GCed.
     * 
     * <p><b>Note:</b> Does not remove the entry from the freeConnections list.
     * It is assumed that the caller has already handled this step.</p>
     * <!-- @@@ is that a good idea? or rather fix it? -->
     * 
     * @param entry         the pool entry for the connection to delete
     */
    protected void deleteEntry(BasicPoolEntry entry) {

        HttpRoute route = entry.getPlannedRoute();

        if (log.isDebugEnabled()) {
            log.debug("Deleting connection" 
                    + " [" + route + "][" + entry.getState() + "]");
        }

        poolLock.lock();
        try {

            closeConnection(entry.getConnection());

            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.deleteEntry(entry);
            numConnections--;
            if (rospl.isUnused()) {
                routeToPool.remove(route);
            }

            idleConnHandler.remove(entry.getConnection());// not idle, but dead

        } finally {
            poolLock.unlock();
        }
    }


    /**
     * Delete an old, free pool entry to make room for a new one.
     * Used to replace pool entries with ones for a different route.
     */
    protected void deleteLeastUsedEntry() {

        try {
            poolLock.lock();

            //@@@ with get() instead of remove, we could
            //@@@ leave the removing to deleteEntry()
            BasicPoolEntry entry = freeConnections.remove();

            if (entry != null) {
                deleteEntry(entry);
            } else if (log.isDebugEnabled()) {
                log.debug("No free connection to delete.");
            }

        } finally {
            poolLock.unlock();
        }
    }


    // non-javadoc, see base class AbstractConnPool
    @Override
    protected void handleLostEntry(HttpRoute route) {

        poolLock.lock();
        try {

            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.dropEntry();
            if (rospl.isUnused()) {
                routeToPool.remove(route);
            }

            numConnections--;
            notifyWaitingThread(rospl);

        } finally {
            poolLock.unlock();
        }
    }


    /**
     * Notifies a waiting thread that a connection is available.
     * This will wake a thread waiting in the specific route pool,
     * if there is one.
     * Otherwise, a thread in the connection pool will be notified.
     * 
     * @param rospl     the pool in which to notify, or <code>null</code>
     */
    protected void notifyWaitingThread(RouteSpecificPool rospl) {

        //@@@ while this strategy provides for best connection re-use,
        //@@@ is it fair? only do this if the connection is open?
        // Find the thread we are going to notify. We want to ensure that
        // each waiting thread is only interrupted once, so we will remove
        // it from all wait queues before interrupting.
        WaitingThread waitingThread = null;

        poolLock.lock();
        try {

            if ((rospl != null) && rospl.hasThread()) {
                if (log.isDebugEnabled()) {
                    log.debug("Notifying thread waiting on pool" +
                            " [" + rospl.getRoute() + "]");
                }
                waitingThread = rospl.nextThread();
            } else if (!waitingThreads.isEmpty()) {
                if (log.isDebugEnabled()) {
                    log.debug("Notifying thread waiting on any pool");
                }
                waitingThread = waitingThreads.remove();
            } else if (log.isDebugEnabled()) {
                log.debug("Notifying no-one, there are no waiting threads");
            }

            if (waitingThread != null) {
                waitingThread.wakeup();
            }

        } finally {
            poolLock.unlock();
        }
    }


    //@@@ revise this cleanup stuff
    //@@@ move method to base class when deleteEntry() is fixed
    // non-javadoc, see base class AbstractConnPool
    @Override
    public void deleteClosedConnections() {

        poolLock.lock();
        try {

            Iterator<BasicPoolEntry>  iter = freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = iter.next();
                if (!entry.getConnection().isOpen()) {
                    iter.remove();
                    deleteEntry(entry);
                }
            }

        } finally {
            poolLock.unlock();
        }
    }


    // non-javadoc, see base class AbstractConnPool
    @Override
    public void shutdown() {

        poolLock.lock();
        try {

            super.shutdown();

            // close all free connections
            //@@@ move this to base class?
            Iterator<BasicPoolEntry> ibpe = freeConnections.iterator();
            while (ibpe.hasNext()) {
                BasicPoolEntry entry = ibpe.next();
                ibpe.remove();
                closeConnection(entry.getConnection());
            }

            // wake up all waiting threads
            Iterator<WaitingThread> iwth = waitingThreads.iterator();
            while (iwth.hasNext()) {
                WaitingThread waiter = iwth.next();
                iwth.remove();
                waiter.wakeup();
            }

            routeToPool.clear();

        } finally {
            poolLock.unlock();
        }
    }


} // class ConnPoolByRoute

