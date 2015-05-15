/*
 * Copyright (C) 2013 RoboVM AB
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
package org.robovm.libimobiledevice.util;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class GdbRemoteParserTest {
    @Test
    public void testParser() throws UnsupportedEncodingException {
        GdbRemoteParser parser = new GdbRemoteParser();

        assertEquals(Collections.EMPTY_LIST, parser.parse("+".getBytes("ASCII")));
        assertEquals(Collections.EMPTY_LIST, parser.parse("QStartNo".getBytes("ASCII")));
        List<byte[]> result = parser.parse("AckMode#b0$QStartNoAckMode#b0".getBytes("ASCII"));
        List<byte[]> expected = Arrays.asList("+QStartNoAckMode#b0".getBytes("ASCII"), "$QStartNoAckMode#b0".getBytes("ASCII"));
        assertEquals(expected.size(), result.size());
        for(int i = 0; i < result.size(); i++) {
            assertEquals(expected.size(), result.size());
        }
        
        result = parser.parse("+QStartNoAckMode#b0$QStartNoAckMode#b0$yxz".getBytes("ASCII"));
        assertEquals(expected.size(), result.size());
        for(int i = 0; i < result.size(); i++) {
            assertEquals(expected.size(), result.size());
        }
        
        expected = Arrays.asList("yxz#00".getBytes("ASCII"));
        result = parser.parse("#00".getBytes("ASCII"));
        assertEquals(expected.size(), result.size());
        for(int i = 0; i < result.size(); i++) {
            assertEquals(expected.size(), result.size());
        }
    }
}
