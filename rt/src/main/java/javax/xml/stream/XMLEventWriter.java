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

// $Id: XMLEventWriter.java 669794 2008-06-20 05:13:36Z mrglavas $

package javax.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventConsumer;

public interface XMLEventWriter extends XMLEventConsumer {
    public void add(XMLEvent event) throws XMLStreamException;

    public void add(XMLEventReader reader) throws XMLStreamException;

    public void close() throws XMLStreamException;

    public void flush() throws XMLStreamException;

    public NamespaceContext getNamespaceContext();

    public String getPrefix(String uri) throws XMLStreamException;

    public void setDefaultNamespace(String uri) throws XMLStreamException;

    public void setNamespaceContext(NamespaceContext context) throws XMLStreamException;

    public void setPrefix(String prefix, String uri) throws XMLStreamException;
}
