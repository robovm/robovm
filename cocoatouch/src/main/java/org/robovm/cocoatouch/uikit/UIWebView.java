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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIWebView /*</name>*/.class);

    /*<constructors>*/
    protected UIWebView(SkipInit skipInit) { super(skipInit); }
    public UIWebView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("allowsInlineMediaPlayback") public native boolean isAllowsInlineMediaPlayback();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setAllowsInlineMediaPlayback:") public native void setAllowsInlineMediaPlayback(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoBack">@property(nonatomic, readonly, getter=canGoBack) BOOL canGoBack</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canGoBack") public native boolean isCanGoBack();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoForward">@property(nonatomic, readonly, getter=canGoForward) BOOL canGoForward</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canGoForward") public native boolean isCanGoForward();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("dataDetectorTypes") public native EnumSet<UIDataDetectorType> getDataDetectorTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDataDetectorTypes:") public native void setDataDetectorTypes(EnumSet<UIDataDetectorType> v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIWebViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIWebViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("keyboardDisplayRequiresUserAction") public native boolean isKeyboardDisplayRequiresUserAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setKeyboardDisplayRequiresUserAction:") public native void setKeyboardDisplayRequiresUserAction(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/loading">@property(nonatomic, readonly, getter=isLoading) BOOL loading</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isLoading") public native boolean isLoading();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("mediaPlaybackAllowsAirPlay") public native boolean isMediaPlaybackAllowsAirPlay();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMediaPlaybackAllowsAirPlay:") public native void setMediaPlaybackAllowsAirPlay(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("mediaPlaybackRequiresUserAction") public native boolean isMediaPlaybackRequiresUserAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setMediaPlaybackRequiresUserAction:") public native void setMediaPlaybackRequiresUserAction(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/request">@property(nonatomic, readonly, retain) NSURLRequest *request</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("request") public native NSURLRequest getRequest();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scalesPageToFit") public native boolean isScalesPageToFit();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScalesPageToFit:") public native void setScalesPageToFit(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scrollView">@property(nonatomic, readonly, retain) UIScrollView *scrollView</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scrollView") public native UIScrollView getScrollView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("suppressesIncrementalRendering") public native boolean isSuppressesIncrementalRendering();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSuppressesIncrementalRendering:") public native void setSuppressesIncrementalRendering(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector stringByEvaluatingJavaScriptFromString$ = Selector.register("stringByEvaluatingJavaScriptFromString:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_evaluateJavaScript(UIWebView __self__, Selector __cmd__, String script);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_evaluateJavaScriptSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__, String script);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stringByEvaluatingJavaScriptFromString:">- (NSString *)stringByEvaluatingJavaScriptFromString:(NSString *)script</a>
     * @since Available in iOS 2.0 and later.
     */
    public String evaluateJavaScript(String script) {
        if (customClass) { return objc_evaluateJavaScriptSuper(getSuper(), this, stringByEvaluatingJavaScriptFromString$, script); } else { return objc_evaluateJavaScript(this, stringByEvaluatingJavaScriptFromString$, script); }
    }
    
    private static final Selector goBack = Selector.register("goBack");
    @Bridge(symbol = "objc_msgSend") private native static void objc_goBack(UIWebView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_goBackSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goBack">- (void)goBack</a>
     * @since Available in iOS 2.0 and later.
     */
    public void goBack() {
        if (customClass) { objc_goBackSuper(getSuper(), this, goBack); } else { objc_goBack(this, goBack); }
    }
    
    private static final Selector goForward = Selector.register("goForward");
    @Bridge(symbol = "objc_msgSend") private native static void objc_goForward(UIWebView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_goForwardSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goForward">- (void)goForward</a>
     * @since Available in iOS 2.0 and later.
     */
    public void goForward() {
        if (customClass) { objc_goForwardSuper(getSuper(), this, goForward); } else { objc_goForward(this, goForward); }
    }
    
    private static final Selector loadData$MIMEType$textEncodingName$baseURL$ = Selector.register("loadData:MIMEType:textEncodingName:baseURL:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_loadData(UIWebView __self__, Selector __cmd__, NSData data, String MIMEType, String encodingName, NSURL baseURL);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_loadDataSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__, NSData data, String MIMEType, String encodingName, NSURL baseURL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadData:MIMEType:textEncodingName:baseURL:">- (void)loadData:(NSData *)data MIMEType:(NSString *)MIMEType textEncodingName:(NSString *)encodingName baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadData(NSData data, String MIMEType, String encodingName, NSURL baseURL) {
        if (customClass) { objc_loadDataSuper(getSuper(), this, loadData$MIMEType$textEncodingName$baseURL$, data, MIMEType, encodingName, baseURL); } else { objc_loadData(this, loadData$MIMEType$textEncodingName$baseURL$, data, MIMEType, encodingName, baseURL); }
    }
    
    private static final Selector loadHTMLString$baseURL$ = Selector.register("loadHTMLString:baseURL:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_loadHTML(UIWebView __self__, Selector __cmd__, String string, NSURL baseURL);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_loadHTMLSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__, String string, NSURL baseURL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadHTMLString:baseURL:">- (void)loadHTMLString:(NSString *)string baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadHTML(String string, NSURL baseURL) {
        if (customClass) { objc_loadHTMLSuper(getSuper(), this, loadHTMLString$baseURL$, string, baseURL); } else { objc_loadHTML(this, loadHTMLString$baseURL$, string, baseURL); }
    }
    
    private static final Selector loadRequest$ = Selector.register("loadRequest:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_loadRequest(UIWebView __self__, Selector __cmd__, NSURLRequest request);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_loadRequestSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__, NSURLRequest request);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadRequest:">- (void)loadRequest:(NSURLRequest *)request</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadRequest(NSURLRequest request) {
        if (customClass) { objc_loadRequestSuper(getSuper(), this, loadRequest$, request); } else { objc_loadRequest(this, loadRequest$, request); }
    }
    
    private static final Selector reload = Selector.register("reload");
    @Bridge(symbol = "objc_msgSend") private native static void objc_reload(UIWebView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_reloadSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/reload">- (void)reload</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reload() {
        if (customClass) { objc_reloadSuper(getSuper(), this, reload); } else { objc_reload(this, reload); }
    }
    
    private static final Selector stopLoading = Selector.register("stopLoading");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stopLoading(UIWebView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_stopLoadingSuper(ObjCSuper __super__, UIWebView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stopLoading">- (void)stopLoading</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopLoading() {
        if (customClass) { objc_stopLoadingSuper(getSuper(), this, stopLoading); } else { objc_stopLoading(this, stopLoading); }
    }
    /*</methods>*/

}
