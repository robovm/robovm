/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/client/AbstractHttpClient.java $
 * $Revision: 677250 $
 * $Date: 2008-07-16 04:45:47 -0700 (Wed, 16 Jul 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.client;

import java.io.IOException;
import java.net.URI;
import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

/**
 * Convenience base class for HTTP client implementations.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version   $Revision: 677250 $
 *
 * @since 4.0
 */
public abstract class AbstractHttpClient implements HttpClient {

    private final Log log = LogFactory.getLog(getClass());

    /** The parameters. */
    private HttpParams defaultParams;

    /** The request executor. */
    private HttpRequestExecutor requestExec;

    /** The connection manager. */
    private ClientConnectionManager connManager;

    /** The connection re-use strategy. */
    private ConnectionReuseStrategy reuseStrategy;
    
    /** The connection keep-alive strategy. */
    private ConnectionKeepAliveStrategy keepAliveStrategy;

    /** The cookie spec registry. */
    private CookieSpecRegistry supportedCookieSpecs;

    /** The authentication scheme registry. */
    private AuthSchemeRegistry supportedAuthSchemes;
    
    /** The HTTP processor. */
    private BasicHttpProcessor httpProcessor;

    /** The request retry handler. */
    private HttpRequestRetryHandler retryHandler;

    /** The redirect handler. */
    private RedirectHandler redirectHandler;

    /** The target authentication handler. */
    private AuthenticationHandler targetAuthHandler;

    /** The proxy authentication handler. */
    private AuthenticationHandler proxyAuthHandler;

    /** The cookie store. */
    private CookieStore cookieStore;

    /** The credentials provider. */
    private CredentialsProvider credsProvider;
    
    /** The route planner. */
    private HttpRoutePlanner routePlanner;

    /** The user token handler. */
    private UserTokenHandler userTokenHandler;


    /**
     * Creates a new HTTP client.
     *
     * @param conman    the connection manager
     * @param params    the parameters
     */
    protected AbstractHttpClient(
            final ClientConnectionManager conman,
            final HttpParams params) {
        defaultParams        = params;
        connManager          = conman;
    } // constructor

    protected abstract HttpParams createHttpParams();

    
    protected abstract HttpContext createHttpContext();

    
    protected abstract HttpRequestExecutor createRequestExecutor();


    protected abstract ClientConnectionManager createClientConnectionManager();


    protected abstract AuthSchemeRegistry createAuthSchemeRegistry();

    
    protected abstract CookieSpecRegistry createCookieSpecRegistry();

    
    protected abstract ConnectionReuseStrategy createConnectionReuseStrategy();

    
    protected abstract ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy();

    
    protected abstract BasicHttpProcessor createHttpProcessor();

    
    protected abstract HttpRequestRetryHandler createHttpRequestRetryHandler();

    
    protected abstract RedirectHandler createRedirectHandler();

    
    protected abstract AuthenticationHandler createTargetAuthenticationHandler();

    
    protected abstract AuthenticationHandler createProxyAuthenticationHandler();

    
    protected abstract CookieStore createCookieStore();
    
    
    protected abstract CredentialsProvider createCredentialsProvider();
    
    
    protected abstract HttpRoutePlanner createHttpRoutePlanner();

    
    protected abstract UserTokenHandler createUserTokenHandler();

    
    // non-javadoc, see interface HttpClient
    public synchronized final HttpParams getParams() {
        if (defaultParams == null) {
            defaultParams = createHttpParams();
        }
        return defaultParams;
    }


    /**
     * Replaces the parameters.
     * The implementation here does not update parameters of dependent objects.
     *
     * @param params    the new default parameters
     */
    public synchronized void setParams(HttpParams params) {
        defaultParams = params;
    }


    public synchronized final ClientConnectionManager getConnectionManager() {
        if (connManager == null) {
            connManager = createClientConnectionManager();
        }
        return connManager;
    }


    public synchronized final HttpRequestExecutor getRequestExecutor() {
        if (requestExec == null) {
            requestExec = createRequestExecutor();
        }
        return requestExec;
    }


    public synchronized final AuthSchemeRegistry getAuthSchemes() {
        if (supportedAuthSchemes == null) {
            supportedAuthSchemes = createAuthSchemeRegistry();
        }
        return supportedAuthSchemes;
    }


    public synchronized void setAuthSchemes(final AuthSchemeRegistry authSchemeRegistry) {
        supportedAuthSchemes = authSchemeRegistry;
    }


    public synchronized final CookieSpecRegistry getCookieSpecs() {
        if (supportedCookieSpecs == null) {
            supportedCookieSpecs = createCookieSpecRegistry();
        }
        return supportedCookieSpecs;
    }


    public synchronized void setCookieSpecs(final CookieSpecRegistry cookieSpecRegistry) {
        supportedCookieSpecs = cookieSpecRegistry;
    }

    
    public synchronized final ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (reuseStrategy == null) {
            reuseStrategy = createConnectionReuseStrategy();
        }
        return reuseStrategy;
    }


    public synchronized void setReuseStrategy(final ConnectionReuseStrategy reuseStrategy) {
        this.reuseStrategy = reuseStrategy;
    }

    
    public synchronized final ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (keepAliveStrategy == null) {
            keepAliveStrategy = createConnectionKeepAliveStrategy();
        }
        return keepAliveStrategy;
    }

    
    public synchronized void setKeepAliveStrategy(final ConnectionKeepAliveStrategy keepAliveStrategy) {
        this.keepAliveStrategy = keepAliveStrategy;
    }


    public synchronized final HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (retryHandler == null) {
            retryHandler = createHttpRequestRetryHandler();
        }
        return retryHandler;
    }


    public synchronized void setHttpRequestRetryHandler(final HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
    }


    public synchronized final RedirectHandler getRedirectHandler() {
        if (redirectHandler == null) {
            redirectHandler = createRedirectHandler();
        }
        return redirectHandler;
    }


    public synchronized void setRedirectHandler(final RedirectHandler redirectHandler) {
        this.redirectHandler = redirectHandler;
    }


    public synchronized final AuthenticationHandler getTargetAuthenticationHandler() {
        if (targetAuthHandler == null) {
            targetAuthHandler = createTargetAuthenticationHandler();
        }
        return targetAuthHandler;
    }


    public synchronized void setTargetAuthenticationHandler(
            final AuthenticationHandler targetAuthHandler) {
        this.targetAuthHandler = targetAuthHandler;
    }


    public synchronized final AuthenticationHandler getProxyAuthenticationHandler() {
        if (proxyAuthHandler == null) {
            proxyAuthHandler = createProxyAuthenticationHandler();
        }
        return proxyAuthHandler;
    }


    public synchronized void setProxyAuthenticationHandler(
            final AuthenticationHandler proxyAuthHandler) {
        this.proxyAuthHandler = proxyAuthHandler;
    }


    public synchronized final CookieStore getCookieStore() {
        if (cookieStore == null) {
            cookieStore = createCookieStore();
        }
        return cookieStore;
    }


    public synchronized void setCookieStore(final CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }


    public synchronized final CredentialsProvider getCredentialsProvider() {
        if (credsProvider == null) {
            credsProvider = createCredentialsProvider();
        }
        return credsProvider;
    }


    public synchronized void setCredentialsProvider(final CredentialsProvider credsProvider) {
        this.credsProvider = credsProvider;
    }


    public synchronized final HttpRoutePlanner getRoutePlanner() {
        if (this.routePlanner == null) {
            this.routePlanner = createHttpRoutePlanner();
        }
        return this.routePlanner;
    }


    public synchronized void setRoutePlanner(final HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }
    
    
    public synchronized final UserTokenHandler getUserTokenHandler() {
        if (this.userTokenHandler == null) {
            this.userTokenHandler = createUserTokenHandler();
        }
        return this.userTokenHandler;
    }


    public synchronized void setUserTokenHandler(final UserTokenHandler userTokenHandler) {
        this.userTokenHandler = userTokenHandler;
    }
    
    
    protected synchronized final BasicHttpProcessor getHttpProcessor() {
        if (httpProcessor == null) {
            httpProcessor = createHttpProcessor();
        }
        return httpProcessor;
    }


    public synchronized void addResponseInterceptor(final HttpResponseInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
    }


    public synchronized void addResponseInterceptor(final HttpResponseInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
    }


    public synchronized HttpResponseInterceptor getResponseInterceptor(int index) {
        return getHttpProcessor().getResponseInterceptor(index);
    }


    public synchronized int getResponseInterceptorCount() {
        return getHttpProcessor().getResponseInterceptorCount();
    }


    public synchronized void clearResponseInterceptors() {
        getHttpProcessor().clearResponseInterceptors();
    }


    public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        getHttpProcessor().removeResponseInterceptorByClass(clazz);
    }

    
    public synchronized void addRequestInterceptor(final HttpRequestInterceptor itcp) {
        getHttpProcessor().addInterceptor(itcp);
    }


    public synchronized void addRequestInterceptor(final HttpRequestInterceptor itcp, int index) {
        getHttpProcessor().addInterceptor(itcp, index);
    }


    public synchronized HttpRequestInterceptor getRequestInterceptor(int index) {
        return getHttpProcessor().getRequestInterceptor(index);
    }


    public synchronized int getRequestInterceptorCount() {
        return getHttpProcessor().getRequestInterceptorCount();
    }


    public synchronized void clearRequestInterceptors() {
        getHttpProcessor().clearRequestInterceptors();
    }


    public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        getHttpProcessor().removeRequestInterceptorByClass(clazz);
    }


    // non-javadoc, see interface HttpClient
    public final HttpResponse execute(HttpUriRequest request)
        throws IOException, ClientProtocolException {

        return execute(request, (HttpContext) null);
    }


    /**
     * Maps to {@link HttpClient#execute(HttpHost,HttpRequest,HttpContext)
     *                           execute(target, request, context)}.
     * The target is determined from the URI of the request.
     *
     * @param request   the request to execute
     * @param context   the request-specific execution context,
     *                  or <code>null</code> to use a default context
     */
    public final HttpResponse execute(HttpUriRequest request,
                                      HttpContext context)
        throws IOException, ClientProtocolException {

        if (request == null) {
            throw new IllegalArgumentException
                ("Request must not be null.");
        }

        return execute(determineTarget(request), request, context);
    }

    private HttpHost determineTarget(HttpUriRequest request) {
        // A null target may be acceptable if there is a default target.
        // Otherwise, the null target is detected in the director.
        HttpHost target = null;

        URI requestURI = request.getURI();
        if (requestURI.isAbsolute()) {
            target = new HttpHost(
                    requestURI.getHost(),
                    requestURI.getPort(),
                    requestURI.getScheme());
        }
        return target;
    }

    // non-javadoc, see interface HttpClient
    public final HttpResponse execute(HttpHost target, HttpRequest request)
        throws IOException, ClientProtocolException {

        return execute(target, request, (HttpContext) null);
    }


    // non-javadoc, see interface HttpClient
    public final HttpResponse execute(HttpHost target, HttpRequest request,
                                      HttpContext context)
        throws IOException, ClientProtocolException {

        if (request == null) {
            throw new IllegalArgumentException
                ("Request must not be null.");
        }
        // a null target may be acceptable, this depends on the route planner
        // a null context is acceptable, default context created below

        HttpContext execContext = null;
        RequestDirector director = null;
        
        // Initialize the request execution context making copies of 
        // all shared objects that are potentially threading unsafe.
        synchronized (this) {

            HttpContext defaultContext = createHttpContext();
            if (context == null) {
                execContext = defaultContext;
            } else {
                execContext = new DefaultedHttpContext(context, defaultContext);
            }
            // Create a director for this request
            director = createClientRequestDirector(
                    getRequestExecutor(),
                    getConnectionManager(),
                    getConnectionReuseStrategy(),
                    getConnectionKeepAliveStrategy(),
                    getRoutePlanner(),
                    getHttpProcessor().copy(),
                    getHttpRequestRetryHandler(),
                    getRedirectHandler(),
                    getTargetAuthenticationHandler(),
                    getProxyAuthenticationHandler(),
                    getUserTokenHandler(),
                    determineParams(request));
        }

        try {
            return director.execute(target, request, execContext);
        } catch(HttpException httpException) {
            throw new ClientProtocolException(httpException);
        }
    } // execute

    
    protected RequestDirector createClientRequestDirector(
            final HttpRequestExecutor requestExec,
            final ClientConnectionManager conman,
            final ConnectionReuseStrategy reustrat,
            final ConnectionKeepAliveStrategy kastrat,
            final HttpRoutePlanner rouplan,
            final HttpProcessor httpProcessor,
            final HttpRequestRetryHandler retryHandler,
            final RedirectHandler redirectHandler,
            final AuthenticationHandler targetAuthHandler,
            final AuthenticationHandler proxyAuthHandler,
            final UserTokenHandler stateHandler,
            final HttpParams params) {
        return new DefaultRequestDirector(
                requestExec,
                conman,
                reustrat,
                kastrat,
                rouplan,
                httpProcessor,
                retryHandler,
                redirectHandler,
                targetAuthHandler,
                proxyAuthHandler,
                stateHandler,
                params);
    }

    /**
     * Obtains parameters for executing a request.
     * The default implementation in this class creates a new
     * {@link ClientParamsStack} from the request parameters
     * and the client parameters.
     * <br/>
     * This method is called by the default implementation of
     * {@link #execute(HttpHost,HttpRequest,HttpContext)}
     * to obtain the parameters for the
     * {@link DefaultRequestDirector}.
     *
     * @param req    the request that will be executed
     *
     * @return  the parameters to use
     */
    protected HttpParams determineParams(HttpRequest req) {
        return new ClientParamsStack
            (null, getParams(), req.getParams(), null);
    }


    // non-javadoc, see interface HttpClient
    public <T> T execute(
            final HttpUriRequest request, 
            final ResponseHandler<? extends T> responseHandler) 
                throws IOException, ClientProtocolException {
        return execute(request, responseHandler, null);
    }


    // non-javadoc, see interface HttpClient
    public <T> T execute(
            final HttpUriRequest request,
            final ResponseHandler<? extends T> responseHandler, 
            final HttpContext context)
                throws IOException, ClientProtocolException {
        HttpHost target = determineTarget(request);
        return execute(target, request, responseHandler, context);
    }


    // non-javadoc, see interface HttpClient
    public <T> T execute(
            final HttpHost target, 
            final HttpRequest request,
            final ResponseHandler<? extends T> responseHandler) 
                throws IOException, ClientProtocolException {
        return execute(target, request, responseHandler, null);
    }


    // non-javadoc, see interface HttpClient
    public <T> T execute(
            final HttpHost target, 
            final HttpRequest request,
            final ResponseHandler<? extends T> responseHandler, 
            final HttpContext context) 
                throws IOException, ClientProtocolException {
        if (responseHandler == null) {
            throw new IllegalArgumentException
                ("Response handler must not be null.");
        }

        HttpResponse response = execute(target, request, context);

        T result;
        try {
            result = responseHandler.handleResponse(response);
        } catch (Throwable t) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try {
                    entity.consumeContent();
                } catch (Throwable t2) {
                    // Log this exception. The original exception is more
                    // important and will be thrown to the caller.
                    this.log.warn("Error consuming content after an exception.", t2);
                }
            }

            if (t instanceof Error) {
                throw (Error) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }

            if (t instanceof IOException) {
                throw (IOException) t;
            }
            
            throw new UndeclaredThrowableException(t);
        }

        // Handling the response was successful. Ensure that the content has
        // been fully consumed.
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // Let this exception go to the caller.
            entity.consumeContent();
        }

        return result;
    }


} // class AbstractHttpClient
