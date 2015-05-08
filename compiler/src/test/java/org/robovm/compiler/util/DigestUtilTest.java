/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.compiler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link DigestUtil}.
 */
public class DigestUtilTest {

    @Test
    public void testSha1() {
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", DigestUtil.sha1(""));
        assertEquals("8843d7f92416211de9ebb963ff4ce28125932878", DigestUtil.sha1("foobar"));
    }

}
