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
    @Bind("dataSource") public native @Type("id<UIPickerViewDataSource>") UIPickerViewDataSource getDataSource();
    @Bind("setDataSource:") public native void setDataSource(@Type("id<UIPickerViewDataSource>") UIPickerViewDataSource v);
    @Bind("delegate") public native @Type("id<UIPickerViewDelegate>") UIPickerViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIPickerViewDelegate>") UIPickerViewDelegate v);
    @Bind("numberOfComponents") public native @Type("NSInteger") int getNumberOfComponents();
    @Bind("showsSelectionIndicator") public native @Type("BOOL") boolean isShowsSelectionIndicator();
    @Bind("setShowsSelectionIndicator:") public native void setShowsSelectionIndicator(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("numberOfRowsInComponent:") public native @Type("NSInteger") int getNumberOfRows(@Type("NSInteger") int component);
    @Bind("rowSizeForComponent:") public native @Type("CGSize") CGSize getRowSize(@Type("NSInteger") int component);
    @Bind("viewForRow:forComponent:") public native @Type("UIView *") UIView getRowView(@Type("NSInteger") int row, @Type("NSInteger") int component);
    @Bind("selectedRowInComponent:") public native @Type("NSInteger") int getSelectedRow(@Type("NSInteger") int component);
    @Bind("reloadAllComponents") public native @Type("void") void reloadAllComponents();
    @Bind("reloadComponent:") public native @Type("void") void reloadComponent(@Type("NSInteger") int component);
    @Bind("selectRow:inComponent:animated:") public native @Type("void") void selectRow(@Type("NSInteger") int row, @Type("NSInteger") int component, @Type("BOOL") boolean animated);
    /*</methods>*/

}
