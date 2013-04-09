/*
* Copyright (C) 2011 The Android Open Source Project
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

package libcore.net.http;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import junit.framework.TestCase;

public final class ParsedHeadersTest extends TestCase {

    private URI uri;

    @Override protected void setUp() throws Exception {
        super.setUp();
        uri = new URI("http", "localhost", "/");
    }

    public void testUpperCaseHttpHeaders() {
        RawHeaders headers = new RawHeaders();
        headers.add("CACHE-CONTROL", "no-store");
        headers.add("DATE", "Thu, 01 Jan 1970 00:00:01 UTC");
        headers.add("EXPIRES", "Thu, 01 Jan 1970 00:00:02 UTC");
        headers.add("LAST-MODIFIED", "Thu, 01 Jan 1970 00:00:03 UTC");
        headers.add("ETAG", "v1");
        headers.add("PRAGMA", "no-cache");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoStore());
        assertEquals(new Date(1000), parsedHeaders.getServedDate());
        assertEquals(new Date(2000), parsedHeaders.getExpires());
        assertEquals(new Date(3000), parsedHeaders.getLastModified());
        assertEquals("v1", parsedHeaders.getEtag());
        assertTrue(parsedHeaders.isNoCache());
    }

    public void testCommaSeparatedCacheControlHeaders() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "no-store, max-age=60, public");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoStore());
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
        assertTrue(parsedHeaders.isPublic());
    }

    public void testQuotedFieldName() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "private=\"Set-Cookie\", no-store");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoStore());
    }

    public void testUnquotedValue() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "private=Set-Cookie, no-store");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoStore());
    }

    public void testQuotedValue() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "private=\" a, no-cache, c \", no-store");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoStore());
        assertFalse(parsedHeaders.isNoCache());
    }

    public void testDanglingQuote() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "private=\"a, no-cache, c");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertFalse(parsedHeaders.isNoCache());
    }

    public void testTrailingComma() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "public,");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isPublic());
    }

    public void testTrailingEquals() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "private=");
        new ResponseHeaders(uri, headers);
    }

    public void testSpaceBeforeEquals() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "max-age =60");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
    }

    public void testSpaceAfterEqualsWithQuotes() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "max-age= \"60\"");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
    }

    public void testSpaceAfterEqualsWithoutQuotes() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "max-age= 60");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
    }

    public void testCacheControlRequestDirectivesAreCaseInsensitive() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "NO-CACHE");
        headers.add("Cache-Control", "MAX-AGE=60");
        headers.add("Cache-Control", "MAX-STALE=70");
        headers.add("Cache-Control", "MIN-FRESH=80");
        headers.add("Cache-Control", "ONLY-IF-CACHED");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoCache());
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
        assertEquals(70, parsedHeaders.getMaxStaleSeconds());
        assertEquals(80, parsedHeaders.getMinFreshSeconds());
        assertTrue(parsedHeaders.isOnlyIfCached());
    }

    public void testCacheControlResponseDirectivesAreCaseInsensitive() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "NO-CACHE");
        headers.add("Cache-Control", "NO-STORE");
        headers.add("Cache-Control", "MAX-AGE=60");
        headers.add("Cache-Control", "S-MAXAGE=70");
        headers.add("Cache-Control", "PUBLIC");
        headers.add("Cache-Control", "MUST-REVALIDATE");
        ResponseHeaders parsedHeaders = new ResponseHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoCache());
        assertTrue(parsedHeaders.isNoStore());
        assertEquals(60, parsedHeaders.getMaxAgeSeconds());
        assertEquals(70, parsedHeaders.getSMaxAgeSeconds());
        assertTrue(parsedHeaders.isPublic());
        assertTrue(parsedHeaders.isMustRevalidate());
    }

    public void testPragmaDirectivesAreCaseInsensitive() {
        RawHeaders headers = new RawHeaders();
        headers.add("Pragma", "NO-CACHE");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertTrue(parsedHeaders.isNoCache());
    }

    public void testMissingInteger() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "max-age");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(-1, parsedHeaders.getMaxAgeSeconds());
    }

    public void testInvalidInteger() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "MAX-AGE=pi");
        RequestHeaders requestHeaders = new RequestHeaders(uri, headers);
        assertEquals(-1, requestHeaders.getMaxAgeSeconds());
    }

    public void testVeryLargeInteger() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "MAX-AGE=" + (Integer.MAX_VALUE + 1L));
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(Integer.MAX_VALUE, parsedHeaders.getMaxAgeSeconds());
    }

    public void testNegativeInteger() {
        RawHeaders headers = new RawHeaders();
        headers.add("Cache-Control", "MAX-AGE=-2");
        RequestHeaders parsedHeaders = new RequestHeaders(uri, headers);
        assertEquals(0, parsedHeaders.getMaxAgeSeconds());
    }

    public void testParseChallengesWithCommaInRealm() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "s1 realm=\"ab,cd\", s2 realm=\"ef,gh\"");
        assertEquals(Arrays.asList(new Challenge("s1", "ab,cd"), new Challenge("s2", "ef,gh")),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    public void testParseChallengesWithMultipleHeaders() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "s1 realm=\"abc\"");
        headers.add("WWW-Authenticate", "s2 realm=\"def\"");
        assertEquals(Arrays.asList(new Challenge("s1", "abc"), new Challenge("s2", "def")),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    public void testParseChallengesWithExtraWhitespace() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "  s1  realm=\"a\"  ,  s2  realm=\"b\",  ");
        assertEquals(Arrays.asList(new Challenge("s1", "a"), new Challenge("s2", "b")),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    public void testParseChallengesWithMissingRealm() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "Basic");
        assertEquals(Collections.<Challenge>emptyList(),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    public void testParseChallengesWithEmptyRealm() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "Basic realm=\"\"");
        assertEquals(Arrays.asList(new Challenge("Basic", "")),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    public void testParseChallengesWithMissingScheme() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "realm=\"a\"");
        assertEquals(Collections.<Challenge>emptyList(),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }

    // http://code.google.com/p/android/issues/detail?id=11140
    public void testParseChallengesWithManyParameters() {
        RawHeaders headers = new RawHeaders();
        headers.add("WWW-Authenticate", "Digest realm=\"abc\","
                + " qop=\"auth,auth-int\","
                + " nonce=\"dcd98b7102dd2f0e8b11d0f600bfb0c093\","
                + " opaque=\"5ccc069c403ebaf9f0171e9517f40e41\","
                + " Basic realm=\"def\"");
        assertEquals(Arrays.asList(new Challenge("Digest", "abc"), new Challenge("Basic", "def")),
                HeaderParser.parseChallenges(headers, "WWW-Authenticate"));
    }
}
