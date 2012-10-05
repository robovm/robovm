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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html">UIPickerViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIPickerViewDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:didSelectRow:inComponent:">- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pickerView:didSelectRow:inComponent:") @Type("void") void didSelectRow(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int row, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:attributedTitleForRow:forComponent:">- (NSAttributedString *)pickerView:(UIPickerView *)pickerView attributedTitleForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("pickerView:attributedTitleForRow:forComponent:") @Type("NSAttributedString *") NSAttributedString getAttributedRowTitle(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int row, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:widthForComponent:">- (CGFloat)pickerView:(UIPickerView *)pickerView  widthForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pickerView:widthForComponent:") @Type("CGFloat") float getComponentWidth(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:rowHeightForComponent:">- (CGFloat)pickerView:(UIPickerView *)pickerView rowHeightForComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pickerView:rowHeightForComponent:") @Type("CGFloat") float getRowHeight(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:titleForRow:forComponent:">- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pickerView:titleForRow:forComponent:") @Type("NSString *") String getRowTitle(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int row, @Type("NSInteger") int component);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewDelegate_Protocol/Reference/UIPickerViewDelegate.html#//apple_ref/occ/intfm/UIPickerViewDelegate/pickerView:viewForRow:forComponent:reusingView:">- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pickerView:viewForRow:forComponent:reusingView:") @Type("UIView *") UIView getRowView(@Type("UIPickerView *") UIPickerView pickerView, @Type("NSInteger") int row, @Type("NSInteger") int component, @Type("UIView *") UIView view);
    /*</methods>*/

}
