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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIAccessibilityIdentification/*</implements>*/ {

    /*<ptr>*/public static class UIImagePtr extends Ptr<UIImage, UIImagePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIImage() {}
    protected UIImage(SkipInit skipInit) { super(skipInit); }
    public UIImage(String path) { super((SkipInit) null); initObject(initWithContentsOfFile$(path)); }
    public UIImage(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    public UIImage(NSData data, @MachineSizedFloat double scale) { super((SkipInit) null); initObject(initWithData$scale$(data, scale)); }
    public UIImage(CGImage cgImage) { super((SkipInit) null); initObject(initWithCGImage$(cgImage)); }
    public UIImage(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(initWithCGImage$scale$orientation$(cgImage, scale, orientation)); }
    public UIImage(CIImage ciImage) { super((SkipInit) null); initObject(initWithCIImage$(ciImage)); }
    public UIImage(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation) { super((SkipInit) null); initObject(initWithCIImage$scale$orientation$(ciImage, scale, orientation)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "CGImage")
    public native CGImage getCGImage();
    @Property(selector = "CIImage")
    public native CIImage getCIImage();
    @Property(selector = "imageOrientation")
    public native UIImageOrientation getOrientation();
    @Property(selector = "scale")
    public native @MachineSizedFloat double getScale();
    @Property(selector = "images")
    public native NSArray<?> getImages();
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "capInsets")
    public native @ByVal UIEdgeInsets getCapInsets();
    @Property(selector = "resizingMode")
    public native UIImageResizingMode getResizingMode();
    @Property(selector = "alignmentRectInsets")
    public native @ByVal UIEdgeInsets getAlignmentRectInsets();
    @Property(selector = "renderingMode")
    public native UIImageRenderingMode getRenderingMode();
    @Property(selector = "leftCapWidth")
    public native @MachineSizedSInt long getLeftCapWidth();
    @Property(selector = "topCapHeight")
    public native @MachineSizedSInt long getTopCapHeight();
    @Property(selector = "accessibilityIdentifier")
    public native String getAccessibilityIdentifier();
    @Property(selector = "setAccessibilityIdentifier:")
    public native void setAccessibilityIdentifier(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithContentsOfFile:")
    protected native @Pointer long initWithContentsOfFile$(String path);
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    @Method(selector = "initWithData:scale:")
    protected native @Pointer long initWithData$scale$(NSData data, @MachineSizedFloat double scale);
    @Method(selector = "initWithCGImage:")
    protected native @Pointer long initWithCGImage$(CGImage cgImage);
    @Method(selector = "initWithCGImage:scale:orientation:")
    protected native @Pointer long initWithCGImage$scale$orientation$(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    @Method(selector = "initWithCIImage:")
    protected native @Pointer long initWithCIImage$(CIImage ciImage);
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
    @Method(selector = "resizableImageWithCapInsets:")
    public native UIImage copyWithCapInsets(@ByVal UIEdgeInsets capInsets);
    @Method(selector = "resizableImageWithCapInsets:resizingMode:")
    public native UIImage copyWithCapInsets(@ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode);
    @Method(selector = "imageWithAlignmentRectInsets:")
    public native UIImage copyWithAlignmentRectInsets(@ByVal UIEdgeInsets alignmentInsets);
    @Method(selector = "imageWithRenderingMode:")
    public native UIImage imageWithRenderingMode$(UIImageRenderingMode renderingMode);
    @Method(selector = "imageNamed:")
    public static native UIImage fromBundle(String name);
    @Method(selector = "imageWithContentsOfFile:")
    public static native UIImage fromFile(String path);
    @Method(selector = "imageWithData:")
    public static native UIImage fromData(NSData data);
    @Method(selector = "imageWithData:scale:")
    public static native UIImage fromData(NSData data, @MachineSizedFloat double scale);
    @Method(selector = "imageWithCGImage:")
    public static native UIImage fromImage(CGImage cgImage);
    @Method(selector = "imageWithCGImage:scale:orientation:")
    public static native UIImage fromImage(CGImage cgImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    @Method(selector = "imageWithCIImage:")
    public static native UIImage fromImage(CIImage ciImage);
    @Method(selector = "imageWithCIImage:scale:orientation:")
    public static native UIImage fromImage(CIImage ciImage, @MachineSizedFloat double scale, UIImageOrientation orientation);
    @Method(selector = "animatedImageNamed:duration:")
    public static native UIImage createAnimated(String name, double duration);
    @Method(selector = "animatedResizableImageNamed:capInsets:duration:")
    public static native UIImage createAnimatedResizable(String name, @ByVal UIEdgeInsets capInsets, double duration);
    @Method(selector = "animatedResizableImageNamed:capInsets:resizingMode:duration:")
    public static native UIImage createAnimatedResizable(String name, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode, double duration);
    @Method(selector = "animatedImageWithImages:duration:")
    public static native UIImage createAnimated(NSArray<?> images, double duration);
    @Method(selector = "stretchableImageWithLeftCapWidth:topCapHeight:")
    public native UIImage stretchableImageWithLeftCapWidth$topCapHeight$(@MachineSizedSInt long leftCapWidth, @MachineSizedSInt long topCapHeight);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
