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

// $Id: StAXSource.java 670394 2008-06-22 18:50:36Z mrglavas $

package javax.xml.transform.stax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;

public class StAXSource implements Source {
    
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXSource/feature";
    
    private final XMLStreamReader xmlStreamReader;
    private final XMLEventReader xmlEventReader;
    private final String systemId;
    
    public StAXSource(XMLStreamReader xmlStreamReader) {
        if (xmlStreamReader == null) {
            throw new IllegalArgumentException("XMLStreamReader cannot be null.");
        }
        final int event = xmlStreamReader.getEventType();
        if (event != XMLStreamConstants.START_DOCUMENT && 
            event != XMLStreamConstants.START_ELEMENT) {
            throw new IllegalStateException("The state of the XMLStreamReader must be START_DOCUMENT or START_ELEMENT");
        }
        this.xmlStreamReader = xmlStreamReader;
        this.xmlEventReader = null;
        this.systemId = xmlStreamReader.getLocation().getSystemId();
    }
    
    public StAXSource(XMLEventReader xmlEventReader)
        throws XMLStreamException {
        if (xmlEventReader == null) {
            throw new IllegalArgumentException("XMLEventReader cannot be null.");
        }
        final XMLEvent event = xmlEventReader.peek();
        if (!event.isStartDocument() && 
            !event.isStartElement()) {
            throw new IllegalStateException("The state of the XMLEventReader must be START_DOCUMENT or START_ELEMENT");
        }
        this.xmlStreamReader = null;
        this.xmlEventReader = xmlEventReader;
        this.systemId = event.getLocation().getSystemId();
    }
    
    public XMLStreamReader getXMLStreamReader() {
        return xmlStreamReader;
    }
    
    public XMLEventReader getXMLEventReader() {
        return xmlEventReader;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        throw new UnsupportedOperationException("Setting systemId is not supported.");
    }
}
