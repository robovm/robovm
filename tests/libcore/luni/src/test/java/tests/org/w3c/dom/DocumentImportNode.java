package tests.org.w3c.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.parsers.DocumentBuilder;

/**
 * The importNode method imports a node from another document to this document.
 * The returned node has no parent; (parentNode is null). The source node is not
 * altered or removed from the original document but a new copy of the source
 * node is created.
 *
 * Using the method importNode with deep=true, import the attribute, "street" of
 * the second element node, from a list of nodes whose local names are "address"
 * and namespaceURI "http://www.nist.gov" into the same document. Check the
 * parentNode, nodeName, nodeType and nodeValue of the imported node to verify
 * if it has been imported correctly.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core">http://www.w3.org/TR/DOM-Level-2-Core/core</a>
 * @see <a
 *      href="http://www.w3.org/TR/DOM-Level-2-Core/core#Core-Document-importNode">http://www.w3.org/TR/DOM-Level-2-Core/core#Core-Document-importNode</a>
 */
public final class DocumentImportNode extends DOMTestCase {

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
// Assumes validation.
//    public void testImportNode1() throws Throwable {
//        Document doc;
//        Element element;
//        Attr attr;
//        NodeList childList;
//        Node importedAttr;
//        String nodeName;
//        int nodeType;
//        String nodeValue;
//        doc = (Document) load("staffNS", builder);
//        childList = doc
//                .getElementsByTagNameNS("http://www.nist.gov", "address");
//        element = (Element) childList.item(1);
//        attr = element.getAttributeNode("street");
//        importedAttr = doc.importNode(attr, false);
//        nodeName = importedAttr.getNodeName();
//        nodeValue = importedAttr.getNodeValue();
//        nodeType = (int) importedAttr.getNodeType();
//        assertEquals("documentimportnode01_nodeName", "street", nodeName);
//        assertEquals("documentimportnode01_nodeType", 2, nodeType);
//        assertEquals("documentimportnode01_nodeValue", "Yes", nodeValue);
//    }
    public void testImportNode2() throws Throwable {
        Document doc;
        Document docImported;
        Element element;
        Attr attr;
        Node importedAttr;
        String nodeName;
        int nodeType;
        String nodeValue;
        NodeList addresses;
        Node attrsParent;
        doc = (Document) load("staffNS", builder);
        docImported = (Document) load("staff", builder);
        addresses = doc
                .getElementsByTagNameNS("http://www.nist.gov", "address");
        element = (Element) addresses.item(1);
        attr = element.getAttributeNodeNS("http://www.nist.gov", "zone");
        importedAttr = docImported.importNode(attr, false);
        nodeName = importedAttr.getNodeName();
        nodeType = (int) importedAttr.getNodeType();
        nodeValue = importedAttr.getNodeValue();
        attrsParent = importedAttr.getParentNode();
        assertNull("documentimportnode02_parentNull", attrsParent);
        assertEquals("documentimportnode02_nodeName", "emp:zone", nodeName);
        assertEquals("documentimportnode02_nodeType", 2, nodeType);
        assertEquals("documentimportnode02_nodeValue", "CANADA", nodeValue);
    }

// Assumes validation.
//    public void testImportNode3() throws Throwable {
//        Document doc;
//        Element element;
//        Attr attr;
//        NodeList childList;
//        Node importedAttr;
//        String nodeName;
//        int nodeType;
//        String nodeValue;
//        doc = (Document) load("staffNS", builder);
//        childList = doc.getElementsByTagNameNS("http://www.nist.gov",
//                "employee");
//        element = (Element) childList.item(1);
//        attr = element.getAttributeNode("defaultAttr");
//        importedAttr = doc.importNode(attr, false);
//        nodeName = importedAttr.getNodeName();
//        nodeValue = importedAttr.getNodeValue();
//        nodeType = (int) importedAttr.getNodeType();
//        assertEquals("documentimportnode03_nodeName", "defaultAttr", nodeName);
//        assertEquals("documentimportnode03_nodeType", 2, nodeType);
//        assertEquals("documentimportnode03_nodeValue", "defaultVal", nodeValue);
//    }

// Assumes validation.
//    public void testImportNode4() throws Throwable {
//        Document doc;
//        Document newDoc;
//        DocumentType docType = null;
//
//        DOMImplementation domImpl;
//        Element element;
//        Attr attr;
//        NodeList childList;
//        Node importedAttr;
//        String nodeName;
//        int nodeType;
//        String nodeValue;
//        doc = (Document) load("staffNS", builder);
//        domImpl = doc.getImplementation();
//        newDoc = domImpl.createDocument("http://www.w3.org/DOM/Test",
//                "l2:root", docType);
//        childList = doc.getElementsByTagNameNS("http://www.nist.gov",
//                "employee");
//        element = (Element) childList.item(1);
//        attr = element.getAttributeNode("defaultAttr");
//        importedAttr = newDoc.importNode(attr, true);
//        nodeName = importedAttr.getNodeName();
//        nodeValue = importedAttr.getNodeValue();
//        nodeType = (int) importedAttr.getNodeType();
//        assertEquals("documentimportnode04_nodeName", "defaultAttr", nodeName);
//        assertEquals("documentimportnode04_nodeType", 2, nodeType);
//        assertEquals("documentimportnode04_nodeValue", "defaultVal", nodeValue);
//    }
    public void testImportNode5() throws Throwable {
        Document doc;
        Document docImported;
        Attr attr;
        Node importedAttr;
        String nodeName;
        int nodeType;
        String nodeValue;
        String namespaceURI;
        doc = (Document) load("staffNS", builder);
        docImported = (Document) load("staff", builder);
        attr = doc.createAttributeNS("http://www.w3.org/DOM/Test", "a_:b0");
        importedAttr = docImported.importNode(attr, false);
        nodeName = importedAttr.getNodeName();
        nodeValue = importedAttr.getNodeValue();
        nodeType = (int) importedAttr.getNodeType();
        namespaceURI = importedAttr.getNamespaceURI();
        assertEquals("documentimportnode05_nodeName", "a_:b0", nodeName);
        assertEquals("documentimportnode05_nodeType", 2, nodeType);
        assertEquals("documentimportnode05_nodeValue", "", nodeValue);
        assertEquals("documentimportnode05_namespaceURI",
                "http://www.w3.org/DOM/Test", namespaceURI);
    }
    public void testImportNode6() throws Throwable {
        Document doc;

        doc = (Document) load("staffNS", builder);

        {
            boolean success = false;
            try {
                doc.importNode(doc, false);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NOT_SUPPORTED_ERR);
            }
            assertTrue("throw_NOT_SUPPORTED_ERR", success);
        }
    }
    public void testImportNode7() throws Throwable {
        Document doc;

        DocumentType docType;
        doc = (Document) load("staffNS", builder);
        docType = doc.getDoctype();

        {
            boolean success = false;
            try {
                doc.importNode(docType, true);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NOT_SUPPORTED_ERR);
            }
            assertTrue("throw_NOT_SUPPORTED_ERR", success);
        }
    }
    public void testImportNode8() throws Throwable {
        Document doc;

        DocumentType docType;
        DOMImplementation domImpl;
        String nullNS = null;

        doc = (Document) load("staffNS", builder);
        domImpl = doc.getImplementation();
        docType = domImpl.createDocumentType("test:root", nullNS, nullNS);

        {
            boolean success = false;
            try {
                doc.importNode(docType, true);
            } catch (DOMException ex) {
                success = (ex.code == DOMException.NOT_SUPPORTED_ERR);
            }
            assertTrue("throw_NOT_SUPPORTED_ERR", success);
        }
    }
    public void testImportNode9() throws Throwable {
        Document doc;
        DocumentFragment docFragment;
        NodeList childList;
        boolean success;
        Node addressNode;

        Node importedDocFrag;
        doc = (Document) load("staffNS", builder);
        docFragment = doc.createDocumentFragment();
        childList = doc.getElementsByTagNameNS("*", "address");
        addressNode = childList.item(0);
        docFragment.appendChild(addressNode);
        importedDocFrag = doc.importNode(docFragment, false);
        success = importedDocFrag.hasChildNodes();
        assertFalse("documentimportnode09", success);
    }
    public void testImportNode10() throws Throwable {
        Document doc;
        DocumentFragment docFragment;
        NodeList childList;
        boolean success;
        Node addressNode;

        Node importedDocFrag;
        doc = (Document) load("staffNS", builder);
        docFragment = doc.createDocumentFragment();
        childList = doc.getElementsByTagNameNS("*", "address");
        addressNode = childList.item(0);
        docFragment.appendChild(addressNode);
        importedDocFrag = doc.importNode(docFragment, true);
        success = importedDocFrag.hasChildNodes();
        assertTrue("documentimportnode10", success);
    }
    public void testImportNode11() throws Throwable {
        Document doc;
        Element docElement;
        Node imported;
        boolean success;
        String nodeNameOrig;
        String nodeNameImported;
        doc = (Document) load("staffNS", builder);
        docElement = doc.getDocumentElement();
        imported = doc.importNode(docElement, false);
        success = imported.hasChildNodes();
        assertFalse("documentimportnode11", success);
        nodeNameImported = imported.getNodeName();
        nodeNameOrig = docElement.getNodeName();
        assertEquals("documentimportnode11_NodeName", nodeNameImported,
                nodeNameOrig);
    }
    public void testImportNode12() throws Throwable {
        Document doc;
        NodeList childList;
        Node imported;
        Node addressElem;
        NodeList addressElemChildren;
        NodeList importedChildren;
        int addressElemLen;
        int importedLen;
        doc = (Document) load("staffNS", builder);
        childList = doc.getElementsByTagNameNS("*", "address");
        addressElem = childList.item(0);
        imported = doc.importNode(addressElem, true);
        addressElemChildren = addressElem.getChildNodes();
        importedChildren = imported.getChildNodes();
        addressElemLen = (int) addressElemChildren.getLength();
        importedLen = (int) importedChildren.getLength();
        assertEquals("documentimportnode12", importedLen, addressElemLen);
    }
    public void testImportNode13() throws Throwable {
        Document doc;
        NodeList childList;
        Node imported;
        NodeList importedList;
        Node employeeElem;
        int importedLen;
        doc = (Document) load("staffNS", builder);
        childList = doc.getElementsByTagNameNS("*", "employee");
        employeeElem = childList.item(0);
        imported = doc.importNode(employeeElem, false);
        importedList = imported.getChildNodes();
        importedLen = (int) importedList.getLength();
        assertEquals("documentimportnode13", 0, importedLen);
    }

// Assumes validation.
//    public void testImportNode14() throws Throwable {
//        Document doc;
//        Document newDoc;
//        DOMImplementation domImpl;
//        DocumentType nullDocType = null;
//
//        NodeList childList;
//        Node imported;
//        Node employeeElem;
//        Attr attrNode;
//        String attrValue;
//        String nullNS = null;
//
//        doc = (Document) load("staffNS", builder);
//        childList = doc.getElementsByTagNameNS("*", "employee");
//        employeeElem = childList.item(3);
//        domImpl = builder.getDOMImplementation();
//        newDoc = domImpl.createDocument(nullNS, "staff", nullDocType);
//        imported = newDoc.importNode(employeeElem, true);
//        attrNode = ((Element) /* Node */imported).getAttributeNodeNS(nullNS,
//                "defaultAttr");
//        assertNull("defaultAttrNotImported", attrNode);
//        attrValue = ((Element) /* Node */imported).getAttributeNS(
//                "http://www.w3.org/2000/xmlns/", "emp");
//        assertEquals("explicitAttrImported", "http://www.nist.gov", attrValue);
//    }
    public void testImportNode15() throws Throwable {
        Document doc;

        Node textImport;
        Node textToImport;
        String nodeValue;
        doc = (Document) load("staffNS", builder);

        textToImport = doc
                .createTextNode("Document.importNode test for a TEXT_NODE");
        textImport = doc.importNode(textToImport, true);
        nodeValue = textImport.getNodeValue();
        assertEquals("documentimportnode15",
                "Document.importNode test for a TEXT_NODE", nodeValue);
    }
    public void testImportNode17() throws Throwable {
        Document doc;

        Node commentImport;
        Node commentToImport;
        String nodeValue;
        doc = (Document) load("staffNS", builder);

        commentToImport = doc
                .createComment("Document.importNode test for a COMMENT_NODE");
        commentImport = doc.importNode(commentToImport, true);
        nodeValue = commentImport.getNodeValue();
        assertEquals("documentimportnode17",
                "Document.importNode test for a COMMENT_NODE", nodeValue);
    }
    public void testImportNode18() throws Throwable {
        Document doc;

        ProcessingInstruction piImport;
        ProcessingInstruction piToImport;
        String piData;
        String piTarget;
        doc = (Document) load("staffNS", builder);

        piToImport = doc.createProcessingInstruction("Target", "Data");
        piImport = (ProcessingInstruction) doc.importNode(piToImport, false);
        piTarget = piImport.getTarget();
        piData = piImport.getData();
        assertEquals("documentimportnode18_Target", "Target", piTarget);
        assertEquals("documentimportnode18_Data", "Data", piData);
    }

// Assumes validation.
//    public void testImportNode19() throws Throwable {
//        Document doc;
//        DocumentType docTypeNull = null;
//
//        Document docImp;
//        DOMImplementation domImpl;
//        DocumentType docType;
//        NamedNodeMap nodeMap;
//        Entity entity2;
//        Entity entity6;
//        Entity entityImp2;
//        Entity entityImp6;
//        String nodeName;
//        String systemId;
//        String notationName;
//        String nodeNameImp;
//        String systemIdImp;
//        String notationNameImp;
//        doc = (Document) load("staffNS", builder);
//        domImpl = doc.getImplementation();
//        docType = doc.getDoctype();
//        docImp = domImpl.createDocument("http://www.w3.org/DOM/Test", "a:b",
//                docTypeNull);
//        nodeMap = docType.getEntities();
//        assertNotNull("entitiesNotNull", nodeMap);
//        entity2 = (Entity) nodeMap.getNamedItem("ent2");
//        entity6 = (Entity) nodeMap.getNamedItem("ent6");
//        entityImp2 = (Entity) docImp.importNode(entity2, false);
//        entityImp6 = (Entity) docImp.importNode(entity6, true);
//        nodeName = entity2.getNodeName();
//        nodeNameImp = entityImp2.getNodeName();
//        assertEquals("documentimportnode19_Ent2NodeName", nodeName, nodeNameImp);
//        nodeName = entity6.getNodeName();
//        nodeNameImp = entityImp6.getNodeName();
//        assertEquals("documentimportnode19_Ent6NodeName", nodeName, nodeNameImp);
//        systemId = entity2.getSystemId();
//        systemIdImp = entityImp2.getSystemId();
//        assertEquals("documentimportnode19_Ent2SystemId", systemId, systemIdImp);
//        systemId = entity6.getSystemId();
//        systemIdImp = entityImp6.getSystemId();
//        assertEquals("documentimportnode19_Ent6SystemId", systemId, systemIdImp);
//        notationName = entity2.getNotationName();
//        notationNameImp = entityImp2.getNotationName();
//        assertEquals("documentimportnode19_Ent2NotationName", notationName,
//                notationNameImp);
//        notationName = entity6.getNotationName();
//        notationNameImp = entityImp6.getNotationName();
//        assertEquals("documentimportnode19_Ent6NotationName", notationName,
//                notationNameImp);
//    }

// Assumes validation.
//    public void testImportNode20() throws Throwable {
//        Document doc;
//        Document docImp;
//        DOMImplementation domImpl;
//        DocumentType docType;
//        DocumentType docTypeNull = null;
//
//        NamedNodeMap nodeMap;
//        Entity entity4;
//        Entity entityImp4;
//        Element element;
//        CharacterData cdata;
//        ProcessingInstruction pi;
//        NodeList childList;
//        NodeList elemchildList;
//        String ent4Name;
//        String ent4ImpName;
//        String cdataVal;
//        String piTargetVal;
//        String piDataVal;
//        doc = (Document) load("staffNS", builder);
//        domImpl = doc.getImplementation();
//        docType = doc.getDoctype();
//        docImp = domImpl.createDocument("http://www.w3.org/DOM/Test", "a:b",
//                docTypeNull);
//        nodeMap = docType.getEntities();
//        entity4 = (Entity) nodeMap.getNamedItem("ent4");
//        entityImp4 = (Entity) docImp.importNode(entity4, true);
//        childList = entityImp4.getChildNodes();
//        element = (Element) childList.item(0);
//        elemchildList = element.getChildNodes();
//        cdata = (CharacterData) elemchildList.item(0);
//        pi = (ProcessingInstruction) childList.item(1);
//        ent4Name = entity4.getNodeName();
//        ent4ImpName = entityImp4.getNodeName();
//        cdataVal = cdata.getData();
//        piTargetVal = pi.getTarget();
//        piDataVal = pi.getData();
//        assertEquals("documentimportnode20_Ent4NodeName", ent4Name, ent4ImpName);
//        assertEquals("documentimportnode20_Cdata", "Element data", cdataVal);
//        assertEquals("documentimportnode20_PITarget", "PItarget", piTargetVal);
//        assertEquals("documentimportnode20_PIData", "PIdata", piDataVal);
//    }

// TODO Fails on JDK. Why?
//    public void testImportNode21() throws Throwable {
//
//
//        Document doc;
//        DocumentType docTypeNull = null;
//
//        Document docImp;
//        DOMImplementation domImpl;
//        NodeList addressList;
//        NodeList addressChildList;
//        Element element;
//        EntityReference entRef2;
//        EntityReference entRefImp2;
//        EntityReference entRef3;
//        EntityReference entRefImp3;
//        String nodeName2;
//        String nodeName3;
//        String nodeNameImp2;
//        String nodeNameImp3;
//        NodeList nodes;
//        Node nodeImp3;
//        Node nodeImp2;
//        String nodeValueImp2;
//        String nodeValueImp3;
//        doc = (Document) load("staffNS", builder);
//        domImpl = doc.getImplementation();
//        docImp = domImpl.createDocument("http://www.w3.org/DOM/Test", "a:b",
//                docTypeNull);
//        addressList = doc.getElementsByTagName("address");
//        element = (Element) addressList.item(1);
//        addressChildList = element.getChildNodes();
//        entRef2 = (EntityReference) addressChildList.item(0);
//        entRef3 = (EntityReference) addressChildList.item(2);
//        entRefImp2 = (EntityReference) docImp.importNode(entRef2, true);
//        entRefImp3 = (EntityReference) docImp.importNode(entRef3, false);
//        nodeName2 = entRef2.getNodeName();
//        nodeName3 = entRef3.getNodeName();
//        nodeNameImp2 = entRefImp2.getNodeName();
//        nodeNameImp3 = entRefImp3.getNodeName();
//        assertEquals("documentimportnode21_Ent2NodeName", nodeName2,
//                nodeNameImp2);
//        assertEquals("documentimportnode21_Ent3NodeName", nodeName3,
//                nodeNameImp3);
//        entRefImp2 = (EntityReference) doc.importNode(entRef2, true);
//        entRefImp3 = (EntityReference) doc.importNode(entRef3, false);
//        nodes = entRefImp2.getChildNodes();
//        nodeImp2 = nodes.item(0);
//        nodeValueImp2 = nodeImp2.getNodeValue();
//        nodes = entRefImp3.getChildNodes();
//        nodeImp3 = nodes.item(0);
//        nodeValueImp3 = nodeImp3.getNodeValue();
//        assertEquals("documentimportnode21_Ent2NodeValue", "1900 Dallas Road",
//                nodeValueImp2);
//        assertEquals("documentimportnode21_Ent3Nodevalue", "Texas",
//                nodeValueImp3);
//
//    }

// Assumes validation.
//    public void testImportNode22() throws Throwable {
//        Document doc;
//        DocumentType docTypeNull = null;
//
//        Document docImp;
//        DOMImplementation domImpl;
//        DocumentType docType;
//        NamedNodeMap nodeMap;
//        Notation notation1;
//        Notation notation2;
//
//        String publicId1;
//        String publicId1Imp;
//        String publicId1NewImp;
//        String publicId2Imp;
//
//        String systemId1Imp;
//        String systemId1NewImp;
//        String systemId2;
//        String systemId2Imp;
//        String systemId2NewImp;
//        doc = (Document) load("staffNS", builder);
//        domImpl = doc.getImplementation();
//        docType = doc.getDoctype();
//        docImp = domImpl.createDocument("http://www.w3.org/DOM/Test", "a:b",
//                docTypeNull);
//        nodeMap = docType.getNotations();
//        assertNotNull("notationsNotNull", nodeMap);
//        notation1 = (Notation) nodeMap.getNamedItem("notation1");
//        notation2 = (Notation) nodeMap.getNamedItem("notation2");
//        doc.importNode(notation1, true);
//        doc.importNode(notation2, false);
//        docImp.importNode(notation1, false);
//        docImp.importNode(notation2, true);
//        publicId1 = notation1.getPublicId();
//        publicId1Imp = notation1.getPublicId();
//        publicId1NewImp = notation1.getPublicId();
//        systemId1Imp = notation1.getSystemId();
//        systemId1NewImp = notation1.getSystemId();
//        publicId2Imp = notation2.getPublicId();
//        notation2.getPublicId();
//        systemId2 = notation2.getSystemId();
//        systemId2Imp = notation2.getSystemId();
//        systemId2NewImp = notation2.getSystemId();
//        assertEquals("documentimportnode22_N1PID", publicId1, publicId1Imp);
//        assertEquals("documentimportnode22_N1NPID", publicId1, publicId1NewImp);
//        assertNull("documentimportnode22_N1SID", systemId1Imp);
//        assertNull("documentimportnode22_N1NSID", systemId1NewImp);
//        assertEquals("documentimportnode22_N2SID", systemId2, systemId2Imp);
//        assertEquals("documentimportnode22_N2NSID", systemId2, systemId2NewImp);
//        assertNull("documentimportnode22_N2PID", publicId2Imp);
//        assertNull("documentimportnode22_N2NPID", publicId2Imp);
//    }
}
