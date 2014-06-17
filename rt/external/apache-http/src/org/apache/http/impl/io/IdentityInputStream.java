/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/io/IdentityInputStream.java $
 * $Revision: 560358 $
 * $Date: 2007-07-27 12:30:42 -0700 (Fri, 27 Jul 2007) $
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

package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.io.SessionInputBuffer;

/**
 * A stream for reading from a {@link SessionInputBuffer session input buffer}.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 560358 $
 * 
 * @since 4.0
 */
public class IdentityInputStream extends InputStream {
    
    private final SessionInputBuffer in;
    
    private boolean closed = false;
    
    public IdentityInputStream(final SessionInputBuffer in) {
        super();
        if (in == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        this.in = in;
    }
    
    public int available() throws IOException {
        if (!this.closed && this.in.isDataAvailable(10)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void close() throws IOException {
        this.closed = true;
    }

    public int read() throws IOException {
        if (this.closed) {
            return -1;
        } else {
            return this.in.read();
        }
    }
    
    public int read(final byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            return -1;
        } else {
            return this.in.read(b, off, len);
        }
    }
    
}
