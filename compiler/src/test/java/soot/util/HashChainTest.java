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
package soot.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests changes made to {@link HashChain} for RoboVM.
 */
public class HashChainTest {

    @Test
    public void testFollows() {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();
        Object o5 = new Object();
        HashChain<Object> h = new HashChain<>();
        h.addAll(Arrays.asList(o1, o2, o3, o4));
        
        assertEquals(h.followsOld(o1, o1), h.follows(o1, o1));
        assertEquals(h.followsOld(o2, o1), h.follows(o2, o1));
        assertEquals(h.followsOld(o1, o2), h.follows(o1, o2));
        assertEquals(h.followsOld(o1, o5), h.follows(o1, o5));
        assertEquals(h.followsOld(o1, null), h.follows(o1, null));
        
        try {
            h.followsOld(o5, o1);
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {}
        try {
            h.follows(o5, o1);
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {}

        assertEquals(h.followsOld(null, o1), h.follows(null, o1));
        assertEquals(h.followsOld(null, o5), h.follows(null, o5));
    }

}
