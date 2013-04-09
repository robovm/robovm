package tests.org.w3c.dom;

import org.w3c.dom.DocumentType;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method getInternalSubset() returns the internal subset as a string.
 *
 * Create a new DocumentType node with null values for publicId and systemId.
 * Verify that its internal subset is null.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-Core-DocType-internalSubset">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-Core-DocType-internalSubset</a>
 * @see <a
 *      href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=259">http://www.w3.org/Bugs/Public/show_bug.cgi?id=259</a>
 */
public final class DocumentTypeInternalSubset extends DOMTestCase {

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
    public void testGetInternalSubset() throws Throwable {
        Document doc;
        DocumentType docType;
        DOMImplementation domImpl;
        String internal;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();
        docType = domImpl.createDocumentType("l2:root", nullNS, nullNS);
        internal = docType.getInternalSubset();
        assertNull("internalSubsetNull", internal);
    }
}
