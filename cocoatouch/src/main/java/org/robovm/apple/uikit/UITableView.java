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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITableView/*</name>*/ 
    extends /*<extends>*/UIScrollView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UITableViewPtr extends Ptr<UITableView, UITableViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITableView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITableView() {}
    protected UITableView(SkipInit skipInit) { super(skipInit); }
    public UITableView(@ByVal CGRect frame, UITableViewStyle style) { super((SkipInit) null); initObject(initWithFrame$style$(frame, style)); }
    /*</constructors>*/
    
    public UITableView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "style")
    public native UITableViewStyle getStyle();
    @Property(selector = "dataSource")
    public native UITableViewDataSource getDataSource();
    @Property(selector = "setDataSource:", strongRef = true)
    public native void setDataSource(UITableViewDataSource v);
    @Property(selector = "delegate")
    public native UITableViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UITableViewDelegate v);
    @Property(selector = "rowHeight")
    public native @MachineSizedFloat double getRowHeight();
    @Property(selector = "setRowHeight:")
    public native void setRowHeight(@MachineSizedFloat double v);
    @Property(selector = "sectionHeaderHeight")
    public native @MachineSizedFloat double getSectionHeaderHeight();
    @Property(selector = "setSectionHeaderHeight:")
    public native void setSectionHeaderHeight(@MachineSizedFloat double v);
    @Property(selector = "sectionFooterHeight")
    public native @MachineSizedFloat double getSectionFooterHeight();
    @Property(selector = "setSectionFooterHeight:")
    public native void setSectionFooterHeight(@MachineSizedFloat double v);
    @Property(selector = "estimatedRowHeight")
    public native @MachineSizedFloat double getEstimatedRowHeight();
    @Property(selector = "setEstimatedRowHeight:")
    public native void setEstimatedRowHeight(@MachineSizedFloat double v);
    @Property(selector = "estimatedSectionHeaderHeight")
    public native @MachineSizedFloat double getEstimatedSectionHeaderHeight();
    @Property(selector = "setEstimatedSectionHeaderHeight:")
    public native void setEstimatedSectionHeaderHeight(@MachineSizedFloat double v);
    @Property(selector = "estimatedSectionFooterHeight")
    public native @MachineSizedFloat double getEstimatedSectionFooterHeight();
    @Property(selector = "setEstimatedSectionFooterHeight:")
    public native void setEstimatedSectionFooterHeight(@MachineSizedFloat double v);
    @Property(selector = "separatorInset")
    public native @ByVal UIEdgeInsets getSeparatorInset();
    @Property(selector = "setSeparatorInset:")
    public native void setSeparatorInset(@ByVal UIEdgeInsets v);
    @Property(selector = "backgroundView")
    public native UIView getBackgroundView();
    @Property(selector = "setBackgroundView:")
    public native void setBackgroundView(UIView v);
    @Property(selector = "isEditing")
    public native boolean isEditing();
    @Property(selector = "setEditing:")
    public native void setEditing(boolean v);
    @Property(selector = "allowsSelection")
    public native boolean isAllowsSelection();
    @Property(selector = "setAllowsSelection:")
    public native void setAllowsSelection(boolean v);
    @Property(selector = "allowsSelectionDuringEditing")
    public native boolean isAllowsSelectionDuringEditing();
    @Property(selector = "setAllowsSelectionDuringEditing:")
    public native void setAllowsSelectionDuringEditing(boolean v);
    @Property(selector = "allowsMultipleSelection")
    public native boolean isAllowsMultipleSelection();
    @Property(selector = "setAllowsMultipleSelection:")
    public native void setAllowsMultipleSelection(boolean v);
    @Property(selector = "allowsMultipleSelectionDuringEditing")
    public native boolean isAllowsMultipleSelectionDuringEditing();
    @Property(selector = "setAllowsMultipleSelectionDuringEditing:")
    public native void setAllowsMultipleSelectionDuringEditing(boolean v);
    @Property(selector = "sectionIndexMinimumDisplayRowCount")
    public native @MachineSizedSInt long getSectionIndexMinimumDisplayRowCount();
    @Property(selector = "setSectionIndexMinimumDisplayRowCount:")
    public native void setSectionIndexMinimumDisplayRowCount(@MachineSizedSInt long v);
    @Property(selector = "sectionIndexColor")
    public native UIColor getSectionIndexColor();
    @Property(selector = "setSectionIndexColor:")
    public native void setSectionIndexColor(UIColor v);
    @Property(selector = "sectionIndexBackgroundColor")
    public native UIColor getSectionIndexBackgroundColor();
    @Property(selector = "setSectionIndexBackgroundColor:")
    public native void setSectionIndexBackgroundColor(UIColor v);
    @Property(selector = "sectionIndexTrackingBackgroundColor")
    public native UIColor getSectionIndexTrackingBackgroundColor();
    @Property(selector = "setSectionIndexTrackingBackgroundColor:")
    public native void setSectionIndexTrackingBackgroundColor(UIColor v);
    @Property(selector = "separatorStyle")
    public native UITableViewCellSeparatorStyle getSeparatorStyle();
    @Property(selector = "setSeparatorStyle:")
    public native void setSeparatorStyle(UITableViewCellSeparatorStyle v);
    @Property(selector = "separatorColor")
    public native UIColor getSeparatorColor();
    @Property(selector = "setSeparatorColor:")
    public native void setSeparatorColor(UIColor v);
    @Property(selector = "tableHeaderView")
    public native UIView getTableHeaderView();
    @Property(selector = "setTableHeaderView:")
    public native void setTableHeaderView(UIView v);
    @Property(selector = "tableFooterView")
    public native UIView getTableFooterView();
    @Property(selector = "setTableFooterView:")
    public native void setTableFooterView(UIView v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:style:")
    protected native @Pointer long initWithFrame$style$(@ByVal CGRect frame, UITableViewStyle style);
    @Method(selector = "reloadData")
    public native void reloadData();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "reloadSectionIndexTitles")
    public native void reloadSectionIndexTitles();
    @Method(selector = "numberOfSections")
    public native @MachineSizedSInt long getNumberOfSections();
    @Method(selector = "numberOfRowsInSection:")
    public native @MachineSizedSInt long getNumberOfRowsInSection(@MachineSizedSInt long section);
    @Method(selector = "rectForSection:")
    public native @ByVal CGRect getRectForSection(@MachineSizedSInt long section);
    @Method(selector = "rectForHeaderInSection:")
    public native @ByVal CGRect getRectForHeaderInSection(@MachineSizedSInt long section);
    @Method(selector = "rectForFooterInSection:")
    public native @ByVal CGRect getRectForFooterInSection(@MachineSizedSInt long section);
    @Method(selector = "rectForRowAtIndexPath:")
    public native @ByVal CGRect getRectForRow(NSIndexPath indexPath);
    @Method(selector = "indexPathForRowAtPoint:")
    public native NSIndexPath getIndexPathForRow(@ByVal CGPoint point);
    @Method(selector = "indexPathForCell:")
    public native NSIndexPath getIndexPathForCell(UITableViewCell cell);
    @Method(selector = "indexPathsForRowsInRect:")
    public native NSArray<NSIndexPath> getIndexPathsForRowsInRect(@ByVal CGRect rect);
    @Method(selector = "cellForRowAtIndexPath:")
    public native UITableViewCell getCellForRow(NSIndexPath indexPath);
    @Method(selector = "visibleCells")
    public native NSArray<UITableViewCell> getVisibleCells();
    @Method(selector = "indexPathsForVisibleRows")
    public native NSArray<NSIndexPath> getIndexPathsForVisibleRows();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "headerViewForSection:")
    public native UITableViewHeaderFooterView getHeaderViewForSection(@MachineSizedSInt long section);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "footerViewForSection:")
    public native UITableViewHeaderFooterView getFooterViewForSection(@MachineSizedSInt long section);
    @Method(selector = "scrollToRowAtIndexPath:atScrollPosition:animated:")
    public native void scrollToRow(NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated);
    @Method(selector = "scrollToNearestSelectedRowAtScrollPosition:animated:")
    public native void scrollToNearestSelectedRow(UITableViewScrollPosition scrollPosition, boolean animated);
    @Method(selector = "beginUpdates")
    public native void beginUpdates();
    @Method(selector = "endUpdates")
    public native void endUpdates();
    @Method(selector = "insertSections:withRowAnimation:")
    public native void insertSections(NSIndexSet sections, UITableViewRowAnimation animation);
    @Method(selector = "deleteSections:withRowAnimation:")
    public native void deleteSections(NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "reloadSections:withRowAnimation:")
    public native void reloadSections(NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "moveSection:toSection:")
    public native void moveSection(@MachineSizedSInt long section, @MachineSizedSInt long newSection);
    @Method(selector = "insertRowsAtIndexPaths:withRowAnimation:")
    public native void insertRows(NSArray<NSIndexPath> indexPaths, UITableViewRowAnimation animation);
    @Method(selector = "deleteRowsAtIndexPaths:withRowAnimation:")
    public native void deleteRows(NSArray<NSIndexPath> indexPaths, UITableViewRowAnimation animation);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "reloadRowsAtIndexPaths:withRowAnimation:")
    public native void reloadRows(NSArray<NSIndexPath> indexPaths, UITableViewRowAnimation animation);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "moveRowAtIndexPath:toIndexPath:")
    public native void moveRow(NSIndexPath indexPath, NSIndexPath newIndexPath);
    @Method(selector = "setEditing:animated:")
    public native void setEditing(boolean editing, boolean animated);
    @Method(selector = "indexPathForSelectedRow")
    public native NSIndexPath getIndexPathForSelectedRow();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "indexPathsForSelectedRows")
    public native NSArray<NSIndexPath> getIndexPathsForSelectedRows();
    @Method(selector = "selectRowAtIndexPath:animated:scrollPosition:")
    public native void selectRow(NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition);
    @Method(selector = "deselectRowAtIndexPath:animated:")
    public native void deselectRow(NSIndexPath indexPath, boolean animated);
    @Method(selector = "dequeueReusableCellWithIdentifier:")
    public native NSObject dequeueReusableCell(String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "dequeueReusableCellWithIdentifier:forIndexPath:")
    public native NSObject dequeueReusableCell(String identifier, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "dequeueReusableHeaderFooterViewWithIdentifier:")
    public native NSObject dequeueReusableHeaderFooterView(String identifier);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "registerNib:forCellReuseIdentifier:")
    public native void registerReusableCellNib(UINib nib, String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "registerClass:forCellReuseIdentifier:")
    public native void registerReusableCellClass(ObjCClass cellClass, String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "registerNib:forHeaderFooterViewReuseIdentifier:")
    public native void registerReusableHeaderFooterViewNib(UINib nib, String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "registerClass:forHeaderFooterViewReuseIdentifier:")
    public native void registerReusableHeaderFooterViewClass(ObjCClass aClass, String identifier);
    /*</methods>*/
}
