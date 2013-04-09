package tests.org.w3c.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import javax.xml.parsers.DocumentBuilder;

public final class AttrGetOwnerElement extends DOMTestCase {

    DOMDocumentBuilderFactory factory;

    DocumentBuilder builder;

    protected void setUp() throws Exception {
        super.setUp();
        try {
            factory = new DOMDocumentBuilderFactory(DOMDocumentBuilderFactory
                    .getConfiguration2());
            builder = factory.getBuilder();
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

    protected void tearDown() throws Exception {
        factory = null;
        builder = null;
        super.tearDown();
    }

// Assumes validation.
//    public void testGetOwnerElement1() throws Throwable {
//        Document doc;
//        Attr attr;
//        Element element;
//        Element ownerElement;
//        String ownerElementName;
//        NodeList elementList;
//        NamedNodeMap attributes;
//        String nullNS = null;
//
//        doc = (Document) load("staffNS", builder);
//
//        elementList = doc.getElementsByTagNameNS("http://www.nist.gov",
//                "employee");
//        element = (Element) elementList.item(1);
//        attributes = element.getAttributes();
//        attr = (Attr) attributes.getNamedItemNS(nullNS, "defaultAttr");
//        ownerElement = attr.getOwnerElement();
//        ownerElementName = ownerElement.getNodeName();
//        assertEquals("attrgetownerelement01", "emp:employee", ownerElementName);
//
//    }
    public void testGetOwnerElement2() throws Throwable {
        Document doc;
        Element element;
        Element ownerElement;
        String ownerElementName;
        Attr attr;

        doc = (Document) load("staffNS", builder);
        element = doc.createElement("root");
        attr = doc.createAttributeNS("http://www.w3.org/DOM/L1", "L1:att");
        element.setAttributeNodeNS(attr);
        ownerElement = attr.getOwnerElement();
        ownerElementName = ownerElement.getNodeName();
        assertEquals("attrgetownerelement02", "root", ownerElementName);
    }
    public void testGetOwnerElement3() throws Throwable {
        Document doc;
        Node ownerElement;
        Attr attr;
        doc = (Document) load("staffNS", builder);
        attr = doc.createAttributeNS("http://www.w3.org/DOM", "dom:attr");
        ownerElement = attr.getOwnerElement();
        assertNull("attrgetownerelement03", ownerElement);
    }
    public void testGetOwnerElement4() throws Throwable {
        Document doc;
        Document docImp;
        Node ownerElement;
        Element element;
        Attr attr;
        Attr attrImp;
        NodeList addresses;

        doc = (Document) load("staffNS", builder);
        docImp = (Document) load("staff", builder);

        addresses = doc
                .getElementsByTagNameNS("http://www.nist.gov", "address");
        element = (Element) addresses.item(1);
        assertNotNull("empAddressNotNull", element);
        attr = element.getAttributeNodeNS("http://www.nist.gov", "zone");
        attrImp = (Attr) docImp.importNode(attr, true);
        ownerElement = attrImp.getOwnerElement();
        assertNull("attrgetownerelement04", ownerElement);
    }
    public void testGetOwnerElement5() throws Throwable {
        Document doc;
        Node element;
        Element ownerElement;
        Element parentElement;
        NodeList elementList;
        String ownerElementName;
        Attr attr;

        NamedNodeMap nodeMap;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagNameNS("*", "address");
        element = elementList.item(1);
        parentElement = (Element) element.getParentNode();
        nodeMap = element.getAttributes();
        parentElement.removeChild(element);
        attr = (Attr) nodeMap.getNamedItemNS(nullNS, "street");
        ownerElement = attr.getOwnerElement();
        ownerElementName = ownerElement.getNodeName();
        assertEquals("attrgetownerelement05", "address", ownerElementName);
    }
}
