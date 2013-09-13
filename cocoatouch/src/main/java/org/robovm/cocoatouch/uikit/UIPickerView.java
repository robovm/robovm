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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html">UIPickerView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPickerView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPickerView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPickerView /*</name>*/.class);

    public UIPickerView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIPickerView(SkipInit skipInit) { super(skipInit); }
    public UIPickerView() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector dataSource = Selector.register("dataSource");
    @Bridge private native static UIPickerViewDataSource objc_getDataSource(UIPickerView __self__, Selector __cmd__);
    @Bridge private native static UIPickerViewDataSource objc_getDataSourceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIPickerViewDataSource getDataSource() {
        if (customClass) { return objc_getDataSourceSuper(getSuper(), dataSource); } else { return objc_getDataSource(this, dataSource); }
    }
    
    private static final Selector setDataSource$ = Selector.register("setDataSource:");
    @Bridge private native static void objc_setDataSource(UIPickerView __self__, Selector __cmd__, UIPickerViewDataSource dataSource);
    @Bridge private native static void objc_setDataSourceSuper(ObjCSuper __super__, Selector __cmd__, UIPickerViewDataSource dataSource);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDataSource(UIPickerViewDataSource dataSource) {
        if (customClass) { objc_setDataSourceSuper(getSuper(), setDataSource$, dataSource); } else { objc_setDataSource(this, setDataSource$, dataSource); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIPickerViewDelegate objc_getDelegate(UIPickerView __self__, Selector __cmd__);
    @Bridge private native static UIPickerViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIPickerViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIPickerView __self__, Selector __cmd__, UIPickerViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIPickerViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIPickerViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector numberOfComponents = Selector.register("numberOfComponents");
    @Bridge private native static int objc_getNumberOfComponents(UIPickerView __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfComponentsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/numberOfComponents">@property(nonatomic, readonly) NSInteger numberOfComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfComponents() {
        if (customClass) { return objc_getNumberOfComponentsSuper(getSuper(), numberOfComponents); } else { return objc_getNumberOfComponents(this, numberOfComponents); }
    }
    
    private static final Selector showsSelectionIndicator = Selector.register("showsSelectionIndicator");
    @Bridge private native static boolean objc_isShowsSelectionIndicator(UIPickerView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsSelectionIndicatorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsSelectionIndicator() {
        if (customClass) { return objc_isShowsSelectionIndicatorSuper(getSuper(), showsSelectionIndicator); } else { return objc_isShowsSelectionIndicator(this, showsSelectionIndicator); }
    }
    
    private static final Selector setShowsSelectionIndicator$ = Selector.register("setShowsSelectionIndicator:");
    @Bridge private native static void objc_setShowsSelectionIndicator(UIPickerView __self__, Selector __cmd__, boolean showsSelectionIndicator);
    @Bridge private native static void objc_setShowsSelectionIndicatorSuper(ObjCSuper __super__, Selector __cmd__, boolean showsSelectionIndicator);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsSelectionIndicator(boolean showsSelectionIndicator) {
        if (customClass) { objc_setShowsSelectionIndicatorSuper(getSuper(), setShowsSelectionIndicator$, showsSelectionIndicator); } else { objc_setShowsSelectionIndicator(this, setShowsSelectionIndicator$, showsSelectionIndicator); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector numberOfRowsInComponent$ = Selector.register("numberOfRowsInComponent:");
    @Bridge private native static int objc_getNumberOfRows(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge private native static int objc_getNumberOfRowsSuper(ObjCSuper __super__, Selector __cmd__, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/numberOfRowsInComponent:">- (NSInteger)numberOfRowsInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfRows(int component) {
        if (customClass) { return objc_getNumberOfRowsSuper(getSuper(), numberOfRowsInComponent$, component); } else { return objc_getNumberOfRows(this, numberOfRowsInComponent$, component); }
    }
    
    private static final Selector rowSizeForComponent$ = Selector.register("rowSizeForComponent:");
    @Bridge private native static @ByVal CGSize objc_getRowSize(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge private native static @ByVal CGSize objc_getRowSizeSuper(ObjCSuper __super__, Selector __cmd__, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/rowSizeForComponent:">- (CGSize)rowSizeForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getRowSize(int component) {
        if (customClass) { return objc_getRowSizeSuper(getSuper(), rowSizeForComponent$, component); } else { return objc_getRowSize(this, rowSizeForComponent$, component); }
    }
    
    private static final Selector viewForRow$forComponent$ = Selector.register("viewForRow:forComponent:");
    @Bridge private native static UIView objc_getRowView(UIPickerView __self__, Selector __cmd__, int row, int component);
    @Bridge private native static UIView objc_getRowViewSuper(ObjCSuper __super__, Selector __cmd__, int row, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/viewForRow:forComponent:">- (UIView *)viewForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRowView(int row, int component) {
        if (customClass) { return objc_getRowViewSuper(getSuper(), viewForRow$forComponent$, row, component); } else { return objc_getRowView(this, viewForRow$forComponent$, row, component); }
    }
    
    private static final Selector selectedRowInComponent$ = Selector.register("selectedRowInComponent:");
    @Bridge private native static int objc_getSelectedRow(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge private native static int objc_getSelectedRowSuper(ObjCSuper __super__, Selector __cmd__, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectedRowInComponent:">- (NSInteger)selectedRowInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getSelectedRow(int component) {
        if (customClass) { return objc_getSelectedRowSuper(getSuper(), selectedRowInComponent$, component); } else { return objc_getSelectedRow(this, selectedRowInComponent$, component); }
    }
    
    private static final Selector reloadAllComponents = Selector.register("reloadAllComponents");
    @Bridge private native static void objc_reloadAllComponents(UIPickerView __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadAllComponentsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadAllComponents">- (void)reloadAllComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadAllComponents() {
        if (customClass) { objc_reloadAllComponentsSuper(getSuper(), reloadAllComponents); } else { objc_reloadAllComponents(this, reloadAllComponents); }
    }
    
    private static final Selector reloadComponent$ = Selector.register("reloadComponent:");
    @Bridge private native static void objc_reloadComponent(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge private native static void objc_reloadComponentSuper(ObjCSuper __super__, Selector __cmd__, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadComponent:">- (void)reloadComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadComponent(int component) {
        if (customClass) { objc_reloadComponentSuper(getSuper(), reloadComponent$, component); } else { objc_reloadComponent(this, reloadComponent$, component); }
    }
    
    private static final Selector selectRow$inComponent$animated$ = Selector.register("selectRow:inComponent:animated:");
    @Bridge private native static void objc_selectRow(UIPickerView __self__, Selector __cmd__, int row, int component, boolean animated);
    @Bridge private native static void objc_selectRowSuper(ObjCSuper __super__, Selector __cmd__, int row, int component, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectRow:inComponent:animated:">- (void)selectRow:(NSInteger)row inComponent:(NSInteger)component animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void selectRow(int row, int component, boolean animated) {
        if (customClass) { objc_selectRowSuper(getSuper(), selectRow$inComponent$animated$, row, component, animated); } else { objc_selectRow(this, selectRow$inComponent$animated$, row, component, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("dataSource") public static UIPickerViewDataSource getDataSource(UIPickerView __self__, Selector __cmd__) { return __self__.getDataSource(); }
        @Callback @BindSelector("setDataSource:") public static void setDataSource(UIPickerView __self__, Selector __cmd__, UIPickerViewDataSource dataSource) { __self__.setDataSource(dataSource); }
        @Callback @BindSelector("delegate") public static UIPickerViewDelegate getDelegate(UIPickerView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIPickerView __self__, Selector __cmd__, UIPickerViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("numberOfComponents") public static int getNumberOfComponents(UIPickerView __self__, Selector __cmd__) { return __self__.getNumberOfComponents(); }
        @Callback @BindSelector("showsSelectionIndicator") public static boolean isShowsSelectionIndicator(UIPickerView __self__, Selector __cmd__) { return __self__.isShowsSelectionIndicator(); }
        @Callback @BindSelector("setShowsSelectionIndicator:") public static void setShowsSelectionIndicator(UIPickerView __self__, Selector __cmd__, boolean showsSelectionIndicator) { __self__.setShowsSelectionIndicator(showsSelectionIndicator); }
        @Callback @BindSelector("numberOfRowsInComponent:") public static int getNumberOfRows(UIPickerView __self__, Selector __cmd__, int component) { return __self__.getNumberOfRows(component); }
        @Callback @BindSelector("rowSizeForComponent:") public static @ByVal CGSize getRowSize(UIPickerView __self__, Selector __cmd__, int component) { return __self__.getRowSize(component); }
        @Callback @BindSelector("viewForRow:forComponent:") public static UIView getRowView(UIPickerView __self__, Selector __cmd__, int row, int component) { return __self__.getRowView(row, component); }
        @Callback @BindSelector("selectedRowInComponent:") public static int getSelectedRow(UIPickerView __self__, Selector __cmd__, int component) { return __self__.getSelectedRow(component); }
        @Callback @BindSelector("reloadAllComponents") public static void reloadAllComponents(UIPickerView __self__, Selector __cmd__) { __self__.reloadAllComponents(); }
        @Callback @BindSelector("reloadComponent:") public static void reloadComponent(UIPickerView __self__, Selector __cmd__, int component) { __self__.reloadComponent(component); }
        @Callback @BindSelector("selectRow:inComponent:animated:") public static void selectRow(UIPickerView __self__, Selector __cmd__, int row, int component, boolean animated) { __self__.selectRow(row, component, animated); }
    }
    /*</callbacks>*/

}
