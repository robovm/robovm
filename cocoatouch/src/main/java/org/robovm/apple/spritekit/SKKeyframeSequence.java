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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKKeyframeSequence/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKKeyframeSequencePtr extends Ptr<SKKeyframeSequence, SKKeyframeSequencePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKKeyframeSequence.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKKeyframeSequence() {}
    protected SKKeyframeSequence(SkipInit skipInit) { super(skipInit); }
    public SKKeyframeSequence(NSArray<NSObject> values, NSArray<NSNumber> times) { super((SkipInit) null); initObject(init(values, times)); }
    public SKKeyframeSequence(@MachineSizedUInt long numItems) { super((SkipInit) null); initObject(init(numItems)); }
    public SKKeyframeSequence(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "interpolationMode")
    public native SKInterpolationMode getInterpolationMode();
    @Property(selector = "setInterpolationMode:")
    public native void setInterpolationMode(SKInterpolationMode v);
    @Property(selector = "repeatMode")
    public native SKRepeatMode getRepeatMode();
    @Property(selector = "setRepeatMode:")
    public native void setRepeatMode(SKRepeatMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithKeyframeValues:times:")
    protected native @Pointer long init(NSArray<NSObject> values, NSArray<NSNumber> times);
    @Method(selector = "initWithCapacity:")
    protected native @Pointer long init(@MachineSizedUInt long numItems);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "count")
    public native @MachineSizedUInt long size();
    @Method(selector = "addKeyframeValue:time:")
    public native void add(NSObject value, @MachineSizedFloat double time);
    @Method(selector = "removeLastKeyframe")
    public native void removeLast();
    @Method(selector = "removeKeyframeAtIndex:")
    public native void remove(@MachineSizedUInt long index);
    @Method(selector = "setKeyframeValue:forIndex:")
    public native void setValue(NSObject value, @MachineSizedUInt long index);
    @Method(selector = "setKeyframeTime:forIndex:")
    public native void setTime(@MachineSizedFloat double time, @MachineSizedUInt long index);
    @Method(selector = "setKeyframeValue:time:forIndex:")
    public native void set(NSObject value, @MachineSizedFloat double time, @MachineSizedUInt long index);
    @Method(selector = "getKeyframeValueForIndex:")
    public native NSObject getValue(@MachineSizedUInt long index);
    @Method(selector = "getKeyframeTimeForIndex:")
    public native @MachineSizedFloat double getTime(@MachineSizedUInt long index);
    @Method(selector = "sampleAtTime:")
    public native NSObject getSample(@MachineSizedFloat double time);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
