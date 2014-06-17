/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/ReasonPhraseCatalog.java $
 * $Revision: 505744 $
 * $Date: 2007-02-10 10:58:45 -0800 (Sat, 10 Feb 2007) $
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

import java.util.Locale;


/**
 * Interface for obtaining reason phrases for HTTP status codes.
 *
 * @author <a href="mailto:rolandw at apache.org">Roland Weber</a>
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision: 505744 $
 * 
 * @since 4.0
 */
public interface ReasonPhraseCatalog {

    /**
     * Obtains the reason phrase for a status code.
     * The optional context allows for catalogs that detect
     * the language for the reason phrase.
     *
     * @param status    the status code, in the range 100-599
     * @param loc       the preferred locale for the reason phrase
     *
     * @return  the reason phrase, or <code>null</code> if unknown
     */
    public String getReason(int status, Locale loc)
        ;

}
