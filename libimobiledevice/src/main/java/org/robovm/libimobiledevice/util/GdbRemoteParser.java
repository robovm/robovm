/*
 * Copyright (C) 2013 Trillian Mobile AB
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

import java.util.ArrayList;
import java.util.List;


public class GdbRemoteParser { 
    int hashPosition = -1;
    int position = 0;
    boolean waitingForChecksum = false;
    byte[] buffer = new byte[4096];
    
    public List<byte[]> parse(byte[] bytes) {
        return parse(bytes, 0, bytes.length);
    }
    /**
     * parses the next n bytes and returns a list of byte[] each 
     * representing an individual message
     */
    public List<byte[]> parse(byte[] bytes, int offset, int length) {
        List<byte[]> result = new ArrayList<>();
        
        for(int i = offset; i < offset + length; i++) {
            // enlarge buffer
            if(buffer.length == position) {
                byte[] tmp = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, buffer.length);
                buffer = tmp;
            }
            
            // read the next byte to the buffer 
            byte b = bytes[i];
            buffer[position++] = b;
            
            // if we are inside a message, and we've read the hash
            // symbol plus the 2 byte checksum after the hash symbol
            // we've found a new message. Add it to the results
            if(waitingForChecksum && (position - hashPosition) == 2) {
                waitingForChecksum = false;
                byte[] tmp = new byte[position];
                System.arraycopy(buffer, 0, tmp, 0, position);
                position = 0;
                result.add(tmp);
            } else if(b == '#') {
                waitingForChecksum = true;
                hashPosition = position;
                
            }
        }
        return result;
    }
}
