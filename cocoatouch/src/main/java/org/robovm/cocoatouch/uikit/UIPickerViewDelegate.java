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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html">UIPickerViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIPickerViewDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:didSelectRow:inComponent:">- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    void didSelectRow(UIPickerView pickerView, int row, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:attributedTitleForRow:forComponent:">- (NSAttributedString *)pickerView:(UIPickerView *)pickerView attributedTitleForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 6.0 and later.
     */
    NSAttributedString getAttributedRowTitle(UIPickerView pickerView, int row, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:widthForComponent:">- (CGFloat)pickerView:(UIPickerView *)pickerView  widthForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    float getComponentWidth(UIPickerView pickerView, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:rowHeightForComponent:">- (CGFloat)pickerView:(UIPickerView *)pickerView rowHeightForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    float getRowHeight(UIPickerView pickerView, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:titleForRow:forComponent:">- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    String getRowTitle(UIPickerView pickerView, int row, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:viewForRow:forComponent:reusingView:">- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    UIView getRowView(UIPickerView pickerView, int row, int component, UIView view);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIPickerViewDelegate {
        @NotImplemented("pickerView:didSelectRow:inComponent:") public void didSelectRow(UIPickerView pickerView, int row, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:attributedTitleForRow:forComponent:") public NSAttributedString getAttributedRowTitle(UIPickerView pickerView, int row, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:widthForComponent:") public float getComponentWidth(UIPickerView pickerView, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:rowHeightForComponent:") public float getRowHeight(UIPickerView pickerView, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:titleForRow:forComponent:") public String getRowTitle(UIPickerView pickerView, int row, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:viewForRow:forComponent:reusingView:") public UIView getRowView(UIPickerView pickerView, int row, int component, UIView view) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("pickerView:didSelectRow:inComponent:") public static void didSelectRow(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int row, int component) { __self__.didSelectRow(pickerView, row, component); }
        @Callback @BindSelector("pickerView:attributedTitleForRow:forComponent:") public static NSAttributedString getAttributedRowTitle(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int row, int component) { return __self__.getAttributedRowTitle(pickerView, row, component); }
        @Callback @BindSelector("pickerView:widthForComponent:") public static float getComponentWidth(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int component) { return __self__.getComponentWidth(pickerView, component); }
        @Callback @BindSelector("pickerView:rowHeightForComponent:") public static float getRowHeight(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int component) { return __self__.getRowHeight(pickerView, component); }
        @Callback @BindSelector("pickerView:titleForRow:forComponent:") public static String getRowTitle(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int row, int component) { return __self__.getRowTitle(pickerView, row, component); }
        @Callback @BindSelector("pickerView:viewForRow:forComponent:reusingView:") public static UIView getRowView(UIPickerViewDelegate __self__, Selector __cmd__, UIPickerView pickerView, int row, int component, UIView view) { return __self__.getRowView(pickerView, row, component, view); }
    }
    /*</callbacks>*/

}
