package tests.org.w3c.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method createElementNS creates an element of the given valid
 * qualifiedName and NamespaceURI.
 *
 * Invoke the createElementNS method on this Document object with a valid
 * namespaceURI and qualifiedName. Check if a valid Element object is returned
 * with the same node attributes.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core">http://www.w3.org/TR/DOM-Level-2-Core/core</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-DocCrElNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-DocCrElNS</a>
 */
public final class DocumentCreateElementNS extends DOMTestCase {

    DOMDocumentBuilderFactory factory;

    DocumentBuilder builder;

    protected void setUp() throws Exception {
        super.setUp();
        try {
            factory = new DOMDocumentBuilderFactory(DOMDocumentBuilderFactory
                    .getConfiguration1());
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
    public void testCreateElementNS1() throws Throwable {
        Document doc;
        Element element;
        String namespaceURI = "http://www.w3.org/DOM/Test/level2";
        String qualifiedName = "XML:XML";
        String nodeName;
        String nsURI;
        String localName;
        String prefix;
        String tagName;
        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS(namespaceURI, qualifiedName);
        nodeName = element.getNodeName();
        nsURI = element.getNamespaceURI();
        localName = element.getLocalName();
        prefix = element.getPrefix();
        tagName = element.getTagName();
        assertEquals("documentcreateelementNS01_nodeName", "XML:XML", nodeName);
        assertEquals("documentcreateelementNS01_namespaceURI",
                "http://www.w3.org/DOM/Test/level2", nsURI);
        assertEquals("documentcreateelementNS01_localName", "XML", localName);
        assertEquals("documentcreateelementNS01_prefix", "XML", prefix);
        assertEquals("documentcreateelementNS01_tagName", "XML:XML", tagName);
    }
    public void testCreateElementNS2() throws Throwable {
        Document doc;

        String namespaceURI = null;

        String qualifiedName = "^^";
        doc = (Document) load("staffNS", builder);

        {
            boolean success = false;
            try {
                doc.createElementNS(namespaceURI, qualifiedName);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.INVALID_CHARACTER_ERR);
            }
            assertTrue("documentcreateelementNS02", success);
        }
    }
    public void testCreateElementNS5() throws Throwable {
        Document doc;

        String namespaceURI = null;

        String qualifiedName = "null:xml";
        doc = (Document) load("staffNS", builder);

        {
            boolean success = false;
            try {
                doc.createElementNS(namespaceURI, qualifiedName);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("documentcreateelementNS05", success);
        }
    }
    public void testCreateElementNS6() throws Throwable {
        Document doc;
        Document newDoc;
        DocumentType docType = null;

        DOMImplementation domImpl;

        String namespaceURI = "http://www.w3.org/xml/1998/namespace ";
        String qualifiedName = "xml:root";
        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();
        newDoc = domImpl.createDocument("http://www.w3.org/DOM/Test",
                "dom:doc", docType);

        {
            boolean success = false;
            try {
                newDoc.createElementNS(namespaceURI, qualifiedName);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("documentcreateelementNS06", success);
        }
    }
}
