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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioServices/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    
    public interface SystemSoundCompletionListener {
        void onComplete(int systemSoundID);
    }
    
    private static final LongMap<SystemSoundCompletionListener> completionListeners = new LongMap<>();
    private static final java.lang.reflect.Method cbSystemSoundCompleted;
    
    static {
        try {
            cbSystemSoundCompleted = AudioServices.class.getDeclaredMethod("cbSystemSoundCompleted", Integer.TYPE, Long.TYPE);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<bind>*/static { Bro.bind(AudioServices.class); }/*</bind>*/
    /*<constants>*/
    public static final int SystemSoundVibrate = 4095;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbSystemSoundCompleted(int systemSoundID, long clientData) {
        synchronized (completionListeners) {
            completionListeners.get(systemSoundID).onComplete(systemSoundID);
        }
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static int createSystemSoundID(NSURL fileURL) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = createSystemSoundID0(fileURL, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static void disposeSystemSoundID(int systemSoundID) throws OSStatusException {
        OSStatus status = disposeSystemSoundID0(systemSoundID);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static void addSystemSoundCompletionListener(int systemSoundID, NSRunLoop runLoop, String runLoopMode, SystemSoundCompletionListener listener) throws OSStatusException {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        OSStatus status = addSystemSoundCompletion0(systemSoundID, runLoop, runLoopMode, new FunctionPtr(cbSystemSoundCompleted), null);
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (completionListeners) {
                completionListeners.put(systemSoundID, listener);
            }
        }
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static void removeSystemSoundCompletion(int systemSoundID) {
        removeSystemSoundCompletion0(systemSoundID);
        synchronized (completionListeners) {
            completionListeners.remove(systemSoundID);
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static int getPropertySize(AudioServicesProperty id) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getPropertyInfo0(id, 0, null, ptr, null);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static boolean isPropertyWritable(AudioServicesProperty id) throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = getPropertyInfo0(id, 0, null, null, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static <T extends Struct<T>> T getProperty(AudioServicesProperty id, Struct<?> specifier, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(id, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), dataSize, data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static <T extends Struct<T>> void setProperty(AudioServicesProperty id, Struct<?> specifier, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, specifier == null ? 0 : Struct.sizeOf(specifier), specifier == null ? null : specifier.as(VoidPtr.class), data == null ? 0 : Struct.sizeOf(data), data == null ? null : data.as(VoidPtr.class));
        OSStatusException.throwIfNecessary(status);
    }
    public static boolean isUISound(int systemSoundID) throws OSStatusException {
        IntPtr ptr = new IntPtr(systemSoundID);
        IntPtr result = getProperty(AudioServicesProperty.IsUISound, ptr, IntPtr.class);
        return result.get() == 1;
    }
    public static void setUISound(int systemSoundID, boolean ui) throws OSStatusException {
        IntPtr ptr = new IntPtr(systemSoundID);
        setProperty(AudioServicesProperty.IsUISound, ptr, new IntPtr(ui ? 1 : 0));
    }
    public static boolean completesPlaybackIfAppDies(int systemSoundID) throws OSStatusException {
        IntPtr ptr = new IntPtr(systemSoundID);
        IntPtr result = getProperty(AudioServicesProperty.CompletePlaybackIfAppDies, ptr, IntPtr.class);
        return result.get() == 1;
    }
    public static void setCompletesPlaybackIfAppDies(int systemSoundID, boolean ui) throws OSStatusException {
        IntPtr ptr = new IntPtr(systemSoundID);
        setProperty(AudioServicesProperty.CompletePlaybackIfAppDies, ptr, new IntPtr(ui ? 1 : 0));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesPlayAlertSound", optional=true)
    public static native void playAlertSound(int systemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesPlaySystemSound", optional=true)
    public static native void playSystemSound(int systemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesCreateSystemSoundID", optional=true)
    protected static native OSStatus createSystemSoundID0(NSURL inFileURL, IntPtr outSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesDisposeSystemSoundID", optional=true)
    protected static native OSStatus disposeSystemSoundID0(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesAddSystemSoundCompletion", optional=true)
    protected static native OSStatus addSystemSoundCompletion0(int inSystemSoundID, NSRunLoop inRunLoop, String inRunLoopMode, FunctionPtr inCompletionRoutine, VoidPtr inClientData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesRemoveSystemSoundCompletion", optional=true)
    protected static native void removeSystemSoundCompletion0(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesGetPropertyInfo", optional=true)
    protected static native OSStatus getPropertyInfo0(AudioServicesProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outPropertyDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesGetProperty", optional=true)
    protected static native OSStatus getProperty0(AudioServicesProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesSetProperty", optional=true)
    protected static native OSStatus setProperty0(AudioServicesProperty inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, int inPropertyDataSize, VoidPtr inPropertyData);
    /*</methods>*/
}
