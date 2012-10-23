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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html">UIWebViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIWebViewDelegate /*</name>*/ /*<implements>*/ extends ObjCProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webView:didFailLoadWithError:">- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFailLoad(UIWebView webView, NSError error);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webViewDidFinishLoad:">- (void)webViewDidFinishLoad:(UIWebView *)webView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFinishLoad(UIWebView webView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webViewDidStartLoad:">- (void)webViewDidStartLoad:(UIWebView *)webView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didStartLoad(UIWebView webView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webView:shouldStartLoadWithRequest:navigationType:">- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldStartLoad(UIWebView webView, NSURLRequest request, UIWebViewNavigationType navigationType);
    /*</methods>*/

}
