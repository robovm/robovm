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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewDelegate_ProtocolRef/Reference/Reference.html">GLKViewDelegate Protocol Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
public interface /*<name>*/ GLKViewDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/GLkit/Reference/GLKit_Collection/../GLKViewDelegate_ProtocolRef/Reference/Reference.html#//apple_ref/occ/intfm/GLKViewDelegate/glkView:drawInRect:">- (void)glkView:(GLKView *)view drawInRect:(CGRect)rect</a>
     * @since Available in iOS 5.0 and later.
     */
    void draw(GLKView view, CGRect rect);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements GLKViewDelegate {
        @NotImplemented("glkView:drawInRect:") public void draw(GLKView view, CGRect rect) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("glkView:drawInRect:") public static void draw(GLKViewDelegate __self__, Selector __cmd__, GLKView view, @ByVal CGRect rect) { __self__.draw(view, rect); }
    }
    /*</callbacks>*/

}
