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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html">UICollectionViewLayout Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionViewLayout /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewLayout /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewLayout /*</name>*/.class);

    /*<constructors>*/
    protected UICollectionViewLayout(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewLayout() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector collectionView = Selector.register("collectionView");
    @Bridge private native static UICollectionView objc_getCollectionView(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static UICollectionView objc_getCollectionViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayout/collectionView">@property (nonatomic, readonly) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionView getCollectionView() {
        if (customClass) { return objc_getCollectionViewSuper(getSuper(), collectionView); } else { return objc_getCollectionView(this, collectionView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector layoutAttributesClass = Selector.register("layoutAttributesClass");
    @Bridge private native static ObjCClass objc_getLayoutAttributesClass(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayout/layoutAttributesClass">+ (Class)layoutAttributesClass</a>
     * @since Available in iOS 6.0 and later.
     */
    public static ObjCClass getLayoutAttributesClass() {
        return objc_getLayoutAttributesClass(objCClass, layoutAttributesClass);
    }
    
    private static final Selector finalizeAnimatedBoundsChange = Selector.register("finalizeAnimatedBoundsChange");
    @Bridge private native static void objc_finalizeAnimatedBoundsChange(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static void objc_finalizeAnimatedBoundsChangeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalizeAnimatedBoundsChange">- (void)finalizeAnimatedBoundsChange</a>
     * @since Available in iOS 6.0 and later.
     */
    public void finalizeAnimatedBoundsChange() {
        if (customClass) { objc_finalizeAnimatedBoundsChangeSuper(getSuper(), finalizeAnimatedBoundsChange); } else { objc_finalizeAnimatedBoundsChange(this, finalizeAnimatedBoundsChange); }
    }
    
    private static final Selector finalizeCollectionViewUpdates = Selector.register("finalizeCollectionViewUpdates");
    @Bridge private native static void objc_finalizeCollectionViewUpdates(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static void objc_finalizeCollectionViewUpdatesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalizeCollectionViewUpdates">- (void)finalizeCollectionViewUpdates</a>
     * @since Available in iOS 6.0 and later.
     */
    public void finalizeCollectionViewUpdates() {
        if (customClass) { objc_finalizeCollectionViewUpdatesSuper(getSuper(), finalizeCollectionViewUpdates); } else { objc_finalizeCollectionViewUpdates(this, finalizeCollectionViewUpdates); }
    }
    
    private static final Selector collectionViewContentSize = Selector.register("collectionViewContentSize");
    @Bridge private native static @ByVal CGSize objc_getCollectionViewContentSize(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getCollectionViewContentSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/collectionViewContentSize">- (CGSize)collectionViewContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getCollectionViewContentSize() {
        if (customClass) { return objc_getCollectionViewContentSizeSuper(getSuper(), collectionViewContentSize); } else { return objc_getCollectionViewContentSize(this, collectionViewContentSize); }
    }
    
    private static final Selector finalLayoutAttributesForDisappearingDecorationElementOfKind$atIndexPath$ = Selector.register("finalLayoutAttributesForDisappearingDecorationElementOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingDecorationElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingDecorationElementSuper(ObjCSuper __super__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingDecorationElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingDecorationElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingDecorationElement(String elementKind, NSIndexPath elementIndexPath) {
        if (customClass) { return objc_getFinalLayoutAttributesForDisappearingDecorationElementSuper(getSuper(), finalLayoutAttributesForDisappearingDecorationElementOfKind$atIndexPath$, elementKind, elementIndexPath); } else { return objc_getFinalLayoutAttributesForDisappearingDecorationElement(this, finalLayoutAttributesForDisappearingDecorationElementOfKind$atIndexPath$, elementKind, elementIndexPath); }
    }
    
    private static final Selector finalLayoutAttributesForDisappearingItemAtIndexPath$ = Selector.register("finalLayoutAttributesForDisappearingItemAtIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath itemIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath itemIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingItemAtIndexPath:(NSIndexPath *)itemIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingItem(NSIndexPath itemIndexPath) {
        if (customClass) { return objc_getFinalLayoutAttributesForDisappearingItemSuper(getSuper(), finalLayoutAttributesForDisappearingItemAtIndexPath$, itemIndexPath); } else { return objc_getFinalLayoutAttributesForDisappearingItem(this, finalLayoutAttributesForDisappearingItemAtIndexPath$, itemIndexPath); }
    }
    
    private static final Selector finalLayoutAttributesForDisappearingSupplementaryElementOfKind$atIndexPath$ = Selector.register("finalLayoutAttributesForDisappearingSupplementaryElementOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingSupplementaryElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getFinalLayoutAttributesForDisappearingSupplementaryElementSuper(ObjCSuper __super__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingSupplementaryElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingSupplementaryElement(String elementKind, NSIndexPath elementIndexPath) {
        if (customClass) { return objc_getFinalLayoutAttributesForDisappearingSupplementaryElementSuper(getSuper(), finalLayoutAttributesForDisappearingSupplementaryElementOfKind$atIndexPath$, elementKind, elementIndexPath); } else { return objc_getFinalLayoutAttributesForDisappearingSupplementaryElement(this, finalLayoutAttributesForDisappearingSupplementaryElementOfKind$atIndexPath$, elementKind, elementIndexPath); }
    }
    
    private static final Selector initialLayoutAttributesForAppearingDecorationElementOfKind$atIndexPath$ = Selector.register("initialLayoutAttributesForAppearingDecorationElementOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingDecorationElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingDecorationElementSuper(ObjCSuper __super__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingDecorationElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingDecorationElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingDecorationElement(String elementKind, NSIndexPath elementIndexPath) {
        if (customClass) { return objc_getInitialLayoutAttributesForAppearingDecorationElementSuper(getSuper(), initialLayoutAttributesForAppearingDecorationElementOfKind$atIndexPath$, elementKind, elementIndexPath); } else { return objc_getInitialLayoutAttributesForAppearingDecorationElement(this, initialLayoutAttributesForAppearingDecorationElementOfKind$atIndexPath$, elementKind, elementIndexPath); }
    }
    
    private static final Selector initialLayoutAttributesForAppearingItemAtIndexPath$ = Selector.register("initialLayoutAttributesForAppearingItemAtIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath itemIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath itemIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingItemAtIndexPath:(NSIndexPath *)itemIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingItem(NSIndexPath itemIndexPath) {
        if (customClass) { return objc_getInitialLayoutAttributesForAppearingItemSuper(getSuper(), initialLayoutAttributesForAppearingItemAtIndexPath$, itemIndexPath); } else { return objc_getInitialLayoutAttributesForAppearingItem(this, initialLayoutAttributesForAppearingItemAtIndexPath$, itemIndexPath); }
    }
    
    private static final Selector initialLayoutAttributesForAppearingSupplementaryElementOfKind$atIndexPath$ = Selector.register("initialLayoutAttributesForAppearingSupplementaryElementOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingSupplementaryElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getInitialLayoutAttributesForAppearingSupplementaryElementSuper(ObjCSuper __super__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingSupplementaryElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingSupplementaryElement(String elementKind, NSIndexPath elementIndexPath) {
        if (customClass) { return objc_getInitialLayoutAttributesForAppearingSupplementaryElementSuper(getSuper(), initialLayoutAttributesForAppearingSupplementaryElementOfKind$atIndexPath$, elementKind, elementIndexPath); } else { return objc_getInitialLayoutAttributesForAppearingSupplementaryElement(this, initialLayoutAttributesForAppearingSupplementaryElementOfKind$atIndexPath$, elementKind, elementIndexPath); }
    }
    
    private static final Selector layoutAttributesForDecorationViewOfKind$atIndexPath$ = Selector.register("layoutAttributesForDecorationViewOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForDecorationView(UICollectionViewLayout __self__, Selector __cmd__, String decorationViewKind, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForDecorationViewSuper(ObjCSuper __super__, Selector __cmd__, String decorationViewKind, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForDecorationViewOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForDecorationViewOfKind:(NSString*)decorationViewKind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getLayoutAttributesForDecorationView(String decorationViewKind, NSIndexPath indexPath) {
        if (customClass) { return objc_getLayoutAttributesForDecorationViewSuper(getSuper(), layoutAttributesForDecorationViewOfKind$atIndexPath$, decorationViewKind, indexPath); } else { return objc_getLayoutAttributesForDecorationView(this, layoutAttributesForDecorationViewOfKind$atIndexPath$, decorationViewKind, indexPath); }
    }
    
    private static final Selector layoutAttributesForElementsInRect$ = Selector.register("layoutAttributesForElementsInRect:");
    @Bridge private native static NSArray objc_getLayoutAttributesForElements(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static NSArray objc_getLayoutAttributesForElementsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForElementsInRect:">- (NSArray *)layoutAttributesForElementsInRect:(CGRect)rect</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getLayoutAttributesForElements(CGRect rect) {
        if (customClass) { return objc_getLayoutAttributesForElementsSuper(getSuper(), layoutAttributesForElementsInRect$, rect); } else { return objc_getLayoutAttributesForElements(this, layoutAttributesForElementsInRect$, rect); }
    }
    
    private static final Selector layoutAttributesForItemAtIndexPath$ = Selector.register("layoutAttributesForItemAtIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getLayoutAttributesForItem(NSIndexPath indexPath) {
        if (customClass) { return objc_getLayoutAttributesForItemSuper(getSuper(), layoutAttributesForItemAtIndexPath$, indexPath); } else { return objc_getLayoutAttributesForItem(this, layoutAttributesForItemAtIndexPath$, indexPath); }
    }
    
    private static final Selector layoutAttributesForSupplementaryViewOfKind$atIndexPath$ = Selector.register("layoutAttributesForSupplementaryViewOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForSupplementaryView(UICollectionViewLayout __self__, Selector __cmd__, String kind, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getLayoutAttributesForSupplementaryViewSuper(ObjCSuper __super__, Selector __cmd__, String kind, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForSupplementaryViewOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForSupplementaryViewOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getLayoutAttributesForSupplementaryView(String kind, NSIndexPath indexPath) {
        if (customClass) { return objc_getLayoutAttributesForSupplementaryViewSuper(getSuper(), layoutAttributesForSupplementaryViewOfKind$atIndexPath$, kind, indexPath); } else { return objc_getLayoutAttributesForSupplementaryView(this, layoutAttributesForSupplementaryViewOfKind$atIndexPath$, kind, indexPath); }
    }
    
    private static final Selector targetContentOffsetForProposedContentOffset$withScrollingVelocity$ = Selector.register("targetContentOffsetForProposedContentOffset:withScrollingVelocity:");
    @Bridge private native static @ByVal CGPoint objc_getTargetContentOffset(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGPoint proposedContentOffset, @ByVal CGPoint velocity);
    @Bridge private native static @ByVal CGPoint objc_getTargetContentOffsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint proposedContentOffset, @ByVal CGPoint velocity);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/targetContentOffsetForProposedContentOffset:withScrollingVelocity:">- (CGPoint)targetContentOffsetForProposedContentOffset:(CGPoint)proposedContentOffset withScrollingVelocity:(CGPoint)velocity</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGPoint getTargetContentOffset(CGPoint proposedContentOffset, CGPoint velocity) {
        if (customClass) { return objc_getTargetContentOffsetSuper(getSuper(), targetContentOffsetForProposedContentOffset$withScrollingVelocity$, proposedContentOffset, velocity); } else { return objc_getTargetContentOffset(this, targetContentOffsetForProposedContentOffset$withScrollingVelocity$, proposedContentOffset, velocity); }
    }
    
    private static final Selector invalidateLayout = Selector.register("invalidateLayout");
    @Bridge private native static void objc_invalidateLayout(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static void objc_invalidateLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/invalidateLayout">- (void)invalidateLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public void invalidateLayout() {
        if (customClass) { objc_invalidateLayoutSuper(getSuper(), invalidateLayout); } else { objc_invalidateLayout(this, invalidateLayout); }
    }
    
    private static final Selector prepareForAnimatedBoundsChange$ = Selector.register("prepareForAnimatedBoundsChange:");
    @Bridge private native static void objc_prepareForAnimatedBoundsChange(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect oldBounds);
    @Bridge private native static void objc_prepareForAnimatedBoundsChangeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect oldBounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareForAnimatedBoundsChange:">- (void)prepareForAnimatedBoundsChange:(CGRect)oldBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    public void prepareForAnimatedBoundsChange(CGRect oldBounds) {
        if (customClass) { objc_prepareForAnimatedBoundsChangeSuper(getSuper(), prepareForAnimatedBoundsChange$, oldBounds); } else { objc_prepareForAnimatedBoundsChange(this, prepareForAnimatedBoundsChange$, oldBounds); }
    }
    
    private static final Selector prepareForCollectionViewUpdates$ = Selector.register("prepareForCollectionViewUpdates:");
    @Bridge private native static void objc_prepareForCollectionViewUpdates(UICollectionViewLayout __self__, Selector __cmd__, NSArray updateItems);
    @Bridge private native static void objc_prepareForCollectionViewUpdatesSuper(ObjCSuper __super__, Selector __cmd__, NSArray updateItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareForCollectionViewUpdates:">- (void)prepareForCollectionViewUpdates:(NSArray *)updateItems</a>
     * @since Available in iOS 6.0 and later.
     */
    public void prepareForCollectionViewUpdates(NSArray updateItems) {
        if (customClass) { objc_prepareForCollectionViewUpdatesSuper(getSuper(), prepareForCollectionViewUpdates$, updateItems); } else { objc_prepareForCollectionViewUpdates(this, prepareForCollectionViewUpdates$, updateItems); }
    }
    
    private static final Selector prepareLayout = Selector.register("prepareLayout");
    @Bridge private native static void objc_prepareLayout(UICollectionViewLayout __self__, Selector __cmd__);
    @Bridge private native static void objc_prepareLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareLayout">- (void)prepareLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public void prepareLayout() {
        if (customClass) { objc_prepareLayoutSuper(getSuper(), prepareLayout); } else { objc_prepareLayout(this, prepareLayout); }
    }
    
    private static final Selector registerClass$forDecorationViewOfKind$ = Selector.register("registerClass:forDecorationViewOfKind:");
    @Bridge private native static void objc_registerDecorationViewClass(UICollectionViewLayout __self__, Selector __cmd__, ObjCClass viewClass, String decorationViewKind);
    @Bridge private native static void objc_registerDecorationViewClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass viewClass, String decorationViewKind);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/registerClass:forDecorationViewOfKind:">- (void)registerClass:(Class)viewClass forDecorationViewOfKind:(NSString *)decorationViewKind</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerDecorationViewClass(ObjCClass viewClass, String decorationViewKind) {
        if (customClass) { objc_registerDecorationViewClassSuper(getSuper(), registerClass$forDecorationViewOfKind$, viewClass, decorationViewKind); } else { objc_registerDecorationViewClass(this, registerClass$forDecorationViewOfKind$, viewClass, decorationViewKind); }
    }
    
    private static final Selector registerNib$forDecorationViewOfKind$ = Selector.register("registerNib:forDecorationViewOfKind:");
    @Bridge private native static void objc_registerDecorationViewNib(UICollectionViewLayout __self__, Selector __cmd__, UINib nib, String decorationViewKind);
    @Bridge private native static void objc_registerDecorationViewNibSuper(ObjCSuper __super__, Selector __cmd__, UINib nib, String decorationViewKind);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/registerNib:forDecorationViewOfKind:">- (void)registerNib:(UINib *)nib forDecorationViewOfKind:(NSString *)decorationViewKind</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerDecorationViewNib(UINib nib, String decorationViewKind) {
        if (customClass) { objc_registerDecorationViewNibSuper(getSuper(), registerNib$forDecorationViewOfKind$, nib, decorationViewKind); } else { objc_registerDecorationViewNib(this, registerNib$forDecorationViewOfKind$, nib, decorationViewKind); }
    }
    
    private static final Selector shouldInvalidateLayoutForBoundsChange$ = Selector.register("shouldInvalidateLayoutForBoundsChange:");
    @Bridge private native static boolean objc_shouldInvalidateLayoutForBoundsChange(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect newBounds);
    @Bridge private native static boolean objc_shouldInvalidateLayoutForBoundsChangeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect newBounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/shouldInvalidateLayoutForBoundsChange:">- (BOOL)shouldInvalidateLayoutForBoundsChange:(CGRect)newBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldInvalidateLayoutForBoundsChange(CGRect newBounds) {
        if (customClass) { return objc_shouldInvalidateLayoutForBoundsChangeSuper(getSuper(), shouldInvalidateLayoutForBoundsChange$, newBounds); } else { return objc_shouldInvalidateLayoutForBoundsChange(this, shouldInvalidateLayoutForBoundsChange$, newBounds); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("collectionView") public static UICollectionView getCollectionView(UICollectionViewLayout __self__, Selector __cmd__) { return __self__.getCollectionView(); }
        @Callback @BindSelector("finalizeAnimatedBoundsChange") public static void finalizeAnimatedBoundsChange(UICollectionViewLayout __self__, Selector __cmd__) { __self__.finalizeAnimatedBoundsChange(); }
        @Callback @BindSelector("finalizeCollectionViewUpdates") public static void finalizeCollectionViewUpdates(UICollectionViewLayout __self__, Selector __cmd__) { __self__.finalizeCollectionViewUpdates(); }
        @Callback @BindSelector("collectionViewContentSize") public static @ByVal CGSize getCollectionViewContentSize(UICollectionViewLayout __self__, Selector __cmd__) { return __self__.getCollectionViewContentSize(); }
        @Callback @BindSelector("finalLayoutAttributesForDisappearingDecorationElementOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingDecorationElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath) { return __self__.getFinalLayoutAttributesForDisappearingDecorationElement(elementKind, elementIndexPath); }
        @Callback @BindSelector("finalLayoutAttributesForDisappearingItemAtIndexPath:") public static UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath itemIndexPath) { return __self__.getFinalLayoutAttributesForDisappearingItem(itemIndexPath); }
        @Callback @BindSelector("finalLayoutAttributesForDisappearingSupplementaryElementOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingSupplementaryElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath) { return __self__.getFinalLayoutAttributesForDisappearingSupplementaryElement(elementKind, elementIndexPath); }
        @Callback @BindSelector("initialLayoutAttributesForAppearingDecorationElementOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingDecorationElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath) { return __self__.getInitialLayoutAttributesForAppearingDecorationElement(elementKind, elementIndexPath); }
        @Callback @BindSelector("initialLayoutAttributesForAppearingItemAtIndexPath:") public static UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath itemIndexPath) { return __self__.getInitialLayoutAttributesForAppearingItem(itemIndexPath); }
        @Callback @BindSelector("initialLayoutAttributesForAppearingSupplementaryElementOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingSupplementaryElement(UICollectionViewLayout __self__, Selector __cmd__, String elementKind, NSIndexPath elementIndexPath) { return __self__.getInitialLayoutAttributesForAppearingSupplementaryElement(elementKind, elementIndexPath); }
        @Callback @BindSelector("layoutAttributesForDecorationViewOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getLayoutAttributesForDecorationView(UICollectionViewLayout __self__, Selector __cmd__, String decorationViewKind, NSIndexPath indexPath) { return __self__.getLayoutAttributesForDecorationView(decorationViewKind, indexPath); }
        @Callback @BindSelector("layoutAttributesForElementsInRect:") public static NSArray getLayoutAttributesForElements(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect rect) { return __self__.getLayoutAttributesForElements(rect); }
        @Callback @BindSelector("layoutAttributesForItemAtIndexPath:") public static UICollectionViewLayoutAttributes getLayoutAttributesForItem(UICollectionViewLayout __self__, Selector __cmd__, NSIndexPath indexPath) { return __self__.getLayoutAttributesForItem(indexPath); }
        @Callback @BindSelector("layoutAttributesForSupplementaryViewOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getLayoutAttributesForSupplementaryView(UICollectionViewLayout __self__, Selector __cmd__, String kind, NSIndexPath indexPath) { return __self__.getLayoutAttributesForSupplementaryView(kind, indexPath); }
        @Callback @BindSelector("targetContentOffsetForProposedContentOffset:withScrollingVelocity:") public static @ByVal CGPoint getTargetContentOffset(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGPoint proposedContentOffset, @ByVal CGPoint velocity) { return __self__.getTargetContentOffset(proposedContentOffset, velocity); }
        @Callback @BindSelector("invalidateLayout") public static void invalidateLayout(UICollectionViewLayout __self__, Selector __cmd__) { __self__.invalidateLayout(); }
        @Callback @BindSelector("prepareForAnimatedBoundsChange:") public static void prepareForAnimatedBoundsChange(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect oldBounds) { __self__.prepareForAnimatedBoundsChange(oldBounds); }
        @Callback @BindSelector("prepareForCollectionViewUpdates:") public static void prepareForCollectionViewUpdates(UICollectionViewLayout __self__, Selector __cmd__, NSArray updateItems) { __self__.prepareForCollectionViewUpdates(updateItems); }
        @Callback @BindSelector("prepareLayout") public static void prepareLayout(UICollectionViewLayout __self__, Selector __cmd__) { __self__.prepareLayout(); }
        @Callback @BindSelector("registerClass:forDecorationViewOfKind:") public static void registerDecorationViewClass(UICollectionViewLayout __self__, Selector __cmd__, ObjCClass viewClass, String decorationViewKind) { __self__.registerDecorationViewClass(viewClass, decorationViewKind); }
        @Callback @BindSelector("registerNib:forDecorationViewOfKind:") public static void registerDecorationViewNib(UICollectionViewLayout __self__, Selector __cmd__, UINib nib, String decorationViewKind) { __self__.registerDecorationViewNib(nib, decorationViewKind); }
        @Callback @BindSelector("shouldInvalidateLayoutForBoundsChange:") public static boolean shouldInvalidateLayoutForBoundsChange(UICollectionViewLayout __self__, Selector __cmd__, @ByVal CGRect newBounds) { return __self__.shouldInvalidateLayoutForBoundsChange(newBounds); }
    }
    /*</callbacks>*/

}
