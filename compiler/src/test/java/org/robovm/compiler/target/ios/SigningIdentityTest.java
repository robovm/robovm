/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.target.ios;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Tests {@link SigningIdentity}.
 */
public class SigningIdentityTest {

    @Test
    public void testParseWithRevokedCerts() throws Exception {
        List<SigningIdentity> l = SigningIdentity.parse(IOUtils.toString(getClass().getResourceAsStream("codesigning_dump.txt")));
        assertEquals(1, l.size());
        assertEquals("97BBE93758B08A8FE7B970354D8B6D9821CF9108", l.get(0).getFingerprint());
        assertEquals("iPhone Developer: Gerrard Eliot (KDH76S9URT)", l.get(0).getName());
    }

}
