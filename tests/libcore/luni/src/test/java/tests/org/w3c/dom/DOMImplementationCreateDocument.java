package tests.org.w3c.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMException;

import javax.xml.parsers.DocumentBuilder;

/**
 * The createDocument method with valid arguments, should create a DOM Document
 * of the specified type.
 *
 * Call the createDocument on this DOMImplementation with createDocument
 * ("http://www.w3.org/DOMTest/L2",see the array below for valid QNames,null).
 * Check if the returned Document object is is empty with no Document Element.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#Level-2-Core-DOM-createDocument">http://www.w3.org/TR/DOM-Level-2-Core/core#Level-2-Core-DOM-createDocument</a>
 */
public final class DOMImplementationCreateDocument extends DOMTestCase {

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
    public void testCreateDocument3() throws Throwable {
        Document doc;
        DOMImplementation domImpl;
        Document newDoc;
        DocumentType docType = null;

        String namespaceURI = "http://www.w3.org/DOMTest/L2";
        String qualifiedName;
        List<String> qualifiedNames = new ArrayList<String>();
        qualifiedNames.add("_:_");
        qualifiedNames.add("_:h0");
        qualifiedNames.add("_:test");
        qualifiedNames.add("l_:_");
        qualifiedNames.add("ns:_0");
        qualifiedNames.add("ns:a0");
        qualifiedNames.add("ns0:test");
        qualifiedNames.add("a.b:c");
        qualifiedNames.add("a-b:c");
        qualifiedNames.add("a-b:c");

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();
        for (int indexN1006B = 0; indexN1006B < qualifiedNames.size(); indexN1006B++) {
            qualifiedName = (String) qualifiedNames.get(indexN1006B);
            newDoc = domImpl.createDocument(namespaceURI, qualifiedName,
                    docType);
            assertNotNull("domimplementationcreatedocument03", newDoc);
        }
    }
    public void testCreateDocument4() throws Throwable {
        Document doc;
        DOMImplementation domImpl;

        String namespaceURI = null;

        String qualifiedName = "dom:root";
        DocumentType docType = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();

        {
            boolean success = false;
            try {
                domImpl.createDocument(namespaceURI, qualifiedName, docType);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("domimplementationcreatedocument04", success);
        }
    }
    public void testCreateDocument5() throws Throwable {
        Document doc;
        DOMImplementation domImpl;

        String namespaceURI = "http://www.w3.org/xml/1998/namespace";
        String qualifiedName = "xml:root";
        DocumentType docType = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();

        {
            boolean success = false;
            try {
                domImpl.createDocument(namespaceURI, qualifiedName, docType);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("domimplementationcreatedocument05", success);
        }
    }
    public void testCreateDocument7() throws Throwable {
        Document doc;
        DOMImplementation domImpl;

        String namespaceURI = "http://www.w3.org/DOMTest/level2";
        DocumentType docType = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();

        {
            boolean success = false;
            try {
                domImpl.createDocument(namespaceURI, ":", docType);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("domimplementationcreatedocument07", success);
        }
    }
}
