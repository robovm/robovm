package tests.org.w3c.dom;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * An attempt to add an element to the named node map returned by entities
 * should result in a NO_MODIFICATION_ERR or HIERARCHY_REQUEST_ERR.
 *
 * @author Curt Arnold
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-1788794630">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-1788794630</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS</a>
 */
public final class HCEntitiesSetNamedItemNS extends DOMTestCase {

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
// Assumes validation.
//    public void testSetNamedItemNS() throws Throwable {
//        Document doc;
//        NamedNodeMap entities;
//        DocumentType docType;
//
//        Element elem;
//        doc = (Document) load("hc_staff", builder);
//        docType = doc.getDoctype();
//
//        if (!(("text/html".equals(getContentType())))) {
//            assertNotNull("docTypeNotNull", docType);
//            entities = docType.getEntities();
//            assertNotNull("entitiesNotNull", entities);
//            elem = doc.createElementNS("http://www.w3.org/1999/xhtml", "br");
//
//            try {
//                entities.setNamedItemNS(elem);
//                fail("throw_HIER_OR_NO_MOD_ERR");
//
//            } catch (DOMException ex) {
//                switch (ex.code) {
//                case 3:
//                    break;
//                case 7:
//                    break;
//                default:
//                    throw ex;
//                }
//            }
//        }
//    }

}
