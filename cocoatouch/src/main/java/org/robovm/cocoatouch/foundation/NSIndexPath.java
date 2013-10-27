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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html">NSIndexPath Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSIndexPath /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSIndexPath /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSIndexPath /*</name>*/.class);

    /*<constructors>*/
    protected NSIndexPath(SkipInit skipInit) { super(skipInit); }
    public NSIndexPath() {}
    
    private static final Selector initWithIndex$ = Selector.register("initWithIndex:");
    @Bridge private native static @Pointer long objc_initWithIndex(NSIndexPath __self__, Selector __cmd__, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html#//apple_ref/occ/instm/NSIndexPath/initWithIndex:">- (instancetype)initWithIndex:(NSUInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath(int index) {
        super((SkipInit) null);
        initObject(objc_initWithIndex(this, initWithIndex$, index));
    }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    
    private static final Selector getSection = Selector.register("section");
    @Bridge private native static int objc_getSection(NSIndexPath __self__, Selector __cmd__);
    @Bridge private native static int objc_getSectionSuper(ObjCSuper __super__, Selector __cmd__);
    public int getSection() {
        if (customClass) { return objc_getSectionSuper(getSuper(), getSection); } else { return objc_getSection(this, getSection); }
    }
    private static final Selector getRow = Selector.register("row");
    @Bridge private native static int objc_getRow(NSIndexPath __self__, Selector __cmd__);
    @Bridge private native static int objc_getRowSuper(ObjCSuper __super__, Selector __cmd__);
    public int getRow() {
        if (customClass) { return objc_getRowSuper(getSuper(), getRow); } else { return objc_getRow(this, getRow); }
    }
    private static final Selector getItem = Selector.register("item");
    @Bridge private native static int objc_getItem(NSIndexPath __self__, Selector __cmd__);
    @Bridge private native static int objc_getItemSuper(ObjCSuper __super__, Selector __cmd__);
    public int getItem() {
        if (customClass) { return objc_getItemSuper(getSuper(), getItem); } else { return objc_getItem(this, getItem); }
    }
    
    /*<methods>*/
    
    private static final Selector indexPathByAddingIndex$ = Selector.register("indexPathByAddingIndex:");
    @Bridge private native static NSIndexPath objc_addIndex(NSIndexPath __self__, Selector __cmd__, int index);
    @Bridge private native static NSIndexPath objc_addIndexSuper(ObjCSuper __super__, Selector __cmd__, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html#//apple_ref/occ/instm/NSIndexPath/indexPathByAddingIndex:">- (NSIndexPath *)indexPathByAddingIndex:(NSUInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath addIndex(int index) {
        if (customClass) { return objc_addIndexSuper(getSuper(), indexPathByAddingIndex$, index); } else { return objc_addIndex(this, indexPathByAddingIndex$, index); }
    }
    
    private static final Selector indexAtPosition$ = Selector.register("indexAtPosition:");
    @Bridge private native static int objc_getIndexAt(NSIndexPath __self__, Selector __cmd__, int node);
    @Bridge private native static int objc_getIndexAtSuper(ObjCSuper __super__, Selector __cmd__, int node);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html#//apple_ref/occ/instm/NSIndexPath/indexAtPosition:">- (NSUInteger)indexAtPosition:(NSUInteger)node</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getIndexAt(int node) {
        if (customClass) { return objc_getIndexAtSuper(getSuper(), indexAtPosition$, node); } else { return objc_getIndexAt(this, indexAtPosition$, node); }
    }
    
    private static final Selector length = Selector.register("length");
    @Bridge private native static int objc_getLength(NSIndexPath __self__, Selector __cmd__);
    @Bridge private native static int objc_getLengthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html#//apple_ref/occ/instm/NSIndexPath/length">- (NSUInteger)length</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getLength() {
        if (customClass) { return objc_getLengthSuper(getSuper(), length); } else { return objc_getLength(this, length); }
    }
    
    private static final Selector indexPathByRemovingLastIndex = Selector.register("indexPathByRemovingLastIndex");
    @Bridge private native static NSIndexPath objc_removeLastIndex(NSIndexPath __self__, Selector __cmd__);
    @Bridge private native static NSIndexPath objc_removeLastIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSIndexPath_Class/Reference/Reference.html#//apple_ref/occ/instm/NSIndexPath/indexPathByRemovingLastIndex">- (NSIndexPath *)indexPathByRemovingLastIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSIndexPath removeLastIndex() {
        if (customClass) { return objc_removeLastIndexSuper(getSuper(), indexPathByRemovingLastIndex); } else { return objc_removeLastIndex(this, indexPathByRemovingLastIndex); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("indexPathByAddingIndex:") public static NSIndexPath addIndex(NSIndexPath __self__, Selector __cmd__, int index) { return __self__.addIndex(index); }
        @Callback @BindSelector("indexAtPosition:") public static int getIndexAt(NSIndexPath __self__, Selector __cmd__, int node) { return __self__.getIndexAt(node); }
        @Callback @BindSelector("length") public static int getLength(NSIndexPath __self__, Selector __cmd__) { return __self__.getLength(); }
        @Callback @BindSelector("indexPathByRemovingLastIndex") public static NSIndexPath removeLastIndex(NSIndexPath __self__, Selector __cmd__) { return __self__.removeLastIndex(); }
    }
    /*</callbacks>*/

}
