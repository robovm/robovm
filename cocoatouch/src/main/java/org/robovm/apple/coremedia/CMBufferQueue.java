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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMBufferQueue/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMBufferQueuePtr extends Ptr<CMBufferQueue, CMBufferQueuePtr> {}/*</ptr>*/
    
    public interface ResetCallback {
        void reset(CMBuffer buffer);
    }
    public interface TriggerCallback {
        void trigger(CMBufferQueueTriggerToken triggerToken);
    }
    public interface ValidationCallback {
        void validate(CMBufferQueue bufferQueue, CMBuffer buffer);
    }
    public interface ForEachCallback {
        void invoke(CMBuffer buffer) throws OSStatusException;
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static java.util.concurrent.atomic.AtomicLong triggerId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<CMBufferQueueCallbacks> bufferQueueCallbacks = new LongMap<>();
    private static final LongMap<ResetCallback> resetCallbacks = new LongMap<>();
    private static final LongMap<TriggerCallback> triggerCallbacks = new LongMap<>();
    private static final LongMap<ValidationCallback> validationCallbacks = new LongMap<>();
    private static final LongMap<ForEachCallback> forEachCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbGetDecodeTimeStamp;
    private static final java.lang.reflect.Method cbGetPresentationTimeStamp;
    private static final java.lang.reflect.Method cbGetDuration;
    private static final java.lang.reflect.Method cbIsDataReady;
    private static final java.lang.reflect.Method cbCompare;
    private static final java.lang.reflect.Method cbGetSize;
    private static final java.lang.reflect.Method cbReset;
    private static final java.lang.reflect.Method cbTrigger;
    private static final java.lang.reflect.Method cbValidate;
    private static final java.lang.reflect.Method cbForEach;
    
    static {
        try {
            cbGetDecodeTimeStamp = CMBufferQueue.class.getDeclaredMethod("cbGetDecodeTimeStamp", CFType.class, long.class);
            cbGetPresentationTimeStamp = CMBufferQueue.class.getDeclaredMethod("cbGetPresentationTimeStamp", CFType.class, long.class);
            cbGetDuration = CMBufferQueue.class.getDeclaredMethod("cbGetDuration", CFType.class, long.class);
            cbIsDataReady = CMBufferQueue.class.getDeclaredMethod("cbIsDataReady", CFType.class, long.class);         
            cbCompare = CMBufferQueue.class.getDeclaredMethod("cbCompare", CFType.class, CFType.class, long.class);
            cbGetSize = CMBufferQueue.class.getDeclaredMethod("cbGetSize", CFType.class, long.class);
            cbReset = CMBufferQueue.class.getDeclaredMethod("cbReset", CFType.class, long.class);
            cbTrigger = CMBufferQueue.class.getDeclaredMethod("cbTrigger", long.class, CMBufferQueueTriggerToken.class);
            cbValidate = CMBufferQueue.class.getDeclaredMethod("cbValidate", CMBufferQueue.class, CFType.class, long.class);
            cbForEach = CMBufferQueue.class.getDeclaredMethod("cbForEach", CFType.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CMBufferQueue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMBufferQueue() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static CMTime cbGetDecodeTimeStamp(CFType buffer, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.getDecodeTimeStamp((CMBuffer)buffer);
    }
    @Callback
    private static CMTime cbGetPresentationTimeStamp(CFType buffer, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.getPresentationTimeStamp((CMBuffer)buffer);
    }
    @Callback
    private static CMTime cbGetDuration(CFType buffer, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.getDuration((CMBuffer)buffer);
    }
    @Callback
    private static boolean cbIsDataReady(CFType buffer, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.isDataReady((CMBuffer)buffer);
    }
    @Callback
    private static CFComparisonResult cbCompare(CFType buffer1, CFType buffer2, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.compare((CMBuffer)buffer1, (CMBuffer)buffer2);
    }
    @Callback
    private static int cbGetSize(CFType buffer, @Pointer long refcon) {
        CMBufferQueueCallbacks callback = null;
        synchronized (bufferQueueCallbacks) {
            callback = bufferQueueCallbacks.get(refcon);
        }
        return callback.getSize((CMBuffer)buffer);
    }
    @Callback
    private static void cbReset(CFType buffer, @Pointer long refcon) {
        ResetCallback callback = null;
        synchronized (resetCallbacks) {
            callback = resetCallbacks.get(refcon);
        }
        callback.reset((CMBuffer)buffer);
    }
    @Callback
    private static void cbTrigger(@Pointer long refcon, CMBufferQueueTriggerToken triggerToken) {
        TriggerCallback callback = null;
        synchronized (triggerCallbacks) {
            callback = triggerCallbacks.get(refcon);
        }
        callback.trigger(triggerToken);
    }
    @Callback
    private static void cbValidate(CMBufferQueue bufferQueue, CFType buffer, @Pointer long refcon) {
        ValidationCallback callback = null;
        synchronized (validationCallbacks) {
            callback = validationCallbacks.get(refcon);
        }
        callback.validate(bufferQueue, (CMBuffer)buffer);
    }
    @Callback
    private static OSStatus cbForEach(CFType buffer, @Pointer long refcon) {
        synchronized (forEachCallbacks) {
            try {
                forEachCallbacks.get(refcon).invoke((CMBuffer)buffer);
            } catch (OSStatusException e) {
                return e.getStatus();
            }
            return OSStatus.NO_ERR;
        }
    }
    
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMBufferQueue create(@MachineSizedSInt long capacity, CMBufferQueueCallbacks callback) throws OSStatusException {
        long refconId = CMBufferQueue.refconId.getAndIncrement();
        CMBufferCallbacksStruct callbacks = new CMBufferCallbacksStruct();
        callbacks.setRefcon(refconId);
        callbacks.setGetDecodeTimeStamp(new FunctionPtr(cbGetDecodeTimeStamp));
        callbacks.setGetPresentationTimeStamp(new FunctionPtr(cbGetPresentationTimeStamp));
        callbacks.setGetDuration(new FunctionPtr(cbGetDuration));
        callbacks.setIsDataReady(new FunctionPtr(cbIsDataReady));
        callbacks.setCompare(new FunctionPtr(cbCompare));
        callbacks.setGetSize(new FunctionPtr(cbGetSize));
        CMBufferQueuePtr ptr = new CMBufferQueuePtr();
        OSStatus status = create0(null, capacity, callbacks, ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (bufferQueueCallbacks) {
                bufferQueueCallbacks.put(refconId, callback);
            }
            return ptr.get();
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void markEndOfData() throws OSStatusException {
        OSStatus status = markEndOfData0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void reset() throws OSStatusException {
        OSStatus status = reset0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void reset(ResetCallback callback) throws OSStatusException {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus status = reset0(new FunctionPtr(cbReset), refconId);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (resetCallbacks) {
                resetCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void installTrigger(TriggerCallback callback, CMBufferQueueTriggerCondition triggerCondition, @ByVal CMTime triggerTime) throws OSStatusException {
        long refconId = CMBufferQueue.triggerId.getAndIncrement();
        OSStatus status = installTrigger0(new FunctionPtr(cbTrigger), refconId, triggerCondition, triggerTime, null);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (triggerCallbacks) {
                triggerCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void installTrigger(TriggerCallback callback, CMBufferQueueTriggerCondition triggerCondition, @MachineSizedSInt long triggerThreshold) throws OSStatusException {
        long refconId = CMBufferQueue.triggerId.getAndIncrement();
        OSStatus status = installTrigger0(new FunctionPtr(cbTrigger), refconId, triggerCondition, triggerThreshold, null);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (triggerCallbacks) {
                triggerCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void removeTrigger(CMBufferQueueTriggerToken triggerToken) throws OSStatusException {
        OSStatus status = removeTrigger0(triggerToken);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void callForEachBuffer(ForEachCallback callback) throws OSStatusException {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus status = callForEachBuffer0(new FunctionPtr(cbReset), refconId);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (forEachCallbacks) {
                forEachCallbacks.put(refconId, callback);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void setValidationCallback(ValidationCallback callback) throws OSStatusException {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus status = setValidationCallback0(new FunctionPtr(cbValidate), refconId);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (validationCallbacks) {
                validationCallbacks.put(refconId, callback);
            }
        }
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueCreate", optional=true)
    protected static native OSStatus create0(CFAllocator allocator, @MachineSizedSInt long capacity, CMBufferCallbacksStruct callbacks, CMBufferQueue.CMBufferQueuePtr queueOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueEnqueue", optional=true)
    protected native OSStatus enqueue0(CFType buf);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueDequeueAndRetain", optional=true)
    public native CFType dequeueAndRetain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueDequeueIfDataReadyAndRetain", optional=true)
    public native CFType dequeueIfDataReadyAndRetain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetHead", optional=true)
    public native CFType getHead();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueIsEmpty", optional=true)
    public native boolean isEmpty();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueMarkEndOfData", optional=true)
    protected native OSStatus markEndOfData0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueContainsEndOfData", optional=true)
    public native boolean containsEndOfData();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueIsAtEndOfData", optional=true)
    public native boolean isAtEndOfData();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueReset", optional=true)
    protected native OSStatus reset0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueResetWithCallback", optional=true)
    protected native OSStatus reset0(FunctionPtr callback, @Pointer long refcon);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetBufferCount", optional=true)
    public native @MachineSizedSInt long getBufferCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetDuration", optional=true)
    public native @ByVal CMTime getDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetMinDecodeTimeStamp", optional=true)
    public native @ByVal CMTime getMinDecodeTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetFirstDecodeTimeStamp", optional=true)
    public native @ByVal CMTime getFirstDecodeTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetMinPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getMinPresentationTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetFirstPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getFirstPresentationTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetMaxPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getMaxPresentationTimeStamp();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetEndPresentationTimeStamp", optional=true)
    public native @ByVal CMTime getEndPresentationTimeStamp();
    /**
     * @since Available in iOS 7.1 and later.
     */
    @Bridge(symbol="CMBufferQueueGetTotalSize", optional=true)
    public native @MachineSizedUInt long getTotalSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueInstallTrigger", optional=true)
    protected native OSStatus installTrigger0(FunctionPtr triggerCallback, @Pointer long triggerRefcon, CMBufferQueueTriggerCondition triggerCondition, @ByVal CMTime triggerTime, CMBufferQueueTriggerToken.CMBufferQueueTriggerTokenPtr triggerTokenOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueInstallTriggerWithIntegerThreshold", optional=true)
    protected native OSStatus installTrigger0(FunctionPtr triggerCallback, @Pointer long triggerRefcon, CMBufferQueueTriggerCondition triggerCondition, @MachineSizedSInt long triggerThreshold, CMBufferQueueTriggerToken.CMBufferQueueTriggerTokenPtr triggerTokenOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueRemoveTrigger", optional=true)
    protected native OSStatus removeTrigger0(CMBufferQueueTriggerToken triggerToken);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueTestTrigger", optional=true)
    protected native boolean testTrigger0(CMBufferQueueTriggerToken triggerToken);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueCallForEachBuffer", optional=true)
    protected native OSStatus callForEachBuffer0(FunctionPtr callback, @Pointer long refcon);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueSetValidationCallback", optional=true)
    protected native OSStatus setValidationCallback0(FunctionPtr validationCallback, @Pointer long validationRefCon);
    /*</methods>*/
}
