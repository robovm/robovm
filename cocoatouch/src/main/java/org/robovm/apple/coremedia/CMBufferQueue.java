/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
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
        CMBufferQueueError invoke(CMBuffer buffer);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static java.util.concurrent.atomic.AtomicLong triggerId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, CMBufferQueueCallbacks> bufferQueueCallbacks = new HashMap<Long, CMBufferQueueCallbacks>();
    private static Map<Long, ResetCallback> resetCallbacks = new HashMap<Long, ResetCallback>();
    private static Map<Long, TriggerCallback> triggerCallbacks = new HashMap<Long, TriggerCallback>();
    private static Map<Long, ValidationCallback> validationCallbacks = new HashMap<Long, ValidationCallback>();
    private static Map<Long, ForEachCallback> forEachCallbacks = new HashMap<Long, ForEachCallback>();
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
    private static void cbForEach(CFType buffer, @Pointer long refcon) {
        ForEachCallback callback = null;
        synchronized (forEachCallbacks) {
            callback = forEachCallbacks.get(refcon);
        }
        callback.invoke((CMBuffer)buffer);
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CMBufferQueue create(@MachineSizedSInt long capacity, CMBufferQueueCallbacks callback) {
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
        OSStatus err = create(null, capacity, callbacks, ptr);
        if (err.equals(CMBufferQueueError.No)) {
            synchronized (bufferQueueCallbacks) {
                bufferQueueCallbacks.put(refconId, callback);
            }
            return ptr.get();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public OSStatus reset(ResetCallback callback) {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus error = reset(new FunctionPtr(cbReset), refconId);
        synchronized (resetCallbacks) {
            resetCallbacks.put(refconId, callback);
        }
        return error;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public OSStatus installTrigger(TriggerCallback callback, CMBufferQueueTriggerCondition triggerCondition, @ByVal CMTime triggerTime) {
        long refconId = CMBufferQueue.triggerId.getAndIncrement();
        OSStatus error = installTrigger(new FunctionPtr(cbTrigger), refconId, triggerCondition, triggerTime, null);
        synchronized (triggerCallbacks) {
            triggerCallbacks.put(refconId, callback);
        }
        return error;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public OSStatus installTrigger(TriggerCallback callback, CMBufferQueueTriggerCondition triggerCondition, @MachineSizedSInt long triggerThreshold) {
        long refconId = CMBufferQueue.triggerId.getAndIncrement();
        OSStatus error = installTrigger(new FunctionPtr(cbTrigger), refconId, triggerCondition, triggerThreshold, null);
        synchronized (triggerCallbacks) {
            triggerCallbacks.put(refconId, callback);
        }
        return error;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public OSStatus callForEachBuffer(ForEachCallback callback) {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus error = callForEachBuffer(new FunctionPtr(cbReset), refconId);
        synchronized (forEachCallbacks) {
            forEachCallbacks.put(refconId, callback);
        }
        return error;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public OSStatus setValidationCallback(ValidationCallback callback) {
        long refconId = CMBufferQueue.refconId.get();
        OSStatus error = setValidationCallback(new FunctionPtr(cbValidate), refconId);
        synchronized (validationCallbacks) {
            validationCallbacks.put(refconId, callback);
        }
        return error;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueCreate", optional=true)
    private static native OSStatus create(CFAllocator allocator, @MachineSizedSInt long capacity, CMBufferCallbacksStruct callbacks, CMBufferQueue.CMBufferQueuePtr queueOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueEnqueue", optional=true)
    public native OSStatus enqueue(CFType buf);
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
    public native OSStatus markEndOfData();
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
    public native OSStatus reset();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueResetWithCallback", optional=true)
    private native OSStatus reset(FunctionPtr callback, @Pointer long refcon);
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
    private native OSStatus installTrigger(FunctionPtr triggerCallback, @Pointer long triggerRefcon, CMBufferQueueTriggerCondition triggerCondition, @ByVal CMTime triggerTime, CMBufferQueueTriggerToken.CMBufferQueueTriggerTokenPtr triggerTokenOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueInstallTriggerWithIntegerThreshold", optional=true)
    private native OSStatus installTrigger(FunctionPtr triggerCallback, @Pointer long triggerRefcon, CMBufferQueueTriggerCondition triggerCondition, @MachineSizedSInt long triggerThreshold, CMBufferQueueTriggerToken.CMBufferQueueTriggerTokenPtr triggerTokenOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueRemoveTrigger", optional=true)
    public native OSStatus removeTrigger(CMBufferQueueTriggerToken triggerToken);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueTestTrigger", optional=true)
    public native boolean testTrigger(CMBufferQueueTriggerToken triggerToken);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueCallForEachBuffer", optional=true)
    private native OSStatus callForEachBuffer(FunctionPtr callback, @Pointer long refcon);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBufferQueueSetValidationCallback", optional=true)
    private native OSStatus setValidationCallback(FunctionPtr validationCallback, @Pointer long validationRefCon);
    /*</methods>*/
}
