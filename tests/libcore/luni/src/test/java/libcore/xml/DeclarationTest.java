/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.xml;

import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Test the parsing of the XML declaration, plus the additional document fields
 * captured during parsing.
 */
public class DeclarationTest extends TestCase {

    private String systemIdA;
    private Document documentA;

    private String systemIdB;
    private Document documentB;

    @Override protected void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        systemIdA = stringToSystemId(
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\" ?><foo />");
        InputSource inputSourceA = new InputSource(systemIdA);
        inputSourceA.setEncoding("US-ASCII");
        documentA = builder.parse(inputSourceA);

        systemIdB = stringToSystemId(
                "<?xml version=\"1.1\" encoding=\"US-ASCII\" standalone=\"yes\" ?><foo />");
        InputSource inputSourceB = new InputSource(systemIdB);
        inputSourceB.setEncoding("ISO-8859-1");
        documentB = builder.parse(inputSourceB);
    }

    private String stringToSystemId(String contents) throws IOException {
        File file = File.createTempFile("temp", "xml");
        file.deleteOnExit();
        OutputStream out = new FileOutputStream(file);
        out.write(contents.getBytes("UTF-8"));
        out.close();
        return "file:" + file;
    }

    /**
     * XML parsers are advised of the document's character set via two channels:
     * via the declaration and also the document's input source. To test that
     * each of these winds up in the correct location in the document model, we
     * supply different names for each. This is only safe because for the subset
     * of characters in the document, the character sets are equivalent.
     */
    public void testGetInputEncoding() throws Exception {
        assertEquals("US-ASCII", documentA.getInputEncoding());
        assertEquals("ISO-8859-1", documentB.getInputEncoding());
    }

    public void testGetXmlEncoding() throws Exception {
        String message = "This implementation doesn't parse the encoding from the XML declaration";
        assertEquals(message, "ISO-8859-1", documentA.getXmlEncoding());
        assertEquals(message, "US-ASCII", documentB.getXmlEncoding());
    }

    public void testGetXmlVersion() throws Exception {
        String message = "This implementation doesn't parse the version from the XML declaration";
        assertEquals(message, "1.0", documentA.getXmlVersion());
        assertEquals(message, "1.1", documentB.getXmlVersion());
    }

    public void testGetXmlStandalone() throws Exception {
        String message = "This implementation doesn't parse standalone from the XML declaration";
        assertEquals(message, false, documentA.getXmlStandalone());
        assertEquals(message, true, documentB.getXmlStandalone());
    }

    public void testGetDocumentUri() throws Exception {
        assertEquals(systemIdA, documentA.getDocumentURI());
        assertEquals(systemIdB, documentB.getDocumentURI());
    }
}
