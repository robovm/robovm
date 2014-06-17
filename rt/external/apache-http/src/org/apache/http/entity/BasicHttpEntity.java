/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/entity/BasicHttpEntity.java $
 * $Revision: 496070 $
 * $Date: 2007-01-14 04:18:34 -0800 (Sun, 14 Jan 2007) $
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

package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A generic streamed entity being received on a connection.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 496070 $
 * 
 * @since 4.0
 */
public class BasicHttpEntity extends AbstractHttpEntity {

    private InputStream content;
    private boolean contentObtained;
    private long length;

    /**
     * Creates a new basic entity.
     * The content is initially missing, the content length
     * is set to a negative number.
     */
    public BasicHttpEntity() {
        super();
        this.length = -1;
    }

    // non-javadoc, see interface HttpEntity
    public long getContentLength() {
        return this.length;
    }

    /**
     * Obtains the content, once only.
     *
     * @return  the content, if this is the first call to this method
     *          since {@link #setContent setContent} has been called
     *
     * @throws IllegalStateException
     *          if the content has been obtained before, or
     *          has not yet been provided
     */
    public InputStream getContent()
        throws IllegalStateException {
        if (this.content == null) {
            throw new IllegalStateException("Content has not been provided");
        }
        if (this.contentObtained) {
            throw new IllegalStateException("Content has been consumed");
        }
        this.contentObtained = true;
        return this.content;

    } // getContent

    /**
     * Tells that this entity is not repeatable.
     *
     * @return <code>false</code>
     */
    public boolean isRepeatable() {
        return false;
    }

    /**
     * Specifies the length of the content.
     *
     * @param len       the number of bytes in the content, or
     *                  a negative number to indicate an unknown length
     */
    public void setContentLength(long len) {
        this.length = len;
    }

    /**
     * Specifies the content.
     *
     * @param instream          the stream to return with the next call to
     *                          {@link #getContent getContent}
     */
    public void setContent(final InputStream instream) {
        this.content = instream;
        this.contentObtained = false; 
    }

    // non-javadoc, see interface HttpEntity
    public void writeTo(final OutputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream instream = getContent();
        int l;
        byte[] tmp = new byte[2048];
        while ((l = instream.read(tmp)) != -1) {
            outstream.write(tmp, 0, l);
        }
    }

    // non-javadoc, see interface HttpEntity
    public boolean isStreaming() {
        return !this.contentObtained && this.content != null;
    }

    // non-javadoc, see interface HttpEntity
    public void consumeContent() throws IOException {
        if (content != null) {
            content.close(); // reads to the end of the entity
        }
    }
    
} // class BasicHttpEntity
