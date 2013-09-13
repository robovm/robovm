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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPickerViewDataSource_Protocol/Reference/Reference.html">UIPickerViewDataSource Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIPickerViewDataSource /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPickerViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPickerViewDataSource/numberOfComponentsInPickerView:">- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView</a>
     * @since Available in iOS 2.0 and later.
     */
    int getNumberOfComponents(UIPickerView pickerView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIPickerViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPickerViewDataSource/pickerView:numberOfRowsInComponent:">- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component</a>
     * @since Available in iOS 2.0 and later.
     */
    int getNumberOfRows(UIPickerView pickerView, int component);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIPickerViewDataSource {
        @NotImplemented("numberOfComponentsInPickerView:") public int getNumberOfComponents(UIPickerView pickerView) { throw new UnsupportedOperationException(); }
        @NotImplemented("pickerView:numberOfRowsInComponent:") public int getNumberOfRows(UIPickerView pickerView, int component) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("numberOfComponentsInPickerView:") public static int getNumberOfComponents(UIPickerViewDataSource __self__, Selector __cmd__, UIPickerView pickerView) { return __self__.getNumberOfComponents(pickerView); }
        @Callback @BindSelector("pickerView:numberOfRowsInComponent:") public static int getNumberOfRows(UIPickerViewDataSource __self__, Selector __cmd__, UIPickerView pickerView, int component) { return __self__.getNumberOfRows(pickerView, component); }
    }
    /*</callbacks>*/

}
