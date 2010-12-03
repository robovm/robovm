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

// $Id: XMLStreamWriter.java 669794 2008-06-20 05:13:36Z mrglavas $

package javax.xml.stream;

import javax.xml.namespace.NamespaceContext;

public interface XMLStreamWriter {
    public void close() throws XMLStreamException;

    public void flush() throws XMLStreamException;

    public NamespaceContext getNamespaceContext();

    public String getPrefix(String uri) throws XMLStreamException;

    public Object getProperty(String name) throws IllegalArgumentException;

    public void setDefaultNamespace(String uri) throws XMLStreamException;

    public void setNamespaceContext(NamespaceContext context)
    throws XMLStreamException;

    public void setPrefix(String prefix, String uri) throws XMLStreamException;

    public void writeAttribute(String localName, String value)
    throws XMLStreamException;

    public void writeAttribute(String namespaceURI, String localName,
            String value) throws XMLStreamException;

    public void writeAttribute(String prefix, String namespaceURI,
            String localName, String value) throws XMLStreamException;

    public void writeCData(String data) throws XMLStreamException;

    public void writeCharacters(char[] text, int start, int len)
    throws XMLStreamException;

    public void writeCharacters(String text) throws XMLStreamException;

    public void writeComment(String data) throws XMLStreamException;

    public void writeDefaultNamespace(String namespaceURI)
    throws XMLStreamException;

    public void writeDTD(String dtd) throws XMLStreamException;

    public void writeEmptyElement(String localName) throws XMLStreamException;

    public void writeEmptyElement(String namespaceURI, String localName)
    throws XMLStreamException;

    public void writeEmptyElement(String prefix, String localName,
            String namespaceURI) throws XMLStreamException;

    public void writeEndDocument() throws XMLStreamException;

    public void writeEndElement() throws XMLStreamException;

    public void writeEntityRef(String name) throws XMLStreamException;

    public void writeNamespace(String prefix, String namespaceURI)
    throws XMLStreamException;

    public void writeProcessingInstruction(String target)
    throws XMLStreamException;

    public void writeProcessingInstruction(String target, String data)
    throws XMLStreamException;

    public void writeStartDocument() throws XMLStreamException;

    public void writeStartDocument(String version) throws XMLStreamException;

    public void writeStartDocument(String encoding, String version)
    throws XMLStreamException;

    public void writeStartElement(String localName) throws XMLStreamException;

    public void writeStartElement(String namespaceURI, String localName)
    throws XMLStreamException;

    public void writeStartElement(String prefix, String localName,
            String namespaceURI) throws XMLStreamException;
}
