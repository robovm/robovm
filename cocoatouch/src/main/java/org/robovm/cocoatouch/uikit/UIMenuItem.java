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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html">UIMenuItem Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIMenuItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIMenuItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UIMenuItem() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instm/UIMenuItem/initWithTitle:action:">- (id)initWithTitle:(NSString *)title action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("initWithTitle:action:") public UIMenuItem(@Type("NSString *") String title, @Type("SEL") Selector action) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/action">@property SEL action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("action") public native @Type("SEL") Selector getAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/action">@property SEL action</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setAction:") public native void setAction(@Type("SEL") Selector v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/title">@property(copy) NSString *title</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/title">@property(copy) NSString *title</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
