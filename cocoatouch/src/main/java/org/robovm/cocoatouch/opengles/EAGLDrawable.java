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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../../../iPhone/Reference/EAGLDrawable_Ref/EAGLDrawable/EAGLDrawable.html">EAGLDrawable Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ EAGLDrawable /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../../../iPhone/Reference/EAGLDrawable_Ref/EAGLDrawable/EAGLDrawable.html#//apple_ref/occ/intfp/EAGLDrawable/drawableProperties">@property(copy) NSDictionary* drawableProperties;</a>
     * @since Available in iOS 2.0 and later.
     */
    NSDictionary getDrawableProperties();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/opengles/reference/opengles_framework/../../../iPhone/Reference/EAGLDrawable_Ref/EAGLDrawable/EAGLDrawable.html#//apple_ref/occ/intfp/EAGLDrawable/drawableProperties">@property(copy) NSDictionary* drawableProperties;</a>
     * @since Available in iOS 2.0 and later.
     */
    void setDrawableProperties(NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements EAGLDrawable {
        @NotImplemented("drawableProperties") public NSDictionary getDrawableProperties() { throw new UnsupportedOperationException(); }
        @NotImplemented("setDrawableProperties:") public void setDrawableProperties(NSDictionary drawableProperties) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("drawableProperties") public static NSDictionary getDrawableProperties(EAGLDrawable __self__, Selector __cmd__) { return __self__.getDrawableProperties(); }
        @Callback @BindSelector("setDrawableProperties:") public static void setDrawableProperties(EAGLDrawable __self__, Selector __cmd__, NSDictionary drawableProperties) { __self__.setDrawableProperties(drawableProperties); }
    }
    /*</callbacks>*/

}
