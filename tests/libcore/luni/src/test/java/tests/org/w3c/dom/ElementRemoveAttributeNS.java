package tests.org.w3c.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method removeAttributeNS removes an attribute by local name and namespace
 * URI. Create a new element and add a new attribute node to it. Remove the
 * attribute node using the removeAttributeNodeNS method. Check if the attribute
 * was remove by invoking the hasAttributeNS method on the element and check if
 * it returns false.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElRemAtNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElRemAtNS</a>
 */
public final class ElementRemoveAttributeNS extends DOMTestCase {

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
    public void testRemoveAttributeNS() throws Throwable {
        Document doc;
        Element element;
        boolean state;
        Attr attribute;

        doc = (Document) load("staff", builder);
        element = doc.createElementNS("http://www.w3.org/DOM", "elem");
        attribute = doc.createAttributeNS(
                "http://www.w3.org/DOM/Test/createAttributeNS", "attr");
        element.setAttributeNodeNS(attribute);
        element.removeAttributeNS(
                "http://www.w3.org/DOM/Test/createAttributeNS", "attr");
        state = element.hasAttributeNS(
                "http://www.w3.org/DOM/Test/createAttributeNS", "attr");
        assertFalse("elementremoveattributens01", state);
    }

}
