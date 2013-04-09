package tests.org.w3c.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

/**
 * The "getNamedItemNS(namespaceURI,localName)" method for a NamedNodeMap should
 * return a node specified by localName and namespaceURI
 *
 * Retrieve a list of elements with tag name "address". Access the second
 * element from the list and get its attributes. Try to retrieve the attribute
 * node with local name "domestic" and namespace uri "http://www.usa.com" with
 * method getNamedItemNS(namespaceURI,localName).
 *
 * @author NIST
 * @author Mary Brady
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-F68D095">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-F68D095</a>
 */
public final class GetNamedItemNS extends DOMTestCase {

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
    public void testGetNamedItemNS1() throws Throwable {
        Document doc;
        NodeList elementList;
        Node testEmployee;
        NamedNodeMap attributes;
        Attr domesticAttr;
        String attrName;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("address");
        testEmployee = elementList.item(1);
        attributes = testEmployee.getAttributes();
        domesticAttr = (Attr) attributes.getNamedItemNS("http://www.usa.com",
                "domestic");
        attrName = domesticAttr.getNodeName();
        assertEquals("attrName", "dmstc:domestic", attrName);
    }
    public void testGetNamedItemNS2() throws Throwable {
        String namespaceURI = "http://www.usa.com";
        String localName = "domest";
        Document doc;
        NodeList elementList;
        Node testEmployee;
        NamedNodeMap attributes;
        Attr newAttr;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("address");
        testEmployee = elementList.item(1);
        attributes = testEmployee.getAttributes();
        newAttr = (Attr) attributes.getNamedItemNS(namespaceURI, localName);
        assertNull("throw_Null", newAttr);
    }

// Assumes validation.
//    public void testGetNamedItemNS3() throws Throwable {
//        Document doc;
//        DocumentType docType;
//        NamedNodeMap entities;
//        Entity entity;
//        String nullNS = null;
//
//        doc = (Document) load("staffNS", builder);
//        docType = doc.getDoctype();
//        entities = docType.getEntities();
//        assertNotNull("entitiesNotNull", entities);
//        entity = (Entity) entities.getNamedItemNS(nullNS, "ent1");
//        assertNotNull("entityNull", entity);
//    }

// Assumes validation.
//    public void testGetNamedItemNS4() throws Throwable {
//        Document doc;
//        DocumentType docType;
//        NamedNodeMap notations;
//        Notation notation;
//        String nullNS = null;
//
//        doc = (Document) load("staffNS", builder);
//        docType = doc.getDoctype();
//        notations = docType.getNotations();
//        assertNotNull("notationsNotNull", notations);
//        notation = (Notation) notations.getNamedItemNS(nullNS, "notation1");
//        assertNotNull("notationNull", notation);
//    }
}
