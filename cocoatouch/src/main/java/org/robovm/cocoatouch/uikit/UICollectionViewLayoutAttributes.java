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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html">UICollectionViewLayoutAttributes Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionViewLayoutAttributes /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewLayoutAttributes /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewLayoutAttributes /*</name>*/.class);

    /*<constructors>*/
    protected UICollectionViewLayoutAttributes(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewLayoutAttributes() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alpha = Selector.register("alpha");
    @Bridge private native static float objc_getAlpha(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static float objc_getAlphaSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/alpha">@property (nonatomic) CGFloat alpha;</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getAlpha() {
        if (customClass) { return objc_getAlphaSuper(getSuper(), alpha); } else { return objc_getAlpha(this, alpha); }
    }
    
    private static final Selector setAlpha$ = Selector.register("setAlpha:");
    @Bridge private native static void objc_setAlpha(UICollectionViewLayoutAttributes __self__, Selector __cmd__, float alpha);
    @Bridge private native static void objc_setAlphaSuper(ObjCSuper __super__, Selector __cmd__, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/alpha">@property (nonatomic) CGFloat alpha;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAlpha(float alpha) {
        if (customClass) { objc_setAlphaSuper(getSuper(), setAlpha$, alpha); } else { objc_setAlpha(this, setAlpha$, alpha); }
    }
    
    private static final Selector center = Selector.register("center");
    @Bridge private native static @ByVal CGPoint objc_getCenter(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGPoint objc_getCenterSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/center">@property (nonatomic) CGPoint center;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGPoint getCenter() {
        if (customClass) { return objc_getCenterSuper(getSuper(), center); } else { return objc_getCenter(this, center); }
    }
    
    private static final Selector setCenter$ = Selector.register("setCenter:");
    @Bridge private native static void objc_setCenter(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGPoint center);
    @Bridge private native static void objc_setCenterSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint center);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/center">@property (nonatomic) CGPoint center;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setCenter(CGPoint center) {
        if (customClass) { objc_setCenterSuper(getSuper(), setCenter$, center); } else { objc_setCenter(this, setCenter$, center); }
    }
    
    private static final Selector frame = Selector.register("frame");
    @Bridge private native static @ByVal CGRect objc_getFrame(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getFrameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/frame">@property (nonatomic) CGRect frame;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getFrame() {
        if (customClass) { return objc_getFrameSuper(getSuper(), frame); } else { return objc_getFrame(this, frame); }
    }
    
    private static final Selector setFrame$ = Selector.register("setFrame:");
    @Bridge private native static void objc_setFrame(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGRect frame);
    @Bridge private native static void objc_setFrameSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect frame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/frame">@property (nonatomic) CGRect frame;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setFrame(CGRect frame) {
        if (customClass) { objc_setFrameSuper(getSuper(), setFrame$, frame); } else { objc_setFrame(this, setFrame$, frame); }
    }
    
    private static final Selector isHidden = Selector.register("isHidden");
    @Bridge private native static boolean objc_isHidden(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHiddenSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/hidden">@property (nonatomic, getter=isHidden) BOOL hidden;</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isHidden() {
        if (customClass) { return objc_isHiddenSuper(getSuper(), isHidden); } else { return objc_isHidden(this, isHidden); }
    }
    
    private static final Selector setHidden$ = Selector.register("setHidden:");
    @Bridge private native static void objc_setHidden(UICollectionViewLayoutAttributes __self__, Selector __cmd__, boolean hidden);
    @Bridge private native static void objc_setHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean hidden);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/hidden">@property (nonatomic, getter=isHidden) BOOL hidden;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setHidden(boolean hidden) {
        if (customClass) { objc_setHiddenSuper(getSuper(), setHidden$, hidden); } else { objc_setHidden(this, setHidden$, hidden); }
    }
    
    private static final Selector indexPath = Selector.register("indexPath");
    @Bridge private native static NSIndexPath objc_getIndexPath(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static NSIndexPath objc_getIndexPathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/indexPath">@property (nonatomic, retain) NSIndexPath *indexPath;</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSIndexPath getIndexPath() {
        if (customClass) { return objc_getIndexPathSuper(getSuper(), indexPath); } else { return objc_getIndexPath(this, indexPath); }
    }
    
    private static final Selector setIndexPath$ = Selector.register("setIndexPath:");
    @Bridge private native static void objc_setIndexPath(UICollectionViewLayoutAttributes __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static void objc_setIndexPathSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/indexPath">@property (nonatomic, retain) NSIndexPath *indexPath;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setIndexPath(NSIndexPath indexPath) {
        if (customClass) { objc_setIndexPathSuper(getSuper(), setIndexPath$, indexPath); } else { objc_setIndexPath(this, setIndexPath$, indexPath); }
    }
    
    private static final Selector representedElementCategory = Selector.register("representedElementCategory");
    @Bridge private native static UICollectionElementCategory objc_getRepresentedElementCategory(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static UICollectionElementCategory objc_getRepresentedElementCategorySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/representedElementCategory">@property (nonatomic, readonly) UICollectionElementCategory representedElementCategory;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionElementCategory getRepresentedElementCategory() {
        if (customClass) { return objc_getRepresentedElementCategorySuper(getSuper(), representedElementCategory); } else { return objc_getRepresentedElementCategory(this, representedElementCategory); }
    }
    
    private static final Selector representedElementKind = Selector.register("representedElementKind");
    @Bridge private native static String objc_getRepresentedElementKind(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static String objc_getRepresentedElementKindSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/representedElementKind">@property (nonatomic, readonly) NSString *representedElementKind;</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getRepresentedElementKind() {
        if (customClass) { return objc_getRepresentedElementKindSuper(getSuper(), representedElementKind); } else { return objc_getRepresentedElementKind(this, representedElementKind); }
    }
    
    private static final Selector size = Selector.register("size");
    @Bridge private native static @ByVal CGSize objc_getSize(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/size">@property (nonatomic) CGSize size;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getSize() {
        if (customClass) { return objc_getSizeSuper(getSuper(), size); } else { return objc_getSize(this, size); }
    }
    
    private static final Selector setSize$ = Selector.register("setSize:");
    @Bridge private native static void objc_setSize(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGSize size);
    @Bridge private native static void objc_setSizeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize size);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/size">@property (nonatomic) CGSize size;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSize(CGSize size) {
        if (customClass) { objc_setSizeSuper(getSuper(), setSize$, size); } else { objc_setSize(this, setSize$, size); }
    }
    
    private static final Selector transform3D = Selector.register("transform3D");
    @Bridge private native static @ByVal CATransform3D objc_getTransform3D(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static @ByVal CATransform3D objc_getTransform3DSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/transform3D">@property (nonatomic) CATransform3D transform3D;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CATransform3D getTransform3D() {
        if (customClass) { return objc_getTransform3DSuper(getSuper(), transform3D); } else { return objc_getTransform3D(this, transform3D); }
    }
    
    private static final Selector setTransform3D$ = Selector.register("setTransform3D:");
    @Bridge private native static void objc_setTransform3D(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CATransform3D transform3D);
    @Bridge private native static void objc_setTransform3DSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CATransform3D transform3D);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/transform3D">@property (nonatomic) CATransform3D transform3D;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTransform3D(CATransform3D transform3D) {
        if (customClass) { objc_setTransform3DSuper(getSuper(), setTransform3D$, transform3D); } else { objc_setTransform3D(this, setTransform3D$, transform3D); }
    }
    
    private static final Selector zIndex = Selector.register("zIndex");
    @Bridge private native static int objc_getZIndex(UICollectionViewLayoutAttributes __self__, Selector __cmd__);
    @Bridge private native static int objc_getZIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/zIndex">@property (nonatomic) NSInteger zIndex;</a>
     * @since Available in iOS 6.0 and later.
     */
    public int getZIndex() {
        if (customClass) { return objc_getZIndexSuper(getSuper(), zIndex); } else { return objc_getZIndex(this, zIndex); }
    }
    
    private static final Selector setZIndex$ = Selector.register("setZIndex:");
    @Bridge private native static void objc_setZIndex(UICollectionViewLayoutAttributes __self__, Selector __cmd__, int zIndex);
    @Bridge private native static void objc_setZIndexSuper(ObjCSuper __super__, Selector __cmd__, int zIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/zIndex">@property (nonatomic) NSInteger zIndex;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setZIndex(int zIndex) {
        if (customClass) { objc_setZIndexSuper(getSuper(), setZIndex$, zIndex); } else { objc_setZIndex(this, setZIndex$, zIndex); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector layoutAttributesForCellWithIndexPath$ = Selector.register("layoutAttributesForCellWithIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_createForCell(ObjCClass __self__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForCellWithIndexPath:">+ (instancetype)layoutAttributesForCellWithIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UICollectionViewLayoutAttributes createForCell(NSIndexPath indexPath) {
        return objc_createForCell(objCClass, layoutAttributesForCellWithIndexPath$, indexPath);
    }
    
    private static final Selector layoutAttributesForDecorationViewOfKind$withIndexPath$ = Selector.register("layoutAttributesForDecorationViewOfKind:withIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_createForDecorationView(ObjCClass __self__, Selector __cmd__, String decorationViewKind, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForDecorationViewOfKind:withIndexPath:">+ (instancetype)layoutAttributesForDecorationViewOfKind:(NSString *)decorationViewKind withIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UICollectionViewLayoutAttributes createForDecorationView(String decorationViewKind, NSIndexPath indexPath) {
        return objc_createForDecorationView(objCClass, layoutAttributesForDecorationViewOfKind$withIndexPath$, decorationViewKind, indexPath);
    }
    
    private static final Selector layoutAttributesForSupplementaryViewOfKind$withIndexPath$ = Selector.register("layoutAttributesForSupplementaryViewOfKind:withIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_createForSupplementaryView(ObjCClass __self__, Selector __cmd__, String elementKind, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForSupplementaryViewOfKind:withIndexPath:">+ (instancetype)layoutAttributesForSupplementaryViewOfKind:(NSString *)elementKind withIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public static UICollectionViewLayoutAttributes createForSupplementaryView(String elementKind, NSIndexPath indexPath) {
        return objc_createForSupplementaryView(objCClass, layoutAttributesForSupplementaryViewOfKind$withIndexPath$, elementKind, indexPath);
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alpha") public static float getAlpha(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getAlpha(); }
        @Callback @BindSelector("setAlpha:") public static void setAlpha(UICollectionViewLayoutAttributes __self__, Selector __cmd__, float alpha) { __self__.setAlpha(alpha); }
        @Callback @BindSelector("center") public static @ByVal CGPoint getCenter(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getCenter(); }
        @Callback @BindSelector("setCenter:") public static void setCenter(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGPoint center) { __self__.setCenter(center); }
        @Callback @BindSelector("frame") public static @ByVal CGRect getFrame(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getFrame(); }
        @Callback @BindSelector("setFrame:") public static void setFrame(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGRect frame) { __self__.setFrame(frame); }
        @Callback @BindSelector("isHidden") public static boolean isHidden(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.isHidden(); }
        @Callback @BindSelector("setHidden:") public static void setHidden(UICollectionViewLayoutAttributes __self__, Selector __cmd__, boolean hidden) { __self__.setHidden(hidden); }
        @Callback @BindSelector("indexPath") public static NSIndexPath getIndexPath(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getIndexPath(); }
        @Callback @BindSelector("setIndexPath:") public static void setIndexPath(UICollectionViewLayoutAttributes __self__, Selector __cmd__, NSIndexPath indexPath) { __self__.setIndexPath(indexPath); }
        @Callback @BindSelector("representedElementCategory") public static UICollectionElementCategory getRepresentedElementCategory(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getRepresentedElementCategory(); }
        @Callback @BindSelector("representedElementKind") public static String getRepresentedElementKind(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getRepresentedElementKind(); }
        @Callback @BindSelector("size") public static @ByVal CGSize getSize(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getSize(); }
        @Callback @BindSelector("setSize:") public static void setSize(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CGSize size) { __self__.setSize(size); }
        @Callback @BindSelector("transform3D") public static @ByVal CATransform3D getTransform3D(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getTransform3D(); }
        @Callback @BindSelector("setTransform3D:") public static void setTransform3D(UICollectionViewLayoutAttributes __self__, Selector __cmd__, @ByVal CATransform3D transform3D) { __self__.setTransform3D(transform3D); }
        @Callback @BindSelector("zIndex") public static int getZIndex(UICollectionViewLayoutAttributes __self__, Selector __cmd__) { return __self__.getZIndex(); }
        @Callback @BindSelector("setZIndex:") public static void setZIndex(UICollectionViewLayoutAttributes __self__, Selector __cmd__, int zIndex) { __self__.setZIndex(zIndex); }
    }
    /*</callbacks>*/

}
