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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITableViewDataSourceAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UITableViewDataSource/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("tableView:numberOfRowsInSection:")
    public @MachineSizedSInt long getNumberOfRowsInSection(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:cellForRowAtIndexPath:")
    public UITableViewCell getRowCell(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("numberOfSectionsInTableView:")
    public @MachineSizedSInt long getNumberOfSections(UITableView tableView) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:titleForHeaderInSection:")
    public String getSectionHeaderTitle(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:titleForFooterInSection:")
    public String getSectionFooterTitle(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:canEditRowAtIndexPath:")
    public boolean canEditRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:canMoveRowAtIndexPath:")
    public boolean canMoveRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("sectionIndexTitlesForTableView:")
    public @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSectionIndexTitles(UITableView tableView) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:sectionForSectionIndexTitle:atIndex:")
    public @MachineSizedSInt long getSectionForSectionIndexTitle(UITableView tableView, String title, @MachineSizedSInt long index) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:commitEditingStyle:forRowAtIndexPath:")
    public void commitRowEditingStyle(UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:moveRowAtIndexPath:toIndexPath:")
    public void moveRow(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath destinationIndexPath) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
