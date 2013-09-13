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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html">UINib Class Reference</a>
 *   @since Available in iOS 4.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UINib /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINib /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UINib /*</name>*/.class);

    /*<constructors>*/
    protected UINib(SkipInit skipInit) { super(skipInit); }
    public UINib() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector nibWithData$bundle$ = Selector.register("nibWithData:bundle:");
    @Bridge private native static UINib objc_fromData(ObjCClass __self__, Selector __cmd__, NSData data, NSBundle bundleOrNil);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/clm/UINib/nibWithData:bundle:">+ (UINib *)nibWithData:(NSData *)data bundle:(NSBundle *)bundleOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    public static UINib fromData(NSData data, NSBundle bundleOrNil) {
        return objc_fromData(objCClass, nibWithData$bundle$, data, bundleOrNil);
    }
    
    private static final Selector nibWithNibName$bundle$ = Selector.register("nibWithNibName:bundle:");
    @Bridge private native static UINib objc_fromName(ObjCClass __self__, Selector __cmd__, String name, NSBundle bundleOrNil);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/clm/UINib/nibWithNibName:bundle:">+ (UINib *)nibWithNibName:(NSString *)name bundle:(NSBundle *)bundleOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    public static UINib fromName(String name, NSBundle bundleOrNil) {
        return objc_fromName(objCClass, nibWithNibName$bundle$, name, bundleOrNil);
    }
    
    private static final Selector instantiateWithOwner$options$ = Selector.register("instantiateWithOwner:options:");
    @Bridge private native static NSArray objc_instantiate(UINib __self__, Selector __cmd__, NSObject ownerOrNil, NSDictionary optionsOrNil);
    @Bridge private native static NSArray objc_instantiateSuper(ObjCSuper __super__, Selector __cmd__, NSObject ownerOrNil, NSDictionary optionsOrNil);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINib_Ref/Reference/Reference.html#//apple_ref/occ/instm/UINib/instantiateWithOwner:options:">- (NSArray *)instantiateWithOwner:(id)ownerOrNil options:(NSDictionary *)optionsOrNil</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSArray instantiate(NSObject ownerOrNil, NSDictionary optionsOrNil) {
        if (customClass) { return objc_instantiateSuper(getSuper(), instantiateWithOwner$options$, ownerOrNil, optionsOrNil); } else { return objc_instantiate(this, instantiateWithOwner$options$, ownerOrNil, optionsOrNil); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("instantiateWithOwner:options:") public static NSArray instantiate(UINib __self__, Selector __cmd__, NSObject ownerOrNil, NSDictionary optionsOrNil) { return __self__.instantiate(ownerOrNil, optionsOrNil); }
    }
    /*</callbacks>*/

}
