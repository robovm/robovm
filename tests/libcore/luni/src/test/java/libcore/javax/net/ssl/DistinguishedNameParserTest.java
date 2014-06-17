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

package libcore.javax.net.ssl;

import javax.net.ssl.DistinguishedNameParser;
import javax.security.auth.x500.X500Principal;
import junit.framework.TestCase;

public final class DistinguishedNameParserTest extends TestCase {
    public void testGetFirstCn() {
        assertFirstCn("", null);
        assertFirstCn("ou=xxx", null);
        assertFirstCn("ou=xxx,cn=xxx", "xxx");
        assertFirstCn("ou=xxx+cn=yyy,cn=zzz+cn=abc", "yyy");
        assertFirstCn("cn=a,cn=b", "a");
        assertFirstCn("cn=Cc,cn=Bb,cn=Aa", "Cc");
        assertFirstCn("cn=imap.gmail.com", "imap.gmail.com");
    }

    public void testGetFirstCnWithOid() {
        assertFirstCn("2.5.4.3=a,ou=xxx", "a");
    }

    public void testGetFirstCnWithQuotedStrings() {
        assertFirstCn("cn=\"\\\" a ,=<>#;\"", "\" a ,=<>#;");
        assertFirstCn("cn=abc\\,def", "abc,def");
    }

    public void testGetFirstCnWithUtf8() {
        assertFirstCn("cn=Lu\\C4\\8Di\\C4\\87", "\u004c\u0075\u010d\u0069\u0107");
    }

    public void testGetFirstCnWithWhitespace() {
        assertFirstCn("ou=a, cn=  a  b  ,o=x", "a  b");
        assertFirstCn("cn=\"  a  b  \" ,o=x", "  a  b  ");
    }

    private void assertFirstCn(String dn, String expected) {
        X500Principal principal = new X500Principal(dn);
        assertEquals(dn, expected, new DistinguishedNameParser(principal).findMostSpecific("cn"));
    }
}
