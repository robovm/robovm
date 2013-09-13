/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.cocoatouch.glkit;

/*<imports>*/
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.foundation.*;
import org.robovm.cocoatouch.opengles.*;
import org.robovm.cocoatouch.uikit.*;
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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html">GLKView Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("GLKit")/*</library>*/
@NativeClass public class /*<name>*/ GLKView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ GLKView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ GLKView /*</name>*/.class);

    public GLKView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected GLKView(SkipInit skipInit) { super(skipInit); }
    public GLKView() {}
    
    private static final Selector initWithFrame$context$ = Selector.register("initWithFrame:context:");
    @Bridge private native static @Pointer long objc_initWithFrame(GLKView __self__, Selector __cmd__, @ByVal CGRect frame, EAGLContext context);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instm/GLKView/initWithFrame:context:">- (id)initWithFrame:(CGRect)frame context:(EAGLContext *)context</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKView(CGRect frame, EAGLContext context) {
        super((SkipInit) null);
        initObject(objc_initWithFrame(this, initWithFrame$context$, frame, context));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector context = Selector.register("context");
    @Bridge private native static EAGLContext objc_getContext(GLKView __self__, Selector __cmd__);
    @Bridge private native static EAGLContext objc_getContextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/context">@property(nonatomic, retain) EAGLContext *context</a>
     * @since Available in iOS 5.0 and later.
     */
    public EAGLContext getContext() {
        if (customClass) { return objc_getContextSuper(getSuper(), context); } else { return objc_getContext(this, context); }
    }
    
    private static final Selector setContext$ = Selector.register("setContext:");
    @Bridge private native static void objc_setContext(GLKView __self__, Selector __cmd__, EAGLContext context);
    @Bridge private native static void objc_setContextSuper(ObjCSuper __super__, Selector __cmd__, EAGLContext context);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/context">@property(nonatomic, retain) EAGLContext *context</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setContext(EAGLContext context) {
        if (customClass) { objc_setContextSuper(getSuper(), setContext$, context); } else { objc_setContext(this, setContext$, context); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static GLKViewDelegate objc_getDelegate(GLKView __self__, Selector __cmd__);
    @Bridge private native static GLKViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/delegate">@property(nonatomic, assign) id&amp;lt;GLKViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(GLKView __self__, Selector __cmd__, GLKViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, GLKViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/delegate">@property(nonatomic, assign) id&amp;lt;GLKViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDelegate(GLKViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector drawableColorFormat = Selector.register("drawableColorFormat");
    @Bridge private native static GLKViewDrawableColorFormat objc_getDrawableColorFormat(GLKView __self__, Selector __cmd__);
    @Bridge private native static GLKViewDrawableColorFormat objc_getDrawableColorFormatSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableColorFormat">@property(nonatomic) GLKViewDrawableColorFormat drawableColorFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewDrawableColorFormat getDrawableColorFormat() {
        if (customClass) { return objc_getDrawableColorFormatSuper(getSuper(), drawableColorFormat); } else { return objc_getDrawableColorFormat(this, drawableColorFormat); }
    }
    
    private static final Selector setDrawableColorFormat$ = Selector.register("setDrawableColorFormat:");
    @Bridge private native static void objc_setDrawableColorFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableColorFormat drawableColorFormat);
    @Bridge private native static void objc_setDrawableColorFormatSuper(ObjCSuper __super__, Selector __cmd__, GLKViewDrawableColorFormat drawableColorFormat);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableColorFormat">@property(nonatomic) GLKViewDrawableColorFormat drawableColorFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDrawableColorFormat(GLKViewDrawableColorFormat drawableColorFormat) {
        if (customClass) { objc_setDrawableColorFormatSuper(getSuper(), setDrawableColorFormat$, drawableColorFormat); } else { objc_setDrawableColorFormat(this, setDrawableColorFormat$, drawableColorFormat); }
    }
    
    private static final Selector drawableDepthFormat = Selector.register("drawableDepthFormat");
    @Bridge private native static GLKViewDrawableDepthFormat objc_getDrawableDepthFormat(GLKView __self__, Selector __cmd__);
    @Bridge private native static GLKViewDrawableDepthFormat objc_getDrawableDepthFormatSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableDepthFormat">@property(nonatomic) GLKViewDrawableDepthFormat drawableDepthFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewDrawableDepthFormat getDrawableDepthFormat() {
        if (customClass) { return objc_getDrawableDepthFormatSuper(getSuper(), drawableDepthFormat); } else { return objc_getDrawableDepthFormat(this, drawableDepthFormat); }
    }
    
    private static final Selector setDrawableDepthFormat$ = Selector.register("setDrawableDepthFormat:");
    @Bridge private native static void objc_setDrawableDepthFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableDepthFormat drawableDepthFormat);
    @Bridge private native static void objc_setDrawableDepthFormatSuper(ObjCSuper __super__, Selector __cmd__, GLKViewDrawableDepthFormat drawableDepthFormat);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableDepthFormat">@property(nonatomic) GLKViewDrawableDepthFormat drawableDepthFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDrawableDepthFormat(GLKViewDrawableDepthFormat drawableDepthFormat) {
        if (customClass) { objc_setDrawableDepthFormatSuper(getSuper(), setDrawableDepthFormat$, drawableDepthFormat); } else { objc_setDrawableDepthFormat(this, setDrawableDepthFormat$, drawableDepthFormat); }
    }
    
    private static final Selector drawableHeight = Selector.register("drawableHeight");
    @Bridge private native static int objc_getDrawableHeight(GLKView __self__, Selector __cmd__);
    @Bridge private native static int objc_getDrawableHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableHeight">@property(nonatomic, readonly) NSInteger drawableHeight</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getDrawableHeight() {
        if (customClass) { return objc_getDrawableHeightSuper(getSuper(), drawableHeight); } else { return objc_getDrawableHeight(this, drawableHeight); }
    }
    
    private static final Selector drawableMultisample = Selector.register("drawableMultisample");
    @Bridge private native static GLKViewDrawableMultisample objc_getDrawableMultisample(GLKView __self__, Selector __cmd__);
    @Bridge private native static GLKViewDrawableMultisample objc_getDrawableMultisampleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableMultisample">@property(nonatomic) GLKViewDrawableMultisample drawableMultisample</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewDrawableMultisample getDrawableMultisample() {
        if (customClass) { return objc_getDrawableMultisampleSuper(getSuper(), drawableMultisample); } else { return objc_getDrawableMultisample(this, drawableMultisample); }
    }
    
    private static final Selector setDrawableMultisample$ = Selector.register("setDrawableMultisample:");
    @Bridge private native static void objc_setDrawableMultisample(GLKView __self__, Selector __cmd__, GLKViewDrawableMultisample drawableMultisample);
    @Bridge private native static void objc_setDrawableMultisampleSuper(ObjCSuper __super__, Selector __cmd__, GLKViewDrawableMultisample drawableMultisample);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableMultisample">@property(nonatomic) GLKViewDrawableMultisample drawableMultisample</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDrawableMultisample(GLKViewDrawableMultisample drawableMultisample) {
        if (customClass) { objc_setDrawableMultisampleSuper(getSuper(), setDrawableMultisample$, drawableMultisample); } else { objc_setDrawableMultisample(this, setDrawableMultisample$, drawableMultisample); }
    }
    
    private static final Selector drawableStencilFormat = Selector.register("drawableStencilFormat");
    @Bridge private native static GLKViewDrawableStencilFormat objc_getDrawableStencilFormat(GLKView __self__, Selector __cmd__);
    @Bridge private native static GLKViewDrawableStencilFormat objc_getDrawableStencilFormatSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableStencilFormat">@property(nonatomic) GLKViewDrawableStencilFormat drawableStencilFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKViewDrawableStencilFormat getDrawableStencilFormat() {
        if (customClass) { return objc_getDrawableStencilFormatSuper(getSuper(), drawableStencilFormat); } else { return objc_getDrawableStencilFormat(this, drawableStencilFormat); }
    }
    
    private static final Selector setDrawableStencilFormat$ = Selector.register("setDrawableStencilFormat:");
    @Bridge private native static void objc_setDrawableStencilFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableStencilFormat drawableStencilFormat);
    @Bridge private native static void objc_setDrawableStencilFormatSuper(ObjCSuper __super__, Selector __cmd__, GLKViewDrawableStencilFormat drawableStencilFormat);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableStencilFormat">@property(nonatomic) GLKViewDrawableStencilFormat drawableStencilFormat</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDrawableStencilFormat(GLKViewDrawableStencilFormat drawableStencilFormat) {
        if (customClass) { objc_setDrawableStencilFormatSuper(getSuper(), setDrawableStencilFormat$, drawableStencilFormat); } else { objc_setDrawableStencilFormat(this, setDrawableStencilFormat$, drawableStencilFormat); }
    }
    
    private static final Selector drawableWidth = Selector.register("drawableWidth");
    @Bridge private native static int objc_getDrawableWidth(GLKView __self__, Selector __cmd__);
    @Bridge private native static int objc_getDrawableWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/drawableWidth">@property(nonatomic, readonly) NSInteger drawableWidth</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getDrawableWidth() {
        if (customClass) { return objc_getDrawableWidthSuper(getSuper(), drawableWidth); } else { return objc_getDrawableWidth(this, drawableWidth); }
    }
    
    private static final Selector enableSetNeedsDisplay = Selector.register("enableSetNeedsDisplay");
    @Bridge private native static boolean objc_isEnableSetNeedsDisplay(GLKView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEnableSetNeedsDisplaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/enableSetNeedsDisplay">@property(nonatomic) BOOL enableSetNeedsDisplay</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isEnableSetNeedsDisplay() {
        if (customClass) { return objc_isEnableSetNeedsDisplaySuper(getSuper(), enableSetNeedsDisplay); } else { return objc_isEnableSetNeedsDisplay(this, enableSetNeedsDisplay); }
    }
    
    private static final Selector setEnableSetNeedsDisplay$ = Selector.register("setEnableSetNeedsDisplay:");
    @Bridge private native static void objc_setEnableSetNeedsDisplay(GLKView __self__, Selector __cmd__, boolean enableSetNeedsDisplay);
    @Bridge private native static void objc_setEnableSetNeedsDisplaySuper(ObjCSuper __super__, Selector __cmd__, boolean enableSetNeedsDisplay);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/GLKView/enableSetNeedsDisplay">@property(nonatomic) BOOL enableSetNeedsDisplay</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setEnableSetNeedsDisplay(boolean enableSetNeedsDisplay) {
        if (customClass) { objc_setEnableSetNeedsDisplaySuper(getSuper(), setEnableSetNeedsDisplay$, enableSetNeedsDisplay); } else { objc_setEnableSetNeedsDisplay(this, setEnableSetNeedsDisplay$, enableSetNeedsDisplay); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector bindDrawable = Selector.register("bindDrawable");
    @Bridge private native static void objc_bindDrawable(GLKView __self__, Selector __cmd__);
    @Bridge private native static void objc_bindDrawableSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instm/GLKView/bindDrawable">- (void)bindDrawable</a>
     * @since Available in iOS 5.0 and later.
     */
    public void bindDrawable() {
        if (customClass) { objc_bindDrawableSuper(getSuper(), bindDrawable); } else { objc_bindDrawable(this, bindDrawable); }
    }
    
    private static final Selector deleteDrawable = Selector.register("deleteDrawable");
    @Bridge private native static void objc_deleteDrawable(GLKView __self__, Selector __cmd__);
    @Bridge private native static void objc_deleteDrawableSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instm/GLKView/deleteDrawable">- (void)deleteDrawable</a>
     * @since Available in iOS 5.0 and later.
     */
    public void deleteDrawable() {
        if (customClass) { objc_deleteDrawableSuper(getSuper(), deleteDrawable); } else { objc_deleteDrawable(this, deleteDrawable); }
    }
    
    private static final Selector display = Selector.register("display");
    @Bridge private native static void objc_display(GLKView __self__, Selector __cmd__);
    @Bridge private native static void objc_displaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instm/GLKView/display">- (void)display</a>
     * @since Available in iOS 5.0 and later.
     */
    public void display() {
        if (customClass) { objc_displaySuper(getSuper(), display); } else { objc_display(this, display); }
    }
    
    private static final Selector snapshot = Selector.register("snapshot");
    @Bridge private native static UIImage objc_snapshot(GLKView __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_snapshotSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKView_ClassReference/Reference/Reference.html#//apple_ref/occ/instm/GLKView/snapshot">- (UIImage *)snapshot</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage snapshot() {
        if (customClass) { return objc_snapshotSuper(getSuper(), snapshot); } else { return objc_snapshot(this, snapshot); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("context") public static EAGLContext getContext(GLKView __self__, Selector __cmd__) { return __self__.getContext(); }
        @Callback @BindSelector("setContext:") public static void setContext(GLKView __self__, Selector __cmd__, EAGLContext context) { __self__.setContext(context); }
        @Callback @BindSelector("delegate") public static GLKViewDelegate getDelegate(GLKView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(GLKView __self__, Selector __cmd__, GLKViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("drawableColorFormat") public static GLKViewDrawableColorFormat getDrawableColorFormat(GLKView __self__, Selector __cmd__) { return __self__.getDrawableColorFormat(); }
        @Callback @BindSelector("setDrawableColorFormat:") public static void setDrawableColorFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableColorFormat drawableColorFormat) { __self__.setDrawableColorFormat(drawableColorFormat); }
        @Callback @BindSelector("drawableDepthFormat") public static GLKViewDrawableDepthFormat getDrawableDepthFormat(GLKView __self__, Selector __cmd__) { return __self__.getDrawableDepthFormat(); }
        @Callback @BindSelector("setDrawableDepthFormat:") public static void setDrawableDepthFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableDepthFormat drawableDepthFormat) { __self__.setDrawableDepthFormat(drawableDepthFormat); }
        @Callback @BindSelector("drawableHeight") public static int getDrawableHeight(GLKView __self__, Selector __cmd__) { return __self__.getDrawableHeight(); }
        @Callback @BindSelector("drawableMultisample") public static GLKViewDrawableMultisample getDrawableMultisample(GLKView __self__, Selector __cmd__) { return __self__.getDrawableMultisample(); }
        @Callback @BindSelector("setDrawableMultisample:") public static void setDrawableMultisample(GLKView __self__, Selector __cmd__, GLKViewDrawableMultisample drawableMultisample) { __self__.setDrawableMultisample(drawableMultisample); }
        @Callback @BindSelector("drawableStencilFormat") public static GLKViewDrawableStencilFormat getDrawableStencilFormat(GLKView __self__, Selector __cmd__) { return __self__.getDrawableStencilFormat(); }
        @Callback @BindSelector("setDrawableStencilFormat:") public static void setDrawableStencilFormat(GLKView __self__, Selector __cmd__, GLKViewDrawableStencilFormat drawableStencilFormat) { __self__.setDrawableStencilFormat(drawableStencilFormat); }
        @Callback @BindSelector("drawableWidth") public static int getDrawableWidth(GLKView __self__, Selector __cmd__) { return __self__.getDrawableWidth(); }
        @Callback @BindSelector("enableSetNeedsDisplay") public static boolean isEnableSetNeedsDisplay(GLKView __self__, Selector __cmd__) { return __self__.isEnableSetNeedsDisplay(); }
        @Callback @BindSelector("setEnableSetNeedsDisplay:") public static void setEnableSetNeedsDisplay(GLKView __self__, Selector __cmd__, boolean enableSetNeedsDisplay) { __self__.setEnableSetNeedsDisplay(enableSetNeedsDisplay); }
        @Callback @BindSelector("bindDrawable") public static void bindDrawable(GLKView __self__, Selector __cmd__) { __self__.bindDrawable(); }
        @Callback @BindSelector("deleteDrawable") public static void deleteDrawable(GLKView __self__, Selector __cmd__) { __self__.deleteDrawable(); }
        @Callback @BindSelector("display") public static void display(GLKView __self__, Selector __cmd__) { __self__.display(); }
        @Callback @BindSelector("snapshot") public static UIImage snapshot(GLKView __self__, Selector __cmd__) { return __self__.snapshot(); }
    }
    /*</callbacks>*/

}
