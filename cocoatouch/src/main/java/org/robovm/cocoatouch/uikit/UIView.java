/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIView /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ implements UIAppearance, UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIView() {}
    @Bind("initWithFrame:") public UIView(@Type("CGRect") CGRect aRect) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("alpha") public native @Type("CGFloat") float getAlpha();
    @Bind("setAlpha:") public native void setAlpha(@Type("CGFloat") float v);
    @Bind("autoresizesSubviews") public native @Type("BOOL") boolean isAutoresizesSubviews();
    @Bind("setAutoresizesSubviews:") public native void setAutoresizesSubviews(@Type("BOOL") boolean v);
    @Bind("autoresizingMask") public native @Type("UIViewAutoresizing") EnumSet<UIViewAutoresizing> getAutoresizingMask();
    @Bind("setAutoresizingMask:") public native void setAutoresizingMask(@Type("UIViewAutoresizing") EnumSet<UIViewAutoresizing> v);
    @Bind("backgroundColor") public native @Type("UIColor *") UIColor getBackgroundColor();
    @Bind("setBackgroundColor:") public native void setBackgroundColor(@Type("UIColor *") UIColor v);
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    @Bind("setBounds:") public native void setBounds(@Type("CGRect") CGRect v);
    @Bind("center") public native @Type("CGPoint") CGPoint getCenter();
    @Bind("setCenter:") public native void setCenter(@Type("CGPoint") CGPoint v);
    @Bind("clearsContextBeforeDrawing") public native @Type("BOOL") boolean isClearsContextBeforeDrawing();
    @Bind("setClearsContextBeforeDrawing:") public native void setClearsContextBeforeDrawing(@Type("BOOL") boolean v);
    @Bind("clipsToBounds") public native @Type("BOOL") boolean isClipsToBounds();
    @Bind("setClipsToBounds:") public native void setClipsToBounds(@Type("BOOL") boolean v);
    @Bind("contentMode") public native @Type("UIViewContentMode") UIViewContentMode getContentMode();
    @Bind("setContentMode:") public native void setContentMode(@Type("UIViewContentMode") UIViewContentMode v);
    @Bind("contentScaleFactor") public native @Type("CGFloat") float getContentScaleFactor();
    @Bind("setContentScaleFactor:") public native void setContentScaleFactor(@Type("CGFloat") float v);
    @Bind("isExclusiveTouch") public native @Type("BOOL") boolean isExclusiveTouch();
    @Bind("setExclusiveTouch:") public native void setExclusiveTouch(@Type("BOOL") boolean v);
    @Bind("frame") public native @Type("CGRect") CGRect getFrame();
    @Bind("setFrame:") public native void setFrame(@Type("CGRect") CGRect v);
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    @Bind("setGestureRecognizers:") public native void setGestureRecognizers(@Type("NSArray *") NSArray v);
    @Bind("isHidden") public native @Type("BOOL") boolean isHidden();
    @Bind("setHidden:") public native void setHidden(@Type("BOOL") boolean v);
    @Bind("layer") public native @Type("CALayer *") CALayer getLayer();
    @Bind("isMultipleTouchEnabled") public native @Type("BOOL") boolean isMultipleTouchEnabled();
    @Bind("setMultipleTouchEnabled:") public native void setMultipleTouchEnabled(@Type("BOOL") boolean v);
    @Bind("isOpaque") public native @Type("BOOL") boolean isOpaque();
    @Bind("setOpaque:") public native void setOpaque(@Type("BOOL") boolean v);
    @Bind("restorationIdentifier") public native @Type("NSString *") String getRestorationIdentifier();
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(@Type("NSString *") String v);
    @Bind("subviews") public native @Type("NSArray *") NSArray getSubviews();
    @Bind("superview") public native @Type("UIView *") UIView getSuperview();
    @Bind("tag") public native @Type("NSInteger") int getTag();
    @Bind("setTag:") public native void setTag(@Type("NSInteger") int v);
    @Bind("transform") public native @Type("CGAffineTransform") CGAffineTransform getTransform();
    @Bind("setTransform:") public native void setTransform(@Type("CGAffineTransform") CGAffineTransform v);
    @Bind("isUserInteractionEnabled") public native @Type("BOOL") boolean isUserInteractionEnabled();
    @Bind("setUserInteractionEnabled:") public native void setUserInteractionEnabled(@Type("BOOL") boolean v);
    @Bind("window") public native @Type("UIWindow *") UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    @Bind("animateWithDuration:animations:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("void (^)(void)") VoidBlock animations);
    @Bind("animateWithDuration:animations:completion:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    @Bind("animateWithDuration:delay:options:animations:completion:") public native static @Type("void") void animate(@Type("NSTimeInterval") double duration, @Type("NSTimeInterval") double delay, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    @Bind("areAnimationsEnabled") public native static @Type("BOOL") boolean areAnimationsEnabled();
    @Bind("beginAnimations:context:") public native static @Type("void") void beginAnimations(@Type("NSString *") String animationID, @Type("void *") VoidPtr context);
    @Bind("commitAnimations") public native static @Type("void") void commitAnimations();
    @Bind("layerClass") public native static @Type("Class") ObjCClass getLayerClass();
    @Bind("requiresConstraintBasedLayout") public native static @Type("BOOL") boolean requiresConstraintBasedLayout();
    @Bind("setAnimationBeginsFromCurrentState:") public native static @Type("void") void setAnimationBeginsFromCurrentState(@Type("BOOL") boolean fromCurrentState);
    @Bind("setAnimationCurve:") public native static @Type("void") void setAnimationCurve(@Type("UIViewAnimationCurve") UIViewAnimationCurve curve);
    @Bind("setAnimationDelay:") public native static @Type("void") void setAnimationDelay(@Type("NSTimeInterval") double delay);
    @Bind("setAnimationDelegate:") public native static @Type("void") void setAnimationDelegate(@Type("id") NSObject delegate);
    @Bind("setAnimationDidStopSelector:") public native static @Type("void") void setAnimationDidStopSelector(@Type("SEL") Selector selector);
    @Bind("setAnimationRepeatAutoreverses:") public native static @Type("void") void setAnimationRepeatAutoreverses(@Type("BOOL") boolean repeatAutoreverses);
    @Bind("setAnimationRepeatCount:") public native static @Type("void") void setAnimationRepeatCount(@Type("float") float repeatCount);
    @Bind("setAnimationStartDate:") public native static @Type("void") void setAnimationStartDate(@Type("NSDate *") NSDate startTime);
    @Bind("setAnimationTransition:forView:cache:") public native static @Type("void") void setAnimationTransition(@Type("UIViewAnimationTransition") UIViewAnimationTransition transition, @Type("UIView *") UIView view, @Type("BOOL") boolean cache);
    @Bind("setAnimationWillStartSelector:") public native static @Type("void") void setAnimationWillStartSelector(@Type("SEL") Selector selector);
    @Bind("setAnimationsEnabled:") public native static @Type("void") void setAnimationsEnabled(@Type("BOOL") boolean enabled);
    @Bind("setAnimationDuration:") public native static @Type("void") void setDurationForAnimation(@Type("NSTimeInterval") double duration);
    @Bind("transitionWithView:duration:options:animations:completion:") public native static @Type("void") void transition(@Type("UIView *") UIView view, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    @Bind("transitionFromView:toView:duration:options:completion:") public native static @Type("void") void transition(@Type("UIView *") UIView fromView, @Type("UIView *") UIView toView, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    @Bind("addConstraint:") public native @Type("void") void addConstraint(@Type("NSLayoutConstraint *") NSLayoutConstraint constraint);
    @Bind("addConstraints:") public native @Type("void") void addConstraints(@Type("NSArray *") NSArray constraints);
    @Bind("addGestureRecognizer:") public native @Type("void") void addGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    @Bind("addSubview:") public native @Type("void") void addSubview(@Type("UIView *") UIView view);
    @Bind("alignmentRectForFrame:") public native @Type("CGRect") CGRect alignmentRectForFrame(@Type("CGRect") CGRect frame);
    @Bind("alignmentRectInsets") public native @Type("UIEdgeInsets") UIEdgeInsets alignmentRectInsets();
    @Bind("bringSubviewToFront:") public native @Type("void") void bringSubviewToFront(@Type("UIView *") UIView view);
    @Bind("constraints") public native @Type("NSArray *") NSArray constraints();
    @Bind("constraintsAffectingLayoutForAxis:") public native @Type("NSArray *") NSArray constraintsAffectingLayoutForAxis(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    @Bind("convertPoint:fromView:") public native @Type("CGPoint") CGPoint convertPointFromView(@Type("CGPoint") CGPoint point, @Type("UIView *") UIView view);
    @Bind("convertPoint:toView:") public native @Type("CGPoint") CGPoint convertPointToView(@Type("CGPoint") CGPoint point, @Type("UIView *") UIView view);
    @Bind("convertRect:fromView:") public native @Type("CGRect") CGRect convertRectFromView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view);
    @Bind("convertRect:toView:") public native @Type("CGRect") CGRect convertRectToView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view);
    @Bind("decodeRestorableStateWithCoder:") public native @Type("void") void decodeRestorableStateWithCoder(@Type("NSCoder *") NSCoder coder);
    @Bind("didAddSubview:") public native @Type("void") void didAddSubview(@Type("UIView *") UIView subview);
    @Bind("didMoveToSuperview") public native @Type("void") void didMoveToSuperview();
    @Bind("didMoveToWindow") public native @Type("void") void didMoveToWindow();
    @Bind("drawRect:") public native @Type("void") void draw(@Type("CGRect") CGRect rect);
    @Bind("drawRect:forViewPrintFormatter:") public native @Type("void") void drawRect(@Type("CGRect") CGRect area, @Type("UIViewPrintFormatter *") UIViewPrintFormatter formatter);
    @Bind("encodeRestorableStateWithCoder:") public native @Type("void") void encodeRestorableStateWithCoder(@Type("NSCoder *") NSCoder coder);
    @Bind("endEditing:") public native @Type("BOOL") boolean endEditing(@Type("BOOL") boolean force);
    @Bind("exchangeSubviewAtIndex:withSubviewAtIndex:") public native @Type("void") void exchangeSubview(@Type("NSInteger") int index1, @Type("NSInteger") int index2);
    @Bind("exerciseAmbiguityInLayout") public native @Type("void") void exerciseAmbiguityInLayout();
    @Bind("frameForAlignmentRect:") public native @Type("CGRect") CGRect frameForAlignmentRect(@Type("CGRect") CGRect alignmentRect);
    @Bind("gestureRecognizerShouldBegin:") public native @Type("BOOL") boolean gestureRecognizerShouldBegin(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    @Bind("contentHuggingPriorityForAxis:") public native @Type("UILayoutPriority") UILayoutPriority getContentHuggingPriority(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    @Bind("sizeThatFits:") public native @Type("CGSize") CGSize getSizeThatFits(@Type("CGSize") CGSize size);
    @Bind("contentCompressionResistancePriorityForAxis:") public native @Type("UILayoutPriority") UILayoutPriority getSontentCompressionResistancePriority(@Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    @Bind("viewPrintFormatter") public native @Type("UIViewPrintFormatter *") UIViewPrintFormatter getViewPrintFormatter();
    @Bind("viewWithTag:") public native @Type("UIView *") UIView getViewWithTag(@Type("NSInteger") int tag);
    @Bind("hasAmbiguousLayout") public native @Type("BOOL") boolean hasAmbiguousLayout();
    @Bind("hitTest:withEvent:") public native @Type("UIView *") UIView hitTest(@Type("CGPoint") CGPoint point, @Type("UIEvent *") UIEvent event);
    @Bind("insertSubview:atIndex:") public native @Type("void") void insertSubview(@Type("UIView *") UIView view, @Type("NSInteger") int index);
    @Bind("insertSubview:aboveSubview:") public native @Type("void") void insertSubviewAbove(@Type("UIView *") UIView view, @Type("UIView *") UIView siblingSubview);
    @Bind("insertSubview:belowSubview:") public native @Type("void") void insertSubviewBelow(@Type("UIView *") UIView view, @Type("UIView *") UIView siblingSubview);
    @Bind("intrinsicContentSize") public native @Type("CGSize") CGSize intrinsicContentSize();
    @Bind("invalidateIntrinsicContentSize") public native @Type("void") void invalidateIntrinsicContentSize();
    @Bind("isDescendantOfView:") public native @Type("BOOL") boolean isDescendantOf(@Type("UIView *") UIView view);
    @Bind("layoutIfNeeded") public native @Type("void") void layoutIfNeeded();
    @Bind("layoutSubviews") public native @Type("void") void layoutSubviews();
    @Bind("needsUpdateConstraints") public native @Type("BOOL") boolean needsUpdateConstraints();
    @Bind("pointInside:withEvent:") public native @Type("BOOL") boolean pointInside(@Type("CGPoint") CGPoint point, @Type("UIEvent *") UIEvent event);
    @Bind("removeConstraint:") public native @Type("void") void removeConstraint(@Type("NSLayoutConstraint *") NSLayoutConstraint constraint);
    @Bind("removeConstraints:") public native @Type("void") void removeConstraints(@Type("NSArray *") NSArray constraints);
    @Bind("removeFromSuperview") public native @Type("void") void removeFromSuperview();
    @Bind("removeGestureRecognizer:") public native @Type("void") void removeGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer gestureRecognizer);
    @Bind("sizeToFit") public native @Type("void") void resizeToFit();
    @Bind("sendSubviewToBack:") public native @Type("void") void sendSubviewToBack(@Type("UIView *") UIView view);
    @Bind("setContentCompressionResistancePriority:forAxis:") public native @Type("void") void setContentCompressionResistancePriority(@Type("UILayoutPriority") UILayoutPriority priority, @Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    @Bind("setContentHuggingPriority:forAxis:") public native @Type("void") void setContentHuggingPriority(@Type("UILayoutPriority") UILayoutPriority priority, @Type("UILayoutConstraintAxis") UILayoutConstraintAxis axis);
    @Bind("setNeedsDisplay") public native @Type("void") void setNeedsDisplay();
    @Bind("setNeedsDisplayInRect:") public native @Type("void") void setNeedsDisplay(@Type("CGRect") CGRect invalidRect);
    @Bind("setNeedsLayout") public native @Type("void") void setNeedsLayout();
    @Bind("setNeedsUpdateConstraints") public native @Type("void") void setNeedsUpdateConstraints();
    @Bind("setTranslatesAutoresizingMaskIntoConstraints:") public native @Type("void") void setTranslatesAutoresizingMaskIntoConstraints(@Type("BOOL") boolean flag);
    @Bind("systemLayoutSizeFittingSize:") public native @Type("CGSize") CGSize systemLayoutSizeFittingSize(@Type("CGSize") CGSize targetSize);
    @Bind("translatesAutoresizingMaskIntoConstraints") public native @Type("BOOL") boolean translatesAutoresizingMaskIntoConstraints();
    @Bind("updateConstraints") public native @Type("void") void updateConstraints();
    @Bind("updateConstraintsIfNeeded") public native @Type("void") void updateConstraintsIfNeeded();
    @Bind("viewForBaselineLayout") public native @Type("UIView *") UIView viewForBaselineLayout();
    @Bind("willMoveToSuperview:") public native @Type("void") void willMoveToSuperview(@Type("UIView *") UIView newSuperview);
    @Bind("willMoveToWindow:") public native @Type("void") void willMoveToWindow(@Type("UIWindow *") UIWindow newWindow);
    @Bind("willRemoveSubview:") public native @Type("void") void willRemoveSubview(@Type("UIView *") UIView subview);
    /*</methods>*/

}
