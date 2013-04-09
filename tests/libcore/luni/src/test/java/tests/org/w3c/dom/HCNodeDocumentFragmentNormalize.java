package tests.org.w3c.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;

/**
 * Create a document fragment with two adjacent text nodes, normalize and see if
 * the text nodes were combined.
 *
 * @author Curt Arnold
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-F68D095">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-F68D095</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-B63ED1A3">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-B63ED1A3</a>
 */
public final class HCNodeDocumentFragmentNormalize extends DOMTestCase {

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
    public void testNodeDocumentFragmentNormalize1() throws Throwable {
        Document doc;
        DocumentFragment docFragment;
        String nodeValue;
        Text txtNode;
        Node retval;

        doc = (Document) load("hc_staff", builder);
        docFragment = doc.createDocumentFragment();
        txtNode = doc.createTextNode("foo");
        retval = docFragment.appendChild(txtNode);
        txtNode = doc.createTextNode("bar");
        retval = docFragment.appendChild(txtNode);
        docFragment.normalize();
        txtNode = (Text) docFragment.getFirstChild();
        nodeValue = txtNode.getNodeValue();
        assertEquals("normalizedNodeValue", "foobar", nodeValue);
        retval = txtNode.getNextSibling();
        assertNull("singleChild", retval);
    }
    public void testNodeDocumentFragmentNormalize2() throws Throwable {
        Document doc;
        DocumentFragment docFragment;
        Text txtNode;

        doc = (Document) load("hc_staff", builder);
        docFragment = doc.createDocumentFragment();
        txtNode = doc.createTextNode("");
        docFragment.appendChild(txtNode);
        docFragment.normalize();
        txtNode = (Text) docFragment.getFirstChild();
        assertNull("noChild", txtNode);
    }
}
