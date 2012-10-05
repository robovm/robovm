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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html">UIImage Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIImage /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImage /*</name>*/.class);
    }

    /*<constructors>*/
    public UIImage() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCGImage:">- (id)initWithCGImage:(CGImageRef)CGImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithCGImage:") public UIImage(@Type("CGImageRef") CGImage CGImage) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCGImage:scale:orientation:">- (id)initWithCGImage:(CGImageRef)imageRef scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("initWithCGImage:scale:orientation:") public UIImage(@Type("CGImageRef") CGImage imageRef, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCIImage:">- (id)initWithCIImage:(CIImage *)ciImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithCIImage:") public UIImage(@Type("CIImage *") CIImage ciImage) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCIImage:scale:orientation:">- (id)initWithCIImage:(CIImage *)ciImage scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initWithCIImage:scale:orientation:") public UIImage(@Type("CIImage *") CIImage ciImage, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithContentsOfFile:">- (id)initWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithContentsOfFile:") public UIImage(@Type("NSString *") String path) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithData:scale:">- (id)initWithData:(NSData *)data scale:(CGFloat)scale</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initWithData:scale:") public UIImage(@Type("NSData *") NSData data, @Type("CGFloat") float scale) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithData:">- (id)initWithData:(NSData *)data</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithData:") public UIImage(@Type("NSData *") NSData data) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/CGImage">@property(nonatomic, readonly) CGImageRef CGImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("CGImage") public native @Type("CGImageRef") CGImage getCGImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/CIImage">@property(nonatomic, readonly) CIImage *CIImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("CIImage") public native @Type("CIImage *") CIImage getCIImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/alignmentRectInsets">@property(nonatomic, readonly) UIEdgeInsets alignmentRectInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alignmentRectInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getAlignmentRectInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/capInsets">@property(nonatomic, readonly) UIEdgeInsets capInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("capInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getCapInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/duration">@property(nonatomic, readonly) NSTimeInterval duration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("duration") public native @Type("NSTimeInterval") double getDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/images">@property(nonatomic, readonly) NSArray *images</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("images") public native @Type("NSArray *") NSArray getImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/imageOrientation">@property(nonatomic, readonly) UIImageOrientation imageOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageOrientation") public native @Type("UIImageOrientation") UIImageOrientation getOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/resizingMode">@property(nonatomic,readonly) UIImageResizingMode resizingMode</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("resizingMode") public native @Type("UIImageResizingMode") UIImageResizingMode getResizingMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/scale">@property(nonatomic, readonly) CGFloat scale</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("scale") public native @Type("CGFloat") float getScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/size">@property(nonatomic, readonly) CGSize size</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("size") public native @Type("CGSize") CGSize getSize();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedImageNamed:duration:">+ (UIImage *)animatedImageNamed:(NSString *)name duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("animatedImageNamed:duration:") public native static @Type("UIImage *") UIImage createAnimated(@Type("NSString *") String name, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedImageWithImages:duration:">+ (UIImage *)animatedImageWithImages:(NSArray *)images duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("animatedImageWithImages:duration:") public native static @Type("UIImage *") UIImage createAnimated(@Type("NSArray *") NSArray images, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedResizableImageNamed:capInsets:duration:">+ (UIImage *)animatedResizableImageNamed:(NSString *)name capInsets:(UIEdgeInsets)capInsets duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("animatedResizableImageNamed:capInsets:duration:") public native static @Type("UIImage *") UIImage createAnimatedResizable(@Type("NSString *") String name, @Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedResizableImageNamed:capInsets:resizingMode:duration:">+ (UIImage *)animatedResizableImageNamed:(NSString *)name capInsets:(UIEdgeInsets)capInsets resizingMode:(UIImageResizingMode)resizingMode duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("animatedResizableImageNamed:capInsets:resizingMode:duration:") public native static @Type("UIImage *") UIImage createAnimatedResizable(@Type("NSString *") String name, @Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("UIImageResizingMode") UIImageResizingMode resizingMode, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageNamed:">+ (UIImage *)imageNamed:(NSString *)name</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageNamed:") public native static @Type("UIImage *") UIImage fromBundle(@Type("NSString *") String name);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithData:scale:">+ (UIImage *)imageWithData:(NSData *)data scale:(CGFloat)scale</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("imageWithData:scale:") public native static @Type("UIImage *") UIImage fromData(@Type("NSData *") NSData data, @Type("CGFloat") float scale);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithData:">+ (UIImage *)imageWithData:(NSData *)data</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageWithData:") public native static @Type("UIImage *") UIImage fromData(@Type("NSData *") NSData data);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithContentsOfFile:">+ (UIImage *)imageWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageWithContentsOfFile:") public native static @Type("UIImage *") UIImage fromFile(@Type("NSString *") String path);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCGImage:scale:orientation:">+ (UIImage *)imageWithCGImage:(CGImageRef)imageRef scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("imageWithCGImage:scale:orientation:") public native static @Type("UIImage *") UIImage fromImage(@Type("CGImageRef") CGImage imageRef, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCIImage:scale:orientation:">+ (UIImage *)imageWithCIImage:(CIImage *)ciImage scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("imageWithCIImage:scale:orientation:") public native static @Type("UIImage *") UIImage fromImage(@Type("CIImage *") CIImage ciImage, @Type("CGFloat") float scale, @Type("UIImageOrientation") UIImageOrientation orientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCGImage:">+ (UIImage *)imageWithCGImage:(CGImageRef)cgImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageWithCGImage:") public native static @Type("UIImage *") UIImage fromImage(@Type("CGImageRef") CGImage cgImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCIImage:">+ (UIImage *)imageWithCIImage:(CIImage *)ciImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("imageWithCIImage:") public native static @Type("UIImage *") UIImage fromImage(@Type("CIImage *") CIImage ciImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/imageWithAlignmentRectInsets:">- (UIImage *)imageWithAlignmentRectInsets:(UIEdgeInsets)alignmentInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("imageWithAlignmentRectInsets:") public native @Type("UIImage *") UIImage copyWithAlignmentRectInsets(@Type("UIEdgeInsets") UIEdgeInsets alignmentInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/resizableImageWithCapInsets:">- (UIImage *)resizableImageWithCapInsets:(UIEdgeInsets)capInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("resizableImageWithCapInsets:") public native @Type("UIImage *") UIImage copyWithCapInsets(@Type("UIEdgeInsets") UIEdgeInsets capInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/resizableImageWithCapInsets:resizingMode:">- (UIImage *)resizableImageWithCapInsets:(UIEdgeInsets)capInsets resizingMode:(UIImageResizingMode)resizingMode</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("resizableImageWithCapInsets:resizingMode:") public native @Type("UIImage *") UIImage copyWithCapInsets(@Type("UIEdgeInsets") UIEdgeInsets capInsets, @Type("UIImageResizingMode") UIImageResizingMode resizingMode);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawInRect:">- (void)drawInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawInRect:") public native @Type("void") void draw(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawInRect:blendMode:alpha:">- (void)drawInRect:(CGRect)rect blendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawInRect:blendMode:alpha:") public native @Type("void") void draw(@Type("CGRect") CGRect rect, @Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAtPoint:blendMode:alpha:">- (void)drawAtPoint:(CGPoint)point blendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawAtPoint:blendMode:alpha:") public native @Type("void") void draw(@Type("CGPoint") CGPoint point, @Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAtPoint:">- (void)drawAtPoint:(CGPoint)point</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawAtPoint:") public native @Type("void") void draw(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAsPatternInRect:">- (void)drawAsPatternInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawAsPatternInRect:") public native @Type("void") void drawAsPattern(@Type("CGRect") CGRect rect);
    /*</methods>*/

}
