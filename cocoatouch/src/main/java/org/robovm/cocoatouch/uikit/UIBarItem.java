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
public class /*<name>*/ UIBarItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UIAppearance /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBarItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UIBarItem() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    @Bind("imageInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getImageInsets();
    @Bind("setImageInsets:") public native void setImageInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("landscapeImagePhone") public native @Type("UIImage *") UIImage getLandscapeImagePhone();
    @Bind("setLandscapeImagePhone:") public native void setLandscapeImagePhone(@Type("UIImage *") UIImage v);
    @Bind("landscapeImagePhoneInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getLandscapeImagePhoneInsets();
    @Bind("setLandscapeImagePhoneInsets:") public native void setLandscapeImagePhoneInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("tag") public native @Type("NSInteger") int getTag();
    @Bind("setTag:") public native void setTag(@Type("NSInteger") int v);
    @Bind("title") public native @Type("NSString *") String getTitle();
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    @Bind("titleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes(@Type("UIControlState") UIControlState state);
    @Bind("setTitleTextAttributes:forState:") public native @Type("void") void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
