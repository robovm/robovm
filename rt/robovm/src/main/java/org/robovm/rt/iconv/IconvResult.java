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
