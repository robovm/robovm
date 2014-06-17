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

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import junit.framework.TestCase;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class PullParserTest extends TestCase {

    public void testAttributeNoValueWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        parser.setInput(new StringReader("<input checked></input>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals("checked", parser.getAttributeName(0));
        assertEquals("checked", parser.getAttributeValue(0));
    }

    public void testAttributeUnquotedValueWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        parser.setInput(new StringReader("<input checked=true></input>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals("checked", parser.getAttributeName(0));
        assertEquals("true", parser.getAttributeValue(0));
    }

    public void testUnterminatedEntityWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        parser.setInput(new StringReader("<foo bar='A&W'>mac&cheese</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals("bar", parser.getAttributeName(0));
        assertEquals("A&W", parser.getAttributeValue(0));
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("mac&cheese", parser.getText());
    }

    public void testEntitiesAndNamespaces() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        parser.setInput(new StringReader(
                "<foo:a xmlns:foo='http://foo' xmlns:bar='http://bar'><bar:b/></foo:a>"));
        testNamespace(parser);
    }

    public void testEntitiesAndNamespacesWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        parser.setInput(new StringReader(
                "<foo:a xmlns:foo='http://foo' xmlns:bar='http://bar'><bar:b/></foo:a>"));
        testNamespace(parser);
    }

    private void testNamespace(XmlPullParser parser) throws Exception {
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("http://foo", parser.getNamespace());
        assertEquals("a", parser.getName());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("http://bar", parser.getNamespace());
        assertEquals("b", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("http://bar", parser.getNamespace());
        assertEquals("b", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("http://foo", parser.getNamespace());
        assertEquals("a", parser.getName());
    }

    public void testRegularNumericEntities() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>&#65;</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.ENTITY_REF, parser.nextToken());
        assertEquals("#65", parser.getName());
        assertEquals("A", parser.getText());
    }

    public void testNumericEntitiesLargerThanChar() throws Exception {
        assertParseFailure("<foo>&#2147483647; &#-2147483648;</foo>");
    }

    public void testNumericEntitiesLargerThanInt() throws Exception {
        assertParseFailure("<foo>&#2147483648;</foo>");
    }

    public void testCharacterReferenceOfHexUtf16Surrogates() throws Exception {
        testCharacterReferenceOfUtf16Surrogates("<foo>&#x10000; &#x10381; &#x10FFF0;</foo>");
    }

    public void testCharacterReferenceOfDecimalUtf16Surrogates() throws Exception {
        testCharacterReferenceOfUtf16Surrogates("<foo>&#65536; &#66433; &#1114096;</foo>");
    }

    private void testCharacterReferenceOfUtf16Surrogates(String xml) throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(xml));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals(new String(new int[]{65536, ' ', 66433, ' ', 1114096}, 0, 5),
                parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testCharacterReferenceOfLastUtf16Surrogate() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>&#x10FFFF;</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals(new String(new int[]{0x10FFFF}, 0, 1), parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testOmittedNumericEntities() throws Exception {
        assertParseFailure("<foo>&#;</foo>");
    }

    /**
     * Carriage returns followed by line feeds are silently discarded.
     */
    public void testCarriageReturnLineFeed() throws Exception {
        testLineEndings("\r\n<foo\r\na='b\r\nc'\r\n>d\r\ne</foo\r\n>\r\n");
    }

    /**
     * Lone carriage returns are treated like newlines.
     */
    public void testLoneCarriageReturn() throws Exception {
        testLineEndings("\r<foo\ra='b\rc'\r>d\re</foo\r>\r");
    }

    public void testLoneNewLine() throws Exception {
        testLineEndings("\n<foo\na='b\nc'\n>d\ne</foo\n>\n");
    }

    private void testLineEndings(String xml) throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(xml));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals("b c", parser.getAttributeValue(0));
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("d\ne", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testXmlDeclaration() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(
                "<?xml version='1.0' encoding='UTF-8' standalone='no'?><foo/>"));
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("1.0", parser.getProperty(
                "http://xmlpull.org/v1/doc/properties.html#xmldecl-version"));
        assertEquals(Boolean.FALSE, parser.getProperty(
                "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone"));
        assertEquals("UTF-8", parser.getInputEncoding());
    }

    public void testXmlDeclarationExtraAttributes() throws Exception {
        assertParseFailure("<?xml version='1.0' encoding='UTF-8' standalone='no' a='b'?><foo/>");
    }

    public void testCustomEntitiesUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(
                "<foo a='cd&aaaaaaaaaa;ef'>wx&aaaaaaaaaa;yz</foo>"));
        parser.defineEntityReplacementText("aaaaaaaaaa", "b");
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("cdbef", parser.getAttributeValue(0));
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("wxbyz", parser.getText());
    }

    public void testCustomEntitiesUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(
                "<foo a='cd&aaaaaaaaaa;ef'>wx&aaaaaaaaaa;yz</foo>"));
        parser.defineEntityReplacementText("aaaaaaaaaa", "b");
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("cdbef", parser.getAttributeValue(0));
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("wx", parser.getText());
        assertEquals(XmlPullParser.ENTITY_REF, parser.nextToken());
        assertEquals("aaaaaaaaaa", parser.getName());
        assertEquals("b", parser.getText());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("yz", parser.getText());
    }

    public void testCustomEntitiesAreNotEvaluated() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader(
                "<foo a='&a;'>&a;</foo>"));
        parser.defineEntityReplacementText("a", "&amp; &a;");
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("&amp; &a;", parser.getAttributeValue(0));
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("&amp; &a;", parser.getText());
    }

    public void testMissingEntities() throws Exception {
        assertParseFailure("<foo>&aaa;</foo>");
    }

    public void testMissingEntitiesWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        parser.setInput(new StringReader("<foo>&aaa;</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals(null, parser.getName());
        assertEquals("Expected unresolved entities to be left in-place. The old parser "
                + "would resolve these to the empty string.", "&aaa;", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testMissingEntitiesUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        testMissingEntitiesUsingNextToken(parser);
    }

    public void testMissingEntitiesUsingNextTokenWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        testMissingEntitiesUsingNextToken(parser);
    }

    private void testMissingEntitiesUsingNextToken(XmlPullParser parser) throws Exception {
        parser.setInput(new StringReader("<foo>&aaa;</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(XmlPullParser.ENTITY_REF, parser.nextToken());
        assertEquals("aaa", parser.getName());
        assertEquals(null, parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testEntityInAttributeUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo bar=\"&amp;\"></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("foo", parser.getName());
        assertEquals("&", parser.getAttributeValue(null, "bar"));
    }

    public void testMissingEntitiesInAttributesUsingNext() throws Exception {
        assertParseFailure("<foo b='&aaa;'></foo>");
    }

    public void testMissingEntitiesInAttributesUsingNextWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo b='&aaa;'></foo>"));
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(1, parser.getAttributeCount());
        assertEquals("b", parser.getAttributeName(0));
        assertEquals("Expected unresolved entities to be left in-place. The old parser "
                + "would resolve these to the empty string.", "&aaa;", parser.getAttributeValue(0));
    }

    public void testMissingEntitiesInAttributesUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo b='&aaa;'></foo>"));
        testMissingEntitiesInAttributesUsingNextToken(parser);
    }

    public void testMissingEntitiesInAttributesUsingNextTokenWithRelaxed() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo b='&aaa;'></foo>"));
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        testMissingEntitiesInAttributesUsingNextToken(parser);
    }

    private void testMissingEntitiesInAttributesUsingNextToken(XmlPullParser parser)
            throws Exception {
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(1, parser.getAttributeCount());
        assertEquals("b", parser.getAttributeName(0));
        assertEquals("Expected unresolved entities to be left in-place. The old parser "
                + "would resolve these to the empty string.", "&aaa;", parser.getAttributeValue(0));
    }

    public void testGreaterThanInText() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals(">", parser.getText());
    }

    public void testGreaterThanInAttribute() throws Exception{
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo a='>'></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(">", parser.getAttributeValue(0));
    }

    public void testLessThanInText() throws Exception{
        assertParseFailure("<foo><</foo>");
    }

    public void testLessThanInAttribute() throws Exception{
        assertParseFailure("<foo a='<'></foo>");
    }

    public void testQuotesInAttribute() throws Exception{
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo a='\"' b=\"'\"></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("\"", parser.getAttributeValue(0));
        assertEquals("'", parser.getAttributeValue(1));
    }

    public void testQuotesInText() throws Exception{
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>\" '</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("\" '", parser.getText());
    }

    public void testCdataDelimiterInAttribute() throws Exception{
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo a=']]>'></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("]]>", parser.getAttributeValue(0));
    }

    public void testCdataDelimiterInText() throws Exception{
        assertParseFailure("<foo>]]></foo>");
    }

    public void testUnexpectedEof() throws Exception {
        assertParseFailure("<foo><![C");
    }

    public void testUnexpectedSequence() throws Exception {
        assertParseFailure("<foo><![Cdata[bar]]></foo>");
    }

    public void testThreeDashCommentDelimiter() throws Exception {
        assertParseFailure("<foo><!--a---></foo>");
    }

    public void testTwoDashesInComment() throws Exception {
        assertParseFailure("<foo><!-- -- --></foo>");
    }

    public void testEmptyComment() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo><!----></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.COMMENT, parser.nextToken());
        assertEquals("", parser.getText());
    }

    /**
     * Close braces require lookaheads because we need to defend against "]]>".
     */
    public void testManyCloseBraces() throws Exception{
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>]]]]]]]]]]]]]]]]]]]]]]]</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("]]]]]]]]]]]]]]]]]]]]]]]", parser.getText());
    }

    public void testCommentUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<!-- comment! -->cd</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("abcd", parser.getText());
    }

    public void testCommentUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<!-- comment! -->cd</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("ab", parser.getText());
        assertEquals(XmlPullParser.COMMENT, parser.nextToken());
        assertEquals(" comment! ", parser.getText());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("cd", parser.getText());
    }

    public void testCdataUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<![CDATA[cdef]]gh&amp;i]]>jk</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("abcdef]]gh&amp;ijk", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testCdataUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<![CDATA[cdef]]gh&amp;i]]>jk</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("ab", parser.getText());
        assertEquals(XmlPullParser.CDSECT, parser.nextToken());
        assertEquals("cdef]]gh&amp;i", parser.getText());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("jk", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
    }

    public void testEntityLooksLikeCdataClose() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>&#93;&#93;></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("]]>", parser.getText());
    }

    public void testProcessingInstructionUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<?cd efg hij?>kl</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals("abkl", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testProcessingInstructionUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>ab<?cd efg hij?>kl</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("ab", parser.getText());
        assertEquals(XmlPullParser.PROCESSING_INSTRUCTION, parser.nextToken());
        assertEquals("cd efg hij", parser.getText());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("kl", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testWhitespaceUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("  \n  <foo> \n </foo>   \n   "));
        assertEquals(XmlPullParser.IGNORABLE_WHITESPACE, parser.nextToken());
        assertEquals(true, parser.isWhitespace());
        assertEquals("  \n  ", parser.getText());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals(true, parser.isWhitespace());
        assertEquals(" \n ", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
        assertEquals(XmlPullParser.IGNORABLE_WHITESPACE, parser.nextToken());
        assertEquals(true, parser.isWhitespace());
        assertEquals("   \n   ", parser.getText());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.nextToken());
    }

    public void testLinesAndColumns() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("\n"
                + "  <foo><bar a='\n"
                + "' b='cde'></bar\n"
                + "><!--\n"
                + "\n"
                + "--><baz/>fg\n"
                + "</foo>"));
        assertEquals("1,1", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.IGNORABLE_WHITESPACE, parser.nextToken());
        assertEquals("2,3", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("2,8", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("3,11", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
        assertEquals("4,2", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.COMMENT, parser.nextToken());
        assertEquals("6,4", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals("6,10", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
        assertEquals("6,10", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.TEXT, parser.nextToken());
        assertEquals("7,1", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
        assertEquals("7,7", parser.getLineNumber() + "," + parser.getColumnNumber());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.nextToken());
        assertEquals("7,7", parser.getLineNumber() + "," + parser.getColumnNumber());
    }

    public void testEmptyEntityReferenceUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>&empty;</foo>"));
        parser.defineEntityReplacementText("empty", "");
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testEmptyEntityReferenceUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>&empty;</foo>"));
        parser.defineEntityReplacementText("empty", "");
        assertEquals(XmlPullParser.START_TAG, parser.nextToken());
        assertEquals(XmlPullParser.ENTITY_REF, parser.nextToken());
        assertEquals("empty", parser.getName());
        assertEquals("", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.nextToken());
    }

    public void testEmptyCdataUsingNext() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo><![CDATA[]]></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testEmptyCdataUsingNextToken() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo><![CDATA[]]></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.CDSECT, parser.nextToken());
        assertEquals("", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
    }

    public void testParseReader() throws Exception {
        String snippet = "<dagny dad=\"bob\">hello</dagny>";
        XmlPullParser parser = newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(new StringReader(snippet));
        validate(parser);
    }

    public void testParseInputStream() throws Exception {
        String snippet = "<dagny dad=\"bob\">hello</dagny>";
        XmlPullParser parser = newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(new ByteArrayInputStream(snippet.getBytes()), "UTF-8");
        validate(parser);
    }

    static void validate(XmlPullParser parser) throws Exception {
        assertEquals(XmlPullParser.START_DOCUMENT, parser.getEventType());
        assertEquals(0, parser.getDepth());
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(1, parser.getDepth());
        assertEquals("dagny", parser.getName());
        assertEquals(1, parser.getAttributeCount());
        assertEquals("dad", parser.getAttributeName(0));
        assertEquals("bob", parser.getAttributeValue(0));
        assertEquals("bob", parser.getAttributeValue(null, "dad"));
        assertEquals(XmlPullParser.TEXT, parser.next());
        assertEquals(1, parser.getDepth());
        assertEquals("hello", parser.getText());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(1, parser.getDepth());
        assertEquals("dagny", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
        assertEquals(0, parser.getDepth());
    }

    public void testNextAfterEndDocument() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo></foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testNamespaces() throws Exception {
        String xml = "<one xmlns='ns:default' xmlns:n1='ns:1' a='b'>\n"
                + "  <n1:two c='d' n1:e='f' xmlns:n2='ns:2'>text</n1:two>\n"
                + "</one>";

        XmlPullParser parser = newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(new StringReader(xml));

        assertEquals(0, parser.getDepth());
        assertEquals(0, parser.getNamespaceCount(0));

        try {
            parser.getNamespaceCount(1);
            fail();
        } catch (IndexOutOfBoundsException e) { /* expected */ }

        // one
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals(1, parser.getDepth());

        checkNamespacesInOne(parser);

        // n1:two
        assertEquals(XmlPullParser.START_TAG, parser.nextTag());

        assertEquals(2, parser.getDepth());
        checkNamespacesInTwo(parser);

        // Body of two.
        assertEquals(XmlPullParser.TEXT, parser.next());

        // End of two.
        assertEquals(XmlPullParser.END_TAG, parser.nextTag());

        // Depth should still be 2.
        assertEquals(2, parser.getDepth());

        // We should still be able to see the namespaces from two.
        checkNamespacesInTwo(parser);

        // End of one.
        assertEquals(XmlPullParser.END_TAG, parser.nextTag());

        // Depth should be back to 1.
        assertEquals(1, parser.getDepth());

        // We can still see the namespaces in one.
        checkNamespacesInOne(parser);

        // We shouldn't be able to see the namespaces in two anymore.
        try {
            parser.getNamespaceCount(2);
            fail();
        } catch (IndexOutOfBoundsException e) { /* expected */ }

        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());

        // We shouldn't be able to see the namespaces in one anymore.
        try {
            parser.getNamespaceCount(1);
            fail();
        } catch (IndexOutOfBoundsException e) { /* expected */ }

        assertEquals(0, parser.getNamespaceCount(0));
    }

    private void checkNamespacesInOne(XmlPullParser parser) throws XmlPullParserException {
        assertEquals(2, parser.getNamespaceCount(1));

        // Prefix for default namespace is null.
        assertNull(parser.getNamespacePrefix(0));
        assertEquals("ns:default", parser.getNamespaceUri(0));

        assertEquals("n1", parser.getNamespacePrefix(1));
        assertEquals("ns:1", parser.getNamespaceUri(1));

        assertEquals("ns:default", parser.getNamespace(null));

        // KXML returns null.
        // assertEquals("ns:default", parser.getNamespace(""));
    }

    private void checkNamespacesInTwo(XmlPullParser parser) throws XmlPullParserException {
        // These should still be valid.
        checkNamespacesInOne(parser);

        assertEquals(3, parser.getNamespaceCount(2));

        // Default ns should still be in the stack
        assertNull(parser.getNamespacePrefix(0));
        assertEquals("ns:default", parser.getNamespaceUri(0));
    }

    public void testTextBeforeDocumentElement() throws Exception {
        assertParseFailure("not xml<foo/>");
    }

    public void testTextAfterDocumentElement() throws Exception {
        assertParseFailure("<foo/>not xml");
    }

    public void testTextNoDocumentElement() throws Exception {
        assertParseFailure("not xml");
    }

    public void testBomAndByteInput() throws Exception {
        byte[] xml = "\ufeff<?xml version='1.0'?><input/>".getBytes("UTF-8");
        XmlPullParser parser = newPullParser();
        parser.setInput(new ByteArrayInputStream(xml), null);
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testBomAndByteInputWithExplicitCharset() throws Exception {
        byte[] xml = "\ufeff<?xml version='1.0'?><input/>".getBytes("UTF-8");
        XmlPullParser parser = newPullParser();
        parser.setInput(new ByteArrayInputStream(xml), "UTF-8");
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.next());
        assertEquals("input", parser.getName());
        assertEquals(XmlPullParser.END_DOCUMENT, parser.next());
    }

    public void testBomAndCharacterInput() throws Exception {
        assertParseFailure("\ufeff<?xml version='1.0'?><input/>");
    }

    // http://code.google.com/p/android/issues/detail?id=21425
    public void testNextTextAdvancesToEndTag() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo>bar</foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.next());
        assertEquals("bar", parser.nextText());
        assertEquals(XmlPullParser.END_TAG, parser.getEventType());
    }

    public void testNextTag() throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setInput(new StringReader("<foo> <bar></bar> </foo>"));
        assertEquals(XmlPullParser.START_TAG, parser.nextTag());
        assertEquals("foo", parser.getName());
        assertEquals(XmlPullParser.START_TAG, parser.nextTag());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.nextTag());
        assertEquals("bar", parser.getName());
        assertEquals(XmlPullParser.END_TAG, parser.nextTag());
        assertEquals("foo", parser.getName());
    }

    public void testEofInElementSpecRelaxed() throws Exception {
        assertRelaxedParseFailure("<!DOCTYPE foo [<!ELEMENT foo (unterminated");
    }

    public void testEofInAttributeValue() throws Exception {
        assertParseFailure("<!DOCTYPE foo [<!ATTLIST foo x y \"unterminated");
    }

    public void testEofInEntityValue() throws Exception {
        assertParseFailure("<!DOCTYPE foo [<!ENTITY aaa \"unterminated");
    }

    public void testEofInStartTagAttributeValue() throws Exception {
        assertParseFailure("<long foo=\"unterminated");
    }

    public void testEofInReadCharRelaxed() throws Exception {
        assertRelaxedParseFailure("<!DOCTYPE foo [<!ELEMENT foo ()"); // EOF in read('>')
    }

    public void testEofAfterReadCharArrayRelaxed() throws Exception {
        assertRelaxedParseFailure("<!DOCTYPE foo [<!ELEMENT foo EMPTY"); // EOF in read('>')
    }

    private void assertParseFailure(String xml) throws Exception {
        XmlPullParser parser = newPullParser();
        assertParseFailure(xml, parser);
    }

    private void assertRelaxedParseFailure(String xml) throws Exception {
        XmlPullParser parser = newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true);
        assertParseFailure(xml, parser);
    }

    private void assertParseFailure(String xml, XmlPullParser parser) throws Exception {
        parser.setInput(new StringReader(xml));
        try {
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
            }
            fail();
        } catch (XmlPullParserException expected) {
        }
    }

    /**
     * Creates a new pull parser.
     */
    abstract XmlPullParser newPullParser();
}
