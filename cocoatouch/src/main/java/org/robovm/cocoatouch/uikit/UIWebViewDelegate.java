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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html">UIWebViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIWebViewDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webView:didFailLoadWithError:">- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFailLoad(UIWebView webView, NSError error);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webViewDidFinishLoad:">- (void)webViewDidFinishLoad:(UIWebView *)webView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didFinishLoad(UIWebView webView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webViewDidStartLoad:">- (void)webViewDidStartLoad:(UIWebView *)webView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didStartLoad(UIWebView webView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebViewDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIWebViewDelegate/webView:shouldStartLoadWithRequest:navigationType:">- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldStartLoad(UIWebView webView, NSURLRequest request, UIWebViewNavigationType navigationType);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIWebViewDelegate {
        @NotImplemented("webView:didFailLoadWithError:") public void didFailLoad(UIWebView webView, NSError error) { throw new UnsupportedOperationException(); }
        @NotImplemented("webViewDidFinishLoad:") public void didFinishLoad(UIWebView webView) { throw new UnsupportedOperationException(); }
        @NotImplemented("webViewDidStartLoad:") public void didStartLoad(UIWebView webView) { throw new UnsupportedOperationException(); }
        @NotImplemented("webView:shouldStartLoadWithRequest:navigationType:") public boolean shouldStartLoad(UIWebView webView, NSURLRequest request, UIWebViewNavigationType navigationType) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("webView:didFailLoadWithError:") public static void didFailLoad(UIWebViewDelegate __self__, Selector __cmd__, UIWebView webView, NSError error) { __self__.didFailLoad(webView, error); }
        @Callback @BindSelector("webViewDidFinishLoad:") public static void didFinishLoad(UIWebViewDelegate __self__, Selector __cmd__, UIWebView webView) { __self__.didFinishLoad(webView); }
        @Callback @BindSelector("webViewDidStartLoad:") public static void didStartLoad(UIWebViewDelegate __self__, Selector __cmd__, UIWebView webView) { __self__.didStartLoad(webView); }
        @Callback @BindSelector("webView:shouldStartLoadWithRequest:navigationType:") public static boolean shouldStartLoad(UIWebViewDelegate __self__, Selector __cmd__, UIWebView webView, NSURLRequest request, UIWebViewNavigationType navigationType) { return __self__.shouldStartLoad(webView, request, navigationType); }
    }
    /*</callbacks>*/

}
