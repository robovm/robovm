/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/HttpException.java $
 * $Revision: 618017 $
 * $Date: 2008-02-03 08:42:22 -0800 (Sun, 03 Feb 2008) $
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

package org.apache.http;

import org.apache.http.util.ExceptionUtils;

/**
 * Signals that an HTTP exception has occurred.
 * 
 * @author Laura Werner
 * 
 * @version $Revision: 618017 $ $Date: 2008-02-03 08:42:22 -0800 (Sun, 03 Feb 2008) $
 */
public class HttpException extends Exception {

    private static final long serialVersionUID = -5437299376222011036L;
    
    /**
     * Creates a new HttpException with a <tt>null</tt> detail message.
     */
    public HttpException() {
        super();
    }

    /**
     * Creates a new HttpException with the specified detail message.
     *
     * @param message the exception detail message
     */
    public HttpException(final String message) {
        super(message);
    }

    /**
     * Creates a new HttpException with the specified detail message and cause.
     * 
     * @param message the exception detail message
     * @param cause the <tt>Throwable</tt> that caused this exception, or <tt>null</tt>
     * if the cause is unavailable, unknown, or not a <tt>Throwable</tt>
     */
    public HttpException(final String message, final Throwable cause) {
        super(message);
        ExceptionUtils.initCause(this, cause);
    }

}
