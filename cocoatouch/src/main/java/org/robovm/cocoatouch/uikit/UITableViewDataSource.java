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

@Protocol
public interface /*<name>*/ UITableViewDataSource /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("tableView:canEditRowAtIndexPath:") @Type("BOOL") boolean canEditRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:canMoveRowAtIndexPath:") @Type("BOOL") boolean canMoveRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:commitEditingStyle:forRowAtIndexPath:") @Type("void") void commitRowEditingStyle(@Type("UITableView *") UITableView tableView, @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle editingStyle, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:numberOfRowsInSection:") @Type("NSInteger") int getNumberOfRowsInSection(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("numberOfSectionsInTableView:") @Type("NSInteger") int getNumberOfSections(@Type("UITableView *") UITableView tableView);
    @Bind("tableView:cellForRowAtIndexPath:") @Type("UITableViewCell *") UITableViewCell getRowCell(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:titleForFooterInSection:") @Type("NSString *") String getSectionFooterTitle(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("tableView:sectionForSectionIndexTitle:atIndex:") @Type("NSInteger") int getSectionForSectionIndexTitle(@Type("UITableView *") UITableView tableView, @Type("NSString *") String title, @Type("NSInteger") int index);
    @Bind("tableView:titleForHeaderInSection:") @Type("NSString *") String getSectionHeaderTitle(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("sectionIndexTitlesForTableView:") @Type("NSArray *") NSArray getSectionIndexTitles(@Type("UITableView *") UITableView tableView);
    @Bind("tableView:moveRowAtIndexPath:toIndexPath:") @Type("void") void moveRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath fromIndexPath, @Type("NSIndexPath *") NSIndexPath toIndexPath);
    /*</methods>*/

}
