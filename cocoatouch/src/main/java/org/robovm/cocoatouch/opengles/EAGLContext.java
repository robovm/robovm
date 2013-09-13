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
package org.robovm.cocoatouch.opengles;

/*<imports>*/
import org.robovm.cocoatouch.coregraphics.*;
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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html">EAGLContext Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("OpenGLES")/*</library>*/
@NativeClass public class /*<name>*/ EAGLContext /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ EAGLContext /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ EAGLContext /*</name>*/.class);

    /*<constructors>*/
    protected EAGLContext(SkipInit skipInit) { super(skipInit); }
    public EAGLContext() {}
    
    private static final Selector initWithAPI$ = Selector.register("initWithAPI:");
    @Bridge private native static @Pointer long objc_initWithAPI(EAGLContext __self__, Selector __cmd__, EAGLRenderingAPI api);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instm/EAGLContext/initWithAPI:">- (id)initWithAPI:(EAGLRenderingAPI)api</a>
     * @since Available in iOS 2.0 and later.
     */
    public EAGLContext(EAGLRenderingAPI api) {
        super((SkipInit) null);
        initObject(objc_initWithAPI(this, initWithAPI$, api));
    }
    
    private static final Selector initWithAPI$sharegroup$ = Selector.register("initWithAPI:sharegroup:");
    @Bridge private native static @Pointer long objc_initWithAPI(EAGLContext __self__, Selector __cmd__, EAGLRenderingAPI api, EAGLSharegroup sharegroup);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instm/EAGLContext/initWithAPI:sharegroup:">- (id)initWithAPI:(EAGLRenderingAPI)api sharegroup:(EAGLSharegroup *)sharegroup</a>
     * @since Available in iOS 2.0 and later.
     */
    public EAGLContext(EAGLRenderingAPI api, EAGLSharegroup sharegroup) {
        super((SkipInit) null);
        initObject(objc_initWithAPI(this, initWithAPI$sharegroup$, api, sharegroup));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector API = Selector.register("API");
    @Bridge private native static EAGLRenderingAPI objc_getAPI(EAGLContext __self__, Selector __cmd__);
    @Bridge private native static EAGLRenderingAPI objc_getAPISuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instp/EAGLContext/API">@property(readonly) EAGLRenderingAPI API</a>
     * @since Available in iOS 2.0 and later.
     */
    public EAGLRenderingAPI getAPI() {
        if (customClass) { return objc_getAPISuper(getSuper(), API); } else { return objc_getAPI(this, API); }
    }
    
    private static final Selector sharegroup = Selector.register("sharegroup");
    @Bridge private native static EAGLSharegroup objc_getSharegroup(EAGLContext __self__, Selector __cmd__);
    @Bridge private native static EAGLSharegroup objc_getSharegroupSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instp/EAGLContext/sharegroup">@property(readonly) EAGLSharegroup *sharegroup</a>
     * @since Available in iOS 2.0 and later.
     */
    public EAGLSharegroup getSharegroup() {
        if (customClass) { return objc_getSharegroupSuper(getSuper(), sharegroup); } else { return objc_getSharegroup(this, sharegroup); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector currentContext = Selector.register("currentContext");
    @Bridge private native static EAGLContext objc_getCurrentContext(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/clm/EAGLContext/currentContext">+ (EAGLContext *)currentContext</a>
     * @since Available in iOS 2.0 and later.
     */
    public static EAGLContext getCurrentContext() {
        return objc_getCurrentContext(objCClass, currentContext);
    }
    
    private static final Selector setCurrentContext$ = Selector.register("setCurrentContext:");
    @Bridge private native static boolean objc_setCurrentContext(ObjCClass __self__, Selector __cmd__, EAGLContext context);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/clm/EAGLContext/setCurrentContext:">+ (BOOL)setCurrentContext:(EAGLContext *)context</a>
     * @since Available in iOS 2.0 and later.
     */
    public static boolean setCurrentContext(EAGLContext context) {
        return objc_setCurrentContext(objCClass, setCurrentContext$, context);
    }
    
    private static final Selector presentRenderbuffer$ = Selector.register("presentRenderbuffer:");
    @Bridge private native static boolean objc_presentRenderbuffer(EAGLContext __self__, Selector __cmd__, int target);
    @Bridge private native static boolean objc_presentRenderbufferSuper(ObjCSuper __super__, Selector __cmd__, int target);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instm/EAGLContext/presentRenderbuffer:">- (BOOL)presentRenderbuffer:(NSUInteger)target</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean presentRenderbuffer(int target) {
        if (customClass) { return objc_presentRenderbufferSuper(getSuper(), presentRenderbuffer$, target); } else { return objc_presentRenderbuffer(this, presentRenderbuffer$, target); }
    }
    
    private static final Selector renderbufferStorage$fromDrawable$ = Selector.register("renderbufferStorage:fromDrawable:");
    @Bridge private native static boolean objc_renderbufferStorage(EAGLContext __self__, Selector __cmd__, int target, EAGLDrawable drawable);
    @Bridge private native static boolean objc_renderbufferStorageSuper(ObjCSuper __super__, Selector __cmd__, int target, EAGLDrawable drawable);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../EAGLContext_ClassRef/Reference/EAGLContext.html#//apple_ref/occ/instm/EAGLContext/renderbufferStorage:fromDrawable:">- (BOOL)renderbufferStorage:(NSUInteger)target fromDrawable:(id&amp;lt;EAGLDrawable&amp;gt;)drawable</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean renderbufferStorage(int target, EAGLDrawable drawable) {
        if (customClass) { return objc_renderbufferStorageSuper(getSuper(), renderbufferStorage$fromDrawable$, target, drawable); } else { return objc_renderbufferStorage(this, renderbufferStorage$fromDrawable$, target, drawable); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("API") public static EAGLRenderingAPI getAPI(EAGLContext __self__, Selector __cmd__) { return __self__.getAPI(); }
        @Callback @BindSelector("sharegroup") public static EAGLSharegroup getSharegroup(EAGLContext __self__, Selector __cmd__) { return __self__.getSharegroup(); }
        @Callback @BindSelector("presentRenderbuffer:") public static boolean presentRenderbuffer(EAGLContext __self__, Selector __cmd__, int target) { return __self__.presentRenderbuffer(target); }
        @Callback @BindSelector("renderbufferStorage:fromDrawable:") public static boolean renderbufferStorage(EAGLContext __self__, Selector __cmd__, int target, EAGLDrawable drawable) { return __self__.renderbufferStorage(target, drawable); }
    }
    /*</callbacks>*/

}
