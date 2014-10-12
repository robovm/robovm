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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAccessibilityIdentification/*</implements>*/ {

    /*<ptr>*/public static class UIImagePtr extends Ptr<UIImage, UIImagePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIImage() {}
    protected UIImage(SkipInit skipInit) { super(skipInit); }
    public UIImage(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(NSData data, @MachineSizedFloat double scale) { super((SkipInit) null); initObject(initWithData$scale$(data, scale)); }
    public UIImage(CGImage cgImage) { super((SkipInit) null); initObject(initWithCGImage$(cgImage)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public UIImage(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(initWithCGImage$scale$orientation$(cgImage, scale, orientation)); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UIImage(CIImage ciImage) { super((SkipInit) null); initObject(initWithCIImage$(ciImage)); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(initWithCIImage$scale$orientation$(ciImage, scale, orientation)); }
    /*</constructors>*/

    public UIImage(File file) {
        super((SkipInit) null);
        initObject(initWithContentsOfFile$(file.getAbsolutePath()));
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
    /*<methods>*/
    @Bridge(symbol="UIImagePNGRepresentation", optional=true)
    public native NSData toPNGData();
    @Bridge(symbol="UIImageJPEGRepresentation", optional=true)
    public native NSData toJPEGData(@MachineSizedFloat double compressionQuality);
    @Bridge(symbol="UIImageWriteToSavedPhotosAlbum", optional=true)
    public native void saveToPhotosAlbum(NSObject completionTarget, Selector completionSelector, VoidPtr contextInfo);
    
    @Method(selector = "initWithContentsOfFile:")
    protected native @Pointer long initWithContentsOfFile$(String path);
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithData:scale:")
    protected native @Pointer long initWithData$scale$(NSData data, @MachineSizedFloat double scale);
    @Method(selector = "initWithCGImage:")
    protected native @Pointer long initWithCGImage$(CGImage cgImage);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithCGImage:scale:orientation:")
    protected native @Pointer long initWithCGImage$scale$orientation$(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithCIImage:")
    protected native @Pointer long initWithCIImage$(CIImage ciImage);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithCIImage:scale:orientation:")
    protected native @Pointer long initWithCIImage$scale$orientation$(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
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
