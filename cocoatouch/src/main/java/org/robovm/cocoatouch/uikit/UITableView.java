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
    
    private static final Selector allowsMultipleSelection = Selector.register("allowsMultipleSelection");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAllowsMultipleSelection(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAllowsMultipleSelectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAllowsMultipleSelection() {
        if (customClass) { return objc_isAllowsMultipleSelectionSuper(getSuper(), this, allowsMultipleSelection); } else { return objc_isAllowsMultipleSelection(this, allowsMultipleSelection); }
    }
    
    private static final Selector setAllowsMultipleSelection$ = Selector.register("setAllowsMultipleSelection:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAllowsMultipleSelection(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelection);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAllowsMultipleSelectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean allowsMultipleSelection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelection">@property(nonatomic) BOOL allowsMultipleSelection</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAllowsMultipleSelection(boolean allowsMultipleSelection) {
        if (customClass) { objc_setAllowsMultipleSelectionSuper(getSuper(), this, setAllowsMultipleSelection$, allowsMultipleSelection); } else { objc_setAllowsMultipleSelection(this, setAllowsMultipleSelection$, allowsMultipleSelection); }
    }
    
    private static final Selector allowsMultipleSelectionDuringEditing = Selector.register("allowsMultipleSelectionDuringEditing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAllowsMultipleSelectionDuringEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAllowsMultipleSelectionDuringEditing() {
        if (customClass) { return objc_isAllowsMultipleSelectionDuringEditingSuper(getSuper(), this, allowsMultipleSelectionDuringEditing); } else { return objc_isAllowsMultipleSelectionDuringEditing(this, allowsMultipleSelectionDuringEditing); }
    }
    
    private static final Selector setAllowsMultipleSelectionDuringEditing$ = Selector.register("setAllowsMultipleSelectionDuringEditing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAllowsMultipleSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsMultipleSelectionDuringEditing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAllowsMultipleSelectionDuringEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean allowsMultipleSelectionDuringEditing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsMultipleSelectionDuringEditing">@property(nonatomic) BOOL allowsMultipleSelectionDuringEditing</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAllowsMultipleSelectionDuringEditing(boolean allowsMultipleSelectionDuringEditing) {
        if (customClass) { objc_setAllowsMultipleSelectionDuringEditingSuper(getSuper(), this, setAllowsMultipleSelectionDuringEditing$, allowsMultipleSelectionDuringEditing); } else { objc_setAllowsMultipleSelectionDuringEditing(this, setAllowsMultipleSelectionDuringEditing$, allowsMultipleSelectionDuringEditing); }
    }
    
    private static final Selector allowsSelection = Selector.register("allowsSelection");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAllowsSelection(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAllowsSelectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isAllowsSelection() {
        if (customClass) { return objc_isAllowsSelectionSuper(getSuper(), this, allowsSelection); } else { return objc_isAllowsSelection(this, allowsSelection); }
    }
    
    private static final Selector setAllowsSelection$ = Selector.register("setAllowsSelection:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAllowsSelection(UITableView __self__, Selector __cmd__, boolean allowsSelection);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAllowsSelectionSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean allowsSelection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelection">@property(nonatomic) BOOL allowsSelection</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setAllowsSelection(boolean allowsSelection) {
        if (customClass) { objc_setAllowsSelectionSuper(getSuper(), this, setAllowsSelection$, allowsSelection); } else { objc_setAllowsSelection(this, setAllowsSelection$, allowsSelection); }
    }
    
    private static final Selector allowsSelectionDuringEditing = Selector.register("allowsSelectionDuringEditing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAllowsSelectionDuringEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAllowsSelectionDuringEditing() {
        if (customClass) { return objc_isAllowsSelectionDuringEditingSuper(getSuper(), this, allowsSelectionDuringEditing); } else { return objc_isAllowsSelectionDuringEditing(this, allowsSelectionDuringEditing); }
    }
    
    private static final Selector setAllowsSelectionDuringEditing$ = Selector.register("setAllowsSelectionDuringEditing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAllowsSelectionDuringEditing(UITableView __self__, Selector __cmd__, boolean allowsSelectionDuringEditing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAllowsSelectionDuringEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean allowsSelectionDuringEditing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/allowsSelectionDuringEditing">@property(nonatomic) BOOL allowsSelectionDuringEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAllowsSelectionDuringEditing(boolean allowsSelectionDuringEditing) {
        if (customClass) { objc_setAllowsSelectionDuringEditingSuper(getSuper(), this, setAllowsSelectionDuringEditing$, allowsSelectionDuringEditing); } else { objc_setAllowsSelectionDuringEditing(this, setAllowsSelectionDuringEditing$, allowsSelectionDuringEditing); }
    }
    
    private static final Selector backgroundView = Selector.register("backgroundView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getBackgroundView(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getBackgroundViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getBackgroundView() {
        if (customClass) { return objc_getBackgroundViewSuper(getSuper(), this, backgroundView); } else { return objc_getBackgroundView(this, backgroundView); }
    }
    
    private static final Selector setBackgroundView$ = Selector.register("setBackgroundView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundView(UITableView __self__, Selector __cmd__, UIView backgroundView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIView backgroundView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/backgroundView">@property(nonatomic, readwrite, retain) UIView *backgroundView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setBackgroundView(UIView backgroundView) {
        if (customClass) { objc_setBackgroundViewSuper(getSuper(), this, setBackgroundView$, backgroundView); } else { objc_setBackgroundView(this, setBackgroundView$, backgroundView); }
    }
    
    private static final Selector dataSource = Selector.register("dataSource");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewDataSource objc_getDataSource(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewDataSource objc_getDataSourceSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewDataSource getDataSource() {
        if (customClass) { return objc_getDataSourceSuper(getSuper(), this, dataSource); } else { return objc_getDataSource(this, dataSource); }
    }
    
    private static final Selector setDataSource$ = Selector.register("setDataSource:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDataSource(UITableView __self__, Selector __cmd__, UITableViewDataSource dataSource);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDataSourceSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UITableViewDataSource dataSource);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/dataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDataSource(UITableViewDataSource dataSource) {
        if (customClass) { objc_setDataSourceSuper(getSuper(), this, setDataSource$, dataSource); } else { objc_setDataSource(this, setDataSource$, dataSource); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewDelegate objc_getDelegate(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewDelegate objc_getDelegateSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), this, delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDelegate(UITableView __self__, Selector __cmd__, UITableViewDelegate delegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDelegateSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UITableViewDelegate delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/delegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UITableViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), this, setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isEditing = Selector.register("isEditing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isEditing(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditing() {
        if (customClass) { return objc_isEditingSuper(getSuper(), this, isEditing); } else { return objc_isEditing(this, isEditing); }
    }
    
    private static final Selector setEditing$ = Selector.register("setEditing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditing(UITableView __self__, Selector __cmd__, boolean editing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, boolean editing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing) {
        if (customClass) { objc_setEditingSuper(getSuper(), this, setEditing$, editing); } else { objc_setEditing(this, setEditing$, editing); }
    }
    
    private static final Selector rowHeight = Selector.register("rowHeight");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getRowHeight(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getRowHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getRowHeight() {
        if (customClass) { return objc_getRowHeightSuper(getSuper(), this, rowHeight); } else { return objc_getRowHeight(this, rowHeight); }
    }
    
    private static final Selector setRowHeight$ = Selector.register("setRowHeight:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRowHeight(UITableView __self__, Selector __cmd__, float rowHeight);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRowHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, float rowHeight);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/rowHeight">@property(nonatomic) CGFloat rowHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRowHeight(float rowHeight) {
        if (customClass) { objc_setRowHeightSuper(getSuper(), this, setRowHeight$, rowHeight); } else { objc_setRowHeight(this, setRowHeight$, rowHeight); }
    }
    
    private static final Selector sectionFooterHeight = Selector.register("sectionFooterHeight");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getSectionFooterHeight(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getSectionFooterHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSectionFooterHeight() {
        if (customClass) { return objc_getSectionFooterHeightSuper(getSuper(), this, sectionFooterHeight); } else { return objc_getSectionFooterHeight(this, sectionFooterHeight); }
    }
    
    private static final Selector setSectionFooterHeight$ = Selector.register("setSectionFooterHeight:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionFooterHeight(UITableView __self__, Selector __cmd__, float sectionFooterHeight);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionFooterHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, float sectionFooterHeight);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionFooterHeight">@property(nonatomic) CGFloat sectionFooterHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionFooterHeight(float sectionFooterHeight) {
        if (customClass) { objc_setSectionFooterHeightSuper(getSuper(), this, setSectionFooterHeight$, sectionFooterHeight); } else { objc_setSectionFooterHeight(this, setSectionFooterHeight$, sectionFooterHeight); }
    }
    
    private static final Selector sectionHeaderHeight = Selector.register("sectionHeaderHeight");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getSectionHeaderHeight(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getSectionHeaderHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSectionHeaderHeight() {
        if (customClass) { return objc_getSectionHeaderHeightSuper(getSuper(), this, sectionHeaderHeight); } else { return objc_getSectionHeaderHeight(this, sectionHeaderHeight); }
    }
    
    private static final Selector setSectionHeaderHeight$ = Selector.register("setSectionHeaderHeight:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionHeaderHeight(UITableView __self__, Selector __cmd__, float sectionHeaderHeight);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionHeaderHeightSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, float sectionHeaderHeight);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionHeaderHeight">@property(nonatomic) CGFloat sectionHeaderHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionHeaderHeight(float sectionHeaderHeight) {
        if (customClass) { objc_setSectionHeaderHeightSuper(getSuper(), this, setSectionHeaderHeight$, sectionHeaderHeight); } else { objc_setSectionHeaderHeight(this, setSectionHeaderHeight$, sectionHeaderHeight); }
    }
    
    private static final Selector sectionIndexColor = Selector.register("sectionIndexColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getSectionIndexColor(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getSectionIndexColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getSectionIndexColor() {
        if (customClass) { return objc_getSectionIndexColorSuper(getSuper(), this, sectionIndexColor); } else { return objc_getSectionIndexColor(this, sectionIndexColor); }
    }
    
    private static final Selector setSectionIndexColor$ = Selector.register("setSectionIndexColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionIndexColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionIndexColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIColor sectionIndexColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexColor">@property(nonatomic, retain) UIColor *sectionIndexColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSectionIndexColor(UIColor sectionIndexColor) {
        if (customClass) { objc_setSectionIndexColorSuper(getSuper(), this, setSectionIndexColor$, sectionIndexColor); } else { objc_setSectionIndexColor(this, setSectionIndexColor$, sectionIndexColor); }
    }
    
    private static final Selector sectionIndexMinimumDisplayRowCount = Selector.register("sectionIndexMinimumDisplayRowCount");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getSectionIndexMinimumDisplayRowCountSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getSectionIndexMinimumDisplayRowCount() {
        if (customClass) { return objc_getSectionIndexMinimumDisplayRowCountSuper(getSuper(), this, sectionIndexMinimumDisplayRowCount); } else { return objc_getSectionIndexMinimumDisplayRowCount(this, sectionIndexMinimumDisplayRowCount); }
    }
    
    private static final Selector setSectionIndexMinimumDisplayRowCount$ = Selector.register("setSectionIndexMinimumDisplayRowCount:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionIndexMinimumDisplayRowCount(UITableView __self__, Selector __cmd__, int sectionIndexMinimumDisplayRowCount);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionIndexMinimumDisplayRowCountSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, int sectionIndexMinimumDisplayRowCount);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexMinimumDisplayRowCount">@property(nonatomic) NSInteger sectionIndexMinimumDisplayRowCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSectionIndexMinimumDisplayRowCount(int sectionIndexMinimumDisplayRowCount) {
        if (customClass) { objc_setSectionIndexMinimumDisplayRowCountSuper(getSuper(), this, setSectionIndexMinimumDisplayRowCount$, sectionIndexMinimumDisplayRowCount); } else { objc_setSectionIndexMinimumDisplayRowCount(this, setSectionIndexMinimumDisplayRowCount$, sectionIndexMinimumDisplayRowCount); }
    }
    
    private static final Selector sectionIndexTrackingBackgroundColor = Selector.register("sectionIndexTrackingBackgroundColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getSectionIndexTrackingBackgroundColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getSectionIndexTrackingBackgroundColor() {
        if (customClass) { return objc_getSectionIndexTrackingBackgroundColorSuper(getSuper(), this, sectionIndexTrackingBackgroundColor); } else { return objc_getSectionIndexTrackingBackgroundColor(this, sectionIndexTrackingBackgroundColor); }
    }
    
    private static final Selector setSectionIndexTrackingBackgroundColor$ = Selector.register("setSectionIndexTrackingBackgroundColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionIndexTrackingBackgroundColor(UITableView __self__, Selector __cmd__, UIColor sectionIndexTrackingBackgroundColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionIndexTrackingBackgroundColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIColor sectionIndexTrackingBackgroundColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/sectionIndexTrackingBackgroundColor">@property(nonatomic, retain) UIColor *sectionIndexTrackingBackgroundColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSectionIndexTrackingBackgroundColor(UIColor sectionIndexTrackingBackgroundColor) {
        if (customClass) { objc_setSectionIndexTrackingBackgroundColorSuper(getSuper(), this, setSectionIndexTrackingBackgroundColor$, sectionIndexTrackingBackgroundColor); } else { objc_setSectionIndexTrackingBackgroundColor(this, setSectionIndexTrackingBackgroundColor$, sectionIndexTrackingBackgroundColor); }
    }
    
    private static final Selector separatorColor = Selector.register("separatorColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getSeparatorColor(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getSeparatorColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getSeparatorColor() {
        if (customClass) { return objc_getSeparatorColorSuper(getSuper(), this, separatorColor); } else { return objc_getSeparatorColor(this, separatorColor); }
    }
    
    private static final Selector setSeparatorColor$ = Selector.register("setSeparatorColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSeparatorColor(UITableView __self__, Selector __cmd__, UIColor separatorColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSeparatorColorSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIColor separatorColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorColor">@property(nonatomic, retain) UIColor *separatorColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSeparatorColor(UIColor separatorColor) {
        if (customClass) { objc_setSeparatorColorSuper(getSuper(), this, setSeparatorColor$, separatorColor); } else { objc_setSeparatorColor(this, setSeparatorColor$, separatorColor); }
    }
    
    private static final Selector separatorStyle = Selector.register("separatorStyle");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewCellSeparatorStyle objc_getSeparatorStyle(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewCellSeparatorStyle objc_getSeparatorStyleSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewCellSeparatorStyle getSeparatorStyle() {
        if (customClass) { return objc_getSeparatorStyleSuper(getSuper(), this, separatorStyle); } else { return objc_getSeparatorStyle(this, separatorStyle); }
    }
    
    private static final Selector setSeparatorStyle$ = Selector.register("setSeparatorStyle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSeparatorStyle(UITableView __self__, Selector __cmd__, UITableViewCellSeparatorStyle separatorStyle);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSeparatorStyleSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UITableViewCellSeparatorStyle separatorStyle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/separatorStyle">@property(nonatomic) UITableViewCellSeparatorStyle separatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSeparatorStyle(UITableViewCellSeparatorStyle separatorStyle) {
        if (customClass) { objc_setSeparatorStyleSuper(getSuper(), this, setSeparatorStyle$, separatorStyle); } else { objc_setSeparatorStyle(this, setSeparatorStyle$, separatorStyle); }
    }
    
    private static final Selector style = Selector.register("style");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewStyle objc_getStyle(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewStyle objc_getStyleSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/style">@property(nonatomic, readonly) UITableViewStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewStyle getStyle() {
        if (customClass) { return objc_getStyleSuper(getSuper(), this, style); } else { return objc_getStyle(this, style); }
    }
    
    private static final Selector tableFooterView = Selector.register("tableFooterView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getTableFooterView(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getTableFooterViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTableFooterView() {
        if (customClass) { return objc_getTableFooterViewSuper(getSuper(), this, tableFooterView); } else { return objc_getTableFooterView(this, tableFooterView); }
    }
    
    private static final Selector setTableFooterView$ = Selector.register("setTableFooterView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTableFooterView(UITableView __self__, Selector __cmd__, UIView tableFooterView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTableFooterViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIView tableFooterView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableFooterView">@property(nonatomic, retain) UIView *tableFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTableFooterView(UIView tableFooterView) {
        if (customClass) { objc_setTableFooterViewSuper(getSuper(), this, setTableFooterView$, tableFooterView); } else { objc_setTableFooterView(this, setTableFooterView$, tableFooterView); }
    }
    
    private static final Selector tableHeaderView = Selector.register("tableHeaderView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getTableHeaderView(UITableView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getTableHeaderViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTableHeaderView() {
        if (customClass) { return objc_getTableHeaderViewSuper(getSuper(), this, tableHeaderView); } else { return objc_getTableHeaderView(this, tableHeaderView); }
    }
    
    private static final Selector setTableHeaderView$ = Selector.register("setTableHeaderView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTableHeaderView(UITableView __self__, Selector __cmd__, UIView tableHeaderView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTableHeaderViewSuper(ObjCSuper __super__, UITableView __self__, Selector __cmd__, UIView tableHeaderView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableView_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableView/tableHeaderView">@property(nonatomic, retain) UIView *tableHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTableHeaderView(UIView tableHeaderView) {
        if (customClass) { objc_setTableHeaderViewSuper(getSuper(), this, setTableHeaderView$, tableHeaderView); } else { objc_setTableHeaderView(this, setTableHeaderView$, tableHeaderView); }
    }
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
