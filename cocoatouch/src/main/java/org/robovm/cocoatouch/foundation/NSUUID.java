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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../../../../Foundation/Reference/NSUUID_Class/Reference/Reference.html">NSUUID Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("Foundation")/*</library>*/
@NativeClass public class /*<name>*/ NSUUID /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ NSUUID /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ NSUUID /*</name>*/.class);

    /*<constructors>*/
    protected NSUUID(SkipInit skipInit) { super(skipInit); }
    public NSUUID() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector UUIDString = Selector.register("UUIDString");
    @Bridge private native static String objc_asString(NSUUID __self__, Selector __cmd__);
    @Bridge private native static String objc_asStringSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/Cocoa/Reference/Foundation/ObjC_classic/../../../../Foundation/Reference/NSUUID_Class/Reference/Reference.html#//apple_ref/occ/instm/NSUUID/UUIDString">- (NSString *)UUIDString</a>
     * @since Available in iOS 6.0 and later.
     */
    public String asString() {
        if (customClass) { return objc_asStringSuper(getSuper(), UUIDString); } else { return objc_asString(this, UUIDString); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("UUIDString") public static String asString(NSUUID __self__, Selector __cmd__) { return __self__.asString(); }
    }
    /*</callbacks>*/

}
