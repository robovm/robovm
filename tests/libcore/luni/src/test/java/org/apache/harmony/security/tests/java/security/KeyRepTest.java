/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Vladimir N. Molotkov
 * @version $Revision$
 */

package org.apache.harmony.security.tests.java.security;

import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.security.KeyRep;
import java.security.Security;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

/**
 *
 *
 */
public class KeyRepTest extends TestCase {

    private static final Set<String> keyFactoryAlgorithm;
    static {
        keyFactoryAlgorithm = Security.getAlgorithms("KeyFactory");
    }

    public final void testKeyRep01() {
        try {
            assertNotNull(new KeyRep(KeyRep.Type.SECRET, "", "", new byte[] {}));
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }

        try {
            assertNotNull(new KeyRep(KeyRep.Type.PUBLIC, "", "", new byte[] {}));
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }

        try {
            assertNotNull(new KeyRep(KeyRep.Type.PRIVATE, "", "", new byte[] {}));
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

    public final void testKeyRep02() {
        try {
            new KeyRep(null, "", "", new byte[] {});
            fail("NullPointerException has not been thrown (type)");
        } catch (NullPointerException ok) {

        }
        try {
            new KeyRep(KeyRep.Type.SECRET, null, "", new byte[] {});
            fail("NullPointerException has not been thrown (alg)");
        } catch (NullPointerException ok) {

        }
        try {
            new KeyRep(KeyRep.Type.PRIVATE, "", null, new byte[] {});
            fail("NullPointerException has not been thrown (format)");
        } catch (NullPointerException ok) {

        }
        try {
            new KeyRep(KeyRep.Type.PUBLIC, "", "", null);
            fail("NullPointerException has not been thrown (encoding)");
        } catch (NullPointerException ok) {

        }
    }

    public final void testReadResolve01() throws ObjectStreamException {
        KeyRepChild kr = new KeyRepChild(KeyRep.Type.SECRET, "", "",
                new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (no format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.SECRET, "", "X.509", new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (unacceptable format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.SECRET, "", "RAW", new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (empty key)");
        } catch (NotSerializableException ok) {

        }
    }

    public final void testReadResolve02() throws ObjectStreamException {
        KeyRepChild kr = new KeyRepChild(KeyRep.Type.PUBLIC, "", "",
                new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (no format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.PUBLIC, "", "RAW", new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (unacceptable format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.PUBLIC, "bla-bla", "X.509",
                new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (unknown KeyFactory algorithm)");
        } catch (NotSerializableException ok) {

        }
    }

    public final void testReadResolve03() throws ObjectStreamException {
        KeyRepChild kr = new KeyRepChild(KeyRep.Type.PRIVATE, "", "",
                new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (no format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.PRIVATE, "", "RAW", new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (unacceptable format)");
        } catch (NotSerializableException ok) {

        }

        kr = new KeyRepChild(KeyRep.Type.PRIVATE, "bla-bla", "PKCS#8",
                new byte[] {});
        try {
            kr.readResolve();
            fail("NotSerializableException has not been thrown (unknown KeyFactory algorithm)");
        } catch (NotSerializableException ok) {

        }
    }

    public final void testReadResolve04() throws ObjectStreamException {
        if (keyFactoryAlgorithm.isEmpty()) {
            System.err.println(getName()
                    + ": skipped - no KeyFactory algorithms available");
            return;
        } else {
        }
        for (Iterator<String> i = keyFactoryAlgorithm.iterator(); i.hasNext();) {
            KeyRepChild kr = new KeyRepChild(KeyRep.Type.PUBLIC, i.next(),
                    "X.509", new byte[] { 1, 2, 3 });
            try {
                kr.readResolve();
                fail("NotSerializableException has not been thrown (no format)");
            } catch (NotSerializableException ok) {

            }
        }
    }

    public final void testReadResolve05() throws ObjectStreamException {
        if (keyFactoryAlgorithm.isEmpty()) {
            System.err.println(getName()
                    + ": skipped - no KeyFactory algorithms available");
            return;
        } else {
        }
        for (Iterator<String> i = keyFactoryAlgorithm.iterator(); i.hasNext();) {
            KeyRepChild kr = new KeyRepChild(KeyRep.Type.PRIVATE, i.next(),
                    "PKCS#8", new byte[] { 1, 2, 3 });
            try {
                kr.readResolve();
                fail("NotSerializableException has not been thrown (no format)");
            } catch (NotSerializableException ok) {

            }
        }
    }

    class KeyRepChild extends KeyRep {
        public KeyRepChild(KeyRep.Type type, String algorithm, String format,
                byte[] encoded) {
            super(type, algorithm, format, encoded);
        }

        public Object readResolve() throws ObjectStreamException {
            return super.readResolve();
        }

    }
}
