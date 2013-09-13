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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html">UIWebView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIWebView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIWebView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIWebView /*</name>*/.class);

    public UIWebView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIWebView(SkipInit skipInit) { super(skipInit); }
    public UIWebView() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector allowsInlineMediaPlayback = Selector.register("allowsInlineMediaPlayback");
    @Bridge private native static boolean objc_isAllowsInlineMediaPlayback(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsInlineMediaPlaybackSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean isAllowsInlineMediaPlayback() {
        if (customClass) { return objc_isAllowsInlineMediaPlaybackSuper(getSuper(), allowsInlineMediaPlayback); } else { return objc_isAllowsInlineMediaPlayback(this, allowsInlineMediaPlayback); }
    }
    
    private static final Selector setAllowsInlineMediaPlayback$ = Selector.register("setAllowsInlineMediaPlayback:");
    @Bridge private native static void objc_setAllowsInlineMediaPlayback(UIWebView __self__, Selector __cmd__, boolean allowsInlineMediaPlayback);
    @Bridge private native static void objc_setAllowsInlineMediaPlaybackSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsInlineMediaPlayback);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/allowsInlineMediaPlayback">@property(nonatomic) BOOL allowsInlineMediaPlayback</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setAllowsInlineMediaPlayback(boolean allowsInlineMediaPlayback) {
        if (customClass) { objc_setAllowsInlineMediaPlaybackSuper(getSuper(), setAllowsInlineMediaPlayback$, allowsInlineMediaPlayback); } else { objc_setAllowsInlineMediaPlayback(this, setAllowsInlineMediaPlayback$, allowsInlineMediaPlayback); }
    }
    
    private static final Selector canGoBack = Selector.register("canGoBack");
    @Bridge private native static boolean objc_isCanGoBack(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isCanGoBackSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoBack">@property(nonatomic, readonly, getter=canGoBack) BOOL canGoBack</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCanGoBack() {
        if (customClass) { return objc_isCanGoBackSuper(getSuper(), canGoBack); } else { return objc_isCanGoBack(this, canGoBack); }
    }
    
    private static final Selector canGoForward = Selector.register("canGoForward");
    @Bridge private native static boolean objc_isCanGoForward(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isCanGoForwardSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/canGoForward">@property(nonatomic, readonly, getter=canGoForward) BOOL canGoForward</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCanGoForward() {
        if (customClass) { return objc_isCanGoForwardSuper(getSuper(), canGoForward); } else { return objc_isCanGoForward(this, canGoForward); }
    }
    
    private static final Selector dataDetectorTypes = Selector.register("dataDetectorTypes");
    @Bridge private native static UIDataDetectorTypes objc_getDataDetectorTypes(UIWebView __self__, Selector __cmd__);
    @Bridge private native static UIDataDetectorTypes objc_getDataDetectorTypesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIDataDetectorTypes getDataDetectorTypes() {
        if (customClass) { return objc_getDataDetectorTypesSuper(getSuper(), dataDetectorTypes); } else { return objc_getDataDetectorTypes(this, dataDetectorTypes); }
    }
    
    private static final Selector setDataDetectorTypes$ = Selector.register("setDataDetectorTypes:");
    @Bridge private native static void objc_setDataDetectorTypes(UIWebView __self__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes);
    @Bridge private native static void objc_setDataDetectorTypesSuper(ObjCSuper __super__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setDataDetectorTypes(UIDataDetectorTypes dataDetectorTypes) {
        if (customClass) { objc_setDataDetectorTypesSuper(getSuper(), setDataDetectorTypes$, dataDetectorTypes); } else { objc_setDataDetectorTypes(this, setDataDetectorTypes$, dataDetectorTypes); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIWebViewDelegate objc_getDelegate(UIWebView __self__, Selector __cmd__);
    @Bridge private native static UIWebViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIWebViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIWebView __self__, Selector __cmd__, UIWebViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIWebViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/delegate">@property(nonatomic, assign) id&amp;lt;UIWebViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIWebViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector keyboardDisplayRequiresUserAction = Selector.register("keyboardDisplayRequiresUserAction");
    @Bridge private native static boolean objc_isKeyboardDisplayRequiresUserAction(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isKeyboardDisplayRequiresUserActionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isKeyboardDisplayRequiresUserAction() {
        if (customClass) { return objc_isKeyboardDisplayRequiresUserActionSuper(getSuper(), keyboardDisplayRequiresUserAction); } else { return objc_isKeyboardDisplayRequiresUserAction(this, keyboardDisplayRequiresUserAction); }
    }
    
    private static final Selector setKeyboardDisplayRequiresUserAction$ = Selector.register("setKeyboardDisplayRequiresUserAction:");
    @Bridge private native static void objc_setKeyboardDisplayRequiresUserAction(UIWebView __self__, Selector __cmd__, boolean keyboardDisplayRequiresUserAction);
    @Bridge private native static void objc_setKeyboardDisplayRequiresUserActionSuper(ObjCSuper __super__, Selector __cmd__, boolean keyboardDisplayRequiresUserAction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/keyboardDisplayRequiresUserAction">@property (nonatomic) BOOL keyboardDisplayRequiresUserAction</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setKeyboardDisplayRequiresUserAction(boolean keyboardDisplayRequiresUserAction) {
        if (customClass) { objc_setKeyboardDisplayRequiresUserActionSuper(getSuper(), setKeyboardDisplayRequiresUserAction$, keyboardDisplayRequiresUserAction); } else { objc_setKeyboardDisplayRequiresUserAction(this, setKeyboardDisplayRequiresUserAction$, keyboardDisplayRequiresUserAction); }
    }
    
    private static final Selector isLoading = Selector.register("isLoading");
    @Bridge private native static boolean objc_isLoading(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isLoadingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/loading">@property(nonatomic, readonly, getter=isLoading) BOOL loading</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isLoading() {
        if (customClass) { return objc_isLoadingSuper(getSuper(), isLoading); } else { return objc_isLoading(this, isLoading); }
    }
    
    private static final Selector mediaPlaybackAllowsAirPlay = Selector.register("mediaPlaybackAllowsAirPlay");
    @Bridge private native static boolean objc_isMediaPlaybackAllowsAirPlay(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMediaPlaybackAllowsAirPlaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMediaPlaybackAllowsAirPlay() {
        if (customClass) { return objc_isMediaPlaybackAllowsAirPlaySuper(getSuper(), mediaPlaybackAllowsAirPlay); } else { return objc_isMediaPlaybackAllowsAirPlay(this, mediaPlaybackAllowsAirPlay); }
    }
    
    private static final Selector setMediaPlaybackAllowsAirPlay$ = Selector.register("setMediaPlaybackAllowsAirPlay:");
    @Bridge private native static void objc_setMediaPlaybackAllowsAirPlay(UIWebView __self__, Selector __cmd__, boolean mediaPlaybackAllowsAirPlay);
    @Bridge private native static void objc_setMediaPlaybackAllowsAirPlaySuper(ObjCSuper __super__, Selector __cmd__, boolean mediaPlaybackAllowsAirPlay);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackAllowsAirPlay">@property(nonatomic) BOOL mediaPlaybackAllowsAirPlay</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMediaPlaybackAllowsAirPlay(boolean mediaPlaybackAllowsAirPlay) {
        if (customClass) { objc_setMediaPlaybackAllowsAirPlaySuper(getSuper(), setMediaPlaybackAllowsAirPlay$, mediaPlaybackAllowsAirPlay); } else { objc_setMediaPlaybackAllowsAirPlay(this, setMediaPlaybackAllowsAirPlay$, mediaPlaybackAllowsAirPlay); }
    }
    
    private static final Selector mediaPlaybackRequiresUserAction = Selector.register("mediaPlaybackRequiresUserAction");
    @Bridge private native static boolean objc_isMediaPlaybackRequiresUserAction(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMediaPlaybackRequiresUserActionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean isMediaPlaybackRequiresUserAction() {
        if (customClass) { return objc_isMediaPlaybackRequiresUserActionSuper(getSuper(), mediaPlaybackRequiresUserAction); } else { return objc_isMediaPlaybackRequiresUserAction(this, mediaPlaybackRequiresUserAction); }
    }
    
    private static final Selector setMediaPlaybackRequiresUserAction$ = Selector.register("setMediaPlaybackRequiresUserAction:");
    @Bridge private native static void objc_setMediaPlaybackRequiresUserAction(UIWebView __self__, Selector __cmd__, boolean mediaPlaybackRequiresUserAction);
    @Bridge private native static void objc_setMediaPlaybackRequiresUserActionSuper(ObjCSuper __super__, Selector __cmd__, boolean mediaPlaybackRequiresUserAction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/mediaPlaybackRequiresUserAction">@property(nonatomic) BOOL mediaPlaybackRequiresUserAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setMediaPlaybackRequiresUserAction(boolean mediaPlaybackRequiresUserAction) {
        if (customClass) { objc_setMediaPlaybackRequiresUserActionSuper(getSuper(), setMediaPlaybackRequiresUserAction$, mediaPlaybackRequiresUserAction); } else { objc_setMediaPlaybackRequiresUserAction(this, setMediaPlaybackRequiresUserAction$, mediaPlaybackRequiresUserAction); }
    }
    
    private static final Selector request = Selector.register("request");
    @Bridge private native static NSURLRequest objc_getRequest(UIWebView __self__, Selector __cmd__);
    @Bridge private native static NSURLRequest objc_getRequestSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/request">@property(nonatomic, readonly, retain) NSURLRequest *request</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSURLRequest getRequest() {
        if (customClass) { return objc_getRequestSuper(getSuper(), request); } else { return objc_getRequest(this, request); }
    }
    
    private static final Selector scalesPageToFit = Selector.register("scalesPageToFit");
    @Bridge private native static boolean objc_isScalesPageToFit(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isScalesPageToFitSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isScalesPageToFit() {
        if (customClass) { return objc_isScalesPageToFitSuper(getSuper(), scalesPageToFit); } else { return objc_isScalesPageToFit(this, scalesPageToFit); }
    }
    
    private static final Selector setScalesPageToFit$ = Selector.register("setScalesPageToFit:");
    @Bridge private native static void objc_setScalesPageToFit(UIWebView __self__, Selector __cmd__, boolean scalesPageToFit);
    @Bridge private native static void objc_setScalesPageToFitSuper(ObjCSuper __super__, Selector __cmd__, boolean scalesPageToFit);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scalesPageToFit">@property(nonatomic) BOOL scalesPageToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setScalesPageToFit(boolean scalesPageToFit) {
        if (customClass) { objc_setScalesPageToFitSuper(getSuper(), setScalesPageToFit$, scalesPageToFit); } else { objc_setScalesPageToFit(this, setScalesPageToFit$, scalesPageToFit); }
    }
    
    private static final Selector scrollView = Selector.register("scrollView");
    @Bridge private native static UIScrollView objc_getScrollView(UIWebView __self__, Selector __cmd__);
    @Bridge private native static UIScrollView objc_getScrollViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/scrollView">@property(nonatomic, readonly, retain) UIScrollView *scrollView</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIScrollView getScrollView() {
        if (customClass) { return objc_getScrollViewSuper(getSuper(), scrollView); } else { return objc_getScrollView(this, scrollView); }
    }
    
    private static final Selector suppressesIncrementalRendering = Selector.register("suppressesIncrementalRendering");
    @Bridge private native static boolean objc_isSuppressesIncrementalRendering(UIWebView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isSuppressesIncrementalRenderingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isSuppressesIncrementalRendering() {
        if (customClass) { return objc_isSuppressesIncrementalRenderingSuper(getSuper(), suppressesIncrementalRendering); } else { return objc_isSuppressesIncrementalRendering(this, suppressesIncrementalRendering); }
    }
    
    private static final Selector setSuppressesIncrementalRendering$ = Selector.register("setSuppressesIncrementalRendering:");
    @Bridge private native static void objc_setSuppressesIncrementalRendering(UIWebView __self__, Selector __cmd__, boolean suppressesIncrementalRendering);
    @Bridge private native static void objc_setSuppressesIncrementalRenderingSuper(ObjCSuper __super__, Selector __cmd__, boolean suppressesIncrementalRendering);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instp/UIWebView/suppressesIncrementalRendering">@property(nonatomic) BOOL suppressesIncrementalRendering</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSuppressesIncrementalRendering(boolean suppressesIncrementalRendering) {
        if (customClass) { objc_setSuppressesIncrementalRenderingSuper(getSuper(), setSuppressesIncrementalRendering$, suppressesIncrementalRendering); } else { objc_setSuppressesIncrementalRendering(this, setSuppressesIncrementalRendering$, suppressesIncrementalRendering); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector stringByEvaluatingJavaScriptFromString$ = Selector.register("stringByEvaluatingJavaScriptFromString:");
    @Bridge private native static String objc_evaluateJavaScript(UIWebView __self__, Selector __cmd__, String script);
    @Bridge private native static String objc_evaluateJavaScriptSuper(ObjCSuper __super__, Selector __cmd__, String script);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stringByEvaluatingJavaScriptFromString:">- (NSString *)stringByEvaluatingJavaScriptFromString:(NSString *)script</a>
     * @since Available in iOS 2.0 and later.
     */
    public String evaluateJavaScript(String script) {
        if (customClass) { return objc_evaluateJavaScriptSuper(getSuper(), stringByEvaluatingJavaScriptFromString$, script); } else { return objc_evaluateJavaScript(this, stringByEvaluatingJavaScriptFromString$, script); }
    }
    
    private static final Selector goBack = Selector.register("goBack");
    @Bridge private native static void objc_goBack(UIWebView __self__, Selector __cmd__);
    @Bridge private native static void objc_goBackSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goBack">- (void)goBack</a>
     * @since Available in iOS 2.0 and later.
     */
    public void goBack() {
        if (customClass) { objc_goBackSuper(getSuper(), goBack); } else { objc_goBack(this, goBack); }
    }
    
    private static final Selector goForward = Selector.register("goForward");
    @Bridge private native static void objc_goForward(UIWebView __self__, Selector __cmd__);
    @Bridge private native static void objc_goForwardSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/goForward">- (void)goForward</a>
     * @since Available in iOS 2.0 and later.
     */
    public void goForward() {
        if (customClass) { objc_goForwardSuper(getSuper(), goForward); } else { objc_goForward(this, goForward); }
    }
    
    private static final Selector loadData$MIMEType$textEncodingName$baseURL$ = Selector.register("loadData:MIMEType:textEncodingName:baseURL:");
    @Bridge private native static void objc_loadData(UIWebView __self__, Selector __cmd__, NSData data, String MIMEType, String encodingName, NSURL baseURL);
    @Bridge private native static void objc_loadDataSuper(ObjCSuper __super__, Selector __cmd__, NSData data, String MIMEType, String encodingName, NSURL baseURL);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadData:MIMEType:textEncodingName:baseURL:">- (void)loadData:(NSData *)data MIMEType:(NSString *)MIMEType textEncodingName:(NSString *)encodingName baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadData(NSData data, String MIMEType, String encodingName, NSURL baseURL) {
        if (customClass) { objc_loadDataSuper(getSuper(), loadData$MIMEType$textEncodingName$baseURL$, data, MIMEType, encodingName, baseURL); } else { objc_loadData(this, loadData$MIMEType$textEncodingName$baseURL$, data, MIMEType, encodingName, baseURL); }
    }
    
    private static final Selector loadHTMLString$baseURL$ = Selector.register("loadHTMLString:baseURL:");
    @Bridge private native static void objc_loadHTML(UIWebView __self__, Selector __cmd__, String string, NSURL baseURL);
    @Bridge private native static void objc_loadHTMLSuper(ObjCSuper __super__, Selector __cmd__, String string, NSURL baseURL);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadHTMLString:baseURL:">- (void)loadHTMLString:(NSString *)string baseURL:(NSURL *)baseURL</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadHTML(String string, NSURL baseURL) {
        if (customClass) { objc_loadHTMLSuper(getSuper(), loadHTMLString$baseURL$, string, baseURL); } else { objc_loadHTML(this, loadHTMLString$baseURL$, string, baseURL); }
    }
    
    private static final Selector loadRequest$ = Selector.register("loadRequest:");
    @Bridge private native static void objc_loadRequest(UIWebView __self__, Selector __cmd__, NSURLRequest request);
    @Bridge private native static void objc_loadRequestSuper(ObjCSuper __super__, Selector __cmd__, NSURLRequest request);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/loadRequest:">- (void)loadRequest:(NSURLRequest *)request</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadRequest(NSURLRequest request) {
        if (customClass) { objc_loadRequestSuper(getSuper(), loadRequest$, request); } else { objc_loadRequest(this, loadRequest$, request); }
    }
    
    private static final Selector reload = Selector.register("reload");
    @Bridge private native static void objc_reload(UIWebView __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/reload">- (void)reload</a>
     * @since Available in iOS 2.0 and later.
     */
    public void reload() {
        if (customClass) { objc_reloadSuper(getSuper(), reload); } else { objc_reload(this, reload); }
    }
    
    private static final Selector stopLoading = Selector.register("stopLoading");
    @Bridge private native static void objc_stopLoading(UIWebView __self__, Selector __cmd__);
    @Bridge private native static void objc_stopLoadingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIWebView_Class/Reference/Reference.html#//apple_ref/occ/instm/UIWebView/stopLoading">- (void)stopLoading</a>
     * @since Available in iOS 2.0 and later.
     */
    public void stopLoading() {
        if (customClass) { objc_stopLoadingSuper(getSuper(), stopLoading); } else { objc_stopLoading(this, stopLoading); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allowsInlineMediaPlayback") public static boolean isAllowsInlineMediaPlayback(UIWebView __self__, Selector __cmd__) { return __self__.isAllowsInlineMediaPlayback(); }
        @Callback @BindSelector("setAllowsInlineMediaPlayback:") public static void setAllowsInlineMediaPlayback(UIWebView __self__, Selector __cmd__, boolean allowsInlineMediaPlayback) { __self__.setAllowsInlineMediaPlayback(allowsInlineMediaPlayback); }
        @Callback @BindSelector("canGoBack") public static boolean isCanGoBack(UIWebView __self__, Selector __cmd__) { return __self__.isCanGoBack(); }
        @Callback @BindSelector("canGoForward") public static boolean isCanGoForward(UIWebView __self__, Selector __cmd__) { return __self__.isCanGoForward(); }
        @Callback @BindSelector("dataDetectorTypes") public static UIDataDetectorTypes getDataDetectorTypes(UIWebView __self__, Selector __cmd__) { return __self__.getDataDetectorTypes(); }
        @Callback @BindSelector("setDataDetectorTypes:") public static void setDataDetectorTypes(UIWebView __self__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes) { __self__.setDataDetectorTypes(dataDetectorTypes); }
        @Callback @BindSelector("delegate") public static UIWebViewDelegate getDelegate(UIWebView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIWebView __self__, Selector __cmd__, UIWebViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("keyboardDisplayRequiresUserAction") public static boolean isKeyboardDisplayRequiresUserAction(UIWebView __self__, Selector __cmd__) { return __self__.isKeyboardDisplayRequiresUserAction(); }
        @Callback @BindSelector("setKeyboardDisplayRequiresUserAction:") public static void setKeyboardDisplayRequiresUserAction(UIWebView __self__, Selector __cmd__, boolean keyboardDisplayRequiresUserAction) { __self__.setKeyboardDisplayRequiresUserAction(keyboardDisplayRequiresUserAction); }
        @Callback @BindSelector("isLoading") public static boolean isLoading(UIWebView __self__, Selector __cmd__) { return __self__.isLoading(); }
        @Callback @BindSelector("mediaPlaybackAllowsAirPlay") public static boolean isMediaPlaybackAllowsAirPlay(UIWebView __self__, Selector __cmd__) { return __self__.isMediaPlaybackAllowsAirPlay(); }
        @Callback @BindSelector("setMediaPlaybackAllowsAirPlay:") public static void setMediaPlaybackAllowsAirPlay(UIWebView __self__, Selector __cmd__, boolean mediaPlaybackAllowsAirPlay) { __self__.setMediaPlaybackAllowsAirPlay(mediaPlaybackAllowsAirPlay); }
        @Callback @BindSelector("mediaPlaybackRequiresUserAction") public static boolean isMediaPlaybackRequiresUserAction(UIWebView __self__, Selector __cmd__) { return __self__.isMediaPlaybackRequiresUserAction(); }
        @Callback @BindSelector("setMediaPlaybackRequiresUserAction:") public static void setMediaPlaybackRequiresUserAction(UIWebView __self__, Selector __cmd__, boolean mediaPlaybackRequiresUserAction) { __self__.setMediaPlaybackRequiresUserAction(mediaPlaybackRequiresUserAction); }
        @Callback @BindSelector("request") public static NSURLRequest getRequest(UIWebView __self__, Selector __cmd__) { return __self__.getRequest(); }
        @Callback @BindSelector("scalesPageToFit") public static boolean isScalesPageToFit(UIWebView __self__, Selector __cmd__) { return __self__.isScalesPageToFit(); }
        @Callback @BindSelector("setScalesPageToFit:") public static void setScalesPageToFit(UIWebView __self__, Selector __cmd__, boolean scalesPageToFit) { __self__.setScalesPageToFit(scalesPageToFit); }
        @Callback @BindSelector("scrollView") public static UIScrollView getScrollView(UIWebView __self__, Selector __cmd__) { return __self__.getScrollView(); }
        @Callback @BindSelector("suppressesIncrementalRendering") public static boolean isSuppressesIncrementalRendering(UIWebView __self__, Selector __cmd__) { return __self__.isSuppressesIncrementalRendering(); }
        @Callback @BindSelector("setSuppressesIncrementalRendering:") public static void setSuppressesIncrementalRendering(UIWebView __self__, Selector __cmd__, boolean suppressesIncrementalRendering) { __self__.setSuppressesIncrementalRendering(suppressesIncrementalRendering); }
        @Callback @BindSelector("stringByEvaluatingJavaScriptFromString:") public static String evaluateJavaScript(UIWebView __self__, Selector __cmd__, String script) { return __self__.evaluateJavaScript(script); }
        @Callback @BindSelector("goBack") public static void goBack(UIWebView __self__, Selector __cmd__) { __self__.goBack(); }
        @Callback @BindSelector("goForward") public static void goForward(UIWebView __self__, Selector __cmd__) { __self__.goForward(); }
        @Callback @BindSelector("loadData:MIMEType:textEncodingName:baseURL:") public static void loadData(UIWebView __self__, Selector __cmd__, NSData data, String MIMEType, String encodingName, NSURL baseURL) { __self__.loadData(data, MIMEType, encodingName, baseURL); }
        @Callback @BindSelector("loadHTMLString:baseURL:") public static void loadHTML(UIWebView __self__, Selector __cmd__, String string, NSURL baseURL) { __self__.loadHTML(string, baseURL); }
        @Callback @BindSelector("loadRequest:") public static void loadRequest(UIWebView __self__, Selector __cmd__, NSURLRequest request) { __self__.loadRequest(request); }
        @Callback @BindSelector("reload") public static void reload(UIWebView __self__, Selector __cmd__) { __self__.reload(); }
        @Callback @BindSelector("stopLoading") public static void stopLoading(UIWebView __self__, Selector __cmd__) { __self__.stopLoading(); }
    }
    /*</callbacks>*/

}
