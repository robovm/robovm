/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITableViewDelegateAdapter/*</name>*/ 
    extends /*<extends>*/UIScrollViewDelegateAdapter/*</extends>*/ 
    /*<implements>*/implements UITableViewDelegate/*</implements>*/ {

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
    @NotImplemented("tableView:willDisplayCell:forRowAtIndexPath:")
    public void willDisplayCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:willDisplayHeaderView:forSection:")
    public void willDisplayHeaderView(UITableView tableView, UIView view, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:willDisplayFooterView:forSection:")
    public void willDisplayFooterView(UITableView tableView, UIView view, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didEndDisplayingCell:forRowAtIndexPath:")
    public void didEndDisplayingCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didEndDisplayingHeaderView:forSection:")
    public void didEndDisplayingHeaderView(UITableView tableView, UIView view, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didEndDisplayingFooterView:forSection:")
    public void didEndDisplayingFooterView(UITableView tableView, UIView view, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:heightForRowAtIndexPath:")
    public @MachineSizedFloat double getRowHeight(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:heightForHeaderInSection:")
    public @MachineSizedFloat double getSectionHeaderHeight(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:heightForFooterInSection:")
    public @MachineSizedFloat double getSectionFooterHeight(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:estimatedHeightForRowAtIndexPath:")
    public @MachineSizedFloat double tableView$estimatedHeightForRowAtIndexPath$(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:estimatedHeightForHeaderInSection:")
    public @MachineSizedFloat double tableView$estimatedHeightForHeaderInSection$(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:estimatedHeightForFooterInSection:")
    public @MachineSizedFloat double tableView$estimatedHeightForFooterInSection$(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:viewForHeaderInSection:")
    public UIView getSectionHeaderView(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:viewForFooterInSection:")
    public UIView getSectionFooterView(UITableView tableView, @MachineSizedSInt long section) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:accessoryTypeForRowWithIndexPath:")
    public UITableViewCellAccessoryType tableView$accessoryTypeForRowWithIndexPath$(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:accessoryButtonTappedForRowWithIndexPath:")
    public void accessoryButtonTapped(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:shouldHighlightRowAtIndexPath:")
    public boolean shouldHighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didHighlightRowAtIndexPath:")
    public void didHighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didUnhighlightRowAtIndexPath:")
    public void didUnhighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:willSelectRowAtIndexPath:")
    public NSIndexPath willSelectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:willDeselectRowAtIndexPath:")
    public NSIndexPath willDeselectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didSelectRowAtIndexPath:")
    public void didSelectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didDeselectRowAtIndexPath:")
    public void didDeselectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:editingStyleForRowAtIndexPath:")
    public UITableViewCellEditingStyle getRowEditingStyle(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:")
    public String getDeleteConfirmationButtonTitle(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:shouldIndentWhileEditingRowAtIndexPath:")
    public boolean shouldIndentWhileEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:willBeginEditingRowAtIndexPath:")
    public void willBeginEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:didEndEditingRowAtIndexPath:")
    public void didEndEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:")
    public NSIndexPath getTargetForMove(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath proposedDestinationIndexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:indentationLevelForRowAtIndexPath:")
    public @MachineSizedSInt long getRowIndentationLevel(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:shouldShowMenuForRowAtIndexPath:")
    public boolean shouldShowMenuForRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:canPerformAction:forRowAtIndexPath:withSender:")
    public boolean canPerformAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { throw new UnsupportedOperationException(); }
    @NotImplemented("tableView:performAction:forRowAtIndexPath:withSender:")
    public void performAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
