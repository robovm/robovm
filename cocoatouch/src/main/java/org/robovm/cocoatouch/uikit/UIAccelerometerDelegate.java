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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometerDelegate_Protocol/UIAccelerometerDelegate/UIAccelerometerDelegate.html">UIAccelerometerDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIAccelerometerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAccelerometerDelegate_Protocol/DeprecationAppendix/AppendixADeprecatedAPI.html#//apple_ref/occ/intfm/UIAccelerometerDelegate/accelerometer:didAccelerate:">- (void)accelerometer:(UIAccelerometer *)accelerometer didAccelerate:(UIAcceleration *)acceleration</a>
     * @since Available in iOS 2.0 and later.
     */
    void didAccelerate(UIAccelerometer accelerometer, UIAcceleration acceleration);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIAccelerometerDelegate {
        @NotImplemented("accelerometer:didAccelerate:") public void didAccelerate(UIAccelerometer accelerometer, UIAcceleration acceleration) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("accelerometer:didAccelerate:") public static void didAccelerate(UIAccelerometerDelegate __self__, Selector __cmd__, UIAccelerometer accelerometer, UIAcceleration acceleration) { __self__.didAccelerate(accelerometer, acceleration); }
    }
    /*</callbacks>*/

}
