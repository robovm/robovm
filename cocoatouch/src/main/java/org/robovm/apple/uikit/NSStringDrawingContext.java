/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStringDrawingContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSStringDrawingContextPtr extends Ptr<NSStringDrawingContext, NSStringDrawingContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSStringDrawingContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSStringDrawingContext() {}
    protected NSStringDrawingContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minimumScaleFactor")
    public native @MachineSizedFloat double getMinimumScaleFactor();
    @Property(selector = "setMinimumScaleFactor:")
    public native void setMinimumScaleFactor(@MachineSizedFloat double v);
    @Property(selector = "minimumTrackingAdjustment")
    public native @MachineSizedFloat double getMinimumTrackingAdjustment();
    @Property(selector = "setMinimumTrackingAdjustment:")
    public native void setMinimumTrackingAdjustment(@MachineSizedFloat double v);
    @Property(selector = "actualScaleFactor")
    public native @MachineSizedFloat double getActualScaleFactor();
    @Property(selector = "actualTrackingAdjustment")
    public native @MachineSizedFloat double getActualTrackingAdjustment();
    @Property(selector = "totalBounds")
    public native @ByVal CGRect getTotalBounds();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
