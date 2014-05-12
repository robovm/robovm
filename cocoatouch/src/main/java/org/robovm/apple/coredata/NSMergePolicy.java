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
package org.robovm.apple.coredata;

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
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMergePolicy/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMergePolicyPtr extends Ptr<NSMergePolicy, NSMergePolicyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMergePolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMergePolicy() {}
    protected NSMergePolicy(SkipInit skipInit) { super(skipInit); }
    public NSMergePolicy(NSMergePolicyType ty) { super((SkipInit) null); initObject(initWithMergeType$(ty)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "mergeType")
    public native NSMergePolicyType getMergeType();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithMergeType:")
    protected native @Pointer long initWithMergeType$(NSMergePolicyType ty);
    @Method(selector = "resolveConflicts:error:")
    public native boolean resolveConflicts$error$(NSArray<?> list, NSError.NSErrorPtr error);
    /*</methods>*/
}
