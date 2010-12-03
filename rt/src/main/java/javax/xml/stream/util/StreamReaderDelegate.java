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

// $Id: StreamReaderDelegate.java 670273 2008-06-21 23:31:31Z mrglavas $

package javax.xml.stream.util;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StreamReaderDelegate implements XMLStreamReader {
    
    private XMLStreamReader reader;

    public StreamReaderDelegate() {}

    public StreamReaderDelegate(XMLStreamReader reader) {
        this.reader = reader;
    }

    public void setParent(XMLStreamReader reader) {
        this.reader = reader;
    }

    public XMLStreamReader getParent() {
        return reader;
    }
    
    /*
     * XMLStreamReader methods
     */

    public int next() throws XMLStreamException {
        return reader.next();
    }

    public int nextTag() throws XMLStreamException {
        return reader.nextTag();
    }

    public String getElementText() throws XMLStreamException {
        return reader.getElementText();
    }

    public void require(int type, String namespaceURI, String localName)
    throws XMLStreamException {
        reader.require(type, namespaceURI, localName);
    }

    public boolean hasNext() throws XMLStreamException {
        return reader.hasNext();
    }

    public void close() throws XMLStreamException {
        reader.close();
    }

    public String getNamespaceURI(String prefix) {
        return reader.getNamespaceURI(prefix);
    }

    public NamespaceContext getNamespaceContext() {
        return reader.getNamespaceContext();
    }

    public boolean isStartElement() {
        return reader.isStartElement();
    }

    public boolean isEndElement() {
        return reader.isEndElement();
    }

    public boolean isCharacters() {
        return reader.isCharacters();
    }

    public boolean isWhiteSpace() {
        return reader.isWhiteSpace();
    }

    public String getAttributeValue(String namespaceURI, String localName) {
        return reader.getAttributeValue(namespaceURI, localName);
    }

    public int getAttributeCount() {
        return reader.getAttributeCount();
    }

    public QName getAttributeName(int index) {
        return reader.getAttributeName(index);
    }

    public String getAttributePrefix(int index) {
        return reader.getAttributePrefix(index);
    }

    public String getAttributeNamespace(int index) {
        return reader.getAttributeNamespace(index);
    }

    public String getAttributeLocalName(int index) {
        return reader.getAttributeLocalName(index);
    }

    public String getAttributeType(int index) {
        return reader.getAttributeType(index);
    }

    public String getAttributeValue(int index) {
        return reader.getAttributeValue(index);
    }

    public boolean isAttributeSpecified(int index) {
        return reader.isAttributeSpecified(index);
    }

    public int getNamespaceCount() {
        return reader.getNamespaceCount();
    }

    public String getNamespacePrefix(int index) {
        return reader.getNamespacePrefix(index);
    }

    public String getNamespaceURI(int index) {
        return reader.getNamespaceURI(index);
    }

    public int getEventType() {
        return reader.getEventType();
    }

    public String getText() {
        return reader.getText();
    }

    public int getTextCharacters(int sourceStart, char[] target,
            int targetStart, int length) throws XMLStreamException {
        return reader.getTextCharacters(sourceStart, target, targetStart,
                length);
    }

    public char[] getTextCharacters() {
        return reader.getTextCharacters();
    }

    public int getTextStart() {
        return reader.getTextStart();
    }

    public int getTextLength() {
        return reader.getTextLength();
    }

    public String getEncoding() {
        return reader.getEncoding();
    }

    public boolean hasText() {
        return reader.hasText();
    }

    public Location getLocation() {
        return reader.getLocation();
    }

    public QName getName() {
        return reader.getName();
    }

    public String getLocalName() {
        return reader.getLocalName();
    }

    public boolean hasName() {
        return reader.hasName();
    }

    public String getNamespaceURI() {
        return reader.getNamespaceURI();
    }

    public String getPrefix() {
        return reader.getPrefix();
    }

    public String getVersion() {
        return reader.getVersion();
    }

    public boolean isStandalone() {
        return reader.isStandalone();
    }

    public boolean standaloneSet() {
        return reader.standaloneSet();
    }

    public String getCharacterEncodingScheme() {
        return reader.getCharacterEncodingScheme();
    }

    public String getPITarget() {
        return reader.getPITarget();
    }

    public String getPIData() {
        return reader.getPIData();
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return reader.getProperty(name);
    }
}
