/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/HttpClient.java $
 * $Revision: 676020 $
 * $Date: 2008-07-11 09:38:49 -0700 (Fri, 11 Jul 2008) $
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

package org.apache.http.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;

/**
 * Interface for an HTTP client.
 * HTTP clients encapsulate a smorgasbord of objects required to
 * execute HTTP requests while handling cookies, authentication,
 * connection management, and other features.
 * Thread safety of HTTP clients depends on the implementation
 * and configuration of the specific client.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version   $Revision: 676020 $
 *
 * @since 4.0
 */
public interface HttpClient {


    /**
     * Obtains the parameters for this client.
     * These parameters will become defaults for all requests being
     * executed with this client, and for the parameters of
     * dependent objects in this client.
     *
     * @return  the default parameters
     */
    HttpParams getParams()
        ;


    /**
     * Obtains the connection manager used by this client.
     *
     * @return  the connection manager
     */
    ClientConnectionManager getConnectionManager()
        ;

    /**
     * Executes a request using the default context.
     *
     * @param request   the request to execute
     *
     * @return  the response to the request. This is always a final response,
     *          never an intermediate response with an 1xx status code.
     *          Whether redirects or authentication challenges will be returned
     *          or handled automatically depends on the implementation and
     *          configuration of this client.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    HttpResponse execute(HttpUriRequest request)
        throws IOException, ClientProtocolException
        ;


    /**
     * Executes a request using the given context.
     * The route to the target will be determined by the HTTP client.
     *
     * @param request   the request to execute
     * @param context   the context to use for the execution, or
     *                  <code>null</code> to use the default context
     *
     * @return  the response to the request. This is always a final response,
     *          never an intermediate response with an 1xx status code.
     *          Whether redirects or authentication challenges will be returned
     *          or handled automatically depends on the implementation and
     *          configuration of this client.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    HttpResponse execute(HttpUriRequest request, HttpContext context)
        throws IOException, ClientProtocolException
        ;


    /**
     * Executes a request to the target using the default context.
     *
     * @param target    the target host for the request.
     *                  Implementations may accept <code>null</code>
     *                  if they can still determine a route, for example
     *                  to a default target or by inspecting the request.
     * @param request   the request to execute
     *
     * @return  the response to the request. This is always a final response,
     *          never an intermediate response with an 1xx status code.
     *          Whether redirects or authentication challenges will be returned
     *          or handled automatically depends on the implementation and
     *          configuration of this client.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    HttpResponse execute(HttpHost target, HttpRequest request)
        throws IOException, ClientProtocolException
        ;

    /**
     * Executes a request to the target using the given context.
     *
     * @param target    the target host for the request.
     *                  Implementations may accept <code>null</code>
     *                  if they can still determine a route, for example
     *                  to a default target or by inspecting the request.
     * @param request   the request to execute
     * @param context   the context to use for the execution, or
     *                  <code>null</code> to use the default context
     *
     * @return  the response to the request. This is always a final response,
     *          never an intermediate response with an 1xx status code.
     *          Whether redirects or authentication challenges will be returned
     *          or handled automatically depends on the implementation and
     *          configuration of this client.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    HttpResponse execute(HttpHost target, HttpRequest request,
                         HttpContext context)
        throws IOException, ClientProtocolException
        ;

    /**
     * Executes a request using the default context and processes the
     * response using the given response handler.
     *
     * @param request   the request to execute
     * @param responseHandler the response handler
     *
     * @return  the response object as generated by the response handler.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    <T> T execute(
            HttpUriRequest request, 
            ResponseHandler<? extends T> responseHandler)
        throws IOException, ClientProtocolException
        ;

    /**
     * Executes a request using the given context and processes the
     * response using the given response handler.
     *
     * @param request   the request to execute
     * @param responseHandler the response handler
     *
     * @return  the response object as generated by the response handler.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    <T> T execute(
            HttpUriRequest request, 
            ResponseHandler<? extends T> responseHandler,
            HttpContext context)
        throws IOException, ClientProtocolException
        ;

    /**
     * Executes a request to the target using the default context and 
     * processes the response using the given response handler.
     *
     * @param target    the target host for the request.
     *                  Implementations may accept <code>null</code>
     *                  if they can still determine a route, for example
     *                  to a default target or by inspecting the request.
     * @param request   the request to execute
     * @param responseHandler the response handler
     *
     * @return  the response object as generated by the response handler.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    <T> T execute(
            HttpHost target, 
            HttpRequest request,
            ResponseHandler<? extends T> responseHandler)
        throws IOException, ClientProtocolException
        ;
    
    /**
     * Executes a request to the target using the given context and 
     * processes the response using the given response handler.
     *
     * @param target    the target host for the request.
     *                  Implementations may accept <code>null</code>
     *                  if they can still determine a route, for example
     *                  to a default target or by inspecting the request.
     * @param request   the request to execute
     * @param responseHandler the response handler
     * @param context   the context to use for the execution, or
     *                  <code>null</code> to use the default context
     *
     * @return  the response object as generated by the response handler.
     * @throws IOException in case of a problem or the connection was aborted
     * @throws ClientProtocolException in case of an http protocol error
     */
    <T> T execute(
            HttpHost target, 
            HttpRequest request,
            ResponseHandler<? extends T> responseHandler, 
            HttpContext context)
        throws IOException, ClientProtocolException
        ;
    
} // interface HttpClient
