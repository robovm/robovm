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
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html">UITableView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableView /*</name>*/.class);
    }

    /*<constructors>*/
    public UITableView() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/initWithFrame:style:">- (id)initWithFrame:(CGRect)frame style:(UITableViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithFrame:style:") public UITableView(@Type("CGRect") CGRect frame, @Type("UITableViewStyle") UITableViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("allowsMultipleSelection") public native @Type("BOOL") boolean isAllowsMultipleSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAllowsMultipleSelection:") public native void setAllowsMultipleSelection(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("allowsMultipleSelectionDuringEditing") public native @Type("BOOL") boolean isAllowsMultipleSelectionDuringEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAllowsMultipleSelectionDuringEditing:") public native void setAllowsMultipleSelectionDuringEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("allowsSelection") public native @Type("BOOL") boolean isAllowsSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setAllowsSelection:") public native void setAllowsSelection(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("allowsSelectionDuringEditing") public native @Type("BOOL") boolean isAllowsSelectionDuringEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAllowsSelectionDuringEditing:") public native void setAllowsSelectionDuringEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dataSource") public native @Type("id<UITableViewDataSource>") UITableViewDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UITableViewDataSource>") UITableViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UITableViewDelegate>") UITableViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITableViewDelegate>") UITableViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rowHeight") public native @Type("CGFloat") float getRowHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRowHeight:") public native void setRowHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionFooterHeight") public native @Type("CGFloat") float getSectionFooterHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionFooterHeight:") public native void setSectionFooterHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionHeaderHeight") public native @Type("CGFloat") float getSectionHeaderHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionHeaderHeight:") public native void setSectionHeaderHeight(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("sectionIndexColor") public native @Type("UIColor *") UIColor getSectionIndexColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSectionIndexColor:") public native void setSectionIndexColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionIndexMinimumDisplayRowCount") public native @Type("NSInteger") int getSectionIndexMinimumDisplayRowCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionIndexMinimumDisplayRowCount:") public native void setSectionIndexMinimumDisplayRowCount(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("sectionIndexTrackingBackgroundColor") public native @Type("UIColor *") UIColor getSectionIndexTrackingBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSectionIndexTrackingBackgroundColor:") public native void setSectionIndexTrackingBackgroundColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("separatorColor") public native @Type("UIColor *") UIColor getSeparatorColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSeparatorColor:") public native void setSeparatorColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("separatorStyle") public native @Type("UITableViewCellSeparatorStyle") UITableViewCellSeparatorStyle getSeparatorStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSeparatorStyle:") public native void setSeparatorStyle(@Type("UITableViewCellSeparatorStyle") UITableViewCellSeparatorStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/style">@property(nonatomic, readonly) UITableViewStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("style") public native @Type("UITableViewStyle") UITableViewStyle getStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableFooterView") public native @Type("UIView *") UIView getTableFooterView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTableFooterView:") public native void setTableFooterView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableHeaderView") public native @Type("UIView *") UIView getTableHeaderView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTableHeaderView:") public native void setTableHeaderView(@Type("UIView *") UIView v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/beginUpdates">- (void)beginUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginUpdates") public native @Type("void") void beginUpdates();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteRowsAtIndexPaths:withRowAnimation:">- (void)deleteRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("deleteRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void deleteRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteSections:withRowAnimation:">- (void)deleteSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("deleteSections:withRowAnimation:") public native @Type("void") void deleteSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:forIndexPath:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dequeueReusableCellWithIdentifier:forIndexPath:") public native @Type("id") NSObject dequeueReusableCell(@Type("NSString *") String identifier, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dequeueReusableCellWithIdentifier:") public native @Type("id") NSObject dequeueReusableCell(@Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableHeaderFooterViewWithIdentifier:">- (id)dequeueReusableHeaderFooterViewWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dequeueReusableHeaderFooterViewWithIdentifier:") public native @Type("id") NSObject dequeueReusableHeaderFooterView(@Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deselectRowAtIndexPath:animated:">- (void)deselectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("deselectRowAtIndexPath:animated:") public native @Type("void") void deselectRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/endUpdates">- (void)endUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endUpdates") public native @Type("void") void endUpdates();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForCell:">- (NSIndexPath *)indexPathForCell:(UITableViewCell *)cell</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indexPathForCell:") public native @Type("NSIndexPath *") NSIndexPath getCellIndexPath(@Type("UITableViewCell *") UITableViewCell cell);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfRowsInSection:">- (NSInteger)numberOfRowsInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfRowsInSection:") public native @Type("NSInteger") int getNumberOfRowsInSection(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfSections">- (NSInteger)numberOfSections</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfSections") public native @Type("NSInteger") int getNumberOfSections();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/cellForRowAtIndexPath:">- (UITableViewCell *)cellForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cellForRowAtIndexPath:") public native @Type("UITableViewCell *") UITableViewCell getRowCell(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForRowAtPoint:">- (NSIndexPath *)indexPathForRowAtPoint:(CGPoint)point</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indexPathForRowAtPoint:") public native @Type("NSIndexPath *") NSIndexPath getRowIndexPath(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForRowAtIndexPath:">- (CGRect)rectForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rectForRowAtIndexPath:") public native @Type("CGRect") CGRect getRowRect(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForRowsInRect:">- (NSArray *)indexPathsForRowsInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indexPathsForRowsInRect:") public native @Type("NSArray *") NSArray getRowsIndexPaths(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForFooterInSection:">- (CGRect)rectForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rectForFooterInSection:") public native @Type("CGRect") CGRect getSectionFooterRect(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/footerViewForSection:">- (UITableViewHeaderFooterView *)footerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("footerViewForSection:") public native @Type("UITableViewHeaderFooterView *") UITableViewHeaderFooterView getSectionFooterView(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForHeaderInSection:">- (CGRect)rectForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rectForHeaderInSection:") public native @Type("CGRect") CGRect getSectionHeaderRect(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/headerViewForSection:">- (UITableViewHeaderFooterView *)headerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("headerViewForSection:") public native @Type("UITableViewHeaderFooterView *") UITableViewHeaderFooterView getSectionHeaderView(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForSection:">- (CGRect)rectForSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rectForSection:") public native @Type("CGRect") CGRect getSectionRect(@Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForSelectedRow">- (NSIndexPath *)indexPathForSelectedRow</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indexPathForSelectedRow") public native @Type("NSIndexPath *") NSIndexPath getSelectedRowIndexPath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForSelectedRows">- (NSArray *)indexPathsForSelectedRows</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("indexPathsForSelectedRows") public native @Type("NSArray *") NSArray getSelectedRowsIndexPaths();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/visibleCells">- (NSArray *)visibleCells</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("visibleCells") public native @Type("NSArray *") NSArray getVisibleCells();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForVisibleRows">- (NSArray *)indexPathsForVisibleRows</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indexPathsForVisibleRows") public native @Type("NSArray *") NSArray getVisibleRowsIndexPaths();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertRowsAtIndexPaths:withRowAnimation:">- (void)insertRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void insertRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertSections:withRowAnimation:">- (void)insertSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSections:withRowAnimation:") public native @Type("void") void insertSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveRowAtIndexPath:toIndexPath:">- (void)moveRowAtIndexPath:(NSIndexPath *)indexPath toIndexPath:(NSIndexPath *)newIndexPath</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("moveRowAtIndexPath:toIndexPath:") public native @Type("void") void moveRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("NSIndexPath *") NSIndexPath newIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveSection:toSection:">- (void)moveSection:(NSInteger)section toSection:(NSInteger)newSection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("moveSection:toSection:") public native @Type("void") void moveSection(@Type("NSInteger") int section, @Type("NSInteger") int newSection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forCellReuseIdentifier:">- (void)registerClass:(Class)cellClass forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerClass:forCellReuseIdentifier:") public native @Type("void") void registerReusableCellClass(@Type("Class") ObjCClass cellClass, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forCellReuseIdentifier:">- (void)registerNib:(UINib *)nib forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("registerNib:forCellReuseIdentifier:") public native @Type("void") void registerReusableCellNib(@Type("UINib *") UINib nib, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forHeaderFooterViewReuseIdentifier:">- (void)registerClass:(Class)aClass forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerClass:forHeaderFooterViewReuseIdentifier:") public native @Type("void") void registerReusableHeaderFooterViewClass(@Type("Class") ObjCClass aClass, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forHeaderFooterViewReuseIdentifier:">- (void)registerNib:(UINib *)nib forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("registerNib:forHeaderFooterViewReuseIdentifier:") public native @Type("void") void registerReusableHeaderFooterViewNib(@Type("UINib *") UINib nib, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadData">- (void)reloadData</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reloadData") public native @Type("void") void reloadData();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadRowsAtIndexPaths:withRowAnimation:">- (void)reloadRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("reloadRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void reloadRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSectionIndexTitles">- (void)reloadSectionIndexTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("reloadSectionIndexTitles") public native @Type("void") void reloadSectionIndexTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSections:withRowAnimation:">- (void)reloadSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("reloadSections:withRowAnimation:") public native @Type("void") void reloadSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToNearestSelectedRowAtScrollPosition:animated:">- (void)scrollToNearestSelectedRowAtScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollToNearestSelectedRowAtScrollPosition:animated:") public native @Type("void") void scrollToNearestSelectedRow(@Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToRowAtIndexPath:atScrollPosition:animated:">- (void)scrollToRowAtIndexPath:(NSIndexPath *)indexPath atScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollToRowAtIndexPath:atScrollPosition:animated:") public native @Type("void") void scrollToRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/selectRowAtIndexPath:animated:scrollPosition:">- (void)selectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated scrollPosition:(UITableViewScrollPosition)scrollPosition</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectRowAtIndexPath:animated:scrollPosition:") public native @Type("void") void selectRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated, @Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animate);
    /*</methods>*/

}
