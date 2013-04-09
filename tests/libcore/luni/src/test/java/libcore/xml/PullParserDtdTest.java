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

import java.io.StringReader;
import java.util.Arrays;
import junit.framework.TestCase;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Test doctype handling in pull parsers.
 */
public abstract class PullParserDtdTest extends TestCase {

    private static final int READ_BUFFER_SIZE = 8192;

    /**
     * Android's Expat pull parser permits parameter entities to be declared,
     * but it doesn't permit such entities to be used.
     */
    public void testDeclaringParameterEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
            + "  <!ENTITY % a \"android\">"
            + "]><foo></foo>";
        XmlPullParser parser = newPullParser(xml);
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
        }
    }

    public void testUsingParameterEntitiesInDtds() throws Exception {
        assertParseFailure("<!DOCTYPE foo ["
            + "  <!ENTITY % a \"android\">"
            + "  <!ENTITY b \"%a;\">"
            + "]><foo></foo>");
    }

    public void testUsingParameterInDocuments() throws Exception {
        assertParseFailure("<!DOCTYPE foo ["
            + "  <!ENTITY % a \"android\">"
            + "]><foo>&a;</foo>");
    }

    public void testGeneralAndParameterEntityWithTheSameName() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY a \"aaa\">"
                + "  <!ENTITY % a \"bbb\">"
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("aaa", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testInternalEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY a \"android\">"
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("android", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testExternalDtdIsSilentlyIgnored() throws Exception {
        String xml = "<!DOCTYPE foo SYSTEM \"http://127.0.0.1:1/no-such-file.dtd\"><foo></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testExternalAndInternalDtd() throws Exception {
        String xml = "<!DOCTYPE foo SYSTEM \"http://127.0.0.1:1/no-such-file.dtd\" ["
                + "  <!ENTITY a \"android\">"
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("android", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testInternalEntitiesAreParsed() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY a \"&#38;#65;\">" // &#38; expands to '&', &#65; expands to 'A'
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("A", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testFoolishlyRecursiveInternalEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY a \"&#38;#38;#38;#38;\">" // expand &#38; to '&' only twice
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("&#38;#38;", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    /**
     * Test that the output of {@code &#38;} is parsed, but {@code &amp;} isn't.
     * http://www.w3.org/TR/2008/REC-xml-20081126/#sec-entexpand
     */
    public void testExpansionOfEntityAndCharacterReferences() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "<!ENTITY example \"<p>An ampersand (&#38;#38;) may be escaped\n"
                + "numerically (&#38;#38;#38;) or with a general entity\n"
                + "(&amp;amp;).</p>\" >"
                + "]><foo>&example;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("p", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("An ampersand (&) may be escaped\n"
                + "numerically (&#38;) or with a general entity\n"
                + "(&amp;).", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("p", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testGeneralEntitiesAreStoredUnresolved() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "<!ENTITY b \"&a;\" >"
                + "<!ENTITY a \"android\" >"
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("android", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testStructuredEntityAndNextToken() throws Exception {
        String xml = "<!DOCTYPE foo [<!ENTITY bb \"<bar>baz<!--quux--></bar>\">]><foo>a&bb;c</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.DOCDECL, parser.nextToken());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("a", parser.getText());
        assertEquals(XmlPullParser.ENTITY_REF, parser.nextToken());
        assertEquals("bb", parser.getName());
        assertEquals("", parser.getText());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("baz", parser.getText());
        assertEquals(XmlPullParser.COMMENT, parser.nextToken());
        assertEquals("quux", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("c", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    /**
     * Android's Expat replaces external entities with the empty string.
     */
    public void testUsingExternalEntities() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!ENTITY a SYSTEM \"http://localhost:1/no-such-file.xml\">"
                + "]><foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        // &a; is dropped!
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    /**
     * Android's ExpatPullParser replaces missing entities with the empty string
     * when an external DTD is declared.
     */
    public void testExternalDtdAndMissingEntity() throws Exception {
        String xml = "<!DOCTYPE foo SYSTEM \"http://127.0.0.1:1/no-such-file.dtd\">"
                + "<foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }


    public void testExternalIdIsCaseSensitive() throws Exception {
        // The spec requires 'SYSTEM' in upper case
        assertParseFailure("<!DOCTYPE foo ["
                + "  <!ENTITY a system \"http://localhost:1/no-such-file.xml\">"
                + "]><foo/>");
    }

    /**
     * Use a DTD to specify that {@code <foo>} only contains {@code <bar>} tags.
     * Validating parsers react to this by dropping whitespace between the two
     * tags.
     */
    public void testDtdDoesNotInformIgnorableWhitespace() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo (bar)*>\n"
                + "  <!ELEMENT bar ANY>\n"
                + "]>"
                + "<foo>  \n  <bar></bar>  \t  </foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("  \n  ", parser.getText());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("  \t  ", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testEmptyDoesNotInformIgnorableWhitespace() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo EMPTY>\n"
                + "]>"
                + "<foo>  \n  </foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("  \n  ", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    /**
     * Test that the parser doesn't expand the entity attributes.
     */
    public void testAttributeOfTypeEntity() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ENTITY a \"android\">"
                + "  <!ELEMENT foo ANY>\n"
                + "  <!ATTLIST foo\n"
                + "    bar ENTITY #IMPLIED>"
                + "]>"
                + "<foo bar=\"a\"></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals("a", parser.getAttributeValue(null, "bar"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testTagStructureNotValidated() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo (bar)*>\n"
                + "  <!ELEMENT bar ANY>\n"
                + "  <!ELEMENT baz ANY>\n"
                + "]>"
                + "<foo><bar/><bar/><baz/></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("baz", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testAttributeDefaultValues() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ATTLIST bar\n"
                + "    baz (a|b|c)  \"c\">"
                + "]>"
                + "<foo>"
                + "<bar/>"
                + "<bar baz=\"a\"/>"
                + "</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals("c", parser.getAttributeValue(null, "baz"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.getName());
        assertEquals("a", parser.getAttributeValue(null, "baz"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testAttributeDefaultValueEntitiesExpanded() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ENTITY g \"ghi\">"
                + "  <!ELEMENT foo ANY>\n"
                + "  <!ATTLIST foo\n"
                + "    bar CDATA \"abc &amp; def &g; jk\">"
                + "]>"
                + "<foo></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals("abc & def ghi jk", parser.getAttributeValue(null, "bar"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testAttributeDefaultValuesAndNamespaces() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ATTLIST foo\n"
                + "    bar:a CDATA \"android\">"
                + "]>"
                + "<foo xmlns:bar='http://bar'></foo>";
        XmlPullParser parser = newPullParser(xml);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        // In Expat, namespaces don't apply to default attributes
        int index = indexOfAttributeWithName(parser, "bar:a");
        assertEquals("", parser.getAttributeNamespace(index));
        assertEquals("bar:a", parser.getAttributeName(index));
        assertEquals("android", parser.getAttributeValue(index));
        assertEquals("CDATA", parser.getAttributeType(index));
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    private int indexOfAttributeWithName(XmlPullParser parser, String name) {
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            if (parser.getAttributeName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void testAttributeEntitiesExpandedEagerly() throws Exception {
        assertParseFailure("<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo ANY>\n"
                + "  <!ATTLIST foo\n"
                + "    bar CDATA \"abc &amp; def &g; jk\">"
                + "  <!ENTITY g \"ghi\">"
                + "]>"
                + "<foo></foo>");
    }

    public void testRequiredAttributesOmitted() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo ANY>\n"
                + "  <!ATTLIST foo\n"
                + "    bar (a|b|c) #REQUIRED>"
                + "]>"
                + "<foo></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(null, parser.getAttributeValue(null, "bar"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testFixedAttributesWithConflictingValues() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!ELEMENT foo ANY>\n"
                + "  <!ATTLIST foo\n"
                + "    bar (a|b|c) #FIXED \"c\">"
                + "]>"
                + "<foo bar=\"a\"></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals("a", parser.getAttributeValue(null, "bar"));
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testParsingNotations() throws Exception {
        String xml = "<!DOCTYPE foo [\n"
                + "  <!NOTATION type-a PUBLIC \"application/a\"> \n"
                + "  <!NOTATION type-b PUBLIC \"image/b\">\n"
                + "  <!NOTATION type-c PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n"
                + "     \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\"> \n"
                + "  <!ENTITY file          SYSTEM \"d.xml\">\n"
                + "  <!ENTITY fileWithNdata SYSTEM \"e.bin\" NDATA type-b>\n"
                + "]>"
                + "<foo type=\"type-a\"/>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testCommentsInDoctype() throws Exception {
        String xml = "<!DOCTYPE foo ["
                + "  <!-- ' -->"
                + "]><foo>android</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("android", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testDoctypeNameOnly() throws Exception {
        String xml = "<!DOCTYPE foo><foo></foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testVeryLongEntities() throws Exception {
        String a = repeat('a', READ_BUFFER_SIZE + 1);
        String b = repeat('b', READ_BUFFER_SIZE + 1);
        String c = repeat('c', READ_BUFFER_SIZE + 1);

        String xml = "<!DOCTYPE foo [\n"
                + "  <!ENTITY " + a + "  \"d &" + b + "; e\">"
                + "  <!ENTITY " + b + "  \"f " + c + " g\">"
                + "]>"
                + "<foo>h &" + a + "; i</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("h d f " + c + " g e i", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testManuallyRegisteredEntitiesWithDoctypeParsing() throws Exception {
        String xml = "<foo>&a;</foo>";
        XmlPullParser parser = newPullParser(xml);
        try {
            parser.defineEntityReplacementText("a", "android");
            fail();
        } catch (UnsupportedOperationException expected) {
        } catch (IllegalStateException expected) {
        }
    }

    public void testDoctypeWithNextToken() throws Exception {
        String xml = "<!DOCTYPE foo [<!ENTITY bb \"bar baz\">]><foo>a&bb;c</foo>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.DOCDECL, parser.nextToken());
        assertEquals(" foo [<!ENTITY bb \"bar baz\">]", parser.getText());
        assertNull(parser.getName());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("abar bazc", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testDoctypeSpansBuffers() throws Exception {
        char[] doctypeChars = new char[READ_BUFFER_SIZE + 1];
        Arrays.fill(doctypeChars, 'x');
        String doctypeBody = " foo [<!--" + new String(doctypeChars) + "-->]";
        String xml = "<!DOCTYPE" + doctypeBody + "><foo/>";
        XmlPullParser parser = newPullParser(xml);
        assertEquals(XmlPullParser.DOCDECL, parser.nextToken());
        assertEquals(doctypeBody, parser.getText());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testDoctypeInDocumentElement() throws Exception {
        assertParseFailure("<foo><!DOCTYPE foo></foo>");
    }

    public void testDoctypeAfterDocumentElement() throws Exception {
        assertParseFailure("<foo/><!DOCTYPE foo>");
    }

    private void assertParseFailure(String xml) throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(xml));
        try {
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
            }
            fail();
        } catch (XmlPullParserException expected) {
        }
    }

    private String repeat(char c, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    private XmlPullParser newPullParser(String xml) throws XmlPullParserException {
        XmlPullParser result = newPullParser();
        result.setInput(new StringReader(xml));
        return result;
    }

    /**
     * Creates a new pull parser.
     */
    abstract XmlPullParser newPullParser() throws XmlPullParserException;
}
