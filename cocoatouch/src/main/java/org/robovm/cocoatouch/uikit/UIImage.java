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
public class /*<name>*/ UIImage /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImage /*</name>*/.class);
    }

    /*<constructors>*/
    public UIImage() {}
    @Bind("initWithCGImage:scale:orientation:") public UIImage(@Type("CGImageRef") CGImage imageRef, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation) {}
    @Bind("initWithCGImage:") public UIImage(@Type("CGImageRef") CGImage CGImage) {}
    @Bind("initWithCIImage:scale:orientation:") public UIImage(@Type("CIImage *") CIImage ciImage, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation) {}
    @Bind("initWithCIImage:") public UIImage(@Type("CIImage *") CIImage ciImage) {}
    @Bind("initWithContentsOfFile:") public UIImage(@Type("NSString *") String path) {}
    @Bind("initWithData:scale:") public UIImage(@Type("NSData *") NSData data, @Type("CGFloat") float scale) {}
    @Bind("initWithData:") public UIImage(@Type("NSData *") NSData data) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("CGImage") public native @Type("CGImageRef") CGImage getCGImage();
    @Bind("CIImage") public native @Type("CIImage *") CIImage getCIImage();
    @Bind("alignmentRectInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getAlignmentRectInsets();
    @Bind("capInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getCapInsets();
    @Bind("duration") public native @Type("NSTimeInterval") double getDuration();
    @Bind("images") public native @Type("NSArray *") NSArray getImages();
    @Bind("imageOrientation") public native @Type("UIImageOrientation") UIImageOrientation getOrientation();
    @Bind("resizingMode") public native @Type("UIImageResizingMode") UIImageResizingMode getResizingMode();
    @Bind("scale") public native @Type("CGFloat") float getScale();
    @Bind("size") public native @Type("CGSize") CGSize getSize();
    /*</properties>*/
    /*<methods>*/
    @Bind("animatedResizableImageNamed:capInsets:resizingMode:duration:") public native static @Type("UIImage *") UIImage animatedResizableImageNamed(@Type("NSString *") String name, @Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("UIImageResizingMode") UIImageResizingMode resizingMode, @Type("NSTimeInterval") double duration);
    @Bind("animatedImageNamed:duration:") public native static @Type("UIImage *") UIImage createAnimated(@Type("NSString *") String name, @Type("NSTimeInterval") double duration);
    @Bind("animatedImageWithImages:duration:") public native static @Type("UIImage *") UIImage createAnimated(@Type("NSArray *") NSArray images, @Type("NSTimeInterval") double duration);
    @Bind("animatedResizableImageNamed:capInsets:duration:") public native static @Type("UIImage *") UIImage createAnimatedResizable(@Type("NSString *") String name, @Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("NSTimeInterval") double duration);
    @Bind("imageNamed:") public native static @Type("UIImage *") UIImage fromBundle(@Type("NSString *") String name);
    @Bind("imageWithData:") public native static @Type("UIImage *") UIImage fromData(@Type("NSData *") NSData data);
    @Bind("imageWithContentsOfFile:") public native static @Type("UIImage *") UIImage fromFile(@Type("NSString *") String path);
    @Bind("imageWithCIImage:") public native static @Type("UIImage *") UIImage fromImage(@Type("CIImage *") CIImage ciImage);
    @Bind("imageWithCGImage:scale:orientation:") public native static @Type("UIImage *") UIImage fromImage(@Type("CGImageRef") CGImage imageRef, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation);
    @Bind("imageWithCGImage:") public native static @Type("UIImage *") UIImage fromImage(@Type("CGImageRef") CGImage cgImage);
    @Bind("imageWithCIImage:scale:orientation:") public native static @Type("UIImage *") UIImage imageWithCIImage(@Type("CIImage *") CIImage ciImage, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation);
    @Bind("imageWithData:scale:") public native static @Type("UIImage *") UIImage imageWithData(@Type("NSData *") NSData data, @Type("CGFloat") float scale);
    @Bind("resizableImageWithCapInsets:") public native @Type("UIImage *") UIImage createResizable(@Type("UIEdgeInsets") UIEdgeInsets capInsets);
    @Bind("drawInRect:blendMode:alpha:") public native @Type("void") void draw(@Type("CGRect") CGRect rect, @Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    @Bind("drawAtPoint:blendMode:alpha:") public native @Type("void") void draw(@Type("CGPoint") CGPoint point, @Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    @Bind("drawInRect:") public native @Type("void") void draw(@Type("CGRect") CGRect rect);
    @Bind("drawAtPoint:") public native @Type("void") void draw(@Type("CGPoint") CGPoint point);
    @Bind("drawAsPatternInRect:") public native @Type("void") void drawAsPattern(@Type("CGRect") CGRect rect);
    @Bind("imageWithAlignmentRectInsets:") public native @Type("UIImage *") UIImage imageWithAlignmentRectInsets(@Type("UIEdgeInsets") UIEdgeInsets alignmentInsets);
    @Bind("resizableImageWithCapInsets:resizingMode:") public native @Type("UIImage *") UIImage resizableImageWithCapInsets(@Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("UIImageResizingMode") UIImageResizingMode resizingMode);
    /*</methods>*/

}
