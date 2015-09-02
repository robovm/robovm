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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CALayer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, CAMediaTiming/*</implements>*/ {

    /*<ptr>*/public static class CALayerPtr extends Ptr<CALayer, CALayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CALayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CALayer() {}
    protected CALayer(SkipInit skipInit) { super(skipInit); }
    public CALayer(CALayer layer) { super((SkipInit) null); initObject(init(layer)); }
    public CALayer(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "setBounds:")
    public native void setBounds(@ByVal CGRect v);
    @Property(selector = "position")
    public native @ByVal CGPoint getPosition();
    @Property(selector = "setPosition:")
    public native void setPosition(@ByVal CGPoint v);
    @Property(selector = "zPosition")
    public native @MachineSizedFloat double getZPosition();
    @Property(selector = "setZPosition:")
    public native void setZPosition(@MachineSizedFloat double v);
    @Property(selector = "anchorPoint")
    public native @ByVal CGPoint getAnchorPoint();
    @Property(selector = "setAnchorPoint:")
    public native void setAnchorPoint(@ByVal CGPoint v);
    @Property(selector = "anchorPointZ")
    public native @MachineSizedFloat double getAnchorPointZ();
    @Property(selector = "setAnchorPointZ:")
    public native void setAnchorPointZ(@MachineSizedFloat double v);
    @Property(selector = "transform")
    public native @ByVal CATransform3D getTransform();
    @Property(selector = "setTransform:")
    public native void setTransform(@ByVal CATransform3D v);
    @Property(selector = "frame")
    public native @ByVal CGRect getFrame();
    @Property(selector = "setFrame:")
    public native void setFrame(@ByVal CGRect v);
    @Property(selector = "isHidden")
    public native boolean isHidden();
    @Property(selector = "setHidden:")
    public native void setHidden(boolean v);
    @Property(selector = "isDoubleSided")
    public native boolean isDoubleSided();
    @Property(selector = "setDoubleSided:")
    public native void setDoubleSided(boolean v);
    @Property(selector = "isGeometryFlipped")
    public native boolean isGeometryFlipped();
    @Property(selector = "setGeometryFlipped:")
    public native void setGeometryFlipped(boolean v);
    @Property(selector = "superlayer")
    public native CALayer getSuperlayer();
    @Property(selector = "sublayers")
    public native NSArray<CALayer> getSublayers();
    @Property(selector = "setSublayers:")
    public native void setSublayers(NSArray<CALayer> v);
    @Property(selector = "sublayerTransform")
    public native @ByVal CATransform3D getSublayerTransform();
    @Property(selector = "setSublayerTransform:")
    public native void setSublayerTransform(@ByVal CATransform3D v);
    @Property(selector = "mask")
    public native CALayer getMask();
    @Property(selector = "setMask:")
    public native void setMask(CALayer v);
    @Property(selector = "masksToBounds")
    public native boolean masksToBounds();
    @Property(selector = "setMasksToBounds:")
    public native void setMasksToBounds(boolean v);
    @Property(selector = "contents")
    public native NSObject getContents();
    @Property(selector = "setContents:")
    public native void setContents(NSObject v);
    @Property(selector = "contentsRect")
    public native @ByVal CGRect getContentsRect();
    @Property(selector = "setContentsRect:")
    public native void setContentsRect(@ByVal CGRect v);
    @Property(selector = "contentsGravity")
    public native CAGravity getContentsGravity();
    @Property(selector = "setContentsGravity:")
    public native void setContentsGravity(CAGravity v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "contentsScale")
    public native @MachineSizedFloat double getContentsScale();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setContentsScale:")
    public native void setContentsScale(@MachineSizedFloat double v);
    @Property(selector = "contentsCenter")
    public native @ByVal CGRect getContentsCenter();
    @Property(selector = "setContentsCenter:")
    public native void setContentsCenter(@ByVal CGRect v);
    @Property(selector = "minificationFilter")
    public native CAFilter getMinificationFilter();
    @Property(selector = "setMinificationFilter:")
    public native void setMinificationFilter(CAFilter v);
    @Property(selector = "magnificationFilter")
    public native CAFilter getMagnificationFilter();
    @Property(selector = "setMagnificationFilter:")
    public native void setMagnificationFilter(CAFilter v);
    @Property(selector = "minificationFilterBias")
    public native float getMinificationFilterBias();
    @Property(selector = "setMinificationFilterBias:")
    public native void setMinificationFilterBias(float v);
    @Property(selector = "isOpaque")
    public native boolean isOpaque();
    @Property(selector = "setOpaque:")
    public native void setOpaque(boolean v);
    @Property(selector = "needsDisplayOnBoundsChange")
    public native boolean needsDisplayOnBoundsChange();
    @Property(selector = "setNeedsDisplayOnBoundsChange:")
    public native void setNeedsDisplayOnBoundsChange(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "drawsAsynchronously")
    public native boolean drawsAsynchronously();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setDrawsAsynchronously:")
    public native void setDrawsAsynchronously(boolean v);
    @Property(selector = "edgeAntialiasingMask")
    public native CAEdgeAntialiasingMask getEdgeAntialiasingMask();
    @Property(selector = "setEdgeAntialiasingMask:")
    public native void setEdgeAntialiasingMask(CAEdgeAntialiasingMask v);
    @Property(selector = "allowsEdgeAntialiasing")
    public native boolean allowsEdgeAntialiasing();
    @Property(selector = "setAllowsEdgeAntialiasing:")
    public native void setAllowsEdgeAntialiasing(boolean v);
    @Property(selector = "backgroundColor")
    public native CGColor getBackgroundColor();
    @Property(selector = "setBackgroundColor:")
    public native void setBackgroundColor(CGColor v);
    @Property(selector = "cornerRadius")
    public native @MachineSizedFloat double getCornerRadius();
    @Property(selector = "setCornerRadius:")
    public native void setCornerRadius(@MachineSizedFloat double v);
    @Property(selector = "borderWidth")
    public native @MachineSizedFloat double getBorderWidth();
    @Property(selector = "setBorderWidth:")
    public native void setBorderWidth(@MachineSizedFloat double v);
    @Property(selector = "borderColor")
    public native CGColor getBorderColor();
    @Property(selector = "setBorderColor:")
    public native void setBorderColor(CGColor v);
    @Property(selector = "opacity")
    public native float getOpacity();
    @Property(selector = "setOpacity:")
    public native void setOpacity(float v);
    @Property(selector = "allowsGroupOpacity")
    public native boolean allowsGroupOpacity();
    @Property(selector = "setAllowsGroupOpacity:")
    public native void setAllowsGroupOpacity(boolean v);
    @WeaklyLinked
    @Property(selector = "compositingFilter")
    public native CIFilter getCompositingFilter();
    @WeaklyLinked
    @Property(selector = "setCompositingFilter:")
    public native void setCompositingFilter(CIFilter v);
    @WeaklyLinked
    @Property(selector = "filters")
    public native NSArray<CIFilter> getFilters();
    @WeaklyLinked
    @Property(selector = "setFilters:")
    public native void setFilters(NSArray<CIFilter> v);
    @WeaklyLinked
    @Property(selector = "backgroundFilters")
    public native NSArray<CIFilter> getBackgroundFilters();
    @WeaklyLinked
    @Property(selector = "setBackgroundFilters:")
    public native void setBackgroundFilters(NSArray<CIFilter> v);
    @Property(selector = "shouldRasterize")
    public native boolean shouldRasterize();
    @Property(selector = "setShouldRasterize:")
    public native void setShouldRasterize(boolean v);
    @Property(selector = "rasterizationScale")
    public native @MachineSizedFloat double getRasterizationScale();
    @Property(selector = "setRasterizationScale:")
    public native void setRasterizationScale(@MachineSizedFloat double v);
    @Property(selector = "shadowColor")
    public native CGColor getShadowColor();
    @Property(selector = "setShadowColor:")
    public native void setShadowColor(CGColor v);
    @Property(selector = "shadowOpacity")
    public native float getShadowOpacity();
    @Property(selector = "setShadowOpacity:")
    public native void setShadowOpacity(float v);
    @Property(selector = "shadowOffset")
    public native @ByVal CGSize getShadowOffset();
    @Property(selector = "setShadowOffset:")
    public native void setShadowOffset(@ByVal CGSize v);
    @Property(selector = "shadowRadius")
    public native @MachineSizedFloat double getShadowRadius();
    @Property(selector = "setShadowRadius:")
    public native void setShadowRadius(@MachineSizedFloat double v);
    @Property(selector = "shadowPath")
    public native CGPath getShadowPath();
    @Property(selector = "setShadowPath:")
    public native void setShadowPath(CGPath v);
    @Property(selector = "actions")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, CAAction> getActions();
    @Property(selector = "setActions:")
    public native void setActions(@org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, CAAction> v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "delegate")
    public native CALayerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(CALayerDelegate v);
    @Property(selector = "style")
    public native NSDictionary<?, ?> getStyle();
    @Property(selector = "setStyle:")
    public native void setStyle(NSDictionary<?, ?> v);
    @Property(selector = "visibleRect")
    public native @ByVal CGRect getVisibleRect();
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
    @Method(selector = "initWithLayer:")
    protected native @Pointer long init(CALayer layer);
    @Method(selector = "presentationLayer")
    public native CALayer getPresentationLayer();
    @Method(selector = "modelLayer")
    public native CALayer getModelLayer();
    @Method(selector = "shouldArchiveValueForKey:")
    public native boolean shouldArchiveValue(String key);
    @Method(selector = "affineTransform")
    public native @ByVal CGAffineTransform getAffineTransform();
    @Method(selector = "setAffineTransform:")
    public native void setAffineTransform(@ByVal CGAffineTransform m);
    @Method(selector = "contentsAreFlipped")
    public native boolean areContentsFlipped();
    @Method(selector = "removeFromSuperlayer")
    public native void removeFromSuperlayer();
    @Method(selector = "addSublayer:")
    public native void addSublayer(CALayer layer);
    @Method(selector = "insertSublayer:atIndex:")
    public native void insertSublayerAt(CALayer layer, int idx);
    @Method(selector = "insertSublayer:below:")
    public native void insertSublayerBelow(CALayer layer, CALayer sibling);
    @Method(selector = "insertSublayer:above:")
    public native void insertSublayerAbove(CALayer layer, CALayer sibling);
    @Method(selector = "replaceSublayer:with:")
    public native void replaceSublayer(CALayer layer, CALayer layer2);
    @Method(selector = "convertPoint:fromLayer:")
    public native @ByVal CGPoint convertPointFromLayer(@ByVal CGPoint p, CALayer l);
    @Method(selector = "convertPoint:toLayer:")
    public native @ByVal CGPoint convertPointToLayer(@ByVal CGPoint p, CALayer l);
    @Method(selector = "convertRect:fromLayer:")
    public native @ByVal CGRect convertRectFromLayer(@ByVal CGRect r, CALayer l);
    @Method(selector = "convertRect:toLayer:")
    public native @ByVal CGRect convertRectToLayer(@ByVal CGRect r, CALayer l);
    @Method(selector = "convertTime:fromLayer:")
    public native double convertTimeFromLayer(double t, CALayer l);
    @Method(selector = "convertTime:toLayer:")
    public native double convertTimeToLayer(double t, CALayer l);
    @Method(selector = "hitTest:")
    public native CALayer hitTest(@ByVal CGPoint p);
    @Method(selector = "containsPoint:")
    public native boolean containsPoint(@ByVal CGPoint p);
    @Method(selector = "display")
    public native void display();
    @Method(selector = "setNeedsDisplay")
    public native void setNeedsDisplay();
    @Method(selector = "setNeedsDisplayInRect:")
    public native void setNeedsDisplay(@ByVal CGRect r);
    @Method(selector = "needsDisplay")
    public native boolean needsDisplay();
    @Method(selector = "displayIfNeeded")
    public native void displayIfNeeded();
    @Method(selector = "drawInContext:")
    public native void draw(CGContext ctx);
    @Method(selector = "renderInContext:")
    public native void render(CGContext ctx);
    @Method(selector = "preferredFrameSize")
    public native @ByVal CGSize getPreferredFrameSize();
    @Method(selector = "setNeedsLayout")
    public native void setNeedsLayout();
    @Method(selector = "needsLayout")
    public native boolean needsLayout();
    @Method(selector = "layoutIfNeeded")
    public native void layoutIfNeeded();
    @Method(selector = "layoutSublayers")
    public native void layoutSublayers();
    @Method(selector = "actionForKey:")
    public native CAAction getAction(String event);
    @Method(selector = "addAnimation:forKey:")
    public native void addAnimation(CAAnimation anim, String key);
    @Method(selector = "removeAllAnimations")
    public native void removeAllAnimations();
    @Method(selector = "removeAnimationForKey:")
    public native void removeAnimation(String key);
    @Method(selector = "animationKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAnimationKeys();
    @Method(selector = "animationForKey:")
    public native CAAnimation getAnimation(String key);
    @Method(selector = "defaultValueForKey:")
    public static native NSObject getDefaultValue(String key);
    @Method(selector = "needsDisplayForKey:")
    public static native boolean needsDisplay(String key);
    @Method(selector = "defaultActionForKey:")
    public static native CAAction getDefaultAction(String event);
    @Method(selector = "scrollPoint:")
    public native void scrollTo(@ByVal CGPoint p);
    @Method(selector = "scrollRectToVisible:")
    public native void scrollTo(@ByVal CGRect r);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    /*</methods>*/
}
