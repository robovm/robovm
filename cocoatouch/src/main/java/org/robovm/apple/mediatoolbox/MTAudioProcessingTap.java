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
package org.robovm.apple.mediatoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTAudioProcessingTap/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public interface Callbacks {
        void init(MTAudioProcessingTap tap);
        
        void finalize(MTAudioProcessingTap tap);
        
        void prepare(MTAudioProcessingTap tap, long maxFrames, AudioStreamBasicDescription processingFormat);
        
        void unprepare(MTAudioProcessingTap tap);

        long process(MTAudioProcessingTap tap, long numberFrames, MTAudioProcessingTapMutableFlags flags, AudioBufferList bufferList);
    }
    
    private static final java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<Callbacks> callbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbInit;
    private static final java.lang.reflect.Method cbFinalize;
    private static final java.lang.reflect.Method cbPrepare;
    private static final java.lang.reflect.Method cbUnprepare;
    private static final java.lang.reflect.Method cbProcess;
    
    static {
        try {
            cbInit = MTAudioProcessingTap.class.getDeclaredMethod("cbInit", MTAudioProcessingTap.class, Long.TYPE, LongPtr.LongPtrPtr.class);
            cbFinalize = MTAudioProcessingTap.class.getDeclaredMethod("cbFinalize", MTAudioProcessingTap.class);
            cbPrepare = MTAudioProcessingTap.class.getDeclaredMethod("cbPrepare", MTAudioProcessingTap.class, Long.TYPE, AudioStreamBasicDescription.class);
            cbUnprepare = MTAudioProcessingTap.class.getDeclaredMethod("cbUnprepare", MTAudioProcessingTap.class);
            cbProcess = MTAudioProcessingTap.class.getDeclaredMethod("cbProcess", MTAudioProcessingTap.class, Long.TYPE, MTAudioProcessingTapMutableFlags.class, 
                AudioBufferList.class, MachineSizedSIntPtr.class, MTAudioProcessingTapFlags.MTAudioProcessingTapFlagsPtr.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/public static class MTAudioProcessingTapPtr extends Ptr<MTAudioProcessingTap, MTAudioProcessingTapPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MTAudioProcessingTap.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MTAudioProcessingTap() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInit(MTAudioProcessingTap tap, @Pointer long clientInfo, LongPtr.LongPtrPtr tapStorageOut) {
        Callbacks cb = null;
        synchronized (callbacks) {
            cb = callbacks.get(clientInfo);
        }
        cb.init(tap);

        tapStorageOut.set(clientInfo);
    }
    @Callback
    private static void cbFinalize(MTAudioProcessingTap tap) {
        long storage = tap.getStorage();
        Callbacks cb = null;
        synchronized (callbacks) {
            cb = callbacks.remove(storage);
        }
        cb.finalize(tap);
    }
    @Callback
    private static void cbPrepare(MTAudioProcessingTap tap, @MachineSizedSInt long maxFrames, AudioStreamBasicDescription processingFormat) {
        Callbacks cb = null;
        synchronized (callbacks) {
            cb = callbacks.get(tap.getStorage());
        }
        cb.prepare(tap, maxFrames, processingFormat);
    }
    @Callback
    private static void cbUnprepare(MTAudioProcessingTap tap) {
        Callbacks cb = null;
        synchronized (callbacks) {
            cb = callbacks.get(tap.getStorage());
        }
        cb.unprepare(tap);
    }
    @Callback
    private static void cbProcess(MTAudioProcessingTap tap, @MachineSizedSInt long numberFrames, MTAudioProcessingTapMutableFlags flags, AudioBufferList bufferListInOut, 
            MachineSizedSIntPtr numberFramesOut, MTAudioProcessingTapFlags.MTAudioProcessingTapFlagsPtr flagsOut) {
        Callbacks cb = null;
        synchronized (callbacks) {
            cb = callbacks.get(tap.getStorage());
        }
        long frames = cb.process(tap, numberFrames, flags, bufferListInOut);
        numberFramesOut.set(frames);
        flagsOut.set(flags.get());
    }
    
    private CMTimeRange lastTimeRange;
    
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public static MTAudioProcessingTap create(Callbacks callbacks, MTAudioProcessingTapCreationFlags flags) throws OSStatusException {
        MTAudioProcessingTap.MTAudioProcessingTapPtr ptr = new MTAudioProcessingTap.MTAudioProcessingTapPtr();
        long callbackId = MTAudioProcessingTap.callbackId.getAndIncrement();
        
        MTAudioProcessingTapCallbacksStruct struct = new MTAudioProcessingTapCallbacksStruct(0, callbackId, new FunctionPtr(cbInit), new FunctionPtr(cbFinalize), 
            new FunctionPtr(cbPrepare), new FunctionPtr(cbUnprepare), new FunctionPtr(cbProcess));
        OSStatus status = create(null, struct, flags, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (MTAudioProcessingTap.callbacks) {
                MTAudioProcessingTap.callbacks.put(callbackId, callbacks);
            }
            return ptr.get();
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public long getSourceAudio(long numberFrames, AudioBufferList bufferList, MTAudioProcessingTapMutableFlags flags) throws OSStatusException {
        CMTimeRange timeRangePtr = new CMTimeRange();
        MachineSizedSIntPtr numberFramesPtr = new MachineSizedSIntPtr();
        
        OSStatus status = getSourceAudio0(numberFrames, bufferList, flags, timeRangePtr, numberFramesPtr);
        OSStatusException.throwIfNecessary(status);
        this.lastTimeRange = timeRangePtr;
        return numberFramesPtr.get();
    }

    public CMTimeRange getLastTimeRange() {
        return lastTimeRange;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapCreate", optional=true)
    protected static native OSStatus create(CFAllocator allocator, MTAudioProcessingTapCallbacksStruct callbacks, MTAudioProcessingTapCreationFlags flags, MTAudioProcessingTap.MTAudioProcessingTapPtr tapOut);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetStorage", optional=true)
    protected native @Pointer long getStorage();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="MTAudioProcessingTapGetSourceAudio", optional=true)
    protected native OSStatus getSourceAudio0(@MachineSizedSInt long numberFrames, AudioBufferList bufferListInOut, MTAudioProcessingTapMutableFlags flagsOut, CMTimeRange timeRangeOut, MachineSizedSIntPtr numberFramesOut);
    /*</methods>*/
}
