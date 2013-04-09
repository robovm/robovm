package tests.org.w3c.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method getElementById returns the element whose ID is given by elementId.
 * If not such element exists, returns null.
 *
 * Invoke the getElementById method on this Document object with an invalid
 * elementId. This should return a null element.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core">http://www.w3.org/TR/DOM-Level-2-Core/core</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBId">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBId</a>
 */
public final class DocumentGeteEementById extends DOMTestCase {

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
    public void testGetElementById() throws Throwable {
        Document doc;
        Element element;
        String elementId = "---";
        doc = (Document) load("staffNS", builder);
        element = doc.getElementById(elementId);
        assertNull("documentgetelementbyid01", element);
    }

}
