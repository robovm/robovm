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
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html">UITableView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITableView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITableView /*</name>*/.class);

    public UITableView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UITableView(SkipInit skipInit) { super(skipInit); }
    public UITableView() {}
    
    private static final Selector initWithFrame$style$ = Selector.register("initWithFrame:style:");
    @Bridge private native static @Pointer long objc_initWithFrame(UITableView __self__, Selector __cmd__, @ByVal CGRect frame, UITableViewStyle style);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/initWithFrame:style:">- (id)initWithFrame:(CGRect)frame style:(UITableViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableView(CGRect frame, UITableViewStyle style) {
        super((SkipInit) null);
        initObject(objc_initWithFrame(this, initWithFrame$style$, frame, style));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector allowsMultipleSelection = Selector.register("allowsMultipleSelection");
    @Bridge private native static boolean objc_isAllowsMultipleSelection(UITableView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsMultipleSelectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAllowsMultipleSelection() {
        if (customClass) { return objc_isAllowsMultipleSelectionSuper(getSuper(), allowsMultipleSelection); } else { return objc_isAllowsMultipleSelection(this, allowsMultipleSelection); }
    }
    
    private static final Selector setAllowsMultipleSelection$ = Selector.register("setAllowsMultipleSelection:");
    @Bridge private native static void objc_setAllowsMultipleSelection(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelection);
    @Bridge private native static void objc_setAllowsMultipleSelectionSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsMultipleSelection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAllowsMultipleSelection(boolean allowsMultipleSelection) {
        if (customClass) { objc_setAllowsMultipleSelectionSuper(getSuper(), setAllowsMultipleSelection$, allowsMultipleSelection); } else { objc_setAllowsMultipleSelection(this, setAllowsMultipleSelection$, allowsMultipleSelection); }
    }
    
    private static final Selector allowsMultipleSelectionDuringEditing = Selector.register("allowsMultipleSelectionDuringEditing");
    @Bridge private native static boolean objc_isAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsMultipleSelectionDuringEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAllowsMultipleSelectionDuringEditing() {
        if (customClass) { return objc_isAllowsMultipleSelectionDuringEditingSuper(getSuper(), allowsMultipleSelectionDuringEditing); } else { return objc_isAllowsMultipleSelectionDuringEditing(this, allowsMultipleSelectionDuringEditing); }
    }
    
    private static final Selector setAllowsMultipleSelectionDuringEditing$ = Selector.register("setAllowsMultipleSelectionDuringEditing:");
    @Bridge private native static void objc_setAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelectionDuringEditing);
    @Bridge private native static void objc_setAllowsMultipleSelectionDuringEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsMultipleSelectionDuringEditing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAllowsMultipleSelectionDuringEditing(boolean allowsMultipleSelectionDuringEditing) {
        if (customClass) { objc_setAllowsMultipleSelectionDuringEditingSuper(getSuper(), setAllowsMultipleSelectionDuringEditing$, allowsMultipleSelectionDuringEditing); } else { objc_setAllowsMultipleSelectionDuringEditing(this, setAllowsMultipleSelectionDuringEditing$, allowsMultipleSelectionDuringEditing); }
    }
    
    private static final Selector allowsSelection = Selector.register("allowsSelection");
    @Bridge private native static boolean objc_isAllowsSelection(UITableView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsSelectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isAllowsSelection() {
        if (customClass) { return objc_isAllowsSelectionSuper(getSuper(), allowsSelection); } else { return objc_isAllowsSelection(this, allowsSelection); }
    }
    
    private static final Selector setAllowsSelection$ = Selector.register("setAllowsSelection:");
    @Bridge private native static void objc_setAllowsSelection(UITableView __self__, Selector __cmd__, boolean allowsSelection);
    @Bridge private native static void objc_setAllowsSelectionSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsSelection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setAllowsSelection(boolean allowsSelection) {
        if (customClass) { objc_setAllowsSelectionSuper(getSuper(), setAllowsSelection$, allowsSelection); } else { objc_setAllowsSelection(this, setAllowsSelection$, allowsSelection); }
    }
    
    private static final Selector allowsSelectionDuringEditing = Selector.register("allowsSelectionDuringEditing");
    @Bridge private native static boolean objc_isAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsSelectionDuringEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAllowsSelectionDuringEditing() {
        if (customClass) { return objc_isAllowsSelectionDuringEditingSuper(getSuper(), allowsSelectionDuringEditing); } else { return objc_isAllowsSelectionDuringEditing(this, allowsSelectionDuringEditing); }
    }
    
    private static final Selector setAllowsSelectionDuringEditing$ = Selector.register("setAllowsSelectionDuringEditing:");
    @Bridge private native static void objc_setAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsSelectionDuringEditing);
    @Bridge private native static void objc_setAllowsSelectionDuringEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsSelectionDuringEditing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAllowsSelectionDuringEditing(boolean allowsSelectionDuringEditing) {
        if (customClass) { objc_setAllowsSelectionDuringEditingSuper(getSuper(), setAllowsSelectionDuringEditing$, allowsSelectionDuringEditing); } else { objc_setAllowsSelectionDuringEditing(this, setAllowsSelectionDuringEditing$, allowsSelectionDuringEditing); }
    }
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge private native static UIView objc_getBackgroundView(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge private native static void objc_setBackgroundView(UITableView __self__, Selector __cmd__, UIView backgroundView);
    @Bridge private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector dataSource = Selector.register("dataSource");
    @Bridge private native static UITableViewDataSource objc_getDataSource(UITableView __self__, Selector __cmd__);
    @Bridge private native static UITableViewDataSource objc_getDataSourceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewDataSource getDataSource() {
        if (customClass) { return objc_getDataSourceSuper(getSuper(), dataSource); } else { return objc_getDataSource(this, dataSource); }
    }
    
    private static final Selector setDataSource$ = Selector.register("setDataSource:");
    @Bridge private native static void objc_setDataSource(UITableView __self__, Selector __cmd__, UITableViewDataSource dataSource);
    @Bridge private native static void objc_setDataSourceSuper(ObjCSuper __super__, Selector __cmd__, UITableViewDataSource dataSource);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDataSource(UITableViewDataSource dataSource) {
        if (customClass) { objc_setDataSourceSuper(getSuper(), setDataSource$, dataSource); } else { objc_setDataSource(this, setDataSource$, dataSource); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UITableViewDelegate objc_getDelegate(UITableView __self__, Selector __cmd__);
    @Bridge private native static UITableViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UITableView __self__, Selector __cmd__, UITableViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UITableViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UITableViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isEditing = Selector.register("isEditing");
    @Bridge private native static boolean objc_isEditing(UITableView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditing() {
        if (customClass) { return objc_isEditingSuper(getSuper(), isEditing); } else { return objc_isEditing(this, isEditing); }
    }
    
    private static final Selector setEditing$ = Selector.register("setEditing:");
    @Bridge private native static void objc_setEditing(UITableView __self__, Selector __cmd__, boolean editing);
    @Bridge private native static void objc_setEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean editing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing) {
        if (customClass) { objc_setEditingSuper(getSuper(), setEditing$, editing); } else { objc_setEditing(this, setEditing$, editing); }
    }
    
    private static final Selector rowHeight = Selector.register("rowHeight");
    @Bridge private native static float objc_getRowHeight(UITableView __self__, Selector __cmd__);
    @Bridge private native static float objc_getRowHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getRowHeight() {
        if (customClass) { return objc_getRowHeightSuper(getSuper(), rowHeight); } else { return objc_getRowHeight(this, rowHeight); }
    }
    
    private static final Selector setRowHeight$ = Selector.register("setRowHeight:");
    @Bridge private native static void objc_setRowHeight(UITableView __self__, Selector __cmd__, float rowHeight);
    @Bridge private native static void objc_setRowHeightSuper(ObjCSuper __super__, Selector __cmd__, float rowHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRowHeight(float rowHeight) {
        if (customClass) { objc_setRowHeightSuper(getSuper(), setRowHeight$, rowHeight); } else { objc_setRowHeight(this, setRowHeight$, rowHeight); }
    }
    
    private static final Selector sectionFooterHeight = Selector.register("sectionFooterHeight");
    @Bridge private native static float objc_getSectionFooterHeight(UITableView __self__, Selector __cmd__);
    @Bridge private native static float objc_getSectionFooterHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSectionFooterHeight() {
        if (customClass) { return objc_getSectionFooterHeightSuper(getSuper(), sectionFooterHeight); } else { return objc_getSectionFooterHeight(this, sectionFooterHeight); }
    }
    
    private static final Selector setSectionFooterHeight$ = Selector.register("setSectionFooterHeight:");
    @Bridge private native static void objc_setSectionFooterHeight(UITableView __self__, Selector __cmd__, float sectionFooterHeight);
    @Bridge private native static void objc_setSectionFooterHeightSuper(ObjCSuper __super__, Selector __cmd__, float sectionFooterHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionFooterHeight(float sectionFooterHeight) {
        if (customClass) { objc_setSectionFooterHeightSuper(getSuper(), setSectionFooterHeight$, sectionFooterHeight); } else { objc_setSectionFooterHeight(this, setSectionFooterHeight$, sectionFooterHeight); }
    }
    
    private static final Selector sectionHeaderHeight = Selector.register("sectionHeaderHeight");
    @Bridge private native static float objc_getSectionHeaderHeight(UITableView __self__, Selector __cmd__);
    @Bridge private native static float objc_getSectionHeaderHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSectionHeaderHeight() {
        if (customClass) { return objc_getSectionHeaderHeightSuper(getSuper(), sectionHeaderHeight); } else { return objc_getSectionHeaderHeight(this, sectionHeaderHeight); }
    }
    
    private static final Selector setSectionHeaderHeight$ = Selector.register("setSectionHeaderHeight:");
    @Bridge private native static void objc_setSectionHeaderHeight(UITableView __self__, Selector __cmd__, float sectionHeaderHeight);
    @Bridge private native static void objc_setSectionHeaderHeightSuper(ObjCSuper __super__, Selector __cmd__, float sectionHeaderHeight);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionHeaderHeight(float sectionHeaderHeight) {
        if (customClass) { objc_setSectionHeaderHeightSuper(getSuper(), setSectionHeaderHeight$, sectionHeaderHeight); } else { objc_setSectionHeaderHeight(this, setSectionHeaderHeight$, sectionHeaderHeight); }
    }
    
    private static final Selector sectionIndexColor = Selector.register("sectionIndexColor");
    @Bridge private native static UIColor objc_getSectionIndexColor(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getSectionIndexColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getSectionIndexColor() {
        if (customClass) { return objc_getSectionIndexColorSuper(getSuper(), sectionIndexColor); } else { return objc_getSectionIndexColor(this, sectionIndexColor); }
    }
    
    private static final Selector setSectionIndexColor$ = Selector.register("setSectionIndexColor:");
    @Bridge private native static void objc_setSectionIndexColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexColor);
    @Bridge private native static void objc_setSectionIndexColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor sectionIndexColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSectionIndexColor(UIColor sectionIndexColor) {
        if (customClass) { objc_setSectionIndexColorSuper(getSuper(), setSectionIndexColor$, sectionIndexColor); } else { objc_setSectionIndexColor(this, setSectionIndexColor$, sectionIndexColor); }
    }
    
    private static final Selector sectionIndexMinimumDisplayRowCount = Selector.register("sectionIndexMinimumDisplayRowCount");
    @Bridge private native static int objc_getSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__);
    @Bridge private native static int objc_getSectionIndexMinimumDisplayRowCountSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getSectionIndexMinimumDisplayRowCount() {
        if (customClass) { return objc_getSectionIndexMinimumDisplayRowCountSuper(getSuper(), sectionIndexMinimumDisplayRowCount); } else { return objc_getSectionIndexMinimumDisplayRowCount(this, sectionIndexMinimumDisplayRowCount); }
    }
    
    private static final Selector setSectionIndexMinimumDisplayRowCount$ = Selector.register("setSectionIndexMinimumDisplayRowCount:");
    @Bridge private native static void objc_setSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__, int sectionIndexMinimumDisplayRowCount);
    @Bridge private native static void objc_setSectionIndexMinimumDisplayRowCountSuper(ObjCSuper __super__, Selector __cmd__, int sectionIndexMinimumDisplayRowCount);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionIndexMinimumDisplayRowCount(int sectionIndexMinimumDisplayRowCount) {
        if (customClass) { objc_setSectionIndexMinimumDisplayRowCountSuper(getSuper(), setSectionIndexMinimumDisplayRowCount$, sectionIndexMinimumDisplayRowCount); } else { objc_setSectionIndexMinimumDisplayRowCount(this, setSectionIndexMinimumDisplayRowCount$, sectionIndexMinimumDisplayRowCount); }
    }
    
    private static final Selector sectionIndexTrackingBackgroundColor = Selector.register("sectionIndexTrackingBackgroundColor");
    @Bridge private native static UIColor objc_getSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getSectionIndexTrackingBackgroundColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getSectionIndexTrackingBackgroundColor() {
        if (customClass) { return objc_getSectionIndexTrackingBackgroundColorSuper(getSuper(), sectionIndexTrackingBackgroundColor); } else { return objc_getSectionIndexTrackingBackgroundColor(this, sectionIndexTrackingBackgroundColor); }
    }
    
    private static final Selector setSectionIndexTrackingBackgroundColor$ = Selector.register("setSectionIndexTrackingBackgroundColor:");
    @Bridge private native static void objc_setSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexTrackingBackgroundColor);
    @Bridge private native static void objc_setSectionIndexTrackingBackgroundColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor sectionIndexTrackingBackgroundColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSectionIndexTrackingBackgroundColor(UIColor sectionIndexTrackingBackgroundColor) {
        if (customClass) { objc_setSectionIndexTrackingBackgroundColorSuper(getSuper(), setSectionIndexTrackingBackgroundColor$, sectionIndexTrackingBackgroundColor); } else { objc_setSectionIndexTrackingBackgroundColor(this, setSectionIndexTrackingBackgroundColor$, sectionIndexTrackingBackgroundColor); }
    }
    
    private static final Selector separatorColor = Selector.register("separatorColor");
    @Bridge private native static UIColor objc_getSeparatorColor(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getSeparatorColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getSeparatorColor() {
        if (customClass) { return objc_getSeparatorColorSuper(getSuper(), separatorColor); } else { return objc_getSeparatorColor(this, separatorColor); }
    }
    
    private static final Selector setSeparatorColor$ = Selector.register("setSeparatorColor:");
    @Bridge private native static void objc_setSeparatorColor(UITableView __self__, Selector __cmd__, UIColor separatorColor);
    @Bridge private native static void objc_setSeparatorColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor separatorColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSeparatorColor(UIColor separatorColor) {
        if (customClass) { objc_setSeparatorColorSuper(getSuper(), setSeparatorColor$, separatorColor); } else { objc_setSeparatorColor(this, setSeparatorColor$, separatorColor); }
    }
    
    private static final Selector separatorStyle = Selector.register("separatorStyle");
    @Bridge private native static UITableViewCellSeparatorStyle objc_getSeparatorStyle(UITableView __self__, Selector __cmd__);
    @Bridge private native static UITableViewCellSeparatorStyle objc_getSeparatorStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCellSeparatorStyle getSeparatorStyle() {
        if (customClass) { return objc_getSeparatorStyleSuper(getSuper(), separatorStyle); } else { return objc_getSeparatorStyle(this, separatorStyle); }
    }
    
    private static final Selector setSeparatorStyle$ = Selector.register("setSeparatorStyle:");
    @Bridge private native static void objc_setSeparatorStyle(UITableView __self__, Selector __cmd__, UITableViewCellSeparatorStyle separatorStyle);
    @Bridge private native static void objc_setSeparatorStyleSuper(ObjCSuper __super__, Selector __cmd__, UITableViewCellSeparatorStyle separatorStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSeparatorStyle(UITableViewCellSeparatorStyle separatorStyle) {
        if (customClass) { objc_setSeparatorStyleSuper(getSuper(), setSeparatorStyle$, separatorStyle); } else { objc_setSeparatorStyle(this, setSeparatorStyle$, separatorStyle); }
    }
    
    private static final Selector style = Selector.register("style");
    @Bridge private native static UITableViewStyle objc_getStyle(UITableView __self__, Selector __cmd__);
    @Bridge private native static UITableViewStyle objc_getStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/style">@property(nonatomic, readonly) UITableViewStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewStyle getStyle() {
        if (customClass) { return objc_getStyleSuper(getSuper(), style); } else { return objc_getStyle(this, style); }
    }
    
    private static final Selector tableFooterView = Selector.register("tableFooterView");
    @Bridge private native static UIView objc_getTableFooterView(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getTableFooterViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTableFooterView() {
        if (customClass) { return objc_getTableFooterViewSuper(getSuper(), tableFooterView); } else { return objc_getTableFooterView(this, tableFooterView); }
    }
    
    private static final Selector setTableFooterView$ = Selector.register("setTableFooterView:");
    @Bridge private native static void objc_setTableFooterView(UITableView __self__, Selector __cmd__, UIView tableFooterView);
    @Bridge private native static void objc_setTableFooterViewSuper(ObjCSuper __super__, Selector __cmd__, UIView tableFooterView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTableFooterView(UIView tableFooterView) {
        if (customClass) { objc_setTableFooterViewSuper(getSuper(), setTableFooterView$, tableFooterView); } else { objc_setTableFooterView(this, setTableFooterView$, tableFooterView); }
    }
    
    private static final Selector tableHeaderView = Selector.register("tableHeaderView");
    @Bridge private native static UIView objc_getTableHeaderView(UITableView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getTableHeaderViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTableHeaderView() {
        if (customClass) { return objc_getTableHeaderViewSuper(getSuper(), tableHeaderView); } else { return objc_getTableHeaderView(this, tableHeaderView); }
    }
    
    private static final Selector setTableHeaderView$ = Selector.register("setTableHeaderView:");
    @Bridge private native static void objc_setTableHeaderView(UITableView __self__, Selector __cmd__, UIView tableHeaderView);
    @Bridge private native static void objc_setTableHeaderViewSuper(ObjCSuper __super__, Selector __cmd__, UIView tableHeaderView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTableHeaderView(UIView tableHeaderView) {
        if (customClass) { objc_setTableHeaderViewSuper(getSuper(), setTableHeaderView$, tableHeaderView); } else { objc_setTableHeaderView(this, setTableHeaderView$, tableHeaderView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector beginUpdates = Selector.register("beginUpdates");
    @Bridge private native static void objc_beginUpdates(UITableView __self__, Selector __cmd__);
    @Bridge private native static void objc_beginUpdatesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/beginUpdates">- (void)beginUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginUpdates() {
        if (customClass) { objc_beginUpdatesSuper(getSuper(), beginUpdates); } else { objc_beginUpdates(this, beginUpdates); }
    }
    
    private static final Selector deleteRowsAtIndexPaths$withRowAnimation$ = Selector.register("deleteRowsAtIndexPaths:withRowAnimation:");
    @Bridge private native static void objc_deleteRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge private native static void objc_deleteRowsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteRowsAtIndexPaths:withRowAnimation:">- (void)deleteRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deleteRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_deleteRowsSuper(getSuper(), deleteRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_deleteRows(this, deleteRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector deleteSections$withRowAnimation$ = Selector.register("deleteSections:withRowAnimation:");
    @Bridge private native static void objc_deleteSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge private native static void objc_deleteSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deleteSections:withRowAnimation:">- (void)deleteSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deleteSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_deleteSectionsSuper(getSuper(), deleteSections$withRowAnimation$, sections, animation); } else { objc_deleteSections(this, deleteSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector dequeueReusableCellWithIdentifier$forIndexPath$ = Selector.register("dequeueReusableCellWithIdentifier:forIndexPath:");
    @Bridge private native static NSObject objc_dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    @Bridge private native static NSObject objc_dequeueReusableCellSuper(ObjCSuper __super__, Selector __cmd__, String identifier, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:forIndexPath:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier forIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableCell(String identifier, NSIndexPath indexPath) {
        if (customClass) { return objc_dequeueReusableCellSuper(getSuper(), dequeueReusableCellWithIdentifier$forIndexPath$, identifier, indexPath); } else { return objc_dequeueReusableCell(this, dequeueReusableCellWithIdentifier$forIndexPath$, identifier, indexPath); }
    }
    
    private static final Selector dequeueReusableCellWithIdentifier$ = Selector.register("dequeueReusableCellWithIdentifier:");
    @Bridge private native static NSObject objc_dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier);
    @Bridge private native static NSObject objc_dequeueReusableCellSuper(ObjCSuper __super__, Selector __cmd__, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableCellWithIdentifier:">- (id)dequeueReusableCellWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSObject dequeueReusableCell(String identifier) {
        if (customClass) { return objc_dequeueReusableCellSuper(getSuper(), dequeueReusableCellWithIdentifier$, identifier); } else { return objc_dequeueReusableCell(this, dequeueReusableCellWithIdentifier$, identifier); }
    }
    
    private static final Selector dequeueReusableHeaderFooterViewWithIdentifier$ = Selector.register("dequeueReusableHeaderFooterViewWithIdentifier:");
    @Bridge private native static NSObject objc_dequeueReusableHeaderFooterView(UITableView __self__, Selector __cmd__, String identifier);
    @Bridge private native static NSObject objc_dequeueReusableHeaderFooterViewSuper(ObjCSuper __super__, Selector __cmd__, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/dequeueReusableHeaderFooterViewWithIdentifier:">- (id)dequeueReusableHeaderFooterViewWithIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject dequeueReusableHeaderFooterView(String identifier) {
        if (customClass) { return objc_dequeueReusableHeaderFooterViewSuper(getSuper(), dequeueReusableHeaderFooterViewWithIdentifier$, identifier); } else { return objc_dequeueReusableHeaderFooterView(this, dequeueReusableHeaderFooterViewWithIdentifier$, identifier); }
    }
    
    private static final Selector deselectRowAtIndexPath$animated$ = Selector.register("deselectRowAtIndexPath:animated:");
    @Bridge private native static void objc_deselectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    @Bridge private native static void objc_deselectRowSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/deselectRowAtIndexPath:animated:">- (void)deselectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void deselectRow(NSIndexPath indexPath, boolean animated) {
        if (customClass) { objc_deselectRowSuper(getSuper(), deselectRowAtIndexPath$animated$, indexPath, animated); } else { objc_deselectRow(this, deselectRowAtIndexPath$animated$, indexPath, animated); }
    }
    
    private static final Selector endUpdates = Selector.register("endUpdates");
    @Bridge private native static void objc_endUpdates(UITableView __self__, Selector __cmd__);
    @Bridge private native static void objc_endUpdatesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/endUpdates">- (void)endUpdates</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endUpdates() {
        if (customClass) { objc_endUpdatesSuper(getSuper(), endUpdates); } else { objc_endUpdates(this, endUpdates); }
    }
    
    private static final Selector indexPathForCell$ = Selector.register("indexPathForCell:");
    @Bridge private native static NSIndexPath objc_getCellIndexPath(UITableView __self__, Selector __cmd__, UITableViewCell cell);
    @Bridge private native static NSIndexPath objc_getCellIndexPathSuper(ObjCSuper __super__, Selector __cmd__, UITableViewCell cell);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForCell:">- (NSIndexPath *)indexPathForCell:(UITableViewCell *)cell</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getCellIndexPath(UITableViewCell cell) {
        if (customClass) { return objc_getCellIndexPathSuper(getSuper(), indexPathForCell$, cell); } else { return objc_getCellIndexPath(this, indexPathForCell$, cell); }
    }
    
    private static final Selector numberOfRowsInSection$ = Selector.register("numberOfRowsInSection:");
    @Bridge private native static int objc_getNumberOfRowsInSection(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static int objc_getNumberOfRowsInSectionSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfRowsInSection:">- (NSInteger)numberOfRowsInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfRowsInSection(int section) {
        if (customClass) { return objc_getNumberOfRowsInSectionSuper(getSuper(), numberOfRowsInSection$, section); } else { return objc_getNumberOfRowsInSection(this, numberOfRowsInSection$, section); }
    }
    
    private static final Selector numberOfSections = Selector.register("numberOfSections");
    @Bridge private native static int objc_getNumberOfSections(UITableView __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfSectionsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/numberOfSections">- (NSInteger)numberOfSections</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfSections() {
        if (customClass) { return objc_getNumberOfSectionsSuper(getSuper(), numberOfSections); } else { return objc_getNumberOfSections(this, numberOfSections); }
    }
    
    private static final Selector cellForRowAtIndexPath$ = Selector.register("cellForRowAtIndexPath:");
    @Bridge private native static UITableViewCell objc_getRowCell(UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static UITableViewCell objc_getRowCellSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/cellForRowAtIndexPath:">- (UITableViewCell *)cellForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCell getRowCell(NSIndexPath indexPath) {
        if (customClass) { return objc_getRowCellSuper(getSuper(), cellForRowAtIndexPath$, indexPath); } else { return objc_getRowCell(this, cellForRowAtIndexPath$, indexPath); }
    }
    
    private static final Selector indexPathForRowAtPoint$ = Selector.register("indexPathForRowAtPoint:");
    @Bridge private native static NSIndexPath objc_getRowIndexPath(UITableView __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static NSIndexPath objc_getRowIndexPathSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForRowAtPoint:">- (NSIndexPath *)indexPathForRowAtPoint:(CGPoint)point</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getRowIndexPath(CGPoint point) {
        if (customClass) { return objc_getRowIndexPathSuper(getSuper(), indexPathForRowAtPoint$, point); } else { return objc_getRowIndexPath(this, indexPathForRowAtPoint$, point); }
    }
    
    private static final Selector rectForRowAtIndexPath$ = Selector.register("rectForRowAtIndexPath:");
    @Bridge private native static @ByVal CGRect objc_getRowRect(UITableView __self__, Selector __cmd__, NSIndexPath indexPath);
    @Bridge private native static @ByVal CGRect objc_getRowRectSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForRowAtIndexPath:">- (CGRect)rectForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getRowRect(NSIndexPath indexPath) {
        if (customClass) { return objc_getRowRectSuper(getSuper(), rectForRowAtIndexPath$, indexPath); } else { return objc_getRowRect(this, rectForRowAtIndexPath$, indexPath); }
    }
    
    private static final Selector indexPathsForRowsInRect$ = Selector.register("indexPathsForRowsInRect:");
    @Bridge private native static NSArray objc_getRowsIndexPaths(UITableView __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static NSArray objc_getRowsIndexPathsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForRowsInRect:">- (NSArray *)indexPathsForRowsInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getRowsIndexPaths(CGRect rect) {
        if (customClass) { return objc_getRowsIndexPathsSuper(getSuper(), indexPathsForRowsInRect$, rect); } else { return objc_getRowsIndexPaths(this, indexPathsForRowsInRect$, rect); }
    }
    
    private static final Selector rectForFooterInSection$ = Selector.register("rectForFooterInSection:");
    @Bridge private native static @ByVal CGRect objc_getSectionFooterRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static @ByVal CGRect objc_getSectionFooterRectSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForFooterInSection:">- (CGRect)rectForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionFooterRect(int section) {
        if (customClass) { return objc_getSectionFooterRectSuper(getSuper(), rectForFooterInSection$, section); } else { return objc_getSectionFooterRect(this, rectForFooterInSection$, section); }
    }
    
    private static final Selector footerViewForSection$ = Selector.register("footerViewForSection:");
    @Bridge private native static UITableViewHeaderFooterView objc_getSectionFooterView(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static UITableViewHeaderFooterView objc_getSectionFooterViewSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/footerViewForSection:">- (UITableViewHeaderFooterView *)footerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITableViewHeaderFooterView getSectionFooterView(int section) {
        if (customClass) { return objc_getSectionFooterViewSuper(getSuper(), footerViewForSection$, section); } else { return objc_getSectionFooterView(this, footerViewForSection$, section); }
    }
    
    private static final Selector rectForHeaderInSection$ = Selector.register("rectForHeaderInSection:");
    @Bridge private native static @ByVal CGRect objc_getSectionHeaderRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static @ByVal CGRect objc_getSectionHeaderRectSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForHeaderInSection:">- (CGRect)rectForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionHeaderRect(int section) {
        if (customClass) { return objc_getSectionHeaderRectSuper(getSuper(), rectForHeaderInSection$, section); } else { return objc_getSectionHeaderRect(this, rectForHeaderInSection$, section); }
    }
    
    private static final Selector headerViewForSection$ = Selector.register("headerViewForSection:");
    @Bridge private native static UITableViewHeaderFooterView objc_getSectionHeaderView(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static UITableViewHeaderFooterView objc_getSectionHeaderViewSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/headerViewForSection:">- (UITableViewHeaderFooterView *)headerViewForSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITableViewHeaderFooterView getSectionHeaderView(int section) {
        if (customClass) { return objc_getSectionHeaderViewSuper(getSuper(), headerViewForSection$, section); } else { return objc_getSectionHeaderView(this, headerViewForSection$, section); }
    }
    
    private static final Selector rectForSection$ = Selector.register("rectForSection:");
    @Bridge private native static @ByVal CGRect objc_getSectionRect(UITableView __self__, Selector __cmd__, int section);
    @Bridge private native static @ByVal CGRect objc_getSectionRectSuper(ObjCSuper __super__, Selector __cmd__, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/rectForSection:">- (CGRect)rectForSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getSectionRect(int section) {
        if (customClass) { return objc_getSectionRectSuper(getSuper(), rectForSection$, section); } else { return objc_getSectionRect(this, rectForSection$, section); }
    }
    
    private static final Selector indexPathForSelectedRow = Selector.register("indexPathForSelectedRow");
    @Bridge private native static NSIndexPath objc_getSelectedRowIndexPath(UITableView __self__, Selector __cmd__);
    @Bridge private native static NSIndexPath objc_getSelectedRowIndexPathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathForSelectedRow">- (NSIndexPath *)indexPathForSelectedRow</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath getSelectedRowIndexPath() {
        if (customClass) { return objc_getSelectedRowIndexPathSuper(getSuper(), indexPathForSelectedRow); } else { return objc_getSelectedRowIndexPath(this, indexPathForSelectedRow); }
    }
    
    private static final Selector indexPathsForSelectedRows = Selector.register("indexPathsForSelectedRows");
    @Bridge private native static NSArray objc_getSelectedRowsIndexPaths(UITableView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getSelectedRowsIndexPathsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForSelectedRows">- (NSArray *)indexPathsForSelectedRows</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getSelectedRowsIndexPaths() {
        if (customClass) { return objc_getSelectedRowsIndexPathsSuper(getSuper(), indexPathsForSelectedRows); } else { return objc_getSelectedRowsIndexPaths(this, indexPathsForSelectedRows); }
    }
    
    private static final Selector visibleCells = Selector.register("visibleCells");
    @Bridge private native static NSArray objc_getVisibleCells(UITableView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getVisibleCellsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/visibleCells">- (NSArray *)visibleCells</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getVisibleCells() {
        if (customClass) { return objc_getVisibleCellsSuper(getSuper(), visibleCells); } else { return objc_getVisibleCells(this, visibleCells); }
    }
    
    private static final Selector indexPathsForVisibleRows = Selector.register("indexPathsForVisibleRows");
    @Bridge private native static NSArray objc_getVisibleRowsIndexPaths(UITableView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getVisibleRowsIndexPathsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/indexPathsForVisibleRows">- (NSArray *)indexPathsForVisibleRows</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getVisibleRowsIndexPaths() {
        if (customClass) { return objc_getVisibleRowsIndexPathsSuper(getSuper(), indexPathsForVisibleRows); } else { return objc_getVisibleRowsIndexPaths(this, indexPathsForVisibleRows); }
    }
    
    private static final Selector insertRowsAtIndexPaths$withRowAnimation$ = Selector.register("insertRowsAtIndexPaths:withRowAnimation:");
    @Bridge private native static void objc_insertRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge private native static void objc_insertRowsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertRowsAtIndexPaths:withRowAnimation:">- (void)insertRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_insertRowsSuper(getSuper(), insertRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_insertRows(this, insertRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector insertSections$withRowAnimation$ = Selector.register("insertSections:withRowAnimation:");
    @Bridge private native static void objc_insertSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge private native static void objc_insertSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/insertSections:withRowAnimation:">- (void)insertSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_insertSectionsSuper(getSuper(), insertSections$withRowAnimation$, sections, animation); } else { objc_insertSections(this, insertSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector moveRowAtIndexPath$toIndexPath$ = Selector.register("moveRowAtIndexPath:toIndexPath:");
    @Bridge private native static void objc_moveRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    @Bridge private native static void objc_moveRowSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveRowAtIndexPath:toIndexPath:">- (void)moveRowAtIndexPath:(NSIndexPath *)indexPath toIndexPath:(NSIndexPath *)newIndexPath</a>
     * @since Available in iOS 5.0 and later.
     */
    public void moveRow(NSIndexPath indexPath, NSIndexPath newIndexPath) {
        if (customClass) { objc_moveRowSuper(getSuper(), moveRowAtIndexPath$toIndexPath$, indexPath, newIndexPath); } else { objc_moveRow(this, moveRowAtIndexPath$toIndexPath$, indexPath, newIndexPath); }
    }
    
    private static final Selector moveSection$toSection$ = Selector.register("moveSection:toSection:");
    @Bridge private native static void objc_moveSection(UITableView __self__, Selector __cmd__, int section, int newSection);
    @Bridge private native static void objc_moveSectionSuper(ObjCSuper __super__, Selector __cmd__, int section, int newSection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/moveSection:toSection:">- (void)moveSection:(NSInteger)section toSection:(NSInteger)newSection</a>
     * @since Available in iOS 5.0 and later.
     */
    public void moveSection(int section, int newSection) {
        if (customClass) { objc_moveSectionSuper(getSuper(), moveSection$toSection$, section, newSection); } else { objc_moveSection(this, moveSection$toSection$, section, newSection); }
    }
    
    private static final Selector registerClass$forCellReuseIdentifier$ = Selector.register("registerClass:forCellReuseIdentifier:");
    @Bridge private native static void objc_registerReusableCellClass(UITableView __self__, Selector __cmd__, ObjCClass cellClass, String identifier);
    @Bridge private native static void objc_registerReusableCellClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass cellClass, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forCellReuseIdentifier:">- (void)registerClass:(Class)cellClass forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableCellClass(ObjCClass cellClass, String identifier) {
        if (customClass) { objc_registerReusableCellClassSuper(getSuper(), registerClass$forCellReuseIdentifier$, cellClass, identifier); } else { objc_registerReusableCellClass(this, registerClass$forCellReuseIdentifier$, cellClass, identifier); }
    }
    
    private static final Selector registerNib$forCellReuseIdentifier$ = Selector.register("registerNib:forCellReuseIdentifier:");
    @Bridge private native static void objc_registerReusableCellNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    @Bridge private native static void objc_registerReusableCellNibSuper(ObjCSuper __super__, Selector __cmd__, UINib nib, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forCellReuseIdentifier:">- (void)registerNib:(UINib *)nib forCellReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 5.0 and later.
     */
    public void registerReusableCellNib(UINib nib, String identifier) {
        if (customClass) { objc_registerReusableCellNibSuper(getSuper(), registerNib$forCellReuseIdentifier$, nib, identifier); } else { objc_registerReusableCellNib(this, registerNib$forCellReuseIdentifier$, nib, identifier); }
    }
    
    private static final Selector registerClass$forHeaderFooterViewReuseIdentifier$ = Selector.register("registerClass:forHeaderFooterViewReuseIdentifier:");
    @Bridge private native static void objc_registerReusableHeaderFooterViewClass(UITableView __self__, Selector __cmd__, ObjCClass aClass, String identifier);
    @Bridge private native static void objc_registerReusableHeaderFooterViewClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass aClass, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerClass:forHeaderFooterViewReuseIdentifier:">- (void)registerClass:(Class)aClass forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableHeaderFooterViewClass(ObjCClass aClass, String identifier) {
        if (customClass) { objc_registerReusableHeaderFooterViewClassSuper(getSuper(), registerClass$forHeaderFooterViewReuseIdentifier$, aClass, identifier); } else { objc_registerReusableHeaderFooterViewClass(this, registerClass$forHeaderFooterViewReuseIdentifier$, aClass, identifier); }
    }
    
    private static final Selector registerNib$forHeaderFooterViewReuseIdentifier$ = Selector.register("registerNib:forHeaderFooterViewReuseIdentifier:");
    @Bridge private native static void objc_registerReusableHeaderFooterViewNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier);
    @Bridge private native static void objc_registerReusableHeaderFooterViewNibSuper(ObjCSuper __super__, Selector __cmd__, UINib nib, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/registerNib:forHeaderFooterViewReuseIdentifier:">- (void)registerNib:(UINib *)nib forHeaderFooterViewReuseIdentifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void registerReusableHeaderFooterViewNib(UINib nib, String identifier) {
        if (customClass) { objc_registerReusableHeaderFooterViewNibSuper(getSuper(), registerNib$forHeaderFooterViewReuseIdentifier$, nib, identifier); } else { objc_registerReusableHeaderFooterViewNib(this, registerNib$forHeaderFooterViewReuseIdentifier$, nib, identifier); }
    }
    
    private static final Selector reloadData = Selector.register("reloadData");
    @Bridge private native static void objc_reloadData(UITableView __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadDataSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadData">- (void)reloadData</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadData() {
        if (customClass) { objc_reloadDataSuper(getSuper(), reloadData); } else { objc_reloadData(this, reloadData); }
    }
    
    private static final Selector reloadRowsAtIndexPaths$withRowAnimation$ = Selector.register("reloadRowsAtIndexPaths:withRowAnimation:");
    @Bridge private native static void objc_reloadRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    @Bridge private native static void objc_reloadRowsSuper(ObjCSuper __super__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadRowsAtIndexPaths:withRowAnimation:">- (void)reloadRowsAtIndexPaths:(NSArray *)indexPaths withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadRows(NSArray indexPaths, UITableViewRowAnimation animation) {
        if (customClass) { objc_reloadRowsSuper(getSuper(), reloadRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); } else { objc_reloadRows(this, reloadRowsAtIndexPaths$withRowAnimation$, indexPaths, animation); }
    }
    
    private static final Selector reloadSectionIndexTitles = Selector.register("reloadSectionIndexTitles");
    @Bridge private native static void objc_reloadSectionIndexTitles(UITableView __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadSectionIndexTitlesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSectionIndexTitles">- (void)reloadSectionIndexTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadSectionIndexTitles() {
        if (customClass) { objc_reloadSectionIndexTitlesSuper(getSuper(), reloadSectionIndexTitles); } else { objc_reloadSectionIndexTitles(this, reloadSectionIndexTitles); }
    }
    
    private static final Selector reloadSections$withRowAnimation$ = Selector.register("reloadSections:withRowAnimation:");
    @Bridge private native static void objc_reloadSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    @Bridge private native static void objc_reloadSectionsSuper(ObjCSuper __super__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/reloadSections:withRowAnimation:">- (void)reloadSections:(NSIndexSet *)sections withRowAnimation:(UITableViewRowAnimation)animation</a>
     * @since Available in iOS 3.0 and later.
     */
    public void reloadSections(NSIndexSet sections, UITableViewRowAnimation animation) {
        if (customClass) { objc_reloadSectionsSuper(getSuper(), reloadSections$withRowAnimation$, sections, animation); } else { objc_reloadSections(this, reloadSections$withRowAnimation$, sections, animation); }
    }
    
    private static final Selector scrollToNearestSelectedRowAtScrollPosition$animated$ = Selector.register("scrollToNearestSelectedRowAtScrollPosition:animated:");
    @Bridge private native static void objc_scrollToNearestSelectedRow(UITableView __self__, Selector __cmd__, UITableViewScrollPosition scrollPosition, boolean animated);
    @Bridge private native static void objc_scrollToNearestSelectedRowSuper(ObjCSuper __super__, Selector __cmd__, UITableViewScrollPosition scrollPosition, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToNearestSelectedRowAtScrollPosition:animated:">- (void)scrollToNearestSelectedRowAtScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollToNearestSelectedRow(UITableViewScrollPosition scrollPosition, boolean animated) {
        if (customClass) { objc_scrollToNearestSelectedRowSuper(getSuper(), scrollToNearestSelectedRowAtScrollPosition$animated$, scrollPosition, animated); } else { objc_scrollToNearestSelectedRow(this, scrollToNearestSelectedRowAtScrollPosition$animated$, scrollPosition, animated); }
    }
    
    private static final Selector scrollToRowAtIndexPath$atScrollPosition$animated$ = Selector.register("scrollToRowAtIndexPath:atScrollPosition:animated:");
    @Bridge private native static void objc_scrollToRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated);
    @Bridge private native static void objc_scrollToRowSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/scrollToRowAtIndexPath:atScrollPosition:animated:">- (void)scrollToRowAtIndexPath:(NSIndexPath *)indexPath atScrollPosition:(UITableViewScrollPosition)scrollPosition animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollToRow(NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated) {
        if (customClass) { objc_scrollToRowSuper(getSuper(), scrollToRowAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); } else { objc_scrollToRow(this, scrollToRowAtIndexPath$atScrollPosition$animated$, indexPath, scrollPosition, animated); }
    }
    
    private static final Selector selectRowAtIndexPath$animated$scrollPosition$ = Selector.register("selectRowAtIndexPath:animated:scrollPosition:");
    @Bridge private native static void objc_selectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition);
    @Bridge private native static void objc_selectRowSuper(ObjCSuper __super__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/selectRowAtIndexPath:animated:scrollPosition:">- (void)selectRowAtIndexPath:(NSIndexPath *)indexPath animated:(BOOL)animated scrollPosition:(UITableViewScrollPosition)scrollPosition</a>
     * @since Available in iOS 2.0 and later.
     */
    public void selectRow(NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition) {
        if (customClass) { objc_selectRowSuper(getSuper(), selectRowAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); } else { objc_selectRow(this, selectRowAtIndexPath$animated$scrollPosition$, indexPath, animated, scrollPosition); }
    }
    
    private static final Selector setEditing$animated$ = Selector.register("setEditing:animated:");
    @Bridge private native static void objc_setEditing(UITableView __self__, Selector __cmd__, boolean editing, boolean animate);
    @Bridge private native static void objc_setEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean editing, boolean animate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableView/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing, boolean animate) {
        if (customClass) { objc_setEditingSuper(getSuper(), setEditing$animated$, editing, animate); } else { objc_setEditing(this, setEditing$animated$, editing, animate); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allowsMultipleSelection") public static boolean isAllowsMultipleSelection(UITableView __self__, Selector __cmd__) { return __self__.isAllowsMultipleSelection(); }
        @Callback @BindSelector("setAllowsMultipleSelection:") public static void setAllowsMultipleSelection(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelection) { __self__.setAllowsMultipleSelection(allowsMultipleSelection); }
        @Callback @BindSelector("allowsMultipleSelectionDuringEditing") public static boolean isAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__) { return __self__.isAllowsMultipleSelectionDuringEditing(); }
        @Callback @BindSelector("setAllowsMultipleSelectionDuringEditing:") public static void setAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelectionDuringEditing) { __self__.setAllowsMultipleSelectionDuringEditing(allowsMultipleSelectionDuringEditing); }
        @Callback @BindSelector("allowsSelection") public static boolean isAllowsSelection(UITableView __self__, Selector __cmd__) { return __self__.isAllowsSelection(); }
        @Callback @BindSelector("setAllowsSelection:") public static void setAllowsSelection(UITableView __self__, Selector __cmd__, boolean allowsSelection) { __self__.setAllowsSelection(allowsSelection); }
        @Callback @BindSelector("allowsSelectionDuringEditing") public static boolean isAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__) { return __self__.isAllowsSelectionDuringEditing(); }
        @Callback @BindSelector("setAllowsSelectionDuringEditing:") public static void setAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsSelectionDuringEditing) { __self__.setAllowsSelectionDuringEditing(allowsSelectionDuringEditing); }
        @Callback @BindSelector("backgroundView") public static UIView getBackgroundView(UITableView __self__, Selector __cmd__) { return __self__.getBackgroundView(); }
        @Callback @BindSelector("setBackgroundView:") public static void setBackgroundView(UITableView __self__, Selector __cmd__, UIView backgroundView) { __self__.setBackgroundView(backgroundView); }
        @Callback @BindSelector("dataSource") public static UITableViewDataSource getDataSource(UITableView __self__, Selector __cmd__) { return __self__.getDataSource(); }
        @Callback @BindSelector("setDataSource:") public static void setDataSource(UITableView __self__, Selector __cmd__, UITableViewDataSource dataSource) { __self__.setDataSource(dataSource); }
        @Callback @BindSelector("delegate") public static UITableViewDelegate getDelegate(UITableView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UITableView __self__, Selector __cmd__, UITableViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isEditing") public static boolean isEditing(UITableView __self__, Selector __cmd__) { return __self__.isEditing(); }
        @Callback @BindSelector("setEditing:") public static void setEditing(UITableView __self__, Selector __cmd__, boolean editing) { __self__.setEditing(editing); }
        @Callback @BindSelector("rowHeight") public static float getRowHeight(UITableView __self__, Selector __cmd__) { return __self__.getRowHeight(); }
        @Callback @BindSelector("setRowHeight:") public static void setRowHeight(UITableView __self__, Selector __cmd__, float rowHeight) { __self__.setRowHeight(rowHeight); }
        @Callback @BindSelector("sectionFooterHeight") public static float getSectionFooterHeight(UITableView __self__, Selector __cmd__) { return __self__.getSectionFooterHeight(); }
        @Callback @BindSelector("setSectionFooterHeight:") public static void setSectionFooterHeight(UITableView __self__, Selector __cmd__, float sectionFooterHeight) { __self__.setSectionFooterHeight(sectionFooterHeight); }
        @Callback @BindSelector("sectionHeaderHeight") public static float getSectionHeaderHeight(UITableView __self__, Selector __cmd__) { return __self__.getSectionHeaderHeight(); }
        @Callback @BindSelector("setSectionHeaderHeight:") public static void setSectionHeaderHeight(UITableView __self__, Selector __cmd__, float sectionHeaderHeight) { __self__.setSectionHeaderHeight(sectionHeaderHeight); }
        @Callback @BindSelector("sectionIndexColor") public static UIColor getSectionIndexColor(UITableView __self__, Selector __cmd__) { return __self__.getSectionIndexColor(); }
        @Callback @BindSelector("setSectionIndexColor:") public static void setSectionIndexColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexColor) { __self__.setSectionIndexColor(sectionIndexColor); }
        @Callback @BindSelector("sectionIndexMinimumDisplayRowCount") public static int getSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__) { return __self__.getSectionIndexMinimumDisplayRowCount(); }
        @Callback @BindSelector("setSectionIndexMinimumDisplayRowCount:") public static void setSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__, int sectionIndexMinimumDisplayRowCount) { __self__.setSectionIndexMinimumDisplayRowCount(sectionIndexMinimumDisplayRowCount); }
        @Callback @BindSelector("sectionIndexTrackingBackgroundColor") public static UIColor getSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__) { return __self__.getSectionIndexTrackingBackgroundColor(); }
        @Callback @BindSelector("setSectionIndexTrackingBackgroundColor:") public static void setSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexTrackingBackgroundColor) { __self__.setSectionIndexTrackingBackgroundColor(sectionIndexTrackingBackgroundColor); }
        @Callback @BindSelector("separatorColor") public static UIColor getSeparatorColor(UITableView __self__, Selector __cmd__) { return __self__.getSeparatorColor(); }
        @Callback @BindSelector("setSeparatorColor:") public static void setSeparatorColor(UITableView __self__, Selector __cmd__, UIColor separatorColor) { __self__.setSeparatorColor(separatorColor); }
        @Callback @BindSelector("separatorStyle") public static UITableViewCellSeparatorStyle getSeparatorStyle(UITableView __self__, Selector __cmd__) { return __self__.getSeparatorStyle(); }
        @Callback @BindSelector("setSeparatorStyle:") public static void setSeparatorStyle(UITableView __self__, Selector __cmd__, UITableViewCellSeparatorStyle separatorStyle) { __self__.setSeparatorStyle(separatorStyle); }
        @Callback @BindSelector("style") public static UITableViewStyle getStyle(UITableView __self__, Selector __cmd__) { return __self__.getStyle(); }
        @Callback @BindSelector("tableFooterView") public static UIView getTableFooterView(UITableView __self__, Selector __cmd__) { return __self__.getTableFooterView(); }
        @Callback @BindSelector("setTableFooterView:") public static void setTableFooterView(UITableView __self__, Selector __cmd__, UIView tableFooterView) { __self__.setTableFooterView(tableFooterView); }
        @Callback @BindSelector("tableHeaderView") public static UIView getTableHeaderView(UITableView __self__, Selector __cmd__) { return __self__.getTableHeaderView(); }
        @Callback @BindSelector("setTableHeaderView:") public static void setTableHeaderView(UITableView __self__, Selector __cmd__, UIView tableHeaderView) { __self__.setTableHeaderView(tableHeaderView); }
        @Callback @BindSelector("beginUpdates") public static void beginUpdates(UITableView __self__, Selector __cmd__) { __self__.beginUpdates(); }
        @Callback @BindSelector("deleteRowsAtIndexPaths:withRowAnimation:") public static void deleteRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation) { __self__.deleteRows(indexPaths, animation); }
        @Callback @BindSelector("deleteSections:withRowAnimation:") public static void deleteSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation) { __self__.deleteSections(sections, animation); }
        @Callback @BindSelector("dequeueReusableCellWithIdentifier:forIndexPath:") public static NSObject dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier, NSIndexPath indexPath) { return __self__.dequeueReusableCell(identifier, indexPath); }
        @Callback @BindSelector("dequeueReusableCellWithIdentifier:") public static NSObject dequeueReusableCell(UITableView __self__, Selector __cmd__, String identifier) { return __self__.dequeueReusableCell(identifier); }
        @Callback @BindSelector("dequeueReusableHeaderFooterViewWithIdentifier:") public static NSObject dequeueReusableHeaderFooterView(UITableView __self__, Selector __cmd__, String identifier) { return __self__.dequeueReusableHeaderFooterView(identifier); }
        @Callback @BindSelector("deselectRowAtIndexPath:animated:") public static void deselectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated) { __self__.deselectRow(indexPath, animated); }
        @Callback @BindSelector("endUpdates") public static void endUpdates(UITableView __self__, Selector __cmd__) { __self__.endUpdates(); }
        @Callback @BindSelector("indexPathForCell:") public static NSIndexPath getCellIndexPath(UITableView __self__, Selector __cmd__, UITableViewCell cell) { return __self__.getCellIndexPath(cell); }
        @Callback @BindSelector("numberOfRowsInSection:") public static int getNumberOfRowsInSection(UITableView __self__, Selector __cmd__, int section) { return __self__.getNumberOfRowsInSection(section); }
        @Callback @BindSelector("numberOfSections") public static int getNumberOfSections(UITableView __self__, Selector __cmd__) { return __self__.getNumberOfSections(); }
        @Callback @BindSelector("cellForRowAtIndexPath:") public static UITableViewCell getRowCell(UITableView __self__, Selector __cmd__, NSIndexPath indexPath) { return __self__.getRowCell(indexPath); }
        @Callback @BindSelector("indexPathForRowAtPoint:") public static NSIndexPath getRowIndexPath(UITableView __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getRowIndexPath(point); }
        @Callback @BindSelector("rectForRowAtIndexPath:") public static @ByVal CGRect getRowRect(UITableView __self__, Selector __cmd__, NSIndexPath indexPath) { return __self__.getRowRect(indexPath); }
        @Callback @BindSelector("indexPathsForRowsInRect:") public static NSArray getRowsIndexPaths(UITableView __self__, Selector __cmd__, @ByVal CGRect rect) { return __self__.getRowsIndexPaths(rect); }
        @Callback @BindSelector("rectForFooterInSection:") public static @ByVal CGRect getSectionFooterRect(UITableView __self__, Selector __cmd__, int section) { return __self__.getSectionFooterRect(section); }
        @Callback @BindSelector("footerViewForSection:") public static UITableViewHeaderFooterView getSectionFooterView(UITableView __self__, Selector __cmd__, int section) { return __self__.getSectionFooterView(section); }
        @Callback @BindSelector("rectForHeaderInSection:") public static @ByVal CGRect getSectionHeaderRect(UITableView __self__, Selector __cmd__, int section) { return __self__.getSectionHeaderRect(section); }
        @Callback @BindSelector("headerViewForSection:") public static UITableViewHeaderFooterView getSectionHeaderView(UITableView __self__, Selector __cmd__, int section) { return __self__.getSectionHeaderView(section); }
        @Callback @BindSelector("rectForSection:") public static @ByVal CGRect getSectionRect(UITableView __self__, Selector __cmd__, int section) { return __self__.getSectionRect(section); }
        @Callback @BindSelector("indexPathForSelectedRow") public static NSIndexPath getSelectedRowIndexPath(UITableView __self__, Selector __cmd__) { return __self__.getSelectedRowIndexPath(); }
        @Callback @BindSelector("indexPathsForSelectedRows") public static NSArray getSelectedRowsIndexPaths(UITableView __self__, Selector __cmd__) { return __self__.getSelectedRowsIndexPaths(); }
        @Callback @BindSelector("visibleCells") public static NSArray getVisibleCells(UITableView __self__, Selector __cmd__) { return __self__.getVisibleCells(); }
        @Callback @BindSelector("indexPathsForVisibleRows") public static NSArray getVisibleRowsIndexPaths(UITableView __self__, Selector __cmd__) { return __self__.getVisibleRowsIndexPaths(); }
        @Callback @BindSelector("insertRowsAtIndexPaths:withRowAnimation:") public static void insertRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation) { __self__.insertRows(indexPaths, animation); }
        @Callback @BindSelector("insertSections:withRowAnimation:") public static void insertSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation) { __self__.insertSections(sections, animation); }
        @Callback @BindSelector("moveRowAtIndexPath:toIndexPath:") public static void moveRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, NSIndexPath newIndexPath) { __self__.moveRow(indexPath, newIndexPath); }
        @Callback @BindSelector("moveSection:toSection:") public static void moveSection(UITableView __self__, Selector __cmd__, int section, int newSection) { __self__.moveSection(section, newSection); }
        @Callback @BindSelector("registerClass:forCellReuseIdentifier:") public static void registerReusableCellClass(UITableView __self__, Selector __cmd__, ObjCClass cellClass, String identifier) { __self__.registerReusableCellClass(cellClass, identifier); }
        @Callback @BindSelector("registerNib:forCellReuseIdentifier:") public static void registerReusableCellNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier) { __self__.registerReusableCellNib(nib, identifier); }
        @Callback @BindSelector("registerClass:forHeaderFooterViewReuseIdentifier:") public static void registerReusableHeaderFooterViewClass(UITableView __self__, Selector __cmd__, ObjCClass aClass, String identifier) { __self__.registerReusableHeaderFooterViewClass(aClass, identifier); }
        @Callback @BindSelector("registerNib:forHeaderFooterViewReuseIdentifier:") public static void registerReusableHeaderFooterViewNib(UITableView __self__, Selector __cmd__, UINib nib, String identifier) { __self__.registerReusableHeaderFooterViewNib(nib, identifier); }
        @Callback @BindSelector("reloadData") public static void reloadData(UITableView __self__, Selector __cmd__) { __self__.reloadData(); }
        @Callback @BindSelector("reloadRowsAtIndexPaths:withRowAnimation:") public static void reloadRows(UITableView __self__, Selector __cmd__, NSArray indexPaths, UITableViewRowAnimation animation) { __self__.reloadRows(indexPaths, animation); }
        @Callback @BindSelector("reloadSectionIndexTitles") public static void reloadSectionIndexTitles(UITableView __self__, Selector __cmd__) { __self__.reloadSectionIndexTitles(); }
        @Callback @BindSelector("reloadSections:withRowAnimation:") public static void reloadSections(UITableView __self__, Selector __cmd__, NSIndexSet sections, UITableViewRowAnimation animation) { __self__.reloadSections(sections, animation); }
        @Callback @BindSelector("scrollToNearestSelectedRowAtScrollPosition:animated:") public static void scrollToNearestSelectedRow(UITableView __self__, Selector __cmd__, UITableViewScrollPosition scrollPosition, boolean animated) { __self__.scrollToNearestSelectedRow(scrollPosition, animated); }
        @Callback @BindSelector("scrollToRowAtIndexPath:atScrollPosition:animated:") public static void scrollToRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, UITableViewScrollPosition scrollPosition, boolean animated) { __self__.scrollToRow(indexPath, scrollPosition, animated); }
        @Callback @BindSelector("selectRowAtIndexPath:animated:scrollPosition:") public static void selectRow(UITableView __self__, Selector __cmd__, NSIndexPath indexPath, boolean animated, UITableViewScrollPosition scrollPosition) { __self__.selectRow(indexPath, animated, scrollPosition); }
        @Callback @BindSelector("setEditing:animated:") public static void setEditing(UITableView __self__, Selector __cmd__, boolean editing, boolean animate) { __self__.setEditing(editing, animate); }
    }
    /*</callbacks>*/

}
