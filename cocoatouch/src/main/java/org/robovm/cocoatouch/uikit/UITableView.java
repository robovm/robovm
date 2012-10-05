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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITableView /*</name>*/.class);

    /*<constructors>*/
    protected UITableView(SkipInit skipInit) { super(skipInit); }
    public UITableView() {}
    
    private static final Selector initWithFrame$style$ = Selector.register("initWithFrame:style:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithFrame(UITableView __self__, Selector __cmd__, CGRect frame, UITableViewStyle style);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/initWithFrame:style:">- (id)initWithFrame:(CGRect)frame style:(UITableViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableView(CGRect frame, UITableViewStyle style) {
        super((SkipInit) null);
        objc_initWithFrame(this, initWithFrame$style$, frame, style);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("allowsMultipleSelection") public native boolean isAllowsMultipleSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAllowsMultipleSelection:") public native void setAllowsMultipleSelection(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("allowsMultipleSelectionDuringEditing") public native boolean isAllowsMultipleSelectionDuringEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAllowsMultipleSelectionDuringEditing:") public native void setAllowsMultipleSelectionDuringEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("allowsSelection") public native boolean isAllowsSelection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setAllowsSelection:") public native void setAllowsSelection(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("allowsSelectionDuringEditing") public native boolean isAllowsSelectionDuringEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAllowsSelectionDuringEditing:") public native void setAllowsSelectionDuringEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("backgroundView") public native UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dataSource") public native UITableViewDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(UITableViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UITableViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UITableViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rowHeight") public native float getRowHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRowHeight:") public native void setRowHeight(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionFooterHeight") public native float getSectionFooterHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionFooterHeight:") public native void setSectionFooterHeight(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionHeaderHeight") public native float getSectionHeaderHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionHeaderHeight:") public native void setSectionHeaderHeight(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("sectionIndexColor") public native UIColor getSectionIndexColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSectionIndexColor:") public native void setSectionIndexColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionIndexMinimumDisplayRowCount") public native int getSectionIndexMinimumDisplayRowCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSectionIndexMinimumDisplayRowCount:") public native void setSectionIndexMinimumDisplayRowCount(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("sectionIndexTrackingBackgroundColor") public native UIColor getSectionIndexTrackingBackgroundColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSectionIndexTrackingBackgroundColor:") public native void setSectionIndexTrackingBackgroundColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("separatorColor") public native UIColor getSeparatorColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSeparatorColor:") public native void setSeparatorColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("separatorStyle") public native UITableViewCellSeparatorStyle getSeparatorStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSeparatorStyle:") public native void setSeparatorStyle(UITableViewCellSeparatorStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/style">@property(nonatomic, readonly) UITableViewStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("style") public native UITableViewStyle getStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableFooterView") public native UIView getTableFooterView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTableFooterView:") public native void setTableFooterView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableHeaderView") public native UIView getTableHeaderView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTableHeaderView:") public native void setTableHeaderView(UIView v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector beginUpdates = Selector.register("beginUpdates");
    @Bridge(symbol = "objc_msgSend") private native static void objc_beginUpdates(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_beginUpdatesSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/beginUpdates">- (void)beginUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginUpdates() {
        if (customClass) { objc_beginUpdatesSuper(getSuper(), this, beginUpdates); } else { objc_beginUpdates(this, beginUpdates); }
    }
    
    private static final Selector deleteRowsAtIndexPaths$withRowAnimation$ = Selector.register("deleteRowsAtIndexPaths:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_deleteRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_deleteRowsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteRowsAtIndexPaths:withRowAnimation:">- (void)deleteRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deleteRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_deleteRowsSuper(getSuper(), this, deleteRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_deleteRows(this, deleteRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector deleteSections$withRowAnimation$ = Selector.register("deleteSections:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_deleteSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_deleteSectionsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteSections:withRowAnimation:">- (void)deleteSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deleteSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_deleteSectionsSuper(getSuper(), this, deleteSections$withRowAnimation$, sections, animation); } else { objc_deleteSections(this, deleteSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector dequeueReusableCellWithIdentifier$forIndexPath$ = Selector.register("dequeueReusableCellWithIdentifier:forIndexPath:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_dequeueReusableCellSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:forIndexPath:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableCell(String identifier, NSIndexPath indexPath) {
        if (customClass) { return objc_dequeueReusableCellSuper(getSuper(), this, dequeueReusableCellWithIdentifier$forIndexPath$, identifier, indexPath); } else { return objc_dequeueReusableCell(this, dequeueReusableCellWithIdentifier$forIndexPath$, identifier, indexPath); }
    }
    
    private static final Selector dequeueReusableCellWithIdentifier$ = Selector.register("dequeueReusableCellWithIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_dequeueReusableCellSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSObject dequeueReusableCell(String identifier) {
        if (customClass) { return objc_dequeueReusableCellSuper(getSuper(), this, dequeueReusableCellWithIdentifier$, identifier); } else { return objc_dequeueReusableCell(this, dequeueReusableCellWithIdentifier$, identifier); }
    }
    
    private static final Selector dequeueReusableHeaderFooterViewWithIdentifier$ = Selector.register("dequeueReusableHeaderFooterViewWithIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_dequeueReusableHeaderFooterView(UITableView __self__, Selector __cmd__, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_dequeueReusableHeaderFooterViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableHeaderFooterViewWithIdentifier:">- (id)dequeueReusableHeaderFooterViewWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableHeaderFooterView(String identifier) {
        if (customClass) { return objc_dequeueReusableHeaderFooterViewSuper(getSuper(), this, dequeueReusableHeaderFooterViewWithIdentifier$, identifier); } else { return objc_dequeueReusableHeaderFooterView(this, dequeueReusableHeaderFooterViewWithIdentifier$, identifier); }
    }
    
    private static final Selector deselectRowAtIndexPath$animated$ = Selector.register("deselectRowAtIndexPath:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_deselectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_deselectRowSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deselectRowAtIndexPath:animated:">- (void)deselectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deselectRow(NSIndexPath indexPath, boolean animated) {
        if (customClass) { objc_deselectRowSuper(getSuper(), this, deselectRowAtIndexPath$animated$, indexPath, animated); } else { objc_deselectRow(this, deselectRowAtIndexPath$animated$, indexPath, animated); }
    }
    
    private static final Selector endUpdates = Selector.register("endUpdates");
    @Bridge(symbol = "objc_msgSend") private native static void objc_endUpdates(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_endUpdatesSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/endUpdates">- (void)endUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endUpdates() {
        if (customClass) { objc_endUpdatesSuper(getSuper(), this, endUpdates); } else { objc_endUpdates(this, endUpdates); }
    }
    
    private static final Selector indexPathForCell$ = Selector.register("indexPathForCell:");
    @Bridge(symbol = "objc_msgSend") private native static NSIndexPath objc_getCellIndexPath(UITableView __self__, Selector __cmd__, UITableViewCell cell);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSIndexPath objc_getCellIndexPathSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UITableViewCell cell);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForCell:">- (NSIndexPath *)indexPathForCell:(UITableViewCell *)cell</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getCellIndexPath(UITableViewCell cell) {
        if (customClass) { return objc_getCellIndexPathSuper(getSuper(), this, indexPathForCell$, cell); } else { return objc_getCellIndexPath(this, indexPathForCell$, cell); }
    }
    
    private static final Selector numberOfRowsInSection$ = Selector.register("numberOfRowsInSection:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getNumberOfRowsInSection(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getNumberOfRowsInSectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfRowsInSection:">- (NSInteger)numberOfRowsInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfRowsInSection(int section) {
        if (customClass) { return objc_getNumberOfRowsInSectionSuper(getSuper(), this, numberOfRowsInSection$, section); } else { return objc_getNumberOfRowsInSection(this, numberOfRowsInSection$, section); }
    }
    
    private static final Selector numberOfSections = Selector.register("numberOfSections");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getNumberOfSections(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getNumberOfSectionsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfSections">- (NSInteger)numberOfSections</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfSections() {
        if (customClass) { return objc_getNumberOfSectionsSuper(getSuper(), this, numberOfSections); } else { return objc_getNumberOfSections(this, numberOfSections); }
    }
    
    private static final Selector cellForRowAtIndexPath$ = Selector.register("cellForRowAtIndexPath:");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCell objc_getRowCell(UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCell objc_getRowCellSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/cellForRowAtIndexPath:">- (UITableViewCell *)cellForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCell getRowCell(NSIndexPath indexPath) {
        if (customClass) { return objc_getRowCellSuper(getSuper(), this, cellForRowAtIndexPath$, indexPath); } else { return objc_getRowCell(this, cellForRowAtIndexPath$, indexPath); }
    }
    
    private static final Selector indexPathForRowAtPoint$ = Selector.register("indexPathForRowAtPoint:");
    @Bridge(symbol = "objc_msgSend") private native static NSIndexPath objc_getRowIndexPath(UITableView __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSIndexPath objc_getRowIndexPathSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForRowAtPoint:">- (NSIndexPath *)indexPathForRowAtPoint:(CGPoint)point</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getRowIndexPath(CGPoint point) {
        if (customClass) { return objc_getRowIndexPathSuper(getSuper(), this, indexPathForRowAtPoint$, point); } else { return objc_getRowIndexPath(this, indexPathForRowAtPoint$, point); }
    }
    
    private static final Selector rectForRowAtIndexPath$ = Selector.register("rectForRowAtIndexPath:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getRowRect(UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getRowRectSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForRowAtIndexPath:">- (CGRect)rectForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getRowRect(NSIndexPath indexPath) {
        if (customClass) { return objc_getRowRectSuper(getSuper(), this, rectForRowAtIndexPath$, indexPath); } else { return objc_getRowRect(this, rectForRowAtIndexPath$, indexPath); }
    }
    
    private static final Selector indexPathsForRowsInRect$ = Selector.register("indexPathsForRowsInRect:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getRowsIndexPaths(UITableView __self__, Selector __cmd__, CGRect rect);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getRowsIndexPathsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForRowsInRect:">- (NSArray *)indexPathsForRowsInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getRowsIndexPaths(CGRect rect) {
        if (customClass) { return objc_getRowsIndexPathsSuper(getSuper(), this, indexPathsForRowsInRect$, rect); } else { return objc_getRowsIndexPaths(this, indexPathsForRowsInRect$, rect); }
    }
    
    private static final Selector rectForFooterInSection$ = Selector.register("rectForFooterInSection:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getSectionFooterRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getSectionFooterRectSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForFooterInSection:">- (CGRect)rectForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionFooterRect(int section) {
        if (customClass) { return objc_getSectionFooterRectSuper(getSuper(), this, rectForFooterInSection$, section); } else { return objc_getSectionFooterRect(this, rectForFooterInSection$, section); }
    }
    
    private static final Selector footerViewForSection$ = Selector.register("footerViewForSection:");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewHeaderFooterView objc_getSectionFooterView(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewHeaderFooterView objc_getSectionFooterViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/footerViewForSection:">- (UITableViewHeaderFooterView *)footerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITableViewHeaderFooterView getSectionFooterView(int section) {
        if (customClass) { return objc_getSectionFooterViewSuper(getSuper(), this, footerViewForSection$, section); } else { return objc_getSectionFooterView(this, footerViewForSection$, section); }
    }
    
    private static final Selector rectForHeaderInSection$ = Selector.register("rectForHeaderInSection:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getSectionHeaderRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getSectionHeaderRectSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForHeaderInSection:">- (CGRect)rectForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionHeaderRect(int section) {
        if (customClass) { return objc_getSectionHeaderRectSuper(getSuper(), this, rectForHeaderInSection$, section); } else { return objc_getSectionHeaderRect(this, rectForHeaderInSection$, section); }
    }
    
    private static final Selector headerViewForSection$ = Selector.register("headerViewForSection:");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewHeaderFooterView objc_getSectionHeaderView(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewHeaderFooterView objc_getSectionHeaderViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/headerViewForSection:">- (UITableViewHeaderFooterView *)headerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITableViewHeaderFooterView getSectionHeaderView(int section) {
        if (customClass) { return objc_getSectionHeaderViewSuper(getSuper(), this, headerViewForSection$, section); } else { return objc_getSectionHeaderView(this, headerViewForSection$, section); }
    }
    
    private static final Selector rectForSection$ = Selector.register("rectForSection:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getSectionRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getSectionRectSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForSection:">- (CGRect)rectForSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionRect(int section) {
        if (customClass) { return objc_getSectionRectSuper(getSuper(), this, rectForSection$, section); } else { return objc_getSectionRect(this, rectForSection$, section); }
    }
    
    private static final Selector indexPathForSelectedRow = Selector.register("indexPathForSelectedRow");
    @Bridge(symbol = "objc_msgSend") private native static NSIndexPath objc_getSelectedRowIndexPath(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSIndexPath objc_getSelectedRowIndexPathSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForSelectedRow">- (NSIndexPath *)indexPathForSelectedRow</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getSelectedRowIndexPath() {
        if (customClass) { return objc_getSelectedRowIndexPathSuper(getSuper(), this, indexPathForSelectedRow); } else { return objc_getSelectedRowIndexPath(this, indexPathForSelectedRow); }
    }
    
    private static final Selector indexPathsForSelectedRows = Selector.register("indexPathsForSelectedRows");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getSelectedRowsIndexPaths(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getSelectedRowsIndexPathsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForSelectedRows">- (NSArray *)indexPathsForSelectedRows</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getSelectedRowsIndexPaths() {
        if (customClass) { return objc_getSelectedRowsIndexPathsSuper(getSuper(), this, indexPathsForSelectedRows); } else { return objc_getSelectedRowsIndexPaths(this, indexPathsForSelectedRows); }
    }
    
    private static final Selector visibleCells = Selector.register("visibleCells");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getVisibleCells(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getVisibleCellsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/visibleCells">- (NSArray *)visibleCells</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getVisibleCells() {
        if (customClass) { return objc_getVisibleCellsSuper(getSuper(), this, visibleCells); } else { return objc_getVisibleCells(this, visibleCells); }
    }
    
    private static final Selector indexPathsForVisibleRows = Selector.register("indexPathsForVisibleRows");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getVisibleRowsIndexPaths(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getVisibleRowsIndexPathsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForVisibleRows">- (NSArray *)indexPathsForVisibleRows</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getVisibleRowsIndexPaths() {
        if (customClass) { return objc_getVisibleRowsIndexPathsSuper(getSuper(), this, indexPathsForVisibleRows); } else { return objc_getVisibleRowsIndexPaths(this, indexPathsForVisibleRows); }
    }
    
    private static final Selector insertRowsAtIndexPaths$withRowAnimation$ = Selector.register("insertRowsAtIndexPaths:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertRowsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertRowsAtIndexPaths:withRowAnimation:">- (void)insertRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_insertRowsSuper(getSuper(), this, insertRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_insertRows(this, insertRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector insertSections$withRowAnimation$ = Selector.register("insertSections:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSectionsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertSections:withRowAnimation:">- (void)insertSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_insertSectionsSuper(getSuper(), this, insertSections$withRowAnimation$, sections, animation); } else { objc_insertSections(this, insertSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector moveRowAtIndexPath$toIndexPath$ = Selector.register("moveRowAtIndexPath:toIndexPath:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_moveRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_moveRowSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveRowAtIndexPath:toIndexPath:">- (void)moveRowAtIndexPath:(NSIndexPath *)indexPath toIndexPath:(NSIndexPath *)newIndexPath</a>
     * @since Available in iOS 5.0 and later.
     */
    public void moveRow(NSIndexPath indexPath, NSIndexPath newIndexPath) {
        if (customClass) { objc_moveRowSuper(getSuper(), this, moveRowAtIndexPath$toIndexPath$, indexPath, newIndexPath); } else { objc_moveRow(this, moveRowAtIndexPath$toIndexPath$, indexPath, newIndexPath); }
    }
    
    private static final Selector moveSection$toSection$ = Selector.register("moveSection:toSection:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_moveSection(UITableView __self__, Selector __cmd__, int section, int newSection);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_moveSectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int section, int newSection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveSection:toSection:">- (void)moveSection:(NSInteger)section toSection:(NSInteger)newSection</a>
     * @since Available in iOS 5.0 and later.
     */
    public void moveSection(int section, int newSection) {
        if (customClass) { objc_moveSectionSuper(getSuper(), this, moveSection$toSection$, section, newSection); } else { objc_moveSection(this, moveSection$toSection$, section, newSection); }
    }
    
    private static final Selector registerClass$forCellReuseIdentifier$ = Selector.register("registerClass:forCellReuseIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_registerReusableCellClass(UITableView __self__, Selector __cmd__, ObjCClass cellClass, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_registerReusableCellClassSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, ObjCClass cellClass, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forCellReuseIdentifier:">- (void)registerClass:(Class)cellClass forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableCellClass(ObjCClass cellClass, String identifier) {
        if (customClass) { objc_registerReusableCellClassSuper(getSuper(), this, registerClass$forCellReuseIdentifier$, cellClass, identifier); } else { objc_registerReusableCellClass(this, registerClass$forCellReuseIdentifier$, cellClass, identifier); }
    }
    
    private static final Selector registerNib$forCellReuseIdentifier$ = Selector.register("registerNib:forCellReuseIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_registerReusableCellNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_registerReusableCellNibSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forCellReuseIdentifier:">- (void)registerNib:(UINib *)nib forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 5.0 and later.
     */
    public void registerReusableCellNib(UINib nib, String identifier) {
        if (customClass) { objc_registerReusableCellNibSuper(getSuper(), this, registerNib$forCellReuseIdentifier$, nib, identifier); } else { objc_registerReusableCellNib(this, registerNib$forCellReuseIdentifier$, nib, identifier); }
    }
    
    private static final Selector registerClass$forHeaderFooterViewReuseIdentifier$ = Selector.register("registerClass:forHeaderFooterViewReuseIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_registerReusableHeaderFooterViewClass(UITableView __self__, Selector __cmd__, ObjCClass aClass, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_registerReusableHeaderFooterViewClassSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, ObjCClass aClass, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forHeaderFooterViewReuseIdentifier:">- (void)registerClass:(Class)aClass forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableHeaderFooterViewClass(ObjCClass aClass, String identifier) {
        if (customClass) { objc_registerReusableHeaderFooterViewClassSuper(getSuper(), this, registerClass$forHeaderFooterViewReuseIdentifier$, aClass, identifier); } else { objc_registerReusableHeaderFooterViewClass(this, registerClass$forHeaderFooterViewReuseIdentifier$, aClass, identifier); }
    }
    
    private static final Selector registerNib$forHeaderFooterViewReuseIdentifier$ = Selector.register("registerNib:forHeaderFooterViewReuseIdentifier:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_registerReusableHeaderFooterViewNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_registerReusableHeaderFooterViewNibSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forHeaderFooterViewReuseIdentifier:">- (void)registerNib:(UINib *)nib forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableHeaderFooterViewNib(UINib nib, String identifier) {
        if (customClass) { objc_registerReusableHeaderFooterViewNibSuper(getSuper(), this, registerNib$forHeaderFooterViewReuseIdentifier$, nib, identifier); } else { objc_registerReusableHeaderFooterViewNib(this, registerNib$forHeaderFooterViewReuseIdentifier$, nib, identifier); }
    }
    
    private static final Selector reloadData = Selector.register("reloadData");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadData(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadDataSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadData">- (void)reloadData</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadData() {
        if (customClass) { objc_reloadDataSuper(getSuper(), this, reloadData); } else { objc_reloadData(this, reloadData); }
    }
    
    private static final Selector reloadRowsAtIndexPaths$withRowAnimation$ = Selector.register("reloadRowsAtIndexPaths:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadRowsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadRowsAtIndexPaths:withRowAnimation:">- (void)reloadRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_reloadRowsSuper(getSuper(), this, reloadRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_reloadRows(this, reloadRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector reloadSectionIndexTitles = Selector.register("reloadSectionIndexTitles");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadSectionIndexTitles(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadSectionIndexTitlesSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSectionIndexTitles">- (void)reloadSectionIndexTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadSectionIndexTitles() {
        if (customClass) { objc_reloadSectionIndexTitlesSuper(getSuper(), this, reloadSectionIndexTitles); } else { objc_reloadSectionIndexTitles(this, reloadSectionIndexTitles); }
    }
    
    private static final Selector reloadSections$withRowAnimation$ = Selector.register("reloadSections:withRowAnimation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadSectionsSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSections:withRowAnimation:">- (void)reloadSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_reloadSectionsSuper(getSuper(), this, reloadSections$withRowAnimation$, sections, animation); } else { objc_reloadSections(this, reloadSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector scrollToNearestSelectedRowAtScrollPosition$animated$ = Selector.register("scrollToNearestSelectedRowAtScrollPosition:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_scrollToNearestSelectedRow(UITableView __self__, Selector __cmd__, UITableViewScrollPosition scrollPosition, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_scrollToNearestSelectedRowSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UITableViewScrollPosition scrollPosition, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToNearestSelectedRowAtScrollPosition:animated:">- (void)scrollToNearestSelectedRowAtScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollToNearestSelectedRow(UITableViewScrollPosition scrollPosition, boolean animated) {
        if (customClass) { objc_scrollToNearestSelectedRowSuper(getSuper(), this, scrollToNearestSelectedRowAtScrollPosition$animated$, scrollPosition, animated); } else { objc_scrollToNearestSelectedRow(this, scrollToNearestSelectedRowAtScrollPosition$animated$, scrollPosition, animated); }
    }
    
    private static final Selector scrollToRowAtIndexPath$atScrollPosition$animated$ = Selector.register("scrollToRowAtIndexPath:atScrollPosition:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_scrollToRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_scrollToRowSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToRowAtIndexPath:atScrollPosition:animated:">- (void)scrollToRowAtIndexPath:(NSIndexPath *)indexPath atScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollToRow(NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated) {
        if (customClass) { objc_scrollToRowSuper(getSuper(), this, scrollToRowAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); } else { objc_scrollToRow(this, scrollToRowAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); }
    }
    
    private static final Selector selectRowAtIndexPath$animated$scrollPosition$ = Selector.register("selectRowAtIndexPath:animated:scrollPosition:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_selectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_selectRowSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/selectRowAtIndexPath:animated:scrollPosition:">- (void)selectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated scrollPosition:(UITableViewScrollPosition)scrollPosition</a>
     * @since Available in iOS 2.0 and later.
     */
    public void selectRow(NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition) {
        if (customClass) { objc_selectRowSuper(getSuper(), this, selectRowAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); } else { objc_selectRow(this, selectRowAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); }
    }
    
    private static final Selector setEditing$animated$ = Selector.register("setEditing:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditing(UITableView __self__, Selector __cmd__, boolean editing, boolean animate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean editing, boolean animate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing, boolean animate) {
        if (customClass) { objc_setEditingSuper(getSuper(), this, setEditing$animated$, editing, animate); } else { objc_setEditing(this, setEditing$animated$, editing, animate); }
    }
    /*</methods>*/

}
