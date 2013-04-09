package tests.org.w3c.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;

/**
 * The "feature" parameter in the isSupported(feature,version)" method is the
 * name of the feature and the version is the version number of the feature to
 * test. XXX is NOT a legal value for the feature parameter. The method should
 * return "false" since XXX is not a valid feature.
 *
 * Retrieve the root node of the DOM document by invoking the
 * "getDocumentElement()" method. This should create a node object on which the
 * "isSupported(feature,version)" method is invoked with "feature" equal to
 * "XXX" and version to "1.0". The method should return a boolean "false" since
 * XXX is not a valid feature.
 *
 * @author NIST
 * @author Mary Brady
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#Level-2-Core-Node-supports">http://www.w3.org/TR/DOM-Level-2-Core/core#Level-2-Core-Node-supports</a>
 */
public final class IsSupported extends DOMTestCase {

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
    public void testIsSupported1() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("XXX", "1.0");
        assertFalse("throw_False", state);
    }
    public void testIsSupported2() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("XML", "9.0");
        assertFalse("throw_False", state);
    }
    public void testIsSupported4() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("xml", "1.0");
        assertTrue("throw_True", state);
    }
    public void testIsSupported5() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("core", "2.0");
        assertTrue("throw_True", state);
    }
    public void testIsSupported6() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("xml", "2.0");
        assertTrue("throw_True", state);
    }
    public void testIsSupported7() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("XML", "");
        assertTrue("throw_True", state);
    }
    public void testIsSupported9() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("XML", "1.0");
        assertTrue("throw_True", state);
    }
    public void testIsSupported10() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("CORE", "2.0");
        assertTrue("throw_True", state);
    }
    public void testIsSupported11() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("XML", "2.0");
        assertTrue("throw_True", state);
    }

    public void testIsSupported12() throws Throwable {
        List<String> features = new ArrayList<String>();
        features.add("Core");
        features.add("XML");
        features.add("HTML");
        features.add("Views");
        features.add("StyleSheets");
        features.add("CSS");
        features.add("CSS2");
        features.add("Events");
        features.add("UIEvents");
        features.add("MouseEvents");
        features.add("MutationEvents");
        features.add("HTMLEvents");
        features.add("Range");
        features.add("Traversal");
        features.add("bogus.bogus.bogus");

        Document doc;
        Node rootNode;
        String featureElement;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("Core", "2.0");
        assertTrue("Core2", state);
        for (int indexN10078 = 0; indexN10078 < features.size(); indexN10078++) {
            featureElement = (String) features.get(indexN10078);
            state = rootNode.isSupported(featureElement, "1.0");
        }
        for (int indexN10083 = 0; indexN10083 < features.size(); indexN10083++) {
            featureElement = (String) features.get(indexN10083);
            state = rootNode.isSupported(featureElement, "2.0");
        }
    }
    public void testIsSupported13() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("Core", "");
        assertTrue("Core", state);
    }
    public void testIsSupported14() throws Throwable {
        Document doc;
        Node rootNode;
        boolean state;
        String nullString = null;

        doc = (Document) load("staff", builder);
        rootNode = doc.getDocumentElement();
        state = rootNode.isSupported("Core", nullString);
        assertTrue("Core", state);
    }
}
