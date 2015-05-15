/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.coremotion;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreMotion") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMotionActivityManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMMotionActivityManagerPtr extends Ptr<CMMotionActivityManager, CMMotionActivityManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CMMotionActivityManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMMotionActivityManager() {}
    protected CMMotionActivityManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "queryActivityStartingFromDate:toDate:toQueue:withHandler:")
    public native void queryActivity(NSDate start, NSDate end, NSOperationQueue queue, @Block VoidBlock2<NSArray<CMMotionActivity>, NSError> handler);
    @Method(selector = "startActivityUpdatesToQueue:withHandler:")
    public native void startActivityUpdates(NSOperationQueue queue, @Block VoidBlock1<CMMotionActivity> handler);
    @Method(selector = "stopActivityUpdates")
    public native void stopActivityUpdates();
    @Method(selector = "isActivityAvailable")
    public static native boolean isActivityAvailable();
    /*</methods>*/
}
