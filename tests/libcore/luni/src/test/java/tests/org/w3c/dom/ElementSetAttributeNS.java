package tests.org.w3c.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;

/**
 * The method setAttributeNS adds a new attribute. Create a new element and add
 * a new attribute node to it using the setAttributeNS method. Check if the
 * attribute was correctly set by invoking the getAttributeNodeNS method and
 * checking the nodeName and nodeValue of the returned nodes.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElSetAttrNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-ElSetAttrNS</a>
 */
public final class ElementSetAttributeNS extends DOMTestCase {

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
    public void testSetAttributeNS1() throws Throwable {
        Document doc;
        Element element;
        Attr attribute;
        String attrName;
        String attrValue;
        doc = (Document) load("staff", builder);
        element = doc.createElementNS("http://www.w3.org/DOM", "dom:elem");
        element.setAttributeNS("http://www.w3.org/DOM/Test/setAttributeNS",
                "attr", "value");
        attribute = element.getAttributeNodeNS(
                "http://www.w3.org/DOM/Test/setAttributeNS", "attr");
        attrName = attribute.getNodeName();
        attrValue = attribute.getNodeValue();
        assertEquals("elementsetattributens01_attrName", "attr", attrName);
        assertEquals("elementsetattributens01_attrValue", "value", attrValue);
    }
    public void testSetAttributeNS2() throws Throwable {
        Document doc;
        Element element;
        Attr attribute;
        NodeList elementList;
        String attrName;
        String attrValue;
        doc = (Document) load("staff", builder);
        elementList = doc.getElementsByTagNameNS("*", "address");
        element = (Element) elementList.item(0);
        element.setAttributeNS("http://www.w3.org/DOM/Test/setAttributeNS",
                "this:street", "Silver Street");
        attribute = element.getAttributeNodeNS(
                "http://www.w3.org/DOM/Test/setAttributeNS", "street");
        attrName = attribute.getNodeName();
        attrValue = attribute.getNodeValue();
        assertEquals("elementsetattributens02_attrName", "this:street",
                attrName);
        assertEquals("elementsetattributens02_attrValue", "Silver Street",
                attrValue);
    }
    public void testSetAttributeNS3() throws Throwable {
        Document doc;
        Element element;
        Attr attribute;
        NodeList elementList;
        String attrName;
        String attrValue;
        doc = (Document) load("staffNS", builder);
        elementList = doc.getElementsByTagName("emp:employee");
        element = (Element) elementList.item(0);
        assertNotNull("empEmployeeNotNull", element);
        element.setAttributeNS("http://www.w3.org/DOM/Test/1", "defaultAttr",
                "default1");
        element.setAttributeNS("http://www.w3.org/DOM/Test/2", "defaultAttr",
                "default2");
        attribute = element.getAttributeNodeNS("http://www.w3.org/DOM/Test/1",
                "defaultAttr");
        attrName = attribute.getNodeName();
        attrValue = attribute.getNodeValue();
        assertEquals("elementsetattributens03_attrName", "defaultAttr",
                attrName);
        assertEquals("elementsetattributens03_attrValue", "default1", attrValue);
    }
    public void testSetAttributeNS4() throws Throwable {
        Document doc;
        Element element;
        String qualifiedName;
        List<String> qualifiedNames = new ArrayList<String>();
        qualifiedNames.add("/");
        qualifiedNames.add("//");
        qualifiedNames.add("\\");
        qualifiedNames.add(";");
        qualifiedNames.add("&");
        qualifiedNames.add("*");
        qualifiedNames.add("]]");
        qualifiedNames.add(">");
        qualifiedNames.add("<");

        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("http://www.w3.org/DOM/Test/L2",
                "dom:elem");
        for (int indexN10058 = 0; indexN10058 < qualifiedNames.size(); indexN10058++) {
            qualifiedName = (String) qualifiedNames.get(indexN10058);

            {
                boolean success = false;
                try {
                    element.setAttributeNS("http://www.w3.org/DOM/Test/L2",
                            qualifiedName, "test");
                } catch (DOMException ex) {
                    success = (ex.code == DOMException.INVALID_CHARACTER_ERR);
                }
                assertTrue("elementsetattributens04", success);
            }
        }
    }
    public void testSetAttributeNS5() throws Throwable {
        Document doc;
        Element element;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("http://www.w3.org/DOM/Test/L2",
                "dom:elem");

        {
            boolean success = false;
            try {
                element.setAttributeNS(nullNS, "dom:root", "test");
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("elementsetattributens05", success);
        }
    }
    public void testSetAttributeNS8() throws Throwable {
        Document doc;
        Element element;
        doc = (Document) load("staffNS", builder);
        element = doc.createElementNS("http://www.w3.org/DOMTest/level2",
                "dom:elem");

        {
            boolean success = false;
            try {
                element.setAttributeNS("http://www.w3.org/DOMTest/level2",
                        "xmlns", "test");
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("elementsetattributens08_Err1", success);
        }

        {
            boolean success = false;
            try {
                element.setAttributeNS("http://www.w3.org/DOMTest/level2",
                        "xmlns:root", "test");
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
            }
            assertTrue("elementsetattributens08_Err2", success);
        }
    }
    public void testSetAttributeNSURINull() throws Throwable {
          String namespaceURI = null;

          String qualifiedName = "emp:qualifiedName";
          Document doc;
          NodeList elementList;
          Node testAddr;
          doc = (Document) load("staff", builder);
          elementList = doc.getElementsByTagName("employee");
          testAddr = elementList.item(0);

          {
             boolean success = false;
             try {
                ((Element) /*Node */testAddr).setAttributeNS(namespaceURI, qualifiedName, "newValue");
              } catch (DOMException ex) {
                success = (ex.code == DOMException.NAMESPACE_ERR);
             }
             assertTrue("throw_NAMESPACE_ERR", success);
          }
    }
}
