/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler.hash;

import org.robovm.compiler.Strings;

/**
 * @author niklas
 *
 */
public class ModifiedUtf8HashFunction implements HashFunction<String> {
    @Override
    public int hash(String k) {
        byte[] data = Strings.stringToModifiedUtf8Z(k);
        return MurmurHash3.murmurhash3_x86_32(data, 0, data.length, 0x1ce79e5c);
    }
}
