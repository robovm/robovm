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
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge(symbol = "objc_msgSend") private native static UIVideoEditorControllerDelegate objc_getDelegate(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIVideoEditorControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIVideoEditorControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), this, delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDelegate(UIVideoEditorController __self__, Selector __cmd__, UIVideoEditorControllerDelegate delegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDelegateSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__, UIVideoEditorControllerDelegate delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/delegate">@property(nonatomic,assign) id &amp;lt;UINavigationControllerDelegate, UIVideoEditorControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setDelegate(UIVideoEditorControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), this, setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector videoMaximumDuration = Selector.register("videoMaximumDuration");
    @Bridge(symbol = "objc_msgSend") private native static double objc_getVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static double objc_getVideoMaximumDurationSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public double getVideoMaximumDuration() {
        if (customClass) { return objc_getVideoMaximumDurationSuper(getSuper(), this, videoMaximumDuration); } else { return objc_getVideoMaximumDuration(this, videoMaximumDuration); }
    }
    
    private static final Selector setVideoMaximumDuration$ = Selector.register("setVideoMaximumDuration:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setVideoMaximumDuration(UIVideoEditorController __self__, Selector __cmd__, double videoMaximumDuration);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setVideoMaximumDurationSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__, double videoMaximumDuration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoMaximumDuration">@property(nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoMaximumDuration(double videoMaximumDuration) {
        if (customClass) { objc_setVideoMaximumDurationSuper(getSuper(), this, setVideoMaximumDuration$, videoMaximumDuration); } else { objc_setVideoMaximumDuration(this, setVideoMaximumDuration$, videoMaximumDuration); }
    }
    
    private static final Selector videoPath = Selector.register("videoPath");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getVideoPath(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getVideoPathSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public String getVideoPath() {
        if (customClass) { return objc_getVideoPathSuper(getSuper(), this, videoPath); } else { return objc_getVideoPath(this, videoPath); }
    }
    
    private static final Selector setVideoPath$ = Selector.register("setVideoPath:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setVideoPath(UIVideoEditorController __self__, Selector __cmd__, String videoPath);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setVideoPathSuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__, String videoPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoPath">@property(nonatomic, copy) NSString *videoPath</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoPath(String videoPath) {
        if (customClass) { objc_setVideoPathSuper(getSuper(), this, setVideoPath$, videoPath); } else { objc_setVideoPath(this, setVideoPath$, videoPath); }
    }
    
    private static final Selector videoQuality = Selector.register("videoQuality");
    @Bridge(symbol = "objc_msgSend") private native static UIImagePickerControllerQualityType objc_getVideoQuality(UIVideoEditorController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImagePickerControllerQualityType objc_getVideoQualitySuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIImagePickerControllerQualityType getVideoQuality() {
        if (customClass) { return objc_getVideoQualitySuper(getSuper(), this, videoQuality); } else { return objc_getVideoQuality(this, videoQuality); }
    }
    
    private static final Selector setVideoQuality$ = Selector.register("setVideoQuality:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setVideoQuality(UIVideoEditorController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setVideoQualitySuper(ObjCSuper __super__, UIVideoEditorController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIVideoEditorController_ClassReference/Reference/Reference.html#//apple_ref/occ/instp/UIVideoEditorController/videoQuality">@property(nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoQuality(UIImagePickerControllerQualityType videoQuality) {
        if (customClass) { objc_setVideoQualitySuper(getSuper(), this, setVideoQuality$, videoQuality); } else { objc_setVideoQuality(this, setVideoQuality$, videoQuality); }
    }
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
