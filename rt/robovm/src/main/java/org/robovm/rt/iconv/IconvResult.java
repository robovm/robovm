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

    private int bytesReadFromSource;

    private int bytesWrittenToDestination;

    private ConversionResult resultCode;

    /**
     * @return number of bytes processed in source
     */
    public int getBytesReadFromSource() {
        return bytesReadFromSource;
    }

    /**
     * Sets number of bytes processed in source
     */
    public void setBytesReadFromSource(int bytesWrittenFromSource) {
        this.bytesReadFromSource = bytesWrittenFromSource;
    }

    /**
     * @return number of bytes processed in destination
     */
    public int getBytesWrittenToDestination() {
        return bytesWrittenToDestination;
    }

    /**
     * Sets number of bytes processed in destination
     */
    public void setBytesWrittenToDestination(int bytesWrittenToDestination) {
        this.bytesWrittenToDestination = bytesWrittenToDestination;
    }

    /**
     * Gets result code of operation
     */
    public ConversionResult getResultCode() {
        return resultCode;
    }

    /**
     * Sets result code  
     */
    public void setResultCode(ConversionResult resultCode) {
        this.resultCode = resultCode;
    }

}
