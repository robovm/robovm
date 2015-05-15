/*
 * Copyright (C) 2013-2015 RoboVM AB
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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewDelegateAdapter/*</name>*/ 
    extends /*<extends>*/UIScrollViewDelegateAdapter/*</extends>*/ 
    /*<implements>*/implements UICollectionViewDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("collectionView:shouldHighlightItemAtIndexPath:")
    public boolean shouldHighlightItem(UICollectionView collectionView, NSIndexPath indexPath) { return false; }
    @NotImplemented("collectionView:didHighlightItemAtIndexPath:")
    public void didHighlightItem(UICollectionView collectionView, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:didUnhighlightItemAtIndexPath:")
    public void didUnhighlightItem(UICollectionView collectionView, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:shouldSelectItemAtIndexPath:")
    public boolean shouldSelectItem(UICollectionView collectionView, NSIndexPath indexPath) { return false; }
    @NotImplemented("collectionView:shouldDeselectItemAtIndexPath:")
    public boolean shouldDeselectItem(UICollectionView collectionView, NSIndexPath indexPath) { return false; }
    @NotImplemented("collectionView:didSelectItemAtIndexPath:")
    public void didSelectItem(UICollectionView collectionView, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:didDeselectItemAtIndexPath:")
    public void didDeselectItem(UICollectionView collectionView, NSIndexPath indexPath) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("collectionView:willDisplayCell:forItemAtIndexPath:")
    public void willDisplayCell(UICollectionView collectionView, UICollectionViewCell cell, NSIndexPath indexPath) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("collectionView:willDisplaySupplementaryView:forElementKind:atIndexPath:")
    public void willDisplaySupplementaryView(UICollectionView collectionView, UICollectionReusableView view, String elementKind, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:didEndDisplayingCell:forItemAtIndexPath:")
    public void didEndDisplayingCell(UICollectionView collectionView, UICollectionViewCell cell, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:didEndDisplayingSupplementaryView:forElementOfKind:atIndexPath:")
    public void didEndDisplayingSupplementaryView(UICollectionView collectionView, UICollectionReusableView view, String elementKind, NSIndexPath indexPath) {}
    @NotImplemented("collectionView:shouldShowMenuForItemAtIndexPath:")
    public boolean shouldShowMenuForItem(UICollectionView collectionView, NSIndexPath indexPath) { return false; }
    @NotImplemented("collectionView:canPerformAction:forItemAtIndexPath:withSender:")
    public boolean canPerformAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender) { return false; }
    @NotImplemented("collectionView:performAction:forItemAtIndexPath:withSender:")
    public void performAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender) {}
    @NotImplemented("collectionView:transitionLayoutForOldLayout:newLayout:")
    public UICollectionViewTransitionLayout getTransitionLayout(UICollectionView collectionView, UICollectionViewLayout fromLayout, UICollectionViewLayout toLayout) { return null; }
    /*</methods>*/
}
