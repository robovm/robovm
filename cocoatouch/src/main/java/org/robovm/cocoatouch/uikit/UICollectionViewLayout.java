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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html">UICollectionViewLayout Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewLayout /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewLayout /*</name>*/.class);
    }

    /*<constructors>*/
    public UICollectionViewLayout() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayout/collectionView">@property (nonatomic, readonly) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("collectionView") public native @Type("UICollectionView *") UICollectionView getCollectionView();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayout/layoutAttributesClass">+ (Class)layoutAttributesClass</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesClass") public native static @Type("Class") ObjCClass getLayoutAttributesClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalizeAnimatedBoundsChange">- (void)finalizeAnimatedBoundsChange</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("finalizeAnimatedBoundsChange") public native @Type("void") void finalizeAnimatedBoundsChange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalizeCollectionViewUpdates">- (void)finalizeCollectionViewUpdates</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("finalizeCollectionViewUpdates") public native @Type("void") void finalizeCollectionViewUpdates();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/collectionViewContentSize">- (CGSize)collectionViewContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("collectionViewContentSize") public native @Type("CGSize") CGSize getCollectionViewContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingDecorationElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingDecorationElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("finalLayoutAttributesForDisappearingDecorationElementOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingDecorationElement(@Type("NSString *") String elementKind, @Type("NSIndexPath *") NSIndexPath elementIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingItemAtIndexPath:(NSIndexPath *)itemIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("finalLayoutAttributesForDisappearingItemAtIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingItem(@Type("NSIndexPath *") NSIndexPath itemIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/finalLayoutAttributesForDisappearingSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingSupplementaryElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("finalLayoutAttributesForDisappearingSupplementaryElementOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getFinalLayoutAttributesForDisappearingSupplementaryElement(@Type("NSString *") String elementKind, @Type("NSIndexPath *") NSIndexPath elementIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingDecorationElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingDecorationElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initialLayoutAttributesForAppearingDecorationElementOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingDecorationElement(@Type("NSString *") String elementKind, @Type("NSIndexPath *") NSIndexPath elementIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingItemAtIndexPath:(NSIndexPath *)itemIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initialLayoutAttributesForAppearingItemAtIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingItem(@Type("NSIndexPath *") NSIndexPath itemIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/initialLayoutAttributesForAppearingSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)initialLayoutAttributesForAppearingSupplementaryElementOfKind:(NSString *)elementKind atIndexPath:(NSIndexPath *)elementIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initialLayoutAttributesForAppearingSupplementaryElementOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getInitialLayoutAttributesForAppearingSupplementaryElement(@Type("NSString *") String elementKind, @Type("NSIndexPath *") NSIndexPath elementIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForDecorationViewOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForDecorationViewOfKind:(NSString*)decorationViewKind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForDecorationViewOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getLayoutAttributesForDecorationView(@Type("NSString*") String decorationViewKind, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForElementsInRect:">- (NSArray *)layoutAttributesForElementsInRect:(CGRect)rect</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForElementsInRect:") public native @Type("NSArray *") NSArray getLayoutAttributesForElements(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForItemAtIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getLayoutAttributesForItem(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/layoutAttributesForSupplementaryViewOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForSupplementaryViewOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForSupplementaryViewOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getLayoutAttributesForSupplementaryView(@Type("NSString *") String kind, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/targetContentOffsetForProposedContentOffset:withScrollingVelocity:">- (CGPoint)targetContentOffsetForProposedContentOffset:(CGPoint)proposedContentOffset withScrollingVelocity:(CGPoint)velocity</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("targetContentOffsetForProposedContentOffset:withScrollingVelocity:") public native @Type("CGPoint") CGPoint getTargetContentOffset(@Type("CGPoint") CGPoint proposedContentOffset, @Type("CGPoint") CGPoint velocity);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/invalidateLayout">- (void)invalidateLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("invalidateLayout") public native @Type("void") void invalidateLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareForAnimatedBoundsChange:">- (void)prepareForAnimatedBoundsChange:(CGRect)oldBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareForAnimatedBoundsChange:") public native @Type("void") void prepareForAnimatedBoundsChange(@Type("CGRect") CGRect oldBounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareForCollectionViewUpdates:">- (void)prepareForCollectionViewUpdates:(NSArray *)updateItems</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareForCollectionViewUpdates:") public native @Type("void") void prepareForCollectionViewUpdates(@Type("NSArray *") NSArray updateItems);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/prepareLayout">- (void)prepareLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareLayout") public native @Type("void") void prepareLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/registerClass:forDecorationViewOfKind:">- (void)registerClass:(Class)viewClass forDecorationViewOfKind:(NSString *)decorationViewKind</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerClass:forDecorationViewOfKind:") public native @Type("void") void registerDecorationViewClass(@Type("Class") ObjCClass viewClass, @Type("NSString *") String decorationViewKind);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/registerNib:forDecorationViewOfKind:">- (void)registerNib:(UINib *)nib forDecorationViewOfKind:(NSString *)decorationViewKind</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerNib:forDecorationViewOfKind:") public native @Type("void") void registerDecorationViewNib(@Type("UINib *") UINib nib, @Type("NSString *") String decorationViewKind);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayout_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewLayout/shouldInvalidateLayoutForBoundsChange:">- (BOOL)shouldInvalidateLayoutForBoundsChange:(CGRect)newBounds</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldInvalidateLayoutForBoundsChange:") public native @Type("BOOL") boolean shouldInvalidateLayoutForBoundsChange(@Type("CGRect") CGRect newBounds);
    /*</methods>*/

}
