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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioComponent/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeRegistrationsChanged(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(RegistrationsChangedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }

    /*<ptr>*/public static class AudioComponentPtr extends Ptr<AudioComponent, AudioComponentPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioComponent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioComponent() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getName() {
        CFString.CFStringPtr ptr = new CFString.CFStringPtr();
        getName(ptr);
        if (ptr.get() != null) {
            return ptr.get().toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public AudioComponentDescription getDescription() {
        AudioComponentDescription.AudioComponentDescriptionPtr ptr = new AudioComponentDescription.AudioComponentDescriptionPtr();
        getDescription(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getVersion() {
        IntPtr ptr = new IntPtr();
        getVersion(ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kAudioComponentRegistrationsChangedNotification", optional=true)
    public static native NSString RegistrationsChangedNotification();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentFindNext", optional=true)
    public static native AudioComponent findNext(AudioComponent inComponent, AudioComponentDescription inDesc);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentCount", optional=true)
    public static native int count(AudioComponentDescription inDesc);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentCopyName", optional=true)
    private native OSStatus getName(CFString.CFStringPtr outName);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentGetDescription", optional=true)
    private native OSStatus getDescription(AudioComponentDescription.AudioComponentDescriptionPtr desc);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentGetVersion", optional=true)
    private native OSStatus getVersion(IntPtr outVersion);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="AudioComponentGetIcon", optional=true)
    public native UIImage getIcon(float desiredPointSize);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="AudioComponentGetLastActiveTime", optional=true)
    public native double getLastActiveTime();
    /*</methods>*/
}
