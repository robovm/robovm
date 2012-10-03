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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableView /*</name>*/.class);
    }

    /*<constructors>*/
    public UITableView() {}
    @Bind("initWithFrame:style:") public UITableView(@Type("CGRect") CGRect frame, @Type("UITableViewStyle") UITableViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("allowsMultipleSelection") public native @Type("BOOL") boolean isAllowsMultipleSelection();
    @Bind("setAllowsMultipleSelection:") public native void setAllowsMultipleSelection(@Type("BOOL") boolean v);
    @Bind("allowsMultipleSelectionDuringEditing") public native @Type("BOOL") boolean isAllowsMultipleSelectionDuringEditing();
    @Bind("setAllowsMultipleSelectionDuringEditing:") public native void setAllowsMultipleSelectionDuringEditing(@Type("BOOL") boolean v);
    @Bind("allowsSelection") public native @Type("BOOL") boolean isAllowsSelection();
    @Bind("setAllowsSelection:") public native void setAllowsSelection(@Type("BOOL") boolean v);
    @Bind("allowsSelectionDuringEditing") public native @Type("BOOL") boolean isAllowsSelectionDuringEditing();
    @Bind("setAllowsSelectionDuringEditing:") public native void setAllowsSelectionDuringEditing(@Type("BOOL") boolean v);
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    @Bind("dataSource") public native @Type("id<UITableViewDataSource>") UITableViewDataSource getDataSource();
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UITableViewDataSource>") UITableViewDataSource v);
    @Bind("delegate") public native @Type("id<UITableViewDelegate>") UITableViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITableViewDelegate>") UITableViewDelegate v);
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    @Bind("rowHeight") public native @Type("CGFloat") float getRowHeight();
    @Bind("setRowHeight:") public native void setRowHeight(@Type("CGFloat") float v);
    @Bind("sectionFooterHeight") public native @Type("CGFloat") float getSectionFooterHeight();
    @Bind("setSectionFooterHeight:") public native void setSectionFooterHeight(@Type("CGFloat") float v);
    @Bind("sectionHeaderHeight") public native @Type("CGFloat") float getSectionHeaderHeight();
    @Bind("setSectionHeaderHeight:") public native void setSectionHeaderHeight(@Type("CGFloat") float v);
    @Bind("sectionIndexColor") public native @Type("UIColor *") UIColor getSectionIndexColor();
    @Bind("setSectionIndexColor:") public native void setSectionIndexColor(@Type("UIColor *") UIColor v);
    @Bind("sectionIndexMinimumDisplayRowCount") public native @Type("NSInteger") int getSectionIndexMinimumDisplayRowCount();
    @Bind("setSectionIndexMinimumDisplayRowCount:") public native void setSectionIndexMinimumDisplayRowCount(@Type("NSInteger") int v);
    @Bind("sectionIndexTrackingBackgroundColor") public native @Type("UIColor *") UIColor getSectionIndexTrackingBackgroundColor();
    @Bind("setSectionIndexTrackingBackgroundColor:") public native void setSectionIndexTrackingBackgroundColor(@Type("UIColor *") UIColor v);
    @Bind("separatorColor") public native @Type("UIColor *") UIColor getSeparatorColor();
    @Bind("setSeparatorColor:") public native void setSeparatorColor(@Type("UIColor *") UIColor v);
    @Bind("separatorStyle") public native @Type("UITableViewCellSeparatorStyle") UITableViewCellSeparatorStyle getSeparatorStyle();
    @Bind("setSeparatorStyle:") public native void setSeparatorStyle(@Type("UITableViewCellSeparatorStyle") UITableViewCellSeparatorStyle v);
    @Bind("style") public native @Type("UITableViewStyle") UITableViewStyle getStyle();
    @Bind("tableFooterView") public native @Type("UIView *") UIView getTableFooterView();
    @Bind("setTableFooterView:") public native void setTableFooterView(@Type("UIView *") UIView v);
    @Bind("tableHeaderView") public native @Type("UIView *") UIView getTableHeaderView();
    @Bind("setTableHeaderView:") public native void setTableHeaderView(@Type("UIView *") UIView v);
    /*</properties>*/
    /*<methods>*/
    @Bind("beginUpdates") public native @Type("void") void beginUpdates();
    @Bind("deleteRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void deleteRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("deleteSections:withRowAnimation:") public native @Type("void") void deleteSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("dequeueReusableCellWithIdentifier:") public native @Type("id") NSObject dequeueReusableCell(@Type("NSString *") String identifier);
    @Bind("dequeueReusableCellWithIdentifier:forIndexPath:") public native @Type("id") NSObject dequeueReusableCellWithIdentifier(@Type("NSString *") String identifier, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("dequeueReusableHeaderFooterViewWithIdentifier:") public native @Type("id") NSObject dequeueReusableHeaderFooterViewWithIdentifier(@Type("NSString *") String identifier);
    @Bind("deselectRowAtIndexPath:animated:") public native @Type("void") void deselectRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated);
    @Bind("endUpdates") public native @Type("void") void endUpdates();
    @Bind("footerViewForSection:") public native @Type("UITableViewHeaderFooterView *") UITableViewHeaderFooterView footerViewForSection(@Type("NSInteger") int section);
    @Bind("indexPathsForRowsInRect:") public native @Type("NSArray *") NSArray getIndexPath(@Type("CGRect") CGRect rect);
    @Bind("indexPathForRowAtPoint:") public native @Type("NSIndexPath *") NSIndexPath getIndexPath(@Type("CGPoint") CGPoint point);
    @Bind("indexPathForCell:") public native @Type("NSIndexPath *") NSIndexPath getIndexPath(@Type("UITableViewCell *") UITableViewCell cell);
    @Bind("numberOfRowsInSection:") public native @Type("NSInteger") int getNumberOfRowsInSection(@Type("NSInteger") int section);
    @Bind("numberOfSections") public native @Type("NSInteger") int getNumberOfSections();
    @Bind("cellForRowAtIndexPath:") public native @Type("UITableViewCell *") UITableViewCell getRowCell(@Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("rectForRowAtIndexPath:") public native @Type("CGRect") CGRect getRowRect(@Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("rectForFooterInSection:") public native @Type("CGRect") CGRect getSectionFooterRect(@Type("NSInteger") int section);
    @Bind("rectForHeaderInSection:") public native @Type("CGRect") CGRect getSectionHeaderRect(@Type("NSInteger") int section);
    @Bind("rectForSection:") public native @Type("CGRect") CGRect getSectionRect(@Type("NSInteger") int section);
    @Bind("indexPathForSelectedRow") public native @Type("NSIndexPath *") NSIndexPath getSelectedRowIndexPath();
    @Bind("indexPathsForSelectedRows") public native @Type("NSArray *") NSArray getSelectedRowsIndexPaths();
    @Bind("visibleCells") public native @Type("NSArray *") NSArray getVisibleCells();
    @Bind("indexPathsForVisibleRows") public native @Type("NSArray *") NSArray getVisibleRowsIndexPaths();
    @Bind("headerViewForSection:") public native @Type("UITableViewHeaderFooterView *") UITableViewHeaderFooterView headerViewForSection(@Type("NSInteger") int section);
    @Bind("insertRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void insertRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("insertSections:withRowAnimation:") public native @Type("void") void insertSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("moveRowAtIndexPath:toIndexPath:") public native @Type("void") void moveRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("NSIndexPath *") NSIndexPath newIndexPath);
    @Bind("moveSection:toSection:") public native @Type("void") void moveSection(@Type("NSInteger") int section, @Type("NSInteger") int newSection);
    @Bind("registerClass:forCellReuseIdentifier:") public native @Type("void") void registerReusableCellClass(@Type("Class") ObjCClass cellClass, @Type("NSString *") String identifier);
    @Bind("registerNib:forCellReuseIdentifier:") public native @Type("void") void registerReusableCellNib(@Type("UINib *") UINib nib, @Type("NSString *") String identifier);
    @Bind("registerClass:forHeaderFooterViewReuseIdentifier:") public native @Type("void") void registerReusableHeaderFooterViewClass(@Type("Class") ObjCClass aClass, @Type("NSString *") String identifier);
    @Bind("registerNib:forHeaderFooterViewReuseIdentifier:") public native @Type("void") void registerReusableHeaderFooterViewNib(@Type("UINib *") UINib nib, @Type("NSString *") String identifier);
    @Bind("reloadData") public native @Type("void") void reloadData();
    @Bind("reloadRowsAtIndexPaths:withRowAnimation:") public native @Type("void") void reloadRows(@Type("NSArray *") NSArray indexPaths, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("reloadSectionIndexTitles") public native @Type("void") void reloadSectionIndexTitles();
    @Bind("reloadSections:withRowAnimation:") public native @Type("void") void reloadSections(@Type("NSIndexSet *") NSIndexSet sections, @Type("UITableViewRowAnimation") UITableViewRowAnimation animation);
    @Bind("scrollToNearestSelectedRowAtScrollPosition:animated:") public native @Type("void") void scrollToNearestSelectedRow(@Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition, @Type("BOOL") boolean animated);
    @Bind("scrollToRowAtIndexPath:atScrollPosition:animated:") public native @Type("void") void scrollToRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition, @Type("BOOL") boolean animated);
    @Bind("selectRowAtIndexPath:animated:scrollPosition:") public native @Type("void") void selectRow(@Type("NSIndexPath *") NSIndexPath indexPath, @Type("BOOL") boolean animated, @Type("UITableViewScrollPosition") UITableViewScrollPosition scrollPosition);
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animate);
    /*</methods>*/

}
