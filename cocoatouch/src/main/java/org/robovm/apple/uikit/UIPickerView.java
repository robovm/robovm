/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPickerView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding, UITableViewDataSource/*</implements>*/ {

    /*<ptr>*/public static class UIPickerViewPtr extends Ptr<UIPickerView, UIPickerViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPickerView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPickerView() {}
    protected UIPickerView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIPickerView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "dataSource")
    public native UIPickerViewDataSource getDataSource();
    @Property(selector = "setDataSource:", strongRef = true)
    public native void setDataSource(UIPickerViewDataSource v);
    @Property(selector = "delegate")
    public native UIPickerViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIPickerViewDelegate v);
    @Property(selector = "showsSelectionIndicator")
    public native boolean showsSelectionIndicator();
    @Property(selector = "setShowsSelectionIndicator:")
    public native void setShowsSelectionIndicator(boolean v);
    @Property(selector = "numberOfComponents")
    public native @MachineSizedSInt long getNumberOfComponents();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "numberOfRowsInComponent:")
    public native @MachineSizedSInt long getNumberOfRows(@MachineSizedSInt long component);
    @Method(selector = "rowSizeForComponent:")
    public native @ByVal CGSize getRowSize(@MachineSizedSInt long component);
    @Method(selector = "viewForRow:forComponent:")
    public native UIView getViewForRow(@MachineSizedSInt long row, @MachineSizedSInt long component);
    @Method(selector = "reloadAllComponents")
    public native void reloadAllComponents();
    @Method(selector = "reloadComponent:")
    public native void reloadComponent(@MachineSizedSInt long component);
    @Method(selector = "selectRow:inComponent:animated:")
    public native void selectRow(@MachineSizedSInt long row, @MachineSizedSInt long component, boolean animated);
    @Method(selector = "selectedRowInComponent:")
    public native @MachineSizedSInt long getSelectedRow(@MachineSizedSInt long component);
    @Method(selector = "tableView:numberOfRowsInSection:")
    public native @MachineSizedSInt long getNumberOfRowsInSection(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:cellForRowAtIndexPath:")
    public native UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "numberOfSectionsInTableView:")
    public native @MachineSizedSInt long getNumberOfSections(UITableView tableView);
    @Method(selector = "tableView:titleForHeaderInSection:")
    public native String getTitleForHeader(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:titleForFooterInSection:")
    public native String getTitleForFooter(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:canEditRowAtIndexPath:")
    public native boolean canEditRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:canMoveRowAtIndexPath:")
    public native boolean canMoveRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "sectionIndexTitlesForTableView:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSectionIndexTitles(UITableView tableView);
    @Method(selector = "tableView:sectionForSectionIndexTitle:atIndex:")
    public native @MachineSizedSInt long getSectionForSectionIndexTitle(UITableView tableView, String title, @MachineSizedSInt long index);
    @Method(selector = "tableView:commitEditingStyle:forRowAtIndexPath:")
    public native void commitEditingStyleForRow(UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath);
    @Method(selector = "tableView:moveRowAtIndexPath:toIndexPath:")
    public native void moveRow(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath destinationIndexPath);
    /*</methods>*/
}
