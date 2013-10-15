/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.libimobiledevice;

import java.io.IOException;

import org.robovm.libimobiledevice.binding.ByteArray;
import org.robovm.libimobiledevice.binding.ByteArrayOut;
import org.robovm.libimobiledevice.binding.IntOut;
import org.robovm.libimobiledevice.binding.LibIMobileDevice;
import org.robovm.libimobiledevice.binding.PlistRef;
import org.robovm.libimobiledevice.binding.PlistRefOut;

import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;

/**
 * 
 */
class PlistUtil {

    public static NSObject toJavaPlist(PlistRef plist) throws IOException {
        PlistRefOut plistOut = new PlistRefOut();
        ByteArrayOut plistBinOut = new ByteArrayOut();
        IntOut lengthOut = new IntOut();
        try {
            LibIMobileDevice.plist_to_bin(plist, plistBinOut, lengthOut);
            LibIMobileDevice.plist_free(plist);
            int length = lengthOut.getValue();
            ByteArray plistBin = plistBinOut.getValue();
            if (length == 0 || plistBin == null) {
                return null;
            }
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = plistBin.get(i);
            }
            return PropertyListParser.parse(data);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            plistOut.delete();
            LibIMobileDevice.delete_ByteArrayOut_value(plistBinOut);
            plistBinOut.delete();
            lengthOut.delete();
        }
    }
    
}
