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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIVideoEditorController /*</name>*/ 
    extends /*<extends>*/ UINavigationController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIVideoEditorController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIVideoEditorController() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("delegate") public native @Type("id <UINavigationControllerDelegate, UIVideoEditorControllerDelegate>") UIVideoEditorControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id <UINavigationControllerDelegate, UIVideoEditorControllerDelegate>") UIVideoEditorControllerDelegate v);
    @Bind("videoMaximumDuration") public native @Type("NSTimeInterval") double getVideoMaximumDuration();
    @Bind("setVideoMaximumDuration:") public native void setVideoMaximumDuration(@Type("NSTimeInterval") double v);
    @Bind("videoPath") public native @Type("NSString *") String getVideoPath();
    @Bind("setVideoPath:") public native void setVideoPath(@Type("NSString *") String v);
    @Bind("videoQuality") public native @Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType getVideoQuality();
    @Bind("setVideoQuality:") public native void setVideoQuality(@Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType v);
    /*</properties>*/
    /*<methods>*/
    @Bind("canEditVideoAtPath:") public native static @Type("BOOL") boolean canEditVideo(@Type("NSString *") String videoPath);
    /*</methods>*/

}
