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
import org.robovm.rt.annotation.*;
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
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
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
            cbPropertyChanged = AudioUnit.class.getDeclaredMethod("cbPropertyChanged", Long.TYPE, AudioUnit.class, AUPropertyType.class, AUScope.class, Integer.TYPE);
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
    private static void cbPropertyChanged(@Pointer long refCon, AudioUnit unit, AUPropertyType id, AUScope scope, int element) {
        synchronized (propertyListeners) {
            propertyListeners.get(refCon).onChange(unit, id, scope, element);
        }
    }
    @Callback
    private static OSStatus cbRender(@Pointer long refCon, AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int busNumber, int numberFrames, AudioBufferList data) {
        synchronized (renderCallbacks) {
            OSStatus status = OSStatus.NO_ERR;
            try {
                renderCallbacks.get(refCon).onRender(actionFlags, timeStamp, busNumber, numberFrames, data);
            } catch (OSStatusException e) {
                status = e.getStatus();
            }
            return status;
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
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static AudioUnit create(AudioComponent component) throws OSStatusException {
        AudioComponentInstance result = AudioComponentInstance.create(component);
        if (result != null) {
            return result.as(AudioUnit.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean canDo(AUSelector selector) {
        return super.canDo((short) selector.value());
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public boolean canDo(AUOutputSelector selector) {
        return super.canDo((short) selector.value());
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void initialize() throws OSStatusException {
        OSStatus status = initialize0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void uninitialize() throws OSStatusException {
        OSStatus status = uninitialize0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AUPropertyType type, AUScope scope) throws OSStatusException {
        return getPropertySize(type, scope, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public int getPropertySize(AUPropertyType type, AUScope scope, int element) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(type, scope, element, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AUPropertyType type, AUScope scope) throws OSStatusException {
        return isPropertyWritable(type, scope, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPropertyWritable(AUPropertyType type, AUScope scope, int element) throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getPropertyInfo0(type, scope, element, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AUPropertyType type, Class<T> returnType, AUScope scope) throws OSStatusException {
        return getProperty(type, returnType, scope, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> T getProperty(AUPropertyType type, Class<T> returnType, AUScope scope, int element) throws OSStatusException {
        T data = Struct.allocate(returnType);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(type, scope, element, data.as(VoidPtr.class), dataSize);
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> void setProperty(AUPropertyType type, Struct<T> data, AUScope scope) throws OSStatusException {
        setProperty(type, data, scope, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public <T extends Struct<T>> void setProperty(AUPropertyType type, Struct<T> data, AUScope scope, int element) throws OSStatusException {
        OSStatus status = setProperty0(type, scope, element, data == null ? null : data.as(VoidPtr.class), data == null ? 0 : Struct.sizeOf(data));
        OSStatusException.throwIfNecessary(status);
    }
    
    public int getPropertyAsInt(AUPropertyType type, AUScope scope) throws OSStatusException {
        IntPtr ptr = getProperty(type, IntPtr.class, scope);
        return ptr.get();
    }
    public long getPropertyAsLong(AUPropertyType type, AUScope scope) throws OSStatusException {
        LongPtr ptr = getProperty(type, LongPtr.class, scope);
        return ptr.get();
    }
    public float getPropertyAsFloat(AUPropertyType type, AUScope scope) throws OSStatusException {
        FloatPtr ptr = getProperty(type, FloatPtr.class, scope);
        return ptr.get();
    }
    public double getPropertyAsDouble(AUPropertyType type, AUScope scope) throws OSStatusException {
        DoublePtr ptr = getProperty(type, DoublePtr.class, scope);
        return ptr.get();
    }
    public void setProperty(AUPropertyType type, int value, AUScope scope) throws OSStatusException {
        setProperty(type, new IntPtr(value), scope);
    }
    public void setProperty(AUPropertyType type, long value, AUScope scope) throws OSStatusException {
        setProperty(type, new LongPtr(value), scope);
    }
    public void setProperty(AUPropertyType type,  float value, AUScope scope) throws OSStatusException {
        setProperty(type, new FloatPtr(value), scope);
    }
    public void setProperty(AUPropertyType type, double value, AUScope scope) throws OSStatusException {
        setProperty(type, new DoublePtr(value), scope);
    }
    
    /* Convenience methods for getting/setting properties */
    public int getMaxFramesPerSlice(AUScope scope) throws OSStatusException {
        return getPropertyAsInt(AUGenericProperty.MaximumFramesPerSlice, scope);
    }
    public void setMaxFramesPerSlice(int maxFramesPerSlice, AUScope scope) throws OSStatusException {
        setProperty(AUGenericProperty.MaximumFramesPerSlice, maxFramesPerSlice, scope);
    }
    
    public AudioStreamBasicDescription getStreamFormat(AUScope scope) throws OSStatusException {
        return getProperty(AUGenericProperty.StreamFormat, AudioStreamBasicDescription.class, scope);
    }
    public void setStreamFormat(AudioStreamBasicDescription streamFormat, AUScope scope) throws OSStatusException {
        setProperty(AUGenericProperty.StreamFormat, streamFormat, scope);
    }
    public void makeConnection(AUConnection connection, AUScope scope) throws OSStatusException {
        setProperty(AUGenericProperty.MakeConnection, connection, scope);
    }
    public void setRenderCallback(AURenderCallback callback, AUScope scope) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        AURenderCallbackStruct struct = new AURenderCallbackStruct(new FunctionPtr(cbRender), cid);
        setProperty(AUGenericProperty.SetRenderCallback, struct, scope);
        synchronized (renderCallbacks) {
            renderCallbacks.put(cid, callback);
        }
    }
    public void setHostCallback(AUHostCallback callback, AUScope scope) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        AUHostCallbackInfo struct = new AUHostCallbackInfo(cid, new FunctionPtr(cbHostGetBeatAndTempo), 
                new FunctionPtr(cbHostGetMusicalTimeLocation), new FunctionPtr(cbHostGetTransportState), new FunctionPtr(cbHostGetTransportState2));
        setProperty(AUGenericProperty.HostCallbacks, struct, scope);
        synchronized (hostCallbacks) {
            hostCallbacks.put(cid, callback);
        }
    }
    public AUInputSamplesInOutputCallback getInputSamplesInOutputCallback(AUScope scope) throws OSStatusException {
        AUInputSamplesInOutputCallbackStruct struct = getProperty(AUGenericProperty.InputSamplesInOutput, AUInputSamplesInOutputCallbackStruct.class, scope);
        AUInputSamplesInOutputCallback result = null;
        if (struct != null) {
            synchronized (isioCallbacks) {
                result = isioCallbacks.get(struct.getUserData());
            }
        }
        return result;
    }
    public void setInputSamplesInOutputCallback(AUInputSamplesInOutputCallback callback, AUScope scope) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        AUInputSamplesInOutputCallbackStruct struct = new AUInputSamplesInOutputCallbackStruct(new FunctionPtr(cbInputSamplesInOutput), cid);
        setProperty(AUGenericProperty.InputSamplesInOutput, struct, scope);
        synchronized (isioCallbacks) {
            isioCallbacks.put(cid, callback);
        }
    }
    public void scheduleAudioSlice(AUScheduledAudioSlice slice, AUScope scope) throws OSStatusException {
        setProperty(AUScheduledSoundPlayerProperty.ScheduleAudioSlice, slice, scope);
    }
    public void scheduleAudioFileRegion(AUScheduledAudioFileRegion region, AUScope scope) throws OSStatusException {
        setProperty(AUAudioFilePlayerProperty.ScheduledFileRegion, region, scope);
    }
    public AUOutputMIDICallback getMIDICallbacks(AUScope scope) throws OSStatusException {
        AUOutputMIDICallbacksStruct struct = getProperty(AUOutputProperty.MIDICallbacks, AUOutputMIDICallbacksStruct.class, scope);
        AUOutputMIDICallback result = null;
        if (struct != null) {
            synchronized (midiCallbacks) {
                result = midiCallbacks.get(struct.getUserData());
            }
        }
        return result;
    }
    public void setMIDICallbacks(AUOutputMIDICallback callback, AUScope scope) throws OSStatusException {
        AUOutputMIDICallbacksStruct struct = null;
        if (callback == null) {
            struct = getProperty(AUOutputProperty.MIDICallbacks, AUOutputMIDICallbacksStruct.class, scope);
            if (struct != null) {
                setProperty(AUOutputProperty.MIDICallbacks, null, scope);
                synchronized (midiCallbacks) {
                    midiCallbacks.remove(struct.getUserData());
                }
            }
        } else {
            long cid = callbackId.getAndIncrement();
            
            struct = new AUOutputMIDICallbacksStruct(cid, new FunctionPtr(cbMIDIEvent), new FunctionPtr(cbMIDISysEx));
            setProperty(AUOutputProperty.MIDICallbacks, struct, scope);
            synchronized (midiCallbacks) {
                midiCallbacks.put(cid, callback);
            }
        }
    }
    /* End: Convenience methods for getting/setting properties */
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void addPropertyListener(AUPropertyType id, AUPropertyListener listener) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        OSStatus status = addPropertyListener0(id, new FunctionPtr(cbPropertyChanged), cid);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (propertyListeners) {
                propertyListeners.put(cid, listener);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void removePropertyListener(AUPropertyType id, AUPropertyListener listener) throws OSStatusException {
        synchronized (propertyListeners) {
            for (Iterator<LongMap.Entry<AUPropertyListener>> it = propertyListeners.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AUPropertyListener> entry = it.next();
                if (entry.value == listener) {
                    OSStatus status = removePropertyListener0(id, new FunctionPtr(cbPropertyChanged), entry.key);
                    OSStatusException.throwIfNecessary(status);
                }
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void addRenderNotify(AURenderCallback callback) throws OSStatusException {
        long cid = callbackId.getAndIncrement();
        
        OSStatus status = addRenderNotify0(new FunctionPtr(cbRender), cid);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (renderCallbacks) {
                renderCallbacks.put(cid, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void removeRenderNotify(AURenderCallback callback) throws OSStatusException {
        synchronized (renderCallbacks) {
            for (Iterator<LongMap.Entry<AURenderCallback>> it = renderCallbacks.entries().iterator(); it.hasNext();) {
                LongMap.Entry<AURenderCallback> entry = it.next();
                if (entry.value == callback) {
                    OSStatus status = removeRenderNotify0(new FunctionPtr(cbRender), entry.key);
                    OSStatusException.throwIfNecessary(status);
                }
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public float getParameter(AUParameterType type, AUScope scope) throws OSStatusException {
        return getParameter(type, scope, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public float getParameter(AUParameterType type, AUScope scope, int element) throws OSStatusException {
        FloatPtr ptr = new FloatPtr();
        OSStatus status = getParameter0(type, scope, element, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setParameter(AUParameterType type, float value, AUScope scope) throws OSStatusException {
        setParameter(type, value, scope, 0, 0);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setParameter(AUParameterType type, float value, AUScope scope, int element, int bufferOffsetInFrames) throws OSStatusException {
        OSStatus status = setParameter0(type, scope, element, value, bufferOffsetInFrames);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void render(AURenderActionFlags actionFlags, AudioTimeStamp timeStamp, int outputBusNumber, int numberFrames, AudioBufferList data) throws OSStatusException {
        render(new AUMutableRenderActionFlags(actionFlags), timeStamp, outputBusNumber, numberFrames, data);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void render(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int outputBusNumber, int numberFrames, AudioBufferList data) throws OSStatusException {
        OSStatus status = render0(actionFlags, timeStamp, outputBusNumber, numberFrames, data);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public void process(AUMutableRenderActionFlags actionFlags, AudioTimeStamp timeStamp, int numberFrames, AudioBufferList data) throws OSStatusException {
        OSStatus status = process0(actionFlags, timeStamp, numberFrames, data);
        OSStatusException.throwIfNecessary(status);
    }
//    /**
//     * @since Available in iOS 6.0 and later.
//     */
//    @Bridge(symbol="AudioUnitProcessMultiple", optional=true) TODO
//    protected native OSStatus processMultiple0(AUMutableRenderActionFlags actionFlags, AudioTimeStamp inTimeStamp, int inNumberFrames, int inNumberInputBufferLists, AudioBufferList.AudioBufferListPtr inInputBufferLists, int inNumberOutputBufferLists, AudioBufferList.AudioBufferListPtr ioOutputBufferLists);
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void reset(AUScope scope, int element) throws OSStatusException {
        OSStatus status = reset0(scope, element);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public void publishOutput(AudioComponentDescription desc, String name, int version) throws OSStatusException {
        OSStatus status = publishOutput0(desc, name, version, this);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void startOutput() throws OSStatusException {
        OSStatus status = startOutput0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void stopOutput() throws OSStatusException {
        OSStatus status = stopOutput0();
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitInitialize", optional=true)
    protected native OSStatus initialize0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitUninitialize", optional=true)
    protected native OSStatus uninitialize0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetPropertyInfo", optional=true)
    protected native OSStatus getPropertyInfo0(AUPropertyType inID, AUScope inScope, int inElement, IntPtr outDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetProperty", optional=true)
    protected native OSStatus getProperty0(AUPropertyType inID, AUScope inScope, int inElement, VoidPtr outData, IntPtr ioDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitSetProperty", optional=true)
    protected native OSStatus setProperty0(AUPropertyType inID, AUScope inScope, int inElement, VoidPtr inData, int inDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitAddPropertyListener", optional=true)
    protected native OSStatus addPropertyListener0(AUPropertyType inID, FunctionPtr inProc, @Pointer long inProcUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRemovePropertyListenerWithUserData", optional=true)
    protected native OSStatus removePropertyListener0(AUPropertyType inID, FunctionPtr inProc, @Pointer long inProcUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitAddRenderNotify", optional=true)
    protected native OSStatus addRenderNotify0(FunctionPtr inProc, @Pointer long inProcUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRemoveRenderNotify", optional=true)
    protected native OSStatus removeRenderNotify0(FunctionPtr inProc, @Pointer long inProcUserData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitGetParameter", optional=true)
    protected native OSStatus getParameter0(AUParameterType inID, AUScope inScope, int inElement, FloatPtr outValue);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitSetParameter", optional=true)
    protected native OSStatus setParameter0(AUParameterType inID, AUScope inScope, int inElement, float inValue, int inBufferOffsetInFrames);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitRender", optional=true)
    protected native OSStatus render0(AUMutableRenderActionFlags actionFlags, AudioTimeStamp inTimeStamp, int inOutputBusNumber, int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioUnitProcess", optional=true)
    protected native OSStatus process0(AUMutableRenderActionFlags actionFlags, AudioTimeStamp inTimeStamp, int inNumberFrames, AudioBufferList ioData);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="AudioUnitProcessMultiple", optional=true)
    protected native OSStatus processMultiple0(AUMutableRenderActionFlags actionFlags, AudioTimeStamp inTimeStamp, int inNumberFrames, int inNumberInputBufferLists, AudioBufferList.AudioBufferListPtr inInputBufferLists, int inNumberOutputBufferLists, AudioBufferList.AudioBufferListPtr ioOutputBufferLists);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioUnitReset", optional=true)
    protected native OSStatus reset0(AUScope inScope, int inElement);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitPublish", optional=true)
    protected static native OSStatus publishOutput0(AudioComponentDescription inDesc, String inName, int inVersion, AudioUnit inOutputUnit);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="AudioOutputUnitGetHostIcon", optional=true)
    public native UIImage getOutputHostIcon(float desiredPointSize);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitStart", optional=true)
    protected native OSStatus startOutput0();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioOutputUnitStop", optional=true)
    protected native OSStatus stopOutput0();
    /*</methods>*/
}