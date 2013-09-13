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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html">UITableViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITableViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:accessoryButtonTappedForRowWithIndexPath:">- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void accessoryButtonTapped(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:canPerformAction:forRowAtIndexPath:withSender:">- (BOOL)tableView:(UITableView *)tableView canPerformAction:(SEL)action forRowAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    boolean canPerformAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didDeselectRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    void didDeselectRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingCell:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didEndDisplayingCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    void didEndDisplayingCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingFooterView:forSection:">- (void)tableView:(UITableView *)tableView didEndDisplayingFooterView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    void didEndDisplayingFooterView(UITableView tableView, UIView view, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndDisplayingHeaderView:forSection:">- (void)tableView:(UITableView *)tableView didEndDisplayingHeaderView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    void didEndDisplayingHeaderView(UITableView tableView, UIView view, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didEndEditingRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didEndEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndEditingRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didHighlightRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didHighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    void didHighlightRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didSelectRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void didSelectRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:didUnhighlightRowAtIndexPath:">- (void)tableView:(UITableView *)tableView didUnhighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    void didUnhighlightRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:">- (NSString *)tableView:(UITableView *)tableView titleForDeleteConfirmationButtonForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    String getDeleteConfirmationButtonTitle(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:editingStyleForRowAtIndexPath:">- (UITableViewCellEditingStyle)tableView:(UITableView *)tableView editingStyleForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    UITableViewCellEditingStyle getRowEditingStyle(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForRowAtIndexPath:">- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    float getRowHeight(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:indentationLevelForRowAtIndexPath:">- (NSInteger)tableView:(UITableView *)tableView indentationLevelForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    int getRowIndentationLevel(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForFooterInSection:">- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    float getSectionFooterHeight(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:viewForFooterInSection:">- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    UIView getSectionFooterView(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:heightForHeaderInSection:">- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    float getSectionHeaderHeight(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:viewForHeaderInSection:">- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section</a>
     * @since Available in iOS 2.0 and later.
     */
    UIView getSectionHeaderView(UITableView tableView, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView targetIndexPathForMoveFromRowAtIndexPath:(NSIndexPath *)sourceIndexPath toProposedIndexPath:(NSIndexPath *)proposedDestinationIndexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    NSIndexPath getTargetForMove(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath proposedDestinationIndexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:performAction:forRowAtIndexPath:withSender:">- (void)tableView:(UITableView *)tableView performAction:(SEL)action forRowAtIndexPath:(NSIndexPath *)indexPath withSender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    void performAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldHighlightRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldHighlightRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldHighlightRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldIndentWhileEditingRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldIndentWhileEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldIndentWhileEditingRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:shouldShowMenuForRowAtIndexPath:">- (BOOL)tableView:(UITableView *)tableView shouldShowMenuForRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 5.0 and later.
     */
    boolean shouldShowMenuForRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willBeginEditingRowAtIndexPath:">- (void)tableView:(UITableView *)tableView willBeginEditingRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginEditingRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDeselectRowAtIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView willDeselectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 3.0 and later.
     */
    NSIndexPath willDeselectRow(UITableView tableView, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayCell:forRowAtIndexPath:">- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    void willDisplayCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayFooterView:forSection:">- (void)tableView:(UITableView *)tableView willDisplayFooterView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    void willDisplayFooterView(UITableView tableView, UIView view, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willDisplayHeaderView:forSection:">- (void)tableView:(UITableView *)tableView willDisplayHeaderView:(UIView *)view forSection:(NSInteger)section</a>
     * @since Available in iOS 6.0 and later.
     */
    void willDisplayHeaderView(UITableView tableView, UIView view, int section);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITableViewDelegate/tableView:willSelectRowAtIndexPath:">- (NSIndexPath *)tableView:(UITableView *)tableView willSelectRowAtIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 2.0 and later.
     */
    NSIndexPath willSelectRow(UITableView tableView, NSIndexPath indexPath);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UIScrollViewDelegate.Adapter implements UITableViewDelegate {
        @NotImplemented("tableView:accessoryButtonTappedForRowWithIndexPath:") public void accessoryButtonTapped(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:canPerformAction:forRowAtIndexPath:withSender:") public boolean canPerformAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didDeselectRowAtIndexPath:") public void didDeselectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didEndDisplayingCell:forRowAtIndexPath:") public void didEndDisplayingCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didEndDisplayingFooterView:forSection:") public void didEndDisplayingFooterView(UITableView tableView, UIView view, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didEndDisplayingHeaderView:forSection:") public void didEndDisplayingHeaderView(UITableView tableView, UIView view, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didEndEditingRowAtIndexPath:") public void didEndEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didHighlightRowAtIndexPath:") public void didHighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didSelectRowAtIndexPath:") public void didSelectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:didUnhighlightRowAtIndexPath:") public void didUnhighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:") public String getDeleteConfirmationButtonTitle(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:editingStyleForRowAtIndexPath:") public UITableViewCellEditingStyle getRowEditingStyle(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:heightForRowAtIndexPath:") public float getRowHeight(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:indentationLevelForRowAtIndexPath:") public int getRowIndentationLevel(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:heightForFooterInSection:") public float getSectionFooterHeight(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:viewForFooterInSection:") public UIView getSectionFooterView(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:heightForHeaderInSection:") public float getSectionHeaderHeight(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:viewForHeaderInSection:") public UIView getSectionHeaderView(UITableView tableView, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:") public NSIndexPath getTargetForMove(UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath proposedDestinationIndexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:performAction:forRowAtIndexPath:withSender:") public void performAction(UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:shouldHighlightRowAtIndexPath:") public boolean shouldHighlightRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:shouldIndentWhileEditingRowAtIndexPath:") public boolean shouldIndentWhileEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:shouldShowMenuForRowAtIndexPath:") public boolean shouldShowMenuForRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willBeginEditingRowAtIndexPath:") public void willBeginEditingRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willDeselectRowAtIndexPath:") public NSIndexPath willDeselectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willDisplayCell:forRowAtIndexPath:") public void willDisplayCell(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willDisplayFooterView:forSection:") public void willDisplayFooterView(UITableView tableView, UIView view, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willDisplayHeaderView:forSection:") public void willDisplayHeaderView(UITableView tableView, UIView view, int section) { throw new UnsupportedOperationException(); }
        @NotImplemented("tableView:willSelectRowAtIndexPath:") public NSIndexPath willSelectRow(UITableView tableView, NSIndexPath indexPath) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("tableView:accessoryButtonTappedForRowWithIndexPath:") public static void accessoryButtonTapped(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.accessoryButtonTapped(tableView, indexPath); }
        @Callback @BindSelector("tableView:canPerformAction:forRowAtIndexPath:withSender:") public static boolean canPerformAction(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { return __self__.canPerformAction(tableView, action, indexPath, sender); }
        @Callback @BindSelector("tableView:didDeselectRowAtIndexPath:") public static void didDeselectRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.didDeselectRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:didEndDisplayingCell:forRowAtIndexPath:") public static void didEndDisplayingCell(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { __self__.didEndDisplayingCell(tableView, cell, indexPath); }
        @Callback @BindSelector("tableView:didEndDisplayingFooterView:forSection:") public static void didEndDisplayingFooterView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UIView view, int section) { __self__.didEndDisplayingFooterView(tableView, view, section); }
        @Callback @BindSelector("tableView:didEndDisplayingHeaderView:forSection:") public static void didEndDisplayingHeaderView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UIView view, int section) { __self__.didEndDisplayingHeaderView(tableView, view, section); }
        @Callback @BindSelector("tableView:didEndEditingRowAtIndexPath:") public static void didEndEditingRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.didEndEditingRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:didHighlightRowAtIndexPath:") public static void didHighlightRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.didHighlightRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:didSelectRowAtIndexPath:") public static void didSelectRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.didSelectRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:didUnhighlightRowAtIndexPath:") public static void didUnhighlightRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.didUnhighlightRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:") public static String getDeleteConfirmationButtonTitle(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.getDeleteConfirmationButtonTitle(tableView, indexPath); }
        @Callback @BindSelector("tableView:editingStyleForRowAtIndexPath:") public static UITableViewCellEditingStyle getRowEditingStyle(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.getRowEditingStyle(tableView, indexPath); }
        @Callback @BindSelector("tableView:heightForRowAtIndexPath:") public static float getRowHeight(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.getRowHeight(tableView, indexPath); }
        @Callback @BindSelector("tableView:indentationLevelForRowAtIndexPath:") public static int getRowIndentationLevel(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.getRowIndentationLevel(tableView, indexPath); }
        @Callback @BindSelector("tableView:heightForFooterInSection:") public static float getSectionFooterHeight(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionFooterHeight(tableView, section); }
        @Callback @BindSelector("tableView:viewForFooterInSection:") public static UIView getSectionFooterView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionFooterView(tableView, section); }
        @Callback @BindSelector("tableView:heightForHeaderInSection:") public static float getSectionHeaderHeight(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionHeaderHeight(tableView, section); }
        @Callback @BindSelector("tableView:viewForHeaderInSection:") public static UIView getSectionHeaderView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, int section) { return __self__.getSectionHeaderView(tableView, section); }
        @Callback @BindSelector("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:") public static NSIndexPath getTargetForMove(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath sourceIndexPath, NSIndexPath proposedDestinationIndexPath) { return __self__.getTargetForMove(tableView, sourceIndexPath, proposedDestinationIndexPath); }
        @Callback @BindSelector("tableView:performAction:forRowAtIndexPath:withSender:") public static void performAction(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, Selector action, NSIndexPath indexPath, NSObject sender) { __self__.performAction(tableView, action, indexPath, sender); }
        @Callback @BindSelector("tableView:shouldHighlightRowAtIndexPath:") public static boolean shouldHighlightRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.shouldHighlightRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:shouldIndentWhileEditingRowAtIndexPath:") public static boolean shouldIndentWhileEditingRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.shouldIndentWhileEditingRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:shouldShowMenuForRowAtIndexPath:") public static boolean shouldShowMenuForRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.shouldShowMenuForRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:willBeginEditingRowAtIndexPath:") public static void willBeginEditingRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { __self__.willBeginEditingRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:willDeselectRowAtIndexPath:") public static NSIndexPath willDeselectRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.willDeselectRow(tableView, indexPath); }
        @Callback @BindSelector("tableView:willDisplayCell:forRowAtIndexPath:") public static void willDisplayCell(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) { __self__.willDisplayCell(tableView, cell, indexPath); }
        @Callback @BindSelector("tableView:willDisplayFooterView:forSection:") public static void willDisplayFooterView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UIView view, int section) { __self__.willDisplayFooterView(tableView, view, section); }
        @Callback @BindSelector("tableView:willDisplayHeaderView:forSection:") public static void willDisplayHeaderView(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, UIView view, int section) { __self__.willDisplayHeaderView(tableView, view, section); }
        @Callback @BindSelector("tableView:willSelectRowAtIndexPath:") public static NSIndexPath willSelectRow(UITableViewDelegate __self__, Selector __cmd__, UITableView tableView, NSIndexPath indexPath) { return __self__.willSelectRow(tableView, indexPath); }
    }
    /*</callbacks>*/

}
