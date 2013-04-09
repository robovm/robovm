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

import java.util.Arrays;
import javax.net.ssl.SSLParameters;
import junit.framework.TestCase;

public class SSLParametersTest extends TestCase {

    public void test_SSLParameters_emptyConstructor() {
        SSLParameters p = new SSLParameters();
        assertNull(p.getCipherSuites());
        assertNull(p.getProtocols());
        assertFalse(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());
    }

    public void test_SSLParameters_cipherSuitesConstructor() {
        String[] cipherSuites = new String[] { "foo", null, "bar" };
        SSLParameters p = new SSLParameters(cipherSuites);
        assertNotNull(p.getCipherSuites());
        assertNotSame(cipherSuites, p.getCipherSuites());
        assertEquals(Arrays.asList(cipherSuites), Arrays.asList(p.getCipherSuites()));
        assertNull(p.getProtocols());
        assertFalse(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());
    }

    public void test_SSLParameters_cpherSuitesProtocolsConstructor() {
        String[] cipherSuites = new String[] { "foo", null, "bar" };
        String[] protocols = new String[] { "baz", null, "qux" };
        SSLParameters p = new SSLParameters(cipherSuites, protocols);
        assertNotNull(p.getCipherSuites());
        assertNotNull(p.getProtocols());
        assertNotSame(cipherSuites, p.getCipherSuites());
        assertNotSame(protocols, p.getProtocols());
        assertEquals(Arrays.asList(cipherSuites), Arrays.asList(p.getCipherSuites()));
        assertEquals(Arrays.asList(protocols), Arrays.asList(p.getProtocols()));
        assertFalse(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());
    }

    public void test_SSLParameters_CipherSuites() {
        SSLParameters p = new SSLParameters();
        assertNull(p.getCipherSuites());

        // confirm clone on input
        String[] cipherSuites = new String[] { "fnord" };
        String[] copy = cipherSuites.clone();
        p.setCipherSuites(copy);
        copy[0] = null;
        assertEquals(Arrays.asList(cipherSuites), Arrays.asList(p.getCipherSuites()));

        // confirm clone on output
        assertNotSame(p.getCipherSuites(), p.getCipherSuites());
    }

    public void test_SSLParameters_Protocols() {
        SSLParameters p = new SSLParameters();
        assertNull(p.getProtocols());

        // confirm clone on input
        String[] protocols = new String[] { "fnord" };
        String[] copy = protocols.clone();
        p.setProtocols(copy);
        copy[0] = null;
        assertEquals(Arrays.asList(protocols), Arrays.asList(p.getProtocols()));

        // confirm clone on output
        assertNotSame(p.getProtocols(), p.getProtocols());
    }

    public void test_SSLParameters_ClientAuth() {
        SSLParameters p = new SSLParameters();
        assertFalse(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());

        // confirm turning one on by itself
        p.setWantClientAuth(true);
        assertTrue(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());

        // confirm turning setting on toggles the other
        p.setNeedClientAuth(true);
        assertFalse(p.getWantClientAuth());
        assertTrue(p.getNeedClientAuth());

        // confirm toggling back
        p.setWantClientAuth(true);
        assertTrue(p.getWantClientAuth());
        assertFalse(p.getNeedClientAuth());
    }
}
