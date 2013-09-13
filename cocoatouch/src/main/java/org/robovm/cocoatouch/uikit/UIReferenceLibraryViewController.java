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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIReferenceLibraryViewControllerClassRef/Reference/Reference.html">UIReferenceLibraryViewController Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIReferenceLibraryViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIReferenceLibraryViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIReferenceLibraryViewController /*</name>*/.class);

    /*<constructors>*/
    protected UIReferenceLibraryViewController(SkipInit skipInit) { super(skipInit); }
    public UIReferenceLibraryViewController() {}
    
    private static final Selector initWithTerm$ = Selector.register("initWithTerm:");
    @Bridge private native static @Pointer long objc_initWithTerm(UIReferenceLibraryViewController __self__, Selector __cmd__, String term);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIReferenceLibraryViewControllerClassRef/Reference/Reference.html#//apple_ref/occ/instm/UIReferenceLibraryViewController/initWithTerm:">- (id)initWithTerm:(NSString *)term</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIReferenceLibraryViewController(String term) {
        super((SkipInit) null);
        initObject(objc_initWithTerm(this, initWithTerm$, term));
    }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector dictionaryHasDefinitionForTerm$ = Selector.register("dictionaryHasDefinitionForTerm:");
    @Bridge private native static boolean objc_dictionaryHasDefinitionForTerm(ObjCClass __self__, Selector __cmd__, String term);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIReferenceLibraryViewControllerClassRef/Reference/Reference.html#//apple_ref/occ/clm/UIReferenceLibraryViewController/dictionaryHasDefinitionForTerm:">+ (BOOL)dictionaryHasDefinitionForTerm:(NSString *)term</a>
     * @since Available in iOS 5.0 and later.
     */
    public static boolean dictionaryHasDefinitionForTerm(String term) {
        return objc_dictionaryHasDefinitionForTerm(objCClass, dictionaryHasDefinitionForTerm$, term);
    }
    /*</methods>*/
    /*<callbacks>*/
    /*</callbacks>*/

}
