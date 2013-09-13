/*
 * Copyright (C) 2012 Trillian AB
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
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html">UIStoryboardSegue Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIStoryboardSegue /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIStoryboardSegue /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIStoryboardSegue /*</name>*/.class);

    /*<constructors>*/
    protected UIStoryboardSegue(SkipInit skipInit) { super(skipInit); }
    public UIStoryboardSegue() {}
    
    private static final Selector initWithIdentifier$source$destination$ = Selector.register("initWithIdentifier:source:destination:");
    @Bridge private native static @Pointer long objc_initWithIdentifier(UIStoryboardSegue __self__, Selector __cmd__, String identifier, UIViewController source, UIViewController destination);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStoryboardSegue/initWithIdentifier:source:destination:">- (id)initWithIdentifier:(NSString *)identifier source:(UIViewController *)source destination:(UIViewController *)destination</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIStoryboardSegue(String identifier, UIViewController source, UIViewController destination) {
        super((SkipInit) null);
        initObject(objc_initWithIdentifier(this, initWithIdentifier$source$destination$, identifier, source, destination));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector destinationViewController = Selector.register("destinationViewController");
    @Bridge private native static NSObject objc_getDestinationViewController(UIStoryboardSegue __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getDestinationViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStoryboardSegue/destinationViewController">@property(nonatomic, readonly) id destinationViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getDestinationViewController() {
        if (customClass) { return objc_getDestinationViewControllerSuper(getSuper(), destinationViewController); } else { return objc_getDestinationViewController(this, destinationViewController); }
    }
    
    private static final Selector identifier = Selector.register("identifier");
    @Bridge private native static String objc_getIdentifier(UIStoryboardSegue __self__, Selector __cmd__);
    @Bridge private native static String objc_getIdentifierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStoryboardSegue/identifier">@property(nonatomic, readonly) NSString *identifier</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getIdentifier() {
        if (customClass) { return objc_getIdentifierSuper(getSuper(), identifier); } else { return objc_getIdentifier(this, identifier); }
    }
    
    private static final Selector sourceViewController = Selector.register("sourceViewController");
    @Bridge private native static NSObject objc_getSourceViewController(UIStoryboardSegue __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getSourceViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStoryboardSegue/sourceViewController">@property(nonatomic, readonly) id sourceViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getSourceViewController() {
        if (customClass) { return objc_getSourceViewControllerSuper(getSuper(), sourceViewController); } else { return objc_getSourceViewController(this, sourceViewController); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector segueWithIdentifier$source$destination$performHandler$ = Selector.register("segueWithIdentifier:source:destination:performHandler:");
    @Bridge private native static NSObject objc_getSegue(ObjCClass __self__, Selector __cmd__, String identifier, UIViewController source, UIViewController destination, VoidBlock performHandler);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/clm/UIStoryboardSegue/segueWithIdentifier:source:destination:performHandler:">+ (id)segueWithIdentifier:(NSString *)identifier source:(UIViewController *)source destination:(UIViewController *)destination performHandler:(void (^)(void))performHandler</a>
     * @since Available in iOS 6.0 and later.
     */
    public static NSObject getSegue(String identifier, UIViewController source, UIViewController destination, VoidBlock performHandler) {
        return objc_getSegue(objCClass, segueWithIdentifier$source$destination$performHandler$, identifier, source, destination, performHandler);
    }
    
    private static final Selector perform = Selector.register("perform");
    @Bridge private native static void objc_perform(UIStoryboardSegue __self__, Selector __cmd__);
    @Bridge private native static void objc_performSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStoryboardSegue_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStoryboardSegue/perform">- (void)perform</a>
     * @since Available in iOS 5.0 and later.
     */
    public void perform() {
        if (customClass) { objc_performSuper(getSuper(), perform); } else { objc_perform(this, perform); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("destinationViewController") public static NSObject getDestinationViewController(UIStoryboardSegue __self__, Selector __cmd__) { return __self__.getDestinationViewController(); }
        @Callback @BindSelector("identifier") public static String getIdentifier(UIStoryboardSegue __self__, Selector __cmd__) { return __self__.getIdentifier(); }
        @Callback @BindSelector("sourceViewController") public static NSObject getSourceViewController(UIStoryboardSegue __self__, Selector __cmd__) { return __self__.getSourceViewController(); }
        @Callback @BindSelector("perform") public static void perform(UIStoryboardSegue __self__, Selector __cmd__) { __self__.perform(); }
    }
    /*</callbacks>*/

}
