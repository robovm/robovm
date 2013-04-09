/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.xml;

import junit.framework.TestCase;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SimpleParserTest extends TestCase {

    private SAXParser parser;

    private StringBuffer instructions;

    private Map<String, String> namespaces1;
    private Map<String, String> namespaces2;

    private StringBuffer elements1;
    private StringBuffer elements2;

    private Map<String, String> attributes1;
    private Map<String, String> attributes2;

    private StringBuffer text;

    @Override
    protected void setUp() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        parser = factory.newSAXParser();
        parser.getXMLReader().setContentHandler(contentHandler);

        instructions = new StringBuffer();
        namespaces1 = new HashMap<String, String>();
        namespaces2 = new HashMap<String, String>();
        elements1 = new StringBuffer();
        elements2 = new StringBuffer();
        attributes1 = new HashMap<String, String>();
        attributes2 = new HashMap<String, String>();
        text = new StringBuffer();
    }

    @Override
    protected void tearDown() throws Exception {
        instructions = null;
        parser = null;
        namespaces1 = null;
        namespaces2 = null;
        attributes1 = null;
        attributes2 = null;
        elements1 = null;
        elements2 = null;
        text = null;
    }

    private final ContentHandler contentHandler = new ContentHandler() {
        public void characters(char[] ch, int start, int length) {
            String s = new String(ch, start, length).trim();
            if (!s.isEmpty()) {
                if (text.length() != 0) {
                    text.append(",");
                }
                text.append(s);
            }
        }

        public void processingInstruction(String target, String data) {
            String s = target + ":" + data;
            if (instructions.length() != 0) {
                instructions.append(",");
            }
            instructions.append(s);
        }


        public void startElement(String uri, String localName, String qName, Attributes atts) {

            if (elements1.length() != 0) {
                elements1.append(",");
            }

            elements1.append(localName);

            if (!"".equals(uri)) {
                namespaces1.put(localName, uri);
            }

            for (int i = 0; i < atts.getLength(); i++) {
                attributes1.put(atts.getLocalName(i), atts.getValue(i));
            }

            if (elements2.length() != 0) {
                elements2.append(",");
            }

            elements2.append(qName);

            if (!"".equals(uri)) {
                namespaces2.put(qName, uri);
            }

            for (int i = 0; i < atts.getLength(); i++) {
                attributes2.put(atts.getQName(i), atts.getValue(i));
            }
        }

        public void endDocument() {}
        public void endElement(String uri, String localName, String qName) {}
        public void endPrefixMapping(String prefix) {}
        public void ignorableWhitespace(char[] ch, int start, int length) {}
        public void setDocumentLocator(Locator locator) {}
        public void skippedEntity(String name) {}
        public void startDocument() {}
        public void startPrefixMapping(String prefix, String uri) {}
    };

    public void testWorkingFile1() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();
        parser.getXMLReader().setContentHandler(contentHandler);

        parser.parse(getClass().getResourceAsStream("/SimpleParserTest.xml"),
                (DefaultHandler) null);

        assertEquals("The:quick,brown:fox", instructions.toString());

        assertEquals("stuff,nestedStuff,nestedStuff,nestedStuff", elements1
                .toString());

        assertEquals("Some text here,some more here...", text.toString());

        assertEquals("eins", attributes1.get("one"));
        assertEquals("zwei", attributes1.get("two"));
        assertEquals("drei", attributes1.get("three"));

        assertEquals("http://www.foobar.org", namespaces1.get("stuff"));
    }
    public void testWorkingFile2() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setValidating(false);
        factory.setNamespaceAware(false);
        factory.setFeature("http://xml.org/sax/features/namespace-prefixes",
                true);

        SAXParser parser = factory.newSAXParser();
        parser.getXMLReader().setContentHandler(contentHandler);
        parser.parse(getClass().getResourceAsStream("/SimpleParserTest.xml"),
                (DefaultHandler) null);

        assertFalse(parser.isNamespaceAware());

        assertEquals("The:quick,brown:fox", instructions.toString());

        assertEquals("t:stuff,nestedStuff,nestedStuff,nestedStuff", elements2
                .toString());

        assertEquals("Some text here,some more here...", text.toString());

        assertEquals("eins", attributes2.get("one"));
        assertEquals("zwei", attributes2.get("two"));
        assertEquals("drei", attributes2.get("three"));

        assertEquals(0, namespaces2.size());
    }
    public void testEntityResolver() throws Exception {
        final StringBuilder text = new StringBuilder();
        DefaultHandler handler = new DefaultHandler() {
            public void characters(char[] ch, int start, int length) {
                String s = new String(ch, start, length).trim();
                if (s.length() != 0) {
                    if (text.length() != 0) {
                        text.append(",");
                    }
                    text.append(s);
                }
            }

            public InputSource resolveEntity(String publicId, String systemId)
                    throws IOException, SAXException {
                return new InputSource(new InputStreamReader(
                        new ByteArrayInputStream("test".getBytes())));
            }
        };

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        parser = spf.newSAXParser();
        parser.parse(this.getClass().getResourceAsStream("/staffEntRes.xml"),
                handler);
        assertTrue(
                "resolved external entity must be in parser character stream",
                text.toString().contains("test"));
    }
    public void testGetValue() throws Exception{
        parser.parse(getClass().getResourceAsStream("/staffNS.xml"),
                new DefaultHandler() {
            boolean firstAddressElem = true;
            @Override
            public void startElement (String uri, String localName,
                    String qName, Attributes attributes) {
                if(firstAddressElem && localName.equals("address")) {
                    firstAddressElem = false;
                    assertNotNull(attributes.getValue("http://www.usa.com",
                            "domestic"));
                }
            }
        });
    }
}
