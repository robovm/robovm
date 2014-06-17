/*
 * Copyright (C) 2011 The Android Open Source Project
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

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import junit.framework.TestCase;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class DomSerializationTest extends TestCase {
    private DocumentBuilder documentBuilder;
    private Transformer transformer;

    @Override protected void setUp() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = builderFactory.newDocumentBuilder();
        transformer = TransformerFactory.newInstance().newTransformer();
    }

    public void testWriteDocument() throws Exception {
        Document document = documentBuilder.newDocument();
        Element foo = document.createElement("foo");
        Attr quux = document.createAttribute("quux");
        quux.setValue("abc");
        foo.setAttributeNode(quux);
        foo.appendChild(document.createElement("bar"));
        foo.appendChild(document.createElement("baz"));
        document.appendChild(foo);
        assertXmlEquals("<foo quux=\"abc\"><bar/><baz/></foo>", document);
    }

    public void testWriteSpecialCharactersInText() throws Exception {
        Document document = documentBuilder.newDocument();
        Element foo = document.createElement("foo");
        foo.appendChild(document.createTextNode("5'8\", 5 < 6 & 7 > 3!"));
        document.appendChild(foo);
        assertXmlEquals("<foo>5'8\", 5 &lt; 6 &amp; 7 &gt; 3!</foo>", document);
    }

    private String toXml(Document document) throws Exception {
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    private void assertXmlEquals(String expectedXml, Document document) throws Exception {
        String actual = toXml(document);
        String declarationA = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String declarationB = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        assertTrue(actual, actual.equals(declarationA + expectedXml)
                || actual.equals(declarationB + expectedXml));
    }
}
