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
public interface /*<name>*/ UITableViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("tableView:accessoryButtonTappedForRowWithIndexPath:") @Type("void") void accessoryButtonTapped(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:canPerformAction:forRowAtIndexPath:withSender:") @Type("BOOL") boolean canPerformAction(@Type("UITableView *") UITableView tableView, @Type("SEL") Selector action, @Type("NSIndexPath *") NSIndexPath indexPath, @Type("id") NSObject sender);
    @Bind("tableView:didDeselectRowAtIndexPath:") @Type("void") void didDeselectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:didEndDisplayingCell:forRowAtIndexPath:") @Type("void") void didEndDisplayingCell(@Type("UITableView *") UITableView tableView, @Type("UITableViewCell *") UITableViewCell cell, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:didEndDisplayingFooterView:forSection:") @Type("void") void didEndDisplayingFooterView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    @Bind("tableView:didEndDisplayingHeaderView:forSection:") @Type("void") void didEndDisplayingHeaderView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    @Bind("tableView:didEndEditingRowAtIndexPath:") @Type("void") void didEndEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:didHighlightRowAtIndexPath:") @Type("void") void didHighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:didSelectRowAtIndexPath:") @Type("void") void didSelectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:didUnhighlightRowAtIndexPath:") @Type("void") void didUnhighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:") @Type("NSString *") String getDeleteConfirmationButtonTitle(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:editingStyleForRowAtIndexPath:") @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle getRowEditingStyle(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:heightForRowAtIndexPath:") @Type("CGFloat") float getRowHeight(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:indentationLevelForRowAtIndexPath:") @Type("NSInteger") int getRowIndentationLevel(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:heightForFooterInSection:") @Type("CGFloat") float getSectionFooterHeight(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("tableView:viewForFooterInSection:") @Type("UIView *") UIView getSectionFooterView(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("tableView:heightForHeaderInSection:") @Type("CGFloat") float getSectionHeaderHeight(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("tableView:viewForHeaderInSection:") @Type("UIView *") UIView getSectionHeaderView(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    @Bind("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:") @Type("NSIndexPath *") NSIndexPath getTargetForMove(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath sourceIndexPath, @Type("NSIndexPath *") NSIndexPath proposedDestinationIndexPath);
    @Bind("tableView:performAction:forRowAtIndexPath:withSender:") @Type("void") void performAction(@Type("UITableView *") UITableView tableView, @Type("SEL") Selector action, @Type("NSIndexPath *") NSIndexPath indexPath, @Type("id") NSObject sender);
    @Bind("tableView:shouldHighlightRowAtIndexPath:") @Type("BOOL") boolean shouldHighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:shouldIndentWhileEditingRowAtIndexPath:") @Type("BOOL") boolean shouldIndentWhileEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:shouldShowMenuForRowAtIndexPath:") @Type("BOOL") boolean shouldShowMenuForRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:willBeginEditingRowAtIndexPath:") @Type("void") void willBeginEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:willDeselectRowAtIndexPath:") @Type("NSIndexPath *") NSIndexPath willDeselectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:willDisplayCell:forRowAtIndexPath:") @Type("void") void willDisplayCell(@Type("UITableView *") UITableView tableView, @Type("UITableViewCell *") UITableViewCell cell, @Type("NSIndexPath *") NSIndexPath indexPath);
    @Bind("tableView:willDisplayFooterView:forSection:") @Type("void") void willDisplayFooterView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    @Bind("tableView:willDisplayHeaderView:forSection:") @Type("void") void willDisplayHeaderView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    @Bind("tableView:willSelectRowAtIndexPath:") @Type("NSIndexPath *") NSIndexPath willSelectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /*</methods>*/

}
