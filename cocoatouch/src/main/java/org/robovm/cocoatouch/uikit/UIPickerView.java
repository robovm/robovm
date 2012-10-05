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

    /*<constructors>*/
    public UIPickerView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dataSource") public native @Type("id<UIPickerViewDataSource>") UIPickerViewDataSource getDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/dataSource">@property(nonatomic, assign) id&amp;lt;UIPickerViewDataSource&amp;gt; dataSource</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UIPickerViewDataSource>") UIPickerViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIPickerViewDelegate>") UIPickerViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/delegate">@property(nonatomic, assign) id&amp;lt;UIPickerViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPickerViewDelegate>") UIPickerViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/numberOfComponents">@property(nonatomic, readonly) NSInteger numberOfComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfComponents") public native @Type("NSInteger") int getNumberOfComponents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsSelectionIndicator") public native @Type("BOOL") boolean isShowsSelectionIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instp/UIPickerView/showsSelectionIndicator">@property(nonatomic) BOOL showsSelectionIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsSelectionIndicator:") public native void setShowsSelectionIndicator(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/numberOfRowsInComponent:">- (NSInteger)numberOfRowsInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfRowsInComponent:") public native @Type("NSInteger") int getNumberOfRows(@Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/rowSizeForComponent:">- (CGSize)rowSizeForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rowSizeForComponent:") public native @Type("CGSize") CGSize getRowSize(@Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/viewForRow:forComponent:">- (UIView *)viewForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewForRow:forComponent:") public native @Type("UIView *") UIView getRowView(@Type("NSInteger") int row, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectedRowInComponent:">- (NSInteger)selectedRowInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedRowInComponent:") public native @Type("NSInteger") int getSelectedRow(@Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadAllComponents">- (void)reloadAllComponents</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reloadAllComponents") public native @Type("void") void reloadAllComponents();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/reloadComponent:">- (void)reloadComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reloadComponent:") public native @Type("void") void reloadComponent(@Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerView_Class/Reference/UIPickerView.html#//apple_ref/occ/instm/UIPickerView/selectRow:inComponent:animated:">- (void)selectRow:(NSInteger)row inComponent:(NSInteger)component animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectRow:inComponent:animated:") public native @Type("void") void selectRow(@Type("NSInteger") int row, @Type("NSInteger") int component, @Type("BOOL") boolean animated);
    /*</methods>*/

}
