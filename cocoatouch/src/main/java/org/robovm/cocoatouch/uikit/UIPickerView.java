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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html">UIPickerView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPickerView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPickerView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPickerView /*</name>*/.class);

    /*<constructors>*/
    protected UIPickerView(SkipInit skipInit) { super(skipInit); }
    public UIPickerView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dataSource") public native UIPickerViewDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(UIPickerViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIPickerViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIPickerViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/numberOfComponents">@property(nonatomic, readonly) NSInteger numberOfComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfComponents") public native int getNumberOfComponents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsSelectionIndicator") public native boolean isShowsSelectionIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsSelectionIndicator:") public native void setShowsSelectionIndicator(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector numberOfRowsInComponent$ = Selector.register("numberOfRowsInComponent:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getNumberOfRows(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getNumberOfRowsSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/numberOfRowsInComponent:">- (NSInteger)numberOfRowsInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfRows(int component) {
        if (customClass) { return objc_getNumberOfRowsSuper(getSuper(), this, numberOfRowsInComponent$, component); } else { return objc_getNumberOfRows(this, numberOfRowsInComponent$, component); }
    }
    
    private static final Selector rowSizeForComponent$ = Selector.register("rowSizeForComponent:");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getRowSize(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getRowSizeSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/rowSizeForComponent:">- (CGSize)rowSizeForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getRowSize(int component) {
        if (customClass) { return objc_getRowSizeSuper(getSuper(), this, rowSizeForComponent$, component); } else { return objc_getRowSize(this, rowSizeForComponent$, component); }
    }
    
    private static final Selector viewForRow$forComponent$ = Selector.register("viewForRow:forComponent:");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getRowView(UIPickerView __self__, Selector __cmd__, int row, int component);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getRowViewSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int row, int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/viewForRow:forComponent:">- (UIView *)viewForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRowView(int row, int component) {
        if (customClass) { return objc_getRowViewSuper(getSuper(), this, viewForRow$forComponent$, row, component); } else { return objc_getRowView(this, viewForRow$forComponent$, row, component); }
    }
    
    private static final Selector selectedRowInComponent$ = Selector.register("selectedRowInComponent:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getSelectedRow(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getSelectedRowSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectedRowInComponent:">- (NSInteger)selectedRowInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getSelectedRow(int component) {
        if (customClass) { return objc_getSelectedRowSuper(getSuper(), this, selectedRowInComponent$, component); } else { return objc_getSelectedRow(this, selectedRowInComponent$, component); }
    }
    
    private static final Selector reloadAllComponents = Selector.register("reloadAllComponents");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadAllComponents(UIPickerView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadAllComponentsSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadAllComponents">- (void)reloadAllComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadAllComponents() {
        if (customClass) { objc_reloadAllComponentsSuper(getSuper(), this, reloadAllComponents); } else { objc_reloadAllComponents(this, reloadAllComponents); }
    }
    
    private static final Selector reloadComponent$ = Selector.register("reloadComponent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reloadComponent(UIPickerView __self__, Selector __cmd__, int component);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadComponentSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadComponent:">- (void)reloadComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reloadComponent(int component) {
        if (customClass) { objc_reloadComponentSuper(getSuper(), this, reloadComponent$, component); } else { objc_reloadComponent(this, reloadComponent$, component); }
    }
    
    private static final Selector selectRow$inComponent$animated$ = Selector.register("selectRow:inComponent:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_selectRow(UIPickerView __self__, Selector __cmd__, int row, int component, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_selectRowSuper(ObjCSuper __super__, UIPickerView __self__, Selector __cmd__, int row, int component, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectRow:inComponent:animated:">- (void)selectRow:(NSInteger)row inComponent:(NSInteger)component animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void selectRow(int row, int component, boolean animated) {
        if (customClass) { objc_selectRowSuper(getSuper(), this, selectRow$inComponent$animated$, row, component, animated); } else { objc_selectRow(this, selectRow$inComponent$animated$, row, component, animated); }
    }
    /*</methods>*/

}
