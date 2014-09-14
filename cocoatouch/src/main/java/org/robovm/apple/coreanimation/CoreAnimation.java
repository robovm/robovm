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
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreAnimation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreAnimation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="CATransform3DIdentity", optional=true)
    public static native @ByVal CATransform3D Transform3DIdentity();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeForwards", optional=true)
    public static native NSString FillModeForwards();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeBackwards", optional=true)
    public static native NSString FillModeBackwards();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeBoth", optional=true)
    public static native NSString FillModeBoth();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeRemoved", optional=true)
    public static native NSString FillModeRemoved();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityCenter", optional=true)
    public static native NSString GravityCenter();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTop", optional=true)
    public static native NSString GravityTop();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottom", optional=true)
    public static native NSString GravityBottom();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityLeft", optional=true)
    public static native NSString GravityLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityRight", optional=true)
    public static native NSString GravityRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopLeft", optional=true)
    public static native NSString GravityTopLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopRight", optional=true)
    public static native NSString GravityTopRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomLeft", optional=true)
    public static native NSString GravityBottomLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomRight", optional=true)
    public static native NSString GravityBottomRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResize", optional=true)
    public static native NSString GravityResize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspect", optional=true)
    public static native NSString GravityResizeAspect();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspectFill", optional=true)
    public static native NSString GravityResizeAspectFill();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFilterNearest", optional=true)
    public static native NSString FilterNearest();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFilterLinear", optional=true)
    public static native NSString FilterLinear();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFilterTrilinear", optional=true)
    public static native NSString FilterTrilinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAOnOrderIn", optional=true)
    public static native NSString OnOrderIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAOnOrderOut", optional=true)
    public static native NSString OnOrderOut();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransition", optional=true)
    public static native NSString Transition();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationLinear", optional=true)
    public static native NSString AnimationLinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationDiscrete", optional=true)
    public static native NSString AnimationDiscrete();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationPaced", optional=true)
    public static native NSString AnimationPaced();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubic", optional=true)
    public static native NSString AnimationCubic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubicPaced", optional=true)
    public static native NSString AnimationCubicPaced();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationRotateAuto", optional=true)
    public static native NSString AnimationRotateAuto();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationRotateAutoReverse", optional=true)
    public static native NSString AnimationRotateAutoReverse();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFade", optional=true)
    public static native NSString TransitionFade();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionMoveIn", optional=true)
    public static native NSString TransitionMoveIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionPush", optional=true)
    public static native NSString TransitionPush();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionReveal", optional=true)
    public static native NSString TransitionReveal();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromRight", optional=true)
    public static native NSString TransitionFromRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromLeft", optional=true)
    public static native NSString TransitionFromLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromTop", optional=true)
    public static native NSString TransitionFromTop();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromBottom", optional=true)
    public static native NSString TransitionFromBottom();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoint", optional=true)
    public static native NSString EmitterLayerPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerLine", optional=true)
    public static native NSString EmitterLayerLine();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerRectangle", optional=true)
    public static native NSString EmitterLayerRectangle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCuboid", optional=true)
    public static native NSString EmitterLayerCuboid();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCircle", optional=true)
    public static native NSString EmitterLayerCircle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSphere", optional=true)
    public static native NSString EmitterLayerSphere();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoints", optional=true)
    public static native NSString EmitterLayerPoints();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOutline", optional=true)
    public static native NSString EmitterLayerOutline();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSurface", optional=true)
    public static native NSString EmitterLayerSurface();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerVolume", optional=true)
    public static native NSString EmitterLayerVolume();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerUnordered", optional=true)
    public static native NSString EmitterLayerUnordered();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestFirst", optional=true)
    public static native NSString EmitterLayerOldestFirst();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestLast", optional=true)
    public static native NSString EmitterLayerOldestLast();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerBackToFront", optional=true)
    public static native NSString EmitterLayerBackToFront();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerAdditive", optional=true)
    public static native NSString EmitterLayerAdditive();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAGradientLayerAxial", optional=true)
    public static native NSString GradientLayerAxial();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionLinear", optional=true)
    public static native NSString MediaTimingFunctionLinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseIn", optional=true)
    public static native NSString MediaTimingFunctionEaseIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseOut", optional=true)
    public static native NSString MediaTimingFunctionEaseOut();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseInEaseOut", optional=true)
    public static native NSString MediaTimingFunctionEaseInEaseOut();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionDefault", optional=true)
    public static native NSString MediaTimingFunctionDefault();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollNone", optional=true)
    public static native NSString ScrollNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollVertically", optional=true)
    public static native NSString ScrollVertically();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollHorizontally", optional=true)
    public static native NSString ScrollHorizontally();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollBoth", optional=true)
    public static native NSString ScrollBoth();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleNonZero", optional=true)
    public static native NSString FillRuleNonZero();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleEvenOdd", optional=true)
    public static native NSString FillRuleEvenOdd();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinMiter", optional=true)
    public static native NSString LineJoinMiter();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinRound", optional=true)
    public static native NSString LineJoinRound();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinBevel", optional=true)
    public static native NSString LineJoinBevel();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapButt", optional=true)
    public static native NSString LineCapButt();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapRound", optional=true)
    public static native NSString LineCapRound();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapSquare", optional=true)
    public static native NSString LineCapSquare();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationNone", optional=true)
    public static native NSString TruncationNone();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationStart", optional=true)
    public static native NSString TruncationStart();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationEnd", optional=true)
    public static native NSString TruncationEnd();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationMiddle", optional=true)
    public static native NSString TruncationMiddle();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentNatural", optional=true)
    public static native NSString AlignmentNatural();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentLeft", optional=true)
    public static native NSString AlignmentLeft();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentRight", optional=true)
    public static native NSString AlignmentRight();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentCenter", optional=true)
    public static native NSString AlignmentCenter();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentJustified", optional=true)
    public static native NSString AlignmentJustified();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationDuration", optional=true)
    public static native NSString TransactionAnimationDuration();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionDisableActions", optional=true)
    public static native NSString TransactionDisableActions();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationTimingFunction", optional=true)
    public static native NSString TransactionAnimationTimingFunction();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCATransactionCompletionBlock", optional=true)
    public static native NSString TransactionCompletionBlock();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateX", optional=true)
    public static native NSString ValueFunctionRotateX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateY", optional=true)
    public static native NSString ValueFunctionRotateY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateZ", optional=true)
    public static native NSString ValueFunctionRotateZ();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScale", optional=true)
    public static native NSString ValueFunctionScale();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleX", optional=true)
    public static native NSString ValueFunctionScaleX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleY", optional=true)
    public static native NSString ValueFunctionScaleY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleZ", optional=true)
    public static native NSString ValueFunctionScaleZ();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslate", optional=true)
    public static native NSString ValueFunctionTranslate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateX", optional=true)
    public static native NSString ValueFunctionTranslateX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateY", optional=true)
    public static native NSString ValueFunctionTranslateY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateZ", optional=true)
    public static native NSString ValueFunctionTranslateZ();
    /*</methods>*/
}
