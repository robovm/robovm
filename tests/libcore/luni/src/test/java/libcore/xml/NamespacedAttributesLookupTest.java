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

import junit.framework.TestCase;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests that we both report and retrieve attributes using the appropriate
 * names for different combinations of namespaces and namespace prefixes.
 */
public class NamespacedAttributesLookupTest extends TestCase {

    private static final String SAX_PROPERTY_NS =
            "http://xml.org/sax/features/namespaces";
    private static final String SAX_PROPERTY_NS_PREFIXES =
            "http://xml.org/sax/features/namespace-prefixes";

    private static String xml = "<?xml version='1.0' encoding='UTF-8'?>" +
            "<test xmlns='http://foo' xmlns:bar='http://bar' xmlns:baz='http://baz' baz:c='a'>" +
            "<b c='w' bar:c='x'/>" +
            "<bar:e baz:c='y' bar:c='z'/>" +
            "</test>";

    public void testNamespace() throws Exception {
        List<String> expected = Arrays.asList(
                "http://foo,test\n" +
                "  http://baz,c\n" +
                "  http://bar+c=null,\n" +
                "  bar:c=null\n",

                "http://foo,b\n" +
                "  ,c\n" +
                "  http://bar,c\n" +
                "  http://bar+c=x,\n" +
                "  bar:c=x\n",

                "http://bar,e\n" +
                "  http://baz,c\n" +
                "  http://bar,c\n" +
                "  http://bar+c=z,\n" +
                "  bar:c=z\n");

        boolean namespace = true;
        boolean namespacePrefixes = false;
        assertEquals(expected, getStartElements(xml, namespace, namespacePrefixes));
    }

    public void testNamespacePrefixes() throws Exception {
        List<String> expected = Arrays.asList(
                "test\n" +
                "  xmlns\n" +
                "  xmlns:bar\n" +
                "  xmlns:baz\n" +
                "  baz:c\n" +
                "  http://bar+c=null,\n" +
                "  bar:c=null\n",

                "b\n" +
                "  c\n" +
                "  bar:c\n" +
                "  http://bar+c=null,\n" +
                "  bar:c=x\n",

                "bar:e\n" +
                "  baz:c\n" +
                "  bar:c\n" +
                "  http://bar+c=null,\n" +
                "  bar:c=z\n");

        boolean namespace = false;
        boolean namespacePrefixes = true;
        assertEquals(expected, getStartElements(xml, namespace, namespacePrefixes));
    }

    public List<String> getStartElements(String xml, final boolean namespace, boolean namespacePrefixes)
            throws Exception {
        final List<String> result = new ArrayList<String>();
        XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        reader.setFeature(SAX_PROPERTY_NS, namespace);
        reader.setFeature(SAX_PROPERTY_NS_PREFIXES, namespacePrefixes);
        reader.setContentHandler(new DefaultHandler() {
            @Override public final void startElement(
                    String uri, String localName, String qName, Attributes attributes) {
                StringBuilder serialized = new StringBuilder();
                /*
                 * Only supply the uri+localName or qname depending on whether namespaces are
                 * enabled. It's an optional parameter and the RI only supplies one or the other.
                 */
                if (namespace) {
                    serialized.append(uri).append(",");
                    serialized.append(localName);
                } else {
                    serialized.append(qName);
                }
                for (int i = 0; i < attributes.getLength(); i++) {
                    serialized.append("\n  ");
                    if (namespace) {
                        serialized.append(attributes.getURI(i)).append(",");
                        serialized.append(attributes.getLocalName(i));
                    } else {
                        serialized.append(attributes.getQName(i));
                    }
                }
                serialized.append("\n  http://bar+c=")
                        .append(attributes.getValue("http://bar", "c")).append(",")
                        .append("\n  bar:c=")
                        .append(attributes.getValue("bar:c"))
                        .append("\n");
                result.add(serialized.toString());
            }
        });
        reader.parse(new InputSource(new StringReader(xml)));
        return result;
    }
}
