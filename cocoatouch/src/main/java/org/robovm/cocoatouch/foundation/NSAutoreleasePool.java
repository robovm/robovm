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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSAutoreleasePool_Class/Reference/Reference.html">NSAutoreleasePool Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSAutoreleasePool /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSAutoreleasePool /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSAutoreleasePool /*</name>*/.class);

    /*<constructors>*/
    protected NSAutoreleasePool(SkipInit skipInit) { super(skipInit); }
    public NSAutoreleasePool() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector drain = Selector.register("drain");
    @Bridge private native static void objc_drain(NSAutoreleasePool __self__, Selector __cmd__);
    @Bridge private native static void objc_drainSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../Classes/NSAutoreleasePool_Class/Reference/Reference.html#//apple_ref/occ/instm/NSAutoreleasePool/drain">- (void)drain</a>
     * @since Available in iOS 2.0 and later.
     */
    public void drain() {
        if (customClass) { objc_drainSuper(getSuper(), drain); } else { objc_drain(this, drain); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("drain") public static void drain(NSAutoreleasePool __self__, Selector __cmd__) { __self__.drain(); }
    }
    /*</callbacks>*/

}
