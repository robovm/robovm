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
import org.robovm.objc.annotation.*;
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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIMenuItem /*</name>*/.class);

    /*<constructors>*/
    protected UIMenuItem(SkipInit skipInit) { super(skipInit); }
    public UIMenuItem() {}
    
    private static final Selector initWithTitle$action$ = Selector.register("initWithTitle:action:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTitle(UIMenuItem __self__, Selector __cmd__, String title, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instm/UIMenuItem/initWithTitle:action:">- (id)initWithTitle:(NSString *)title action:(SEL)action</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIMenuItem(String title, Selector action) {
        super((SkipInit) null);
        objc_initWithTitle(this, initWithTitle$action$, title, action);
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector action = Selector.register("action");
    @Bridge(symbol = "objc_msgSend") private native static Selector objc_getAction(UIMenuItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static Selector objc_getActionSuper(ObjCSuper __super__, UIMenuItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/action">@property SEL action</a>
     * @since Available in iOS 3.2 and later.
     */
    public Selector getAction() {
        if (customClass) { return objc_getActionSuper(getSuper(), this, action); } else { return objc_getAction(this, action); }
    }
    
    private static final Selector setAction$ = Selector.register("setAction:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAction(UIMenuItem __self__, Selector __cmd__, Selector action);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setActionSuper(ObjCSuper __super__, UIMenuItem __self__, Selector __cmd__, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/action">@property SEL action</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setAction(Selector action) {
        if (customClass) { objc_setActionSuper(getSuper(), this, setAction$, action); } else { objc_setAction(this, setAction$, action); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getTitle(UIMenuItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTitleSuper(ObjCSuper __super__, UIMenuItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/title">@property(copy) NSString *title</a>
     * @since Available in iOS 3.2 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), this, title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitle(UIMenuItem __self__, Selector __cmd__, String title);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleSuper(ObjCSuper __super__, UIMenuItem __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIMenuItem_Class/Reference/MenuItem.html#//apple_ref/occ/instp/UIMenuItem/title">@property(copy) NSString *title</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), this, setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    /*</callbacks>*/

}
