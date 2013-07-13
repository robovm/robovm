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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSMutableDictionary_Class/Reference/Reference.html">NSMutableDictionary Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSMutableDictionary /*</name>*/ <K extends NSObject, V extends NSObject>
    extends /*<extends>*/ NSDictionary /*</extends>*/ <K, V>
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSMutableDictionary /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSMutableDictionary /*</name>*/.class);

    public NSMutableDictionary() {
        this(16);
    }
    /*<constructors>*/
    protected NSMutableDictionary(SkipInit skipInit) { super(skipInit); }
    
    private static final Selector initWithCapacity$ = Selector.register("initWithCapacity:");
    @Bridge private native static @Pointer long objc_initWithCapacity(NSMutableDictionary __self__, Selector __cmd__, int numItems);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSMutableDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSMutableDictionary/initWithCapacity:">- (id)initWithCapacity:(NSUInteger)numItems</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSMutableDictionary(int numItems) {
        super((SkipInit) null);
        setHandle(objc_initWithCapacity(this, initWithCapacity$, numItems));
    }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    
    private static final Selector dictionaryWithContentsOfFile$ = Selector.register("dictionaryWithContentsOfFile:");
    @Bridge(symbol = "objc_msgSend") private native static NSMutableDictionary objc_fromFile(ObjCClass __self__, Selector __cmd__, String path);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSDictionary_Class/Reference/Reference.html#//apple_ref/occ/clm/NSDictionary/dictionaryWithContentsOfFile:">+ (id)dictionaryWithContentsOfFile:(NSString *)path</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSMutableDictionary fromFile(String path) {
        return objc_fromFile(objCClass, dictionaryWithContentsOfFile$, path);
    }
    
    /*<methods>*/
    
    private static final Selector setObject$forKey$ = Selector.register("setObject:forKey:");
    @Bridge private native static void objc_setObject(NSMutableDictionary __self__, Selector __cmd__, NSObject anObject, NSObject aKey);
    @Bridge private native static void objc_setObjectSuper(ObjCSuper __super__, Selector __cmd__, NSObject anObject, NSObject aKey);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSMutableDictionary_Class/Reference/Reference.html#//apple_ref/occ/instm/NSMutableDictionary/setObject:forKey:">- (void)setObject:(id)anObject forKey:(id &amp;lt; NSCopying &amp;gt;)aKey</a>
     * @since Available in iOS 2.0 and later.
     */
    protected void setObject(NSObject anObject, NSObject aKey) {
        if (customClass) { objc_setObjectSuper(getSuper(), setObject$forKey$, anObject, aKey); } else { objc_setObject(this, setObject$forKey$, anObject, aKey); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("setObject:forKey:") public static void setObject(NSMutableDictionary __self__, Selector __cmd__, NSObject anObject, NSObject aKey) { __self__.setObject(anObject, aKey); }
    }
    /*</callbacks>*/

}
