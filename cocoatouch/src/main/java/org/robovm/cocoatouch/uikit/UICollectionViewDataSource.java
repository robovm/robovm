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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDataSource_protocol/Reference/Reference.html">UICollectionViewDataSource Protocol Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
public interface /*<name>*/ UICollectionViewDataSource /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDataSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDataSource/collectionView:cellForItemAtIndexPath:">- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    UICollectionViewCell getItemCell(UICollectionView collectionView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDataSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDataSource/collectionView:numberOfItemsInSection:">- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    int getNumberOfItemsInSection(UICollectionView collectionView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDataSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDataSource/numberOfSectionsInCollectionView:">- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    int getNumberOfSections(UICollectionView collectionView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewDataSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UICollectionViewDataSource/collectionView:viewForSupplementaryElementOfKind:atIndexPath:">- (UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    UICollectionReusableView getSupplementaryElementView(UICollectionView collectionView, String kind, NSIndexPath indexPath);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UICollectionViewDataSource {
        @NotImplemented("collectionView:cellForItemAtIndexPath:") public UICollectionViewCell getItemCell(UICollectionView collectionView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:numberOfItemsInSection:") public int getNumberOfItemsInSection(UICollectionView collectionView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("numberOfSectionsInCollectionView:") public int getNumberOfSections(UICollectionView collectionView) { throw new UnsupportedOperationException(); }
        @NotImplemented("collectionView:viewForSupplementaryElementOfKind:atIndexPath:") public UICollectionReusableView getSupplementaryElementView(UICollectionView collectionView, String kind, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("collectionView:cellForItemAtIndexPath:") public static UICollectionViewCell getItemCell(UICollectionViewDataSource __self__, Selector __cmd__, UICollectionView collectionView, NSIndexPath indexPath) { return __self__.getItemCell(collectionView, indexPath); }
        @Callback @BindSelector("collectionView:numberOfItemsInSection:") public static int getNumberOfItemsInSection(UICollectionViewDataSource __self__, Selector __cmd__, UICollectionView collectionView, int section) { return __self__.getNumberOfItemsInSection(collectionView, section); }
        @Callback @BindSelector("numberOfSectionsInCollectionView:") public static int getNumberOfSections(UICollectionViewDataSource __self__, Selector __cmd__, UICollectionView collectionView) { return __self__.getNumberOfSections(collectionView); }
        @Callback @BindSelector("collectionView:viewForSupplementaryElementOfKind:atIndexPath:") public static UICollectionReusableView getSupplementaryElementView(UICollectionViewDataSource __self__, Selector __cmd__, UICollectionView collectionView, String kind, NSIndexPath indexPath) { return __self__.getSupplementaryElementView(collectionView, kind, indexPath); }
    }
    /*</callbacks>*/

}
