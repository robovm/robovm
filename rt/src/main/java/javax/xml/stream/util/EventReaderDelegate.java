/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: EventReaderDelegate.java 670273 2008-06-21 23:31:31Z mrglavas $

package javax.xml.stream.util;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class EventReaderDelegate implements XMLEventReader {
    
    private XMLEventReader reader;

    public EventReaderDelegate() {}

    public EventReaderDelegate(XMLEventReader reader) {
        this.reader = reader;
    }
    
    public void setParent(XMLEventReader reader) {
        this.reader = reader;
    }
    
    public XMLEventReader getParent() {
        return reader;
    }
    
    /*
     * XMLEventReader methods
     */

    public void close() throws XMLStreamException {
        reader.close();
    }

    public String getElementText() throws XMLStreamException {
        return reader.getElementText();
    }

    public Object getProperty(String name)
    throws IllegalArgumentException {
        return reader.getProperty(name);
    }

    public boolean hasNext() {
        return reader.hasNext();
    }

    public Object next() {
        return reader.next();
    }

    public XMLEvent nextEvent() throws XMLStreamException {
        return reader.nextEvent();
    }

    public XMLEvent nextTag() throws XMLStreamException {
        return reader.nextTag();
    }

    public XMLEvent peek() throws XMLStreamException {
        return reader.peek();
    }

    public void remove() {
        reader.remove();
    }
}