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

// $Id: XMLStreamReader.java 669794 2008-06-20 05:13:36Z mrglavas $

package javax.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

public interface XMLStreamReader extends XMLStreamConstants {
    public void close() throws XMLStreamException;

    public int getAttributeCount();

    public String getAttributeLocalName(int index);

    public QName getAttributeName(int index);

    public String getAttributeNamespace(int index);

    public String getAttributePrefix(int index);

    public String getAttributeType(int index);

    public String getAttributeValue(int index);

    public String getAttributeValue(String namespaceURI,
            String localName);

    public String getCharacterEncodingScheme();

    public String getElementText() throws XMLStreamException;

    public String getEncoding();

    public int getEventType();

    public String getLocalName();

    public Location getLocation();

    public QName getName();

    public NamespaceContext getNamespaceContext();

    public int getNamespaceCount();

    public String getNamespacePrefix(int index);

    public String getNamespaceURI();

    public String getNamespaceURI(int index);

    public String getNamespaceURI(String prefix);

    public String getPIData();

    public String getPITarget();

    public String getPrefix();

    public java.lang.Object getProperty(String name) throws IllegalArgumentException;

    public String getText();

    public char[] getTextCharacters();

    public int getTextCharacters(int sourceStart, char[] target, int targetStart,
            int length) throws XMLStreamException;

    public int getTextLength();

    public int getTextStart();

    public String getVersion();

    public boolean hasName();

    public boolean hasNext() throws XMLStreamException;

    public boolean hasText();

    public boolean isAttributeSpecified(int index);

    public boolean isCharacters();

    public boolean isEndElement();

    public boolean isStandalone();

    public boolean isStartElement();

    public boolean isWhiteSpace();

    public int next() throws XMLStreamException;

    public int nextTag() throws XMLStreamException ;

    public void require(int type, String namespaceURI,
            String localName) throws XMLStreamException ;

    public boolean standaloneSet();
}
