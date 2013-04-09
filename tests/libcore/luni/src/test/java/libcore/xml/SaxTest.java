/*
 * Copyright (C) 2010 The Android Open Source Project
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

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Initiate and observe a SAX parse session.
 */
public final class SaxTest extends TestCase {

    public void testNoPrefixesNoNamespaces() throws Exception {
        parse(false, false, "<foo bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("", localName);
                assertEquals("foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertOneOf("bar", "", attributes.getLocalName(0));
                assertEquals("bar", attributes.getQName(0));
            }
        });

        parse(false, false, "<a:foo a:bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("", localName);
                assertEquals("a:foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertOneOf("a:bar", "", attributes.getLocalName(0));
                assertEquals("a:bar", attributes.getQName(0));
            }
        });
    }

    public void testNoPrefixesYesNamespaces() throws Exception {
        parse(false, true, "<foo bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("foo", localName);
                assertEquals("foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertEquals("bar", attributes.getLocalName(0));
                assertEquals("bar", attributes.getQName(0));
            }
        });

        parse(false, true, "<a:foo a:bar=\"baz\" xmlns:a=\"http://quux\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("http://quux", uri);
                assertEquals("foo", localName);
                assertEquals("a:foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("http://quux", attributes.getURI(0));
                assertEquals("bar", attributes.getLocalName(0));
                assertEquals("a:bar", attributes.getQName(0));
            }
        });
    }

    /**
     * Android's Expat-based SAX parser fails this test because Expat doesn't
     * supply us with our much desired {@code xmlns="http://..."} attributes.
     */
    public void testYesPrefixesYesNamespaces() throws Exception {
        parse(true, true, "<foo bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("foo", localName);
                assertEquals("foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertEquals("bar", attributes.getLocalName(0));
                assertEquals("bar", attributes.getQName(0));
            }
        });

        parse(true, true, "<a:foo a:bar=\"baz\" xmlns:a=\"http://quux\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("http://quux", uri);
                assertEquals("foo", localName);
                assertEquals("a:foo", qName);
                assertEquals(2, attributes.getLength());
                assertEquals("http://quux", attributes.getURI(0));
                assertEquals("bar", attributes.getLocalName(0));
                assertEquals("a:bar", attributes.getQName(0));
                assertEquals("", attributes.getURI(1));
                assertEquals("", attributes.getLocalName(1));
                assertEquals("xmlns:a", attributes.getQName(1));
            }
        });
    }

    public void testYesPrefixesNoNamespaces() throws Exception {
        parse(true, false, "<foo bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("", localName);
                assertEquals("foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertOneOf("bar", "", attributes.getLocalName(0));
                assertEquals("bar", attributes.getQName(0));
            }
        });

        parse(true, false, "<a:foo a:bar=\"baz\"/>", new DefaultHandler() {
            @Override public void startElement(String uri, String localName,
                    String qName, Attributes attributes) {
                assertEquals("", uri);
                assertEquals("", localName);
                assertEquals("a:foo", qName);
                assertEquals(1, attributes.getLength());
                assertEquals("", attributes.getURI(0));
                assertOneOf("a:bar", "", attributes.getLocalName(0));
                assertEquals("a:bar", attributes.getQName(0));
            }
        });
    }

    /**
     * Test that the external-general-entities feature can be disabled.
     * http://code.google.com/p/android/issues/detail?id=9493
     */
    public void testDisableExternalGeneralEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY bar SYSTEM \"/no-such-document.xml\">"
                + "]>"
                + "<foo>&bar;</foo>";
        testDisableExternalEntities("http://xml.org/sax/features/external-general-entities", xml);
    }

    /**
     * Test that the external-parameter-entities feature can be disabled.
     * http://code.google.com/p/android/issues/detail?id=9493
     */
    public void testDisableExternalParameterEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY % bar SYSTEM \"/no-such-document.xml\">"
                + "  %bar;"
                + "]>"
                + "<foo/>";
        testDisableExternalEntities("http://xml.org/sax/features/external-parameter-entities", xml);
    }

    /**
     * Disables the named feature and then parses the supplied XML. The content
     * is expected to be equivalent to "<foo/>".
     */
    private void testDisableExternalEntities(String feature, String xml) throws Exception {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        XMLReader reader = parser.getXMLReader();
        reader.setFeature(feature, false);
        assertFalse(reader.getFeature(feature));
        reader.setContentHandler(new ThrowingHandler() {
            @Override public void startElement(
                    String uri, String localName, String qName, Attributes attributes) {
                assertEquals("foo", qName);
            }
            @Override public void endElement(String uri, String localName, String qName) {
                assertEquals("foo", qName);
            }
        });
        reader.parse(new InputSource(new StringReader(xml)));
    }

    private void parse(boolean prefixes, boolean namespaces, String xml,
            ContentHandler handler) throws Exception {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        XMLReader reader = parser.getXMLReader();
        reader.setFeature("http://xml.org/sax/features/namespace-prefixes", prefixes);
        reader.setFeature("http://xml.org/sax/features/namespaces", namespaces);
        reader.setContentHandler(handler);
        reader.parse(new InputSource(new StringReader(xml)));
    }

    /**
     * @param expected an optional value that may or may have not been supplied
     * @param sentinel a marker value that means the expected value was omitted
     */
    private void assertOneOf(String expected, String sentinel, String actual) {
        List<String> optionsList = Arrays.asList(sentinel, expected);
        assertTrue("Expected one of " + optionsList + " but was " + actual,
                optionsList.contains(actual));
    }

    /**
     * This SAX handler throws on everything but startDocument, endDocument,
     * and setDocumentLocator(). Override the methods that are expected to be
     * called.
     */
    static class ThrowingHandler extends DefaultHandler {
        @Override public InputSource resolveEntity(String publicId, String systemId) {
            throw new AssertionFailedError();
        }
        @Override public void notationDecl(String name, String publicId, String systemId) {
            throw new AssertionFailedError();
        }
        @Override public void unparsedEntityDecl(
                String name, String publicId, String systemId, String notationName) {
            throw new AssertionFailedError();
        }
        @Override public void startPrefixMapping(String prefix, String uri) {
            throw new AssertionFailedError();
        }
        @Override public void endPrefixMapping(String prefix) {
            throw new AssertionFailedError();
        }
        @Override public void startElement(
                String uri, String localName, String qName, Attributes attributes) {
            throw new AssertionFailedError();
        }
        @Override public void endElement(String uri, String localName, String qName) {
            throw new AssertionFailedError();
        }
        @Override public void characters(char[] ch, int start, int length) {
            throw new AssertionFailedError();
        }
        @Override public void ignorableWhitespace(char[] ch, int start, int length) {
            throw new AssertionFailedError();
        }
        @Override public void processingInstruction(String target, String data) {
            throw new AssertionFailedError();
        }
        @Override public void skippedEntity(String name) {
            throw new AssertionFailedError();
        }
        @Override public void warning(SAXParseException e) {
            throw new AssertionFailedError();
        }
        @Override public void error(SAXParseException e) {
            throw new AssertionFailedError();
        }
        @Override public void fatalError(SAXParseException e) {
            throw new AssertionFailedError();
        }
    }
}
