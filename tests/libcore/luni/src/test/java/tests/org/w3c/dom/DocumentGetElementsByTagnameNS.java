package tests.org.w3c.dom;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method getElementsByTagNameNS returns a NodeList of all the Elements with
 * a given local name and namespace URI in the order in which they are
 * encountered in a preorder traversal of the Document tree.
 *
 * Invoke the getElementsByTagNameNS method on a new Document object with the
 * values of namespaceURI=* and localName=*. This should return a nodeList of 1
 * item.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core">http://www.w3.org/TR/DOM-Level-2-Core/core</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS</a>
 * @see <a
 *      href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=259">http://www.w3.org/Bugs/Public/show_bug.cgi?id=259</a>
 */
public final class DocumentGetElementsByTagnameNS extends DOMTestCase {

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
        Document newDoc;
        DocumentType docType = null;

        DOMImplementation domImpl;
        NodeList childList;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();
        newDoc = domImpl.createDocument(nullNS, "root", docType);
        childList = newDoc.getElementsByTagNameNS("*", "*");
        assertEquals("documentgetelementsbytagnameNS01", 1, childList
                .getLength());
    }
    public void testGetElementsByTagNameNS2() throws Throwable {
        Document doc;
        Element docElem;
        Element element;
        NodeList childList;

        doc = (Document) load("staffNS", builder);
        docElem = doc.getDocumentElement();
        element = doc.createElementNS("test", "employeeId");
        docElem.appendChild(element);
        childList = doc.getElementsByTagNameNS("*", "employeeId");
        assertEquals("documentgetelementsbytagnameNS02", 6, childList
                .getLength());
    }
    public void testGetElementsByTagNameNS3() throws Throwable {
        Document doc;
        NodeList childList;
        doc = (Document) load("staffNS", builder);
        childList = doc.getElementsByTagNameNS("**", "*");
        assertEquals("documentgetelementsbytagnameNS03", 0, childList
                .getLength());
    }
    public void testGetElementsByTagNameNS4() throws Throwable {
        Document doc;
        NodeList childList;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        childList = doc.getElementsByTagNameNS(nullNS, "0");
        assertEquals("documentgetelementsbytagnameNS04", 0, childList
                .getLength());
    }
    public void testGetElementsByTagNameNS5() throws Throwable {
        Document doc;
        NodeList childList;
        doc = (Document) load("staffNS", builder);
        childList = doc.getElementsByTagNameNS("null", "elementId");
        assertEquals("documentgetelementsbytagnameNS05", 0, childList
                .getLength());
    }
}
