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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/
import org.robovm.rt.annotation.WeaklyLinked;

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAAnimation/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, CAMediaTiming, CAAction/*</implements>*/ {

    /*<ptr>*/public static class CAAnimationPtr extends Ptr<CAAnimation, CAAnimationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAAnimation() {}
    protected CAAnimation(long handle) { super(handle); }
    protected CAAnimation(SkipInit skipInit) { super(skipInit); }
    public CAAnimation(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    
    /* SceneKit extensions */
    @WeaklyLinked
    public boolean usesSceneTimeBase() {
        return org.robovm.apple.scenekit.CAAnimationExtensions.usesSceneTimeBase(this);
    }
    @WeaklyLinked
    public void setUsesSceneTimeBase(boolean v) {
        org.robovm.apple.scenekit.CAAnimationExtensions.setUsesSceneTimeBase(this, v);
    }
    @WeaklyLinked
    public @MachineSizedFloat double getFadeInDuration() {
        return org.robovm.apple.scenekit.CAAnimationExtensions.getFadeInDuration(this);
    }
    @WeaklyLinked
    public void setFadeInDuration(@MachineSizedFloat double v) {
        org.robovm.apple.scenekit.CAAnimationExtensions.setFadeInDuration(this, v);
    }
    @WeaklyLinked
    public @MachineSizedFloat double getFadeOutDuration() {
        return org.robovm.apple.scenekit.CAAnimationExtensions.getFadeOutDuration(this);
    }
    @WeaklyLinked
    public void setFadeOutDuration(@MachineSizedFloat double v) {
        org.robovm.apple.scenekit.CAAnimationExtensions.setFadeOutDuration(this, v);
    }
    @WeaklyLinked
    public NSArray<org.robovm.apple.scenekit.SCNAnimationEvent> getAnimationEvents() {
        return org.robovm.apple.scenekit.CAAnimationExtensions.getAnimationEvents(this);
    }
    @WeaklyLinked
    public void setAnimationEvents(NSArray<org.robovm.apple.scenekit.SCNAnimationEvent> v) {
        org.robovm.apple.scenekit.CAAnimationExtensions.setAnimationEvents(this, v);
    }
    /*<properties>*/
    @Property(selector = "timingFunction")
    public native CAMediaTimingFunction getTimingFunction();
    @Property(selector = "setTimingFunction:")
    public native void setTimingFunction(CAMediaTimingFunction v);
    @Property(selector = "delegate")
    public native CAAnimationDelegate getDelegate();
    @Property(selector = "setDelegate:")
    public native void setDelegate(CAAnimationDelegate v);
    @Property(selector = "isRemovedOnCompletion")
    public native boolean isRemovedOnCompletion();
    @Property(selector = "setRemovedOnCompletion:")
    public native void setRemovedOnCompletion(boolean v);
    @Property(selector = "beginTime")
    public native double getBeginTime();
    @Property(selector = "setBeginTime:")
    public native void setBeginTime(double v);
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "setDuration:")
    public native void setDuration(double v);
    @Property(selector = "speed")
    public native float getSpeed();
    @Property(selector = "setSpeed:")
    public native void setSpeed(float v);
    @Property(selector = "timeOffset")
    public native double getTimeOffset();
    @Property(selector = "setTimeOffset:")
    public native void setTimeOffset(double v);
    @Property(selector = "repeatCount")
    public native float getRepeatCount();
    @Property(selector = "setRepeatCount:")
    public native void setRepeatCount(float v);
    @Property(selector = "repeatDuration")
    public native double getRepeatDuration();
    @Property(selector = "setRepeatDuration:")
    public native void setRepeatDuration(double v);
    @Property(selector = "autoreverses")
    public native boolean autoreverses();
    @Property(selector = "setAutoreverses:")
    public native void setAutoreverses(boolean v);
    @Property(selector = "fillMode")
    public native CAFillMode getFillMode();
    @Property(selector = "setFillMode:")
    public native void setFillMode(CAFillMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CACurrentMediaTime", optional=true)
    public static native double getCurrentMediaTime();
    
    @Method(selector = "shouldArchiveValueForKey:")
    public native boolean shouldArchiveValue(String key);
    @Method(selector = "defaultValueForKey:")
    public static native NSObject getDefaultValue(String key);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "runActionForKey:object:arguments:")
    public native void runAction(String event, NSObject anObject, NSDictionary<NSString, ?> dict);
    /*</methods>*/
}
