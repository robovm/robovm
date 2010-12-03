/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: StAXResult.java 670395 2008-06-22 18:50:54Z mrglavas $

package javax.xml.transform.stax;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

public class StAXResult implements Result {
    
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXResult/feature";
    
    private final XMLStreamWriter xmlStreamWriter;
    private final XMLEventWriter xmlEventWriter;
    
    public StAXResult(XMLStreamWriter xmlStreamWriter) {
        if (xmlStreamWriter == null) {
            throw new IllegalArgumentException("XMLStreamWriter cannot be null.");
        }
        this.xmlStreamWriter = xmlStreamWriter;
        this.xmlEventWriter = null;
    }
    
    public StAXResult(XMLEventWriter xmlEventWriter) {
        if (xmlEventWriter == null) {
            throw new IllegalArgumentException("XMLEventWriter cannot be null.");
        }
        this.xmlStreamWriter = null;
        this.xmlEventWriter = xmlEventWriter;
    }
    
    public XMLStreamWriter getXMLStreamWriter() {
        return xmlStreamWriter;
    }
    
    public XMLEventWriter getXMLEventWriter() {
        return xmlEventWriter;
    }

    public String getSystemId() {
        return null;
    }

    public void setSystemId(String systemId) {
        throw new UnsupportedOperationException("Setting systemId is not supported.");
    }
}
