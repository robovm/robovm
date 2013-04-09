package tests.org.w3c.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Attr;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method getAttributeNodeNS retrieves an Attr node by local name and
 * namespace URI. Create a new element node and add 2 new attribute nodes to it
 * that have the same local name but different namespaceURIs and prefixes.
 * Retrieve an attribute using namespace and localname and check its value, name
 * and namespaceURI.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElGetAtNodeNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElGetAtNodeNS</a>
 */
public final class ElementGetAttributeNodeNS extends DOMTestCase {

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
    public void testGetAttributeNodeNS1() throws Throwable {
        Document doc;
        Element element;
        Attr attribute1;
        Attr attribute2;


        Attr attribute;
        String attrValue;
        String attrName;
        String attNodeName;
        String attrLocalName;
        String attrNS;
        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("namespaceURI", "root");
        attribute1 = doc.createAttributeNS("http://www.w3.org/DOM/Level2",
                "l2:att");
        element.setAttributeNodeNS(attribute1);
        attribute2 = doc.createAttributeNS("http://www.w3.org/DOM/Level1",
                "att");
        element.setAttributeNodeNS(attribute2);
        attribute = element.getAttributeNodeNS("http://www.w3.org/DOM/Level2",
                "att");
        attrValue = attribute.getNodeValue();
        attrName = attribute.getName();
        attNodeName = attribute.getNodeName();
        attrLocalName = attribute.getLocalName();
        attrNS = attribute.getNamespaceURI();
        assertEquals("elementgetattributenodens01_attrValue", "", attrValue);
        assertEquals("elementgetattributenodens01_attrName", "l2:att", attrName);
        assertEquals("elementgetattributenodens01_attrNodeName", "l2:att",
                attNodeName);
        assertEquals("elementgetattributenodens01_attrLocalName", "att",
                attrLocalName);
        assertEquals("elementgetattributenodens01_attrNs",
                "http://www.w3.org/DOM/Level2", attrNS);
    }
    public void testGetAttributeNodeNS2() throws Throwable {
        Document doc;
        Element element;
        Attr attribute;

        String attrValue;
        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("namespaceURI", "root");
        attribute = doc.createAttributeNS("http://www.w3.org/DOM/Level2",
                "l2:att");
        element.setAttributeNodeNS(attribute);
        attribute = element.getAttributeNodeNS("http://www.w3.org/DOM/Level2",
                "att");
        attrValue = attribute.getNodeValue();
        assertEquals("elementgetattributenodens02", "", attrValue);
    }

// Assumes validation.
//    public void testGetAttributeNodeNS3() throws Throwable {
//        Document doc;
//        Element element;
//        Attr attribute;
//        String attrValue;
//        NodeList childList;
//        String nullNS = null;
//
//        doc = (Document) load("staffNS", builder);
//        childList = doc.getElementsByTagNameNS("http://www.nist.gov",
//                "employee");
//        element = (Element) childList.item(1);
//        attribute = element.getAttributeNodeNS(nullNS, "defaultAttr");
//        attrValue = attribute.getNodeValue();
//        assertEquals("elementgetattributenodens03", "defaultVal", attrValue);
//    }
}
