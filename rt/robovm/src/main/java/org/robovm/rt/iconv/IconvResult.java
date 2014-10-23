/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
