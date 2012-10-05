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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html">UITableViewDataSource Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UITableViewDataSource /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:canEditRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:canEditRowAtIndexPath:") @Type("BOOL") boolean canEditRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:canMoveRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:canMoveRowAtIndexPath:") @Type("BOOL") boolean canMoveRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:commitEditingStyle:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:commitEditingStyle:forRowAtIndexPath:") @Type("void") void commitRowEditingStyle(@Type("UITableView *") UITableView tableView, @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle editingStyle, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:numberOfRowsInSection:">- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:numberOfRowsInSection:") @Type("NSInteger") int getNumberOfRowsInSection(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/numberOfSectionsInTableView:">- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfSectionsInTableView:") @Type("NSInteger") int getNumberOfSections(@Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:cellForRowAtIndexPath:">- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:cellForRowAtIndexPath:") @Type("UITableViewCell *") UITableViewCell getRowCell(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:titleForFooterInSection:">- (NSString *)tableView:(UITableView *)tableView titleForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:titleForFooterInSection:") @Type("NSString *") String getSectionFooterTitle(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:sectionForSectionIndexTitle:atIndex:">- (NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString *)title atIndex:(NSInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:sectionForSectionIndexTitle:atIndex:") @Type("NSInteger") int getSectionForSectionIndexTitle(@Type("UITableView *") UITableView tableView, @Type("NSString *") String title, @Type("NSInteger") int index);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:titleForHeaderInSection:">- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:titleForHeaderInSection:") @Type("NSString *") String getSectionHeaderTitle(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/sectionIndexTitlesForTableView:">- (NSArray *)sectionIndexTitlesForTableView:(UITableView *)tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sectionIndexTitlesForTableView:") @Type("NSArray *") NSArray getSectionIndexTitles(@Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:moveRowAtIndexPath:toIndexPath:">- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:moveRowAtIndexPath:toIndexPath:") @Type("void") void moveRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath fromIndexPath, @Type("NSIndexPath *") NSIndexPath toIndexPath);
    /*</methods>*/

}
