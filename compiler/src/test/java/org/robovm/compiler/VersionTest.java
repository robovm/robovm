/*
 * Copyright (C) 2014 RoboVM AB
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
package org.robovm.compiler;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link Version}.
 */
public class VersionTest {

    @Test
    public void testToInt() {
        assertEquals(15701, Version.toLong("0.0.15"));
        assertEquals(1001001701, Version.toLong("1.1.1"));
        assertEquals(123456789701L, Version.toLong("123.456.789"));
        assertEquals(1002003000, Version.toLong("1.2.3-SNAPSHOT"));
        assertEquals(1002003101, Version.toLong("1.2.3-alpha-01"));
        assertEquals(1002003323, Version.toLong("1.2.3-beta-23"));
        assertEquals(1002003507, Version.toLong("1.2.3-rc-07"));
    }

}
