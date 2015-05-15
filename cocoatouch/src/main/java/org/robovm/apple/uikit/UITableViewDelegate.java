/*
 * Copyright (C) 2013-2015 RoboVM AB
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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UITableViewDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol, UIScrollViewDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "tableView:willDisplayCell:forRowAtIndexPath:")
    void willDisplayCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:willDisplayHeaderView:forSection:")
    void willDisplayHeaderView(UITableView tableView, UIView view, @MachineSizedSInt long section);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:willDisplayFooterView:forSection:")
    void willDisplayFooterView(UITableView tableView, UIView view, @MachineSizedSInt long section);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:didEndDisplayingCell:forRowAtIndexPath:")
    void didEndDisplayingCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:didEndDisplayingHeaderView:forSection:")
    void didEndDisplayingHeaderView(UITableView tableView, UIView view, @MachineSizedSInt long section);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:didEndDisplayingFooterView:forSection:")
    void didEndDisplayingFooterView(UITableView tableView, UIView view, @MachineSizedSInt long section);
    @Method(selector = "tableView:heightForRowAtIndexPath:")
    @MachineSizedFloat double getHeightForRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:heightForHeaderInSection:")
    @MachineSizedFloat double getHeightForHeader(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:heightForFooterInSection:")
    @MachineSizedFloat double getHeightForFooter(UITableView tableView, @MachineSizedSInt long section);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "tableView:estimatedHeightForRowAtIndexPath:")
    @MachineSizedFloat double getEstimatedHeightForRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "tableView:estimatedHeightForHeaderInSection:")
    @MachineSizedFloat double getEstimatedHeightForHeader(UITableView tableView, @MachineSizedSInt long section);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "tableView:estimatedHeightForFooterInSection:")
    @MachineSizedFloat double getEstimatedHeightForFooter(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:viewForHeaderInSection:")
    UIView getViewForHeader(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:viewForFooterInSection:")
    UIView getViewForFooter(UITableView tableView, @MachineSizedSInt long section);
    @Method(selector = "tableView:accessoryButtonTappedForRowWithIndexPath:")
    void accessoryButtonTapped(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:shouldHighlightRowAtIndexPath:")
    boolean shouldHighlightRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:didHighlightRowAtIndexPath:")
    void didHighlightRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "tableView:didUnhighlightRowAtIndexPath:")
    void didUnhighlightRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:willSelectRowAtIndexPath:")
    NSIndexPath willSelectRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "tableView:willDeselectRowAtIndexPath:")
    NSIndexPath willDeselectRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:didSelectRowAtIndexPath:")
    void didSelectRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "tableView:didDeselectRowAtIndexPath:")
    void didDeselectRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:editingStyleForRowAtIndexPath:")
    UITableViewCellEditingStyle getEditingStyleForRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:")
    String getTitleForDeleteConfirmationButton(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "tableView:editActionsForRowAtIndexPath:")
    NSArray<UITableViewRowAction> getEditActionsForRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:shouldIndentWhileEditingRowAtIndexPath:")
    boolean shouldIndentWhileEditingRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:willBeginEditingRowAtIndexPath:")
    void willBeginEditingRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:didEndEditingRowAtIndexPath:")
    void didEndEditingRow(UITableView tableView, NSIndexPath indexPath);
    @Method(selector = "tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:")
    NSIndexPath getTargetForMove(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath proposedDestinationIndexPath);
    @Method(selector = "tableView:indentationLevelForRowAtIndexPath:")
    @MachineSizedSInt long getIndentationLevelForRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tableView:shouldShowMenuForRowAtIndexPath:")
    boolean shouldShowMenuForRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tableView:canPerformAction:forRowAtIndexPath:withSender:")
    boolean canPerformAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "tableView:performAction:forRowAtIndexPath:withSender:")
    void performActionForRow(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
