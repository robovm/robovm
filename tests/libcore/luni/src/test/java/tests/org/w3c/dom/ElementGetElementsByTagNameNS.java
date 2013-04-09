package tests.org.w3c.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method getElementsByTagNameNS returns a NodeList of all the Elements with
 * a given local name and namespace URI in the order in which they are
 * encountered in a preorder traversal of the Document tree. Invoke
 * getElementsByTagNameNS on the documentElement with values for namespaceURI
 * '*' and localName '*'. Verify if this returns a nodeList of 0 elements.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS</a>
 */
public final class ElementGetElementsByTagNameNS extends DOMTestCase {

    DOMDocumentBuilderFactory factory;

    DocumentBuilder builder;

    protected void setUp() throws Exception {
        super.setUp();
        try {
            factory = new DOMDocumentBuilderFactory(DOMDocumentBuilderFactory
                    .getConfiguration2());
            builder = factory.getBuilder();
        } catch (Exception e) {
            fail("Unexpected exception" + e.getMessage());
        }
    }

    protected void tearDown() throws Exception {
        factory = null;
        builder = null;
        super.tearDown();
    }

    /**
     * Runs the test case.
     *
     * @throws Throwable
     *             Any uncaught exception causes test to fail
     */
    public void testGetElementsByTagNameNS1() throws Throwable {
        Document doc;
        Element element;
        NodeList elementList;
        doc = (Document) load("staffNS", builder);
        element = doc.getDocumentElement();
        elementList = element.getElementsByTagNameNS("**", "*");
        assertEquals("elementgetelementsbytagnamens02", 0, elementList
                .getLength());
    }
    public void testGetElementsByTagNameNS4() throws Throwable {
        Document doc;
        Element element;
        Element child1;
        Element child2;
        Element child3;

        NodeList elementList;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("http://www.w3.org/DOM", "root");
        child1 = doc.createElementNS("http://www.w3.org/DOM/Level1",
                "dom:child");
        child2 = doc.createElementNS(nullNS, "child");
        child3 = doc.createElementNS("http://www.w3.org/DOM/Level2",
                "dom:child");
        element.appendChild(child1);
        element.appendChild(child2);
        element.appendChild(child3);
        elementList = element.getElementsByTagNameNS(nullNS, "child");
        assertEquals("elementgetelementsbytagnamens04_1", 1, elementList
                .getLength());
        elementList = element.getElementsByTagNameNS("*", "child");
        assertEquals("elementgetelementsbytagnamens04_2", 3, elementList
                .getLength());
    }
    public void testGetElementsByTagNameNS5() throws Throwable {
        Document doc;
        Element element;
        NodeList elementList;
        doc = (Document) load("staffNS", builder);
        element = doc.getDocumentElement();
        elementList = element.getElementsByTagNameNS(
                "http://www.altavista.com", "*");
        assertEquals("elementgetelementsbytagnamens05", 1, elementList
                .getLength());
    }
}
