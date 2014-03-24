/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAEmitterCell/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, CAMediaTiming/*</implements>*/ {

    /*<ptr>*/public static class CAEmitterCellPtr extends Ptr<CAEmitterCell, CAEmitterCellPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAEmitterCell.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAEmitterCell() {}
    protected CAEmitterCell(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "birthRate")
    public native float getBirthRate();
    @Property(selector = "setBirthRate:")
    public native void setBirthRate(float v);
    @Property(selector = "lifetime")
    public native float getLifetime();
    @Property(selector = "setLifetime:")
    public native void setLifetime(float v);
    @Property(selector = "lifetimeRange")
    public native float getLifetimeRange();
    @Property(selector = "setLifetimeRange:")
    public native void setLifetimeRange(float v);
    @Property(selector = "emissionLatitude")
    public native @MachineSizedFloat double getEmissionLatitude();
    @Property(selector = "setEmissionLatitude:")
    public native void setEmissionLatitude(@MachineSizedFloat double v);
    @Property(selector = "emissionLongitude")
    public native @MachineSizedFloat double getEmissionLongitude();
    @Property(selector = "setEmissionLongitude:")
    public native void setEmissionLongitude(@MachineSizedFloat double v);
    @Property(selector = "emissionRange")
    public native @MachineSizedFloat double getEmissionRange();
    @Property(selector = "setEmissionRange:")
    public native void setEmissionRange(@MachineSizedFloat double v);
    @Property(selector = "velocity")
    public native @MachineSizedFloat double getVelocity();
    @Property(selector = "setVelocity:")
    public native void setVelocity(@MachineSizedFloat double v);
    @Property(selector = "velocityRange")
    public native @MachineSizedFloat double getVelocityRange();
    @Property(selector = "setVelocityRange:")
    public native void setVelocityRange(@MachineSizedFloat double v);
    @Property(selector = "xAcceleration")
    public native @MachineSizedFloat double getXAcceleration();
    @Property(selector = "setXAcceleration:")
    public native void setXAcceleration(@MachineSizedFloat double v);
    @Property(selector = "yAcceleration")
    public native @MachineSizedFloat double getYAcceleration();
    @Property(selector = "setYAcceleration:")
    public native void setYAcceleration(@MachineSizedFloat double v);
    @Property(selector = "zAcceleration")
    public native @MachineSizedFloat double getZAcceleration();
    @Property(selector = "setZAcceleration:")
    public native void setZAcceleration(@MachineSizedFloat double v);
    @Property(selector = "scale")
    public native @MachineSizedFloat double getScale();
    @Property(selector = "setScale:")
    public native void setScale(@MachineSizedFloat double v);
    @Property(selector = "scaleRange")
    public native @MachineSizedFloat double getScaleRange();
    @Property(selector = "setScaleRange:")
    public native void setScaleRange(@MachineSizedFloat double v);
    @Property(selector = "scaleSpeed")
    public native @MachineSizedFloat double getScaleSpeed();
    @Property(selector = "setScaleSpeed:")
    public native void setScaleSpeed(@MachineSizedFloat double v);
    @Property(selector = "spin")
    public native @MachineSizedFloat double getSpin();
    @Property(selector = "setSpin:")
    public native void setSpin(@MachineSizedFloat double v);
    @Property(selector = "spinRange")
    public native @MachineSizedFloat double getSpinRange();
    @Property(selector = "setSpinRange:")
    public native void setSpinRange(@MachineSizedFloat double v);
    @Property(selector = "color")
    public native CGColor getColor();
    @Property(selector = "setColor:")
    public native void setColor(CGColor v);
    @Property(selector = "redRange")
    public native float getRedRange();
    @Property(selector = "setRedRange:")
    public native void setRedRange(float v);
    @Property(selector = "greenRange")
    public native float getGreenRange();
    @Property(selector = "setGreenRange:")
    public native void setGreenRange(float v);
    @Property(selector = "blueRange")
    public native float getBlueRange();
    @Property(selector = "setBlueRange:")
    public native void setBlueRange(float v);
    @Property(selector = "alphaRange")
    public native float getAlphaRange();
    @Property(selector = "setAlphaRange:")
    public native void setAlphaRange(float v);
    @Property(selector = "redSpeed")
    public native float getRedSpeed();
    @Property(selector = "setRedSpeed:")
    public native void setRedSpeed(float v);
    @Property(selector = "greenSpeed")
    public native float getGreenSpeed();
    @Property(selector = "setGreenSpeed:")
    public native void setGreenSpeed(float v);
    @Property(selector = "blueSpeed")
    public native float getBlueSpeed();
    @Property(selector = "setBlueSpeed:")
    public native void setBlueSpeed(float v);
    @Property(selector = "alphaSpeed")
    public native float getAlphaSpeed();
    @Property(selector = "setAlphaSpeed:")
    public native void setAlphaSpeed(float v);
    @Property(selector = "contents")
    public native NSObject getContents();
    @Property(selector = "setContents:")
    public native void setContents(NSObject v);
    @Property(selector = "contentsRect")
    public native @ByVal CGRect getContentsRect();
    @Property(selector = "setContentsRect:")
    public native void setContentsRect(@ByVal CGRect v);
    @Property(selector = "minificationFilter")
    public native String getMinificationFilter();
    @Property(selector = "setMinificationFilter:")
    public native void setMinificationFilter(String v);
    @Property(selector = "magnificationFilter")
    public native String getMagnificationFilter();
    @Property(selector = "setMagnificationFilter:")
    public native void setMagnificationFilter(String v);
    @Property(selector = "minificationFilterBias")
    public native float getMinificationFilterBias();
    @Property(selector = "setMinificationFilterBias:")
    public native void setMinificationFilterBias(float v);
    @Property(selector = "emitterCells")
    public native NSArray<?> getEmitterCells();
    @Property(selector = "setEmitterCells:")
    public native void setEmitterCells(NSArray<?> v);
    @Property(selector = "style")
    public native NSDictionary<?, ?> getStyle();
    @Property(selector = "setStyle:")
    public native void setStyle(NSDictionary<?, ?> v);
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
    public native boolean isAutoreverses();
    @Property(selector = "setAutoreverses:")
    public native void setAutoreverses(boolean v);
    @Property(selector = "fillMode")
    public native String getFillMode();
    @Property(selector = "setFillMode:")
    public native void setFillMode(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "shouldArchiveValueForKey:")
    public native boolean shouldArchiveValueForKey$(String key);
    @Method(selector = "emitterCell")
    public static native NSObject emitterCell();
    @Method(selector = "defaultValueForKey:")
    public static native NSObject defaultValueForKey$(String key);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
