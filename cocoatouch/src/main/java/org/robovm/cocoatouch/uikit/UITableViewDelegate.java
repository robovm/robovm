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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html">UITableViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UITableViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:accessoryButtonTappedForRowWithIndexPath:">- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:accessoryButtonTappedForRowWithIndexPath:") @Type("void") void accessoryButtonTapped(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:canPerformAction:forRowAtIndexPath:withSender:">- (BOOL)tableView:(UITableView *)tableView canPerformAction:(SEL)action forRowAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tableView:canPerformAction:forRowAtIndexPath:withSender:") @Type("BOOL") boolean canPerformAction(@Type("UITableView *") UITableView tableView, @Type("SEL") Selector action, @Type("NSIndexPath *") NSIndexPath indexPath, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didDeselectRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tableView:didDeselectRowAtIndexPath:") @Type("void") void didDeselectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingCell:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didEndDisplayingCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:didEndDisplayingCell:forRowAtIndexPath:") @Type("void") void didEndDisplayingCell(@Type("UITableView *") UITableView tableView, @Type("UITableViewCell *") UITableViewCell cell, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingFooterView:forSection:">- (void)tableView:(UITableView *)tableView didEndDisplayingFooterView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:didEndDisplayingFooterView:forSection:") @Type("void") void didEndDisplayingFooterView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingHeaderView:forSection:">- (void)tableView:(UITableView *)tableView didEndDisplayingHeaderView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:didEndDisplayingHeaderView:forSection:") @Type("void") void didEndDisplayingHeaderView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndEditingRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didEndEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:didEndEditingRowAtIndexPath:") @Type("void") void didEndEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didHighlightRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didHighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:didHighlightRowAtIndexPath:") @Type("void") void didHighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didSelectRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:didSelectRowAtIndexPath:") @Type("void") void didSelectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didUnhighlightRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didUnhighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:didUnhighlightRowAtIndexPath:") @Type("void") void didUnhighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:">- (NSString *)tableView:(UITableView *)tableView titleForDeleteConfirmationButtonForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:") @Type("NSString *") String getDeleteConfirmationButtonTitle(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:editingStyleForRowAtIndexPath:">- (UITableViewCellEditingStyle)tableView:(UITableView *)tableView editingStyleForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:editingStyleForRowAtIndexPath:") @Type("UITableViewCellEditingStyle") UITableViewCellEditingStyle getRowEditingStyle(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForRowAtIndexPath:">- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:heightForRowAtIndexPath:") @Type("CGFloat") float getRowHeight(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:indentationLevelForRowAtIndexPath:">- (NSInteger)tableView:(UITableView *)tableView indentationLevelForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:indentationLevelForRowAtIndexPath:") @Type("NSInteger") int getRowIndentationLevel(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForFooterInSection:">- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:heightForFooterInSection:") @Type("CGFloat") float getSectionFooterHeight(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:viewForFooterInSection:">- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:viewForFooterInSection:") @Type("UIView *") UIView getSectionFooterView(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForHeaderInSection:">- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:heightForHeaderInSection:") @Type("CGFloat") float getSectionHeaderHeight(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:viewForHeaderInSection:">- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:viewForHeaderInSection:") @Type("UIView *") UIView getSectionHeaderView(@Type("UITableView *") UITableView tableView, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView targetIndexPathForMoveFromRowAtIndexPath:(NSIndexPath *)sourceIndexPath toProposedIndexPath:(NSIndexPath *)proposedDestinationIndexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:") @Type("NSIndexPath *") NSIndexPath getTargetForMove(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath sourceIndexPath, @Type("NSIndexPath *") NSIndexPath proposedDestinationIndexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:performAction:forRowAtIndexPath:withSender:">- (void)tableView:(UITableView *)tableView performAction:(SEL)action forRowAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tableView:performAction:forRowAtIndexPath:withSender:") @Type("void") void performAction(@Type("UITableView *") UITableView tableView, @Type("SEL") Selector action, @Type("NSIndexPath *") NSIndexPath indexPath, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldHighlightRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldHighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:shouldHighlightRowAtIndexPath:") @Type("BOOL") boolean shouldHighlightRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldIndentWhileEditingRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldIndentWhileEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:shouldIndentWhileEditingRowAtIndexPath:") @Type("BOOL") boolean shouldIndentWhileEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldShowMenuForRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldShowMenuForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tableView:shouldShowMenuForRowAtIndexPath:") @Type("BOOL") boolean shouldShowMenuForRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willBeginEditingRowAtIndexPath:">- (void)tableView:(UITableView *)tableView willBeginEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:willBeginEditingRowAtIndexPath:") @Type("void") void willBeginEditingRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDeselectRowAtIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView willDeselectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tableView:willDeselectRowAtIndexPath:") @Type("NSIndexPath *") NSIndexPath willDeselectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayCell:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:willDisplayCell:forRowAtIndexPath:") @Type("void") void willDisplayCell(@Type("UITableView *") UITableView tableView, @Type("UITableViewCell *") UITableViewCell cell, @Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayFooterView:forSection:">- (void)tableView:(UITableView *)tableView willDisplayFooterView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:willDisplayFooterView:forSection:") @Type("void") void willDisplayFooterView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayHeaderView:forSection:">- (void)tableView:(UITableView *)tableView willDisplayHeaderView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tableView:willDisplayHeaderView:forSection:") @Type("void") void willDisplayHeaderView(@Type("UITableView *") UITableView tableView, @Type("UIView *") UIView view, @Type("NSInteger") int section);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willSelectRowAtIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView willSelectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView:willSelectRowAtIndexPath:") @Type("NSIndexPath *") NSIndexPath willSelectRow(@Type("UITableView *") UITableView tableView, @Type("NSIndexPath *") NSIndexPath indexPath);
    /*</methods>*/

}
