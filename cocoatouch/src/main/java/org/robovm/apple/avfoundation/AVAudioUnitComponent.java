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
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnitComponent/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        /**
         * @since Available in iOS 9.0 and later.
         */
        public static NSObject observeTagsDidChange(AVAudioUnitComponent object, final VoidBlock1<AVAudioUnitComponent> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(TagsDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification notification) {
                    block.invoke((AVAudioUnitComponent) notification.getObject());
                }
            });
        }
    }

    /*<ptr>*/public static class AVAudioUnitComponentPtr extends Ptr<AVAudioUnitComponent, AVAudioUnitComponentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioUnitComponent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioUnitComponent() {}
    protected AVAudioUnitComponent(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "typeName")
    public native String getTypeName();
    @Property(selector = "localizedTypeName")
    public native String getLocalizedTypeName();
    @Property(selector = "manufacturerName")
    public native String getManufacturerName();
    @Property(selector = "version")
    public native @MachineSizedUInt long getVersion();
    @Property(selector = "versionString")
    public native String getVersionString();
    @Property(selector = "isSandboxSafe")
    public native boolean isSandboxSafe();
    @Property(selector = "hasMIDIInput")
    public native boolean hasMIDIInput();
    @Property(selector = "hasMIDIOutput")
    public native boolean hasMIDIOutput();
    @Property(selector = "audioComponent")
    public native AudioComponent getAudioComponent();
    @Property(selector = "allTagNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAllTagNames();
    @Property(selector = "audioComponentDescription")
    public native @ByVal AudioComponentDescription getAudioComponentDescription();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="AVAudioUnitComponentTagsDidChangeNotification", optional=true)
    public static native NSString TagsDidChangeNotification();
    
    
    /*</methods>*/
}
