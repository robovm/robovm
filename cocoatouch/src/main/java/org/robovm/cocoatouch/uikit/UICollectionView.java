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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html">UICollectionView Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionView /*</name>*/.class);

    public UICollectionView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UICollectionView(SkipInit skipInit) { super(skipInit); }
    public UICollectionView() {}
    
    private static final Selector initWithFrame$collectionViewLayout$ = Selector.register("initWithFrame:collectionViewLayout:");
    @Bridge private native static @Pointer long objc_initWithFrame(UICollectionView __self__, Selector __cmd__, @ByVal CGRect frame, UICollectionViewLayout layout);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/initWithFrame:collectionViewLayout:">- (id)initWithFrame:(CGRect)frame  collectionViewLayout:(UICollectionViewLayout *)layout</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionView(CGRect frame, UICollectionViewLayout layout) {
        super((SkipInit) null);
        initObject(objc_initWithFrame(this, initWithFrame$collectionViewLayout$, frame, layout));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector allowsMultipleSelection = Selector.register("allowsMultipleSelection");
    @Bridge private native static boolean objc_isAllowsMultipleSelection(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsMultipleSelectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsMultipleSelection">@property (nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isAllowsMultipleSelection() {
        if (customClass) { return objc_isAllowsMultipleSelectionSuper(getSuper(), allowsMultipleSelection); } else { return objc_isAllowsMultipleSelection(this, allowsMultipleSelection); }
    }
    
    private static final Selector setAllowsMultipleSelection$ = Selector.register("setAllowsMultipleSelection:");
    @Bridge private native static void objc_setAllowsMultipleSelection(UICollectionView __self__, Selector __cmd__, boolean allowsMultipleSelection);
    @Bridge private native static void objc_setAllowsMultipleSelectionSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsMultipleSelection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsMultipleSelection">@property (nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAllowsMultipleSelection(boolean allowsMultipleSelection) {
        if (customClass) { objc_setAllowsMultipleSelectionSuper(getSuper(), setAllowsMultipleSelection$, allowsMultipleSelection); } else { objc_setAllowsMultipleSelection(this, setAllowsMultipleSelection$, allowsMultipleSelection); }
    }
    
    private static final Selector allowsSelection = Selector.register("allowsSelection");
    @Bridge private native static boolean objc_isAllowsSelection(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsSelectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsSelection">@property (nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isAllowsSelection() {
        if (customClass) { return objc_isAllowsSelectionSuper(getSuper(), allowsSelection); } else { return objc_isAllowsSelection(this, allowsSelection); }
    }
    
    private static final Selector setAllowsSelection$ = Selector.register("setAllowsSelection:");
    @Bridge private native static void objc_setAllowsSelection(UICollectionView __self__, Selector __cmd__, boolean allowsSelection);
    @Bridge private native static void objc_setAllowsSelectionSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsSelection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/allowsSelection">@property (nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAllowsSelection(boolean allowsSelection) {
        if (customClass) { objc_setAllowsSelectionSuper(getSuper(), setAllowsSelection$, allowsSelection); } else { objc_setAllowsSelection(this, setAllowsSelection$, allowsSelection); }
    }
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge private native static UIView objc_getBackgroundView(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/backgroundView">@property (nonatomic, retain) UIView *backgroundView;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge private native static void objc_setBackgroundView(UICollectionView __self__, Selector __cmd__, UIView backgroundView);
    @Bridge private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/backgroundView">@property (nonatomic, retain) UIView *backgroundView;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector collectionViewLayout = Selector.register("collectionViewLayout");
    @Bridge private native static UICollectionViewLayout objc_getCollectionViewLayout(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static UICollectionViewLayout objc_getCollectionViewLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/collectionViewLayout">@property (nonatomic, retain) UICollectionViewLayout *collectionViewLayout;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayout getCollectionViewLayout() {
        if (customClass) { return objc_getCollectionViewLayoutSuper(getSuper(), collectionViewLayout); } else { return objc_getCollectionViewLayout(this, collectionViewLayout); }
    }
    
    private static final Selector setCollectionViewLayout$ = Selector.register("setCollectionViewLayout:");
    @Bridge private native static void objc_setCollectionViewLayout(UICollectionView __self__, Selector __cmd__, UICollectionViewLayout collectionViewLayout);
    @Bridge private native static void objc_setCollectionViewLayoutSuper(ObjCSuper __super__, Selector __cmd__, UICollectionViewLayout collectionViewLayout);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/collectionViewLayout">@property (nonatomic, retain) UICollectionViewLayout *collectionViewLayout;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setCollectionViewLayout(UICollectionViewLayout collectionViewLayout) {
        if (customClass) { objc_setCollectionViewLayoutSuper(getSuper(), setCollectionViewLayout$, collectionViewLayout); } else { objc_setCollectionViewLayout(this, setCollectionViewLayout$, collectionViewLayout); }
    }
    
    private static final Selector dataSource = Selector.register("dataSource");
    @Bridge private native static UICollectionViewDataSource objc_getDataSource(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static UICollectionViewDataSource objc_getDataSourceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/dataSource">@property (nonatomic, assign) id &amp;lt;UICollectionViewDataSource&amp;gt; dataSource;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewDataSource getDataSource() {
        if (customClass) { return objc_getDataSourceSuper(getSuper(), dataSource); } else { return objc_getDataSource(this, dataSource); }
    }
    
    private static final Selector setDataSource$ = Selector.register("setDataSource:");
    @Bridge private native static void objc_setDataSource(UICollectionView __self__, Selector __cmd__, UICollectionViewDataSource dataSource);
    @Bridge private native static void objc_setDataSourceSuper(ObjCSuper __super__, Selector __cmd__, UICollectionViewDataSource dataSource);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/dataSource">@property (nonatomic, assign) id &amp;lt;UICollectionViewDataSource&amp;gt; dataSource;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDataSource(UICollectionViewDataSource dataSource) {
        if (customClass) { objc_setDataSourceSuper(getSuper(), setDataSource$, dataSource); } else { objc_setDataSource(this, setDataSource$, dataSource); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UICollectionViewDelegate objc_getDelegate(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static UICollectionViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/delegate">@property (nonatomic, assign) id &amp;lt;UICollectionViewDelegate&amp;gt; delegate;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UICollectionView __self__, Selector __cmd__, UICollectionViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UICollectionViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionView/delegate">@property (nonatomic, assign) id &amp;lt;UICollectionViewDelegate&amp;gt; delegate;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDelegate(UICollectionViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector deleteItemsAtIndexPaths$ = Selector.register("deleteItemsAtIndexPaths:");
    @Bridge private native static void objc_deleteItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths);
    @Bridge private native static void objc_deleteItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deleteItemsAtIndexPaths:">- (void)deleteItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    public void deleteItems(NSArray indexPaths) {
        if (customClass) { objc_deleteItemsSuper(getSuper(), deleteItemsAtIndexPaths$, indexPaths); } else { objc_deleteItems(this, deleteItemsAtIndexPaths$, indexPaths); }
    }
    
    private static final Selector deleteSections$ = Selector.register("deleteSections:");
    @Bridge private native static void objc_deleteSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections);
    @Bridge private native static void objc_deleteSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deleteSections:">- (void)deleteSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    public void deleteSections(NSIndexSet sections) {
        if (customClass) { objc_deleteSectionsSuper(getSuper(), deleteSections$, sections); } else { objc_deleteSections(this, deleteSections$, sections); }
    }
    
    private static final Selector dequeueReusableCellWithReuseIdentifier$forIndexPath$ = Selector.register("dequeueReusableCellWithReuseIdentifier:forIndexPath:");
    @Bridge private native static NSObject objc_dequeueReusableCell(UICollectionView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    @Bridge private native static NSObject objc_dequeueReusableCellSuper(ObjCSuper __super__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/dequeueReusableCellWithReuseIdentifier:forIndexPath:">- (id)dequeueReusableCellWithReuseIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableCell(String identifier, NSIndexPath indexPath) {
        if (customClass) { return objc_dequeueReusableCellSuper(getSuper(), dequeueReusableCellWithReuseIdentifier$forIndexPath$, identifier, indexPath); } else { return objc_dequeueReusableCell(this, dequeueReusableCellWithReuseIdentifier$forIndexPath$, identifier, indexPath); }
    }
    
    private static final Selector dequeueReusableSupplementaryViewOfKind$withReuseIdentifier$forIndexPath$ = Selector.register("dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:");
    @Bridge private native static NSObject objc_dequeueReusableSupplementaryView(UICollectionView __self__, Selector __cmd__, String elementKind, String identifier, NSIndexPath indexPath);
    @Bridge private native static NSObject objc_dequeueReusableSupplementaryViewSuper(ObjCSuper __super__, Selector __cmd__, String elementKind, String identifier, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:">- (id)dequeueReusableSupplementaryViewOfKind:(NSString*)elementKind withReuseIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableSupplementaryView(String elementKind, String identifier, NSIndexPath indexPath) {
        if (customClass) { return objc_dequeueReusableSupplementaryViewSuper(getSuper(), dequeueReusableSupplementaryViewOfKind$withReuseIdentifier$forIndexPath$, elementKind, identifier, indexPath); } else { return objc_dequeueReusableSupplementaryView(this, dequeueReusableSupplementaryViewOfKind$withReuseIdentifier$forIndexPath$, elementKind, identifier, indexPath); }
    }
    
    private static final Selector deselectItemAtIndexPath$animated$ = Selector.register("deselectItemAtIndexPath:animated:");
    @Bridge private native static void objc_deselectItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    @Bridge private native static void objc_deselectItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/deselectItemAtIndexPath:animated:">- (void)deselectItemAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    public void deselectItem(NSIndexPath indexPath, boolean animated) {
        if (customClass) { objc_deselectItemSuper(getSuper(), deselectItemAtIndexPath$animated$, indexPath, animated); } else { objc_deselectItem(this, deselectItemAtIndexPath$animated$, indexPath, animated); }
    }
    
    private static final Selector indexPathForCell$ = Selector.register("indexPathForCell:");
    @Bridge private native static NSIndexPath objc_getCellIndexPath(UICollectionView __self__, Selector __cmd__, UICollectionViewCell cell);
    @Bridge private native static NSIndexPath objc_getCellIndexPathSuper(ObjCSuper __super__, Selector __cmd__, UICollectionViewCell cell);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathForCell:">- (NSIndexPath *)indexPathForCell:(UICollectionViewCell *)cell</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSIndexPath getCellIndexPath(UICollectionViewCell cell) {
        if (customClass) { return objc_getCellIndexPathSuper(getSuper(), indexPathForCell$, cell); } else { return objc_getCellIndexPath(this, indexPathForCell$, cell); }
    }
    
    private static final Selector cellForItemAtIndexPath$ = Selector.register("cellForItemAtIndexPath:");
    @Bridge private native static UICollectionViewCell objc_getItemCell(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewCell objc_getItemCellSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/cellForItemAtIndexPath:">- (UICollectionViewCell *)cellForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewCell getItemCell(NSIndexPath indexPath) {
        if (customClass) { return objc_getItemCellSuper(getSuper(), cellForItemAtIndexPath$, indexPath); } else { return objc_getItemCell(this, cellForItemAtIndexPath$, indexPath); }
    }
    
    private static final Selector indexPathForItemAtPoint$ = Selector.register("indexPathForItemAtPoint:");
    @Bridge private native static NSIndexPath objc_getItemIndexPath(UICollectionView __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static NSIndexPath objc_getItemIndexPathSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathForItemAtPoint:">- (NSIndexPath *)indexPathForItemAtPoint:(CGPoint)point</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSIndexPath getItemIndexPath(CGPoint point) {
        if (customClass) { return objc_getItemIndexPathSuper(getSuper(), indexPathForItemAtPoint$, point); } else { return objc_getItemIndexPath(this, indexPathForItemAtPoint$, point); }
    }
    
    private static final Selector layoutAttributesForItemAtIndexPath$ = Selector.register("layoutAttributesForItemAtIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getItemLayoutAttributes(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getItemLayoutAttributesSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/layoutAttributesForItemAtIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForItemAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getItemLayoutAttributes(NSIndexPath indexPath) {
        if (customClass) { return objc_getItemLayoutAttributesSuper(getSuper(), layoutAttributesForItemAtIndexPath$, indexPath); } else { return objc_getItemLayoutAttributes(this, layoutAttributesForItemAtIndexPath$, indexPath); }
    }
    
    private static final Selector numberOfItemsInSection$ = Selector.register("numberOfItemsInSection:");
    @Bridge private native static int objc_getNumberOfItemsInSection(UICollectionView __self__, Selector __cmd__, int section);
    @Bridge private native static int objc_getNumberOfItemsInSectionSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/numberOfItemsInSection:">- (NSInteger)numberOfItemsInSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    public int getNumberOfItemsInSection(int section) {
        if (customClass) { return objc_getNumberOfItemsInSectionSuper(getSuper(), numberOfItemsInSection$, section); } else { return objc_getNumberOfItemsInSection(this, numberOfItemsInSection$, section); }
    }
    
    private static final Selector numberOfSections = Selector.register("numberOfSections");
    @Bridge private native static int objc_getNumberOfSections(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfSectionsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/numberOfSections">- (NSInteger)numberOfSections</a>
     * @since Available in iOS 6.0 and later.
     */
    public int getNumberOfSections() {
        if (customClass) { return objc_getNumberOfSectionsSuper(getSuper(), numberOfSections); } else { return objc_getNumberOfSections(this, numberOfSections); }
    }
    
    private static final Selector indexPathsForSelectedItems = Selector.register("indexPathsForSelectedItems");
    @Bridge private native static NSArray objc_getSelectedItemsIndexPaths(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getSelectedItemsIndexPathsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathsForSelectedItems">- (NSArray *)indexPathsForSelectedItems</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getSelectedItemsIndexPaths() {
        if (customClass) { return objc_getSelectedItemsIndexPathsSuper(getSuper(), indexPathsForSelectedItems); } else { return objc_getSelectedItemsIndexPaths(this, indexPathsForSelectedItems); }
    }
    
    private static final Selector layoutAttributesForSupplementaryElementOfKind$atIndexPath$ = Selector.register("layoutAttributesForSupplementaryElementOfKind:atIndexPath:");
    @Bridge private native static UICollectionViewLayoutAttributes objc_getSupplementaryElementLayoutAttributes(UICollectionView __self__, Selector __cmd__, String kind, NSIndexPath indexPath);
    @Bridge private native static UICollectionViewLayoutAttributes objc_getSupplementaryElementLayoutAttributesSuper(ObjCSuper __super__, Selector __cmd__, String kind, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/layoutAttributesForSupplementaryElementOfKind:atIndexPath:">- (UICollectionViewLayoutAttributes *)layoutAttributesForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewLayoutAttributes getSupplementaryElementLayoutAttributes(String kind, NSIndexPath indexPath) {
        if (customClass) { return objc_getSupplementaryElementLayoutAttributesSuper(getSuper(), layoutAttributesForSupplementaryElementOfKind$atIndexPath$, kind, indexPath); } else { return objc_getSupplementaryElementLayoutAttributes(this, layoutAttributesForSupplementaryElementOfKind$atIndexPath$, kind, indexPath); }
    }
    
    private static final Selector visibleCells = Selector.register("visibleCells");
    @Bridge private native static NSArray objc_getVisibleCells(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getVisibleCellsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/visibleCells">- (NSArray *)visibleCells</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getVisibleCells() {
        if (customClass) { return objc_getVisibleCellsSuper(getSuper(), visibleCells); } else { return objc_getVisibleCells(this, visibleCells); }
    }
    
    private static final Selector indexPathsForVisibleItems = Selector.register("indexPathsForVisibleItems");
    @Bridge private native static NSArray objc_getVisibleItemsIndexPaths(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getVisibleItemsIndexPathsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/indexPathsForVisibleItems">- (NSArray *)indexPathsForVisibleItems</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getVisibleItemsIndexPaths() {
        if (customClass) { return objc_getVisibleItemsIndexPathsSuper(getSuper(), indexPathsForVisibleItems); } else { return objc_getVisibleItemsIndexPaths(this, indexPathsForVisibleItems); }
    }
    
    private static final Selector insertItemsAtIndexPaths$ = Selector.register("insertItemsAtIndexPaths:");
    @Bridge private native static void objc_insertItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths);
    @Bridge private native static void objc_insertItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/insertItemsAtIndexPaths:">- (void)insertItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    public void insertItems(NSArray indexPaths) {
        if (customClass) { objc_insertItemsSuper(getSuper(), insertItemsAtIndexPaths$, indexPaths); } else { objc_insertItems(this, insertItemsAtIndexPaths$, indexPaths); }
    }
    
    private static final Selector insertSections$ = Selector.register("insertSections:");
    @Bridge private native static void objc_insertSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections);
    @Bridge private native static void objc_insertSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/insertSections:">- (void)insertSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    public void insertSections(NSIndexSet sections) {
        if (customClass) { objc_insertSectionsSuper(getSuper(), insertSections$, sections); } else { objc_insertSections(this, insertSections$, sections); }
    }
    
    private static final Selector moveItemAtIndexPath$toIndexPath$ = Selector.register("moveItemAtIndexPath:toIndexPath:");
    @Bridge private native static void objc_moveItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    @Bridge private native static void objc_moveItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/moveItemAtIndexPath:toIndexPath:">- (void)moveItemAtIndexPath:(NSIndexPath *)indexPath toIndexPath:(NSIndexPath *)newIndexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public void moveItem(NSIndexPath indexPath, NSIndexPath newIndexPath) {
        if (customClass) { objc_moveItemSuper(getSuper(), moveItemAtIndexPath$toIndexPath$, indexPath, newIndexPath); } else { objc_moveItem(this, moveItemAtIndexPath$toIndexPath$, indexPath, newIndexPath); }
    }
    
    private static final Selector moveSection$toSection$ = Selector.register("moveSection:toSection:");
    @Bridge private native static void objc_moveSection(UICollectionView __self__, Selector __cmd__, int section, int newSection);
    @Bridge private native static void objc_moveSectionSuper(ObjCSuper __super__, Selector __cmd__, int section, int newSection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/moveSection:toSection:">- (void)moveSection:(NSInteger)section toSection:(NSInteger)newSection</a>
     * @since Available in iOS 6.0 and later.
     */
    public void moveSection(int section, int newSection) {
        if (customClass) { objc_moveSectionSuper(getSuper(), moveSection$toSection$, section, newSection); } else { objc_moveSection(this, moveSection$toSection$, section, newSection); }
    }
    
    private static final Selector performBatchUpdates$completion$ = Selector.register("performBatchUpdates:completion:");
    @Bridge private native static void objc_performBatchUpdates(UICollectionView __self__, Selector __cmd__, VoidBlock updates, VoidBooleanBlock completion);
    @Bridge private native static void objc_performBatchUpdatesSuper(ObjCSuper __super__, Selector __cmd__, VoidBlock updates, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/performBatchUpdates:completion:">- (void)performBatchUpdates:(void (^)(void))updates completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 6.0 and later.
     */
    public void performBatchUpdates(VoidBlock updates, VoidBooleanBlock completion) {
        if (customClass) { objc_performBatchUpdatesSuper(getSuper(), performBatchUpdates$completion$, updates, completion); } else { objc_performBatchUpdates(this, performBatchUpdates$completion$, updates, completion); }
    }
    
    private static final Selector registerClass$forCellWithReuseIdentifier$ = Selector.register("registerClass:forCellWithReuseIdentifier:");
    @Bridge private native static void objc_registerReusableCellClass(UICollectionView __self__, Selector __cmd__, ObjCClass cellClass, String identifier);
    @Bridge private native static void objc_registerReusableCellClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass cellClass, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerClass:forCellWithReuseIdentifier:">- (void)registerClass:(Class)cellClass forCellWithReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableCellClass(ObjCClass cellClass, String identifier) {
        if (customClass) { objc_registerReusableCellClassSuper(getSuper(), registerClass$forCellWithReuseIdentifier$, cellClass, identifier); } else { objc_registerReusableCellClass(this, registerClass$forCellWithReuseIdentifier$, cellClass, identifier); }
    }
    
    private static final Selector registerNib$forCellWithReuseIdentifier$ = Selector.register("registerNib:forCellWithReuseIdentifier:");
    @Bridge private native static void objc_registerReusableCellNib(UICollectionView __self__, Selector __cmd__, UINib nib, String identifier);
    @Bridge private native static void objc_registerReusableCellNibSuper(ObjCSuper __super__, Selector __cmd__, UINib nib, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerNib:forCellWithReuseIdentifier:">- (void)registerNib:(UINib *)nib forCellWithReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableCellNib(UINib nib, String identifier) {
        if (customClass) { objc_registerReusableCellNibSuper(getSuper(), registerNib$forCellWithReuseIdentifier$, nib, identifier); } else { objc_registerReusableCellNib(this, registerNib$forCellWithReuseIdentifier$, nib, identifier); }
    }
    
    private static final Selector registerClass$forSupplementaryViewOfKind$withReuseIdentifier$ = Selector.register("registerClass:forSupplementaryViewOfKind:withReuseIdentifier:");
    @Bridge private native static void objc_registerReusableSupplementaryViewClass(UICollectionView __self__, Selector __cmd__, ObjCClass viewClass, String elementKind, String identifier);
    @Bridge private native static void objc_registerReusableSupplementaryViewClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass viewClass, String elementKind, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerClass:forSupplementaryViewOfKind:withReuseIdentifier:">- (void)registerClass:(Class)viewClass forSupplementaryViewOfKind:(NSString *)elementKind withReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableSupplementaryViewClass(ObjCClass viewClass, String elementKind, String identifier) {
        if (customClass) { objc_registerReusableSupplementaryViewClassSuper(getSuper(), registerClass$forSupplementaryViewOfKind$withReuseIdentifier$, viewClass, elementKind, identifier); } else { objc_registerReusableSupplementaryViewClass(this, registerClass$forSupplementaryViewOfKind$withReuseIdentifier$, viewClass, elementKind, identifier); }
    }
    
    private static final Selector registerNib$forSupplementaryViewOfKind$withReuseIdentifier$ = Selector.register("registerNib:forSupplementaryViewOfKind:withReuseIdentifier:");
    @Bridge private native static void objc_registerReusableSupplementaryViewNib(UICollectionView __self__, Selector __cmd__, UINib nib, String kind, String identifier);
    @Bridge private native static void objc_registerReusableSupplementaryViewNibSuper(ObjCSuper __super__, Selector __cmd__, UINib nib, String kind, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/registerNib:forSupplementaryViewOfKind:withReuseIdentifier:">- (void)registerNib:(UINib *)nib forSupplementaryViewOfKind:(NSString *)kind withReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableSupplementaryViewNib(UINib nib, String kind, String identifier) {
        if (customClass) { objc_registerReusableSupplementaryViewNibSuper(getSuper(), registerNib$forSupplementaryViewOfKind$withReuseIdentifier$, nib, kind, identifier); } else { objc_registerReusableSupplementaryViewNib(this, registerNib$forSupplementaryViewOfKind$withReuseIdentifier$, nib, kind, identifier); }
    }
    
    private static final Selector reloadData = Selector.register("reloadData");
    @Bridge private native static void objc_reloadData(UICollectionView __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadDataSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadData">- (void)reloadData</a>
     * @since Available in iOS 6.0 and later.
     */
    public void reloadData() {
        if (customClass) { objc_reloadDataSuper(getSuper(), reloadData); } else { objc_reloadData(this, reloadData); }
    }
    
    private static final Selector reloadItemsAtIndexPaths$ = Selector.register("reloadItemsAtIndexPaths:");
    @Bridge private native static void objc_reloadItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths);
    @Bridge private native static void objc_reloadItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadItemsAtIndexPaths:">- (void)reloadItemsAtIndexPaths:(NSArray *)indexPaths</a>
     * @since Available in iOS 6.0 and later.
     */
    public void reloadItems(NSArray indexPaths) {
        if (customClass) { objc_reloadItemsSuper(getSuper(), reloadItemsAtIndexPaths$, indexPaths); } else { objc_reloadItems(this, reloadItemsAtIndexPaths$, indexPaths); }
    }
    
    private static final Selector reloadSections$ = Selector.register("reloadSections:");
    @Bridge private native static void objc_reloadSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections);
    @Bridge private native static void objc_reloadSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/reloadSections:">- (void)reloadSections:(NSIndexSet *)sections</a>
     * @since Available in iOS 6.0 and later.
     */
    public void reloadSections(NSIndexSet sections) {
        if (customClass) { objc_reloadSectionsSuper(getSuper(), reloadSections$, sections); } else { objc_reloadSections(this, reloadSections$, sections); }
    }
    
    private static final Selector scrollToItemAtIndexPath$atScrollPosition$animated$ = Selector.register("scrollToItemAtIndexPath:atScrollPosition:animated:");
    @Bridge private native static void objc_scrollToItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, UICollectionViewScrollPosition scrollPosition, boolean animated);
    @Bridge private native static void objc_scrollToItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, UICollectionViewScrollPosition scrollPosition, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/scrollToItemAtIndexPath:atScrollPosition:animated:">- (void)scrollToItemAtIndexPath:(NSIndexPath *)indexPath atScrollPosition:(UICollectionViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    public void scrollToItem(NSIndexPath indexPath, UICollectionViewScrollPosition scrollPosition, boolean animated) {
        if (customClass) { objc_scrollToItemSuper(getSuper(), scrollToItemAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); } else { objc_scrollToItem(this, scrollToItemAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); }
    }
    
    private static final Selector selectItemAtIndexPath$animated$scrollPosition$ = Selector.register("selectItemAtIndexPath:animated:scrollPosition:");
    @Bridge private native static void objc_selectItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UICollectionViewScrollPosition scrollPosition);
    @Bridge private native static void objc_selectItemSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UICollectionViewScrollPosition scrollPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/selectItemAtIndexPath:animated:scrollPosition:">- (void)selectItemAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated scrollPosition:(UICollectionViewScrollPosition)scrollPosition</a>
     * @since Available in iOS 6.0 and later.
     */
    public void selectItem(NSIndexPath indexPath, boolean animated, UICollectionViewScrollPosition scrollPosition) {
        if (customClass) { objc_selectItemSuper(getSuper(), selectItemAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); } else { objc_selectItem(this, selectItemAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); }
    }
    
    private static final Selector setCollectionViewLayout$animated$ = Selector.register("setCollectionViewLayout:animated:");
    @Bridge private native static void objc_setCollectionViewLayout(UICollectionView __self__, Selector __cmd__, UICollectionViewLayout layout, boolean animated);
    @Bridge private native static void objc_setCollectionViewLayoutSuper(ObjCSuper __super__, Selector __cmd__, UICollectionViewLayout layout, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionView/setCollectionViewLayout:animated:">- (void)setCollectionViewLayout:(UICollectionViewLayout *)layout animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setCollectionViewLayout(UICollectionViewLayout layout, boolean animated) {
        if (customClass) { objc_setCollectionViewLayoutSuper(getSuper(), setCollectionViewLayout$animated$, layout, animated); } else { objc_setCollectionViewLayout(this, setCollectionViewLayout$animated$, layout, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allowsMultipleSelection") public static boolean isAllowsMultipleSelection(UICollectionView __self__, Selector __cmd__) { return __self__.isAllowsMultipleSelection(); }
        @Callback @BindSelector("setAllowsMultipleSelection:") public static void setAllowsMultipleSelection(UICollectionView __self__, Selector __cmd__, boolean allowsMultipleSelection) { __self__.setAllowsMultipleSelection(allowsMultipleSelection); }
        @Callback @BindSelector("allowsSelection") public static boolean isAllowsSelection(UICollectionView __self__, Selector __cmd__) { return __self__.isAllowsSelection(); }
        @Callback @BindSelector("setAllowsSelection:") public static void setAllowsSelection(UICollectionView __self__, Selector __cmd__, boolean allowsSelection) { __self__.setAllowsSelection(allowsSelection); }
        @Callback @BindSelector("backgroundView") public static UIView getBackgroundView(UICollectionView __self__, Selector __cmd__) { return __self__.getBackgroundView(); }
        @Callback @BindSelector("setBackgroundView:") public static void setBackgroundView(UICollectionView __self__, Selector __cmd__, UIView backgroundView) { __self__.setBackgroundView(backgroundView); }
        @Callback @BindSelector("collectionViewLayout") public static UICollectionViewLayout getCollectionViewLayout(UICollectionView __self__, Selector __cmd__) { return __self__.getCollectionViewLayout(); }
        @Callback @BindSelector("setCollectionViewLayout:") public static void setCollectionViewLayout(UICollectionView __self__, Selector __cmd__, UICollectionViewLayout collectionViewLayout) { __self__.setCollectionViewLayout(collectionViewLayout); }
        @Callback @BindSelector("dataSource") public static UICollectionViewDataSource getDataSource(UICollectionView __self__, Selector __cmd__) { return __self__.getDataSource(); }
        @Callback @BindSelector("setDataSource:") public static void setDataSource(UICollectionView __self__, Selector __cmd__, UICollectionViewDataSource dataSource) { __self__.setDataSource(dataSource); }
        @Callback @BindSelector("delegate") public static UICollectionViewDelegate getDelegate(UICollectionView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UICollectionView __self__, Selector __cmd__, UICollectionViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("deleteItemsAtIndexPaths:") public static void deleteItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths) { __self__.deleteItems(indexPaths); }
        @Callback @BindSelector("deleteSections:") public static void deleteSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections) { __self__.deleteSections(sections); }
        @Callback @BindSelector("dequeueReusableCellWithReuseIdentifier:forIndexPath:") public static NSObject dequeueReusableCell(UICollectionView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath) { return __self__.dequeueReusableCell(identifier, indexPath); }
        @Callback @BindSelector("dequeueReusableSupplementaryViewOfKind:withReuseIdentifier:forIndexPath:") public static NSObject dequeueReusableSupplementaryView(UICollectionView __self__, Selector __cmd__, String elementKind, String identifier, NSIndexPath indexPath) { return __self__.dequeueReusableSupplementaryView(elementKind, identifier, indexPath); }
        @Callback @BindSelector("deselectItemAtIndexPath:animated:") public static void deselectItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated) { __self__.deselectItem(indexPath, animated); }
        @Callback @BindSelector("indexPathForCell:") public static NSIndexPath getCellIndexPath(UICollectionView __self__, Selector __cmd__, UICollectionViewCell cell) { return __self__.getCellIndexPath(cell); }
        @Callback @BindSelector("cellForItemAtIndexPath:") public static UICollectionViewCell getItemCell(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath) { return __self__.getItemCell(indexPath); }
        @Callback @BindSelector("indexPathForItemAtPoint:") public static NSIndexPath getItemIndexPath(UICollectionView __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getItemIndexPath(point); }
        @Callback @BindSelector("layoutAttributesForItemAtIndexPath:") public static UICollectionViewLayoutAttributes getItemLayoutAttributes(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath) { return __self__.getItemLayoutAttributes(indexPath); }
        @Callback @BindSelector("numberOfItemsInSection:") public static int getNumberOfItemsInSection(UICollectionView __self__, Selector __cmd__, int section) { return __self__.getNumberOfItemsInSection(section); }
        @Callback @BindSelector("numberOfSections") public static int getNumberOfSections(UICollectionView __self__, Selector __cmd__) { return __self__.getNumberOfSections(); }
        @Callback @BindSelector("indexPathsForSelectedItems") public static NSArray getSelectedItemsIndexPaths(UICollectionView __self__, Selector __cmd__) { return __self__.getSelectedItemsIndexPaths(); }
        @Callback @BindSelector("layoutAttributesForSupplementaryElementOfKind:atIndexPath:") public static UICollectionViewLayoutAttributes getSupplementaryElementLayoutAttributes(UICollectionView __self__, Selector __cmd__, String kind, NSIndexPath indexPath) { return __self__.getSupplementaryElementLayoutAttributes(kind, indexPath); }
        @Callback @BindSelector("visibleCells") public static NSArray getVisibleCells(UICollectionView __self__, Selector __cmd__) { return __self__.getVisibleCells(); }
        @Callback @BindSelector("indexPathsForVisibleItems") public static NSArray getVisibleItemsIndexPaths(UICollectionView __self__, Selector __cmd__) { return __self__.getVisibleItemsIndexPaths(); }
        @Callback @BindSelector("insertItemsAtIndexPaths:") public static void insertItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths) { __self__.insertItems(indexPaths); }
        @Callback @BindSelector("insertSections:") public static void insertSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections) { __self__.insertSections(sections); }
        @Callback @BindSelector("moveItemAtIndexPath:toIndexPath:") public static void moveItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath) { __self__.moveItem(indexPath, newIndexPath); }
        @Callback @BindSelector("moveSection:toSection:") public static void moveSection(UICollectionView __self__, Selector __cmd__, int section, int newSection) { __self__.moveSection(section, newSection); }
        @Callback @BindSelector("performBatchUpdates:completion:") public static void performBatchUpdates(UICollectionView __self__, Selector __cmd__, VoidBlock updates, VoidBooleanBlock completion) { __self__.performBatchUpdates(updates, completion); }
        @Callback @BindSelector("registerClass:forCellWithReuseIdentifier:") public static void registerReusableCellClass(UICollectionView __self__, Selector __cmd__, ObjCClass cellClass, String identifier) { __self__.registerReusableCellClass(cellClass, identifier); }
        @Callback @BindSelector("registerNib:forCellWithReuseIdentifier:") public static void registerReusableCellNib(UICollectionView __self__, Selector __cmd__, UINib nib, String identifier) { __self__.registerReusableCellNib(nib, identifier); }
        @Callback @BindSelector("registerClass:forSupplementaryViewOfKind:withReuseIdentifier:") public static void registerReusableSupplementaryViewClass(UICollectionView __self__, Selector __cmd__, ObjCClass viewClass, String elementKind, String identifier) { __self__.registerReusableSupplementaryViewClass(viewClass, elementKind, identifier); }
        @Callback @BindSelector("registerNib:forSupplementaryViewOfKind:withReuseIdentifier:") public static void registerReusableSupplementaryViewNib(UICollectionView __self__, Selector __cmd__, UINib nib, String kind, String identifier) { __self__.registerReusableSupplementaryViewNib(nib, kind, identifier); }
        @Callback @BindSelector("reloadData") public static void reloadData(UICollectionView __self__, Selector __cmd__) { __self__.reloadData(); }
        @Callback @BindSelector("reloadItemsAtIndexPaths:") public static void reloadItems(UICollectionView __self__, Selector __cmd__, NSArray indexPaths) { __self__.reloadItems(indexPaths); }
        @Callback @BindSelector("reloadSections:") public static void reloadSections(UICollectionView __self__, Selector __cmd__, NSIndexSet sections) { __self__.reloadSections(sections); }
        @Callback @BindSelector("scrollToItemAtIndexPath:atScrollPosition:animated:") public static void scrollToItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, UICollectionViewScrollPosition scrollPosition, boolean animated) { __self__.scrollToItem(indexPath, scrollPosition, animated); }
        @Callback @BindSelector("selectItemAtIndexPath:animated:scrollPosition:") public static void selectItem(UICollectionView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UICollectionViewScrollPosition scrollPosition) { __self__.selectItem(indexPath, animated, scrollPosition); }
        @Callback @BindSelector("setCollectionViewLayout:animated:") public static void setCollectionViewLayout(UICollectionView __self__, Selector __cmd__, UICollectionViewLayout layout, boolean animated) { __self__.setCollectionViewLayout(layout, animated); }
    }
    /*</callbacks>*/

}
