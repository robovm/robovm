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

// $Id: XMLInputFactory.java 670283 2008-06-22 01:04:09Z mrglavas $

package javax.xml.stream;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.stream.util.XMLEventAllocator;

public abstract class XMLInputFactory {

    public static final String ALLOCATOR = "javax.xml.stream.allocator";
    public static final String IS_COALESCING = "javax.xml.stream.isCoalescing";
    public static final String IS_NAMESPACE_AWARE = "javax.xml.stream.isNamespaceAware";
    public static final String IS_REPLACING_ENTITY_REFERENCES = "javax.xml.stream.isReplacingEntityReferences";
    public static final String IS_SUPPORTING_EXTERNAL_ENTITIES = "javax.xml.stream.isSupportingExternalEntities";
    public static final String IS_VALIDATING = "javax.xml.stream.isValidating";
    public static final String REPORTER = "javax.xml.stream.reporter";
    public static final String RESOLVER = "javax.xml.stream.resolver";
    public static final String SUPPORT_DTD = "javax.xml.stream.supportDTD";

    private static final String PROPERTY_NAME = "javax.xml.stream.XMLInputFactory";
    private static final String DEFAULT_FACTORY = "com.ctc.wstx.stax.WstxInputFactory";

    protected XMLInputFactory() {}

    public static XMLInputFactory newInstance()
        throws FactoryConfigurationError {
        try {
            return (XMLInputFactory) FactoryFinder.find(PROPERTY_NAME, DEFAULT_FACTORY);
        }
        catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }

    public static XMLInputFactory newInstance(String factoryId,
            ClassLoader classLoader) throws FactoryConfigurationError {
        if (classLoader == null) {
            classLoader = SecuritySupport.getContextClassLoader();
        }
        try {
            return (XMLInputFactory) FactoryFinder.find(factoryId, classLoader, DEFAULT_FACTORY);
        }
        catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }

    public abstract XMLStreamReader createXMLStreamReader(Reader reader)
    throws XMLStreamException;

    public abstract XMLStreamReader createXMLStreamReader(
            javax.xml.transform.Source source) throws XMLStreamException;

    public abstract XMLStreamReader createXMLStreamReader(
            InputStream stream) throws XMLStreamException;

    public abstract XMLStreamReader createXMLStreamReader(
            InputStream stream, String encoding)
    throws XMLStreamException;

    public abstract XMLStreamReader createXMLStreamReader(
            String systemId, InputStream stream)
    throws XMLStreamException;

    public abstract XMLStreamReader createXMLStreamReader(
            String systemId, Reader reader)
    throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(Reader reader)
    throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(
            String systemId, Reader reader)
    throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(XMLStreamReader reader)
    throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(
            javax.xml.transform.Source source) throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(
            InputStream stream) throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(
            InputStream stream, String encoding)
    throws XMLStreamException;

    public abstract XMLEventReader createXMLEventReader(
            String systemId, InputStream stream)
    throws XMLStreamException;

    public abstract XMLStreamReader createFilteredReader(
            XMLStreamReader reader, StreamFilter filter)
    throws XMLStreamException;

    public abstract XMLEventReader createFilteredReader(XMLEventReader reader,
            EventFilter filter) throws XMLStreamException;

    public abstract XMLResolver getXMLResolver();

    public abstract void setXMLResolver(XMLResolver resolver);

    public abstract XMLReporter getXMLReporter();

    public abstract void setXMLReporter(XMLReporter reporter);

    public abstract void setProperty(String name,
            Object value) throws IllegalArgumentException;

    public abstract Object getProperty(String name)
    throws IllegalArgumentException;

    public abstract boolean isPropertySupported(String name);

    public abstract void setEventAllocator(XMLEventAllocator allocator);

    public abstract XMLEventAllocator getEventAllocator();
}
