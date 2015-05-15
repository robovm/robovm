package org.robovm.apple.audiounit;

public interface AUOutputMIDICallback {
    void onMIDIEvent(int status, int data1, int data2, int offsetSampleFrame);

    void onMIDISysEx(byte[] data);
}
