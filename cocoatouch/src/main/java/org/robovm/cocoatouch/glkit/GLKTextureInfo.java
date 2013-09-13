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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html">GLKTextureInfo Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("GLKit")/*</library>*/
@NativeClass public class /*<name>*/ GLKTextureInfo /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ GLKTextureInfo /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ GLKTextureInfo /*</name>*/.class);

    /*<constructors>*/
    protected GLKTextureInfo(SkipInit skipInit) { super(skipInit); }
    public GLKTextureInfo() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alphaState = Selector.register("alphaState");
    @Bridge private native static GLKTextureInfoAlphaState objc_getAlphaState(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static GLKTextureInfoAlphaState objc_getAlphaStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/alphaState">@property(readonly) GLKTextureInfoAlphaState alphaState</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureInfoAlphaState getAlphaState() {
        if (customClass) { return objc_getAlphaStateSuper(getSuper(), alphaState); } else { return objc_getAlphaState(this, alphaState); }
    }
    
    private static final Selector containsMipmaps = Selector.register("containsMipmaps");
    @Bridge private native static boolean objc_isContainsMipmaps(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isContainsMipmapsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/containsMipmaps">@property(readonly) BOOL containsMipmaps</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isContainsMipmaps() {
        if (customClass) { return objc_isContainsMipmapsSuper(getSuper(), containsMipmaps); } else { return objc_isContainsMipmaps(this, containsMipmaps); }
    }
    
    private static final Selector height = Selector.register("height");
    @Bridge private native static int objc_getHeight(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static int objc_getHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/height">@property(readonly) GLuint height</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getHeight() {
        if (customClass) { return objc_getHeightSuper(getSuper(), height); } else { return objc_getHeight(this, height); }
    }
    
    private static final Selector name = Selector.register("name");
    @Bridge private native static int objc_getName(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static int objc_getNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/name">@property(readonly) GLuint name</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getName() {
        if (customClass) { return objc_getNameSuper(getSuper(), name); } else { return objc_getName(this, name); }
    }
    
    private static final Selector target = Selector.register("target");
    @Bridge private native static int objc_getTarget(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static int objc_getTargetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/target">@property(readonly) GLenum target</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getTarget() {
        if (customClass) { return objc_getTargetSuper(getSuper(), target); } else { return objc_getTarget(this, target); }
    }
    
    private static final Selector textureOrigin = Selector.register("textureOrigin");
    @Bridge private native static GLKTextureInfoOrigin objc_getTextureOrigin(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static GLKTextureInfoOrigin objc_getTextureOriginSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/textureOrigin">@property(readonly) GLKTextureInfoOrigin textureOrigin</a>
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureInfoOrigin getTextureOrigin() {
        if (customClass) { return objc_getTextureOriginSuper(getSuper(), textureOrigin); } else { return objc_getTextureOrigin(this, textureOrigin); }
    }
    
    private static final Selector width = Selector.register("width");
    @Bridge private native static int objc_getWidth(GLKTextureInfo __self__, Selector __cmd__);
    @Bridge private native static int objc_getWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKTextureInfo_Ref/Reference/Reference.html#//apple_ref/occ/instp/GLKTextureInfo/width">@property(readonly) GLuint width</a>
     * @since Available in iOS 5.0 and later.
     */
    public int getWidth() {
        if (customClass) { return objc_getWidthSuper(getSuper(), width); } else { return objc_getWidth(this, width); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alphaState") public static GLKTextureInfoAlphaState getAlphaState(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getAlphaState(); }
        @Callback @BindSelector("containsMipmaps") public static boolean isContainsMipmaps(GLKTextureInfo __self__, Selector __cmd__) { return __self__.isContainsMipmaps(); }
        @Callback @BindSelector("height") public static int getHeight(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getHeight(); }
        @Callback @BindSelector("name") public static int getName(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getName(); }
        @Callback @BindSelector("target") public static int getTarget(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getTarget(); }
        @Callback @BindSelector("textureOrigin") public static GLKTextureInfoOrigin getTextureOrigin(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getTextureOrigin(); }
        @Callback @BindSelector("width") public static int getWidth(GLKTextureInfo __self__, Selector __cmd__) { return __self__.getWidth(); }
    }
    /*</callbacks>*/

}
