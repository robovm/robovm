/*
 * Copyright (C) 2009 The Android Open Source Project
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tests.support.resource.Support_Resources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NodeTest extends TestCase {

    /**
     * For bug 779: Node#getNextSibling throws IndexOutOfBoundsException.
     */
    public void test_getNextSibling() throws Exception {
        // Calling getNextSibling when there is no next sibling should return null.
        // From http://code.google.com/p/android/issues/detail?id=779.
        ByteArrayInputStream bis = new ByteArrayInputStream("<root/>".getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bis);
        Node root = document.getDocumentElement();
        assertNull(root.getNextSibling());
    }

    public void testGetBaseUri() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = Support_Resources.resourceToTempFile("/simple.xml");
        Document document = builder.parse(file);

        assertFileUriEquals(file, document.getBaseURI());

        Element documentElement = document.getDocumentElement();
        for (Node node : flattenSubtree(documentElement)) {
            if (node.getNodeType() == Node.ELEMENT_NODE
                    || node.getNodeType() == Node.DOCUMENT_NODE) {
                assertFileUriEquals(file, node.getBaseURI());
            } else {
                assertNull("Unexpected base URI for " + node, node.getBaseURI());
            }
        }

        // TODO: test other node types
        // TODO: test resolution of relative paths
        // TODO: test URI sanitization
    }

    private void assertFileUriEquals(File expectedFile, String actual) {
        assertTrue("Expected URI for: " + expectedFile + " but was " + actual + ". ",
                actual.equals("file:" + expectedFile)
                        || actual.equals("file://" + expectedFile));
    }

    private List<Node> flattenSubtree(Node subtree) {
        List<Node> result = new ArrayList<Node>();
        traverse(subtree, result);
        return result;
    }

    private void traverse(Node node, List<Node> sink) {
        sink.add(node);

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            traverse(children.item(i), sink);
        }
    }
}
