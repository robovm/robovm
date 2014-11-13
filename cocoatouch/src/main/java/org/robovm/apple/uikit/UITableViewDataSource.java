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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UITableViewDataSource/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "tableView:numberOfRowsInSection:")
    @MachineSizedSInt long getNumberOfRowsInSection(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:cellForRowAtIndexPath:")
    UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "numberOfSectionsInTableView:")
    @MachineSizedSInt long getNumberOfSections(UITableView tableView);
    @Method(selector = "tableView:titleForHeaderInSection:")
    String getTitleForHeader(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:titleForFooterInSection:")
    String getTitleForFooter(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:canEditRowAtIndexPath:")
    boolean canEditRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:canMoveRowAtIndexPath:")
    boolean canMoveRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "sectionIndexTitlesForTableView:")
    @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSectionIndexTitles(UITableView tableView);
    @Method(selector = "tableView:sectionForSectionIndexTitle:atIndex:")
    @MachineSizedSInt long getSectionForSectionIndexTitle(UITableView tableView, String title, @MachineSizedSInt long index);
    @Method(selector = "tableView:commitEditingStyle:forRowAtIndexPath:")
    void commitEditingStyleForRow(UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath);
    @Method(selector = "tableView:moveRowAtIndexPath:toIndexPath:")
    void moveRow(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath destinationIndexPath);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
