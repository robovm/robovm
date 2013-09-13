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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html">UITableViewDataSource Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITableViewDataSource /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:canEditRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean canEditRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:canMoveRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean canMoveRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:commitEditingStyle:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void commitRowEditingStyle(UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:numberOfRowsInSection:">- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    int getNumberOfRowsInSection(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/numberOfSectionsInTableView:">- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    int getNumberOfSections(UITableView tableView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:cellForRowAtIndexPath:">- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    UITableViewCell getRowCell(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:titleForFooterInSection:">- (NSString *)tableView:(UITableView *)tableView titleForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    String getSectionFooterTitle(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:sectionForSectionIndexTitle:atIndex:">- (NSInteger)tableView:(UITableView *)tableView sectionForSectionIndexTitle:(NSString *)title atIndex:(NSInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    int getSectionForSectionIndexTitle(UITableView tableView, String title, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:titleForHeaderInSection:">- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    String getSectionHeaderTitle(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/sectionIndexTitlesForTableView:">- (NSArray *)sectionIndexTitlesForTableView:(UITableView *)tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    NSArray getSectionIndexTitles(UITableView tableView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDataSource_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDataSource/tableView:moveRowAtIndexPath:toIndexPath:">- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void moveRow(UITableView tableView, NSIndexPath fromIndexPath, NSIndexPath toIndexPath);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITableViewDataSource {
        @NotImplemented("tableView:canEditRowAtIndexPath:") public boolean canEditRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:canMoveRowAtIndexPath:") public boolean canMoveRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:commitEditingStyle:forRowAtIndexPath:") public void commitRowEditingStyle(UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:numberOfRowsInSection:") public int getNumberOfRowsInSection(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("numberOfSectionsInTableView:") public int getNumberOfSections(UITableView tableView) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:cellForRowAtIndexPath:") public UITableViewCell getRowCell(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:titleForFooterInSection:") public String getSectionFooterTitle(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:sectionForSectionIndexTitle:atIndex:") public int getSectionForSectionIndexTitle(UITableView tableView, String title, int index) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:titleForHeaderInSection:") public String getSectionHeaderTitle(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("sectionIndexTitlesForTableView:") public NSArray getSectionIndexTitles(UITableView tableView) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:moveRowAtIndexPath:toIndexPath:") public void moveRow(UITableView tableView, NSIndexPath fromIndexPath, NSIndexPath toIndexPath) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("tableView:canEditRowAtIndexPath:") public static boolean canEditRow(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.canEditRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:canMoveRowAtIndexPath:") public static boolean canMoveRow(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.canMoveRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:commitEditingStyle:forRowAtIndexPath:") public static void commitRowEditingStyle(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, UITableViewCellEditingStyle editingStyle, NSIndexPath indexPath) { __self__.commitRowEditingStyle(tableView, editingStyle, indexPath); }
        @Callback @BindSelector("tableView:numberOfRowsInSection:") public static int getNumberOfRowsInSection(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getNumberOfRowsInSection(tableView, section); }
        @Callback @BindSelector("numberOfSectionsInTableView:") public static int getNumberOfSections(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView) { return __self__.getNumberOfSections(tableView); }
        @Callback @BindSelector("tableView:cellForRowAtIndexPath:") public static UITableViewCell getRowCell(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.getRowCell(tableView, indexPath); }
        @Callback @BindSelector("tableView:titleForFooterInSection:") public static String getSectionFooterTitle(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionFooterTitle(tableView, section); }
        @Callback @BindSelector("tableView:sectionForSectionIndexTitle:atIndex:") public static int getSectionForSectionIndexTitle(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, String title, int index) { return __self__.getSectionForSectionIndexTitle(tableView, title, index); }
        @Callback @BindSelector("tableView:titleForHeaderInSection:") public static String getSectionHeaderTitle(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionHeaderTitle(tableView, section); }
        @Callback @BindSelector("sectionIndexTitlesForTableView:") public static NSArray getSectionIndexTitles(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView) { return __self__.getSectionIndexTitles(tableView); }
        @Callback @BindSelector("tableView:moveRowAtIndexPath:toIndexPath:") public static void moveRow(UITableViewDataSource __self__, Selector __cmd__, UITableView tableView, NSIndexPath fromIndexPath, NSIndexPath toIndexPath) { __self__.moveRow(tableView, fromIndexPath, toIndexPath); }
    }
    /*</callbacks>*/

}
