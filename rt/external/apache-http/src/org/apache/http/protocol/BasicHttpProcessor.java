/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/BasicHttpProcessor.java $
 * $Revision: 613298 $
 * $Date: 2008-01-18 14:09:22 -0800 (Fri, 18 Jan 2008) $
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

package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

/**
 * Keeps lists of interceptors for processing requests and responses.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * @author Andrea Selva
 *
 * @version $Revision: 613298 $
 * 
 * @since 4.0
 */
public final class BasicHttpProcessor implements
    HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable {

    protected List requestInterceptors = null; 
    protected List responseInterceptors = null;


    // non-Javadoc, see interface HttpRequestInterceptorList
    public void addRequestInterceptor(final HttpRequestInterceptor itcp) {

        if (itcp == null) {
            return;
        }
        if (this.requestInterceptors == null) {
            this.requestInterceptors = new ArrayList();
        }
        this.requestInterceptors.add(itcp);
    }
    
    // non-Javadoc, see interface HttpRequestInterceptorList
    public void addRequestInterceptor(final HttpRequestInterceptor itcp,
                                      int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        if (itcp == null) {
            return;
        }

        if (this.requestInterceptors == null) {
            if (index > 0) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            }
            this.requestInterceptors = new ArrayList();
        }
        this.requestInterceptors.add(index, itcp);
    }
    

    public void addResponseInterceptor(HttpResponseInterceptor itcp,
                                       int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        if (itcp == null) {
            return;
        }

        if (this.responseInterceptors == null) {
            if (index > 0) {
                throw new IndexOutOfBoundsException(String.valueOf(index));
            }
            this.responseInterceptors = new ArrayList();
        }
        this.responseInterceptors.add(index, itcp);
    }
    
    
    // non-Javadoc, see interface HttpRequestInterceptorList
    public void removeRequestInterceptorByClass(final Class clazz) {
        if (this.requestInterceptors == null) {
            return;
        }
        for (Iterator it = this.requestInterceptors.iterator();
             it.hasNext(); ) {
            Object request = it.next();
            if (request.getClass().equals(clazz)) {
                it.remove();
            }
        }
    }
    
    // non-Javadoc, see interface HttpResponseInterceptorList
    public void removeResponseInterceptorByClass(final Class clazz) {
        if (this.responseInterceptors == null) {
            return;
        }
        for (Iterator it = this.responseInterceptors.iterator();
             it.hasNext(); ) {
            Object request = it.next();
            if (request.getClass().equals(clazz)) {
                it.remove();
            }
        }
    }
    
    /**
     * Same as {@link #addRequestInterceptor(HttpRequestInterceptor) addRequestInterceptor}.
     *
     * @param interceptor       the interceptor to add
     */
    public final
            void addInterceptor(final HttpRequestInterceptor interceptor) {
        addRequestInterceptor(interceptor);
    }
    
     public final
            void addInterceptor(final HttpRequestInterceptor interceptor,
                                int index) {
        addRequestInterceptor(interceptor, index);
    }
    
    
    // non-Javadoc, see interface HttpRequestInterceptorList
    public int getRequestInterceptorCount() {
        return (this.requestInterceptors == null) ?
            0 : this.requestInterceptors.size();
    }
    
    
    // non-Javadoc, see interface HttpRequestInterceptorList
    public HttpRequestInterceptor getRequestInterceptor(int index) {
        
        if ((this.requestInterceptors == null) ||
                (index < 0) || (index >= this.requestInterceptors.size()))
            return null;
        
        return (HttpRequestInterceptor) this.requestInterceptors.get(index);
    }
    
    
    // non-Javadoc, see interface HttpRequestInterceptorList
    public void clearRequestInterceptors() {
        this.requestInterceptors = null;
    }
    
    
    
    // non-Javadoc, see interface HttpResponseInterceptorList
    public void addResponseInterceptor(final HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return;
        }
        if (this.responseInterceptors == null) {
            this.responseInterceptors = new ArrayList();
        }
        this.responseInterceptors.add(itcp);
    }
    
    /**
     * Same as {@link #addResponseInterceptor(HttpResponseInterceptor) addResponseInterceptor}.
     *
     * @param interceptor       the interceptor to add
     */
    public final
            void addInterceptor(final HttpResponseInterceptor interceptor) {
        addResponseInterceptor(interceptor);
    }
    
    public final void addInterceptor(final HttpResponseInterceptor interceptor,
                                     int index) {
        addResponseInterceptor(interceptor, index);
    }
      
    
    
    // non-Javadoc, see interface HttpResponseInterceptorList
    public int getResponseInterceptorCount() {
        return (this.responseInterceptors == null) ?
            0 : this.responseInterceptors.size();
    }
    
    
    // non-Javadoc, see interface HttpResponseInterceptorList
    public HttpResponseInterceptor getResponseInterceptor(int index) {
        
        if ((this.responseInterceptors == null) ||
                (index < 0) || (index >= this.responseInterceptors.size()))
            return null;
        
        return (HttpResponseInterceptor) this.responseInterceptors.get(index);
    }
    
    
    // non-Javadoc, see interface HttpResponseInterceptorList
    public void clearResponseInterceptors() {
        this.responseInterceptors = null;
    }
    
    
    /**
     * Sets the interceptor lists.
     * First, both interceptor lists maintained by this processor
     * will be cleared.
     * Subsequently,
     * elements of the argument list that are request interceptors will be
     * added to the request interceptor list.
     * Elements that are response interceptors will be
     * added to the response interceptor list.
     * Elements that are both request and response interceptor will be
     * added to both lists.
     * Elements that are neither request nor response interceptor
     * will be ignored.
     *
     * @param list      the list of request and response interceptors
     *                  from which to initialize
     */
    public void setInterceptors(final List list) {
        if (list == null) {
            throw new IllegalArgumentException("List must not be null.");
        }
        if (this.requestInterceptors != null) {
            this.requestInterceptors.clear();
        }
        if (this.responseInterceptors != null) {
            this.responseInterceptors.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof HttpRequestInterceptor) {
                addInterceptor((HttpRequestInterceptor)obj);
            }
            if (obj instanceof HttpResponseInterceptor) {
                addInterceptor((HttpResponseInterceptor)obj);
            }
        }
    }
    
    /**
     * Clears both interceptor lists maintained by this processor.
     */
    public void clearInterceptors() {
        clearRequestInterceptors();
        clearResponseInterceptors();
    }
    
    // non-Javadoc, see interface HttpRequestInterceptor (via HttpProcessor)
    public void process(
            final HttpRequest request,
            final HttpContext context)
            throws IOException, HttpException {
        if (this.requestInterceptors != null) {
            for (int i = 0; i < this.requestInterceptors.size(); i++) {
                HttpRequestInterceptor interceptor =
                    (HttpRequestInterceptor) this.requestInterceptors.get(i);
                interceptor.process(request, context);
            }
        }
    }
    
    // non-Javadoc, see interface HttpResponseInterceptor (via HttpProcessor)
    public void process(
            final HttpResponse response,
            final HttpContext context)
            throws IOException, HttpException {
        if (this.responseInterceptors != null) {
            for (int i = 0; i < this.responseInterceptors.size(); i++) {
                HttpResponseInterceptor interceptor =
                    (HttpResponseInterceptor) this.responseInterceptors.get(i);
                interceptor.process(response, context);
            }
        }
    }

    protected void copyInterceptors(final BasicHttpProcessor target) {
        if (this.requestInterceptors != null) {
            target.requestInterceptors =
                new ArrayList(this.requestInterceptors);
        }
        if (this.responseInterceptors != null) {
            target.responseInterceptors =
                new ArrayList(this.responseInterceptors);
        }
    }
    
    /**
     * Creates a copy of this instance
     *
     * @return new instance of the BasicHttpProcessor
     */
    public BasicHttpProcessor copy() {
        BasicHttpProcessor clone = new BasicHttpProcessor();
        copyInterceptors(clone);
        return clone;
    }
    
    public Object clone() throws CloneNotSupportedException {
        BasicHttpProcessor clone = (BasicHttpProcessor) super.clone();
        copyInterceptors(clone);
        return clone;
    }
 
}
