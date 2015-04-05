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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioComponentInstance/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioComponentInstancePtr extends Ptr<AudioComponentInstance, AudioComponentInstancePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(AudioComponentInstance.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected AudioComponentInstance() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static AudioComponentInstance create(AudioComponent component) {
        AudioComponentInstance.AudioComponentInstancePtr ptr = new AudioComponentInstance.AudioComponentInstancePtr();
        create(component, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentInstanceNew", optional=true)
    protected static native OSStatus create(AudioComponent inComponent, AudioComponentInstance.AudioComponentInstancePtr outInstance);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentInstanceDispose", optional=true)
    public native OSStatus dispose();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="AudioComponentInstanceGetComponent", optional=true)
    public native AudioComponent getComponent();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="AudioComponentInstanceCanDo", optional=true)
     native boolean canDo(short inSelectorID);
    /*</methods>*/
}
