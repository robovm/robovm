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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioSession/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface PropertyListener {
        void onChange(AudioSessionProperty id, int dataSize, VoidPtr data);
    }
    public interface InterruptionListener {
        void onInterrupt(AudioSessionInterruptionState interruptionState);
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    
    private static final LongMap<PropertyListener> propertyListeners = new LongMap<>();
    private static final java.lang.reflect.Method cbPropertyChanged;
    private static final LongMap<InterruptionListener> interruptionListeners = new LongMap<>();
    private static final java.lang.reflect.Method cbInterruption;
    
    static {
        try {
            cbPropertyChanged = AudioSession.class.getDeclaredMethod("cbPropertyChanged", Long.TYPE, AudioSessionProperty.class, Integer.TYPE, VoidPtr.class);
            cbInterruption = AudioSession.class.getDeclaredMethod("cbInterruption", Long.TYPE, AudioSessionInterruptionState.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(AudioSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbPropertyChanged(@Pointer long clientData, AudioSessionProperty id, int dataSize, VoidPtr data) {
        synchronized (propertyListeners) {
            propertyListeners.get(clientData).onChange(id, dataSize, data);
        }
    }
    @Callback
    private static void cbInterruption(@Pointer long clientData, AudioSessionInterruptionState interruptionState) {
        synchronized (interruptionListeners) {
            interruptionListeners.get(clientData).onInterrupt(interruptionState);
        }
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void initialize(NSRunLoop runLoop, NSRunLoopMode runLoopMode, InterruptionListener interruptionListener) throws OSStatusException {
        initialize(runLoop, runLoopMode == null ? null : runLoopMode.value().toString(), interruptionListener);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void initialize(NSRunLoop runLoop, String runLoopMode, InterruptionListener interruptionListener) throws OSStatusException {
        if (interruptionListener == null) {
            OSStatus status = initialize0(runLoop, runLoopMode, null, 0);
            OSStatusException.throwIfNecessary(status);
            return;
        }
        long cid = callbackId.getAndIncrement();
        
        OSStatus status = initialize0(runLoop, runLoopMode, new FunctionPtr(cbInterruption), cid);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (interruptionListeners) {
                interruptionListeners.put(cid, interruptionListener);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void setActive(boolean active) throws OSStatusException {
        OSStatus status = setActive0(active);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void setActive(boolean active, AudioSessionActivationFlags flags) throws OSStatusException {
        OSStatus status = setActive0(active, flags);
        OSStatusException.throwIfNecessary(status);
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static <T extends Struct<T>> T getProperty(AudioSessionProperty id, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(id, dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static <T extends Struct<T>> void setProperty(AudioSessionProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    @Deprecated
    public static int getPropertyAsInt(AudioSessionProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    @Deprecated
    public static long getPropertyAsLong(AudioSessionProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    @Deprecated
    public static float getPropertyAsFloat(AudioSessionProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    @Deprecated
    public static double getPropertyAsDouble(AudioSessionProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    @Deprecated
    public static void setProperty(AudioSessionProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    @Deprecated
    public static void setProperty(AudioSessionProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    @Deprecated
    public static void setProperty(AudioSessionProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    @Deprecated
    public static void setProperty(AudioSessionProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static int getPropertySize(AudioSessionProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertySize0(id, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void addPropertyListener(AudioSessionProperty id, PropertyListener listener) throws OSStatusException {
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
     * @since Available in iOS 2.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static void removePropertyListener(AudioSessionProperty id, PropertyListener listener) throws OSStatusException {
        synchronized (propertyListeners) {
            for (Iterator<LongMap.Entry<PropertyListener>> it = propertyListeners.entries().iterator(); it.hasNext();) {
                LongMap.Entry<PropertyListener> entry = it.next();
                if (entry.value == listener) {
                    OSStatus status = removePropertyListener0(id, new FunctionPtr(cbPropertyChanged), entry.key);
                    OSStatusException.throwIfNecessary(status);
                }
            }
        }
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionInitialize", optional=true)
    protected static native OSStatus initialize0(NSRunLoop inRunLoop, String inRunLoopMode, FunctionPtr inInterruptionListener, @Pointer long inClientData);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionSetActive", optional=true)
    protected static native OSStatus setActive0(boolean active);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionSetActiveWithFlags", optional=true)
    protected static native OSStatus setActive0(boolean active, AudioSessionActivationFlags flags);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionGetProperty", optional=true)
    protected static native OSStatus getProperty0(AudioSessionProperty id, IntPtr ioDataSize, VoidPtr outData);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionSetProperty", optional=true)
    protected static native OSStatus setProperty0(AudioSessionProperty id, int inDataSize, VoidPtr inData);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionGetPropertySize", optional=true)
    protected static native OSStatus getPropertySize0(AudioSessionProperty id, IntPtr outDataSize);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionAddPropertyListener", optional=true)
    protected static native OSStatus addPropertyListener0(AudioSessionProperty id, FunctionPtr inProc, @Pointer long inClientData);
    /**
     * @since Available in iOS 2.1 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="AudioSessionRemovePropertyListenerWithUserData", optional=true)
    protected static native OSStatus removePropertyListener0(AudioSessionProperty id, FunctionPtr inProc, @Pointer long inClientData);
    /*</methods>*/
}
