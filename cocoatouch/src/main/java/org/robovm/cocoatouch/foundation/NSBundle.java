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
package org.robovm.cocoatouch.foundation;

/*<imports>*/
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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSBundle_Class/Reference/Reference.html">NSBundle Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSBundle /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSBundle /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSBundle /*</name>*/.class);

    /*<constructors>*/
    protected NSBundle(SkipInit skipInit) { super(skipInit); }
    public NSBundle() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector mainBundle = Selector.register("mainBundle");
    @Bridge private native static NSBundle objc_getMainBundle(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSBundle_Class/Reference/Reference.html#//apple_ref/occ/clm/NSBundle/mainBundle">+ (NSBundle *)mainBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSBundle getMainBundle() {
        return objc_getMainBundle(objCClass, mainBundle);
    }
    
    private static final Selector bundlePath = Selector.register("bundlePath");
    @Bridge private native static String objc_getBundlePath(NSBundle __self__, Selector __cmd__);
    @Bridge private native static String objc_getBundlePathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSBundle_Class/Reference/Reference.html#//apple_ref/occ/instm/NSBundle/bundlePath">- (NSString *)bundlePath</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getBundlePath() {
        if (customClass) { return objc_getBundlePathSuper(getSuper(), bundlePath); } else { return objc_getBundlePath(this, bundlePath); }
    }
    
    private static final Selector infoDictionary = Selector.register("infoDictionary");
    @Bridge private native static NSDictionary objc_getInfoDictionary(NSBundle __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_getInfoDictionarySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSBundle_Class/Reference/Reference.html#//apple_ref/occ/instm/NSBundle/infoDictionary">- (NSDictionary *)infoDictionary</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSDictionary getInfoDictionary() {
        if (customClass) { return objc_getInfoDictionarySuper(getSuper(), infoDictionary); } else { return objc_getInfoDictionary(this, infoDictionary); }
    }
    
    private static final Selector resourcePath = Selector.register("resourcePath");
    @Bridge private native static String objc_getResourcePath(NSBundle __self__, Selector __cmd__);
    @Bridge private native static String objc_getResourcePathSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSBundle_Class/Reference/Reference.html#//apple_ref/occ/instm/NSBundle/resourcePath">- (NSString *)resourcePath</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getResourcePath() {
        if (customClass) { return objc_getResourcePathSuper(getSuper(), resourcePath); } else { return objc_getResourcePath(this, resourcePath); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("bundlePath") public static String getBundlePath(NSBundle __self__, Selector __cmd__) { return __self__.getBundlePath(); }
        @Callback @BindSelector("infoDictionary") public static NSDictionary getInfoDictionary(NSBundle __self__, Selector __cmd__) { return __self__.getInfoDictionary(); }
        @Callback @BindSelector("resourcePath") public static String getResourcePath(NSBundle __self__, Selector __cmd__) { return __self__.getResourcePath(); }
    }
    /*</callbacks>*/

}
