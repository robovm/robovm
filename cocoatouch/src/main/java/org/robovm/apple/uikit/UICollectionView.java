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
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionView/*</name>*/ 
    extends /*<extends>*/UIScrollView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewPtr extends Ptr<UICollectionView, UICollectionViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionView() {}
    protected UICollectionView(SkipInit skipInit) { super(skipInit); }
    public UICollectionView(@ByVal CGRect frame, UICollectionViewLayout layout) { super((SkipInit) null); initObject(initWithFrame$collectionViewLayout$(frame, layout)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "collectionViewLayout")
    public native UICollectionViewLayout getCollectionViewLayout();
    @Property(selector = "setCollectionViewLayout:")
    public native void setCollectionViewLayout(UICollectionViewLayout v);
    @Property(selector = "delegate")
    public native UICollectionViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UICollectionViewDelegate v);
    @Property(selector = "dataSource")
    public native UICollectionViewDataSource getDataSource();
    @Property(selector = "setDataSource:", strongRef = true)
    public native void setDataSource(UICollectionViewDataSource v);
    @Property(selector = "backgroundView")
    public native UIView getBackgroundView();
    @Property(selector = "setBackgroundView:")
    public native void setBackgroundView(UIView v);
    @Property(selector = "allowsSelection")
    public native boolean isAllowsSelection();
    @Property(selector = "setAllowsSelection:")
    public native void setAllowsSelection(boolean v);
    @Property(selector = "allowsMultipleSelection")
    public native boolean isAllowsMultipleSelection();
    @Property(selector = "setAllowsMultipleSelection:")
    public native void setAllowsMultipleSelection(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:collectionViewLayout:")
    protected native @Pointer long initWithFrame$collectionViewLayout$(@ByVal CGRect frame, UICollectionViewLayout layout);
    @Method(selector = "registerClass:forCellWithReuseIdentifier:")
    public native void registerReusableCellClass(ObjCClass cellClass, String identifier);
    @Method(selector = "registerNib:forCellWithReuseIdentifier:")
    public native void registerReusableCellNib(UINib nib, String identifier);
    @Method(selector = "registerClass:forSupplementaryViewOfKind:withReuseIdentifier:")
    public native void registerReusableSupplementaryViewClass(ObjCClass viewClass, String elementKind, String identifier);
    @Method(selector = "registerNib:forSupplementaryViewOfKind:withReuseIdentifier:")
    public native void registerReusableSupplementaryViewNib(UINib nib, String kind, String identifier);
    @Method(selector = "dequeueReusableCellWithReuseIdentifier:forIndexPath:")
    public native NSObject dequeueReusableCell(String identifier, NSIndexPath indexPath);
    @Method(selector = "dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:")
    public native NSObject dequeueReusableSupplementaryView(String elementKind, String identifier, NSIndexPath indexPath);
    @Method(selector = "indexPathsForSelectedItems")
    public native NSArray<?> getSelectedItemsIndexPaths();
    @Method(selector = "selectItemAtIndexPath:animated:scrollPosition:")
    public native void selectItem(NSIndexPath indexPath, boolean animated, UICollectionViewScrollPosition scrollPosition);
    @Method(selector = "deselectItemAtIndexPath:animated:")
    public native void deselectItem(NSIndexPath indexPath, boolean animated);
    @Method(selector = "reloadData")
    public native void reloadData();
    @Method(selector = "setCollectionViewLayout:animated:")
    public native void setCollectionViewLayout$animated$(UICollectionViewLayout layout, boolean animated);
    @Method(selector = "setCollectionViewLayout:animated:completion:")
    public native void setCollectionViewLayout$animated$completion$(UICollectionViewLayout layout, boolean animated, @Block VoidBooleanBlock completion);
    @Method(selector = "startInteractiveTransitionToCollectionViewLayout:completion:")
    public native UICollectionViewTransitionLayout startInteractiveTransitionToCollectionViewLayout$completion$(UICollectionViewLayout layout, FunctionPtr completion);
    @Method(selector = "finishInteractiveTransition")
    public native void finishInteractiveTransition();
    @Method(selector = "cancelInteractiveTransition")
    public native void cancelInteractiveTransition();
    @Method(selector = "numberOfSections")
    public native @MachineSizedSInt long getNumberOfSections();
    @Method(selector = "numberOfItemsInSection:")
    public native @MachineSizedSInt long getNumberOfItemsInSection(@MachineSizedSInt long section);
    @Method(selector = "layoutAttributesForItemAtIndexPath:")
    public native UICollectionViewLayoutAttributes getItemLayoutAttributes(NSIndexPath indexPath);
    @Method(selector = "layoutAttributesForSupplementaryElementOfKind:atIndexPath:")
    public native UICollectionViewLayoutAttributes getSupplementaryElementLayoutAttributes(String kind, NSIndexPath indexPath);
    @Method(selector = "indexPathForItemAtPoint:")
    public native NSIndexPath getItemIndexPath(@ByVal CGPoint point);
    @Method(selector = "indexPathForCell:")
    public native NSIndexPath getCellIndexPath(UICollectionViewCell cell);
    @Method(selector = "cellForItemAtIndexPath:")
    public native UICollectionViewCell getItemCell(NSIndexPath indexPath);
    @Method(selector = "visibleCells")
    public native NSArray<?> getVisibleCells();
    @Method(selector = "indexPathsForVisibleItems")
    public native NSArray<?> getVisibleItemsIndexPaths();
    @Method(selector = "scrollToItemAtIndexPath:atScrollPosition:animated:")
    public native void scrollToItem(NSIndexPath indexPath, UICollectionViewScrollPosition scrollPosition, boolean animated);
    @Method(selector = "insertSections:")
    public native void insertSections$(NSIndexSet sections);
    @Method(selector = "deleteSections:")
    public native void deleteSections$(NSIndexSet sections);
    @Method(selector = "reloadSections:")
    public native void reloadSections$(NSIndexSet sections);
    @Method(selector = "moveSection:toSection:")
    public native void moveSection$toSection$(@MachineSizedSInt long section, @MachineSizedSInt long newSection);
    @Method(selector = "insertItemsAtIndexPaths:")
    public native void insertItems(NSArray<?> indexPaths);
    @Method(selector = "deleteItemsAtIndexPaths:")
    public native void deleteItems(NSArray<?> indexPaths);
    @Method(selector = "reloadItemsAtIndexPaths:")
    public native void reloadItems(NSArray<?> indexPaths);
    @Method(selector = "moveItemAtIndexPath:toIndexPath:")
    public native void moveItem(NSIndexPath indexPath, NSIndexPath newIndexPath);
    @Method(selector = "performBatchUpdates:completion:")
    public native void performBatchUpdates$completion$(@Block Runnable updates, @Block VoidBooleanBlock completion);
    /*</methods>*/
}
