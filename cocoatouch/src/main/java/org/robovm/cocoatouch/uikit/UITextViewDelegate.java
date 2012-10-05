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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html">UITextViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UITextViewDelegate /*</name>*/ /*<implements>*/ extends UIScrollViewDelegate /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidBeginEditing:">- (void)textViewDidBeginEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewDidBeginEditing:") @Type("void") void didBeginEditing(@Type("UITextView *") UITextView textView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidChange:">- (void)textViewDidChange:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewDidChange:") @Type("void") void didChange(@Type("UITextView *") UITextView textView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidChangeSelection:">- (void)textViewDidChangeSelection:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewDidChangeSelection:") @Type("void") void didChangeSelection(@Type("UITextView *") UITextView textView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewDidEndEditing:">- (void)textViewDidEndEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewDidEndEditing:") @Type("void") void didEndEditing(@Type("UITextView *") UITextView textView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewShouldBeginEditing:">- (BOOL)textViewShouldBeginEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewShouldBeginEditing:") @Type("BOOL") boolean shouldBeginEditing(@Type("UITextView *") UITextView textView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textView:shouldChangeTextInRange:replacementText:">- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textView:shouldChangeTextInRange:replacementText:") @Type("BOOL") boolean shouldChangeCharacters(@Type("UITextView *") UITextView textView, @Type("NSRange") NSRange range, @Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextViewDelegate_Protocol/Reference/UITextViewDelegate.html#//apple_ref/occ/intfm/UITextViewDelegate/textViewShouldEndEditing:">- (BOOL)textViewShouldEndEditing:(UITextView *)textView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textViewShouldEndEditing:") @Type("BOOL") boolean shouldEndEditing(@Type("UITextView *") UITextView textView);
    /*</methods>*/

}
