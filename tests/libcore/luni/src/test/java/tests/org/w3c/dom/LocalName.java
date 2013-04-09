package tests.org.w3c.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

import javax.xml.parsers.DocumentBuilder;

/**
 * The "getLocalName()" method for a Node returns the local part of the
 * qualified name of this node, and for nodes of any type other than
 * ELEMENT_NODE and ATTRIBUTE_NODE and nodes created with a DOM Level 1 method,
 * this is null.
 *
 * Retrieve the first emp:address node and get the attributes of this node."
 * Then apply the getLocalName() method to the emp:domestic attribute. The
 * method should return "domestic".
 *
 * @author NIST
 * @author Mary Brady
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeNSLocalN">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeNSLocalN</a>
 */
public final class LocalName extends DOMTestCase {

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
    public void testGetLocalName1() throws Throwable {
        Document doc;
        NodeList elementList;
        Element testAddr;
        Attr addrAttr;
        String localName;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("emp:address");
        testAddr = (Element) elementList.item(0);
        assertNotNull("empAddrNotNull", testAddr);
        addrAttr = testAddr.getAttributeNode("emp:domestic");
        localName = addrAttr.getLocalName();
        assertEquals("localName", "domestic", localName);
    }
    public void testGetLocalName2() throws Throwable {
        Document doc;
        Node createdNode;
        String localName;
        doc = (Document) load("staffNS", builder);
        createdNode = doc.createElement("test:employee");
        localName = createdNode.getLocalName();
        assertNull("localNameNull", localName);
    }
    public void testGetLocalName3() throws Throwable {
        Document doc;
        NodeList elementList;
        Node testEmployee;
        Node textNode;
        String localName;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("employeeId");
        testEmployee = elementList.item(0);
        textNode = testEmployee.getFirstChild();
        localName = textNode.getLocalName();
        assertNull("textNodeLocalName", localName);
    }
    public void testGetLocalName4() throws Throwable {
        Document doc;
        NodeList elementList;
        Node testEmployee;
        String employeeLocalName;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("employee");
        testEmployee = elementList.item(0);
        employeeLocalName = testEmployee.getLocalName();
        assertEquals("lname", "employee", employeeLocalName);
    }
}
