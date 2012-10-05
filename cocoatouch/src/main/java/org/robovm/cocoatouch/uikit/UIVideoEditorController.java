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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html">UIVideoEditorController Class Reference</a>
 *   @since Available in iOS 3.1 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIVideoEditorController /*</name>*/ 
    extends /*<extends>*/ UINavigationController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIVideoEditorController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIVideoEditorController /*</name>*/.class);

    /*<constructors>*/
    protected UIVideoEditorController(SkipInit skipInit) { super(skipInit); }
    public UIVideoEditorController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("delegate") public native UIVideoEditorControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIVideoEditorControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoMaximumDuration") public native double getVideoMaximumDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoMaximumDuration:") public native void setVideoMaximumDuration(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoPath") public native String getVideoPath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoPath:") public native void setVideoPath(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoQuality") public native UIImagePickerControllerQualityType getVideoQuality();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoQuality:") public native void setVideoQuality(UIImagePickerControllerQualityType v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector canEditVideoAtPath$ = Selector.register("canEditVideoAtPath:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canEditVideo(ObjCClass __self__, Selector __cmd__, String videoPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/clm/UIVideoEditorController/canEditVideoAtPath:">+ (BOOL)canEditVideoAtPath:(NSString *)videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public static boolean canEditVideo(String videoPath) {
        return objc_canEditVideo(objCClass, canEditVideoAtPath$, videoPath);
    }
    /*</methods>*/

}
