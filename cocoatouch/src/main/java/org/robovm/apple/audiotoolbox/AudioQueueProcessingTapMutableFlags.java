package org.robovm.apple.audiotoolbox;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

public class AudioQueueProcessingTapMutableFlags extends Struct<AudioQueueProcessingTapMutableFlags> {

    public AudioQueueProcessingTapFlags get() {
        return new AudioQueueProcessingTapFlags(getValue());
    }
    
    public void set(AudioQueueProcessingTapFlags flags) {
        setValue((int)flags.value());
    }
    
    @StructMember(0)
    private native int getValue();
    
    @StructMember(0)
    private native void setValue(int value);
}
