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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html">UIWebView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIWebView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIWebView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIWebView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("allowsInlineMediaPlayback") public native @Type("BOOL") boolean isAllowsInlineMediaPlayback();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setAllowsInlineMediaPlayback:") public native void setAllowsInlineMediaPlayback(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoBack">@property(nonatomic, readonly, getter=canGoBack) BOOL canGoBack</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canGoBack") public native @Type("BOOL") boolean isCanGoBack();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoForward">@property(nonatomic, readonly, getter=canGoForward) BOOL canGoForward</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canGoForward") public native @Type("BOOL") boolean isCanGoForward();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("dataDetectorTypes") public native @Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> getDataDetectorTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDataDetectorTypes:") public native void setDataDetectorTypes(@Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIWebViewDelegate>") UIWebViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIWebViewDelegate>") UIWebViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("keyboardDisplayRequiresUserAction") public native @Type("BOOL") boolean isKeyboardDisplayRequiresUserAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setKeyboardDisplayRequiresUserAction:") public native void setKeyboardDisplayRequiresUserAction(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/loading">@property(nonatomic, readonly, getter=isLoading) BOOL loading</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isLoading") public native @Type("BOOL") boolean isLoading();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("mediaPlaybackAllowsAirPlay") public native @Type("BOOL") boolean isMediaPlaybackAllowsAirPlay();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMediaPlaybackAllowsAirPlay:") public native void setMediaPlaybackAllowsAirPlay(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("mediaPlaybackRequiresUserAction") public native @Type("BOOL") boolean isMediaPlaybackRequiresUserAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setMediaPlaybackRequiresUserAction:") public native void setMediaPlaybackRequiresUserAction(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/request">@property(nonatomic, readonly, retain) NSURLRequest *request</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("request") public native @Type("NSURLRequest *") NSURLRequest getRequest();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scalesPageToFit") public native @Type("BOOL") boolean isScalesPageToFit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScalesPageToFit:") public native void setScalesPageToFit(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scrollView">@property(nonatomic, readonly, retain) UIScrollView *scrollView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scrollView") public native @Type("UIScrollView *") UIScrollView getScrollView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("suppressesIncrementalRendering") public native @Type("BOOL") boolean isSuppressesIncrementalRendering();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSuppressesIncrementalRendering:") public native void setSuppressesIncrementalRendering(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stringByEvaluatingJavaScriptFromString:">- (NSString *)stringByEvaluatingJavaScriptFromString:(NSString *)script</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("stringByEvaluatingJavaScriptFromString:") public native @Type("NSString *") String evaluateJavaScript(@Type("NSString *") String script);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goBack">- (void)goBack</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("goBack") public native @Type("void") void goBack();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goForward">- (void)goForward</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("goForward") public native @Type("void") void goForward();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadData:MIMEType:textEncodingName:baseURL:">- (void)loadData:(NSData *)data MIMEType:(NSString *)MIMEType textEncodingName:(NSString *)encodingName baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("loadData:MIMEType:textEncodingName:baseURL:") public native @Type("void") void loadData(@Type("NSData *") NSData data, @Type("NSString *") String MIMEType, @Type("NSString *") String encodingName, @Type("NSURL *") NSURL baseURL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadHTMLString:baseURL:">- (void)loadHTMLString:(NSString *)string baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("loadHTMLString:baseURL:") public native @Type("void") void loadHTML(@Type("NSString *") String string, @Type("NSURL *") NSURL baseURL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadRequest:">- (void)loadRequest:(NSURLRequest *)request</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("loadRequest:") public native @Type("void") void loadRequest(@Type("NSURLRequest *") NSURLRequest request);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/reload">- (void)reload</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reload") public native @Type("void") void reload();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stopLoading">- (void)stopLoading</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("stopLoading") public native @Type("void") void stopLoading();
    /*</methods>*/

}
