/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.prefs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import libcore.io.IoUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Utility class for the Preferences import/export from XML file.
 */
class XMLParser {

    /*
     * Constant - the specified DTD URL
     */
    static final String PREFS_DTD_NAME = "http://java.sun.com/dtd/preferences.dtd";

    /*
     * Constant - the DTD string
     */
    static final String PREFS_DTD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "    <!ELEMENT preferences (root)>"
        + "    <!ATTLIST preferences EXTERNAL_XML_VERSION CDATA \"0.0\" >"
        + "    <!ELEMENT root (map, node*) >"
        + "    <!ATTLIST root type (system|user) #REQUIRED >"
        + "    <!ELEMENT node (map, node*) >"
        + "    <!ATTLIST node name CDATA #REQUIRED >"
        + "    <!ELEMENT map (entry*) >"
        + "    <!ELEMENT entry EMPTY >"
        + "    <!ATTLIST entry key   CDATA #REQUIRED value CDATA #REQUIRED >";

    /*
     * Constant - the specified header
     */
    static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    /*
     * Constant - the specified DOCTYPE
     */
    static final String DOCTYPE = "<!DOCTYPE preferences SYSTEM";

    /*
     * Constant - used by FilePreferencesImpl, which is default implementation of Linux platform
     */
    private static final String FILE_PREFS = "<!DOCTYPE map SYSTEM 'http://java.sun.com/dtd/preferences.dtd'>";

    /*
     * Constant - specify the DTD version
     */
    private static final float XML_VERSION = 1.0f;

    /*
     * DOM builder
     */
    private static final DocumentBuilder builder;

    /*
     * specify the indent level
     */
    private static int indent = -1;

    /*
     * init DOM builder
     */
    static {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new Error(e);
        }
        builder.setEntityResolver(new EntityResolver() {
            public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
                if (systemId.equals(PREFS_DTD_NAME)) {
                    InputSource result = new InputSource(new StringReader(
                            PREFS_DTD));
                    result.setSystemId(PREFS_DTD_NAME);
                    return result;
                }
                throw new SAXException("Invalid DOCTYPE declaration " + systemId);
            }
        });
        builder.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException e) throws SAXException {
                throw e;
            }

            public void error(SAXParseException e) throws SAXException {
                throw e;
            }

            public void fatalError(SAXParseException e) throws SAXException {
                throw e;
            }
        });
    }

    private XMLParser() {// empty constructor
    }

    /***************************************************************************
     * utilities for Preferences export
     **************************************************************************/
    static void exportPrefs(Preferences prefs, OutputStream stream,
            boolean withSubTree) throws IOException, BackingStoreException {
        indent = -1;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
        out.write(HEADER);
        out.newLine();
        out.newLine();

        out.write(DOCTYPE);
        out.write(" '");
        out.write(PREFS_DTD_NAME);
        out.write("'>");
        out.newLine();
        out.newLine();

        flushStartTag("preferences", new String[] { "EXTERNAL_XML_VERSION" },
                new String[] { String.valueOf(XML_VERSION) }, out);
        flushStartTag("root", new String[] { "type" },
                new String[] { prefs.isUserNode() ? "user" : "system" }, out);
        flushEmptyElement("map", out);

        StringTokenizer ancestors = new StringTokenizer(prefs.absolutePath(), "/");
        exportNode(ancestors, prefs, withSubTree, out);

        flushEndTag("root", out);
        flushEndTag("preferences", out);
        out.flush();
        out = null;
    }

    private static void exportNode(StringTokenizer ancestors,
            Preferences prefs, boolean withSubTree, BufferedWriter out)
            throws IOException, BackingStoreException {
        if (ancestors.hasMoreTokens()) {
            String name = ancestors.nextToken();
            flushStartTag("node", new String[] { "name" }, new String[] { name }, out);
            if (ancestors.hasMoreTokens()) {
                flushEmptyElement("map", out);
                exportNode(ancestors, prefs, withSubTree, out);
            } else {
                exportEntries(prefs, out);
                if (withSubTree) {
                    exportSubTree(prefs, out);
                }
            }
            flushEndTag("node", out);
        }
    }

    private static void exportSubTree(Preferences prefs, BufferedWriter out)
    throws BackingStoreException, IOException {
        String[] names = prefs.childrenNames();
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                Preferences child = prefs.node(names[i]);
                flushStartTag("node", new String[] { "name" }, new String[] { names[i] }, out);
                exportEntries(child, out);
                exportSubTree(child, out);
                flushEndTag("node", out);
            }
        }
    }

    private static void exportEntries(Preferences prefs, BufferedWriter out)
    throws BackingStoreException, IOException {
        String[] keys = prefs.keys();
        String[] values = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            values[i] = prefs.get(keys[i], null);
        }
        exportEntries(keys, values, out);
    }

    private static void exportEntries(String[] keys, String[] values,
            BufferedWriter out) throws IOException {
        if (keys.length == 0) {
            flushEmptyElement("map", out);
            return;
        }
        flushStartTag("map", out);
        for (int i = 0; i < keys.length; i++) {
            if (values[i] != null) {
                flushEmptyElement("entry", new String[] { "key", "value" },
                        new String[] { keys[i], values[i] }, out);
            }
        }
        flushEndTag("map", out);
    }

    private static void flushEndTag(String tagName, BufferedWriter out)
    throws IOException {
        flushIndent(indent--, out);
        out.write("</");
        out.write(tagName);
        out.write(">");
        out.newLine();
    }

    private static void flushEmptyElement(String tagName, BufferedWriter out)
    throws IOException {
        flushIndent(++indent, out);
        out.write("<");
        out.write(tagName);
        out.write(" />");
        out.newLine();
        indent--;
    }

    private static void flushEmptyElement(String tagName, String[] attrKeys,
            String[] attrValues, BufferedWriter out) throws IOException {
        flushIndent(++indent, out);
        out.write("<");
        out.write(tagName);
        flushPairs(attrKeys, attrValues, out);
        out.write(" />");
        out.newLine();
        indent--;
    }

    private static void flushPairs(String[] attrKeys, String[] attrValues,
            BufferedWriter out) throws IOException {
        for (int i = 0; i < attrKeys.length; i++) {
            out.write(" ");
            out.write(attrKeys[i]);
            out.write("=\"");
            out.write(htmlEncode(attrValues[i]));
            out.write("\"");
        }
    }

    private static void flushIndent(int ind, BufferedWriter out)
    throws IOException {
        for (int i = 0; i < ind; i++) {
            out.write("  ");
        }
    }

    private static void flushStartTag(String tagName, String[] attrKeys,
            String[] attrValues, BufferedWriter out) throws IOException {
        flushIndent(++indent, out);
        out.write("<");
        out.write(tagName);
        flushPairs(attrKeys, attrValues, out);
        out.write(">");
        out.newLine();
    }

    private static void flushStartTag(String tagName, BufferedWriter out)
    throws IOException {
        flushIndent(++indent, out);
        out.write("<");
        out.write(tagName);
        out.write(">");
        out.newLine();
    }

    private static String htmlEncode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /***************************************************************************
     * utilities for Preferences import
     **************************************************************************/
    static void importPrefs(InputStream in) throws IOException, InvalidPreferencesFormatException {
        try {
            // load XML document
            Document doc = builder.parse(new InputSource(in));

            // check preferences' export version
            Element preferences;
            preferences = doc.getDocumentElement();
            String version = preferences.getAttribute("EXTERNAL_XML_VERSION");
            if (version != null && Float.parseFloat(version) > XML_VERSION) {
                throw new InvalidPreferencesFormatException("Preferences version " + version +
                        " is not supported");
            }

            // check preferences root's type
            Element root = (Element) preferences
            .getElementsByTagName("root").item(0);
            Preferences prefsRoot = null;
            String type = root.getAttribute("type");
            if (type.equals("user")) {
                prefsRoot = Preferences.userRoot();
            } else {
                prefsRoot = Preferences.systemRoot();
            }

            // load node
            loadNode(prefsRoot, root);
        } catch (FactoryConfigurationError e) {
            throw new InvalidPreferencesFormatException(e);
        } catch (SAXException e) {
            throw new InvalidPreferencesFormatException(e);
        }
    }

    private static void loadNode(Preferences prefs, Element node) {
        // load preferences
        NodeList children = selectNodeList(node, "node");
        NodeList entries = selectNodeList(node, "map/entry");
        int childNumber = children.getLength();
        Preferences[] prefChildren = new Preferences[childNumber];
        int entryNumber = entries.getLength();
        synchronized (((AbstractPreferences) prefs).lock) {
            if (((AbstractPreferences) prefs).isRemoved()) {
                return;
            }
            for (int i = 0; i < entryNumber; i++) {
                Element entry = (Element) entries.item(i);
                String key = entry.getAttribute("key");
                String value = entry.getAttribute("value");
                prefs.put(key, value);
            }
            // get children preferences node
            for (int i = 0; i < childNumber; i++) {
                Element child = (Element) children.item(i);
                String name = child.getAttribute("name");
                prefChildren[i] = prefs.node(name);
            }
        }

        // load children nodes after unlock
        for (int i = 0; i < childNumber; i++) {
            loadNode(prefChildren[i], (Element) children.item(i));
        }
    }

    // TODO dirty implementation of a method from javax.xml.xpath
    // should be replaced with a call to a good impl of this method
    private static NodeList selectNodeList(Element documentElement, String string) {

        NodeList result = null;

        ArrayList<Node> input = new ArrayList<Node>();

        String[] path = string.split("/");

        NodeList childNodes = documentElement.getChildNodes();

        if(path[0].equals("entry") || path[0].equals("node")) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Object next = childNodes.item(i);
                if(next instanceof Element) {
                    if(((Element) next).getNodeName().equals(path[0])) {
                        input.add((Node)next);
                    }
                }
            }
        } else if(path[0].equals("map") && path[1].equals("entry")) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Object next = childNodes.item(i);
                if(next instanceof Element) {
                    if(((Element) next).getNodeName().equals(path[0])) {
                        NodeList nextChildNodes = ((Node)next).getChildNodes();
                        for (int j = 0; j < nextChildNodes.getLength(); j++) {
                            Object subnext = nextChildNodes.item(j);
                            if(subnext instanceof Element) {
                                if(((Element)subnext).getNodeName().equals(path[1])) {
                                    input.add((Node)subnext);
                                }
                            }
                        }
                    }
                }
            }
        }

        result = new NodeSet(input.iterator());

        return result;
    }

    /**
     * Returns the preferences from {@code xmlFile}. Returns empty properties if
     * any errors occur.
     */
    static Properties readXmlPreferences(File xmlFile) {
        Properties result = new Properties();
        if (!xmlFile.exists()) {
            xmlFile.getParentFile().mkdirs();
        } else if (xmlFile.canRead()) {
            Reader reader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(xmlFile), "UTF-8");
                Document document = builder.parse(new InputSource(reader));
                NodeList entries = selectNodeList(document.getDocumentElement(), "entry");
                int length = entries.getLength();
                for (int i = 0; i < length; i++) {
                    Element node = (Element) entries.item(i);
                    String key = node.getAttribute("key");
                    String value = node.getAttribute("value");
                    result.setProperty(key, value);
                }
            } catch (IOException ignored) {
            } catch (SAXException ignored) {
            } finally {
                IoUtils.closeQuietly(reader);
            }
        } else {
            // the prefs API requires this to be hostile towards pre-existing files
            xmlFile.delete();
        }
        return result;
    }

    /**
     * Writes the preferences to {@code xmlFile}.
     */
    static void writeXmlPreferences(File xmlFile, Properties properties) throws IOException {
        File parent = xmlFile.getParentFile();
        File temporaryForWriting = new File(parent, "prefs-" + UUID.randomUUID() + ".xml.tmp");

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(temporaryForWriting), "UTF-8"));
            out.write(HEADER);
            out.newLine();
            out.write(FILE_PREFS);
            out.newLine();
            String[] keys = properties.keySet().toArray(new String[properties.size()]);
            int length = keys.length;
            String[] values = new String[length];
            for (int i = 0; i < length; i++) {
                values[i] = properties.getProperty(keys[i]);
            }
            exportEntries(keys, values, out);
            out.close();
            if (!temporaryForWriting.renameTo(xmlFile)) {
                throw new IOException("Failed to write preferences to " + xmlFile);
            }
        } finally {
            IoUtils.closeQuietly(out);
            temporaryForWriting.delete(); // no-op unless something failed
        }
    }
}
