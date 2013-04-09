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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import junit.framework.TestCase;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class SimpleBuilderTest extends TestCase {

    private DocumentBuilder builder;

    protected void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        builder = factory.newDocumentBuilder();
    }

    protected void tearDown() throws Exception {
        builder = null;
    }

    private String getTextContent(Node node) {
        String result = (node instanceof Text ? ((Text) node).getData() : "");

        Node child = node.getFirstChild();
        while (child != null) {
            result = result + getTextContent(child);
            child = child.getNextSibling();
        }

        return result;
    }
    public void testGoodFile1() throws Exception {
        Document document = builder.parse(getClass().getResourceAsStream(
                "/SimpleBuilderTest.xml"));

        Element root = document.getDocumentElement();
        assertNotNull(root);
        assertEquals("http://www.foo.bar", root.getNamespaceURI());
        assertEquals("t", root.getPrefix());
        assertEquals("stuff", root.getLocalName());

        NodeList list = root.getElementsByTagName("nestedStuff");
        assertNotNull(list);
        assertEquals(list.getLength(), 4);

        Element one = (Element) list.item(0);
        Element two = (Element) list.item(1);
        Element three = (Element) list.item(2);
        Element four = (Element) list.item(3);

        assertEquals("This space intentionally left blank.",
                getTextContent(one));
        assertEquals("Nothing to see here - please get along!",
                getTextContent(two));
        assertEquals("Rent this space!", getTextContent(three));
        assertEquals("", getTextContent(four));

        assertEquals("eins", one.getAttribute("one"));
        assertEquals("zwei", two.getAttribute("two"));
        assertEquals("drei", three.getAttribute("three"));

        assertEquals("vier", four.getAttribute("t:four"));
        assertEquals("vier", four.getAttributeNS("http://www.foo.bar", "four"));

        list = document.getChildNodes();
        assertNotNull(list);

        String proinst = "";
        String comment = "";

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node instanceof ProcessingInstruction) {
                proinst = proinst + node.getNodeValue();
            } else if (node instanceof Comment) {
                comment = comment + node.getNodeValue();
            }
        }

        assertEquals("The quick brown fox jumps over the lazy dog.", proinst);
        assertEquals(" Fragile!  Handle me with care! ", comment);
    }

    public void testGoodFile2() throws Exception {
        Document document = builder.parse(getClass().getResourceAsStream(
                "/staffNS.xml"));

        Element root = document.getDocumentElement();
        assertNotNull(root);
    }
}
