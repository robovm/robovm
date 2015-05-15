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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAccessibility, UIAccessibilityIdentification/*</implements>*/ {

    /*<ptr>*/public static class UIImagePtr extends Ptr<UIImage, UIImagePtr> {}/*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong id = new java.util.concurrent.atomic.AtomicLong();
    private static final Selector didFinishSaving = Selector.register("image:didFinishSavingWithError:contextInfo:");
    private static final LongMap<CallbackWrapper> callbacks = new LongMap<>();
    private static class CallbackWrapper extends NSObject {
        private final VoidBlock2<UIImage, NSError> callback;
        private CallbackWrapper(VoidBlock2<UIImage, NSError> callback, long contextInfo) {
            this.callback = callback;
            
            synchronized (callbacks) {
                callbacks.put(contextInfo, this);
            }
        }
        @TypeEncoding("v@:@:^v")
        @Method(selector = "image:didFinishSavingWithError:contextInfo:")
        private void didFinishSaving(UIImage image, NSError error, @Pointer long contextInfo) {
            callback.invoke(image, error);
            
            synchronized (callbacks) {
                callbacks.remove(contextInfo);
            }
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(UIImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIImage() {}
    protected UIImage(SkipInit skipInit) { super(skipInit); }
    public UIImage(NSData data) { super((SkipInit) null); initObject(init(data)); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(NSData data, @MachineSizedFloat double scale) { super((SkipInit) null); initObject(init(data, scale)); }
    public UIImage(CGImage cgImage) { super((SkipInit) null); initObject(init(cgImage)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public UIImage(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(init(cgImage, scale, orientation)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIImage(CIImage ciImage) { super((SkipInit) null); initObject(init(ciImage)); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(init(ciImage, scale, orientation)); }
    /*</constructors>*/

    public UIImage(File file) {
        super((SkipInit) null);
        initObject(initWithFile(file.getAbsolutePath()));
    }
    
    /*<properties>*/
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "CGImage")
    public native CGImage getCGImage();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "CIImage")
    public native CIImage getCIImage();
    @Property(selector = "imageOrientation")
    public native UIImageOrientation getOrientation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "scale")
    public native @MachineSizedFloat double getScale();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "images")
    public native NSArray<UIImage> getImages();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "duration")
    public native double getDuration();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "capInsets")
    public native @ByVal UIEdgeInsets getCapInsets();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "resizingMode")
    public native UIImageResizingMode getResizingMode();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "alignmentRectInsets")
    public native @ByVal UIEdgeInsets getAlignmentRectInsets();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "renderingMode")
    public native UIImageRenderingMode getRenderingMode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "traitCollection")
    public native UITraitCollection getTraitCollection();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "imageAsset")
    public native UIImageAsset getImageAsset();
    @Property(selector = "leftCapWidth")
    public native @MachineSizedSInt long getLeftCapWidth();
    @Property(selector = "topCapHeight")
    public native @MachineSizedSInt long getTopCapHeight();
    @Property(selector = "isAccessibilityElement")
    public native boolean isAccessibilityElement();
    @Property(selector = "setIsAccessibilityElement:")
    public native void setAccessibilityElement(boolean v);
    @Property(selector = "accessibilityLabel")
    public native String getAccessibilityLabel();
    @Property(selector = "setAccessibilityLabel:")
    public native void setAccessibilityLabel(String v);
    @Property(selector = "accessibilityHint")
    public native String getAccessibilityHint();
    @Property(selector = "setAccessibilityHint:")
    public native void setAccessibilityHint(String v);
    @Property(selector = "accessibilityValue")
    public native String getAccessibilityValue();
    @Property(selector = "setAccessibilityValue:")
    public native void setAccessibilityValue(String v);
    @Property(selector = "accessibilityTraits")
    public native UIAccessibilityTraits getAccessibilityTraits();
    @Property(selector = "setAccessibilityTraits:")
    public native void setAccessibilityTraits(UIAccessibilityTraits v);
    @Property(selector = "accessibilityFrame")
    public native @ByVal CGRect getAccessibilityFrame();
    @Property(selector = "setAccessibilityFrame:")
    public native void setAccessibilityFrame(@ByVal CGRect v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "accessibilityPath")
    public native UIBezierPath getAccessibilityPath();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAccessibilityPath:")
    public native void setAccessibilityPath(UIBezierPath v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityActivationPoint")
    public native @ByVal CGPoint getAccessibilityActivationPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityActivationPoint:")
    public native void setAccessibilityActivationPoint(@ByVal CGPoint v);
    @Property(selector = "accessibilityLanguage")
    public native String getAccessibilityLanguage();
    @Property(selector = "setAccessibilityLanguage:")
    public native void setAccessibilityLanguage(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityElementsHidden")
    public native boolean areAccessibilityElementsHidden();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityElementsHidden:")
    public native void setAccessibilityElementsHidden(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityViewIsModal")
    public native boolean isAccessibilityViewModal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityViewIsModal:")
    public native void setAccessibilityViewModal(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "shouldGroupAccessibilityChildren")
    public native boolean shouldGroupAccessibilityChildren();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setShouldGroupAccessibilityChildren:")
    public native void setShouldGroupAccessibilityChildren(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "accessibilityNavigationStyle")
    public native UIAccessibilityNavigationStyle getAccessibilityNavigationStyle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setAccessibilityNavigationStyle:")
    public native void setAccessibilityNavigationStyle(UIAccessibilityNavigationStyle v);
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
    public static UIImage create(File file) {
        return createFromFile(file.getAbsolutePath());
    }
    
    public void saveToPhotosAlbum(VoidBlock2<UIImage, NSError> callback) {
        if (callback != null) {
            long context = id.getAndIncrement();
            CallbackWrapper l = new CallbackWrapper(callback, context);
            saveToPhotosAlbum(l, didFinishSaving, context);
        } else {
            saveToPhotosAlbum(null, null, 0);
        }
    }
    /*<methods>*/
    @Bridge(symbol="UIImagePNGRepresentation", optional=true)
    public native NSData toPNGData();
    @Bridge(symbol="UIImageJPEGRepresentation", optional=true)
    public native NSData toJPEGData(@MachineSizedFloat double compressionQuality);
    @Bridge(symbol="UIImageWriteToSavedPhotosAlbum", optional=true)
    public native void saveToPhotosAlbum(NSObject completionTarget, Selector completionSelector, @Pointer long contextInfo);
    
    @Method(selector = "initWithContentsOfFile:")
    protected native @Pointer long initWithFile(String path);
    @Method(selector = "initWithData:")
    protected native @Pointer long init(NSData data);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithData:scale:")
    protected native @Pointer long init(NSData data, @MachineSizedFloat double scale);
    @Method(selector = "initWithCGImage:")
    protected native @Pointer long init(CGImage cgImage);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithCGImage:scale:orientation:")
    protected native @Pointer long init(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCIImage:")
    protected native @Pointer long init(CIImage ciImage);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithCIImage:scale:orientation:")
    protected native @Pointer long init(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    @Method(selector = "drawAtPoint:")
    public native void draw(@ByVal CGPoint point);
    @Method(selector = "drawAtPoint:blendMode:alpha:")
    public native void draw(@ByVal CGPoint point, CGBlendMode blendMode, @MachineSizedFloat double alpha);
    @Method(selector = "drawInRect:")
    public native void draw(@ByVal CGRect rect);
    @Method(selector = "drawInRect:blendMode:alpha:")
    public native void draw(@ByVal CGRect rect, CGBlendMode blendMode, @MachineSizedFloat double alpha);
    @Method(selector = "drawAsPatternInRect:")
    public native void drawAsPattern(@ByVal CGRect rect);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "resizableImageWithCapInsets:")
    public native UIImage createResizable(@ByVal UIEdgeInsets capInsets);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "resizableImageWithCapInsets:resizingMode:")
    public native UIImage createResizable(@ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "imageWithAlignmentRectInsets:")
    public native UIImage create(@ByVal UIEdgeInsets alignmentInsets);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "imageWithRenderingMode:")
    public native UIImage create(UIImageRenderingMode renderingMode);
    @Method(selector = "imageNamed:")
    public static native UIImage create(String name);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "imageNamed:inBundle:compatibleWithTraitCollection:")
    public static native UIImage create(String name, NSBundle bundle, UITraitCollection traitCollection);
    @Method(selector = "imageWithContentsOfFile:")
    protected static native UIImage createFromFile(String path);
    @Method(selector = "imageWithData:")
    public static native UIImage create(NSData data);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "imageWithData:scale:")
    public static native UIImage create(NSData data, @MachineSizedFloat double scale);
    @Method(selector = "imageWithCGImage:")
    public static native UIImage create(CGImage cgImage);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "imageWithCGImage:scale:orientation:")
    public static native UIImage create(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "imageWithCIImage:")
    public static native UIImage create(CIImage ciImage);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "imageWithCIImage:scale:orientation:")
    public static native UIImage create(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "animatedImageNamed:duration:")
    public static native UIImage createAnimated(String name, double duration);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "animatedResizableImageNamed:capInsets:duration:")
    public static native UIImage createAnimatedResizable(String name, @ByVal UIEdgeInsets capInsets, double duration);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "animatedResizableImageNamed:capInsets:resizingMode:duration:")
    public static native UIImage createAnimatedResizable(String name, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode, double duration);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "animatedImageWithImages:duration:")
    public static native UIImage createAnimated(NSArray<UIImage> images, double duration);
    @Method(selector = "stretchableImageWithLeftCapWidth:topCapHeight:")
    public native UIImage createStretchable(@MachineSizedSInt long leftCapWidth, @MachineSizedSInt long topCapHeight);
    /*</methods>*/
}
