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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html">UICollectionView Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionView /*</name>*/.class);
    }

    /*<constructors>*/
    public UICollectionView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/initWithFrame:collectionViewLayout:">- (id)initWithFrame:(CGRect)frame  collectionViewLayout:(UICollectionViewLayout *)layout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("initWithFrame:collectionViewLayout:") public UICollectionView(@Type("CGRect") CGRect frame, @Type("UICollectionViewLayout *") UICollectionViewLayout layout) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsMultipleSelection">@property (nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("allowsMultipleSelection") public native @Type("BOOL") boolean isAllowsMultipleSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsMultipleSelection">@property (nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAllowsMultipleSelection:") public native void setAllowsMultipleSelection(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsSelection">@property (nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("allowsSelection") public native @Type("BOOL") boolean isAllowsSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsSelection">@property (nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAllowsSelection:") public native void setAllowsSelection(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/backgroundView">@property (nonatomic, retain) UIView *backgroundView;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/backgroundView">@property (nonatomic, retain) UIView *backgroundView;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/collectionViewLayout">@property (nonatomic, retain) UICollectionViewLayout *collectionViewLayout;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("collectionViewLayout") public native @Type("UICollectionViewLayout *") UICollectionViewLayout getCollectionViewLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/collectionViewLayout">@property (nonatomic, retain) UICollectionViewLayout *collectionViewLayout;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setCollectionViewLayout:") public native void setCollectionViewLayout(@Type("UICollectionViewLayout *") UICollectionViewLayout v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/dataSource">@property (nonatomic, assign) id &amp;lt;UICollectionViewDataSource&amp;gt; dataSource;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dataSource") public native @Type("id <UICollectionViewDataSource>") UICollectionViewDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/dataSource">@property (nonatomic, assign) id &amp;lt;UICollectionViewDataSource&amp;gt; dataSource;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(@Type("id <UICollectionViewDataSource>") UICollectionViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/delegate">@property (nonatomic, assign) id &amp;lt;UICollectionViewDelegate&amp;gt; delegate;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("delegate") public native @Type("id <UICollectionViewDelegate>") UICollectionViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/delegate">@property (nonatomic, assign) id &amp;lt;UICollectionViewDelegate&amp;gt; delegate;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id <UICollectionViewDelegate>") UICollectionViewDelegate v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deleteItemsAtIndexPaths:">- (void)deleteItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("deleteItemsAtIndexPaths:") public native @Type("void") void deleteItems(@Type("NSArray *") NSArray indexPaths);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deleteSections:">- (void)deleteSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("deleteSections:") public native @Type("void") void deleteSections(@Type("NSIndexSet *") NSIndexSet sections);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/dequeueReusableCellWithReuseIdentifier:forIndexPath:">- (id)dequeueReusableCellWithReuseIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dequeueReusableCellWithReuseIdentifier:forIndexPath:") public native @Type("id") NSObject dequeueReusableCell(@Type("NSString *") String identifier, @Type("NSIndexPath*") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:">- (id)dequeueReusableSupplementaryViewOfKind:(NSString*)elementKind withReuseIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:") public native @Type("id") NSObject dequeueReusableSupplementaryView(@Type("NSString*") String elementKind, @Type("NSString *") String identifier, @Type("NSIndexPath*") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deselectItemAtIndexPath:animated:">- (void)deselectItemAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("deselectItemAtIndexPath:animated:") public native @Type("void") void deselectItem(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathForCell:">- (NSIndexPath *)indexPathForCell:(UICollectionViewCell *)cell</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathForCell:") public native @Type("NSIndexPath *") NSIndexPath getCellIndexPath(@Type("UICollectionViewCell *") UICollectionViewCell cell);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/cellForItemAtIndexPath:">- (UICollectionViewCell *)cellForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("cellForItemAtIndexPath:") public native @Type("UICollectionViewCell *") UICollectionViewCell getItemCell(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathForItemAtPoint:">- (NSIndexPath *)indexPathForItemAtPoint:(CGPoint)point</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathForItemAtPoint:") public native @Type("NSIndexPath *") NSIndexPath getItemIndexPath(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/layoutAttributesForItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForItemAtIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getItemLayoutAttributes(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/numberOfItemsInSection:">- (NSInteger)numberOfItemsInSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("numberOfItemsInSection:") public native @Type("NSInteger") int getNumberOfItemsInSection(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/numberOfSections">- (NSInteger)numberOfSections</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("numberOfSections") public native @Type("NSInteger") int getNumberOfSections();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathsForSelectedItems">- (NSArray *)indexPathsForSelectedItems</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathsForSelectedItems") public native @Type("NSArray *") NSArray getSelectedItemsIndexPaths();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/layoutAttributesForSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForSupplementaryElementOfKind:atIndexPath:") public native @Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes getSupplementaryElementLayoutAttributes(@Type("NSString *") String kind, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/visibleCells">- (NSArray *)visibleCells</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("visibleCells") public native @Type("NSArray *") NSArray getVisibleCells();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathsForVisibleItems">- (NSArray *)indexPathsForVisibleItems</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathsForVisibleItems") public native @Type("NSArray *") NSArray getVisibleItemsIndexPaths();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/insertItemsAtIndexPaths:">- (void)insertItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("insertItemsAtIndexPaths:") public native @Type("void") void insertItems(@Type("NSArray *") NSArray indexPaths);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/insertSections:">- (void)insertSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("insertSections:") public native @Type("void") void insertSections(@Type("NSIndexSet *") NSIndexSet sections);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/moveItemAtIndexPath:toIndexPath:">- (void)moveItemAtIndexPath:(NSIndexPath *)indexPath toIndexPath:(NSIndexPath *)newIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("moveItemAtIndexPath:toIndexPath:") public native @Type("void") void moveItem(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("NSIndexPath *") NSIndexPath newIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/moveSection:toSection:">- (void)moveSection:(NSInteger)section toSection:(NSInteger)newSection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("moveSection:toSection:") public native @Type("void") void moveSection(@Type("NSInteger") int section, @Type("NSInteger") int newSection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/performBatchUpdates:completion:">- (void)performBatchUpdates:(void (^)(void))updates completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("performBatchUpdates:completion:") public native @Type("void") void performBatchUpdates(@Type("void (^)(void)") VoidBlock updates, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerClass:forCellWithReuseIdentifier:">- (void)registerClass:(Class)cellClass forCellWithReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerClass:forCellWithReuseIdentifier:") public native @Type("void") void registerReusableCellClass(@Type("Class") ObjCClass cellClass, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerNib:forCellWithReuseIdentifier:">- (void)registerNib:(UINib *)nib forCellWithReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerNib:forCellWithReuseIdentifier:") public native @Type("void") void registerReusableCellNib(@Type("UINib *") UINib nib, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerClass:forSupplementaryViewOfKind:withReuseIdentifier:">- (void)registerClass:(Class)viewClass forSupplementaryViewOfKind:(NSString *)elementKind withReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerClass:forSupplementaryViewOfKind:withReuseIdentifier:") public native @Type("void") void registerReusableSupplementaryViewClass(@Type("Class") ObjCClass viewClass, @Type("NSString *") String elementKind, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerNib:forSupplementaryViewOfKind:withReuseIdentifier:">- (void)registerNib:(UINib *)nib forSupplementaryViewOfKind:(NSString *)kind withReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerNib:forSupplementaryViewOfKind:withReuseIdentifier:") public native @Type("void") void registerReusableSupplementaryViewNib(@Type("UINib *") UINib nib, @Type("NSString *") String kind, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadData">- (void)reloadData</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("reloadData") public native @Type("void") void reloadData();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadItemsAtIndexPaths:">- (void)reloadItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("reloadItemsAtIndexPaths:") public native @Type("void") void reloadItems(@Type("NSArray *") NSArray indexPaths);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadSections:">- (void)reloadSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("reloadSections:") public native @Type("void") void reloadSections(@Type("NSIndexSet *") NSIndexSet sections);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/scrollToItemAtIndexPath:atScrollPosition:animated:">- (void)scrollToItemAtIndexPath:(NSIndexPath *)indexPath atScrollPosition:(UICollectionViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("scrollToItemAtIndexPath:atScrollPosition:animated:") public native @Type("void") void scrollToItem(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("UICollectionViewScrollPosition") UICollectionViewScrollPosition scrollPosition, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/selectItemAtIndexPath:animated:scrollPosition:">- (void)selectItemAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated scrollPosition:(UICollectionViewScrollPosition)scrollPosition</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("selectItemAtIndexPath:animated:scrollPosition:") public native @Type("void") void selectItem(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated, @Type("UICollectionViewScrollPosition") UICollectionViewScrollPosition scrollPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/setCollectionViewLayout:animated:">- (void)setCollectionViewLayout:(UICollectionViewLayout *)layout animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setCollectionViewLayout:animated:") public native @Type("void") void setCollectionViewLayout(@Type("UICollectionViewLayout *") UICollectionViewLayout layout, @Type("BOOL") boolean animated);
    /*</methods>*/

}
