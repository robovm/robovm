/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.audiounit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;

import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioUnit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioUnit/*</name>*/ 
    extends /*<extends>*/AudioComponentInstance/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioUnitPtr extends Ptr<AudioUnit, AudioUnitPtr> {}/*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static LongMap<AUPropertyListener> propertyListeners = new LongMap<>();
    private static final java.lang.reflect.Method cbPropertyChanged;
    private static LongMap<AURenderCallback> renderCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbRender;
    private static LongMap<AUHostCallback> hostCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbHostGetBeatAndTempo;
    private static final java.lang.reflect.Method cbHostGetMusicalTimeLocation;
    private static final java.lang.reflect.Method cbHostGetTransportState;
    private static final java.lang.reflect.Method cbHostGetTransportState2;
    private static LongMap<AUInputSamplesInOutputCallback> isioCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbInputSamplesInOutput;
    private static LongMap<AUOutputMIDICallback> midiCallbacks;
    private static final java.lang.reflect.Method cbMIDIEvent;
    private static final java.lang.reflect.Method cbMIDISysEx;
    
    static {
        try {
            cbPropertyChanged = AudioUnit.class.getDeclaredMethod("cbPropertyChanged", Long.TYPE, AudioUnit.class, AUPropertyID.class, AUScope.class, Integer.TYPE);
            cbRender = AudioUnit.class.getDeclaredMethod("cbRender", Long.TYPE, AUMutableRenderActionFlags.class, AudioTimeStamp.class, Integer.TYPE, Integer.TYPE, AudioBufferList.class);
            cbHostGetBeatAndTempo = AudioUnit.class.getDeclaredMethod("cbHostGetBeatAndTempo", Long.TYPE, DoublePtr.class, DoublePtr.class);
            cbHostGetMusicalTimeLocation = AudioUnit.class.getDeclaredMethod("cbHostGetMusicalTimeLocation", Long.TYPE, IntPtr.class, FloatPtr.class, IntPtr.class, DoublePtr.class);
            cbHostGetTransportState = AudioUnit.class.getDeclaredMethod("cbHostGetTransportState", Long.TYPE, BooleanPtr.class, BooleanPtr.class, DoublePtr.class, BooleanPtr.class, DoublePtr.class, DoublePtr.class);
            cbHostGetTransportState2 = AudioUnit.class.getDeclaredMethod("cbHostGetTransportState", Long.TYPE, BooleanPtr.class, BooleanPtr.class, BooleanPtr.class, DoublePtr.class, BooleanPtr.class, DoublePtr.class, DoublePtr.class);
            cbInputSamplesInOutput = AudioUnit.class.getDeclaredMethod("cbInputSamplesInOutput", Long.TYPE, AudioTimeStamp.class, Double.TYPE, Double.TYPE);
            cbMIDIEvent = AudioUnit.class.getDeclaredMethod("cbMIDIEvent", Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            cbMIDISysEx = AudioUnit.class.getDeclaredMethod("cbMIDISysEx", Long.TYPE, BytePtr.class, Integer.TYPE);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(AudioUnit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioUnit() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbPropertyChanged(@Pointer long refCon, AudioUnit unit, AUPropertyID id, AUScope scope, int element) {
        synchronized (propertyListeners) {
            propertyListeners.get(refCon).onChange(unit, id, scope, element);
        }
    }
    @Callback
    private static OSStatus cbRender(@Pointer long refCon, AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int busNumber, int numberFrames, AudioBufferList data) {
        synchronized (renderCallbacks) {
            return renderCallbacks.get(refCon).onRender(actionFlags, timeStamp, busNumber, numberFrames, data);
        }
    }
    @Callback
    private static OSStatus cbHostGetBeatAndTempo(@Pointer long userData, DoublePtr currentBeat, DoublePtr currentTempo) {
        synchronized (hostCallbacks) {
            return hostCallbacks.get(userData).getBeatAndTempo(currentBeat, currentTempo);
        }
    }
    @Callback
    private static OSStatus cbHostGetMusicalTimeLocation(@Pointer long userData, IntPtr deltaSampleOffsetToNextBeat, FloatPtr timeSigNumerator,
            IntPtr timeSigDenominator, DoublePtr currentMeasureDownBeat) {
        synchronized (hostCallbacks) {
            return hostCallbacks.get(userData).getMusicalTimeLocation(deltaSampleOffsetToNextBeat, timeSigNumerator, timeSigDenominator, currentMeasureDownBeat);
        }
    }
    @Callback
    private static OSStatus cbHostGetTransportState(@Pointer long userData, BooleanPtr isPlaying, BooleanPtr transportStateChanged,
            DoublePtr currentSampleInTimeLine, BooleanPtr isCycling, DoublePtr cycleStartBeat, DoublePtr cycleEndBeat) {
        synchronized (hostCallbacks) {
            return hostCallbacks.get(userData).getTransportState(isPlaying, transportStateChanged, currentSampleInTimeLine, isCycling, cycleStartBeat, cycleEndBeat);
        }
    }
    @Callback
    private static OSStatus cbHostGetTransportState(@Pointer long userData, BooleanPtr isPlaying, BooleanPtr isRecording, BooleanPtr transportStateChanged,
            DoublePtr currentSampleInTimeLine, BooleanPtr isCycling, DoublePtr cycleStartBeat, DoublePtr cycleEndBeat) {
        synchronized (hostCallbacks) {
            return hostCallbacks.get(userData).getTransportState2(isPlaying, isRecording, transportStateChanged, currentSampleInTimeLine, isCycling, cycleStartBeat, cycleEndBeat);
        }
    }
    @Callback
    private static void cbInputSamplesInOutput(@Pointer long refCon, AudioTimeStamp outputTimeStamp, double inputSample, double numberInputSamples) {
        synchronized (isioCallbacks) {
            isioCallbacks.get(refCon).invoke(outputTimeStamp, inputSample, numberInputSamples);
        }
    }
    @Callback
    private static void cbMIDIEvent(@Pointer long userData, int status, int data1, int data2, int offsetSampleFrame) {
        synchronized (midiCallbacks) {
            midiCallbacks.get(userData).onMIDIEvent(status, data1, data2, offsetSampleFrame);
        }
    }
    @Callback
    private static void cbMIDISysEx(@Pointer long userData, BytePtr data, int length) {
        synchronized (midiCallbacks) {
            midiCallbacks.get(userData).onMIDISysEx(data.toByteArray(length));
        }
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static AudioUnit create(AudioComponent component) {
        AudioComponentInstance result = AudioComponentInstance.create(component);
        if (result != null) {
            return result.as(AudioUnit.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean canDo(AUSelector inSelectorID) {
        return super.canDo((short) inSelectorID.value());
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean canDo(AUOutputSelector inSelectorID) {
        return super.canDo((short) inSelectorID.value());
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AUPropertyID id, AUScope scope, int element) {
        IntPtr ptr = new IntPtr();
        getPropertyInfo(id, scope, element, ptr, null);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AUPropertyID id, AUScope scope, int element) {
        BooleanPtr ptr = new BooleanPtr();
        getPropertyInfo(id, scope, element, null, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AUPropertyID id, AUScope scope, int element, Class<T> type) {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        getProperty(id, scope, element, data.as(VoidPtr.class), dataSize);
        return data;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> OSStatus setProperty(AUPropertyID id, AUScope scope, int element, Struct<T> data) {
        return setProperty(id, scope, element, data == null ? null : data.as(VoidPtr.class), data == null ? 0 : Struct.sizeOf(data));
    }
    
    public int getPropertyAsInt(AUPropertyID id, AUScope scope, int element) {
        IntPtr ptr = getProperty(id, scope, element, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(AUPropertyID id, AUScope scope, int element) {
        LongPtr ptr = getProperty(id, scope, element, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(AUPropertyID id, AUScope scope, int element) {
        FloatPtr ptr = getProperty(id, scope, element, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(AUPropertyID id, AUScope scope, int element) {
        DoublePtr ptr = getProperty(id, scope, element, DoublePtr.class);
        return ptr.get();
    }
    public OSStatus setProperty(AUPropertyID id, AUScope scope, int element, int value) {
        return setProperty(id, scope, element, new IntPtr(value));
    }
    public OSStatus setProperty(AUPropertyID id, AUScope scope, int element, long value) {
        return setProperty(id, scope, element, new LongPtr(value));
    }
    public OSStatus setProperty(AUPropertyID id, AUScope scope, int element, float value) {
        return setProperty(id, scope, element, new FloatPtr(value));
    }
    public OSStatus setProperty(AUPropertyID id, AUScope scope, int element, double value) {
        return setProperty(id, scope, element, new DoublePtr(value));
    }
    
    
    public AudioStreamBasicDescription getStreamFormat(AUScope scope, int element) {
        return getProperty(AUGenericProperty.StreamFormat, scope, element, AudioStreamBasicDescription.class);
    }
    public OSStatus setStreamFormat(AUScope scope, int element, AudioStreamBasicDescription streamFormat) {
        return setProperty(AUGenericProperty.StreamFormat, scope, element, streamFormat);
    }
    public OSStatus makeConnection(AUScope scope, int element, AUConnection connection) {
        return setProperty(AUGenericProperty.MakeConnection, scope, element, connection);
    }
    
    public OSStatus setRenderCallback(AUScope scope, int element, AURenderCallback callback) {
        long cid = callbackId.getAndIncrement();
        
        AURenderCallbackStruct struct = new AURenderCallbackStruct(new FunctionPtr(cbRender), cid);
        OSStatus result = setProperty(AUGenericProperty.SetRenderCallback, scope, element, struct);
        if (result.equals(0)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, callback);
            }
        }
        return result;
    }
    public OSStatus setHostCallback(AUScope scope, int element, AUHostCallback callback) {
        long cid = callbackId.getAndIncrement();
        
        AUHostCallbackInfo struct = new AUHostCallbackInfo(cid, new FunctionPtr(cbHostGetBeatAndTempo), 
                new FunctionPtr(cbHostGetMusicalTimeLocation), new FunctionPtr(cbHostGetTransportState), new FunctionPtr(cbHostGetTransportState2));
        OSStatus result = setProperty(AUGenericProperty.HostCallbacks, scope, element, struct);
        if (result.equals(0)) {
            synchronized (hostCallbacks) {
                hostCallbacks.put(cid, callback);
            }
        }
        return result;
    }
    public AUInputSamplesInOutputCallback getInputSamplesInOutputCallback(AUScope scope, int element) {
        AUInputSamplesInOutputCallbackStruct struct = getProperty(AUGenericProperty.InputSamplesInOutput, scope, element, AUInputSamplesInOutputCallbackStruct.class);
        AUInputSamplesInOutputCallback result = null;
        if (struct != null) {
            synchronized (isioCallbacks) {
                result = isioCallbacks.get(struct.getUserData());
            }
        }
        return result;
    }
    public OSStatus setInputSamplesInOutputCallback(AUScope scope, int element, AUInputSamplesInOutputCallback callback) {
        long cid = callbackId.getAndIncrement();
        
        AUInputSamplesInOutputCallbackStruct struct = new AUInputSamplesInOutputCallbackStruct(new FunctionPtr(cbInputSamplesInOutput), cid);
        OSStatus result = setProperty(AUGenericProperty.InputSamplesInOutput, scope, element, struct);
        if (result.equals(0)) {
            synchronized (isioCallbacks) {
                isioCallbacks.put(cid, callback);
            }
        }
        return result;
    }
    public OSStatus scheduleAudioSlice(AUScope scope, int element, AUScheduledAudioSlice slice) {
        return setProperty(AUScheduledSoundPlayerProperty.ScheduleAudioSlice, scope, element, slice);
    }
    public OSStatus scheduleAudioFileRegion(AUScope scope, int element, AUScheduledAudioFileRegion region) {
        return setProperty(AUAudioFilePlayerProperty.ScheduledFileRegion, scope, element, region);
    }
    public AUOutputMIDICallback getMIDICallbacks(AUScope scope, int element) {
        AUOutputMIDICallbacksStruct struct = getProperty(AUOutputProperty.MIDICallbacks, scope, element, AUOutputMIDICallbacksStruct.class);
        AUOutputMIDICallback result = null;
        if (struct != null) {
            synchronized (midiCallbacks) {
                result = midiCallbacks.get(struct.getUserData());
            }
        }
        return result;
    }
    public OSStatus setMIDICallbacks(AUScope scope, int element, AUOutputMIDICallback callback) {
        OSStatus result = null;
        AUOutputMIDICallbacksStruct struct = null;
        if (callback == null) {
            struct = getProperty(AUOutputProperty.MIDICallbacks, scope, element, AUOutputMIDICallbacksStruct.class);
            if (struct != null) {
                setProperty(AUOutputProperty.MIDICallbacks, scope, element, null);
                synchronized (midiCallbacks) {
                    midiCallbacks.remove(struct.getUserData());
                }
            }
        } else {
            long cid = callbackId.getAndIncrement();
            
            struct = new AUOutputMIDICallbacksStruct(cid, new FunctionPtr(cbMIDIEvent), new FunctionPtr(cbMIDISysEx));
            result = setProperty(AUOutputProperty.MIDICallbacks, scope, element, struct);
            if (result.equals(0)) {
                synchronized (midiCallbacks) {
                    midiCallbacks.put(cid, callback);
                }
            }
        }
        return result;
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus addPropertyListener(AUPropertyID id, AUPropertyListener listener) {
        long cid = callbackId.getAndIncrement();
        
        OSStatus result = addPropertyListener(id, new FunctionPtr(cbPropertyChanged), cid);
        if (result.equals(0)) {
            synchronized (propertyListeners) {
                propertyListeners.put(cid, listener);
            }
        }
        return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus removePropertyListener(AUPropertyID id, AUPropertyListener listener) {
        synchronized (propertyListeners) {
            for (Iterator<LongMap.Entry<AUPropertyListener>> it = propertyListeners.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AUPropertyListener> entry = it.next();
                if (entry.value == listener) {
                    return removePropertyListener(id, new FunctionPtr(cbPropertyChanged), entry.key);
                }
            }
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus addRenderNotify(AURenderCallback callback) {
        long cid = callbackId.getAndIncrement();
        
        OSStatus result = addRenderNotify(new FunctionPtr(cbRender), cid);
        if (result.equals(0)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, callback);
            }
        }
        return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public OSStatus removeRenderNotify(AURenderCallback callback) {
        synchronized (renderCallbacks) {
            for (Iterator<LongMap.Entry<AURenderCallback>> it = renderCallbacks.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AURenderCallback> entry = it.next();
                if (entry.value == callback) {
                    return removeRenderNotify(new FunctionPtr(cbRender), entry.key);
                }
            }
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public float getParameter(AUPropertyID id, AUScope scope, int element) {
        FloatPtr ptr = new FloatPtr();
        getParameter(id, scope, element, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    private OSStatus publish(AudioComponentDescription desc, String name, int version) {
        return publish(desc, name, version, this);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitInitialize", optional=true)
    public native OSStatus initialize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitUninitialize", optional=true)
    public native OSStatus uninitialize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetPropertyInfo", optional=true)
    private native OSStatus getPropertyInfo(AUPropertyID id, AUScope scope, int element, IntPtr outDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetProperty", optional=true)
    protected native OSStatus getProperty(AUPropertyID id, AUScope scope, int element, VoidPtr outData, IntPtr ioDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitSetProperty", optional=true)
    protected native OSStatus setProperty(AUPropertyID id, AUScope scope, int element, VoidPtr inData, int dataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitAddPropertyListener", optional=true)
    protected native OSStatus addPropertyListener(AUPropertyID id, FunctionPtr proc, @Pointer long procUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRemovePropertyListenerWithUserData", optional=true)
    protected native OSStatus removePropertyListener(AUPropertyID id, FunctionPtr proc, @Pointer long procUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitAddRenderNotify", optional=true)
    protected native OSStatus addRenderNotify(FunctionPtr proc, @Pointer long procUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRemoveRenderNotify", optional=true)
    protected native OSStatus removeRenderNotify(FunctionPtr proc, @Pointer long procUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetParameter", optional=true)
    protected native OSStatus getParameter(AUPropertyID id, AUScope scope, int element, FloatPtr outValue);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitSetParameter", optional=true)
    public native OSStatus setParameter(AUPropertyID id, AUScope scope, int element, float value, int bufferOffsetInFrames);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRender", optional=true)
    public native OSStatus render(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int outputBusNumber, int numberFrames, AudioBufferList data);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioUnitProcess", optional=true)
    public native OSStatus process(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int numberFrames, AudioBufferList data);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioUnitProcessMultiple", optional=true)
    protected native OSStatus processMultiple(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int numberFrames, int inNumberInputBufferLists, AudioBufferList.AudioBufferListPtr inInputBufferLists, int inNumberOutputBufferLists, AudioBufferList.AudioBufferListPtr ioOutputBufferLists);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitReset", optional=true)
    public native OSStatus reset(AUScope scope, int inElement);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitPublish", optional=true)
    private static native OSStatus publish(AudioComponentDescription inDesc, String inName, int inVersion, AudioUnit inOutputUnit);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitGetHostIcon", optional=true)
    public native UIImage getOutputHostIcon(float desiredPointSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitStart", optional=true)
    public native OSStatus startOutput();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitStop", optional=true)
    public native OSStatus stopOutput();
    /*</methods>*/
}
