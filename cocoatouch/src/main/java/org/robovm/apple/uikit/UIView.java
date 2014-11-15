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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIView/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIAppearanceContainer, UIDynamicItem, UITraitEnvironment, UICoordinateSpace, UIAccessibilityIdentification/*</implements>*/ {

    /*<ptr>*/public static class UIViewPtr extends Ptr<UIView, UIViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIView() {}
    protected UIView(SkipInit skipInit) { super(skipInit); }
    public UIView(@ByVal CGRect frame) { super((SkipInit) null); initObject(initWithFrame$(frame)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isUserInteractionEnabled")
    public native boolean isUserInteractionEnabled();
    @Property(selector = "setUserInteractionEnabled:")
    public native void setUserInteractionEnabled(boolean v);
    @Property(selector = "tag")
    public native @MachineSizedSInt long getTag();
    @Property(selector = "setTag:")
    public native void setTag(@MachineSizedSInt long v);
    @Property(selector = "layer")
    public native CALayer getLayer();
    @Property(selector = "frame")
    public native @ByVal CGRect getFrame();
    @Property(selector = "setFrame:")
    public native void setFrame(@ByVal CGRect v);
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "setBounds:")
    public native void setBounds(@ByVal CGRect v);
    @Property(selector = "center")
    public native @ByVal CGPoint getCenter();
    @Property(selector = "setCenter:")
    public native void setCenter(@ByVal CGPoint v);
    @Property(selector = "transform")
    public native @ByVal CGAffineTransform getTransform();
    @Property(selector = "setTransform:")
    public native void setTransform(@ByVal CGAffineTransform v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "contentScaleFactor")
    public native @MachineSizedFloat double getContentScaleFactor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setContentScaleFactor:")
    public native void setContentScaleFactor(@MachineSizedFloat double v);
    @Property(selector = "isMultipleTouchEnabled")
    public native boolean isMultipleTouchEnabled();
    @Property(selector = "setMultipleTouchEnabled:")
    public native void setMultipleTouchEnabled(boolean v);
    @Property(selector = "isExclusiveTouch")
    public native boolean isExclusiveTouch();
    @Property(selector = "setExclusiveTouch:")
    public native void setExclusiveTouch(boolean v);
    @Property(selector = "autoresizesSubviews")
    public native boolean isAutoresizesSubviews();
    @Property(selector = "setAutoresizesSubviews:")
    public native void setAutoresizesSubviews(boolean v);
    @Property(selector = "autoresizingMask")
    public native UIViewAutoresizing getAutoresizingMask();
    @Property(selector = "setAutoresizingMask:")
    public native void setAutoresizingMask(UIViewAutoresizing v);
    @Property(selector = "superview")
    public native UIView getSuperview();
    @Property(selector = "subviews")
    public native NSArray<UIView> getSubviews();
    @Property(selector = "window")
    public native UIWindow getWindow();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "layoutMargins")
    public native @ByVal UIEdgeInsets getLayoutMargins();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setLayoutMargins:")
    public native void setLayoutMargins(@ByVal UIEdgeInsets v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "preservesSuperviewLayoutMargins")
    public native boolean isPreservesSuperviewLayoutMargins();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPreservesSuperviewLayoutMargins:")
    public native void setPreservesSuperviewLayoutMargins(boolean v);
    @Property(selector = "clipsToBounds")
    public native boolean isClipsToBounds();
    @Property(selector = "setClipsToBounds:")
    public native void setClipsToBounds(boolean v);
    @Property(selector = "backgroundColor")
    public native UIColor getBackgroundColor();
    @Property(selector = "setBackgroundColor:")
    public native void setBackgroundColor(UIColor v);
    @Property(selector = "alpha")
    public native @MachineSizedFloat double getAlpha();
    @Property(selector = "setAlpha:")
    public native void setAlpha(@MachineSizedFloat double v);
    @Property(selector = "isOpaque")
    public native boolean isOpaque();
    @Property(selector = "setOpaque:")
    public native void setOpaque(boolean v);
    @Property(selector = "clearsContextBeforeDrawing")
    public native boolean isClearsContextBeforeDrawing();
    @Property(selector = "setClearsContextBeforeDrawing:")
    public native void setClearsContextBeforeDrawing(boolean v);
    @Property(selector = "isHidden")
    public native boolean isHidden();
    @Property(selector = "setHidden:")
    public native void setHidden(boolean v);
    @Property(selector = "contentMode")
    public native UIViewContentMode getContentMode();
    @Property(selector = "setContentMode:")
    public native void setContentMode(UIViewContentMode v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "contentStretch")
    public native @ByVal CGRect getContentStretch();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setContentStretch:")
    public native void setContentStretch(@ByVal CGRect v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "maskView")
    public native UIView getMaskView();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setMaskView:")
    public native void setMaskView(UIView v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "tintAdjustmentMode")
    public native UIViewTintAdjustmentMode getTintAdjustmentMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTintAdjustmentMode:")
    public native void setTintAdjustmentMode(UIViewTintAdjustmentMode v);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "gestureRecognizers")
    public native NSArray<UIGestureRecognizer> getGestureRecognizers();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setGestureRecognizers:")
    public native void setGestureRecognizers(NSArray<UIGestureRecognizer> v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "motionEffects")
    public native NSArray<UIMotionEffect> getMotionEffects();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setMotionEffects:")
    public native void setMotionEffects(NSArray<UIMotionEffect> v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "restorationIdentifier")
    public native String getRestorationIdentifier();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setRestorationIdentifier:")
    public native void setRestorationIdentifier(String v);
    @Property(selector = "traitCollection")
    public native UITraitCollection getTraitCollection();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityIdentifier")
    public native String getAccessibilityIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityIdentifier:")
    public native void setAccessibilityIdentifier(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIViewNoIntrinsicMetric", optional=true)
    public static native @MachineSizedFloat double NoIntrinsicMetric();
    
    @Method(selector = "initWithFrame:")
    protected native @Pointer long initWithFrame$(@ByVal CGRect frame);
    @Method(selector = "layerClass")
    public static native Class<?> getLayerClass();
    @Method(selector = "hitTest:withEvent:")
    public native UIView hitTest(@ByVal CGPoint point, UIEvent event);
    @Method(selector = "pointInside:withEvent:")
    public native boolean isPointInside(@ByVal CGPoint point, UIEvent event);
    @Method(selector = "convertPoint:toView:")
    public native @ByVal CGPoint convertPointToView(@ByVal CGPoint point, UIView view);
    @Method(selector = "convertPoint:fromView:")
    public native @ByVal CGPoint convertPointFromView(@ByVal CGPoint point, UIView view);
    @Method(selector = "convertRect:toView:")
    public native @ByVal CGRect convertRectToView(@ByVal CGRect rect, UIView view);
    @Method(selector = "convertRect:fromView:")
    public native @ByVal CGRect convertRectFromView(@ByVal CGRect rect, UIView view);
    @Method(selector = "sizeThatFits:")
    public native @ByVal CGSize getSizeThatFits(@ByVal CGSize size);
    @Method(selector = "sizeToFit")
    public native void sizeToFit();
    @Method(selector = "removeFromSuperview")
    public native void removeFromSuperview();
    @Method(selector = "insertSubview:atIndex:")
    public native void insertSubview(UIView view, @MachineSizedSInt long index);
    @Method(selector = "exchangeSubviewAtIndex:withSubviewAtIndex:")
    public native void exchangeSubview(@MachineSizedSInt long index1, @MachineSizedSInt long index2);
    @Method(selector = "addSubview:")
    public native void addSubview(UIView view);
    @Method(selector = "insertSubview:belowSubview:")
    public native void insertSubviewBelow(UIView view, UIView siblingSubview);
    @Method(selector = "insertSubview:aboveSubview:")
    public native void insertSubviewAbove(UIView view, UIView siblingSubview);
    @Method(selector = "bringSubviewToFront:")
    public native void bringSubviewToFront(UIView view);
    @Method(selector = "sendSubviewToBack:")
    public native void sendSubviewToBack(UIView view);
    @Method(selector = "didAddSubview:")
    public native void didAddSubview(UIView subview);
    @Method(selector = "willRemoveSubview:")
    public native void willRemoveSubview(UIView subview);
    @Method(selector = "willMoveToSuperview:")
    public native void willMoveToSuperview(UIView newSuperview);
    @Method(selector = "didMoveToSuperview")
    public native void didMoveToSuperview();
    @Method(selector = "willMoveToWindow:")
    public native void willMoveToWindow(UIWindow newWindow);
    @Method(selector = "didMoveToWindow")
    public native void didMoveToWindow();
    @Method(selector = "isDescendantOfView:")
    public native boolean isDescendantOf(UIView view);
    @Method(selector = "viewWithTag:")
    public native UIView getViewWithTag(@MachineSizedSInt long tag);
    @Method(selector = "setNeedsLayout")
    public native void setNeedsLayout();
    @Method(selector = "layoutIfNeeded")
    public native void layoutIfNeeded();
    @Method(selector = "layoutSubviews")
    public native void layoutSubviews();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "layoutMarginsDidChange")
    public native void layoutMarginsDidChange();
    @Method(selector = "drawRect:")
    public native void draw(@ByVal CGRect rect);
    @Method(selector = "setNeedsDisplay")
    public native void setNeedsDisplay();
    @Method(selector = "setNeedsDisplayInRect:")
    public native void setNeedsDisplay(@ByVal CGRect rect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "tintColorDidChange")
    public native void tintColorDidChange();
    @Method(selector = "beginAnimations:context:")
    public static native void beginAnimations(String animationID, VoidPtr context);
    @Method(selector = "commitAnimations")
    public static native void commitAnimations();
    @Method(selector = "setAnimationDelegate:")
    public static native void setAnimationDelegate(NSObject delegate);
    @Method(selector = "setAnimationWillStartSelector:")
    public static native void setAnimationWillStartSelector(Selector selector);
    @Method(selector = "setAnimationDidStopSelector:")
    public static native void setAnimationDidStopSelector(Selector selector);
    @Method(selector = "setAnimationDuration:")
    public static native void setAnimationDurationInSeconds(double duration);
    @Method(selector = "setAnimationDelay:")
    public static native void setAnimationDelay(double delay);
    @Method(selector = "setAnimationStartDate:")
    public static native void setAnimationStartDate(NSDate startDate);
    @Method(selector = "setAnimationCurve:")
    public static native void setAnimationCurve(UIViewAnimationCurve curve);
    @Method(selector = "setAnimationRepeatCount:")
    public static native void setAnimationRepeatCount(float repeatCount);
    @Method(selector = "setAnimationRepeatAutoreverses:")
    public static native void setAnimationRepeatAutoreverses(boolean repeatAutoreverses);
    @Method(selector = "setAnimationBeginsFromCurrentState:")
    public static native void setAnimationBeginsFromCurrentState(boolean fromCurrentState);
    @Method(selector = "setAnimationTransition:forView:cache:")
    public static native void setAnimationTransition(UIViewAnimationTransition transition, UIView view, boolean cache);
    @Method(selector = "setAnimationsEnabled:")
    public static native void setAnimationsEnabled(boolean enabled);
    @Method(selector = "areAnimationsEnabled")
    public static native boolean areAnimationsEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "performWithoutAnimation:")
    public static native void performWithoutAnimation(@Block Runnable actionsWithoutAnimation);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "animateWithDuration:delay:options:animations:completion:")
    public static native void animate(double duration, double delay, UIViewAnimationOptions options, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "animateWithDuration:animations:completion:")
    public static native void animate(double duration, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "animateWithDuration:animations:")
    public static native void animate(double duration, @Block Runnable animations);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "animateWithDuration:delay:usingSpringWithDamping:initialSpringVelocity:options:animations:completion:")
    public static native void animate(double duration, double delay, @MachineSizedFloat double dampingRatio, @MachineSizedFloat double velocity, UIViewAnimationOptions options, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "transitionWithView:duration:options:animations:completion:")
    public static native void transition(UIView view, double duration, UIViewAnimationOptions options, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "transitionFromView:toView:duration:options:completion:")
    public static native void transition(UIView fromView, UIView toView, double duration, UIViewAnimationOptions options, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "performSystemAnimation:onViews:options:animations:completion:")
    public static native void performSystemAnimation(UISystemAnimation animation, NSArray<UIView> views, UIViewAnimationOptions options, @Block Runnable parallelAnimations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "animateKeyframesWithDuration:delay:options:animations:completion:")
    public static native void animateKeyframes(double duration, double delay, UIViewKeyframeAnimationOptions options, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addKeyframeWithRelativeStartTime:relativeDuration:animations:")
    public static native void addKeyframe(double frameStartTime, double frameDuration, @Block Runnable animations);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "addGestureRecognizer:")
    public native void addGestureRecognizer(UIGestureRecognizer gestureRecognizer);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "removeGestureRecognizer:")
    public native void removeGestureRecognizer(UIGestureRecognizer gestureRecognizer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "gestureRecognizerShouldBegin:")
    public native boolean gestureRecognizerShouldBegin(UIGestureRecognizer gestureRecognizer);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addMotionEffect:")
    public native void addMotionEffect(UIMotionEffect effect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeMotionEffect:")
    public native void removeMotionEffect(UIMotionEffect effect);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "constraints")
    public native NSArray<NSLayoutConstraint> getConstraints();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "addConstraint:")
    public native void addConstraint(NSLayoutConstraint constraint);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "addConstraints:")
    public native void addConstraints(NSArray<NSLayoutConstraint> constraints);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "removeConstraint:")
    public native void removeConstraint(NSLayoutConstraint constraint);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "removeConstraints:")
    public native void removeConstraints(NSArray<NSLayoutConstraint> constraints);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "updateConstraintsIfNeeded")
    public native void updateConstraintsIfNeeded();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "updateConstraints")
    public native void updateConstraints();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "needsUpdateConstraints")
    public native boolean needsUpdateConstraints();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setNeedsUpdateConstraints")
    public native void setNeedsUpdateConstraints();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "translatesAutoresizingMaskIntoConstraints")
    public native boolean translatesAutoresizingMaskIntoConstraints();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setTranslatesAutoresizingMaskIntoConstraints:")
    public native void setTranslatesAutoresizingMaskIntoConstraints(boolean flag);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "requiresConstraintBasedLayout")
    public static native boolean requiresConstraintBasedLayout();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "alignmentRectForFrame:")
    public native @ByVal CGRect getAlignmentRectForFrame(@ByVal CGRect frame);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "frameForAlignmentRect:")
    public native @ByVal CGRect getFrameForAlignmentRect(@ByVal CGRect alignmentRect);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "alignmentRectInsets")
    public native @ByVal UIEdgeInsets getAlignmentRectInsets();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "viewForBaselineLayout")
    public native UIView getViewForBaselineLayout();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "intrinsicContentSize")
    public native @ByVal CGSize getIntrinsicContentSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "invalidateIntrinsicContentSize")
    public native void invalidateIntrinsicContentSize();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "contentHuggingPriorityForAxis:")
    public native float getContentHuggingPriority(UILayoutConstraintAxis axis);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setContentHuggingPriority:forAxis:")
    public native void setContentHuggingPriority(float priority, UILayoutConstraintAxis axis);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "contentCompressionResistancePriorityForAxis:")
    public native float getContentCompressionResistancePriority(UILayoutConstraintAxis axis);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setContentCompressionResistancePriority:forAxis:")
    public native void setContentCompressionResistancePriority(float priority, UILayoutConstraintAxis axis);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "systemLayoutSizeFittingSize:")
    public native @ByVal CGSize getSystemLayoutSizeFittingSize(UILayoutFittingSize targetSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "systemLayoutSizeFittingSize:withHorizontalFittingPriority:verticalFittingPriority:")
    public native @ByVal CGSize systemLayoutSizeFittingSize$withHorizontalFittingPriority$verticalFittingPriority$(@ByVal CGSize targetSize, float horizontalFittingPriority, float verticalFittingPriority);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "constraintsAffectingLayoutForAxis:")
    public native NSArray<NSLayoutConstraint> getConstraintsAffectingLayout(UILayoutConstraintAxis axis);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "hasAmbiguousLayout")
    public native boolean hasAmbiguousLayout();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "exerciseAmbiguityInLayout")
    public native void exerciseAmbiguityInLayout();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "encodeRestorableStateWithCoder:")
    public native void encodeRestorableState(NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeRestorableStateWithCoder:")
    public native void decodeRestorableState(NSCoder coder);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "snapshotViewAfterScreenUpdates:")
    public native UIView getSnapshot(boolean afterUpdates);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "resizableSnapshotViewFromRect:afterScreenUpdates:withCapInsets:")
    public native UIView getResizableSnapshot(@ByVal CGRect rect, boolean afterUpdates, @ByVal UIEdgeInsets capInsets);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "drawViewHierarchyInRect:afterScreenUpdates:")
    public native boolean drawViewHierarchy(@ByVal CGRect rect, boolean afterUpdates);
    @Method(selector = "endEditing:")
    public native boolean endEditing(boolean force);
    @Method(selector = "viewPrintFormatter")
    public native UIViewPrintFormatter getViewPrintFormatter();
    @Method(selector = "drawRect:forViewPrintFormatter:")
    public native void draw(@ByVal CGRect rect, UIViewPrintFormatter formatter);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    @Method(selector = "traitCollectionDidChange:")
    public native void traitCollectionDidChange(UITraitCollection previousTraitCollection);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertPoint:toCoordinateSpace:")
    public native @ByVal CGPoint convertPointToCoordinateSpace(@ByVal CGPoint point, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertPoint:fromCoordinateSpace:")
    public native @ByVal CGPoint convertPointFromCoordinateSpace(@ByVal CGPoint point, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertRect:toCoordinateSpace:")
    public native @ByVal CGRect convertRectToCoordinateSpace(@ByVal CGRect rect, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertRect:fromCoordinateSpace:")
    public native @ByVal CGRect convertRectFromCoordinateSpace(@ByVal CGRect rect, UICoordinateSpace coordinateSpace);
    /*</methods>*/
}
