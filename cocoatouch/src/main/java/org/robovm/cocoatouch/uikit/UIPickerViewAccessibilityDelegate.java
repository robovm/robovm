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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewAccessibilityDelegate_Protocol/Introduction/Introduction.html">UIPickerViewAccessibilityDelegate Protocol Reference</a>
 *   @since Available in iOS 4.0 and later.
 * </div>
 */
public interface /*<name>*/ UIPickerViewAccessibilityDelegate /*</name>*/ /*<implements>*/ extends UIPickerViewDelegate, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewAccessibilityDelegate_Protocol/Introduction/Introduction.html#//apple_ref/occ/intfm/UIPickerViewAccessibilityDelegate/pickerView:accessibilityHintForComponent:">- (NSString *)pickerView:(UIPickerView *)pickerView accessibilityHintForComponent:(NSInteger)component</a>
     * @since Available in iOS 4.0 and later.
     */
    String getAccessibilityHint(UIPickerView pickerView, int component);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPickerViewAccessibilityDelegate_Protocol/Introduction/Introduction.html#//apple_ref/occ/intfm/UIPickerViewAccessibilityDelegate/pickerView:accessibilityLabelForComponent:">- (NSString *)pickerView:(UIPickerView *)pickerView accessibilityLabelForComponent:(NSInteger)component</a>
     * @since Available in iOS 4.0 and later.
     */
    String getAccessibilityLabel(UIPickerView pickerView, int component);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UIPickerViewDelegate.Adapter implements UIPickerViewAccessibilityDelegate {
        @NotImplemented("pickerView:accessibilityHintForComponent:") public String getAccessibilityHint(UIPickerView pickerView, int component) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:accessibilityLabelForComponent:") public String getAccessibilityLabel(UIPickerView pickerView, int component) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("pickerView:accessibilityHintForComponent:") public static String getAccessibilityHint(UIPickerViewAccessibilityDelegate __self__, Selector __cmd__, UIPickerView pickerView, int component) { return __self__.getAccessibilityHint(pickerView, component); }
        @Callback @BindSelector("pickerView:accessibilityLabelForComponent:") public static String getAccessibilityLabel(UIPickerViewAccessibilityDelegate __self__, Selector __cmd__, UIPickerView pickerView, int component) { return __self__.getAccessibilityLabel(pickerView, component); }
    }
    /*</callbacks>*/

}
