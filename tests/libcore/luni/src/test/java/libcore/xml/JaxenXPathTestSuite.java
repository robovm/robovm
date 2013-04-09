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

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathVariableResolver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation-independent part of the <a
 * href="http://jaxen.codehaus.org/">Jaxen</a> XPath test suite, adapted for use
 * by JUnit. To run these tests on a device:
 * <ul>
 *   <li>Obtain the Jaxen source from the project's website.
 *   <li>Copy the files to a device: <code>adb shell mkdir /data/jaxen ;
 *       adb push /home/dalvik-prebuild/jaxen /data/jaxen</code>
 *   <li>Invoke this class' main method, passing the on-device path to the test
 *       suite's root directory as an argument.
 * </ul>
 */
public class JaxenXPathTestSuite {

    private static final String DEFAULT_JAXEN_HOME = "/home/dalvik-prebuild/jaxen";

    public static Test suite() throws Exception {
        String jaxenHome = System.getProperty("jaxen.home", DEFAULT_JAXEN_HOME);
        return suite(new File(jaxenHome));
    }

    /**
     * Creates a test suite from the Jaxen tests.xml catalog.
     */
    public static Test suite(File jaxenHome)
            throws ParserConfigurationException, IOException, SAXException {

        /*
         * The tests.xml document has this structure:
         *
         * <tests>
         *   <document url="...">
         *     <context .../>
         *     <context .../>
         *     <context .../>
         *   </document>
         *   <document url="...">
         *     <context .../>
         *   </document>
         * </tests>
         */

        File testsXml = new File(jaxenHome + "/xml/test/tests.xml");
        Element tests = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(testsXml).getDocumentElement();

        TestSuite result = new TestSuite();
        for (Element document : elementsOf(tests.getElementsByTagName("document"))) {
            String url = document.getAttribute("url");
            InputSource inputSource = new InputSource("file:" + jaxenHome + "/" + url);
            for (final Element context : elementsOf(document.getElementsByTagName("context"))) {
                contextToTestSuite(result, url, inputSource, context);
            }
        }

        return result;
    }

    /**
     * Populates the test suite with tests from the given XML context element.
     */
    private static void contextToTestSuite(TestSuite suite, String url,
            InputSource inputSource, Element element) {

        /*
         * Each context element has this structure:
         *
         * <context select="...">
         *   <test .../>
         *   <test .../>
         *   <test .../>
         *   <valueOf .../>
         *   <valueOf .../>
         *   <valueOf .../>
         * </context>
         */

        String select = element.getAttribute("select");
        Context context = new Context(inputSource, url, select);

        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setXPathVariableResolver(new ElementVariableResolver(element));

        for (Element test : elementsOf(element.getChildNodes())) {
            if (test.getTagName().equals("test")) {
                suite.addTest(createFromTest(xpath, context, test));

            } else if (test.getTagName().equals("valueOf")) {
                suite.addTest(createFromValueOf(xpath, context, test));

            } else {
                throw new UnsupportedOperationException("Unsupported test: " + context);
            }
        }
    }

    /**
     * Returns the test described by the given {@code <test>} element. Such
     * tests come in one of three varieties:
     *
     * <ul>
     *   <li>Expected failures.
     *   <li>String matches. These tests have a nested {@code <valueOf>} element
     *       that sub-selects an expected text.
     *   <li>Count matches. These tests specify how many nodes are expected to
     *       match.
     * </ul>
     */
    private static TestCase createFromTest(
            final XPath xpath, final Context context, final Element element) {
        final String select = element.getAttribute("select");

        /* Such as <test exception="true" select="..." count="0"/> */
        if (element.getAttribute("exception").equals("true")) {
            return new XPathTest(context, select) {
                @Override void test(Node contextNode) {
                    try {
                        xpath.evaluate(select, contextNode);
                        fail("Expected exception!");
                    } catch (XPathExpressionException expected) {
                    }
                }
            };
        }

        /* a <test> with a nested <valueOf>, both of which have select attributes */
        NodeList valueOfElements = element.getElementsByTagName("valueOf");
        if (valueOfElements.getLength() == 1) {
            final Element valueOf = (Element) valueOfElements.item(0);
            final String valueOfSelect = valueOf.getAttribute("select");

            return new XPathTest(context, select) {
                @Override void test(Node contextNode) throws XPathExpressionException {
                    Node newContext = (Node) xpath.evaluate(
                            select, contextNode, XPathConstants.NODE);
                    assertEquals(valueOf.getTextContent(),
                            xpath.evaluate(valueOfSelect, newContext, XPathConstants.STRING));
                }
            };
        }

        /* Such as <test select="..." count="5"/> */
        final String count = element.getAttribute("count");
        if (count.length() > 0) {
            return new XPathTest(context, select) {
                @Override void test(Node contextNode) throws XPathExpressionException {
                    NodeList result = (NodeList) xpath.evaluate(
                            select, contextNode, XPathConstants.NODESET);
                    assertEquals(Integer.parseInt(count), result.getLength());
                }
            };
        }

        throw new UnsupportedOperationException("Unsupported test: " + context);
    }

    /**
     * Returns the test described by the given {@code <valueOf>} element. These
     * tests select an expected text.
     */
    private static TestCase createFromValueOf(
            final XPath xpath, final Context context, final Element element) {
        final String select = element.getAttribute("select");
        return new XPathTest(context, select) {
            @Override void test(Node contextNode) throws XPathExpressionException {
                assertEquals(element.getTextContent(),
                        xpath.evaluate(select, contextNode, XPathConstants.STRING));
            }
        };
    }

    /**
     * The subject of an XPath query. This is itself defined by an XPath query,
     * so each test requires at least XPath expressions to be evaluated.
     */
    static class Context {
        private final InputSource inputSource;
        private final String url;
        private final String select;

        Context(InputSource inputSource, String url, String select) {
            this.inputSource = inputSource;
            this.url = url;
            this.select = select;
        }

        Node getNode() {
            XPath xpath = XPathFactory.newInstance().newXPath();
            try {
                return (Node) xpath.evaluate(select, inputSource, XPathConstants.NODE);
            } catch (XPathExpressionException e) {
                Error error = new AssertionFailedError("Failed to get context");
                error.initCause(e);
                throw error;
            }
        }

        @Override public String toString() {
            return url + " " + select;
        }
    }

    /**
     * This test evaluates an XPath expression against a context node and
     * compares the result to a known expectation.
     */
    public abstract static class XPathTest extends TestCase {
        private final Context context;
        private final String select;

        public XPathTest(Context context, String select) {
            super("test");
            this.context = context;
            this.select = select;
        }

        abstract void test(Node contextNode) throws XPathExpressionException;

        public final void test() throws XPathExpressionException {
            try {
                test(context.getNode());
            } catch (XPathExpressionException e) {
                if (isMissingFunction(e)) {
                    fail(e.getCause().getMessage());
                } else {
                    throw e;
                }
            }
        }

        private boolean isMissingFunction(XPathExpressionException e) {
            return e.getCause() != null
                    && e.getCause().getMessage().startsWith("Could not find function");
        }

        @Override public String getName() {
            return context + " " + select;
        }
    }

    /**
     * Performs XPath variable resolution by using {@code var:name="value"}
     * attributes from the given element.
     */
    private static class ElementVariableResolver implements XPathVariableResolver {
        private final Element element;
        public ElementVariableResolver(Element element) {
            this.element = element;
        }
        public Object resolveVariable(QName variableName) {
            return element.getAttribute("var:" + variableName.getLocalPart());
        }
    }

    private static List<Element> elementsOf(NodeList nodeList) {
        List<Element> result = new ArrayList<Element>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                result.add((Element) node);
            }
        }
        return result;
    }
}
