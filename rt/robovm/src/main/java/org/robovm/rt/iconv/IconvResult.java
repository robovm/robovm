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
package org.robovm.rt.iconv;

import org.robovm.rt.iconv.IconvProvider.ConversionResult;

/**
 * IconvResult - Result of an encoding and decoding operation 
 */
public class IconvResult {

    private int bytesWrittenFromSource;

    private int bytesWrittenToDestination;

    private ConversionResult resultCode;

    public int getBytesWrittenFromSource() {
        return bytesWrittenFromSource;
    }

    public void setBytesWrittenFromSource(int bytesWrittenFromSource) {
        this.bytesWrittenFromSource = bytesWrittenFromSource;
    }

    public int getBytesWrittenToDestination() {
        return bytesWrittenToDestination;
    }

    public void setBytesWrittenToDestination(int bytesWrittenToDestination) {
        this.bytesWrittenToDestination = bytesWrittenToDestination;
    }

    public ConversionResult getResultCode() {
        return resultCode;
    }

    public void setResultCode(ConversionResult resultCode) {
        this.resultCode = resultCode;
    }

}
