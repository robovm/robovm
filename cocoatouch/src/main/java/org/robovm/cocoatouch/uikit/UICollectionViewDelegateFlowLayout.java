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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html">UICollectionViewDelegateFlowLayout Protocol Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
public interface /*<name>*/ UICollectionViewDelegateFlowLayout /*</name>*/ /*<implements>*/ extends UICollectionViewDelegate, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:sizeForItemAtIndexPath:">- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    CGSize getItemSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:referenceSizeForFooterInSection:">- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    CGSize getSectionFooterReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:referenceSizeForHeaderInSection:">- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    CGSize getSectionHeaderReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:insetForSectionAtIndex:">- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout insetForSectionAtIndex:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    UIEdgeInsets getSectionInset(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:minimumInteritemSpacingForSectionAtIndex:">- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumInteritemSpacingForSectionAtIndex:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    float getSectionMinimumInteritemSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDelegateFlowLayout_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDelegateFlowLayout/collectionView:layout:minimumLineSpacingForSectionAtIndex:">- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumLineSpacingForSectionAtIndex:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    float getSectionMinimumLineSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UICollectionViewDelegate.Adapter implements UICollectionViewDelegateFlowLayout {
        @NotImplemented("collectionView:layout:sizeForItemAtIndexPath:") public CGSize getItemSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:layout:referenceSizeForFooterInSection:") public CGSize getSectionFooterReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:layout:referenceSizeForHeaderInSection:") public CGSize getSectionHeaderReferenceSize(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:layout:insetForSectionAtIndex:") public UIEdgeInsets getSectionInset(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:layout:minimumInteritemSpacingForSectionAtIndex:") public float getSectionMinimumInteritemSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:layout:minimumLineSpacingForSectionAtIndex:") public float getSectionMinimumLineSpacing(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("collectionView:layout:sizeForItemAtIndexPath:") public static @ByVal CGSize getItemSize(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, NSIndexPath indexPath) { return __self__.getItemSize(collectionView, collectionViewLayout, indexPath); }
        @Callback @BindSelector("collectionView:layout:referenceSizeForFooterInSection:") public static @ByVal CGSize getSectionFooterReferenceSize(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { return __self__.getSectionFooterReferenceSize(collectionView, collectionViewLayout, section); }
        @Callback @BindSelector("collectionView:layout:referenceSizeForHeaderInSection:") public static @ByVal CGSize getSectionHeaderReferenceSize(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { return __self__.getSectionHeaderReferenceSize(collectionView, collectionViewLayout, section); }
        @Callback @BindSelector("collectionView:layout:insetForSectionAtIndex:") public static @ByVal UIEdgeInsets getSectionInset(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { return __self__.getSectionInset(collectionView, collectionViewLayout, section); }
        @Callback @BindSelector("collectionView:layout:minimumInteritemSpacingForSectionAtIndex:") public static float getSectionMinimumInteritemSpacing(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { return __self__.getSectionMinimumInteritemSpacing(collectionView, collectionViewLayout, section); }
        @Callback @BindSelector("collectionView:layout:minimumLineSpacingForSectionAtIndex:") public static float getSectionMinimumLineSpacing(UICollectionViewDelegateFlowLayout __self__, Selector __cmd__, UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, int section) { return __self__.getSectionMinimumLineSpacing(collectionView, collectionViewLayout, section); }
    }
    /*</callbacks>*/

}
