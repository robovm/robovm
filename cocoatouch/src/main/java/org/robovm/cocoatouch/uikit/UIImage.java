/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html">UIImage Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIImage /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImage /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIImage /*</name>*/.class);

    /*<constructors>*/
    protected UIImage(SkipInit skipInit) { super(skipInit); }
    public UIImage() {}
    
    private static final Selector initWithCGImage$ = Selector.register("initWithCGImage:");
    @Bridge private native static @Pointer long objc_initWithCGImage(UIImage __self__, Selector __cmd__, CGImage CGImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCGImage:">- (id)initWithCGImage:(CGImageRef)CGImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage(CGImage CGImage) {
        super((SkipInit) null);
        initObject(objc_initWithCGImage(this, initWithCGImage$, CGImage));
    }
    
    private static final Selector initWithCGImage$scale$orientation$ = Selector.register("initWithCGImage:scale:orientation:");
    @Bridge private native static @Pointer long objc_initWithCGImage(UIImage __self__, Selector __cmd__, CGImage imageRef, float scale, UIImageOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCGImage:scale:orientation:">- (id)initWithCGImage:(CGImageRef)imageRef scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 4.0 and later.
     */
    public UIImage(CGImage imageRef, float scale, UIImageOrientation orientation) {
        super((SkipInit) null);
        initObject(objc_initWithCGImage(this, initWithCGImage$scale$orientation$, imageRef, scale, orientation));
    }
    
    private static final Selector initWithCIImage$ = Selector.register("initWithCIImage:");
    @Bridge private native static @Pointer long objc_initWithCIImage(UIImage __self__, Selector __cmd__, CIImage ciImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCIImage:">- (id)initWithCIImage:(CIImage *)ciImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage(CIImage ciImage) {
        super((SkipInit) null);
        initObject(objc_initWithCIImage(this, initWithCIImage$, ciImage));
    }
    
    private static final Selector initWithCIImage$scale$orientation$ = Selector.register("initWithCIImage:scale:orientation:");
    @Bridge private native static @Pointer long objc_initWithCIImage(UIImage __self__, Selector __cmd__, CIImage ciImage, float scale, UIImageOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithCIImage:scale:orientation:">- (id)initWithCIImage:(CIImage *)ciImage scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(CIImage ciImage, float scale, UIImageOrientation orientation) {
        super((SkipInit) null);
        initObject(objc_initWithCIImage(this, initWithCIImage$scale$orientation$, ciImage, scale, orientation));
    }
    
    private static final Selector initWithContentsOfFile$ = Selector.register("initWithContentsOfFile:");
    @Bridge private native static @Pointer long objc_initWithContentsOfFile(UIImage __self__, Selector __cmd__, String path);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithContentsOfFile:">- (id)initWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage(String path) {
        super((SkipInit) null);
        initObject(objc_initWithContentsOfFile(this, initWithContentsOfFile$, path));
    }
    
    private static final Selector initWithData$scale$ = Selector.register("initWithData:scale:");
    @Bridge private native static @Pointer long objc_initWithData(UIImage __self__, Selector __cmd__, NSData data, float scale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithData:scale:">- (id)initWithData:(NSData *)data scale:(CGFloat)scale</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage(NSData data, float scale) {
        super((SkipInit) null);
        initObject(objc_initWithData(this, initWithData$scale$, data, scale));
    }
    
    private static final Selector initWithData$ = Selector.register("initWithData:");
    @Bridge private native static @Pointer long objc_initWithData(UIImage __self__, Selector __cmd__, NSData data);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/initWithData:">- (id)initWithData:(NSData *)data</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage(NSData data) {
        super((SkipInit) null);
        initObject(objc_initWithData(this, initWithData$, data));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector CGImage = Selector.register("CGImage");
    @Bridge private native static CGImage objc_getCGImage(UIImage __self__, Selector __cmd__);
    @Bridge private native static CGImage objc_getCGImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/CGImage">@property(nonatomic, readonly) CGImageRef CGImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGImage getCGImage() {
        if (customClass) { return objc_getCGImageSuper(getSuper(), CGImage); } else { return objc_getCGImage(this, CGImage); }
    }
    
    private static final Selector CIImage = Selector.register("CIImage");
    @Bridge private native static CIImage objc_getCIImage(UIImage __self__, Selector __cmd__);
    @Bridge private native static CIImage objc_getCIImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/CIImage">@property(nonatomic, readonly) CIImage *CIImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public CIImage getCIImage() {
        if (customClass) { return objc_getCIImageSuper(getSuper(), CIImage); } else { return objc_getCIImage(this, CIImage); }
    }
    
    private static final Selector alignmentRectInsets = Selector.register("alignmentRectInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getAlignmentRectInsets(UIImage __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getAlignmentRectInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/alignmentRectInsets">@property(nonatomic, readonly) UIEdgeInsets alignmentRectInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIEdgeInsets getAlignmentRectInsets() {
        if (customClass) { return objc_getAlignmentRectInsetsSuper(getSuper(), alignmentRectInsets); } else { return objc_getAlignmentRectInsets(this, alignmentRectInsets); }
    }
    
    private static final Selector capInsets = Selector.register("capInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getCapInsets(UIImage __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getCapInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/capInsets">@property(nonatomic, readonly) UIEdgeInsets capInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIEdgeInsets getCapInsets() {
        if (customClass) { return objc_getCapInsetsSuper(getSuper(), capInsets); } else { return objc_getCapInsets(this, capInsets); }
    }
    
    private static final Selector duration = Selector.register("duration");
    @Bridge private native static double objc_getDuration(UIImage __self__, Selector __cmd__);
    @Bridge private native static double objc_getDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/duration">@property(nonatomic, readonly) NSTimeInterval duration</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getDuration() {
        if (customClass) { return objc_getDurationSuper(getSuper(), duration); } else { return objc_getDuration(this, duration); }
    }
    
    private static final Selector images = Selector.register("images");
    @Bridge private native static NSArray objc_getImages(UIImage __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getImagesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/images">@property(nonatomic, readonly) NSArray *images</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getImages() {
        if (customClass) { return objc_getImagesSuper(getSuper(), images); } else { return objc_getImages(this, images); }
    }
    
    private static final Selector imageOrientation = Selector.register("imageOrientation");
    @Bridge private native static UIImageOrientation objc_getOrientation(UIImage __self__, Selector __cmd__);
    @Bridge private native static UIImageOrientation objc_getOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/imageOrientation">@property(nonatomic, readonly) UIImageOrientation imageOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImageOrientation getOrientation() {
        if (customClass) { return objc_getOrientationSuper(getSuper(), imageOrientation); } else { return objc_getOrientation(this, imageOrientation); }
    }
    
    private static final Selector resizingMode = Selector.register("resizingMode");
    @Bridge private native static UIImageResizingMode objc_getResizingMode(UIImage __self__, Selector __cmd__);
    @Bridge private native static UIImageResizingMode objc_getResizingModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/resizingMode">@property(nonatomic,readonly) UIImageResizingMode resizingMode</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImageResizingMode getResizingMode() {
        if (customClass) { return objc_getResizingModeSuper(getSuper(), resizingMode); } else { return objc_getResizingMode(this, resizingMode); }
    }
    
    private static final Selector scale = Selector.register("scale");
    @Bridge private native static float objc_getScale(UIImage __self__, Selector __cmd__);
    @Bridge private native static float objc_getScaleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/scale">@property(nonatomic, readonly) CGFloat scale</a>
     * @since Available in iOS 4.0 and later.
     */
    public float getScale() {
        if (customClass) { return objc_getScaleSuper(getSuper(), scale); } else { return objc_getScale(this, scale); }
    }
    
    private static final Selector size = Selector.register("size");
    @Bridge private native static @ByVal CGSize objc_getSize(UIImage __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instp/UIImage/size">@property(nonatomic, readonly) CGSize size</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSize() {
        if (customClass) { return objc_getSizeSuper(getSuper(), size); } else { return objc_getSize(this, size); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector animatedImageNamed$duration$ = Selector.register("animatedImageNamed:duration:");
    @Bridge private native static UIImage objc_createAnimated(ObjCClass __self__, Selector __cmd__, String name, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedImageNamed:duration:">+ (UIImage *)animatedImageNamed:(NSString *)name duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIImage createAnimated(String name, double duration) {
        return objc_createAnimated(objCClass, animatedImageNamed$duration$, name, duration);
    }
    
    private static final Selector animatedImageWithImages$duration$ = Selector.register("animatedImageWithImages:duration:");
    @Bridge private native static UIImage objc_createAnimated(ObjCClass __self__, Selector __cmd__, NSArray images, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedImageWithImages:duration:">+ (UIImage *)animatedImageWithImages:(NSArray *)images duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIImage createAnimated(NSArray images, double duration) {
        return objc_createAnimated(objCClass, animatedImageWithImages$duration$, images, duration);
    }
    
    private static final Selector animatedResizableImageNamed$capInsets$duration$ = Selector.register("animatedResizableImageNamed:capInsets:duration:");
    @Bridge private native static UIImage objc_createAnimatedResizable(ObjCClass __self__, Selector __cmd__, String name, @ByVal UIEdgeInsets capInsets, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedResizableImageNamed:capInsets:duration:">+ (UIImage *)animatedResizableImageNamed:(NSString *)name capInsets:(UIEdgeInsets)capInsets duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIImage createAnimatedResizable(String name, UIEdgeInsets capInsets, double duration) {
        return objc_createAnimatedResizable(objCClass, animatedResizableImageNamed$capInsets$duration$, name, capInsets, duration);
    }
    
    private static final Selector animatedResizableImageNamed$capInsets$resizingMode$duration$ = Selector.register("animatedResizableImageNamed:capInsets:resizingMode:duration:");
    @Bridge private native static UIImage objc_createAnimatedResizable(ObjCClass __self__, Selector __cmd__, String name, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/animatedResizableImageNamed:capInsets:resizingMode:duration:">+ (UIImage *)animatedResizableImageNamed:(NSString *)name capInsets:(UIEdgeInsets)capInsets resizingMode:(UIImageResizingMode)resizingMode duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UIImage createAnimatedResizable(String name, UIEdgeInsets capInsets, UIImageResizingMode resizingMode, double duration) {
        return objc_createAnimatedResizable(objCClass, animatedResizableImageNamed$capInsets$resizingMode$duration$, name, capInsets, resizingMode, duration);
    }
    
    private static final Selector imageNamed$ = Selector.register("imageNamed:");
    @Bridge private native static UIImage objc_fromBundle(ObjCClass __self__, Selector __cmd__, String name);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageNamed:">+ (UIImage *)imageNamed:(NSString *)name</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIImage fromBundle(String name) {
        return objc_fromBundle(objCClass, imageNamed$, name);
    }
    
    private static final Selector imageWithData$scale$ = Selector.register("imageWithData:scale:");
    @Bridge private native static UIImage objc_fromData(ObjCClass __self__, Selector __cmd__, NSData data, float scale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithData:scale:">+ (UIImage *)imageWithData:(NSData *)data scale:(CGFloat)scale</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UIImage fromData(NSData data, float scale) {
        return objc_fromData(objCClass, imageWithData$scale$, data, scale);
    }
    
    private static final Selector imageWithData$ = Selector.register("imageWithData:");
    @Bridge private native static UIImage objc_fromData(ObjCClass __self__, Selector __cmd__, NSData data);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithData:">+ (UIImage *)imageWithData:(NSData *)data</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIImage fromData(NSData data) {
        return objc_fromData(objCClass, imageWithData$, data);
    }
    
    private static final Selector imageWithContentsOfFile$ = Selector.register("imageWithContentsOfFile:");
    @Bridge private native static UIImage objc_fromFile(ObjCClass __self__, Selector __cmd__, String path);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithContentsOfFile:">+ (UIImage *)imageWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIImage fromFile(String path) {
        return objc_fromFile(objCClass, imageWithContentsOfFile$, path);
    }
    
    private static final Selector imageWithCGImage$scale$orientation$ = Selector.register("imageWithCGImage:scale:orientation:");
    @Bridge private native static UIImage objc_fromImage(ObjCClass __self__, Selector __cmd__, CGImage imageRef, float scale, UIImageOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCGImage:scale:orientation:">+ (UIImage *)imageWithCGImage:(CGImageRef)imageRef scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 4.0 and later.
     */
    public static UIImage fromImage(CGImage imageRef, float scale, UIImageOrientation orientation) {
        return objc_fromImage(objCClass, imageWithCGImage$scale$orientation$, imageRef, scale, orientation);
    }
    
    private static final Selector imageWithCIImage$scale$orientation$ = Selector.register("imageWithCIImage:scale:orientation:");
    @Bridge private native static UIImage objc_fromImage(ObjCClass __self__, Selector __cmd__, CIImage ciImage, float scale, UIImageOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCIImage:scale:orientation:">+ (UIImage *)imageWithCIImage:(CIImage *)ciImage scale:(CGFloat)scale orientation:(UIImageOrientation)orientation</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UIImage fromImage(CIImage ciImage, float scale, UIImageOrientation orientation) {
        return objc_fromImage(objCClass, imageWithCIImage$scale$orientation$, ciImage, scale, orientation);
    }
    
    private static final Selector imageWithCGImage$ = Selector.register("imageWithCGImage:");
    @Bridge private native static UIImage objc_fromImage(ObjCClass __self__, Selector __cmd__, CGImage cgImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCGImage:">+ (UIImage *)imageWithCGImage:(CGImageRef)cgImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIImage fromImage(CGImage cgImage) {
        return objc_fromImage(objCClass, imageWithCGImage$, cgImage);
    }
    
    private static final Selector imageWithCIImage$ = Selector.register("imageWithCIImage:");
    @Bridge private native static UIImage objc_fromImage(ObjCClass __self__, Selector __cmd__, CIImage ciImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/clm/UIImage/imageWithCIImage:">+ (UIImage *)imageWithCIImage:(CIImage *)ciImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public static UIImage fromImage(CIImage ciImage) {
        return objc_fromImage(objCClass, imageWithCIImage$, ciImage);
    }
    
    private static final Selector imageWithAlignmentRectInsets$ = Selector.register("imageWithAlignmentRectInsets:");
    @Bridge private native static UIImage objc_copyWithAlignmentRectInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets alignmentInsets);
    @Bridge private native static UIImage objc_copyWithAlignmentRectInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets alignmentInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/imageWithAlignmentRectInsets:">- (UIImage *)imageWithAlignmentRectInsets:(UIEdgeInsets)alignmentInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage copyWithAlignmentRectInsets(UIEdgeInsets alignmentInsets) {
        if (customClass) { return objc_copyWithAlignmentRectInsetsSuper(getSuper(), imageWithAlignmentRectInsets$, alignmentInsets); } else { return objc_copyWithAlignmentRectInsets(this, imageWithAlignmentRectInsets$, alignmentInsets); }
    }
    
    private static final Selector resizableImageWithCapInsets$ = Selector.register("resizableImageWithCapInsets:");
    @Bridge private native static UIImage objc_copyWithCapInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets capInsets);
    @Bridge private native static UIImage objc_copyWithCapInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets capInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/resizableImageWithCapInsets:">- (UIImage *)resizableImageWithCapInsets:(UIEdgeInsets)capInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage copyWithCapInsets(UIEdgeInsets capInsets) {
        if (customClass) { return objc_copyWithCapInsetsSuper(getSuper(), resizableImageWithCapInsets$, capInsets); } else { return objc_copyWithCapInsets(this, resizableImageWithCapInsets$, capInsets); }
    }
    
    private static final Selector resizableImageWithCapInsets$resizingMode$ = Selector.register("resizableImageWithCapInsets:resizingMode:");
    @Bridge private native static UIImage objc_copyWithCapInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode);
    @Bridge private native static UIImage objc_copyWithCapInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/resizableImageWithCapInsets:resizingMode:">- (UIImage *)resizableImageWithCapInsets:(UIEdgeInsets)capInsets resizingMode:(UIImageResizingMode)resizingMode</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage copyWithCapInsets(UIEdgeInsets capInsets, UIImageResizingMode resizingMode) {
        if (customClass) { return objc_copyWithCapInsetsSuper(getSuper(), resizableImageWithCapInsets$resizingMode$, capInsets, resizingMode); } else { return objc_copyWithCapInsets(this, resizableImageWithCapInsets$resizingMode$, capInsets, resizingMode); }
    }
    
    private static final Selector drawInRect$ = Selector.register("drawInRect:");
    @Bridge private native static void objc_draw(UIImage __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static void objc_drawSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawInRect:">- (void)drawInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGRect rect) {
        if (customClass) { objc_drawSuper(getSuper(), drawInRect$, rect); } else { objc_draw(this, drawInRect$, rect); }
    }
    
    private static final Selector drawInRect$blendMode$alpha$ = Selector.register("drawInRect:blendMode:alpha:");
    @Bridge private native static void objc_draw(UIImage __self__, Selector __cmd__, @ByVal CGRect rect, CGBlendMode blendMode, float alpha);
    @Bridge private native static void objc_drawSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawInRect:blendMode:alpha:">- (void)drawInRect:(CGRect)rect blendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGRect rect, CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_drawSuper(getSuper(), drawInRect$blendMode$alpha$, rect, blendMode, alpha); } else { objc_draw(this, drawInRect$blendMode$alpha$, rect, blendMode, alpha); }
    }
    
    private static final Selector drawAtPoint$blendMode$alpha$ = Selector.register("drawAtPoint:blendMode:alpha:");
    @Bridge private native static void objc_draw(UIImage __self__, Selector __cmd__, @ByVal CGPoint point, CGBlendMode blendMode, float alpha);
    @Bridge private native static void objc_drawSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, CGBlendMode blendMode, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAtPoint:blendMode:alpha:">- (void)drawAtPoint:(CGPoint)point blendMode:(CGBlendMode)blendMode alpha:(CGFloat)alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGPoint point, CGBlendMode blendMode, float alpha) {
        if (customClass) { objc_drawSuper(getSuper(), drawAtPoint$blendMode$alpha$, point, blendMode, alpha); } else { objc_draw(this, drawAtPoint$blendMode$alpha$, point, blendMode, alpha); }
    }
    
    private static final Selector drawAtPoint$ = Selector.register("drawAtPoint:");
    @Bridge private native static void objc_draw(UIImage __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static void objc_drawSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAtPoint:">- (void)drawAtPoint:(CGPoint)point</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGPoint point) {
        if (customClass) { objc_drawSuper(getSuper(), drawAtPoint$, point); } else { objc_draw(this, drawAtPoint$, point); }
    }
    
    private static final Selector drawAsPatternInRect$ = Selector.register("drawAsPatternInRect:");
    @Bridge private native static void objc_drawAsPattern(UIImage __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static void objc_drawAsPatternSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImage_Class/Reference/Reference.html#//apple_ref/occ/instm/UIImage/drawAsPatternInRect:">- (void)drawAsPatternInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void drawAsPattern(CGRect rect) {
        if (customClass) { objc_drawAsPatternSuper(getSuper(), drawAsPatternInRect$, rect); } else { objc_drawAsPattern(this, drawAsPatternInRect$, rect); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("CGImage") public static CGImage getCGImage(UIImage __self__, Selector __cmd__) { return __self__.getCGImage(); }
        @Callback @BindSelector("CIImage") public static CIImage getCIImage(UIImage __self__, Selector __cmd__) { return __self__.getCIImage(); }
        @Callback @BindSelector("alignmentRectInsets") public static @ByVal UIEdgeInsets getAlignmentRectInsets(UIImage __self__, Selector __cmd__) { return __self__.getAlignmentRectInsets(); }
        @Callback @BindSelector("capInsets") public static @ByVal UIEdgeInsets getCapInsets(UIImage __self__, Selector __cmd__) { return __self__.getCapInsets(); }
        @Callback @BindSelector("duration") public static double getDuration(UIImage __self__, Selector __cmd__) { return __self__.getDuration(); }
        @Callback @BindSelector("images") public static NSArray getImages(UIImage __self__, Selector __cmd__) { return __self__.getImages(); }
        @Callback @BindSelector("imageOrientation") public static UIImageOrientation getOrientation(UIImage __self__, Selector __cmd__) { return __self__.getOrientation(); }
        @Callback @BindSelector("resizingMode") public static UIImageResizingMode getResizingMode(UIImage __self__, Selector __cmd__) { return __self__.getResizingMode(); }
        @Callback @BindSelector("scale") public static float getScale(UIImage __self__, Selector __cmd__) { return __self__.getScale(); }
        @Callback @BindSelector("size") public static @ByVal CGSize getSize(UIImage __self__, Selector __cmd__) { return __self__.getSize(); }
        @Callback @BindSelector("imageWithAlignmentRectInsets:") public static UIImage copyWithAlignmentRectInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets alignmentInsets) { return __self__.copyWithAlignmentRectInsets(alignmentInsets); }
        @Callback @BindSelector("resizableImageWithCapInsets:") public static UIImage copyWithCapInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets capInsets) { return __self__.copyWithCapInsets(capInsets); }
        @Callback @BindSelector("resizableImageWithCapInsets:resizingMode:") public static UIImage copyWithCapInsets(UIImage __self__, Selector __cmd__, @ByVal UIEdgeInsets capInsets, UIImageResizingMode resizingMode) { return __self__.copyWithCapInsets(capInsets, resizingMode); }
        @Callback @BindSelector("drawInRect:") public static void draw(UIImage __self__, Selector __cmd__, @ByVal CGRect rect) { __self__.draw(rect); }
        @Callback @BindSelector("drawInRect:blendMode:alpha:") public static void draw(UIImage __self__, Selector __cmd__, @ByVal CGRect rect, CGBlendMode blendMode, float alpha) { __self__.draw(rect, blendMode, alpha); }
        @Callback @BindSelector("drawAtPoint:blendMode:alpha:") public static void draw(UIImage __self__, Selector __cmd__, @ByVal CGPoint point, CGBlendMode blendMode, float alpha) { __self__.draw(point, blendMode, alpha); }
        @Callback @BindSelector("drawAtPoint:") public static void draw(UIImage __self__, Selector __cmd__, @ByVal CGPoint point) { __self__.draw(point); }
        @Callback @BindSelector("drawAsPatternInRect:") public static void drawAsPattern(UIImage __self__, Selector __cmd__, @ByVal CGRect rect) { __self__.drawAsPattern(rect); }
    }
    /*</callbacks>*/

}
