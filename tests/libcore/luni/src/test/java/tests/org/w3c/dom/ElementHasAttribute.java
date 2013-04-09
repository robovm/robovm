package tests.org.w3c.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Attr;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method hasAttribute returns true when an attribute with a given name is
 * specified on this element or has a default value, false otherwise Invoke the
 * hasAttribute method to check if the documentElement has attributres.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeHasAttrs">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeHasAttrs</a>
 */
public final class ElementHasAttribute extends DOMTestCase {

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
    public void testHasAttribute1() throws Throwable {
        Document doc;
        Element element;
        boolean state;
        doc = (Document) load("staff", builder);
        element = doc.getDocumentElement();
        state = element.hasAttribute("");
        assertFalse("elementhasattribute01", state);
    }

// Assumes validation.
//    public void testHasAttribute2() throws Throwable {
//        Document doc;
//        Element element;
//        boolean state;
//        NodeList elementList;
//        doc = (Document) load("staffNS", builder);
//        elementList = doc.getElementsByTagName("emp:employee");
//        element = (Element) elementList.item(0);
//        assertNotNull("empEmployeeNotNull", element);
//        state = element.hasAttribute("defaultAttr");
//        assertTrue("elementhasattribute02", state);
//    }
    public void testHasAttribute3() throws Throwable {
        Document doc;
        Element element;
        boolean state;
        Attr attribute;

        doc = (Document) load("staff", builder);
        element = doc.createElement("address");
        attribute = doc.createAttribute("domestic");
        state = element.hasAttribute("domestic");
        assertFalse("elementhasattribute03_False", state);
        element.setAttributeNode(attribute);
        state = element.hasAttribute("domestic");
        assertTrue("elementhasattribute03_True", state);
    }
    public void testHasAttribute4() throws Throwable {
        Document doc;
        Element element;
        boolean state;
        Attr attribute;

        doc = (Document) load("staff", builder);
        element = doc.createElement("address");
        attribute = doc.createAttribute("domestic");
        element.setAttributeNode(attribute);
        state = element.hasAttribute("domestic");
        assertTrue("elementhasattribute04", state);
    }
}
