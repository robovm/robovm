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
package org.robovm.apple.social;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.accounts.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Social") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SLComposeServiceViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*/implements UITextViewDelegate/*</implements>*/ {

    /*<ptr>*/public static class SLComposeServiceViewControllerPtr extends Ptr<SLComposeServiceViewController, SLComposeServiceViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SLComposeServiceViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SLComposeServiceViewController() {}
    protected SLComposeServiceViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "textView")
    public native UITextView getTextView();
    @Property(selector = "contentText")
    public native String getContentText();
    @Property(selector = "placeholder")
    public native String getPlaceholder();
    @Property(selector = "setPlaceholder:")
    public native void setPlaceholder(String v);
    @Property(selector = "charactersRemaining")
    public native NSNumber getCharactersRemaining();
    @Property(selector = "setCharactersRemaining:")
    public native void setCharactersRemaining(NSNumber v);
    @Property(selector = "autoCompletionViewController")
    public native UIViewController getAutoCompletionViewController();
    @Property(selector = "setAutoCompletionViewController:")
    public native void setAutoCompletionViewController(UIViewController v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "presentationAnimationDidFinish")
    public native void presentationAnimationDidFinish();
    @Method(selector = "didSelectPost")
    public native void didSelectPost();
    @Method(selector = "didSelectCancel")
    public native void didSelectCancel();
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "isContentValid")
    public native boolean isContentValid();
    @Method(selector = "validateContent")
    public native void validateContent();
    @Method(selector = "configurationItems")
    public native NSArray<SLComposeSheetConfigurationItem> getConfigurationItems();
    @Method(selector = "reloadConfigurationItems")
    public native void reloadConfigurationItems();
    @Method(selector = "pushConfigurationViewController:")
    public native void pushConfigurationViewController(UIViewController viewController);
    @Method(selector = "popConfigurationViewController")
    public native void popConfigurationViewController();
    @Method(selector = "loadPreviewView")
    public native UIView loadPreviewView();
    @Method(selector = "textViewShouldBeginEditing:")
    public native boolean shouldBeginEditing(UITextView textView);
    @Method(selector = "textViewShouldEndEditing:")
    public native boolean shouldEndEditing(UITextView textView);
    @Method(selector = "textViewDidBeginEditing:")
    public native void didBeginEditing(UITextView textView);
    @Method(selector = "textViewDidEndEditing:")
    public native void didEndEditing(UITextView textView);
    @Method(selector = "textView:shouldChangeTextInRange:replacementText:")
    public native boolean shouldChangeCharacters(UITextView textView, @ByVal NSRange range, String text);
    @Method(selector = "textViewDidChange:")
    public native void didChange(UITextView textView);
    @Method(selector = "textViewDidChangeSelection:")
    public native void didChangeSelection(UITextView textView);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "textView:shouldInteractWithURL:inRange:")
    public native boolean shouldInteractWithURL(UITextView textView, NSURL URL, @ByVal NSRange characterRange);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "textView:shouldInteractWithTextAttachment:inRange:")
    public native boolean shouldInteractWithTextAttachment(UITextView textView, NSTextAttachment textAttachment, @ByVal NSRange characterRange);
    @Method(selector = "scrollViewDidScroll:")
    public native void didScroll(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewDidZoom:")
    public native void didZoom(UIScrollView scrollView);
    @Method(selector = "scrollViewWillBeginDragging:")
    public native void willBeginDragging(UIScrollView scrollView);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scrollViewWillEndDragging:withVelocity:targetContentOffset:")
    public native void willEndDragging(UIScrollView scrollView, @ByVal CGPoint velocity, CGPoint targetContentOffset);
    @Method(selector = "scrollViewDidEndDragging:willDecelerate:")
    public native void didEndDragging(UIScrollView scrollView, boolean decelerate);
    @Method(selector = "scrollViewWillBeginDecelerating:")
    public native void willBeginDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndDecelerating:")
    public native void didEndDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndScrollingAnimation:")
    public native void didEndScrollingAnimation(UIScrollView scrollView);
    @Method(selector = "viewForZoomingInScrollView:")
    public native UIView getViewForZooming(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewWillBeginZooming:withView:")
    public native void willBeginZooming(UIScrollView scrollView, UIView view);
    @Method(selector = "scrollViewDidEndZooming:withView:atScale:")
    public native void didEndZooming(UIScrollView scrollView, UIView view, @MachineSizedFloat double scale);
    @Method(selector = "scrollViewShouldScrollToTop:")
    public native boolean shouldScrollToTop(UIScrollView scrollView);
    @Method(selector = "scrollViewDidScrollToTop:")
    public native void didScrollToTop(UIScrollView scrollView);
    /*</methods>*/
}
