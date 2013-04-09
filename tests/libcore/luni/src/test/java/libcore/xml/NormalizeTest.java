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
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests the acceptance of various parameters on the DOM configuration. This
 * test assumes the same set of parameters as the RI version 1.5. Perfectly
 * correct DOM implementations may fail this test because it assumes certain
 * parameters will be unsupported.
 */
public class NormalizeTest extends TestCase {

    private Document document;
    private DOMConfiguration domConfiguration;

    String[] infosetImpliesFalse = {
            "validate-if-schema", "entities", "datatype-normalization", "cdata-sections" };
    String[] infosetImpliesTrue = { "namespace-declarations", "well-formed",
            "element-content-whitespace", "comments", "namespaces" };

    @Override protected void setUp() throws Exception {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        domConfiguration = document.getDomConfig();
    }

    public void testCanonicalForm() {
        assertEquals(false, domConfiguration.getParameter("canonical-form"));
        assertSupported("canonical-form", false);
        assertUnsupported("canonical-form", true);
    }

    public void testCdataSections() {
        assertEquals(true, domConfiguration.getParameter("cdata-sections"));
        assertSupported("cdata-sections", false);
        assertSupported("cdata-sections", true);
    }

    public void testCheckCharacterNormalization() {
        assertEquals(false, domConfiguration.getParameter("check-character-normalization"));
        assertSupported("check-character-normalization", false);
        assertUnsupported("check-character-normalization", true);
    }

    public void testComments() {
        assertEquals(true, domConfiguration.getParameter("comments"));
        assertSupported("comments", false);
        assertSupported("comments", true);
    }

    public void testDatatypeNormalization() {
        assertEquals(false, domConfiguration.getParameter("datatype-normalization"));
        assertSupported("datatype-normalization", false);
        assertSupported("datatype-normalization", true);

        // setting this parameter to true should set validate to true...
        domConfiguration.setParameter("validate", false);
        domConfiguration.setParameter("datatype-normalization", true);
        assertEquals(true, domConfiguration.getParameter("validate"));

        // ...but the negative case isn't so
        domConfiguration.setParameter("datatype-normalization", false);
        assertEquals(true, domConfiguration.getParameter("validate"));
    }

    public void testElementContentWhitespace() {
        assertEquals(true, domConfiguration.getParameter("element-content-whitespace"));
        assertUnsupported("element-content-whitespace", false);
        assertSupported("element-content-whitespace", true);
    }

    public void testEntities() {
        assertEquals(true, domConfiguration.getParameter("entities"));
        assertSupported("entities", false);
        assertSupported("entities", true);
    }

    public void testErrorHandler() {
        assertEquals(null, domConfiguration.getParameter("error-handler"));
        assertSupported("error-handler", null);
        assertSupported("error-handler", new DOMErrorHandler() {
            public boolean handleError(DOMError error) {
                return true;
            }
        });
    }

    public void testInfoset() {
        assertEquals(false, domConfiguration.getParameter("infoset"));
        assertSupported("infoset", false);
        assertSupported("infoset", true);
    }

    public void testSettingInfosetUpdatesImplied() {
        // first clear those other parameters
        for (String name : infosetImpliesFalse) {
            if (domConfiguration.canSetParameter(name, true)) {
                domConfiguration.setParameter(name, true);
            }
        }
        for (String name : infosetImpliesTrue) {
            if (domConfiguration.canSetParameter(name, false)) {
                domConfiguration.setParameter(name, false);
            }
        }

        // set infoset
        domConfiguration.setParameter("infoset", true);

        // now the parameters should all match what infoset implies
        for (String name : infosetImpliesFalse) {
            assertEquals(false, domConfiguration.getParameter(name));
        }
        for (String name : infosetImpliesTrue) {
            assertEquals(true, domConfiguration.getParameter(name));
        }
    }

    public void testSettingImpliedUpdatesInfoset() {
        for (String name : infosetImpliesFalse) {
            domConfiguration.setParameter("infoset", true);
            if (domConfiguration.canSetParameter(name, true)) {
                domConfiguration.setParameter(name, true);
                assertEquals(false, domConfiguration.getParameter("infoset"));
            }
        }

        for (String name : infosetImpliesTrue) {
            domConfiguration.setParameter("infoset", true);
            if (domConfiguration.canSetParameter(name, false)) {
                domConfiguration.setParameter(name, false);
                assertEquals(false, domConfiguration.getParameter("infoset"));
            }
        }
    }

    public void testNamespaces() {
        assertEquals(true, domConfiguration.getParameter("namespaces"));
        assertSupported("namespaces", false);
        assertSupported("namespaces", true);
    }

    public void testNamespaceDeclarations() {
        assertEquals(true, domConfiguration.getParameter("namespace-declarations"));
        assertUnsupported("namespace-declarations", false); // supported in RI 6
        assertSupported("namespace-declarations", true);
    }

    public void testNormalizeCharacters() {
        assertEquals(false, domConfiguration.getParameter("normalize-characters"));
        assertSupported("normalize-characters", false);
        assertUnsupported("normalize-characters", true);
    }

    public void testSchemaLocation() {
        assertEquals(null, domConfiguration.getParameter("schema-location"));
        assertSupported("schema-location", "http://foo");
        assertSupported("schema-location", null);
    }

    /**
     * This fails under the RI because setParameter() succeeds even though
     * canSetParameter() returns false.
     */
    public void testSchemaTypeDtd() {
        assertUnsupported("schema-type", "http://www.w3.org/TR/REC-xml"); // supported in RI v6
    }

    public void testSchemaTypeXmlSchema() {
        assertEquals(null, domConfiguration.getParameter("schema-type"));
        assertSupported("schema-type", null);
        assertSupported("schema-type", "http://www.w3.org/2001/XMLSchema");
    }

    public void testSplitCdataSections() {
        assertEquals(true, domConfiguration.getParameter("split-cdata-sections"));
        assertSupported("split-cdata-sections", false);
        assertSupported("split-cdata-sections", true);
    }

    public void testValidate() {
        assertEquals(false, domConfiguration.getParameter("validate"));
        assertSupported("validate", false);
        assertSupported("validate", true);
    }

    public void testValidateIfSchema() {
        assertEquals(false, domConfiguration.getParameter("validate-if-schema"));
        assertSupported("validate-if-schema", false);
        assertUnsupported("validate-if-schema", true);
    }

    public void testWellFormed() {
        assertEquals(true, domConfiguration.getParameter("well-formed"));
        assertSupported("well-formed", false);
        assertSupported("well-formed", true);
    }

    public void testMissingParameter() {
        assertFalse(domConfiguration.canSetParameter("foo", true));
        try {
            domConfiguration.getParameter("foo");
            fail();
        } catch (DOMException e) {
        }
        try {
            domConfiguration.setParameter("foo", true);
            fail();
        } catch (DOMException e) {
        }
    }

    public void testNullKey() {
        try {
            domConfiguration.canSetParameter(null, true);
            fail();
        } catch (NullPointerException e) {
        }
        try {
            domConfiguration.getParameter(null);
            fail();
        } catch (NullPointerException e) {
        }
        try {
            domConfiguration.setParameter(null, true);
            fail();
        } catch (NullPointerException e) {
        }
    }

    public void testNullValue() {
        String message = "This implementation's canSetParameter() disagrees"
                + " with its setParameter()";
        try {
            domConfiguration.setParameter("well-formed", null);
            fail(message);
        } catch (DOMException e) {
        }
        assertEquals(message, false, domConfiguration.canSetParameter("well-formed", null));
    }

    public void testTypeMismatch() {
        assertEquals(false, domConfiguration.canSetParameter("well-formed", "true"));
        try {
            domConfiguration.setParameter("well-formed", "true");
            fail();
        } catch (DOMException e) {
        }

        assertEquals(false, domConfiguration.canSetParameter("well-formed", new Object()));
        try {
            domConfiguration.setParameter("well-formed", new Object());
            fail();
        } catch (DOMException e) {
        }
    }

    private void assertUnsupported(String name, Object value) {
        String message = "This implementation's setParameter() supports an unexpected value: "
                + name + "=" + value;
        assertFalse(message, domConfiguration.canSetParameter(name, value));
        try {
            domConfiguration.setParameter(name, value);
            fail(message);
        } catch (DOMException e) {
            assertEquals(DOMException.NOT_SUPPORTED_ERR, e.code);
        }
        try {
            domConfiguration.setParameter(name.toUpperCase(), value);
            fail(message);
        } catch (DOMException e) {
            assertEquals(DOMException.NOT_SUPPORTED_ERR, e.code);
        }
        assertFalse(value.equals(domConfiguration.getParameter(name)));
    }

    private void assertSupported(String name, Object value) {
        String message = "This implementation's canSetParameter() disagrees"
                + " with its setParameter() for " + name + "=" + value;
        try {
            domConfiguration.setParameter(name, value);
        } catch (DOMException e) {
            if (domConfiguration.canSetParameter(name, value)) {
                fail(message);
            } else {
                fail("This implementation's setParameter() doesn't support: "
                        + name + "=" + value);
            }
        }
        assertTrue(message, domConfiguration.canSetParameter(name.toUpperCase(), value));
        assertTrue(message, domConfiguration.canSetParameter(name, value));
        assertEquals(value, domConfiguration.getParameter(name));
        domConfiguration.setParameter(name.toUpperCase(), value);
        assertEquals(value, domConfiguration.getParameter(name.toUpperCase()));
    }

    public void testCdataSectionsNotHonoredByNodeNormalize() throws Exception {
        String xml = "<foo>ABC<![CDATA[DEF]]>GHI</foo>";
        parse(xml);
        domConfiguration.setParameter("cdata-sections", true);
        document.getDocumentElement().normalize();
        assertEquals(xml, domToString(document));

        parse(xml);
        domConfiguration.setParameter("cdata-sections", false);
        document.getDocumentElement().normalize();
        assertEquals(xml, domToString(document));
    }

    public void testCdataSectionsHonoredByDocumentNormalize() throws Exception {
        String xml = "<foo>ABC<![CDATA[DEF]]>GHI</foo>";
        parse(xml);
        domConfiguration.setParameter("cdata-sections", true);
        document.normalizeDocument();
        assertEquals(xml, domToString(document));

        parse(xml);
        domConfiguration.setParameter("cdata-sections", false);
        document.normalizeDocument();
        String expected = xml.replace("<![CDATA[DEF]]>", "DEF");
        assertEquals(expected, domToString(document));
    }

    public void testMergeAdjacentTextNodes() throws Exception {
        document = createDocumentWithAdjacentTexts("abc", "def");
        document.getDocumentElement().normalize();
        assertChildren(document.getDocumentElement(), "abcdef");
    }

    public void testMergeAdjacentEmptyTextNodes() throws Exception {
        document = createDocumentWithAdjacentTexts("", "", "");
        document.getDocumentElement().normalize();
        assertChildren(document.getDocumentElement());
    }

    public void testMergeAdjacentNodesWithNonTextSiblings() throws Exception {
        document = createDocumentWithAdjacentTexts("abc", "def", "<br>", "ghi", "jkl");
        document.getDocumentElement().normalize();
        assertChildren(document.getDocumentElement(), "abcdef", "<br>", "ghijkl");
    }

    public void testMergeAdjacentNodesEliminatesEmptyTexts() throws Exception {
        document = createDocumentWithAdjacentTexts("", "", "<br>", "", "", "<br>", "", "<br>", "");
        document.getDocumentElement().normalize();
        assertChildren(document.getDocumentElement(), "<br>", "<br>", "<br>");
    }

    public void testRetainingComments() throws Exception {
        String xml = "<foo>ABC<!-- bar -->DEF<!-- baz -->GHI</foo>";
        parse(xml);
        domConfiguration.setParameter("comments", true);
        document.normalizeDocument();
        assertEquals(xml, domToString(document));
    }

    public void testCommentContainingDoubleDash() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        root.appendChild(document.createComment("ABC -- DEF"));
        document.normalizeDocument();
        errorRecorder.assertAllErrors(DOMError.SEVERITY_ERROR, "wf-invalid-character");
    }

    public void testStrippingComments() throws Exception {
        String xml = "<foo>ABC<!-- bar -->DEF<!-- baz -->GHI</foo>";
        parse(xml);
        domConfiguration.setParameter("comments", false);
        document.normalizeDocument();
        assertChildren(document.getDocumentElement(), "ABCDEFGHI");
    }

    public void testSplittingCdataSectionsSplit() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("split-cdata-sections", true);
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        root.appendChild(document.createCDATASection("ABC]]>DEF]]>GHI"));
        document.normalizeDocument();
        errorRecorder.assertAllErrors(DOMError.SEVERITY_WARNING, "cdata-sections-splitted");
        assertChildren(root, "<![CDATA[ABC]]]]>", "<![CDATA[>DEF]]]]>", "<![CDATA[>GHI]]>");
    }

    public void testSplittingCdataSectionsReportError() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("split-cdata-sections", false);
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        root.appendChild(document.createCDATASection("ABC]]>DEF"));
        document.normalizeDocument();
        errorRecorder.assertAllErrors(DOMError.SEVERITY_ERROR, "wf-invalid-character");
    }

    public void testInvalidCharactersCdata() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("cdata-sections", true);
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        CDATASection cdata = document.createCDATASection("");
        root.appendChild(cdata);

        for (int c = 0; c <= Character.MAX_VALUE; c++) {
            cdata.setData(new String(new char[]{ 'A', 'B', (char) c }));
            document.normalizeDocument();
            if (isValid((char) c)) {
                assertEquals(Collections.<DOMError>emptyList(), errorRecorder.errors);
            } else {
                errorRecorder.assertAllErrors("For character " + c,
                        DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }

    public void testInvalidCharactersText() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        Text text = document.createTextNode("");
        root.appendChild(text);

        for (int c = 0; c <= Character.MAX_VALUE; c++) {
            text.setData(new String(new char[]{ 'A', 'B', (char) c }));
            document.normalizeDocument();
            if (isValid((char) c)) {
                assertEquals(Collections.<DOMError>emptyList(), errorRecorder.errors);
            } else {
                errorRecorder.assertAllErrors("For character " + c,
                        DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }

    public void testInvalidCharactersAttribute() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);

        for (int c = 0; c <= Character.MAX_VALUE; c++) {
            root.setAttribute("bar", new String(new char[] { 'A', 'B', (char) c}));
            document.normalizeDocument();
            if (isValid((char) c)) {
                assertEquals(Collections.<DOMError>emptyList(), errorRecorder.errors);
            } else {
                errorRecorder.assertAllErrors("For character " + c,
                        DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }

    public void testInvalidCharactersComment() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        Comment comment = document.createComment("");
        root.appendChild(comment);

        for (int c = 0; c <= Character.MAX_VALUE; c++) {
            comment.setData(new String(new char[] { 'A', 'B', (char) c}));
            document.normalizeDocument();
            if (isValid((char) c)) {
                assertEquals(Collections.<DOMError>emptyList(), errorRecorder.errors);
            } else {
                errorRecorder.assertAllErrors("For character " + c,
                        DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }

    public void testInvalidCharactersProcessingInstructionData() throws Exception {
        ErrorRecorder errorRecorder = new ErrorRecorder();
        domConfiguration.setParameter("error-handler", errorRecorder);
        domConfiguration.setParameter("namespaces", false);
        Element root = document.createElement("foo");
        document.appendChild(root);
        ProcessingInstruction pi = document.createProcessingInstruction("foo", "");
        root.appendChild(pi);

        for (int c = 0; c <= Character.MAX_VALUE; c++) {
            pi.setData(new String(new char[] { 'A', 'B', (char) c}));
            document.normalizeDocument();
            if (isValid((char) c)) {
                assertEquals(Collections.<DOMError>emptyList(), errorRecorder.errors);
            } else {
                errorRecorder.assertAllErrors("For character " + c,
                        DOMError.SEVERITY_ERROR, "wf-invalid-character");
            }
        }
    }

    // TODO: test for surrogates

    private boolean isValid(char c) {
        // as defined by http://www.w3.org/TR/REC-xml/#charsets.
        return c == 0x9 || c == 0xA || c == 0xD || (c >= 0x20 && c <= 0xd7ff)
                || (c >= 0xe000 && c <= 0xfffd);
    }

    private Document createDocumentWithAdjacentTexts(String... texts) throws Exception {
        Document result = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();
        Element root = result.createElement("foo");
        result.appendChild(root);
        for (String text : texts) {
            if (text.equals("<br>")) {
                root.appendChild(result.createElement("br"));
            } else {
                root.appendChild(result.createTextNode(text));
            }
        }
        return result;
    }

    private void assertChildren(Element element, String... texts) {
        List<String> actual = new ArrayList<String>();
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                actual.add(((Text) node).getData());
            } else if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
                actual.add("<![CDATA[" + ((CDATASection) node).getData() + "]]>");
            } else {
                actual.add("<" + node.getNodeName() + ">");
            }
        }
        assertEquals(Arrays.asList(texts), actual);
    }

    private void parse(String xml) throws Exception {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new StringReader(xml)));
        domConfiguration = document.getDomConfig();
    }

    private String domToString(Document document) throws TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance() .newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.toString();
    }

    private class ErrorRecorder implements DOMErrorHandler {
        private final List<DOMError> errors = new ArrayList<DOMError>();

        public boolean handleError(DOMError error) {
            errors.add(error);
            return true;
        }

        public void assertAllErrors(int severity, String type) {
            assertAllErrors("Expected one or more " + type + " errors", severity, type);
        }

        public void assertAllErrors(String message, int severity, String type) {
            assertFalse(message, errors.isEmpty());
            for (DOMError error : errors) {
                assertEquals(message, severity, error.getSeverity());
                assertEquals(message, type, error.getType());
            }
            errors.clear();
        }
    }
}
