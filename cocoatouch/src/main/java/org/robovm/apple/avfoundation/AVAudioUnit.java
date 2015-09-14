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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnit/*</name>*/ 
    extends /*<extends>*/AVAudioNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioUnitPtr extends Ptr<AVAudioUnit, AVAudioUnitPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioUnit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioUnit() {}
    protected AVAudioUnit(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @WeaklyLinked
    @Property(selector = "audioComponentDescription")
    public native @ByVal AudioComponentDescription getAudioComponentDescription();
    @WeaklyLinked
    @Property(selector = "audioUnit")
    public native AudioUnit getAudioUnit();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "AUAudioUnit")
    public native AUAudioUnit getAUAudioUnit();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "manufacturerName")
    public native String getManufacturerName();
    @Property(selector = "version")
    public native @MachineSizedUInt long getVersion();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    public boolean loadAudioUnitPreset(NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = loadAudioUnitPreset(url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "loadAudioUnitPresetAtURL:error:")
    private native boolean loadAudioUnitPreset(NSURL url, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "instantiateWithComponentDescription:options:completionHandler:")
    public static native void instantiate(@ByVal AudioComponentDescription audioComponentDescription, AudioComponentInstantiationOptions options, @Block VoidBlock2<AVAudioUnit, NSError> completionHandler);
    /*</methods>*/
}
