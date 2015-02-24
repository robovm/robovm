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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioServices/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioServices.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesPlayAlertSound", optional=true)
    public static native void playAlertSound(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesPlaySystemSound", optional=true)
    public static native void playSystemSound(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesCreateSystemSoundID", optional=true)
    public static native OSStatus createSystemSoundID(CFURL inFileURL, IntPtr outSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesDisposeSystemSoundID", optional=true)
    public static native OSStatus disposeSystemSoundID(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesAddSystemSoundCompletion", optional=true)
    public static native OSStatus addSystemSoundCompletion(int inSystemSoundID, CFRunLoop inRunLoop, CFString inRunLoopMode, FunctionPtr inCompletionRoutine, VoidPtr inClientData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesRemoveSystemSoundCompletion", optional=true)
    public static native void removeSystemSoundCompletion(int inSystemSoundID);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesGetPropertyInfo", optional=true)
    public static native OSStatus getPropertyInfo(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr outPropertyDataSize, BooleanPtr outWritable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesGetProperty", optional=true)
    public static native OSStatus getProperty(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, IntPtr ioPropertyDataSize, VoidPtr outPropertyData);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioServicesSetProperty", optional=true)
    public static native OSStatus setProperty(int inPropertyID, int inSpecifierSize, VoidPtr inSpecifier, int inPropertyDataSize, VoidPtr inPropertyData);
    /*</methods>*/
}
