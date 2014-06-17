/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/io/SessionInputBuffer.java $
 * $Revision: 560528 $
 * $Date: 2007-07-28 04:34:17 -0700 (Sat, 28 Jul 2007) $
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

package org.apache.http.io;

import java.io.IOException;

import org.apache.http.util.CharArrayBuffer;

/**
 * Session input buffer for blocking connections.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 560528 $
 * 
 * @since 4.0
 */
public interface SessionInputBuffer {
    
    int read(byte[] b, int off, int len) throws IOException; 
    
    int read(byte[] b) throws IOException; 
    
    int read() throws IOException; 
    
    int readLine(CharArrayBuffer buffer) throws IOException;
    
    String readLine() throws IOException;
    
    boolean isDataAvailable(int timeout) throws IOException; 

    HttpTransportMetrics getMetrics();
    
}
