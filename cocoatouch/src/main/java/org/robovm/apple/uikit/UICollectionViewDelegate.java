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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UICollectionViewDelegate/*</name>*/ 
    /*<implements>*/extends UIScrollViewDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "collectionView:shouldHighlightItemAtIndexPath:")
    boolean shouldHighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didHighlightItemAtIndexPath:")
    void didHighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didUnhighlightItemAtIndexPath:")
    void didUnhighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldSelectItemAtIndexPath:")
    boolean shouldSelectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldDeselectItemAtIndexPath:")
    boolean shouldDeselectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didSelectItemAtIndexPath:")
    void didSelectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didDeselectItemAtIndexPath:")
    void didDeselectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didEndDisplayingCell:forItemAtIndexPath:")
    void didEndDisplayingCell(UICollectionView collectionView, UICollectionViewCell cell, NSIndexPath indexPath);
    @Method(selector = "collectionView:didEndDisplayingSupplementaryView:forElementOfKind:atIndexPath:")
    void didEndDisplayingSupplementaryView(UICollectionView collectionView, UICollectionReusableView view, String elementKind, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldShowMenuForItemAtIndexPath:")
    boolean shouldShowMenuForItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:canPerformAction:forItemAtIndexPath:withSender:")
    boolean canPerformAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender);
    @Method(selector = "collectionView:performAction:forItemAtIndexPath:withSender:")
    void performAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender);
    @Method(selector = "collectionView:transitionLayoutForOldLayout:newLayout:")
    UICollectionViewTransitionLayout collectionView$transitionLayoutForOldLayout$newLayout$(UICollectionView collectionView, UICollectionViewLayout fromLayout, UICollectionViewLayout toLayout);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
