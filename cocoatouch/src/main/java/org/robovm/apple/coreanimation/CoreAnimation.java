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
    public static native String FillModeForwards();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeBackwards", optional=true)
    public static native String FillModeBackwards();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeBoth", optional=true)
    public static native String FillModeBoth();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFillModeRemoved", optional=true)
    public static native String FillModeRemoved();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityCenter", optional=true)
    public static native String GravityCenter();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTop", optional=true)
    public static native String GravityTop();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottom", optional=true)
    public static native String GravityBottom();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityLeft", optional=true)
    public static native String GravityLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityRight", optional=true)
    public static native String GravityRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopLeft", optional=true)
    public static native String GravityTopLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityTopRight", optional=true)
    public static native String GravityTopRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomLeft", optional=true)
    public static native String GravityBottomLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityBottomRight", optional=true)
    public static native String GravityBottomRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResize", optional=true)
    public static native String GravityResize();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspect", optional=true)
    public static native String GravityResizeAspect();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAGravityResizeAspectFill", optional=true)
    public static native String GravityResizeAspectFill();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFilterNearest", optional=true)
    public static native String FilterNearest();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAFilterLinear", optional=true)
    public static native String FilterLinear();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFilterTrilinear", optional=true)
    public static native String FilterTrilinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAOnOrderIn", optional=true)
    public static native String OnOrderIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAOnOrderOut", optional=true)
    public static native String OnOrderOut();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransition", optional=true)
    public static native String Transition();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationLinear", optional=true)
    public static native String AnimationLinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationDiscrete", optional=true)
    public static native String AnimationDiscrete();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationPaced", optional=true)
    public static native String AnimationPaced();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubic", optional=true)
    public static native String AnimationCubic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationCubicPaced", optional=true)
    public static native String AnimationCubicPaced();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationRotateAuto", optional=true)
    public static native String AnimationRotateAuto();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAAnimationRotateAutoReverse", optional=true)
    public static native String AnimationRotateAutoReverse();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFade", optional=true)
    public static native String TransitionFade();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionMoveIn", optional=true)
    public static native String TransitionMoveIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionPush", optional=true)
    public static native String TransitionPush();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionReveal", optional=true)
    public static native String TransitionReveal();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromRight", optional=true)
    public static native String TransitionFromRight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromLeft", optional=true)
    public static native String TransitionFromLeft();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromTop", optional=true)
    public static native String TransitionFromTop();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransitionFromBottom", optional=true)
    public static native String TransitionFromBottom();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoint", optional=true)
    public static native String EmitterLayerPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerLine", optional=true)
    public static native String EmitterLayerLine();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerRectangle", optional=true)
    public static native String EmitterLayerRectangle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCuboid", optional=true)
    public static native String EmitterLayerCuboid();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCircle", optional=true)
    public static native String EmitterLayerCircle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSphere", optional=true)
    public static native String EmitterLayerSphere();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoints", optional=true)
    public static native String EmitterLayerPoints();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOutline", optional=true)
    public static native String EmitterLayerOutline();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSurface", optional=true)
    public static native String EmitterLayerSurface();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerVolume", optional=true)
    public static native String EmitterLayerVolume();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerUnordered", optional=true)
    public static native String EmitterLayerUnordered();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestFirst", optional=true)
    public static native String EmitterLayerOldestFirst();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestLast", optional=true)
    public static native String EmitterLayerOldestLast();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerBackToFront", optional=true)
    public static native String EmitterLayerBackToFront();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerAdditive", optional=true)
    public static native String EmitterLayerAdditive();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAGradientLayerAxial", optional=true)
    public static native String GradientLayerAxial();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionLinear", optional=true)
    public static native String MediaTimingFunctionLinear();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseIn", optional=true)
    public static native String MediaTimingFunctionEaseIn();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseOut", optional=true)
    public static native String MediaTimingFunctionEaseOut();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionEaseInEaseOut", optional=true)
    public static native String MediaTimingFunctionEaseInEaseOut();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAMediaTimingFunctionDefault", optional=true)
    public static native String MediaTimingFunctionDefault();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollNone", optional=true)
    public static native String ScrollNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollVertically", optional=true)
    public static native String ScrollVertically();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollHorizontally", optional=true)
    public static native String ScrollHorizontally();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCAScrollBoth", optional=true)
    public static native String ScrollBoth();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleNonZero", optional=true)
    public static native String FillRuleNonZero();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAFillRuleEvenOdd", optional=true)
    public static native String FillRuleEvenOdd();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinMiter", optional=true)
    public static native String LineJoinMiter();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinRound", optional=true)
    public static native String LineJoinRound();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineJoinBevel", optional=true)
    public static native String LineJoinBevel();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapButt", optional=true)
    public static native String LineCapButt();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapRound", optional=true)
    public static native String LineCapRound();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCALineCapSquare", optional=true)
    public static native String LineCapSquare();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationNone", optional=true)
    public static native String TruncationNone();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationStart", optional=true)
    public static native String TruncationStart();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationEnd", optional=true)
    public static native String TruncationEnd();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCATruncationMiddle", optional=true)
    public static native String TruncationMiddle();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentNatural", optional=true)
    public static native String AlignmentNatural();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentLeft", optional=true)
    public static native String AlignmentLeft();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentRight", optional=true)
    public static native String AlignmentRight();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentCenter", optional=true)
    public static native String AlignmentCenter();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="kCAAlignmentJustified", optional=true)
    public static native String AlignmentJustified();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationDuration", optional=true)
    public static native String TransactionAnimationDuration();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCATransactionDisableActions", optional=true)
    public static native String TransactionDisableActions();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCATransactionAnimationTimingFunction", optional=true)
    public static native String TransactionAnimationTimingFunction();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCATransactionCompletionBlock", optional=true)
    public static native String TransactionCompletionBlock();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateX", optional=true)
    public static native String ValueFunctionRotateX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateY", optional=true)
    public static native String ValueFunctionRotateY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionRotateZ", optional=true)
    public static native String ValueFunctionRotateZ();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScale", optional=true)
    public static native String ValueFunctionScale();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleX", optional=true)
    public static native String ValueFunctionScaleX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleY", optional=true)
    public static native String ValueFunctionScaleY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionScaleZ", optional=true)
    public static native String ValueFunctionScaleZ();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslate", optional=true)
    public static native String ValueFunctionTranslate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateX", optional=true)
    public static native String ValueFunctionTranslateX();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateY", optional=true)
    public static native String ValueFunctionTranslateY();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCAValueFunctionTranslateZ", optional=true)
    public static native String ValueFunctionTranslateZ();
    /*</methods>*/
}
