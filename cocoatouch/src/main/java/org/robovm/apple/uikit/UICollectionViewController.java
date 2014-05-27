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

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*/implements UICollectionViewDelegate, UICollectionViewDataSource/*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewControllerPtr extends Ptr<UICollectionViewController, UICollectionViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewController() {}
    protected UICollectionViewController(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewController(UICollectionViewLayout layout) { super((SkipInit) null); initObject(initWithCollectionViewLayout$(layout)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "collectionView")
    public native UICollectionView getCollectionView();
    @Property(selector = "setCollectionView:")
    public native void setCollectionView(UICollectionView v);
    @Property(selector = "clearsSelectionOnViewWillAppear")
    public native boolean isClearsSelectionOnViewWillAppear();
    @Property(selector = "setClearsSelectionOnViewWillAppear:")
    public native void setClearsSelectionOnViewWillAppear(boolean v);
    @Property(selector = "useLayoutToLayoutNavigationTransitions")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isUseLayoutToLayoutNavigationTransitions();
    @Property(selector = "setUseLayoutToLayoutNavigationTransitions:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setUseLayoutToLayoutNavigationTransitions(boolean v);
    @Property(selector = "collectionViewLayout")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UICollectionViewLayout getCollectionViewLayout();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCollectionViewLayout:")
    protected native @Pointer long initWithCollectionViewLayout$(UICollectionViewLayout layout);
    @Method(selector = "collectionView:shouldHighlightItemAtIndexPath:")
    public native boolean shouldHighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didHighlightItemAtIndexPath:")
    public native void didHighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didUnhighlightItemAtIndexPath:")
    public native void didUnhighlightItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldSelectItemAtIndexPath:")
    public native boolean shouldSelectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldDeselectItemAtIndexPath:")
    public native boolean shouldDeselectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didSelectItemAtIndexPath:")
    public native void didSelectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didDeselectItemAtIndexPath:")
    public native void didDeselectItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:didEndDisplayingCell:forItemAtIndexPath:")
    public native void didEndDisplayingCell(UICollectionView collectionView, UICollectionViewCell cell, NSIndexPath indexPath);
    @Method(selector = "collectionView:didEndDisplayingSupplementaryView:forElementOfKind:atIndexPath:")
    public native void didEndDisplayingSupplementaryView(UICollectionView collectionView, UICollectionReusableView view, String elementKind, NSIndexPath indexPath);
    @Method(selector = "collectionView:shouldShowMenuForItemAtIndexPath:")
    public native boolean shouldShowMenuForItem(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "collectionView:canPerformAction:forItemAtIndexPath:withSender:")
    public native boolean canPerformAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender);
    @Method(selector = "collectionView:performAction:forItemAtIndexPath:withSender:")
    public native void performAction(UICollectionView collectionView, Selector action, NSIndexPath indexPath, NSObject sender);
    @Method(selector = "collectionView:transitionLayoutForOldLayout:newLayout:")
    public native UICollectionViewTransitionLayout getTransitionLayout(UICollectionView collectionView, UICollectionViewLayout fromLayout, UICollectionViewLayout toLayout);
    @Method(selector = "scrollViewDidScroll:")
    public native void didScroll(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewDidZoom:")
    public native void didZoom(UIScrollView scrollView);
    @Method(selector = "scrollViewWillBeginDragging:")
    public native void willBeginDragging(UIScrollView scrollView);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scrollViewWillEndDragging:withVelocity:targetContentOffset:")
    public native void willEndDragging(UIScrollView scrollView, @ByVal CGPoint velocity, CGPoint targetContentOffset);
    @Method(selector = "scrollViewDidEndDragging:willDecelerate:")
    public native void didEndDragging(UIScrollView scrollView, boolean decelerate);
    @Method(selector = "scrollViewWillBeginDecelerating:")
    public native void willBeginDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndDecelerating:")
    public native void didEndDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndScrollingAnimation:")
    public native void didEndScrollingAnimation(UIScrollView scrollView);
    @Method(selector = "viewForZoomingInScrollView:")
    public native UIView getViewForZooming(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewWillBeginZooming:withView:")
    public native void willBeginZooming(UIScrollView scrollView, UIView view);
    @Method(selector = "scrollViewDidEndZooming:withView:atScale:")
    public native void didEndZooming(UIScrollView scrollView, UIView view, @MachineSizedFloat double scale);
    @Method(selector = "scrollViewShouldScrollToTop:")
    public native boolean shouldScrollToTop(UIScrollView scrollView);
    @Method(selector = "scrollViewDidScrollToTop:")
    public native void didScrollToTop(UIScrollView scrollView);
    @Method(selector = "collectionView:numberOfItemsInSection:")
    public native @MachineSizedSInt long getNumberOfItemsInSection(UICollectionView collectionView, @MachineSizedSInt long section);
    @Method(selector = "collectionView:cellForItemAtIndexPath:")
    public native UICollectionViewCell getItemCell(UICollectionView collectionView, NSIndexPath indexPath);
    @Method(selector = "numberOfSectionsInCollectionView:")
    public native @MachineSizedSInt long getNumberOfSections(UICollectionView collectionView);
    @Method(selector = "collectionView:viewForSupplementaryElementOfKind:atIndexPath:")
    public native UICollectionReusableView getSupplementaryElementView(UICollectionView collectionView, String kind, NSIndexPath indexPath);
    /*</methods>*/
}
